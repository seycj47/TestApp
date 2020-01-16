package com.pentasecurity.cpo.mo.model;

public class TariffInfo {

	private int id;
    private String tariff;
    
    public TariffInfo() {
    }

    public TariffInfo(int id, String tariff) {
        this.id = id;
    	this.tariff = tariff;
    }

    public int getId() {
    	return id;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
    
    public String getTariff() {
        return tariff;
    }

    public void setTariff(String tariff) {
        this.tariff = tariff;
    }
}




