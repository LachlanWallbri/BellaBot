package com.pudutech.peanut.robot_ui.ttsvoices;

import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.p063ui.helper.TtsVoiceHelper;
import com.pudutech.peanut.robot_ui.ttsvoices.TtsCustomPkManager;
import com.pudutech.peanut.robot_ui.ttsvoices.bean.TtsDownInfo;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TtsCustomPkManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001aB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\f\u001a\u00020\rJ\u0011\u0010\u000e\u001a\u00020\u0006H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000fJ)\u0010\u0010\u001a\u00020\r2!\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\r0\u0012J/\u0010\u0016\u001a\u00020\r2%\b\u0002\u0010\u0011\u001a\u001f\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\r\u0018\u00010\u0012H\u0002J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002J\u0006\u0010\u0018\u001a\u00020\u0019R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ttsvoices/TtsCustomPkManager;", "", "()V", "TAG", "", "cancelFlag", "", "needDownTts", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lcom/pudutech/peanut/robot_ui/ttsvoices/TtsCustomPkManager$TtsVoiceDate;", "ttsDownInfo", "Lcom/pudutech/peanut/robot_ui/ttsvoices/bean/TtsDownInfo;", "cancel", "", "checkVoice", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "downTtsVoice", "cb", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "info", "genVoiceIfNeed", "getDataList", "getNeedDownSize", "", "TtsVoiceDate", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class TtsCustomPkManager {
    private static boolean cancelFlag;
    private static TtsDownInfo ttsDownInfo;
    public static final TtsCustomPkManager INSTANCE = new TtsCustomPkManager();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final CopyOnWriteArrayList<TtsVoiceDate> needDownTts = new CopyOnWriteArrayList<>();

    private TtsCustomPkManager() {
    }

    public final int getNeedDownSize() {
        return needDownTts.size();
    }

    public final Object checkVoice(Continuation<? super Boolean> continuation) {
        needDownTts.clear();
        for (TtsVoiceDate ttsVoiceDate : getDataList()) {
            ttsVoiceDate.getTtsConfigData().setPath(TtsVoiceHelper.INSTANCE.getFilePath(RobotContext.INSTANCE.getContext(), ttsVoiceDate.getTtsConfigData().getMd5(), ttsVoiceDate.getTtsVoiceType()));
            if (!new File(ttsVoiceDate.getTtsConfigData().getPath()).exists()) {
                needDownTts.add(ttsVoiceDate);
            }
        }
        Pdlog.m3273d(TAG, "custom needDownTts " + needDownTts.isEmpty());
        if (!needDownTts.isEmpty()) {
            TtsVoiceHelper.INSTANCE.saveConfig(TtsVoiceHelper.TtsVoiceType.CRUISE_TYPE);
            TtsVoiceHelper.INSTANCE.saveConfig(TtsVoiceHelper.TtsVoiceType.DELIVER_TYPE);
            TtsVoiceHelper.INSTANCE.saveConfig(TtsVoiceHelper.TtsVoiceType.USHER_TYPE);
        }
        return Boxing.boxBoolean(needDownTts.isEmpty());
    }

    public final void downTtsVoice(Function1<? super TtsDownInfo, Unit> cb) {
        Intrinsics.checkParameterIsNotNull(cb, "cb");
        if (needDownTts.isEmpty()) {
            cb.invoke(new TtsDownInfo(0, 0, 0, 4, null));
        } else {
            cancelFlag = false;
            genVoiceIfNeed(cb);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void genVoiceIfNeed$default(TtsCustomPkManager ttsCustomPkManager, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = (Function1) null;
        }
        ttsCustomPkManager.genVoiceIfNeed(function1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void genVoiceIfNeed(final Function1<? super TtsDownInfo, Unit> cb) {
        Pdlog.m3273d(TAG, "genCustomVoiceIfNeed " + needDownTts.isEmpty());
        Pdlog.m3273d(TAG, "genCustomVoiceIfNeed cancal = " + cancelFlag);
        if (cancelFlag) {
            ttsDownInfo = (TtsDownInfo) null;
            return;
        }
        if (!needDownTts.isEmpty()) {
            if (ttsDownInfo == null) {
                ttsDownInfo = new TtsDownInfo(needDownTts.size(), needDownTts.size(), 0, 4, null);
            }
            Pdlog.m3273d(TAG, "custom checkAndDownload " + needDownTts);
            final TtsVoiceDate ttsVoiceDate = (TtsVoiceDate) CollectionsKt.firstOrNull((List) needDownTts);
            if (ttsVoiceDate != null) {
                TtsVoiceHelper.TtsConfigData ttsConfigData = ttsVoiceDate.getTtsConfigData();
                TtsVoiceHelper.INSTANCE.genTts(ttsConfigData.getName(), ttsConfigData.getPath(), new Function1<Throwable, Unit>() { // from class: com.pudutech.peanut.robot_ui.ttsvoices.TtsCustomPkManager$genVoiceIfNeed$$inlined$let$lambda$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                        invoke2(th);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Throwable th) {
                        TtsDownInfo ttsDownInfo2;
                        TtsDownInfo ttsDownInfo3;
                        CopyOnWriteArrayList copyOnWriteArrayList;
                        TtsDownInfo ttsDownInfo4;
                        String str;
                        TtsDownInfo ttsDownInfo5;
                        CopyOnWriteArrayList copyOnWriteArrayList2;
                        TtsDownInfo ttsDownInfo6;
                        TtsDownInfo ttsDownInfo7;
                        CopyOnWriteArrayList copyOnWriteArrayList3;
                        TtsDownInfo ttsDownInfo8;
                        if (th == null) {
                            TtsCustomPkManager ttsCustomPkManager = TtsCustomPkManager.INSTANCE;
                            copyOnWriteArrayList2 = TtsCustomPkManager.needDownTts;
                            copyOnWriteArrayList2.remove(TtsCustomPkManager.TtsVoiceDate.this);
                            TtsCustomPkManager ttsCustomPkManager2 = TtsCustomPkManager.INSTANCE;
                            ttsDownInfo6 = TtsCustomPkManager.ttsDownInfo;
                            if (ttsDownInfo6 == null) {
                                Intrinsics.throwNpe();
                            }
                            TtsCustomPkManager ttsCustomPkManager3 = TtsCustomPkManager.INSTANCE;
                            ttsDownInfo7 = TtsCustomPkManager.ttsDownInfo;
                            if (ttsDownInfo7 == null) {
                                Intrinsics.throwNpe();
                            }
                            int all = ttsDownInfo7.getAll();
                            TtsCustomPkManager ttsCustomPkManager4 = TtsCustomPkManager.INSTANCE;
                            copyOnWriteArrayList3 = TtsCustomPkManager.needDownTts;
                            ttsDownInfo6.setLeft(all - copyOnWriteArrayList3.size());
                            Function1 function1 = cb;
                            if (function1 != null) {
                                TtsCustomPkManager ttsCustomPkManager5 = TtsCustomPkManager.INSTANCE;
                                ttsDownInfo8 = TtsCustomPkManager.ttsDownInfo;
                                if (ttsDownInfo8 == null) {
                                    Intrinsics.throwNpe();
                                }
                            }
                            TtsCustomPkManager.INSTANCE.genVoiceIfNeed(cb);
                            return;
                        }
                        TtsCustomPkManager ttsCustomPkManager6 = TtsCustomPkManager.INSTANCE;
                        ttsDownInfo2 = TtsCustomPkManager.ttsDownInfo;
                        if (ttsDownInfo2 == null) {
                            Intrinsics.throwNpe();
                        }
                        TtsCustomPkManager ttsCustomPkManager7 = TtsCustomPkManager.INSTANCE;
                        ttsDownInfo3 = TtsCustomPkManager.ttsDownInfo;
                        if (ttsDownInfo3 == null) {
                            Intrinsics.throwNpe();
                        }
                        int all2 = ttsDownInfo3.getAll();
                        TtsCustomPkManager ttsCustomPkManager8 = TtsCustomPkManager.INSTANCE;
                        copyOnWriteArrayList = TtsCustomPkManager.needDownTts;
                        ttsDownInfo2.setLeft(all2 - copyOnWriteArrayList.size());
                        TtsCustomPkManager ttsCustomPkManager9 = TtsCustomPkManager.INSTANCE;
                        ttsDownInfo4 = TtsCustomPkManager.ttsDownInfo;
                        if (ttsDownInfo4 == null) {
                            Intrinsics.throwNpe();
                        }
                        ttsDownInfo4.setCode(1);
                        Function1 function12 = cb;
                        if (function12 != null) {
                            TtsCustomPkManager ttsCustomPkManager10 = TtsCustomPkManager.INSTANCE;
                            ttsDownInfo5 = TtsCustomPkManager.ttsDownInfo;
                            if (ttsDownInfo5 == null) {
                                Intrinsics.throwNpe();
                            }
                        }
                        TtsCustomPkManager ttsCustomPkManager11 = TtsCustomPkManager.INSTANCE;
                        TtsCustomPkManager.ttsDownInfo = (TtsDownInfo) null;
                        TtsCustomPkManager ttsCustomPkManager12 = TtsCustomPkManager.INSTANCE;
                        str = TtsCustomPkManager.TAG;
                        Pdlog.m3274e(str, "custom checkAndDownload : " + Log.getStackTraceString(th));
                    }
                });
                return;
            }
            return;
        }
        ttsDownInfo = (TtsDownInfo) null;
    }

    private final CopyOnWriteArrayList<TtsVoiceDate> getDataList() {
        CopyOnWriteArrayList<TtsVoiceDate> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        Iterator<T> it = TtsVoiceHelper.INSTANCE.getTtsConfigList(TtsVoiceHelper.TtsVoiceType.CRUISE_TYPE, false).iterator();
        while (it.hasNext()) {
            copyOnWriteArrayList.add(new TtsVoiceDate((TtsVoiceHelper.TtsConfigData) it.next(), TtsVoiceHelper.TtsVoiceType.CRUISE_TYPE));
        }
        Iterator<T> it2 = TtsVoiceHelper.INSTANCE.getTtsConfigList(TtsVoiceHelper.TtsVoiceType.DELIVER_TYPE, false).iterator();
        while (it2.hasNext()) {
            copyOnWriteArrayList.add(new TtsVoiceDate((TtsVoiceHelper.TtsConfigData) it2.next(), TtsVoiceHelper.TtsVoiceType.DELIVER_TYPE));
        }
        Iterator<T> it3 = TtsVoiceHelper.INSTANCE.getTtsConfigList(TtsVoiceHelper.TtsVoiceType.USHER_TYPE, false).iterator();
        while (it3.hasNext()) {
            copyOnWriteArrayList.add(new TtsVoiceDate((TtsVoiceHelper.TtsConfigData) it3.next(), TtsVoiceHelper.TtsVoiceType.USHER_TYPE));
        }
        return copyOnWriteArrayList;
    }

    public final void cancel() {
        cancelFlag = true;
        Pdlog.m3273d(TAG, "genCustomVoiceIfNeed setCancal = " + cancelFlag);
    }

    /* compiled from: TtsCustomPkManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ttsvoices/TtsCustomPkManager$TtsVoiceDate;", "", "ttsConfigData", "Lcom/pudutech/peanut/robot_ui/ui/helper/TtsVoiceHelper$TtsConfigData;", "ttsVoiceType", "Lcom/pudutech/peanut/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;", "(Lcom/pudutech/peanut/robot_ui/ui/helper/TtsVoiceHelper$TtsConfigData;Lcom/pudutech/peanut/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;)V", "getTtsConfigData", "()Lcom/pudutech/peanut/robot_ui/ui/helper/TtsVoiceHelper$TtsConfigData;", "getTtsVoiceType", "()Lcom/pudutech/peanut/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final /* data */ class TtsVoiceDate {
        private final TtsVoiceHelper.TtsConfigData ttsConfigData;
        private final TtsVoiceHelper.TtsVoiceType ttsVoiceType;

        public static /* synthetic */ TtsVoiceDate copy$default(TtsVoiceDate ttsVoiceDate, TtsVoiceHelper.TtsConfigData ttsConfigData, TtsVoiceHelper.TtsVoiceType ttsVoiceType, int i, Object obj) {
            if ((i & 1) != 0) {
                ttsConfigData = ttsVoiceDate.ttsConfigData;
            }
            if ((i & 2) != 0) {
                ttsVoiceType = ttsVoiceDate.ttsVoiceType;
            }
            return ttsVoiceDate.copy(ttsConfigData, ttsVoiceType);
        }

        /* renamed from: component1, reason: from getter */
        public final TtsVoiceHelper.TtsConfigData getTtsConfigData() {
            return this.ttsConfigData;
        }

        /* renamed from: component2, reason: from getter */
        public final TtsVoiceHelper.TtsVoiceType getTtsVoiceType() {
            return this.ttsVoiceType;
        }

        public final TtsVoiceDate copy(TtsVoiceHelper.TtsConfigData ttsConfigData, TtsVoiceHelper.TtsVoiceType ttsVoiceType) {
            Intrinsics.checkParameterIsNotNull(ttsConfigData, "ttsConfigData");
            Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
            return new TtsVoiceDate(ttsConfigData, ttsVoiceType);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TtsVoiceDate)) {
                return false;
            }
            TtsVoiceDate ttsVoiceDate = (TtsVoiceDate) other;
            return Intrinsics.areEqual(this.ttsConfigData, ttsVoiceDate.ttsConfigData) && Intrinsics.areEqual(this.ttsVoiceType, ttsVoiceDate.ttsVoiceType);
        }

        public int hashCode() {
            TtsVoiceHelper.TtsConfigData ttsConfigData = this.ttsConfigData;
            int hashCode = (ttsConfigData != null ? ttsConfigData.hashCode() : 0) * 31;
            TtsVoiceHelper.TtsVoiceType ttsVoiceType = this.ttsVoiceType;
            return hashCode + (ttsVoiceType != null ? ttsVoiceType.hashCode() : 0);
        }

        public String toString() {
            return "TtsVoiceDate(ttsConfigData=" + this.ttsConfigData + ", ttsVoiceType=" + this.ttsVoiceType + ")";
        }

        public TtsVoiceDate(TtsVoiceHelper.TtsConfigData ttsConfigData, TtsVoiceHelper.TtsVoiceType ttsVoiceType) {
            Intrinsics.checkParameterIsNotNull(ttsConfigData, "ttsConfigData");
            Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
            this.ttsConfigData = ttsConfigData;
            this.ttsVoiceType = ttsVoiceType;
        }

        public final TtsVoiceHelper.TtsConfigData getTtsConfigData() {
            return this.ttsConfigData;
        }

        public final TtsVoiceHelper.TtsVoiceType getTtsVoiceType() {
            return this.ttsVoiceType;
        }
    }
}
