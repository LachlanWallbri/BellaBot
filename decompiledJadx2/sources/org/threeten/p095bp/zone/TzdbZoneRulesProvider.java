package org.threeten.p095bp.zone;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StreamCorruptedException;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicReferenceArray;
import org.threeten.p095bp.jdk8.Jdk8Methods;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class TzdbZoneRulesProvider extends ZoneRulesProvider {
    private List<String> regionIds;
    private final ConcurrentNavigableMap<String, Version> versions = new ConcurrentSkipListMap();
    private Set<String> loadedUrls = new CopyOnWriteArraySet();

    public String toString() {
        return "TZDB";
    }

    public TzdbZoneRulesProvider() {
        if (!load(ZoneRulesProvider.class.getClassLoader())) {
            throw new ZoneRulesException("No time-zone rules found for 'TZDB'");
        }
    }

    public TzdbZoneRulesProvider(URL url) {
        try {
            if (load(url)) {
                return;
            }
            throw new ZoneRulesException("No time-zone rules found: " + url);
        } catch (Exception e) {
            throw new ZoneRulesException("Unable to load TZDB time-zone rules: " + url, e);
        }
    }

    public TzdbZoneRulesProvider(InputStream inputStream) {
        try {
            load(inputStream);
        } catch (Exception e) {
            throw new ZoneRulesException("Unable to load TZDB time-zone rules", e);
        }
    }

    @Override // org.threeten.p095bp.zone.ZoneRulesProvider
    protected Set<String> provideZoneIds() {
        return new HashSet(this.regionIds);
    }

    @Override // org.threeten.p095bp.zone.ZoneRulesProvider
    protected ZoneRules provideRules(String str, boolean z) {
        Jdk8Methods.requireNonNull(str, "zoneId");
        ZoneRules rules = this.versions.lastEntry().getValue().getRules(str);
        if (rules != null) {
            return rules;
        }
        throw new ZoneRulesException("Unknown time-zone ID: " + str);
    }

    @Override // org.threeten.p095bp.zone.ZoneRulesProvider
    protected NavigableMap<String, ZoneRules> provideVersions(String str) {
        TreeMap treeMap = new TreeMap();
        for (Version version : this.versions.values()) {
            ZoneRules rules = version.getRules(str);
            if (rules != null) {
                treeMap.put(version.versionId, rules);
            }
        }
        return treeMap;
    }

    private boolean load(ClassLoader classLoader) {
        URL url = null;
        try {
            Enumeration<URL> resources = classLoader.getResources("org/threeten/bp/TZDB.dat");
            boolean z = false;
            while (resources.hasMoreElements()) {
                URL nextElement = resources.nextElement();
                try {
                    z |= load(nextElement);
                    url = nextElement;
                } catch (Exception e) {
                    e = e;
                    url = nextElement;
                    throw new ZoneRulesException("Unable to load TZDB time-zone rules: " + url, e);
                }
            }
            return z;
        } catch (Exception e2) {
            e = e2;
        }
    }

    private boolean load(URL url) throws ClassNotFoundException, IOException, ZoneRulesException {
        boolean z = false;
        if (this.loadedUrls.add(url.toExternalForm())) {
            InputStream inputStream = null;
            try {
                inputStream = url.openStream();
                z = false | load(inputStream);
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
        }
        return z;
    }

    private boolean load(InputStream inputStream) throws IOException, StreamCorruptedException {
        boolean z = false;
        for (Version version : loadData(inputStream)) {
            Version putIfAbsent = this.versions.putIfAbsent(version.versionId, version);
            if (putIfAbsent != null && !putIfAbsent.versionId.equals(version.versionId)) {
                throw new ZoneRulesException("Data already loaded for TZDB time-zone rules version: " + version.versionId);
            }
            z = true;
        }
        return z;
    }

    private Iterable<Version> loadData(InputStream inputStream) throws IOException, StreamCorruptedException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        if (dataInputStream.readByte() != 1) {
            throw new StreamCorruptedException("File format not recognised");
        }
        if (!"TZDB".equals(dataInputStream.readUTF())) {
            throw new StreamCorruptedException("File format not recognised");
        }
        int readShort = dataInputStream.readShort();
        String[] strArr = new String[readShort];
        for (int i = 0; i < readShort; i++) {
            strArr[i] = dataInputStream.readUTF();
        }
        int readShort2 = dataInputStream.readShort();
        String[] strArr2 = new String[readShort2];
        for (int i2 = 0; i2 < readShort2; i2++) {
            strArr2[i2] = dataInputStream.readUTF();
        }
        this.regionIds = Arrays.asList(strArr2);
        int readShort3 = dataInputStream.readShort();
        Object[] objArr = new Object[readShort3];
        for (int i3 = 0; i3 < readShort3; i3++) {
            byte[] bArr = new byte[dataInputStream.readShort()];
            dataInputStream.readFully(bArr);
            objArr[i3] = bArr;
        }
        AtomicReferenceArray atomicReferenceArray = new AtomicReferenceArray(objArr);
        HashSet hashSet = new HashSet(readShort);
        for (int i4 = 0; i4 < readShort; i4++) {
            int readShort4 = dataInputStream.readShort();
            String[] strArr3 = new String[readShort4];
            short[] sArr = new short[readShort4];
            for (int i5 = 0; i5 < readShort4; i5++) {
                strArr3[i5] = strArr2[dataInputStream.readShort()];
                sArr[i5] = dataInputStream.readShort();
            }
            hashSet.add(new Version(strArr[i4], strArr3, sArr, atomicReferenceArray));
        }
        return hashSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public static class Version {
        private final String[] regionArray;
        private final AtomicReferenceArray<Object> ruleData;
        private final short[] ruleIndices;
        private final String versionId;

        Version(String str, String[] strArr, short[] sArr, AtomicReferenceArray<Object> atomicReferenceArray) {
            this.ruleData = atomicReferenceArray;
            this.versionId = str;
            this.regionArray = strArr;
            this.ruleIndices = sArr;
        }

        ZoneRules getRules(String str) {
            int binarySearch = Arrays.binarySearch(this.regionArray, str);
            if (binarySearch < 0) {
                return null;
            }
            try {
                return createRule(this.ruleIndices[binarySearch]);
            } catch (Exception e) {
                throw new ZoneRulesException("Invalid binary time-zone data: TZDB:" + str + ", version: " + this.versionId, e);
            }
        }

        ZoneRules createRule(short s) throws Exception {
            Object obj = this.ruleData.get(s);
            if (obj instanceof byte[]) {
                obj = Ser.read(new DataInputStream(new ByteArrayInputStream((byte[]) obj)));
                this.ruleData.set(s, obj);
            }
            return (ZoneRules) obj;
        }

        public String toString() {
            return this.versionId;
        }
    }
}
