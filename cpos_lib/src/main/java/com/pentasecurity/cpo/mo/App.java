package com.pentasecurity.cpo.mo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.penta.ptls.Constant.Transport;
import com.penta.ptls.PtlsClient;
import com.pentasecurity.cpo.mo.MoAdapter;
import com.pentasecurity.cpo.mo.model.*;
import com.pentasecurity.cpo.mo.model.ServiceEmaidResponse.EmaidInfo;
import com.pentasecurity.cpo.mo.model.ServiceTariffResponse.TariffInfo;
import com.pentasecurity.cpo.mo.util.Json;

public final class App {

    private MoAdapter mo;
    private V2gAdapter v2g;

    List<ServiceEmaidResponse.EmaidInfo> emaidList;

    List<ServiceTariffResponse.TariffInfo> tariffList;

    public App(String moHostName, String moPort, String v2gHostName, String v2gPort) {
        try {
            this.mo = new MoAdapter(moHostName, moPort);
            this.v2g = new V2gAdapter(v2gHostName, v2gPort);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    
    public App(String hostName, String moPort) {
        try {
            this.mo = new MoAdapter(hostName, moPort);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public App(String hostName, String moPort, String tlsPort, String key, String crt, String cas) {
        try {
            this.mo = new MoAdapter(hostName, moPort, tlsPort, key, crt, cas);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private void runMainCommand(BufferedReader br) throws Exception {
        String command = "";
        while (command.equals("0") == false) {
            System.out.println("");
            System.out.println("=====================");
            System.out.println("1 : get eMAID list");
            System.out.println("2 : show eMAID list");
            System.out.println("3 : verify contract");
            System.out.println("4 : get tariff list");
            System.out.println("5 : show tariff list");
            System.out.println("6 : push charge sales");
            System.out.println("7 : Issue TLS Certificate");
            System.out.println("8 : OCSP Request");
            System.out.println("0 : exit");
            System.out.println("=====================");
            System.out.print("select command > ");

            command = br.readLine();
            if (command.equals("1")) {
                runCommandGetEmaidList(br);
            } else if (command.equals("2")) {
                runCommandShowEmaidList();
            } else if (command.equals("3")) {
                runCommandVerifyContract(br);
            } else if (command.equals("4")) {
                runCommandGetTariffList(br);
            } else if (command.equals("5")) {
                runCommandShowTariffList();
            } else if (command.equals("6")) {
                runCommandPushChargeSales(br);
            } else if (command.equals("7")) {
            	runCommandIssueTlsCertificate(br);
            } else if (command.equals("8")) {
//            	runCommandOcspRequest(br);
            }
        }
    }

    private void runCommandGetEmaidList(BufferedReader br) throws Exception {
        System.out.print("input time or blank (ex : 2019-04-31 09:00:00) > ");
        String timeAfter = br.readLine();

        String timeAfterJson = "";
        if (timeAfter.length() != 0) {
            timeAfterJson = "{\"timeAfter\":\"" + timeAfter + "\"}";
        }

        ServiceEmaidResponse response = this.mo.requestEmaid(timeAfterJson);
        if (response == null) {
            System.out.println("Server Returned Error Message");
            return;
        }

        if (response.getResult() == ResultCode.SUCCESS) {
            this.emaidList = response.getEmaidList();
            if (this.emaidList != null) {
                System.out.println("" + this.emaidList.size() + " eMAID received.");
            } else {
                System.out.println(" 0 eMAID received.");
            }
        } else {
            System.out.println("get eMAID request failed : " + response.getResult());
        }
    }

    private void runCommandShowEmaidList() {
        System.out.println("");
        if (this.emaidList != null) {
            System.out.println("current eMAID list : " + this.emaidList.size());
            for (EmaidInfo emaidInfo : this.emaidList) {
                System.out.println("  - " + emaidInfo.getEmaid() + " (" + emaidInfo.getStatus() + ")");
            }
        } else {
            System.out.println("no eMAID list");
        }
    }

    private void runCommandVerifyContract(BufferedReader br) throws Exception {
        System.out.print("input emaid > ");
        String emaid = br.readLine();
        if (emaid.length() == 0) {
            return;
        }

        System.out.print("input OCSP request > ");
        String ocspReq = br.readLine();
        if (ocspReq.length() == 0) {
            return;
        }

        System.out.print("input service code(ex, charging) > ");
        String serviceCodeStr = br.readLine();
        if (serviceCodeStr.length() == 0 ) {
            return;
        }
        System.out.println("");

        ContractVerifyServiceCode serviceCode = ContractVerifyServiceCode.Unknown;
        if (serviceCodeStr.equals("charging")) {
            serviceCode = ContractVerifyServiceCode.Charging;
        }

        ContractVerifyRequest request = new ContractVerifyRequest(emaid, ocspReq, serviceCode);
        ContractVerifyResponse response = this.mo.requestVerifContract(request);
        if (response == null) {
            System.out.println("Server Returned Error Message");
            return;
        }

        if (response.getResult() == ResultCode.SUCCESS) {
            System.out.println("verify request success : " + response.getVerifyResult().name());
        } else {
            System.out.println("verify request failed : " + response.getResult());
        }
    }

    private void runCommandGetTariffList(BufferedReader br) throws Exception {
    	List<Integer> tupleIdList = new ArrayList<>();
    	List<PmaxSchedule> pmaxScheduleList =  new ArrayList<>();
    	
    	System.out.print("input emaid > ");
        String emaid = br.readLine();
        System.out.println("");
    	
        System.out.print("input charge type > ");
        String chargeType = br.readLine();
        if (chargeType.length() == 0) {
            return;
        }
        
        System.out.println("Tuple Id 개수를 입력해주세요");
        int num = Integer.parseInt(br.readLine());
        
        for(int i=0;i<num;i++) {
        	tupleIdList.add(Integer.parseInt(br.readLine()));
        }
        
        //TODO PmaxSchedule값은 입력받지 않음, 임의값으로 대체 
        pmaxScheduleList.add(new PmaxSchedule(1, 1000, 0, "W", 9600));
 	
        GetTariffRequestData request = new GetTariffRequestData(emaid, chargeType, tupleIdList, pmaxScheduleList);        
        TariffResult tariffResponse =  this.mo.requestTariff(request);

        if(tariffResponse.getTupleId()==null) {
        	System.out.println("실패");
        	System.out.println(Json.instance().toJson(tariffResponse, true));
        }else {
        	System.out.println("성공");
        	System.out.println(Json.instance().toJson(tariffResponse, true));
    }
    }
    private void runCommandShowTariffList() {
        System.out.println("");
        if (this.tariffList != null) {
            System.out.println("current tariff list : " + this.tariffList.size());
            for (TariffInfo tariffInfo : this.tariffList) {
                System.out.println("  - tariff : " + tariffInfo.getTariff());
                System.out.println("  - signature : " + tariffInfo.getSignature());
            }
        } else {
            System.out.println("no tariff list");
        }
    }

    private void runCommandPushChargeSales(BufferedReader br) throws Exception {
        System.out.print("input emaid > ");
        String emaid = br.readLine();
        if (emaid.length() == 0) {
            return;
        }
        System.out.print("input sessionId > ");
        String sessionId = br.readLine();
        if (sessionId.length() == 0) {
            return;
        }
        if (sessionId.length() == 0) {
            System.out.println("invalid sessionID");
            return;
        }

        System.out.print("input signature > ");
        String signature = br.readLine();
        if (signature.length() == 0) {
            return;
        }
        System.out.print("input secc cert > ");
        String seccCert = br.readLine();
        if (seccCert.length() == 0) {
            return;
        }
        System.out.print("input subca2 cert > ");
        String subca2Cert = br.readLine();
        if (subca2Cert.length() == 0) {
            return;
        }
        System.out.print("input subca1 cert > ");
        String subca1Cert = br.readLine();
        if (subca1Cert.length() == 0) {
            return;
        }

        System.out.println("");
        EvChargeEndMsg evChargeEndMsg1 = new EvChargeEndMsg();
        EvChargeEndMsg evChargeEndMsg = new EvChargeEndMsg("CPID112948289",
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-05-05 13:00:00")
                ,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-05-05 15:00:00"),
                "DC","765",
                "8000","10000",
                "0","0","0",
                "card","0",
                null,"완충",
                "0", "0", "40다3502",null,
                "0","0","0",
                null, "0", "0", "0",
                "0", "0", "0",
                null,"0","0",null,
                "KR-KEP-0DAAKGH9U-5","0", null,
                null,null,null,
                null,"0",null);

        ChargeSalesRequest request = new ChargeSalesRequest(emaid, sessionId, evChargeEndMsg, signature, seccCert, subca1Cert, subca2Cert);
        ChargeSalesResponse response = this.mo.requestChargeSales(request);
        if (response == null) {
            System.out.println("Server Returned Error Message");
            return;
        }

        if (response.getResult().getCode() == ResultCode.SUCCESS) {
            System.out.println("push charge sales => success");
        } else {
            System.out.println("push charge sales => failed : " + response.getResult().getMessage());
        }
    }

    private void runCommandIssueTlsCertificate(BufferedReader br) throws Exception {
    	System.out.println("input SECC ID > ");
    	String seccId = br.readLine();
    	System.out.println("");
    	
    	IssueTlsCertRequest request = new IssueTlsCertRequest(seccId);
    	IssueTlsCertResponse response =this.v2g.issueTlsCertificate(request);
    	System.out.println(Json.instance().toJson(response, false));
    }
    
//    private void runCommandOcspRequest(BufferedReader br) throws Exception {
//    	System.out.println("Input Target Certificate");
//    	String validCert = br.readLine();
//    	System.out.println("");
//    	
//    	System.out.println("input Target's Issuer Certificate > ");
//    	String caCert = br.readLine();
//    	System.out.println("");
//    	
//    	System.out.println("input OCSP Server IP Address > ");
//    	String ServerIP = br.readLine();
//    	System.out.println("");
//    	
//    	String url_OCSP = "http://" + ServerIP + ":9080/OCSPServer";
//
//    	OcspRequest request = new OcspRequest(validCert, caCert, ServerIP, url_OCSP);
//    	this.v2g.ocspRequest(request);
//    	
//    }
    public static void showHelp() {
        System.out.println("java -jar App.jar HOST_NAME MO_PORT [TLS_PORT]");
        System.out.println("   example - via HTTP) java -jar App.jar 192.168.21.225 8080");
        System.out.println("   example - via TLS) java -jar App.jar 192.168.21.225 8080 18443");
    }

    public static void main(String[] args) {
        App app;
        
        if(args.length == 4) {
        	app = new App(args[0], args[1], args[2], args[3]);
        } else if (args.length == 3) {
            String key = "certs/mo_cli_key.pem";
            String crt = "certs/mo_cli_crt.pem";
            String cas = "certs/mo_ca_chain.txt";

            PtlsClient client = new PtlsClient();
            int ret = client.init(Transport.TLS, key, null, crt, cas);
            System.out.println("PtlsClient init ret : " + ret);

            if (ret != 0) {
                System.out.println("client_init: " + ret);
                return;
            }
            app = new App(args[0], args[1], args[2], key, crt, cas);
        } else if (args.length == 2) {
            app = new App(args[0], args[1]);
        } else {
            showHelp();
            return;
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            app.runMainCommand(br);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
