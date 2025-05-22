package org.apache.commons.compress.archivers.tar;

import com.iflytek.aiui.AIUIConstant;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.EntryStreamOffsets;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.utils.ArchiveUtils;
import org.apache.commons.compress.utils.IOUtils;

/* loaded from: classes8.dex */
public class TarArchiveEntry implements ArchiveEntry, TarConstants, EntryStreamOffsets {
    public static final int DEFAULT_DIR_MODE = 16877;
    public static final int DEFAULT_FILE_MODE = 33188;
    private static final TarArchiveEntry[] EMPTY_TAR_ARCHIVE_ENTRY_ARRAY = new TarArchiveEntry[0];
    public static final int MAX_NAMELEN = 31;
    public static final int MILLIS_PER_SECOND = 1000;
    public static final long UNKNOWN = -1;
    private boolean checkSumOK;
    private long dataOffset;
    private int devMajor;
    private int devMinor;
    private final Map<String, String> extraPaxHeaders;
    private final Path file;
    private long groupId;
    private String groupName;
    private boolean isExtended;
    private byte linkFlag;
    private String linkName;
    private final LinkOption[] linkOptions;
    private String magic;
    private long modTime;
    private int mode;
    private String name;
    private boolean paxGNU1XSparse;
    private boolean paxGNUSparse;
    private final boolean preserveAbsolutePath;
    private long realSize;
    private long size;
    private List<TarArchiveStructSparse> sparseHeaders;
    private boolean starSparse;
    private long userId;
    private String userName;
    private String version;

    @Override // org.apache.commons.compress.archivers.EntryStreamOffsets
    public boolean isStreamContiguous() {
        return true;
    }

    private TarArchiveEntry(boolean z) {
        this.name = "";
        this.linkName = "";
        this.magic = "ustar\u0000";
        this.version = "00";
        this.groupName = "";
        this.extraPaxHeaders = new HashMap();
        this.dataOffset = -1L;
        String property = System.getProperty("user.name", "");
        this.userName = property.length() > 31 ? property.substring(0, 31) : property;
        this.file = null;
        this.linkOptions = IOUtils.EMPTY_LINK_OPTIONS;
        this.preserveAbsolutePath = z;
    }

    public TarArchiveEntry(String str) {
        this(str, false);
    }

    public TarArchiveEntry(String str, boolean z) {
        this(z);
        String normalizeFileName = normalizeFileName(str, z);
        boolean endsWith = normalizeFileName.endsWith("/");
        this.name = normalizeFileName;
        this.mode = endsWith ? DEFAULT_DIR_MODE : DEFAULT_FILE_MODE;
        this.linkFlag = endsWith ? TarConstants.LF_DIR : TarConstants.LF_NORMAL;
        this.modTime = System.currentTimeMillis() / 1000;
        this.userName = "";
    }

    public TarArchiveEntry(String str, byte b) {
        this(str, b, false);
    }

    public TarArchiveEntry(String str, byte b, boolean z) {
        this(str, z);
        this.linkFlag = b;
        if (b == 76) {
            this.magic = TarConstants.MAGIC_GNU;
            this.version = TarConstants.VERSION_GNU_SPACE;
        }
    }

    public TarArchiveEntry(File file) {
        this(file, file.getPath());
    }

    public TarArchiveEntry(Path path) throws IOException {
        this(path, path.toString(), new LinkOption[0]);
    }

    public TarArchiveEntry(File file, String str) {
        this.name = "";
        this.linkName = "";
        this.magic = "ustar\u0000";
        this.version = "00";
        this.groupName = "";
        this.extraPaxHeaders = new HashMap();
        this.dataOffset = -1L;
        String normalizeFileName = normalizeFileName(str, false);
        this.file = file.toPath();
        this.linkOptions = IOUtils.EMPTY_LINK_OPTIONS;
        try {
            readFileMode(this.file, normalizeFileName, new LinkOption[0]);
        } catch (IOException unused) {
            if (!file.isDirectory()) {
                this.size = file.length();
            }
        }
        this.userName = "";
        try {
            readOsSpecificProperties(this.file, new LinkOption[0]);
        } catch (IOException unused2) {
            this.modTime = file.lastModified() / 1000;
        }
        this.preserveAbsolutePath = false;
    }

    public TarArchiveEntry(Path path, String str, LinkOption... linkOptionArr) throws IOException {
        this.name = "";
        this.linkName = "";
        this.magic = "ustar\u0000";
        this.version = "00";
        this.groupName = "";
        this.extraPaxHeaders = new HashMap();
        this.dataOffset = -1L;
        String normalizeFileName = normalizeFileName(str, false);
        this.file = path;
        this.linkOptions = linkOptionArr == null ? IOUtils.EMPTY_LINK_OPTIONS : linkOptionArr;
        readFileMode(path, normalizeFileName, linkOptionArr);
        this.userName = "";
        readOsSpecificProperties(path, new LinkOption[0]);
        this.preserveAbsolutePath = false;
    }

    private void readOsSpecificProperties(Path path, LinkOption... linkOptionArr) throws IOException {
        Set<String> supportedFileAttributeViews = path.getFileSystem().supportedFileAttributeViews();
        if (supportedFileAttributeViews.contains("posix")) {
            PosixFileAttributes posixFileAttributes = (PosixFileAttributes) Files.readAttributes(path, PosixFileAttributes.class, linkOptionArr);
            setModTime(posixFileAttributes.lastModifiedTime());
            this.userName = posixFileAttributes.owner().getName();
            this.groupName = posixFileAttributes.group().getName();
            if (supportedFileAttributeViews.contains("unix")) {
                this.userId = ((Number) Files.getAttribute(path, "unix:uid", linkOptionArr)).longValue();
                this.groupId = ((Number) Files.getAttribute(path, "unix:gid", linkOptionArr)).longValue();
                return;
            }
            return;
        }
        if (supportedFileAttributeViews.contains("dos")) {
            setModTime(((DosFileAttributes) Files.readAttributes(path, DosFileAttributes.class, linkOptionArr)).lastModifiedTime());
            this.userName = Files.getOwner(path, linkOptionArr).getName();
        } else {
            setModTime(Files.readAttributes(path, BasicFileAttributes.class, linkOptionArr).lastModifiedTime());
            this.userName = Files.getOwner(path, linkOptionArr).getName();
        }
    }

    private void readFileMode(Path path, String str, LinkOption... linkOptionArr) throws IOException {
        if (Files.isDirectory(path, linkOptionArr)) {
            this.mode = DEFAULT_DIR_MODE;
            this.linkFlag = TarConstants.LF_DIR;
            int length = str.length();
            if (length == 0 || str.charAt(length - 1) != '/') {
                this.name = str + "/";
                return;
            }
            this.name = str;
            return;
        }
        this.mode = DEFAULT_FILE_MODE;
        this.linkFlag = TarConstants.LF_NORMAL;
        this.name = str;
        this.size = Files.size(path);
    }

    public TarArchiveEntry(byte[] bArr) {
        this(false);
        parseTarHeader(bArr);
    }

    public TarArchiveEntry(byte[] bArr, ZipEncoding zipEncoding) throws IOException {
        this(bArr, zipEncoding, false);
    }

    public TarArchiveEntry(byte[] bArr, ZipEncoding zipEncoding, boolean z) throws IOException {
        this(false);
        parseTarHeader(bArr, zipEncoding, false, z);
    }

    public TarArchiveEntry(byte[] bArr, ZipEncoding zipEncoding, boolean z, long j) throws IOException {
        this(bArr, zipEncoding, z);
        setDataOffset(j);
    }

    public boolean equals(TarArchiveEntry tarArchiveEntry) {
        return tarArchiveEntry != null && getName().equals(tarArchiveEntry.getName());
    }

    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return equals((TarArchiveEntry) obj);
    }

    public int hashCode() {
        return getName().hashCode();
    }

    public boolean isDescendent(TarArchiveEntry tarArchiveEntry) {
        return tarArchiveEntry.getName().startsWith(getName());
    }

    @Override // org.apache.commons.compress.archivers.ArchiveEntry
    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = normalizeFileName(str, this.preserveAbsolutePath);
    }

    public void setMode(int i) {
        this.mode = i;
    }

    public String getLinkName() {
        return this.linkName;
    }

    public void setLinkName(String str) {
        this.linkName = str;
    }

    @Deprecated
    public int getUserId() {
        return (int) (this.userId & (-1));
    }

    public void setUserId(int i) {
        setUserId(i);
    }

    public long getLongUserId() {
        return this.userId;
    }

    public void setUserId(long j) {
        this.userId = j;
    }

    @Deprecated
    public int getGroupId() {
        return (int) (this.groupId & (-1));
    }

    public void setGroupId(int i) {
        setGroupId(i);
    }

    public long getLongGroupId() {
        return this.groupId;
    }

    public void setGroupId(long j) {
        this.groupId = j;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String str) {
        this.userName = str;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String str) {
        this.groupName = str;
    }

    public void setIds(int i, int i2) {
        setUserId(i);
        setGroupId(i2);
    }

    public void setNames(String str, String str2) {
        setUserName(str);
        setGroupName(str2);
    }

    public void setModTime(long j) {
        this.modTime = j / 1000;
    }

    public void setModTime(Date date) {
        this.modTime = date.getTime() / 1000;
    }

    public void setModTime(FileTime fileTime) {
        this.modTime = fileTime.to(TimeUnit.SECONDS);
    }

    public Date getModTime() {
        return new Date(this.modTime * 1000);
    }

    @Override // org.apache.commons.compress.archivers.ArchiveEntry
    public Date getLastModifiedDate() {
        return getModTime();
    }

    public boolean isCheckSumOK() {
        return this.checkSumOK;
    }

    public File getFile() {
        Path path = this.file;
        if (path == null) {
            return null;
        }
        return path.toFile();
    }

    public Path getPath() {
        return this.file;
    }

    public int getMode() {
        return this.mode;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveEntry
    public long getSize() {
        return this.size;
    }

    public void setSparseHeaders(List<TarArchiveStructSparse> list) {
        this.sparseHeaders = list;
    }

    public List<TarArchiveStructSparse> getSparseHeaders() {
        return this.sparseHeaders;
    }

    public List<TarArchiveStructSparse> getOrderedSparseHeaders() throws IOException {
        List<TarArchiveStructSparse> list = this.sparseHeaders;
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        List<TarArchiveStructSparse> list2 = (List) this.sparseHeaders.stream().filter(new Predicate() { // from class: org.apache.commons.compress.archivers.tar.-$$Lambda$TarArchiveEntry$Q2UUJZ37ZKdXu9Xx0k05yc2F9DM
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return TarArchiveEntry.lambda$getOrderedSparseHeaders$0((TarArchiveStructSparse) obj);
            }
        }).sorted(Comparator.comparingLong(new ToLongFunction() { // from class: org.apache.commons.compress.archivers.tar.-$$Lambda$wF3pjOI_l3FtFkDO9E3DBeAAQ2I
            @Override // java.util.function.ToLongFunction
            public final long applyAsLong(Object obj) {
                return ((TarArchiveStructSparse) obj).getOffset();
            }
        })).collect(Collectors.toList());
        int size = list2.size();
        int i = 0;
        while (i < size) {
            TarArchiveStructSparse tarArchiveStructSparse = list2.get(i);
            i++;
            if (i < size && tarArchiveStructSparse.getOffset() + tarArchiveStructSparse.getNumbytes() > list2.get(i).getOffset()) {
                throw new IOException("Corrupted TAR archive. Sparse blocks for " + getName() + " overlap each other.");
            }
            if (tarArchiveStructSparse.getOffset() + tarArchiveStructSparse.getNumbytes() < 0) {
                throw new IOException("Unreadable TAR archive. Offset and numbytes for sparse block in " + getName() + " too large.");
            }
        }
        if (!list2.isEmpty()) {
            TarArchiveStructSparse tarArchiveStructSparse2 = list2.get(size - 1);
            if (tarArchiveStructSparse2.getOffset() + tarArchiveStructSparse2.getNumbytes() > getRealSize()) {
                throw new IOException("Corrupted TAR archive. Sparse block extends beyond real size of the entry");
            }
        }
        return list2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$getOrderedSparseHeaders$0(TarArchiveStructSparse tarArchiveStructSparse) {
        return tarArchiveStructSparse.getOffset() > 0 || tarArchiveStructSparse.getNumbytes() > 0;
    }

    public boolean isPaxGNU1XSparse() {
        return this.paxGNU1XSparse;
    }

    public void setSize(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("Size is out of range: " + j);
        }
        this.size = j;
    }

    public int getDevMajor() {
        return this.devMajor;
    }

    public void setDevMajor(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Major device number is out of range: " + i);
        }
        this.devMajor = i;
    }

    public int getDevMinor() {
        return this.devMinor;
    }

    public void setDevMinor(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Minor device number is out of range: " + i);
        }
        this.devMinor = i;
    }

    public boolean isExtended() {
        return this.isExtended;
    }

    public long getRealSize() {
        if (!isSparse()) {
            return getSize();
        }
        return this.realSize;
    }

    public boolean isGNUSparse() {
        return isOldGNUSparse() || isPaxGNUSparse();
    }

    public boolean isOldGNUSparse() {
        return this.linkFlag == 83;
    }

    public boolean isPaxGNUSparse() {
        return this.paxGNUSparse;
    }

    public boolean isStarSparse() {
        return this.starSparse;
    }

    public boolean isGNULongLinkEntry() {
        return this.linkFlag == 75;
    }

    public boolean isGNULongNameEntry() {
        return this.linkFlag == 76;
    }

    public boolean isPaxHeader() {
        byte b = this.linkFlag;
        return b == 120 || b == 88;
    }

    public boolean isGlobalPaxHeader() {
        return this.linkFlag == 103;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveEntry
    public boolean isDirectory() {
        Path path = this.file;
        if (path != null) {
            return Files.isDirectory(path, this.linkOptions);
        }
        if (this.linkFlag == 53) {
            return true;
        }
        return (isPaxHeader() || isGlobalPaxHeader() || !getName().endsWith("/")) ? false : true;
    }

    public boolean isFile() {
        Path path = this.file;
        if (path != null) {
            return Files.isRegularFile(path, this.linkOptions);
        }
        byte b = this.linkFlag;
        if (b == 0 || b == 48) {
            return true;
        }
        return !getName().endsWith("/");
    }

    public boolean isSymbolicLink() {
        return this.linkFlag == 50;
    }

    public boolean isLink() {
        return this.linkFlag == 49;
    }

    public boolean isCharacterDevice() {
        return this.linkFlag == 51;
    }

    public boolean isBlockDevice() {
        return this.linkFlag == 52;
    }

    public boolean isFIFO() {
        return this.linkFlag == 54;
    }

    public boolean isSparse() {
        return isGNUSparse() || isStarSparse();
    }

    @Override // org.apache.commons.compress.archivers.EntryStreamOffsets
    public long getDataOffset() {
        return this.dataOffset;
    }

    public void setDataOffset(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("The offset can not be smaller than 0");
        }
        this.dataOffset = j;
    }

    public Map<String, String> getExtraPaxHeaders() {
        return Collections.unmodifiableMap(this.extraPaxHeaders);
    }

    public void clearExtraPaxHeaders() {
        this.extraPaxHeaders.clear();
    }

    public void addPaxHeader(String str, String str2) {
        try {
            processPaxHeader(str, str2);
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid input", e);
        }
    }

    public String getExtraPaxHeader(String str) {
        return this.extraPaxHeaders.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateEntryFromPaxHeaders(Map<String, String> map) throws IOException {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            processPaxHeader(entry.getKey(), entry.getValue(), map);
        }
    }

    private void processPaxHeader(String str, String str2) throws IOException {
        processPaxHeader(str, str2, this.extraPaxHeaders);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void processPaxHeader(String str, String str2, Map<String, String> map) throws IOException {
        char c;
        switch (str.hashCode()) {
            case -1916861932:
                if (str.equals("SCHILY.devmajor")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case -1916619760:
                if (str.equals("SCHILY.devminor")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case -277496563:
                if (str.equals("GNU.sparse.realsize")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case -160380561:
                if (str.equals("GNU.sparse.size")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 102338:
                if (str.equals("gid")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 115792:
                if (str.equals(AIUIConstant.KEY_UID)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 3433509:
                if (str.equals("path")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 3530753:
                if (str.equals("size")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 98496370:
                if (str.equals("gname")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 104223930:
                if (str.equals("mtime")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 111425664:
                if (str.equals("uname")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 530706950:
                if (str.equals("SCHILY.filetype")) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case 1195018015:
                if (str.equals("linkpath")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                setName(str2);
                return;
            case 1:
                setLinkName(str2);
                return;
            case 2:
                setGroupId(Long.parseLong(str2));
                return;
            case 3:
                setGroupName(str2);
                return;
            case 4:
                setUserId(Long.parseLong(str2));
                return;
            case 5:
                setUserName(str2);
                return;
            case 6:
                long parseLong = Long.parseLong(str2);
                if (parseLong < 0) {
                    throw new IOException("Corrupted TAR archive. Entry size is negative");
                }
                setSize(parseLong);
                return;
            case 7:
                setModTime((long) (Double.parseDouble(str2) * 1000.0d));
                return;
            case '\b':
                int parseInt = Integer.parseInt(str2);
                if (parseInt < 0) {
                    throw new IOException("Corrupted TAR archive. Dev-Minor is negative");
                }
                setDevMinor(parseInt);
                return;
            case '\t':
                int parseInt2 = Integer.parseInt(str2);
                if (parseInt2 < 0) {
                    throw new IOException("Corrupted TAR archive. Dev-Major is negative");
                }
                setDevMajor(parseInt2);
                return;
            case '\n':
                fillGNUSparse0xData(map);
                return;
            case 11:
                fillGNUSparse1xData(map);
                return;
            case '\f':
                if ("sparse".equals(str2)) {
                    fillStarSparseData(map);
                    return;
                }
                return;
            default:
                this.extraPaxHeaders.put(str, str2);
                return;
        }
    }

    public TarArchiveEntry[] getDirectoryEntries() {
        if (this.file == null || !isDirectory()) {
            return EMPTY_TAR_ARCHIVE_ENTRY_ARRAY;
        }
        ArrayList arrayList = new ArrayList();
        try {
            DirectoryStream<Path> newDirectoryStream = Files.newDirectoryStream(this.file);
            try {
                Iterator<Path> it = newDirectoryStream.iterator();
                while (it.hasNext()) {
                    arrayList.add(new TarArchiveEntry(it.next()));
                }
                if (newDirectoryStream != null) {
                    newDirectoryStream.close();
                }
                return (TarArchiveEntry[]) arrayList.toArray(EMPTY_TAR_ARCHIVE_ENTRY_ARRAY);
            } finally {
            }
        } catch (IOException unused) {
            return EMPTY_TAR_ARCHIVE_ENTRY_ARRAY;
        }
    }

    public void writeEntryHeader(byte[] bArr) {
        try {
            try {
                writeEntryHeader(bArr, TarUtils.DEFAULT_ENCODING, false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException unused) {
            writeEntryHeader(bArr, TarUtils.FALLBACK_ENCODING, false);
        }
    }

    public void writeEntryHeader(byte[] bArr, ZipEncoding zipEncoding, boolean z) throws IOException {
        int writeEntryHeaderField = writeEntryHeaderField(this.modTime, bArr, writeEntryHeaderField(this.size, bArr, writeEntryHeaderField(this.groupId, bArr, writeEntryHeaderField(this.userId, bArr, writeEntryHeaderField(this.mode, bArr, TarUtils.formatNameBytes(this.name, bArr, 0, 100, zipEncoding), 8, z), 8, z), 8, z), 12, z), 12, z);
        int i = 0;
        int i2 = writeEntryHeaderField;
        while (i < 8) {
            bArr[i2] = 32;
            i++;
            i2++;
        }
        bArr[i2] = this.linkFlag;
        for (int writeEntryHeaderField2 = writeEntryHeaderField(this.devMinor, bArr, writeEntryHeaderField(this.devMajor, bArr, TarUtils.formatNameBytes(this.groupName, bArr, TarUtils.formatNameBytes(this.userName, bArr, TarUtils.formatNameBytes(this.version, bArr, TarUtils.formatNameBytes(this.magic, bArr, TarUtils.formatNameBytes(this.linkName, bArr, i2 + 1, 100, zipEncoding), 6), 2), 32, zipEncoding), 32, zipEncoding), 8, z), 8, z); writeEntryHeaderField2 < bArr.length; writeEntryHeaderField2++) {
            bArr[writeEntryHeaderField2] = 0;
        }
        TarUtils.formatCheckSumOctalBytes(TarUtils.computeCheckSum(bArr), bArr, writeEntryHeaderField, 8);
    }

    private int writeEntryHeaderField(long j, byte[] bArr, int i, int i2, boolean z) {
        if (!z && (j < 0 || j >= (1 << ((i2 - 1) * 3)))) {
            return TarUtils.formatLongOctalBytes(0L, bArr, i, i2);
        }
        return TarUtils.formatLongOctalOrBinaryBytes(j, bArr, i, i2);
    }

    public void parseTarHeader(byte[] bArr) {
        try {
            try {
                parseTarHeader(bArr, TarUtils.DEFAULT_ENCODING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException unused) {
            parseTarHeader(bArr, TarUtils.DEFAULT_ENCODING, true, false);
        }
    }

    public void parseTarHeader(byte[] bArr, ZipEncoding zipEncoding) throws IOException {
        parseTarHeader(bArr, zipEncoding, false, false);
    }

    private void parseTarHeader(byte[] bArr, ZipEncoding zipEncoding, boolean z, boolean z2) throws IOException {
        try {
            parseTarHeaderUnwrapped(bArr, zipEncoding, z, z2);
        } catch (IllegalArgumentException e) {
            throw new IOException("Corrupted TAR archive.", e);
        }
    }

    private void parseTarHeaderUnwrapped(byte[] bArr, ZipEncoding zipEncoding, boolean z, boolean z2) throws IOException {
        String parseName;
        String parseName2;
        String parseName3;
        String parseName4;
        String parseName5;
        String parseName6;
        if (z) {
            parseName = TarUtils.parseName(bArr, 0, 100);
        } else {
            parseName = TarUtils.parseName(bArr, 0, 100, zipEncoding);
        }
        this.name = parseName;
        this.mode = (int) parseOctalOrBinary(bArr, 100, 8, z2);
        this.userId = (int) parseOctalOrBinary(bArr, 108, 8, z2);
        this.groupId = (int) parseOctalOrBinary(bArr, 116, 8, z2);
        this.size = TarUtils.parseOctalOrBinary(bArr, 124, 12);
        if (this.size < 0) {
            throw new IOException("broken archive, entry with negative size");
        }
        this.modTime = parseOctalOrBinary(bArr, 136, 12, z2);
        this.checkSumOK = TarUtils.verifyCheckSum(bArr);
        this.linkFlag = bArr[156];
        if (z) {
            parseName2 = TarUtils.parseName(bArr, 157, 100);
        } else {
            parseName2 = TarUtils.parseName(bArr, 157, 100, zipEncoding);
        }
        this.linkName = parseName2;
        this.magic = TarUtils.parseName(bArr, 257, 6);
        this.version = TarUtils.parseName(bArr, TarConstants.VERSION_OFFSET, 2);
        if (z) {
            parseName3 = TarUtils.parseName(bArr, 265, 32);
        } else {
            parseName3 = TarUtils.parseName(bArr, 265, 32, zipEncoding);
        }
        this.userName = parseName3;
        if (z) {
            parseName4 = TarUtils.parseName(bArr, 297, 32);
        } else {
            parseName4 = TarUtils.parseName(bArr, 297, 32, zipEncoding);
        }
        this.groupName = parseName4;
        byte b = this.linkFlag;
        if (b == 51 || b == 52) {
            this.devMajor = (int) parseOctalOrBinary(bArr, 329, 8, z2);
            this.devMinor = (int) parseOctalOrBinary(bArr, 337, 8, z2);
        }
        int evaluateType = evaluateType(bArr);
        if (evaluateType == 2) {
            this.sparseHeaders = new ArrayList(TarUtils.readSparseStructs(bArr, 386, 4));
            this.isExtended = TarUtils.parseBoolean(bArr, 482);
            this.realSize = TarUtils.parseOctal(bArr, 483, 12);
            return;
        }
        if (evaluateType == 4) {
            if (z) {
                parseName5 = TarUtils.parseName(bArr, 345, 131);
            } else {
                parseName5 = TarUtils.parseName(bArr, 345, 131, zipEncoding);
            }
            if (parseName5.isEmpty()) {
                return;
            }
            this.name = parseName5 + "/" + this.name;
            return;
        }
        if (z) {
            parseName6 = TarUtils.parseName(bArr, 345, 155);
        } else {
            parseName6 = TarUtils.parseName(bArr, 345, 155, zipEncoding);
        }
        if (isDirectory() && !this.name.endsWith("/")) {
            this.name += "/";
        }
        if (parseName6.isEmpty()) {
            return;
        }
        this.name = parseName6 + "/" + this.name;
    }

    private long parseOctalOrBinary(byte[] bArr, int i, int i2, boolean z) {
        if (z) {
            try {
                return TarUtils.parseOctalOrBinary(bArr, i, i2);
            } catch (IllegalArgumentException unused) {
                return -1L;
            }
        }
        return TarUtils.parseOctalOrBinary(bArr, i, i2);
    }

    private static String normalizeFileName(String str, boolean z) {
        String lowerCase;
        int indexOf;
        if (!z && (lowerCase = System.getProperty("os.name").toLowerCase(Locale.ENGLISH)) != null) {
            if (lowerCase.startsWith("windows")) {
                if (str.length() > 2) {
                    char charAt = str.charAt(0);
                    if (str.charAt(1) == ':' && ((charAt >= 'a' && charAt <= 'z') || (charAt >= 'A' && charAt <= 'Z'))) {
                        str = str.substring(2);
                    }
                }
            } else if (lowerCase.contains("netware") && (indexOf = str.indexOf(58)) != -1) {
                str = str.substring(indexOf + 1);
            }
        }
        String replace = str.replace(File.separatorChar, '/');
        while (!z && replace.startsWith("/")) {
            replace = replace.substring(1);
        }
        return replace;
    }

    private int evaluateType(byte[] bArr) {
        if (ArchiveUtils.matchAsciiBuffer(TarConstants.MAGIC_GNU, bArr, 257, 6)) {
            return 2;
        }
        if (ArchiveUtils.matchAsciiBuffer("ustar\u0000", bArr, 257, 6)) {
            return ArchiveUtils.matchAsciiBuffer(TarConstants.MAGIC_XSTAR, bArr, 508, 4) ? 4 : 3;
        }
        return 0;
    }

    void fillGNUSparse0xData(Map<String, String> map) {
        this.paxGNUSparse = true;
        this.realSize = Integer.parseInt(map.get("GNU.sparse.size"));
        if (map.containsKey("GNU.sparse.name")) {
            this.name = map.get("GNU.sparse.name");
        }
    }

    void fillGNUSparse1xData(Map<String, String> map) throws IOException {
        this.paxGNUSparse = true;
        this.paxGNU1XSparse = true;
        if (map.containsKey("GNU.sparse.name")) {
            this.name = map.get("GNU.sparse.name");
        }
        if (map.containsKey("GNU.sparse.realsize")) {
            try {
                this.realSize = Integer.parseInt(map.get("GNU.sparse.realsize"));
            } catch (NumberFormatException unused) {
                throw new IOException("Corrupted TAR archive. GNU.sparse.realsize header for " + this.name + " contains non-numeric value");
            }
        }
    }

    void fillStarSparseData(Map<String, String> map) throws IOException {
        this.starSparse = true;
        if (map.containsKey("SCHILY.realsize")) {
            try {
                this.realSize = Long.parseLong(map.get("SCHILY.realsize"));
            } catch (NumberFormatException unused) {
                throw new IOException("Corrupted TAR archive. SCHILY.realsize header for " + this.name + " contains non-numeric value");
            }
        }
    }
}
