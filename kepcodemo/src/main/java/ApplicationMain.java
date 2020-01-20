import Util.Service;
import Util.Utils;
import com.pentasecurity.cpo.mo.App;
import com.pentasecurity.cpo.mo.model.ChainRes;
import com.pentasecurity.cpo.mo.model.IssueCpoCertResponse;
import com.pentasecurity.cpo.mo.model.IssueOemProvCertRes;
import com.pentasecurity.cpo.mo.util.Json;

import javax.annotation.Resources;
import java.io.*;
import java.util.Properties;

public class ApplicationMain {

    private Service service;

    public static void showHelp() {
        System.out.println("java -jar App.jar PropertiesFile");
        System.out.println("   example ) java -jar App.jar ./app.properties");
    }

    ApplicationMain(Properties properties) {
        this.service = new Service(properties);
    }

    public static void main(String[] args) {

        ApplicationMain app = null;

        if(args.length < 1) {
            showHelp();
            return;
        } else {
            try {
                Properties properties = new Properties();
                properties.load(new FileInputStream(args[0]));
                app = new ApplicationMain(properties);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }

        try {
            app.service.createCertUpdateReq("");
        } catch (Exception e) {
            e.printStackTrace();
        }
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String command = "";
//
//        while (!command.equals("0")) {
//            try {
//
//                System.out.println("0: exit");
//                System.out.println("1: Issue OEM Leaf");
//                System.out.println("2. Get OEM Leaf by PCID");
//                System.out.println("3: Issue CPO Leaf");
//                System.out.println("4: Get CPO Leaf by CPID");
//                System.out.println("5: CPO Chain");
//                System.out.println("6: Get CertInstResMsg");
//                System.out.println("7: Get CertUpdtResMsg");
//
//                System.out.println("select command > ");
//                command = br.readLine();
//
//                switch (command) {
//                    case "1": app.runCommandIssueOemProvCert(br);
//                        break;
//                    case "2": app.runCommandGetOemProvCert(br);
//                        break;
//                    case "3": app.runCommandIssueCPOCert(br);
//                        break;
//                    case "4": app.runCommandGetCPOCert(br);
//                        break;
//                    case "5": app.runGetCPOChain(br);
//                        break;
//                    case "6": app.runCommandGetCertInstResMsg();
//                        break;
//                    case "7": app.runCommandGetCertUpdtResMsg();
//                        break;
//                }
//
//            } catch (Exception err) {
//                err.printStackTrace();
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }


    public void runCommandGetCertInstResMsg() throws Exception {

        System.out.println("======== Certificate Installation Request Message 생성 ");
        String v2gMsgXmlStr = service.getCertInstReqMsg();
        System.out.println(v2gMsgXmlStr);

        System.out.println("======== Certificate Installation Response 요청 ");
        String certInstResMsg = service.getCertInstResMsg(v2gMsgXmlStr);


        System.out.println("======== Contract 정보 : ");
        System.out.println("EMAID : "+ service.getEmaid());
        System.out.println();

//        System.out.println("======== OCSP 요청 : ");
//        String ocspResult = service.reqOcsp();
//        System.out.println(ocspResult);
//
//        if(!ocspResult.equals("GOOD"))
//            throw new Exception("contract certificate is "+ocspResult);

        System.out.println("======== Charge : ");
        service.chargeService();
    }

    public void runCommandGetCertUpdtResMsg() throws Exception {

        System.out.println("======== Certificate Update Request Message 생성 ");
        String v2gMsgXmlStr = service.getCertUpdtReqMsg();
        System.out.println(v2gMsgXmlStr);

        System.out.println("======== Certificate Update Response 요청 ");
        String certUpdtResMsg = service.getCertUpdtResMsg(v2gMsgXmlStr);


        System.out.println("======== Contract 정보 : ");
        System.out.println("EMAID : "+ service.getEmaid());
        System.out.println();

//        System.out.println("======== OCSP 요청 : ");
//        String ocspResult = service.reqOcsp();
//        System.out.println(ocspResult);
//
//        if(!ocspResult.equals("GOOD"))
//            throw new Exception("contract certificate is "+ocspResult);

        System.out.println("======== Charge : ");
        service.chargeService();
    }


    // OEM 인증서 발급
    public void runCommandIssueOemProvCert(BufferedReader br) throws Exception {
        System.out.println("input PCID > ");
        String pcid = br.readLine();
        System.out.println("");
        IssueOemProvCertRes oemCert = service.issueOemProvCert(pcid);

        System.out.println(oemCert != null ? oemCert.toString() : "");
//        Utils.writeCert();
    }

    public void runCommandGetOemProvCert(BufferedReader br) throws Exception {
        System.out.println("input PCID > ");
        String pcid = br.readLine();
        System.out.println("");
        IssueOemProvCertRes oemCert = service.getOemProvCert(pcid);

        System.out.println(oemCert != null ? oemCert.toString() : "");
    }

    // CPOS, CP 인증서 발급
    public void runCommandIssueCPOCert(BufferedReader br) throws Exception {
        System.out.println("input CN > ");
        String cn = br.readLine();
        System.out.println("");
        IssueCpoCertResponse cpoCert = service.issueCPOCert(cn);

        System.out.println(cpoCert.toString());
//        Utils.writeCert(service.issueCPOProvCert(cpid));
    }

    public void runCommandGetCPOCert(BufferedReader br) throws Exception {
        System.out.println("input CN > ");
        String cn = br.readLine();
        System.out.println("");
        IssueCpoCertResponse cpoCert = service.getCPOCert(cn);

        System.out.println(cpoCert != null ? cpoCert.toString() : "");
    }

    public void runGetCPOChain(BufferedReader br) throws Exception{
        System.out.println("input TYPE> ");
        String type = br.readLine();
        System.out.println("");
        service.getCPOChain();
    }

}
