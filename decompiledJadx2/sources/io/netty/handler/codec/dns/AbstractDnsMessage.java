package io.netty.handler.codec.dns;

import io.netty.util.AbstractReferenceCounted;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.ReferenceCounted;
import io.netty.util.ResourceLeakDetector;
import io.netty.util.ResourceLeakDetectorFactory;
import io.netty.util.ResourceLeakTracker;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.StringUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.UShort;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public abstract class AbstractDnsMessage extends AbstractReferenceCounted implements DnsMessage {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int SECTION_COUNT = 4;
    private Object additionals;
    private Object answers;
    private Object authorities;

    /* renamed from: id */
    private short f8482id;
    private final ResourceLeakTracker<DnsMessage> leak;
    private DnsOpCode opCode;
    private Object questions;
    private boolean recursionDesired;

    /* renamed from: z */
    private byte f8483z;
    private static final ResourceLeakDetector<DnsMessage> leakDetector = ResourceLeakDetectorFactory.instance().newResourceLeakDetector(DnsMessage.class);
    private static final int SECTION_QUESTION = DnsSection.QUESTION.ordinal();

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractDnsMessage(int i) {
        this(i, DnsOpCode.QUERY);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractDnsMessage(int i, DnsOpCode dnsOpCode) {
        this.leak = leakDetector.track(this);
        setId(i);
        setOpCode(dnsOpCode);
    }

    @Override // io.netty.handler.codec.dns.DnsMessage
    /* renamed from: id */
    public int mo3938id() {
        return this.f8482id & UShort.MAX_VALUE;
    }

    @Override // io.netty.handler.codec.dns.DnsMessage
    public DnsMessage setId(int i) {
        this.f8482id = (short) i;
        return this;
    }

    @Override // io.netty.handler.codec.dns.DnsMessage
    public DnsOpCode opCode() {
        return this.opCode;
    }

    @Override // io.netty.handler.codec.dns.DnsMessage
    public DnsMessage setOpCode(DnsOpCode dnsOpCode) {
        this.opCode = (DnsOpCode) ObjectUtil.checkNotNull(dnsOpCode, "opCode");
        return this;
    }

    @Override // io.netty.handler.codec.dns.DnsMessage
    public boolean isRecursionDesired() {
        return this.recursionDesired;
    }

    @Override // io.netty.handler.codec.dns.DnsMessage
    public DnsMessage setRecursionDesired(boolean z) {
        this.recursionDesired = z;
        return this;
    }

    @Override // io.netty.handler.codec.dns.DnsMessage
    /* renamed from: z */
    public int mo3939z() {
        return this.f8483z;
    }

    @Override // io.netty.handler.codec.dns.DnsMessage
    public DnsMessage setZ(int i) {
        this.f8483z = (byte) (i & 7);
        return this;
    }

    @Override // io.netty.handler.codec.dns.DnsMessage
    public int count(DnsSection dnsSection) {
        return count(sectionOrdinal(dnsSection));
    }

    private int count(int i) {
        Object sectionAt = sectionAt(i);
        if (sectionAt == null) {
            return 0;
        }
        if (sectionAt instanceof DnsRecord) {
            return 1;
        }
        return ((List) sectionAt).size();
    }

    @Override // io.netty.handler.codec.dns.DnsMessage
    public int count() {
        int i = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            i += count(i2);
        }
        return i;
    }

    @Override // io.netty.handler.codec.dns.DnsMessage
    public <T extends DnsRecord> T recordAt(DnsSection dnsSection) {
        return (T) recordAt(sectionOrdinal(dnsSection));
    }

    private <T extends DnsRecord> T recordAt(int i) {
        Object sectionAt = sectionAt(i);
        if (sectionAt == null) {
            return null;
        }
        if (sectionAt instanceof DnsRecord) {
            return (T) castRecord(sectionAt);
        }
        List list = (List) sectionAt;
        if (list.isEmpty()) {
            return null;
        }
        return (T) castRecord(list.get(0));
    }

    @Override // io.netty.handler.codec.dns.DnsMessage
    public <T extends DnsRecord> T recordAt(DnsSection dnsSection, int i) {
        return (T) recordAt(sectionOrdinal(dnsSection), i);
    }

    private <T extends DnsRecord> T recordAt(int i, int i2) {
        Object sectionAt = sectionAt(i);
        if (sectionAt == null) {
            throw new IndexOutOfBoundsException("index: " + i2 + " (expected: none)");
        }
        if (!(sectionAt instanceof DnsRecord)) {
            return (T) castRecord(((List) sectionAt).get(i2));
        }
        if (i2 == 0) {
            return (T) castRecord(sectionAt);
        }
        throw new IndexOutOfBoundsException("index: " + i2 + "' (expected: 0)");
    }

    @Override // io.netty.handler.codec.dns.DnsMessage
    public DnsMessage setRecord(DnsSection dnsSection, DnsRecord dnsRecord) {
        setRecord(sectionOrdinal(dnsSection), dnsRecord);
        return this;
    }

    private void setRecord(int i, DnsRecord dnsRecord) {
        clear(i);
        setSection(i, checkQuestion(i, dnsRecord));
    }

    @Override // io.netty.handler.codec.dns.DnsMessage
    public <T extends DnsRecord> T setRecord(DnsSection dnsSection, int i, DnsRecord dnsRecord) {
        return (T) setRecord(sectionOrdinal(dnsSection), i, dnsRecord);
    }

    private <T extends DnsRecord> T setRecord(int i, int i2, DnsRecord dnsRecord) {
        checkQuestion(i, dnsRecord);
        Object sectionAt = sectionAt(i);
        if (sectionAt == null) {
            throw new IndexOutOfBoundsException("index: " + i2 + " (expected: none)");
        }
        if (!(sectionAt instanceof DnsRecord)) {
            return (T) castRecord(((List) sectionAt).set(i2, dnsRecord));
        }
        if (i2 == 0) {
            setSection(i, dnsRecord);
            return (T) castRecord(sectionAt);
        }
        throw new IndexOutOfBoundsException("index: " + i2 + " (expected: 0)");
    }

    @Override // io.netty.handler.codec.dns.DnsMessage
    public DnsMessage addRecord(DnsSection dnsSection, DnsRecord dnsRecord) {
        addRecord(sectionOrdinal(dnsSection), dnsRecord);
        return this;
    }

    private void addRecord(int i, DnsRecord dnsRecord) {
        checkQuestion(i, dnsRecord);
        Object sectionAt = sectionAt(i);
        if (sectionAt == null) {
            setSection(i, dnsRecord);
            return;
        }
        if (sectionAt instanceof DnsRecord) {
            ArrayList<DnsRecord> newRecordList = newRecordList();
            newRecordList.add(castRecord(sectionAt));
            newRecordList.add(dnsRecord);
            setSection(i, newRecordList);
            return;
        }
        ((List) sectionAt).add(dnsRecord);
    }

    @Override // io.netty.handler.codec.dns.DnsMessage
    public DnsMessage addRecord(DnsSection dnsSection, int i, DnsRecord dnsRecord) {
        addRecord(sectionOrdinal(dnsSection), i, dnsRecord);
        return this;
    }

    private void addRecord(int i, int i2, DnsRecord dnsRecord) {
        ArrayList<DnsRecord> newRecordList;
        checkQuestion(i, dnsRecord);
        Object sectionAt = sectionAt(i);
        if (sectionAt == null) {
            if (i2 != 0) {
                throw new IndexOutOfBoundsException("index: " + i2 + " (expected: 0)");
            }
            setSection(i, dnsRecord);
            return;
        }
        if (sectionAt instanceof DnsRecord) {
            if (i2 == 0) {
                newRecordList = newRecordList();
                newRecordList.add(dnsRecord);
                newRecordList.add(castRecord(sectionAt));
            } else if (i2 == 1) {
                newRecordList = newRecordList();
                newRecordList.add(castRecord(sectionAt));
                newRecordList.add(dnsRecord);
            } else {
                throw new IndexOutOfBoundsException("index: " + i2 + " (expected: 0 or 1)");
            }
            setSection(i, newRecordList);
            return;
        }
        ((List) sectionAt).add(i2, dnsRecord);
    }

    @Override // io.netty.handler.codec.dns.DnsMessage
    public <T extends DnsRecord> T removeRecord(DnsSection dnsSection, int i) {
        return (T) removeRecord(sectionOrdinal(dnsSection), i);
    }

    private <T extends DnsRecord> T removeRecord(int i, int i2) {
        Object sectionAt = sectionAt(i);
        if (sectionAt == null) {
            throw new IndexOutOfBoundsException("index: " + i2 + " (expected: none)");
        }
        if (!(sectionAt instanceof DnsRecord)) {
            return (T) castRecord(((List) sectionAt).remove(i2));
        }
        if (i2 != 0) {
            throw new IndexOutOfBoundsException("index: " + i2 + " (expected: 0)");
        }
        T t = (T) castRecord(sectionAt);
        setSection(i, null);
        return t;
    }

    @Override // io.netty.handler.codec.dns.DnsMessage
    public DnsMessage clear(DnsSection dnsSection) {
        clear(sectionOrdinal(dnsSection));
        return this;
    }

    @Override // io.netty.handler.codec.dns.DnsMessage
    public DnsMessage clear() {
        for (int i = 0; i < 4; i++) {
            clear(i);
        }
        return this;
    }

    private void clear(int i) {
        Object sectionAt = sectionAt(i);
        setSection(i, null);
        if (sectionAt instanceof ReferenceCounted) {
            ((ReferenceCounted) sectionAt).release();
            return;
        }
        if (sectionAt instanceof List) {
            List list = (List) sectionAt;
            if (list.isEmpty()) {
                return;
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ReferenceCountUtil.release(it.next());
            }
        }
    }

    @Override // io.netty.util.AbstractReferenceCounted, io.netty.util.ReferenceCounted
    public DnsMessage touch() {
        return (DnsMessage) super.touch();
    }

    @Override // io.netty.util.ReferenceCounted
    public DnsMessage touch(Object obj) {
        ResourceLeakTracker<DnsMessage> resourceLeakTracker = this.leak;
        if (resourceLeakTracker != null) {
            resourceLeakTracker.record(obj);
        }
        return this;
    }

    @Override // io.netty.util.AbstractReferenceCounted, io.netty.util.ReferenceCounted
    public DnsMessage retain() {
        return (DnsMessage) super.retain();
    }

    @Override // io.netty.util.AbstractReferenceCounted, io.netty.util.ReferenceCounted
    public DnsMessage retain(int i) {
        return (DnsMessage) super.retain(i);
    }

    @Override // io.netty.util.AbstractReferenceCounted
    protected void deallocate() {
        clear();
        ResourceLeakTracker<DnsMessage> resourceLeakTracker = this.leak;
        if (resourceLeakTracker != null) {
            resourceLeakTracker.close(this);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DnsMessage)) {
            return false;
        }
        DnsMessage dnsMessage = (DnsMessage) obj;
        if (mo3938id() != dnsMessage.mo3938id()) {
            return false;
        }
        if (this instanceof DnsQuery) {
            if (!(dnsMessage instanceof DnsQuery)) {
                return false;
            }
        } else if (dnsMessage instanceof DnsQuery) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (mo3938id() * 31) + (!(this instanceof DnsQuery) ? 1 : 0);
    }

    private Object sectionAt(int i) {
        if (i == 0) {
            return this.questions;
        }
        if (i == 1) {
            return this.answers;
        }
        if (i == 2) {
            return this.authorities;
        }
        if (i == 3) {
            return this.additionals;
        }
        throw new Error();
    }

    private void setSection(int i, Object obj) {
        if (i == 0) {
            this.questions = obj;
            return;
        }
        if (i == 1) {
            this.answers = obj;
        } else if (i == 2) {
            this.authorities = obj;
        } else {
            if (i == 3) {
                this.additionals = obj;
                return;
            }
            throw new Error();
        }
    }

    private static int sectionOrdinal(DnsSection dnsSection) {
        return ((DnsSection) ObjectUtil.checkNotNull(dnsSection, "section")).ordinal();
    }

    private static DnsRecord checkQuestion(int i, DnsRecord dnsRecord) {
        if (i != SECTION_QUESTION || (ObjectUtil.checkNotNull(dnsRecord, "record") instanceof DnsQuestion)) {
            return dnsRecord;
        }
        throw new IllegalArgumentException("record: " + dnsRecord + " (expected: " + StringUtil.simpleClassName((Class<?>) DnsQuestion.class) + ')');
    }

    private static <T extends DnsRecord> T castRecord(Object obj) {
        return (T) obj;
    }

    private static ArrayList<DnsRecord> newRecordList() {
        return new ArrayList<>(2);
    }
}
