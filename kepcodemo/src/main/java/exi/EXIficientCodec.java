package exi;

import com.siemens.ct.exi.EXIFactory;
import com.siemens.ct.exi.GrammarFactory;
import com.siemens.ct.exi.api.sax.EXIResult;
import com.siemens.ct.exi.api.sax.EXISource;
import com.siemens.ct.exi.exceptions.EXIException;
import com.siemens.ct.exi.grammars.Grammars;
import com.siemens.ct.exi.helpers.DefaultEXIFactory;
import message.grammars.EXIficient_V2G_CI_MsgDef;
import message.grammars.EXIficient_xmldsig_core_schema;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Base64;

public class EXIficientCodec {
  private static final EXIficientCodec instance = new EXIficientCodec();

  private EXIFactory exiFactory;
  private GrammarFactory grammarFactory;
  private Grammars grammarMsgDef;
  private Grammars grammarXMLDSig;
  private OutputStream encodeOS;

  private EXIficientCodec() {
    super();

    setExiFactory(DefaultEXIFactory.newInstance());
    setGrammarFactory(GrammarFactory.newInstance());

    setGrammarMsgDef(new EXIficient_V2G_CI_MsgDef());
    setGrammarXMLDSig(new EXIficient_xmldsig_core_schema());

    // Non-default settings to fulfill requirements [V2G2-099] and [V2G2-600]
    getExiFactory().setValuePartitionCapacity(0);
    getExiFactory().setMaximumNumberOfBuiltInElementGrammars(0);
    getExiFactory().setMaximumNumberOfBuiltInProductions(0);
  }

  public static EXIficientCodec getInstance() {
    return instance;
  }

  public synchronized String encodeEXI(String xmlString, boolean xmldsig) {
    Grammars grammar = null;

    if (xmldsig){
      grammar = getGrammarXMLDSig();
      setFragment(true);
    }
    else {
      grammar = getGrammarMsgDef();
    }

    InputStream inStream = new ByteArrayInputStream(xmlString.getBytes());
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    baos = ((ByteArrayOutputStream) encode(inStream, grammar));
    setFragment(false);
    return Base64.getEncoder().encodeToString(baos.toByteArray());
  }

  private synchronized OutputStream encode(InputStream jaxbXML, Grammars grammar) {
    EXIResult exiResult = null;

    try {
      exiFactory.setGrammars(grammar);
      encodeOS = new ByteArrayOutputStream();
      exiResult = new EXIResult(exiFactory);
      exiResult.setOutputStream(encodeOS);
      XMLReader xmlReader = XMLReaderFactory.createXMLReader();
      xmlReader.setContentHandler(exiResult.getHandler());

      // parse xml file
      xmlReader.parse(new InputSource(jaxbXML));

      encodeOS.close();
    } catch (SAXException | IOException | EXIException e) {
      e.printStackTrace();
    }

    return encodeOS;
  }


  public synchronized String decodeEXI(String exiEncodedMessage, boolean xmldsig, boolean fragment) {
      byte[] a = Base64.getDecoder().decode(exiEncodedMessage);
    ByteArrayInputStream bais = new ByteArrayInputStream(a);
    String xmlString = decode(bais, xmldsig, fragment);
    return xmlString;
  }

    public synchronized String decodeEXI(byte[] exiEncodedMessage, boolean xmldsig, boolean fragment) {
        ByteArrayInputStream bais = new ByteArrayInputStream(Base64.getDecoder().decode(exiEncodedMessage));
        String xmlString = decode(bais, xmldsig, fragment);
        return xmlString;
    }


  private synchronized String decode(InputStream exiInputStream, boolean xmldsig, boolean fragment) {
    TransformerFactory tf = TransformerFactory.newInstance();
    Transformer transformer = null;
    StringWriter stringWriter = new StringWriter();

    try {
      transformer = tf.newTransformer();
    } catch (TransformerConfigurationException e) {
        e.printStackTrace();
    }
    setFragment(fragment);
//    exiFactory.setGrammars(grammarMsgDef);
//    setFragment(true);
    if (xmldsig) {
      exiFactory.setGrammars(grammarXMLDSig);
    } else {
      exiFactory.setGrammars(grammarMsgDef);
    }

    try {
      EXISource saxSource = new EXISource(exiFactory);
      SAXSource exiSource = new SAXSource(new InputSource(exiInputStream));
      XMLReader exiReader = saxSource.getXMLReader();
      exiSource.setXMLReader(exiReader);
      transformer.transform(exiSource, new StreamResult(stringWriter));
    } catch (Exception e) {
        e.printStackTrace();
    }

    setFragment(false);
    return stringWriter.toString();
  }

  private Grammars getGrammarMsgDef() {
    return grammarMsgDef;
  }

  private void setGrammarMsgDef(Grammars grammarMsgDef) {
    this.grammarMsgDef = grammarMsgDef;
  }


  public Grammars getGrammarXMLDSig() {
    return grammarXMLDSig;
  }

  public void setGrammarXMLDSig(Grammars grammarXMLDSig) {
    this.grammarXMLDSig = grammarXMLDSig;
  }

  public EXIFactory getExiFactory() {
    return exiFactory;
  }

  private void setExiFactory(EXIFactory exiFactory) {
    this.exiFactory = exiFactory;
  }


  @SuppressWarnings("unused")
  private GrammarFactory getGrammarFactory() {
    return grammarFactory;
  }

  private void setGrammarFactory(GrammarFactory grammarFactory) {
    this.grammarFactory = grammarFactory;
  }

  public void setFragment(boolean useFragmentGrammar) {
    getExiFactory().setFragment(useFragmentGrammar);
  }

}
