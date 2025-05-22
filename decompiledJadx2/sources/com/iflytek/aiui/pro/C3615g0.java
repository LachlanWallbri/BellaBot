package com.iflytek.aiui.pro;

import android.content.Context;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.aiui.AIUISetting;
import com.iflytek.aiui.constant.InternalConstant;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.iflytek.aiui.pro.g0 */
/* loaded from: classes4.dex */
public class C3615g0 {
    /* renamed from: a */
    public static boolean m1343a(Context context, String str, String str2) {
        try {
            InputStream open = context.getAssets().open(str);
            File file = new File(str2);
            if (file.exists()) {
                return true;
            }
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = open.read(bArr);
                if (read == -1) {
                    open.close();
                    fileOutputStream.close();
                    return true;
                }
                fileOutputStream.write(bArr, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m1344b(Context context, String str, String str2) {
        try {
            String[] list = context.getAssets().list(str);
            if (list == null) {
                return false;
            }
            if (list.length == 0) {
                return m1343a(context, str, str2);
            }
            boolean mkdirs = new File(str2).mkdirs();
            for (String str3 : list) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                String str4 = File.separator;
                sb.append(str4);
                sb.append(str3);
                mkdirs &= m1344b(context, sb.toString(), str2 + str4 + str3);
            }
            return mkdirs;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: c */
    public static void m1345c(Context context) {
        char c;
        String format;
        String m1477f = C3633p0.m1477f(AIUIConstant.PARAM_SPEECH, AIUIConstant.KEY_WAKEUP_MODE, "off");
        m1477f.hashCode();
        int hashCode = m1477f.hashCode();
        if (hashCode == 98247) {
            if (m1477f.equals(InternalConstant.WAKEUP_MODE_CAE)) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 103411) {
            if (hashCode == 117104 && m1477f.equals("vtn")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (m1477f.equals("hlw")) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            m1344b(context, InternalConstant.WAKEUP_MODE_CAE, String.format("%s/assets/cae/", AIUISetting.getAIUIDir()));
            m1344b(context, "hlw", String.format("%s/assets/hlw/", AIUISetting.getAIUIDir()));
            format = String.format("%s/assets/vtn/", AIUISetting.getAIUIDir());
        } else if (c == 1) {
            m1344b(context, "hlw", String.format("%s/assets/hlw/", AIUISetting.getAIUIDir()));
            return;
        } else if (c != 2) {
            return;
        } else {
            format = String.format("%s/assets/vtn/", AIUISetting.getAIUIDir());
        }
        m1344b(context, "vtn", format);
    }
}
