package com.pudutech.bumblebee.business.ims;

import com.pudutech.bumblebee.business.ims.IMSKit;
import com.pudutech.bumblebee.business.ims.config.CallPoint;
import com.pudutech.bumblebee.business.ims.config.MsgType;
import com.pudutech.bumblebee.business.ims.utils.ShortUUID;
import com.pudutech.freeinstall_ui.utils.Constants;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: IMSKit.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m3961d2 = {"<anonymous>", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class IMSKit$syncTaskPartitionTable$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ ArrayList $callPointList;
    final /* synthetic */ Function0 $onSyncTaskPartitionTableFailed;
    final /* synthetic */ Function1 $onSyncTaskPartitionTablePercent;
    final /* synthetic */ Function0 $onSyncTaskPartitionTableSucceed;
    final /* synthetic */ String $version;
    final /* synthetic */ IMSKit this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IMSKit$syncTaskPartitionTable$1(IMSKit iMSKit, ArrayList arrayList, Function0 function0, Function1 function1, String str, Function0 function02) {
        super(0);
        this.this$0 = iMSKit;
        this.$callPointList = arrayList;
        this.$onSyncTaskPartitionTableSucceed = function0;
        this.$onSyncTaskPartitionTablePercent = function1;
        this.$version = str;
        this.$onSyncTaskPartitionTableFailed = function02;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v10, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v8, types: [T, java.lang.String] */
    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        ?? r0 = (String) 0;
        objectRef2.element = r0;
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        objectRef3.element = r0;
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = 0;
        final ArrayList arrayList = new ArrayList();
        final String str = Constants.POINT_TYPE_TABLE;
        objectRef.element = Constants.POINT_TYPE_TABLE;
        if (this.$callPointList.size() >= 5) {
            arrayList.addAll(this.$callPointList.subList(0, 5));
            if (this.$callPointList.size() > 5) {
                objectRef2.element = Constants.POINT_TYPE_TABLE + ShortUUID.INSTANCE.randomUUID();
                objectRef3.element = (String) objectRef2.element;
                booleanRef.element = false;
            } else {
                objectRef2.element = r0;
                booleanRef.element = true;
            }
        } else {
            arrayList.addAll(this.$callPointList);
            booleanRef.element = true;
        }
        final int i = 5;
        this.this$0.syncCallPointData(MsgType.SyncTaskPartitionTable, this.$version, arrayList, (String) objectRef.element, (String) objectRef2.element, booleanRef.element, new IMSKit.OnSyncCallPointListener() { // from class: com.pudutech.bumblebee.business.ims.IMSKit$syncTaskPartitionTable$1$onSyncCallPointListener$1
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r0v16, types: [T, java.lang.String] */
            /* JADX WARN: Type inference failed for: r0v35, types: [T, java.lang.String] */
            /* JADX WARN: Type inference failed for: r0v38, types: [T, java.lang.String] */
            /* JADX WARN: Type inference failed for: r2v1, types: [T, java.lang.String] */
            /* JADX WARN: Type inference failed for: r2v3, types: [T, java.lang.String] */
            @Override // com.pudutech.bumblebee.business.ims.IMSKit.OnSyncCallPointListener
            public void onSucceed(ArrayList<CallPoint> list) {
                Intrinsics.checkParameterIsNotNull(list, "list");
                intRef.element += list.size();
                arrayList.clear();
                if (intRef.element == IMSKit$syncTaskPartitionTable$1.this.$callPointList.size()) {
                    Function0 function0 = IMSKit$syncTaskPartitionTable$1.this.$onSyncTaskPartitionTableSucceed;
                    if (function0 != null) {
                        return;
                    }
                    return;
                }
                int size = (int) (((intRef.element * 1.0f) / IMSKit$syncTaskPartitionTable$1.this.$callPointList.size()) * 100);
                Function1 function1 = IMSKit$syncTaskPartitionTable$1.this.$onSyncTaskPartitionTablePercent;
                if (function1 != null) {
                }
                Ref.ObjectRef objectRef4 = objectRef;
                ?? r02 = (String) objectRef3.element;
                if (r02 == 0) {
                    Intrinsics.throwNpe();
                }
                objectRef4.element = r02;
                if (IMSKit$syncTaskPartitionTable$1.this.$callPointList.size() - intRef.element >= i) {
                    arrayList.addAll(IMSKit$syncTaskPartitionTable$1.this.$callPointList.subList(intRef.element, intRef.element + i));
                    if (IMSKit$syncTaskPartitionTable$1.this.$callPointList.size() - intRef.element > i) {
                        objectRef2.element = str + ShortUUID.INSTANCE.randomUUID();
                        objectRef3.element = (String) objectRef2.element;
                        booleanRef.element = false;
                    } else {
                        objectRef2.element = (String) 0;
                        booleanRef.element = true;
                    }
                } else {
                    arrayList.addAll(IMSKit$syncTaskPartitionTable$1.this.$callPointList.subList(intRef.element, IMSKit$syncTaskPartitionTable$1.this.$callPointList.size()));
                    objectRef2.element = (String) 0;
                    booleanRef.element = true;
                }
                IMSKit$syncTaskPartitionTable$1.this.this$0.syncCallPointData(MsgType.SyncTaskPartitionTable, IMSKit$syncTaskPartitionTable$1.this.$version, arrayList, (String) objectRef.element, (String) objectRef2.element, booleanRef.element, this);
            }

            @Override // com.pudutech.bumblebee.business.ims.IMSKit.OnSyncCallPointListener
            public void onFailed() {
                Function0 function0 = IMSKit$syncTaskPartitionTable$1.this.$onSyncTaskPartitionTableFailed;
                if (function0 != null) {
                }
            }
        });
    }
}
