package com.pudutech.mirsdk.utiles;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanResult;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.pudutech.mirsdk.bluetooth.BluetoothBleHelper;
import com.pudutech.mirsdk.function.C4946R;
import com.pudutech.mirsdk.utiles.BluetoothRecyclerAdapter;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BluetoothRecyclerAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u001a\u001bB\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0010H\u0016J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0010H\u0016J\u0018\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0010H\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/mirsdk/utiles/BluetoothRecyclerAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/pudutech/mirsdk/utiles/BluetoothRecyclerAdapter$MyViewHolder;", "data", "", "Landroid/bluetooth/le/ScanResult;", "(Ljava/util/List;)V", "onItemClickListener", "Lcom/pudutech/mirsdk/utiles/BluetoothRecyclerAdapter$OnItemClickListener;", "getOnItemClickListener", "()Lcom/pudutech/mirsdk/utiles/BluetoothRecyclerAdapter$OnItemClickListener;", "setOnItemClickListener", "(Lcom/pudutech/mirsdk/utiles/BluetoothRecyclerAdapter$OnItemClickListener;)V", "getConnectStateStr", "", "state", "", "getItemCount", "onBindViewHolder", "", "holder", RequestParameters.POSITION, "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "MyViewHolder", "OnItemClickListener", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class BluetoothRecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private final List<ScanResult> data;
    private OnItemClickListener onItemClickListener;

    /* compiled from: BluetoothRecyclerAdapter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/mirsdk/utiles/BluetoothRecyclerAdapter$OnItemClickListener;", "", "onItemClick", "", RequestParameters.POSITION, "", "mac", "", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public interface OnItemClickListener {
        void onItemClick(int position, String mac);
    }

    public BluetoothRecyclerAdapter(List<ScanResult> data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        this.data = data;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        View view = LayoutInflater.from(parent.getContext()).inflate(C4946R.layout.recycler_bt_item, parent, false);
        Intrinsics.checkExpressionValueIsNotNull(view, "view");
        return new MyViewHolder(view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.data.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        String str;
        String address;
        String address2;
        Intrinsics.checkParameterIsNotNull(holder, "holder");
        final ScanResult scanResult = this.data.get(position);
        TextView title = holder.getTitle();
        StringBuilder sb = new StringBuilder();
        sb.append("Name:");
        BluetoothDevice device = scanResult.getDevice();
        String str2 = null;
        sb.append(device != null ? device.getName() : null);
        sb.append(" State:");
        BluetoothBleHelper bluetoothBleHelper = BluetoothBleHelper.INSTANCE;
        BluetoothDevice device2 = scanResult.getDevice();
        if (device2 == null || (address2 = device2.getAddress()) == null) {
            str = null;
        } else {
            Locale locale = Locale.US;
            Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.US");
            if (address2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            str = address2.toUpperCase(locale);
            Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.String).toUpperCase(locale)");
        }
        sb.append(getConnectStateStr(bluetoothBleHelper.getConnectState(str)));
        title.setText(sb.toString());
        TextView mac = holder.getMac();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("MAC:");
        BluetoothDevice device3 = scanResult.getDevice();
        if (device3 != null && (address = device3.getAddress()) != null) {
            Locale locale2 = Locale.US;
            Intrinsics.checkExpressionValueIsNotNull(locale2, "Locale.US");
            if (address == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            str2 = address.toUpperCase(locale2);
            Intrinsics.checkExpressionValueIsNotNull(str2, "(this as java.lang.String).toUpperCase(locale)");
        }
        sb2.append(str2);
        mac.setText(sb2.toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.mirsdk.utiles.BluetoothRecyclerAdapter$onBindViewHolder$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                String str3;
                String address3;
                BluetoothRecyclerAdapter.OnItemClickListener onItemClickListener = BluetoothRecyclerAdapter.this.getOnItemClickListener();
                if (onItemClickListener != null) {
                    int i = position;
                    BluetoothDevice device4 = scanResult.getDevice();
                    if (device4 == null || (address3 = device4.getAddress()) == null) {
                        str3 = null;
                    } else {
                        Locale locale3 = Locale.US;
                        Intrinsics.checkExpressionValueIsNotNull(locale3, "Locale.US");
                        if (address3 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }
                        str3 = address3.toUpperCase(locale3);
                        Intrinsics.checkExpressionValueIsNotNull(str3, "(this as java.lang.String).toUpperCase(locale)");
                    }
                    onItemClickListener.onItemClick(i, str3);
                }
            }
        });
    }

    private final String getConnectStateStr(int state) {
        if (state == 0) {
            return "未连接[DISCONNECTED]";
        }
        if (state == 1) {
            return "连接中[CONNECTING]";
        }
        if (state == 2) {
            return "已连接[CONNECTED]";
        }
        if (state == 3) {
            return "断开中[DISCONNECTING]";
        }
        return "Unknown State " + state;
    }

    /* compiled from: BluetoothRecyclerAdapter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/mirsdk/utiles/BluetoothRecyclerAdapter$MyViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "mac", "Landroid/widget/TextView;", "getMac", "()Landroid/widget/TextView;", "title", "getTitle", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView mac;
        private final TextView title;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MyViewHolder(View itemView) {
            super(itemView);
            Intrinsics.checkParameterIsNotNull(itemView, "itemView");
            View findViewById = itemView.findViewById(C4946R.id.tv_name);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "itemView.findViewById(R.id.tv_name)");
            this.title = (TextView) findViewById;
            View findViewById2 = itemView.findViewById(C4946R.id.tv_mac);
            Intrinsics.checkExpressionValueIsNotNull(findViewById2, "itemView.findViewById(R.id.tv_mac)");
            this.mac = (TextView) findViewById2;
        }

        public final TextView getTitle() {
            return this.title;
        }

        public final TextView getMac() {
            return this.mac;
        }
    }

    public final OnItemClickListener getOnItemClickListener() {
        return this.onItemClickListener;
    }

    public final void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
