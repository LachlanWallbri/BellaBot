package com.pudutech.factory_test.activity;

import android.view.View;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import com.pudutech.base.Pdlog;
import com.pudutech.factory_test.C4491R;
import com.pudutech.factory_test.test_pack.cloud_server.CloudServerUtil;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
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
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: UploadActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.factory_test.activity.UploadActivity$initLayout$1", m3970f = "UploadActivity.kt", m3971i = {0}, m3972l = {82}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes.dex */
public final class UploadActivity$initLayout$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ AppCompatButton $button;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5164p$;
    final /* synthetic */ UploadActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UploadActivity$initLayout$1(UploadActivity uploadActivity, AppCompatButton appCompatButton, Continuation continuation) {
        super(2, continuation);
        this.this$0 = uploadActivity;
        this.$button = appCompatButton;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        UploadActivity$initLayout$1 uploadActivity$initLayout$1 = new UploadActivity$initLayout$1(this.this$0, this.$button, completion);
        uploadActivity$initLayout$1.f5164p$ = (CoroutineScope) obj;
        return uploadActivity$initLayout$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UploadActivity$initLayout$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        AppCompatTextView appCompatTextView;
        String str;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5164p$;
            AppCompatTextView tvUpload = (AppCompatTextView) this.this$0._$_findCachedViewById(C4491R.id.tvUpload);
            Intrinsics.checkExpressionValueIsNotNull(tvUpload, "tvUpload");
            CloudServerUtil access$getCloudServerUtil$p = UploadActivity.access$getCloudServerUtil$p(this.this$0);
            this.L$0 = coroutineScope;
            this.L$1 = tvUpload;
            this.label = 1;
            obj = access$getCloudServerUtil$p.checkInStorage(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
            appCompatTextView = tvUpload;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            appCompatTextView = (AppCompatTextView) this.L$1;
            ResultKt.throwOnFailure(obj);
        }
        Boolean bool = (Boolean) obj;
        if (!Intrinsics.areEqual(bool, Boxing.boxBoolean(true))) {
            if (!Intrinsics.areEqual(bool, Boxing.boxBoolean(false))) {
                if (bool != null) {
                    throw new NoWhenBranchMatchedException();
                }
            }
        }
        appCompatTextView.setText(str);
        this.$button.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.activity.UploadActivity$initLayout$1.1

            /* compiled from: UploadActivity.kt */
            @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
            @DebugMetadata(m3969c = "com.pudutech.factory_test.activity.UploadActivity$initLayout$1$1$1", m3970f = "UploadActivity.kt", m3971i = {0}, m3972l = {90}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
            /* renamed from: com.pudutech.factory_test.activity.UploadActivity$initLayout$1$1$1, reason: invalid class name */
            /* loaded from: classes.dex */
            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                Object L$0;
                int label;

                /* renamed from: p$ */
                private CoroutineScope f5165p$;

                AnonymousClass1(Continuation continuation) {
                    super(2, continuation);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                    Intrinsics.checkParameterIsNotNull(completion, "completion");
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(completion);
                    anonymousClass1.f5165p$ = (CoroutineScope) obj;
                    return anonymousClass1;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f5165p$;
                        CloudServerUtil access$getCloudServerUtil$p = UploadActivity.access$getCloudServerUtil$p(UploadActivity$initLayout$1.this.this$0);
                        this.L$0 = coroutineScope;
                        this.label = 1;
                        obj = access$getCloudServerUtil$p.putInStorage(this);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    if (((Boolean) obj).booleanValue()) {
                        AppCompatTextView tvUpload = (AppCompatTextView) UploadActivity$initLayout$1.this.this$0._$_findCachedViewById(C4491R.id.tvUpload);
                        Intrinsics.checkExpressionValueIsNotNull(tvUpload, "tvUpload");
                        tvUpload.setText("已入库");
                        Toast.makeText(UploadActivity$initLayout$1.this.this$0, "入库成功", 1).show();
                    } else {
                        Toast.makeText(UploadActivity$initLayout$1.this.this$0, "入库失败", 1).show();
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str2;
                Job launch$default;
                str2 = UploadActivity$initLayout$1.this.this$0.TAG;
                Pdlog.m3275i(str2, "click to put in storage");
                UploadActivity uploadActivity = UploadActivity$initLayout$1.this.this$0;
                launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AnonymousClass1(null), 2, null);
                uploadActivity.setJob(launch$default);
            }
        });
        return Unit.INSTANCE;
    }
}
