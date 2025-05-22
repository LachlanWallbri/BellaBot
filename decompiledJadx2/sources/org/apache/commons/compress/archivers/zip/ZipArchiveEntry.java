package org.apache.commons.compress.archivers.zip;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.EntryStreamOffsets;
import org.apache.commons.compress.archivers.zip.ExtraFieldUtils;
import org.apache.commons.compress.utils.ByteUtils;

/* loaded from: classes8.dex */
public class ZipArchiveEntry extends ZipEntry implements ArchiveEntry, EntryStreamOffsets {
    public static final int CRC_UNKNOWN = -1;
    static final ZipArchiveEntry[] EMPTY_ZIP_ARCHIVE_ENTRY_ARRAY = new ZipArchiveEntry[0];
    public static final int PLATFORM_FAT = 0;
    public static final int PLATFORM_UNIX = 3;
    private static final int SHORT_MASK = 65535;
    private static final int SHORT_SHIFT = 16;
    private int alignment;
    private CommentSource commentSource;
    private long dataOffset;
    private long diskNumberStart;
    private long externalAttributes;
    private ZipExtraField[] extraFields;
    private GeneralPurposeBit gpb;
    private int internalAttributes;
    private boolean isStreamContiguous;
    private long localHeaderOffset;
    private int method;
    private String name;
    private NameSource nameSource;
    private int platform;
    private int rawFlag;
    private byte[] rawName;
    private long size;
    private UnparseableExtraFieldData unparseableExtra;
    private int versionMadeBy;
    private int versionRequired;

    /* loaded from: classes8.dex */
    public enum CommentSource {
        COMMENT,
        UNICODE_EXTRA_FIELD
    }

    /* loaded from: classes8.dex */
    public enum NameSource {
        NAME,
        NAME_WITH_EFS_FLAG,
        UNICODE_EXTRA_FIELD
    }

    public ZipArchiveEntry(String str) {
        super(str);
        this.method = -1;
        this.size = -1L;
        this.platform = 0;
        this.gpb = new GeneralPurposeBit();
        this.localHeaderOffset = -1L;
        this.dataOffset = -1L;
        this.nameSource = NameSource.NAME;
        this.commentSource = CommentSource.COMMENT;
        setName(str);
    }

    public ZipArchiveEntry(ZipEntry zipEntry) throws ZipException {
        super(zipEntry);
        this.method = -1;
        this.size = -1L;
        this.platform = 0;
        this.gpb = new GeneralPurposeBit();
        this.localHeaderOffset = -1L;
        this.dataOffset = -1L;
        this.nameSource = NameSource.NAME;
        this.commentSource = CommentSource.COMMENT;
        setName(zipEntry.getName());
        byte[] extra = zipEntry.getExtra();
        if (extra != null) {
            setExtraFields(ExtraFieldUtils.parse(extra, true, (ExtraFieldParsingBehavior) ExtraFieldParsingMode.BEST_EFFORT));
        } else {
            setExtra();
        }
        setMethod(zipEntry.getMethod());
        this.size = zipEntry.getSize();
    }

    public ZipArchiveEntry(ZipArchiveEntry zipArchiveEntry) throws ZipException {
        this((ZipEntry) zipArchiveEntry);
        setInternalAttributes(zipArchiveEntry.getInternalAttributes());
        setExternalAttributes(zipArchiveEntry.getExternalAttributes());
        setExtraFields(getAllExtraFieldsNoCopy());
        setPlatform(zipArchiveEntry.getPlatform());
        GeneralPurposeBit generalPurposeBit = zipArchiveEntry.getGeneralPurposeBit();
        setGeneralPurposeBit(generalPurposeBit == null ? null : (GeneralPurposeBit) generalPurposeBit.clone());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ZipArchiveEntry() {
        this("");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public ZipArchiveEntry(File file, String str) {
        this(str);
        if (file.isDirectory() && !str.endsWith("/")) {
            str = str + "/";
        }
        if (file.isFile()) {
            setSize(file.length());
        }
        setTime(file.lastModified());
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public ZipArchiveEntry(Path path, String str, LinkOption... linkOptionArr) throws IOException {
        this(str);
        if (Files.isDirectory(path, linkOptionArr) && !str.endsWith("/")) {
            str = str + "/";
        }
        if (Files.isRegularFile(path, linkOptionArr)) {
            setSize(Files.size(path));
        }
        setTime(Files.getLastModifiedTime(path, linkOptionArr));
    }

    public void setTime(FileTime fileTime) {
        setTime(fileTime.toMillis());
    }

    @Override // java.util.zip.ZipEntry
    public Object clone() {
        ZipArchiveEntry zipArchiveEntry = (ZipArchiveEntry) super.clone();
        zipArchiveEntry.setInternalAttributes(getInternalAttributes());
        zipArchiveEntry.setExternalAttributes(getExternalAttributes());
        zipArchiveEntry.setExtraFields(getAllExtraFieldsNoCopy());
        return zipArchiveEntry;
    }

    @Override // java.util.zip.ZipEntry
    public int getMethod() {
        return this.method;
    }

    @Override // java.util.zip.ZipEntry
    public void setMethod(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("ZIP compression method can not be negative: " + i);
        }
        this.method = i;
    }

    public int getInternalAttributes() {
        return this.internalAttributes;
    }

    public void setInternalAttributes(int i) {
        this.internalAttributes = i;
    }

    public long getExternalAttributes() {
        return this.externalAttributes;
    }

    public void setExternalAttributes(long j) {
        this.externalAttributes = j;
    }

    public void setUnixMode(int i) {
        setExternalAttributes(((i & 128) == 0 ? 1 : 0) | (i << 16) | (isDirectory() ? 16 : 0));
        this.platform = 3;
    }

    public int getUnixMode() {
        if (this.platform != 3) {
            return 0;
        }
        return (int) ((getExternalAttributes() >> 16) & 65535);
    }

    public boolean isUnixSymlink() {
        return (getUnixMode() & 61440) == 40960;
    }

    public int getPlatform() {
        return this.platform;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setPlatform(int i) {
        this.platform = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getAlignment() {
        return this.alignment;
    }

    public void setAlignment(int i) {
        if (((i - 1) & i) != 0 || i > 65535) {
            throw new IllegalArgumentException("Invalid value for alignment, must be power of two and no bigger than 65535 but is " + i);
        }
        this.alignment = i;
    }

    public void setExtraFields(ZipExtraField[] zipExtraFieldArr) {
        this.unparseableExtra = null;
        ArrayList arrayList = new ArrayList();
        if (zipExtraFieldArr != null) {
            for (ZipExtraField zipExtraField : zipExtraFieldArr) {
                if (zipExtraField instanceof UnparseableExtraFieldData) {
                    this.unparseableExtra = (UnparseableExtraFieldData) zipExtraField;
                } else {
                    arrayList.add(zipExtraField);
                }
            }
        }
        this.extraFields = (ZipExtraField[]) arrayList.toArray(ExtraFieldUtils.EMPTY_ZIP_EXTRA_FIELD_ARRAY);
        setExtra();
    }

    public ZipExtraField[] getExtraFields() {
        return getParseableExtraFields();
    }

    public ZipExtraField[] getExtraFields(boolean z) {
        if (z) {
            return getAllExtraFields();
        }
        return getParseableExtraFields();
    }

    public ZipExtraField[] getExtraFields(ExtraFieldParsingBehavior extraFieldParsingBehavior) throws ZipException {
        ZipExtraField findMatching;
        if (extraFieldParsingBehavior == ExtraFieldParsingMode.BEST_EFFORT) {
            return getExtraFields(true);
        }
        if (extraFieldParsingBehavior == ExtraFieldParsingMode.ONLY_PARSEABLE_LENIENT) {
            return getExtraFields(false);
        }
        ArrayList<ZipExtraField> arrayList = new ArrayList(Arrays.asList(ExtraFieldUtils.parse(getExtra(), true, extraFieldParsingBehavior)));
        ArrayList arrayList2 = new ArrayList(Arrays.asList(ExtraFieldUtils.parse(getCentralDirectoryExtra(), false, extraFieldParsingBehavior)));
        ArrayList arrayList3 = new ArrayList();
        for (ZipExtraField zipExtraField : arrayList) {
            if (zipExtraField instanceof UnparseableExtraFieldData) {
                findMatching = findUnparseable(arrayList2);
            } else {
                findMatching = findMatching(zipExtraField.getHeaderId(), arrayList2);
            }
            if (findMatching != null) {
                byte[] centralDirectoryData = findMatching.getCentralDirectoryData();
                if (centralDirectoryData != null && centralDirectoryData.length > 0) {
                    zipExtraField.parseFromCentralDirectoryData(centralDirectoryData, 0, centralDirectoryData.length);
                }
                arrayList2.remove(findMatching);
            }
            arrayList3.add(zipExtraField);
        }
        arrayList3.addAll(arrayList2);
        return (ZipExtraField[]) arrayList3.toArray(ExtraFieldUtils.EMPTY_ZIP_EXTRA_FIELD_ARRAY);
    }

    private ZipExtraField[] getParseableExtraFieldsNoCopy() {
        ZipExtraField[] zipExtraFieldArr = this.extraFields;
        return zipExtraFieldArr == null ? ExtraFieldUtils.EMPTY_ZIP_EXTRA_FIELD_ARRAY : zipExtraFieldArr;
    }

    private ZipExtraField[] getParseableExtraFields() {
        ZipExtraField[] parseableExtraFieldsNoCopy = getParseableExtraFieldsNoCopy();
        return parseableExtraFieldsNoCopy == this.extraFields ? copyOf(parseableExtraFieldsNoCopy, parseableExtraFieldsNoCopy.length) : parseableExtraFieldsNoCopy;
    }

    private ZipExtraField[] getAllExtraFieldsNoCopy() {
        ZipExtraField[] zipExtraFieldArr = this.extraFields;
        if (zipExtraFieldArr == null) {
            return getUnparseableOnly();
        }
        return this.unparseableExtra != null ? getMergedFields() : zipExtraFieldArr;
    }

    private ZipExtraField[] getMergedFields() {
        ZipExtraField[] zipExtraFieldArr = this.extraFields;
        ZipExtraField[] copyOf = copyOf(zipExtraFieldArr, zipExtraFieldArr.length + 1);
        copyOf[this.extraFields.length] = this.unparseableExtra;
        return copyOf;
    }

    private ZipExtraField[] getUnparseableOnly() {
        UnparseableExtraFieldData unparseableExtraFieldData = this.unparseableExtra;
        return unparseableExtraFieldData == null ? ExtraFieldUtils.EMPTY_ZIP_EXTRA_FIELD_ARRAY : new ZipExtraField[]{unparseableExtraFieldData};
    }

    private ZipExtraField[] getAllExtraFields() {
        ZipExtraField[] allExtraFieldsNoCopy = getAllExtraFieldsNoCopy();
        return allExtraFieldsNoCopy == this.extraFields ? copyOf(allExtraFieldsNoCopy, allExtraFieldsNoCopy.length) : allExtraFieldsNoCopy;
    }

    private ZipExtraField findUnparseable(List<ZipExtraField> list) {
        for (ZipExtraField zipExtraField : list) {
            if (zipExtraField instanceof UnparseableExtraFieldData) {
                return zipExtraField;
            }
        }
        return null;
    }

    private ZipExtraField findMatching(ZipShort zipShort, List<ZipExtraField> list) {
        for (ZipExtraField zipExtraField : list) {
            if (zipShort.equals(zipExtraField.getHeaderId())) {
                return zipExtraField;
            }
        }
        return null;
    }

    public void addExtraField(ZipExtraField zipExtraField) {
        if (zipExtraField instanceof UnparseableExtraFieldData) {
            this.unparseableExtra = (UnparseableExtraFieldData) zipExtraField;
        } else if (this.extraFields == null) {
            this.extraFields = new ZipExtraField[]{zipExtraField};
        } else {
            if (getExtraField(zipExtraField.getHeaderId()) != null) {
                removeExtraField(zipExtraField.getHeaderId());
            }
            ZipExtraField[] zipExtraFieldArr = this.extraFields;
            ZipExtraField[] copyOf = copyOf(zipExtraFieldArr, zipExtraFieldArr.length + 1);
            copyOf[copyOf.length - 1] = zipExtraField;
            this.extraFields = copyOf;
        }
        setExtra();
    }

    public void addAsFirstExtraField(ZipExtraField zipExtraField) {
        if (zipExtraField instanceof UnparseableExtraFieldData) {
            this.unparseableExtra = (UnparseableExtraFieldData) zipExtraField;
        } else {
            if (getExtraField(zipExtraField.getHeaderId()) != null) {
                removeExtraField(zipExtraField.getHeaderId());
            }
            ZipExtraField[] zipExtraFieldArr = this.extraFields;
            this.extraFields = new ZipExtraField[zipExtraFieldArr != null ? zipExtraFieldArr.length + 1 : 1];
            ZipExtraField[] zipExtraFieldArr2 = this.extraFields;
            zipExtraFieldArr2[0] = zipExtraField;
            if (zipExtraFieldArr != null) {
                System.arraycopy(zipExtraFieldArr, 0, zipExtraFieldArr2, 1, zipExtraFieldArr2.length - 1);
            }
        }
        setExtra();
    }

    public void removeExtraField(ZipShort zipShort) {
        if (this.extraFields == null) {
            throw new NoSuchElementException();
        }
        ArrayList arrayList = new ArrayList();
        for (ZipExtraField zipExtraField : this.extraFields) {
            if (!zipShort.equals(zipExtraField.getHeaderId())) {
                arrayList.add(zipExtraField);
            }
        }
        if (this.extraFields.length == arrayList.size()) {
            throw new NoSuchElementException();
        }
        this.extraFields = (ZipExtraField[]) arrayList.toArray(ExtraFieldUtils.EMPTY_ZIP_EXTRA_FIELD_ARRAY);
        setExtra();
    }

    public void removeUnparseableExtraFieldData() {
        if (this.unparseableExtra == null) {
            throw new NoSuchElementException();
        }
        this.unparseableExtra = null;
        setExtra();
    }

    public ZipExtraField getExtraField(ZipShort zipShort) {
        ZipExtraField[] zipExtraFieldArr = this.extraFields;
        if (zipExtraFieldArr == null) {
            return null;
        }
        for (ZipExtraField zipExtraField : zipExtraFieldArr) {
            if (zipShort.equals(zipExtraField.getHeaderId())) {
                return zipExtraField;
            }
        }
        return null;
    }

    public UnparseableExtraFieldData getUnparseableExtraFieldData() {
        return this.unparseableExtra;
    }

    @Override // java.util.zip.ZipEntry
    public void setExtra(byte[] bArr) throws RuntimeException {
        try {
            mergeExtraFields(ExtraFieldUtils.parse(bArr, true, (ExtraFieldParsingBehavior) ExtraFieldParsingMode.BEST_EFFORT), true);
        } catch (ZipException e) {
            throw new RuntimeException("Error parsing extra fields for entry: " + getName() + " - " + e.getMessage(), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setExtra() {
        super.setExtra(ExtraFieldUtils.mergeLocalFileDataData(getAllExtraFieldsNoCopy()));
    }

    public void setCentralDirectoryExtra(byte[] bArr) {
        try {
            mergeExtraFields(ExtraFieldUtils.parse(bArr, false, (ExtraFieldParsingBehavior) ExtraFieldParsingMode.BEST_EFFORT), false);
        } catch (ZipException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public byte[] getLocalFileDataExtra() {
        byte[] extra = getExtra();
        return extra != null ? extra : ByteUtils.EMPTY_BYTE_ARRAY;
    }

    public byte[] getCentralDirectoryExtra() {
        return ExtraFieldUtils.mergeCentralDirectoryData(getAllExtraFieldsNoCopy());
    }

    @Override // java.util.zip.ZipEntry, org.apache.commons.compress.archivers.ArchiveEntry
    public String getName() {
        String str = this.name;
        return str == null ? super.getName() : str;
    }

    @Override // java.util.zip.ZipEntry, org.apache.commons.compress.archivers.ArchiveEntry
    public boolean isDirectory() {
        String name = getName();
        return name != null && name.endsWith("/");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setName(String str) {
        if (str != null && getPlatform() == 0 && !str.contains("/")) {
            str = str.replace('\\', '/');
        }
        this.name = str;
    }

    @Override // java.util.zip.ZipEntry, org.apache.commons.compress.archivers.ArchiveEntry
    public long getSize() {
        return this.size;
    }

    @Override // java.util.zip.ZipEntry
    public void setSize(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("Invalid entry size");
        }
        this.size = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setName(String str, byte[] bArr) {
        setName(str);
        this.rawName = bArr;
    }

    public byte[] getRawName() {
        byte[] bArr = this.rawName;
        if (bArr != null) {
            return Arrays.copyOf(bArr, bArr.length);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long getLocalHeaderOffset() {
        return this.localHeaderOffset;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setLocalHeaderOffset(long j) {
        this.localHeaderOffset = j;
    }

    @Override // org.apache.commons.compress.archivers.EntryStreamOffsets
    public long getDataOffset() {
        return this.dataOffset;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setDataOffset(long j) {
        this.dataOffset = j;
    }

    @Override // org.apache.commons.compress.archivers.EntryStreamOffsets
    public boolean isStreamContiguous() {
        return this.isStreamContiguous;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setStreamContiguous(boolean z) {
        this.isStreamContiguous = z;
    }

    @Override // java.util.zip.ZipEntry
    public int hashCode() {
        String name = getName();
        if (name == null) {
            name = "";
        }
        return name.hashCode();
    }

    public GeneralPurposeBit getGeneralPurposeBit() {
        return this.gpb;
    }

    public void setGeneralPurposeBit(GeneralPurposeBit generalPurposeBit) {
        this.gpb = generalPurposeBit;
    }

    private void mergeExtraFields(ZipExtraField[] zipExtraFieldArr, boolean z) {
        ZipExtraField extraField;
        byte[] centralDirectoryData;
        if (this.extraFields == null) {
            setExtraFields(zipExtraFieldArr);
            return;
        }
        for (ZipExtraField zipExtraField : zipExtraFieldArr) {
            if (zipExtraField instanceof UnparseableExtraFieldData) {
                extraField = this.unparseableExtra;
            } else {
                extraField = getExtraField(zipExtraField.getHeaderId());
            }
            if (extraField == null) {
                addExtraField(zipExtraField);
            } else {
                if (z) {
                    centralDirectoryData = zipExtraField.getLocalFileDataData();
                } else {
                    centralDirectoryData = zipExtraField.getCentralDirectoryData();
                }
                if (z) {
                    try {
                        extraField.parseFromLocalFileData(centralDirectoryData, 0, centralDirectoryData.length);
                    } catch (ZipException unused) {
                        UnrecognizedExtraField unrecognizedExtraField = new UnrecognizedExtraField();
                        unrecognizedExtraField.setHeaderId(extraField.getHeaderId());
                        if (z) {
                            unrecognizedExtraField.setLocalFileDataData(centralDirectoryData);
                            unrecognizedExtraField.setCentralDirectoryData(extraField.getCentralDirectoryData());
                        } else {
                            unrecognizedExtraField.setLocalFileDataData(extraField.getLocalFileDataData());
                            unrecognizedExtraField.setCentralDirectoryData(centralDirectoryData);
                        }
                        removeExtraField(extraField.getHeaderId());
                        addExtraField(unrecognizedExtraField);
                    }
                } else {
                    extraField.parseFromCentralDirectoryData(centralDirectoryData, 0, centralDirectoryData.length);
                }
            }
        }
        setExtra();
    }

    @Override // org.apache.commons.compress.archivers.ArchiveEntry
    public Date getLastModifiedDate() {
        return new Date(getTime());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ZipArchiveEntry zipArchiveEntry = (ZipArchiveEntry) obj;
        if (!Objects.equals(getName(), zipArchiveEntry.getName())) {
            return false;
        }
        String comment = getComment();
        String comment2 = zipArchiveEntry.getComment();
        if (comment == null) {
            comment = "";
        }
        if (comment2 == null) {
            comment2 = "";
        }
        return getTime() == zipArchiveEntry.getTime() && comment.equals(comment2) && getInternalAttributes() == zipArchiveEntry.getInternalAttributes() && getPlatform() == zipArchiveEntry.getPlatform() && getExternalAttributes() == zipArchiveEntry.getExternalAttributes() && getMethod() == zipArchiveEntry.getMethod() && getSize() == zipArchiveEntry.getSize() && getCrc() == zipArchiveEntry.getCrc() && getCompressedSize() == zipArchiveEntry.getCompressedSize() && Arrays.equals(getCentralDirectoryExtra(), zipArchiveEntry.getCentralDirectoryExtra()) && Arrays.equals(getLocalFileDataExtra(), zipArchiveEntry.getLocalFileDataExtra()) && this.localHeaderOffset == zipArchiveEntry.localHeaderOffset && this.dataOffset == zipArchiveEntry.dataOffset && this.gpb.equals(zipArchiveEntry.gpb);
    }

    public void setVersionMadeBy(int i) {
        this.versionMadeBy = i;
    }

    public void setVersionRequired(int i) {
        this.versionRequired = i;
    }

    public int getVersionRequired() {
        return this.versionRequired;
    }

    public int getVersionMadeBy() {
        return this.versionMadeBy;
    }

    public int getRawFlag() {
        return this.rawFlag;
    }

    public void setRawFlag(int i) {
        this.rawFlag = i;
    }

    public NameSource getNameSource() {
        return this.nameSource;
    }

    public void setNameSource(NameSource nameSource) {
        this.nameSource = nameSource;
    }

    public CommentSource getCommentSource() {
        return this.commentSource;
    }

    public void setCommentSource(CommentSource commentSource) {
        this.commentSource = commentSource;
    }

    public long getDiskNumberStart() {
        return this.diskNumberStart;
    }

    public void setDiskNumberStart(long j) {
        this.diskNumberStart = j;
    }

    private ZipExtraField[] copyOf(ZipExtraField[] zipExtraFieldArr, int i) {
        ZipExtraField[] zipExtraFieldArr2 = new ZipExtraField[i];
        System.arraycopy(zipExtraFieldArr, 0, zipExtraFieldArr2, 0, Math.min(zipExtraFieldArr.length, i));
        return zipExtraFieldArr2;
    }

    /* loaded from: classes8.dex */
    public enum ExtraFieldParsingMode implements ExtraFieldParsingBehavior {
        BEST_EFFORT(ExtraFieldUtils.UnparseableExtraField.READ) { // from class: org.apache.commons.compress.archivers.zip.ZipArchiveEntry.ExtraFieldParsingMode.1
            @Override // org.apache.commons.compress.archivers.zip.ZipArchiveEntry.ExtraFieldParsingMode, org.apache.commons.compress.archivers.zip.ExtraFieldParsingBehavior
            public ZipExtraField fill(ZipExtraField zipExtraField, byte[] bArr, int i, int i2, boolean z) {
                return ExtraFieldParsingMode.fillAndMakeUnrecognizedOnError(zipExtraField, bArr, i, i2, z);
            }
        },
        STRICT_FOR_KNOW_EXTRA_FIELDS(ExtraFieldUtils.UnparseableExtraField.READ),
        ONLY_PARSEABLE_LENIENT(ExtraFieldUtils.UnparseableExtraField.SKIP) { // from class: org.apache.commons.compress.archivers.zip.ZipArchiveEntry.ExtraFieldParsingMode.2
            @Override // org.apache.commons.compress.archivers.zip.ZipArchiveEntry.ExtraFieldParsingMode, org.apache.commons.compress.archivers.zip.ExtraFieldParsingBehavior
            public ZipExtraField fill(ZipExtraField zipExtraField, byte[] bArr, int i, int i2, boolean z) {
                return ExtraFieldParsingMode.fillAndMakeUnrecognizedOnError(zipExtraField, bArr, i, i2, z);
            }
        },
        ONLY_PARSEABLE_STRICT(ExtraFieldUtils.UnparseableExtraField.SKIP),
        DRACONIC(ExtraFieldUtils.UnparseableExtraField.THROW);

        private final ExtraFieldUtils.UnparseableExtraField onUnparseableData;

        ExtraFieldParsingMode(ExtraFieldUtils.UnparseableExtraField unparseableExtraField) {
            this.onUnparseableData = unparseableExtraField;
        }

        @Override // org.apache.commons.compress.archivers.zip.UnparseableExtraFieldBehavior
        public ZipExtraField onUnparseableExtraField(byte[] bArr, int i, int i2, boolean z, int i3) throws ZipException {
            return this.onUnparseableData.onUnparseableExtraField(bArr, i, i2, z, i3);
        }

        @Override // org.apache.commons.compress.archivers.zip.ExtraFieldParsingBehavior
        public ZipExtraField createExtraField(ZipShort zipShort) throws ZipException, InstantiationException, IllegalAccessException {
            return ExtraFieldUtils.createExtraField(zipShort);
        }

        @Override // org.apache.commons.compress.archivers.zip.ExtraFieldParsingBehavior
        public ZipExtraField fill(ZipExtraField zipExtraField, byte[] bArr, int i, int i2, boolean z) throws ZipException {
            return ExtraFieldUtils.fillExtraField(zipExtraField, bArr, i, i2, z);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static ZipExtraField fillAndMakeUnrecognizedOnError(ZipExtraField zipExtraField, byte[] bArr, int i, int i2, boolean z) {
            try {
                return ExtraFieldUtils.fillExtraField(zipExtraField, bArr, i, i2, z);
            } catch (ZipException unused) {
                UnrecognizedExtraField unrecognizedExtraField = new UnrecognizedExtraField();
                unrecognizedExtraField.setHeaderId(zipExtraField.getHeaderId());
                if (z) {
                    unrecognizedExtraField.setLocalFileDataData(Arrays.copyOfRange(bArr, i, i2 + i));
                } else {
                    unrecognizedExtraField.setCentralDirectoryData(Arrays.copyOfRange(bArr, i, i2 + i));
                }
                return unrecognizedExtraField;
            }
        }
    }
}
