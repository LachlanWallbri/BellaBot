package com.pudutech.pd_network.report.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class GsonUtils {
    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
    private static final Gson sLocalGson = createLocalGson();
    private static final Gson sRemoteGson = createRemoteGson();

    private static GsonBuilder createLocalGsonBuilder() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLenient();
        gsonBuilder.setDateFormat(DATE_FORMAT);
        gsonBuilder.registerTypeAdapter(new TypeToken<Map<String, Object>>() { // from class: com.pudutech.pd_network.report.utils.GsonUtils.1
        }.getType(), new MapTypeAdapter());
        return gsonBuilder;
    }

    private static Gson createLocalGson() {
        return createLocalGsonBuilder().create();
    }

    private static Gson createRemoteGson() {
        return createLocalGsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    public static Gson getLocalGson() {
        return sLocalGson;
    }

    public static <T> T fromLocalJson(String str, Class<T> cls) throws JsonSyntaxException {
        try {
            return (T) sLocalGson.fromJson(str, (Class) cls);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T fromLocalJson(String str, Type type) {
        return (T) sLocalGson.fromJson(str, type);
    }

    public static String toJson(Object obj) {
        return sLocalGson.toJson(obj);
    }

    public static String toRemoteJson(Object obj) {
        return sRemoteGson.toJson(obj);
    }

    public static <T> T gsonToBean(String str, Class<T> cls) {
        Gson gson = sLocalGson;
        if (gson != null) {
            return (T) gson.fromJson(str, (Class) cls);
        }
        return null;
    }

    public static <T> List<T> gsonToList(String str, Class<T> cls) {
        Gson gson = sLocalGson;
        if (gson != null) {
            return (List) gson.fromJson(str, new TypeToken<List<T>>() { // from class: com.pudutech.pd_network.report.utils.GsonUtils.2
            }.getType());
        }
        return null;
    }

    public static <T> List<T> getObjectList(String str, Class<T> cls) {
        ArrayList arrayList = new ArrayList();
        try {
            Gson gson = new Gson();
            Iterator<JsonElement> it = new JsonParser().parse(str).getAsJsonArray().iterator();
            while (it.hasNext()) {
                arrayList.add(gson.fromJson(it.next(), (Class) cls));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static <T> List<T> jsonToList(String str, Class<T> cls) {
        ArrayList arrayList = new ArrayList();
        Iterator<JsonElement> it = new JsonParser().parse(str).getAsJsonArray().iterator();
        while (it.hasNext()) {
            arrayList.add(sLocalGson.fromJson(it.next(), (Class) cls));
        }
        return arrayList;
    }

    public static <T> List<Map<String, T>> gsonToListMaps(String str) {
        Gson gson = sLocalGson;
        if (gson != null) {
            return (List) gson.fromJson(str, new TypeToken<List<Map<String, T>>>() { // from class: com.pudutech.pd_network.report.utils.GsonUtils.3
            }.getType());
        }
        return null;
    }

    public static Map<String, Object> gsonToMaps(String str) {
        Gson gson = sLocalGson;
        if (gson != null) {
            return (Map) gson.fromJson(str, new TypeToken<Map<String, Object>>() { // from class: com.pudutech.pd_network.report.utils.GsonUtils.4
            }.getType());
        }
        return null;
    }
}
