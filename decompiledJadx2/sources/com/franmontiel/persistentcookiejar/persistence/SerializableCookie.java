package com.franmontiel.persistentcookiejar.persistence;

import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import okhttp3.Cookie;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
public class SerializableCookie implements Serializable {
    private static final long serialVersionUID = -8594045714036645534L;
    private transient Cookie cookie;
    private static final String TAG = SerializableCookie.class.getSimpleName();
    private static long NON_VALID_EXPIRES_AT = -1;

    /* JADX WARN: Removed duplicated region for block: B:28:0x0043 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public String encode(Cookie cookie) {
        ObjectOutputStream objectOutputStream;
        this.cookie = cookie;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream2 = null;
        try {
            try {
                objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            } catch (IOException e) {
                e = e;
                objectOutputStream = null;
            } catch (Throwable th) {
                th = th;
                if (0 != 0) {
                }
                throw th;
            }
            try {
                objectOutputStream.writeObject(this);
                try {
                    objectOutputStream.close();
                } catch (IOException e2) {
                    Log.d(TAG, "Stream not closed in encodeCookie", e2);
                }
                return byteArrayToHexString(byteArrayOutputStream.toByteArray());
            } catch (IOException e3) {
                e = e3;
                Log.d(TAG, "IOException in encodeCookie", e);
                if (objectOutputStream != null) {
                    try {
                        objectOutputStream.close();
                    } catch (IOException e4) {
                        Log.d(TAG, "Stream not closed in encodeCookie", e4);
                    }
                }
                return null;
            }
        } catch (Throwable th2) {
            th = th2;
            if (0 != 0) {
                try {
                    objectOutputStream2.close();
                } catch (IOException e5) {
                    Log.d(TAG, "Stream not closed in encodeCookie", e5);
                }
            }
            throw th;
        }
    }

    private static String byteArrayToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            int i = b & 255;
            if (i < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(i));
        }
        return sb.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:27:0x004d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.io.ObjectInputStream] */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x001e -> B:8:0x0049). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Cookie decode(String str) {
        Throwable th;
        ?? r2;
        ObjectInputStream objectInputStream;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(hexStringToByteArray(str));
        Cookie cookie = null;
        try {
        } catch (IOException e) {
            String str2 = TAG;
            Log.d(str2, "Stream not closed in decodeCookie", e);
            r2 = str2;
        }
        try {
            try {
                objectInputStream = new ObjectInputStream(byteArrayInputStream);
            } catch (IOException e2) {
                e = e2;
                objectInputStream = null;
            } catch (ClassNotFoundException e3) {
                e = e3;
                objectInputStream = null;
            } catch (Throwable th2) {
                r2 = 0;
                th = th2;
                if (r2 != 0) {
                }
                throw th;
            }
            try {
                cookie = ((SerializableCookie) objectInputStream.readObject()).cookie;
                objectInputStream.close();
                r2 = objectInputStream;
            } catch (IOException e4) {
                e = e4;
                Log.d(TAG, "IOException in decodeCookie", e);
                r2 = objectInputStream;
                if (objectInputStream != null) {
                    objectInputStream.close();
                    r2 = objectInputStream;
                }
                return cookie;
            } catch (ClassNotFoundException e5) {
                e = e5;
                Log.d(TAG, "ClassNotFoundException in decodeCookie", e);
                r2 = objectInputStream;
                if (objectInputStream != null) {
                    objectInputStream.close();
                    r2 = objectInputStream;
                }
                return cookie;
            }
            return cookie;
        } catch (Throwable th3) {
            th = th3;
            if (r2 != 0) {
                try {
                    r2.close();
                } catch (IOException e6) {
                    Log.d(TAG, "Stream not closed in decodeCookie", e6);
                }
            }
            throw th;
        }
    }

    private static byte[] hexStringToByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(this.cookie.name());
        objectOutputStream.writeObject(this.cookie.value());
        objectOutputStream.writeLong(this.cookie.persistent() ? this.cookie.expiresAt() : NON_VALID_EXPIRES_AT);
        objectOutputStream.writeObject(this.cookie.domain());
        objectOutputStream.writeObject(this.cookie.path());
        objectOutputStream.writeBoolean(this.cookie.secure());
        objectOutputStream.writeBoolean(this.cookie.httpOnly());
        objectOutputStream.writeBoolean(this.cookie.hostOnly());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        Cookie.Builder builder = new Cookie.Builder();
        builder.name((String) objectInputStream.readObject());
        builder.value((String) objectInputStream.readObject());
        long readLong = objectInputStream.readLong();
        if (readLong != NON_VALID_EXPIRES_AT) {
            builder.expiresAt(readLong);
        }
        String str = (String) objectInputStream.readObject();
        builder.domain(str);
        builder.path((String) objectInputStream.readObject());
        if (objectInputStream.readBoolean()) {
            builder.secure();
        }
        if (objectInputStream.readBoolean()) {
            builder.httpOnly();
        }
        if (objectInputStream.readBoolean()) {
            builder.hostOnlyDomain(str);
        }
        this.cookie = builder.build();
    }
}
