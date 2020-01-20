package Util;

import message.SignedInfoType;
import message.V2GMessage;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.Signature;

public class MsgDigest {
    public static byte[] generateDigest(JAXBElement element , Class<?> classType, boolean xmlDsigFlag) throws Exception {
        byte[] xmlEle = marshalToByteArray(element, classType);
        xmlEle = ExiFactory.getInstance().encodeEXI(xmlEle, xmlDsigFlag, true);
        byte[] md = Common.hash("SHA-256", xmlEle);
        return md;
    }

    public static byte[] marshalToByteArray(JAXBElement element, @SuppressWarnings("rawtypes") Class<?> classType) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            JAXBContext context = JAXBContext.newInstance(classType);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(element, baos);

            byte[]  result = baos.toByteArray();
            baos.close();

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object unmarshallToMessage(byte[] xml, Class<?> classType) throws Exception {
        JAXBContext context = JAXBContext.newInstance(classType);
        Unmarshaller unMarshaller = context.createUnmarshaller();
        ByteArrayInputStream inStream = new ByteArrayInputStream(xml);
        Object result = unMarshaller.unmarshal(inStream);

        V2GMessage resultToV2gMsgType = (V2GMessage)result;

        return resultToV2gMsgType;
    }


    public static byte[] generateXMLToByteArray(Object xml, Class<?> classType) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        JAXBContext context = JAXBContext.newInstance(classType);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(xml, baos);

        byte[] result = baos.toByteArray();
        baos.close();

        return result;
    }

    public static byte[] signSignedInfoElement(JAXBElement<SignedInfoType> signedInfoJAXB, java.security.interfaces.ECPrivateKey ecPrivateKey) throws Exception {

        try {
            byte[] xmlEle = marshalToByteArray(signedInfoJAXB, SignedInfoType.class);
            byte[] signedInfoElementExi = ExiFactory.getInstance().encodeEXI(xmlEle, true, true);

            Signature ecdsa = Signature.getInstance("SHA256withECDSA", BouncyCastleProvider.PROVIDER_NAME);
            ecdsa.initSign(ecPrivateKey);
            ecdsa.update(signedInfoElementExi);
            String check1 = Utils.base64Encode(signedInfoElementExi);

            byte[] signature = ecdsa.sign();
            String check2 = Utils.base64Encode(signature);
            byte[] rawSignature = getRawSignatureFromDEREncoding(signature);

            return rawSignature;
        } catch (Exception e) {
            throw e;
        }
    }

    public static byte[] getRawSignatureFromDEREncoding(byte[] derEncodedSignature) throws Exception{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] r = new byte[32];
        byte[] s = new byte[32];

        // Length of r is encoded in the fourth byte
        int lengthOfR = derEncodedSignature[3];

        // Length of r is encoded in the second byte AFTER r
        int lengthOfS = derEncodedSignature[lengthOfR + 5];

        // Length of r and s are either 33 bytes (including padding byte 0x00), 32 bytes (normal), or less (leftmost 0x00 bytes were removed)
//        try {
            if (lengthOfR == 33) System.arraycopy(derEncodedSignature, 5, r, 0, lengthOfR - 1); // skip leftmost padding byte 0x00
            else if (lengthOfR == 32) System.arraycopy(derEncodedSignature, 4, r, 0, lengthOfR);
            else System.arraycopy(derEncodedSignature, 4, r, 32 - lengthOfR, lengthOfR); // destPos = number of leftmost 0x00 bytes

            if (lengthOfS == 33) System.arraycopy(derEncodedSignature, lengthOfR + 7, s, 0, lengthOfS - 1); // skip leftmost padding byte 0x00
            else if (lengthOfS == 32) System.arraycopy(derEncodedSignature, lengthOfR + 6, s, 0, lengthOfS);
            else System.arraycopy(derEncodedSignature, lengthOfR + 6, s, 32 - lengthOfS, lengthOfS); // destPos = number of leftmost 0x00 bytes
//        } catch (ArrayIndexOutOfBoundsException e) {
//            getLogger().error("ArrayIndexOutOfBoundsException occurred while trying to get raw signature from DER encoded signature.", e);
//        }

//        try {
            baos.write(r);
            baos.write(s);
//        } catch (IOException e) {
//            getLogger().error("IOException occurred while trying to write r and s into DER-encoded signature", e);
//        }

        byte[] rawRAndS = baos.toByteArray();
        return rawRAndS;
    }
}
