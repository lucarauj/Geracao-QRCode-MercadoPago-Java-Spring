package com.pixapi.infra.mercadoPago.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MpPixPoiDto {

    @JsonProperty("transaction_data")
    private MpPixResponseTrxData trxData;

    public MpPixResponseTrxData getTrxData() {
        return trxData;
    }

    public void setTrxData(MpPixResponseTrxData trxData) {
        this.trxData = trxData;
    }
}
