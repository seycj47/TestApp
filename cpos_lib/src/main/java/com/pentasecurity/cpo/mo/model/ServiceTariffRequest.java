package com.pentasecurity.cpo.mo.model;

import java.util.List;

public class ServiceTariffRequest implements java.io.Serializable {

    private static final long serialVersionUID = -827294883008842414L;
    // @JsonFormat(pattern = "[a-fA-F]{2}\\-?[0-9a-fA-F]{3}\\-?[0-9a-fA-F]{9}(\\-?[0-9X])?")
    private String emaid;
    
    private List<Integer> tupleIdList;
    
    
    
    public ServiceTariffRequest() {
    }

    public ServiceTariffRequest(String emaid, List<Integer> tupleIdList) {
        this.emaid = emaid;
        this.tupleIdList = tupleIdList;
    }
   
    
    public String getEmaid() {
        return emaid;
    }

    public void setEmaid(String emaid) {
        this.emaid = emaid;
    }
    

	public List<Integer> getTupleIdList() {
		return tupleIdList;
	}

	public void setTupleIdList(List<Integer> tupleIdList) {
		this.tupleIdList = tupleIdList;
	}
}
