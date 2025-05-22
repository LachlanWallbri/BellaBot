package com.pudutech.bumblebee.business.peripherals_task.qr_scan_task;

import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.base.BaseMultiListenerImpl;
import com.pudutech.bumblebee.business.robotsdk.peripherals_listeners.MarkScanListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: QrScanTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006H\u0002J\u0010\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u0006H\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\u0006H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/peripherals_task/qr_scan_task/QrScanTask;", "Lcom/pudutech/bumblebee/business/base/BaseMultiListenerImpl;", "Lcom/pudutech/bumblebee/business/peripherals_task/qr_scan_task/QrScanTaskListener;", "Lcom/pudutech/bumblebee/business/robotsdk/peripherals_listeners/MarkScanListener;", "()V", "TAG", "", "notify", "", "event", "Lcom/pudutech/bumblebee/business/peripherals_task/qr_scan_task/QrScanEvent;", NotificationCompat.CATEGORY_MESSAGE, "onMarkScan", "text", "parseToOldTypeIfNeed", "", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class QrScanTask extends BaseMultiListenerImpl<QrScanTaskListener> implements MarkScanListener {
    private final String TAG = "QrScanTask";

    @Override // com.pudutech.bumblebee.business.robotsdk.peripherals_listeners.MarkScanListener
    public void onMarkScan(String text) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        Pdlog.m3273d(this.TAG, "onMarkScan : text = " + text + "; ");
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
                    Pdlog.m3274e(this.TAG, "onMarkScan : unknown type");
                }
            } else {
                Pdlog.m3274e(this.TAG, "onMarkScan : matcher size more 1 : " + text);
            }
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, "onMarkScan : " + Log.getStackTraceString(e));
        }
    }

    private final boolean parseToOldTypeIfNeed(String text) {
        if (StringsKt.split$default((CharSequence) text, new String[]{"|"}, false, 0, 6, (Object) null).size() != 5) {
            return false;
        }
        notify(QrScanEvent.ONE_FOOD_ORDER, text);
        return true;
    }

    private final void notify(final QrScanEvent event, final String msg) {
        getListeners().forEach(new Function1<QrScanTaskListener, Unit>() { // from class: com.pudutech.bumblebee.business.peripherals_task.qr_scan_task.QrScanTask$notify$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(QrScanTaskListener qrScanTaskListener) {
                invoke2(qrScanTaskListener);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(QrScanTaskListener it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                it.onQrScanEvent(QrScanEvent.this, msg);
            }
        });
    }
}
