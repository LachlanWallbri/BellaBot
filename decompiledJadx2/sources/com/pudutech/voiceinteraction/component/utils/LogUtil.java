package com.pudutech.voiceinteraction.component.utils;

import androidx.core.app.NotificationCompat;
import com.aliyun.alink.linksdk.alcs.coap.resources.LinkFormat;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.base.Pdlog;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: LogUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J-\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0016\u0010\u0007\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\b\"\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\tJ7\u0010\u0003\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0016\u0010\u0007\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\b\"\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u000bJ3\u0010\f\u001a\u0004\u0018\u00010\u00062\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000f2\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\b\"\u00020\u0001H\u0002¢\u0006\u0002\u0010\u0010J%\u0010\u0011\u001a\u00020\u00062\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002¢\u0006\u0002\u0010\u0014¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/utils/LogUtil;", "", "()V", LinkFormat.DOMAIN, "", AIUIConstant.KEY_CONTENT, "", "args", "", "(Ljava/lang/String;[Ljava/lang/Object;)V", AIUIConstant.KEY_TAG, "(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V", "getContent", NotificationCompat.CATEGORY_MESSAGE, "place", "", "(Ljava/lang/String;I[Ljava/lang/Object;)Ljava/lang/String;", "getNameFromTrace", "traceElements", "Ljava/lang/StackTraceElement;", "([Ljava/lang/StackTraceElement;I)Ljava/lang/String;", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class LogUtil {
    public static final LogUtil INSTANCE = new LogUtil();

    private LogUtil() {
    }

    /* renamed from: d */
    public final void m3310d(String content, Object... args) {
        Intrinsics.checkParameterIsNotNull(args, "args");
        Thread currentThread = Thread.currentThread();
        Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
        StackTraceElement[] stackTrace = currentThread.getStackTrace();
        Intrinsics.checkExpressionValueIsNotNull(stackTrace, "Thread.currentThread().stackTrace");
        int length = stackTrace.length;
        for (int i = 0; i < length; i++) {
            if (content == null) {
                Intrinsics.throwNpe();
            }
            Pdlog.m3273d("default", getContent(content, i, args));
        }
    }

    /* renamed from: d */
    public final void m3309d(String tag, String content, Object... args) {
        Intrinsics.checkParameterIsNotNull(args, "args");
        Object[] objArr = new Object[1];
        if (content == null) {
            Intrinsics.throwNpe();
        }
        objArr[0] = getContent(content, 4, args);
        Pdlog.m3273d(tag, objArr);
    }

    private final String getContent(String msg, int place, Object... args) {
        try {
            Thread currentThread = Thread.currentThread();
            Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
            String nameFromTrace = getNameFromTrace(currentThread.getStackTrace(), place);
            StringBuilder sb = new StringBuilder();
            sb.append(nameFromTrace);
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Object[] copyOf = Arrays.copyOf(args, args.length);
            String format = String.format(msg, Arrays.copyOf(copyOf, copyOf.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            sb.append(format);
            return sb.toString();
        } catch (Throwable unused) {
            return msg;
        }
    }

    private final String getNameFromTrace(StackTraceElement[] traceElements, int place) {
        StringBuilder sb = new StringBuilder();
        if (traceElements != null && traceElements.length > place) {
            StackTraceElement stackTraceElement = traceElements[place];
            sb.append(stackTraceElement.getMethodName());
            sb.append("(");
            sb.append(stackTraceElement.getFileName());
            sb.append(":");
            sb.append(stackTraceElement.getLineNumber());
            sb.append(")");
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "taskName.toString()");
        return sb2;
    }
}
