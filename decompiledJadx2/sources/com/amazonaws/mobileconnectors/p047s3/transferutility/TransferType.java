package com.amazonaws.mobileconnectors.p047s3.transferutility;

/* loaded from: classes.dex */
public enum TransferType {
    UPLOAD,
    DOWNLOAD,
    ANY;

    public static TransferType getType(String str) {
        if (str.equalsIgnoreCase(UPLOAD.toString())) {
            return UPLOAD;
        }
        if (str.equalsIgnoreCase(DOWNLOAD.toString())) {
            return DOWNLOAD;
        }
        if (str.equalsIgnoreCase(ANY.toString())) {
            return ANY;
        }
        throw new IllegalArgumentException("Type " + str + " is not a recognized type");
    }
}
