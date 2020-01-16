package com.pentasecurity.cpo.mo.tls;

import java.io.ByteArrayOutputStream;

public class HttpResponseParser {
    ByteArrayOutputStream output;
    int hdrLen;
    int contLen;
    int isChunked;

    HttpResponseParser() {
        this.output = new ByteArrayOutputStream();
        this.hdrLen = 0;
        this.contLen = 0;
        this.isChunked = -1;
    }

    public void write(byte[] buf, int startIdx, int length) {
        this.output.write(buf, startIdx, length);
    }

    public byte[] toByteArray() {
        return output.toByteArray();
    }

    public boolean isEnd() {
        String responseMsg = output.toString();
        if (isChunked < 0) {
            if (responseMsg.indexOf("chunked") > 0) {
                isChunked = 1;
            } else {
                int lenSIdx = responseMsg.toLowerCase().indexOf("content-length: ");
                int lenEIdx = responseMsg.indexOf("\r\n", lenSIdx);
                hdrLen = responseMsg.indexOf("\r\n\r\n", lenSIdx) + "\r\n\r\n".length();
                contLen = new Integer(responseMsg.toLowerCase().substring(lenSIdx + "content-length: ".length(), lenEIdx));
                isChunked = 0;
            }
        }

        if (isChunked == 1 && responseMsg.endsWith("0\r\n\r\n")) {
            return true;
        } else if (responseMsg.length() == contLen + hdrLen) {
            return true;
        }

        return false;
    }
}
