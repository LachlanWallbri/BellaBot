package io.netty.resolver.dns;

import io.netty.util.NetUtil;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.SocketUtils;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class UnixResolverDnsServerAddressStreamProvider implements DnsServerAddressStreamProvider {
    static final int DEFAULT_NDOTS = 1;
    private static final String DOMAIN_ROW_LABEL = "domain";
    private static final String ETC_RESOLVER_DIR = "/etc/resolver";
    private static final String ETC_RESOLV_CONF_FILE = "/etc/resolv.conf";
    private static final String NAMESERVER_ROW_LABEL = "nameserver";
    private static final String NDOTS_LABEL = "ndots:";
    private static final String OPTIONS_ROW_LABEL = "options";
    private static final String PORT_ROW_LABEL = "port";
    private static final String SORTLIST_ROW_LABEL = "sortlist";
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) UnixResolverDnsServerAddressStreamProvider.class);
    private final DnsServerAddresses defaultNameServerAddresses;
    private final Map<String, DnsServerAddresses> domainToNameServerStreamMap;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DnsServerAddressStreamProvider parseSilently() {
        try {
            UnixResolverDnsServerAddressStreamProvider unixResolverDnsServerAddressStreamProvider = new UnixResolverDnsServerAddressStreamProvider(ETC_RESOLV_CONF_FILE, ETC_RESOLVER_DIR);
            return unixResolverDnsServerAddressStreamProvider.mayOverrideNameServers() ? unixResolverDnsServerAddressStreamProvider : DefaultDnsServerAddressStreamProvider.INSTANCE;
        } catch (Exception e) {
            logger.debug("failed to parse {} and/or {}", ETC_RESOLV_CONF_FILE, ETC_RESOLVER_DIR, e);
            return DefaultDnsServerAddressStreamProvider.INSTANCE;
        }
    }

    public UnixResolverDnsServerAddressStreamProvider(File file, File... fileArr) throws IOException {
        Map<String, DnsServerAddresses> parse = parse((File) ObjectUtil.checkNotNull(file, "etcResolvConf"));
        boolean z = (fileArr == null || fileArr.length == 0) ? false : true;
        this.domainToNameServerStreamMap = z ? parse(fileArr) : parse;
        DnsServerAddresses dnsServerAddresses = parse.get(file.getName());
        if (dnsServerAddresses == null) {
            Collection<DnsServerAddresses> values = parse.values();
            if (values.isEmpty()) {
                throw new IllegalArgumentException(file + " didn't provide any name servers");
            }
            this.defaultNameServerAddresses = values.iterator().next();
        } else {
            this.defaultNameServerAddresses = dnsServerAddresses;
        }
        if (z) {
            this.domainToNameServerStreamMap.putAll(parse);
        }
    }

    public UnixResolverDnsServerAddressStreamProvider(String str, String str2) throws IOException {
        this(str == null ? null : new File(str), str2 != null ? new File(str2).listFiles() : null);
    }

    @Override // io.netty.resolver.dns.DnsServerAddressStreamProvider
    public DnsServerAddressStream nameServerAddressStream(String str) {
        while (true) {
            int indexOf = str.indexOf(46, 1);
            if (indexOf < 0 || indexOf == str.length() - 1) {
                break;
            }
            DnsServerAddresses dnsServerAddresses = this.domainToNameServerStreamMap.get(str);
            if (dnsServerAddresses != null) {
                return dnsServerAddresses.stream();
            }
            str = str.substring(indexOf + 1);
        }
        return this.defaultNameServerAddresses.stream();
    }

    private boolean mayOverrideNameServers() {
        return (this.domainToNameServerStreamMap.isEmpty() && this.defaultNameServerAddresses.stream().next() == null) ? false : true;
    }

    private static Map<String, DnsServerAddresses> parse(File... fileArr) throws IOException {
        BufferedReader bufferedReader;
        int i;
        char c;
        int i2;
        char charAt;
        HashMap hashMap = new HashMap(fileArr.length << 1);
        int length = fileArr.length;
        int i3 = 0;
        int i4 = 0;
        while (i4 < length) {
            File file = fileArr[i4];
            if (file.isFile()) {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader2 = null;
                try {
                    bufferedReader = new BufferedReader(fileReader);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    char c2 = 2;
                    ArrayList arrayList = new ArrayList(2);
                    String name = file.getName();
                    int i5 = 53;
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            String trim = readLine.trim();
                            if (!trim.isEmpty() && (charAt = trim.charAt(i3)) != '#' && charAt != ';') {
                                if (trim.startsWith(NAMESERVER_ROW_LABEL)) {
                                    int indexOfNonWhiteSpace = StringUtil.indexOfNonWhiteSpace(trim, 10);
                                    if (indexOfNonWhiteSpace < 0) {
                                        throw new IllegalArgumentException("error parsing label nameserver in file " + file + ". value: " + trim);
                                    }
                                    String substring = trim.substring(indexOfNonWhiteSpace);
                                    if (NetUtil.isValidIpV4Address(substring) || NetUtil.isValidIpV6Address(substring)) {
                                        i2 = i3;
                                    } else {
                                        int lastIndexOf = substring.lastIndexOf(46);
                                        int i6 = lastIndexOf + 1;
                                        if (i6 >= substring.length()) {
                                            throw new IllegalArgumentException("error parsing label nameserver in file " + file + ". invalid IP value: " + trim);
                                        }
                                        int parseInt = Integer.parseInt(substring.substring(i6));
                                        i2 = 0;
                                        substring = substring.substring(0, lastIndexOf);
                                        i5 = parseInt;
                                    }
                                    arrayList.add(SocketUtils.socketAddress(substring, i5));
                                    c = 2;
                                } else {
                                    i2 = i3;
                                    if (trim.startsWith("domain")) {
                                        int indexOfNonWhiteSpace2 = StringUtil.indexOfNonWhiteSpace(trim, 6);
                                        if (indexOfNonWhiteSpace2 < 0) {
                                            throw new IllegalArgumentException("error parsing label domain in file " + file + " value: " + trim);
                                        }
                                        String substring2 = trim.substring(indexOfNonWhiteSpace2);
                                        if (!arrayList.isEmpty()) {
                                            putIfAbsent(hashMap, substring2, arrayList);
                                        }
                                        c = 2;
                                        arrayList = new ArrayList(2);
                                        name = substring2;
                                    } else {
                                        c = 2;
                                        if (trim.startsWith("port")) {
                                            int indexOfNonWhiteSpace3 = StringUtil.indexOfNonWhiteSpace(trim, 4);
                                            if (indexOfNonWhiteSpace3 < 0) {
                                                throw new IllegalArgumentException("error parsing label port in file " + file + " value: " + trim);
                                            }
                                            i5 = Integer.parseInt(trim.substring(indexOfNonWhiteSpace3));
                                        } else if (trim.startsWith(SORTLIST_ROW_LABEL)) {
                                            logger.info("row type {} not supported. ignoring line: {}", SORTLIST_ROW_LABEL, trim);
                                        }
                                    }
                                }
                                i3 = i2;
                                c2 = c;
                            }
                            c = c2;
                            i2 = i3;
                            i3 = i2;
                            c2 = c;
                        } else {
                            i = i3;
                            if (!arrayList.isEmpty()) {
                                putIfAbsent(hashMap, name, arrayList);
                            }
                            bufferedReader.close();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader2 = bufferedReader;
                    if (bufferedReader2 == null) {
                        fileReader.close();
                    } else {
                        bufferedReader2.close();
                    }
                    throw th;
                }
            } else {
                i = i3;
            }
            i4++;
            i3 = i;
        }
        return hashMap;
    }

    private static void putIfAbsent(Map<String, DnsServerAddresses> map, String str, List<InetSocketAddress> list) {
        putIfAbsent(map, str, DnsServerAddresses.sequential(list));
    }

    private static void putIfAbsent(Map<String, DnsServerAddresses> map, String str, DnsServerAddresses dnsServerAddresses) {
        DnsServerAddresses put = map.put(str, dnsServerAddresses);
        if (put != null) {
            map.put(str, put);
            logger.debug("Domain name {} already maps to addresses {} so new addresses {} will be discarded", str, put, dnsServerAddresses);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int parseEtcResolverFirstNdots() throws IOException {
        return parseEtcResolverFirstNdots(new File(ETC_RESOLV_CONF_FILE));
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0019, code lost:
    
        r2 = r5.indexOf(io.netty.resolver.dns.UnixResolverDnsServerAddressStreamProvider.NDOTS_LABEL);
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x001f, code lost:
    
        if (r2 < 0) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0021, code lost:
    
        r2 = r2 + 6;
        r3 = r5.indexOf(32, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0029, code lost:
    
        if (r3 >= 0) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002b, code lost:
    
        r3 = r5.length();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002f, code lost:
    
        r5 = java.lang.Integer.parseInt(r5.substring(r2, r3));
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0037, code lost:
    
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003a, code lost:
    
        return r5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static int parseEtcResolverFirstNdots(File file) throws IOException {
        BufferedReader bufferedReader;
        Throwable th;
        FileReader fileReader = new FileReader(file);
        try {
            bufferedReader = new BufferedReader(fileReader);
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    if (readLine.startsWith(OPTIONS_ROW_LABEL)) {
                        break;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedReader == null) {
                        fileReader.close();
                    } else {
                        bufferedReader.close();
                    }
                    throw th;
                }
            }
            bufferedReader.close();
            return 1;
        } catch (Throwable th3) {
            bufferedReader = null;
            th = th3;
        }
    }
}
