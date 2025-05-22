package com.liulishuo.okdownload.core.breakpoint;

import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.Util;
import com.liulishuo.okdownload.core.download.DownloadStrategy;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class BreakpointInfo {
    private final List<BlockInfo> blockInfoList = new ArrayList();
    private boolean chunked;
    private String etag;
    private final DownloadStrategy.FilenameHolder filenameHolder;

    /* renamed from: id */
    final int f3519id;
    final File parentFile;
    private File targetFile;
    private final boolean taskOnlyProvidedParentPath;
    private final String url;

    public BreakpointInfo(int i, String str, File file, String str2) {
        this.f3519id = i;
        this.url = str;
        this.parentFile = file;
        if (Util.isEmpty(str2)) {
            this.filenameHolder = new DownloadStrategy.FilenameHolder();
            this.taskOnlyProvidedParentPath = true;
        } else {
            this.filenameHolder = new DownloadStrategy.FilenameHolder(str2);
            this.taskOnlyProvidedParentPath = false;
            this.targetFile = new File(file, str2);
        }
    }

    BreakpointInfo(int i, String str, File file, String str2, boolean z) {
        this.f3519id = i;
        this.url = str;
        this.parentFile = file;
        if (Util.isEmpty(str2)) {
            this.filenameHolder = new DownloadStrategy.FilenameHolder();
        } else {
            this.filenameHolder = new DownloadStrategy.FilenameHolder(str2);
        }
        this.taskOnlyProvidedParentPath = z;
    }

    public int getId() {
        return this.f3519id;
    }

    public void setChunked(boolean z) {
        this.chunked = z;
    }

    public void addBlock(BlockInfo blockInfo) {
        this.blockInfoList.add(blockInfo);
    }

    public boolean isChunked() {
        return this.chunked;
    }

    public boolean isLastBlock(int i) {
        return i == this.blockInfoList.size() - 1;
    }

    public boolean isSingleBlock() {
        return this.blockInfoList.size() == 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isTaskOnlyProvidedParentPath() {
        return this.taskOnlyProvidedParentPath;
    }

    public BlockInfo getBlock(int i) {
        return this.blockInfoList.get(i);
    }

    public void resetInfo() {
        this.blockInfoList.clear();
        this.etag = null;
    }

    public void resetBlockInfos() {
        this.blockInfoList.clear();
    }

    public int getBlockCount() {
        return this.blockInfoList.size();
    }

    public void setEtag(String str) {
        this.etag = str;
    }

    public long getTotalOffset() {
        Object[] array = this.blockInfoList.toArray();
        long j = 0;
        if (array != null) {
            for (Object obj : array) {
                if (obj instanceof BlockInfo) {
                    j += ((BlockInfo) obj).getCurrentOffset();
                }
            }
        }
        return j;
    }

    public long getTotalLength() {
        if (isChunked()) {
            return getTotalOffset();
        }
        long j = 0;
        Object[] array = this.blockInfoList.toArray();
        if (array != null) {
            for (Object obj : array) {
                if (obj instanceof BlockInfo) {
                    j += ((BlockInfo) obj).getContentLength();
                }
            }
        }
        return j;
    }

    public String getEtag() {
        return this.etag;
    }

    public String getUrl() {
        return this.url;
    }

    public String getFilename() {
        return this.filenameHolder.get();
    }

    public DownloadStrategy.FilenameHolder getFilenameHolder() {
        return this.filenameHolder;
    }

    public File getFile() {
        String str = this.filenameHolder.get();
        if (str == null) {
            return null;
        }
        if (this.targetFile == null) {
            this.targetFile = new File(this.parentFile, str);
        }
        return this.targetFile;
    }

    public BreakpointInfo copy() {
        BreakpointInfo breakpointInfo = new BreakpointInfo(this.f3519id, this.url, this.parentFile, this.filenameHolder.get(), this.taskOnlyProvidedParentPath);
        breakpointInfo.chunked = this.chunked;
        Iterator<BlockInfo> it = this.blockInfoList.iterator();
        while (it.hasNext()) {
            breakpointInfo.blockInfoList.add(it.next().copy());
        }
        return breakpointInfo;
    }

    public BreakpointInfo copyWithReplaceId(int i) {
        BreakpointInfo breakpointInfo = new BreakpointInfo(i, this.url, this.parentFile, this.filenameHolder.get(), this.taskOnlyProvidedParentPath);
        breakpointInfo.chunked = this.chunked;
        Iterator<BlockInfo> it = this.blockInfoList.iterator();
        while (it.hasNext()) {
            breakpointInfo.blockInfoList.add(it.next().copy());
        }
        return breakpointInfo;
    }

    public void reuseBlocks(BreakpointInfo breakpointInfo) {
        this.blockInfoList.clear();
        this.blockInfoList.addAll(breakpointInfo.blockInfoList);
    }

    public BreakpointInfo copyWithReplaceIdAndUrl(int i, String str) {
        BreakpointInfo breakpointInfo = new BreakpointInfo(i, str, this.parentFile, this.filenameHolder.get(), this.taskOnlyProvidedParentPath);
        breakpointInfo.chunked = this.chunked;
        Iterator<BlockInfo> it = this.blockInfoList.iterator();
        while (it.hasNext()) {
            breakpointInfo.blockInfoList.add(it.next().copy());
        }
        return breakpointInfo;
    }

    public boolean isSameFrom(DownloadTask downloadTask) {
        if (!this.parentFile.equals(downloadTask.getParentFile()) || !this.url.equals(downloadTask.getUrl())) {
            return false;
        }
        String filename = downloadTask.getFilename();
        if (filename != null && filename.equals(this.filenameHolder.get())) {
            return true;
        }
        if (this.taskOnlyProvidedParentPath && downloadTask.isFilenameFromResponse()) {
            return filename == null || filename.equals(this.filenameHolder.get());
        }
        return false;
    }

    public String toString() {
        return "id[" + this.f3519id + "] url[" + this.url + "] etag[" + this.etag + "] taskOnlyProvidedParentPath[" + this.taskOnlyProvidedParentPath + "] parent path[" + this.parentFile + "] filename[" + this.filenameHolder.get() + "] block(s):" + this.blockInfoList.toString();
    }
}
