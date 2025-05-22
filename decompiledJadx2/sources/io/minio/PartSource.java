package io.minio;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.SequenceInputStream;
import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import javax.annotation.Nonnull;
import okio.Okio;
import okio.Source;

/* loaded from: classes7.dex */
class PartSource {
    private ByteBufferStream[] buffers;
    private RandomAccessFile file;
    private String md5Hash;
    private int partNumber;
    private long position;
    private String sha256Hash;
    private long size;

    private PartSource(int i, long j, String str, String str2) {
        this.partNumber = i;
        this.size = j;
        this.md5Hash = str;
        this.sha256Hash = str2;
    }

    public PartSource(int i, @Nonnull RandomAccessFile randomAccessFile, long j, String str, String str2) throws IOException {
        this(i, j, str, str2);
        this.file = (RandomAccessFile) Objects.requireNonNull(randomAccessFile, "file must not be null");
        this.position = this.file.getFilePointer();
    }

    public PartSource(int i, @Nonnull ByteBufferStream[] byteBufferStreamArr, long j, String str, String str2) {
        this(i, j, str, str2);
        this.buffers = (ByteBufferStream[]) Objects.requireNonNull(byteBufferStreamArr, "buffers must not be null");
    }

    public int partNumber() {
        return this.partNumber;
    }

    public long size() {
        return this.size;
    }

    public String md5Hash() {
        return this.md5Hash;
    }

    public String sha256Hash() {
        return this.sha256Hash;
    }

    public Source source() throws IOException {
        RandomAccessFile randomAccessFile = this.file;
        if (randomAccessFile != null) {
            randomAccessFile.seek(this.position);
            return Okio.source(Channels.newInputStream(this.file.getChannel()));
        }
        InputStream inputStream = this.buffers[0].inputStream();
        if (this.buffers.length == 1) {
            return Okio.source(inputStream);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(inputStream);
        int i = 1;
        while (true) {
            ByteBufferStream[] byteBufferStreamArr = this.buffers;
            if (i >= byteBufferStreamArr.length || byteBufferStreamArr[i].size() == 0) {
                break;
            }
            arrayList.add(this.buffers[i].inputStream());
            i++;
        }
        return arrayList.size() == 1 ? Okio.source(inputStream) : Okio.source(new SequenceInputStream(Collections.enumeration(arrayList)));
    }
}
