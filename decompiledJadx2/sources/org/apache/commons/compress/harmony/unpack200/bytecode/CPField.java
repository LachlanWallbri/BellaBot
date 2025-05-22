package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.util.List;

/* loaded from: classes9.dex */
public class CPField extends CPMember {
    public CPField(CPUTF8 cputf8, CPUTF8 cputf82, long j, List list) {
        super(cputf8, cputf82, j, list);
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.CPMember, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public String toString() {
        return "Field: " + this.name + "(" + this.descriptor + ")";
    }
}
