package com.pentasecurity.cpo.mo.tls;

import java.io.ByteArrayOutputStream;

public class HttpRequestParser {
    ByteArrayOutputStream output;
    int hdrLen;
    int contLen;

    HttpRequestParser() {
        this.output = new ByteArrayOutputStream();
        this.hdrLen = 0;
        this.contLen = 0;
    }

    public void write(byte[] buf, int startIdx, int length) {
        this.output.write(buf, startIdx, length);
    }

    public byte[] toByteArray() {
        return output.toByteArray();
    }

    public boolean isEnd() {
        String requestMsg = output.toString();
        if (requestMsg.toLowerCase().startsWith("post")) {
            if (this.hdrLen == 0 || this.contLen == 0) {
                int lenSIdx = requestMsg.toLowerCase().indexOf("content-length: ");
                int lenEIdx = requestMsg.indexOf("\r\n", lenSIdx);
                hdrLen = requestMsg.indexOf("\r\n\r\n", lenSIdx) + "\r\n\r\n".length();
                contLen = new Integer(requestMsg.toLowerCase().substring(lenSIdx + "content-length: ".length(), lenEIdx));
            }
            if (requestMsg.length() == this.contLen + this.hdrLen) {
                return true;
            }
        } else {
            if (requestMsg.endsWith("\r\n\r\n")) {
                return true;
            }
        }

        return false;
    }
}
