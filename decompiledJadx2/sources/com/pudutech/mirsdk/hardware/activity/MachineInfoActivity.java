package com.pudutech.mirsdk.hardware.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.library.C5193R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* compiled from: MachineInfoActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0003\u000e\u000f\u0010B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001a\f\u0012\b\u0012\u00060\tR\u00020\u00000\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/activity/MachineInfoActivity;", "Landroid/app/Activity;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "machineKVS", "", "Lcom/pudutech/mirsdk/hardware/activity/MachineInfoActivity$MachineKV;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "MachineAdapter", "MachineKV", "ViewHolder", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class MachineInfoActivity extends Activity {
    private HashMap _$_findViewCache;
    private final String TAG = "MachineInfoActivity";
    private List<MachineKV> machineKVS = new ArrayList();

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

    public final String getTAG() {
        return this.TAG;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HashMap hashMap = (HashMap) getIntent().getSerializableExtra("machine_info");
        String str = this.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("machineInfo ");
        sb.append(hashMap);
        sb.append(' ');
        sb.append(hashMap != null ? Integer.valueOf(hashMap.size()) : null);
        objArr[0] = sb.toString();
        Pdlog.m3275i(str, objArr);
        setContentView(C5193R.layout.machine_activity);
        View findViewById = findViewById(C5193R.id.machine_config);
        if (findViewById == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ListView");
        }
        ListView listView = (ListView) findViewById;
        if (hashMap != null) {
            try {
                for (Map.Entry entry : hashMap.entrySet()) {
                    String str2 = (String) entry.getKey();
                    String str3 = (String) entry.getValue();
                    MachineKV machineKV = new MachineKV();
                    machineKV.setName(str2);
                    machineKV.setValue(str3);
                    Pdlog.m3275i(this.TAG, "key:" + str2 + " value:" + str3);
                    this.machineKVS.add(machineKV);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        listView.setAdapter((ListAdapter) new MachineAdapter());
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes4.dex
      classes5.dex
     */
    /* compiled from: MachineInfoActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0080\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u0004H\u0016J\"\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0016¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/activity/MachineInfoActivity$MachineAdapter;", "Landroid/widget/BaseAdapter;", "(Lcom/pudutech/mirsdk/hardware/activity/MachineInfoActivity;)V", "getCount", "", "getItem", "", RequestParameters.POSITION, "getItemId", "", "getView", "Landroid/view/View;", "convertView", "parent", "Landroid/view/ViewGroup;", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public final class MachineAdapter extends BaseAdapter {
        @Override // android.widget.Adapter
        public long getItemId(int position) {
            return position;
        }

        public MachineAdapter() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return MachineInfoActivity.this.machineKVS.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int position) {
            return MachineInfoActivity.this.machineKVS.get(position);
        }

        @Override // android.widget.Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            Intrinsics.checkParameterIsNotNull(parent, "parent");
            ViewHolder viewHolder = new ViewHolder();
            if (convertView == null) {
                convertView = MachineInfoActivity.this.getLayoutInflater().inflate(C5193R.layout.machine_info_layout, (ViewGroup) null);
                if (convertView == null) {
                    Intrinsics.throwNpe();
                }
                View findViewById = convertView.findViewById(C5193R.id.config_name);
                if (findViewById == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
                }
                viewHolder.setNameTextView((TextView) findViewById);
                View findViewById2 = convertView.findViewById(C5193R.id.config_value);
                if (findViewById2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
                }
                viewHolder.setValueTextView((TextView) findViewById2);
                convertView.setTag(viewHolder);
            } else {
                Object tag = convertView.getTag();
                if (tag == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.mirsdk.hardware.activity.MachineInfoActivity.ViewHolder");
                }
                viewHolder = (ViewHolder) tag;
            }
            try {
                TextView nameTextView = viewHolder.getNameTextView();
                if (nameTextView == null) {
                    Intrinsics.throwNpe();
                }
                nameTextView.setText(((MachineKV) MachineInfoActivity.this.machineKVS.get(position)).getName());
                TextView valueTextView = viewHolder.getValueTextView();
                if (valueTextView == null) {
                    Intrinsics.throwNpe();
                }
                valueTextView.setText(((MachineKV) MachineInfoActivity.this.machineKVS.get(position)).getValue());
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
            return convertView;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes4.dex
      classes5.dex
     */
    /* compiled from: MachineInfoActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0080\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/activity/MachineInfoActivity$ViewHolder;", "", "(Lcom/pudutech/mirsdk/hardware/activity/MachineInfoActivity;)V", "nameTextView", "Landroid/widget/TextView;", "getNameTextView", "()Landroid/widget/TextView;", "setNameTextView", "(Landroid/widget/TextView;)V", "valueTextView", "getValueTextView", "setValueTextView", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public final class ViewHolder {
        private TextView nameTextView;
        private TextView valueTextView;

        public ViewHolder() {
        }

        public final TextView getNameTextView() {
            return this.nameTextView;
        }

        public final void setNameTextView(TextView textView) {
            this.nameTextView = textView;
        }

        public final TextView getValueTextView() {
            return this.valueTextView;
        }

        public final void setValueTextView(TextView textView) {
            this.valueTextView = textView;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes4.dex
      classes5.dex
     */
    /* compiled from: MachineInfoActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u0080\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/activity/MachineInfoActivity$MachineKV;", "", "(Lcom/pudutech/mirsdk/hardware/activity/MachineInfoActivity;)V", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", ES6Iterator.VALUE_PROPERTY, "getValue", "setValue", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public final class MachineKV {
        private String name;
        private String value;

        public MachineKV() {
        }

        public final String getName() {
            return this.name;
        }

        public final void setName(String str) {
            this.name = str;
        }

        public final String getValue() {
            return this.value;
        }

        public final void setValue(String str) {
            this.value = str;
        }
    }
}
