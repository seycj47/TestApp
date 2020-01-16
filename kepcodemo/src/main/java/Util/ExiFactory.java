package Util;

import com.siemens.ct.exi.EXIFactory;
import com.siemens.ct.exi.GrammarFactory;
import com.siemens.ct.exi.api.sax.EXIResult;
import com.siemens.ct.exi.api.sax.EXISource;
import com.siemens.ct.exi.grammars.Grammars;
import com.siemens.ct.exi.helpers.DefaultEXIFactory;
import message.grammars.EXIficient_V2G_CI_MsgDef;
import message.grammars.EXIficient_xmldsig_core_schema;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Base64;

public class ExiFactory {

    private static ExiFactory instance = new ExiFactory();
    private EXIFactory exiFactory;
    private GrammarFactory grammarFactory;
    private Grammars grammarMsgDef;
    private Grammars grammarXMLDSig;
    private OutputStream encodeOS;

    private ExiFactory() {
        exiFactory = DefaultEXIFactory.newInstance();
        grammarFactory = GrammarFactory.newInstance();
        grammarMsgDef = new EXIficient_V2G_CI_MsgDef();
        grammarXMLDSig = new EXIficient_xmldsig_core_schema();

        exiFactory.setValuePartitionCapacity(0);
        exiFactory.setMaximumNumberOfBuiltInElementGrammars(0);
        exiFactory.setMaximumNumberOfBuiltInProductions(0);
    }

    public static ExiFactory getInstance() {
        return instance;
    }

    public synchronized byte[] encodeEXI(byte[] jaxbXML, boolean xmldsig, boolean fragment) throws Exception {
        Grammars grammar = null;
        exiFactory.setFragment(fragment);

        if (xmldsig) {
            grammar = grammarXMLDSig;
        } else {
            grammar = grammarMsgDef;
        }

        InputStream inStream = new ByteArrayInputStream(jaxbXML);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos = ((ByteArrayOutputStream) encode(inStream, grammar));
        exiFactory.setFragment(false);
        return baos.toByteArray();
    }

    private synchronized OutputStream encode(InputStream jaxbXML, Grammars grammar) throws Exception {
        EXIResult exiResult = null;

        exiFactory.setGrammars(grammar);
        encodeOS = new ByteArrayOutputStream();
        exiResult = new EXIResult(exiFactory);
        exiResult.setOutputStream(encodeOS);
        XMLReader xmlReader = XMLReaderFactory.createXMLReader();
        xmlReader.setContentHandler(exiResult.getHandler());

        // parse xml file
        xmlReader.parse(new InputSource(jaxbXML));
        encodeOS.close();

        return encodeOS;
    }

    public synchronized String decodeEXI(String exiEncodedMessage, boolean xmldsig, boolean fragment) throws Exception {
        exiFactory.setFragment(fragment);
        InputStream bais = new ByteArrayInputStream(Base64.getDecoder().decode(exiEncodedMessage));
        return decode(bais, xmldsig);
    }

    private synchronized String decode(InputStream exiInputStream, boolean xmldsig) throws Exception {
        TransformerFactory tf = TransformerFactory.newInstance();
        StringWriter stringWriter = new StringWriter();

        Transformer transformer = tf.newTransformer();

        if (xmldsig) {
            exiFactory.setGrammars(grammarXMLDSig);
        } else {
            exiFactory.setGrammars(grammarMsgDef);
        }

        EXISource saxSource = new EXISource(exiFactory);
        SAXSource exiSource = new SAXSource(new InputSource(exiInputStream));
        XMLReader exiReader = saxSource.getXMLReader();
        exiSource.setXMLReader(exiReader);
        transformer.transform(exiSource, new StreamResult(stringWriter));

        exiFactory.setFragment(false);
        return stringWriter.toString();
    }
}
