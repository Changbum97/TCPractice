package com.study.tcpractice.domain;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DecimalFormat;

public class PriceUtil extends JsonSerializer<Integer> {

    @Override
    public void serialize(Integer price, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String formattedPrice = new DecimalFormat("###,###").format(price);
        jsonGenerator.writeString(formattedPrice + "원");
    }
}
