package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;

/* loaded from: classes9.dex */
public abstract class BCIRenumberedAttribute extends Attribute {
    protected boolean renumbered;

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected abstract int getLength();

    protected abstract int[] getStartPCs();

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    public boolean hasBCIRenumbering() {
        return true;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public abstract String toString();

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected abstract void writeBody(DataOutputStream dataOutputStream) throws IOException;

    public BCIRenumberedAttribute(CPUTF8 cputf8) {
        super(cputf8);
    }

    public void renumber(List list) throws Pack200Exception {
        if (this.renumbered) {
            throw new Error("Trying to renumber a line number table that has already been renumbered");
        }
        this.renumbered = true;
        int[] startPCs = getStartPCs();
        for (int i = 0; i < startPCs.length; i++) {
            startPCs[i] = ((Integer) list.get(startPCs[i])).intValue();
        }
    }
}
