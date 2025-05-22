package com.pudutech.mirsdk.mircore.p057ui.speedconfig;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.mircore.C5224R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONException;
import org.json.JSONObject;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: EditTextAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u0000 )2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005:\u0005)*+,-B\u000f\b\u0000\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u000e\u001a\u00020\rH\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\rH\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0011\u001a\u00020\rH\u0016J$\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0011\u001a\u00020\r2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020\u0015H\u0016J\u0018\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u001c\u001a\u00020\u0015H\u0016J\u0018\u0010 \u001a\u00020\u001e2\u0006\u0010\u001c\u001a\u00020\u00152\u0006\u0010!\u001a\u00020\"H\u0016J\r\u0010#\u001a\u00020$H\u0000¢\u0006\u0002\b%J\u0017\u0010&\u001a\u00020\u001a2\b\u0010'\u001a\u0004\u0018\u00010$H\u0000¢\u0006\u0002\b(R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\t\u001a\f\u0012\b\u0012\u00060\u000bR\u00020\u00000\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006."}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/ui/speedconfig/EditTextAdapter;", "Landroid/widget/BaseAdapter;", "Landroid/view/View$OnClickListener;", "Landroid/view/View$OnTouchListener;", "Landroid/view/View$OnFocusChangeListener;", "Landroid/view/View$OnLongClickListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "parameterList", "Ljava/util/ArrayList;", "Lcom/pudutech/mirsdk/mircore/ui/speedconfig/EditTextAdapter$KV;", "touchedPosition", "", "getCount", "getItem", "", RequestParameters.POSITION, "getItemId", "", "getView", "Landroid/view/View;", "view", "parent", "Landroid/view/ViewGroup;", "onClick", "", "onFocusChange", "v", "hasFocus", "", "onLongClick", "onTouch", "event", "Landroid/view/MotionEvent;", "saveSpeedMode", "", "saveSpeedMode$mircore_packRelease", "selectedMode", "speedParam", "selectedMode$mircore_packRelease", "Companion", "KV", "KVSort", "ParamTextWatcher", "ViewHolder", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class EditTextAdapter extends BaseAdapter implements View.OnClickListener, View.OnTouchListener, View.OnFocusChangeListener, View.OnLongClickListener {
    private final Context context;
    private final ArrayList<C5270KV> parameterList;
    private int touchedPosition;
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View v) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        return true;
    }

    public EditTextAdapter(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.touchedPosition = -1;
        this.parameterList = new ArrayList<>();
    }

    public final String saveSpeedMode$mircore_packRelease() {
        JSONObject jSONObject = new JSONObject();
        int size = this.parameterList.size();
        for (int i = 0; i < size; i++) {
            C5270KV c5270kv = this.parameterList.get(i);
            Intrinsics.checkExpressionValueIsNotNull(c5270kv, "parameterList[i]");
            C5270KV c5270kv2 = c5270kv;
            String value_str = c5270kv2.getValue_str();
            if (value_str == null) {
                Intrinsics.throwNpe();
            }
            if (value_str.length() == 0) {
                c5270kv2.setValue(c5270kv2.getBackup());
                Object value = c5270kv2.getValue();
                if (value == null) {
                    Intrinsics.throwNpe();
                }
                c5270kv2.setValue_str(value.toString());
                String str = TAG;
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("value: ");
                String value_str2 = c5270kv2.getValue_str();
                if (value_str2 == null) {
                    Intrinsics.throwNpe();
                }
                sb.append(value_str2);
                objArr[0] = sb.toString();
                Pdlog.m3273d(str, objArr);
            } else if ((c5270kv2.getValue() instanceof Double) || (c5270kv2.getValue() instanceof Integer)) {
                try {
                    String value_str3 = c5270kv2.getValue_str();
                    if (value_str3 == null) {
                        Intrinsics.throwNpe();
                    }
                    c5270kv2.setValue(Double.valueOf(value_str3));
                } catch (NumberFormatException unused) {
                    c5270kv2.setValue(c5270kv2.getBackup());
                    String str2 = TAG;
                    Object[] objArr2 = new Object[1];
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("NumberFormatException Double value: ");
                    String value_str4 = c5270kv2.getValue_str();
                    if (value_str4 == null) {
                        Intrinsics.throwNpe();
                    }
                    sb2.append(value_str4);
                    objArr2[0] = sb2.toString();
                    Pdlog.m3277w(str2, objArr2);
                    Object value2 = c5270kv2.getValue();
                    if (value2 == null) {
                        Intrinsics.throwNpe();
                    }
                    c5270kv2.setValue_str(value2.toString());
                }
            } else {
                String str3 = TAG;
                Object[] objArr3 = new Object[1];
                StringBuilder sb3 = new StringBuilder();
                sb3.append("not number type: ");
                String value_str5 = c5270kv2.getValue_str();
                if (value_str5 == null) {
                    Intrinsics.throwNpe();
                }
                sb3.append(value_str5);
                objArr3[0] = sb3.toString();
                Pdlog.m3277w(str3, objArr3);
            }
            try {
                jSONObject.put(c5270kv2.getKey(), c5270kv2.getValue());
            } catch (JSONException e) {
                Pdlog.m3274e(TAG, "json error:" + e);
            }
        }
        String jSONObject2 = jSONObject.toString();
        Intrinsics.checkExpressionValueIsNotNull(jSONObject2, "jsonObject.toString()");
        return jSONObject2;
    }

    public final void selectedMode$mircore_packRelease(String speedParam) {
        String str = TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("speed parameter: ");
        if (speedParam == null) {
            Intrinsics.throwNpe();
        }
        sb.append(speedParam);
        objArr[0] = sb.toString();
        Pdlog.m3273d(str, objArr);
        this.parameterList.clear();
        try {
            JSONObject jSONObject = new JSONObject(speedParam);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                C5270KV c5270kv = new C5270KV();
                String next = keys.next();
                if (next == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                }
                c5270kv.setKey(next);
                c5270kv.setValue(jSONObject.get(c5270kv.getKey()));
                c5270kv.setBackup(c5270kv.getValue());
                Object value = c5270kv.getValue();
                if (value == null) {
                    Intrinsics.throwNpe();
                }
                c5270kv.setValue_str(value.toString());
                this.parameterList.add(c5270kv);
            }
        } catch (JSONException e) {
            Pdlog.m3274e(TAG, "exception ", e.getLocalizedMessage(), " :", Log.getStackTraceString(e));
        }
        Collections.sort(this.parameterList, new KVSort());
        Pdlog.m3273d(TAG, "value list size: " + this.parameterList.size());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        Object tag = view.getTag();
        if (tag == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
        }
        int intValue = ((Integer) tag).intValue();
        this.parameterList.get(intValue).setValue(this.parameterList.get(intValue).getBackup());
        C5270KV c5270kv = this.parameterList.get(intValue);
        Object value = this.parameterList.get(intValue).getValue();
        if (value == null) {
            Intrinsics.throwNpe();
        }
        c5270kv.setValue_str(value.toString());
        notifyDataSetChanged();
        this.touchedPosition = intValue;
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View v, boolean hasFocus) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        v.dispatchWindowFocusChanged(hasFocus);
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View v, MotionEvent event) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        Intrinsics.checkParameterIsNotNull(event, "event");
        if (event.getAction() != 1) {
            return false;
        }
        v.performClick();
        Object tag = v.getTag();
        if (tag == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
        }
        this.touchedPosition = ((Integer) tag).intValue();
        return false;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.parameterList.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int position) {
        C5270KV c5270kv = this.parameterList.get(position);
        Intrinsics.checkExpressionValueIsNotNull(c5270kv, "parameterList[position]");
        return c5270kv;
    }

    @Override // android.widget.Adapter
    public View getView(int position, View view, ViewGroup parent) {
        View view2;
        ViewHolder viewHolder;
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        if (this.parameterList.isEmpty()) {
            return null;
        }
        if (view == null) {
            viewHolder = new ViewHolder();
            view2 = View.inflate(this.context, C5224R.layout.activity_speed_mode_modify, null);
            if (view2 == null) {
                Intrinsics.throwNpe();
            }
            viewHolder.setTextView((TextView) view2.findViewById(C5224R.id.tvKey));
            viewHolder.setEditText((SpeedModeText) view2.findViewById(C5224R.id.etValue));
            viewHolder.setResetButton((Button) view2.findViewById(C5224R.id.btnReset));
            viewHolder.setListener(this);
            view2.setTag(viewHolder);
        } else {
            Object tag = view.getTag();
            if (tag == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.mirsdk.mircore.ui.speedconfig.EditTextAdapter.ViewHolder");
            }
            ViewHolder viewHolder2 = (ViewHolder) tag;
            view2 = view;
            viewHolder = viewHolder2;
        }
        viewHolder.updatePosition(position);
        C5270KV c5270kv = this.parameterList.get(position);
        Intrinsics.checkExpressionValueIsNotNull(c5270kv, "parameterList[position]");
        C5270KV c5270kv2 = c5270kv;
        TextView textView = viewHolder.getTextView();
        if (textView == null) {
            Intrinsics.throwNpe();
        }
        textView.setText(c5270kv2.getKey());
        SpeedModeText editText = viewHolder.getEditText();
        if (editText == null) {
            Intrinsics.throwNpe();
        }
        editText.setText(c5270kv2.getValue_str());
        if ((c5270kv2.getValue() instanceof Integer) || (c5270kv2.getValue() instanceof Double)) {
            SpeedModeText editText2 = viewHolder.getEditText();
            if (editText2 == null) {
                Intrinsics.throwNpe();
            }
            editText2.setFocusable(true);
            SpeedModeText editText3 = viewHolder.getEditText();
            if (editText3 == null) {
                Intrinsics.throwNpe();
            }
            editText3.setFocusableInTouchMode(true);
            SpeedModeText editText4 = viewHolder.getEditText();
            if (editText4 == null) {
                Intrinsics.throwNpe();
            }
            editText4.setBackgroundColor(0);
        } else {
            SpeedModeText editText5 = viewHolder.getEditText();
            if (editText5 == null) {
                Intrinsics.throwNpe();
            }
            editText5.setFocusable(false);
            SpeedModeText editText6 = viewHolder.getEditText();
            if (editText6 == null) {
                Intrinsics.throwNpe();
            }
            editText6.setInputType(0);
            SpeedModeText editText7 = viewHolder.getEditText();
            if (editText7 == null) {
                Intrinsics.throwNpe();
            }
            editText7.setBackgroundColor(0);
        }
        int i = this.touchedPosition;
        if (i != -1 && i == position) {
            SpeedModeText editText8 = viewHolder.getEditText();
            if (editText8 == null) {
                Intrinsics.throwNpe();
            }
            editText8.requestFocus();
            SpeedModeText editText9 = viewHolder.getEditText();
            if (editText9 == null) {
                Intrinsics.throwNpe();
            }
            Editable text = editText9.getText();
            if (text != null) {
                SpeedModeText editText10 = viewHolder.getEditText();
                if (editText10 == null) {
                    Intrinsics.throwNpe();
                }
                editText10.setSelection(text.length());
            }
            String str = TAG;
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("request focus and set selection: ");
            TextView textView2 = viewHolder.getTextView();
            if (textView2 == null) {
                Intrinsics.throwNpe();
            }
            sb.append(textView2.getText());
            objArr[0] = sb.toString();
            Pdlog.m3273d(str, objArr);
        } else {
            SpeedModeText editText11 = viewHolder.getEditText();
            if (editText11 == null) {
                Intrinsics.throwNpe();
            }
            editText11.clearFocus();
        }
        return view2;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: EditTextAdapter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0080\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0005\"\u0004\b\u0010\u0010\u0007R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000b\"\u0004\b\u0013\u0010\r¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/ui/speedconfig/EditTextAdapter$KV;", "", "(Lcom/pudutech/mirsdk/mircore/ui/speedconfig/EditTextAdapter;)V", "backup", "getBackup", "()Ljava/lang/Object;", "setBackup", "(Ljava/lang/Object;)V", TransferTable.COLUMN_KEY, "", "getKey", "()Ljava/lang/String;", "setKey", "(Ljava/lang/String;)V", ES6Iterator.VALUE_PROPERTY, "getValue", "setValue", "value_str", "getValue_str", "setValue_str", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.mircore.ui.speedconfig.EditTextAdapter$KV */
    /* loaded from: classes6.dex */
    public final class C5270KV {
        private Object backup;
        private String key;
        private Object value;
        private String value_str;

        public C5270KV() {
        }

        public final String getKey() {
            return this.key;
        }

        public final void setKey(String str) {
            this.key = str;
        }

        public final Object getValue() {
            return this.value;
        }

        public final void setValue(Object obj) {
            this.value = obj;
        }

        public final Object getBackup() {
            return this.backup;
        }

        public final void setBackup(Object obj) {
            this.backup = obj;
        }

        public final String getValue_str() {
            return this.value_str;
        }

        public final void setValue_str(String str) {
            this.value_str = str;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: EditTextAdapter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0080\u0004\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J \u0010\u0005\u001a\u00020\u00062\n\u0010\u0007\u001a\u00060\u0002R\u00020\u00032\n\u0010\b\u001a\u00060\u0002R\u00020\u0003H\u0016¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/ui/speedconfig/EditTextAdapter$KVSort;", "Ljava/util/Comparator;", "Lcom/pudutech/mirsdk/mircore/ui/speedconfig/EditTextAdapter$KV;", "Lcom/pudutech/mirsdk/mircore/ui/speedconfig/EditTextAdapter;", "(Lcom/pudutech/mirsdk/mircore/ui/speedconfig/EditTextAdapter;)V", "compare", "", "o1", "o2", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final class KVSort implements Comparator<C5270KV> {
        public KVSort() {
        }

        @Override // java.util.Comparator
        public int compare(C5270KV o1, C5270KV o2) {
            Intrinsics.checkParameterIsNotNull(o1, "o1");
            Intrinsics.checkParameterIsNotNull(o2, "o2");
            String key = o1.getKey();
            if (key == null) {
                Intrinsics.throwNpe();
            }
            String key2 = o2.getKey();
            if (key2 == null) {
                Intrinsics.throwNpe();
            }
            return StringsKt.compareTo(key, key2, true);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: EditTextAdapter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0080\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u000bJ\u000e\u0010\u001f\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020!R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00060\nR\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006\""}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/ui/speedconfig/EditTextAdapter$ViewHolder;", "", "(Lcom/pudutech/mirsdk/mircore/ui/speedconfig/EditTextAdapter;)V", "editText", "Lcom/pudutech/mirsdk/mircore/ui/speedconfig/SpeedModeText;", "getEditText", "()Lcom/pudutech/mirsdk/mircore/ui/speedconfig/SpeedModeText;", "setEditText", "(Lcom/pudutech/mirsdk/mircore/ui/speedconfig/SpeedModeText;)V", "paramTextWatcher", "Lcom/pudutech/mirsdk/mircore/ui/speedconfig/EditTextAdapter$ParamTextWatcher;", "Lcom/pudutech/mirsdk/mircore/ui/speedconfig/EditTextAdapter;", "getParamTextWatcher", "()Lcom/pudutech/mirsdk/mircore/ui/speedconfig/EditTextAdapter$ParamTextWatcher;", "setParamTextWatcher", "(Lcom/pudutech/mirsdk/mircore/ui/speedconfig/EditTextAdapter$ParamTextWatcher;)V", "resetButton", "Landroid/widget/Button;", "getResetButton", "()Landroid/widget/Button;", "setResetButton", "(Landroid/widget/Button;)V", "textView", "Landroid/widget/TextView;", "getTextView", "()Landroid/widget/TextView;", "setTextView", "(Landroid/widget/TextView;)V", "setListener", "", "edittextAdapter", "updatePosition", RequestParameters.POSITION, "", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final class ViewHolder {
        private SpeedModeText editText;
        private ParamTextWatcher paramTextWatcher;
        private Button resetButton;
        private TextView textView;

        public ViewHolder() {
            this.paramTextWatcher = new ParamTextWatcher();
        }

        public final TextView getTextView() {
            return this.textView;
        }

        public final void setTextView(TextView textView) {
            this.textView = textView;
        }

        public final SpeedModeText getEditText() {
            return this.editText;
        }

        public final void setEditText(SpeedModeText speedModeText) {
            this.editText = speedModeText;
        }

        public final Button getResetButton() {
            return this.resetButton;
        }

        public final void setResetButton(Button button) {
            this.resetButton = button;
        }

        public final ParamTextWatcher getParamTextWatcher() {
            return this.paramTextWatcher;
        }

        public final void setParamTextWatcher(ParamTextWatcher paramTextWatcher) {
            Intrinsics.checkParameterIsNotNull(paramTextWatcher, "<set-?>");
            this.paramTextWatcher = paramTextWatcher;
        }

        public final void updatePosition(int position) {
            this.paramTextWatcher.updatePosition(position);
            TextView textView = this.textView;
            if (textView != null) {
                if (textView == null) {
                    Intrinsics.throwNpe();
                }
                textView.setTag(Integer.valueOf(position));
            }
            SpeedModeText speedModeText = this.editText;
            if (speedModeText != null) {
                if (speedModeText == null) {
                    Intrinsics.throwNpe();
                }
                speedModeText.setTag(Integer.valueOf(position));
            }
            Button button = this.resetButton;
            if (button != null) {
                if (button == null) {
                    Intrinsics.throwNpe();
                }
                button.setTag(Integer.valueOf(position));
            }
        }

        public final void setListener(EditTextAdapter edittextAdapter) {
            Intrinsics.checkParameterIsNotNull(edittextAdapter, "edittextAdapter");
            SpeedModeText speedModeText = this.editText;
            if (speedModeText == null) {
                Intrinsics.throwNpe();
            }
            speedModeText.setOnTouchListener(edittextAdapter);
            SpeedModeText speedModeText2 = this.editText;
            if (speedModeText2 == null) {
                Intrinsics.throwNpe();
            }
            speedModeText2.setOnFocusChangeListener(edittextAdapter);
            Button button = this.resetButton;
            if (button == null) {
                Intrinsics.throwNpe();
            }
            button.setOnClickListener(edittextAdapter);
            SpeedModeText speedModeText3 = this.editText;
            if (speedModeText3 == null) {
                Intrinsics.throwNpe();
            }
            speedModeText3.addTextChangedListener(this.paramTextWatcher);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: EditTextAdapter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\b\b\u0080\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J(\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004H\u0016J(\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0016J\u000e\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/ui/speedconfig/EditTextAdapter$ParamTextWatcher;", "Landroid/text/TextWatcher;", "(Lcom/pudutech/mirsdk/mircore/ui/speedconfig/EditTextAdapter;)V", "mPosition", "", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "count", "after", "onTextChanged", "before", "updatePosition", RequestParameters.POSITION, "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final class ParamTextWatcher implements TextWatcher {
        private int mPosition;

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Intrinsics.checkParameterIsNotNull(s, "s");
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Intrinsics.checkParameterIsNotNull(s, "s");
        }

        public ParamTextWatcher() {
        }

        public final void updatePosition(int position) {
            this.mPosition = position;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable s) {
            Intrinsics.checkParameterIsNotNull(s, "s");
            ((C5270KV) EditTextAdapter.this.parameterList.get(this.mPosition)).setValue_str(s.toString());
        }
    }
}
