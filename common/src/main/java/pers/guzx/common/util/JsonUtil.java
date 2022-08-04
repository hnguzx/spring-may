package pers.guzx.common.util;

import com.google.gson.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author 25446
 */
@Slf4j
public class JsonUtil {

    /**
     * Object To JsonString
     *
     * @param obj
     * @return
     */
    public static String toJsonString(Object obj) {
        if (obj == null) {
            return "";
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof Number) {
            return obj.toString();
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(obj);
    }

    /**
     * JsonString to Object
     *
     * @param jsonString
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T fromJsonString(String jsonString, Class<T> type) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.fromJson(jsonString, type);
    }

    /**
     * JsonElement to Object
     *
     * @param jsonElement
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T fromJson(JsonElement jsonElement, Class<T> type) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.fromJson(jsonElement, type);
    }

    /**
     * JsonString to List
     *
     * @param jsonString
     * @param type
     * @param <T>
     * @return
     */
    public static <T> List<T> fromJsonString2List(String jsonString, Class<T> type) {
        if (!StringUtils.hasLength(jsonString)) {
            return new ArrayList<T>();
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray array = JsonParser.parseString(jsonString).getAsJsonArray();
        List<T> list = new ArrayList<T>();
        array.forEach(item -> list.add(fromJson(item, type)));
        return list;
    }

    /**
     * JsonString to Map
     *
     * @param jsonString
     * @return
     */
    public static Map toMap(String jsonString) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return (Map) gson.fromJson(jsonString, Object.class);
    }

    /**
     * JsonString to List
     *
     * @param jsonString
     * @return
     */
    public static List toList(String jsonString) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return (List) gson.fromJson(jsonString, Object.class);
    }

    /**
     * JsonString to JsonElement
     *
     * @param jsonString
     * @return
     */
    public static JsonElement parse(String jsonString) {
        return JsonParser.parseString(jsonString);
    }

    /**
     * Object to JsonElement
     *
     * @param obj
     * @return
     */
    public static JsonElement parse(Object obj) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJsonTree(obj);
    }

    public static JsonObject toJson(String jsonString) {
        JsonElement jsonElement = JsonParser.parseString(jsonString);
        return jsonElement.getAsJsonObject();
    }

    public static String getAsString(String jsonString, String key) {
        JsonObject jsonObject = toJson(jsonString);
        return getAsString(jsonObject, key);
    }

    public static String getAsString(JsonObject jsonObject, String key) {
        JsonElement jsonElement = jsonObject.get(key);
        if (isNotNull(jsonElement)) {
            return toJsonString(jsonElement);
            //return jsonElement.getAsString();
        }
        return null;
    }

    public static int getAsInt(String jsonString, String key) {
        JsonObject jsonObject = toJson(jsonString);
        return getAsInt(jsonObject, key);
    }

    public static int getAsInt(JsonObject jsonObject, String key) {
        JsonElement jsonElement = jsonObject.get(key);
        if (isNotNull(jsonElement)) {
            return jsonElement.getAsInt();
        }
        return 0;
    }

    public static long getAsLong(String jsonString, String key) {
        JsonObject jsonObject = toJson(jsonString);
        return getAsLong(jsonObject, key);
    }

    public static long getAsLong(JsonObject jsonObject, String key) {
        JsonElement jsonElement = jsonObject.get(key);
        if (isNotNull(jsonElement)) {
            return jsonElement.getAsLong();
        }
        return 0;
    }

    public static float getAsFloat(String jsonString, String key) {
        JsonObject jsonObject = toJson(jsonString);
        return getAsFloat(jsonObject, key);
    }

    public static float getAsFloat(JsonObject jsonObject, String key) {
        JsonElement jsonElement = jsonObject.get(key);
        if (isNotNull(jsonElement)) {
            return jsonElement.getAsFloat();
        }
        return 0;
    }

    public static double getAsDouble(String jsonString, String key) {
        JsonObject jsonObject = toJson(jsonString);
        return getAsDouble(jsonObject, key);
    }

    public static double getAsDouble(JsonObject jsonObject, String key) {
        JsonElement jsonElement = jsonObject.get(key);
        if (isNotNull(jsonElement)) {
            return jsonElement.getAsDouble();
        }
        return 0;
    }

    public static boolean getAsBoolean(String jsonString, String key) {
        JsonObject jsonObject = toJson(jsonString);
        return getAsBoolean(jsonObject, key);
    }

    public static boolean getAsBoolean(JsonObject jsonObject, String key) {
        JsonElement jsonElement = jsonObject.get(key);
        if (isNotNull(jsonElement)) {
            return jsonElement.getAsBoolean();
        }
        return false;
    }

    public static Iterator<Map.Entry<String, JsonElement>> getIteratorFromJsonString(String jsonString) {
        JsonObject jsonObject = toJson(jsonString);
        return getIteratorFromJsonObject(jsonObject);
    }

    public static Iterator<Map.Entry<String, JsonElement>> getIteratorFromJsonElement(JsonElement jsonElement) {
        if (isNotNull(jsonElement)) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            return getIteratorFromJsonObject(jsonObject);
        }
        return null;
    }

    public static Iterator<Map.Entry<String, JsonElement>> getIteratorFromJsonObject(JsonObject jsonObject) {
        Set<Map.Entry<String, JsonElement>> entries = jsonObject.entrySet();
        return entries.iterator();
    }

    public static boolean hasKey(String jsonString, String key) {
        JsonObject jsonObject = toJson(jsonString);
        return hasKey(jsonObject, key);
    }

    public static boolean hasKey(JsonObject jsonObject, String key) {
        JsonElement jsonElement = jsonObject.get(key);
        return isNotNull(jsonElement);
    }

    public static boolean isNotNull(JsonElement jsonElement) {
        return jsonElement != null && !jsonElement.isJsonNull();
    }

}
