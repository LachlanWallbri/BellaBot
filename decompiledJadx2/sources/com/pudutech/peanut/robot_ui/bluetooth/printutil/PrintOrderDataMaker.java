package com.pudutech.peanut.robot_ui.bluetooth.printutil;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.disinfect.baselib.util.QrCodeUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PrintOrderDataMaker.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ6\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0005H\u0016J$\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0007H\u0002R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/bluetooth/printutil/PrintOrderDataMaker;", "Lcom/pudutech/peanut/robot_ui/bluetooth/printutil/PrintDataMaker;", "btService", "Landroid/content/Context;", "qr", "", "width", "", "height", "(Landroid/content/Context;Ljava/lang/String;II)V", "getBtService", "()Landroid/content/Context;", "setBtService", "(Landroid/content/Context;)V", "getPrintData", "", "", "type", "name", "number", "shopName", "url", "scalingBitmap", "Landroid/graphics/Bitmap;", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_RES, "Landroid/content/res/Resources;", "id", "maxWidth", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class PrintOrderDataMaker implements PrintDataMaker {
    private Context btService;
    private final int height;
    private final String qr;
    private final int width;

    public PrintOrderDataMaker(Context btService, String qr, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(btService, "btService");
        Intrinsics.checkParameterIsNotNull(qr, "qr");
        this.btService = btService;
        this.qr = qr;
        this.width = i;
        this.height = i2;
    }

    public final Context getBtService() {
        return this.btService;
    }

    public final void setBtService(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        this.btService = context;
    }

    @Override // com.pudutech.peanut.robot_ui.bluetooth.printutil.PrintDataMaker
    public List<byte[]> getPrintData(int type, String name, String number, String shopName, String url) {
        PrinterWriter80mm printerWriter80mm;
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(number, "number");
        Intrinsics.checkParameterIsNotNull(shopName, "shopName");
        Intrinsics.checkParameterIsNotNull(url, "url");
        ArrayList arrayList = new ArrayList();
        try {
            if (type == 58) {
                printerWriter80mm = new PrinterWriter58mm(this.height, this.width);
            } else {
                printerWriter80mm = new PrinterWriter80mm(this.height, this.width);
            }
            printerWriter80mm.setAlignCenter();
            printerWriter80mm.setEmphasizedOn();
            printerWriter80mm.setFontSize(1);
            printerWriter80mm.print(shopName);
            printerWriter80mm.setEmphasizedOff();
            printerWriter80mm.printLineFeed();
            printerWriter80mm.printLineFeed();
            printerWriter80mm.setFontSize(0);
            printerWriter80mm.setAlignCenter();
            printerWriter80mm.print(new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date(System.currentTimeMillis())));
            printerWriter80mm.printLineFeed();
            printerWriter80mm.printLineFeed();
            printerWriter80mm.printLine();
            printerWriter80mm.printLineFeed();
            printerWriter80mm.printLineFeed();
            printerWriter80mm.setAlignCenter();
            printerWriter80mm.setEmphasizedOn();
            printerWriter80mm.setFontSize(2);
            printerWriter80mm.print(name);
            printerWriter80mm.setEmphasizedOff();
            printerWriter80mm.printLineFeed();
            printerWriter80mm.printLineFeed();
            printerWriter80mm.setAlignCenter();
            printerWriter80mm.setFontSize(0);
            printerWriter80mm.print("您前面还有 " + number + " 桌在等候");
            printerWriter80mm.print("听到叫号请到迎宾台，(餐厅规则)");
            printerWriter80mm.printLineFeed();
            printerWriter80mm.printLineFeed();
            printerWriter80mm.setAlignCenter();
            arrayList.add(printerWriter80mm.getDataAndReset());
            arrayList.addAll(printerWriter80mm.getImageByte(QrCodeUtils.createQrCode("http://nas.pudu.work:5000/", 382, 382)));
            printerWriter80mm.printLineFeed();
            printerWriter80mm.printLineFeed();
            printerWriter80mm.setFontSize(0);
            printerWriter80mm.print("扫描二维码，实时查询排队状态");
            printerWriter80mm.printLineFeed();
            printerWriter80mm.printLineFeed();
            printerWriter80mm.printLine();
            printerWriter80mm.printLineFeed();
            printerWriter80mm.printLineFeed();
            printerWriter80mm.setEmphasizedOn();
            printerWriter80mm.setFontSize(1);
            printerWriter80mm.setAlignCenter();
            printerWriter80mm.print("*普渡科技");
            printerWriter80mm.printLineFeed();
            printerWriter80mm.printLineFeed();
            printerWriter80mm.printLineFeed();
            printerWriter80mm.printLineFeed();
            printerWriter80mm.feedPaperCutPartial();
            arrayList.add(printerWriter80mm.getDataAndClose());
        } catch (Exception unused) {
            arrayList = new ArrayList();
        }
        return arrayList;
    }

    private final Bitmap scalingBitmap(Resources res, int id, int maxWidth) {
        int floor;
        if (res == null) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, id, options);
        options.inJustDecodeBounds = false;
        if (maxWidth > 0 && options.outWidth > maxWidth && (floor = (int) Math.floor(options.outWidth / maxWidth)) > 1) {
            options.inSampleSize = floor;
        }
        try {
            Bitmap image = BitmapFactory.decodeResource(res, id, options);
            Intrinsics.checkExpressionValueIsNotNull(image, "image");
            int width = image.getWidth();
            int height = image.getHeight();
            if (maxWidth > 0 && width > maxWidth) {
                float f = maxWidth / width;
                Matrix matrix = new Matrix();
                matrix.postScale(f, f);
                Bitmap createBitmap = Bitmap.createBitmap(image, 0, 0, width, height, matrix, true);
                image.recycle();
                return createBitmap;
            }
            return image;
        } catch (OutOfMemoryError unused) {
            return null;
        }
    }
}
