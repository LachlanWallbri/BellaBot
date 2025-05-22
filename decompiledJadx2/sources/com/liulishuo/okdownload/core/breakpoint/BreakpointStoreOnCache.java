package com.liulishuo.okdownload.core.breakpoint;

import android.util.SparseArray;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.IdentifiedTask;
import com.liulishuo.okdownload.core.cause.EndCause;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes4.dex */
public class BreakpointStoreOnCache implements DownloadStore {
    public static final int FIRST_ID = 1;
    private final List<Integer> fileDirtyList;
    private final KeyToIdMap keyToIdMap;
    private final HashMap<String, String> responseFilenameMap;
    private final List<Integer> sortedOccupiedIds;
    private final SparseArray<BreakpointInfo> storedInfos;
    private final SparseArray<IdentifiedTask> unStoredTasks;

    @Override // com.liulishuo.okdownload.core.breakpoint.DownloadStore
    public BreakpointInfo getAfterCompleted(int i) {
        return null;
    }

    @Override // com.liulishuo.okdownload.core.breakpoint.BreakpointStore
    public boolean isOnlyMemoryCache() {
        return true;
    }

    @Override // com.liulishuo.okdownload.core.breakpoint.DownloadStore
    public void onTaskStart(int i) {
    }

    public BreakpointStoreOnCache() {
        this(new SparseArray(), new ArrayList(), new HashMap());
    }

    BreakpointStoreOnCache(SparseArray<BreakpointInfo> sparseArray, List<Integer> list, HashMap<String, String> hashMap, SparseArray<IdentifiedTask> sparseArray2, List<Integer> list2, KeyToIdMap keyToIdMap) {
        this.unStoredTasks = sparseArray2;
        this.fileDirtyList = list;
        this.storedInfos = sparseArray;
        this.responseFilenameMap = hashMap;
        this.sortedOccupiedIds = list2;
        this.keyToIdMap = keyToIdMap;
    }

    public BreakpointStoreOnCache(SparseArray<BreakpointInfo> sparseArray, List<Integer> list, HashMap<String, String> hashMap) {
        this.unStoredTasks = new SparseArray<>();
        this.storedInfos = sparseArray;
        this.fileDirtyList = list;
        this.responseFilenameMap = hashMap;
        this.keyToIdMap = new KeyToIdMap();
        int size = sparseArray.size();
        this.sortedOccupiedIds = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            this.sortedOccupiedIds.add(Integer.valueOf(sparseArray.valueAt(i).f3519id));
        }
        Collections.sort(this.sortedOccupiedIds);
    }

    @Override // com.liulishuo.okdownload.core.breakpoint.BreakpointStore
    public BreakpointInfo get(int i) {
        return this.storedInfos.get(i);
    }

    @Override // com.liulishuo.okdownload.core.breakpoint.BreakpointStore
    public BreakpointInfo createAndInsert(DownloadTask downloadTask) {
        int id = downloadTask.getId();
        BreakpointInfo breakpointInfo = new BreakpointInfo(id, downloadTask.getUrl(), downloadTask.getParentFile(), downloadTask.getFilename());
        synchronized (this) {
            this.storedInfos.put(id, breakpointInfo);
            this.unStoredTasks.remove(id);
        }
        return breakpointInfo;
    }

    @Override // com.liulishuo.okdownload.core.breakpoint.DownloadStore
    public void onSyncToFilesystemSuccess(BreakpointInfo breakpointInfo, int i, long j) throws IOException {
        BreakpointInfo breakpointInfo2 = this.storedInfos.get(breakpointInfo.f3519id);
        if (breakpointInfo != breakpointInfo2) {
            throw new IOException("Info not on store!");
        }
        breakpointInfo2.getBlock(i).increaseCurrentOffset(j);
    }

    @Override // com.liulishuo.okdownload.core.breakpoint.BreakpointStore
    public boolean update(BreakpointInfo breakpointInfo) {
        String filename = breakpointInfo.getFilename();
        if (breakpointInfo.isTaskOnlyProvidedParentPath() && filename != null) {
            this.responseFilenameMap.put(breakpointInfo.getUrl(), filename);
        }
        BreakpointInfo breakpointInfo2 = this.storedInfos.get(breakpointInfo.f3519id);
        if (breakpointInfo2 == null) {
            return false;
        }
        if (breakpointInfo2 == breakpointInfo) {
            return true;
        }
        synchronized (this) {
            this.storedInfos.put(breakpointInfo.f3519id, breakpointInfo.copy());
        }
        return true;
    }

    @Override // com.liulishuo.okdownload.core.breakpoint.DownloadStore
    public void onTaskEnd(int i, EndCause endCause, Exception exc) {
        if (endCause == EndCause.COMPLETED) {
            remove(i);
        }
    }

    @Override // com.liulishuo.okdownload.core.breakpoint.DownloadStore
    public boolean markFileDirty(int i) {
        if (this.fileDirtyList.contains(Integer.valueOf(i))) {
            return false;
        }
        synchronized (this.fileDirtyList) {
            if (this.fileDirtyList.contains(Integer.valueOf(i))) {
                return false;
            }
            this.fileDirtyList.add(Integer.valueOf(i));
            return true;
        }
    }

    @Override // com.liulishuo.okdownload.core.breakpoint.DownloadStore
    public boolean markFileClear(int i) {
        boolean remove;
        synchronized (this.fileDirtyList) {
            remove = this.fileDirtyList.remove(Integer.valueOf(i));
        }
        return remove;
    }

    @Override // com.liulishuo.okdownload.core.breakpoint.BreakpointStore
    public synchronized void remove(int i) {
        this.storedInfos.remove(i);
        if (this.unStoredTasks.get(i) == null) {
            this.sortedOccupiedIds.remove(Integer.valueOf(i));
        }
        this.keyToIdMap.remove(i);
    }

    @Override // com.liulishuo.okdownload.core.breakpoint.BreakpointStore
    public synchronized int findOrCreateId(DownloadTask downloadTask) {
        Integer num = this.keyToIdMap.get(downloadTask);
        if (num != null) {
            return num.intValue();
        }
        int size = this.storedInfos.size();
        for (int i = 0; i < size; i++) {
            BreakpointInfo valueAt = this.storedInfos.valueAt(i);
            if (valueAt != null && valueAt.isSameFrom(downloadTask)) {
                return valueAt.f3519id;
            }
        }
        int size2 = this.unStoredTasks.size();
        for (int i2 = 0; i2 < size2; i2++) {
            IdentifiedTask valueAt2 = this.unStoredTasks.valueAt(i2);
            if (valueAt2 != null && valueAt2.compareIgnoreId(downloadTask)) {
                return valueAt2.getId();
            }
        }
        int allocateId = allocateId();
        this.unStoredTasks.put(allocateId, downloadTask.mock(allocateId));
        this.keyToIdMap.add(downloadTask, allocateId);
        return allocateId;
    }

    @Override // com.liulishuo.okdownload.core.breakpoint.BreakpointStore
    public BreakpointInfo findAnotherInfoFromCompare(DownloadTask downloadTask, BreakpointInfo breakpointInfo) {
        SparseArray<BreakpointInfo> clone;
        synchronized (this) {
            clone = this.storedInfos.clone();
        }
        int size = clone.size();
        for (int i = 0; i < size; i++) {
            BreakpointInfo valueAt = clone.valueAt(i);
            if (valueAt != breakpointInfo && valueAt.isSameFrom(downloadTask)) {
                return valueAt;
            }
        }
        return null;
    }

    @Override // com.liulishuo.okdownload.core.breakpoint.BreakpointStore
    public boolean isFileDirty(int i) {
        return this.fileDirtyList.contains(Integer.valueOf(i));
    }

    @Override // com.liulishuo.okdownload.core.breakpoint.BreakpointStore
    public String getResponseFilename(String str) {
        return this.responseFilenameMap.get(str);
    }

    synchronized int allocateId() {
        int i;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            i = 1;
            if (i3 >= this.sortedOccupiedIds.size()) {
                i3 = 0;
                break;
            }
            Integer num = this.sortedOccupiedIds.get(i3);
            if (num == null) {
                i2 = i4 + 1;
                break;
            }
            int intValue = num.intValue();
            if (i4 != 0) {
                int i5 = i4 + 1;
                if (intValue != i5) {
                    i2 = i5;
                    break;
                }
                i3++;
                i4 = intValue;
            } else {
                if (intValue != 1) {
                    i3 = 0;
                    i2 = 1;
                    break;
                }
                i3++;
                i4 = intValue;
            }
        }
        if (i2 != 0) {
            i = i2;
        } else if (!this.sortedOccupiedIds.isEmpty()) {
            i = 1 + this.sortedOccupiedIds.get(this.sortedOccupiedIds.size() - 1).intValue();
            i3 = this.sortedOccupiedIds.size();
        }
        this.sortedOccupiedIds.add(i3, Integer.valueOf(i));
        return i;
    }
}
