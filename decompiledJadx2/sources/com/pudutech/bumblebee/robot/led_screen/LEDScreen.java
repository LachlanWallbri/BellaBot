package com.pudutech.bumblebee.robot.led_screen;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot.aidl.serialize.LEDScreenMode;
import com.pudutech.bumblebee.robot.protocol_utils.ProtocolUtils;
import com.pudutech.bumblebee.robot.protocol_utils.UByteArrayUtilsKt;
import java.util.concurrent.CancellationException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.ThreadPoolDispatcherKt;
import kotlinx.coroutines.TimeoutKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: LEDScreen.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 )2\u00020\u0001:\u0001)B\u0005¢\u0006\u0002\u0010\u0002J\u0011\u0010 \u001a\u00020\u0013H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010!J\u000e\u0010\"\u001a\u00020\u00132\u0006\u0010#\u001a\u00020$J\u001e\u0010%\u001a\u00020\u00132\u0006\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u001e2\u0006\u0010(\u001a\u00020\u001eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0013\u0010\u0005\u001a\u00020\u0006X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\n\u001a\u00020\u0006X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0007R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR-\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00128FX\u0086\u000eø\u0001\u0000¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0013\u0010\u0018\u001a\u00020\u0006X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0007RC\u0010\u001a\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00122\u0014\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012@FX\u0086\u000eø\u0001\u0000¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0015\"\u0004\b\u001c\u0010\u0017R\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006*"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/led_screen/LEDScreen;", "", "()V", "TAG", "", "config", "Lkotlin/UByteArray;", "[B", "job", "Lkotlinx/coroutines/Job;", "modeAnimation", "protocolUtils", "Lcom/pudutech/bumblebee/robot/protocol_utils/ProtocolUtils;", "getProtocolUtils", "()Lcom/pudutech/bumblebee/robot/protocol_utils/ProtocolUtils;", "protocolUtils$delegate", "Lkotlin/Lazy;", "receiveListener", "Lkotlin/Function1;", "", "getReceiveListener", "()Lkotlin/jvm/functions/Function1;", "setReceiveListener", "(Lkotlin/jvm/functions/Function1;)V", "requestVersion", ES6Iterator.VALUE_PROPERTY, "sender", "getSender", "setSender", "version", "", "Ljava/lang/Integer;", "checkVersion", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "showAnimation", "mode", "Lcom/pudutech/bumblebee/robot/aidl/serialize/LEDScreenMode;", "showText", AIUIConstant.KEY_CONTENT, TypedValues.Custom.S_COLOR, "offsetY", "Companion", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class LEDScreen {
    private Job job;
    private Function1<? super UByteArray, Unit> receiveListener;
    private Function1<? super UByteArray, Unit> sender;
    private Integer version;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ExecutorCoroutineDispatcher woker = ThreadPoolDispatcherKt.newSingleThreadContext("LEDScreen");
    private static final byte ProtocolHead = UByte.m4528constructorimpl((byte) 150);
    private static final byte VersionProtocolHead = UByte.m4528constructorimpl((byte) 149);
    private final String TAG = "LEDScreen";

    /* renamed from: protocolUtils$delegate, reason: from kotlin metadata */
    private final Lazy protocolUtils = LazyKt.lazy(new Function0<ProtocolUtils>() { // from class: com.pudutech.bumblebee.robot.led_screen.LEDScreen$protocolUtils$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ProtocolUtils invoke() {
            return new ProtocolUtils();
        }
    });
    private final byte[] config = {-106, 1, 0, 0, 0, 0, 0};
    private final byte[] modeAnimation = {-110, 0, 0, 0, 0, 0, 0, 0};
    private final byte[] requestVersion = {-107, 6, 0, 0, 0, 0, 0};

    /* JADX INFO: Access modifiers changed from: private */
    public final ProtocolUtils getProtocolUtils() {
        return (ProtocolUtils) this.protocolUtils.getValue();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* compiled from: LEDScreen.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u0016\u0010\b\u001a\u00020\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/led_screen/LEDScreen$Companion;", "", "()V", "ProtocolHead", "Lkotlin/UByte;", "getProtocolHead", "()B", "B", "VersionProtocolHead", "getVersionProtocolHead", "woker", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "Robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final byte getProtocolHead() {
            return LEDScreen.ProtocolHead;
        }

        public final byte getVersionProtocolHead() {
            return LEDScreen.VersionProtocolHead;
        }
    }

    public final void setReceiveListener(Function1<? super UByteArray, Unit> function1) {
        this.receiveListener = function1;
    }

    public final Function1<UByteArray, Unit> getReceiveListener() {
        return getProtocolUtils().getReceiveListener();
    }

    public final Function1<UByteArray, Unit> getSender() {
        return this.sender;
    }

    public final void setSender(Function1<? super UByteArray, Unit> function1) {
        this.sender = function1;
        getProtocolUtils().setSender(function1);
        Pdlog.m3275i(this.TAG, "set sender=" + function1);
    }

    public final void showAnimation(LEDScreenMode mode) {
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        Pdlog.m3273d(this.TAG, "showAnimation mode=" + mode);
        Job job = this.job;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, woker, null, new LEDScreen$showAnimation$1(this, mode, null), 2, null);
        this.job = launch$default;
    }

    public final void showText(String content, int color, int offsetY) {
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(content, "content");
        Pdlog.m3273d(this.TAG, "showText content=" + content + "  color=" + color + "  offsetY=" + offsetY + " fontVersion=" + this.version);
        Job job = this.job;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, woker, null, new LEDScreen$showText$1(this, content, color, offsetY, null), 2, null);
        this.job = launch$default;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final /* synthetic */ Object checkVersion(Continuation<? super Unit> continuation) {
        LEDScreen$checkVersion$1 lEDScreen$checkVersion$1;
        int i;
        int i2;
        LEDScreen lEDScreen;
        byte[] storage;
        byte[] m4572constructorimpl;
        Integer boxInt;
        if (continuation instanceof LEDScreen$checkVersion$1) {
            lEDScreen$checkVersion$1 = (LEDScreen$checkVersion$1) continuation;
            if ((lEDScreen$checkVersion$1.label & Integer.MIN_VALUE) != 0) {
                lEDScreen$checkVersion$1.label -= Integer.MIN_VALUE;
                Object obj = lEDScreen$checkVersion$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = lEDScreen$checkVersion$1.label;
                i2 = 0;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    if (this.version != null) {
                        return Unit.INSTANCE;
                    }
                    Pdlog.m3273d(this.TAG, "checkVersion ");
                    LEDScreen$checkVersion$response$1 lEDScreen$checkVersion$response$1 = new LEDScreen$checkVersion$response$1(this, null);
                    lEDScreen$checkVersion$1.L$0 = this;
                    lEDScreen$checkVersion$1.label = 1;
                    obj = TimeoutKt.withTimeoutOrNull(200L, lEDScreen$checkVersion$response$1, lEDScreen$checkVersion$1);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    lEDScreen = this;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    lEDScreen = (LEDScreen) lEDScreen$checkVersion$1.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                UByteArray uByteArray = (UByteArray) obj;
                storage = uByteArray != null ? uByteArray.getStorage() : null;
                if (storage != null && (m4572constructorimpl = UByteArray.m4572constructorimpl(ArraysKt.copyOfRange(storage, 2, 6))) != null && (boxInt = Boxing.boxInt(UByteArrayUtilsKt.m4335toUIntGBYM_sE(m4572constructorimpl))) != null) {
                    i2 = boxInt.intValue();
                }
                lEDScreen.version = Boxing.boxInt(i2);
                return Unit.INSTANCE;
            }
        }
        lEDScreen$checkVersion$1 = new LEDScreen$checkVersion$1(this, continuation);
        Object obj2 = lEDScreen$checkVersion$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = lEDScreen$checkVersion$1.label;
        i2 = 0;
        if (i != 0) {
        }
        UByteArray uByteArray2 = (UByteArray) obj2;
        if (uByteArray2 != null) {
        }
        if (storage != null) {
            i2 = boxInt.intValue();
        }
        lEDScreen.version = Boxing.boxInt(i2);
        return Unit.INSTANCE;
    }
}
