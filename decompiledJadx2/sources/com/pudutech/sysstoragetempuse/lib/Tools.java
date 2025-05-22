package com.pudutech.sysstoragetempuse.lib;

import android.util.Log;
import android.util.Pair;
import com.pudutech.lib_update.util.ShellUtils;
import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/* loaded from: classes7.dex */
public class Tools {
    private static String TAG = Tools.class.getName();

    public static Pair<Integer, String> execCommand(String str, boolean z) {
        String str2 = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("execCommand:");
        sb.append(str);
        sb.append(z ? " with su" : " with sh");
        Log.d(str2, sb.toString());
        String[] strArr = new String[3];
        strArr[0] = z ? ShellUtils.COMMAND_SU : ShellUtils.COMMAND_SH;
        strArr[1] = "-c";
        strArr[2] = str;
        try {
            Process exec = Runtime.getRuntime().exec(strArr);
            waitFor(30L, TimeUnit.SECONDS, exec);
            InputStream inputStream = exec.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            StringBuilder sb2 = new StringBuilder();
            while (scanner.hasNextLine()) {
                sb2.append(scanner.nextLine());
                sb2.append("\n");
            }
            inputStream.close();
            int exitValue = exec.exitValue();
            Log.d(TAG, "su exit value = " + exitValue + " " + sb2.toString());
            return Pair.create(Integer.valueOf(exitValue), sb2.toString());
        } catch (Throwable th) {
            Log.w(TAG, th);
            return Pair.create(1, null);
        }
    }

    public static boolean waitFor(long j, TimeUnit timeUnit, Process process) throws InterruptedException {
        long nanoTime = System.nanoTime();
        long nanos = timeUnit.toNanos(j);
        do {
            try {
                process.exitValue();
                return true;
            } catch (IllegalThreadStateException unused) {
                if (nanos > 0) {
                    Thread.sleep(Math.min(TimeUnit.NANOSECONDS.toMillis(nanos) + 1, 100L));
                }
                nanos = timeUnit.toNanos(j) - (System.nanoTime() - nanoTime);
            }
        } while (nanos > 0);
        return false;
    }
}
