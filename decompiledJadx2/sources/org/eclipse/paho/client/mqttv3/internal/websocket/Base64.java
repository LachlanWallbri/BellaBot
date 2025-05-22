package org.eclipse.paho.client.mqttv3.internal.websocket;

import java.util.prefs.AbstractPreferences;
import java.util.prefs.BackingStoreException;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
public class Base64 {
    private static final Base64Encoder encoder;
    private static final Base64 instance = new Base64();

    static {
        Base64 base64 = instance;
        base64.getClass();
        encoder = new Base64Encoder();
    }

    public static String encode(String str) {
        encoder.putByteArray("akey", str.getBytes());
        return encoder.getBase64String();
    }

    public static String encodeBytes(byte[] bArr) {
        encoder.putByteArray("aKey", bArr);
        return encoder.getBase64String();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes9.dex
     */
    /* loaded from: classes2.dex */
    public class Base64Encoder extends AbstractPreferences {
        private String base64String;

        @Override // java.util.prefs.AbstractPreferences
        protected AbstractPreferences childSpi(String str) {
            return null;
        }

        @Override // java.util.prefs.AbstractPreferences
        protected String[] childrenNamesSpi() throws BackingStoreException {
            return null;
        }

        @Override // java.util.prefs.AbstractPreferences
        protected void flushSpi() throws BackingStoreException {
        }

        @Override // java.util.prefs.AbstractPreferences
        protected String getSpi(String str) {
            return null;
        }

        @Override // java.util.prefs.AbstractPreferences
        protected String[] keysSpi() throws BackingStoreException {
            return null;
        }

        @Override // java.util.prefs.AbstractPreferences
        protected void removeNodeSpi() throws BackingStoreException {
        }

        @Override // java.util.prefs.AbstractPreferences
        protected void removeSpi(String str) {
        }

        @Override // java.util.prefs.AbstractPreferences
        protected void syncSpi() throws BackingStoreException {
        }

        public Base64Encoder() {
            super(null, "");
            this.base64String = null;
        }

        @Override // java.util.prefs.AbstractPreferences
        protected void putSpi(String str, String str2) {
            this.base64String = str2;
        }

        public String getBase64String() {
            return this.base64String;
        }
    }
}
