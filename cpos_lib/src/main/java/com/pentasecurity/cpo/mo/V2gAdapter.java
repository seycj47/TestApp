package com.pentasecurity.cpo.mo;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.security.auth.x500.X500Principal;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;

import com.google.gson.reflect.TypeToken;
import com.pentasecurity.cpo.mo.model.ChainReq;
import com.pentasecurity.cpo.mo.model.ChainRes;
import com.pentasecurity.cpo.mo.model.IssueOemProvCertReq;
import com.pentasecurity.cpo.mo.model.IssueOemProvCertRes;
import com.pentasecurity.cpo.mo.model.IssueCpoCertRequest;
import com.pentasecurity.cpo.mo.model.IssueCpoCertResponse;
import com.pentasecurity.cpo.mo.model.Pkcs10Req;
import com.pentasecurity.cpo.mo.model.ResponseData;
import com.pentasecurity.cpo.mo.model.V2gMessage;
import com.pentasecurity.cpo.mo.model.V2gMessagePartitial;
import com.pentasecurity.cpo.mo.tls.PtlsService;
import com.pentasecurity.cpo.mo.util.HttpUtil;
import com.pentasecurity.cpo.mo.util.Json;

public class V2gAdapter {

	private static final String URL_CERT_INST_RES_MSG = "/api/v1/ccp/certInstOrUpdtResByMsg";
	private static final String URL_CERT_UPDT_RES_MSG = "/api/v1/ccp/certUpdtResByMsg";
	private static final String URL_ISSUE_CPO_CERT_BY_CSR = "/api/v1/cpo/seccCertByCsr";
	private static final String URL_ISSUE_CPO_CERT = "/api/v1/cpo/seccCert";
	private static final String URL_ISSUE_OEM_PROV_CERT = "/api/v1/oem/provCert";
	private static final String URL_CERT_CHAIN = "/api/v1/cpo/chain";

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


	public V2gMessage certInstMsgRequest(V2gMessage v2gMessage) {
		try {
			String request = Json.instance().toJson(v2gMessage, false);
			String response = requestJson("POST", URL_CERT_INST_RES_MSG, request);
			ResponseData<V2gMessage> resultData = Json.instance().fromJson(response,
					new TypeToken<ResponseData<V2gMessage>>() {}.getType());
	
			if (resultData.getStatus().equals("error"))
				throw new Exception(resultData.getMessage());
	
			return resultData.getData();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public V2gMessage certUpdtMsgRequest(V2gMessage v2gMessage) {
		try {
			String request = Json.instance().toJson(v2gMessage, false);
			String response = requestJson("POST", URL_CERT_UPDT_RES_MSG, request);
			ResponseData<V2gMessage> resultData = Json.instance().fromJson(response,
					new TypeToken<ResponseData<V2gMessage>>() {}.getType());
	
			if (resultData.getStatus().equals("error")) {
				throw new Exception(resultData.getMessage());
			}
	
			return resultData.getData();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	public IssueOemProvCertRes getOemProvCertByPcid(String pcid) {
		try {
			String response = requestJson("GET", URL_ISSUE_OEM_PROV_CERT+"/"+pcid, null);
			ResponseData<IssueOemProvCertRes> resultData = Json.instance().fromJson(response,
					new TypeToken<ResponseData<IssueOemProvCertRes>>() {}.getType());
	
			if (resultData.getStatus().equals("error")) {
				throw new Exception(resultData.getMessage());
			}
			return resultData.getData();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public IssueOemProvCertRes issueOemProvCert(String pcid) {
		try {
			IssueOemProvCertReq issueOemProvCertReq = new IssueOemProvCertReq(pcid);
			String request = Json.instance().toJson(issueOemProvCertReq, false);
			String response = requestJson("POST", URL_ISSUE_OEM_PROV_CERT, request);
			ResponseData<IssueOemProvCertRes> resultData = Json.instance().fromJson(response,
					new TypeToken<ResponseData<IssueOemProvCertRes>>() {}.getType());
	
			if (resultData.getStatus().equals("error")) {
				throw new Exception(resultData.getMessage());
			}
			return resultData.getData();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//FIXME : 클래스, 메소드명 통일
	public IssueCpoCertResponse issueCpoCertificate(String cn) {
		try {
			byte[] csr = generatePKCS10(cn);
			Pkcs10Req pkcs10Req = new Pkcs10Req(csr, cn);
			String request = Json.instance().toJson(pkcs10Req, false);
			String response = requestJson("POST", URL_ISSUE_CPO_CERT_BY_CSR, request);
			ResponseData<IssueCpoCertResponse> resultData = Json.instance().fromJson(response,
					new TypeToken<ResponseData<IssueCpoCertResponse>>() {}.getType());

			if (resultData.getStatus().equals("error")) {
				throw new Exception(resultData.getMessage());
			}
			return resultData.getData();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public IssueCpoCertResponse getCpoCertByCn(String cn) {
		try {
			String response = requestJson("GET", URL_ISSUE_CPO_CERT+"/"+cn, null);
			ResponseData<IssueCpoCertResponse> resultData = Json.instance().fromJson(response,
					new TypeToken<ResponseData<IssueCpoCertResponse>>() {}.getType());
	
			if (resultData.getStatus().equals("error")) {
				throw new Exception(resultData.getMessage());
			}
			return resultData.getData();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ChainRes chainReq(String type) {
		try {
			ChainReq chainReq = new ChainReq(type);
			String request = Json.instance().toJson(chainReq, false);
			String response = requestJson("POST", URL_CERT_CHAIN, request);
			ResponseData<ChainRes> resultData = Json.instance().fromJson(response,
					new TypeToken<ResponseData<ChainRes>>() {}.getType());

			if (resultData.getStatus().equals("error")) {
				throw new Exception(resultData.getMessage());
			}
			return resultData.getData();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private byte[] generatePKCS10(String cn) {
		try {

			KeyPair keyPair = generageKeyPair();
			X500Principal x500Principal = new X500Principal("cn=" + cn);
			JcaPKCS10CertificationRequestBuilder p10Builder = new JcaPKCS10CertificationRequestBuilder(x500Principal,
					keyPair.getPublic());
			ContentSigner contentSignerBuilder = new JcaContentSignerBuilder("SHA256withECDSA")
					.setProvider(BouncyCastleProvider.PROVIDER_NAME).build(keyPair.getPrivate());

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
		return keyPairGen.generateKeyPair();
	}


	private String requestJson(String requestMode, String url, String json) {
		if (this.tlsPort != null) {
			return requestJsonViaTls(requestMode, url, json);
		} else {
			return requestJsonViaHttp(requestMode, url, json);
		}
	}

	private String requestJsonViaHttp(String requestMode, String url, String json) {
		HttpUtil httpUtil = new HttpUtil();
//		System.out.println("request : " + json);
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
//			System.out.println("response : " + response);
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
