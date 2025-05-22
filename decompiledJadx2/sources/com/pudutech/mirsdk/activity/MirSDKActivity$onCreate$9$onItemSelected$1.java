package com.pudutech.mirsdk.activity;

import android.widget.TextView;
import com.pudutech.base.Pdlog;
import com.pudutech.installerserver.proto.InstallerProto;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.aidl.SDKInterface;
import com.pudutech.mirsdk.aidl.serialize.ElevatorConnectionType;
import com.pudutech.mirsdk.function.C4946R;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
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
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import org.simpleframework.xml.strategy.Name;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$9$onItemSelected$1", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes4.dex */
public final class MirSDKActivity$onCreate$9$onItemSelected$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ElevatorConnectionType $selectServer;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5687p$;
    final /* synthetic */ MirSDKActivity$onCreate$9 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MirSDKActivity$onCreate$9$onItemSelected$1(MirSDKActivity$onCreate$9 mirSDKActivity$onCreate$9, ElevatorConnectionType elevatorConnectionType, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mirSDKActivity$onCreate$9;
        this.$selectServer = elevatorConnectionType;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MirSDKActivity$onCreate$9$onItemSelected$1 mirSDKActivity$onCreate$9$onItemSelected$1 = new MirSDKActivity$onCreate$9$onItemSelected$1(this.this$0, this.$selectServer, completion);
        mirSDKActivity$onCreate$9$onItemSelected$1.f5687p$ = (CoroutineScope) obj;
        return mirSDKActivity$onCreate$9$onItemSelected$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MirSDKActivity$onCreate$9$onItemSelected$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x0016, code lost:
    
        r7 = r6.this$0.this$0.socket;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Socket socket;
        Socket socket2;
        String str;
        InputStream inputStream;
        OutputStream outputStream;
        byte[] int22ByteArray;
        OutputStream outputStream2;
        boolean z;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5687p$;
        socket = this.this$0.this$0.socket;
        if (socket != null && socket2 != null && !socket2.isClosed()) {
            str = this.this$0.this$0.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("send to InstallerServer show info inputstream: ");
            inputStream = this.this$0.this$0.inputStream;
            sb.append(inputStream);
            sb.append(" outputstream: ");
            outputStream = this.this$0.this$0.outputStream;
            sb.append(outputStream);
            Pdlog.m3273d(str, sb.toString());
            InstallerProto.InstallerProtocol.Builder newBuilder = InstallerProto.InstallerProtocol.newBuilder();
            newBuilder.setType(InstallerProto.InstallerProtocol.ProType.Cmd);
            InstallerProto.Command.Builder newBuilder2 = InstallerProto.Command.newBuilder();
            newBuilder2.setShow(true);
            newBuilder.setCmd(newBuilder2.build());
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(new byte[]{(byte) 175, (byte) 173});
            byte[] byteArray = newBuilder.build().toByteArray();
            int22ByteArray = this.this$0.this$0.int22ByteArray(byteArray.length);
            byteArrayOutputStream.write(int22ByteArray);
            byteArrayOutputStream.write(byteArray);
            outputStream2 = this.this$0.this$0.outputStream;
            if (outputStream2 != null) {
                outputStream2.write(byteArrayOutputStream.toByteArray());
            }
            z = this.this$0.this$0.show;
            if (!z) {
                this.this$0.this$0.show = true;
                BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new C48741(null), 3, null);
            }
        }
        SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
        if (sDKInterface != null) {
            sDKInterface.setElevatorConnection(this.$selectServer);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MirSDKActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$9$onItemSelected$1$1", m3970f = "MirSDKActivity.kt", m3971i = {0, 0, 0, 1, 1}, m3972l = {338, 340}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "recvBytes", Name.LENGTH, "$this$launch", "recvBytes"}, m3975s = {"L$0", "L$1", "L$2", "L$0", "L$1"})
    /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$9$onItemSelected$1$1 */
    /* loaded from: classes4.dex */
    public static final class C48741 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5688p$;

        C48741(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C48741 c48741 = new C48741(completion);
            c48741.f5688p$ = (CoroutineScope) obj;
            return c48741;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C48741) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Incorrect condition in loop: B:11:0x003c */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            byte[] bArr;
            CoroutineScope coroutineScope;
            boolean z;
            InputStream inputStream;
            InputStream inputStream2;
            String str;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i != 0) {
                if (i == 1) {
                } else if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                bArr = (byte[]) this.L$1;
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            } else {
                ResultKt.throwOnFailure(obj);
                bArr = new byte[1024];
                coroutineScope = this.f5688p$;
            }
            while (z) {
                inputStream = MirSDKActivity$onCreate$9$onItemSelected$1.this.this$0.this$0.inputStream;
                if (inputStream != null) {
                    inputStream2 = MirSDKActivity$onCreate$9$onItemSelected$1.this.this$0.this$0.inputStream;
                    Integer boxInt = inputStream2 != null ? Boxing.boxInt(inputStream2.read(bArr)) : null;
                    if (boxInt == null || boxInt.intValue() > 0) {
                        str = MirSDKActivity$onCreate$9$onItemSelected$1.this.this$0.this$0.TAG;
                        Pdlog.m3273d(str, "recv from installer server size " + boxInt);
                        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, Dispatchers.getMain(), null, new AnonymousClass1(boxInt, null), 2, null);
                    } else {
                        MirSDKActivity$onCreate$9$onItemSelected$1.this.this$0.this$0.disableConnection();
                    }
                    this.L$0 = coroutineScope;
                    this.L$1 = bArr;
                    this.L$2 = boxInt;
                    this.label = 1;
                    if (DelayKt.delay(1L, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    this.L$0 = coroutineScope;
                    this.L$1 = bArr;
                    this.label = 2;
                    if (DelayKt.delay(1000L, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: MirSDKActivity.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
        @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$9$onItemSelected$1$1$1", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
        /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$onCreate$9$onItemSelected$1$1$1, reason: invalid class name */
        /* loaded from: classes4.dex */
        public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Integer $length;
            int label;

            /* renamed from: p$ */
            private CoroutineScope f5689p$;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(Integer num, Continuation continuation) {
                super(2, continuation);
                this.$length = num;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$length, completion);
                anonymousClass1.f5689p$ = (CoroutineScope) obj;
                return anonymousClass1;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f5689p$;
                TextView txRecv = (TextView) MirSDKActivity$onCreate$9$onItemSelected$1.this.this$0.this$0._$_findCachedViewById(C4946R.id.txRecv);
                Intrinsics.checkExpressionValueIsNotNull(txRecv, "txRecv");
                txRecv.setText("recv msg length " + this.$length);
                return Unit.INSTANCE;
            }
        }
    }
}
