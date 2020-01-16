package com.pentasecurity.cpo.mo.model;

import javax.annotation.Nullable;
import java.util.Date;

public class EvChargeEndMsg {
    @Nullable
    String cpid;
    @Nullable
    Date start_date;
    @Nullable
    Date end_date;
    @Nullable
    String charge_type;
    @Nullable
    String charge_amount_total;
    @Nullable
    String charging_time;
    @Nullable
    String charge_price_total;
    @Nullable
    String infra_price_total;
    @Nullable
    String service_price_total;
    @Nullable
    String tax_total;
    @Nullable
    String payment_type;
    @Nullable
    String charge_customer_num;
    @Nullable
    String charge_customer_name;
    @Nullable
    String charging_state;
    @Nullable
    String membership_num;
    @Nullable
    String car_num;
    @Nullable
    String car_num_info;
    @Nullable
    Date renewal_date;
    @Nullable
    String card_approval_num;
    @Nullable
    String mileage_deduction;
    @Nullable
    String outlet_num;
    @Nullable
    String outlet_type;
    @Nullable
    String off_peak_amount;
    @Nullable
    String mid_peak_amount;
    @Nullable
    String on_peak_amount;
    @Nullable
    String off_peak_price;
    @Nullable
    String mid_peak_price;
    @Nullable
    String on_peak_price;
    @Nullable
    String cp_operate_mode;
    @Nullable
    String charge_list_price;
    @Nullable
    String charge_vat_price;
    @Nullable
    String hash_mode;
    @Nullable
    String emaid;
    @Nullable
    String session_id;
    @Nullable
    String schema_ver;
    @Nullable
    String first_metering_sign;
    @Nullable
    String last_metering_sign;
    @Nullable
    String signature;
    @Nullable
    String cert;
    @Nullable
    String ca_cert_cnt;
    @Nullable
    String ca_cert_chain;


    public EvChargeEndMsg() {

    }

    public EvChargeEndMsg(String cpid, Date start_date, Date end_date, String charge_type,
                          String charge_amount_total, String charging_time, String charge_price_total,
                          String infra_price_total, String service_price_total, String tax_total,
                          String payment_type, String charge_customer_num, String charge_customer_name,
                          String charging_state, String membership_num, String car_num, String car_num_info,
                          Date renewal_date, String card_approval_num, String mileage_deduction, String outlet_num,
                          String outlet_type, String off_peak_amount, String mid_peak_amount, String on_peak_amount,
                          String off_peak_price, String mid_peak_price, String on_peak_price, String cp_operate_mode,
                          String charge_list_price, String charge_vat_price, String hash_mode, String emaid,
                          String session_id, String schema_ver, String first_metering_sign,
                          String last_metering_sign, String signature, String cert, String ca_cert_cnt,
                          String ca_cert_chain) {
        this.cpid = cpid;
        this.start_date = start_date;
        this.end_date = end_date;
        this.charge_type = charge_type;
        this.charge_amount_total = charge_amount_total;
        this.charging_time = charging_time;
        this.charge_price_total = charge_price_total;
        this.infra_price_total = infra_price_total;
        this.service_price_total = service_price_total;
        this.tax_total = tax_total;
        this.payment_type = payment_type;
        this.charge_customer_num = charge_customer_num;
        this.charge_customer_name = charge_customer_name;
        this.charging_state = charging_state;
        this.membership_num = membership_num;
        this.car_num = car_num;
        this.car_num_info = car_num_info;
        this.renewal_date = renewal_date;
        this.card_approval_num = card_approval_num;
        this.mileage_deduction = mileage_deduction;
        this.outlet_num = outlet_num;
        this.outlet_type = outlet_type;
        this.off_peak_amount = off_peak_amount;
        this.mid_peak_amount = mid_peak_amount;
        this.on_peak_amount = on_peak_amount;
        this.off_peak_price = off_peak_price;
        this.mid_peak_price = mid_peak_price;
        this.on_peak_price = on_peak_price;
        this.cp_operate_mode = cp_operate_mode;
        this.charge_list_price = charge_list_price;
        this.charge_vat_price = charge_vat_price;
        this.hash_mode = hash_mode;
        this.emaid = emaid;
        this.session_id = session_id;
        this.schema_ver = schema_ver;
        this.first_metering_sign = first_metering_sign;
        this.last_metering_sign = last_metering_sign;
        this.signature = signature;
        this.cert = cert;
        this.ca_cert_cnt = ca_cert_cnt;
        this.ca_cert_chain = ca_cert_chain;
    }

    public String getCpid() {
        return cpid;
    }

    public void setCpid(String cpid) {
        this.cpid = cpid;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getCharge_type() {
        return charge_type;
    }

    public void setCharge_type(String charge_type) {
        this.charge_type = charge_type;
    }

    public String getCharge_amount_total() {
        return charge_amount_total;
    }

    public void setCharge_amount_total(String charge_amount_total) {
        this.charge_amount_total = charge_amount_total;
    }

    public String getCharging_time() {
        return charging_time;
    }

    public void setCharging_time(String charging_time) {
        this.charging_time = charging_time;
    }

    public String getCharge_price_total() {
        return charge_price_total;
    }

    public void setCharge_price_total(String charge_price_total) {
        this.charge_price_total = charge_price_total;
    }

    public String getInfra_price_total() {
        return infra_price_total;
    }

    public void setInfra_price_total(String infra_price_total) {
        this.infra_price_total = infra_price_total;
    }

    public String getService_price_total() {
        return service_price_total;
    }

    public void setService_price_total(String service_price_total) {
        this.service_price_total = service_price_total;
    }

    public String getTax_total() {
        return tax_total;
    }

    public void setTax_total(String tax_total) {
        this.tax_total = tax_total;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getCharge_customer_num() {
        return charge_customer_num;
    }

    public void setCharge_customer_num(String charge_customer_num) {
        this.charge_customer_num = charge_customer_num;
    }

    public String getCharge_customer_name() {
        return charge_customer_name;
    }

    public void setCharge_customer_name(String charge_customer_name) {
        this.charge_customer_name = charge_customer_name;
    }

    public String getCharging_state() {
        return charging_state;
    }

    public void setCharging_state(String charging_state) {
        this.charging_state = charging_state;
    }

    public String getMembership_num() {
        return membership_num;
    }

    public void setMembership_num(String membership_num) {
        this.membership_num = membership_num;
    }

    public String getCar_num() {
        return car_num;
    }

    public void setCar_num(String car_num) {
        this.car_num = car_num;
    }

    public String getCar_num_info() {
        return car_num_info;
    }

    public void setCar_num_info(String car_num_info) {
        this.car_num_info = car_num_info;
    }

    public Date getRenewal_date() {
        return renewal_date;
    }

    public void setRenewal_date(Date renewal_date) {
        this.renewal_date = renewal_date;
    }

    public String getCard_approval_num() {
        return card_approval_num;
    }

    public void setCard_approval_num(String card_approval_num) {
        this.card_approval_num = card_approval_num;
    }

    public String getMileage_deduction() {
        return mileage_deduction;
    }

    public void setMileage_deduction(String mileage_deduction) {
        this.mileage_deduction = mileage_deduction;
    }

    public String getOutlet_num() {
        return outlet_num;
    }

    public void setOutlet_num(String outlet_num) {
        this.outlet_num = outlet_num;
    }

    public String getOutlet_type() {
        return outlet_type;
    }

    public void setOutlet_type(String outlet_type) {
        this.outlet_type = outlet_type;
    }

    public String getOff_peak_amount() {
        return off_peak_amount;
    }

    public void setOff_peak_amount(String off_peak_amount) {
        this.off_peak_amount = off_peak_amount;
    }

    public String getMid_peak_amount() {
        return mid_peak_amount;
    }

    public void setMid_peak_amount(String mid_peak_amount) {
        this.mid_peak_amount = mid_peak_amount;
    }

    public String getOn_peak_amount() {
        return on_peak_amount;
    }

    public void setOn_peak_amount(String on_peak_amount) {
        this.on_peak_amount = on_peak_amount;
    }

    public String getOff_peak_price() {
        return off_peak_price;
    }

    public void setOff_peak_price(String off_peak_price) {
        this.off_peak_price = off_peak_price;
    }

    public String getMid_peak_price() {
        return mid_peak_price;
    }

    public void setMid_peak_price(String mid_peak_price) {
        this.mid_peak_price = mid_peak_price;
    }

    public String getOn_peak_price() {
        return on_peak_price;
    }

    public void setOn_peak_price(String on_peak_price) {
        this.on_peak_price = on_peak_price;
    }

    public String getCp_operate_mode() {
        return cp_operate_mode;
    }

    public void setCp_operate_mode(String cp_operate_mode) {
        this.cp_operate_mode = cp_operate_mode;
    }

    public String getCharge_list_price() {
        return charge_list_price;
    }

    public void setCharge_list_price(String charge_list_price) {
        this.charge_list_price = charge_list_price;
    }

    public String getCharge_vat_price() {
        return charge_vat_price;
    }

    public void setCharge_vat_price(String charge_vat_price) {
        this.charge_vat_price = charge_vat_price;
    }

    public String getHash_mode() {
        return hash_mode;
    }

    public void setHash_mode(String hash_mode) {
        this.hash_mode = hash_mode;
    }

    public String getEmaid() {
        return emaid;
    }

    public void setEmaid(String emaid) {
        this.emaid = emaid;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getSchema_ver() {
        return schema_ver;
    }

    public void setSchema_ver(String schema_ver) {
        this.schema_ver = schema_ver;
    }

    public String getFirst_metering_sign() {
        return first_metering_sign;
    }

    public void setFirst_metering_sign(String first_metering_sign) {
        this.first_metering_sign = first_metering_sign;
    }

    public String getLast_metering_sign() {
        return last_metering_sign;
    }

    public void setLast_metering_sign(String last_metering_sign) {
        this.last_metering_sign = last_metering_sign;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getCert() {
        return cert;
    }

    public void setCert(String cert) {
        this.cert = cert;
    }

    public String getCa_cert_cnt() {
        return ca_cert_cnt;
    }

    public void setCa_cert_cnt(String ca_cert_cnt) {
        this.ca_cert_cnt = ca_cert_cnt;
    }

    public String getCa_cert_chain() {
        return ca_cert_chain;
    }

    public void setCa_cert_chain(String ca_cert_chain) {
        this.ca_cert_chain = ca_cert_chain;
    }
}
