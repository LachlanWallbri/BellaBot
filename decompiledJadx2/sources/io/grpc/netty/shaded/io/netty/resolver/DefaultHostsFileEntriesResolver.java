package io.grpc.netty.shaded.io.netty.resolver;

import io.grpc.netty.shaded.io.netty.util.CharsetUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class DefaultHostsFileEntriesResolver implements HostsFileEntriesResolver {
    private final Map<String, Inet4Address> inet4Entries;
    private final Map<String, Inet6Address> inet6Entries;

    public DefaultHostsFileEntriesResolver() {
        this(parseEntries());
    }

    DefaultHostsFileEntriesResolver(HostsFileEntries hostsFileEntries) {
        this.inet4Entries = hostsFileEntries.inet4Entries();
        this.inet6Entries = hostsFileEntries.inet6Entries();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.netty.shaded.io.netty.resolver.DefaultHostsFileEntriesResolver$1 */
    /* loaded from: classes7.dex */
    static /* synthetic */ class C66881 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$resolver$ResolvedAddressTypes = new int[ResolvedAddressTypes.values().length];

        static {
            try {
                $SwitchMap$io$netty$resolver$ResolvedAddressTypes[ResolvedAddressTypes.IPV4_ONLY.ordinal()] = 1;
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

    @Override // io.grpc.netty.shaded.io.netty.resolver.HostsFileEntriesResolver
    public InetAddress address(String str, ResolvedAddressTypes resolvedAddressTypes) {
        String normalize = normalize(str);
        int i = C66881.$SwitchMap$io$netty$resolver$ResolvedAddressTypes[resolvedAddressTypes.ordinal()];
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

    private static HostsFileEntries parseEntries() {
        return PlatformDependent.isWindows() ? HostsFileParser.parseSilently(Charset.defaultCharset(), CharsetUtil.UTF_16, CharsetUtil.UTF_8) : HostsFileParser.parseSilently();
    }
}
