package org.conscrypt;

import java.io.File;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xerial.snappy.OSInfo;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
class HostProperties {
    private static final String TEMP_DIR_PROPERTY_NAME = "org.conscrypt.tmpdir";
    private static final Logger logger = Logger.getLogger(HostProperties.class.getName());

    /* renamed from: OS */
    static final OperatingSystem f9987OS = getOperatingSystem(System.getProperty("os.name", ""));
    static final Architecture ARCH = getArchitecture(System.getProperty("os.arch", ""));

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    public enum OperatingSystem {
        AIX,
        HPUX,
        OS400,
        LINUX,
        OSX,
        FREEBSD,
        OPENBSD,
        NETBSD,
        SUNOS,
        WINDOWS,
        UNKNOWN;

        public String getFileComponent() {
            return name().toLowerCase();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    public enum Architecture {
        X86_64,
        X86_32 { // from class: org.conscrypt.HostProperties.Architecture.1
            @Override // org.conscrypt.HostProperties.Architecture
            public String getFileComponent() {
                return OSInfo.X86;
            }
        },
        ITANIUM_64,
        SPARC_32,
        SPARC_64,
        ARM_32,
        AARCH_64,
        PPC_32,
        PPC_64,
        PPCLE_64,
        S390_32,
        S390_64,
        UNKNOWN;

        public String getFileComponent() {
            return name().toLowerCase();
        }
    }

    static boolean isWindows() {
        return f9987OS == OperatingSystem.WINDOWS;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isOSX() {
        return f9987OS == OperatingSystem.OSX;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static File getTempDir() {
        File file;
        File directory;
        try {
            directory = toDirectory(System.getProperty(TEMP_DIR_PROPERTY_NAME));
        } catch (Exception unused) {
        }
        if (directory != null) {
            return directory;
        }
        File directory2 = toDirectory(System.getProperty("java.io.tmpdir"));
        if (directory2 != null) {
            return directory2;
        }
        if (isWindows()) {
            File directory3 = toDirectory(System.getenv("TEMP"));
            if (directory3 != null) {
                return directory3;
            }
            String str = System.getenv("USERPROFILE");
            if (str != null) {
                File directory4 = toDirectory(str + "\\AppData\\Local\\Temp");
                if (directory4 != null) {
                    return directory4;
                }
                File directory5 = toDirectory(str + "\\Local Settings\\Temp");
                if (directory5 != null) {
                    return directory5;
                }
            }
        } else {
            File directory6 = toDirectory(System.getenv("TMPDIR"));
            if (directory6 != null) {
                return directory6;
            }
        }
        if (isWindows()) {
            file = new File("C:\\Windows\\Temp");
        } else {
            file = new File("/tmp");
        }
        logger.log(Level.WARNING, "Failed to get the temporary directory; falling back to: {0}", file);
        return file;
    }

    private static File toDirectory(String str) {
        if (str == null) {
            return null;
        }
        File file = new File(str);
        file.mkdirs();
        if (!file.isDirectory()) {
            return null;
        }
        try {
            return file.getAbsoluteFile();
        } catch (Exception unused) {
            return file;
        }
    }

    private static String normalize(String str) {
        return str.toLowerCase(Locale.US).replaceAll("[^a-z0-9]+", "");
    }

    private static OperatingSystem getOperatingSystem(String str) {
        String normalize = normalize(str);
        if (normalize.startsWith("aix")) {
            return OperatingSystem.AIX;
        }
        if (normalize.startsWith("hpux")) {
            return OperatingSystem.HPUX;
        }
        if (normalize.startsWith("os400") && (normalize.length() <= 5 || !Character.isDigit(normalize.charAt(5)))) {
            return OperatingSystem.OS400;
        }
        if (normalize.startsWith("linux")) {
            return OperatingSystem.LINUX;
        }
        if (normalize.startsWith("macosx") || normalize.startsWith("osx")) {
            return OperatingSystem.OSX;
        }
        if (normalize.startsWith("freebsd")) {
            return OperatingSystem.FREEBSD;
        }
        if (normalize.startsWith("openbsd")) {
            return OperatingSystem.OPENBSD;
        }
        if (normalize.startsWith("netbsd")) {
            return OperatingSystem.NETBSD;
        }
        if (normalize.startsWith("solaris") || normalize.startsWith("sunos")) {
            return OperatingSystem.SUNOS;
        }
        if (normalize.startsWith("windows")) {
            return OperatingSystem.WINDOWS;
        }
        return OperatingSystem.UNKNOWN;
    }

    private static Architecture getArchitecture(String str) {
        String normalize = normalize(str);
        if (normalize.matches("^(x8664|amd64|ia32e|em64t|x64)$")) {
            return Architecture.X86_64;
        }
        if (normalize.matches("^(x8632|x86|i[3-6]86|ia32|x32)$")) {
            return Architecture.X86_32;
        }
        if (normalize.matches("^(ia64|itanium64)$")) {
            return Architecture.ITANIUM_64;
        }
        if (normalize.matches("^(sparc|sparc32)$")) {
            return Architecture.SPARC_32;
        }
        if (normalize.matches("^(sparcv9|sparc64)$")) {
            return Architecture.SPARC_64;
        }
        if (normalize.matches("^(arm|arm32)$")) {
            return Architecture.ARM_32;
        }
        if (OSInfo.AARCH_64.equals(normalize)) {
            return Architecture.AARCH_64;
        }
        if (normalize.matches("^(ppc|ppc32)$")) {
            return Architecture.PPC_32;
        }
        if (OSInfo.PPC64.equals(normalize)) {
            return Architecture.PPC_64;
        }
        if ("ppc64le".equals(normalize)) {
            return Architecture.PPCLE_64;
        }
        if (OSInfo.IBMZ.equals(normalize)) {
            return Architecture.S390_32;
        }
        if (OSInfo.IBMZ_64.equals(normalize)) {
            return Architecture.S390_64;
        }
        return Architecture.UNKNOWN;
    }

    private HostProperties() {
    }
}
