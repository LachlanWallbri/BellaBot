package com.pudutech.aiuicae;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes.dex */
public class AIUIConfig {
    public String readAssetFile(Context context, String str) {
        try {
            return readStringFromStream(context.getAssets().open(str));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String readStringFromStream(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read > 0) {
                sb.append(new String(bArr, 0, read));
            } else {
                return sb.toString();
            }
        }
    }
}
