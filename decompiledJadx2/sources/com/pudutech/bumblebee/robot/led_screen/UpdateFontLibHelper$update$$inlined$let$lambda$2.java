package com.pudutech.bumblebee.robot.led_screen;

import android.content.Context;
import com.pudutech.bumblebee.robot.aidl.IUpdateListener;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.ICANData;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: UpdateFontLibHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/bumblebee/robot/led_screen/UpdateFontLibHelper$update$2$2"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class UpdateFontLibHelper$update$$inlined$let$lambda$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context$inlined;
    final /* synthetic */ byte[] $fileContent;
    final /* synthetic */ HardwareInterface $inf$inlined;
    final /* synthetic */ boolean $isForce$inlined;
    final /* synthetic */ IUpdateListener $listener$inlined;
    final /* synthetic */ int $targetVersion;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4787p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UpdateFontLibHelper$update$$inlined$let$lambda$2(int i, byte[] bArr, Continuation continuation, Context context, IUpdateListener iUpdateListener, HardwareInterface hardwareInterface, boolean z) {
        super(2, continuation);
        this.$targetVersion = i;
        this.$fileContent = bArr;
        this.$context$inlined = context;
        this.$listener$inlined = iUpdateListener;
        this.$inf$inlined = hardwareInterface;
        this.$isForce$inlined = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        UpdateFontLibHelper$update$$inlined$let$lambda$2 updateFontLibHelper$update$$inlined$let$lambda$2 = new UpdateFontLibHelper$update$$inlined$let$lambda$2(this.$targetVersion, this.$fileContent, completion, this.$context$inlined, this.$listener$inlined, this.$inf$inlined, this.$isForce$inlined);
        updateFontLibHelper$update$$inlined$let$lambda$2.f4787p$ = (CoroutineScope) obj;
        return updateFontLibHelper$update$$inlined$let$lambda$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UpdateFontLibHelper$update$$inlined$let$lambda$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        UpdateFontLib updateFontLib;
        UpdateFontLib updateFontLib2;
        UpdateFontLib updateFontLib3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4787p$;
            updateFontLib = UpdateFontLibHelper.INSTANCE.getUpdateFontLib();
            updateFontLib.setSender(new Function1<UByteArray, Unit>() { // from class: com.pudutech.bumblebee.robot.led_screen.UpdateFontLibHelper$update$$inlined$let$lambda$2.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(UByteArray uByteArray) {
                    invoke(uByteArray.getStorage());
                    return Unit.INSTANCE;
                }

                public final void invoke(byte[] it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    HardwareInterface hardwareInterface = UpdateFontLibHelper$update$$inlined$let$lambda$2.this.$inf$inlined;
                    byte[] copyOf = Arrays.copyOf(it, it.length);
                    Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
                    hardwareInterface.sendCAN(copyOf);
                }
            });
            HardwareInterface hardwareInterface = this.$inf$inlined;
            updateFontLib2 = UpdateFontLibHelper.INSTANCE.getUpdateFontLib();
            hardwareInterface.addCANDataListener("UpdateFontLib", new byte[]{updateFontLib2.getProtocolHead()}, new ICANData.Stub() { // from class: com.pudutech.bumblebee.robot.led_screen.UpdateFontLibHelper$update$$inlined$let$lambda$2.2
                @Override // com.pudutech.mirsdk.hardware.ICANData
                public void onData(int p0, byte[] p1) {
                    UpdateFontLib updateFontLib4;
                    updateFontLib4 = UpdateFontLibHelper.INSTANCE.getUpdateFontLib();
                    Function1<UByteArray, Unit> receiveListener = updateFontLib4.getReceiveListener();
                    if (receiveListener != null) {
                        if (p1 == null) {
                            Intrinsics.throwNpe();
                        }
                        byte[] copyOf = Arrays.copyOf(p1, p1.length);
                        Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
                        receiveListener.invoke(UByteArray.m4570boximpl(UByteArray.m4572constructorimpl(copyOf)));
                    }
                }
            });
            updateFontLib3 = UpdateFontLibHelper.INSTANCE.getUpdateFontLib();
            int i2 = this.$targetVersion;
            byte[] bArr = this.$fileContent;
            boolean z = this.$isForce$inlined;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (updateFontLib3.doCase(i2, bArr, z, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        this.$inf$inlined.removeCANDataListener("UpdateFontLib");
        return Unit.INSTANCE;
    }
}
