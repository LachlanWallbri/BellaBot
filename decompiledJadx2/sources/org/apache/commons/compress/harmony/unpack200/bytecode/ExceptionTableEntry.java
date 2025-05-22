package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

/* loaded from: classes9.dex */
public class ExceptionTableEntry {
    private final CPClass catchType;
    private int catchTypeIndex;
    private final int endPC;
    private int endPcRenumbered;
    private final int handlerPC;
    private int handlerPcRenumbered;
    private final int startPC;
    private int startPcRenumbered;

    public ExceptionTableEntry(int i, int i2, int i3, CPClass cPClass) {
        this.startPC = i;
        this.endPC = i2;
        this.handlerPC = i3;
        this.catchType = cPClass;
    }

    public void write(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.startPcRenumbered);
        dataOutputStream.writeShort(this.endPcRenumbered);
        dataOutputStream.writeShort(this.handlerPcRenumbered);
        dataOutputStream.writeShort(this.catchTypeIndex);
    }

    public void renumber(List list) {
        this.startPcRenumbered = ((Integer) list.get(this.startPC)).intValue();
        int i = this.startPC + this.endPC;
        this.endPcRenumbered = ((Integer) list.get(i)).intValue();
        this.handlerPcRenumbered = ((Integer) list.get(i + this.handlerPC)).intValue();
    }

    public CPClass getCatchType() {
        return this.catchType;
    }

    public void resolve(ClassConstantPool classConstantPool) {
        CPClass cPClass = this.catchType;
        if (cPClass == null) {
            this.catchTypeIndex = 0;
        } else {
            cPClass.resolve(classConstantPool);
            this.catchTypeIndex = classConstantPool.indexOf(this.catchType);
        }
    }
}
