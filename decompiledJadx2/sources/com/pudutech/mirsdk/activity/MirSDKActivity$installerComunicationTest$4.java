package com.pudutech.mirsdk.activity;

import android.view.View;
import com.pudutech.installerserver.proto.InstallerProto;
import com.pudutech.mirsdk.FunctionScope;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MirSDKActivity$installerComunicationTest$4 implements View.OnClickListener {
    final /* synthetic */ MirSDKActivity this$0;

    MirSDKActivity$installerComunicationTest$4(MirSDKActivity mirSDKActivity) {
        this.this$0 = mirSDKActivity;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: MirSDKActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$installerComunicationTest$4$1", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$installerComunicationTest$4$1 */
    /* loaded from: classes5.dex */
    static final class C48391 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5649p$;

        C48391(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C48391 c48391 = new C48391(completion);
            c48391.f5649p$ = (CoroutineScope) obj;
            return c48391;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C48391) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:7:0x0021, code lost:
        
            r6 = r5.this$0.this$0.socket;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            boolean z;
            Socket socket;
            Socket socket2;
            byte[] int22ByteArray;
            OutputStream outputStream;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5649p$;
            z = MirSDKActivity$installerComunicationTest$4.this.this$0.show;
            if (z) {
                socket = MirSDKActivity$installerComunicationTest$4.this.this$0.socket;
                if (socket != null && socket2 != null && !socket2.isClosed()) {
                    InstallerProto.InstallerProtocol.Builder newBuilder = InstallerProto.InstallerProtocol.newBuilder();
                    newBuilder.setType(InstallerProto.InstallerProtocol.ProType.Cmd);
                    InstallerProto.Command.Builder newBuilder2 = InstallerProto.Command.newBuilder();
                    newBuilder2.setShow(false);
                    newBuilder.setCmd(newBuilder2.build());
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byteArrayOutputStream.write(new byte[]{(byte) 175, (byte) 173});
                    byte[] byteArray = newBuilder.build().toByteArray();
                    int22ByteArray = MirSDKActivity$installerComunicationTest$4.this.this$0.int22ByteArray(byteArray.length);
                    byteArrayOutputStream.write(int22ByteArray);
                    byteArrayOutputStream.write(byteArray);
                    outputStream = MirSDKActivity$installerComunicationTest$4.this.this$0.outputStream;
                    if (outputStream != null) {
                        outputStream.write(byteArrayOutputStream.toByteArray());
                    }
                }
                MirSDKActivity$installerComunicationTest$4.this.this$0.show = false;
            }
            return Unit.INSTANCE;
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new C48391(null), 3, null);
    }
}
