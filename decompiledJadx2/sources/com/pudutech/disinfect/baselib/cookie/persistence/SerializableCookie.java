package com.pudutech.disinfect.baselib.cookie.persistence;

import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import okhttp3.Cookie;

/* compiled from: SerializableCookie.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u000e\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\bJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000b\u001a\u00020\fJ\u0010\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\bH\u0002J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001cH\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/cookie/persistence/SerializableCookie;", "Ljava/io/Serializable;", "()V", "NON_VALID_EXPIRES_AT", "", "getNON_VALID_EXPIRES_AT", "()J", "TAG", "", "getTAG", "()Ljava/lang/String;", "cookie", "Lokhttp3/Cookie;", "serialVersionUID", "getSerialVersionUID", "byteArrayToHexString", "bytes", "", "decode", "encodeCookie", "encode", "hexStringToByteArray", "readObject", "", "input", "Ljava/io/ObjectInputStream;", "writeObject", "out", "Ljava/io/ObjectOutputStream;", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class SerializableCookie implements Serializable {
    private final long NON_VALID_EXPIRES_AT;
    private final String TAG;
    private transient Cookie cookie;
    private final long serialVersionUID = -8594045714036645534L;

    public SerializableCookie() {
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "javaClass.simpleName");
        this.TAG = simpleName;
        this.NON_VALID_EXPIRES_AT = -1L;
    }

    public final long getSerialVersionUID() {
        return this.serialVersionUID;
    }

    public final String getTAG() {
        return this.TAG;
    }

    public final String encode(Cookie cookie) {
        Intrinsics.checkParameterIsNotNull(cookie, "cookie");
        this.cookie = cookie;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = (ObjectOutputStream) null;
        try {
            try {
                ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(byteArrayOutputStream);
                try {
                    objectOutputStream2.writeObject(this);
                    try {
                        objectOutputStream2.close();
                    } catch (IOException e) {
                        Log.e(this.TAG, "Stream not close in encodeCookie", e);
                    }
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    Intrinsics.checkExpressionValueIsNotNull(byteArray, "byteArrayOutputStream.toByteArray()");
                    return byteArrayToHexString(byteArray);
                } catch (IOException e2) {
                    e = e2;
                    objectOutputStream = objectOutputStream2;
                    Log.e(this.TAG, "IOException in encodeCookie ", e);
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (IOException e3) {
                            Log.e(this.TAG, "Stream not close in encodeCookie", e3);
                        }
                    }
                    return null;
                } catch (Throwable th) {
                    th = th;
                    objectOutputStream = objectOutputStream2;
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (IOException e4) {
                            Log.e(this.TAG, "Stream not close in encodeCookie", e4);
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e5) {
            e = e5;
        }
    }

    private final String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            int intValue = ((Integer) Byte.valueOf((byte) (b & ((byte) 255)))).intValue();
            if (intValue < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(intValue));
        }
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x006b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Cookie decode(String encodeCookie) {
        ObjectInputStream objectInputStream;
        ClassNotFoundException e;
        IOException e2;
        Object readObject;
        Intrinsics.checkParameterIsNotNull(encodeCookie, "encodeCookie");
        byte[] hexStringToByteArray = hexStringToByteArray(encodeCookie);
        if (hexStringToByteArray == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.ByteArray");
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(hexStringToByteArray);
        Cookie cookie = (Cookie) null;
        ObjectInputStream objectInputStream2 = (ObjectInputStream) null;
        try {
            try {
                try {
                    objectInputStream = new ObjectInputStream(byteArrayInputStream);
                    try {
                        readObject = objectInputStream.readObject();
                    } catch (IOException e3) {
                        e2 = e3;
                        Log.e(this.TAG, "IOException in decodeCookie ", e2);
                        if (objectInputStream != null) {
                            objectInputStream.close();
                        }
                        if (cookie == null) {
                        }
                        return cookie;
                    } catch (ClassNotFoundException e4) {
                        e = e4;
                        Log.e(this.TAG, "ClassNotFound in decodeCookie", e);
                        if (objectInputStream != null) {
                            objectInputStream.close();
                        }
                        if (cookie == null) {
                        }
                        return cookie;
                    }
                } catch (IOException e5) {
                    objectInputStream = objectInputStream2;
                    e2 = e5;
                } catch (ClassNotFoundException e6) {
                    objectInputStream = objectInputStream2;
                    e = e6;
                } catch (Throwable th) {
                    th = th;
                    if (objectInputStream2 != null) {
                        try {
                            objectInputStream2.close();
                        } catch (IOException e7) {
                            Log.e(this.TAG, "Stream not closed in decodeCookie", e7);
                        }
                    }
                    throw th;
                }
            } catch (IOException e8) {
                Log.e(this.TAG, "Stream not closed in decodeCookie", e8);
            }
            if (readObject == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.disinfect.baselib.cookie.persistence.SerializableCookie");
            }
            cookie = ((SerializableCookie) readObject).cookie;
            objectInputStream.close();
            if (cookie == null) {
                Intrinsics.throwNpe();
            }
            return cookie;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private final byte[] hexStringToByteArray(String encodeCookie) {
        int length = encodeCookie.length();
        byte[] bArr = new byte[length / 2];
        IntProgression step = RangesKt.step(RangesKt.until(1, length), 2);
        int first = step.getFirst();
        int last = step.getLast();
        int step2 = step.getStep();
        if (step2 < 0 ? first >= last : first <= last) {
            while (true) {
                bArr[first / 2] = ((Byte) Integer.valueOf((Character.digit(encodeCookie.charAt(first), 16) << 4) + Character.digit(encodeCookie.charAt(first + 1), 16))).byteValue();
                if (first == last) {
                    break;
                }
                first += step2;
            }
        }
        return bArr;
    }

    public final long getNON_VALID_EXPIRES_AT() {
        return this.NON_VALID_EXPIRES_AT;
    }

    private final void writeObject(ObjectOutputStream out) throws IOException {
        long j;
        Cookie cookie = this.cookie;
        if (cookie == null) {
            Intrinsics.throwNpe();
        }
        out.writeObject(cookie.name());
        Cookie cookie2 = this.cookie;
        if (cookie2 == null) {
            Intrinsics.throwNpe();
        }
        out.writeObject(cookie2.value());
        Cookie cookie3 = this.cookie;
        if (cookie3 == null) {
            Intrinsics.throwNpe();
        }
        if (cookie3.persistent()) {
            Cookie cookie4 = this.cookie;
            if (cookie4 == null) {
                Intrinsics.throwNpe();
            }
            j = cookie4.expiresAt();
        } else {
            j = this.NON_VALID_EXPIRES_AT;
        }
        out.writeLong(j);
        Cookie cookie5 = this.cookie;
        if (cookie5 == null) {
            Intrinsics.throwNpe();
        }
        out.writeObject(cookie5.domain());
        Cookie cookie6 = this.cookie;
        if (cookie6 == null) {
            Intrinsics.throwNpe();
        }
        out.writeObject(cookie6.path());
        Cookie cookie7 = this.cookie;
        if (cookie7 == null) {
            Intrinsics.throwNpe();
        }
        out.writeBoolean(cookie7.secure());
        Cookie cookie8 = this.cookie;
        if (cookie8 == null) {
            Intrinsics.throwNpe();
        }
        out.writeBoolean(cookie8.httpOnly());
        Cookie cookie9 = this.cookie;
        if (cookie9 == null) {
            Intrinsics.throwNpe();
        }
        out.writeBoolean(cookie9.hostOnly());
    }

    private final void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException {
        Cookie.Builder builder = new Cookie.Builder();
        Object readObject = input.readObject();
        if (readObject == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
        }
        builder.name((String) readObject);
        Object readObject2 = input.readObject();
        if (readObject2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
        }
        builder.value((String) readObject2);
        long readLong = input.readLong();
        if (readLong != this.NON_VALID_EXPIRES_AT) {
            builder.expiresAt(readLong);
        }
        Object readObject3 = input.readObject();
        if (readObject3 == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
        }
        String str = (String) readObject3;
        builder.domain(str);
        Object readObject4 = input.readObject();
        if (readObject4 == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
        }
        builder.path((String) readObject4);
        if (input.readBoolean()) {
            builder.secure();
        }
        if (input.readBoolean()) {
            builder.httpOnly();
        }
        if (input.readBoolean()) {
            builder.hostOnlyDomain(str);
        }
        this.cookie = builder.build();
    }
}
