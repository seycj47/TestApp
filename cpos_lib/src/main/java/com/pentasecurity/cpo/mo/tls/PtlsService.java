package com.pentasecurity.cpo.mo.tls;

import java.io.File;

public class PtlsService {

    private String keyPath;
    private String crtPath;
    private String casPath;

    public void init(String hostName, String moPort, String tlsPort, String keyPath, String crtPath, String casPath) {
//        String keyPath = "/opt/penta/ptls/mo_srv_key.pem";
//        String crtPath = "/opt/penta/ptls/mo_srv_crt.pem";
//        String casPath = "/opt/penta/ptls/mo_ca_chain.txt";
        this.keyPath = keyPath;
        this.crtPath = crtPath;
        this.casPath = casPath;

        File keyFile = new File(this.keyPath);
        File crtFile = new File(this.crtPath);
        File casFile = new File(this.casPath);

        if (keyFile.exists() == false || keyFile.isFile() == false) {
            System.out.println("# TLS server disabled - no key file exist : " + this.keyPath);
            return;
        }
        if (crtFile.exists() == false || crtFile.isFile() == false) {
            System.out.println("# TLS server disabled - no certificate file exist : " + this.crtPath);
            return;
        }
        if (casFile.exists() == false || casFile.isFile() == false) {
            System.out.println("# TLS server disabled - no ca certificates file exist : " + this.casPath);
            return;
        }

        Thread moClientThread = new Thread(new PtlsProxyClient("0.0.0.0", "8184", hostName, tlsPort, keyPath, crtPath, casPath));
        moClientThread.start();
    }
}