package org.apache.commons.compress.archivers.arj;

import java.util.Arrays;

/* loaded from: classes7.dex */
class MainHeader {
    long archiveSize;
    int archiverVersionNumber;
    int arjFlags;
    int arjFlags2;
    int arjProtectionFactor;
    String comment;
    int dateTimeCreated;
    int dateTimeModified;
    int encryptionVersion;
    byte[] extendedHeaderBytes;
    int fileSpecPosition;
    int fileType;
    int hostOS;
    int lastChapter;
    int minVersionToExtract;
    String name;
    int reserved;
    int securityEnvelopeFilePosition;
    int securityEnvelopeLength;
    int securityVersion;

    /* loaded from: classes7.dex */
    static class Flags {
        static final int ALTNAME = 128;
        static final int ARJPROT = 8;
        static final int BACKUP = 32;
        static final int GARBLED = 1;
        static final int OLD_SECURED_NEW_ANSI_PAGE = 2;
        static final int PATHSYM = 16;
        static final int SECURED = 64;
        static final int VOLUME = 4;

        Flags() {
        }
    }

    /* loaded from: classes7.dex */
    static class HostOS {
        static final int AMIGA = 3;
        static final int APPLE_GS = 6;
        static final int ATARI_ST = 7;
        static final int MAC_OS = 4;
        static final int MS_DOS = 0;
        static final int NeXT = 8;
        static final int OS2 = 5;
        static final int PRIMOS = 1;
        static final int UNIX = 2;
        static final int VAX_VMS = 9;
        static final int WIN32 = 11;
        static final int WIN95 = 10;

        HostOS() {
        }
    }

    public String toString() {
        return "MainHeader [archiverVersionNumber=" + this.archiverVersionNumber + ", minVersionToExtract=" + this.minVersionToExtract + ", hostOS=" + this.hostOS + ", arjFlags=" + this.arjFlags + ", securityVersion=" + this.securityVersion + ", fileType=" + this.fileType + ", reserved=" + this.reserved + ", dateTimeCreated=" + this.dateTimeCreated + ", dateTimeModified=" + this.dateTimeModified + ", archiveSize=" + this.archiveSize + ", securityEnvelopeFilePosition=" + this.securityEnvelopeFilePosition + ", fileSpecPosition=" + this.fileSpecPosition + ", securityEnvelopeLength=" + this.securityEnvelopeLength + ", encryptionVersion=" + this.encryptionVersion + ", lastChapter=" + this.lastChapter + ", arjProtectionFactor=" + this.arjProtectionFactor + ", arjFlags2=" + this.arjFlags2 + ", name=" + this.name + ", comment=" + this.comment + ", extendedHeaderBytes=" + Arrays.toString(this.extendedHeaderBytes) + "]";
    }
}
