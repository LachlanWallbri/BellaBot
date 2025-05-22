package org.apache.commons.compress.harmony.unpack200;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TimeZone;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.zip.CRC32;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.Attribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPClass;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPField;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPMethod;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPUTF8;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassConstantPool;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassFile;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry;
import org.apache.commons.compress.harmony.unpack200.bytecode.InnerClassesAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.SourceFileAttribute;

/* loaded from: classes9.dex */
public class Segment {
    public static final int LOG_LEVEL_QUIET = 0;
    public static final int LOG_LEVEL_STANDARD = 1;
    public static final int LOG_LEVEL_VERBOSE = 2;
    private AttrDefinitionBands attrDefinitionBands;
    private BcBands bcBands;
    private ClassBands classBands;
    private byte[][] classFilesContents;
    private CpBands cpBands;
    private boolean deflateHint;
    private boolean doPreRead;
    private FileBands fileBands;
    private boolean[] fileDeflate;
    private boolean[] fileIsClass;
    private SegmentHeader header;
    private IcBands icBands;
    private InputStream internalBuffer;
    private int logLevel;
    private PrintWriter logStream;
    private boolean overrideDeflateHint;

    private ClassFile buildClassFile(int i) throws Pack200Exception {
        CPClass cpClassValue;
        CPUTF8 cputf8;
        CPClass cPClass;
        ClassFile classFile = new ClassFile();
        int[] classVersionMajor = this.classBands.getClassVersionMajor();
        int[] classVersionMinor = this.classBands.getClassVersionMinor();
        if (classVersionMajor != null) {
            classFile.major = classVersionMajor[i];
            classFile.minor = classVersionMinor[i];
        } else {
            classFile.major = this.header.getDefaultClassMajorVersion();
            classFile.minor = this.header.getDefaultClassMinorVersion();
        }
        ClassConstantPool classConstantPool = classFile.pool;
        int i2 = this.classBands.getClassThisInts()[i];
        String str = this.cpBands.getCpClass()[i2];
        int lastIndexOf = str.lastIndexOf("/") + 1;
        ArrayList arrayList = this.classBands.getClassAttributes()[i];
        int i3 = 0;
        SourceFileAttribute sourceFileAttribute = null;
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            if (((Attribute) arrayList.get(i4)).isSourceFileAttribute()) {
                sourceFileAttribute = (SourceFileAttribute) arrayList.get(i4);
            }
        }
        if (sourceFileAttribute != null) {
            classFile.attributes = new Attribute[]{(Attribute) classConstantPool.add(sourceFileAttribute)};
        } else if (this.attrDefinitionBands.getAttributeDefinitionMap().getAttributeLayout(AttributeLayout.ATTRIBUTE_SOURCE_FILE, 0).matches(this.classBands.getRawClassFlags()[i])) {
            int i5 = -1;
            for (int i6 = 0; i6 < str.length(); i6++) {
                if (str.charAt(i6) <= '$') {
                    i5 = i6;
                }
            }
            classFile.attributes = new Attribute[]{(Attribute) classConstantPool.add(new SourceFileAttribute(this.cpBands.cpUTF8Value((i5 > -1 && lastIndexOf <= i5) ? str.substring(lastIndexOf, i5) + ".java" : str.substring(lastIndexOf) + ".java", false)))};
        } else {
            classFile.attributes = new Attribute[0];
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        for (int i7 = 0; i7 < arrayList.size(); i7++) {
            Attribute attribute = (Attribute) arrayList.get(i7);
            if (!attribute.isSourceFileAttribute()) {
                arrayList2.add(attribute);
            }
        }
        Attribute[] attributeArr = classFile.attributes;
        classFile.attributes = new Attribute[attributeArr.length + arrayList2.size()];
        System.arraycopy(attributeArr, 0, classFile.attributes, 0, attributeArr.length);
        for (int i8 = 0; i8 < arrayList2.size(); i8++) {
            Attribute attribute2 = (Attribute) arrayList2.get(i8);
            classConstantPool.add(attribute2);
            classFile.attributes[attributeArr.length + i8] = attribute2;
        }
        ClassFileEntry add = classConstantPool.add(this.cpBands.cpClassValue(i2));
        ClassFileEntry add2 = classConstantPool.add(this.cpBands.cpClassValue(this.classBands.getClassSuperInts()[i]));
        ClassFileEntry[] classFileEntryArr = new ClassFileEntry[this.classBands.getClassInterfacesInts()[i].length];
        for (int i9 = 0; i9 < classFileEntryArr.length; i9++) {
            classFileEntryArr[i9] = classConstantPool.add(this.cpBands.cpClassValue(this.classBands.getClassInterfacesInts()[i][i9]));
        }
        ClassFileEntry[] classFileEntryArr2 = new ClassFileEntry[this.classBands.getClassFieldCount()[i]];
        for (int i10 = 0; i10 < classFileEntryArr2.length; i10++) {
            int i11 = this.classBands.getFieldDescrInts()[i][i10];
            classFileEntryArr2[i10] = classConstantPool.add(new CPField(this.cpBands.cpUTF8Value(this.cpBands.getCpDescriptorNameInts()[i11]), this.cpBands.cpSignatureValue(this.cpBands.getCpDescriptorTypeInts()[i11]), this.classBands.getFieldFlags()[i][i10], this.classBands.getFieldAttributes()[i][i10]));
        }
        ClassFileEntry[] classFileEntryArr3 = new ClassFileEntry[this.classBands.getClassMethodCount()[i]];
        for (int i12 = 0; i12 < classFileEntryArr3.length; i12++) {
            int i13 = this.classBands.getMethodDescrInts()[i][i12];
            classFileEntryArr3[i12] = classConstantPool.add(new CPMethod(this.cpBands.cpUTF8Value(this.cpBands.getCpDescriptorNameInts()[i13]), this.cpBands.cpSignatureValue(this.cpBands.getCpDescriptorTypeInts()[i13]), this.classBands.getMethodFlags()[i][i12], this.classBands.getMethodAttributes()[i][i12]));
        }
        classConstantPool.addNestedEntries();
        IcTuple[] icTupleArr = getClassBands().getIcLocal()[i];
        boolean z = icTupleArr != null;
        InnerClassesAttribute innerClassesAttribute = new InnerClassesAttribute(AttributeLayout.ATTRIBUTE_INNER_CLASSES);
        IcTuple[] relevantIcTuples = getIcBands().getRelevantIcTuples(str, classConstantPool);
        List computeIcStored = computeIcStored(icTupleArr, relevantIcTuples);
        boolean z2 = false;
        while (i3 < computeIcStored.size()) {
            IcTuple icTuple = (IcTuple) computeIcStored.get(i3);
            int thisClassIndex = icTuple.thisClassIndex();
            List list = computeIcStored;
            int outerClassIndex = icTuple.outerClassIndex();
            ClassFileEntry[] classFileEntryArr4 = classFileEntryArr3;
            int simpleClassNameIndex = icTuple.simpleClassNameIndex();
            ClassFileEntry[] classFileEntryArr5 = classFileEntryArr2;
            String thisClassString = icTuple.thisClassString();
            ClassFileEntry[] classFileEntryArr6 = classFileEntryArr;
            String outerClassString = icTuple.outerClassString();
            ClassFileEntry classFileEntry = add2;
            String simpleClassName = icTuple.simpleClassName();
            ClassFileEntry classFileEntry2 = add;
            if (thisClassIndex != -1) {
                cpClassValue = this.cpBands.cpClassValue(thisClassIndex);
            } else {
                cpClassValue = this.cpBands.cpClassValue(thisClassString);
            }
            if (icTuple.isAnonymous()) {
                cputf8 = null;
            } else if (simpleClassNameIndex != -1) {
                cputf8 = this.cpBands.cpUTF8Value(simpleClassNameIndex);
            } else {
                cputf8 = this.cpBands.cpUTF8Value(simpleClassName);
            }
            if (!icTuple.isMember()) {
                cPClass = null;
            } else if (outerClassIndex != -1) {
                cPClass = this.cpBands.cpClassValue(outerClassIndex);
            } else {
                cPClass = this.cpBands.cpClassValue(outerClassString);
            }
            innerClassesAttribute.addInnerClassesEntry(cpClassValue, cPClass, cputf8, icTuple.f8967F);
            i3++;
            computeIcStored = list;
            classFileEntryArr3 = classFileEntryArr4;
            classFileEntryArr2 = classFileEntryArr5;
            classFileEntryArr = classFileEntryArr6;
            add2 = classFileEntry;
            add = classFileEntry2;
            z2 = true;
        }
        ClassFileEntry classFileEntry3 = add;
        ClassFileEntry classFileEntry4 = add2;
        ClassFileEntry[] classFileEntryArr7 = classFileEntryArr;
        ClassFileEntry[] classFileEntryArr8 = classFileEntryArr2;
        ClassFileEntry[] classFileEntryArr9 = classFileEntryArr3;
        boolean z3 = (z && icTupleArr.length == 0) ? false : z2;
        if (!z && relevantIcTuples.length == 0) {
            z3 = false;
        }
        if (z3) {
            Attribute[] attributeArr2 = classFile.attributes;
            Attribute[] attributeArr3 = new Attribute[attributeArr2.length + 1];
            for (int i14 = 0; i14 < attributeArr2.length; i14++) {
                attributeArr3[i14] = attributeArr2[i14];
            }
            attributeArr3[attributeArr3.length - 1] = innerClassesAttribute;
            classFile.attributes = attributeArr3;
            classConstantPool.addWithNestedEntries(innerClassesAttribute);
        }
        classConstantPool.resolve(this);
        classFile.accessFlags = (int) this.classBands.getClassFlags()[i];
        classFile.thisClass = classConstantPool.indexOf(classFileEntry3);
        classFile.superClass = classConstantPool.indexOf(classFileEntry4);
        classFile.interfaces = new int[classFileEntryArr7.length];
        for (int i15 = 0; i15 < classFileEntryArr7.length; i15++) {
            classFile.interfaces[i15] = classConstantPool.indexOf(classFileEntryArr7[i15]);
        }
        classFile.fields = classFileEntryArr8;
        classFile.methods = classFileEntryArr9;
        return classFile;
    }

    private List computeIcStored(IcTuple[] icTupleArr, IcTuple[] icTupleArr2) {
        ArrayList arrayList = new ArrayList(icTupleArr2.length);
        ArrayList arrayList2 = new ArrayList(icTupleArr2.length);
        HashSet hashSet = new HashSet(icTupleArr2.length);
        if (icTupleArr != null) {
            for (int i = 0; i < icTupleArr.length; i++) {
                if (hashSet.add(icTupleArr[i])) {
                    arrayList.add(icTupleArr[i]);
                }
            }
        }
        for (int i2 = 0; i2 < icTupleArr2.length; i2++) {
            if (hashSet.add(icTupleArr2[i2])) {
                arrayList.add(icTupleArr2[i2]);
            } else {
                arrayList2.add(icTupleArr2[i2]);
            }
        }
        for (int i3 = 0; i3 < arrayList2.size(); i3++) {
            arrayList.remove((IcTuple) arrayList2.get(i3));
        }
        return arrayList;
    }

    private void readSegment(InputStream inputStream) throws IOException, Pack200Exception {
        log(2, "-------");
        this.cpBands = new CpBands(this);
        this.cpBands.read(inputStream);
        this.attrDefinitionBands = new AttrDefinitionBands(this);
        this.attrDefinitionBands.read(inputStream);
        this.icBands = new IcBands(this);
        this.icBands.read(inputStream);
        this.classBands = new ClassBands(this);
        this.classBands.read(inputStream);
        this.bcBands = new BcBands(this);
        this.bcBands.read(inputStream);
        this.fileBands = new FileBands(this);
        this.fileBands.read(inputStream);
        this.fileBands.processFileBits();
    }

    private void parseSegment() throws IOException, Pack200Exception {
        this.header.unpack();
        this.cpBands.unpack();
        this.attrDefinitionBands.unpack();
        this.icBands.unpack();
        this.classBands.unpack();
        this.bcBands.unpack();
        this.fileBands.unpack();
        int numberOfFiles = this.header.getNumberOfFiles();
        String[] fileName = this.fileBands.getFileName();
        int[] fileOptions = this.fileBands.getFileOptions();
        SegmentOptions options = this.header.getOptions();
        this.classFilesContents = new byte[numberOfFiles];
        this.fileDeflate = new boolean[numberOfFiles];
        this.fileIsClass = new boolean[numberOfFiles];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        int i = 0;
        for (int i2 = 0; i2 < numberOfFiles; i2++) {
            String str = fileName[i2];
            boolean z = true;
            boolean z2 = str == null || str.equals("");
            boolean z3 = (fileOptions[i2] & 2) == 2 || z2;
            if (z3 && z2) {
                fileName[i2] = this.cpBands.getCpClass()[this.classBands.getClassThisInts()[i]] + ".class";
            }
            if (!this.overrideDeflateHint) {
                boolean[] zArr = this.fileDeflate;
                if ((fileOptions[i2] & 1) != 1 && !options.shouldDeflate()) {
                    z = false;
                }
                zArr[i2] = z;
            } else {
                this.fileDeflate[i2] = this.deflateHint;
            }
            this.fileIsClass[i2] = z3;
            if (z3) {
                buildClassFile(i).write(dataOutputStream);
                dataOutputStream.flush();
                this.classFilesContents[i] = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.reset();
                i++;
            }
        }
    }

    public void unpack(InputStream inputStream, JarOutputStream jarOutputStream) throws IOException, Pack200Exception {
        unpackRead(inputStream);
        unpackProcess();
        unpackWrite(jarOutputStream);
    }

    void unpackRead(InputStream inputStream) throws IOException, Pack200Exception {
        if (!inputStream.markSupported()) {
            inputStream = new BufferedInputStream(inputStream);
        }
        this.header = new SegmentHeader(this);
        this.header.read(inputStream);
        int archiveSize = ((int) this.header.getArchiveSize()) - this.header.getArchiveSizeOffset();
        if (this.doPreRead && this.header.getArchiveSize() != 0) {
            byte[] bArr = new byte[archiveSize];
            inputStream.read(bArr);
            this.internalBuffer = new BufferedInputStream(new ByteArrayInputStream(bArr));
            return;
        }
        readSegment(inputStream);
    }

    void unpackProcess() throws IOException, Pack200Exception {
        InputStream inputStream = this.internalBuffer;
        if (inputStream != null) {
            readSegment(inputStream);
        }
        parseSegment();
    }

    void unpackWrite(JarOutputStream jarOutputStream) throws IOException, Pack200Exception {
        writeJar(jarOutputStream);
        PrintWriter printWriter = this.logStream;
        if (printWriter != null) {
            printWriter.close();
        }
    }

    public void writeJar(JarOutputStream jarOutputStream) throws IOException, Pack200Exception {
        String[] strArr;
        int[] iArr;
        String[] fileName = this.fileBands.getFileName();
        int[] fileModtime = this.fileBands.getFileModtime();
        long[] fileSize = this.fileBands.getFileSize();
        byte[][] fileBits = this.fileBands.getFileBits();
        int numberOfFiles = this.header.getNumberOfFiles();
        long archiveModtime = this.header.getArchiveModtime();
        int i = 0;
        int i2 = 0;
        while (i < numberOfFiles) {
            String str = fileName[i];
            int i3 = i;
            long j = (fileModtime[i] + archiveModtime) * 1000;
            boolean z = this.fileDeflate[i3];
            JarEntry jarEntry = new JarEntry(str);
            if (z) {
                jarEntry.setMethod(8);
                strArr = fileName;
                iArr = fileModtime;
            } else {
                jarEntry.setMethod(0);
                CRC32 crc32 = new CRC32();
                if (this.fileIsClass[i3]) {
                    crc32.update(this.classFilesContents[i2]);
                    jarEntry.setSize(this.classFilesContents[i2].length);
                    strArr = fileName;
                    iArr = fileModtime;
                    crc32 = crc32;
                } else {
                    crc32.update(fileBits[i3]);
                    strArr = fileName;
                    iArr = fileModtime;
                    jarEntry.setSize(fileSize[i3]);
                }
                jarEntry.setCrc(crc32.getValue());
            }
            jarEntry.setTime(j - TimeZone.getDefault().getRawOffset());
            jarOutputStream.putNextEntry(jarEntry);
            if (this.fileIsClass[i3]) {
                jarEntry.setSize(this.classFilesContents[i2].length);
                jarOutputStream.write(this.classFilesContents[i2]);
                i2++;
            } else {
                jarEntry.setSize(fileSize[i3]);
                jarOutputStream.write(fileBits[i3]);
            }
            i = i3 + 1;
            fileName = strArr;
            fileModtime = iArr;
        }
    }

    public SegmentConstantPool getConstantPool() {
        return this.cpBands.getConstantPool();
    }

    public SegmentHeader getSegmentHeader() {
        return this.header;
    }

    public void setPreRead(boolean z) {
        this.doPreRead = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AttrDefinitionBands getAttrDefinitionBands() {
        return this.attrDefinitionBands;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ClassBands getClassBands() {
        return this.classBands;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CpBands getCpBands() {
        return this.cpBands;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public IcBands getIcBands() {
        return this.icBands;
    }

    public void setLogLevel(int i) {
        this.logLevel = i;
    }

    public void setLogStream(OutputStream outputStream) {
        this.logStream = new PrintWriter(outputStream);
    }

    public void log(int i, String str) {
        if (this.logLevel >= i) {
            this.logStream.println(str);
        }
    }

    public void overrideDeflateHint(boolean z) {
        this.overrideDeflateHint = true;
        this.deflateHint = z;
    }
}
