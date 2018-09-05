package com.project.springstocks.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AggregateData {

    public final float high;
    public final float low;
    public final float total_volume;
    public final float closing_price;

    @JsonCreator
    public AggregateData(@JsonProperty float high, @JsonProperty float low,
                         @JsonProperty float total_volume, @JsonProperty float closing_price) {
        this.high = high;
        this.low = low;
        this.total_volume = total_volume;
        this.closing_price = closing_price;
    }

    public float getHigh() {
        return high;
    }

    public float getLow() { return low; }

    public float getTotal_volume() {
        return total_volume;
    }

    public float getClosing_price() { return closing_price; }
}
