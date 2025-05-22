package com.pudutech.schedulerlib.p065ui;

import android.widget.TextView;
import com.pudutech.base.Pdlog;
import com.pudutech.schedulerlib.C5725R;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SchedulerPressTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.schedulerlib.ui.SchedulerPressTestActivity$transmitRateTestStart$4", m3970f = "SchedulerPressTestActivity.kt", m3971i = {0}, m3972l = {270}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes7.dex */
public final class SchedulerPressTestActivity$transmitRateTestStart$4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7490p$;
    final /* synthetic */ SchedulerPressTestActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SchedulerPressTestActivity$transmitRateTestStart$4(SchedulerPressTestActivity schedulerPressTestActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = schedulerPressTestActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SchedulerPressTestActivity$transmitRateTestStart$4 schedulerPressTestActivity$transmitRateTestStart$4 = new SchedulerPressTestActivity$transmitRateTestStart$4(this.this$0, completion);
        schedulerPressTestActivity$transmitRateTestStart$4.f7490p$ = (CoroutineScope) obj;
        return schedulerPressTestActivity$transmitRateTestStart$4;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SchedulerPressTestActivity$transmitRateTestStart$4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x003f  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:9:0x0049 -> B:5:0x004c). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Object obj2;
        SchedulerPressTestActivity$transmitRateTestStart$4 schedulerPressTestActivity$transmitRateTestStart$4;
        boolean z;
        AtomicLong atomicLong;
        AtomicLong atomicLong2;
        AtomicLong atomicLong3;
        AtomicLong atomicLong4;
        long j;
        long j2;
        String str;
        String str2;
        AtomicLong atomicLong5;
        AtomicLong atomicLong6;
        String str3;
        AtomicLong atomicLong7;
        AtomicLong atomicLong8;
        long j3;
        long j4;
        AtomicLong atomicLong9;
        long j5;
        AtomicLong atomicLong10;
        DecimalFormat decimalFormat;
        long j6;
        long j7;
        DecimalFormat decimalFormat2;
        long j8;
        long j9;
        String str4;
        String str5;
        long j10;
        long j11;
        String str6;
        AtomicLong atomicLong11;
        AtomicLong atomicLong12;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        String str7 = "sendMsgRate";
        String str8 = "transmitRate";
        char c = 0;
        int i2 = 1;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f7490p$;
            obj2 = coroutine_suspended;
            schedulerPressTestActivity$transmitRateTestStart$4 = this;
            z = schedulerPressTestActivity$transmitRateTestStart$4.this$0.transmitRateButtonFlag;
            if (!z) {
            }
        } else if (i == 1) {
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            obj2 = coroutine_suspended;
            schedulerPressTestActivity$transmitRateTestStart$4 = this;
            str2 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.TAG;
            Object[] objArr = new Object[2];
            objArr[c] = "recv msg speed is ";
            atomicLong5 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.recvMsgs;
            objArr[1] = atomicLong5;
            Pdlog.m3273d(str2, objArr);
            TextView textView = (TextView) schedulerPressTestActivity$transmitRateTestStart$4.this$0._$_findCachedViewById(C5725R.id.transmitRate);
            Intrinsics.checkExpressionValueIsNotNull(textView, str8);
            StringBuilder sb = new StringBuilder();
            sb.append("接收：");
            atomicLong6 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.recvMsgs;
            sb.append(atomicLong6);
            sb.append(" 帧/s");
            textView.setText(sb.toString());
            str3 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.TAG;
            atomicLong7 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.sendMsgs;
            Pdlog.m3273d(str3, "send msg speed is ", atomicLong7);
            TextView textView2 = (TextView) schedulerPressTestActivity$transmitRateTestStart$4.this$0._$_findCachedViewById(C5725R.id.sendMsgRate);
            Intrinsics.checkExpressionValueIsNotNull(textView2, str7);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("发送：");
            atomicLong8 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.sendMsgs;
            sb2.append(atomicLong8);
            sb2.append(" 帧/s");
            textView2.setText(sb2.toString());
            SchedulerPressTestActivity schedulerPressTestActivity = schedulerPressTestActivity$transmitRateTestStart$4.this$0;
            j3 = schedulerPressTestActivity.timeCnt;
            schedulerPressTestActivity.timeCnt = j3 + 1;
            SchedulerPressTestActivity schedulerPressTestActivity2 = schedulerPressTestActivity$transmitRateTestStart$4.this$0;
            j4 = schedulerPressTestActivity2.sendAverageCnt;
            atomicLong9 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.sendMsgs;
            schedulerPressTestActivity2.sendAverageCnt = j4 + atomicLong9.get();
            SchedulerPressTestActivity schedulerPressTestActivity3 = schedulerPressTestActivity$transmitRateTestStart$4.this$0;
            j5 = schedulerPressTestActivity3.recvAverageCnt;
            atomicLong10 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.recvMsgs;
            schedulerPressTestActivity3.recvAverageCnt = j5 + atomicLong10.get();
            decimalFormat = schedulerPressTestActivity$transmitRateTestStart$4.this$0.format;
            j6 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.sendAverageCnt;
            j7 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.timeCnt;
            String format = decimalFormat.format(j6 / j7);
            decimalFormat2 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.format;
            j8 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.recvAverageCnt;
            String str9 = str7;
            String str10 = str8;
            j9 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.timeCnt;
            String format2 = decimalFormat2.format(j8 / j9);
            str4 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.TAG;
            Pdlog.m3273d(str4, "send msg average rate is ", format);
            str5 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.TAG;
            Pdlog.m3273d(str5, "recv msg average rate is ", format2);
            j10 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.timeCnt;
            long j12 = 3600;
            long j13 = j10 / j12;
            j11 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.timeCnt;
            long j14 = j11 % j12;
            long j15 = 60;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Object[] objArr2 = {Boxing.boxLong(j13), Boxing.boxLong(j14 / j15), Boxing.boxLong(j14 % j15)};
            String format3 = String.format("%02d小时 %02d分 %02d秒", Arrays.copyOf(objArr2, objArr2.length));
            Intrinsics.checkExpressionValueIsNotNull(format3, "java.lang.String.format(format, *args)");
            str6 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.TAG;
            Pdlog.m3273d(str6, "速率测试累计传输时间：" + format3);
            TextView timeCount = (TextView) schedulerPressTestActivity$transmitRateTestStart$4.this$0._$_findCachedViewById(C5725R.id.timeCount);
            Intrinsics.checkExpressionValueIsNotNull(timeCount, "timeCount");
            timeCount.setText("传输时间：" + format3);
            TextView sendAverageRate = (TextView) schedulerPressTestActivity$transmitRateTestStart$4.this$0._$_findCachedViewById(C5725R.id.sendAverageRate);
            Intrinsics.checkExpressionValueIsNotNull(sendAverageRate, "sendAverageRate");
            sendAverageRate.setText("平均发送速率： " + format + " 帧/s");
            TextView recvAverageRate = (TextView) schedulerPressTestActivity$transmitRateTestStart$4.this$0._$_findCachedViewById(C5725R.id.recvAverageRate);
            Intrinsics.checkExpressionValueIsNotNull(recvAverageRate, "recvAverageRate");
            recvAverageRate.setText("平均接收速率： " + format2 + " 帧/s");
            atomicLong11 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.recvMsgs;
            atomicLong11.set(0L);
            atomicLong12 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.sendMsgs;
            atomicLong12.set(0L);
            obj2 = obj2;
            str7 = str9;
            str8 = str10;
            c = 0;
            i2 = 1;
            z = schedulerPressTestActivity$transmitRateTestStart$4.this$0.transmitRateButtonFlag;
            if (!z) {
                schedulerPressTestActivity$transmitRateTestStart$4.L$0 = coroutineScope;
                schedulerPressTestActivity$transmitRateTestStart$4.label = i2;
                if (DelayKt.delay(1000L, schedulerPressTestActivity$transmitRateTestStart$4) == obj2) {
                    return obj2;
                }
                str2 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.TAG;
                Object[] objArr3 = new Object[2];
                objArr3[c] = "recv msg speed is ";
                atomicLong5 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.recvMsgs;
                objArr3[1] = atomicLong5;
                Pdlog.m3273d(str2, objArr3);
                TextView textView3 = (TextView) schedulerPressTestActivity$transmitRateTestStart$4.this$0._$_findCachedViewById(C5725R.id.transmitRate);
                Intrinsics.checkExpressionValueIsNotNull(textView3, str8);
                StringBuilder sb3 = new StringBuilder();
                sb3.append("接收：");
                atomicLong6 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.recvMsgs;
                sb3.append(atomicLong6);
                sb3.append(" 帧/s");
                textView3.setText(sb3.toString());
                str3 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.TAG;
                atomicLong7 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.sendMsgs;
                Pdlog.m3273d(str3, "send msg speed is ", atomicLong7);
                TextView textView22 = (TextView) schedulerPressTestActivity$transmitRateTestStart$4.this$0._$_findCachedViewById(C5725R.id.sendMsgRate);
                Intrinsics.checkExpressionValueIsNotNull(textView22, str7);
                StringBuilder sb22 = new StringBuilder();
                sb22.append("发送：");
                atomicLong8 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.sendMsgs;
                sb22.append(atomicLong8);
                sb22.append(" 帧/s");
                textView22.setText(sb22.toString());
                SchedulerPressTestActivity schedulerPressTestActivity4 = schedulerPressTestActivity$transmitRateTestStart$4.this$0;
                j3 = schedulerPressTestActivity4.timeCnt;
                schedulerPressTestActivity4.timeCnt = j3 + 1;
                SchedulerPressTestActivity schedulerPressTestActivity22 = schedulerPressTestActivity$transmitRateTestStart$4.this$0;
                j4 = schedulerPressTestActivity22.sendAverageCnt;
                atomicLong9 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.sendMsgs;
                schedulerPressTestActivity22.sendAverageCnt = j4 + atomicLong9.get();
                SchedulerPressTestActivity schedulerPressTestActivity32 = schedulerPressTestActivity$transmitRateTestStart$4.this$0;
                j5 = schedulerPressTestActivity32.recvAverageCnt;
                atomicLong10 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.recvMsgs;
                schedulerPressTestActivity32.recvAverageCnt = j5 + atomicLong10.get();
                decimalFormat = schedulerPressTestActivity$transmitRateTestStart$4.this$0.format;
                j6 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.sendAverageCnt;
                j7 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.timeCnt;
                String format4 = decimalFormat.format(j6 / j7);
                decimalFormat2 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.format;
                j8 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.recvAverageCnt;
                String str92 = str7;
                String str102 = str8;
                j9 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.timeCnt;
                String format22 = decimalFormat2.format(j8 / j9);
                str4 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.TAG;
                Pdlog.m3273d(str4, "send msg average rate is ", format4);
                str5 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.TAG;
                Pdlog.m3273d(str5, "recv msg average rate is ", format22);
                j10 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.timeCnt;
                long j122 = 3600;
                long j132 = j10 / j122;
                j11 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.timeCnt;
                long j142 = j11 % j122;
                long j152 = 60;
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                Object[] objArr22 = {Boxing.boxLong(j132), Boxing.boxLong(j142 / j152), Boxing.boxLong(j142 % j152)};
                String format32 = String.format("%02d小时 %02d分 %02d秒", Arrays.copyOf(objArr22, objArr22.length));
                Intrinsics.checkExpressionValueIsNotNull(format32, "java.lang.String.format(format, *args)");
                str6 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.TAG;
                Pdlog.m3273d(str6, "速率测试累计传输时间：" + format32);
                TextView timeCount2 = (TextView) schedulerPressTestActivity$transmitRateTestStart$4.this$0._$_findCachedViewById(C5725R.id.timeCount);
                Intrinsics.checkExpressionValueIsNotNull(timeCount2, "timeCount");
                timeCount2.setText("传输时间：" + format32);
                TextView sendAverageRate2 = (TextView) schedulerPressTestActivity$transmitRateTestStart$4.this$0._$_findCachedViewById(C5725R.id.sendAverageRate);
                Intrinsics.checkExpressionValueIsNotNull(sendAverageRate2, "sendAverageRate");
                sendAverageRate2.setText("平均发送速率： " + format4 + " 帧/s");
                TextView recvAverageRate2 = (TextView) schedulerPressTestActivity$transmitRateTestStart$4.this$0._$_findCachedViewById(C5725R.id.recvAverageRate);
                Intrinsics.checkExpressionValueIsNotNull(recvAverageRate2, "recvAverageRate");
                recvAverageRate2.setText("平均接收速率： " + format22 + " 帧/s");
                atomicLong11 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.recvMsgs;
                atomicLong11.set(0L);
                atomicLong12 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.sendMsgs;
                atomicLong12.set(0L);
                obj2 = obj2;
                str7 = str92;
                str8 = str102;
                c = 0;
                i2 = 1;
                z = schedulerPressTestActivity$transmitRateTestStart$4.this$0.transmitRateButtonFlag;
                if (!z) {
                    String str11 = str8;
                    schedulerPressTestActivity$transmitRateTestStart$4.this$0.closeEspForPressTest();
                    atomicLong = schedulerPressTestActivity$transmitRateTestStart$4.this$0.sendMsgs;
                    atomicLong.set(0L);
                    atomicLong2 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.recvMsgs;
                    atomicLong2.set(0L);
                    schedulerPressTestActivity$transmitRateTestStart$4.this$0.recvAverageCnt = 0L;
                    schedulerPressTestActivity$transmitRateTestStart$4.this$0.sendAverageCnt = 0L;
                    schedulerPressTestActivity$transmitRateTestStart$4.this$0.timeCnt = 0L;
                    TextView textView4 = (TextView) schedulerPressTestActivity$transmitRateTestStart$4.this$0._$_findCachedViewById(C5725R.id.sendMsgRate);
                    Intrinsics.checkExpressionValueIsNotNull(textView4, str7);
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("发送： ");
                    atomicLong3 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.sendMsgs;
                    sb4.append(atomicLong3);
                    sb4.append(" 帧/s");
                    textView4.setText(sb4.toString());
                    TextView textView5 = (TextView) schedulerPressTestActivity$transmitRateTestStart$4.this$0._$_findCachedViewById(C5725R.id.transmitRate);
                    Intrinsics.checkExpressionValueIsNotNull(textView5, str11);
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append("接收： ");
                    atomicLong4 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.recvMsgs;
                    sb5.append(atomicLong4);
                    sb5.append(" 帧/s");
                    textView5.setText(sb5.toString());
                    TextView timeCount3 = (TextView) schedulerPressTestActivity$transmitRateTestStart$4.this$0._$_findCachedViewById(C5725R.id.timeCount);
                    Intrinsics.checkExpressionValueIsNotNull(timeCount3, "timeCount");
                    timeCount3.setText("传输时间：0 小时 0 分 0 秒");
                    TextView sendAverageRate3 = (TextView) schedulerPressTestActivity$transmitRateTestStart$4.this$0._$_findCachedViewById(C5725R.id.sendAverageRate);
                    Intrinsics.checkExpressionValueIsNotNull(sendAverageRate3, "sendAverageRate");
                    StringBuilder sb6 = new StringBuilder();
                    sb6.append("平均发送速率： ");
                    j = schedulerPressTestActivity$transmitRateTestStart$4.this$0.sendAverageCnt;
                    sb6.append(j);
                    sb6.append(" 帧/s");
                    sendAverageRate3.setText(sb6.toString());
                    TextView recvAverageRate3 = (TextView) schedulerPressTestActivity$transmitRateTestStart$4.this$0._$_findCachedViewById(C5725R.id.recvAverageRate);
                    Intrinsics.checkExpressionValueIsNotNull(recvAverageRate3, "recvAverageRate");
                    StringBuilder sb7 = new StringBuilder();
                    sb7.append("平均接收速率： ");
                    j2 = schedulerPressTestActivity$transmitRateTestStart$4.this$0.recvAverageCnt;
                    sb7.append(j2);
                    sb7.append(" 帧/s");
                    recvAverageRate3.setText(sb7.toString());
                    str = schedulerPressTestActivity$transmitRateTestStart$4.this$0.TAG;
                    Pdlog.m3273d(str, "速率测试launch代码结束：switchTest end...");
                    return Unit.INSTANCE;
                }
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
