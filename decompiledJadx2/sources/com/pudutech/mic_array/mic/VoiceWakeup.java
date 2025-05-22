package com.pudutech.mic_array.mic;

import com.iflytek.aiui.AIUIConstant;
import com.pudutech.mic_array.util.LogUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import org.apache.commons.io.IOUtils;

/* loaded from: classes5.dex */
public class VoiceWakeup {
    private static String TAG = VoiceWakeup.class.getSimpleName();
    private String line = null;
    private String special = AIUIConstant.KEY_RES_PATH;

    public boolean replaceWakeup(String str, String str2) {
        File file = new File(str);
        if (file.exists()) {
            LogUtils.m3290d(TAG, "Right" + str);
            try {
                StringBuffer stringBuffer = new StringBuffer();
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    this.line = readLine;
                    if (readLine == null) {
                        break;
                    }
                    this.line = this.line.trim();
                    if (!this.line.startsWith(this.special)) {
                        stringBuffer.append(this.line);
                        stringBuffer.append(IOUtils.LINE_SEPARATOR_WINDOWS);
                    }
                }
                bufferedReader.close();
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                bufferedWriter.write(stringBuffer.toString());
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(str, true));
                bufferedWriter2.write(str2);
                bufferedWriter2.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return true;
        }
        LogUtils.m3290d(TAG, "File Error:" + str);
        return false;
    }
}
