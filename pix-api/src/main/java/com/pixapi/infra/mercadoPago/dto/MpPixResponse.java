package com.pixapi.infra.mercadoPago.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MpPixResponse {

    @JsonProperty("point_of_interaction")
    private MpPixPoiDto poi;

    public MpPixPoiDto getPoi() {
        return poi;
    }

    public void setPoi(MpPixPoiDto poi) {
        this.poi = poi;
    }
}
