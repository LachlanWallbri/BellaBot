package com.pudutech.lidar.util;

import android.util.Pair;
import com.pudutech.base.Pdlog;
import com.pudutech.lib_update.util.ShellUtils;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class Tools {
    private static String TAG = Tools.class.getName();

    public static Pair<Integer, String> execCommand(String str, boolean z) {
        String str2 = TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("execCommand:");
        sb.append(str);
        sb.append(z ? " with su" : " with sh");
        objArr[0] = sb.toString();
        Pdlog.m3273d(str2, objArr);
        String[] strArr = new String[3];
        strArr[0] = z ? ShellUtils.COMMAND_SU : ShellUtils.COMMAND_SH;
        strArr[1] = "-c";
        strArr[2] = str;
        return rootCommand(strArr.toString());
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

    public static Pair<Integer, String> rootCommand(String str) {
        try {
            Process exec = Runtime.getRuntime().exec(ShellUtils.COMMAND_SU);
            exec.getOutputStream().write((str + "\n").getBytes());
            DataOutputStream dataOutputStream = new DataOutputStream(exec.getOutputStream());
            dataOutputStream.writeBytes(str + "\n");
            dataOutputStream.writeBytes(ShellUtils.COMMAND_EXIT);
            dataOutputStream.flush();
            exec.waitFor();
            InputStream inputStream = exec.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
                sb.append("\n");
            }
            inputStream.close();
            int exitValue = exec.exitValue();
            Pdlog.m3273d(TAG, "su exit value = " + exitValue + " " + sb.toString());
            return Pair.create(Integer.valueOf(exitValue), sb.toString());
        } catch (Exception e) {
            Pdlog.m3277w(TAG, e);
            return Pair.create(1, null);
        }
    }
}
