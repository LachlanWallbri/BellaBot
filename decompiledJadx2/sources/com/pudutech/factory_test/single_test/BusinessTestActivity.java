package com.pudutech.factory_test.single_test;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.factory_test.C4491R;
import com.pudutech.factory_test.LayoutHelperKt;
import com.pudutech.factory_test.WaterMarkUtil;
import com.pudutech.factory_test.test_pack.AllTestItem;
import com.pudutech.factory_test.test_pack.TestConstantKt;
import com.pudutech.factory_test.test_pack.TestItem;
import com.pudutech.factory_test.test_pack.TestStage;
import com.pudutech.factory_test.test_pack.TestStatus;
import com.pudutech.factory_test.test_pack.cloud_server.CloudServerUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: BusinessTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u001e\u001a\u00020\u001fJ\u0006\u0010 \u001a\u00020\u001fJ\u0012\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$H\u0014J\b\u0010%\u001a\u00020\"H\u0014J\b\u0010&\u001a\u00020\"H\u0014J\u0006\u0010'\u001a\u00020\"J\u0006\u0010(\u001a\u00020\"J\u000e\u0010)\u001a\u00020\"2\u0006\u0010*\u001a\u00020\u0004J\u0006\u0010+\u001a\u00020\"J*\u0010,\u001a\u00020\u0004*\"\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0014j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0004`\u0015R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R1\u0010\u0013\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0014j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0004`\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006-"}, m3961d2 = {"Lcom/pudutech/factory_test/single_test/BusinessTestActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "cloudServerUtil", "Lcom/pudutech/factory_test/test_pack/cloud_server/CloudServerUtil;", "getCloudServerUtil", "()Lcom/pudutech/factory_test/test_pack/cloud_server/CloudServerUtil;", "setCloudServerUtil", "(Lcom/pudutech/factory_test/test_pack/cloud_server/CloudServerUtil;)V", "file", "Ljava/io/File;", "job", "Lkotlinx/coroutines/Job;", "getJob", "()Lkotlinx/coroutines/Job;", "setJob", "(Lkotlinx/coroutines/Job;)V", "keyInfo", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "getKeyInfo", "()Ljava/util/LinkedHashMap;", "mTestItem", "Lcom/pudutech/factory_test/test_pack/TestItem;", "getMTestItem", "()Lcom/pudutech/factory_test/test_pack/TestItem;", "setMTestItem", "(Lcom/pudutech/factory_test/test_pack/TestItem;)V", "checkNinetalesResult", "", "checkResult", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onStart", "runStepFail", "runStepSuccess", "showKeyInfo", "string", "test", "toStr", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class BusinessTestActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    public CloudServerUtil cloudServerUtil;
    private Job job;
    public TestItem mTestItem;
    private final String TAG = "BusinessTestActivity";
    private final File file = new File("sdcard/BusinessTest");
    private final LinkedHashMap<String, String> keyInfo = new LinkedHashMap<>();

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public final TestItem getMTestItem() {
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        return testItem;
    }

    public final void setMTestItem(TestItem testItem) {
        Intrinsics.checkParameterIsNotNull(testItem, "<set-?>");
        this.mTestItem = testItem;
    }

    public final Job getJob() {
        return this.job;
    }

    public final void setJob(Job job) {
        this.job = job;
    }

    public final CloudServerUtil getCloudServerUtil() {
        CloudServerUtil cloudServerUtil = this.cloudServerUtil;
        if (cloudServerUtil == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cloudServerUtil");
        }
        return cloudServerUtil;
    }

    public final void setCloudServerUtil(CloudServerUtil cloudServerUtil) {
        Intrinsics.checkParameterIsNotNull(cloudServerUtil, "<set-?>");
        this.cloudServerUtil = cloudServerUtil;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.appcompat.app.AlertDialog, T] */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        TestItem testItem;
        super.onCreate(savedInstanceState);
        setContentView(2131427397);
        if (getIntent() != null) {
            TestStage.Companion companion = TestStage.INSTANCE;
            String stringExtra = getIntent().getStringExtra(AllTestItem.INSTANCE.getEXTER_TEST_STAGE_KEY());
            Intrinsics.checkExpressionValueIsNotNull(stringExtra, "intent.getStringExtra(Al…tem.EXTER_TEST_STAGE_KEY)");
            TestStage fromValue = companion.fromValue(stringExtra);
            Iterator<TestItem> it = AllTestItem.INSTANCE.iterator();
            while (true) {
                if (!it.hasNext()) {
                    testItem = null;
                    break;
                }
                testItem = it.next();
                TestItem testItem2 = testItem;
                if (Intrinsics.areEqual(testItem2.getName(), TestConstantKt.BUSINESS) && testItem2.getStage() == fromValue) {
                    break;
                }
            }
            if (testItem == null) {
                Intrinsics.throwNpe();
            }
            this.mTestItem = testItem;
        }
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setMovementMethod(ScrollingMovementMethod.getInstance());
        AppCompatTextView tvGuide2 = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide2, "tvGuide");
        tvGuide2.setText("联网检测状态中");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (AlertDialog) 0;
        CloudServerUtil cloudServerUtil = new CloudServerUtil(this);
        cloudServerUtil.setOnException(new Function1<Exception, Unit>() { // from class: com.pudutech.factory_test.single_test.BusinessTestActivity$onCreate$$inlined$apply$lambda$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                invoke2(exc);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final Exception it2) {
                String str;
                Intrinsics.checkParameterIsNotNull(it2, "it");
                str = BusinessTestActivity.this.TAG;
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("onException ");
                sb.append(it2);
                sb.append(" isShowing=");
                AlertDialog alertDialog = (AlertDialog) objectRef.element;
                sb.append(alertDialog != null ? Boolean.valueOf(alertDialog.isShowing()) : null);
                objArr[0] = sb.toString();
                Pdlog.m3273d(str, objArr);
                BusinessTestActivity.this.runOnUiThread(new Runnable() { // from class: com.pudutech.factory_test.single_test.BusinessTestActivity$onCreate$$inlined$apply$lambda$1.1
                    /* JADX WARN: Multi-variable type inference failed */
                    /* JADX WARN: Type inference failed for: r1v1, types: [androidx.appcompat.app.AlertDialog, T] */
                    @Override // java.lang.Runnable
                    public final void run() {
                        AlertDialog alertDialog2 = (AlertDialog) objectRef.element;
                        if (alertDialog2 == null || !alertDialog2.isShowing()) {
                            Ref.ObjectRef objectRef2 = objectRef;
                            ?? create = new AlertDialog.Builder(BusinessTestActivity.this).create();
                            create.setTitle("发生异常");
                            create.setMessage("无法获取合法返回值 " + it2 + "\n请检查网络或联系管理员检查服务器状态");
                            create.show();
                            objectRef2.element = create;
                        }
                    }
                });
            }
        });
        cloudServerUtil.setOnCloudFailResponse(new Function1<String, Unit>() { // from class: com.pudutech.factory_test.single_test.BusinessTestActivity$onCreate$$inlined$apply$lambda$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final String it2) {
                String str;
                Intrinsics.checkParameterIsNotNull(it2, "it");
                str = BusinessTestActivity.this.TAG;
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("onCloudFailResponse ");
                sb.append(it2);
                sb.append(" isShowing=");
                AlertDialog alertDialog = (AlertDialog) objectRef.element;
                sb.append(alertDialog != null ? Boolean.valueOf(alertDialog.isShowing()) : null);
                objArr[0] = sb.toString();
                Pdlog.m3273d(str, objArr);
                BusinessTestActivity.this.runOnUiThread(new Runnable() { // from class: com.pudutech.factory_test.single_test.BusinessTestActivity$onCreate$$inlined$apply$lambda$2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    /* JADX WARN: Type inference failed for: r1v1, types: [androidx.appcompat.app.AlertDialog, T] */
                    @Override // java.lang.Runnable
                    public final void run() {
                        AlertDialog alertDialog2 = (AlertDialog) objectRef.element;
                        if (alertDialog2 == null || !alertDialog2.isShowing()) {
                            Ref.ObjectRef objectRef2 = objectRef;
                            ?? create = new AlertDialog.Builder(BusinessTestActivity.this).create();
                            create.setTitle("上报失败");
                            create.setMessage(it2);
                            create.show();
                            objectRef2.element = create;
                        }
                    }
                });
            }
        });
        this.cloudServerUtil = cloudServerUtil;
        AppCompatButton btnReset = (AppCompatButton) _$_findCachedViewById(C4491R.id.btnReset);
        Intrinsics.checkExpressionValueIsNotNull(btnReset, "btnReset");
        btnReset.setVisibility(4);
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.BusinessTestActivity$onCreate$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = BusinessTestActivity.this.TAG;
                Pdlog.m3275i(str, "click quit");
                BusinessTestActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnErrorQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.BusinessTestActivity$onCreate$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = BusinessTestActivity.this.TAG;
                Pdlog.m3275i(str, "click error quit");
                BusinessTestActivity.this.getMTestItem().setStatus(TestStatus.FAIL);
                BusinessTestActivity.this.finish();
            }
        });
        TestItem testItem3 = this.mTestItem;
        if (testItem3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        if (testItem3.getStatus() == TestStatus.TESTING) {
            if (checkResult()) {
                runStepSuccess();
                return;
            } else {
                runStepFail();
                return;
            }
        }
        TestItem testItem4 = this.mTestItem;
        if (testItem4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        if (testItem4.getStatus() == TestStatus.SUCCESS) {
            runStepSuccess();
        } else {
            test();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Job job = this.job;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        WaterMarkUtil.INSTANCE.onActivityStart(this);
    }

    public final void runStepSuccess() {
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setStatus(TestStatus.SUCCESS);
        showKeyInfo(toStr(this.keyInfo));
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        tvGuide.setText("业务测试结束\n测试结果是：成功");
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("完成", "重新测试"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.BusinessTestActivity$runStepSuccess$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BusinessTestActivity.this.finish();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.BusinessTestActivity$runStepSuccess$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = BusinessTestActivity.this.TAG;
                Pdlog.m3273d(str, "click retest after success");
                BusinessTestActivity.this.getMTestItem().setStatus(TestStatus.UNTESTED);
                BusinessTestActivity.this.test();
            }
        });
    }

    public final void runStepFail() {
        Pdlog.m3274e(this.TAG, "test fail");
        TestItem testItem = this.mTestItem;
        if (testItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        testItem.setStatus(TestStatus.FAIL);
        showKeyInfo(toStr(this.keyInfo));
        AppCompatTextView tvGuide = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvGuide);
        Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
        StringBuilder sb = new StringBuilder();
        sb.append("业务测试结束\n测试结果是：失败\n描述：");
        TestItem testItem2 = this.mTestItem;
        if (testItem2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
        }
        sb.append(testItem2.getFailDescription());
        tvGuide.setText(sb.toString());
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("退出", "重新测试"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.BusinessTestActivity$runStepFail$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BusinessTestActivity.this.finish();
            }
        });
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.single_test.BusinessTestActivity$runStepFail$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BusinessTestActivity.this.test();
            }
        });
    }

    public final void test() {
        Job launch$default;
        if (this.file.exists()) {
            this.file.delete();
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new BusinessTestActivity$test$1(this, null), 2, null);
        this.job = launch$default;
    }

    public final boolean checkResult() {
        if (!this.file.exists()) {
            return false;
        }
        if (AllTestItem.INSTANCE.isNineTales()) {
            return checkNinetalesResult();
        }
        String readText$default = FilesKt.readText$default(this.file, null, 1, null);
        Pdlog.m3273d(this.TAG, "load result " + readText$default);
        this.file.delete();
        try {
            JSONObject jSONObject = new JSONObject(readText$default);
            this.keyInfo.put("运行中发生过异常", String.valueOf(jSONObject.has("error")));
            if (jSONObject.has("error")) {
                TestItem testItem = this.mTestItem;
                if (testItem == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
                }
                testItem.setFailDescription("运行过程中发生过异常");
                return false;
            }
            long optLong = jSONObject.optLong("cruiseTime", 0L);
            this.keyInfo.put("巡航时间", String.valueOf((int) (optLong / 1000)));
            Pdlog.m3273d(this.TAG, "cruiseTime=" + optLong);
            if (optLong < 1800000) {
                TestItem testItem2 = this.mTestItem;
                if (testItem2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
                }
                testItem2.setFailDescription("巡航时间不能低于半小时");
                return false;
            }
            if (AllTestItem.INSTANCE.isHLS() || AllTestItem.INSTANCE.isBella() || AllTestItem.INSTANCE.isPeanut()) {
                int optInt = jSONObject.optInt("deliveryTimes", 0);
                Pdlog.m3273d(this.TAG, "deliveryTimes=" + optInt);
                this.keyInfo.put("送餐次数", String.valueOf(optInt));
                if (optInt < 1) {
                    TestItem testItem3 = this.mTestItem;
                    if (testItem3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
                    }
                    testItem3.setFailDescription("送餐次数不能少于一次");
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, String.valueOf(e));
            if (AllTestItem.INSTANCE.isHLS() || AllTestItem.INSTANCE.isBella() || AllTestItem.INSTANCE.isPeanut()) {
                TestItem testItem4 = this.mTestItem;
                if (testItem4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
                }
                testItem4.setFailDescription("文件解析异常，请确认刚才是否完成了至少一次送餐模式和半小时巡航模式。" + e + " \n文件内容=" + readText$default);
            } else {
                TestItem testItem5 = this.mTestItem;
                if (testItem5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
                }
                testItem5.setFailDescription("文件解析异常，请确认刚才是否完成了半小时巡航模式。" + e + " \n文件内容=" + readText$default);
            }
            return false;
        }
    }

    public final boolean checkNinetalesResult() {
        String readText$default = FilesKt.readText$default(this.file, null, 1, null);
        Pdlog.m3273d(this.TAG, "load result " + readText$default);
        this.file.delete();
        try {
            JSONObject jSONObject = new JSONObject(readText$default);
            this.keyInfo.put("运行中发生过异常", String.valueOf(jSONObject.has("error")));
            if (jSONObject.has("error")) {
                TestItem testItem = this.mTestItem;
                if (testItem == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
                }
                testItem.setFailDescription("运行过程中发生过异常");
                return false;
            }
            long optLong = jSONObject.optLong("cruiseTime", 0L);
            this.keyInfo.put("巡航时间", String.valueOf((int) (optLong / 1000)));
            Pdlog.m3273d(this.TAG, "cruiseTime=" + optLong);
            if (optLong < 1800000) {
                TestItem testItem2 = this.mTestItem;
                if (testItem2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
                }
                testItem2.setFailDescription("巡航时间不能低于半小时");
                return false;
            }
            int optInt = jSONObject.optInt("deliveryTimes", 0);
            Pdlog.m3273d(this.TAG, "deliveryTimes=" + optInt);
            this.keyInfo.put("送餐次数", String.valueOf(optInt));
            if (optInt < 1) {
                TestItem testItem3 = this.mTestItem;
                if (testItem3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
                }
                testItem3.setFailDescription("定点任务不能少于一次");
                return false;
            }
            ArrayList arrayListOf = CollectionsKt.arrayListOf("UvLamp", "Spray");
            JSONArray jSONArray = jSONObject.getJSONArray("deviceUse");
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                String string = jSONArray.getString(i);
                Pdlog.m3275i(this.TAG, "usedDevices=" + string);
                arrayListOf.remove(string);
            }
            if (arrayListOf.size() <= 0) {
                return true;
            }
            TestItem testItem4 = this.mTestItem;
            if (testItem4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
            }
            testItem4.setFailDescription("业务测试时没有打开设备: " + arrayListOf);
            return false;
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, String.valueOf(e));
            TestItem testItem5 = this.mTestItem;
            if (testItem5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTestItem");
            }
            testItem5.setFailDescription("文件解析异常，请确认刚才是否完成了至少一次消毒、一次紫外测试、一次定点任务和半小时巡航模式。" + e + " \n文件内容=" + readText$default);
            return false;
        }
    }

    public final LinkedHashMap<String, String> getKeyInfo() {
        return this.keyInfo;
    }

    public final String toStr(LinkedHashMap<String, String> toStr) {
        Intrinsics.checkParameterIsNotNull(toStr, "$this$toStr");
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : toStr.entrySet()) {
            sb.append(entry.getKey() + " : " + entry.getValue() + '\n');
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "strBuilder.toString()");
        return sb2;
    }

    public final void showKeyInfo(String string) {
        Intrinsics.checkParameterIsNotNull(string, "string");
        Pdlog.m3275i(this.TAG, "showKeyInfo string=" + string);
        AppCompatTextView tvDebugInfo = (AppCompatTextView) _$_findCachedViewById(C4491R.id.tvDebugInfo);
        Intrinsics.checkExpressionValueIsNotNull(tvDebugInfo, "tvDebugInfo");
        tvDebugInfo.setText(string);
    }
}
