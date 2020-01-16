package message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BodyBaseType", namespace = "urn:iso:15118:2:2013:MsgBody")
@XmlSeeAlso({
//        AuthorizationResType.class,
//        PaymentDetailsReqType.class,
//        AuthorizationReqType.class,
//        WeldingDetectionReqType.class,
//        PaymentDetailsResType.class,
//        WeldingDetectionResType.class,
//        ChargeParameterDiscoveryResType.class,
        CertificateUpdateReqType.class,
//        ChargeParameterDiscoveryReqType.class,
//        PreChargeReqType.class,
//        ServiceDetailResType.class,
//        MeteringReceiptReqType.class,
//        ServiceDetailReqType.class,
        CertificateInstallationResType.class,
//        PreChargeResType.class,
        CertificateInstallationReqType.class,
//        CableCheckResType.class,
//        CableCheckReqType.class,
//        MeteringReceiptResType.class,
//        ChargingStatusReqType.class,
//        ServiceDiscoveryResType.class,
//        PowerDeliveryReqType.class,
//        ChargingStatusResType.class,
//        ServiceDiscoveryReqType.class,
//        PowerDeliveryResType.class,
//        SessionStopResType.class,
//        SessionSetupResType.class,
//        PaymentServiceSelectionResType.class,
        CertificateUpdateResType.class,
//        PaymentServiceSelectionReqType.class,
//        CurrentDemandReqType.class,
//        CurrentDemandResType.class,
//        SessionStopReqType.class,
//        SessionSetupReqType.class
})
public abstract class BodyBaseType {

}
