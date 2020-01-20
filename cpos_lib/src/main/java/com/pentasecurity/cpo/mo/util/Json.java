package com.pentasecurity.cpo.mo.util;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.pentasecurity.cpo.mo.model.IssueCpoCertResponse;
import com.pentasecurity.cpo.mo.model.ResponseData;
import com.pentasecurity.cpo.mo.model.TariffResult;

public class Json {
    
    private Gson gson;
    private Gson gsonPretty;
    
    private Json() {
        this.gson = new Gson();
        this.gsonPretty = new GsonBuilder().setPrettyPrinting().create();;
    }
    
    public String toJson(Object value, boolean pretty) throws Exception {
        if (pretty == true) {
            return this.gsonPretty.toJson(value);
        } else {
            return this.gson.toJson(value);
        }
    }
    
    public <T> T fromJson(String value, Type type) throws Exception {
        return this.gson.fromJson(value, type);
    }
    
    public <T> T fromJson(String value, Class<T> valueType) throws Exception {
        return this.gson.fromJson(value, valueType);
    }

    private static class Singleton {
        private static final Json instance = new Json();
    }

    public static Json instance() {
        return Singleton.instance;
    }
}
