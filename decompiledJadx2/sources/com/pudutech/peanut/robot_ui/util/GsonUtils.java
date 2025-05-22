package com.pudutech.peanut.robot_ui.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

/* loaded from: classes5.dex */
public class GsonUtils {
    private static Gson gsonDefault;

    private GsonUtils() {
    }

    public static Gson getGson() {
        if (gsonDefault == null) {
            gsonDefault = createGson();
        }
        return gsonDefault;
    }

    public static String toJson(Object obj) {
        return getGson().toJson(obj);
    }

    public static String toJson(Object obj, Type type) {
        return getGson().toJson(obj, type);
    }

    public static <T> T fromJson(String str, Class<T> cls) {
        return (T) getGson().fromJson(str, (Class) cls);
    }

    public static <T> T fromJson(String str, Type type) {
        return (T) getGson().fromJson(str, type);
    }

    public static <T> T fromJson(Reader reader, Class<T> cls) {
        return (T) getGson().fromJson(reader, (Class) cls);
    }

    public static <T> T fromJson(Reader reader, Type type) {
        return (T) getGson().fromJson(reader, type);
    }

    public static Type getListType(Type type) {
        return TypeToken.getParameterized(List.class, type).getType();
    }

    private static Gson createGson() {
        return new GsonBuilder().serializeNulls().disableHtmlEscaping().create();
    }
}
