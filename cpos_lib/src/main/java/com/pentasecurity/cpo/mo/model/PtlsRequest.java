package com.pentasecurity.cpo.mo.model;

public class PtlsRequest implements java.io.Serializable {

    private static final long serialVersionUID = -7749634231659236094L;
    
    // @JsonFormat(pattern = "POST|GET")
    private String method;
    
    private String hostName;
    
    private String port;

    private String url;

    // @JsonFormat(pattern = "[A-Fa-f0-9+/=]+")
    private String json;

    public PtlsRequest() {
    }

    public PtlsRequest(String method, String hostName, String port, String url) {
        this.method = method;
        this.hostName = hostName;
        this.port = port;
        this.url = url;
        this.json = null;
    }

    public PtlsRequest(String method, String hostName, String port, String url, String json) {
        this.method = method;
        this.hostName = hostName;
        this.port = port;
        this.url = url;
        this.json = json;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

}