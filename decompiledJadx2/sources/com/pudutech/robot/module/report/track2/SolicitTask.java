package com.pudutech.robot.module.report.track2;

import com.pudutech.event_tracking.PuduEventTrackingManager;
import com.pudutech.event_tracking.custom.CustomArgs;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.robot.module.report.ExtKt;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* compiled from: robot_solicit.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0013\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004JA\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u00062*\u0010\u0018\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u001a0\u0019\"\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u001aH\u0002¢\u0006\u0002\u0010\u001bJ\u0014\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u001aH\u0002J\u000e\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\nJ\u0010\u0010\u001f\u001a\u00020\u000f2\b\u0010 \u001a\u0004\u0018\u00010\u0006J\u000e\u0010!\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020\u0006J\u000e\u0010#\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020\nJ \u0010%\u001a\u00020\u000f2\b\u0010 \u001a\u0004\u0018\u00010\u00062\u0006\u0010&\u001a\u00020\n2\u0006\u0010'\u001a\u00020\nJ\u000e\u0010(\u001a\u00020\u000f2\u0006\u0010)\u001a\u00020\nJ\u0006\u0010*\u001a\u00020\u000fJ\u0006\u0010+\u001a\u00020\u000fJ\u0006\u0010,\u001a\u00020\u000fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006-"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/SolicitTask;", "", "config", "Lcom/pudutech/robot/module/report/track2/BaseSolicitConfig;", "(Lcom/pudutech/robot/module/report/track2/BaseSolicitConfig;)V", "TAG", "", "autoReleaseTaskIdJob", "Lkotlinx/coroutines/Job;", "crtPeople", "", "crtTaskID", "onPeopleMarkerRelease", "Lkotlin/Function2;", "", "", "peopleMarkerMap", "Ljava/util/concurrent/ConcurrentHashMap;", "Lcom/pudutech/robot/module/report/track2/PeopleMarker;", "preWakeupTime", TrackKey.SCENE_ID, "sleepTime", "customEvent", "status", "pairs", "", "Lkotlin/Pair;", "(Ljava/lang/String;[Lkotlin/Pair;)V", "fetchTaskId", "newPeopleFlow", "pid", "onAnswer", "text", "onCardClick", "cardInfo", "onFace", "fid", "onRepeat", "mInterruptType", "state", "onSolicitVoice", "type", "onWakeUp", "start", "stop", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class SolicitTask {
    private final String TAG;
    private Job autoReleaseTaskIdJob;
    private final BaseSolicitConfig config;
    private int crtPeople;
    private String crtTaskID;
    private final Function2<Integer, Long, Unit> onPeopleMarkerRelease;
    private final ConcurrentHashMap<Integer, PeopleMarker> peopleMarkerMap;
    private long preWakeupTime;
    private final String scene_id;
    private long sleepTime;

    public SolicitTask(BaseSolicitConfig config) {
        Intrinsics.checkParameterIsNotNull(config, "config");
        this.config = config;
        this.TAG = TrackType.SOLICIT.toString();
        this.peopleMarkerMap = new ConcurrentHashMap<>();
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
        this.scene_id = StringsKt.replace$default(uuid, "-", "", false, 4, (Object) null);
        this.onPeopleMarkerRelease = new Function2<Integer, Long, Unit>() { // from class: com.pudutech.robot.module.report.track2.SolicitTask$onPeopleMarkerRelease$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Integer num, Long l) {
                invoke(num.intValue(), l.longValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i, long j) {
                ConcurrentHashMap concurrentHashMap;
                String str;
                long j2;
                long j3;
                concurrentHashMap = SolicitTask.this.peopleMarkerMap;
                concurrentHashMap.remove(Integer.valueOf(i));
                NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
                str = SolicitTask.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onPeopleMarkerRelease ");
                j2 = Robot_solicitKt.MIN_DURATION;
                sb.append(j > j2);
                sb.append(" : ");
                sb.append(i);
                sb.append(' ');
                sb.append(j);
                netWorkLog.mo3280i(str, sb.toString());
                j3 = Robot_solicitKt.MIN_DURATION;
                if (j > j3) {
                    SolicitTask.this.customEvent("ON_FACE", TuplesKt.m3968to("face_stay_time", Long.valueOf(j)));
                }
            }
        };
        this.crtTaskID = "";
    }

    public final void start() {
        Pair<String, Object>[] pairArray = ExtKt.toPairArray(this.config);
        customEvent("START", (Pair[]) Arrays.copyOf(pairArray, pairArray.length));
    }

    public final void stop() {
        Set<Map.Entry<Integer, PeopleMarker>> entrySet = this.peopleMarkerMap.entrySet();
        Intrinsics.checkExpressionValueIsNotNull(entrySet, "peopleMarkerMap.entries");
        Iterator<T> it = entrySet.iterator();
        while (it.hasNext()) {
            ((PeopleMarker) ((Map.Entry) it.next()).getValue()).calc();
        }
        customEvent("STOP", new Pair[0]);
    }

    public final void onSolicitVoice(int type) {
        customEvent("ON_VOICE", TuplesKt.m3968to("voice_type", Integer.valueOf(type)));
    }

    public final void onFace(int fid) {
        NetWorkLog.INSTANCE.mo3280i(this.TAG, "onFace : " + fid);
        PeopleMarker peopleMarker = this.peopleMarkerMap.get(Integer.valueOf(fid));
        if (peopleMarker == null) {
            peopleMarker = new PeopleMarker(fid, this.onPeopleMarkerRelease);
            this.peopleMarkerMap.put(Integer.valueOf(fid), peopleMarker);
        }
        peopleMarker.refresh();
        if (this.autoReleaseTaskIdJob != null) {
            fetchTaskId();
        }
    }

    public final void newPeopleFlow(int pid) {
        this.crtPeople++;
    }

    public final void onWakeUp() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.preWakeupTime > 10000) {
            customEvent("ON_WAKE_UP", new Pair[0]);
            this.preWakeupTime = currentTimeMillis;
        }
    }

    public final void onCardClick(String cardInfo) {
        Intrinsics.checkParameterIsNotNull(cardInfo, "cardInfo");
        customEvent("ON_CARD_CLICK", TuplesKt.m3968to("card_info", cardInfo), fetchTaskId());
    }

    public final void onAnswer(String text) {
        Pair<String, ? extends Object>[] pairArr = new Pair[2];
        if (text == null) {
            text = "";
        }
        pairArr[0] = TuplesKt.m3968to("text", text);
        pairArr[1] = fetchTaskId();
        customEvent("ON_ANSWER", pairArr);
    }

    public final void onRepeat(String text, int mInterruptType, int state) {
        Pair<String, ? extends Object>[] pairArr = new Pair[4];
        if (text == null) {
            text = "";
        }
        pairArr[0] = TuplesKt.m3968to("text", text);
        pairArr[1] = TuplesKt.m3968to("interrupt_type", Integer.valueOf(mInterruptType));
        pairArr[2] = TuplesKt.m3968to("state", Integer.valueOf(state));
        pairArr[3] = fetchTaskId();
        customEvent("ON_REPEAT", pairArr);
    }

    private final synchronized Pair<String, String> fetchTaskId() {
        CoroutineScope coroutineScope;
        Job launch$default;
        this.sleepTime = System.currentTimeMillis() + 101000;
        if (this.autoReleaseTaskIdJob == null) {
            coroutineScope = Robot_solicitKt.scope;
            launch$default = BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new SolicitTask$fetchTaskId$1(this, null), 3, null);
            this.autoReleaseTaskIdJob = launch$default;
            String uuid = UUID.randomUUID().toString();
            Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
            this.crtTaskID = StringsKt.replace$default(uuid, "-", "", false, 4, (Object) null);
        }
        return TuplesKt.m3968to("task_id", this.crtTaskID);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void customEvent(String status, Pair<String, ? extends Object>... pairs) {
        PuduEventTrackingManager puduEventTrackingManager = PuduEventTrackingManager.INSTANCE;
        String str = this.TAG;
        SpreadBuilder spreadBuilder = new SpreadBuilder(4);
        spreadBuilder.addSpread(pairs);
        spreadBuilder.add(TuplesKt.m3968to(TrackKey.BUSINESS_STATUS, status));
        spreadBuilder.add(TuplesKt.m3968to("people_sum", Integer.valueOf(this.crtPeople)));
        spreadBuilder.add(TuplesKt.m3968to(TrackKey.SCENE_ID, this.scene_id));
        puduEventTrackingManager.customEvent(new CustomArgs(str, MapsKt.mapOf((Pair[]) spreadBuilder.toArray(new Pair[spreadBuilder.size()])), 0));
    }
}
