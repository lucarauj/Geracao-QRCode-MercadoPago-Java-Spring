package com.pixapi.infra.mercadoPago.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MpPixResponseTrxData {

    @JsonProperty("qr_code")
    private String emv;

    @JsonProperty("qr_code_base64")
    private String base64;

    public String getEmv() {
        return emv;
    }

    public void setEmv(String emv) {
        this.emv = emv;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }
}
