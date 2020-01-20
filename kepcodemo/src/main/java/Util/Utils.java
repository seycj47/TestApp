package Util;

import javafx.util.Pair;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

public class Utils {
    public static String generateUUID() {
        UUID uuid = UUID.randomUUID();

        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());

        return base64Encode(bb.array());
    }

    public static String base64Encode(byte[] str) {
        return Base64.getEncoder().encodeToString(str);
    }
    public static byte[] base64Decode(String str) {
        return Base64.getDecoder().decode(str);
    }
    public static byte[] hash(String alg, byte[] arr) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(alg).digest(arr);
    }

}
