package com.pentasecurity.cpo.mo;

import java.io.BufferedReader;
import java.io.IOException;

import com.google.gson.reflect.TypeToken;
import com.pentasecurity.cpo.mo.model.IssueTlsCertRequest;
import com.pentasecurity.cpo.mo.model.IssueTlsCertResponse;
import com.pentasecurity.cpo.mo.model.OcspRequest;
import com.pentasecurity.cpo.mo.model.ResponseData;
import com.pentasecurity.cpo.mo.model.V2gMessage;
import com.pentasecurity.cpo.mo.model.V2gMessagePartitial;
import com.pentasecurity.cpo.mo.tls.PtlsService;
import com.pentasecurity.cpo.mo.util.HttpUtil;
import com.pentasecurity.cpo.mo.util.Json;

public class V2gAdapter {

	private static final String URL_CERT_INST_RES_MSG = "/api/v1/ccp/certInstOrUpdtResByMsg";
	private static final String URL_CERT_UPDT_RES_MSG = "/api/v1/ccp/certUpdtResByMsg";
	private static final String URL_ISSUE_SECC_CERT = "/api/v1/cpo/seccCert";

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

	public IssueTlsCertResponse issueTlsCertificate(IssueTlsCertRequest issueTlsCertRequest) throws Exception {
    	String request = Json.instance().toJson(issueTlsCertRequest, false);
    	String response = requestJson("POST", URL_ISSUE_SECC_CERT, request);
    	ResponseData<IssueTlsCertResponse> responseData = Json.instance().fromJson(response, new TypeToken<ResponseData<IssueTlsCertResponse>>(){}.getType());
    	
    	if(responseData.getStatus().equals("error")) {
    		throw new Exception(responseData.getMessage());    		
    	}
    	
    	return responseData.getData();
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
