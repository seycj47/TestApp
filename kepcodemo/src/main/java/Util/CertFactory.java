package Util;

import javafx.util.Pair;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.bouncycastle.util.io.pem.PemWriter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.Security;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class CertFactory {

    public CertFactory() {
        Security.addProvider(new BouncyCastleProvider());
    }

    public KeyStore getPKCS12KeyStore(String leafPfx, String keyStorePassword) throws Exception {
        ByteArrayInputStream keyStoreIS = new ByteArrayInputStream(Common.base64Decode(leafPfx));
        //TODO: pkcs12 에 대한 global 변수 생성 필요
        return getKeyStore(keyStoreIS, keyStorePassword, "pkcs12");
    }

    public KeyStore getKeyStore(InputStream keyStoreIS, String keyStorePassword, String keyStoreType) throws Exception {
        KeyStore keyStore;
        keyStore = KeyStore.getInstance(keyStoreType);
        keyStore.load(keyStoreIS, "Autocrypt123!".toCharArray());
        keyStoreIS.close();
        return keyStore;
    }

    public java.security.interfaces.ECPrivateKey getPrivateKey(KeyStore keyStore, String alias) throws Exception {
        java.security.interfaces.ECPrivateKey privateKey = (java.security.interfaces.ECPrivateKey) keyStore.getKey(alias, "".toCharArray());
        return privateKey;
    }

    public byte[] readPem(String data) throws  Exception {
        PemReader pemReader = new PemReader(new StringReader(data));
        byte[] contentBytes = pemReader.readPemObject().getContent();
        return contentBytes;
    }


    public void writeCert(String certificate) throws Exception {
        final String FILE_PATH = "certFiles/";

        try {
            X509Certificate x509Certificate = getX509Cert(certificate);
            List<Pair<String, X509Certificate>> certList = new ArrayList<>();
            certList.add(new Pair<String, X509Certificate>(x509Certificate.getSubjectX500Principal().getName(), x509Certificate));
            writeCertToPem(certList, FILE_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public X509Certificate getX509Cert(String certificate) throws Exception {
        byte[] cert = Base64.getDecoder().decode(certificate);

        CertificateFactory certFactory = CertificateFactory.getInstance("X.509", BouncyCastleProvider.PROVIDER_NAME);
        new ByteArrayInputStream(cert);
        return ((X509Certificate) certFactory.generateCertificate(new ByteArrayInputStream(cert)));
    }

    public X509Certificate getX509Cert(byte[] certificate) throws Exception {
//        byte[] cert = Base64.getDecoder().decode(certificate);

        CertificateFactory certFactory = CertificateFactory.getInstance("X.509", BouncyCastleProvider.PROVIDER_NAME);
        new ByteArrayInputStream(certificate);
        return ((X509Certificate) certFactory.generateCertificate(new ByteArrayInputStream(certificate)));
    }

    public void writeCertToPem(List<Pair<String, X509Certificate>> certList, String path) throws IOException, CertificateEncodingException {
        String ext = ".pem";

        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdir();
        }

        for (Pair<String, X509Certificate> pair : certList) {
            String tbsStr = convertPem("CERTIFICATE", pair.getValue().getEncoded());
            Files.write(Paths.get(path + pair.getKey() + ext), tbsStr.getBytes());
        }
    }

    public String convertPem(String type, byte[] data) {
        StringWriter stringWriter = new StringWriter();
        PemWriter pemWriter = new PemWriter(stringWriter);
        PemObject pemObject = new PemObject(type, data);
        try {
            pemWriter.writeObject(pemObject);
            pemWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String pemString = stringWriter.toString();
        return pemString;
    }

    public byte[] getDEREncodedSignature (byte[] rawSignatureValue) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // First we separate x and y of coordinates into separate byte arrays r and s
        byte[] r = new byte[32];
        byte[] s = new byte[32];

        try {
            System.arraycopy(rawSignatureValue, 0, r, 0, 32);
            System.arraycopy(rawSignatureValue, 32, s, 0, 32);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return new byte[0];
        }

        // Then encode both parts (r & s) individually
        byte[] rDerEncoded = getDerEncodedSignatureValue(r);
        byte[] sDerEncoded = getDerEncodedSignatureValue(s);

        // And write everything with the proper header to the buffer
        baos.write(0x30);
        baos.write(rDerEncoded.length + sDerEncoded.length);
        try {
            baos.write(rDerEncoded);
            baos.write(sDerEncoded);
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] derEncodedSignature = baos.toByteArray();

        try {
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return derEncodedSignature;
    }

    public byte[] getDerEncodedSignatureValue(byte[] value) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // Check if the value is negative which is equivalent to r[0] being bigger than 0x7f
        boolean isFillByteNeeded = value[0] < 0;

        int indexOfFirstNonNullValue = 0;
        for (/* empty init statement */; indexOfFirstNonNullValue < value.length; indexOfFirstNonNullValue++) {
            if (value[indexOfFirstNonNullValue] != 0) {
                break;
            }
        }

        byte derEncodedLength = (byte) (value.length - indexOfFirstNonNullValue);

        baos.write(0x02);
        if (isFillByteNeeded) {
            baos.write(derEncodedLength + 1);
            baos.write(0x00);
        } else {
            baos.write(derEncodedLength);
        }

        baos.write(value, indexOfFirstNonNullValue, value.length - indexOfFirstNonNullValue);
        byte[] result = baos.toByteArray();

        try {
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
