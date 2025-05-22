package org.eclipse.paho.client.mqttv3.logging;

import java.util.ResourceBundle;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
public class LoggerFactory {
    public static final String MQTT_CLIENT_MSG_CAT = "org.eclipse.paho.client.mqttv3.internal.nls.logcat";
    private static final String CLASS_NAME = LoggerFactory.class.getName();
    private static String overrideloggerClassName = null;
    private static String jsr47LoggerClassName = JSR47Logger.class.getName();

    public static Logger getLogger(String str, String str2) {
        Logger logger;
        String str3;
        String str4 = overrideloggerClassName;
        if (str4 == null) {
            str4 = jsr47LoggerClassName;
        }
        try {
            logger = getLogger(str4, ResourceBundle.getBundle(str), str2, null);
        } catch (Exception e) {
            e.printStackTrace();
            logger = null;
        }
        if (logger == null && (str3 = overrideloggerClassName) != null) {
            logger = getLogger(str3, str2, null);
        }
        return logger == null ? new DefaultLogger() : logger;
    }

    private static Logger getLogger(String str, String str2, String str3) {
        try {
            Class<?> cls = Class.forName(str);
            if (cls == null) {
                return null;
            }
            Logger logger = (Logger) cls.newInstance();
            logger.initialise(null, str2, str3);
            return logger;
        } catch (ClassNotFoundException | ExceptionInInitializerError | IllegalAccessException | InstantiationException | NoClassDefFoundError | SecurityException unused) {
            return null;
        }
    }

    private static Logger getLogger(String str, ResourceBundle resourceBundle, String str2, String str3) {
        try {
            Class<?> cls = Class.forName(str);
            if (cls == null) {
                return null;
            }
            Logger logger = (Logger) cls.newInstance();
            logger.initialise(resourceBundle, str2, str3);
            return logger;
        } catch (ClassNotFoundException | ExceptionInInitializerError | IllegalAccessException | InstantiationException | NoClassDefFoundError | SecurityException unused) {
            return null;
        }
    }

    public static String getLoggingProperty(String str) {
        try {
            Class<?> cls = Class.forName("java.util.logging.LogManager");
            return (String) cls.getMethod("getProperty", String.class).invoke(cls.getMethod("getLogManager", new Class[0]).invoke(null, null), str);
        } catch (Exception unused) {
            return null;
        }
    }

    public static void setLogger(String str) {
        overrideloggerClassName = str;
    }
}
