package org.apache.commons.compress.harmony.pack200;

import java.io.IOException;
import java.io.OutputStream;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import org.apache.commons.compress.java.util.jar.Pack200;

/* loaded from: classes9.dex */
public class Pack200PackerAdapter extends Pack200Adapter implements Pack200.Packer {
    private final PackingOptions options = new PackingOptions();

    @Override // org.apache.commons.compress.java.util.jar.Pack200.Packer
    public void pack(JarFile jarFile, OutputStream outputStream) throws IOException {
        if (jarFile == null || outputStream == null) {
            throw new IllegalArgumentException("Must specify both input and output streams");
        }
        completed(0.0d);
        try {
            new Archive(jarFile, outputStream, this.options).pack();
            completed(1.0d);
        } catch (Pack200Exception e) {
            throw new IOException("Failed to pack Jar:" + String.valueOf(e));
        }
    }

    @Override // org.apache.commons.compress.java.util.jar.Pack200.Packer
    public void pack(JarInputStream jarInputStream, OutputStream outputStream) throws IOException {
        if (jarInputStream == null || outputStream == null) {
            throw new IllegalArgumentException("Must specify both input and output streams");
        }
        completed(0.0d);
        try {
            new Archive(jarInputStream, outputStream, new PackingOptions()).pack();
            completed(1.0d);
            jarInputStream.close();
        } catch (Pack200Exception e) {
            throw new IOException("Failed to pack Jar:" + String.valueOf(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.pack200.Pack200Adapter
    public void firePropertyChange(String str, Object obj, Object obj2) {
        super.firePropertyChange(str, obj, obj2);
        if (obj2 == null || obj2.equals(obj)) {
            return;
        }
        if (str.startsWith(Pack200.Packer.CLASS_ATTRIBUTE_PFX)) {
            this.options.addClassAttributeAction(str.substring(21), (String) obj2);
            return;
        }
        if (str.startsWith(Pack200.Packer.CODE_ATTRIBUTE_PFX)) {
            this.options.addCodeAttributeAction(str.substring(20), (String) obj2);
            return;
        }
        if (str.equals(Pack200.Packer.DEFLATE_HINT)) {
            this.options.setDeflateHint((String) obj2);
            return;
        }
        if (str.equals(Pack200.Packer.EFFORT)) {
            this.options.setEffort(Integer.parseInt((String) obj2));
            return;
        }
        if (str.startsWith(Pack200.Packer.FIELD_ATTRIBUTE_PFX)) {
            this.options.addFieldAttributeAction(str.substring(21), (String) obj2);
            return;
        }
        if (str.equals(Pack200.Packer.KEEP_FILE_ORDER)) {
            this.options.setKeepFileOrder(Boolean.parseBoolean((String) obj2));
            return;
        }
        if (str.startsWith(Pack200.Packer.METHOD_ATTRIBUTE_PFX)) {
            this.options.addMethodAttributeAction(str.substring(22), (String) obj2);
            return;
        }
        if (str.equals(Pack200.Packer.MODIFICATION_TIME)) {
            this.options.setModificationTime((String) obj2);
            return;
        }
        if (str.startsWith(Pack200.Packer.PASS_FILE_PFX)) {
            if (obj != null && !obj.equals("")) {
                this.options.removePassFile((String) obj);
            }
            this.options.addPassFile((String) obj2);
            return;
        }
        if (str.equals(Pack200.Packer.SEGMENT_LIMIT)) {
            this.options.setSegmentLimit(Long.parseLong((String) obj2));
        } else if (str.equals(Pack200.Packer.UNKNOWN_ATTRIBUTE)) {
            this.options.setUnknownAttributeAction((String) obj2);
        }
    }
}
