package com.pudutech.factory_test.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.LinearLayoutCompat;
import com.google.gson.Gson;
import com.pudutech.base.Pdlog;
import com.pudutech.factory_test.C4491R;
import com.pudutech.factory_test.test_pack.Recorder;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: ExtraInfoActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001b2\u00020\u0001:\u0003\u001b\u001c\u001dB\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u0016J\u0012\u0010\u0018\u001a\u00020\u00162\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u001e"}, m3961d2 = {"Lcom/pudutech/factory_test/activity/ExtraInfoActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "extra", "Lcom/pudutech/factory_test/activity/ExtraInfoActivity$Extra;", "getExtra", "()Lcom/pudutech/factory_test/activity/ExtraInfoActivity$Extra;", "setExtra", "(Lcom/pudutech/factory_test/activity/ExtraInfoActivity$Extra;)V", "gson", "Lcom/google/gson/Gson;", "getGson", "()Lcom/google/gson/Gson;", "recorderMap", "Lcom/pudutech/factory_test/activity/ExtraInfoActivity$RecorderMap;", "getRecorderMap", "()Lcom/pudutech/factory_test/activity/ExtraInfoActivity$RecorderMap;", "setRecorderMap", "(Lcom/pudutech/factory_test/activity/ExtraInfoActivity$RecorderMap;)V", "layout", "", "loadJson", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "Extra", "RecorderMap", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class ExtraInfoActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    private Extra extra;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String RECORDER_EXTRA_INFO = RECORDER_EXTRA_INFO;
    private static final String RECORDER_EXTRA_INFO = RECORDER_EXTRA_INFO;
    private static final String OPTIONS_FILE_PATH = OPTIONS_FILE_PATH;
    private static final String OPTIONS_FILE_PATH = OPTIONS_FILE_PATH;
    private final String TAG = "ExtraInfoActivity";
    private RecorderMap recorderMap = new RecorderMap();
    private final Gson gson = new Gson();

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

    /* compiled from: ExtraInfoActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/factory_test/activity/ExtraInfoActivity$Companion;", "", "()V", "OPTIONS_FILE_PATH", "", "getOPTIONS_FILE_PATH", "()Ljava/lang/String;", "RECORDER_EXTRA_INFO", "getRECORDER_EXTRA_INFO", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getRECORDER_EXTRA_INFO() {
            return ExtraInfoActivity.RECORDER_EXTRA_INFO;
        }

        public final String getOPTIONS_FILE_PATH() {
            return ExtraInfoActivity.OPTIONS_FILE_PATH;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(2131427361);
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.activity.ExtraInfoActivity$onCreate$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                str = ExtraInfoActivity.this.TAG;
                Pdlog.m3273d(str, "click finish");
                ExtraInfoActivity.this.finish();
            }
        });
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnSave)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.activity.ExtraInfoActivity$onCreate$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str;
                String str2;
                str = ExtraInfoActivity.this.TAG;
                Pdlog.m3273d(str, "click save. " + ExtraInfoActivity.this.getRecorderMap());
                try {
                    Recorder recorder = Recorder.INSTANCE;
                    String recorder_extra_info = ExtraInfoActivity.INSTANCE.getRECORDER_EXTRA_INFO();
                    String json = ExtraInfoActivity.this.getGson().toJson(ExtraInfoActivity.this.getRecorderMap());
                    Intrinsics.checkExpressionValueIsNotNull(json, "gson.toJson(recorderMap)");
                    recorder.save(recorder_extra_info, json);
                    Toast.makeText(ExtraInfoActivity.this, "保存成功", 0).show();
                } catch (Exception e) {
                    str2 = ExtraInfoActivity.this.TAG;
                    e.printStackTrace();
                    Pdlog.m3277w(str2, String.valueOf(Unit.INSTANCE));
                }
            }
        });
        loadJson();
        layout();
    }

    /* compiled from: ExtraInfoActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u000024\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030\u0001j\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00020\u0003j\b\u0012\u0004\u0012\u00020\u0002`\u0005`\u0004B\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/factory_test/activity/ExtraInfoActivity$Extra;", "Ljava/util/LinkedHashMap;", "", "Ljava/util/ArrayList;", "Lkotlin/collections/LinkedHashMap;", "Lkotlin/collections/ArrayList;", "()V", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final class Extra extends LinkedHashMap<String, ArrayList<String>> {
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
            if (obj instanceof ArrayList) {
                return containsValue((ArrayList) obj);
            }
            return false;
        }

        public /* bridge */ boolean containsValue(ArrayList arrayList) {
            return super.containsValue((Object) arrayList);
        }

        @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ Set<Map.Entry<String, ArrayList<String>>> entrySet() {
            return getEntries();
        }

        @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ ArrayList<String> get(Object obj) {
            if (obj instanceof String) {
                return get((String) obj);
            }
            return null;
        }

        public /* bridge */ ArrayList get(String str) {
            return (ArrayList) super.get((Object) str);
        }

        public /* bridge */ Set getEntries() {
            return super.entrySet();
        }

        public /* bridge */ Set getKeys() {
            return super.keySet();
        }

        @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.Map
        public final /* bridge */ Object getOrDefault(Object obj, Object obj2) {
            return obj instanceof String ? getOrDefault((String) obj, (ArrayList) obj2) : obj2;
        }

        public /* bridge */ ArrayList getOrDefault(String str, ArrayList arrayList) {
            return (ArrayList) super.getOrDefault((Object) str, (String) arrayList);
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
        public final /* bridge */ ArrayList<String> remove(Object obj) {
            if (obj instanceof String) {
                return remove((String) obj);
            }
            return null;
        }

        public /* bridge */ ArrayList remove(String str) {
            return (ArrayList) super.remove((Object) str);
        }

        @Override // java.util.HashMap, java.util.Map
        public final /* bridge */ boolean remove(Object obj, Object obj2) {
            if ((obj instanceof String) && (obj2 instanceof ArrayList)) {
                return remove((String) obj, (ArrayList) obj2);
            }
            return false;
        }

        public /* bridge */ boolean remove(String str, ArrayList arrayList) {
            return super.remove((Object) str, (Object) arrayList);
        }

        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ int size() {
            return getSize();
        }

        @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
        public final /* bridge */ Collection<ArrayList<String>> values() {
            return getValues();
        }
    }

    /* compiled from: ExtraInfoActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001j\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002`\u0003B\u0005¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/factory_test/activity/ExtraInfoActivity$RecorderMap;", "Ljava/util/LinkedHashMap;", "", "Lkotlin/collections/LinkedHashMap;", "()V", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
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

    public final Extra getExtra() {
        return this.extra;
    }

    public final void setExtra(Extra extra) {
        this.extra = extra;
    }

    public final RecorderMap getRecorderMap() {
        return this.recorderMap;
    }

    public final void setRecorderMap(RecorderMap recorderMap) {
        Intrinsics.checkParameterIsNotNull(recorderMap, "<set-?>");
        this.recorderMap = recorderMap;
    }

    public final Gson getGson() {
        return this.gson;
    }

    public final void loadJson() {
        String description = Recorder.INSTANCE.getDescription(RECORDER_EXTRA_INFO, "");
        Pdlog.m3273d(this.TAG, "loadJson " + description);
        if (!StringsKt.isBlank(description)) {
            try {
                RecorderMap recorderMap = (RecorderMap) this.gson.fromJson(description, RecorderMap.class);
                if (recorderMap != null) {
                    this.recorderMap = recorderMap;
                }
            } catch (Exception e) {
                String str = this.TAG;
                e.printStackTrace();
                Pdlog.m3277w(str, String.valueOf(Unit.INSTANCE));
            }
        }
        File file = new File(OPTIONS_FILE_PATH);
        if (!file.exists()) {
            Pdlog.m3277w(this.TAG, "file not exist");
            return;
        }
        String readText$default = FilesKt.readText$default(file, null, 1, null);
        Pdlog.m3273d(this.TAG, "read file=" + file.getAbsolutePath() + ". content=" + readText$default);
        try {
            this.extra = (Extra) this.gson.fromJson(readText$default, Extra.class);
        } catch (Exception e2) {
            String str2 = this.TAG;
            e2.printStackTrace();
            Pdlog.m3277w(str2, String.valueOf(Unit.INSTANCE));
        }
    }

    public final void layout() {
        ExtraInfoActivity extraInfoActivity = this;
        TextView textView = new TextView(extraInfoActivity);
        Extra extra = this.extra;
        if (extra == null || extra.isEmpty()) {
            textView.setText("暂无配置需要配置");
        } else {
            textView.setText("需要配置的信息如下：");
        }
        textView.setTextSize(30.0f);
        ((LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layout_info_list)).addView(textView);
        Extra extra2 = this.extra;
        if (extra2 != null) {
            for (final Map.Entry<String, ArrayList<String>> entry : extra2.entrySet()) {
                TextView textView2 = new TextView(extraInfoActivity);
                textView2.setText(entry.getKey());
                textView2.setTextSize(30.0f);
                final RadioGroup radioGroup = new RadioGroup(extraInfoActivity);
                for (final String str : entry.getValue()) {
                    RadioButton radioButton = new RadioButton(extraInfoActivity);
                    radioButton.setText(str);
                    radioButton.setTextSize(30.0f);
                    radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.pudutech.factory_test.activity.ExtraInfoActivity$layout$$inlined$forEach$lambda$1
                        @Override // android.widget.CompoundButton.OnCheckedChangeListener
                        public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                            String str2;
                            if (z) {
                                str2 = this.TAG;
                                Pdlog.m3275i(str2, "set checked. " + str);
                                this.getRecorderMap().put(entry.getKey(), str);
                            }
                        }
                    });
                    radioButton.setChecked(Intrinsics.areEqual((String) this.recorderMap.get((Object) entry.getKey()), str));
                    radioGroup.addView(radioButton);
                }
                ((LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layout_info_list)).addView(textView2);
                ((LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layout_info_list)).addView(radioGroup);
            }
        }
    }
}
