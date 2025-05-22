package com.pudutech.peanut.robot_ui.p063ui.dialog;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ScrollView;
import android.widget.TextView;
import com.pudutech.base.Pdlog;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.config.Constans;
import com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener;
import com.pudutech.peanut.robot_ui.p063ui.helper.PalletCountHelper;
import com.pudutech.peanut.robot_ui.util.NavigationBarUtil;
import com.pudutech.peanut.robot_ui.util.UiUtils;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class DeliveryHistoryTaskDialog extends Dialog {
    private static final String TAG = "DeliveryHistoryTaskDialog";
    private int AUTO_CLOSE;
    private Context context;
    private Handler mainHandle;
    private ArrayList<ScrollView> tablesList;
    private ArrayList<TextView> traysList;

    public /* synthetic */ boolean lambda$new$0$DeliveryHistoryTaskDialog(Message message) {
        if (message.what == this.AUTO_CLOSE) {
            Pdlog.m3273d(TAG, "AUTO_CLOSE");
            dismiss();
        }
        return true;
    }

    public DeliveryHistoryTaskDialog(Context context) {
        super(context);
        this.AUTO_CLOSE = 1001;
        this.traysList = new ArrayList<>();
        this.tablesList = new ArrayList<>();
        this.mainHandle = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.-$$Lambda$DeliveryHistoryTaskDialog$73QJk0lhySFWDejGT3r6Hyh7i8Y
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return DeliveryHistoryTaskDialog.this.lambda$new$0$DeliveryHistoryTaskDialog(message);
            }
        });
        init(context);
    }

    public DeliveryHistoryTaskDialog(Context context, int i) {
        super(context, i);
        this.AUTO_CLOSE = 1001;
        this.traysList = new ArrayList<>();
        this.tablesList = new ArrayList<>();
        this.mainHandle = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.-$$Lambda$DeliveryHistoryTaskDialog$73QJk0lhySFWDejGT3r6Hyh7i8Y
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return DeliveryHistoryTaskDialog.this.lambda$new$0$DeliveryHistoryTaskDialog(message);
            }
        });
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        build();
    }

    private void build() {
        View inflate = getLayoutInflater().inflate(C5508R.layout.dialog_delivery_history_task, (ViewGroup) null);
        requestWindowFeature(1);
        Window window = getWindow();
        window.getDecorView().setSystemUiVisibility(3846);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setGravity(17);
        window.setAttributes(attributes);
        initView(inflate);
        setContentView(inflate);
        window.setLayout(-1, -1);
        window.setBackgroundDrawableResource(R.color.transparent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void initView(View view) {
        this.traysList.add(view.findViewById(C5508R.id.tray_1_tv));
        this.traysList.add(view.findViewById(C5508R.id.tray_2_tv));
        this.traysList.add(view.findViewById(C5508R.id.tray_3_tv));
        this.traysList.add(view.findViewById(C5508R.id.tray_4_tv));
        this.traysList.add(view.findViewById(C5508R.id.tray_5_tv));
        this.tablesList.add(view.findViewById(C5508R.id.tray_1_tables_layout));
        this.tablesList.add(view.findViewById(C5508R.id.tray_2_tables_layout));
        this.tablesList.add(view.findViewById(C5508R.id.tray_3_tables_layout));
        this.tablesList.add(view.findViewById(C5508R.id.tray_4_tables_layout));
        this.tablesList.add(view.findViewById(C5508R.id.tray_5_tables_layout));
        view.findViewById(C5508R.id.close_iv).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.dialog.DeliveryHistoryTaskDialog.1
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
            public void onSingleClick() {
                super.onSingleClick();
                DeliveryHistoryTaskDialog.this.dismiss();
            }
        });
    }

    @Override // android.app.Dialog
    public void show() {
        NavigationBarUtil.focusNotAle(getWindow());
        super.show();
        NavigationBarUtil.hideNavigationBar(getWindow());
        NavigationBarUtil.clearFocusNotAle(getWindow());
        try {
            initData();
        } catch (Exception e) {
            Pdlog.m3274e(TAG, Log.getStackTraceString(e));
        }
    }

    private void initData() {
        ArrayList<ArrayList<String>> lastDeliveryTask = Constans.INSTANCE.getLastDeliveryTask();
        if (lastDeliveryTask == null) {
            int count = PalletCountHelper.INSTANCE.getCount();
            ArrayList<ArrayList<String>> arrayList = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                arrayList.add(new ArrayList<>());
            }
            lastDeliveryTask = arrayList;
        }
        for (int i2 = 0; i2 < this.traysList.size(); i2++) {
            TextView textView = this.traysList.get(i2);
            ScrollView scrollView = this.tablesList.get(i2);
            if (lastDeliveryTask.size() > i2) {
                ArrayList<String> arrayList2 = lastDeliveryTask.get(i2);
                textView.setVisibility(0);
                textView.setTextSize(30.0f);
                if (arrayList2.size() == 0) {
                    textView.setText("");
                    scrollView.setVisibility(8);
                } else if (arrayList2.size() == 1) {
                    textView.setText(arrayList2.get(0));
                    scrollView.setVisibility(8);
                } else {
                    String tables = getTables(arrayList2);
                    textView.setText(tables);
                    ((TextView) scrollView.getChildAt(0)).setText(tables);
                    scrollView.setVisibility(0);
                }
                UiUtils.adjustTvTextSize(textView, 100, textView.getText().toString(), 22);
            } else {
                textView.setVisibility(8);
                scrollView.setVisibility(8);
            }
        }
    }

    private String getTables(ArrayList<String> arrayList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arrayList.size(); i++) {
            if (i == arrayList.size() - 1) {
                sb.append(arrayList.get(i));
            } else {
                sb.append(arrayList.get(i));
                sb.append(",");
            }
        }
        return sb.toString();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Pdlog.m3273d(TAG, "onDetachedFromWindow");
        this.mainHandle.removeMessages(this.AUTO_CLOSE);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Pdlog.m3273d(TAG, "onAttachedToWindow");
        this.mainHandle.sendEmptyMessageDelayed(this.AUTO_CLOSE, 120000L);
    }
}
