package com.pudutech.bumblebee.robot;

import android.content.Context;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.bumblebee.robot.aidl.IRecycleRobotListener;
import com.pudutech.bumblebee.robot.aidl.IRobotListener;
import com.pudutech.bumblebee.robot.aidl.serialize.PeripheralDevice;
import com.pudutech.bumblebee.robot.aidl.serialize.PeripheralDeviceStatus;
import com.pudutech.bumblebee.robot.nfc.NFC;
import com.pudutech.bumblebee.robot.nfc.NFCListener;
import com.pudutech.bumblebee.robot.remote_device.LoRaClient;
import com.pudutech.bumblebee.robot.remote_device.RecycleRobotLora;
import com.pudutech.bumblebee.robot.remote_device.RemoteDeviceInterface;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: RobotService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot.RobotService$iBinder$1$openDevices$3", m3970f = "RobotService.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes.dex */
public final class RobotService$iBinder$1$openDevices$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MachineInfo.LoraType $loraType;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4733p$;
    final /* synthetic */ RobotService$iBinder$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RobotService$iBinder$1$openDevices$3(RobotService$iBinder$1 robotService$iBinder$1, MachineInfo.LoraType loraType, Continuation continuation) {
        super(2, continuation);
        this.this$0 = robotService$iBinder$1;
        this.$loraType = loraType;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RobotService$iBinder$1$openDevices$3 robotService$iBinder$1$openDevices$3 = new RobotService$iBinder$1$openDevices$3(this.this$0, this.$loraType, completion);
        robotService$iBinder$1$openDevices$3.f4733p$ = (CoroutineScope) obj;
        return robotService$iBinder$1$openDevices$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RobotService$iBinder$1$openDevices$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        NFC nfc;
        String str;
        NFC nfc2;
        RemoteDeviceInterface remoteDeviceInterface;
        LoRaClient loRaClient;
        String str2;
        RemoteDeviceInterface remoteDeviceInterface2;
        RemoteDeviceInterface remoteDeviceInterface3;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        nfc = this.this$0.nfc;
        if (nfc == null) {
            this.this$0.nfc = new NFC();
        }
        str = this.this$0.this$0.TAG;
        Pdlog.m3275i(str, "open nfc");
        nfc2 = this.this$0.nfc;
        if (nfc2 == null) {
            Intrinsics.throwNpe();
        }
        nfc2.open(this.this$0.this$0, new NFCListener() { // from class: com.pudutech.bumblebee.robot.RobotService$iBinder$1$openDevices$3.1
            @Override // com.pudutech.bumblebee.robot.nfc.NFCListener
            public void onSignDetected(final String nfcID) {
                ThreadSafeListener threadSafeListener;
                Intrinsics.checkParameterIsNotNull(nfcID, "nfcID");
                threadSafeListener = RobotService$iBinder$1$openDevices$3.this.this$0.recycleRobotListener;
                threadSafeListener.notify(new Function2<IRecycleRobotListener, String, Unit>() { // from class: com.pudutech.bumblebee.robot.RobotService$iBinder$1$openDevices$3$1$onSignDetected$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IRecycleRobotListener iRecycleRobotListener, String str3) {
                        invoke2(iRecycleRobotListener, str3);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IRecycleRobotListener it, String str3) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str3, "<anonymous parameter 1>");
                        it.onNFCSignDetected(nfcID);
                    }
                });
            }
        });
        if (this.$loraType != MachineInfo.LoraType.NoDevice) {
            remoteDeviceInterface = this.this$0.remoteDevice;
            if (remoteDeviceInterface == null) {
                this.this$0.remoteDevice = new RecycleRobotLora();
            }
            loRaClient = this.this$0.loraClient;
            if (loRaClient == null) {
                RobotService$iBinder$1 robotService$iBinder$1 = this.this$0;
                Context applicationContext = this.this$0.this$0.getApplicationContext();
                Intrinsics.checkExpressionValueIsNotNull(applicationContext, "applicationContext");
                remoteDeviceInterface3 = this.this$0.remoteDevice;
                if (remoteDeviceInterface3 == null) {
                    Intrinsics.throwNpe();
                }
                robotService$iBinder$1.loraClient = new LoRaClient(applicationContext, remoteDeviceInterface3);
            }
            str2 = this.this$0.this$0.TAG;
            Pdlog.m3275i(str2, "open recycle robot lora");
            remoteDeviceInterface2 = this.this$0.remoteDevice;
            if (remoteDeviceInterface2 == null) {
                Intrinsics.throwNpe();
            }
            remoteDeviceInterface2.open(this.this$0.this$0, new Function1<PeripheralDeviceStatus, Unit>() { // from class: com.pudutech.bumblebee.robot.RobotService$iBinder$1$openDevices$3.2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(PeripheralDeviceStatus peripheralDeviceStatus) {
                    invoke2(peripheralDeviceStatus);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(final PeripheralDeviceStatus status) {
                    ThreadSafeListener threadSafeListener;
                    Intrinsics.checkParameterIsNotNull(status, "status");
                    threadSafeListener = RobotService$iBinder$1$openDevices$3.this.this$0.commonListener;
                    threadSafeListener.notify(new Function2<IRobotListener, String, Unit>() { // from class: com.pudutech.bumblebee.robot.RobotService.iBinder.1.openDevices.3.2.1
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(IRobotListener iRobotListener, String str3) {
                            invoke2(iRobotListener, str3);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(IRobotListener it, String str3) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            Intrinsics.checkParameterIsNotNull(str3, "<anonymous parameter 1>");
                            it.onDeviceStatusChange(PeripheralDevice.LORA, PeripheralDeviceStatus.this, "");
                        }
                    });
                }
            }, (Function1) new Function1<byte[], Unit>() { // from class: com.pudutech.bumblebee.robot.RobotService$iBinder$1$openDevices$3.3
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(byte[] bArr) {
                    invoke2(bArr);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(final byte[] data) {
                    ThreadSafeListener threadSafeListener;
                    LoRaClient loRaClient2;
                    Intrinsics.checkParameterIsNotNull(data, "data");
                    threadSafeListener = RobotService$iBinder$1$openDevices$3.this.this$0.recycleRobotListener;
                    threadSafeListener.notify(new Function2<IRecycleRobotListener, String, Unit>() { // from class: com.pudutech.bumblebee.robot.RobotService.iBinder.1.openDevices.3.3.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(IRecycleRobotListener iRecycleRobotListener, String str3) {
                            invoke2(iRecycleRobotListener, str3);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(IRecycleRobotListener it, String str3) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            Intrinsics.checkParameterIsNotNull(str3, "<anonymous parameter 1>");
                            it.onRemoteDeviceMsg(data);
                        }
                    });
                    loRaClient2 = RobotService$iBinder$1$openDevices$3.this.this$0.loraClient;
                    if (loRaClient2 != null) {
                        loRaClient2.onReceiveMsg(data);
                    }
                }
            });
        }
        return Unit.INSTANCE;
    }
}
