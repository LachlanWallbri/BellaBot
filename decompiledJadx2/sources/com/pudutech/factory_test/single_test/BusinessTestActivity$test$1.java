package com.pudutech.factory_test.single_test;

import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import com.pudutech.factory_test.C4491R;
import com.pudutech.factory_test.LayoutHelperKt;
import com.pudutech.factory_test.test_pack.AllTestItem;
import com.pudutech.factory_test.test_pack.TestStatus;
import com.pudutech.factory_test.test_pack.cloud_server.CloudServerUtil;
import java.io.File;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
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
/* compiled from: BusinessTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.factory_test.single_test.BusinessTestActivity$test$1", m3970f = "BusinessTestActivity.kt", m3971i = {0, 1}, m3972l = {127, 145}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0"})
/* loaded from: classes.dex */
public final class BusinessTestActivity$test$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5169p$;
    final /* synthetic */ BusinessTestActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BusinessTestActivity$test$1(BusinessTestActivity businessTestActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = businessTestActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        BusinessTestActivity$test$1 businessTestActivity$test$1 = new BusinessTestActivity$test$1(this.this$0, completion);
        businessTestActivity$test$1.f5169p$ = (CoroutineScope) obj;
        return businessTestActivity$test$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BusinessTestActivity$test$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x00f1  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5169p$;
            CloudServerUtil cloudServerUtil = this.this$0.getCloudServerUtil();
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = cloudServerUtil.checkTestingStatus(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                if (!Intrinsics.areEqual((Boolean) obj, Boxing.boxBoolean(true))) {
                    AppCompatTextView tvGuide = (AppCompatTextView) this.this$0._$_findCachedViewById(C4491R.id.tvGuide);
                    Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
                    tvGuide.setText("机器已经入库，无法进行该测试。请联系管理员进行处理");
                    LinearLayoutCompat layoutOptions = (LinearLayoutCompat) this.this$0._$_findCachedViewById(C4491R.id.layoutOptions);
                    Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
                    ((Button) LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this.this$0, CollectionsKt.arrayListOf("退出"), 0.0f, 4, null).get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.BusinessTestActivity$test$1.3
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            BusinessTestActivity$test$1.this.this$0.finish();
                        }
                    });
                    return Unit.INSTANCE;
                }
                AppCompatTextView tvGuide2 = (AppCompatTextView) this.this$0._$_findCachedViewById(C4491R.id.tvGuide);
                Intrinsics.checkExpressionValueIsNotNull(tvGuide2, "tvGuide");
                tvGuide2.setText("未联网登记。联网登记后需在10天内完成后续的入库操作");
                LinearLayoutCompat layoutOptions2 = (LinearLayoutCompat) this.this$0._$_findCachedViewById(C4491R.id.layoutOptions);
                Intrinsics.checkExpressionValueIsNotNull(layoutOptions2, "layoutOptions");
                ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions2, this.this$0, CollectionsKt.arrayListOf("退出", "联网登记"), 0.0f, 4, null);
                ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.BusinessTestActivity$test$1.4
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        BusinessTestActivity$test$1.this.this$0.finish();
                    }
                });
                ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new ViewOnClickListenerC45045());
                return Unit.INSTANCE;
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        if (((Boolean) obj).booleanValue()) {
            if (AllTestItem.INSTANCE.isHLS() || AllTestItem.INSTANCE.isBella() || AllTestItem.INSTANCE.isPeanut()) {
                AppCompatTextView tvGuide3 = (AppCompatTextView) this.this$0._$_findCachedViewById(C4491R.id.tvGuide);
                Intrinsics.checkExpressionValueIsNotNull(tvGuide3, "tvGuide");
                tvGuide3.setText("业务测试：\n已登记，有效时间10天。需要至少完成一次送餐模式和半小时巡航模式");
            } else {
                AppCompatTextView tvGuide4 = (AppCompatTextView) this.this$0._$_findCachedViewById(C4491R.id.tvGuide);
                Intrinsics.checkExpressionValueIsNotNull(tvGuide4, "tvGuide");
                tvGuide4.setText("业务测试：\n已登记，有效时间10天。需要至少完成半小时巡航模式");
            }
            LinearLayoutCompat layoutOptions3 = (LinearLayoutCompat) this.this$0._$_findCachedViewById(C4491R.id.layoutOptions);
            Intrinsics.checkExpressionValueIsNotNull(layoutOptions3, "layoutOptions");
            ArrayList layoutBtnOptions$default2 = LayoutHelperKt.layoutBtnOptions$default(layoutOptions3, this.this$0, CollectionsKt.arrayListOf("退出", "跳转至机器人APP"), 0.0f, 4, null);
            ((Button) layoutBtnOptions$default2.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.BusinessTestActivity$test$1.1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BusinessTestActivity$test$1.this.this$0.finish();
                }
            });
            ((Button) layoutBtnOptions$default2.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.BusinessTestActivity$test$1.2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    File file;
                    BusinessTestActivity$test$1.this.this$0.getMTestItem().setStatus(TestStatus.TESTING);
                    BusinessTestActivity$test$1.this.this$0.getMTestItem().setFailDescription((String) null);
                    file = BusinessTestActivity$test$1.this.this$0.file;
                    file.createNewFile();
                    BusinessTestActivity$test$1.this.this$0.setResult(100);
                    BusinessTestActivity$test$1.this.this$0.finish();
                }
            });
            return Unit.INSTANCE;
        }
        CloudServerUtil cloudServerUtil2 = this.this$0.getCloudServerUtil();
        this.L$0 = coroutineScope;
        this.label = 2;
        obj = cloudServerUtil2.checkInStorage(this);
        if (obj == coroutine_suspended) {
            return coroutine_suspended;
        }
        if (!Intrinsics.areEqual((Boolean) obj, Boxing.boxBoolean(true))) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: BusinessTestActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.factory_test.single_test.BusinessTestActivity$test$1$5 */
    /* loaded from: classes.dex */
    public static final class ViewOnClickListenerC45045 implements View.OnClickListener {
        ViewOnClickListenerC45045() {
        }

        /* compiled from: BusinessTestActivity.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
        @DebugMetadata(m3969c = "com.pudutech.factory_test.single_test.BusinessTestActivity$test$1$5$1", m3970f = "BusinessTestActivity.kt", m3971i = {0}, m3972l = {162}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
        /* renamed from: com.pudutech.factory_test.single_test.BusinessTestActivity$test$1$5$1, reason: invalid class name */
        /* loaded from: classes.dex */
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            Object L$0;
            int label;

            /* renamed from: p$ */
            private CoroutineScope f5170p$;

            AnonymousClass1(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(completion);
                anonymousClass1.f5170p$ = (CoroutineScope) obj;
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
                    CoroutineScope coroutineScope = this.f5170p$;
                    AppCompatTextView tvGuide = (AppCompatTextView) BusinessTestActivity$test$1.this.this$0._$_findCachedViewById(C4491R.id.tvGuide);
                    Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
                    tvGuide.setText("联网检测状态中");
                    CloudServerUtil cloudServerUtil = BusinessTestActivity$test$1.this.this$0.getCloudServerUtil();
                    this.L$0 = coroutineScope;
                    this.label = 1;
                    obj = cloudServerUtil.registerTesting(this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                if (!((Boolean) obj).booleanValue()) {
                    BusinessTestActivity$test$1.this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.factory_test.single_test.BusinessTestActivity.test.1.5.1.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            BusinessTestActivity$test$1.this.this$0.getMTestItem().setFailDescription("联网登记失败。请退出检查网络后重试");
                            BusinessTestActivity$test$1.this.this$0.runStepFail();
                        }
                    });
                    return Unit.INSTANCE;
                }
                BusinessTestActivity$test$1.this.this$0.test();
                return Unit.INSTANCE;
            }
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            Job launch$default;
            BusinessTestActivity businessTestActivity = BusinessTestActivity$test$1.this.this$0;
            launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AnonymousClass1(null), 2, null);
            businessTestActivity.setJob(launch$default);
        }
    }
}
