package Util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.Security;

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


}
