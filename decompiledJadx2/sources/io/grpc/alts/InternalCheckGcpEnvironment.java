package io.grpc.alts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class InternalCheckGcpEnvironment {
    private static final String WINDOWS_COMMAND = "powershell.exe";
    private static final Logger logger = Logger.getLogger(InternalCheckGcpEnvironment.class.getName());
    private static Boolean cachedResult = null;

    private InternalCheckGcpEnvironment() {
    }

    public static synchronized boolean isOnGcp() {
        boolean booleanValue;
        synchronized (InternalCheckGcpEnvironment.class) {
            if (cachedResult == null) {
                cachedResult = Boolean.valueOf(isRunningOnGcp());
            }
            booleanValue = cachedResult.booleanValue();
        }
        return booleanValue;
    }

    static boolean checkProductNameOnLinux(BufferedReader bufferedReader) throws IOException {
        String trim = bufferedReader.readLine().trim();
        return trim.equals("Google") || trim.equals("Google Compute Engine");
    }

    static boolean checkBiosDataOnWindows(BufferedReader bufferedReader) throws IOException {
        String readLine;
        do {
            readLine = bufferedReader.readLine();
            if (readLine == null) {
                return false;
            }
        } while (!readLine.startsWith("Manufacturer"));
        return readLine.substring(readLine.indexOf(58) + 1).trim().equals("Google");
    }

    private static boolean isRunningOnGcp() {
        String lowerCase = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);
        try {
            if (lowerCase.startsWith("linux")) {
                return checkProductNameOnLinux(Files.newBufferedReader(Paths.get("/sys/class/dmi/id/product_name", new String[0]), StandardCharsets.UTF_8));
            }
            if (lowerCase.startsWith("windows")) {
                return checkBiosDataOnWindows(new BufferedReader(new InputStreamReader(new ProcessBuilder(new String[0]).command(WINDOWS_COMMAND, "Get-WmiObject", "-Class", "Win32_BIOS").start().getInputStream(), StandardCharsets.UTF_8)));
            }
            return false;
        } catch (IOException e) {
            logger.log(Level.WARNING, "Fail to read platform information: ", (Throwable) e);
            return false;
        }
    }
}
