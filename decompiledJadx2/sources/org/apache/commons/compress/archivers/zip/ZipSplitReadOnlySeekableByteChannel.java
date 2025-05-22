package org.apache.commons.compress.archivers.zip;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.commons.compress.utils.FileNameUtils;
import org.apache.commons.compress.utils.MultiReadOnlySeekableByteChannel;

/* loaded from: classes8.dex */
public class ZipSplitReadOnlySeekableByteChannel extends MultiReadOnlySeekableByteChannel {
    private static final int ZIP_SPLIT_SIGNATURE_LENGTH = 4;
    private final ByteBuffer zipSplitSignatureByteBuffer;

    public ZipSplitReadOnlySeekableByteChannel(List<SeekableByteChannel> list) throws IOException {
        super(list);
        this.zipSplitSignatureByteBuffer = ByteBuffer.allocate(4);
        assertSplitSignature(list);
    }

    private void assertSplitSignature(List<SeekableByteChannel> list) throws IOException {
        SeekableByteChannel seekableByteChannel = list.get(0);
        seekableByteChannel.position(0L);
        this.zipSplitSignatureByteBuffer.rewind();
        seekableByteChannel.read(this.zipSplitSignatureByteBuffer);
        if (!new ZipLong(this.zipSplitSignatureByteBuffer.array()).equals(ZipLong.DD_SIG)) {
            seekableByteChannel.position(0L);
            throw new IOException("The first zip split segment does not begin with split zip file signature");
        }
        seekableByteChannel.position(0L);
    }

    public static SeekableByteChannel forOrderedSeekableByteChannels(SeekableByteChannel... seekableByteChannelArr) throws IOException {
        if (((SeekableByteChannel[]) Objects.requireNonNull(seekableByteChannelArr, "channels must not be null")).length == 1) {
            return seekableByteChannelArr[0];
        }
        return new ZipSplitReadOnlySeekableByteChannel(Arrays.asList(seekableByteChannelArr));
    }

    public static SeekableByteChannel forOrderedSeekableByteChannels(SeekableByteChannel seekableByteChannel, Iterable<SeekableByteChannel> iterable) throws IOException {
        Objects.requireNonNull(iterable, "channels");
        Objects.requireNonNull(seekableByteChannel, "lastSegmentChannel");
        ArrayList arrayList = new ArrayList();
        Iterator<SeekableByteChannel> it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        arrayList.add(seekableByteChannel);
        return forOrderedSeekableByteChannels((SeekableByteChannel[]) arrayList.toArray(new SeekableByteChannel[0]));
    }

    public static SeekableByteChannel buildFromLastSplitSegment(File file) throws IOException {
        if (!FileNameUtils.getExtension(file.getCanonicalPath()).equalsIgnoreCase(ArchiveStreamFactory.ZIP)) {
            throw new IllegalArgumentException("The extension of last zip split segment should be .zip");
        }
        File parentFile = file.getParentFile();
        String baseName = FileNameUtils.getBaseName(file.getCanonicalPath());
        ArrayList arrayList = new ArrayList();
        Pattern compile = Pattern.compile(Pattern.quote(baseName) + ".[zZ][0-9]+");
        File[] listFiles = parentFile.listFiles();
        if (listFiles != null) {
            for (File file2 : listFiles) {
                if (compile.matcher(file2.getName()).matches()) {
                    arrayList.add(file2);
                }
            }
        }
        arrayList.sort(new ZipSplitSegmentComparator());
        return forFiles(file, arrayList);
    }

    public static SeekableByteChannel forFiles(File... fileArr) throws IOException {
        ArrayList arrayList = new ArrayList();
        for (File file : (File[]) Objects.requireNonNull(fileArr, "files must not be null")) {
            arrayList.add(Files.newByteChannel(file.toPath(), StandardOpenOption.READ));
        }
        if (arrayList.size() == 1) {
            return (SeekableByteChannel) arrayList.get(0);
        }
        return new ZipSplitReadOnlySeekableByteChannel(arrayList);
    }

    public static SeekableByteChannel forFiles(File file, Iterable<File> iterable) throws IOException {
        Objects.requireNonNull(iterable, "files");
        Objects.requireNonNull(file, "lastSegmentFile");
        ArrayList arrayList = new ArrayList();
        Iterator<File> it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        arrayList.add(file);
        return forFiles((File[]) arrayList.toArray(new File[0]));
    }

    /* loaded from: classes8.dex */
    private static class ZipSplitSegmentComparator implements Comparator<File>, Serializable {
        private static final long serialVersionUID = 20200123;

        private ZipSplitSegmentComparator() {
        }

        @Override // java.util.Comparator
        public int compare(File file, File file2) {
            String extension = FileNameUtils.getExtension(file.getPath());
            String extension2 = FileNameUtils.getExtension(file2.getPath());
            if (!extension.startsWith(CompressorStreamFactory.f8930Z)) {
                return -1;
            }
            if (extension2.startsWith(CompressorStreamFactory.f8930Z)) {
                return Integer.valueOf(Integer.parseInt(extension.substring(1))).compareTo(Integer.valueOf(Integer.parseInt(extension2.substring(1))));
            }
            return 1;
        }
    }
}
