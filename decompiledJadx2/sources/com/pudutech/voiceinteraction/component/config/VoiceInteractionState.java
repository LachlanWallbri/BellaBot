package com.pudutech.voiceinteraction.component.config;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: VoiceInteractionState.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0012\b\u0086\u0001\u0018\u0000 \u00142\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0014B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/config/VoiceInteractionState;", "", "state", "", "(Ljava/lang/String;II)V", "getState", "()I", "Unknown", "Recording", "Idle", "Speaking", "BosTimeout", "Eos", "WakeUp", "PlayCompleted", "Sleep", "ConnectToServer", "DisConnectToServer", "ErrorNetWork", "ErrorAudioError", "Companion", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public enum VoiceInteractionState {
    Unknown(0),
    Recording(1),
    Idle(2),
    Speaking(3),
    BosTimeout(4),
    Eos(5),
    WakeUp(6),
    PlayCompleted(7),
    Sleep(8),
    ConnectToServer(1001),
    DisConnectToServer(1002),
    ErrorNetWork(101),
    ErrorAudioError(102);


    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int state;

    VoiceInteractionState(int i) {
        this.state = i;
    }

    public final int getState() {
        return this.state;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* compiled from: VoiceInteractionState.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/config/VoiceInteractionState$Companion;", "", "()V", "stateOf", "Lcom/pudutech/voiceinteraction/component/config/VoiceInteractionState;", "state", "", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final VoiceInteractionState stateOf(int state) {
            for (VoiceInteractionState voiceInteractionState : VoiceInteractionState.values()) {
                if (voiceInteractionState.getState() == state) {
                    return voiceInteractionState;
                }
            }
            return VoiceInteractionState.Unknown;
        }
    }
}
