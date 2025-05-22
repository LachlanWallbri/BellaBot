package com.pudutech.bumblebee.robot_ui.p054ui.adapter;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.util.ToastUtils;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RecycleTaskAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/adapter/RecycleTaskItem;", "", "destination", "", "selected", "", "(Ljava/lang/String;Z)V", "getDestination", "()Ljava/lang/String;", "setDestination", "(Ljava/lang/String;)V", "getSelected", "()Z", "setSelected", "(Z)V", "Companion", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class RecycleTaskItem {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private String destination;
    private boolean selected;

    /* compiled from: RecycleTaskAdapter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J.\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u0004¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/adapter/RecycleTaskItem$Companion;", "", "()V", "checkConsecutive", "", "data", "", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/RecycleTaskItem;", RequestParameters.POSITION, "", "destination", "", "isDelete", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ boolean checkConsecutive$default(Companion companion, List list, int i, String str, boolean z, int i2, Object obj) {
            if ((i2 & 8) != 0) {
                z = false;
            }
            return companion.checkConsecutive(list, i, str, z);
        }

        /* JADX WARN: Code restructure failed: missing block: B:21:0x0041, code lost:
        
            if (kotlin.jvm.internal.Intrinsics.areEqual(r1 != null ? r1.getDestination() : null, r5 != null ? r5.getDestination() : null) != false) goto L33;
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x005c, code lost:
        
            if (kotlin.jvm.internal.Intrinsics.areEqual(r7, r5 != null ? r5.getDestination() : null) != false) goto L33;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final boolean checkConsecutive(List<RecycleTaskItem> data, int position, String destination, boolean isDelete) {
            Intrinsics.checkParameterIsNotNull(data, "data");
            Intrinsics.checkParameterIsNotNull(destination, "destination");
            RecycleTaskItem recycleTaskItem = position == 0 ? null : data.get(position - 1);
            boolean z = true;
            RecycleTaskItem recycleTaskItem2 = position == data.size() - 1 ? null : data.get(position + 1);
            if (isDelete) {
                if (recycleTaskItem != null || recycleTaskItem2 != null) {
                }
                z = false;
            } else {
                if (!Intrinsics.areEqual(destination, recycleTaskItem != null ? recycleTaskItem.getDestination() : null)) {
                }
            }
            if (z) {
                ToastUtils.show(RobotContext.INSTANCE.getContext(), C4188R.string.tips_consecutive_same_table);
            }
            return z;
        }
    }

    public RecycleTaskItem(String destination, boolean z) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        this.destination = destination;
        this.selected = z;
    }

    public /* synthetic */ RecycleTaskItem(String str, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? false : z);
    }

    public final String getDestination() {
        return this.destination;
    }

    public final boolean getSelected() {
        return this.selected;
    }

    public final void setDestination(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.destination = str;
    }

    public final void setSelected(boolean z) {
        this.selected = z;
    }
}
