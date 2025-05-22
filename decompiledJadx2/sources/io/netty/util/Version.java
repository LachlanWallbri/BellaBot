package io.netty.util;

import io.netty.util.internal.PlatformDependent;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import org.apache.commons.codec.language.Soundex;
import org.apache.commons.io.FilenameUtils;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class Version {
    private static final String PROP_BUILD_DATE = ".buildDate";
    private static final String PROP_COMMIT_DATE = ".commitDate";
    private static final String PROP_LONG_COMMIT_HASH = ".longCommitHash";
    private static final String PROP_REPO_STATUS = ".repoStatus";
    private static final String PROP_SHORT_COMMIT_HASH = ".shortCommitHash";
    private static final String PROP_VERSION = ".version";
    private final String artifactId;
    private final String artifactVersion;
    private final long buildTimeMillis;
    private final long commitTimeMillis;
    private final String longCommitHash;
    private final String repositoryStatus;
    private final String shortCommitHash;

    public static Map<String, Version> identify() {
        return identify(null);
    }

    public static Map<String, Version> identify(ClassLoader classLoader) {
        ClassLoader contextClassLoader = classLoader == null ? PlatformDependent.getContextClassLoader() : classLoader;
        Properties properties = new Properties();
        try {
            Enumeration<URL> resources = contextClassLoader.getResources("META-INF/io.netty.versions.properties");
            while (resources.hasMoreElements()) {
                InputStream openStream = resources.nextElement().openStream();
                try {
                    properties.load(openStream);
                    try {
                        openStream.close();
                    } catch (Exception unused) {
                    }
                } catch (Throwable th) {
                    try {
                        openStream.close();
                        throw th;
                    } catch (Exception unused2) {
                        throw th;
                    }
                }
            }
        } catch (Exception unused3) {
        }
        HashSet hashSet = new HashSet();
        for (String str : properties.keySet()) {
            int indexOf = str.indexOf(46);
            if (indexOf > 0) {
                String substring = str.substring(0, indexOf);
                if (properties.containsKey(substring + PROP_VERSION)) {
                    if (properties.containsKey(substring + PROP_BUILD_DATE)) {
                        if (properties.containsKey(substring + PROP_COMMIT_DATE)) {
                            if (properties.containsKey(substring + PROP_SHORT_COMMIT_HASH)) {
                                if (properties.containsKey(substring + PROP_LONG_COMMIT_HASH)) {
                                    if (properties.containsKey(substring + PROP_REPO_STATUS)) {
                                        hashSet.add(substring);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        TreeMap treeMap = new TreeMap();
        for (Iterator it = hashSet.iterator(); it.hasNext(); it = it) {
            String str2 = (String) it.next();
            treeMap.put(str2, new Version(str2, properties.getProperty(str2 + PROP_VERSION), parseIso8601(properties.getProperty(str2 + PROP_BUILD_DATE)), parseIso8601(properties.getProperty(str2 + PROP_COMMIT_DATE)), properties.getProperty(str2 + PROP_SHORT_COMMIT_HASH), properties.getProperty(str2 + PROP_LONG_COMMIT_HASH), properties.getProperty(str2 + PROP_REPO_STATUS)));
        }
        return treeMap;
    }

    private static long parseIso8601(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z").parse(str).getTime();
        } catch (ParseException unused) {
            return 0L;
        }
    }

    public static void main(String[] strArr) {
        Iterator<Version> it = identify().values().iterator();
        while (it.hasNext()) {
            System.err.println(it.next());
        }
    }

    private Version(String str, String str2, long j, long j2, String str3, String str4, String str5) {
        this.artifactId = str;
        this.artifactVersion = str2;
        this.buildTimeMillis = j;
        this.commitTimeMillis = j2;
        this.shortCommitHash = str3;
        this.longCommitHash = str4;
        this.repositoryStatus = str5;
    }

    public String artifactId() {
        return this.artifactId;
    }

    public String artifactVersion() {
        return this.artifactVersion;
    }

    public long buildTimeMillis() {
        return this.buildTimeMillis;
    }

    public long commitTimeMillis() {
        return this.commitTimeMillis;
    }

    public String shortCommitHash() {
        return this.shortCommitHash;
    }

    public String longCommitHash() {
        return this.longCommitHash;
    }

    public String repositoryStatus() {
        return this.repositoryStatus;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(this.artifactId);
        sb.append(Soundex.SILENT_MARKER);
        sb.append(this.artifactVersion);
        sb.append(FilenameUtils.EXTENSION_SEPARATOR);
        sb.append(this.shortCommitHash);
        if ("clean".equals(this.repositoryStatus)) {
            str = "";
        } else {
            str = " (repository: " + this.repositoryStatus + ')';
        }
        sb.append(str);
        return sb.toString();
    }
}
