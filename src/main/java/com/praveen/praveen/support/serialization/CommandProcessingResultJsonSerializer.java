package com.praveen.praveen.support.serialization;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.MonthDay;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.praveen.praveen.support.JodaDateTimeAdapter;
import com.praveen.praveen.support.JodaLocalDateAdapter;
import com.praveen.praveen.support.JodaMonthDayAdapter;

@Component
public final class CommandProcessingResultJsonSerializer {

    private final Gson gson;

    public CommandProcessingResultJsonSerializer() {
        final GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LocalDate.class, new JodaLocalDateAdapter());
        builder.registerTypeAdapter(DateTime.class, new JodaDateTimeAdapter());
        builder.registerTypeAdapter(MonthDay.class, new JodaMonthDayAdapter());
        builder.serializeNulls();

        this.gson = builder.create();
    }

    public String serialize(final Object result) {
        String returnedResult = null;
        final String serializedResult = this.gson.toJson(result);
        if (!"null".equalsIgnoreCase(serializedResult)) {
            returnedResult = serializedResult;
        }
        return returnedResult;
    }
}