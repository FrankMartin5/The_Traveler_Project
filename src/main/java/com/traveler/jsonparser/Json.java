package com.traveler.jsonparser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Json {
    private static ObjectMapper objectMapper = getDefaultObjectMapper();


    public InputStream getResourceStream(String name) {

        URL url = getClass().getResource(name);
        InputStream inputStream = null;
        try {
            inputStream  = url.openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return inputStream;
    }

    private static ObjectMapper getDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return defaultObjectMapper;
    }

    public JsonNode parse(File src) throws IOException {
        return objectMapper.readTree(src);
    }

    public JsonNode parse(InputStream src) throws IOException {
        return objectMapper.readTree(src);
    }

    public <T> T fromJson(JsonNode node , Class<T> c) throws JsonProcessingException {
        return objectMapper.treeToValue(node, c);
    }

    public static JsonNode toJson(Object a) {
        return objectMapper.valueToTree(a);
    }

    public static String stringify(JsonNode node) throws JsonProcessingException {
        return generateString(node, false);
    }

    public static String prettyPrint(JsonNode node) throws JsonProcessingException {
        return generateString(node, true);
    }

    private static String generateString(JsonNode node, boolean pretty) throws JsonProcessingException {
        ObjectWriter objectWriter = objectMapper.writer();
        if ( pretty )
            objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
        return objectWriter.writeValueAsString(node);
    }
}
