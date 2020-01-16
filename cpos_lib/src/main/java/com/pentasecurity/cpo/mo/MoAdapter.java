package com.pentasecurity.cpo.mo;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.penta.ptls.PtlsClient;
import com.pentasecurity.cpo.mo.model.ChargeSalesRequest;
import com.pentasecurity.cpo.mo.model.ChargeSalesResponse;
import com.pentasecurity.cpo.mo.model.ContractVerifyRequest;
import com.pentasecurity.cpo.mo.model.ContractVerifyResponse;
import com.pentasecurity.cpo.mo.model.GetTariffRequestData;
import com.pentasecurity.cpo.mo.model.ServiceEmaidResponse;
import com.pentasecurity.cpo.mo.model.ServiceTariffRequest;
import com.pentasecurity.cpo.mo.model.ServiceTariffResponse;
import com.pentasecurity.cpo.mo.model.TariffInfo;
import com.pentasecurity.cpo.mo.model.TariffResult;
import com.pentasecurity.cpo.mo.tls.PtlsService;
import com.pentasecurity.cpo.mo.util.HttpUtil;
import com.pentasecurity.cpo.mo.util.Json;
import com.pentasecurity.cpo.mo.model.GetTariffResponse;
import com.pentasecurity.cpo.mo.model.GetTariffResponseData;
import com.pentasecurity.cpo.mo.model.ResultCode;

public class MoAdapter {

	private static final String URL_SERVICE_EMAID = "/api/service/emaid";
	private static final String URL_SERVICE_TARIFF = "/api/service/tariff";
	private static final String URL_CONTRACT_VERIFY = "/api/contract/verify";
	private static final String URL_CHARGE_SALES = "/api/charge/sales";

	private String hostName;

	private String moPort;

	private String tlsPort;

	// private PtlsClient client;
	PtlsService ptlsService;

	public MoAdapter(String hostName, String moPort) throws Exception {
		this.hostName = hostName;
		this.moPort = moPort;
		this.tlsPort = null;
		this.ptlsService = null;
	}

	public MoAdapter(String hostName, String moPort, String tlsPort, String key, String crt, String cas)
			throws Exception {
		this.hostName = hostName;
		this.moPort = moPort;
		this.tlsPort = tlsPort;

		this.ptlsService = new PtlsService();
		this.ptlsService.init(hostName, moPort, tlsPort, key, crt, cas);
	}

	public ServiceEmaidResponse requestEmaid(String timeAfterJson) throws Exception {
		String response = requestJson("POST", URL_SERVICE_EMAID, timeAfterJson);
		return Json.instance().fromJson(response, ServiceEmaidResponse.class);
	}

	public TariffResult requestTariff(GetTariffRequestData request) throws Exception {
		
		String emaid = request.getEmaid();
		List<Integer> inputTariff = request.getTupleIdList();
		String requestAsJson = Json.instance().toJson(new ServiceTariffRequest(emaid, inputTariff), false);
		String response = requestJson("POST", URL_SERVICE_TARIFF, requestAsJson);
		System.out.println(response);
		GetTariffResponse responseData = Json.instance().fromJson(response, GetTariffResponse.class);
		if(responseData.getResult().equals(ResultCode.FAIL)) { 
			return new TariffResult();
		}
		
		List<TariffInfo> tariffList = responseData.getTariffList();
		List<String> tariff = new ArrayList<>();
		List<Integer> tupleIdList = new ArrayList<>();

		for(TariffInfo tariffInfo : tariffList) {
			tariff.add(tariffInfo.getTariff());				
			tupleIdList.add(tariffInfo.getId());
		}		
		String signature = responseData.getSignature();
		String moSubCa2 = "MIIDADCCAqagAwIBAgIBBzAKBggqhkjOPQQDAjBUMQswCQYDVQQGEwJLUjEOMAwGA1UECgwFa2VwY28xDjAMBgNVBAsMBWtlcGNvMRIwEAYKCZImiZPyLGQBGRYCTU8xETAPBgNVBAMMCE1PU3ViQ0ExMB4XDTE5MDUyODA0MzUwN1oXDTI0MDUyODA0MzUwN1owVDELMAkGA1UEBhMCS1IxDjAMBgNVBAoMBWtlcGNvMQ4wDAYDVQQLDAVrZXBjbzESMBAGCgmSJomT8ixkARkWAk1PMREwDwYDVQQDDAhNT1N1YkNBMjBZMBMGByqGSM49AgEGCCqGSM49AwEHA0IABIKAogNSB3+zbs2iTVji+Fzm9ltegx/ofq82PvZDIale1hRocCtvoz+8AQtXpy0ePpMwggseY2LDo4xAwvLyoFWjggFnMIIBYzAdBgNVHQ4EFgQUqGutum1DVObXDVhyiVosk+GUnmwwegYDVR0jBHMwcYAUMxlPdpaxXhf5u3sTyAIGoSPVbTChVqRUMFIxCzAJBgNVBAYTAktSMQ4wDAYDVQQKDAVrZXBjbzEOMAwGA1UECwwFa2VwY28xEjAQBgoJkiaJk/IsZAEZFgJNTzEPMA0GA1UEAwwGTU9Sb290ggEGMA4GA1UdDwEB/wQEAwIBxjASBgNVHRMBAf8ECDAGAQH/AgEAMF8GA1UdHwRYMFYwVKBSoFCGTmh0dHA6Ly9tby5rZXBjby5jby5rcjo4MDgwL2NuPWhpSTB3MnpSUnVTQmRuRG5RbmJERmcsb3U9a2VwY28sbz1rZXBjbyxjPUtSLmFybDBBBggrBgEFBQcBAQQ1MDMwMQYIKwYBBQUHMAGGJWh0dHA6Ly9tby5rZXBjby5jby5rcjo5MDAwL09DU1BTZXJ2ZXIwCgYIKoZIzj0EAwIDSAAwRQIgHi61LFIC2X17kZ6ZfTGDD77mpnbbG2TCQb3B0jMSDOQCIQCzqaEGc4reAVBLOoIgkFY0nEt18NyAK4ltgcs1cWCZhA==";

		return new TariffResult(tupleIdList, tariff, signature, moSubCa2);
	}

	public ContractVerifyResponse requestVerifContract(ContractVerifyRequest request) throws Exception {
		String response = requestJson("POST", URL_CONTRACT_VERIFY, Json.instance().toJson(request, false));
		return Json.instance().fromJson(response, ContractVerifyResponse.class);
	}

	public ChargeSalesResponse requestChargeSales(ChargeSalesRequest request) throws Exception {
		String response = requestJson("POST", URL_CHARGE_SALES, Json.instance().toJson(request, false));
		return Json.instance().fromJson(response, ChargeSalesResponse.class);
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
		System.out.println("request : " + json);
		String apiUrl = "http://" + this.hostName + ":" + this.moPort + url;
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
