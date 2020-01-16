import Util.CertFactory;
import Util.ExiFactory;
import Util.MsgDigest;
import Util.Common;
import com.pentasecurity.cpo.mo.V2gAdapter;
import com.pentasecurity.cpo.mo.model.IssueTlsCertRequest;
import com.pentasecurity.cpo.mo.model.IssueTlsCertResponse;
import com.pentasecurity.cpo.mo.model.V2gMessage;
import com.pentasecurity.cpo.mo.model.V2gMessagePartitial;
import message.*;
import ocsp.OcspReqClient;

import javax.xml.bind.JAXBElement;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.KeyStore;
import java.util.*;

public class ApplicationMain {

    private V2gAdapter v2g;
    private String instResMsg;

    public ApplicationMain() {
        try {
            v2g = new V2gAdapter("localhost", "8080");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command = "";
        ApplicationMain app = new ApplicationMain();
        while (!command.equals("0")) {
            try {
                System.out.println("");
                System.out.println("=====================");
                System.out.println("1 : Get CertInstResMsg");
                System.out.println("2 : Get CertUpdtResMsg");
                System.out.println("3 : Issue CPO leaf");
                System.out.println("4 : OCPS TEST");
                System.out.println("5 : Charging TEST");
                System.out.println("0 : exit");
                System.out.println("=====================");
                System.out.println("select command > ");

                command = br.readLine();
                System.out.println();
                if (command.equals("1")) {
                    app.runCommandGetCertInstResMsg();
                } else if (command.equals("2")) {
                    app.runCommandGetCertUpdtResMsg(br);
                } else if (command.equals("3")) {
                    app.runCommandIssueCpoLeaf(br);
                } else if (command.equals("4")) {
                    app.runOCSPTest();
                } else if (command.equals("5")) {
                    app.chargeTest();
                }
            } catch (Exception err) {
                err.printStackTrace();
                try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace();}
            }
        }
    }


    public void runCommandIssueCpoLeaf(BufferedReader br) throws Exception {
        System.out.println("Input CN >");
        String cn = br.readLine();
        if(!cn.equals("0")) {
            IssueTlsCertResponse result = this.v2g.issueTlsCertificate(new IssueTlsCertRequest(cn));
            String leaf = "CPO Leaf: ";
            System.out.println(leaf);
            leaf =  result.toString();
            System.out.println(leaf);
        }
    }

    public void runCommandGetCertInstResMsg() throws Exception {

        // 1. certificateInstallRequest 생성
        Common common = new Common();
        V2GMessage v2gMsg = common.createCertInstallReq();

        byte[] v2gMsgXml = MsgDigest.generateXMLToByteArray(v2gMsg, V2GMessage.class);
        String v2gMsgXmlStr = new String(v2gMsgXml);
        System.out.println("======== Cert Installation Request Message: ");
        System.out.println(v2gMsgXmlStr);
        String encodedV2gMsgXml = Common.base64Encode(ExiFactory.getInstance().encodeEXI(v2gMsgXml, false, false));
        System.out.println("======== Encoded Message: ");
        System.out.println(encodedV2gMsgXml);

        // 2. certificateInstallResponse 요청
        V2gMessage req = new V2gMessage(encodedV2gMsgXml);
        com.pentasecurity.cpo.mo.model.V2gMessage result = v2g.certInstMsgRequest(req);
        String resmsg = result.getMsg();

        // 3. certificateInstallResponse exi decoding/ print
        System.out.println("======== Cert Installation Response Message: ");
        System.out.println(ExiFactory.getInstance().decodeEXI(resmsg, false, false));
        String decodedMsg = ExiFactory.getInstance().decodeEXI(resmsg, false, false);
        V2GMessage resV2gMsg = (V2GMessage) MsgDigest.unmarshallToMessage(decodedMsg.getBytes(), V2GMessage.class);

        this.instResMsg = resmsg;
        System.out.println();
    }

    // instRes -> UpdtReq
    public void runCommandGetCertUpdtResMsg(BufferedReader br) throws Exception {
        Common common = new Common();

        if(this.instResMsg.equals("")) {
            System.out.println("Input Cert Inst Res Msg > ");
            String input = br.readLine();
            if(input.equals("0")) return;
            this.instResMsg = input;
        }

        V2GMessage v2gMsg = common.createCertUpdateReq(this.instResMsg);
        byte[] v2gMsgXml = MsgDigest.generateXMLToByteArray(v2gMsg, V2GMessage.class);
        String v2gMsgXmlStr = new String(v2gMsgXml);

        System.out.println("Cert Update Request Message : ");
        System.out.println(v2gMsgXmlStr);

        String encodedV2gMsgXml = Common.base64Encode(ExiFactory.getInstance().encodeEXI(v2gMsgXml, false, false));

        V2gMessage req = new V2gMessage(encodedV2gMsgXml);
        V2gMessagePartitial result = v2g.certUpdtMsgRequest(req);
        String resmsg = result.getV2gMessage();
        System.out.println("Cert Update Response Message: ");
        System.out.println(ExiFactory.getInstance().decodeEXI(resmsg, false, false));
        System.out.println();
    }

    public void runOCSPTest() throws Exception {
        String validCert = "MIICZzCCAh2gAwIBAgIGAOjUpRAyMAoGCCqGSM49BAMCMEAxCzAJBgNVBAYTAktSMQ4wDAYDVQQKDAVwZW50YTEOMAwGA1UECwwFcGVudGExETAPBgNVBAMMCE1PU3ViQ2EyMB4XDTIwMDEwOTAwMDAwMFoXDTIyMDEwOTIzNTk1OVowSjELMAkGA1UEBhMCS1IxDjAMBgNVBAoMBXBlbnRhMQ4wDAYDVQQLDAVwZW50YTEbMBkGA1UEAwwSS1ItS0VQLTJIN0pGTllFVC04MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEqWUnZnpraLfWwNFHXd3cTyptIys4VETfcb25YeTVhTPlAMhhOXwRoFwtd2eVUzM2rHLFrNpjk/74vqUdywfAmaOB+DCB9TBoBgNVHSMEYTBfgBQEXQdq/zNYgaEIqEmJ8b/TA+IEDqFEpEIwQDELMAkGA1UEBhMCS1IxDjAMBgNVBAoMBXBlbnRhMQ4wDAYDVQQLDAVwZW50YTERMA8GA1UEAwwITU9TdWJDYTGCARAwHQYDVR0OBBYEFJhFv0EbYqVHEa3CqeIEyL6DkJicMA4GA1UdDwEB/wQEAwIFoDBaBgNVHR8EUzBRME+gTaBLhklodHRwOi8vMTI3LjAuMC4xL2NuPWRwX2ppZ1NKNHowUXEyQ1JDYkpTTTN1MFFwMCxvdT1wZW50YSxvPXBlbnRhLGM9S1IuY3JsMAoGCCqGSM49BAMCAzgAMDUCGQC06/u58BWFzRSp9sIvVC3+Fe05GB8o+j4CGGlkNUpohvb9TXQQ+qjeHupAIfjTpYK7Dw==";
        String caCert = "MIICZzCCAg2gAwIBAgIBEDAKBggqhkjOPQQDAjBAMQswCQYDVQQGEwJLUjEOMAwGA1UECgwFcGVudGExDjAMBgNVBAsMBXBlbnRhMREwDwYDVQQDDAhNT1N1YkNhMTAeFw0yMDAxMDcwNDM2MTFaFw0yMTAxMDcwNDM2MTFaMEAxCzAJBgNVBAYTAktSMQ4wDAYDVQQKDAVwZW50YTEOMAwGA1UECwwFcGVudGExETAPBgNVBAMMCE1PU3ViQ2EyMEkwEwYHKoZIzj0CAQYIKoZIzj0DAQEDMgAEENVVvW1t+pu5Pi/9b+AJdec6+fOTxtqPL5jl6dGv0KDm33H3w1/6lPD6bmZ/VXUyo4IBBjCCAQIwHQYDVR0OBBYEFARdB2r/M1iBoQioSYnxv9MD4gQOMGYGA1UdIwRfMF2AFKjVNfFgJ7bJUFXN6gDrFhVvSY4/oUKkQDA+MQswCQYDVQQGEwJLUjEOMAwGA1UECgwFcGVudGExDjAMBgNVBAsMBXBlbnRhMQ8wDQYDVQQDDAZNT1Jvb3SCAQ8wDgYDVR0PAQH/BAQDAgEGMBIGA1UdEwEB/wQIMAYBAf8CAQAwVQYDVR0fBE4wTDBKoEigRoZEaHR0cDovLzEyNy4wLjAuMS9jbj1vNmJEMUVxWFR0dWFHU2Q2QXVTQkVBLG91PXBlbnRhLG89cGVudGEsYz1LUi5hcmwwCgYIKoZIzj0EAwIDSAAwRQIgd5qu8ocHvptJ76S6LRPedwtOj0ht8jVZ8ZKfNJtlTA0CIQCnD3wlrSTBYjffoj4nfskMYc7HdipZQMZnavAkcdkIHA==";
        String ServerIP = "";
        String url_OCSP = "http://"+ServerIP+":9091/OCSPServer";

        try {
            OcspReqClient ocspReqClient = new OcspReqClient(url_OCSP);
            ocspReqClient.add(validCert, caCert, null, null);
            byte[] ocspReqBytes = ocspReqClient.buildReq();
            // Send Post
            String request = "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";
            byte[] resPost = ocspReqClient.sendPost(ocspReqBytes);
            if (resPost != null) {
                ocspReqClient.validOcspResponse(resPost);
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }

    public void chargeTest() throws Exception {

        // contractCert
        // moChain[]
        // certInstRes
        // oemCert/pfx
        //

        // 1. check contractCert
            //1.1 if - contractCert is null : get install
        // 2. charge scenario
            // 2.1. contract cert ocsp
            // 2.2. RA request


    }
}
