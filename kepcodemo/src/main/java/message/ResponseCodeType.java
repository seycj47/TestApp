package message;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für responseCodeType.
 *
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="responseCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="OK"/>
 *     &lt;enumeration value="OK_NewSessionEstablished"/>
 *     &lt;enumeration value="OK_OldSessionJoined"/>
 *     &lt;enumeration value="OK_CertificateExpiresSoon"/>
 *     &lt;enumeration value="FAILED"/>
 *     &lt;enumeration value="FAILED_SequenceError"/>
 *     &lt;enumeration value="FAILED_ServiceIDInvalid"/>
 *     &lt;enumeration value="FAILED_UnknownSession"/>
 *     &lt;enumeration value="FAILED_ServiceSelectionInvalid"/>
 *     &lt;enumeration value="FAILED_PaymentSelectionInvalid"/>
 *     &lt;enumeration value="FAILED_CertificateExpired"/>
 *     &lt;enumeration value="FAILED_SignatureError"/>
 *     &lt;enumeration value="FAILED_NoCertificateAvailable"/>
 *     &lt;enumeration value="FAILED_CertChainError"/>
 *     &lt;enumeration value="FAILED_ChallengeInvalid"/>
 *     &lt;enumeration value="FAILED_ContractCanceled"/>
 *     &lt;enumeration value="FAILED_WrongChargeParameter"/>
 *     &lt;enumeration value="FAILED_PowerDeliveryNotApplied"/>
 *     &lt;enumeration value="FAILED_TariffSelectionInvalid"/>
 *     &lt;enumeration value="FAILED_ChargingProfileInvalid"/>
 *     &lt;enumeration value="FAILED_MeteringSignatureNotValid"/>
 *     &lt;enumeration value="FAILED_NoChargeServiceSelected"/>
 *     &lt;enumeration value="FAILED_WrongEnergyTransferMode"/>
 *     &lt;enumeration value="FAILED_ContactorError"/>
 *     &lt;enumeration value="FAILED_CertificateNotAllowedAtThisEVSE"/>
 *     &lt;enumeration value="FAILED_CertificateRevoked"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "responseCodeType")
@XmlEnum
public enum ResponseCodeType {

    OK("OK"),
    @XmlEnumValue("OK_NewSessionEstablished")
    OK_NEW_SESSION_ESTABLISHED("OK_NewSessionEstablished"),
    @XmlEnumValue("OK_OldSessionJoined")
    OK_OLD_SESSION_JOINED("OK_OldSessionJoined"),
    @XmlEnumValue("OK_CertificateExpiresSoon")
    OK_CERTIFICATE_EXPIRES_SOON("OK_CertificateExpiresSoon"),
    FAILED("FAILED"),
    @XmlEnumValue("FAILED_SequenceError")
    FAILED_SEQUENCE_ERROR("FAILED_SequenceError"),
    @XmlEnumValue("FAILED_ServiceIDInvalid")
    FAILED_SERVICE_ID_INVALID("FAILED_ServiceIDInvalid"),
    @XmlEnumValue("FAILED_UnknownSession")
    FAILED_UNKNOWN_SESSION("FAILED_UnknownSession"),
    @XmlEnumValue("FAILED_ServiceSelectionInvalid")
    FAILED_SERVICE_SELECTION_INVALID("FAILED_ServiceSelectionInvalid"),
    @XmlEnumValue("FAILED_PaymentSelectionInvalid")
    FAILED_PAYMENT_SELECTION_INVALID("FAILED_PaymentSelectionInvalid"),
    @XmlEnumValue("FAILED_CertificateExpired")
    FAILED_CERTIFICATE_EXPIRED("FAILED_CertificateExpired"),
    @XmlEnumValue("FAILED_SignatureError")
    FAILED_SIGNATURE_ERROR("FAILED_SignatureError"),
    @XmlEnumValue("FAILED_NoCertificateAvailable")
    FAILED_NO_CERTIFICATE_AVAILABLE("FAILED_NoCertificateAvailable"),
    @XmlEnumValue("FAILED_CertChainError")
    FAILED_CERT_CHAIN_ERROR("FAILED_CertChainError"),
    @XmlEnumValue("FAILED_ChallengeInvalid")
    FAILED_CHALLENGE_INVALID("FAILED_ChallengeInvalid"),
    @XmlEnumValue("FAILED_ContractCanceled")
    FAILED_CONTRACT_CANCELED("FAILED_ContractCanceled"),
    @XmlEnumValue("FAILED_WrongChargeParameter")
    FAILED_WRONG_CHARGE_PARAMETER("FAILED_WrongChargeParameter"),
    @XmlEnumValue("FAILED_PowerDeliveryNotApplied")
    FAILED_POWER_DELIVERY_NOT_APPLIED("FAILED_PowerDeliveryNotApplied"),
    @XmlEnumValue("FAILED_TariffSelectionInvalid")
    FAILED_TARIFF_SELECTION_INVALID("FAILED_TariffSelectionInvalid"),
    @XmlEnumValue("FAILED_ChargingProfileInvalid")
    FAILED_CHARGING_PROFILE_INVALID("FAILED_ChargingProfileInvalid"),
    @XmlEnumValue("FAILED_MeteringSignatureNotValid")
    FAILED_METERING_SIGNATURE_NOT_VALID("FAILED_MeteringSignatureNotValid"),
    @XmlEnumValue("FAILED_NoChargeServiceSelected")
    FAILED_NO_CHARGE_SERVICE_SELECTED("FAILED_NoChargeServiceSelected"),
    @XmlEnumValue("FAILED_WrongEnergyTransferMode")
    FAILED_WRONG_ENERGY_TRANSFER_MODE("FAILED_WrongEnergyTransferMode"),
    @XmlEnumValue("FAILED_ContactorError")
    FAILED_CONTACTOR_ERROR("FAILED_ContactorError"),
    @XmlEnumValue("FAILED_CertificateNotAllowedAtThisEVSE")
    FAILED_CERTIFICATE_NOT_ALLOWED_AT_THIS_EVSE("FAILED_CertificateNotAllowedAtThisEVSE"),
    @XmlEnumValue("FAILED_CertificateRevoked")
    FAILED_CERTIFICATE_REVOKED("FAILED_CertificateRevoked");
    private final String value;

    ResponseCodeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ResponseCodeType fromValue(String v) {
        for (ResponseCodeType c: ResponseCodeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
