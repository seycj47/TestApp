package com.pentasecurity.cpo.mo.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpUtil {
    public final static String API_VERSION = "2";

    public BufferedReader sendHttpRequest(String requestMode, String apiKey, String secretKey,
            String urlString, String PostData) {

        URL url = null;
        BufferedReader rd = null;

        URLConnection con;
        try {
            trustAllHttpsCertificates();
            url = new URL(urlString);

            if (checkHttp(urlString)) {
                con = (HttpURLConnection) url.openConnection();
            } else {
                con = (HttpsURLConnection) url.openConnection();
            }

            // add request header
            String signature = null;

            if (requestMode.equals("POST")) {
                if (PostData != null) {
                    signature = makeSignature(secretKey, PostData);
                }

                con.setDoOutput(true);
                con.setRequestProperty("User-Agent", "Mozilla/5.0");
                con.setRequestProperty("Content-Type", "application/json");
                con.setRequestProperty("Content-Length", String.valueOf(PostData.length()));
                if (apiKey != null) {
                    con.setRequestProperty("APIKey", apiKey);
                } else {
                    con.setRequestProperty("APIKey", "");
                }
                con.setRequestProperty("APIVersion", API_VERSION);
                if (signature != null) {
                    con.setRequestProperty("Signature", signature);
                } else {
                    con.setRequestProperty("Signature", "");
                }
                con.setDoOutput(true);

                DataOutputStream wr;
                wr = new DataOutputStream(con.getOutputStream());
                wr.writeBytes(PostData);
                wr.flush();
                wr.close();

                rd = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            } else {
                con.setDoOutput(false);
                con.setRequestProperty("User-Agent", "Mozilla/5.0");
                con.setRequestProperty("APIKey", apiKey);
                con.setRequestProperty("APIVersion", API_VERSION);
                con.setDoOutput(false);
                rd = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
        return rd;
    }

    private void trustAllHttpsCertificates() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        } };

        // Install the all-trusting trust manager
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    }

    /**
     * Make Signature
     * 
     * @param secretKey
     * @param postData
     * @return String
     */
    private String makeSignature(String secretKey, String postData) {
        if (secretKey == null) {
            return null;
        }
            
        String signature = null;
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(secretKeySpec);
            mac.update(postData.getBytes());
            byte[] signatureBytes = mac.doFinal();
            signature = Base64.getEncoder().encodeToString(signatureBytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }

        System.out.println("signature : " + signature);
        return signature;
    }

    private boolean checkHttp(String url) {
        if (url.indexOf("http://") < 0) {
            return false;
        }

        return true;
    }

}
