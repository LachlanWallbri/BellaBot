package com.pudutech.lidar.base;

import android.os.Handler;
import android.os.Message;
import androidx.core.app.NotificationCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.lidar.LidarAdapterCallback;
import com.pudutech.lidar.LidarNode;
import com.pudutech.lidar.ListLidarNodePool;
import com.pudutech.lidar.base.BaseLidarAdapter;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BaseLidarAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00005\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J&\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002J\u001e\u0010\r\u001a\u00020\u00072\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\b\u001a\u00020\tH\u0002J\u001a\u0010\u000e\u001a\u00020\t*\u00020\u000f2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002¨\u0006\u0010"}, m3961d2 = {"com/pudutech/lidar/base/BaseLidarAdapter$handleNodeProcess$1", "Landroid/os/Handler$Callback;", "handleMessage", "", NotificationCompat.CATEGORY_MESSAGE, "Landroid/os/Message;", "preview", "", "viewLidarDatas", "Lcom/pudutech/lidar/ListLidarNodePool;", "list", "", "Lcom/pudutech/lidar/LidarNode;", "recycle", "handleNodes", "Lcom/pudutech/lidar/base/BaseLidarAdapter;", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class BaseLidarAdapter$handleNodeProcess$1 implements Handler.Callback {
    final /* synthetic */ BaseLidarAdapter this$0;

    BaseLidarAdapter$handleNodeProcess$1(BaseLidarAdapter baseLidarAdapter) {
        this.this$0 = baseLidarAdapter;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message msg) {
        Handler handler;
        Message obtainMessage;
        if (msg == null) {
            return false;
        }
        int i = msg.what;
        if (i == 1) {
            Object obj = msg.obj;
            if (obj == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<com.pudutech.lidar.LidarNode>");
            }
            List<? extends LidarNode> list = (List) obj;
            if (BaseLidarAdapter.WhenMappings.$EnumSwitchMapping$0[this.this$0.getVersion().ordinal()] == 1) {
                BaseLidarAdapter.access$calculateFrameCnt(this.this$0);
                this.this$0.getLidarAdapterCallback().onOneFrame(list);
                WeakReference<Handler> viewHandler = this.this$0.getViewHandler();
                if (viewHandler != null && (handler = viewHandler.get()) != null && (obtainMessage = handler.obtainMessage(1, list)) != null) {
                    obtainMessage.sendToTarget();
                }
            } else {
                if (!list.isEmpty()) {
                    this.this$0.checkEndAngle(((LidarNode) CollectionsKt.last((List) list)).angleInRad);
                }
                ListLidarNodePool handleNodes = handleNodes(this.this$0, list);
                if (this.this$0.getLegalCnt() < BaseLidarAdapter.ONE_FRAME_LEGAL_CNT_AT_LEAST) {
                    Pdlog.m3274e(this.this$0.getTAG(), "legal cnt=" + this.this$0.getLegalCnt() + " < " + BaseLidarAdapter.ONE_FRAME_LEGAL_CNT_AT_LEAST + " when legal distance=0.05");
                    LidarErrorType lidarErrorType = LidarErrorType.ILLEGAL_FRAME;
                    StringBuilder sb = new StringBuilder();
                    sb.append("legal cnt is ");
                    sb.append(this.this$0.getLegalCnt());
                    sb.append(". too less");
                    this.this$0.getLidarAdapterCallback().onError(new LidarError(lidarErrorType, sb.toString()));
                    preview(msg, handleNodes, list);
                    return true;
                }
                BaseLidarAdapter.access$calculateFrameCnt(this.this$0);
                LidarAdapterCallback lidarAdapterCallback = this.this$0.getLidarAdapterCallback();
                if (handleNodes == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<com.pudutech.lidar.LidarNode>");
                }
                lidarAdapterCallback.onOneFrame(handleNodes);
                preview(msg, handleNodes, list);
            }
        } else if (i == 256 && (msg.obj instanceof ListLidarNodePool)) {
            Object obj2 = msg.obj;
            if (obj2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.lidar.ListLidarNodePool");
            }
            ListLidarNodePool listLidarNodePool = (ListLidarNodePool) obj2;
            Iterator<LidarNode> it = listLidarNodePool.iterator();
            while (it.hasNext()) {
                it.next().recycle();
            }
            listLidarNodePool.recycle();
        }
        return true;
    }

    private final void preview(Message msg, ListLidarNodePool viewLidarDatas, List<? extends LidarNode> list) {
        Handler handler;
        WeakReference<Handler> viewHandler = this.this$0.getViewHandler();
        if (viewHandler != null && (handler = viewHandler.get()) != null) {
            handler.obtainMessage(1, this.this$0.getViewerOriginDataSwitcher() ? msg.obj : viewLidarDatas).sendToTarget();
        }
        recycle(list, viewLidarDatas);
    }

    private final ListLidarNodePool handleNodes(BaseLidarAdapter baseLidarAdapter, List<? extends LidarNode> list) {
        LidarNode access$handle;
        ListLidarNodePool obtain = ListLidarNodePool.INSTANCE.obtain(list.size());
        baseLidarAdapter.legalCnt = 0;
        for (LidarNode lidarNode : list) {
            if (baseLidarAdapter.getViewHandler() != null && baseLidarAdapter.getViewerOriginDataSwitcher()) {
                LidarNode newNode = LidarNode.obtain();
                newNode.angleInRad = lidarNode.angleInRad;
                newNode.distanceM = lidarNode.distanceM;
                Intrinsics.checkExpressionValueIsNotNull(newNode, "newNode");
                access$handle = BaseLidarAdapter.access$handle(baseLidarAdapter, newNode);
            } else {
                access$handle = BaseLidarAdapter.access$handle(baseLidarAdapter, lidarNode);
            }
            if (access$handle != null) {
                obtain.add(access$handle);
            }
        }
        return obtain;
    }

    private final void recycle(List<? extends LidarNode> list, ListLidarNodePool viewLidarDatas) {
        Handler handler;
        Handler handler2;
        if (this.this$0.getViewHandler() == null) {
            handler = this.this$0.processHandler;
            handler.obtainMessage(256, list).sendToTarget();
            handler2 = this.this$0.processHandler;
            handler2.obtainMessage(256, viewLidarDatas).sendToTarget();
            return;
        }
        if (!this.this$0.getViewerOriginDataSwitcher()) {
            if (list instanceof ListLidarNodePool) {
                ((ListLidarNodePool) list).recycle();
                return;
            }
            return;
        }
        viewLidarDatas.recycle();
    }
}
