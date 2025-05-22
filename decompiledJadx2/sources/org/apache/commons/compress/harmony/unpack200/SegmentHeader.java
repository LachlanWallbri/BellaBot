package org.apache.commons.compress.harmony.unpack200;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.harmony.pack200.BHSDCodec;
import org.apache.commons.compress.harmony.pack200.Codec;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;

/* loaded from: classes9.dex */
public class SegmentHeader {
    private static final int[] magic = {202, 254, 208, 13};
    private int archiveMajor;
    private int archiveMinor;
    private long archiveModtime;
    private long archiveSize;
    private int archiveSizeOffset;
    private int attributeDefinitionCount;
    private InputStream bandHeadersInputStream;
    private int bandHeadersSize;
    private int classCount;
    private int cpClassCount;
    private int cpDescriptorCount;
    private int cpDoubleCount;
    private int cpFieldCount;
    private int cpFloatCount;
    private int cpIMethodCount;
    private int cpIntCount;
    private int cpLongCount;
    private int cpMethodCount;
    private int cpSignatureCount;
    private int cpStringCount;
    private int cpUTF8Count;
    private int defaultClassMajorVersion;
    private int defaultClassMinorVersion;
    private int innerClassCount;
    private int numberOfFiles;
    private SegmentOptions options;
    private final Segment segment;
    private int segmentsRemaining;

    public void unpack() {
    }

    public SegmentHeader(Segment segment) {
        this.segment = segment;
    }

    public int getArchiveSizeOffset() {
        return this.archiveSizeOffset;
    }

    public void read(InputStream inputStream) throws IOException, Pack200Exception, Error, Pack200Exception {
        int[] decodeScalar = decodeScalar("archive_magic_word", inputStream, Codec.BYTE1, magic.length);
        int i = 0;
        while (true) {
            int[] iArr = magic;
            if (i < iArr.length) {
                if (decodeScalar[i] != iArr[i]) {
                    throw new Error("Bad header");
                }
                i++;
            } else {
                setArchiveMinorVersion(decodeScalar("archive_minver", inputStream, Codec.UNSIGNED5));
                setArchiveMajorVersion(decodeScalar("archive_majver", inputStream, Codec.UNSIGNED5));
                this.options = new SegmentOptions(decodeScalar("archive_options", inputStream, Codec.UNSIGNED5));
                parseArchiveFileCounts(inputStream);
                parseArchiveSpecialCounts(inputStream);
                parseCpCounts(inputStream);
                parseClassCounts(inputStream);
                if (getBandHeadersSize() > 0) {
                    byte[] bArr = new byte[getBandHeadersSize()];
                    readFully(inputStream, bArr);
                    setBandHeadersData(bArr);
                }
                this.archiveSizeOffset -= inputStream.available();
                return;
            }
        }
    }

    private void setArchiveMinorVersion(int i) throws Pack200Exception {
        if (i != 7) {
            throw new Pack200Exception("Invalid segment minor version");
        }
        this.archiveMinor = i;
    }

    private void setArchiveMajorVersion(int i) throws Pack200Exception {
        if (i != 150) {
            throw new Pack200Exception("Invalid segment major version: " + i);
        }
        this.archiveMajor = i;
    }

    public long getArchiveModtime() {
        return this.archiveModtime;
    }

    public int getAttributeDefinitionCount() {
        return this.attributeDefinitionCount;
    }

    public int getClassCount() {
        return this.classCount;
    }

    public int getCpClassCount() {
        return this.cpClassCount;
    }

    public int getCpDescriptorCount() {
        return this.cpDescriptorCount;
    }

    public int getCpDoubleCount() {
        return this.cpDoubleCount;
    }

    public int getCpFieldCount() {
        return this.cpFieldCount;
    }

    public int getCpFloatCount() {
        return this.cpFloatCount;
    }

    public int getCpIMethodCount() {
        return this.cpIMethodCount;
    }

    public int getCpIntCount() {
        return this.cpIntCount;
    }

    public int getCpLongCount() {
        return this.cpLongCount;
    }

    public int getCpMethodCount() {
        return this.cpMethodCount;
    }

    public int getCpSignatureCount() {
        return this.cpSignatureCount;
    }

    public int getCpStringCount() {
        return this.cpStringCount;
    }

    public int getCpUTF8Count() {
        return this.cpUTF8Count;
    }

    public int getDefaultClassMajorVersion() {
        return this.defaultClassMajorVersion;
    }

    public int getDefaultClassMinorVersion() {
        return this.defaultClassMinorVersion;
    }

    public int getInnerClassCount() {
        return this.innerClassCount;
    }

    public long getArchiveSize() {
        return this.archiveSize;
    }

    public InputStream getBandHeadersInputStream() {
        if (this.bandHeadersInputStream == null) {
            this.bandHeadersInputStream = new ByteArrayInputStream(new byte[0]);
        }
        return this.bandHeadersInputStream;
    }

    public int getNumberOfFiles() {
        return this.numberOfFiles;
    }

    public int getSegmentsRemaining() {
        return this.segmentsRemaining;
    }

    public SegmentOptions getOptions() {
        return this.options;
    }

    private void parseArchiveFileCounts(InputStream inputStream) throws IOException, Pack200Exception {
        if (this.options.hasArchiveFileCounts()) {
            setArchiveSize((decodeScalar("archive_size_hi", inputStream, Codec.UNSIGNED5) << 32) | decodeScalar("archive_size_lo", inputStream, Codec.UNSIGNED5));
            this.archiveSizeOffset = inputStream.available();
            setSegmentsRemaining(decodeScalar("archive_next_count", inputStream, Codec.UNSIGNED5));
            setArchiveModtime(decodeScalar("archive_modtime", inputStream, Codec.UNSIGNED5));
            this.numberOfFiles = decodeScalar("file_count", inputStream, Codec.UNSIGNED5);
        }
    }

    private void parseArchiveSpecialCounts(InputStream inputStream) throws IOException, Pack200Exception {
        if (getOptions().hasSpecialFormats()) {
            this.bandHeadersSize = decodeScalar("band_headers_size", inputStream, Codec.UNSIGNED5);
            setAttributeDefinitionCount(decodeScalar("attr_definition_count", inputStream, Codec.UNSIGNED5));
        }
    }

    private void parseClassCounts(InputStream inputStream) throws IOException, Pack200Exception {
        this.innerClassCount = decodeScalar("ic_count", inputStream, Codec.UNSIGNED5);
        this.defaultClassMinorVersion = decodeScalar("default_class_minver", inputStream, Codec.UNSIGNED5);
        this.defaultClassMajorVersion = decodeScalar("default_class_majver", inputStream, Codec.UNSIGNED5);
        this.classCount = decodeScalar("class_count", inputStream, Codec.UNSIGNED5);
    }

    private void parseCpCounts(InputStream inputStream) throws IOException, Pack200Exception {
        this.cpUTF8Count = decodeScalar("cp_Utf8_count", inputStream, Codec.UNSIGNED5);
        if (getOptions().hasCPNumberCounts()) {
            this.cpIntCount = decodeScalar("cp_Int_count", inputStream, Codec.UNSIGNED5);
            this.cpFloatCount = decodeScalar("cp_Float_count", inputStream, Codec.UNSIGNED5);
            this.cpLongCount = decodeScalar("cp_Long_count", inputStream, Codec.UNSIGNED5);
            this.cpDoubleCount = decodeScalar("cp_Double_count", inputStream, Codec.UNSIGNED5);
        }
        this.cpStringCount = decodeScalar("cp_String_count", inputStream, Codec.UNSIGNED5);
        this.cpClassCount = decodeScalar("cp_Class_count", inputStream, Codec.UNSIGNED5);
        this.cpSignatureCount = decodeScalar("cp_Signature_count", inputStream, Codec.UNSIGNED5);
        this.cpDescriptorCount = decodeScalar("cp_Descr_count", inputStream, Codec.UNSIGNED5);
        this.cpFieldCount = decodeScalar("cp_Field_count", inputStream, Codec.UNSIGNED5);
        this.cpMethodCount = decodeScalar("cp_Method_count", inputStream, Codec.UNSIGNED5);
        this.cpIMethodCount = decodeScalar("cp_Imethod_count", inputStream, Codec.UNSIGNED5);
    }

    private int[] decodeScalar(String str, InputStream inputStream, BHSDCodec bHSDCodec, int i) throws IOException, Pack200Exception {
        this.segment.log(2, "Parsed #" + str + " (" + i + ")");
        return bHSDCodec.decodeInts(i, inputStream);
    }

    private int decodeScalar(String str, InputStream inputStream, BHSDCodec bHSDCodec) throws IOException, Pack200Exception {
        int decode = bHSDCodec.decode(inputStream);
        this.segment.log(2, "Parsed #" + str + " as " + decode);
        return decode;
    }

    public void setArchiveModtime(long j) {
        this.archiveModtime = j;
    }

    public void setArchiveSize(long j) {
        this.archiveSize = j;
    }

    private void setAttributeDefinitionCount(long j) {
        this.attributeDefinitionCount = (int) j;
    }

    private void setBandHeadersData(byte[] bArr) {
        this.bandHeadersInputStream = new ByteArrayInputStream(bArr);
    }

    public void setSegmentsRemaining(long j) {
        this.segmentsRemaining = (int) j;
    }

    private void readFully(InputStream inputStream, byte[] bArr) throws IOException, Pack200Exception {
        int read = inputStream.read(bArr);
        if (read == -1) {
            throw new EOFException("Failed to read any data from input stream");
        }
        while (read < bArr.length) {
            int read2 = inputStream.read(bArr, read, bArr.length - read);
            if (read2 == -1) {
                throw new EOFException("Failed to read some data from input stream");
            }
            read += read2;
        }
    }

    public int getBandHeadersSize() {
        return this.bandHeadersSize;
    }
}
