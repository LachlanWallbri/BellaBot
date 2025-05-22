package com.pudutech.remotemaintenance.service;

import com.alibaba.fastjson.JSONObject;
import com.pudutech.base.Pdlog;
import com.pudutech.remotemaintenance.IoTManagerFactory;
import com.pudutech.remotemaintenance.aliyun.AliyunIoTMsg;
import com.pudutech.remotemaintenance.interf.IoTInterface;
import com.pudutech.remotemaintenance.utils.FileUtil;
import com.pudutech.remotemaintenance.utils.StringUtil;
import java.io.File;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: RemoteMaintenanceService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.remotemaintenance.service.MyBind$updateIoTMapSyncMsg$1", m3970f = "RemoteMaintenanceService.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes.dex */
public final class MyBind$updateIoTMapSyncMsg$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $mapPath;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7092p$;
    final /* synthetic */ MyBind this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MyBind$updateIoTMapSyncMsg$1(MyBind myBind, String str, Continuation continuation) {
        super(2, continuation);
        this.this$0 = myBind;
        this.$mapPath = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MyBind$updateIoTMapSyncMsg$1 myBind$updateIoTMapSyncMsg$1 = new MyBind$updateIoTMapSyncMsg$1(this.this$0, this.$mapPath, completion);
        myBind$updateIoTMapSyncMsg$1.f7092p$ = (CoroutineScope) obj;
        return myBind$updateIoTMapSyncMsg$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MyBind$updateIoTMapSyncMsg$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        JSONObject iotMapSyncMsgObj;
        JSONObject iotMapSyncMsgObj2;
        AliyunIoTMsg aliyunIoTMsg;
        JSONObject jSONObject;
        AliyunIoTMsg aliyunIoTMsg2;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            File file = new File(this.$mapPath);
            if (!file.exists() || !file.isFile()) {
                return Unit.INSTANCE;
            }
            String readStringFromFile = FileUtil.INSTANCE.readStringFromFile(file.getPath());
            String str = readStringFromFile;
            if (str == null || str.length() == 0) {
                return Unit.INSTANCE;
            }
            String fileMD5 = FileUtil.INSTANCE.getFileMD5(file);
            String str2 = fileMD5;
            if (str2 == null || str2.length() == 0) {
                return Unit.INSTANCE;
            }
            try {
                Pdlog.m3273d(RemoteMaintenanceService.TAG, "before compress length[" + readStringFromFile.length() + "], text[" + readStringFromFile + "].");
                String arrays = Arrays.toString(StringUtil.INSTANCE.compress(readStringFromFile));
                Pdlog.m3273d(RemoteMaintenanceService.TAG, "after compress length[" + arrays.length() + "], text[" + arrays + ']');
                iotMapSyncMsgObj = this.this$0.iotMapSyncMsgObj;
                Intrinsics.checkExpressionValueIsNotNull(iotMapSyncMsgObj, "iotMapSyncMsgObj");
                iotMapSyncMsgObj.put((JSONObject) "data", arrays);
                iotMapSyncMsgObj2 = this.this$0.iotMapSyncMsgObj;
                Intrinsics.checkExpressionValueIsNotNull(iotMapSyncMsgObj2, "iotMapSyncMsgObj");
                iotMapSyncMsgObj2.put((JSONObject) "md5", fileMD5);
                aliyunIoTMsg = this.this$0.iotMapSyncMsg;
                jSONObject = this.this$0.iotMapSyncMsgObj;
                aliyunIoTMsg.setContent(jSONObject.toString());
                IoTInterface<AliyunIoTMsg> ioTManager = IoTManagerFactory.INSTANCE.getIoTManager();
                aliyunIoTMsg2 = this.this$0.iotMapSyncMsg;
                ioTManager.setMapSyncMsg(aliyunIoTMsg2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
