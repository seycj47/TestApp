package com.pentasecurity.cpo.mo.model;

public enum ContractVerifyResult {
    Unknown,
    Valid,
    ContractNotStarted,
    ContractExpired,
    ContractRevoked,
    CertificateNotStarted,
    CertificateExpired,
    CertificateRevoked,
    CertificateHold;
}