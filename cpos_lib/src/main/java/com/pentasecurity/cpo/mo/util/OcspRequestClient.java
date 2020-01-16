package com.pentasecurity.cpo.mo.util;

import java.security.Security;
import org.bouncycastle.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
public class OcspRequestClient {

	static {
	    Security.addProvider(new BouncyCastleProvider()); //Load BouncyCastle
	  }
}
