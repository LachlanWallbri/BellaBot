package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.iflytek.msc.MSC;
import com.pudutech.base.FileInfo;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import org.jetbrains.anko.DimensionsKt;

/* renamed from: com.iflytek.cloud.thirdparty.ar */
/* loaded from: classes3.dex */
public class C3706ar {

    /* renamed from: a */
    public static int f3074a;

    /* renamed from: b */
    public static int f3075b;

    /* renamed from: c */
    public static int f3076c;

    /* renamed from: d */
    public static int f3077d;

    /* renamed from: e */
    private static final String f3078e;

    /* renamed from: f */
    private static final String f3079f;

    /* renamed from: g */
    private static HashMap<String, Drawable> f3080g;

    /* renamed from: h */
    private static HashMap<String, Drawable> f3081h;

    static {
        f3078e = MSC.isIflyVersion() ? "iflytek/" : "cmcc/";
        f3079f = "assets/" + f3078e;
        f3080g = new HashMap<>();
        f3081h = new HashMap<>();
        f3074a = 3;
        f3075b = 4;
        f3076c = 7;
        f3077d = 8;
    }

    /* renamed from: a */
    public static int[] m1972a() {
        return new int[]{-1579033, -9933198};
    }

    /* renamed from: b */
    public static int[] m1974b() {
        return new int[]{20, 16};
    }

    /* renamed from: b */
    private static InputStream m1973b(Context context, String str) throws IOException {
        return context.getAssets().open(str);
    }

    /* renamed from: a */
    public static View m1971a(Context context, String str, ViewGroup viewGroup) throws Exception {
        return ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(context.getAssets().openXmlResourceParser(f3079f + str + ".xml"), viewGroup);
    }

    /* renamed from: a */
    public static synchronized Drawable m1970a(Context context, String str) throws Exception {
        Drawable drawable;
        synchronized (C3706ar.class) {
            drawable = f3080g.get(str);
            if (drawable == null) {
                drawable = m1975c(context, str);
                f3080g.put(str, drawable);
            }
        }
        return drawable;
    }

    /* renamed from: c */
    private static Drawable m1975c(Context context, String str) throws Exception {
        Drawable createFromResourceStream;
        InputStream m1973b = m1973b(context, f3078e + str + FileInfo.EXTEND_PNG);
        TypedValue typedValue = new TypedValue();
        typedValue.density = DimensionsKt.HDPI;
        if (Build.VERSION.SDK_INT > f3074a) {
            createFromResourceStream = C3707as.m1978a(context.getResources(), typedValue, m1973b, str, (BitmapFactory.Options) null);
        } else {
            createFromResourceStream = Drawable.createFromResourceStream(context.getResources(), typedValue, m1973b, str);
        }
        if (m1973b != null) {
            m1973b.close();
        }
        return createFromResourceStream;
    }
}
