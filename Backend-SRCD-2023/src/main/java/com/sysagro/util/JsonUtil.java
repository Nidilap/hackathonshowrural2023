/*
 */
package com.sysagro.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import io.goodforgod.gson.configuration.deserializer.LocalDateDeserializer;
import io.goodforgod.gson.configuration.deserializer.ZonedDateTimeDeserializer;
import io.goodforgod.gson.configuration.serializer.LocalDateSerializer;
import io.goodforgod.gson.configuration.serializer.ZonedDateTimeSerializer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import static java.util.Objects.isNull;

/**
 *
 * @author Pedro
 */
public final class JsonUtil {
    
    private static final Type typeArray = new TypeToken<ArrayList<JsonObject>>(){}.getType();

    // Construtor
    private JsonUtil() {}

    // Geral
    public static Gson criarGsonParaUsoAPI() {
        // Formato UTC para Data/Hora
        return new GsonBuilder()
            .setDateFormat(ConfiguracaoUtil.FORMATO_DATA_HORA)
            .registerTypeAdapter(LocalDate.class, new LocalDateSerializer(DateTimeFormatterUtil.criarComUTC(ConfiguracaoUtil.FORMATO_DATA)))
            .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer(DateTimeFormatterUtil.criarComUTC(ConfiguracaoUtil.FORMATO_DATA)))
            .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeSerializer(DateTimeFormatterUtil.criarComUTC(ConfiguracaoUtil.FORMATO_DATA_HORA_COM_FUSO_HORARIO)))
            .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeDeserializer(DateTimeFormatterUtil.criarComUTC(ConfiguracaoUtil.FORMATO_DATA_HORA_COM_FUSO_HORARIO)))
            .create();
    }
    
    public static JsonObject transformarEmObjeto(String json) {
        if (isNull(json)) {
            return null;
        }
        return new Gson().fromJson(json, JsonObject.class);
    }
    
    public static List<JsonObject> transformarEmArray(String json) {
        if (isNull(json)) {
            return null;
        }
        return new Gson().fromJson(json, typeArray);  
    }

    public static String retornarString(JsonObject jsonObject, String propriedade) {
        if (isNull(jsonObject) || isNull(propriedade)) {
            return null;
        }
        JsonElement jsonElement = jsonObject.get(propriedade);
        return (isNull(jsonElement) || jsonElement instanceof JsonNull) ? null : jsonElement.getAsString();
    }

    public static Boolean retornarBoolean(JsonObject jsonObject, String propriedade) {
        if (isNull(jsonObject) || isNull(propriedade)) {
            return null;
        }
        JsonElement jsonElement = jsonObject.get(propriedade);
        return (isNull(jsonElement) || jsonElement instanceof JsonNull) ? null : jsonElement.getAsBoolean();
    }

    public static Integer retornarInteger(JsonObject jsonObject, String propriedade) {
        if (isNull(jsonObject) || isNull(propriedade)) {
            return null;
        }
        JsonElement jsonElement = jsonObject.get(propriedade);
        return (isNull(jsonElement) || jsonElement instanceof JsonNull) ? null : jsonElement.getAsInt();
    }

    public static Long retornarLong(JsonObject jsonObject, String propriedade) {
        if (isNull(jsonObject) || isNull(propriedade)) {
            return null;
        }
        JsonElement jsonElement = jsonObject.get(propriedade);
        return (isNull(jsonElement) || jsonElement instanceof JsonNull) ? null : jsonElement.getAsLong();
    }

    public static Float retornarFloat(JsonObject jsonObject, String propriedade) {
        if (isNull(jsonObject) || isNull(propriedade)) {
            return null;
        }
        JsonElement jsonElement = jsonObject.get(propriedade);
        return (isNull(jsonElement) || jsonElement instanceof JsonNull) ? null : jsonElement.getAsFloat();
    }

    public static Double retornarDouble(JsonObject jsonObject, String propriedade) {
        if (isNull(jsonObject) || isNull(propriedade)) {
            return null;
        }
        JsonElement jsonElement = jsonObject.get(propriedade);
        return (isNull(jsonElement) || jsonElement instanceof JsonNull) ? null : jsonElement.getAsDouble();
    }

    public static BigDecimal retornarBigDecimal(JsonObject jsonObject, String propriedade) {
        if (isNull(jsonObject) || isNull(propriedade)) {
            return null;
        }
        JsonElement jsonElement = jsonObject.get(propriedade);
        return (isNull(jsonElement) || jsonElement instanceof JsonNull) ? null : jsonElement.getAsBigDecimal();
    }

    public static Enum retornarEnum(JsonObject jsonObject, String propriedade, Class clazz) {
        if (isNull(jsonObject) || isNull(propriedade) || isNull(clazz)) {
            return null;
        }
        JsonElement jsonElement = jsonObject.get(propriedade);
        return Enum.valueOf(clazz, jsonElement.getAsString());
    }
}