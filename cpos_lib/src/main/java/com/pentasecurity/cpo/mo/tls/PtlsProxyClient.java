package com.pentasecurity.cpo.mo.tls;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.penta.ptls.Constant.ConfigType;
import com.penta.ptls.Constant.Transport;
import com.penta.ptls.PtlsClient;
import com.penta.ptls.PtlsSocket;

public class PtlsProxyClient implements Runnable {
    private static final int TLS_MAX_RECODE_SIZE = 16000;

    private String serverIp;
    private String serverPort;
    private String bindIp;
    private String listenPort;

    private String keyPath;
    private String crtPath;
    private String casPath;

    private PtlsClient tlsClient;

    public PtlsProxyClient(String bindIp, String listenPort, String serverIp, String serverPort, String keyPath, String crtPath,
            String casPath) {
        this.bindIp = bindIp;
        this.listenPort = listenPort;
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        this.keyPath = keyPath;
        this.crtPath = crtPath;
        this.casPath = casPath;
    }

    @Override
    public void run() {
        System.out.println("[PtlsProxyClient] start");

        try {
            // init tls socket
            int ret = 0;
            tlsClient = new PtlsClient();
            ret = tlsClient.init(Transport.TLS, keyPath, null, crtPath, casPath);
            System.out.println("[Tcp2TlsThread] sendTls init : " + ret);
            if (ret != 0) {
                return;
            }

            // configure tls socket
            tlsClient.config(ConfigType.VERIFY_MODE, 0);
            tlsClient.config(ConfigType.DEBUG_LEVEL, 0);
            tlsClient.config(ConfigType.READ_TIMEOUT, 0);

            // open tcp socket
            ServerSocket ssock = new ServerSocket();
            ssock.bind(new InetSocketAddress(this.bindIp, Integer.valueOf(this.listenPort)));

            // wait accept tcp socket
            while (true) {
                System.out.println("[PtlsProxyClient] accept wait... ");
                Socket sock = ssock.accept();
                System.out.println("[PtlsProxyClient] accept success");

                new Thread(new TcpServerThread(sock)).start();
            }

        } catch (Exception e) {
            System.out.println("[PtlsProxyClient] Exception " + e);
            e.printStackTrace();
        }
    }

    class TcpServerThread implements Runnable {
        private Socket tcpSocket;
        private HttpRequestParser reqParser;
        private HttpResponseParser respParser;

        TcpServerThread(Socket tcpSocket) {
            this.tcpSocket = tcpSocket;
            this.reqParser = new HttpRequestParser();
            this.respParser = new HttpResponseParser();
        }

        @Override
        public void run() {
            if (this.tcpSocket == null)
                return;
            try {
                byte[] buf = new byte[10000];
                while (true) {
                    int length = this.tcpSocket.getInputStream().read(buf);
                    reqParser.write(buf, 0, length);
                    if (reqParser.isEnd()) {
                        break;
                    }
                }
                // send tls socket
                byte[] response = sendTls(serverIp, serverPort, reqParser.toByteArray());

                OutputStream output = this.tcpSocket.getOutputStream();
                output.write(response);
                output.flush();

                this.tcpSocket.close();
            } catch (Exception e) {
                System.out.println("[Tcp2TlsThread] Exception" + e);
                e.printStackTrace();
            }
        }

        private byte[] sendTls(String host, String port, byte[] msg) {
            try {
                // connect tls socket
                PtlsSocket tlsSocket = tlsClient.connect(host, port);
                if (tlsSocket == null) {
                    return null;
                }

                // send request
                int ret = tlsSocket.write(msg, msg.length);
                if (ret < 0) {
                    return null;
                }

                // read response
                byte[] buf = new byte[TLS_MAX_RECODE_SIZE];
                while (true) {
                    ret = tlsSocket.read(buf, buf.length);
                    if (ret <= 0) {
                        break;
                    } else {
                        respParser.write(buf, 0, ret);
                        if (respParser.isEnd()) {
                            break;
                        }
                    }
                }

                tlsSocket.close();
                return respParser.toByteArray();
            } catch (Exception e) {
                System.out.println("[Tcp2TlsThread] sendTls Exception " + e);
                e.printStackTrace();
            }
            return null;
        }
    }
}
