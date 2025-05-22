package com.pudutech.voiceinteraction.component.cmd;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pudutech.voiceinteraction.component.VoiceInteractionKit;
import com.pudutech.voiceinteraction.component.log.LogProxy;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: IflyCmdAdatper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u0012\u0013B\u0005¢\u0006\u0002\u0010\u0003J*\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\rH\u0002J*\u0010\u000e\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\rH\u0002J\u0014\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0002X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/cmd/IflyCmdAdatper;", "Lcom/pudutech/voiceinteraction/component/cmd/ICmdAdatper;", "", "()V", "TAG", "gson", "Lcom/google/gson/Gson;", "handlerGuide", "", "slots", "Ljava/util/ArrayList;", "Lcom/pudutech/voiceinteraction/component/cmd/IflyCmdAdatper$SlotBean;", "v", "Ljava/util/HashMap;", "handlerTaskInput", "transformationCmd", "Lcom/pudutech/voiceinteraction/component/cmd/CmdBean;", "data", "SlotBean", "VoiceTriggerBean", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class IflyCmdAdatper implements ICmdAdatper<String> {
    private final String TAG = "IflyCmdAdatper";
    private final Gson gson = new Gson();

    @Override // com.pudutech.voiceinteraction.component.cmd.ICmdAdatper
    public CmdBean transformationCmd(String data) {
        ArrayList arrayList;
        TriggerIntent triggerIntent;
        try {
            arrayList = (ArrayList) this.gson.fromJson(data, new TypeToken<ArrayList<VoiceTriggerBean>>() { // from class: com.pudutech.voiceinteraction.component.cmd.IflyCmdAdatper$transformationCmd$triggerList$1
            }.getType());
        } catch (Exception e) {
            LogProxy.INSTANCE.m3306e(this.TAG, "handlerTriggerMsg : " + Log.getStackTraceString(e));
        }
        if (arrayList.isEmpty()) {
            LogProxy.INSTANCE.m3305d(this.TAG, "handlerTriggerMsg : triggerList is empty ");
            return null;
        }
        Object obj = arrayList.get(0);
        Intrinsics.checkExpressionValueIsNotNull(obj, "triggerList[0]");
        VoiceTriggerBean voiceTriggerBean = (VoiceTriggerBean) obj;
        HashMap<String, String> hashMap = new HashMap<>();
        String intent = voiceTriggerBean.getIntent();
        if (Intrinsics.areEqual(intent, TriggerIntent.TaskInput.name())) {
            handlerTaskInput(voiceTriggerBean.getSlots(), hashMap);
            LogProxy.INSTANCE.m3305d(this.TAG, String.valueOf(voiceTriggerBean.getSlots()));
            triggerIntent = TriggerIntent.TaskInput;
        } else if (Intrinsics.areEqual(intent, TriggerIntent.MissionStart.name())) {
            triggerIntent = TriggerIntent.MissionStart;
        } else if (Intrinsics.areEqual(intent, TriggerIntent.LookAtMe.name())) {
            triggerIntent = TriggerIntent.LookAtMe;
        } else if (Intrinsics.areEqual(intent, TriggerIntent.MissionAccomplished.name())) {
            triggerIntent = TriggerIntent.MissionAccomplished;
        } else if (Intrinsics.areEqual(intent, TriggerIntent.guide.name())) {
            LogProxy.INSTANCE.m3305d(this.TAG, String.valueOf(voiceTriggerBean.getSlots()));
            if (Intrinsics.areEqual(VoiceInteractionKit.INSTANCE.getProduct_type(), "BellaBot")) {
                handlerGuide(voiceTriggerBean.getSlots(), hashMap);
            } else {
                handlerTaskInput(voiceTriggerBean.getSlots(), hashMap);
            }
            triggerIntent = TriggerIntent.guide;
        } else {
            LogProxy.INSTANCE.m3306e(this.TAG, voiceTriggerBean.getIntent() + " not has");
            triggerIntent = null;
        }
        if (triggerIntent != null) {
            return new CmdBean(triggerIntent, hashMap);
        }
        return null;
    }

    private final void handlerTaskInput(ArrayList<SlotBean> slots, HashMap<String, String> v) {
        for (SlotBean slotBean : slots) {
            if (Intrinsics.areEqual(slotBean.getName(), "tray")) {
                try {
                    v.put(slotBean.getName(), String.valueOf(Integer.parseInt(slotBean.getNormValue())));
                } catch (Exception e) {
                    LogProxy.INSTANCE.m3306e(this.TAG, "handlerTaskInput : " + Log.getStackTraceString(e));
                }
            } else if (Intrinsics.areEqual(slotBean.getName(), "destination")) {
                v.put(slotBean.getName(), slotBean.getNormValue());
            }
        }
    }

    private final void handlerGuide(ArrayList<SlotBean> slots, HashMap<String, String> v) {
        for (SlotBean slotBean : slots) {
            if (Intrinsics.areEqual(slotBean.getName(), "destination")) {
                v.put(slotBean.getName(), slotBean.getNormValue());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: IflyCmdAdatper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0016\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\r¢\u0006\u0002\u0010\u000eJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\bHÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\u0019\u0010\u001e\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\rHÆ\u0003JU\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\u0018\b\u0002\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\rHÆ\u0001J\u0013\u0010 \u001a\u00020\u00052\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020#HÖ\u0001J\t\u0010$\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R!\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\r¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010¨\u0006%"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/cmd/IflyCmdAdatper$VoiceTriggerBean;", "", "entrypoint", "", "hazard", "", "intent", "score", "", "template", "slots", "Ljava/util/ArrayList;", "Lcom/pudutech/voiceinteraction/component/cmd/IflyCmdAdatper$SlotBean;", "Lkotlin/collections/ArrayList;", "(Ljava/lang/String;ZLjava/lang/String;DLjava/lang/String;Ljava/util/ArrayList;)V", "getEntrypoint", "()Ljava/lang/String;", "getHazard", "()Z", "getIntent", "getScore", "()D", "getSlots", "()Ljava/util/ArrayList;", "getTemplate", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final /* data */ class VoiceTriggerBean {
        private final String entrypoint;
        private final boolean hazard;
        private final String intent;
        private final double score;
        private final ArrayList<SlotBean> slots;
        private final String template;

        public static /* synthetic */ VoiceTriggerBean copy$default(VoiceTriggerBean voiceTriggerBean, String str, boolean z, String str2, double d, String str3, ArrayList arrayList, int i, Object obj) {
            if ((i & 1) != 0) {
                str = voiceTriggerBean.entrypoint;
            }
            if ((i & 2) != 0) {
                z = voiceTriggerBean.hazard;
            }
            boolean z2 = z;
            if ((i & 4) != 0) {
                str2 = voiceTriggerBean.intent;
            }
            String str4 = str2;
            if ((i & 8) != 0) {
                d = voiceTriggerBean.score;
            }
            double d2 = d;
            if ((i & 16) != 0) {
                str3 = voiceTriggerBean.template;
            }
            String str5 = str3;
            if ((i & 32) != 0) {
                arrayList = voiceTriggerBean.slots;
            }
            return voiceTriggerBean.copy(str, z2, str4, d2, str5, arrayList);
        }

        /* renamed from: component1, reason: from getter */
        public final String getEntrypoint() {
            return this.entrypoint;
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getHazard() {
            return this.hazard;
        }

        /* renamed from: component3, reason: from getter */
        public final String getIntent() {
            return this.intent;
        }

        /* renamed from: component4, reason: from getter */
        public final double getScore() {
            return this.score;
        }

        /* renamed from: component5, reason: from getter */
        public final String getTemplate() {
            return this.template;
        }

        public final ArrayList<SlotBean> component6() {
            return this.slots;
        }

        public final VoiceTriggerBean copy(String entrypoint, boolean hazard, String intent, double score, String template, ArrayList<SlotBean> slots) {
            Intrinsics.checkParameterIsNotNull(entrypoint, "entrypoint");
            Intrinsics.checkParameterIsNotNull(intent, "intent");
            Intrinsics.checkParameterIsNotNull(template, "template");
            Intrinsics.checkParameterIsNotNull(slots, "slots");
            return new VoiceTriggerBean(entrypoint, hazard, intent, score, template, slots);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VoiceTriggerBean)) {
                return false;
            }
            VoiceTriggerBean voiceTriggerBean = (VoiceTriggerBean) other;
            return Intrinsics.areEqual(this.entrypoint, voiceTriggerBean.entrypoint) && this.hazard == voiceTriggerBean.hazard && Intrinsics.areEqual(this.intent, voiceTriggerBean.intent) && Double.compare(this.score, voiceTriggerBean.score) == 0 && Intrinsics.areEqual(this.template, voiceTriggerBean.template) && Intrinsics.areEqual(this.slots, voiceTriggerBean.slots);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            String str = this.entrypoint;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            boolean z = this.hazard;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            int i2 = (hashCode + i) * 31;
            String str2 = this.intent;
            int hashCode2 = (i2 + (str2 != null ? str2.hashCode() : 0)) * 31;
            long doubleToLongBits = Double.doubleToLongBits(this.score);
            int i3 = (hashCode2 + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31;
            String str3 = this.template;
            int hashCode3 = (i3 + (str3 != null ? str3.hashCode() : 0)) * 31;
            ArrayList<SlotBean> arrayList = this.slots;
            return hashCode3 + (arrayList != null ? arrayList.hashCode() : 0);
        }

        public String toString() {
            return "VoiceTriggerBean(entrypoint=" + this.entrypoint + ", hazard=" + this.hazard + ", intent=" + this.intent + ", score=" + this.score + ", template=" + this.template + ", slots=" + this.slots + ")";
        }

        public VoiceTriggerBean(String entrypoint, boolean z, String intent, double d, String template, ArrayList<SlotBean> slots) {
            Intrinsics.checkParameterIsNotNull(entrypoint, "entrypoint");
            Intrinsics.checkParameterIsNotNull(intent, "intent");
            Intrinsics.checkParameterIsNotNull(template, "template");
            Intrinsics.checkParameterIsNotNull(slots, "slots");
            this.entrypoint = entrypoint;
            this.hazard = z;
            this.intent = intent;
            this.score = d;
            this.template = template;
            this.slots = slots;
        }

        public final String getEntrypoint() {
            return this.entrypoint;
        }

        public final boolean getHazard() {
            return this.hazard;
        }

        public final String getIntent() {
            return this.intent;
        }

        public final double getScore() {
            return this.score;
        }

        public final String getTemplate() {
            return this.template;
        }

        public final ArrayList<SlotBean> getSlots() {
            return this.slots;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: IflyCmdAdatper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0082\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÆ\u0003J;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001b\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000e¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/cmd/IflyCmdAdatper$SlotBean;", "", "begin", "", "end", "name", "", "normValue", ES6Iterator.VALUE_PROPERTY, "(IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBegin", "()I", "getEnd", "getName", "()Ljava/lang/String;", "getNormValue", "getValue", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final /* data */ class SlotBean {
        private final int begin;
        private final int end;
        private final String name;
        private final String normValue;
        private final String value;

        public static /* synthetic */ SlotBean copy$default(SlotBean slotBean, int i, int i2, String str, String str2, String str3, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = slotBean.begin;
            }
            if ((i3 & 2) != 0) {
                i2 = slotBean.end;
            }
            int i4 = i2;
            if ((i3 & 4) != 0) {
                str = slotBean.name;
            }
            String str4 = str;
            if ((i3 & 8) != 0) {
                str2 = slotBean.normValue;
            }
            String str5 = str2;
            if ((i3 & 16) != 0) {
                str3 = slotBean.value;
            }
            return slotBean.copy(i, i4, str4, str5, str3);
        }

        /* renamed from: component1, reason: from getter */
        public final int getBegin() {
            return this.begin;
        }

        /* renamed from: component2, reason: from getter */
        public final int getEnd() {
            return this.end;
        }

        /* renamed from: component3, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* renamed from: component4, reason: from getter */
        public final String getNormValue() {
            return this.normValue;
        }

        /* renamed from: component5, reason: from getter */
        public final String getValue() {
            return this.value;
        }

        public final SlotBean copy(int begin, int end, String name, String normValue, String value) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(normValue, "normValue");
            Intrinsics.checkParameterIsNotNull(value, "value");
            return new SlotBean(begin, end, name, normValue, value);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SlotBean)) {
                return false;
            }
            SlotBean slotBean = (SlotBean) other;
            return this.begin == slotBean.begin && this.end == slotBean.end && Intrinsics.areEqual(this.name, slotBean.name) && Intrinsics.areEqual(this.normValue, slotBean.normValue) && Intrinsics.areEqual(this.value, slotBean.value);
        }

        public int hashCode() {
            int i = ((this.begin * 31) + this.end) * 31;
            String str = this.name;
            int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
            String str2 = this.normValue;
            int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.value;
            return hashCode2 + (str3 != null ? str3.hashCode() : 0);
        }

        public String toString() {
            return "SlotBean(begin=" + this.begin + ", end=" + this.end + ", name=" + this.name + ", normValue=" + this.normValue + ", value=" + this.value + ")";
        }

        public SlotBean(int i, int i2, String name, String normValue, String value) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(normValue, "normValue");
            Intrinsics.checkParameterIsNotNull(value, "value");
            this.begin = i;
            this.end = i2;
            this.name = name;
            this.normValue = normValue;
            this.value = value;
        }

        public final int getBegin() {
            return this.begin;
        }

        public final int getEnd() {
            return this.end;
        }

        public final String getName() {
            return this.name;
        }

        public final String getNormValue() {
            return this.normValue;
        }

        public final String getValue() {
            return this.value;
        }
    }
}
