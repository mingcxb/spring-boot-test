package com.cxb.demo.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.DateTime;

import java.io.IOException;
import java.util.Date;

public class Date2StringJsonSerializer extends JsonSerializer {
    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Date date = (Date)value;
        mapper.writeValue(gen, new DateTime(date).toString("yyyy-MM-dd"));
    }
}
