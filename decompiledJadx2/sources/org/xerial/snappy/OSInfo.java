package org.xerial.snappy;

import androidx.core.os.EnvironmentCompat;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: classes9.dex */
public class OSInfo {
    public static final String AARCH_64 = "aarch64";
    public static final String IA64 = "ia64";
    public static final String IA64_32 = "ia64_32";
    public static final String IBMZ = "s390";
    public static final String IBMZ_64 = "s390x";
    public static final String PPC = "ppc";
    public static final String PPC64 = "ppc64";
    public static final String X86 = "x86";
    public static final String X86_64 = "x86_64";
    private static HashMap<String, String> archMapping = new HashMap<>();

    static {
        archMapping.put(X86, X86);
        archMapping.put("i386", X86);
        archMapping.put("i486", X86);
        archMapping.put("i586", X86);
        archMapping.put("i686", X86);
        archMapping.put("pentium", X86);
        archMapping.put(X86_64, X86_64);
        archMapping.put("amd64", X86_64);
        archMapping.put("em64t", X86_64);
        archMapping.put("universal", X86_64);
        archMapping.put(IA64, IA64);
        archMapping.put("ia64w", IA64);
        archMapping.put(IA64_32, IA64_32);
        archMapping.put("ia64n", IA64_32);
        archMapping.put(PPC, PPC);
        archMapping.put("power", PPC);
        archMapping.put("powerpc", PPC);
        archMapping.put("power_pc", PPC);
        archMapping.put("power_rs", PPC);
        archMapping.put(PPC64, PPC64);
        archMapping.put("power64", PPC64);
        archMapping.put("powerpc64", PPC64);
        archMapping.put("power_pc64", PPC64);
        archMapping.put("power_rs64", PPC64);
        archMapping.put(IBMZ, IBMZ);
        archMapping.put(IBMZ_64, IBMZ_64);
        archMapping.put(AARCH_64, AARCH_64);
    }

    public static void main(String[] strArr) {
        if (strArr.length >= 1) {
            if ("--os".equals(strArr[0])) {
                System.out.print(getOSName());
                return;
            } else if ("--arch".equals(strArr[0])) {
                System.out.print(getArchName());
                return;
            }
        }
        System.out.print(getNativeLibFolderPathForCurrentOS());
    }

    public static String getNativeLibFolderPathForCurrentOS() {
        return getOSName() + "/" + getArchName();
    }

    public static String getOSName() {
        return translateOSNameToFolderName(System.getProperty("os.name"));
    }

    public static boolean isAndroid() {
        return System.getProperty("java.runtime.name", "").toLowerCase().contains("android");
    }

    static String getHardwareName() {
        try {
            Process exec = Runtime.getRuntime().exec("uname -m");
            exec.waitFor();
            InputStream inputStream = exec.getInputStream();
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[32];
                while (true) {
                    int read = inputStream.read(bArr, 0, bArr.length);
                    if (read < 0) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                return byteArrayOutputStream.toString();
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
        } catch (Throwable th) {
            System.err.println("Error while running uname -m: " + th.getMessage());
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
    }

    static String resolveArmArchType() {
        if (!System.getProperty("os.name").contains("Linux")) {
            return "arm";
        }
        String hardwareName = getHardwareName();
        if (hardwareName.startsWith("armv6")) {
            return "armv6";
        }
        if (hardwareName.startsWith("armv7")) {
            return "armv7";
        }
        String property = System.getProperty("sun.arch.abi");
        if (property != null && property.startsWith("gnueabihf")) {
            return "armv7";
        }
        try {
            if (Runtime.getRuntime().exec("which readelf").waitFor() == 0) {
                if (Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "find '" + System.getProperty("java.home") + "' -name 'libjvm.so' | head -1 | xargs readelf -A | grep 'Tag_ABI_VFP_args: VFP registers'"}).waitFor() == 0) {
                    return "armv7";
                }
            } else {
                System.err.println("WARNING! readelf not found. Cannot check if running on an armhf system, armel architecture will be presumed.");
            }
            return "arm";
        } catch (IOException | InterruptedException unused) {
            return "arm";
        }
    }

    public static String getArchName() {
        String property = System.getProperty("os.arch");
        if (isAndroid()) {
            return "android-arm";
        }
        if (property.startsWith("arm")) {
            property = resolveArmArchType();
        } else {
            String lowerCase = property.toLowerCase(Locale.US);
            if (archMapping.containsKey(lowerCase)) {
                return archMapping.get(lowerCase);
            }
        }
        return translateArchNameToFolderName(property);
    }

    static String translateOSNameToFolderName(String str) {
        return str.contains("Windows") ? "Windows" : str.contains("Mac") ? "Mac" : str.contains("Linux") ? "Linux" : str.contains("AIX") ? "AIX" : str.replaceAll("\\W", "");
    }

    static String translateArchNameToFolderName(String str) {
        return str.replaceAll("\\W", "");
    }
}
