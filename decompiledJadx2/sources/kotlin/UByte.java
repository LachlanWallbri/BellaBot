package kotlin;

import com.iflytek.cloud.SpeechConstant;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;

/* compiled from: UByte.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0005\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0087@\u0018\u0000 f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001fB\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0000H\u0097\nø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0010H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0013H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0005J\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u000fJ\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u0012J\u001b\u0010\u001b\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b \u0010\u0018J\u0013\u0010!\u001a\u00020\"2\b\u0010\t\u001a\u0004\u0018\u00010#HÖ\u0003J\t\u0010$\u001a\u00020\rHÖ\u0001J\u0013\u0010%\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b&\u0010\u0005J\u0013\u0010'\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b(\u0010\u0005J\u001b\u0010)\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b*\u0010\u000fJ\u001b\u0010)\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\nø\u0001\u0000¢\u0006\u0004\b+\u0010\u0012J\u001b\u0010)\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\nø\u0001\u0000¢\u0006\u0004\b,\u0010\u001fJ\u001b\u0010)\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b-\u0010\u0018J\u001b\u0010.\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b/\u0010\u000bJ\u001b\u00100\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b1\u0010\u000fJ\u001b\u00100\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\nø\u0001\u0000¢\u0006\u0004\b2\u0010\u0012J\u001b\u00100\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\nø\u0001\u0000¢\u0006\u0004\b3\u0010\u001fJ\u001b\u00100\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b4\u0010\u0018J\u001b\u00105\u001a\u0002062\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b7\u00108J\u001b\u00109\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b:\u0010\u000fJ\u001b\u00109\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\nø\u0001\u0000¢\u0006\u0004\b;\u0010\u0012J\u001b\u00109\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\nø\u0001\u0000¢\u0006\u0004\b<\u0010\u001fJ\u001b\u00109\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b=\u0010\u0018J\u001b\u0010>\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b?\u0010\u000fJ\u001b\u0010>\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\nø\u0001\u0000¢\u0006\u0004\b@\u0010\u0012J\u001b\u0010>\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\nø\u0001\u0000¢\u0006\u0004\bA\u0010\u001fJ\u001b\u0010>\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\bB\u0010\u0018J\u0010\u0010C\u001a\u00020\u0003H\u0087\b¢\u0006\u0004\bD\u0010\u0005J\u0010\u0010E\u001a\u00020FH\u0087\b¢\u0006\u0004\bG\u0010HJ\u0010\u0010I\u001a\u00020JH\u0087\b¢\u0006\u0004\bK\u0010LJ\u0010\u0010M\u001a\u00020\rH\u0087\b¢\u0006\u0004\bN\u0010OJ\u0010\u0010P\u001a\u00020QH\u0087\b¢\u0006\u0004\bR\u0010SJ\u0010\u0010T\u001a\u00020UH\u0087\b¢\u0006\u0004\bV\u0010WJ\u000f\u0010X\u001a\u00020YH\u0016¢\u0006\u0004\bZ\u0010[J\u0013\u0010\\\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b]\u0010\u0005J\u0013\u0010^\u001a\u00020\u0010H\u0087\bø\u0001\u0000¢\u0006\u0004\b_\u0010OJ\u0013\u0010`\u001a\u00020\u0013H\u0087\bø\u0001\u0000¢\u0006\u0004\ba\u0010SJ\u0013\u0010b\u001a\u00020\u0016H\u0087\bø\u0001\u0000¢\u0006\u0004\bc\u0010WJ\u001b\u0010d\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\be\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006g"}, m3961d2 = {"Lkotlin/UByte;", "", "data", "", "constructor-impl", "(B)B", "data$annotations", "()V", "and", "other", "and-7apg3OU", "(BB)B", "compareTo", "", "compareTo-7apg3OU", "(BB)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(BI)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(BJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(BS)I", "dec", "dec-impl", "div", "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(BJ)J", "div-xj2QHRw", "equals", "", "", "hashCode", "inc", "inc-impl", "inv", "inv-impl", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "or", "or-7apg3OU", SpeechConstant.MODE_PLUS, "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-7apg3OU", "(BB)Lkotlin/ranges/UIntRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "toByte-impl", "toDouble", "", "toDouble-impl", "(B)D", "toFloat", "", "toFloat-impl", "(B)F", "toInt", "toInt-impl", "(B)I", "toLong", "", "toLong-impl", "(B)J", "toShort", "", "toShort-impl", "(B)S", "toString", "", "toString-impl", "(B)Ljava/lang/String;", "toUByte", "toUByte-impl", "toUInt", "toUInt-impl", "toULong", "toULong-impl", "toUShort", "toUShort-impl", "xor", "xor-7apg3OU", "Companion", "kotlin-stdlib"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class UByte implements Comparable<UByte> {
    public static final byte MAX_VALUE = -1;
    public static final byte MIN_VALUE = 0;
    public static final int SIZE_BITS = 8;
    public static final int SIZE_BYTES = 1;
    private final byte data;

    /* renamed from: box-impl */
    public static final /* synthetic */ UByte m4522boximpl(byte b) {
        return new UByte(b);
    }

    /* renamed from: compareTo-7apg3OU */
    private int m4523compareTo7apg3OU(byte b) {
        return m4524compareTo7apg3OU(this.data, b);
    }

    /* renamed from: constructor-impl */
    public static byte m4528constructorimpl(byte b) {
        return b;
    }

    public static /* synthetic */ void data$annotations() {
    }

    /* renamed from: equals-impl */
    public static boolean m4534equalsimpl(byte b, Object obj) {
        return (obj instanceof UByte) && b == ((UByte) obj).getData();
    }

    /* renamed from: equals-impl0 */
    public static final boolean m4535equalsimpl0(byte b, byte b2) {
        return b == b2;
    }

    /* renamed from: hashCode-impl */
    public static int m4536hashCodeimpl(byte b) {
        return b;
    }

    /* renamed from: toByte-impl */
    private static final byte m4557toByteimpl(byte b) {
        return b;
    }

    /* renamed from: toDouble-impl */
    private static final double m4558toDoubleimpl(byte b) {
        return b & 255;
    }

    /* renamed from: toFloat-impl */
    private static final float m4559toFloatimpl(byte b) {
        return b & 255;
    }

    /* renamed from: toInt-impl */
    private static final int m4560toIntimpl(byte b) {
        return b & 255;
    }

    /* renamed from: toLong-impl */
    private static final long m4561toLongimpl(byte b) {
        return b & 255;
    }

    /* renamed from: toShort-impl */
    private static final short m4562toShortimpl(byte b) {
        return (short) (b & 255);
    }

    /* renamed from: toUByte-impl */
    private static final byte m4564toUByteimpl(byte b) {
        return b;
    }

    public boolean equals(Object other) {
        return m4534equalsimpl(this.data, other);
    }

    public int hashCode() {
        return m4536hashCodeimpl(this.data);
    }

    public String toString() {
        return m4563toStringimpl(this.data);
    }

    /* renamed from: unbox-impl, reason: from getter */
    public final /* synthetic */ byte getData() {
        return this.data;
    }

    private /* synthetic */ UByte(byte b) {
        this.data = b;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(UByte uByte) {
        return m4523compareTo7apg3OU(uByte.getData());
    }

    /* renamed from: compareTo-7apg3OU */
    private static int m4524compareTo7apg3OU(byte b, byte b2) {
        return Intrinsics.compare(b & 255, b2 & 255);
    }

    /* renamed from: compareTo-xj2QHRw */
    private static final int m4527compareToxj2QHRw(byte b, short s) {
        return Intrinsics.compare(b & 255, s & UShort.MAX_VALUE);
    }

    /* renamed from: compareTo-WZ4Q5Ns */
    private static final int m4526compareToWZ4Q5Ns(byte b, int i) {
        return UnsignedKt.uintCompare(UInt.m4595constructorimpl(b & 255), i);
    }

    /* renamed from: compareTo-VKZWuLQ */
    private static final int m4525compareToVKZWuLQ(byte b, long j) {
        return UnsignedKt.ulongCompare(ULong.m4664constructorimpl(b & 255), j);
    }

    /* renamed from: plus-7apg3OU */
    private static final int m4544plus7apg3OU(byte b, byte b2) {
        return UInt.m4595constructorimpl(UInt.m4595constructorimpl(b & 255) + UInt.m4595constructorimpl(b2 & 255));
    }

    /* renamed from: plus-xj2QHRw */
    private static final int m4547plusxj2QHRw(byte b, short s) {
        return UInt.m4595constructorimpl(UInt.m4595constructorimpl(b & 255) + UInt.m4595constructorimpl(s & UShort.MAX_VALUE));
    }

    /* renamed from: plus-WZ4Q5Ns */
    private static final int m4546plusWZ4Q5Ns(byte b, int i) {
        return UInt.m4595constructorimpl(UInt.m4595constructorimpl(b & 255) + i);
    }

    /* renamed from: plus-VKZWuLQ */
    private static final long m4545plusVKZWuLQ(byte b, long j) {
        return ULong.m4664constructorimpl(ULong.m4664constructorimpl(b & 255) + j);
    }

    /* renamed from: minus-7apg3OU */
    private static final int m4539minus7apg3OU(byte b, byte b2) {
        return UInt.m4595constructorimpl(UInt.m4595constructorimpl(b & 255) - UInt.m4595constructorimpl(b2 & 255));
    }

    /* renamed from: minus-xj2QHRw */
    private static final int m4542minusxj2QHRw(byte b, short s) {
        return UInt.m4595constructorimpl(UInt.m4595constructorimpl(b & 255) - UInt.m4595constructorimpl(s & UShort.MAX_VALUE));
    }

    /* renamed from: minus-WZ4Q5Ns */
    private static final int m4541minusWZ4Q5Ns(byte b, int i) {
        return UInt.m4595constructorimpl(UInt.m4595constructorimpl(b & 255) - i);
    }

    /* renamed from: minus-VKZWuLQ */
    private static final long m4540minusVKZWuLQ(byte b, long j) {
        return ULong.m4664constructorimpl(ULong.m4664constructorimpl(b & 255) - j);
    }

    /* renamed from: times-7apg3OU */
    private static final int m4553times7apg3OU(byte b, byte b2) {
        return UInt.m4595constructorimpl(UInt.m4595constructorimpl(b & 255) * UInt.m4595constructorimpl(b2 & 255));
    }

    /* renamed from: times-xj2QHRw */
    private static final int m4556timesxj2QHRw(byte b, short s) {
        return UInt.m4595constructorimpl(UInt.m4595constructorimpl(b & 255) * UInt.m4595constructorimpl(s & UShort.MAX_VALUE));
    }

    /* renamed from: times-WZ4Q5Ns */
    private static final int m4555timesWZ4Q5Ns(byte b, int i) {
        return UInt.m4595constructorimpl(UInt.m4595constructorimpl(b & 255) * i);
    }

    /* renamed from: times-VKZWuLQ */
    private static final long m4554timesVKZWuLQ(byte b, long j) {
        return ULong.m4664constructorimpl(ULong.m4664constructorimpl(b & 255) * j);
    }

    /* renamed from: div-7apg3OU */
    private static final int m4530div7apg3OU(byte b, byte b2) {
        return UnsignedKt.m4821uintDivideJ1ME1BU(UInt.m4595constructorimpl(b & 255), UInt.m4595constructorimpl(b2 & 255));
    }

    /* renamed from: div-xj2QHRw */
    private static final int m4533divxj2QHRw(byte b, short s) {
        return UnsignedKt.m4821uintDivideJ1ME1BU(UInt.m4595constructorimpl(b & 255), UInt.m4595constructorimpl(s & UShort.MAX_VALUE));
    }

    /* renamed from: div-WZ4Q5Ns */
    private static final int m4532divWZ4Q5Ns(byte b, int i) {
        return UnsignedKt.m4821uintDivideJ1ME1BU(UInt.m4595constructorimpl(b & 255), i);
    }

    /* renamed from: div-VKZWuLQ */
    private static final long m4531divVKZWuLQ(byte b, long j) {
        return UnsignedKt.m4823ulongDivideeb3DHEI(ULong.m4664constructorimpl(b & 255), j);
    }

    /* renamed from: rem-7apg3OU */
    private static final int m4549rem7apg3OU(byte b, byte b2) {
        return UnsignedKt.m4822uintRemainderJ1ME1BU(UInt.m4595constructorimpl(b & 255), UInt.m4595constructorimpl(b2 & 255));
    }

    /* renamed from: rem-xj2QHRw */
    private static final int m4552remxj2QHRw(byte b, short s) {
        return UnsignedKt.m4822uintRemainderJ1ME1BU(UInt.m4595constructorimpl(b & 255), UInt.m4595constructorimpl(s & UShort.MAX_VALUE));
    }

    /* renamed from: rem-WZ4Q5Ns */
    private static final int m4551remWZ4Q5Ns(byte b, int i) {
        return UnsignedKt.m4822uintRemainderJ1ME1BU(UInt.m4595constructorimpl(b & 255), i);
    }

    /* renamed from: rem-VKZWuLQ */
    private static final long m4550remVKZWuLQ(byte b, long j) {
        return UnsignedKt.m4824ulongRemaindereb3DHEI(ULong.m4664constructorimpl(b & 255), j);
    }

    /* renamed from: inc-impl */
    private static final byte m4537incimpl(byte b) {
        return m4528constructorimpl((byte) (b + 1));
    }

    /* renamed from: dec-impl */
    private static final byte m4529decimpl(byte b) {
        return m4528constructorimpl((byte) (b - 1));
    }

    /* renamed from: rangeTo-7apg3OU */
    private static final UIntRange m4548rangeTo7apg3OU(byte b, byte b2) {
        return new UIntRange(UInt.m4595constructorimpl(b & 255), UInt.m4595constructorimpl(b2 & 255), null);
    }

    /* renamed from: and-7apg3OU */
    private static final byte m4521and7apg3OU(byte b, byte b2) {
        return m4528constructorimpl((byte) (b & b2));
    }

    /* renamed from: or-7apg3OU */
    private static final byte m4543or7apg3OU(byte b, byte b2) {
        return m4528constructorimpl((byte) (b | b2));
    }

    /* renamed from: xor-7apg3OU */
    private static final byte m4568xor7apg3OU(byte b, byte b2) {
        return m4528constructorimpl((byte) (b ^ b2));
    }

    /* renamed from: inv-impl */
    private static final byte m4538invimpl(byte b) {
        return m4528constructorimpl((byte) (~b));
    }

    /* renamed from: toUShort-impl */
    private static final short m4567toUShortimpl(byte b) {
        return UShort.m4761constructorimpl((short) (b & 255));
    }

    /* renamed from: toUInt-impl */
    private static final int m4565toUIntimpl(byte b) {
        return UInt.m4595constructorimpl(b & 255);
    }

    /* renamed from: toULong-impl */
    private static final long m4566toULongimpl(byte b) {
        return ULong.m4664constructorimpl(b & 255);
    }

    /* renamed from: toString-impl */
    public static String m4563toStringimpl(byte b) {
        return String.valueOf(b & 255);
    }
}
