package com.pudutech.rgbdlib.pointcloud.tangoutils.renderables;

import android.opengl.GLES20;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes5.dex */
public class RenderUtils {
    public static int loadShader(int i, String str) {
        int glCreateShader = GLES20.glCreateShader(i);
        GLES20.glShaderSource(glCreateShader, str);
        GLES20.glCompileShader(glCreateShader);
        return glCreateShader;
    }
}
