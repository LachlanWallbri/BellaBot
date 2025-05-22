package com.pudutech.voiceinteraction.component;

import com.pudutech.base.Pdlog;
import com.pudutech.voiceinteraction.component.cmd.CmdBean;
import com.pudutech.voiceinteraction.component.config.VoiceInteractionState;
import com.pudutech.voiceinteraction.component.config.WakeupInfo;
import com.pudutech.voiceinteraction.component.listener.IVoiceInteractionListener;
import com.pudutech.voiceinteraction.component.log.LogProxy;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VoiceInteractionHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000;\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0007H\u0016J\u001a\u0010\b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\nH\u0016J$\u0010\u000b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u000eH\u0016J\u0012\u0010\u0013\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0014H\u0016¨\u0006\u0015"}, m3961d2 = {"com/pudutech/voiceinteraction/component/VoiceInteractionHelper$mainListener$1", "Lcom/pudutech/voiceinteraction/component/listener/IVoiceInteractionListener;", "offlineCmd", "", "data", "", "onCmdResponse", "Lcom/pudutech/voiceinteraction/component/cmd/CmdBean;", "onResultRequest", "boolean", "", "onResultResponse", "json", "state", "", "onStatusChanged", "Lcom/pudutech/voiceinteraction/component/config/VoiceInteractionState;", "onVolumeChanged", "volume", "onWakeup", "Lcom/pudutech/voiceinteraction/component/config/WakeupInfo;", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class VoiceInteractionHelper$mainListener$1 implements IVoiceInteractionListener {
    final /* synthetic */ VoiceInteractionHelper this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public VoiceInteractionHelper$mainListener$1(VoiceInteractionHelper voiceInteractionHelper) {
        this.this$0 = voiceInteractionHelper;
    }

    @Override // com.pudutech.voiceinteraction.component.listener.IVoiceInteractionListener
    public void onWakeup(final WakeupInfo data) {
        CopyOnWriteArrayList copyOnWriteArrayList;
        StringBuilder sb = new StringBuilder();
        sb.append("mainListener data=");
        copyOnWriteArrayList = this.this$0.voiceListeners;
        sb.append(copyOnWriteArrayList.size());
        Pdlog.m3273d(VoiceInteractionHelper.TAG, sb.toString());
        this.this$0.notifyInMain(new Function0<Unit>() { // from class: com.pudutech.voiceinteraction.component.VoiceInteractionHelper$mainListener$1$onWakeup$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                CopyOnWriteArrayList copyOnWriteArrayList2;
                copyOnWriteArrayList2 = VoiceInteractionHelper$mainListener$1.this.this$0.voiceListeners;
                Iterator it = copyOnWriteArrayList2.iterator();
                while (it.hasNext()) {
                    ((IVoiceInteractionListener) it.next()).onWakeup(data);
                }
            }
        });
    }

    @Override // com.pudutech.voiceinteraction.component.listener.IVoiceInteractionListener
    public void onResultRequest(final String data, final boolean r5) {
        CopyOnWriteArrayList copyOnWriteArrayList;
        CopyOnWriteArrayList copyOnWriteArrayList2;
        LogProxy logProxy = LogProxy.INSTANCE;
        StringBuilder sb = new StringBuilder();
        sb.append("data = ");
        sb.append(data);
        sb.append(", voiceListeners = ");
        copyOnWriteArrayList = this.this$0.voiceListeners;
        sb.append(copyOnWriteArrayList);
        sb.append(' ');
        copyOnWriteArrayList2 = this.this$0.voiceListeners;
        sb.append(copyOnWriteArrayList2.size());
        logProxy.m3305d(VoiceInteractionHelper.TAG, sb.toString());
        this.this$0.notifyInMain(new Function0<Unit>() { // from class: com.pudutech.voiceinteraction.component.VoiceInteractionHelper$mainListener$1$onResultRequest$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                CopyOnWriteArrayList copyOnWriteArrayList3;
                copyOnWriteArrayList3 = VoiceInteractionHelper$mainListener$1.this.this$0.voiceListeners;
                Iterator it = copyOnWriteArrayList3.iterator();
                while (it.hasNext()) {
                    ((IVoiceInteractionListener) it.next()).onResultRequest(data, r5);
                }
            }
        });
    }

    @Override // com.pudutech.voiceinteraction.component.listener.IVoiceInteractionListener
    public void onResultResponse(final String data, final String json, final int state) {
        this.this$0.notifyInMain(new Function0<Unit>() { // from class: com.pudutech.voiceinteraction.component.VoiceInteractionHelper$mainListener$1$onResultResponse$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                CopyOnWriteArrayList copyOnWriteArrayList;
                copyOnWriteArrayList = VoiceInteractionHelper$mainListener$1.this.this$0.voiceListeners;
                Iterator it = copyOnWriteArrayList.iterator();
                while (it.hasNext()) {
                    ((IVoiceInteractionListener) it.next()).onResultResponse(data, json, state);
                }
            }
        });
    }

    @Override // com.pudutech.voiceinteraction.component.listener.IVoiceInteractionListener
    public void onCmdResponse(final CmdBean data) {
        this.this$0.notifyInMain(new Function0<Unit>() { // from class: com.pudutech.voiceinteraction.component.VoiceInteractionHelper$mainListener$1$onCmdResponse$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                CopyOnWriteArrayList copyOnWriteArrayList;
                copyOnWriteArrayList = VoiceInteractionHelper$mainListener$1.this.this$0.voiceListeners;
                Iterator it = copyOnWriteArrayList.iterator();
                while (it.hasNext()) {
                    ((IVoiceInteractionListener) it.next()).onCmdResponse(data);
                }
            }
        });
    }

    @Override // com.pudutech.voiceinteraction.component.listener.IVoiceInteractionListener
    public void onVolumeChanged(final int volume) {
        this.this$0.notifyInMain(new Function0<Unit>() { // from class: com.pudutech.voiceinteraction.component.VoiceInteractionHelper$mainListener$1$onVolumeChanged$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                CopyOnWriteArrayList copyOnWriteArrayList;
                copyOnWriteArrayList = VoiceInteractionHelper$mainListener$1.this.this$0.voiceListeners;
                Iterator it = copyOnWriteArrayList.iterator();
                while (it.hasNext()) {
                    ((IVoiceInteractionListener) it.next()).onVolumeChanged((volume * ((int) 3.33d)) + 1);
                }
            }
        });
    }

    @Override // com.pudutech.voiceinteraction.component.listener.IVoiceInteractionListener
    public void onStatusChanged(final VoiceInteractionState state) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        this.this$0.notifyInMain(new Function0<Unit>() { // from class: com.pudutech.voiceinteraction.component.VoiceInteractionHelper$mainListener$1$onStatusChanged$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                CopyOnWriteArrayList copyOnWriteArrayList;
                copyOnWriteArrayList = VoiceInteractionHelper$mainListener$1.this.this$0.voiceListeners;
                Iterator it = copyOnWriteArrayList.iterator();
                while (it.hasNext()) {
                    ((IVoiceInteractionListener) it.next()).onStatusChanged(state);
                }
            }
        });
    }

    @Override // com.pudutech.voiceinteraction.component.listener.IVoiceInteractionListener
    public void offlineCmd(final String data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        this.this$0.notifyInMain(new Function0<Unit>() { // from class: com.pudutech.voiceinteraction.component.VoiceInteractionHelper$mainListener$1$offlineCmd$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                CopyOnWriteArrayList copyOnWriteArrayList;
                copyOnWriteArrayList = VoiceInteractionHelper$mainListener$1.this.this$0.voiceListeners;
                Iterator it = copyOnWriteArrayList.iterator();
                while (it.hasNext()) {
                    ((IVoiceInteractionListener) it.next()).offlineCmd(data);
                }
            }
        });
    }
}
