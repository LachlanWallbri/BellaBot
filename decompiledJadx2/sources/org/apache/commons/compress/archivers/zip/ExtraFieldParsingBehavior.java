package org.apache.commons.compress.archivers.zip;

import java.util.zip.ZipException;

/* loaded from: classes8.dex */
public interface ExtraFieldParsingBehavior extends UnparseableExtraFieldBehavior {
    ZipExtraField createExtraField(ZipShort zipShort) throws ZipException, InstantiationException, IllegalAccessException;

    ZipExtraField fill(ZipExtraField zipExtraField, byte[] bArr, int i, int i2, boolean z) throws ZipException;
}
