package org.apache.commons.compress.archivers.zip;

import java.util.zip.ZipException;

/* loaded from: classes8.dex */
public interface ZipExtraField {
    public static final int EXTRAFIELD_HEADER_SIZE = 4;

    byte[] getCentralDirectoryData();

    ZipShort getCentralDirectoryLength();

    ZipShort getHeaderId();

    byte[] getLocalFileDataData();

    ZipShort getLocalFileDataLength();

    void parseFromCentralDirectoryData(byte[] bArr, int i, int i2) throws ZipException;

    void parseFromLocalFileData(byte[] bArr, int i, int i2) throws ZipException;
}
