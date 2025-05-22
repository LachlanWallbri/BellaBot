package com.pudutech.mirsdk.hardware.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.pudutech.mirsdk.hardware.activity.MachineInfoActivity;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MachineInfoActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\u0010\u0000\u001a\u00020\u00012\u0016\u0010\u0002\u001a\u0012\u0012\u0002\b\u0003 \u0004*\b\u0012\u0002\b\u0003\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00060\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\n¢\u0006\u0002\b\u000b"}, m3961d2 = {"<anonymous>", "", "parent", "Landroid/widget/AdapterView;", "kotlin.jvm.PlatformType", "view", "Landroid/view/View;", RequestParameters.POSITION, "", "id", "", "onItemClick"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
final class MachineInfoActivity$onCreate$2 implements AdapterView.OnItemClickListener {
    final /* synthetic */ boolean $changeMachineInfo;
    final /* synthetic */ Ref.ObjectRef $deviceList;
    final /* synthetic */ ListView $listView;
    final /* synthetic */ MachineInfoActivity this$0;

    MachineInfoActivity$onCreate$2(MachineInfoActivity machineInfoActivity, ListView listView, boolean z, Ref.ObjectRef objectRef) {
        this.this$0 = machineInfoActivity;
        this.$listView = listView;
        this.$changeMachineInfo = z;
        this.$deviceList = objectRef;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        ListAdapter adapter;
        Function2<Integer, Integer, Unit> function2 = new Function2<Integer, Integer, Unit>() { // from class: com.pudutech.mirsdk.hardware.activity.MachineInfoActivity$onCreate$2$updateDate$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, Integer num2) {
                invoke(num.intValue(), num2.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i2, int i3) {
                ListAdapter adapter2 = MachineInfoActivity$onCreate$2.this.$listView.getAdapter();
                Object item = adapter2 != null ? adapter2.getItem(i2) : null;
                if (item == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.mirsdk.hardware.activity.MachineInfoActivity.MachineKV");
                }
                ((MachineInfoActivity.MachineKV) item).setValue(String.valueOf(i3));
                ListAdapter adapter3 = MachineInfoActivity$onCreate$2.this.$listView.getAdapter();
                if (adapter3 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.mirsdk.hardware.activity.MachineInfoActivity.MachineAdapter");
                }
                ((MachineInfoActivity.MachineAdapter) adapter3).notifyDataSetChanged();
            }
        };
        if (this.$changeMachineInfo) {
            ListView listView = this.$listView;
            Object item = (listView == null || (adapter = listView.getAdapter()) == null) ? null : adapter.getItem(i);
            if (item == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.mirsdk.hardware.activity.MachineInfoActivity.MachineKV");
            }
            MachineInfoActivity.MachineKV machineKV = (MachineInfoActivity.MachineKV) item;
            if (CollectionsKt.contains((List) this.$deviceList.element, machineKV.getName())) {
                TDialog tDialog = new TDialog();
                MachineInfoActivity machineInfoActivity = this.this$0;
                String name = machineKV.getName();
                if (name == null) {
                    Intrinsics.throwNpe();
                }
                String value = machineKV.getValue();
                if (value == null) {
                    Intrinsics.throwNpe();
                }
                tDialog.showSingSelect(machineInfoActivity, name, value, i, function2);
            }
        }
    }
}
