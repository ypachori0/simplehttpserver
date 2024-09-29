package com.coderfromscratch.httpserver.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

/*
* ObjectMapper is a class in Jackson library that is used for converting
* Java objects to and from JSON (as well as other formats)
* Key functions include serialization (Java to JSON)
* and Deserialization (JSON to Java)
 */

public class Json {

    private static ObjectMapper myObjectMapper = defaultObjectMapepr();

    private static ObjectMapper defaultObjectMapepr() {
        ObjectMapper om = new ObjectMapper();
        /*
        * option that controls whether ObjectMapper should fail when it
        * encounters properties in the JSON that don't exist in the Java
        * being deserialized.
        *
        * setting to false tells Jackson not to fail, it will just ignore
        * the unknown properties and proceed with deserialization
         */
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return om;
    }

    /*
    * way to pass JSON string to JSON node
     */
    public static JsonNode parse(String jsonSrc) throws JsonProcessingException {
        return myObjectMapper.readTree(jsonSrc);
    }

    /*
    * way to move JSON node into Configuration.java
     */
    public static <A> A fromJson(JsonNode node, Class<A> clazz) throws JsonProcessingException{
        return myObjectMapper.treeToValue(node, clazz);
    }

    /*
    * way of getting configuration file into JSON node
     */
    public static JsonNode toJson(Object obj) {
        return myObjectMapper.valueToTree(obj);
    }

    public static String stringify(JsonNode node) throws JsonProcessingException {
        return generateJson(node, false);
    }

    public static String stringifyPretty(JsonNode node) throws JsonProcessingException {
        return generateJson(node, true);
    }

    /*
    * way to see JsonNode in string format
     */
    private static String generateJson(Object o, boolean pretty) throws JsonProcessingException {
        ObjectWriter objectWriter = myObjectMapper.writer();
        if (pretty) {
            objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
        }
        return objectWriter.writeValueAsString(o);
    }
}
