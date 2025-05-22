package com.pudutech.event_tracking.utils;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: IpApiInfoUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/event_tracking/utils/IpApiInfoUtils;", "", "()V", "gson", "Lcom/google/gson/Gson;", "getIpApiInfo", "Lcom/pudutech/event_tracking/utils/IpApiInfo;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "is2String", "", "iss", "Ljava/io/InputStream;", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class IpApiInfoUtils {
    public static final IpApiInfoUtils INSTANCE = new IpApiInfoUtils();
    private static final Gson gson = new Gson();

    private IpApiInfoUtils() {
    }

    public final Object getIpApiInfo(Continuation<? super IpApiInfo> continuation) {
        URLConnection openConnection = new URL("http://ip-api.com/json").openConnection();
        if (openConnection == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.net.HttpURLConnection");
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.connect();
        if (httpURLConnection.getResponseCode() != 200) {
            return null;
        }
        InputStream inputStream = httpURLConnection.getInputStream();
        Intrinsics.checkExpressionValueIsNotNull(inputStream, "inputStream");
        return (IpApiInfo) gson.fromJson(is2String(inputStream), IpApiInfo.class);
    }

    public final String is2String(InputStream iss) {
        Intrinsics.checkParameterIsNotNull(iss, "iss");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(iss, "utf-8"));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                break;
            }
            sb.append(readLine);
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "stringBuilder.toString()");
        String str = sb2;
        int length = str.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean z2 = str.charAt(!z ? i : length) <= ' ';
            if (z) {
                if (!z2) {
                    break;
                }
                length--;
            } else if (z2) {
                i++;
            } else {
                z = true;
            }
        }
        return str.subSequence(i, length + 1).toString();
    }
}
