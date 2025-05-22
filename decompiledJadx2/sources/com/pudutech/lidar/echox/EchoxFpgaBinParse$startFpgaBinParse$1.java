package com.pudutech.lidar.echox;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.lidar.LidarUpgradeListener;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: EchoxFpgaBinParse.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.lidar.echox.EchoxFpgaBinParse$startFpgaBinParse$1", m3970f = "EchoxFpgaBinParse.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes5.dex */
final class EchoxFpgaBinParse$startFpgaBinParse$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ String $currentVersion;
    final /* synthetic */ LidarUpgradeListener $lidarUpgralistener;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5453p$;
    final /* synthetic */ EchoxFpgaBinParse this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EchoxFpgaBinParse$startFpgaBinParse$1(EchoxFpgaBinParse echoxFpgaBinParse, Context context, String str, LidarUpgradeListener lidarUpgradeListener, Continuation continuation) {
        super(2, continuation);
        this.this$0 = echoxFpgaBinParse;
        this.$context = context;
        this.$currentVersion = str;
        this.$lidarUpgralistener = lidarUpgradeListener;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        EchoxFpgaBinParse$startFpgaBinParse$1 echoxFpgaBinParse$startFpgaBinParse$1 = new EchoxFpgaBinParse$startFpgaBinParse$1(this.this$0, this.$context, this.$currentVersion, this.$lidarUpgralistener, completion);
        echoxFpgaBinParse$startFpgaBinParse$1.f5453p$ = (CoroutineScope) obj;
        return echoxFpgaBinParse$startFpgaBinParse$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((EchoxFpgaBinParse$startFpgaBinParse$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x01be, code lost:
    
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x01b9, code lost:
    
        if (r2 == null) goto L45;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        String str;
        String str2;
        String str3;
        AssetManager assetManager;
        String str4;
        String str5;
        String str6;
        AtomicBoolean atomicBoolean;
        String str7;
        AssetManager assetManager2;
        AtomicBoolean atomicBoolean2;
        AtomicBoolean atomicBoolean3;
        String str8;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5453p$;
            InputStream inputStream = (InputStream) null;
            try {
                try {
                    this.this$0.assetManager = this.$context.getAssets();
                    assetManager = this.this$0.assetManager;
                    if (assetManager == null) {
                        Intrinsics.throwNpe();
                    }
                    str4 = this.this$0.assetsFolderName;
                    String fpgaFileName = assetManager.list(str4)[0];
                    str5 = this.this$0.TAG;
                    Log.d(str5, "file = " + fpgaFileName);
                    StringBuffer stringBuffer = new StringBuffer();
                    Intrinsics.checkExpressionValueIsNotNull(fpgaFileName, "fpgaFileName");
                    List split$default = StringsKt.split$default((CharSequence) fpgaFileName, new String[]{"_"}, false, 0, 6, (Object) null);
                    String str9 = (String) split$default.get(0);
                    if (str9 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                    String substring = str9.substring(1);
                    Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
                    stringBuffer.append(substring);
                    stringBuffer.append((String) split$default.get(1));
                    stringBuffer.append((String) split$default.get(2));
                    stringBuffer.append((String) split$default.get(3));
                    String stringBuffer2 = stringBuffer.toString();
                    Intrinsics.checkExpressionValueIsNotNull(stringBuffer2, "newFpgaVersionBuffer.app…(split.get(3)).toString()");
                    str6 = this.this$0.TAG;
                    Log.d(str6, "currentVersion = " + Integer.parseInt(this.$currentVersion) + ",newFpgaVersion = " + Integer.parseInt(stringBuffer2));
                    if ((!StringsKt.isBlank(stringBuffer2)) && (!StringsKt.isBlank(this.$currentVersion))) {
                        if (Integer.parseInt(stringBuffer2) <= Integer.parseInt(this.$currentVersion)) {
                            atomicBoolean = this.this$0.mHasProcessUpgrade;
                            if (!atomicBoolean.get()) {
                                str7 = this.this$0.TAG;
                                Log.d(str7, "No Need To Upgrade Echox");
                            } else {
                                this.$lidarUpgralistener.onLidarUpgradeResult(true);
                            }
                            CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
                        } else {
                            this.$lidarUpgralistener.onLidarUpgradeStart();
                            this.this$0.echoxFpgaUpgrade = new EchoxFpgaUpgrade(this.$lidarUpgralistener);
                            assetManager2 = this.this$0.assetManager;
                            if (assetManager2 != null) {
                                StringBuilder sb = new StringBuilder();
                                str8 = this.this$0.assetsFolderName;
                                sb.append(str8);
                                sb.append(File.separator);
                                sb.append(fpgaFileName);
                                inputStream = assetManager2.open(sb.toString());
                            } else {
                                inputStream = null;
                            }
                            atomicBoolean2 = this.this$0.mHasProcessUpgrade;
                            if (!atomicBoolean2.get()) {
                                EchoxFpgaBinParse echoxFpgaBinParse = this.this$0;
                                if (inputStream == null) {
                                    Intrinsics.throwNpe();
                                }
                                echoxFpgaBinParse.processInputStream(inputStream);
                            }
                            atomicBoolean3 = this.this$0.mHasProcessUpgrade;
                            atomicBoolean3.set(true);
                        }
                    } else {
                        CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
                    }
                } catch (Exception e) {
                    str2 = this.this$0.TAG;
                    Pdlog.m3273d(str2, "startFpgaUpgrade: Exception is " + e);
                    e.printStackTrace();
                    CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
                    str3 = this.this$0.TAG;
                    Log.d(str3, "startFpgaBinParse: cancel");
                }
            } finally {
                CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
                str = this.this$0.TAG;
                Log.d(str, "startFpgaBinParse: cancel");
                if (inputStream != null) {
                    inputStream.close();
                }
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
