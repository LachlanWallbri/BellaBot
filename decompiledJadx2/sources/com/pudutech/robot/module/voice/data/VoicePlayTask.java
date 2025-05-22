package com.pudutech.robot.module.voice.data;

import com.iflytek.aiui.AIUIConstant;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;

/* compiled from: VoicePlayTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0014\b\u0086\b\u0018\u00002\u00020\u0001B\u0086\u0001\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012S\b\u0002\u0010\n\u001aM\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0015\u0012\u0013\u0018\u00010\u0010¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0011\u0012\u0015\u0012\u0013\u0018\u00010\u0012¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u000b¢\u0006\u0002\u0010\u0015J\u0019\u0010 \u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005HÆ\u0003J\t\u0010!\u001a\u00020\u0007HÆ\u0003J\t\u0010\"\u001a\u00020\tHÆ\u0003JT\u0010#\u001aM\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0015\u0012\u0013\u0018\u00010\u0010¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0011\u0012\u0015\u0012\u0013\u0018\u00010\u0012¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u000bHÆ\u0003J\u008c\u0001\u0010$\u001a\u00020\u00002\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2S\b\u0002\u0010\n\u001aM\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0015\u0012\u0013\u0018\u00010\u0010¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0011\u0012\u0015\u0012\u0013\u0018\u00010\u0012¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u000bHÆ\u0001J\u0013\u0010%\u001a\u00020\u00122\b\u0010&\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010'\u001a\u0004\u0018\u00010\u0004J\b\u0010(\u001a\u0004\u0018\u00010\u0004J\t\u0010)\u001a\u00020\u0017HÖ\u0001J\t\u0010*\u001a\u00020\u0010HÖ\u0001R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\\\u0010\n\u001aM\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0015\u0012\u0013\u0018\u00010\u0010¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0011\u0012\u0015\u0012\u0013\u0018\u00010\u0012¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR!\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u0006+"}, m3961d2 = {"Lcom/pudutech/robot/module/voice/data/VoicePlayTask;", "", "voiceList", "Ljava/util/ArrayList;", "Lcom/pudutech/robot/module/voice/data/BaseVoice;", "Lkotlin/collections/ArrayList;", "mode", "Lcom/pudutech/robot/module/voice/data/VoicePlayMode;", "playType", "Lcom/pudutech/robot/module/voice/data/VoicePlayType;", "playListener", "Lkotlin/Function3;", "Lcom/pudutech/robot/module/voice/data/PlayEvent;", "Lkotlin/ParameterName;", "name", "event", "", AIUIConstant.KEY_CONTENT, "", "isCombine", "", "(Ljava/util/ArrayList;Lcom/pudutech/robot/module/voice/data/VoicePlayMode;Lcom/pudutech/robot/module/voice/data/VoicePlayType;Lkotlin/jvm/functions/Function3;)V", "index", "", "getMode", "()Lcom/pudutech/robot/module/voice/data/VoicePlayMode;", "getPlayListener", "()Lkotlin/jvm/functions/Function3;", "getPlayType", "()Lcom/pudutech/robot/module/voice/data/VoicePlayType;", "getVoiceList", "()Ljava/util/ArrayList;", "component1", "component2", "component3", "component4", "copy", "equals", "other", "getCurVoice", "getNextPlayVoice", "hashCode", "toString", "module_robot_voice_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class VoicePlayTask {
    private volatile int index;
    private final VoicePlayMode mode;
    private final Function3<PlayEvent, String, Boolean, Unit> playListener;
    private final VoicePlayType playType;
    private final ArrayList<BaseVoice> voiceList;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ VoicePlayTask copy$default(VoicePlayTask voicePlayTask, ArrayList arrayList, VoicePlayMode voicePlayMode, VoicePlayType voicePlayType, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            arrayList = voicePlayTask.voiceList;
        }
        if ((i & 2) != 0) {
            voicePlayMode = voicePlayTask.mode;
        }
        if ((i & 4) != 0) {
            voicePlayType = voicePlayTask.playType;
        }
        if ((i & 8) != 0) {
            function3 = voicePlayTask.playListener;
        }
        return voicePlayTask.copy(arrayList, voicePlayMode, voicePlayType, function3);
    }

    public final ArrayList<BaseVoice> component1() {
        return this.voiceList;
    }

    /* renamed from: component2, reason: from getter */
    public final VoicePlayMode getMode() {
        return this.mode;
    }

    /* renamed from: component3, reason: from getter */
    public final VoicePlayType getPlayType() {
        return this.playType;
    }

    public final Function3<PlayEvent, String, Boolean, Unit> component4() {
        return this.playListener;
    }

    public final VoicePlayTask copy(ArrayList<BaseVoice> voiceList, VoicePlayMode mode, VoicePlayType playType, Function3<? super PlayEvent, ? super String, ? super Boolean, Unit> playListener) {
        Intrinsics.checkParameterIsNotNull(voiceList, "voiceList");
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        Intrinsics.checkParameterIsNotNull(playType, "playType");
        return new VoicePlayTask(voiceList, mode, playType, playListener);
    }

    public int hashCode() {
        ArrayList<BaseVoice> arrayList = this.voiceList;
        int hashCode = (arrayList != null ? arrayList.hashCode() : 0) * 31;
        VoicePlayMode voicePlayMode = this.mode;
        int hashCode2 = (hashCode + (voicePlayMode != null ? voicePlayMode.hashCode() : 0)) * 31;
        VoicePlayType voicePlayType = this.playType;
        int hashCode3 = (hashCode2 + (voicePlayType != null ? voicePlayType.hashCode() : 0)) * 31;
        Function3<PlayEvent, String, Boolean, Unit> function3 = this.playListener;
        return hashCode3 + (function3 != null ? function3.hashCode() : 0);
    }

    public String toString() {
        return "VoicePlayTask(voiceList=" + this.voiceList + ", mode=" + this.mode + ", playType=" + this.playType + ", playListener=" + this.playListener + ")";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public VoicePlayTask(ArrayList<BaseVoice> voiceList, VoicePlayMode mode, VoicePlayType playType, Function3<? super PlayEvent, ? super String, ? super Boolean, Unit> function3) {
        Intrinsics.checkParameterIsNotNull(voiceList, "voiceList");
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        Intrinsics.checkParameterIsNotNull(playType, "playType");
        this.voiceList = voiceList;
        this.mode = mode;
        this.playType = playType;
        this.playListener = function3;
        this.index = -1;
    }

    public final ArrayList<BaseVoice> getVoiceList() {
        return this.voiceList;
    }

    public /* synthetic */ VoicePlayTask(ArrayList arrayList, VoicePlayMode voicePlayMode, VoicePlayType voicePlayType, Function3 function3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(arrayList, (i & 2) != 0 ? VoicePlayMode.Mode : voicePlayMode, (i & 4) != 0 ? VoicePlayType.Order : voicePlayType, (i & 8) != 0 ? (Function3) null : function3);
    }

    public final VoicePlayMode getMode() {
        return this.mode;
    }

    public final VoicePlayType getPlayType() {
        return this.playType;
    }

    public final Function3<PlayEvent, String, Boolean, Unit> getPlayListener() {
        return this.playListener;
    }

    public final BaseVoice getNextPlayVoice() {
        if (this.voiceList.isEmpty()) {
            return null;
        }
        this.index++;
        if (this.index >= this.voiceList.size()) {
            return null;
        }
        return getCurVoice();
    }

    public final BaseVoice getCurVoice() {
        if (this.playType == VoicePlayType.Order) {
            if (this.index < 0 || this.index >= this.voiceList.size()) {
                return null;
            }
            return this.voiceList.get(this.index);
        }
        ArrayList<BaseVoice> arrayList = this.voiceList;
        if (arrayList == null || arrayList.size() == 0) {
            return null;
        }
        return this.voiceList.get(Random.INSTANCE.nextInt(this.voiceList.size()));
    }

    public boolean equals(Object other) {
        if ((other != null ? other.hashCode() : 0) == hashCode()) {
            return true;
        }
        if (!(other instanceof VoicePlayTask)) {
            return false;
        }
        VoicePlayTask voicePlayTask = (VoicePlayTask) other;
        if (voicePlayTask.mode != this.mode || voicePlayTask.voiceList.size() != this.voiceList.size()) {
            return false;
        }
        int i = 0;
        for (Object obj : this.voiceList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            if (!Intrinsics.areEqual((BaseVoice) obj, voicePlayTask.voiceList.get(i))) {
                return false;
            }
            i = i2;
        }
        return true;
    }
}
