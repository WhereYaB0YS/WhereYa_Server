package com.where.WhereYouAt.config.serializer;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.where.WhereYouAt.domain.dto.Birthday;

import java.io.IOException;
import java.time.LocalDate;

//1997-06-27 이런식으로 만들기 위해서
public class BirthdaySerializer extends JsonSerializer<Birthday> {


    @Override
    public void serialize(Birthday value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if(value != null) {
            gen.writeObject(LocalDate.of(value.getYearOfBirthday(), value.getMonthOfBirthday(), value.getDayOfBirthday()));
        }
    }
}
