package okhttp3;

import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.internal.Util;
import okhttp3.internal.http.HttpHeaders;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* loaded from: classes2.dex */
public final class CacheControl {

    @Nullable
    String headerValue;
    private final boolean immutable;
    private final boolean isPrivate;
    private final boolean isPublic;
    private final int maxAgeSeconds;
    private final int maxStaleSeconds;
    private final int minFreshSeconds;
    private final boolean mustRevalidate;
    private final boolean noCache;
    private final boolean noStore;
    private final boolean noTransform;
    private final boolean onlyIfCached;
    private final int sMaxAgeSeconds;
    public static final CacheControl FORCE_NETWORK = new Builder().noCache().build();
    public static final CacheControl FORCE_CACHE = new Builder().onlyIfCached().maxStale(Integer.MAX_VALUE, TimeUnit.SECONDS).build();

    private CacheControl(boolean z, boolean z2, int i, int i2, boolean z3, boolean z4, boolean z5, int i3, int i4, boolean z6, boolean z7, boolean z8, @Nullable String str) {
        this.noCache = z;
        this.noStore = z2;
        this.maxAgeSeconds = i;
        this.sMaxAgeSeconds = i2;
        this.isPrivate = z3;
        this.isPublic = z4;
        this.mustRevalidate = z5;
        this.maxStaleSeconds = i3;
        this.minFreshSeconds = i4;
        this.onlyIfCached = z6;
        this.noTransform = z7;
        this.immutable = z8;
        this.headerValue = str;
    }

    CacheControl(Builder builder) {
        this.noCache = builder.noCache;
        this.noStore = builder.noStore;
        this.maxAgeSeconds = builder.maxAgeSeconds;
        this.sMaxAgeSeconds = -1;
        this.isPrivate = false;
        this.isPublic = false;
        this.mustRevalidate = false;
        this.maxStaleSeconds = builder.maxStaleSeconds;
        this.minFreshSeconds = builder.minFreshSeconds;
        this.onlyIfCached = builder.onlyIfCached;
        this.noTransform = builder.noTransform;
        this.immutable = builder.immutable;
    }

    public boolean noCache() {
        return this.noCache;
    }

    public boolean noStore() {
        return this.noStore;
    }

    public int maxAgeSeconds() {
        return this.maxAgeSeconds;
    }

    public int sMaxAgeSeconds() {
        return this.sMaxAgeSeconds;
    }

    public boolean isPrivate() {
        return this.isPrivate;
    }

    public boolean isPublic() {
        return this.isPublic;
    }

    public boolean mustRevalidate() {
        return this.mustRevalidate;
    }

    public int maxStaleSeconds() {
        return this.maxStaleSeconds;
    }

    public int minFreshSeconds() {
        return this.minFreshSeconds;
    }

    public boolean onlyIfCached() {
        return this.onlyIfCached;
    }

    public boolean noTransform() {
        return this.noTransform;
    }

    public boolean immutable() {
        return this.immutable;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static CacheControl parse(Headers headers) {
        int i;
        int i2;
        String str;
        Headers headers2 = headers;
        int size = headers.size();
        int i3 = 0;
        boolean z = true;
        String str2 = null;
        boolean z2 = false;
        boolean z3 = false;
        int i4 = -1;
        int i5 = -1;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        int i6 = -1;
        int i7 = -1;
        boolean z7 = false;
        boolean z8 = false;
        boolean z9 = false;
        while (i3 < size) {
            String name = headers2.name(i3);
            String value = headers2.value(i3);
            if (name.equalsIgnoreCase("Cache-Control")) {
                if (str2 == null) {
                    str2 = value;
                    for (i = 0; i < value.length(); i = i2) {
                        int skipUntil = HttpHeaders.skipUntil(value, i, "=,;");
                        String trim = value.substring(i, skipUntil).trim();
                        if (skipUntil == value.length() || value.charAt(skipUntil) == ',' || value.charAt(skipUntil) == ';') {
                            i2 = skipUntil + 1;
                            str = null;
                        } else {
                            int skipWhitespace = HttpHeaders.skipWhitespace(value, skipUntil + 1);
                            if (skipWhitespace < value.length() && value.charAt(skipWhitespace) == '\"') {
                                int i8 = skipWhitespace + 1;
                                int skipUntil2 = HttpHeaders.skipUntil(value, i8, "\"");
                                str = value.substring(i8, skipUntil2);
                                i2 = skipUntil2 + 1;
                            } else {
                                i2 = HttpHeaders.skipUntil(value, skipWhitespace, ",;");
                                str = value.substring(skipWhitespace, i2).trim();
                            }
                        }
                        if ("no-cache".equalsIgnoreCase(trim)) {
                            z2 = true;
                        } else if ("no-store".equalsIgnoreCase(trim)) {
                            z3 = true;
                        } else if ("max-age".equalsIgnoreCase(trim)) {
                            i4 = HttpHeaders.parseSeconds(str, -1);
                        } else if ("s-maxage".equalsIgnoreCase(trim)) {
                            i5 = HttpHeaders.parseSeconds(str, -1);
                        } else if ("private".equalsIgnoreCase(trim)) {
                            z4 = true;
                        } else if ("public".equalsIgnoreCase(trim)) {
                            z5 = true;
                        } else if ("must-revalidate".equalsIgnoreCase(trim)) {
                            z6 = true;
                        } else if ("max-stale".equalsIgnoreCase(trim)) {
                            i6 = HttpHeaders.parseSeconds(str, Integer.MAX_VALUE);
                        } else if ("min-fresh".equalsIgnoreCase(trim)) {
                            i7 = HttpHeaders.parseSeconds(str, -1);
                        } else if ("only-if-cached".equalsIgnoreCase(trim)) {
                            z7 = true;
                        } else if ("no-transform".equalsIgnoreCase(trim)) {
                            z8 = true;
                        } else if ("immutable".equalsIgnoreCase(trim)) {
                            z9 = true;
                        }
                    }
                    i3++;
                    headers2 = headers;
                }
            } else if (!name.equalsIgnoreCase("Pragma")) {
                i3++;
                headers2 = headers;
            }
            z = false;
            while (i < value.length()) {
            }
            i3++;
            headers2 = headers;
        }
        return new CacheControl(z2, z3, i4, i5, z4, z5, z6, i6, i7, z7, z8, z9, !z ? null : str2);
    }

    public String toString() {
        String str = this.headerValue;
        if (str != null) {
            return str;
        }
        String headerValue = headerValue();
        this.headerValue = headerValue;
        return headerValue;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* compiled from: CacheControl.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u001e\u0010\t\u001a\u00020\n*\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\nH\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m3961d2 = {"Lokhttp3/CacheControl$Companion;", "", "()V", "FORCE_CACHE", "Lokhttp3/CacheControl;", "FORCE_NETWORK", "parse", "headers", "Lokhttp3/Headers;", "indexOfElement", "", "", "characters", "startIndex", "okhttp"}, m3962k = 1, m3963mv = {1, 1, 15})
    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x004a  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x0102  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0106  */
        @JvmStatic
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final CacheControl parse(Headers headers) {
            String str;
            int i;
            int i2;
            boolean z;
            int i3;
            String str2;
            Headers headers2 = headers;
            Intrinsics.checkParameterIsNotNull(headers2, "headers");
            String str3 = (String) null;
            int size = headers.size();
            boolean z2 = true;
            String str4 = str3;
            int i4 = 0;
            boolean z3 = true;
            boolean z4 = false;
            boolean z5 = false;
            int i5 = -1;
            int i6 = -1;
            boolean z6 = false;
            boolean z7 = false;
            boolean z8 = false;
            int i7 = -1;
            int i8 = -1;
            boolean z9 = false;
            boolean z10 = false;
            boolean z11 = false;
            while (i4 < size) {
                String name = headers2.name(i4);
                String value = headers2.value(i4);
                if (StringsKt.equals(name, "Cache-Control", z2)) {
                    if (str4 == null) {
                        str4 = value;
                        i2 = 0;
                        while (i2 < value.length()) {
                            Companion companion = this;
                            int indexOfElement = companion.indexOfElement(value, "=,;", i2);
                            String str5 = str3;
                            if (value == null) {
                                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                            }
                            String substring = value.substring(i2, indexOfElement);
                            int i9 = size;
                            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                            boolean z12 = z3;
                            if (substring == null) {
                                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                            }
                            String obj = StringsKt.trim((CharSequence) substring).toString();
                            String str6 = str4;
                            if (indexOfElement != value.length()) {
                                z = z4;
                                if (value.charAt(indexOfElement) != ',' && value.charAt(indexOfElement) != ';') {
                                    int indexOfNonWhitespace = Util.indexOfNonWhitespace(value, indexOfElement + 1);
                                    if (indexOfNonWhitespace < value.length() && value.charAt(indexOfNonWhitespace) == '\"') {
                                        int i10 = indexOfNonWhitespace + 1;
                                        int indexOf$default = StringsKt.indexOf$default((CharSequence) value, '\"', i10, false, 4, (Object) null);
                                        if (value == null) {
                                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                                        }
                                        str2 = value.substring(i10, indexOf$default);
                                        Intrinsics.checkExpressionValueIsNotNull(str2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                                        i3 = indexOf$default + 1;
                                    } else {
                                        i3 = companion.indexOfElement(value, ",;", indexOfNonWhitespace);
                                        if (value == null) {
                                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                                        }
                                        String substring2 = value.substring(indexOfNonWhitespace, i3);
                                        Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                                        if (substring2 == null) {
                                            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                                        }
                                        str2 = StringsKt.trim((CharSequence) substring2).toString();
                                    }
                                    z2 = true;
                                    if (!StringsKt.equals("no-cache", obj, true)) {
                                        z4 = true;
                                    } else if (StringsKt.equals("no-store", obj, true)) {
                                        z4 = z;
                                        z5 = true;
                                    } else {
                                        if (StringsKt.equals("max-age", obj, true)) {
                                            i5 = Util.toNonNegativeInt(str2, -1);
                                        } else if (StringsKt.equals("s-maxage", obj, true)) {
                                            i6 = Util.toNonNegativeInt(str2, -1);
                                        } else if (StringsKt.equals("private", obj, true)) {
                                            z4 = z;
                                            z6 = true;
                                        } else if (StringsKt.equals("public", obj, true)) {
                                            z4 = z;
                                            z7 = true;
                                        } else if (StringsKt.equals("must-revalidate", obj, true)) {
                                            z4 = z;
                                            z8 = true;
                                        } else if (StringsKt.equals("max-stale", obj, true)) {
                                            i7 = Util.toNonNegativeInt(str2, Integer.MAX_VALUE);
                                            z4 = z;
                                        } else if (StringsKt.equals("min-fresh", obj, true)) {
                                            i8 = Util.toNonNegativeInt(str2, -1);
                                        } else if (StringsKt.equals("only-if-cached", obj, true)) {
                                            z4 = z;
                                            z9 = true;
                                        } else if (StringsKt.equals("no-transform", obj, true)) {
                                            z4 = z;
                                            z10 = true;
                                        } else {
                                            z4 = z;
                                            if (StringsKt.equals("immutable", obj, true)) {
                                                z11 = true;
                                            }
                                        }
                                        z4 = z;
                                    }
                                    i2 = i3;
                                    str3 = str5;
                                    size = i9;
                                    z3 = z12;
                                    str4 = str6;
                                }
                            } else {
                                z = z4;
                            }
                            i3 = indexOfElement + 1;
                            str2 = str5;
                            z2 = true;
                            if (!StringsKt.equals("no-cache", obj, true)) {
                            }
                            i2 = i3;
                            str3 = str5;
                            size = i9;
                            z3 = z12;
                            str4 = str6;
                        }
                        str = str3;
                        i = size;
                        i4++;
                        headers2 = headers;
                        str3 = str;
                        size = i;
                    }
                } else if (!StringsKt.equals(name, "Pragma", z2)) {
                    str = str3;
                    i = size;
                    i4++;
                    headers2 = headers;
                    str3 = str;
                    size = i;
                }
                z3 = false;
                i2 = 0;
                while (i2 < value.length()) {
                }
                str = str3;
                i = size;
                i4++;
                headers2 = headers;
                str3 = str;
                size = i;
            }
            return new CacheControl(z4, z5, i5, i6, z6, z7, z8, i7, i8, z9, z10, z11, !z3 ? str3 : str4, null);
        }

        static /* synthetic */ int indexOfElement$default(Companion companion, String str, String str2, int i, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                i = 0;
            }
            return companion.indexOfElement(str, str2, i);
        }

        private final int indexOfElement(String str, String str2, int i) {
            int length = str.length();
            while (i < length) {
                if (StringsKt.contains$default((CharSequence) str2, str.charAt(i), false, 2, (Object) null)) {
                    return i;
                }
                i++;
            }
            return str.length();
        }
    }

    private String headerValue() {
        StringBuilder sb = new StringBuilder();
        if (this.noCache) {
            sb.append("no-cache, ");
        }
        if (this.noStore) {
            sb.append("no-store, ");
        }
        if (this.maxAgeSeconds != -1) {
            sb.append("max-age=");
            sb.append(this.maxAgeSeconds);
            sb.append(", ");
        }
        if (this.sMaxAgeSeconds != -1) {
            sb.append("s-maxage=");
            sb.append(this.sMaxAgeSeconds);
            sb.append(", ");
        }
        if (this.isPrivate) {
            sb.append("private, ");
        }
        if (this.isPublic) {
            sb.append("public, ");
        }
        if (this.mustRevalidate) {
            sb.append("must-revalidate, ");
        }
        if (this.maxStaleSeconds != -1) {
            sb.append("max-stale=");
            sb.append(this.maxStaleSeconds);
            sb.append(", ");
        }
        if (this.minFreshSeconds != -1) {
            sb.append("min-fresh=");
            sb.append(this.minFreshSeconds);
            sb.append(", ");
        }
        if (this.onlyIfCached) {
            sb.append("only-if-cached, ");
        }
        if (this.noTransform) {
            sb.append("no-transform, ");
        }
        if (this.immutable) {
            sb.append("immutable, ");
        }
        if (sb.length() == 0) {
            return "";
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* loaded from: classes2.dex */
    public static final class Builder {
        boolean immutable;
        int maxAgeSeconds = -1;
        int maxStaleSeconds = -1;
        int minFreshSeconds = -1;
        boolean noCache;
        boolean noStore;
        boolean noTransform;
        boolean onlyIfCached;

        public Builder noCache() {
            this.noCache = true;
            return this;
        }

        public Builder noStore() {
            this.noStore = true;
            return this;
        }

        public Builder maxAge(int i, TimeUnit timeUnit) {
            if (i < 0) {
                throw new IllegalArgumentException("maxAge < 0: " + i);
            }
            long seconds = timeUnit.toSeconds(i);
            this.maxAgeSeconds = seconds > 2147483647L ? Integer.MAX_VALUE : (int) seconds;
            return this;
        }

        public Builder maxStale(int i, TimeUnit timeUnit) {
            if (i < 0) {
                throw new IllegalArgumentException("maxStale < 0: " + i);
            }
            long seconds = timeUnit.toSeconds(i);
            this.maxStaleSeconds = seconds > 2147483647L ? Integer.MAX_VALUE : (int) seconds;
            return this;
        }

        public Builder minFresh(int i, TimeUnit timeUnit) {
            if (i < 0) {
                throw new IllegalArgumentException("minFresh < 0: " + i);
            }
            long seconds = timeUnit.toSeconds(i);
            this.minFreshSeconds = seconds > 2147483647L ? Integer.MAX_VALUE : (int) seconds;
            return this;
        }

        public Builder onlyIfCached() {
            this.onlyIfCached = true;
            return this;
        }

        public Builder noTransform() {
            this.noTransform = true;
            return this;
        }

        public Builder immutable() {
            this.immutable = true;
            return this;
        }

        public CacheControl build() {
            return new CacheControl(this);
        }
    }
}
