package com.pudutech.rgbdviewer;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.pudutech.base.Pdlog;
import com.pudutech.gatecontrollerlib.GateControllerMsg;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.jvm.internal.Intrinsics;
import org.apache.http.HttpStatus;
import org.bouncycastle.math.Primes;
import org.jetbrains.anko.DimensionsKt;

/* compiled from: RGBDView.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\r\u0018\u00002\u00020\u0001:\u00017B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u0010\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0002J\u0006\u0010,\u001a\u00020\u000fJ\u0006\u0010-\u001a\u00020\u0016J\u0016\u0010.\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010/\u001a\u00020+J\u000e\u00100\u001a\u00020)2\u0006\u00101\u001a\u00020\u000fJ\u000e\u00102\u001a\u00020)2\u0006\u00103\u001a\u00020\u0016J\u0006\u00104\u001a\u00020)J\b\u00105\u001a\u00020)H\u0002J\u0006\u00106\u001a\u00020)R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0013X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u000e\u0010\u001f\u001a\u00020\u001aX\u0082.¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001cR\u000e\u0010!\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u000e\u0010$\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b&\u0010#R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001e¨\u00068"}, m3961d2 = {"Lcom/pudutech/rgbdviewer/RGBDView;", "", "depthView", "Landroid/widget/ImageView;", "irView", "serialView", "Landroid/widget/TextView;", "stateView", "width", "", "height", "context", "Landroid/content/Context;", "(Landroid/widget/ImageView;Landroid/widget/ImageView;Landroid/widget/TextView;Landroid/widget/TextView;IILandroid/content/Context;)V", "TAG", "", "colorBuffer", "", "colormap_", "", "[[I", "configState", "Lcom/pudutech/rgbdviewer/RGBDView$ConfigState;", "getContext", "()Landroid/content/Context;", "depthBitmap", "Landroid/graphics/Bitmap;", "getDepthView", "()Landroid/widget/ImageView;", "getHeight", "()I", "irBitmap", "getIrView", "serialNum", "getSerialView", "()Landroid/widget/TextView;", "showDepthView", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getStateView", "getWidth", "depthToColorBuffer", "", "depthData", "", "getSerialNum", "getState", "setData", "irData", "setSerialNum", "serial", "setState", "state", "show", "showState", "switchView", "ConfigState", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class RGBDView {
    private final String TAG;
    private final int[] colorBuffer;
    private final int[][] colormap_;
    private ConfigState configState;
    private final Context context;
    private Bitmap depthBitmap;
    private final ImageView depthView;
    private final int height;
    private Bitmap irBitmap;
    private final ImageView irView;
    private String serialNum;
    private final TextView serialView;
    private AtomicBoolean showDepthView;
    private final TextView stateView;
    private final int width;

    /* compiled from: RGBDView.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/rgbdviewer/RGBDView$ConfigState;", "", "(Ljava/lang/String;I)V", "Configured", "NotConfigured", "AutoDetect", "AutoDetectError", "ManualSwitch", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public enum ConfigState {
        Configured,
        NotConfigured,
        AutoDetect,
        AutoDetectError,
        ManualSwitch
    }

    public RGBDView(ImageView depthView, ImageView irView, TextView serialView, TextView stateView, int i, int i2, Context context) {
        Intrinsics.checkParameterIsNotNull(depthView, "depthView");
        Intrinsics.checkParameterIsNotNull(irView, "irView");
        Intrinsics.checkParameterIsNotNull(serialView, "serialView");
        Intrinsics.checkParameterIsNotNull(stateView, "stateView");
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.depthView = depthView;
        this.irView = irView;
        this.serialView = serialView;
        this.stateView = stateView;
        this.width = i;
        this.height = i2;
        this.context = context;
        this.TAG = "RGBDView";
        this.serialNum = "";
        this.colorBuffer = new int[i * i2];
        this.showDepthView = new AtomicBoolean(true);
        this.colormap_ = new int[][]{new int[]{0, 0, 128}, new int[]{0, 0, 128}, new int[]{0, 0, 132}, new int[]{0, 0, 137}, new int[]{0, 0, 141}, new int[]{0, 0, 146}, new int[]{0, 0, 150}, new int[]{0, 0, 155}, new int[]{0, 0, 159}, new int[]{0, 0, 164}, new int[]{0, 0, 168}, new int[]{0, 0, 173}, new int[]{0, 0, 178}, new int[]{0, 0, 182}, new int[]{0, 0, 187}, new int[]{0, 0, 191}, new int[]{0, 0, 196}, new int[]{0, 0, 200}, new int[]{0, 0, HttpStatus.SC_RESET_CONTENT}, new int[]{0, 0, 209}, new int[]{0, 0, 214}, new int[]{0, 0, 218}, new int[]{0, 0, 223}, new int[]{0, 0, 227}, new int[]{0, 0, 232}, new int[]{0, 0, 237}, new int[]{0, 0, 241}, new int[]{0, 0, 246}, new int[]{0, 0, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION}, new int[]{0, 0, 255}, new int[]{0, 0, 255}, new int[]{0, 0, 255}, new int[]{0, 0, 255}, new int[]{0, 0, 255}, new int[]{0, 4, 255}, new int[]{0, 8, 255}, new int[]{0, 12, 255}, new int[]{0, 16, 255}, new int[]{0, 20, 255}, new int[]{0, 24, 255}, new int[]{0, 28, 255}, new int[]{0, 32, 255}, new int[]{0, 36, 255}, new int[]{0, 40, 255}, new int[]{0, 44, 255}, new int[]{0, 48, 255}, new int[]{0, 52, 255}, new int[]{0, 56, 255}, new int[]{0, 60, 255}, new int[]{0, 64, 255}, new int[]{0, 68, 255}, new int[]{0, 72, 255}, new int[]{0, 76, 255}, new int[]{0, 80, 255}, new int[]{0, 84, 255}, new int[]{0, 88, 255}, new int[]{0, 92, 255}, new int[]{0, 96, 255}, new int[]{0, 100, 255}, new int[]{0, 104, 255}, new int[]{0, 108, 255}, new int[]{0, 112, 255}, new int[]{0, 116, 255}, new int[]{0, 120, 255}, new int[]{0, 124, 255}, new int[]{0, 128, 255}, new int[]{0, 132, 255}, new int[]{0, 136, 255}, new int[]{0, 140, 255}, new int[]{0, 144, 255}, new int[]{0, 148, 255}, new int[]{0, 152, 255}, new int[]{0, 156, 255}, new int[]{0, 160, 255}, new int[]{0, 164, 255}, new int[]{0, 168, 255}, new int[]{0, 172, 255}, new int[]{0, 176, 255}, new int[]{0, 180, 255}, new int[]{0, 184, 255}, new int[]{0, 188, 255}, new int[]{0, 192, 255}, new int[]{0, 196, 255}, new int[]{0, 200, 255}, new int[]{0, 204, 255}, new int[]{0, 208, 255}, new int[]{0, 212, 255}, new int[]{0, 216, 255}, new int[]{0, 220, 254}, new int[]{0, 224, 251}, new int[]{0, 228, GateControllerMsg.ControlCode.Error}, new int[]{2, 232, 244}, new int[]{6, 236, 241}, new int[]{9, DimensionsKt.HDPI, 238}, new int[]{12, 244, 235}, new int[]{15, GateControllerMsg.ControlCode.Error, 231}, new int[]{19, 252, 228}, new int[]{22, 255, 225}, new int[]{25, 255, 222}, new int[]{28, 255, 219}, new int[]{31, 255, 215}, new int[]{35, 255, 212}, new int[]{38, 255, 209}, new int[]{41, 255, HttpStatus.SC_PARTIAL_CONTENT}, new int[]{44, 255, 202}, new int[]{48, 255, 199}, new int[]{51, 255, 196}, new int[]{54, 255, 193}, new int[]{57, 255, 190}, new int[]{60, 255, 186}, new int[]{64, 255, 183}, new int[]{67, 255, 180}, new int[]{70, 255, 177}, new int[]{73, 255, 173}, new int[]{77, 255, 170}, new int[]{80, 255, 167}, new int[]{83, 255, 164}, new int[]{86, 255, 160}, new int[]{90, 255, 157}, new int[]{93, 255, 154}, new int[]{96, 255, 151}, new int[]{99, 255, 148}, new int[]{102, 255, 144}, new int[]{106, 255, 141}, new int[]{109, 255, 138}, new int[]{112, 255, 135}, new int[]{115, 255, 131}, new int[]{119, 255, 128}, new int[]{122, 255, 125}, new int[]{125, 255, 122}, new int[]{128, 255, 119}, new int[]{131, 255, 115}, new int[]{135, 255, 112}, new int[]{138, 255, 109}, new int[]{141, 255, 106}, new int[]{144, 255, 102}, new int[]{148, 255, 99}, new int[]{151, 255, 96}, new int[]{154, 255, 93}, new int[]{157, 255, 90}, new int[]{160, 255, 86}, new int[]{164, 255, 83}, new int[]{167, 255, 80}, new int[]{170, 255, 77}, new int[]{173, 255, 73}, new int[]{177, 255, 70}, new int[]{180, 255, 67}, new int[]{183, 255, 64}, new int[]{186, 255, 60}, new int[]{190, 255, 57}, new int[]{193, 255, 54}, new int[]{196, 255, 51}, new int[]{199, 255, 48}, new int[]{202, 255, 44}, new int[]{HttpStatus.SC_PARTIAL_CONTENT, 255, 41}, new int[]{209, 255, 38}, new int[]{212, 255, 35}, new int[]{215, 255, 31}, new int[]{219, 255, 28}, new int[]{222, 255, 25}, new int[]{225, 255, 22}, new int[]{228, 255, 19}, new int[]{231, 255, 15}, new int[]{235, 255, 12}, new int[]{238, 255, 9}, new int[]{241, 252, 6}, new int[]{244, GateControllerMsg.ControlCode.Error, 2}, new int[]{GateControllerMsg.ControlCode.Error, 245, 0}, new int[]{251, 241, 0}, new int[]{254, 237, 0}, new int[]{255, 234, 0}, new int[]{255, 230, 0}, new int[]{255, 226, 0}, new int[]{255, 222, 0}, new int[]{255, 219, 0}, new int[]{255, 215, 0}, new int[]{255, Primes.SMALL_FACTOR_LIMIT, 0}, new int[]{255, 208, 0}, new int[]{255, 204, 0}, new int[]{255, 200, 0}, new int[]{255, 196, 0}, new int[]{255, 193, 0}, new int[]{255, 189, 0}, new int[]{255, 185, 0}, new int[]{255, 182, 0}, new int[]{255, 178, 0}, new int[]{255, 174, 0}, new int[]{255, 171, 0}, new int[]{255, 167, 0}, new int[]{255, 163, 0}, new int[]{255, 159, 0}, new int[]{255, 156, 0}, new int[]{255, 152, 0}, new int[]{255, 148, 0}, new int[]{255, 145, 0}, new int[]{255, 141, 0}, new int[]{255, 137, 0}, new int[]{255, 134, 0}, new int[]{255, 130, 0}, new int[]{255, 126, 0}, new int[]{255, 122, 0}, new int[]{255, 119, 0}, new int[]{255, 115, 0}, new int[]{255, 111, 0}, new int[]{255, 108, 0}, new int[]{255, 104, 0}, new int[]{255, 100, 0}, new int[]{255, 96, 0}, new int[]{255, 93, 0}, new int[]{255, 89, 0}, new int[]{255, 85, 0}, new int[]{255, 82, 0}, new int[]{255, 78, 0}, new int[]{255, 74, 0}, new int[]{255, 71, 0}, new int[]{255, 67, 0}, new int[]{255, 63, 0}, new int[]{255, 59, 0}, new int[]{255, 56, 0}, new int[]{255, 52, 0}, new int[]{255, 48, 0}, new int[]{255, 45, 0}, new int[]{255, 41, 0}, new int[]{255, 37, 0}, new int[]{255, 34, 0}, new int[]{255, 30, 0}, new int[]{255, 26, 0}, new int[]{255, 22, 0}, new int[]{255, 19, 0}, new int[]{ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 15, 0}, new int[]{246, 11, 0}, new int[]{241, 8, 0}, new int[]{237, 4, 0}, new int[]{232, 0, 0}, new int[]{228, 0, 0}, new int[]{223, 0, 0}, new int[]{218, 0, 0}, new int[]{214, 0, 0}, new int[]{209, 0, 0}, new int[]{HttpStatus.SC_RESET_CONTENT, 0, 0}, new int[]{200, 0, 0}, new int[]{196, 0, 0}, new int[]{191, 0, 0}, new int[]{187, 0, 0}, new int[]{182, 0, 0}, new int[]{178, 0, 0}, new int[]{173, 0, 0}, new int[]{168, 0, 0}, new int[]{164, 0, 0}, new int[]{159, 0, 0}, new int[]{155, 0, 0}, new int[]{150, 0, 0}, new int[]{146, 0, 0}, new int[]{141, 0, 0}, new int[]{137, 0, 0}, new int[]{132, 0, 0}};
        this.configState = ConfigState.NotConfigured;
        Bitmap createBitmap = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888);
        Intrinsics.checkExpressionValueIsNotNull(createBitmap, "Bitmap.createBitmap(widt…Bitmap.Config.ARGB_8888 )");
        this.depthBitmap = createBitmap;
        Bitmap createBitmap2 = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ALPHA_8);
        Intrinsics.checkExpressionValueIsNotNull(createBitmap2, "Bitmap.createBitmap(widt…, Bitmap.Config.ALPHA_8 )");
        this.irBitmap = createBitmap2;
        this.irView.setVisibility(8);
    }

    public final Context getContext() {
        return this.context;
    }

    public final ImageView getDepthView() {
        return this.depthView;
    }

    public final int getHeight() {
        return this.height;
    }

    public final ImageView getIrView() {
        return this.irView;
    }

    public final TextView getSerialView() {
        return this.serialView;
    }

    public final TextView getStateView() {
        return this.stateView;
    }

    public final int getWidth() {
        return this.width;
    }

    public final String getSerialNum() {
        return this.serialNum;
    }

    public final void setSerialNum(String serial) {
        Intrinsics.checkParameterIsNotNull(serial, "serial");
        this.serialNum = serial;
    }

    public final void setData(byte[] depthData, byte[] irData) {
        Intrinsics.checkParameterIsNotNull(depthData, "depthData");
        Intrinsics.checkParameterIsNotNull(irData, "irData");
        try {
            depthToColorBuffer(depthData);
            Bitmap bitmap = this.depthBitmap;
            if (bitmap == null) {
                Intrinsics.throwUninitializedPropertyAccessException("depthBitmap");
            }
            bitmap.copyPixelsFromBuffer(IntBuffer.wrap(this.colorBuffer).rewind());
            Bitmap bitmap2 = this.irBitmap;
            if (bitmap2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("irBitmap");
            }
            bitmap2.copyPixelsFromBuffer(ByteBuffer.wrap(irData).rewind());
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, "exctption: " + e.toString());
        }
    }

    public final void switchView() {
        this.showDepthView.set(!r0.get());
        this.depthView.setVisibility(this.showDepthView.get() ? 0 : 8);
        this.irView.setVisibility(this.showDepthView.get() ? 8 : 0);
    }

    public final void show() {
        if (this.showDepthView.get()) {
            ImageView imageView = this.depthView;
            Bitmap bitmap = this.depthBitmap;
            if (bitmap == null) {
                Intrinsics.throwUninitializedPropertyAccessException("depthBitmap");
            }
            imageView.setImageBitmap(bitmap);
        } else {
            ImageView imageView2 = this.irView;
            Bitmap bitmap2 = this.irBitmap;
            if (bitmap2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("irBitmap");
            }
            imageView2.setImageBitmap(bitmap2);
        }
        this.serialView.setText(this.serialNum);
        showState();
    }

    public final void setState(ConfigState state) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        this.configState = state;
    }

    /* renamed from: getState, reason: from getter */
    public final ConfigState getConfigState() {
        return this.configState;
    }

    private final void depthToColorBuffer(byte[] depthData) {
        int i = this.height;
        int i2 = this.width;
        ArrayList arrayList = new ArrayList();
        int i3 = 100;
        while (true) {
            arrayList.add(Integer.valueOf(((i3 - 100) * 255) / 7900));
            if (i3 == 8000) {
                break;
            } else {
                i3++;
            }
        }
        int i4 = 0;
        for (int i5 = 0; i5 < i; i5++) {
            for (int i6 = 0; i6 < i2; i6++) {
                ByteBuffer.wrap(new byte[]{1, 2, 3, 4}).getFloat();
                int i7 = ((i5 * i2) + i6) * 2;
                int m4528constructorimpl = ((UByte.m4528constructorimpl(depthData[i7]) & 255) + ((UByte.m4528constructorimpl(depthData[i7 + 1]) & 255) * 256)) - 100;
                if (m4528constructorimpl >= 0 && m4528constructorimpl < arrayList.size()) {
                    Object obj = arrayList.get(m4528constructorimpl);
                    Intrinsics.checkExpressionValueIsNotNull(obj, "idxTable[distIdx]");
                    int intValue = ((Number) obj).intValue();
                    int[][] iArr = this.colormap_;
                    this.colorBuffer[i4] = (-16777216) | ((iArr[intValue][0] & 255) << 16) | ((iArr[intValue][1] & 255) << 8) | (iArr[intValue][2] & 255);
                } else {
                    this.colorBuffer[i4] = -16777216;
                }
                i4++;
            }
        }
    }

    private final void showState() {
        String str;
        ConfigState configState = this.configState;
        ConfigState configState2 = ConfigState.NotConfigured;
        int i = ViewCompat.MEASURED_STATE_MASK;
        if (configState == configState2) {
            str = this.context.getResources().getString(2131558473);
            Intrinsics.checkExpressionValueIsNotNull(str, "context.resources.getStr…(R.string.not_configured)");
        } else if (this.configState == ConfigState.AutoDetect) {
            i = -16711936;
            str = this.context.getResources().getString(2131558432);
            Intrinsics.checkExpressionValueIsNotNull(str, "context.resources.getStr…tring.auto_detect_finish)");
        } else if (this.configState == ConfigState.AutoDetectError) {
            i = SupportMenu.CATEGORY_MASK;
            str = this.context.getResources().getString(2131558431);
            Intrinsics.checkExpressionValueIsNotNull(str, "context.resources.getStr….string.auto_detect_fail)");
        } else if (this.configState == ConfigState.Configured) {
            i = -16776961;
            str = this.context.getResources().getString(2131558441);
            Intrinsics.checkExpressionValueIsNotNull(str, "context.resources.getString(R.string.configured)");
        } else if (this.configState == ConfigState.ManualSwitch) {
            i = -65281;
            str = this.context.getResources().getString(2131558461);
            Intrinsics.checkExpressionValueIsNotNull(str, "context.resources.getStr…g(R.string.manual_switch)");
        } else {
            str = "";
        }
        this.stateView.setTextColor(i);
        this.stateView.setText(str);
    }
}
