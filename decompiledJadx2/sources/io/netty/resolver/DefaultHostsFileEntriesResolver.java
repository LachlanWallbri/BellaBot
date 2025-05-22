package io.netty.resolver;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.util.Locale;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class DefaultHostsFileEntriesResolver implements HostsFileEntriesResolver {
    private final Map<String, Inet4Address> inet4Entries;
    private final Map<String, Inet6Address> inet6Entries;

    public DefaultHostsFileEntriesResolver() {
        this(HostsFileParser.parseSilently());
    }

    DefaultHostsFileEntriesResolver(HostsFileEntries hostsFileEntries) {
        this.inet4Entries = hostsFileEntries.inet4Entries();
        this.inet6Entries = hostsFileEntries.inet6Entries();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* renamed from: io.netty.resolver.DefaultHostsFileEntriesResolver$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C73751 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$resolver$ResolvedAddressTypes;

        static {
            int[] iArr = new int[ResolvedAddressTypes.values().length];
            $SwitchMap$io$netty$resolver$ResolvedAddressTypes = iArr;
            try {
                iArr[ResolvedAddressTypes.IPV4_ONLY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$resolver$ResolvedAddressTypes[ResolvedAddressTypes.IPV6_ONLY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$netty$resolver$ResolvedAddressTypes[ResolvedAddressTypes.IPV4_PREFERRED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$netty$resolver$ResolvedAddressTypes[ResolvedAddressTypes.IPV6_PREFERRED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // io.netty.resolver.HostsFileEntriesResolver
    public InetAddress address(String str, ResolvedAddressTypes resolvedAddressTypes) {
        String normalize = normalize(str);
        int i = C73751.$SwitchMap$io$netty$resolver$ResolvedAddressTypes[resolvedAddressTypes.ordinal()];
        if (i == 1) {
            return this.inet4Entries.get(normalize);
        }
        if (i == 2) {
            return this.inet6Entries.get(normalize);
        }
        if (i == 3) {
            Inet4Address inet4Address = this.inet4Entries.get(normalize);
            return inet4Address != null ? inet4Address : this.inet6Entries.get(normalize);
        }
        if (i == 4) {
            Inet6Address inet6Address = this.inet6Entries.get(normalize);
            return inet6Address != null ? inet6Address : this.inet4Entries.get(normalize);
        }
        throw new IllegalArgumentException("Unknown ResolvedAddressTypes " + resolvedAddressTypes);
    }

    String normalize(String str) {
        return str.toLowerCase(Locale.ENGLISH);
    }
}
