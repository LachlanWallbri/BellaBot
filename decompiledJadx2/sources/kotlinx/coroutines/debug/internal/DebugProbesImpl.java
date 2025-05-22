package kotlinx.coroutines.debug.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.mirsdk.compat.topo.MapElement;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.KotlinVersion;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.ranges.RangesKt;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobSupport;
import kotlinx.coroutines.debug.AgentPremain;
import kotlinx.coroutines.debug.internal.DebugProbesImpl;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: DebugProbesImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000º\u0001\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0003\n\u0002\b\n\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u0000\bÀ\u0002\u0018\u00002\u00020|:\u0001zB\t\b\u0002¢\u0006\u0004\b\u0001\u0010\u0002J3\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\u0004\b\u0000\u0010\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0004\b\b\u0010\tJ\u0015\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ\u0013\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0013\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\u0013\u0010\u000eJ\u0013\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u000f¢\u0006\u0004\b\u0015\u0010\u0012J+\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00170\u000f2\u0006\u0010\u0016\u001a\u00020\u00102\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u000fH\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ?\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020 0\u001f2\u0006\u0010\u001c\u001a\u00020\u001b2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00170\u001d2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u000fH\u0002¢\u0006\u0004\b!\u0010\"J3\u0010$\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020\u001b2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00170\u001d2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u000fH\u0002¢\u0006\u0004\b$\u0010%J\u001d\u0010'\u001a\u0010\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\f\u0018\u00010&H\u0002¢\u0006\u0004\b'\u0010(J\u0015\u0010,\u001a\u00020+2\u0006\u0010*\u001a\u00020)¢\u0006\u0004\b,\u0010-J\r\u0010.\u001a\u00020\f¢\u0006\u0004\b.\u0010\u0002J%\u00100\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\n2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00170\u000fH\u0002¢\u0006\u0004\b0\u00101J\u001b\u00104\u001a\u00020\f2\n\u00103\u001a\u0006\u0012\u0002\b\u000302H\u0002¢\u0006\u0004\b4\u00105J)\u00108\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\u0004\b\u0000\u0010\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004H\u0000¢\u0006\u0004\b6\u00107J\u001b\u0010;\u001a\u00020\f2\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0000¢\u0006\u0004\b9\u0010:J\u001b\u0010=\u001a\u00020\f2\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0000¢\u0006\u0004\b<\u0010:J'\u0010@\u001a\b\u0012\u0004\u0012\u00020\u00170\u000f\"\b\b\u0000\u0010\u0003*\u00020>2\u0006\u0010?\u001a\u00028\u0000H\u0002¢\u0006\u0004\b@\u0010AJ\r\u0010B\u001a\u00020\f¢\u0006\u0004\bB\u0010\u0002J\u001f\u0010D\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010C\u001a\u00020+H\u0002¢\u0006\u0004\bD\u0010EJ#\u0010F\u001a\u00020\f2\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u00042\u0006\u0010C\u001a\u00020+H\u0002¢\u0006\u0004\bF\u0010GJ/\u0010F\u001a\u00020\f2\n\u00103\u001a\u0006\u0012\u0002\b\u0003022\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u00042\u0006\u0010C\u001a\u00020+H\u0002¢\u0006\u0004\bF\u0010HJ;\u0010O\u001a\u00020\f*\u00020)2\u0012\u0010J\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u00100I2\n\u0010M\u001a\u00060Kj\u0002`L2\u0006\u0010N\u001a\u00020+H\u0002¢\u0006\u0004\bO\u0010PJ\u001d\u00103\u001a\b\u0012\u0002\b\u0003\u0018\u000102*\u0006\u0012\u0002\b\u00030\u0004H\u0002¢\u0006\u0004\b3\u0010QJ\u001a\u00103\u001a\b\u0012\u0002\b\u0003\u0018\u000102*\u00020\u0006H\u0082\u0010¢\u0006\u0004\b3\u0010RJ\u0016\u0010S\u001a\u0004\u0018\u00010\u0006*\u00020\u0006H\u0082\u0010¢\u0006\u0004\bS\u0010TR\u0016\u0010U\u001a\u00020+8\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\bU\u0010VR\"\u0010X\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00100W8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bX\u0010YRJ\u0010]\u001a6\u0012\u0014\u0012\u0012\u0012\u0002\b\u0003 [*\b\u0012\u0002\b\u0003\u0018\u00010202 [*\u001a\u0012\u0014\u0012\u0012\u0012\u0002\b\u0003 [*\b\u0012\u0002\b\u0003\u0018\u00010202\u0018\u00010\\0Z8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b]\u0010^R\u0016\u0010`\u001a\u00020_8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b`\u0010aR\u0016\u0010c\u001a\u00020b8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bc\u0010dR$\u0010e\u001a\u0010\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\f\u0018\u00010&8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\be\u0010fR\"\u0010g\u001a\u00020 8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bg\u0010h\u001a\u0004\bi\u0010j\"\u0004\bk\u0010lR\u0016\u0010m\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bm\u0010nR\u0016\u0010p\u001a\u00020 8@@\u0000X\u0080\u0004¢\u0006\u0006\u001a\u0004\bo\u0010jR\"\u0010q\u001a\u00020 8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bq\u0010h\u001a\u0004\br\u0010j\"\u0004\bs\u0010lR \u0010w\u001a\u00020+*\u00020)8B@\u0002X\u0082\u0004¢\u0006\f\u0012\u0004\bu\u0010v\u001a\u0004\bt\u0010-R\u001a\u0010x\u001a\u00020 *\u00020\u00178B@\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\bx\u0010y¨\u0006{"}, m3961d2 = {"Lkotlinx/coroutines/debug/internal/DebugProbesImpl;", "<init>", "()V", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlin/coroutines/Continuation;", "completion", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", TypedValues.Attributes.S_FRAME, "createOwner", "(Lkotlin/coroutines/Continuation;Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;)Lkotlin/coroutines/Continuation;", "Ljava/io/PrintStream;", "out", "", "dumpCoroutines", "(Ljava/io/PrintStream;)V", "", "Lkotlinx/coroutines/debug/internal/DebugCoroutineInfo;", "dumpCoroutinesInfo", "()Ljava/util/List;", "dumpCoroutinesSynchronized", "Lkotlinx/coroutines/debug/internal/DebuggerInfo;", "dumpDebuggerInfo", "info", "Ljava/lang/StackTraceElement;", "coroutineTrace", "enhanceStackTraceWithThreadDump", "(Lkotlinx/coroutines/debug/internal/DebugCoroutineInfo;Ljava/util/List;)Ljava/util/List;", "", "indexOfResumeWith", "", "actualTrace", "Lkotlin/Pair;", "", "findContinuationStartIndex", "(I[Ljava/lang/StackTraceElement;Ljava/util/List;)Lkotlin/Pair;", "frameIndex", "findIndexOfFrame", "(I[Ljava/lang/StackTraceElement;Ljava/util/List;)I", "Lkotlin/Function1;", "getDynamicAttach", "()Lkotlin/jvm/functions/Function1;", "Lkotlinx/coroutines/Job;", "job", "", "hierarchyToString", "(Lkotlinx/coroutines/Job;)Ljava/lang/String;", "install", "frames", "printStackTrace", "(Ljava/io/PrintStream;Ljava/util/List;)V", "Lkotlinx/coroutines/debug/internal/DebugProbesImpl$CoroutineOwner;", "owner", "probeCoroutineCompleted", "(Lkotlinx/coroutines/debug/internal/DebugProbesImpl$CoroutineOwner;)V", "probeCoroutineCreated$kotlinx_coroutines_core", "(Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;", "probeCoroutineCreated", "probeCoroutineResumed$kotlinx_coroutines_core", "(Lkotlin/coroutines/Continuation;)V", "probeCoroutineResumed", "probeCoroutineSuspended$kotlinx_coroutines_core", "probeCoroutineSuspended", "", "throwable", "sanitizeStackTrace", "(Ljava/lang/Throwable;)Ljava/util/List;", "uninstall", "state", "updateRunningState", "(Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;Ljava/lang/String;)V", "updateState", "(Lkotlin/coroutines/Continuation;Ljava/lang/String;)V", "(Lkotlinx/coroutines/debug/internal/DebugProbesImpl$CoroutineOwner;Lkotlin/coroutines/Continuation;Ljava/lang/String;)V", "", MapElement.Key.MAP, "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "builder", "indent", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "(Lkotlinx/coroutines/Job;Ljava/util/Map;Ljava/lang/StringBuilder;Ljava/lang/String;)V", "(Lkotlin/coroutines/Continuation;)Lkotlinx/coroutines/debug/internal/DebugProbesImpl$CoroutineOwner;", "(Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;)Lkotlinx/coroutines/debug/internal/DebugProbesImpl$CoroutineOwner;", "realCaller", "(Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;)Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "ARTIFICIAL_FRAME_MESSAGE", "Ljava/lang/String;", "Ljava/util/concurrent/ConcurrentHashMap;", "callerInfoCache", "Ljava/util/concurrent/ConcurrentHashMap;", "", "kotlin.jvm.PlatformType", "", "capturedCoroutines", "Ljava/util/Set;", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "coroutineStateLock", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "Ljava/text/SimpleDateFormat;", "dateFormat", "Ljava/text/SimpleDateFormat;", "dynamicAttach", "Lkotlin/jvm/functions/Function1;", "enableCreationStackTraces", "Z", "getEnableCreationStackTraces", "()Z", "setEnableCreationStackTraces", "(Z)V", "installations", "I", "isInstalled$kotlinx_coroutines_core", "isInstalled", "sanitizeStackTraces", "getSanitizeStackTraces", "setSanitizeStackTraces", "getDebugString", "debugString$annotations", "(Lkotlinx/coroutines/Job;)V", "debugString", "isInternalMethod", "(Ljava/lang/StackTraceElement;)Z", "CoroutineOwner", "kotlinx-coroutines-core", ""}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class DebugProbesImpl {
    private static final String ARTIFICIAL_FRAME_MESSAGE = "Coroutine creation stacktrace";
    public static final DebugProbesImpl INSTANCE;
    private static final ConcurrentHashMap<CoroutineStackFrame, DebugCoroutineInfo> callerInfoCache;
    private static final Set<CoroutineOwner<?>> capturedCoroutines;
    private static final ReentrantReadWriteLock coroutineStateLock;
    private static final SimpleDateFormat dateFormat;
    static final /* synthetic */ DebugProbesImplSequenceNumberRefVolatile debugProbesImplSequenceNumberRefVolatile;
    private static final Function1<Boolean, Unit> dynamicAttach;
    private static boolean enableCreationStackTraces;
    private static volatile int installations;
    private static boolean sanitizeStackTraces;
    static final /* synthetic */ AtomicLongFieldUpdater sequenceNumber$FU;

    private static /* synthetic */ void debugString$annotations(Job job) {
    }

    /* JADX WARN: Type inference failed for: r1v4, types: [kotlinx.coroutines.debug.internal.DebugProbesImplSequenceNumberRefVolatile] */
    static {
        DebugProbesImpl debugProbesImpl = new DebugProbesImpl();
        INSTANCE = debugProbesImpl;
        dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        capturedCoroutines = Collections.newSetFromMap(new ConcurrentHashMap());
        final long j = 0;
        debugProbesImplSequenceNumberRefVolatile = new Object(j) { // from class: kotlinx.coroutines.debug.internal.DebugProbesImplSequenceNumberRefVolatile
            volatile long sequenceNumber;

            {
                this.sequenceNumber = j;
            }
        };
        coroutineStateLock = new ReentrantReadWriteLock();
        sanitizeStackTraces = true;
        enableCreationStackTraces = true;
        dynamicAttach = debugProbesImpl.getDynamicAttach();
        callerInfoCache = new ConcurrentHashMap<>();
        sequenceNumber$FU = AtomicLongFieldUpdater.newUpdater(DebugProbesImplSequenceNumberRefVolatile.class, "sequenceNumber");
    }

    private DebugProbesImpl() {
    }

    public final boolean isInstalled$kotlinx_coroutines_core() {
        return installations > 0;
    }

    public final boolean getSanitizeStackTraces() {
        return sanitizeStackTraces;
    }

    public final void setSanitizeStackTraces(boolean z) {
        sanitizeStackTraces = z;
    }

    public final boolean getEnableCreationStackTraces() {
        return enableCreationStackTraces;
    }

    public final void setEnableCreationStackTraces(boolean z) {
        enableCreationStackTraces = z;
    }

    private final Function1<Boolean, Unit> getDynamicAttach() {
        Object m4510constructorimpl;
        Object newInstance;
        try {
            Result.Companion companion = Result.INSTANCE;
            DebugProbesImpl debugProbesImpl = this;
            newInstance = Class.forName("kotlinx.coroutines.debug.internal.ByteBuddyDynamicAttach").getConstructors()[0].newInstance(new Object[0]);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m4510constructorimpl = Result.m4510constructorimpl(ResultKt.createFailure(th));
        }
        if (newInstance != null) {
            m4510constructorimpl = Result.m4510constructorimpl((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(newInstance, 1));
            if (Result.m4516isFailureimpl(m4510constructorimpl)) {
                m4510constructorimpl = null;
            }
            return (Function1) m4510constructorimpl;
        }
        throw new TypeCastException("null cannot be cast to non-null type (kotlin.Boolean) -> kotlin.Unit");
    }

    public final void install() {
        ReentrantReadWriteLock reentrantReadWriteLock = coroutineStateLock;
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        int i = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i2 = 0; i2 < readHoldCount; i2++) {
            readLock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            installations++;
            if (installations > 1) {
                return;
            }
            if (AgentPremain.INSTANCE.isInstalledStatically()) {
                while (i < readHoldCount) {
                    readLock.lock();
                    i++;
                }
                writeLock.unlock();
                return;
            }
            Function1<Boolean, Unit> function1 = dynamicAttach;
            if (function1 != null) {
                function1.invoke(true);
            }
            Unit unit = Unit.INSTANCE;
            while (i < readHoldCount) {
                readLock.lock();
                i++;
            }
            writeLock.unlock();
        } finally {
            while (i < readHoldCount) {
                readLock.lock();
                i++;
            }
            writeLock.unlock();
        }
    }

    public final void uninstall() {
        ReentrantReadWriteLock reentrantReadWriteLock = coroutineStateLock;
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        int i = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i2 = 0; i2 < readHoldCount; i2++) {
            readLock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            if (!INSTANCE.isInstalled$kotlinx_coroutines_core()) {
                throw new IllegalStateException("Agent was not installed".toString());
            }
            installations--;
            if (installations != 0) {
                return;
            }
            capturedCoroutines.clear();
            callerInfoCache.clear();
            if (AgentPremain.INSTANCE.isInstalledStatically()) {
                while (i < readHoldCount) {
                    readLock.lock();
                    i++;
                }
                writeLock.unlock();
                return;
            }
            Function1<Boolean, Unit> function1 = dynamicAttach;
            if (function1 != null) {
                function1.invoke(false);
            }
            Unit unit = Unit.INSTANCE;
            while (i < readHoldCount) {
                readLock.lock();
                i++;
            }
            writeLock.unlock();
        } finally {
            while (i < readHoldCount) {
                readLock.lock();
                i++;
            }
            writeLock.unlock();
        }
    }

    public final String hierarchyToString(Job job) {
        ReentrantReadWriteLock reentrantReadWriteLock = coroutineStateLock;
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        int i = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i2 = 0; i2 < readHoldCount; i2++) {
            readLock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            if (!INSTANCE.isInstalled$kotlinx_coroutines_core()) {
                throw new IllegalStateException("Debug probes are not installed".toString());
            }
            Set<CoroutineOwner<?>> set = capturedCoroutines;
            ArrayList arrayList = new ArrayList();
            for (Object obj : set) {
                if (((CoroutineOwner) obj).delegate.getContext().get(Job.INSTANCE) != null) {
                    arrayList.add(obj);
                }
            }
            ArrayList arrayList2 = arrayList;
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(arrayList2, 10)), 16));
            for (Object obj2 : arrayList2) {
                CoroutineContext.Element element = ((CoroutineOwner) obj2).delegate.getContext().get(Job.INSTANCE);
                if (element == null) {
                    Intrinsics.throwNpe();
                }
                linkedHashMap.put((Job) element, ((CoroutineOwner) obj2).info);
            }
            StringBuilder sb = new StringBuilder();
            INSTANCE.build(job, linkedHashMap, sb, "");
            String sb2 = sb.toString();
            Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
            return sb2;
        } finally {
            while (i < readHoldCount) {
                readLock.lock();
                i++;
            }
            writeLock.unlock();
        }
    }

    private final void build(Job job, Map<Job, DebugCoroutineInfo> map, StringBuilder sb, String str) {
        DebugCoroutineInfo debugCoroutineInfo = map.get(job);
        if (debugCoroutineInfo == null) {
            if (!(job instanceof ScopeCoroutine)) {
                sb.append(str + getDebugString(job) + '\n');
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append("\t");
                str = sb2.toString();
            }
        } else {
            StackTraceElement stackTraceElement = (StackTraceElement) CollectionsKt.firstOrNull((List) debugCoroutineInfo.lastObservedStackTrace());
            sb.append(str + getDebugString(job) + ", continuation is " + debugCoroutineInfo.get_state() + " at line " + stackTraceElement + '\n');
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str);
            sb3.append("\t");
            str = sb3.toString();
        }
        Iterator<Job> it = job.getChildren().iterator();
        while (it.hasNext()) {
            build(it.next(), map, sb, str);
        }
    }

    private final String getDebugString(Job job) {
        return job instanceof JobSupport ? ((JobSupport) job).toDebugString() : job.toString();
    }

    public final List<DebugCoroutineInfo> dumpCoroutinesInfo() {
        ReentrantReadWriteLock reentrantReadWriteLock = coroutineStateLock;
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        int i = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i2 = 0; i2 < readHoldCount; i2++) {
            readLock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            if (!INSTANCE.isInstalled$kotlinx_coroutines_core()) {
                throw new IllegalStateException("Debug probes are not installed".toString());
            }
            return SequencesKt.toList(SequencesKt.sortedWith(SequencesKt.map(CollectionsKt.asSequence(capturedCoroutines), new Function1<CoroutineOwner<?>, DebugCoroutineInfo>() { // from class: kotlinx.coroutines.debug.internal.DebugProbesImpl$dumpCoroutinesInfo$1$2
                @Override // kotlin.jvm.functions.Function1
                public final DebugCoroutineInfo invoke(DebugProbesImpl.CoroutineOwner<?> coroutineOwner) {
                    return coroutineOwner.info.copy();
                }
            }), new Comparator<T>() { // from class: kotlinx.coroutines.debug.internal.DebugProbesImpl$$special$$inlined$sortedBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(Long.valueOf(((DebugCoroutineInfo) t).sequenceNumber), Long.valueOf(((DebugCoroutineInfo) t2).sequenceNumber));
                }
            }));
        } finally {
            while (i < readHoldCount) {
                readLock.lock();
                i++;
            }
            writeLock.unlock();
        }
    }

    public final List<DebuggerInfo> dumpDebuggerInfo() {
        List<DebugCoroutineInfo> dumpCoroutinesInfo = dumpCoroutinesInfo();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(dumpCoroutinesInfo, 10));
        Iterator<T> it = dumpCoroutinesInfo.iterator();
        while (it.hasNext()) {
            arrayList.add(new DebuggerInfo((DebugCoroutineInfo) it.next()));
        }
        return arrayList;
    }

    public final void dumpCoroutines(PrintStream out) {
        synchronized (out) {
            INSTANCE.dumpCoroutinesSynchronized(out);
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void dumpCoroutinesSynchronized(PrintStream out) {
        ReentrantReadWriteLock reentrantReadWriteLock = coroutineStateLock;
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        int i = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i2 = 0; i2 < readHoldCount; i2++) {
            readLock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            if (!INSTANCE.isInstalled$kotlinx_coroutines_core()) {
                throw new IllegalStateException("Debug probes are not installed".toString());
            }
            out.print("Coroutines dump " + dateFormat.format(Long.valueOf(System.currentTimeMillis())));
            for (CoroutineOwner coroutineOwner : SequencesKt.sortedWith(CollectionsKt.asSequence(capturedCoroutines), new Comparator<T>() { // from class: kotlinx.coroutines.debug.internal.DebugProbesImpl$$special$$inlined$sortedBy$2
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(Long.valueOf(((DebugProbesImpl.CoroutineOwner) t).info.sequenceNumber), Long.valueOf(((DebugProbesImpl.CoroutineOwner) t2).info.sequenceNumber));
                }
            })) {
                DebugCoroutineInfo debugCoroutineInfo = coroutineOwner.info;
                List<StackTraceElement> lastObservedStackTrace = debugCoroutineInfo.lastObservedStackTrace();
                List<StackTraceElement> enhanceStackTraceWithThreadDump = INSTANCE.enhanceStackTraceWithThreadDump(debugCoroutineInfo, lastObservedStackTrace);
                out.print("\n\nCoroutine " + coroutineOwner.delegate + ", state: " + ((Intrinsics.areEqual(debugCoroutineInfo.get_state(), DebugCoroutineInfoKt.RUNNING) && enhanceStackTraceWithThreadDump == lastObservedStackTrace) ? debugCoroutineInfo.get_state() + " (Last suspension stacktrace, not an actual stacktrace)" : debugCoroutineInfo.get_state().toString()));
                if (lastObservedStackTrace.isEmpty()) {
                    out.print("\n\tat " + StackTraceRecoveryKt.artificialFrame(ARTIFICIAL_FRAME_MESSAGE));
                    INSTANCE.printStackTrace(out, debugCoroutineInfo.getCreationStackTrace());
                } else {
                    INSTANCE.printStackTrace(out, enhanceStackTraceWithThreadDump);
                }
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            while (i < readHoldCount) {
                readLock.lock();
                i++;
            }
            writeLock.unlock();
        }
    }

    private final void printStackTrace(PrintStream out, List<StackTraceElement> frames) {
        Iterator<T> it = frames.iterator();
        while (it.hasNext()) {
            out.print("\n\tat " + ((StackTraceElement) it.next()));
        }
    }

    private final List<StackTraceElement> enhanceStackTraceWithThreadDump(DebugCoroutineInfo info, List<StackTraceElement> coroutineTrace) {
        Object m4510constructorimpl;
        Thread thread = info.lastObservedThread;
        if (!(!Intrinsics.areEqual(info.get_state(), DebugCoroutineInfoKt.RUNNING)) && thread != null) {
            try {
                Result.Companion companion = Result.INSTANCE;
                DebugProbesImpl debugProbesImpl = this;
                m4510constructorimpl = Result.m4510constructorimpl(thread.getStackTrace());
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m4510constructorimpl = Result.m4510constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m4516isFailureimpl(m4510constructorimpl)) {
                m4510constructorimpl = null;
            }
            StackTraceElement[] stackTraceElementArr = (StackTraceElement[]) m4510constructorimpl;
            if (stackTraceElementArr != null) {
                int length = stackTraceElementArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        i = -1;
                        break;
                    }
                    StackTraceElement stackTraceElement = stackTraceElementArr[i];
                    if (Intrinsics.areEqual(stackTraceElement.getClassName(), "kotlin.coroutines.jvm.internal.BaseContinuationImpl") && Intrinsics.areEqual(stackTraceElement.getMethodName(), "resumeWith") && Intrinsics.areEqual(stackTraceElement.getFileName(), "ContinuationImpl.kt")) {
                        break;
                    }
                    i++;
                }
                Pair<Integer, Boolean> findContinuationStartIndex = findContinuationStartIndex(i, stackTraceElementArr, coroutineTrace);
                int intValue = findContinuationStartIndex.component1().intValue();
                boolean booleanValue = findContinuationStartIndex.component2().booleanValue();
                if (intValue == -1) {
                    return coroutineTrace;
                }
                ArrayList arrayList = new ArrayList((((coroutineTrace.size() + i) - intValue) - 1) - (booleanValue ? 1 : 0));
                int i2 = i - (booleanValue ? 1 : 0);
                for (int i3 = 0; i3 < i2; i3++) {
                    arrayList.add(stackTraceElementArr[i3]);
                }
                int size = coroutineTrace.size();
                for (int i4 = intValue + 1; i4 < size; i4++) {
                    arrayList.add(coroutineTrace.get(i4));
                }
                return arrayList;
            }
        }
        return coroutineTrace;
    }

    private final Pair<Integer, Boolean> findContinuationStartIndex(int indexOfResumeWith, StackTraceElement[] actualTrace, List<StackTraceElement> coroutineTrace) {
        int findIndexOfFrame = findIndexOfFrame(indexOfResumeWith - 1, actualTrace, coroutineTrace);
        if (findIndexOfFrame == -1) {
            return TuplesKt.m3968to(Integer.valueOf(findIndexOfFrame(indexOfResumeWith - 2, actualTrace, coroutineTrace)), true);
        }
        return TuplesKt.m3968to(Integer.valueOf(findIndexOfFrame), false);
    }

    private final int findIndexOfFrame(int frameIndex, StackTraceElement[] actualTrace, List<StackTraceElement> coroutineTrace) {
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.getOrNull(actualTrace, frameIndex);
        if (stackTraceElement == null) {
            return -1;
        }
        int i = 0;
        for (StackTraceElement stackTraceElement2 : coroutineTrace) {
            if (Intrinsics.areEqual(stackTraceElement2.getFileName(), stackTraceElement.getFileName()) && Intrinsics.areEqual(stackTraceElement2.getClassName(), stackTraceElement.getClassName()) && Intrinsics.areEqual(stackTraceElement2.getMethodName(), stackTraceElement.getMethodName())) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public final void probeCoroutineResumed$kotlinx_coroutines_core(Continuation<?> frame) {
        updateState(frame, DebugCoroutineInfoKt.RUNNING);
    }

    public final void probeCoroutineSuspended$kotlinx_coroutines_core(Continuation<?> frame) {
        updateState(frame, DebugCoroutineInfoKt.SUSPENDED);
    }

    private final void updateState(Continuation<?> frame, String state) {
        if (Intrinsics.areEqual(state, DebugCoroutineInfoKt.RUNNING) && KotlinVersion.CURRENT.isAtLeast(1, 3, 30)) {
            if (!(frame instanceof CoroutineStackFrame)) {
                frame = null;
            }
            CoroutineStackFrame coroutineStackFrame = (CoroutineStackFrame) frame;
            if (coroutineStackFrame != null) {
                updateRunningState(coroutineStackFrame, state);
                return;
            }
            return;
        }
        CoroutineOwner<?> owner = owner(frame);
        if (owner != null) {
            updateState(owner, frame, state);
        }
    }

    private final void updateRunningState(CoroutineStackFrame frame, String state) {
        ReentrantReadWriteLock.ReadLock readLock = coroutineStateLock.readLock();
        readLock.lock();
        try {
            if (INSTANCE.isInstalled$kotlinx_coroutines_core()) {
                DebugCoroutineInfo remove = callerInfoCache.remove(frame);
                if (remove == null) {
                    CoroutineOwner<?> owner = INSTANCE.owner(frame);
                    if (owner == null || (remove = owner.info) == null) {
                        return;
                    }
                    CoroutineStackFrame coroutineStackFrame = remove.lastObservedFrame;
                    CoroutineStackFrame realCaller = coroutineStackFrame != null ? INSTANCE.realCaller(coroutineStackFrame) : null;
                    if (realCaller != null) {
                        callerInfoCache.remove(realCaller);
                    }
                }
                if (frame != null) {
                    remove.updateState$kotlinx_coroutines_core(state, (Continuation) frame);
                    CoroutineStackFrame realCaller2 = INSTANCE.realCaller(frame);
                    if (realCaller2 != null) {
                        callerInfoCache.put(realCaller2, remove);
                        Unit unit = Unit.INSTANCE;
                        return;
                    }
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.Continuation<*>");
            }
        } finally {
            readLock.unlock();
        }
    }

    private final CoroutineStackFrame realCaller(CoroutineStackFrame coroutineStackFrame) {
        do {
            coroutineStackFrame = coroutineStackFrame.getCallerFrame();
            if (coroutineStackFrame == null) {
                return null;
            }
        } while (coroutineStackFrame.get$frame() == null);
        return coroutineStackFrame;
    }

    private final void updateState(CoroutineOwner<?> owner, Continuation<?> frame, String state) {
        ReentrantReadWriteLock.ReadLock readLock = coroutineStateLock.readLock();
        readLock.lock();
        try {
            if (INSTANCE.isInstalled$kotlinx_coroutines_core()) {
                owner.info.updateState$kotlinx_coroutines_core(state, frame);
                Unit unit = Unit.INSTANCE;
            }
        } finally {
            readLock.unlock();
        }
    }

    private final CoroutineOwner<?> owner(Continuation<?> continuation) {
        if (!(continuation instanceof CoroutineStackFrame)) {
            continuation = null;
        }
        CoroutineStackFrame coroutineStackFrame = (CoroutineStackFrame) continuation;
        if (coroutineStackFrame != null) {
            return owner(coroutineStackFrame);
        }
        return null;
    }

    private final CoroutineOwner<?> owner(CoroutineStackFrame coroutineStackFrame) {
        while (!(coroutineStackFrame instanceof CoroutineOwner)) {
            coroutineStackFrame = coroutineStackFrame.getCallerFrame();
            if (coroutineStackFrame == null) {
                return null;
            }
        }
        return (CoroutineOwner) coroutineStackFrame;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T> Continuation<T> probeCoroutineCreated$kotlinx_coroutines_core(Continuation<? super T> completion) {
        if (!isInstalled$kotlinx_coroutines_core() || owner(completion) != null) {
            return completion;
        }
        CoroutineStackFrame coroutineStackFrame = null;
        if (enableCreationStackTraces) {
            List<StackTraceElement> sanitizeStackTrace = sanitizeStackTrace(new Exception());
            if (!sanitizeStackTrace.isEmpty()) {
                ListIterator<StackTraceElement> listIterator = sanitizeStackTrace.listIterator(sanitizeStackTrace.size());
                while (listIterator.hasPrevious()) {
                    final CoroutineStackFrame coroutineStackFrame2 = coroutineStackFrame;
                    final StackTraceElement previous = listIterator.previous();
                    coroutineStackFrame = new CoroutineStackFrame(previous, coroutineStackFrame2) { // from class: kotlinx.coroutines.debug.internal.DebugProbesImpl$probeCoroutineCreated$frame$1$1
                        final /* synthetic */ CoroutineStackFrame $acc;
                        final /* synthetic */ StackTraceElement $frame;
                        private final CoroutineStackFrame callerFrame;

                        /* JADX INFO: Access modifiers changed from: package-private */
                        {
                            this.$acc = coroutineStackFrame2;
                            this.callerFrame = coroutineStackFrame2;
                        }

                        @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
                        public CoroutineStackFrame getCallerFrame() {
                            return this.callerFrame;
                        }

                        @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
                        /* renamed from: getStackTraceElement, reason: from getter */
                        public StackTraceElement get$frame() {
                            return this.$frame;
                        }
                    };
                }
            }
            coroutineStackFrame = coroutineStackFrame;
        }
        return createOwner(completion, coroutineStackFrame);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final <T> Continuation<T> createOwner(Continuation<? super T> completion, CoroutineStackFrame frame) {
        if (!isInstalled$kotlinx_coroutines_core()) {
            return completion;
        }
        CoroutineOwner<?> coroutineOwner = new CoroutineOwner<>(completion, new DebugCoroutineInfo(completion.getContext(), frame, sequenceNumber$FU.incrementAndGet(debugProbesImplSequenceNumberRefVolatile)), frame);
        capturedCoroutines.add(coroutineOwner);
        if (!isInstalled$kotlinx_coroutines_core()) {
            capturedCoroutines.clear();
        }
        return coroutineOwner;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void probeCoroutineCompleted(CoroutineOwner<?> owner) {
        CoroutineStackFrame realCaller;
        capturedCoroutines.remove(owner);
        CoroutineStackFrame coroutineStackFrame = owner.info.lastObservedFrame;
        if (coroutineStackFrame == null || (realCaller = realCaller(coroutineStackFrame)) == null) {
            return;
        }
        callerInfoCache.remove(realCaller);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* compiled from: DebugProbesImpl.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003B%\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\bJ\n\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u001e\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0015H\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u0016\u0010\t\u001a\u0004\u0018\u00010\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\rX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00028\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, m3961d2 = {"Lkotlinx/coroutines/debug/internal/DebugProbesImpl$CoroutineOwner;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlin/coroutines/Continuation;", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "delegate", "info", "Lkotlinx/coroutines/debug/internal/DebugCoroutineInfo;", TypedValues.Attributes.S_FRAME, "(Lkotlin/coroutines/Continuation;Lkotlinx/coroutines/debug/internal/DebugCoroutineInfo;Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;)V", "callerFrame", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "getStackTraceElement", "Ljava/lang/StackTraceElement;", "resumeWith", "", SpeechUtility.TAG_RESOURCE_RESULT, "Lkotlin/Result;", "(Ljava/lang/Object;)V", "toString", "", "kotlinx-coroutines-core"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static final class CoroutineOwner<T> implements Continuation<T>, CoroutineStackFrame {
        public final Continuation<T> delegate;
        private final CoroutineStackFrame frame;
        public final DebugCoroutineInfo info;

        @Override // kotlin.coroutines.Continuation
        public CoroutineContext getContext() {
            return this.delegate.getContext();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public CoroutineOwner(Continuation<? super T> continuation, DebugCoroutineInfo debugCoroutineInfo, CoroutineStackFrame coroutineStackFrame) {
            this.delegate = continuation;
            this.info = debugCoroutineInfo;
            this.frame = coroutineStackFrame;
        }

        @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
        public CoroutineStackFrame getCallerFrame() {
            CoroutineStackFrame coroutineStackFrame = this.frame;
            if (coroutineStackFrame != null) {
                return coroutineStackFrame.getCallerFrame();
            }
            return null;
        }

        @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
        /* renamed from: getStackTraceElement */
        public StackTraceElement get$frame() {
            CoroutineStackFrame coroutineStackFrame = this.frame;
            if (coroutineStackFrame != null) {
                return coroutineStackFrame.get$frame();
            }
            return null;
        }

        @Override // kotlin.coroutines.Continuation
        public void resumeWith(Object result) {
            DebugProbesImpl.INSTANCE.probeCoroutineCompleted(this);
            this.delegate.resumeWith(result);
        }

        public String toString() {
            return this.delegate.toString();
        }
    }

    private final <T extends Throwable> List<StackTraceElement> sanitizeStackTrace(T throwable) {
        StackTraceElement[] stackTrace = throwable.getStackTrace();
        int length = stackTrace.length;
        int i = -1;
        int length2 = stackTrace.length - 1;
        while (true) {
            if (length2 < 0) {
                break;
            }
            if (Intrinsics.areEqual(stackTrace[length2].getClassName(), "kotlin.coroutines.jvm.internal.DebugProbesKt")) {
                i = length2;
                break;
            }
            length2--;
        }
        int i2 = 0;
        if (!sanitizeStackTraces) {
            int i3 = length - i;
            ArrayList arrayList = new ArrayList(i3);
            while (i2 < i3) {
                arrayList.add(i2 == 0 ? StackTraceRecoveryKt.artificialFrame(ARTIFICIAL_FRAME_MESSAGE) : stackTrace[i2 + i]);
                i2++;
            }
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList((length - i) + 1);
        ArrayList arrayList3 = arrayList2;
        arrayList3.add(StackTraceRecoveryKt.artificialFrame(ARTIFICIAL_FRAME_MESSAGE));
        int i4 = length - 1;
        boolean z = true;
        for (int i5 = i + 1; i5 < i4; i5++) {
            StackTraceElement stackTraceElement = stackTrace[i5];
            if (isInternalMethod(stackTraceElement)) {
                if (z) {
                    arrayList3.add(stackTraceElement);
                    z = false;
                } else if (!isInternalMethod(stackTrace[i5 + 1])) {
                    arrayList3.add(stackTraceElement);
                }
            } else {
                arrayList3.add(stackTraceElement);
            }
            z = true;
        }
        arrayList3.add(stackTrace[i4]);
        return arrayList2;
    }

    private final boolean isInternalMethod(StackTraceElement stackTraceElement) {
        return StringsKt.startsWith$default(stackTraceElement.getClassName(), "kotlinx.coroutines", false, 2, (Object) null);
    }
}
