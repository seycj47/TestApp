package Util;

import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.Security;

public class CryptFactory {

    public CryptFactory() {
        Security.addProvider(new BouncyCastleProvider());
    }

    public byte[] generateSharedSecret(java.security.interfaces.ECPrivateKey privateKey, ECPublicKey publicKey) throws Exception {

        KeyAgreement keyAgreement = KeyAgreement.getInstance("ECDH");
        keyAgreement.init(privateKey, new SecureRandom());
        keyAgreement.doPhase(publicKey, true);

        return keyAgreement.generateSecret();
    }

    public byte[] decrypt(byte[] plaintext, SecretKey secretKey, IvParameterSpec ivParamSpec) throws Exception {
        String transformation = "AES/CBC/NoPadding";
        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParamSpec);
        return cipher.doFinal(plaintext);
    }

    private byte[] generateRandom(int bytesCount) throws Exception {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[bytesCount];
        random.nextBytes(bytes);
        return bytes;
    }

    public SecretKey generateSessionKey(byte[] sharedSecret) throws Exception {
        ByteArrayOutputStream baosOtherInfo = new ByteArrayOutputStream();
        baosOtherInfo.write(0x01);   // algorithm ID
        baosOtherInfo.write(0x55);  // sender name IDu
        baosOtherInfo.write(0x56);  // receiver name IDv

        byte[] otherInfo = baosOtherInfo.toByteArray();
        byte[] sessionKeyAsByteArray = concatKDF(sharedSecret, 128, otherInfo);
        return new SecretKeySpec(sessionKeyAsByteArray, "AES");
    }


    private byte[] concatKDF(byte[] z, int keyDataLen, byte[] otherInfo) throws Exception {
        final long MAX_HASH_INPUTLEN = Long.MAX_VALUE;
        final long UNSIGNED_INT_MAX_VALUE = 4294967295L;
        keyDataLen = keyDataLen/8;
        byte[] key = new byte[keyDataLen];

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        int hashLen = md.getDigestLength();
        int reps = keyDataLen / hashLen;

        if (reps > UNSIGNED_INT_MAX_VALUE)
            throw new Exception("UNSIGNED_INT_MAX_VALUE is small than reps");

        int counter = 1;
        byte[] counterInBytes = intToFourBytes(counter);

        if ((counterInBytes.length + z.length + otherInfo.length) * 8 > MAX_HASH_INPUTLEN)
            throw new Exception("MAX_HASH_INPUTLEN is small than ");

        for (int i = 0; i <= reps; i++) {
            md.reset();
            md.update(intToFourBytes(i+1));
            md.update(z);
            md.update(otherInfo);

            byte[] hash = md.digest();
            if (i < reps) {
                System.arraycopy(hash, 0, key, hashLen * i, hashLen);
            } else {
                if (keyDataLen % hashLen == 0) {
                    System.arraycopy(hash, 0, key, hashLen * i, hashLen);
                } else {
                    System.arraycopy(hash, 0, key, hashLen * i, keyDataLen % hashLen);
                }
            }
        }
        return key;
    }

    private byte[] intToFourBytes(int i) throws Exception {
        byte[] iArray = ByteBuffer.allocate(Integer.SIZE/8).putInt(i).array();
        return iArray;
    }
}
