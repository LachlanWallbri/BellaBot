package com.google.common.io;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.graph.SuccessorsFunction;
import com.google.common.graph.Traverser;
import com.google.common.io.ByteSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystemException;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.SecureDirectoryStream;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Stream;

/* loaded from: classes3.dex */
public final class MoreFiles {
    private static final SuccessorsFunction<Path> FILE_TREE = new SuccessorsFunction<Path>() { // from class: com.google.common.io.MoreFiles.1
        @Override // com.google.common.graph.SuccessorsFunction, com.google.common.graph.Graph
        public Iterable<Path> successors(Path path) {
            return MoreFiles.fileTreeChildren(path);
        }
    };

    private MoreFiles() {
    }

    public static ByteSource asByteSource(Path path, OpenOption... openOptionArr) {
        return new PathByteSource(path, openOptionArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class PathByteSource extends ByteSource {
        private static final LinkOption[] FOLLOW_LINKS = new LinkOption[0];
        private final boolean followLinks;
        private final OpenOption[] options;
        private final Path path;

        private PathByteSource(Path path, OpenOption... openOptionArr) {
            this.path = (Path) Preconditions.checkNotNull(path);
            this.options = (OpenOption[]) openOptionArr.clone();
            this.followLinks = followLinks(this.options);
        }

        private static boolean followLinks(OpenOption[] openOptionArr) {
            for (OpenOption openOption : openOptionArr) {
                if (openOption == LinkOption.NOFOLLOW_LINKS) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openStream() throws IOException {
            return java.nio.file.Files.newInputStream(this.path, this.options);
        }

        private BasicFileAttributes readAttributes() throws IOException {
            return java.nio.file.Files.readAttributes(this.path, BasicFileAttributes.class, this.followLinks ? FOLLOW_LINKS : new LinkOption[]{LinkOption.NOFOLLOW_LINKS});
        }

        @Override // com.google.common.io.ByteSource
        public Optional<Long> sizeIfKnown() {
            try {
                BasicFileAttributes readAttributes = readAttributes();
                if (readAttributes.isDirectory() || readAttributes.isSymbolicLink()) {
                    return Optional.absent();
                }
                return Optional.m610of(Long.valueOf(readAttributes.size()));
            } catch (IOException unused) {
                return Optional.absent();
            }
        }

        @Override // com.google.common.io.ByteSource
        public long size() throws IOException {
            BasicFileAttributes readAttributes = readAttributes();
            if (readAttributes.isDirectory()) {
                throw new IOException("can't read: is a directory");
            }
            if (readAttributes.isSymbolicLink()) {
                throw new IOException("can't read: is a symbolic link");
            }
            return readAttributes.size();
        }

        @Override // com.google.common.io.ByteSource
        public byte[] read() throws IOException {
            SeekableByteChannel newByteChannel = java.nio.file.Files.newByteChannel(this.path, this.options);
            try {
                byte[] byteArray = ByteStreams.toByteArray(Channels.newInputStream(newByteChannel), newByteChannel.size());
                if (newByteChannel != null) {
                    newByteChannel.close();
                }
                return byteArray;
            } catch (Throwable th) {
                if (newByteChannel != null) {
                    try {
                        newByteChannel.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }

        @Override // com.google.common.io.ByteSource
        public CharSource asCharSource(Charset charset) {
            if (this.options.length == 0) {
                return new ByteSource.AsCharSource(charset) { // from class: com.google.common.io.MoreFiles.PathByteSource.1
                    @Override // com.google.common.io.CharSource
                    public Stream<String> lines() throws IOException {
                        return java.nio.file.Files.lines(PathByteSource.this.path, this.charset);
                    }
                };
            }
            return super.asCharSource(charset);
        }

        public String toString() {
            String valueOf = String.valueOf(this.path);
            String arrays = Arrays.toString(this.options);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 26 + String.valueOf(arrays).length());
            sb.append("MoreFiles.asByteSource(");
            sb.append(valueOf);
            sb.append(", ");
            sb.append(arrays);
            sb.append(")");
            return sb.toString();
        }
    }

    public static ByteSink asByteSink(Path path, OpenOption... openOptionArr) {
        return new PathByteSink(path, openOptionArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class PathByteSink extends ByteSink {
        private final OpenOption[] options;
        private final Path path;

        private PathByteSink(Path path, OpenOption... openOptionArr) {
            this.path = (Path) Preconditions.checkNotNull(path);
            this.options = (OpenOption[]) openOptionArr.clone();
        }

        @Override // com.google.common.io.ByteSink
        public OutputStream openStream() throws IOException {
            return java.nio.file.Files.newOutputStream(this.path, this.options);
        }

        public String toString() {
            String valueOf = String.valueOf(this.path);
            String arrays = Arrays.toString(this.options);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 24 + String.valueOf(arrays).length());
            sb.append("MoreFiles.asByteSink(");
            sb.append(valueOf);
            sb.append(", ");
            sb.append(arrays);
            sb.append(")");
            return sb.toString();
        }
    }

    public static CharSource asCharSource(Path path, Charset charset, OpenOption... openOptionArr) {
        return asByteSource(path, openOptionArr).asCharSource(charset);
    }

    public static CharSink asCharSink(Path path, Charset charset, OpenOption... openOptionArr) {
        return asByteSink(path, openOptionArr).asCharSink(charset);
    }

    public static ImmutableList<Path> listFiles(Path path) throws IOException {
        try {
            DirectoryStream<Path> newDirectoryStream = java.nio.file.Files.newDirectoryStream(path);
            try {
                ImmutableList<Path> copyOf = ImmutableList.copyOf(newDirectoryStream);
                if (newDirectoryStream != null) {
                    newDirectoryStream.close();
                }
                return copyOf;
            } finally {
            }
        } catch (DirectoryIteratorException e) {
            throw e.getCause();
        }
    }

    public static Traverser<Path> fileTraverser() {
        return Traverser.forTree(FILE_TREE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Iterable<Path> fileTreeChildren(Path path) {
        if (java.nio.file.Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
            try {
                return listFiles(path);
            } catch (IOException e) {
                throw new DirectoryIteratorException(e);
            }
        }
        return ImmutableList.m634of();
    }

    public static Predicate<Path> isDirectory(LinkOption... linkOptionArr) {
        final LinkOption[] linkOptionArr2 = (LinkOption[]) linkOptionArr.clone();
        return new Predicate<Path>() { // from class: com.google.common.io.MoreFiles.2
            @Override // com.google.common.base.Predicate
            public boolean apply(Path path) {
                return java.nio.file.Files.isDirectory(path, linkOptionArr2);
            }

            public String toString() {
                String arrays = Arrays.toString(linkOptionArr2);
                StringBuilder sb = new StringBuilder(String.valueOf(arrays).length() + 23);
                sb.append("MoreFiles.isDirectory(");
                sb.append(arrays);
                sb.append(")");
                return sb.toString();
            }
        };
    }

    private static boolean isDirectory(SecureDirectoryStream<Path> secureDirectoryStream, Path path, LinkOption... linkOptionArr) throws IOException {
        return ((BasicFileAttributeView) secureDirectoryStream.getFileAttributeView(path, BasicFileAttributeView.class, linkOptionArr)).readAttributes().isDirectory();
    }

    public static Predicate<Path> isRegularFile(LinkOption... linkOptionArr) {
        final LinkOption[] linkOptionArr2 = (LinkOption[]) linkOptionArr.clone();
        return new Predicate<Path>() { // from class: com.google.common.io.MoreFiles.3
            @Override // com.google.common.base.Predicate
            public boolean apply(Path path) {
                return java.nio.file.Files.isRegularFile(path, linkOptionArr2);
            }

            public String toString() {
                String arrays = Arrays.toString(linkOptionArr2);
                StringBuilder sb = new StringBuilder(String.valueOf(arrays).length() + 25);
                sb.append("MoreFiles.isRegularFile(");
                sb.append(arrays);
                sb.append(")");
                return sb.toString();
            }
        };
    }

    public static boolean equal(Path path, Path path2) throws IOException {
        Preconditions.checkNotNull(path);
        Preconditions.checkNotNull(path2);
        if (java.nio.file.Files.isSameFile(path, path2)) {
            return true;
        }
        ByteSource asByteSource = asByteSource(path, new OpenOption[0]);
        ByteSource asByteSource2 = asByteSource(path2, new OpenOption[0]);
        long longValue = asByteSource.sizeIfKnown().mo604or((Optional<Long>) 0L).longValue();
        long longValue2 = asByteSource2.sizeIfKnown().mo604or((Optional<Long>) 0L).longValue();
        if (longValue == 0 || longValue2 == 0 || longValue == longValue2) {
            return asByteSource.contentEquals(asByteSource2);
        }
        return false;
    }

    public static void touch(Path path) throws IOException {
        Preconditions.checkNotNull(path);
        try {
            java.nio.file.Files.setLastModifiedTime(path, FileTime.fromMillis(System.currentTimeMillis()));
        } catch (NoSuchFileException unused) {
            try {
                java.nio.file.Files.createFile(path, new FileAttribute[0]);
            } catch (FileAlreadyExistsException unused2) {
            }
        }
    }

    public static void createParentDirectories(Path path, FileAttribute<?>... fileAttributeArr) throws IOException {
        Path parent = path.toAbsolutePath().normalize().getParent();
        if (parent == null || java.nio.file.Files.isDirectory(parent, new LinkOption[0])) {
            return;
        }
        java.nio.file.Files.createDirectories(parent, fileAttributeArr);
        if (java.nio.file.Files.isDirectory(parent, new LinkOption[0])) {
            return;
        }
        String valueOf = String.valueOf(path);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 39);
        sb.append("Unable to create parent directories of ");
        sb.append(valueOf);
        throw new IOException(sb.toString());
    }

    public static String getFileExtension(Path path) {
        String path2;
        int lastIndexOf;
        Path fileName = path.getFileName();
        return (fileName == null || (lastIndexOf = (path2 = fileName.toString()).lastIndexOf(46)) == -1) ? "" : path2.substring(lastIndexOf + 1);
    }

    public static String getNameWithoutExtension(Path path) {
        Path fileName = path.getFileName();
        if (fileName == null) {
            return "";
        }
        String path2 = fileName.toString();
        int lastIndexOf = path2.lastIndexOf(46);
        return lastIndexOf == -1 ? path2 : path2.substring(0, lastIndexOf);
    }

    public static void deleteRecursively(Path path, RecursiveDeleteOption... recursiveDeleteOptionArr) throws IOException {
        Path parentPath = getParentPath(path);
        Collection<IOException> collection = null;
        if (parentPath == null) {
            throw new FileSystemException(path.toString(), null, "can't delete recursively");
        }
        boolean z = false;
        try {
            DirectoryStream<Path> newDirectoryStream = java.nio.file.Files.newDirectoryStream(parentPath);
            try {
                if (newDirectoryStream instanceof SecureDirectoryStream) {
                    z = true;
                    collection = deleteRecursivelySecure((SecureDirectoryStream) newDirectoryStream, path.getFileName());
                }
                if (newDirectoryStream != null) {
                    newDirectoryStream.close();
                }
                if (!z) {
                    checkAllowsInsecure(path, recursiveDeleteOptionArr);
                    collection = deleteRecursivelyInsecure(path);
                }
            } finally {
            }
        } catch (IOException e) {
            if (collection == null) {
                throw e;
            }
            collection.add(e);
        }
        if (collection != null) {
            throwDeleteFailed(path, collection);
        }
    }

    public static void deleteDirectoryContents(Path path, RecursiveDeleteOption... recursiveDeleteOptionArr) throws IOException {
        Collection<IOException> deleteDirectoryContentsInsecure;
        Collection<IOException> collection = null;
        try {
            DirectoryStream<Path> newDirectoryStream = java.nio.file.Files.newDirectoryStream(path);
            try {
                if (newDirectoryStream instanceof SecureDirectoryStream) {
                    deleteDirectoryContentsInsecure = deleteDirectoryContentsSecure((SecureDirectoryStream) newDirectoryStream);
                } else {
                    checkAllowsInsecure(path, recursiveDeleteOptionArr);
                    deleteDirectoryContentsInsecure = deleteDirectoryContentsInsecure(newDirectoryStream);
                }
                collection = deleteDirectoryContentsInsecure;
                if (newDirectoryStream != null) {
                    newDirectoryStream.close();
                }
            } finally {
            }
        } catch (IOException e) {
            if (0 == 0) {
                throw e;
            }
            collection.add(e);
        }
        if (collection != null) {
            throwDeleteFailed(path, collection);
        }
    }

    private static Collection<IOException> deleteRecursivelySecure(SecureDirectoryStream<Path> secureDirectoryStream, Path path) {
        Collection<IOException> collection = null;
        try {
            if (isDirectory(secureDirectoryStream, path, LinkOption.NOFOLLOW_LINKS)) {
                SecureDirectoryStream<Path> newDirectoryStream = secureDirectoryStream.newDirectoryStream(path, LinkOption.NOFOLLOW_LINKS);
                try {
                    collection = deleteDirectoryContentsSecure(newDirectoryStream);
                    if (newDirectoryStream != null) {
                        newDirectoryStream.close();
                    }
                    if (collection == null) {
                        secureDirectoryStream.deleteDirectory(path);
                    }
                } finally {
                }
            } else {
                secureDirectoryStream.deleteFile(path);
            }
            return collection;
        } catch (IOException e) {
            return addException(collection, e);
        }
    }

    private static Collection<IOException> deleteDirectoryContentsSecure(SecureDirectoryStream<Path> secureDirectoryStream) {
        Collection<IOException> collection = null;
        try {
            Iterator<Path> it = secureDirectoryStream.iterator();
            while (it.hasNext()) {
                collection = concat(collection, deleteRecursivelySecure(secureDirectoryStream, it.next().getFileName()));
            }
            return collection;
        } catch (DirectoryIteratorException e) {
            return addException(collection, e.getCause());
        }
    }

    private static Collection<IOException> deleteRecursivelyInsecure(Path path) {
        Collection<IOException> collection = null;
        try {
            if (java.nio.file.Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
                DirectoryStream<Path> newDirectoryStream = java.nio.file.Files.newDirectoryStream(path);
                try {
                    collection = deleteDirectoryContentsInsecure(newDirectoryStream);
                    if (newDirectoryStream != null) {
                        newDirectoryStream.close();
                    }
                } finally {
                }
            }
            if (collection == null) {
                java.nio.file.Files.delete(path);
            }
            return collection;
        } catch (IOException e) {
            return addException(collection, e);
        }
    }

    private static Collection<IOException> deleteDirectoryContentsInsecure(DirectoryStream<Path> directoryStream) {
        Collection<IOException> collection = null;
        try {
            Iterator<Path> it = directoryStream.iterator();
            while (it.hasNext()) {
                collection = concat(collection, deleteRecursivelyInsecure(it.next()));
            }
            return collection;
        } catch (DirectoryIteratorException e) {
            return addException(collection, e.getCause());
        }
    }

    private static Path getParentPath(Path path) {
        Path parent = path.getParent();
        if (parent != null) {
            return parent;
        }
        if (path.getNameCount() == 0) {
            return null;
        }
        return path.getFileSystem().getPath(".", new String[0]);
    }

    private static void checkAllowsInsecure(Path path, RecursiveDeleteOption[] recursiveDeleteOptionArr) throws InsecureRecursiveDeleteException {
        if (!Arrays.asList(recursiveDeleteOptionArr).contains(RecursiveDeleteOption.ALLOW_INSECURE)) {
            throw new InsecureRecursiveDeleteException(path.toString());
        }
    }

    private static Collection<IOException> addException(Collection<IOException> collection, IOException iOException) {
        if (collection == null) {
            collection = new ArrayList<>();
        }
        collection.add(iOException);
        return collection;
    }

    private static Collection<IOException> concat(Collection<IOException> collection, Collection<IOException> collection2) {
        if (collection == null) {
            return collection2;
        }
        if (collection2 != null) {
            collection.addAll(collection2);
        }
        return collection;
    }

    private static void throwDeleteFailed(Path path, Collection<IOException> collection) throws FileSystemException {
        FileSystemException fileSystemException = new FileSystemException(path.toString(), null, "failed to delete one or more files; see suppressed exceptions for details");
        Iterator<IOException> it = collection.iterator();
        while (it.hasNext()) {
            fileSystemException.addSuppressed(it.next());
        }
        throw fileSystemException;
    }
}
