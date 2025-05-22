package com.iflytek.aiui.data.video;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.iflytek.aiui.pro.AbstractC3617h0;
import com.iflytek.aiui.pro.C3633p0;

/* loaded from: classes4.dex */
public class CameraCompat {
    public static AbstractC3554b createCamera(Context context, PreviewImpl previewImpl, AbstractC3617h0.a aVar, int i, String str) {
        String m1477f = TextUtils.isEmpty(str) ? C3633p0.m1477f("recorder", "cam_id", "") : "";
        if (!"2".equals(C3633p0.m1477f("recorder", "cam_api", i + "")) || Build.VERSION.SDK_INT < 21) {
            return new Camera1(previewImpl, context, aVar, TextUtils.isEmpty(m1477f) ? -1 : Integer.valueOf(m1477f).intValue());
        }
        return new Camera2(previewImpl, context, aVar, m1477f);
    }

    public static PreviewImpl createPreview(Context context, ViewGroup viewGroup) {
        return Build.VERSION.SDK_INT >= 23 ? new SurfaceViewPreview(context, viewGroup) : new TextureViewPreview(context, viewGroup);
    }
}
