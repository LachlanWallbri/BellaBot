package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import java.util.ArrayList;
import java.util.List;
import kotlin.DeprecationLevel;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import org.apache.commons.io.FilenameUtils;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: VersionRequirement.kt */
/* loaded from: classes2.dex */
public final class VersionRequirement {
    public static final Companion Companion = new Companion(null);
    private final Integer errorCode;
    private final ProtoBuf.VersionRequirement.VersionKind kind;
    private final DeprecationLevel level;
    private final String message;
    private final Version version;

    public VersionRequirement(Version version, ProtoBuf.VersionRequirement.VersionKind kind, DeprecationLevel level, Integer num, String str) {
        Intrinsics.checkParameterIsNotNull(version, "version");
        Intrinsics.checkParameterIsNotNull(kind, "kind");
        Intrinsics.checkParameterIsNotNull(level, "level");
        this.version = version;
        this.kind = kind;
        this.level = level;
        this.errorCode = num;
        this.message = str;
    }

    public final Version getVersion() {
        return this.version;
    }

    public final ProtoBuf.VersionRequirement.VersionKind getKind() {
        return this.kind;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* compiled from: VersionRequirement.kt */
    /* loaded from: classes2.dex */
    public static final class Version {
        public static final Companion Companion = new Companion(null);
        public static final Version INFINITY = new Version(256, 256, 256);
        private final int major;
        private final int minor;
        private final int patch;

        public boolean equals(Object obj) {
            if (this != obj) {
                if (obj instanceof Version) {
                    Version version = (Version) obj;
                    if (this.major == version.major) {
                        if (this.minor == version.minor) {
                            if (this.patch == version.patch) {
                            }
                        }
                    }
                }
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (((this.major * 31) + this.minor) * 31) + this.patch;
        }

        public Version(int i, int i2, int i3) {
            this.major = i;
            this.minor = i2;
            this.patch = i3;
        }

        public /* synthetic */ Version(int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, i2, (i4 & 4) != 0 ? 0 : i3);
        }

        public final String asString() {
            StringBuilder sb;
            int i;
            if (this.patch == 0) {
                sb = new StringBuilder();
                sb.append(this.major);
                sb.append(FilenameUtils.EXTENSION_SEPARATOR);
                i = this.minor;
            } else {
                sb = new StringBuilder();
                sb.append(this.major);
                sb.append(FilenameUtils.EXTENSION_SEPARATOR);
                sb.append(this.minor);
                sb.append(FilenameUtils.EXTENSION_SEPARATOR);
                i = this.patch;
            }
            sb.append(i);
            return sb.toString();
        }

        public String toString() {
            return asString();
        }

        /* JADX WARN: Classes with same name are omitted:
          classes7.dex
          classes8.dex
         */
        /* compiled from: VersionRequirement.kt */
        /* loaded from: classes2.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final Version decode(Integer num, Integer num2) {
                if (num2 != null) {
                    return new Version(num2.intValue() & 255, (num2.intValue() >> 8) & 255, (num2.intValue() >> 16) & 255);
                }
                if (num != null) {
                    return new Version(num.intValue() & 7, (num.intValue() >> 3) & 15, (num.intValue() >> 7) & 127);
                }
                return Version.INFINITY;
            }
        }
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("since ");
        sb.append(this.version);
        sb.append(' ');
        sb.append(this.level);
        String str2 = "";
        if (this.errorCode != null) {
            str = " error " + this.errorCode;
        } else {
            str = "";
        }
        sb.append(str);
        if (this.message != null) {
            str2 = ": " + this.message;
        }
        sb.append(str2);
        return sb.toString();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* compiled from: VersionRequirement.kt */
    /* loaded from: classes2.dex */
    public static final class Companion {

        /* JADX WARN: Classes with same name are omitted:
          classes7.dex
          classes8.dex
         */
        /* loaded from: classes2.dex */
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[ProtoBuf.VersionRequirement.Level.values().length];
                $EnumSwitchMapping$0 = iArr;
                iArr[ProtoBuf.VersionRequirement.Level.WARNING.ordinal()] = 1;
                $EnumSwitchMapping$0[ProtoBuf.VersionRequirement.Level.ERROR.ordinal()] = 2;
                $EnumSwitchMapping$0[ProtoBuf.VersionRequirement.Level.HIDDEN.ordinal()] = 3;
            }
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final List<VersionRequirement> create(MessageLite proto, NameResolver nameResolver, VersionRequirementTable table) {
            List<Integer> ids;
            Intrinsics.checkParameterIsNotNull(proto, "proto");
            Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
            Intrinsics.checkParameterIsNotNull(table, "table");
            if (proto instanceof ProtoBuf.Class) {
                ids = ((ProtoBuf.Class) proto).getVersionRequirementList();
            } else if (proto instanceof ProtoBuf.Constructor) {
                ids = ((ProtoBuf.Constructor) proto).getVersionRequirementList();
            } else if (proto instanceof ProtoBuf.Function) {
                ids = ((ProtoBuf.Function) proto).getVersionRequirementList();
            } else if (proto instanceof ProtoBuf.Property) {
                ids = ((ProtoBuf.Property) proto).getVersionRequirementList();
            } else {
                if (!(proto instanceof ProtoBuf.TypeAlias)) {
                    throw new IllegalStateException("Unexpected declaration: " + proto.getClass());
                }
                ids = ((ProtoBuf.TypeAlias) proto).getVersionRequirementList();
            }
            Intrinsics.checkExpressionValueIsNotNull(ids, "ids");
            ArrayList arrayList = new ArrayList();
            for (Integer id : ids) {
                Companion companion = VersionRequirement.Companion;
                Intrinsics.checkExpressionValueIsNotNull(id, "id");
                VersionRequirement create = companion.create(id.intValue(), nameResolver, table);
                if (create != null) {
                    arrayList.add(create);
                }
            }
            return arrayList;
        }

        public final VersionRequirement create(int i, NameResolver nameResolver, VersionRequirementTable table) {
            DeprecationLevel deprecationLevel;
            Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
            Intrinsics.checkParameterIsNotNull(table, "table");
            ProtoBuf.VersionRequirement versionRequirement = table.get(i);
            if (versionRequirement == null) {
                return null;
            }
            Version decode = Version.Companion.decode(versionRequirement.hasVersion() ? Integer.valueOf(versionRequirement.getVersion()) : null, versionRequirement.hasVersionFull() ? Integer.valueOf(versionRequirement.getVersionFull()) : null);
            ProtoBuf.VersionRequirement.Level level = versionRequirement.getLevel();
            if (level == null) {
                Intrinsics.throwNpe();
            }
            int i2 = WhenMappings.$EnumSwitchMapping$0[level.ordinal()];
            if (i2 == 1) {
                deprecationLevel = DeprecationLevel.WARNING;
            } else if (i2 == 2) {
                deprecationLevel = DeprecationLevel.ERROR;
            } else {
                if (i2 != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                deprecationLevel = DeprecationLevel.HIDDEN;
            }
            DeprecationLevel deprecationLevel2 = deprecationLevel;
            Integer valueOf = versionRequirement.hasErrorCode() ? Integer.valueOf(versionRequirement.getErrorCode()) : null;
            String string = versionRequirement.hasMessage() ? nameResolver.getString(versionRequirement.getMessage()) : null;
            ProtoBuf.VersionRequirement.VersionKind versionKind = versionRequirement.getVersionKind();
            Intrinsics.checkExpressionValueIsNotNull(versionKind, "info.versionKind");
            return new VersionRequirement(decode, versionKind, deprecationLevel2, valueOf, string);
        }
    }
}
