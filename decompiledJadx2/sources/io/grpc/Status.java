package io.grpc;

import com.google.common.base.Charsets;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import io.grpc.Metadata;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import org.apache.commons.compress.archivers.tar.TarConstants;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
@CheckReturnValue
/* loaded from: classes7.dex */
public final class Status {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final Metadata.Key<Status> CODE_KEY;
    static final Metadata.Key<String> MESSAGE_KEY;
    private static final Metadata.TrustedAsciiMarshaller<String> STATUS_MESSAGE_MARSHALLER;
    private final Throwable cause;
    private final Code code;
    private final String description;
    private static final String TEST_EQUALS_FAILURE_PROPERTY = "io.grpc.Status.failOnEqualsForTest";
    private static final boolean FAIL_ON_EQUALS_FOR_TEST = Boolean.parseBoolean(System.getProperty(TEST_EQUALS_FAILURE_PROPERTY, "false"));
    private static final List<Status> STATUS_LIST = buildStatusList();

    /* renamed from: OK */
    public static final Status f8305OK = Code.OK.toStatus();
    public static final Status CANCELLED = Code.CANCELLED.toStatus();
    public static final Status UNKNOWN = Code.UNKNOWN.toStatus();
    public static final Status INVALID_ARGUMENT = Code.INVALID_ARGUMENT.toStatus();
    public static final Status DEADLINE_EXCEEDED = Code.DEADLINE_EXCEEDED.toStatus();
    public static final Status NOT_FOUND = Code.NOT_FOUND.toStatus();
    public static final Status ALREADY_EXISTS = Code.ALREADY_EXISTS.toStatus();
    public static final Status PERMISSION_DENIED = Code.PERMISSION_DENIED.toStatus();
    public static final Status UNAUTHENTICATED = Code.UNAUTHENTICATED.toStatus();
    public static final Status RESOURCE_EXHAUSTED = Code.RESOURCE_EXHAUSTED.toStatus();
    public static final Status FAILED_PRECONDITION = Code.FAILED_PRECONDITION.toStatus();
    public static final Status ABORTED = Code.ABORTED.toStatus();
    public static final Status OUT_OF_RANGE = Code.OUT_OF_RANGE.toStatus();
    public static final Status UNIMPLEMENTED = Code.UNIMPLEMENTED.toStatus();
    public static final Status INTERNAL = Code.INTERNAL.toStatus();
    public static final Status UNAVAILABLE = Code.UNAVAILABLE.toStatus();
    public static final Status DATA_LOSS = Code.DATA_LOSS.toStatus();

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum Code {
        OK(0),
        CANCELLED(1),
        UNKNOWN(2),
        INVALID_ARGUMENT(3),
        DEADLINE_EXCEEDED(4),
        NOT_FOUND(5),
        ALREADY_EXISTS(6),
        PERMISSION_DENIED(7),
        RESOURCE_EXHAUSTED(8),
        FAILED_PRECONDITION(9),
        ABORTED(10),
        OUT_OF_RANGE(11),
        UNIMPLEMENTED(12),
        INTERNAL(13),
        UNAVAILABLE(14),
        DATA_LOSS(15),
        UNAUTHENTICATED(16);

        private final int value;
        private final byte[] valueAscii;

        Code(int i) {
            this.value = i;
            this.valueAscii = Integer.toString(i).getBytes(Charsets.US_ASCII);
        }

        public int value() {
            return this.value;
        }

        public Status toStatus() {
            return (Status) Status.STATUS_LIST.get(this.value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public byte[] valueAscii() {
            return this.valueAscii;
        }
    }

    static {
        CODE_KEY = Metadata.Key.m3905of("grpc-status", false, (Metadata.TrustedAsciiMarshaller) new StatusCodeMarshaller());
        STATUS_MESSAGE_MARSHALLER = new StatusMessageMarshaller();
        MESSAGE_KEY = Metadata.Key.m3905of("grpc-message", false, (Metadata.TrustedAsciiMarshaller) STATUS_MESSAGE_MARSHALLER);
    }

    private static List<Status> buildStatusList() {
        TreeMap treeMap = new TreeMap();
        for (Code code : Code.values()) {
            Status status = (Status) treeMap.put(Integer.valueOf(code.value()), new Status(code));
            if (status != null) {
                throw new IllegalStateException("Code value duplication between " + status.getCode().name() + " & " + code.name());
            }
        }
        return Collections.unmodifiableList(new ArrayList(treeMap.values()));
    }

    public static Status fromCodeValue(int i) {
        if (i < 0 || i > STATUS_LIST.size()) {
            return UNKNOWN.withDescription("Unknown code " + i);
        }
        return STATUS_LIST.get(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Status fromCodeValue(byte[] bArr) {
        if (bArr.length == 1 && bArr[0] == 48) {
            return f8305OK;
        }
        return fromCodeValueSlow(bArr);
    }

    private static Status fromCodeValueSlow(byte[] bArr) {
        int i;
        int i2;
        int length = bArr.length;
        char c = 1;
        if (length != 1) {
            i = (length == 2 && bArr[0] >= 48 && bArr[0] <= 57) ? 0 + ((bArr[0] - TarConstants.LF_NORMAL) * 10) : 0;
            return UNKNOWN.withDescription("Unknown code " + new String(bArr, Charsets.US_ASCII));
        }
        c = 0;
        if (bArr[c] >= 48 && bArr[c] <= 57 && (i2 = i + (bArr[c] - TarConstants.LF_NORMAL)) < STATUS_LIST.size()) {
            return STATUS_LIST.get(i2);
        }
        return UNKNOWN.withDescription("Unknown code " + new String(bArr, Charsets.US_ASCII));
    }

    public static Status fromCode(Code code) {
        return code.toStatus();
    }

    public static Status fromThrowable(Throwable th) {
        for (Throwable th2 = (Throwable) Preconditions.checkNotNull(th, "t"); th2 != null; th2 = th2.getCause()) {
            if (th2 instanceof StatusException) {
                return ((StatusException) th2).getStatus();
            }
            if (th2 instanceof StatusRuntimeException) {
                return ((StatusRuntimeException) th2).getStatus();
            }
        }
        return UNKNOWN.withCause(th);
    }

    @Nullable
    public static Metadata trailersFromThrowable(Throwable th) {
        for (Throwable th2 = (Throwable) Preconditions.checkNotNull(th, "t"); th2 != null; th2 = th2.getCause()) {
            if (th2 instanceof StatusException) {
                return ((StatusException) th2).getTrailers();
            }
            if (th2 instanceof StatusRuntimeException) {
                return ((StatusRuntimeException) th2).getTrailers();
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String formatThrowableMessage(Status status) {
        if (status.description == null) {
            return status.code.toString();
        }
        return status.code + ": " + status.description;
    }

    private Status(Code code) {
        this(code, null, null);
    }

    private Status(Code code, @Nullable String str, @Nullable Throwable th) {
        this.code = (Code) Preconditions.checkNotNull(code, "code");
        this.description = str;
        this.cause = th;
    }

    public Status withCause(Throwable th) {
        return Objects.equal(this.cause, th) ? this : new Status(this.code, this.description, th);
    }

    public Status withDescription(String str) {
        return Objects.equal(this.description, str) ? this : new Status(this.code, str, this.cause);
    }

    public Status augmentDescription(String str) {
        if (str == null) {
            return this;
        }
        if (this.description == null) {
            return new Status(this.code, str, this.cause);
        }
        return new Status(this.code, this.description + "\n" + str, this.cause);
    }

    public Code getCode() {
        return this.code;
    }

    @Nullable
    public String getDescription() {
        return this.description;
    }

    @Nullable
    public Throwable getCause() {
        return this.cause;
    }

    public boolean isOk() {
        return Code.OK == this.code;
    }

    public StatusRuntimeException asRuntimeException() {
        return new StatusRuntimeException(this);
    }

    public StatusRuntimeException asRuntimeException(@Nullable Metadata metadata) {
        return new StatusRuntimeException(this, metadata);
    }

    public StatusException asException() {
        return new StatusException(this);
    }

    public StatusException asException(@Nullable Metadata metadata) {
        return new StatusException(this, metadata);
    }

    public String toString() {
        MoreObjects.ToStringHelper add = MoreObjects.toStringHelper(this).add("code", this.code.name()).add("description", this.description);
        Throwable th = this.cause;
        Object obj = th;
        if (th != null) {
            obj = Throwables.getStackTraceAsString(th);
        }
        return add.add("cause", obj).toString();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class StatusCodeMarshaller implements Metadata.TrustedAsciiMarshaller<Status> {
        private StatusCodeMarshaller() {
        }

        @Override // io.grpc.Metadata.TrustedAsciiMarshaller
        public byte[] toAsciiString(Status status) {
            return status.getCode().valueAscii();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.grpc.Metadata.TrustedAsciiMarshaller
        public Status parseAsciiString(byte[] bArr) {
            return Status.fromCodeValue(bArr);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class StatusMessageMarshaller implements Metadata.TrustedAsciiMarshaller<String> {
        private static final byte[] HEX = {TarConstants.LF_NORMAL, TarConstants.LF_LINK, TarConstants.LF_SYMLINK, TarConstants.LF_CHR, TarConstants.LF_BLK, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_CONTIG, 56, 57, 65, 66, 67, 68, 69, 70};

        private static boolean isEscapingChar(byte b) {
            return b < 32 || b >= 126 || b == 37;
        }

        private StatusMessageMarshaller() {
        }

        @Override // io.grpc.Metadata.TrustedAsciiMarshaller
        public byte[] toAsciiString(String str) {
            byte[] bytes = str.getBytes(Charsets.UTF_8);
            for (int i = 0; i < bytes.length; i++) {
                if (isEscapingChar(bytes[i])) {
                    return toAsciiStringSlow(bytes, i);
                }
            }
            return bytes;
        }

        private static byte[] toAsciiStringSlow(byte[] bArr, int i) {
            byte[] bArr2 = new byte[((bArr.length - i) * 3) + i];
            if (i != 0) {
                System.arraycopy(bArr, 0, bArr2, 0, i);
            }
            int i2 = i;
            while (i < bArr.length) {
                byte b = bArr[i];
                if (isEscapingChar(b)) {
                    bArr2[i2] = 37;
                    byte[] bArr3 = HEX;
                    bArr2[i2 + 1] = bArr3[(b >> 4) & 15];
                    bArr2[i2 + 2] = bArr3[b & 15];
                    i2 += 3;
                } else {
                    bArr2[i2] = b;
                    i2++;
                }
                i++;
            }
            return Arrays.copyOf(bArr2, i2);
        }

        @Override // io.grpc.Metadata.TrustedAsciiMarshaller
        public String parseAsciiString(byte[] bArr) {
            for (int i = 0; i < bArr.length; i++) {
                byte b = bArr[i];
                if (b < 32 || b >= 126 || (b == 37 && i + 2 < bArr.length)) {
                    return parseAsciiStringSlow(bArr);
                }
            }
            return new String(bArr, 0);
        }

        private static String parseAsciiStringSlow(byte[] bArr) {
            ByteBuffer allocate = ByteBuffer.allocate(bArr.length);
            int i = 0;
            while (i < bArr.length) {
                if (bArr[i] == 37 && i + 2 < bArr.length) {
                    try {
                        allocate.put((byte) Integer.parseInt(new String(bArr, i + 1, 2, Charsets.US_ASCII), 16));
                        i += 3;
                    } catch (NumberFormatException unused) {
                    }
                }
                allocate.put(bArr[i]);
                i++;
            }
            return new String(allocate.array(), 0, allocate.position(), Charsets.UTF_8);
        }
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public int hashCode() {
        return super.hashCode();
    }
}
