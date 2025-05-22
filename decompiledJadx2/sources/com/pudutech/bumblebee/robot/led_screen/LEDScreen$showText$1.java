package com.pudutech.bumblebee.robot.led_screen;

import com.google.zxing.common.StringUtils;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot.protocol_utils.UByteArrayUtilsKt;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.TimeoutKt;
import org.jetbrains.anko.DimensionsKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: LEDScreen.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot.led_screen.LEDScreen$showText$1", m3970f = "LEDScreen.kt", m3971i = {0, 1, 1}, m3972l = {53, 72}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "bytes"}, m3975s = {"L$0", "L$0", "L$1"})
/* loaded from: classes.dex */
public final class LEDScreen$showText$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $color;
    final /* synthetic */ String $content;
    final /* synthetic */ int $offsetY;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4782p$;
    final /* synthetic */ LEDScreen this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LEDScreen$showText$1(LEDScreen lEDScreen, String str, int i, int i2, Continuation continuation) {
        super(2, continuation);
        this.this$0 = lEDScreen;
        this.$content = str;
        this.$color = i;
        this.$offsetY = i2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        LEDScreen$showText$1 lEDScreen$showText$1 = new LEDScreen$showText$1(this.this$0, this.$content, this.$color, this.$offsetY, completion);
        lEDScreen$showText$1.f4782p$ = (CoroutineScope) obj;
        return lEDScreen$showText$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LEDScreen$showText$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x01f2  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0129 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0130  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Integer num;
        Integer num2;
        byte[] m4572constructorimpl;
        byte[] bArr;
        byte[] bArr2;
        byte[] bArr3;
        byte[] bArr4;
        byte[] bArr5;
        int i;
        byte m4528constructorimpl;
        Object withTimeoutOrNull;
        byte[] bArr6;
        byte[] storage;
        String str;
        String str2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 == 2) {
                    bArr6 = (byte[]) this.L$1;
                    ResultKt.throwOnFailure(obj);
                    UByteArray uByteArray = (UByteArray) obj;
                    storage = uByteArray == null ? uByteArray.getStorage() : null;
                    if (storage != null || UByteArray.m4577getimpl(storage, 1) != UByte.m4528constructorimpl((byte) 2) || UByteArray.m4577getimpl(storage, 2) != UByte.m4528constructorimpl((byte) DimensionsKt.HDPI)) {
                        str = this.this$0.TAG;
                        Object[] objArr = new Object[1];
                        StringBuilder sb = new StringBuilder();
                        sb.append("protocol head fail. ");
                        sb.append(storage != null ? UByteArrayUtilsKt.m4334toHexStringGBYM_sE(storage) : null);
                        objArr[0] = sb.toString();
                        Pdlog.m3277w(str, objArr);
                    } else {
                        List<UByteArray> m4333splitPpDY95g = UByteArrayUtilsKt.m4333splitPpDY95g(bArr6, 6);
                        str2 = this.this$0.TAG;
                        Pdlog.m3276v(str2, "sendSegment bytes.size=" + UByteArray.m4578getSizeimpl(bArr6) + " split to " + m4333splitPpDY95g.size());
                        byte[] bArr7 = {-106, 3};
                        Iterator<T> it = m4333splitPpDY95g.iterator();
                        while (it.hasNext()) {
                            byte[] storage2 = ((UByteArray) it.next()).getStorage();
                            byte[] m4572constructorimpl2 = UByteArray.m4572constructorimpl(ArraysKt.plus(bArr7, storage2));
                            int m4578getSizeimpl = (8 - UByteArray.m4578getSizeimpl(storage2)) - 2;
                            byte[] bArr8 = new byte[m4578getSizeimpl];
                            for (int i3 = 0; i3 < m4578getSizeimpl; i3++) {
                                Boxing.boxInt(i3).intValue();
                                bArr8[i3] = 0;
                            }
                            byte[] m4572constructorimpl3 = UByteArray.m4572constructorimpl(ArraysKt.plus(m4572constructorimpl2, UByteArray.m4572constructorimpl(bArr8)));
                            Function1<UByteArray, Unit> sender = this.this$0.getSender();
                            if (sender != null) {
                                sender.invoke(UByteArray.m4570boximpl(m4572constructorimpl3));
                            }
                        }
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f4782p$;
            LEDScreen lEDScreen = this.this$0;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (lEDScreen.checkVersion(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        num = this.this$0.version;
        if (num == null || num.intValue() != 0) {
            num2 = this.this$0.version;
            if (num2 != null) {
                String str3 = this.$content;
                Charset charset = Charsets.UTF_8;
                if (str3 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                byte[] bytes = str3.getBytes(charset);
                Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
                byte[] copyOf = Arrays.copyOf(bytes, bytes.length);
                Intrinsics.checkExpressionValueIsNotNull(copyOf, "java.util.Arrays.copyOf(this, size)");
                m4572constructorimpl = UByteArray.m4572constructorimpl(copyOf);
                bArr = this.this$0.config;
                UByteArray.m4582setVurrAj0(bArr, 2, UByte.m4528constructorimpl((byte) UByteArray.m4578getSizeimpl(m4572constructorimpl)));
                bArr2 = this.this$0.config;
                UByteArray.m4582setVurrAj0(bArr2, 3, UByte.m4528constructorimpl((byte) ((this.$color & 16711680) >> 16)));
                bArr3 = this.this$0.config;
                UByteArray.m4582setVurrAj0(bArr3, 4, UByte.m4528constructorimpl((byte) ((this.$color & 65280) >> 8)));
                bArr4 = this.this$0.config;
                UByteArray.m4582setVurrAj0(bArr4, 5, UByte.m4528constructorimpl((byte) (this.$color & 255)));
                bArr5 = this.this$0.config;
                i = this.$offsetY;
                if (i <= 15) {
                    m4528constructorimpl = UByte.m4528constructorimpl((byte) 15);
                } else if (i < 0) {
                    m4528constructorimpl = UByte.m4528constructorimpl((byte) 0);
                } else {
                    m4528constructorimpl = UByte.m4528constructorimpl((byte) i);
                }
                UByteArray.m4582setVurrAj0(bArr5, 6, m4528constructorimpl);
                LEDScreen$showText$1$response$1 lEDScreen$showText$1$response$1 = new LEDScreen$showText$1$response$1(this, null);
                this.L$0 = coroutineScope;
                this.L$1 = m4572constructorimpl;
                this.label = 2;
                withTimeoutOrNull = TimeoutKt.withTimeoutOrNull(500L, lEDScreen$showText$1$response$1, this);
                if (withTimeoutOrNull != coroutine_suspended) {
                    return coroutine_suspended;
                }
                bArr6 = m4572constructorimpl;
                obj = withTimeoutOrNull;
                UByteArray uByteArray2 = (UByteArray) obj;
                if (uByteArray2 == null) {
                }
                if (storage != null) {
                }
                str = this.this$0.TAG;
                Object[] objArr2 = new Object[1];
                StringBuilder sb2 = new StringBuilder();
                sb2.append("protocol head fail. ");
                sb2.append(storage != null ? UByteArrayUtilsKt.m4334toHexStringGBYM_sE(storage) : null);
                objArr2[0] = sb2.toString();
                Pdlog.m3277w(str, objArr2);
                return Unit.INSTANCE;
            }
        }
        String str4 = this.$content;
        Charset forName = Charset.forName(StringUtils.GB2312);
        Intrinsics.checkExpressionValueIsNotNull(forName, "Charset.forName(\"GB2312\")");
        if (str4 != null) {
            byte[] bytes2 = str4.getBytes(forName);
            Intrinsics.checkExpressionValueIsNotNull(bytes2, "(this as java.lang.String).getBytes(charset)");
            byte[] copyOf2 = Arrays.copyOf(bytes2, bytes2.length);
            Intrinsics.checkExpressionValueIsNotNull(copyOf2, "java.util.Arrays.copyOf(this, size)");
            m4572constructorimpl = UByteArray.m4572constructorimpl(copyOf2);
            bArr = this.this$0.config;
            UByteArray.m4582setVurrAj0(bArr, 2, UByte.m4528constructorimpl((byte) UByteArray.m4578getSizeimpl(m4572constructorimpl)));
            bArr2 = this.this$0.config;
            UByteArray.m4582setVurrAj0(bArr2, 3, UByte.m4528constructorimpl((byte) ((this.$color & 16711680) >> 16)));
            bArr3 = this.this$0.config;
            UByteArray.m4582setVurrAj0(bArr3, 4, UByte.m4528constructorimpl((byte) ((this.$color & 65280) >> 8)));
            bArr4 = this.this$0.config;
            UByteArray.m4582setVurrAj0(bArr4, 5, UByte.m4528constructorimpl((byte) (this.$color & 255)));
            bArr5 = this.this$0.config;
            i = this.$offsetY;
            if (i <= 15) {
            }
            UByteArray.m4582setVurrAj0(bArr5, 6, m4528constructorimpl);
            LEDScreen$showText$1$response$1 lEDScreen$showText$1$response$12 = new LEDScreen$showText$1$response$1(this, null);
            this.L$0 = coroutineScope;
            this.L$1 = m4572constructorimpl;
            this.label = 2;
            withTimeoutOrNull = TimeoutKt.withTimeoutOrNull(500L, lEDScreen$showText$1$response$12, this);
            if (withTimeoutOrNull != coroutine_suspended) {
            }
        } else {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
    }
}
