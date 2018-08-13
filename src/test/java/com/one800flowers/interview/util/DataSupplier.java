package com.one800flowers.interview.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.one800flowers.interview.model.Json;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class DataSupplier {
    public static List<Json> data() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new FileInputStream("src/test/resources/test.json"), new TypeReference<List<Json>>(){});
    }
}
