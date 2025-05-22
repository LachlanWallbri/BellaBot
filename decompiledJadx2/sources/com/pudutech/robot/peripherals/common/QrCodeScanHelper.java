package com.pudutech.robot.peripherals.common;

import android.content.Context;
import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot.markscanner.MarkScanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: QrCodeScanHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tJ\u000e\u0010\u0005\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010J\u0018\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0004H\u0002J\u0010\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0004H\u0002J\u000e\u0010\u0017\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/common/QrCodeScanHelper;", "", "()V", "TAG", "", "init", "", "listeners", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lcom/pudutech/robot/peripherals/common/QrScanTaskListener;", "markScanner", "Lcom/pudutech/bumblebee/robot/markscanner/MarkScanner;", "addNotifyListener", "", "l", "context", "Landroid/content/Context;", "notify", "event", "Lcom/pudutech/robot/peripherals/common/QrScanEvent;", "data", "onScanReceive", "text", "removeNotifyListener", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class QrCodeScanHelper {
    private static boolean init;
    private static MarkScanner markScanner;
    public static final QrCodeScanHelper INSTANCE = new QrCodeScanHelper();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final CopyOnWriteArrayList<QrScanTaskListener> listeners = new CopyOnWriteArrayList<>();

    private QrCodeScanHelper() {
    }

    public final synchronized void init(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        if (init) {
            return;
        }
        markScanner = new MarkScanner();
        MarkScanner markScanner2 = markScanner;
        if (markScanner2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("markScanner");
        }
        markScanner2.getMarkListener().add(TAG, new Function1<String, Unit>() { // from class: com.pudutech.robot.peripherals.common.QrCodeScanHelper$init$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                QrCodeScanHelper.INSTANCE.onScanReceive(it);
            }
        });
        MarkScanner markScanner3 = markScanner;
        if (markScanner3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("markScanner");
        }
        markScanner3.initScanner(context);
        init = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onScanReceive(String text) {
        Pdlog.m3273d(TAG, "onMarkScan : text = " + text + "; ");
        if (text == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        try {
            Matcher matcher = Pattern.compile("^([1-9][0-9]*)\\|(.*)").matcher(StringsKt.replace$default(StringsKt.trim((CharSequence) text).toString(), "\ufeff", "", false, 4, (Object) null));
            matcher.find();
            if (matcher.groupCount() == 2) {
                String v = matcher.group(1);
                Intrinsics.checkExpressionValueIsNotNull(v, "v");
                int parseInt = Integer.parseInt(v);
                if (parseInt == QrScanEvent.ONE_FOOD_ORDER.getType()) {
                    QrScanEvent qrScanEvent = QrScanEvent.ONE_FOOD_ORDER;
                    String group = matcher.group(2);
                    Intrinsics.checkExpressionValueIsNotNull(group, "matcher.group(2)");
                    notify(qrScanEvent, group);
                } else if (parseInt == QrScanEvent.EMPLOYEES_NO.getType()) {
                    QrScanEvent qrScanEvent2 = QrScanEvent.EMPLOYEES_NO;
                    String group2 = matcher.group(2);
                    Intrinsics.checkExpressionValueIsNotNull(group2, "matcher.group(2)");
                    notify(qrScanEvent2, group2);
                } else if (parseInt == QrScanEvent.OPEN_MSG.getType()) {
                    QrScanEvent qrScanEvent3 = QrScanEvent.OPEN_MSG;
                    String group3 = matcher.group(2);
                    Intrinsics.checkExpressionValueIsNotNull(group3, "matcher.group(2)");
                    notify(qrScanEvent3, group3);
                } else {
                    Pdlog.m3274e(TAG, "onMarkScan : unknown type");
                }
            } else {
                Pdlog.m3274e(TAG, "onMarkScan : matcher size more 1 : " + text);
            }
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "onMarkScan : " + Log.getStackTraceString(e));
        }
    }

    public final void addNotifyListener(QrScanTaskListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        if (listeners.contains(l)) {
            return;
        }
        listeners.remove(l);
    }

    public final void removeNotifyListener(QrScanTaskListener l) {
        Intrinsics.checkParameterIsNotNull(l, "l");
        listeners.remove(l);
    }

    private final void notify(QrScanEvent event, String data) {
        Pdlog.m3273d(TAG, "notify : event = " + event + "; data = " + data + "; ");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new QrCodeScanHelper$notify$1(event, data, null), 2, null);
    }
}
