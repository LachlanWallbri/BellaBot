package com.pudu.library.loracall.viewModel;

import com.pudu.library.loracall.LoraSignalStrength;
import com.pudu.library.loracall.MsgReceiveHandle;
import com.pudu.library.loracall.ReceiveMsgType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: LoraPerformanceViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, m3961d2 = {"com/pudu/library/loracall/viewModel/LoraPerformanceViewModel$listener$1", "Lcom/pudu/library/loracall/MsgReceiveHandle$Listener;", "receive", "", "msgType", "Lcom/pudu/library/loracall/ReceiveMsgType;", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class LoraPerformanceViewModel$listener$1 implements MsgReceiveHandle.Listener {
    final /* synthetic */ LoraPerformanceViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LoraPerformanceViewModel$listener$1(LoraPerformanceViewModel loraPerformanceViewModel) {
        this.this$0 = loraPerformanceViewModel;
    }

    @Override // com.pudu.library.loracall.MsgReceiveHandle.Listener
    public void receive(ReceiveMsgType msgType) {
        Intrinsics.checkParameterIsNotNull(msgType, "msgType");
        if (msgType instanceof LoraSignalStrength) {
            BuildersKt__Builders_commonKt.launch$default(this.this$0.getScope(), Dispatchers.getMain(), null, new LoraPerformanceViewModel$listener$1$receive$1(this, msgType, null), 2, null);
        }
    }
}
