package com.pentasecurity.cpo.mo;

import com.pentasecurity.cpo.mo.model.Pkcs10Req;
import java.io.*;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

import com.google.gson.reflect.TypeToken;
import com.pentasecurity.cpo.mo.model.*;
import com.pentasecurity.cpo.mo.tls.PtlsService;
import com.pentasecurity.cpo.mo.util.HttpUtil;
import com.pentasecurity.cpo.mo.util.Json;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;

import javax.security.auth.x500.X500Principal;


public class V2gAdapter {

	private static final String URL_CERT_INST_RES_MSG = "/api/v1/ccp/certInstOrUpdtResByMsg";
	private static final String URL_CERT_UPDT_RES_MSG = "/api/v1/ccp/certUpdtResByMsg";
	private static final String URL_ISSUE_CPO_CERT = "/api/v1/cpo/tlsCert";
	private static final String URL_ISSUE_OEM_PROV_CERT = "/api/v1/oem/provCert";

	private String hostName;
	private String v2gPort;
	private String tlsPort;
	PtlsService ptlsService;

	public V2gAdapter(String hostName, String v2gPort) throws Exception {
		this.hostName = hostName;
		this.v2gPort = v2gPort;
		this.tlsPort = null;
		this.ptlsService = null;
	}

	public IssueTlsCertResponse issueTLSCertificate(IssueTlsCertRequest issueTlsCertRequest) {
	  try {
	    String cn = issueTlsCertRequest.getCn();
      byte[] csr = generatePKCS10(cn);
      Pkcs10Req pkcs10Req = new Pkcs10Req(csr, cn);
      String request = Json.instance().toJson(pkcs10Req, false);
      String response = requestJson("POST", URL_ISSUE_CPO_CERT, request);
      System.out.println(response);
      ResponseData<IssueTlsCertResponse> resultData = Json.instance().fromJson(response, new TypeToken<ResponseData<IssueTlsCertResponse>>(){}.getType());

      System.out.println(resultData);
      if(resultData.getStatus().equals("error")) {
        throw new Exception(resultData.getMessage());
      }
      return resultData.getData();
    } catch (Exception e){
	    e.printStackTrace();
	    return null;
    }
  }

  private byte[] generatePKCS10(String cn) {
    try {

      KeyPair keyPair = generageKeyPair();
      X500Principal x500Principal = new X500Principal("cn="+cn);
      JcaPKCS10CertificationRequestBuilder p10Builder = new JcaPKCS10CertificationRequestBuilder(x500Principal, keyPair.getPublic());
      ContentSigner contentSignerBuilder= new JcaContentSignerBuilder("SHA256withECDSA")
        .setProvider(BouncyCastleProvider.PROVIDER_NAME)
        .build(keyPair.getPrivate());

      PKCS10CertificationRequest pkcs10 = p10Builder.build(contentSignerBuilder);
      return pkcs10.getEncoded();

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  private KeyPair generageKeyPair() throws Exception {

      KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("EC", BouncyCastleProvider.PROVIDER_NAME);
      keyPairGen.initialize(256);
      return  keyPairGen.generateKeyPair();
  }

  public IssueOemProvCertRes issueOemProvCert(String pcid) throws Exception {
    IssueOemProvCertReq issueOemProvCertReq = new IssueOemProvCertReq(pcid);
    String request = Json.instance().toJson(issueOemProvCertReq, false);
    String response = requestJson("POST", URL_ISSUE_OEM_PROV_CERT, request);
    ResponseData<IssueOemProvCertRes> resultData = Json.instance().fromJson(response, new TypeToken<ResponseData<IssueOemProvCertRes>>(){}.getType());

    System.out.println(resultData);
    if(resultData.getStatus().equals("error")) {
      throw new Exception(resultData.getMessage());
    }
    return resultData.getData();
  }

	public V2gMessage certInstMsgRequest (V2gMessage v2gMessage) throws Exception {
		String request = Json.instance().toJson(v2gMessage, false);
		String response = requestJson("POST", URL_CERT_INST_RES_MSG, request);
		ResponseData<V2gMessage> resultData = Json.instance().fromJson(response, new TypeToken<ResponseData<V2gMessage>>(){}.getType());

		if(resultData.getStatus().equals("error")) 
			throw new Exception(resultData.getMessage());

		return resultData.getData();
		
	}
	
	public V2gMessagePartitial certUpdtMsgRequest (V2gMessage v2gMessage) throws Exception {
		String request = Json.instance().toJson(v2gMessage, false);
		String response = requestJson("POST", URL_CERT_UPDT_RES_MSG, request);
		ResponseData<V2gMessagePartitial> responseData = Json.instance().fromJson(response, new TypeToken<ResponseData<V2gMessagePartitial>>(){}.getType());
		
		if(responseData.getStatus().equals("error")) {
			throw new Exception(responseData.getMessage());
		}
		
		return responseData.getData();
	}
	
//	public void ocspRequest(OcspRequest ocspRequest) throws Exception {
//		String request = Json.instance().toJson(ocspRequest, false);
//	}
	

	private String requestJson(String requestMode, String url, String json) {
		if (this.tlsPort != null) {
			return requestJsonViaTls(requestMode, url, json);
		} else {
			return requestJsonViaHttp(requestMode, url, json);
		}
	}

	private String requestJsonViaHttp(String requestMode, String url, String json) {
		HttpUtil httpUtil = new HttpUtil();
		System.out.println("request : " + json);
		String apiUrl = "http://" + this.hostName + ":" + this.v2gPort + url;
		BufferedReader rd = httpUtil.sendHttpRequest(requestMode, null, null, apiUrl, json);
		if (rd == null) {
			return null;
		}

		String response = "";
		try {
			String readData = null;
			while ((readData = rd.readLine()) != null) {
				response += readData;
			}
			System.out.println("response : " + response);
			return response;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private String requestJsonViaTls(String requestMode, String url, String json) {
		HttpUtil httpUtil = new HttpUtil();
		System.out.println("request : " + json);
		String apiUrl = "http://localhost:8184" + url;
		BufferedReader rd = httpUtil.sendHttpRequest(requestMode, null, null, apiUrl, json);
		if (rd == null) {
			return null;
		}

		String response = "";
		try {
			String readData = null;
			while ((readData = rd.readLine()) != null) {
				response += readData;
			}
			System.out.println("response : " + response);
			return response;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
