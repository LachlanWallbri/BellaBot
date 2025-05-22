package com.pudutech.peanut.robot_ui.bluetooth;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import com.pudutech.peanut.robot_ui.bluetooth.print.GPrinterCommand;
import com.pudutech.peanut.robot_ui.bluetooth.print.PrintPic;
import com.pudutech.peanut.robot_ui.bluetooth.print.PrintQueue;
import com.pudutech.peanut.robot_ui.bluetooth.print.PrintUtil;
import com.pudutech.peanut.robot_ui.bluetooth.printutil.PrintOrderDataMaker;
import java.io.BufferedInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class BtService extends IntentService {
    private String mUrl;
    private String shopName;
    private String tableName;
    private String tableNumber;

    public BtService() {
        super("BtService");
    }

    public BtService(String str) {
        super(str);
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return;
        }
        if (intent.getAction().equals(PrintUtil.ACTION_PRINT_TEST)) {
            this.tableNumber = intent.getStringExtra(PrintUtil.TABLE_NUMBER);
            this.tableName = intent.getStringExtra(PrintUtil.TABLE_NAME);
            this.shopName = intent.getStringExtra(PrintUtil.SHOP_NAME);
            this.mUrl = intent.getStringExtra(PrintUtil.SHOP_URL);
            printTest();
            return;
        }
        if (intent.getAction().equals(PrintUtil.ACTION_PRINT_TEST_TWO)) {
            printTesttwo(3);
        } else if (intent.getAction().equals(PrintUtil.ACTION_PRINT_BITMAP)) {
            printBitmapTest();
        }
    }

    private void printTest() {
        PrintQueue.getQueue(getApplicationContext()).add((ArrayList<byte[]>) new PrintOrderDataMaker(this, "", 58, 255).getPrintData(58, this.tableName, this.tableNumber, this.shopName, this.mUrl));
    }

    private void printTesttwo(int i) {
        try {
            ArrayList<byte[]> arrayList = new ArrayList<>();
            for (int i2 = 0; i2 < i; i2++) {
                arrayList.add(GPrinterCommand.reset);
                arrayList.add("蓝牙打印测试\n蓝牙打印测试\n蓝牙打印测试\n\n".getBytes("gbk"));
                arrayList.add(GPrinterCommand.print);
                arrayList.add(GPrinterCommand.print);
                arrayList.add(GPrinterCommand.print);
            }
            PrintQueue.getQueue(getApplicationContext()).add(arrayList);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void print(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return;
        }
        PrintQueue.getQueue(getApplicationContext()).add(bArr);
    }

    private void printBitmapTest() {
        try {
            Bitmap decodeStream = BitmapFactory.decodeStream(new BufferedInputStream(getAssets().open("icon_empty_bg.bmp")));
            PrintPic printPic = PrintPic.getInstance();
            printPic.init(decodeStream);
            if (decodeStream != null && !decodeStream.isRecycled()) {
                decodeStream.recycle();
            }
            byte[] printDraw = printPic.printDraw();
            ArrayList arrayList = new ArrayList();
            arrayList.add(GPrinterCommand.reset);
            arrayList.add(GPrinterCommand.print);
            arrayList.add(printDraw);
            Log.e("BtService", "image bytes size is :" + printDraw.length);
            arrayList.add(GPrinterCommand.print);
            PrintQueue.getQueue(getApplicationContext()).add(printDraw);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
