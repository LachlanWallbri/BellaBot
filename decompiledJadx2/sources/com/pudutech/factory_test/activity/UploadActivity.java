package com.pudutech.factory_test.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.LinearLayoutCompat;
import com.google.gson.Gson;
import com.pudutech.base.Pdlog;
import com.pudutech.factory_test.C4491R;
import com.pudutech.factory_test.WaterMarkUtil;
import com.pudutech.factory_test.test_pack.Recorder;
import com.pudutech.factory_test.test_pack.cloud_server.CloudServerUtil;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.function.BiConsumer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* compiled from: UploadActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0014B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\r\u001a\u00020\u000eJ\u0012\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J\b\u0010\u0012\u001a\u00020\u000eH\u0014J\b\u0010\u0013\u001a\u00020\u000eH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/factory_test/activity/UploadActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "cloudServerUtil", "Lcom/pudutech/factory_test/test_pack/cloud_server/CloudServerUtil;", "job", "Lkotlinx/coroutines/Job;", "getJob", "()Lkotlinx/coroutines/Job;", "setJob", "(Lkotlinx/coroutines/Job;)V", "initLayout", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onStart", "RecorderMap", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class UploadActivity extends AppCompatActivity {
    private final String TAG = "UploadActivity";
    private HashMap _$_findViewCache;
    private CloudServerUtil cloudServerUtil;
    private Job job;

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

    public static final /* synthetic */ CloudServerUtil access$getCloudServerUtil$p(UploadActivity uploadActivity) {
        CloudServerUtil cloudServerUtil = uploadActivity.cloudServerUtil;
        if (cloudServerUtil == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cloudServerUtil");
        }
        return cloudServerUtil;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Type inference failed for: r0v3, types: [androidx.appcompat.app.AlertDialog, T] */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(2131427371);
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.activity.UploadActivity$onCreate$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = UploadActivity.this.TAG;
                Pdlog.m3273d(str, "click quit");
                UploadActivity.this.finish();
            }
        });
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (AlertDialog) 0;
        CloudServerUtil cloudServerUtil = new CloudServerUtil(this);
        cloudServerUtil.setOnException(new Function1<Exception, Unit>() { // from class: com.pudutech.factory_test.activity.UploadActivity$onCreate$$inlined$apply$lambda$1
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
            public final void invoke2(final Exception it) {
                String str;
                Intrinsics.checkParameterIsNotNull(it, "it");
                str = UploadActivity.this.TAG;
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("onException ");
                sb.append(it);
                sb.append(" isShowing=");
                AlertDialog alertDialog = (AlertDialog) objectRef.element;
                sb.append(alertDialog != null ? Boolean.valueOf(alertDialog.isShowing()) : null);
                objArr[0] = sb.toString();
                Pdlog.m3273d(str, objArr);
                UploadActivity.this.runOnUiThread(new Runnable() { // from class: com.pudutech.factory_test.activity.UploadActivity$onCreate$$inlined$apply$lambda$1.1
                    /* JADX WARN: Multi-variable type inference failed */
                    /* JADX WARN: Type inference failed for: r1v1, types: [androidx.appcompat.app.AlertDialog, T] */
                    @Override // java.lang.Runnable
                    public final void run() {
                        AlertDialog alertDialog2 = (AlertDialog) objectRef.element;
                        if (alertDialog2 == null || !alertDialog2.isShowing()) {
                            Ref.ObjectRef objectRef2 = objectRef;
                            ?? create = new AlertDialog.Builder(UploadActivity.this).create();
                            create.setTitle("发生异常");
                            create.setMessage("无法获取合法返回值 " + it + "\n请检查网络或联系管理员检查服务器状态");
                            create.show();
                            objectRef2.element = create;
                        }
                    }
                });
            }
        });
        cloudServerUtil.setOnCloudFailResponse(new Function1<String, Unit>() { // from class: com.pudutech.factory_test.activity.UploadActivity$onCreate$$inlined$apply$lambda$2
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
            public final void invoke2(final String it) {
                String str;
                Intrinsics.checkParameterIsNotNull(it, "it");
                str = UploadActivity.this.TAG;
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("onCloudFailResponse ");
                sb.append(it);
                sb.append(" isShowing=");
                AlertDialog alertDialog = (AlertDialog) objectRef.element;
                sb.append(alertDialog != null ? Boolean.valueOf(alertDialog.isShowing()) : null);
                objArr[0] = sb.toString();
                Pdlog.m3273d(str, objArr);
                UploadActivity.this.runOnUiThread(new Runnable() { // from class: com.pudutech.factory_test.activity.UploadActivity$onCreate$$inlined$apply$lambda$2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    /* JADX WARN: Type inference failed for: r1v1, types: [androidx.appcompat.app.AlertDialog, T] */
                    @Override // java.lang.Runnable
                    public final void run() {
                        AlertDialog alertDialog2 = (AlertDialog) objectRef.element;
                        if (alertDialog2 == null || !alertDialog2.isShowing()) {
                            Ref.ObjectRef objectRef2 = objectRef;
                            ?? create = new AlertDialog.Builder(UploadActivity.this).create();
                            create.setTitle("上报失败");
                            create.setMessage(it);
                            create.show();
                            objectRef2.element = create;
                        }
                    }
                });
            }
        });
        this.cloudServerUtil = cloudServerUtil;
        initLayout();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        WaterMarkUtil.INSTANCE.onActivityStart(this);
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

    public final Job getJob() {
        return this.job;
    }

    public final void setJob(Job job) {
        this.job = job;
    }

    /* compiled from: UploadActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001j\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002`\u0003B\u0005¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/factory_test/activity/UploadActivity$RecorderMap;", "Ljava/util/LinkedHashMap;", "", "Lkotlin/collections/LinkedHashMap;", "()V", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final class RecorderMap extends LinkedHashMap<String, String> {
        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ boolean containsKey(Object obj) {
            if (obj instanceof String) {
                return containsKey((String) obj);
            }
            return false;
        }

        public /* bridge */ boolean containsKey(String str) {
            return super.containsKey((Object) str);
        }

        @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ boolean containsValue(Object obj) {
            if (obj instanceof String) {
                return containsValue((String) obj);
            }
            return false;
        }

        public /* bridge */ boolean containsValue(String str) {
            return super.containsValue((Object) str);
        }

        @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ Set<Map.Entry<String, String>> entrySet() {
            return getEntries();
        }

        @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ Object get(Object obj) {
            if (obj instanceof String) {
                return get((String) obj);
            }
            return null;
        }

        public /* bridge */ String get(String str) {
            return (String) super.get((Object) str);
        }

        public /* bridge */ Set getEntries() {
            return super.entrySet();
        }

        public /* bridge */ Set getKeys() {
            return super.keySet();
        }

        @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.Map
        public final /* bridge */ Object getOrDefault(Object obj, Object obj2) {
            return obj instanceof String ? getOrDefault((String) obj, (String) obj2) : obj2;
        }

        public /* bridge */ String getOrDefault(String str, String str2) {
            return (String) super.getOrDefault((Object) str, str2);
        }

        public /* bridge */ int getSize() {
            return super.size();
        }

        public /* bridge */ Collection getValues() {
            return super.values();
        }

        @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ Set<String> keySet() {
            return getKeys();
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ Object remove(Object obj) {
            if (obj instanceof String) {
                return remove((String) obj);
            }
            return null;
        }

        public /* bridge */ String remove(String str) {
            return (String) super.remove((Object) str);
        }

        @Override // java.util.HashMap, java.util.Map
        public final /* bridge */ boolean remove(Object obj, Object obj2) {
            if ((obj instanceof String) && (obj2 instanceof String)) {
                return remove((String) obj, (String) obj2);
            }
            return false;
        }

        public /* bridge */ boolean remove(String str, String str2) {
            return super.remove((Object) str, (Object) str2);
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ int size() {
            return getSize();
        }

        @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ Collection<String> values() {
            return getValues();
        }
    }

    public final void initLayout() {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new UploadActivity$initLayout$1(this, (AppCompatButton) _$_findCachedViewById(C4491R.id.btnUpload), null), 2, null);
        this.job = launch$default;
        String description = Recorder.INSTANCE.getDescription(ExtraInfoActivity.INSTANCE.getRECORDER_EXTRA_INFO(), "");
        Gson gson = new Gson();
        Pdlog.m3273d(this.TAG, "loadJson " + description);
        if (StringsKt.isBlank(description)) {
            return;
        }
        try {
            RecorderMap recorderMap = (RecorderMap) gson.fromJson(description, RecorderMap.class);
            if (recorderMap != null) {
                recorderMap.forEach(new BiConsumer<String, String>() { // from class: com.pudutech.factory_test.activity.UploadActivity$initLayout$$inlined$let$lambda$1
                    @Override // java.util.function.BiConsumer
                    public final void accept(String key, String value) {
                        Intrinsics.checkParameterIsNotNull(key, "key");
                        Intrinsics.checkParameterIsNotNull(value, "value");
                        TextView textView = new TextView(UploadActivity.this);
                        textView.setText(key + " : " + value);
                        textView.setTextSize(50.0f);
                        ((LinearLayoutCompat) UploadActivity.this._$_findCachedViewById(C4491R.id.layout_info_list)).addView(textView);
                    }
                });
            }
        } catch (Exception e) {
            String str = this.TAG;
            e.printStackTrace();
            Pdlog.m3277w(str, String.valueOf(Unit.INSTANCE));
        }
    }
}
