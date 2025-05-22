package org.eclipse.paho.client.mqttv3.util;

import com.amazonaws.auth.policy.internal.JsonDocumentFields;
import java.util.Enumeration;
import java.util.Properties;
import org.eclipse.paho.client.mqttv3.internal.ClientComms;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
public class Debug {
    private static final String CLASS_NAME;
    private static final String lineSep;
    private static final Logger log;
    private static final String separator = "==============";
    private String clientID;
    private ClientComms comms;

    static {
        String name = ClientComms.class.getName();
        CLASS_NAME = name;
        log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, name);
        lineSep = System.getProperty("line.separator", "\n");
    }

    public Debug(String str, ClientComms clientComms) {
        this.clientID = str;
        this.comms = clientComms;
        log.setResourceName(str);
    }

    public void dumpClientDebug() {
        dumpClientComms();
        dumpConOptions();
        dumpClientState();
        dumpBaseDebug();
    }

    public void dumpBaseDebug() {
        dumpVersion();
        dumpSystemProperties();
        dumpMemoryTrace();
    }

    protected void dumpMemoryTrace() {
        log.dumpTrace();
    }

    protected void dumpVersion() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(lineSep + separator + " Version Info " + separator + lineSep);
        StringBuilder sb = new StringBuilder();
        sb.append(left(JsonDocumentFields.VERSION, 20, ' '));
        sb.append(":  ");
        sb.append(ClientComms.VERSION);
        sb.append(lineSep);
        stringBuffer.append(sb.toString());
        stringBuffer.append(left("Build Level", 20, ' ') + ":  " + ClientComms.BUILD_LEVEL + lineSep);
        StringBuilder sb2 = new StringBuilder();
        sb2.append("==========================================");
        sb2.append(lineSep);
        stringBuffer.append(sb2.toString());
        log.fine(CLASS_NAME, "dumpVersion", stringBuffer.toString());
    }

    public void dumpSystemProperties() {
        log.fine(CLASS_NAME, "dumpSystemProperties", dumpProperties(System.getProperties(), "SystemProperties").toString());
    }

    public void dumpClientState() {
        ClientComms clientComms = this.comms;
        if (clientComms == null || clientComms.getClientState() == null) {
            return;
        }
        Properties debug = this.comms.getClientState().getDebug();
        log.fine(CLASS_NAME, "dumpClientState", dumpProperties(debug, this.clientID + " : ClientState").toString());
    }

    public void dumpClientComms() {
        ClientComms clientComms = this.comms;
        if (clientComms != null) {
            Properties debug = clientComms.getDebug();
            log.fine(CLASS_NAME, "dumpClientComms", dumpProperties(debug, this.clientID + " : ClientComms").toString());
        }
    }

    public void dumpConOptions() {
        ClientComms clientComms = this.comms;
        if (clientComms != null) {
            Properties debug = clientComms.getConOptions().getDebug();
            log.fine(CLASS_NAME, "dumpConOptions", dumpProperties(debug, this.clientID + " : Connect Options").toString());
        }
    }

    public static String dumpProperties(Properties properties, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        Enumeration<?> propertyNames = properties.propertyNames();
        stringBuffer.append(lineSep + separator + " " + str + " " + separator + lineSep);
        while (propertyNames.hasMoreElements()) {
            String str2 = (String) propertyNames.nextElement();
            stringBuffer.append(left(str2, 28, ' ') + ":  " + properties.get(str2) + lineSep);
        }
        stringBuffer.append("==========================================" + lineSep);
        return stringBuffer.toString();
    }

    public static String left(String str, int i, char c) {
        if (str.length() >= i) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(i);
        stringBuffer.append(str);
        int length = i - str.length();
        while (true) {
            length--;
            if (length >= 0) {
                stringBuffer.append(c);
            } else {
                return stringBuffer.toString();
            }
        }
    }
}
