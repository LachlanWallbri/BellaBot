package com.github.fernandodev.androidproperties.lib;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* loaded from: classes.dex */
public class AssetsProperties {
    private static final String DEFAUT_ANNOTATION_VALUE = "";
    private static final String EXTENSION = ".properties";
    private static final String TAG = "AssetsProperties";
    private Context mContext;
    private Properties mProperties;
    private String mPropertiesFileName;
    private Resources mResources;

    public AssetsProperties(Context context) {
        this.mPropertiesFileName = "config";
        this.mProperties = new Properties();
        this.mContext = context;
        Resources resources = context.getResources();
        this.mResources = resources;
        openProperties(resources);
    }

    public AssetsProperties(Context context, String str) {
        this.mPropertiesFileName = "config";
        this.mProperties = new Properties();
        this.mContext = context;
        Resources resources = context.getResources();
        this.mResources = resources;
        this.mPropertiesFileName = str;
        openProperties(resources);
    }

    public int getInt(String str, int i) {
        try {
            return Integer.parseInt(this.mProperties.getProperty(str));
        } catch (Exception unused) {
            logParseError(str, "int");
            return i;
        }
    }

    public float getFloat(String str, float f) {
        try {
            return Float.parseFloat(this.mProperties.getProperty(str));
        } catch (Exception unused) {
            logParseError(str, "float");
            return f;
        }
    }

    public double getDouble(String str, double d) {
        try {
            return Double.parseDouble(this.mProperties.getProperty(str));
        } catch (Exception unused) {
            logParseError(str, TmpConstant.TYPE_VALUE_DOUBLE);
            return d;
        }
    }

    public boolean getBoolean(String str, boolean z) {
        try {
            return Boolean.parseBoolean(this.mProperties.getProperty(str));
        } catch (Exception unused) {
            logParseError(str, "boolean");
            return z;
        }
    }

    public String getString(String str, String str2) {
        return this.mProperties.getProperty(str, str2);
    }

    private void openProperties(Resources resources) {
        try {
            this.mProperties.load(resources.getAssets().open(this.mPropertiesFileName + EXTENSION));
            loadPropertiesValues();
        } catch (IOException e) {
            Log.wtf(TAG, e);
        }
    }

    protected void loadPropertiesValues() {
        for (Field field : getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Property.class)) {
                field.setAccessible(true);
                String name = field.getName();
                Property property = (Property) field.getAnnotation(Property.class);
                if (property.value().equals("")) {
                    setFieldValue(field, name);
                } else {
                    setFieldValue(field, property.value());
                }
            }
        }
    }

    private void setFieldValue(Field field, String str) {
        try {
            field.set(this, getPropertyValue(field.getType(), str));
        } catch (IllegalAccessException unused) {
            Log.e(TAG, "AssetsProperties : impossible to set value of field: " + field.getName() + " for " + str);
        }
    }

    private Object getPropertyValue(Class<?> cls, String str) {
        if (cls == String.class) {
            return getString(str, "");
        }
        if (cls == Float.TYPE || cls == Float.class) {
            return Float.valueOf(getFloat(str, 0.0f));
        }
        if (cls == Double.TYPE || cls == Double.class) {
            return Double.valueOf(getDouble(str, 0.0d));
        }
        if (cls == Boolean.TYPE || cls == Boolean.class) {
            return Boolean.valueOf(getBoolean(str, false));
        }
        if (cls == Integer.TYPE || cls == Integer.class) {
            return Integer.valueOf(getInt(str, 0));
        }
        return null;
    }

    private void logParseError(String str, String str2) {
        Log.e(TAG, "AssetsProperties can't parse property " + str + " as " + str2);
    }
}
