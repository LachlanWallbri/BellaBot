package com.pudutech.factory_test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.LinearLayoutCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.serialize.DeviceType;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.schedulerlib.connection.ESPScheduleNative;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Charsets;

/* compiled from: TestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0016\u001a\u00020\u0017J\u0012\u0010\u0018\u001a\u00020\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014J\b\u0010\u001b\u001a\u00020\u0017H\u0014J\u0006\u0010\u001c\u001a\u00020\u0017J\u0006\u0010\u001d\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0010\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0015\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u001e"}, m3961d2 = {"Lcom/pudutech/factory_test/TestActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "TAG", "", "allReceive", "", "getAllReceive", "()I", "setAllReceive", "(I)V", "isReading", "", "()Z", "setReading", "(Z)V", "originChannel", "getOriginChannel", "()Ljava/lang/Integer;", "setOriginChannel", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "initReading", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "resetAndCloseESP32", "setESP32", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class TestActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    private int allReceive;
    private Integer originChannel;
    private final String TAG = "TestActivity_ESP";
    private boolean isReading = true;

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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(2131427397);
        Pdlog.m3273d(this.TAG, "onCreate");
        ((AppCompatButton) _$_findCachedViewById(C4491R.id.btnQuit)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.TestActivity$onCreate$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TestActivity.this.finish();
            }
        });
        LinearLayoutCompat layoutOptions = (LinearLayoutCompat) _$_findCachedViewById(C4491R.id.layoutOptions);
        Intrinsics.checkExpressionValueIsNotNull(layoutOptions, "layoutOptions");
        ArrayList layoutBtnOptions$default = LayoutHelperKt.layoutBtnOptions$default(layoutOptions, this, CollectionsKt.arrayListOf("初始化", "发送"), 0.0f, 4, null);
        ((Button) layoutBtnOptions$default.get(0)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.TestActivity$onCreate$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TestActivity.this.setESP32();
            }
        });
        final Ref.ByteRef byteRef = new Ref.ByteRef();
        byteRef.element = (byte) 0;
        ((Button) layoutBtnOptions$default.get(1)).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.factory_test.TestActivity$onCreate$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ESPScheduleNative.INSTANCE.resetChannel(9);
                ESPScheduleNative eSPScheduleNative = ESPScheduleNative.INSTANCE;
                Ref.ByteRef byteRef2 = Ref.ByteRef.this;
                byte b = byteRef2.element;
                byteRef2.element = (byte) (b + 1);
                eSPScheduleNative.sendMsg(ArraysKt.plus(new byte[10], b));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Pdlog.m3273d(this.TAG, "onDestroy ");
        this.isReading = false;
        resetAndCloseESP32();
    }

    public final Integer getOriginChannel() {
        return this.originChannel;
    }

    public final void setOriginChannel(Integer num) {
        this.originChannel = num;
    }

    public final boolean setESP32() {
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        if (hdInterface == null) {
            Intrinsics.throwNpe();
        }
        hdInterface.controlDevice(DeviceType.ESP, false);
        Pdlog.m3273d(this.TAG, "setESP32 ");
        HardwareInterface hdInterface2 = ServiceConnectionKt.getHdInterface();
        if (hdInterface2 == null) {
            Intrinsics.throwNpe();
        }
        UByte uByte = hdInterface2.getMachineInfo().getByte23Data().get(MachineInfo.Byte23Info.ESP32Version);
        if (uByte == null) {
            Intrinsics.throwNpe();
        }
        int data = uByte.getData() & 255;
        Pdlog.m3275i(this.TAG, "espVersion=" + data);
        if (ESPScheduleNative.INSTANCE.openESP(576000, data)) {
            this.originChannel = Integer.valueOf(ESPScheduleNative.INSTANCE.getChannel());
            Pdlog.m3275i(this.TAG, "esp32 open success. getEspFirmwareVersion()=" + ESPScheduleNative.INSTANCE.getEspFirmwareVersion() + " originChannel=" + this.originChannel);
            Integer num = this.originChannel;
            if (num == null || num.intValue() != 9) {
                if (!ESPScheduleNative.INSTANCE.resetChannel(9)) {
                    Pdlog.m3275i(this.TAG, "reset fail");
                    return false;
                }
                Pdlog.m3275i(this.TAG, "resetChannel. read back=" + ESPScheduleNative.INSTANCE.getChannel());
            }
            ESPScheduleNative eSPScheduleNative = ESPScheduleNative.INSTANCE;
            byte[] bytes = "init success".getBytes(Charsets.UTF_8);
            Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
            eSPScheduleNative.sendMsg(bytes);
            initReading();
            return true;
        }
        Pdlog.m3274e(this.TAG, "esp32 open fail");
        return false;
    }

    public final void resetAndCloseESP32() {
        Integer num;
        Pdlog.m3273d(this.TAG, "resetAndCloseESP32 originChannel=" + this.originChannel);
        int channel = ESPScheduleNative.INSTANCE.getChannel();
        Integer num2 = this.originChannel;
        if ((num2 == null || channel != num2.intValue()) && (num = this.originChannel) != null) {
            ESPScheduleNative.INSTANCE.resetChannel(num.intValue());
            ESPScheduleNative eSPScheduleNative = ESPScheduleNative.INSTANCE;
            byte[] bytes = "reset channcel".getBytes(Charsets.UTF_8);
            Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
            eSPScheduleNative.sendMsg(bytes);
        }
        ESPScheduleNative.INSTANCE.closeESP();
    }

    public final int getAllReceive() {
        return this.allReceive;
    }

    public final void setAllReceive(int i) {
        this.allReceive = i;
    }

    /* renamed from: isReading, reason: from getter */
    public final boolean getIsReading() {
        return this.isReading;
    }

    public final void setReading(boolean z) {
        this.isReading = z;
    }

    public final void initReading() {
        Pdlog.m3273d(this.TAG, "initReading ");
        new Thread(new Runnable() { // from class: com.pudutech.factory_test.TestActivity$initReading$1
            @Override // java.lang.Runnable
            public final void run() {
                String str;
                String str2;
                String str3;
                String str4;
                str = TestActivity.this.TAG;
                Pdlog.m3275i(str, "start reading");
                while (TestActivity.this.getIsReading()) {
                    byte[] readMsg = ESPScheduleNative.INSTANCE.readMsg();
                    str3 = TestActivity.this.TAG;
                    Object[] objArr = new Object[1];
                    StringBuilder sb = new StringBuilder();
                    sb.append("receive size=");
                    sb.append(readMsg != null ? Integer.valueOf(readMsg.length) : null);
                    objArr[0] = sb.toString();
                    Pdlog.m3273d(str3, objArr);
                    if ((readMsg != null ? Integer.valueOf(readMsg.length) : null) != null) {
                        str4 = TestActivity.this.TAG;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("receive allCnt=");
                        TestActivity testActivity = TestActivity.this;
                        int allReceive = testActivity.getAllReceive();
                        testActivity.setAllReceive(allReceive + 1);
                        sb2.append(allReceive);
                        sb2.append(" index=");
                        sb2.append((int) ArraysKt.last(readMsg));
                        Pdlog.m3273d(str4, sb2.toString());
                    }
                }
                str2 = TestActivity.this.TAG;
                Pdlog.m3275i(str2, "reading end");
            }
        }).start();
    }
}
