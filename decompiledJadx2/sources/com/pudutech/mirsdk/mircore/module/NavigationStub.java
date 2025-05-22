package com.pudutech.mirsdk.mircore.module;

import androidx.core.app.NotificationCompat;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.compat.topo.MapElement;
import com.pudutech.mirsdk.hardware.serialize.AccelerationType;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.hardware.serialize.SchedulingMode;
import com.pudutech.mirsdk.hardware.serialize.Vector2d;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.MirCoreImpl;
import com.pudutech.mirsdk.mircore.NavigationInterface;
import com.pudutech.mirsdk.mircore.NavigationResultListener;
import com.pudutech.mirsdk.mircore.aidl.CliffInfoListener;
import com.pudutech.mirsdk.mircore.coreparcel.LocalizationStatusLevel;
import com.pudutech.mirsdk.mircore.coreparcel.MoveMode;
import com.pudutech.mirsdk.mircore.coreparcel.NavigationMode;
import com.pudutech.mirsdk.mircore.coreparcel.NavigationResult;
import com.pudutech.mirsdk.mircore.coreparcel.NavigationStatus;
import com.pudutech.mirsdk.mircore.coreparcel.NextNavigationBehavior;
import com.pudutech.mirsdk.mircore.coreparcel.RotateResult;
import com.pudutech.mirsdk.mircore.coreparcel.SmoothMode;
import com.pudutech.mirsdk.mircore.mirnavigation.Navigation;
import com.pudutech.mirsdk.mircore.mirperception.Perception;
import com.pudutech.mirsdk.mircore.mirschedulemaster.ScheduleMaster;
import com.pudutech.mirsdk.mircore.module.NavigationStub;
import com.pudutech.mirsdk.mircore.module.cycleparam.CycleParamUtils;
import com.pudutech.mirsdk.mircore.module.cycleparam.ExecuteType;
import com.pudutech.mirsdk.mircore.module.speedlevel.PlannerParamUtils;
import com.pudutech.mirsdk.mircore.module.speedlevel.RunParamUtils;
import com.pudutech.mirsdk.mircore.tools.AnalysisUtils;
import com.pudutech.mirsdk.mircore.tools.Angles;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.NotImplementedError;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: NavigationStub.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000¶\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010!\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0016\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0011\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0019\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010i\u001a\u00020\u00192\u0006\u0010j\u001a\u00020\u0019H\u0016J\u0010\u0010k\u001a\u00020l2\u0006\u0010m\u001a\u00020nH\u0002J\u0010\u0010o\u001a\u00020l2\u0006\u0010p\u001a\u00020nH\u0002J\u0010\u0010q\u001a\u00020l2\u0006\u0010p\u001a\u00020nH\u0002J\u000e\u0010r\u001a\u00020s2\u0006\u0010t\u001a\u00020\u000eJ\b\u0010u\u001a\u00020lH\u0002J\u0006\u0010v\u001a\u00020sJ\u000e\u0010w\u001a\u00020s2\u0006\u0010x\u001a\u00020lJ\u000e\u0010y\u001a\u00020s2\u0006\u0010z\u001a\u00020{J!\u0010|\u001a\u00020s2\u0006\u0010}\u001a\u00020\u00042\u0006\u0010~\u001a\u00020\u00042\u0007\u0010\u007f\u001a\u00030\u0080\u0001H\u0002J\u0012\u0010\u0081\u0001\u001a\u00020s2\u0007\u0010\u0082\u0001\u001a\u00020\u0019H\u0016J\u0012\u0010\u0083\u0001\u001a\u00020s2\u0007\u0010\u0082\u0001\u001a\u00020\u0019H\u0016J\t\u0010\u0084\u0001\u001a\u00020\u000eH\u0016J\t\u0010\u0085\u0001\u001a\u00020\u0019H\u0016J\t\u0010\u0086\u0001\u001a\u00020\fH\u0016J\t\u0010\u0087\u0001\u001a\u00020\fH\u0016J\t\u0010\u0088\u0001\u001a\u00020NH\u0016J\u0013\u0010\u0089\u0001\u001a\u00020\u00062\b\u0010j\u001a\u0004\u0018\u000102H\u0016J\u0016\u0010\u008a\u0001\u001a\t\u0012\u0004\u0012\u00020\u00060\u008b\u0001H\u0016¢\u0006\u0003\u0010\u008c\u0001J\t\u0010\u008d\u0001\u001a\u00020\u0004H\u0016J\u0010\u0010\u008e\u0001\u001a\u00020s2\u0007\u0010\u008f\u0001\u001a\u00020\u0006J\u0010\u0010\u0090\u0001\u001a\u00020s2\u0007\u0010\u008f\u0001\u001a\u00020\u0006J\u0007\u0010\u0091\u0001\u001a\u00020\u0019J\u001c\u0010\u0092\u0001\u001a\u00020l2\b\u0010\u0093\u0001\u001a\u00030\u0094\u00012\u0007\u0010\u0095\u0001\u001a\u00020\u0019H\u0016J\u0010\u0010\u0096\u0001\u001a\u00020\u00042\u0007\u0010\u0097\u0001\u001a\u00020\u0004J\t\u0010\u0098\u0001\u001a\u00020sH\u0016J,\u0010\u0099\u0001\u001a\u00020l2\u0006\u0010p\u001a\u00020n2\b\u0010\u009a\u0001\u001a\u00030\u0094\u00012\u0007\u0010\u0095\u0001\u001a\u00020\u00192\u0006\u0010V\u001a\u00020\u0019H\u0002J$\u0010\u009b\u0001\u001a\u00020l2\u0006\u0010p\u001a\u00020n2\b\u0010\u009a\u0001\u001a\u00030\u0094\u00012\u0007\u0010\u0095\u0001\u001a\u00020\u0019H\u0002J\u0012\u0010\u009c\u0001\u001a\u00020\u00192\u0007\u0010\u009d\u0001\u001a\u00020\u000eH\u0016J#\u0010\u009e\u0001\u001a\u00020s2\u0007\u0010\u009f\u0001\u001a\u00020\f2\u000f\u0010 \u0001\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010+H\u0016J\u001b\u0010¡\u0001\u001a\u00020\u00192\u0007\u0010¢\u0001\u001a\u00020\u00192\u0007\u0010£\u0001\u001a\u00020\u0019H\u0016J\t\u0010¤\u0001\u001a\u00020\u0019H\u0016J\t\u0010¥\u0001\u001a\u00020\u0019H\u0016J\t\u0010¦\u0001\u001a\u00020\u0019H\u0016J\u0010\u0010§\u0001\u001a\u00020s2\u0007\u0010\u0097\u0001\u001a\u00020\u0004J\t\u0010¨\u0001\u001a\u00020sH\u0016J\t\u0010©\u0001\u001a\u00020sH\u0016J\u0010\u0010ª\u0001\u001a\u00020s2\u0007\u0010«\u0001\u001a\u00020\u000eJ\u0013\u0010¬\u0001\u001a\u00030\u00ad\u00012\u0007\u0010®\u0001\u001a\u00020\u000eH\u0016J\t\u0010¯\u0001\u001a\u00020lH\u0016J\u0012\u0010°\u0001\u001a\u00020s2\t\u0010±\u0001\u001a\u0004\u0018\u00010\u0006J\u0011\u0010²\u0001\u001a\u00020\u00192\u0006\u0010j\u001a\u00020\u0004H\u0016J\u0010\u0010³\u0001\u001a\u00020s2\u0007\u0010´\u0001\u001a\u00020\u0019J\u0012\u0010µ\u0001\u001a\u00020\u00192\u0007\u0010¶\u0001\u001a\u00020\fH\u0016J\u0012\u0010·\u0001\u001a\u00020\u00192\u0007\u0010¸\u0001\u001a\u00020\fH\u0016J&\u0010¹\u0001\u001a\u00020s2\b\u0010º\u0001\u001a\u00030»\u00012\b\u0010\u0093\u0001\u001a\u00030\u0094\u00012\u0007\u0010\u0095\u0001\u001a\u00020\u0019H\u0016J\u001b\u0010¼\u0001\u001a\u00020\u00192\u0007\u0010\u0093\u0001\u001a\u0002022\u0007\u0010½\u0001\u001a\u00020\u0006H\u0016J\u0010\u0010¾\u0001\u001a\u00020s2\u0007\u0010¿\u0001\u001a\u00020\fJ\u001a\u0010À\u0001\u001a\u00020s2\t\u0010Á\u0001\u001a\u0004\u0018\u00010\u000eH\u0000¢\u0006\u0003\bÂ\u0001J\u0012\u0010Ã\u0001\u001a\u00020s2\u0007\u0010Ä\u0001\u001a\u00020\u0019H\u0016J\u0012\u0010Å\u0001\u001a\u00020s2\u0007\u0010Æ\u0001\u001a\u00020\u0004H\u0016J\u001a\u0010Ç\u0001\u001a\u00020s2\t\u0010È\u0001\u001a\u0004\u0018\u00010\u000eH\u0000¢\u0006\u0003\bÉ\u0001J\u0010\u0010Ê\u0001\u001a\u00020s2\u0007\u0010Ë\u0001\u001a\u00020\u000eJ\u0011\u0010Ì\u0001\u001a\u00020s2\u0006\u0010j\u001a\u00020NH\u0016J\u001a\u0010Í\u0001\u001a\u00020s2\t\u0010Î\u0001\u001a\u0004\u0018\u00010\u001fH\u0000¢\u0006\u0003\bÏ\u0001J\u0011\u0010Ð\u0001\u001a\u00020s2\u0006\u0010j\u001a\u00020\u0019H\u0016J\u0012\u0010Ñ\u0001\u001a\u00020s2\u0007\u0010Ò\u0001\u001a\u00020\u0004H\u0016J\t\u0010Ó\u0001\u001a\u00020sH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0019\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\fX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\f0\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u00020\u001f0\u001ej\b\u0012\u0004\u0012\u00020\u001f` X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010*\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010+X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u000202X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010@\u001a\u0012\u0012\u0004\u0012\u00020\u00190\u001ej\b\u0012\u0004\u0012\u00020\u0019` X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010H\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010I\u001a\u00020JX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010K\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010L\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010M\u001a\u00020NX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010O\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010P\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010Q\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010R\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010S\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010T\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010U\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010V\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010W\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010X\u001a\u00020YX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010Z\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010[\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\\\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010]\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010^\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010_\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010`\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010b\u001a\u00020\u00192\u0006\u0010a\u001a\u00020\u0019@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bc\u0010d\"\u0004\be\u0010fR\u000e\u0010g\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010h\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006Ô\u0001"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/module/NavigationStub;", "Lcom/pudutech/mirsdk/mircore/NavigationInterface$Stub;", "()V", "PI", "", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "add_on", "after_emergence_stop_count", "", "autoChargePose", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "begin_radian", "chargeTimes", "charge_dist_offset", "closeAreaRadius", "costmapErrorCNT", "costmapOKRate", "costmapStatus", "Ljava/util/LinkedList;", "coverAroundStr", "deceleration_complete", "", "deliverStart", "dirLis", "dirNav", "dockPosArray", "Ljava/util/ArrayList;", "Lcom/pudutech/mirsdk/hardware/serialize/Vector2d;", "Lkotlin/collections/ArrayList;", "emergence_stop", "featDynRoadblock", "featStuckReplan", "finishArrived", "goalDirProperty", "goal_radian", "haveSetDock", "have_file", "last_cruise_path_id", "last_cruise_stay_points", "", "last_task_model", "linear_diff_threshold", "locUseRGBD", "low_angular", "low_speed", "moveMode", "Lcom/pudutech/mirsdk/mircore/coreparcel/MoveMode;", "open_costmap_status_judge", "open_steady", "origin_acc", "origin_dec", "origin_emergence", "param_steady_acce", "param_steady_dece", "param_steady_emergence", "parking_stop_dec", "pause_stop", "poseLis", "poseNav", "preciseFlag", "recognizeArray", "reflectorBrakeFlag", "remain_radian", "rgbdPark", "rotate_PI_id", "rotate_PI_num", "rotate_begin", "rotate_end", "rotate_radian", "runType", "Lcom/pudutech/mirsdk/mircore/module/cycleparam/ExecuteType;", "sch_stop", "slow_cnt", "smoothMode", "Lcom/pudutech/mirsdk/mircore/coreparcel/SmoothMode;", "smoothStr", "speedLis", "speedNav", "steadyFlag", "steady_acc", "steady_dec", "steady_emergence", "stickToPos", "stuck_count", "stuck_start_time", "", "tmp_em_lower", "tmp_em_lower_stop_dec", "tmp_em_upper", "tmp_em_upper_stop_dec", "trayDis", "unstuck_count", "useCoveAround", ES6Iterator.VALUE_PROPERTY, "useRGBD", "getUseRGBD", "()Z", "setUseRGBD", "(Z)V", "virtualGoal", "waiterFlag", "addCoverAround", "p0", "assignReplanBehavior", "Lcom/pudutech/mirsdk/mircore/coreparcel/NavigationResult;", "costmap_addr", "", "assignVirtualPlanBehavior", "perception_address", "autoChargeBehavior", "autoChargePreparation", "", "auto_charge_pose", "avoidWaitBehavior", "clearActionStatus", "clearRgbdParkingStatus", "navResult", "confirmAccParam", "machineType", "Lcom/pudutech/mirsdk/hardware/serialize/MachineModel;", "doSlowStart", "plan_linear_vel", "plan_angular_vel", "navigation_status", "Lcom/pudutech/mirsdk/mircore/coreparcel/NavigationStatus;", "enableDynamicRoadblock", "enable", "enableStuckReplan", "getAimPoint", "getCoverAround", "getReplanWaitTime", "getRoadblockTimeout", "getSmoothMode", "getSpeedLevel", "getSpeedLevels", "", "()[Ljava/lang/String;", "getTrayDis", "initOtherParamByFiles", "absoluteFileDir", "initParamByFiles", "judgeCostmapStautus", NotificationCompat.CATEGORY_NAVIGATION, "mode", "Lcom/pudutech/mirsdk/mircore/coreparcel/NavigationMode;", "alignGoal", "normalizeRadian", "radian", "pauseNavigation", "planOnNormalPathBehavior", "navigation_mode", "planOnVirtualPathBehavior", "prepareAutoChargeTask", "charge_pos", "prepareCruiseTask", "path_id", "stayPoints", "prepareDeliverTask", "goal_dir_property", "precise_flag", "prepareGoHomeTask", "prepareLeaveChargeTask", "prepareMoveToChargeTask", "prepareRecoverTask", "resetChargeTimes", "resetNavigationFlag", "rgbdParkingPreparation", "final_goal", "rotate", "Lcom/pudutech/mirsdk/mircore/coreparcel/RotateResult;", "goal_direction", "safelyStop", "sendTaskPlannerParam", "speedMode", "setMovingSpeedTask", "setReflectBrakeFlag", "flag", "setReplanWaitTime", "wait_time", "setRoadblockTimeout", "time_out", "startNavigation", "listener", "Lcom/pudutech/mirsdk/mircore/NavigationResultListener;", "switchSpeedLevel", "level", "updateCostmapStatus", "status", "updateDirection", MapElement.Key.DIR, "updateDirection$mircore_packRelease", "updateDynamicConfig", TransferTable.COLUMN_KEY, "updateGateLimitSpeed", "speed", "updatePose", "pose", "updatePose$mircore_packRelease", "updateRobotOdom", "odom", "updateSmoothMode", "updateSpeeds", "speeds", "updateSpeeds$mircore_packRelease", "updateSteadyFlag", "updateTrayDis", "tray_dis", "uploadCliffCenterIr", "mircore_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class NavigationStub extends NavigationInterface.Stub {
    private int after_emergence_stop_count;
    private double begin_radian;
    private double charge_dist_offset;
    private boolean deceleration_complete;
    private boolean deliverStart;
    private boolean emergence_stop;
    private boolean featDynRoadblock;
    private boolean featStuckReplan;
    private boolean finishArrived;
    private boolean goalDirProperty;
    private double goal_radian;
    private boolean haveSetDock;
    private boolean have_file;
    private int last_cruise_path_id;
    private List<String> last_cruise_stay_points;
    private boolean locUseRGBD;
    private boolean preciseFlag;
    private boolean reflectorBrakeFlag;
    private double remain_radian;
    private boolean rgbdPark;
    private int rotate_PI_id;
    private int rotate_PI_num;
    private boolean rotate_begin;
    private boolean rotate_end;
    private double rotate_radian;
    private boolean steadyFlag;
    private boolean stickToPos;
    private int stuck_count;
    private long stuck_start_time;
    private int unstuck_count;
    private boolean useCoveAround;
    private boolean useRGBD;
    private boolean virtualGoal;
    private boolean waiterFlag;
    private final String TAG = getClass().getSimpleName();
    private MoveMode moveMode = MoveMode.Direct;
    private Vector2d speedLis = new Vector2d(0.0d, 0.0d, 3, null);
    private Vector3d poseLis = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
    private Vector3d dirLis = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
    private Vector2d speedNav = new Vector2d(0.0d, 0.0d, 3, null);
    private Vector3d poseNav = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
    private Vector3d dirNav = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
    private Vector3d autoChargePose = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
    private final double closeAreaRadius = 2.0d;
    private double trayDis = -0.1d;
    private final String coverAroundStr = "cover_around";
    private final String smoothStr = "smooth_mode";
    private SmoothMode smoothMode = SmoothMode.LightLoad;
    private ArrayList<Boolean> recognizeArray = new ArrayList<>();
    private ArrayList<Vector2d> dockPosArray = new ArrayList<>();
    private LinkedList<Integer> costmapStatus = new LinkedList<>();
    private final int costmapErrorCNT = 3;
    private final double costmapOKRate = 0.75d;
    private int chargeTimes = 1;
    private ExecuteType runType = ExecuteType.Plan;
    private double origin_acc = 0.7d;
    private double origin_dec = 0.7d;
    private double origin_emergence = 1.5d;
    private double param_steady_acce = 0.35d;
    private double param_steady_dece = 0.25d;
    private double param_steady_emergence = 0.35d;
    private double steady_acc = 0.35d;
    private double steady_dec = 0.65d;
    private double steady_emergence = 0.65d;
    private int slow_cnt = 30;
    private double linear_diff_threshold = 0.3d;
    private double low_speed = 0.3d;
    private double low_angular = 0.7d;
    private double add_on = 0.65d;
    private double tmp_em_upper = 2.0d;
    private double tmp_em_upper_stop_dec = 2.0d;
    private double tmp_em_lower = 1.0d;
    private double tmp_em_lower_stop_dec = 0.8d;
    private double parking_stop_dec = 1.2d;
    private double pause_stop = 1.2d;
    private double sch_stop = 1.2d;
    private boolean open_steady = true;
    private final double PI = 3.1415926d;
    private String last_task_model = "Deliver";
    private boolean open_costmap_status_judge = true;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$10;
        public static final /* synthetic */ int[] $EnumSwitchMapping$11;
        public static final /* synthetic */ int[] $EnumSwitchMapping$12;
        public static final /* synthetic */ int[] $EnumSwitchMapping$13;
        public static final /* synthetic */ int[] $EnumSwitchMapping$14;
        public static final /* synthetic */ int[] $EnumSwitchMapping$15;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;
        public static final /* synthetic */ int[] $EnumSwitchMapping$4;
        public static final /* synthetic */ int[] $EnumSwitchMapping$5;
        public static final /* synthetic */ int[] $EnumSwitchMapping$6;
        public static final /* synthetic */ int[] $EnumSwitchMapping$7;
        public static final /* synthetic */ int[] $EnumSwitchMapping$8;
        public static final /* synthetic */ int[] $EnumSwitchMapping$9;

        static {
            $EnumSwitchMapping$0[MachineModel.Hls.ordinal()] = 1;
            $EnumSwitchMapping$0[MachineModel.Puductor.ordinal()] = 2;
            $EnumSwitchMapping$0[MachineModel.Phoenix.ordinal()] = 3;
            $EnumSwitchMapping$0[MachineModel.Peanut.ordinal()] = 4;
            $EnumSwitchMapping$0[MachineModel.RecycleDog.ordinal()] = 5;
            $EnumSwitchMapping$0[MachineModel.Ninetales.ordinal()] = 6;
            $EnumSwitchMapping$0[MachineModel.Firefox.ordinal()] = 7;
            $EnumSwitchMapping$0[MachineModel.CleanBot.ordinal()] = 8;
            $EnumSwitchMapping$0[MachineModel.BellaBot.ordinal()] = 9;
            $EnumSwitchMapping$1 = new int[MoveMode.values().length];
            $EnumSwitchMapping$1[MoveMode.Direct.ordinal()] = 1;
            $EnumSwitchMapping$1[MoveMode.Cruise.ordinal()] = 2;
            $EnumSwitchMapping$1[MoveMode.GoHome.ordinal()] = 3;
            $EnumSwitchMapping$2 = new int[MoveMode.values().length];
            $EnumSwitchMapping$2[MoveMode.Cruise.ordinal()] = 1;
            $EnumSwitchMapping$2[MoveMode.Direct.ordinal()] = 2;
            $EnumSwitchMapping$2[MoveMode.GoHome.ordinal()] = 3;
            $EnumSwitchMapping$3 = new int[ExecuteType.values().length];
            $EnumSwitchMapping$3[ExecuteType.Plan.ordinal()] = 1;
            $EnumSwitchMapping$3[ExecuteType.AutoCharge.ordinal()] = 2;
            $EnumSwitchMapping$4 = new int[NextNavigationBehavior.values().length];
            $EnumSwitchMapping$4[NextNavigationBehavior.PlanOnNormalPath.ordinal()] = 1;
            $EnumSwitchMapping$4[NextNavigationBehavior.PlanOnVirtualPath.ordinal()] = 2;
            $EnumSwitchMapping$4[NextNavigationBehavior.AssignNewPath.ordinal()] = 3;
            $EnumSwitchMapping$4[NextNavigationBehavior.AssignVirtualPath.ordinal()] = 4;
            $EnumSwitchMapping$5 = new int[ExecuteType.values().length];
            $EnumSwitchMapping$5[ExecuteType.Plan.ordinal()] = 1;
            $EnumSwitchMapping$5[ExecuteType.AutoCharge.ordinal()] = 2;
            $EnumSwitchMapping$5[ExecuteType.Recover.ordinal()] = 3;
            $EnumSwitchMapping$6 = new int[NavigationStatus.values().length];
            $EnumSwitchMapping$6[NavigationStatus.Arrived.ordinal()] = 1;
            $EnumSwitchMapping$6[NavigationStatus.Rotating.ordinal()] = 2;
            $EnumSwitchMapping$6[NavigationStatus.Finished.ordinal()] = 3;
            $EnumSwitchMapping$7 = new int[MachineModel.values().length];
            $EnumSwitchMapping$7[MachineModel.Hls.ordinal()] = 1;
            $EnumSwitchMapping$7[MachineModel.BellaBot.ordinal()] = 2;
            $EnumSwitchMapping$8 = new int[MachineModel.values().length];
            $EnumSwitchMapping$8[MachineModel.Hls.ordinal()] = 1;
            $EnumSwitchMapping$8[MachineModel.BellaBot.ordinal()] = 2;
            $EnumSwitchMapping$9 = new int[SmoothMode.values().length];
            $EnumSwitchMapping$9[SmoothMode.HeavyLoad.ordinal()] = 1;
            $EnumSwitchMapping$9[SmoothMode.LightLoad.ordinal()] = 2;
            $EnumSwitchMapping$9[SmoothMode.NoSmooth.ordinal()] = 3;
            $EnumSwitchMapping$10 = new int[MoveMode.values().length];
            $EnumSwitchMapping$10[MoveMode.Cruise.ordinal()] = 1;
            $EnumSwitchMapping$10[MoveMode.Direct.ordinal()] = 2;
            $EnumSwitchMapping$10[MoveMode.GoHome.ordinal()] = 3;
            $EnumSwitchMapping$11 = new int[ExecuteType.values().length];
            $EnumSwitchMapping$11[ExecuteType.Plan.ordinal()] = 1;
            $EnumSwitchMapping$11[ExecuteType.AutoCharge.ordinal()] = 2;
            $EnumSwitchMapping$12 = new int[NextNavigationBehavior.values().length];
            $EnumSwitchMapping$12[NextNavigationBehavior.PlanOnNormalPath.ordinal()] = 1;
            $EnumSwitchMapping$12[NextNavigationBehavior.PlanOnVirtualPath.ordinal()] = 2;
            $EnumSwitchMapping$12[NextNavigationBehavior.AssignNewPath.ordinal()] = 3;
            $EnumSwitchMapping$12[NextNavigationBehavior.AssignVirtualPath.ordinal()] = 4;
            $EnumSwitchMapping$13 = new int[ExecuteType.values().length];
            $EnumSwitchMapping$13[ExecuteType.Plan.ordinal()] = 1;
            $EnumSwitchMapping$13[ExecuteType.AutoCharge.ordinal()] = 2;
            $EnumSwitchMapping$13[ExecuteType.Recover.ordinal()] = 3;
            $EnumSwitchMapping$14 = new int[MoveMode.values().length];
            $EnumSwitchMapping$14[MoveMode.Direct.ordinal()] = 1;
            $EnumSwitchMapping$14[MoveMode.Cruise.ordinal()] = 2;
            $EnumSwitchMapping$14[MoveMode.GoHome.ordinal()] = 3;
            $EnumSwitchMapping$15 = new int[MachineModel.values().length];
            $EnumSwitchMapping$15[MachineModel.BellaBot.ordinal()] = 1;
            $EnumSwitchMapping$15[MachineModel.RecycleDog.ordinal()] = 2;
        }
    }

    public NavigationStub() {
        initOtherParamByFiles("/sdcard/pudu/config/other.cfg");
    }

    public final String getTAG() {
        return this.TAG;
    }

    public final boolean getUseRGBD() {
        return this.useRGBD;
    }

    public final void setUseRGBD(boolean z) {
        this.locUseRGBD = z;
    }

    public final void updatePose$mircore_packRelease(Vector3d pose) {
        if (pose != null) {
            synchronized (this.poseLis) {
                this.poseLis.setX(pose.getX());
                this.poseLis.setY(pose.getY());
                this.poseLis.setZ(pose.getZ());
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    public final void updateSpeeds$mircore_packRelease(Vector2d speeds) {
        if (speeds != null) {
            synchronized (this.speedLis) {
                this.speedLis.setX(speeds.getX());
                this.speedLis.setY(speeds.getY());
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    public final void updateDirection$mircore_packRelease(Vector3d dir) {
        if (dir != null) {
            synchronized (this.dirLis) {
                this.dirLis.setX(dir.getX());
                this.dirLis.setY(dir.getY());
                this.dirLis.setZ(dir.getZ());
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    public final double normalizeRadian(double radian) {
        while (true) {
            double d = this.PI;
            if (radian >= (-d)) {
                break;
            }
            radian += (radian < (-2) * d ? ((int) (radian / (r4 * d))) * 2 : 2) * d;
        }
        while (true) {
            double d2 = this.PI;
            if (radian <= d2) {
                return radian;
            }
            double d3 = 2;
            if (radian > d3 * d2) {
                d3 = ((int) (radian / (d3 * d2))) * 2;
            }
            radian -= d3 * d2;
        }
    }

    public final void confirmAccParam(MachineModel machineType) {
        Intrinsics.checkParameterIsNotNull(machineType, "machineType");
        switch (machineType) {
            case Hls:
                this.origin_acc = 0.7d;
                this.origin_dec = 0.7d;
                this.origin_emergence = 1.5d;
                this.pause_stop = 0.85d;
                this.sch_stop = 0.85d;
                break;
            case Puductor:
            case Phoenix:
            case Peanut:
                this.origin_acc = 0.7d;
                this.origin_dec = 0.7d;
                this.origin_emergence = 1.5d;
                this.pause_stop = 1.2d;
                this.sch_stop = 1.2d;
                break;
            case RecycleDog:
            case Ninetales:
            case Firefox:
            case CleanBot:
                this.origin_acc = 0.7d;
                this.origin_dec = 0.7d;
                this.origin_emergence = 1.0d;
                this.pause_stop = 0.65d;
                this.sch_stop = 0.65d;
                break;
            case BellaBot:
                this.origin_acc = 0.7d;
                this.origin_dec = 0.7d;
                this.origin_emergence = 1.0d;
                this.pause_stop = 0.65d;
                this.sch_stop = 0.65d;
                Pdlog.m3273d(this.TAG, "confirmAccParam of BellaBot");
                break;
        }
        initParamByFiles("/sdcard/pudu/config/steady_test.cfg");
    }

    public final void prepareRecoverTask(double radian) {
        this.chargeTimes = 0;
        this.moveMode = MoveMode.Direct;
        this.stickToPos = false;
        this.waiterFlag = false;
        this.rotate_radian = radian;
        this.runType = ExecuteType.Recover;
        PlannerParamUtils.INSTANCE.sendP2PParams();
        this.deliverStart = false;
    }

    @Override // com.pudutech.mirsdk.mircore.NavigationInterface
    public void prepareCruiseTask(int path_id, List<String> stayPoints) {
        this.chargeTimes = 0;
        this.moveMode = MoveMode.Cruise;
        this.runType = ExecuteType.Plan;
        this.stickToPos = false;
        PlannerParamUtils.INSTANCE.sendCruiseParams();
        this.last_cruise_path_id = path_id;
        if (stayPoints != null) {
            this.last_cruise_stay_points = stayPoints;
        } else {
            this.last_cruise_stay_points = (List) null;
        }
        List<String> list = stayPoints;
        if (list == null || list.isEmpty()) {
            this.waiterFlag = false;
            ScheduleMaster.INSTANCE.setCirclePath(path_id);
        } else {
            this.waiterFlag = true;
            ScheduleMaster.INSTANCE.setCirclePathWithWaiterPoints(path_id, stayPoints);
        }
    }

    @Override // com.pudutech.mirsdk.mircore.NavigationInterface
    public boolean prepareDeliverTask(boolean goal_dir_property, boolean precise_flag) {
        this.chargeTimes = 0;
        this.moveMode = MoveMode.Direct;
        this.stickToPos = false;
        this.waiterFlag = false;
        this.runType = ExecuteType.Plan;
        PlannerParamUtils.INSTANCE.sendP2PParams();
        this.goalDirProperty = goal_dir_property;
        this.preciseFlag = precise_flag;
        this.deliverStart = false;
        return true;
    }

    @Override // com.pudutech.mirsdk.mircore.NavigationInterface
    public boolean prepareGoHomeTask() {
        this.chargeTimes = 0;
        this.moveMode = MoveMode.GoHome;
        this.stickToPos = false;
        this.waiterFlag = false;
        this.runType = ExecuteType.Plan;
        PlannerParamUtils.INSTANCE.sendGoHomeParams();
        this.goalDirProperty = false;
        this.deliverStart = true;
        this.preciseFlag = false;
        return true;
    }

    @Override // com.pudutech.mirsdk.mircore.NavigationInterface
    public boolean prepareMoveToChargeTask() {
        int i = this.chargeTimes;
        if (i == 0) {
            i = 1;
        }
        this.chargeTimes = i;
        Navigation.INSTANCE.updateCycleParam("{\"charge_times\":" + this.chargeTimes + '}');
        this.moveMode = MoveMode.GoHome;
        this.waiterFlag = false;
        this.stickToPos = true;
        this.runType = ExecuteType.Plan;
        PlannerParamUtils.INSTANCE.sendP2PParams();
        this.goalDirProperty = false;
        this.deliverStart = false;
        this.preciseFlag = false;
        return true;
    }

    @Override // com.pudutech.mirsdk.mircore.NavigationInterface
    public boolean prepareAutoChargeTask(Vector3d charge_pos) {
        Intrinsics.checkParameterIsNotNull(charge_pos, "charge_pos");
        this.moveMode = MoveMode.GoHome;
        Navigation.INSTANCE.updateCycleParam("{\"charge_dist_offset\":" + this.charge_dist_offset + '}');
        this.stickToPos = false;
        this.waiterFlag = false;
        this.runType = ExecuteType.AutoCharge;
        PlannerParamUtils.INSTANCE.sendGoHomeParams();
        this.goalDirProperty = false;
        this.deliverStart = true;
        this.preciseFlag = false;
        this.autoChargePose = charge_pos;
        return true;
    }

    @Override // com.pudutech.mirsdk.mircore.NavigationInterface
    public boolean prepareLeaveChargeTask() {
        Pdlog.m3273d(this.TAG, "before Navigation.disengageChargingPile !!!");
        Navigation.INSTANCE.disengageChargingPile(true);
        int i = this.chargeTimes;
        if (i == 0) {
            i = 1;
        }
        this.chargeTimes = i;
        Navigation.INSTANCE.updateCycleParam("{\"charge_times\":" + this.chargeTimes + '}');
        this.moveMode = MoveMode.GoHome;
        this.waiterFlag = false;
        this.stickToPos = true;
        this.runType = ExecuteType.Plan;
        PlannerParamUtils.INSTANCE.sendP2PParams();
        this.goalDirProperty = false;
        this.deliverStart = false;
        Pdlog.m3273d(this.TAG, "after Navigation.disengageChargingPile !!!");
        return true;
    }

    @Override // com.pudutech.mirsdk.mircore.NavigationInterface
    public boolean setMovingSpeedTask(double p0) {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }

    @Override // com.pudutech.mirsdk.mircore.NavigationInterface
    public void resetNavigationFlag() {
        if (this.haveSetDock) {
            Perception.INSTANCE.setAutoDockSwitch(true);
            this.haveSetDock = false;
        }
        Navigation.INSTANCE.resetNavigationFlag();
    }

    @Override // com.pudutech.mirsdk.mircore.NavigationInterface
    public void updateDynamicConfig(boolean key) {
        Navigation.INSTANCE.updateDynamicConfig(key);
    }

    @Override // com.pudutech.mirsdk.mircore.NavigationInterface
    public boolean switchSpeedLevel(MoveMode mode, String level) {
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        Intrinsics.checkParameterIsNotNull(level, "level");
        return PlannerParamUtils.INSTANCE.switchPlannerLevel(mode, level);
    }

    @Override // com.pudutech.mirsdk.mircore.NavigationInterface
    public String[] getSpeedLevels() {
        ArrayList<Double> maxSpeedArray = PlannerParamUtils.INSTANCE.getMaxSpeedArray();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(maxSpeedArray, 10));
        Iterator<T> it = maxSpeedArray.iterator();
        while (it.hasNext()) {
            arrayList.add(String.valueOf(((Number) it.next()).doubleValue()));
        }
        Object[] array = arrayList.toArray(new String[0]);
        if (array != null) {
            return (String[]) array;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    @Override // com.pudutech.mirsdk.mircore.NavigationInterface
    public String getSpeedLevel(MoveMode p0) {
        if (p0 != null) {
            int i = WhenMappings.$EnumSwitchMapping$1[p0.ordinal()];
            if (i == 1) {
                return PlannerParamUtils.INSTANCE.getDeliverLevelMaxSpeed();
            }
            if (i == 2) {
                return PlannerParamUtils.INSTANCE.getCruiseLevelMaxSpeed();
            }
            if (i == 3) {
                return PlannerParamUtils.INSTANCE.getGoHomeLevelMaxSpeed();
            }
        }
        return PlannerParamUtils.INSTANCE.getDeliverLevelMaxSpeed();
    }

    @Override // com.pudutech.mirsdk.mircore.NavigationInterface
    public NavigationResult navigation(NavigationMode mode, boolean alignGoal) {
        NavigationResult planOnNormalPathBehavior;
        double d;
        double d2;
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        synchronized (this.speedLis) {
            this.speedNav.setX(this.speedLis.getX());
            this.speedNav.setY(this.speedLis.getY());
            Unit unit = Unit.INSTANCE;
        }
        synchronized (this.poseLis) {
            this.poseNav.setX(this.poseLis.getX());
            this.poseNav.setY(this.poseLis.getY());
            this.poseNav.setZ(this.poseLis.getZ());
            Unit unit2 = Unit.INSTANCE;
        }
        synchronized (this.dirLis) {
            this.dirNav.setX(this.dirLis.getX());
            this.dirNav.setY(this.dirLis.getY());
            this.dirNav.setZ(this.dirLis.getZ());
            Unit unit3 = Unit.INSTANCE;
        }
        Pdlog.m3273d(this.TAG, "tangential speed " + this.speedNav.getX() + ", axial speed " + this.speedNav.getY() + ", dir " + this.dirNav.getX() + ", " + this.dirNav.getY() + ", pose " + this.poseNav.getX() + ", " + this.poseNav.getY());
        if (!this.rotate_begin && MirCoreImpl.INSTANCE.getLocalization().isLocalizationFinishInitialization()) {
            this.rotate_radian = MirCoreImpl.INSTANCE.getLocalization().getRotatePlanning();
            if (Math.abs(this.rotate_radian) > 0.1d) {
                int i = WhenMappings.$EnumSwitchMapping$3[this.runType.ordinal()];
                if (i == 1) {
                    int i2 = WhenMappings.$EnumSwitchMapping$2[this.moveMode.ordinal()];
                    if (i2 == 1) {
                        this.last_task_model = "Cruise";
                    } else if (i2 == 2) {
                        this.last_task_model = "Direct";
                    } else if (i2 == 3) {
                        if (this.stickToPos) {
                            this.last_task_model = "MoveToCharge";
                        } else {
                            this.last_task_model = "GoHome";
                        }
                    }
                    prepareRecoverTask(this.rotate_radian);
                } else if (i == 2) {
                    this.last_task_model = "AutoCharge";
                }
            }
        }
        if (this.rotate_begin) {
            prepareRecoverTask(this.rotate_radian);
        }
        if (this.runType == ExecuteType.Recover && MirCoreImpl.INSTANCE.getLocalization().getLocalizationStatus().getStatus_level() == LocalizationStatusLevel.Normal) {
            String str = this.last_task_model;
            switch (str.hashCode()) {
                case 1141148003:
                    if (str.equals("AutoCharge")) {
                        prepareAutoChargeTask(this.autoChargePose);
                        break;
                    }
                    break;
                case 1986308128:
                    if (str.equals("MoveToCharge")) {
                        prepareMoveToChargeTask();
                        break;
                    }
                    break;
                case 2027024629:
                    if (str.equals("Cruise")) {
                        prepareCruiseTask(this.last_cruise_path_id, this.last_cruise_stay_points);
                        break;
                    }
                    break;
                case 2047248393:
                    if (str.equals("Direct")) {
                        prepareDeliverTask(this.goalDirProperty, this.preciseFlag);
                        break;
                    }
                    break;
                case 2137435655:
                    if (str.equals("GoHome")) {
                        prepareGoHomeTask();
                        break;
                    }
                    break;
            }
            this.rotate_begin = false;
            this.rotate_end = false;
            MirCoreImpl.INSTANCE.getLocalization().setRotateEnd(this.rotate_end);
            this.deceleration_complete = false;
        }
        if (!judgeCostmapStautus()) {
            Pdlog.m3274e(this.TAG, "costmap status error, just stop");
            if (MirCoreImpl.INSTANCE.getLocalization().isLocalizationFinishInitialization()) {
                MirCoreImpl.INSTANCE.getLocalization().setTaskStatus(true);
            }
            return new NavigationResult(0.0d, 0.0d, -1.0d, NavigationStatus.Stuck);
        }
        Navigation.INSTANCE.fetchDynamicConfig();
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = Perception.INSTANCE.getCliffInfo();
        MirCoreImpl.INSTANCE.getCliffDisListener$mircore_packRelease().notify(new Function2<CliffInfoListener, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.module.NavigationStub$navigation$4
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CliffInfoListener cliffInfoListener, String str2) {
                invoke2(cliffInfoListener, str2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(CliffInfoListener it, String str2) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                it.cliffDistance(Ref.IntRef.this.element);
            }
        });
        boolean preScheduleCheckReplanVel = ScheduleMaster.INSTANCE.preScheduleCheckReplanVel(this.poseNav.getX(), this.poseNav.getY(), this.moveMode.getValue(), this.speedNav.getX(), this.speedNav.getY());
        long[] costmapAddress = Perception.INSTANCE.getCostmapAddress(this.poseNav.getX(), this.poseNav.getY());
        long[] dynamicAddress = Perception.INSTANCE.getDynamicAddress();
        long[] jArr = {costmapAddress[0], costmapAddress[1], dynamicAddress[0], dynamicAddress[1], Perception.INSTANCE.getMarkerAddress()};
        Pdlog.m3273d(this.TAG, "current runType is " + this.runType.toString());
        int i3 = WhenMappings.$EnumSwitchMapping$5[this.runType.ordinal()];
        if (i3 == 1) {
            this.rotate_begin = false;
            this.rotate_end = false;
            MirCoreImpl.INSTANCE.getLocalization().setRotateEnd(this.rotate_end);
            this.deceleration_complete = false;
            if (preScheduleCheckReplanVel) {
                this.stuck_count = 0;
                clearActionStatus();
                Vector3d finalGoal = ScheduleMaster.INSTANCE.getFinalGoal();
                this.virtualGoal = !this.waiterFlag && this.moveMode == MoveMode.Cruise;
                Pdlog.m3273d(this.TAG, "restPath for reset_plan " + this.goalDirProperty + ' ' + this.deliverStart + ' ' + this.virtualGoal);
                Navigation.INSTANCE.resetPath(costmapAddress[0], ScheduleMaster.INSTANCE.getPathPointAddress(), finalGoal.getX(), finalGoal.getY(), finalGoal.getZ(), this.goalDirProperty, this.deliverStart, this.virtualGoal, ScheduleMaster.INSTANCE.getGlobalPathAddress());
                Navigation navigation = Navigation.INSTANCE;
                StringBuilder sb = new StringBuilder();
                sb.append("{\"precise_flag\": ");
                sb.append(this.preciseFlag);
                sb.append('}');
                navigation.updateCycleParam(sb.toString());
            }
            int i4 = WhenMappings.$EnumSwitchMapping$4[ScheduleMaster.INSTANCE.checkScheduleForNavigation(this.moveMode.getValue()).ordinal()];
            if (i4 == 1) {
                planOnNormalPathBehavior = planOnNormalPathBehavior(jArr, mode, alignGoal, this.stickToPos);
            } else if (i4 == 2) {
                planOnNormalPathBehavior = planOnVirtualPathBehavior(jArr, mode, alignGoal);
            } else if (i4 == 3) {
                this.stuck_count = 0;
                planOnNormalPathBehavior = assignReplanBehavior(jArr);
            } else if (i4 == 4) {
                this.stuck_count = 0;
                planOnNormalPathBehavior = assignVirtualPlanBehavior(jArr);
            } else {
                planOnNormalPathBehavior = avoidWaitBehavior();
            }
            if (MirCoreImpl.INSTANCE.getLocalization().isLocalizationFinishInitialization()) {
                if (planOnNormalPathBehavior.getNavigation_status() == NavigationStatus.Stuck) {
                    MirCoreImpl.INSTANCE.getLocalization().setTaskStatus(true);
                } else {
                    MirCoreImpl.INSTANCE.getLocalization().setTaskStatus(false);
                }
            }
            return planOnNormalPathBehavior;
        }
        if (i3 == 2) {
            this.rotate_begin = false;
            this.rotate_end = false;
            MirCoreImpl.INSTANCE.getLocalization().setRotateEnd(this.rotate_end);
            this.deceleration_complete = false;
            NavigationResult autoChargeBehavior = autoChargeBehavior(jArr);
            if ((autoChargeBehavior.getNavigation_status() == NavigationStatus.FailTrack && autoChargeBehavior.getNavigation_status() == NavigationStatus.FailStuck) || autoChargeBehavior.getNavigation_status() == NavigationStatus.FailOverTime) {
                this.chargeTimes++;
                Perception.INSTANCE.setAutoDockSwitch(false);
            }
            if (MirCoreImpl.INSTANCE.getLocalization().isLocalizationFinishInitialization()) {
                if (autoChargeBehavior.getNavigation_status() == NavigationStatus.Stuck) {
                    MirCoreImpl.INSTANCE.getLocalization().setTaskStatus(true);
                } else {
                    MirCoreImpl.INSTANCE.getLocalization().setTaskStatus(false);
                }
            }
            return autoChargeBehavior;
        }
        if (i3 == 3) {
            if (this.rotate_end) {
                MirCoreImpl.INSTANCE.getLocalization().setRotateEnd(this.rotate_end);
                return new NavigationResult(0.0d, 0.0d, 1.0d, NavigationStatus.Navigating);
            }
            if (!this.rotate_begin) {
                this.begin_radian = Math.atan2(this.dirLis.getY(), this.dirLis.getX());
                this.goal_radian = this.begin_radian;
                this.rotate_PI_id = 0;
                double d3 = this.rotate_radian;
                if (d3 > 0) {
                    double d4 = this.PI;
                    this.rotate_PI_num = (int) (d3 / d4);
                    this.remain_radian = d3 - (this.rotate_PI_num * d4);
                } else {
                    double d5 = this.PI;
                    this.rotate_PI_num = (int) (d3 / (-d5));
                    this.remain_radian = d3 + (this.rotate_PI_num * d5);
                }
                this.rotate_begin = true;
            }
            if (!this.deceleration_complete) {
                double x = this.speedLis.getX() * 0.8d;
                double y = this.speedLis.getY() * 0.8d;
                if (Math.abs(y) >= 0.4d || Math.abs(y) >= 0.5d) {
                    d = x;
                    d2 = y;
                } else {
                    this.deceleration_complete = true;
                    d = 0.0d;
                    d2 = 0.0d;
                }
                return new NavigationResult(d, d2, 1.0d, NavigationStatus.Rotating);
            }
            double d6 = 0;
            double d7 = this.rotate_radian < d6 ? 0.5d * (-1) : 0.5d;
            if (this.rotate_PI_id < this.rotate_PI_num) {
                double atan2 = Math.atan2(this.dirLis.getY(), this.dirLis.getX());
                if (this.rotate_radian > d6) {
                    this.goal_radian = normalizeRadian(this.begin_radian + ((this.rotate_PI_id + 1) * this.PI));
                } else {
                    this.goal_radian = normalizeRadian(this.begin_radian - ((this.rotate_PI_id + 1) * this.PI));
                }
                if (Math.abs(normalizeRadian(atan2 - this.goal_radian)) < 0.1d) {
                    this.rotate_PI_id++;
                }
                return new NavigationResult(0.0d, d7, 1.0d, NavigationStatus.Rotating);
            }
            if (Math.abs(this.remain_radian) < 0.2d) {
                this.rotate_end = true;
                MirCoreImpl.INSTANCE.getLocalization().setRotateEnd(this.rotate_end);
                return new NavigationResult(0.0d, 0.0d, 1.0d, NavigationStatus.Navigating);
            }
            this.goal_radian = normalizeRadian(this.begin_radian + this.rotate_radian);
            if (Math.abs(normalizeRadian(Math.atan2(this.dirLis.getY(), this.dirLis.getX()) - this.goal_radian)) < 0.1d) {
                this.rotate_end = true;
                MirCoreImpl.INSTANCE.getLocalization().setRotateEnd(this.rotate_end);
                return new NavigationResult(0.0d, 0.0d, 1.0d, NavigationStatus.Navigating);
            }
            return new NavigationResult(0.0d, d7, 1.0d, NavigationStatus.Rotating);
        }
        throw new NoWhenBranchMatchedException();
    }

    @Override // com.pudutech.mirsdk.mircore.NavigationInterface
    public NavigationResult safelyStop() {
        if (this.reflectorBrakeFlag) {
            MirCoreImpl.INSTANCE.setAccParam(AccelerationType.EmergencyStopDeceleration, this.origin_emergence);
            if (MirCoreImpl.INSTANCE.getSpeeds$mircore_packRelease().getX() < 0.05d && MirCoreImpl.INSTANCE.getSpeeds$mircore_packRelease().getY() < 0.05d) {
                this.reflectorBrakeFlag = false;
            }
        } else if (this.steadyFlag) {
            MirCoreImpl.INSTANCE.setAccParam(AccelerationType.EmergencyStopDeceleration, this.param_steady_emergence);
        } else {
            MirCoreImpl.INSTANCE.setAccParam(AccelerationType.EmergencyStopDeceleration, this.pause_stop);
        }
        return new NavigationResult(0.0d, 0.0d, 0.0d, NavigationStatus.Navigating);
    }

    @Override // com.pudutech.mirsdk.mircore.NavigationInterface
    public Vector3d getAimPoint() {
        return new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
    }

    @Override // com.pudutech.mirsdk.mircore.NavigationInterface
    public RotateResult rotate(Vector3d goal_direction) {
        Intrinsics.checkParameterIsNotNull(goal_direction, "goal_direction");
        synchronized (this.speedLis) {
            this.speedNav.setX(this.speedLis.getX());
            this.speedNav.setY(this.speedLis.getY());
            Unit unit = Unit.INSTANCE;
        }
        synchronized (this.poseLis) {
            this.poseNav.setX(this.poseLis.getX());
            this.poseNav.setY(this.poseLis.getY());
            this.poseNav.setZ(this.poseLis.getZ());
            Unit unit2 = Unit.INSTANCE;
        }
        synchronized (this.dirLis) {
            this.dirNav.setX(this.dirLis.getX());
            this.dirNav.setY(this.dirLis.getY());
            this.dirNav.setZ(this.dirLis.getZ());
            Unit unit3 = Unit.INSTANCE;
        }
        return Navigation.INSTANCE.rotate(Perception.INSTANCE.getCostmapAddress(this.poseNav.getX(), this.poseNav.getY())[0], this.poseNav.getX(), this.poseNav.getY(), this.dirNav.getX(), this.dirNav.getY(), goal_direction.getX(), goal_direction.getY(), this.speedNav.getY());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final NavigationResult planOnNormalPathBehavior(long[] perception_address, NavigationMode navigation_mode, boolean alignGoal, boolean stickToPos) {
        rgbdParkingPreparation(ScheduleMaster.INSTANCE.getFinalGoal());
        Navigation.INSTANCE.updateCycleParam(CycleParamUtils.INSTANCE.buildParamStr(ScheduleMaster.INSTANCE.getInteractionMode(), navigation_mode, alignGoal, stickToPos));
        NavigationResult callLinearAngularVel = Navigation.INSTANCE.callLinearAngularVel(perception_address, this.poseNav.getX(), this.poseNav.getY(), this.dirNav.getX(), this.dirNav.getY(), this.speedNav.getX(), this.speedNav.getY(), this.moveMode.getValue());
        if (callLinearAngularVel.getNavigation_status() == NavigationStatus.Stuck && callLinearAngularVel.getCost() == -1.0d) {
            this.stuck_count++;
            this.unstuck_count = 0;
        } else if (Math.abs(callLinearAngularVel.getLinear_vel()) > 0.1d) {
            int i = this.unstuck_count;
            if (i < 2) {
                this.unstuck_count = i + 1;
            } else {
                this.unstuck_count = 0;
                this.stuck_count = 0;
            }
        }
        Pdlog.m3273d(this.TAG, " navResult stuck count = " + this.stuck_count);
        clearRgbdParkingStatus(callLinearAngularVel);
        int i2 = WhenMappings.$EnumSwitchMapping$6[callLinearAngularVel.getNavigation_status().ordinal()];
        if (i2 == 1 || i2 == 2) {
            Pdlog.m3273d(this.TAG, "UI show Approaching");
            ScheduleMaster.INSTANCE.resetScheduleMode(SchedulingMode.Arrived);
        } else if (i2 == 3) {
            if (this.moveMode == MoveMode.Cruise) {
                Pdlog.m3273d(this.TAG, "triggerCruiseWaiter for cruise waiter");
                ScheduleMaster.INSTANCE.triggerCruiseWaiter(SchedulingMode.Arrived);
            }
            Pdlog.m3273d(this.TAG, "UI show Arrived");
            ScheduleMaster.INSTANCE.resetScheduleMode(SchedulingMode.Arrived);
        }
        doSlowStart(callLinearAngularVel.getLinear_vel(), callLinearAngularVel.getAngular_vel(), callLinearAngularVel.getNavigation_status());
        return callLinearAngularVel;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final NavigationResult planOnVirtualPathBehavior(long[] perception_address, NavigationMode navigation_mode, boolean alignGoal) {
        Navigation.INSTANCE.updateCycleParam(CycleParamUtils.buildParamStr$default(CycleParamUtils.INSTANCE, ScheduleMaster.INSTANCE.getInteractionMode(), navigation_mode, alignGoal, false, 8, null));
        NavigationResult callLinearAngularVel = Navigation.INSTANCE.callLinearAngularVel(perception_address, this.poseNav.getX(), this.poseNav.getY(), this.dirNav.getX(), this.dirNav.getY(), this.speedNav.getX(), this.speedNav.getY(), this.moveMode.getValue());
        if (callLinearAngularVel.getNavigation_status() == NavigationStatus.Stuck && callLinearAngularVel.getCost() == -1.0d) {
            this.stuck_count++;
            this.unstuck_count = 0;
        } else if (Math.abs(callLinearAngularVel.getLinear_vel()) > 0.1d) {
            int i = this.unstuck_count;
            if (i < 2) {
                this.unstuck_count = i + 1;
            } else {
                this.unstuck_count = 0;
                this.stuck_count = 0;
            }
        }
        Pdlog.m3273d(this.TAG, " navResult stuck count = " + this.stuck_count);
        if (callLinearAngularVel.getNavigation_status() == NavigationStatus.Navigating || callLinearAngularVel.getNavigation_status() == NavigationStatus.Stuck) {
            callLinearAngularVel.setNavigation_status(NavigationStatus.Avoid);
        }
        doSlowStart(callLinearAngularVel.getLinear_vel(), callLinearAngularVel.getAngular_vel(), callLinearAngularVel.getNavigation_status());
        return callLinearAngularVel;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final NavigationResult assignReplanBehavior(long[] costmap_addr) {
        this.virtualGoal = !this.waiterFlag && this.moveMode == MoveMode.Cruise;
        Vector3d finalGoal = ScheduleMaster.INSTANCE.getFinalGoal();
        Pdlog.m3273d(this.TAG, "restPath for assignReplanBehavior " + this.goalDirProperty + ' ' + this.deliverStart + ' ' + this.virtualGoal);
        Navigation.INSTANCE.resetPath(costmap_addr[0], ScheduleMaster.INSTANCE.getPathPointAddress(), finalGoal.getX(), finalGoal.getY(), finalGoal.getZ(), this.goalDirProperty, this.deliverStart, this.virtualGoal, ScheduleMaster.INSTANCE.getGlobalPathAddress());
        Navigation navigation = Navigation.INSTANCE;
        StringBuilder sb = new StringBuilder();
        sb.append("{\"precise_flag\": ");
        sb.append(this.preciseFlag);
        sb.append('}');
        navigation.updateCycleParam(sb.toString());
        return new NavigationResult(0.0d, 0.0d, 1.0d, NavigationStatus.Navigating);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final NavigationResult assignVirtualPlanBehavior(long[] perception_address) {
        this.virtualGoal = true;
        Vector3d finalGoal = ScheduleMaster.INSTANCE.getFinalGoal();
        Pdlog.m3273d(this.TAG, "restPath for assignVirtualPlanBehavior " + this.goalDirProperty + ' ' + this.deliverStart + ' ' + this.virtualGoal);
        Navigation.INSTANCE.resetPath(perception_address[0], ScheduleMaster.INSTANCE.getPathPointAddress(), finalGoal.getX(), finalGoal.getY(), finalGoal.getZ(), this.goalDirProperty, this.deliverStart, this.virtualGoal, ScheduleMaster.INSTANCE.getGlobalPathAddress());
        Navigation navigation = Navigation.INSTANCE;
        StringBuilder sb = new StringBuilder();
        sb.append("{\"precise_flag\": ");
        sb.append(this.preciseFlag);
        sb.append('}');
        navigation.updateCycleParam(sb.toString());
        return new NavigationResult(0.0d, 0.0d, 1.0d, NavigationStatus.Avoid);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final NavigationResult avoidWaitBehavior() {
        doSlowStart(0.0d, 0.0d, NavigationStatus.Avoid);
        return new NavigationResult(0.0d, 0.0d, 1.0d, NavigationStatus.Avoid);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final NavigationResult autoChargeBehavior(long[] perception_address) {
        autoChargePreparation(this.autoChargePose);
        return Navigation.INSTANCE.callLinearAngularVel(perception_address, this.poseNav.getX(), this.poseNav.getY(), this.dirNav.getX(), this.dirNav.getY(), this.speedNav.getX(), this.speedNav.getY(), this.moveMode.getValue());
    }

    private final void doSlowStart(double plan_linear_vel, double plan_angular_vel, NavigationStatus navigation_status) {
        double d;
        double d2;
        if ((!this.have_file || this.open_steady) && !this.steadyFlag) {
            if (this.smoothMode == SmoothMode.NoSmooth && (MirCoreImpl.INSTANCE.getMachineType() == MachineModel.Hls || MirCoreImpl.INSTANCE.getMachineType() == MachineModel.BellaBot)) {
                MirCoreImpl.INSTANCE.setAccParam(AccelerationType.Acceleration, this.origin_acc);
                MirCoreImpl.INSTANCE.setAccParam(AccelerationType.Deceleration, this.origin_dec);
                MirCoreImpl.INSTANCE.setAccParam(AccelerationType.EmergencyStopDeceleration, this.origin_emergence);
                return;
            }
            if (MirCoreImpl.INSTANCE.getMachineType() == MachineModel.Hls || MirCoreImpl.INSTANCE.getMachineType() == MachineModel.BellaBot) {
                if (this.have_file) {
                    Pdlog.m3273d(this.TAG, "have steady test param file, just use file param");
                } else {
                    int i = WhenMappings.$EnumSwitchMapping$9[this.smoothMode.ordinal()];
                    if (i == 1) {
                        this.slow_cnt = 40;
                        int i2 = WhenMappings.$EnumSwitchMapping$7[MirCoreImpl.INSTANCE.getMachineType().ordinal()];
                        if (i2 == 1) {
                            this.parking_stop_dec = 1.1d;
                            this.tmp_em_lower_stop_dec = 0.8d;
                        } else if (i2 == 2) {
                            this.parking_stop_dec = 0.75d;
                            this.tmp_em_lower_stop_dec = 0.6d;
                        }
                    } else if (i == 2) {
                        this.slow_cnt = 30;
                        int i3 = WhenMappings.$EnumSwitchMapping$8[MirCoreImpl.INSTANCE.getMachineType().ordinal()];
                        if (i3 == 1) {
                            this.parking_stop_dec = 1.2d;
                            this.tmp_em_lower_stop_dec = 0.9d;
                        } else if (i3 == 2) {
                            this.parking_stop_dec = 0.85d;
                            this.tmp_em_lower_stop_dec = 0.7d;
                        }
                    }
                }
                double x = this.speedLis.getX();
                this.speedLis.getY();
                double d3 = plan_linear_vel - x;
                if (this.emergence_stop) {
                    int i4 = this.after_emergence_stop_count;
                    if (i4 < this.slow_cnt) {
                        this.after_emergence_stop_count = i4 + 1;
                    } else {
                        this.emergence_stop = false;
                    }
                }
                if (x < 0 && plan_linear_vel == 0.0d) {
                    MirCoreImpl.INSTANCE.setAccParam(AccelerationType.Acceleration, this.origin_acc);
                    MirCoreImpl.INSTANCE.setAccParam(AccelerationType.Deceleration, this.origin_dec);
                    MirCoreImpl.INSTANCE.setAccParam(AccelerationType.EmergencyStopDeceleration, this.origin_emergence);
                } else if (this.emergence_stop || ((x < this.low_speed && Math.abs(plan_angular_vel) < this.low_angular) || d3 > this.linear_diff_threshold)) {
                    boolean z = this.emergence_stop;
                    double d4 = 0.5d;
                    if (z) {
                        boolean z2 = ((double) this.after_emergence_stop_count) / ((double) this.slow_cnt) < 0.5d;
                        if (z2) {
                            d = 0.5d;
                        } else {
                            if (z2) {
                                throw new NoWhenBranchMatchedException();
                            }
                            d = this.after_emergence_stop_count / this.slow_cnt;
                        }
                    } else {
                        if (z) {
                            throw new NoWhenBranchMatchedException();
                        }
                        d = 1.0d;
                    }
                    boolean z3 = x < this.low_speed && !this.emergence_stop;
                    if (z3) {
                        boolean z4 = x / this.low_speed < 0.5d;
                        if (!z4) {
                            if (z4) {
                                throw new NoWhenBranchMatchedException();
                            }
                            d4 = x / this.low_speed;
                        }
                    } else {
                        if (z3) {
                            throw new NoWhenBranchMatchedException();
                        }
                        d4 = 1.0d;
                    }
                    MirCoreImpl.INSTANCE.setAccParam(AccelerationType.Acceleration, this.steady_acc * d * d4);
                    MirCoreImpl.INSTANCE.setAccParam(AccelerationType.Deceleration, this.steady_dec);
                    MirCoreImpl.INSTANCE.setAccParam(AccelerationType.EmergencyStopDeceleration, this.steady_emergence);
                } else {
                    MirCoreImpl.INSTANCE.setAccParam(AccelerationType.Acceleration, this.origin_acc);
                    MirCoreImpl.INSTANCE.setAccParam(AccelerationType.Deceleration, this.origin_dec);
                    MirCoreImpl.INSTANCE.setAccParam(AccelerationType.EmergencyStopDeceleration, this.origin_emergence);
                }
                if (plan_linear_vel == 0.0d && plan_angular_vel == 0.0d) {
                    this.emergence_stop = true;
                    this.after_emergence_stop_count = 0;
                    double abs = Math.abs(x) + this.add_on;
                    if (navigation_status == NavigationStatus.Arrived && this.deliverStart) {
                        d2 = this.parking_stop_dec;
                    } else if (abs > this.tmp_em_upper) {
                        d2 = this.tmp_em_upper_stop_dec;
                    } else if (abs < this.tmp_em_lower) {
                        d2 = this.tmp_em_lower_stop_dec;
                    } else {
                        d2 = this.parking_stop_dec;
                    }
                    MirCoreImpl.INSTANCE.setAccParam(AccelerationType.EmergencyStopDeceleration, d2);
                }
                if (navigation_status == NavigationStatus.Avoid) {
                    MirCoreImpl.INSTANCE.setAccParam(AccelerationType.EmergencyStopDeceleration, this.sch_stop);
                }
            }
        }
    }

    @Override // com.pudutech.mirsdk.mircore.NavigationInterface
    public void updateSteadyFlag(boolean p0) {
        Pdlog.m3273d(this.TAG, "update steady " + p0);
        this.steadyFlag = p0;
        boolean z = this.steadyFlag;
        if (z) {
            MirCoreImpl.INSTANCE.setAccParam(AccelerationType.Acceleration, this.param_steady_acce);
            MirCoreImpl.INSTANCE.setAccParam(AccelerationType.Deceleration, this.param_steady_dece);
            MirCoreImpl.INSTANCE.setAccParam(AccelerationType.EmergencyStopDeceleration, this.param_steady_emergence);
        } else if (!z) {
            MirCoreImpl.INSTANCE.setAccParam(AccelerationType.Acceleration, this.origin_acc);
            MirCoreImpl.INSTANCE.setAccParam(AccelerationType.Deceleration, this.origin_dec);
            MirCoreImpl.INSTANCE.setAccParam(AccelerationType.EmergencyStopDeceleration, this.origin_emergence);
        }
        if (this.steadyFlag) {
            PlannerParamUtils.INSTANCE.sendSteadyParams(this.moveMode);
        }
    }

    @Override // com.pudutech.mirsdk.mircore.NavigationInterface
    public boolean addCoverAround(boolean p0) {
        RunParamUtils.INSTANCE.writeRunParam("cover_around", p0);
        if (this.useCoveAround == p0) {
            return true;
        }
        Pdlog.m3273d(this.TAG, "call updateCoverAround with " + p0);
        this.useCoveAround = p0;
        if (MirCoreImpl.INSTANCE.getMachineType() == MachineModel.Hls || MirCoreImpl.INSTANCE.getMachineType() == MachineModel.BellaBot) {
            Pdlog.m3273d(this.TAG, "HLS/Bella machine update footprint");
            Navigation.INSTANCE.updateFootPrint(this.useCoveAround);
        }
        return true;
    }

    @Override // com.pudutech.mirsdk.mircore.NavigationInterface
    public boolean getCoverAround() {
        return RunParamUtils.INSTANCE.getInitRunParam("cover_around");
    }

    @Override // com.pudutech.mirsdk.mircore.NavigationInterface
    public SmoothMode getSmoothMode() {
        return RunParamUtils.INSTANCE.getInitSmoothMode(MirCoreImpl.INSTANCE.getMachineType());
    }

    @Override // com.pudutech.mirsdk.mircore.NavigationInterface
    public void updateSmoothMode(SmoothMode p0) {
        Intrinsics.checkParameterIsNotNull(p0, "p0");
        MirCoreImpl.INSTANCE.setSmoothMode(p0);
        this.smoothMode = p0;
        Pdlog.m3273d(this.TAG, "update smoothmode to " + this.smoothMode);
        RunParamUtils.INSTANCE.writeRunParam(this.smoothStr, this.smoothMode.name());
    }

    @Override // com.pudutech.mirsdk.mircore.NavigationInterface
    public void enableDynamicRoadblock(boolean enable) {
        Pdlog.m3273d(this.TAG, "roadblock enabled: " + enable);
        if (enable != this.featDynRoadblock) {
            ScheduleMaster.INSTANCE.setRoadblockInFront(false);
        }
        this.featDynRoadblock = enable;
    }

    @Override // com.pudutech.mirsdk.mircore.NavigationInterface
    public void enableStuckReplan(boolean enable) {
        Pdlog.m3273d(this.TAG, "stuckreplan enabled: " + enable);
        if (enable != this.featStuckReplan) {
            ScheduleMaster.INSTANCE.setRoadblockInFront(false);
        }
        this.featStuckReplan = enable;
    }

    @Override // com.pudutech.mirsdk.mircore.NavigationInterface
    public int getReplanWaitTime() {
        return MirCoreImpl.INSTANCE.getReplanWaitTime();
    }

    @Override // com.pudutech.mirsdk.mircore.NavigationInterface
    public boolean setReplanWaitTime(int wait_time) {
        if (wait_time < 5 || wait_time > 3600) {
            return false;
        }
        Pdlog.m3273d(this.TAG, "update replan wait time to " + wait_time);
        MirCoreImpl.INSTANCE.setReplanWaitTime(wait_time);
        return true;
    }

    @Override // com.pudutech.mirsdk.mircore.NavigationInterface
    public int getRoadblockTimeout() {
        return MirCoreImpl.INSTANCE.getRoadblockClearTime();
    }

    @Override // com.pudutech.mirsdk.mircore.NavigationInterface
    public boolean setRoadblockTimeout(int time_out) {
        if (time_out < 1 || time_out > 3600) {
            return false;
        }
        Pdlog.m3273d(this.TAG, "update roadblock clear time to " + time_out);
        MirCoreImpl.INSTANCE.setRoadblockClearTime(time_out);
        String str = "{ \"roadblock_timeout\": " + (time_out * 1000) + " }";
        if (ScheduleMaster.INSTANCE.setPlannerParam(str)) {
            return true;
        }
        Pdlog.m3273d(this.TAG, "failed to set param to ScheduleMaster: " + str);
        return false;
    }

    public final void updateCostmapStatus(int status) {
        synchronized (this.costmapStatus) {
            boolean z = this.costmapStatus.size() >= 30;
            if (!z) {
                this.costmapStatus.add(Integer.valueOf(status));
            } else if (z) {
                this.costmapStatus.poll();
                this.costmapStatus.add(Integer.valueOf(status));
            } else {
                throw new NoWhenBranchMatchedException();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:44:0x0026, code lost:
    
        if (r5 < r4) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean judgeCostmapStautus() {
        boolean z = false;
        if (!this.open_costmap_status_judge) {
            Pdlog.m3275i(this.TAG, "costmap_status_judge set to close, just cheat costmap as ok");
            return true;
        }
        Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        synchronized (this.costmapStatus) {
            int size = this.costmapStatus.size();
            int i = this.costmapErrorCNT - 1;
            if (size >= 0) {
            }
            boolean z2 = CollectionsKt.sumOfInt(CollectionsKt.takeLast(this.costmapStatus, this.costmapErrorCNT)) == this.costmapErrorCNT;
            if (z2) {
                Pdlog.m3274e(this.TAG, "costmap status 0 continus for " + this.costmapErrorCNT + " frames");
            } else {
                if (z2) {
                    throw new NoWhenBranchMatchedException();
                }
                Iterator<T> it = this.costmapStatus.iterator();
                int i2 = 0;
                while (it.hasNext()) {
                    i2 += 1 - ((Number) it.next()).intValue();
                }
                double size2 = i2 / this.costmapStatus.size();
                boolean z3 = size2 >= this.costmapOKRate;
                if (z3) {
                    z = true;
                } else if (!z3) {
                    if (size2 < this.costmapOKRate * 0.5d) {
                        Pdlog.m3274e(this.TAG, "costmap ok rate extremely small, stop and throw error");
                    }
                    Pdlog.m3274e(this.TAG, "costmap ok rate too small, just stop, ele " + this.costmapStatus);
                } else {
                    throw new NoWhenBranchMatchedException();
                }
            }
            booleanRef.element = z;
            Unit unit = Unit.INSTANCE;
        }
        return booleanRef.element;
    }

    public final void updateRobotOdom(Vector3d odom) {
        Intrinsics.checkParameterIsNotNull(odom, "odom");
        synchronized (odom) {
            Navigation.INSTANCE.updateCycleParam("{\"odom\":{\"x\":" + odom.getX() + ",\"y\":" + odom.getY() + ",\"z\":" + odom.getZ() + "}}");
        }
    }

    public final void initOtherParamByFiles(String absoluteFileDir) {
        Intrinsics.checkParameterIsNotNull(absoluteFileDir, "absoluteFileDir");
        if (new File(absoluteFileDir).exists()) {
            try {
                File file = new File(absoluteFileDir);
                FileReader fileReader = new FileReader(file);
                char[] cArr = new char[(int) file.length()];
                fileReader.read(cArr);
                String str = new String(cArr);
                Pdlog.m3273d(this.TAG, absoluteFileDir + " content is " + str);
                String str2 = str;
                int length = str2.length() - 1;
                int i = 0;
                boolean z = false;
                while (i <= length) {
                    boolean z2 = str2.charAt(!z ? i : length) <= ' ';
                    if (z) {
                        if (!z2) {
                            break;
                        } else {
                            length--;
                        }
                    } else if (z2) {
                        i++;
                    } else {
                        z = true;
                    }
                }
                JSONObject jSONObject = new JSONObject(str2.subSequence(i, length + 1).toString());
                if (jSONObject.has("costmap_status_judge")) {
                    this.open_costmap_status_judge = Boolean.parseBoolean(jSONObject.get("costmap_status_judge").toString());
                    Pdlog.m3273d(this.TAG, "file costmap_status_judge is " + this.open_costmap_status_judge);
                }
                if (jSONObject.has("charge_dist_offset")) {
                    this.charge_dist_offset = Double.parseDouble(jSONObject.get("charge_dist_offset").toString());
                    Pdlog.m3273d(this.TAG, "file charge_dist_offset is " + this.charge_dist_offset);
                    return;
                }
                return;
            } catch (Exception unused) {
                Pdlog.m3273d(this.TAG, "exception: " + absoluteFileDir + " error");
                return;
            }
        }
        Pdlog.m3275i(this.TAG, "no " + absoluteFileDir);
    }

    public final void initParamByFiles(String absoluteFileDir) {
        String str;
        String str2 = "tmp_em_upper_stop_dec";
        Intrinsics.checkParameterIsNotNull(absoluteFileDir, "absoluteFileDir");
        if (new File(absoluteFileDir).exists()) {
            this.have_file = true;
            Pdlog.m3273d(this.TAG, "steady_test.cfg exists");
            try {
                File file = new File(absoluteFileDir);
                FileReader fileReader = new FileReader(file);
                char[] cArr = new char[(int) file.length()];
                fileReader.read(cArr);
                String str3 = new String(cArr);
                Pdlog.m3273d(this.TAG, "steady_test.cfg content is " + str3);
                String str4 = str3;
                int length = str4.length() - 1;
                int i = 0;
                boolean z = false;
                while (true) {
                    if (i > length) {
                        str = str2;
                        break;
                    }
                    str = str2;
                    boolean z2 = str4.charAt(!z ? i : length) <= ' ';
                    if (z) {
                        if (!z2) {
                            break;
                        } else {
                            length--;
                        }
                    } else if (z2) {
                        i++;
                    } else {
                        z = true;
                    }
                    str2 = str;
                }
                JSONObject jSONObject = new JSONObject(str4.subSequence(i, length + 1).toString());
                if (jSONObject.has("steady_acc")) {
                    this.steady_acc = Double.parseDouble(jSONObject.get("steady_acc").toString());
                    Pdlog.m3273d(this.TAG, "file steady_acc is " + this.steady_acc);
                }
                if (jSONObject.has("steady_dec")) {
                    this.steady_dec = Double.parseDouble(jSONObject.get("steady_dec").toString());
                    Pdlog.m3273d(this.TAG, "file steady_dec is " + this.steady_dec);
                }
                if (jSONObject.has("steady_emergence")) {
                    this.steady_emergence = Double.parseDouble(jSONObject.get("steady_emergence").toString());
                    Pdlog.m3273d(this.TAG, "file steady_emergence is " + this.steady_emergence);
                }
                if (jSONObject.has("slow_cnt")) {
                    this.slow_cnt = Integer.parseInt(jSONObject.get("slow_cnt").toString());
                    Pdlog.m3273d(this.TAG, "file slow_cnt is " + this.slow_cnt);
                }
                if (jSONObject.has("linear_diff_threshold")) {
                    this.linear_diff_threshold = Double.parseDouble(jSONObject.get("linear_diff_threshold").toString());
                    Pdlog.m3273d(this.TAG, "file linear_diff_threshold is " + this.linear_diff_threshold);
                }
                if (jSONObject.has("low_speed")) {
                    this.low_speed = Double.parseDouble(jSONObject.get("low_speed").toString());
                    Pdlog.m3273d(this.TAG, "file low_speed is " + this.low_speed);
                }
                if (jSONObject.has("low_angular")) {
                    this.low_angular = Double.parseDouble(jSONObject.get("low_angular").toString());
                    Pdlog.m3273d(this.TAG, "file low_angular is " + this.low_angular);
                }
                if (jSONObject.has("add_on")) {
                    this.add_on = Double.parseDouble(jSONObject.get("add_on").toString());
                    Pdlog.m3273d(this.TAG, "file add_on is " + this.add_on);
                }
                if (jSONObject.has("tmp_em_upper")) {
                    this.tmp_em_upper = Double.parseDouble(jSONObject.get("tmp_em_upper").toString());
                    Pdlog.m3273d(this.TAG, "file tmp_em_upper is " + this.tmp_em_upper);
                }
                String str5 = str;
                if (jSONObject.has(str5)) {
                    this.tmp_em_upper_stop_dec = Double.parseDouble(jSONObject.get(str5).toString());
                    Pdlog.m3273d(this.TAG, "file tmp_em_upper_stop_dec is " + this.tmp_em_upper_stop_dec);
                }
                if (jSONObject.has("tmp_em_lower")) {
                    this.tmp_em_lower = Double.parseDouble(jSONObject.get("tmp_em_lower").toString());
                    Pdlog.m3273d(this.TAG, "file tmp_em_lower is " + this.tmp_em_lower);
                }
                if (jSONObject.has("tmp_em_lower_stop_dec")) {
                    this.tmp_em_lower_stop_dec = Double.parseDouble(jSONObject.get("tmp_em_lower_stop_dec").toString());
                    Pdlog.m3273d(this.TAG, "file tmp_em_lower_stop_dec is " + this.tmp_em_lower_stop_dec);
                }
                if (jSONObject.has("origin_emergence")) {
                    this.origin_emergence = Double.parseDouble(jSONObject.get("origin_emergence").toString());
                    Pdlog.m3273d(this.TAG, "file origin_emergence is " + this.origin_emergence);
                }
                if (jSONObject.has("parking_stop_dec")) {
                    this.parking_stop_dec = Double.parseDouble(jSONObject.get("parking_stop_dec").toString());
                    Pdlog.m3273d(this.TAG, "file parking_stop_dec is " + this.parking_stop_dec);
                }
                if (jSONObject.has("pause_stop")) {
                    this.pause_stop = Double.parseDouble(jSONObject.get("pause_stop").toString());
                    Pdlog.m3273d(this.TAG, "file pause_stop is " + this.pause_stop);
                }
                if (jSONObject.has("sch_stop")) {
                    this.sch_stop = Double.parseDouble(jSONObject.get("sch_stop").toString());
                    Pdlog.m3273d(this.TAG, "file sch_stop is " + this.sch_stop);
                }
                if (jSONObject.has("open_steady")) {
                    this.open_steady = Boolean.parseBoolean(jSONObject.get("open_steady").toString());
                    Pdlog.m3273d(this.TAG, "file open_steady is " + this.open_steady);
                }
                fileReader.close();
                return;
            } catch (Exception unused) {
                Pdlog.m3273d(this.TAG, "exception: steady_test.cfg error");
                return;
            }
        }
        Pdlog.m3273d(this.TAG, "no steady_test.cfg file");
    }

    public final void rgbdParkingPreparation(Vector3d final_goal) {
        Intrinsics.checkParameterIsNotNull(final_goal, "final_goal");
        double sqrt = Math.sqrt(Math.pow(final_goal.getX() - this.poseNav.getX(), 2.0d) + Math.pow(final_goal.getY() - this.poseNav.getY(), 2.0d));
        if (this.locUseRGBD && this.moveMode != MoveMode.Cruise && !this.virtualGoal && sqrt <= this.closeAreaRadius && !this.rgbdPark && this.deliverStart) {
            String parkingMarkerID = ScheduleMaster.INSTANCE.getParkingMarkerID(final_goal.getX(), final_goal.getY());
            if (parkingMarkerID != null && StringsKt.toIntOrNull(parkingMarkerID) != null && Integer.parseInt(parkingMarkerID) != 0) {
                this.rgbdPark = true;
                MirCoreImpl.INSTANCE.setRgbdParkingMode(true);
                Perception.INSTANCE.setDockingSwitch(true, Integer.parseInt(parkingMarkerID));
                Pdlog.m3273d(this.TAG, "set setRgbdParkingMode true with marker id " + parkingMarkerID);
            } else {
                Pdlog.m3273d(this.TAG, "invalid paring marker id " + parkingMarkerID);
            }
        }
        double[] dArr = {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d};
        if (this.locUseRGBD && this.rgbdPark) {
            dArr = Perception.INSTANCE.getMarkerPose();
            double d = 0;
            this.recognizeArray.add(Boolean.valueOf(dArr[6] > d));
            if (dArr[6] > d) {
                this.dockPosArray.add(new Vector2d(dArr[3], dArr[4]));
            }
        }
        String buildMarkerPoseStr = CycleParamUtils.INSTANCE.buildMarkerPoseStr(dArr);
        if (buildMarkerPoseStr != null) {
            Navigation.INSTANCE.updateCycleParam(buildMarkerPoseStr);
        }
    }

    public final void autoChargePreparation(Vector3d auto_charge_pose) {
        Intrinsics.checkParameterIsNotNull(auto_charge_pose, "auto_charge_pose");
        if (!this.haveSetDock) {
            Perception.INSTANCE.resetAutoDockPerception();
            Perception.INSTANCE.setAutoDockSwitch(true);
            Perception.INSTANCE.setDockerPose(auto_charge_pose.getX(), auto_charge_pose.getY());
            Pdlog.m3273d(this.TAG, "open setAutoDockSwitch and setDockerPose " + auto_charge_pose.getX() + ' ' + auto_charge_pose.getY());
            this.haveSetDock = true;
        }
        double[] dockerEstimateTransform = Perception.INSTANCE.getDockerEstimateTransform();
        double sqrt = Math.sqrt(Math.pow(this.poseNav.getX() - auto_charge_pose.getX(), 2.0d) + Math.pow(this.poseNav.getY() - auto_charge_pose.getY(), 2.0d));
        double radDist = Angles.INSTANCE.radDist(Angles.INSTANCE.vector2Angle(this.dirNav.getY(), this.dirNav.getX()), Angles.INSTANCE.vector2Angle(auto_charge_pose.getY() - this.poseNav.getY(), auto_charge_pose.getX() - this.poseNav.getX()));
        double[] dArr = {1.0d, Math.cos(radDist) * sqrt, sqrt * Math.sin(radDist), Angles.INSTANCE.radDist(Angles.INSTANCE.vector2Angle(this.dirNav.getY(), this.dirNav.getX()), Angles.INSTANCE.normalizeRad(auto_charge_pose.getZ() + 3.141592653589793d))};
        Pdlog.m3273d(this.TAG, "real charge message is " + dockerEstimateTransform[1] + ", " + dockerEstimateTransform[2] + ", " + dockerEstimateTransform[3]);
        Pdlog.m3273d(this.TAG, "mock charge message is " + dArr[1] + ", " + dArr[2] + ", " + dArr[3]);
        Navigation.INSTANCE.updateCycleParam(CycleParamUtils.INSTANCE.buildAutoChargeMessageStr(true, dockerEstimateTransform));
    }

    public final void clearRgbdParkingStatus(NavigationResult navResult) {
        Intrinsics.checkParameterIsNotNull(navResult, "navResult");
        if (this.locUseRGBD && !this.finishArrived && navResult.getNavigation_status() == NavigationStatus.Rotating && this.dockPosArray.size() > 0) {
            this.finishArrived = true;
            AnalysisUtils.INSTANCE.analyzeRecognization(this.recognizeArray, this.dockPosArray);
            this.dockPosArray.clear();
            this.recognizeArray.clear();
        }
        if (this.locUseRGBD && navResult.getNavigation_status() == NavigationStatus.Finished && this.rgbdPark) {
            MirCoreImpl.INSTANCE.setRgbdParkingMode(false);
            Perception.INSTANCE.setDockingSwitch(false, 0);
            this.rgbdPark = false;
            Pdlog.m3273d(this.TAG, "set setRgbdParkingMode for finished");
        }
    }

    public final void clearActionStatus() {
        if (this.haveSetDock) {
            Pdlog.m3275i(this.TAG, "reset haveSetDock to fasle");
            this.haveSetDock = false;
            Perception.INSTANCE.setAutoDockSwitch(false);
        }
        if (this.rgbdPark && this.locUseRGBD) {
            MirCoreImpl.INSTANCE.setRgbdParkingMode(false);
            Perception.INSTANCE.setDockingSwitch(false, 0);
            this.rgbdPark = false;
            this.finishArrived = false;
            if (this.dockPosArray.size() > 0) {
                AnalysisUtils.INSTANCE.analyzeRecognization(this.recognizeArray, this.dockPosArray);
                this.recognizeArray.clear();
                this.dockPosArray.clear();
            }
        }
    }

    @Override // com.pudutech.mirsdk.mircore.NavigationInterface
    public void startNavigation(final NavigationResultListener listener, final NavigationMode mode, final boolean alignGoal) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        Perception.INSTANCE.registMapUpdatedListener("nav", new Function0<Unit>() { // from class: com.pudutech.mirsdk.mircore.module.NavigationStub$startNavigation$1
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

            /* JADX WARN: Removed duplicated region for block: B:146:0x09b1  */
            /* JADX WARN: Removed duplicated region for block: B:82:0x04de  */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final void invoke2() {
                Vector2d vector2d;
                Vector2d vector2d2;
                Vector2d vector2d3;
                Vector2d vector2d4;
                Vector2d vector2d5;
                Vector3d vector3d;
                Vector3d vector3d2;
                Vector3d vector3d3;
                Vector3d vector3d4;
                Vector3d vector3d5;
                Vector3d vector3d6;
                Vector3d vector3d7;
                Vector3d vector3d8;
                Vector3d vector3d9;
                Vector3d vector3d10;
                Vector3d vector3d11;
                Vector3d vector3d12;
                Vector3d vector3d13;
                Vector3d vector3d14;
                Vector2d vector2d6;
                Vector2d vector2d7;
                Vector3d vector3d15;
                Vector3d vector3d16;
                Vector3d vector3d17;
                Vector3d vector3d18;
                boolean z;
                boolean z2;
                ExecuteType executeType;
                boolean z3;
                boolean z4;
                Vector3d vector3d19;
                Vector3d vector3d20;
                MoveMode moveMode;
                Vector2d vector2d8;
                Vector2d vector2d9;
                Vector3d vector3d21;
                Vector3d vector3d22;
                ExecuteType executeType2;
                ExecuteType executeType3;
                int i;
                boolean z5;
                MoveMode moveMode2;
                boolean z6;
                NavigationResult planOnNormalPathBehavior;
                boolean z7;
                MoveMode moveMode3;
                boolean z8;
                boolean z9;
                boolean z10;
                boolean z11;
                boolean z12;
                boolean z13;
                boolean z14;
                boolean z15;
                boolean z16;
                boolean z17;
                NavigationResult autoChargeBehavior;
                int i2;
                boolean z18;
                boolean z19;
                boolean z20;
                double d;
                int i3;
                int i4;
                double d2;
                double d3;
                double d4;
                Vector3d vector3d23;
                Vector3d vector3d24;
                double d5;
                double d6;
                double d7;
                double d8;
                boolean z21;
                boolean z22;
                Vector3d vector3d25;
                Vector3d vector3d26;
                double d9;
                double d10;
                int i5;
                double d11;
                double d12;
                int i6;
                int i7;
                double d13;
                int i8;
                double d14;
                int i9;
                double d15;
                Vector2d vector2d10;
                Vector2d vector2d11;
                double d16;
                double d17;
                Vector3d vector3d27;
                Vector3d vector3d28;
                double d18;
                double d19;
                double d20;
                double d21;
                double d22;
                int i10;
                double d23;
                double d24;
                int i11;
                double d25;
                double d26;
                double d27;
                double d28;
                int i12;
                double d29;
                boolean z23;
                int i13;
                long j;
                int i14;
                boolean z24;
                int i15;
                boolean z25;
                boolean z26;
                int i16;
                String str;
                boolean z27;
                Vector3d vector3d29;
                int i17;
                List<String> list;
                boolean z28;
                boolean z29;
                double d30;
                double d31;
                double d32;
                ExecuteType executeType4;
                MoveMode moveMode4;
                double d33;
                boolean z30;
                Thread currentThread = Thread.currentThread();
                Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
                currentThread.setName("UpdateCostmap");
                vector2d = NavigationStub.this.speedLis;
                synchronized (vector2d) {
                    vector2d2 = NavigationStub.this.speedNav;
                    vector2d3 = NavigationStub.this.speedLis;
                    vector2d2.setX(vector2d3.getX());
                    vector2d4 = NavigationStub.this.speedNav;
                    vector2d5 = NavigationStub.this.speedLis;
                    vector2d4.setY(vector2d5.getY());
                    Unit unit = Unit.INSTANCE;
                }
                vector3d = NavigationStub.this.poseLis;
                synchronized (vector3d) {
                    vector3d2 = NavigationStub.this.poseNav;
                    vector3d3 = NavigationStub.this.poseLis;
                    vector3d2.setX(vector3d3.getX());
                    vector3d4 = NavigationStub.this.poseNav;
                    vector3d5 = NavigationStub.this.poseLis;
                    vector3d4.setY(vector3d5.getY());
                    vector3d6 = NavigationStub.this.poseNav;
                    vector3d7 = NavigationStub.this.poseLis;
                    vector3d6.setZ(vector3d7.getZ());
                    Unit unit2 = Unit.INSTANCE;
                }
                vector3d8 = NavigationStub.this.dirLis;
                synchronized (vector3d8) {
                    vector3d9 = NavigationStub.this.dirNav;
                    vector3d10 = NavigationStub.this.dirLis;
                    vector3d9.setX(vector3d10.getX());
                    vector3d11 = NavigationStub.this.dirNav;
                    vector3d12 = NavigationStub.this.dirLis;
                    vector3d11.setY(vector3d12.getY());
                    vector3d13 = NavigationStub.this.dirNav;
                    vector3d14 = NavigationStub.this.dirLis;
                    vector3d13.setZ(vector3d14.getZ());
                    Unit unit3 = Unit.INSTANCE;
                }
                String tag = NavigationStub.this.getTAG();
                StringBuilder sb = new StringBuilder();
                sb.append("tangential speed ");
                vector2d6 = NavigationStub.this.speedNav;
                sb.append(vector2d6.getX());
                sb.append(", axial speed ");
                vector2d7 = NavigationStub.this.speedNav;
                sb.append(vector2d7.getY());
                sb.append(", dir ");
                vector3d15 = NavigationStub.this.dirNav;
                sb.append(vector3d15.getX());
                sb.append(", ");
                vector3d16 = NavigationStub.this.dirNav;
                sb.append(vector3d16.getY());
                sb.append(", pose ");
                vector3d17 = NavigationStub.this.poseNav;
                sb.append(vector3d17.getX());
                sb.append(", ");
                vector3d18 = NavigationStub.this.poseNav;
                sb.append(vector3d18.getY());
                Pdlog.m3273d(tag, sb.toString());
                NavigationStub.this.reflectorBrakeFlag = false;
                z = NavigationStub.this.rotate_begin;
                if (!z && MirCoreImpl.INSTANCE.getLocalization().isLocalizationFinishInitialization()) {
                    NavigationStub.this.rotate_radian = MirCoreImpl.INSTANCE.getLocalization().getRotatePlanning();
                    String tag2 = NavigationStub.this.getTAG();
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("rotate_radian ");
                    d31 = NavigationStub.this.rotate_radian;
                    sb2.append(d31);
                    Pdlog.m3273d(tag2, sb2.toString());
                    d32 = NavigationStub.this.rotate_radian;
                    if (Math.abs(d32) > 0.1d) {
                        Pdlog.m3273d(NavigationStub.this.getTAG(), "prepare localization recover");
                        executeType4 = NavigationStub.this.runType;
                        int i18 = NavigationStub.WhenMappings.$EnumSwitchMapping$11[executeType4.ordinal()];
                        if (i18 == 1) {
                            moveMode4 = NavigationStub.this.moveMode;
                            int i19 = NavigationStub.WhenMappings.$EnumSwitchMapping$10[moveMode4.ordinal()];
                            if (i19 == 1) {
                                NavigationStub.this.last_task_model = "Cruise";
                            } else if (i19 == 2) {
                                NavigationStub.this.last_task_model = "Direct";
                            } else if (i19 == 3) {
                                z30 = NavigationStub.this.stickToPos;
                                if (z30) {
                                    NavigationStub.this.last_task_model = "MoveToCharge";
                                } else {
                                    NavigationStub.this.last_task_model = "GoHome";
                                }
                            }
                            NavigationStub navigationStub = NavigationStub.this;
                            d33 = navigationStub.rotate_radian;
                            navigationStub.prepareRecoverTask(d33);
                        } else if (i18 == 2) {
                            NavigationStub.this.last_task_model = "AutoCharge";
                        }
                    }
                }
                z2 = NavigationStub.this.rotate_begin;
                if (z2) {
                    NavigationStub navigationStub2 = NavigationStub.this;
                    d30 = navigationStub2.rotate_radian;
                    navigationStub2.prepareRecoverTask(d30);
                }
                executeType = NavigationStub.this.runType;
                if (executeType == ExecuteType.Recover && MirCoreImpl.INSTANCE.getLocalization().getLocalizationStatus().getStatus_level() == LocalizationStatusLevel.Normal) {
                    str = NavigationStub.this.last_task_model;
                    switch (str.hashCode()) {
                        case 1141148003:
                            if (str.equals("AutoCharge")) {
                                NavigationStub navigationStub3 = NavigationStub.this;
                                vector3d29 = navigationStub3.autoChargePose;
                                navigationStub3.prepareAutoChargeTask(vector3d29);
                                break;
                            }
                            break;
                        case 1986308128:
                            if (str.equals("MoveToCharge")) {
                                NavigationStub.this.prepareMoveToChargeTask();
                                break;
                            }
                            break;
                        case 2027024629:
                            if (str.equals("Cruise")) {
                                NavigationStub navigationStub4 = NavigationStub.this;
                                i17 = navigationStub4.last_cruise_path_id;
                                list = NavigationStub.this.last_cruise_stay_points;
                                navigationStub4.prepareCruiseTask(i17, list);
                                break;
                            }
                            break;
                        case 2047248393:
                            if (str.equals("Direct")) {
                                NavigationStub navigationStub5 = NavigationStub.this;
                                z28 = navigationStub5.goalDirProperty;
                                z29 = NavigationStub.this.preciseFlag;
                                navigationStub5.prepareDeliverTask(z28, z29);
                                break;
                            }
                            break;
                        case 2137435655:
                            if (str.equals("GoHome")) {
                                NavigationStub.this.prepareGoHomeTask();
                                break;
                            }
                            break;
                    }
                    NavigationStub.this.rotate_begin = false;
                    NavigationStub.this.rotate_end = false;
                    LocalizationStub localization = MirCoreImpl.INSTANCE.getLocalization();
                    z27 = NavigationStub.this.rotate_end;
                    localization.setRotateEnd(z27);
                    NavigationStub.this.deceleration_complete = false;
                }
                Navigation.INSTANCE.fetchDynamicConfig();
                final Ref.IntRef intRef = new Ref.IntRef();
                intRef.element = Perception.INSTANCE.getCliffInfo();
                MirCoreImpl.INSTANCE.getCliffDisListener$mircore_packRelease().notify(new Function2<CliffInfoListener, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.module.NavigationStub$startNavigation$1.4
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(CliffInfoListener cliffInfoListener, String str2) {
                        invoke2(cliffInfoListener, str2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(CliffInfoListener it, String str2) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                        it.cliffDistance(Ref.IntRef.this.element);
                    }
                });
                z3 = NavigationStub.this.featStuckReplan;
                if (z3) {
                    i13 = NavigationStub.this.stuck_count;
                    if (i13 < 3) {
                        i16 = NavigationStub.this.stuck_count;
                        if (i16 > 0) {
                            NavigationStub.this.stuck_start_time = System.currentTimeMillis();
                        }
                    }
                    long currentTimeMillis = System.currentTimeMillis();
                    j = NavigationStub.this.stuck_start_time;
                    long j2 = (currentTimeMillis - j) / 1000;
                    long currentTimeMillis2 = System.currentTimeMillis() - MirCoreImpl.INSTANCE.getTimeZero();
                    ScheduleMaster.INSTANCE.setCurrentTime((int) currentTimeMillis2);
                    String tag3 = NavigationStub.this.getTAG();
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("stuck replan: tnow ");
                    sb3.append(currentTimeMillis2);
                    sb3.append(", replanWait ");
                    sb3.append(MirCoreImpl.INSTANCE.getReplanWaitTime());
                    sb3.append(", count ");
                    i14 = NavigationStub.this.stuck_count;
                    sb3.append(i14);
                    sb3.append(", stuck time ");
                    sb3.append(j2);
                    sb3.append(" s, dyn ");
                    z24 = NavigationStub.this.featDynRoadblock;
                    sb3.append(z24);
                    Pdlog.m3273d(tag3, sb3.toString());
                    i15 = NavigationStub.this.stuck_count;
                    if (i15 <= 5 || j2 <= MirCoreImpl.INSTANCE.getReplanWaitTime()) {
                        z25 = NavigationStub.this.featDynRoadblock;
                        if (!z25) {
                            Pdlog.m3273d(NavigationStub.this.getTAG(), "do NOT set road block");
                            ScheduleMaster.INSTANCE.setRoadblockInFront(false);
                        }
                    } else {
                        Pdlog.m3273d(NavigationStub.this.getTAG(), "stuck over " + MirCoreImpl.INSTANCE.getReplanWaitTime() + "s, global replan");
                        ScheduleMaster.INSTANCE.setRoadblockInFront(true);
                        String str2 = "{ \"roadblock_timeout\": " + (MirCoreImpl.INSTANCE.getRoadblockClearTime() * 1000) + " }";
                        z26 = NavigationStub.this.featDynRoadblock;
                        if (!z26) {
                            str2 = "{ \"roadblock_timeout\": -1 }";
                        }
                        if (!ScheduleMaster.INSTANCE.setPlannerParam(str2)) {
                            Pdlog.m3273d(NavigationStub.this.getTAG(), "failed to set param to ScheduleMaster: " + str2);
                        }
                        z4 = true;
                        ScheduleMaster scheduleMaster = ScheduleMaster.INSTANCE;
                        vector3d19 = NavigationStub.this.poseNav;
                        double x = vector3d19.getX();
                        vector3d20 = NavigationStub.this.poseNav;
                        double y = vector3d20.getY();
                        moveMode = NavigationStub.this.moveMode;
                        int value = moveMode.getValue();
                        vector2d8 = NavigationStub.this.speedNav;
                        double x2 = vector2d8.getX();
                        vector2d9 = NavigationStub.this.speedNav;
                        boolean preScheduleCheckReplanVel = scheduleMaster.preScheduleCheckReplanVel(x, y, value, x2, vector2d9.getY());
                        Perception perception = Perception.INSTANCE;
                        vector3d21 = NavigationStub.this.poseNav;
                        double x3 = vector3d21.getX();
                        vector3d22 = NavigationStub.this.poseNav;
                        long[] costmapAddress = perception.getCostmapAddress(x3, vector3d22.getY());
                        long[] dynamicAddress = Perception.INSTANCE.getDynamicAddress();
                        long[] jArr = {costmapAddress[0], costmapAddress[1], dynamicAddress[0], dynamicAddress[1], Perception.INSTANCE.getMarkerAddress()};
                        String tag4 = NavigationStub.this.getTAG();
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append("current runType is ");
                        executeType2 = NavigationStub.this.runType;
                        sb4.append(executeType2.toString());
                        Pdlog.m3273d(tag4, sb4.toString());
                        executeType3 = NavigationStub.this.runType;
                        i = NavigationStub.WhenMappings.$EnumSwitchMapping$13[executeType3.ordinal()];
                        if (i != 1) {
                            NavigationStub.this.rotate_begin = false;
                            NavigationStub.this.rotate_end = false;
                            LocalizationStub localization2 = MirCoreImpl.INSTANCE.getLocalization();
                            z5 = NavigationStub.this.rotate_end;
                            localization2.setRotateEnd(z5);
                            NavigationStub.this.deceleration_complete = false;
                            if (preScheduleCheckReplanVel) {
                                NavigationStub.this.stuck_count = 0;
                                NavigationStub.this.clearActionStatus();
                                Vector3d finalGoal = ScheduleMaster.INSTANCE.getFinalGoal();
                                NavigationStub navigationStub6 = NavigationStub.this;
                                z7 = navigationStub6.waiterFlag;
                                if (!z7) {
                                    moveMode3 = NavigationStub.this.moveMode;
                                    if (moveMode3 == MoveMode.Cruise) {
                                        z8 = true;
                                        navigationStub6.virtualGoal = z8;
                                        String tag5 = NavigationStub.this.getTAG();
                                        StringBuilder sb5 = new StringBuilder();
                                        sb5.append("restPath for reset_plan ");
                                        z9 = NavigationStub.this.goalDirProperty;
                                        sb5.append(z9);
                                        sb5.append(' ');
                                        z10 = NavigationStub.this.deliverStart;
                                        sb5.append(z10);
                                        sb5.append(' ');
                                        z11 = NavigationStub.this.virtualGoal;
                                        sb5.append(z11);
                                        Pdlog.m3273d(tag5, sb5.toString());
                                        Navigation navigation = Navigation.INSTANCE;
                                        long j3 = costmapAddress[0];
                                        long pathPointAddress = ScheduleMaster.INSTANCE.getPathPointAddress();
                                        double x4 = finalGoal.getX();
                                        double y2 = finalGoal.getY();
                                        double z31 = finalGoal.getZ();
                                        z12 = NavigationStub.this.goalDirProperty;
                                        z13 = NavigationStub.this.deliverStart;
                                        z14 = NavigationStub.this.virtualGoal;
                                        navigation.resetPath(j3, pathPointAddress, x4, y2, z31, z12, z13, z14, ScheduleMaster.INSTANCE.getGlobalPathAddress());
                                        Navigation navigation2 = Navigation.INSTANCE;
                                        StringBuilder sb6 = new StringBuilder();
                                        sb6.append("{\"precise_flag\": ");
                                        z15 = NavigationStub.this.preciseFlag;
                                        sb6.append(z15);
                                        sb6.append('}');
                                        navigation2.updateCycleParam(sb6.toString());
                                        z16 = NavigationStub.this.featStuckReplan;
                                        if (z16 && z4) {
                                            listener.onStuckReplan();
                                            Pdlog.m3273d(NavigationStub.this.getTAG(), "notify stuck & replan");
                                        }
                                    }
                                }
                                z8 = false;
                                navigationStub6.virtualGoal = z8;
                                String tag52 = NavigationStub.this.getTAG();
                                StringBuilder sb52 = new StringBuilder();
                                sb52.append("restPath for reset_plan ");
                                z9 = NavigationStub.this.goalDirProperty;
                                sb52.append(z9);
                                sb52.append(' ');
                                z10 = NavigationStub.this.deliverStart;
                                sb52.append(z10);
                                sb52.append(' ');
                                z11 = NavigationStub.this.virtualGoal;
                                sb52.append(z11);
                                Pdlog.m3273d(tag52, sb52.toString());
                                Navigation navigation3 = Navigation.INSTANCE;
                                long j32 = costmapAddress[0];
                                long pathPointAddress2 = ScheduleMaster.INSTANCE.getPathPointAddress();
                                double x42 = finalGoal.getX();
                                double y22 = finalGoal.getY();
                                double z312 = finalGoal.getZ();
                                z12 = NavigationStub.this.goalDirProperty;
                                z13 = NavigationStub.this.deliverStart;
                                z14 = NavigationStub.this.virtualGoal;
                                navigation3.resetPath(j32, pathPointAddress2, x42, y22, z312, z12, z13, z14, ScheduleMaster.INSTANCE.getGlobalPathAddress());
                                Navigation navigation22 = Navigation.INSTANCE;
                                StringBuilder sb62 = new StringBuilder();
                                sb62.append("{\"precise_flag\": ");
                                z15 = NavigationStub.this.preciseFlag;
                                sb62.append(z15);
                                sb62.append('}');
                                navigation22.updateCycleParam(sb62.toString());
                                z16 = NavigationStub.this.featStuckReplan;
                                if (z16) {
                                    listener.onStuckReplan();
                                    Pdlog.m3273d(NavigationStub.this.getTAG(), "notify stuck & replan");
                                }
                            }
                            ScheduleMaster scheduleMaster2 = ScheduleMaster.INSTANCE;
                            moveMode2 = NavigationStub.this.moveMode;
                            NextNavigationBehavior checkScheduleForNavigation = scheduleMaster2.checkScheduleForNavigation(moveMode2.getValue());
                            NavigationResultListener navigationResultListener = listener;
                            if (!NavigationStub.this.judgeCostmapStautus()) {
                                Pdlog.m3274e(NavigationStub.this.getTAG(), "costmap status error, just stop");
                                if (MirCoreImpl.INSTANCE.getLocalization().isLocalizationFinishInitialization()) {
                                    MirCoreImpl.INSTANCE.getLocalization().setTaskStatus(true);
                                }
                                planOnNormalPathBehavior = new NavigationResult(0.0d, 0.0d, 0.0d, null, 15, null);
                                planOnNormalPathBehavior.setNavigation_status(NavigationStatus.Stuck);
                                planOnNormalPathBehavior.setLinear_vel(0.0d);
                                planOnNormalPathBehavior.setAngular_vel(0.0d);
                                planOnNormalPathBehavior.setCost(-1.0d);
                                Unit unit4 = Unit.INSTANCE;
                            } else {
                                int i20 = NavigationStub.WhenMappings.$EnumSwitchMapping$12[checkScheduleForNavigation.ordinal()];
                                if (i20 == 1) {
                                    NavigationStub navigationStub7 = NavigationStub.this;
                                    NavigationMode navigationMode = mode;
                                    boolean z32 = alignGoal;
                                    z6 = navigationStub7.stickToPos;
                                    planOnNormalPathBehavior = navigationStub7.planOnNormalPathBehavior(jArr, navigationMode, z32, z6);
                                } else if (i20 == 2) {
                                    planOnNormalPathBehavior = NavigationStub.this.planOnVirtualPathBehavior(jArr, mode, alignGoal);
                                } else if (i20 == 3) {
                                    NavigationStub.this.stuck_count = 0;
                                    planOnNormalPathBehavior = NavigationStub.this.assignReplanBehavior(jArr);
                                } else if (i20 != 4) {
                                    planOnNormalPathBehavior = NavigationStub.this.avoidWaitBehavior();
                                } else {
                                    NavigationStub.this.stuck_count = 0;
                                    planOnNormalPathBehavior = NavigationStub.this.assignVirtualPlanBehavior(jArr);
                                }
                                if (MirCoreImpl.INSTANCE.getLocalization().isLocalizationFinishInitialization()) {
                                    if (planOnNormalPathBehavior.getNavigation_status() == NavigationStatus.Stuck) {
                                        MirCoreImpl.INSTANCE.getLocalization().setTaskStatus(true);
                                    } else {
                                        MirCoreImpl.INSTANCE.getLocalization().setTaskStatus(false);
                                    }
                                }
                            }
                            navigationResultListener.onResult(planOnNormalPathBehavior);
                            return;
                        }
                        if (i == 2) {
                            NavigationStub.this.rotate_begin = false;
                            NavigationStub.this.rotate_end = false;
                            LocalizationStub localization3 = MirCoreImpl.INSTANCE.getLocalization();
                            z17 = NavigationStub.this.rotate_end;
                            localization3.setRotateEnd(z17);
                            NavigationStub.this.deceleration_complete = false;
                            autoChargeBehavior = NavigationStub.this.autoChargeBehavior(jArr);
                            if ((autoChargeBehavior.getNavigation_status() == NavigationStatus.FailTrack && autoChargeBehavior.getNavigation_status() == NavigationStatus.FailStuck) || autoChargeBehavior.getNavigation_status() == NavigationStatus.FailOverTime) {
                                NavigationStub navigationStub8 = NavigationStub.this;
                                i2 = navigationStub8.chargeTimes;
                                navigationStub8.chargeTimes = i2 + 1;
                                Perception.INSTANCE.setAutoDockSwitch(false);
                            }
                            if (MirCoreImpl.INSTANCE.getLocalization().isLocalizationFinishInitialization()) {
                                if (autoChargeBehavior.getNavigation_status() == NavigationStatus.Stuck) {
                                    MirCoreImpl.INSTANCE.getLocalization().setTaskStatus(true);
                                } else {
                                    MirCoreImpl.INSTANCE.getLocalization().setTaskStatus(false);
                                }
                            }
                            listener.onResult(autoChargeBehavior);
                            return;
                        }
                        if (i != 3) {
                            return;
                        }
                        Pdlog.m3273d(NavigationStub.this.getTAG(), "localization recover mode");
                        z18 = NavigationStub.this.rotate_end;
                        if (!z18) {
                            z19 = NavigationStub.this.rotate_begin;
                            if (!z19) {
                                NavigationStub navigationStub9 = NavigationStub.this;
                                vector3d27 = navigationStub9.dirLis;
                                double y3 = vector3d27.getY();
                                vector3d28 = NavigationStub.this.dirLis;
                                navigationStub9.begin_radian = Math.atan2(y3, vector3d28.getX());
                                NavigationStub navigationStub10 = NavigationStub.this;
                                d18 = navigationStub10.begin_radian;
                                navigationStub10.goal_radian = d18;
                                NavigationStub.this.rotate_PI_id = 0;
                                d19 = NavigationStub.this.rotate_radian;
                                if (d19 > 0) {
                                    d26 = NavigationStub.this.rotate_radian;
                                    d27 = NavigationStub.this.PI;
                                    NavigationStub.this.rotate_PI_num = (int) (d26 / d27);
                                    NavigationStub navigationStub11 = NavigationStub.this;
                                    d28 = navigationStub11.rotate_radian;
                                    i12 = NavigationStub.this.rotate_PI_num;
                                    d29 = NavigationStub.this.PI;
                                    navigationStub11.remain_radian = d28 - (i12 * d29);
                                } else {
                                    d20 = NavigationStub.this.rotate_radian;
                                    d21 = NavigationStub.this.PI;
                                    NavigationStub.this.rotate_PI_num = (int) (d20 / (-d21));
                                    NavigationStub navigationStub12 = NavigationStub.this;
                                    d22 = navigationStub12.rotate_radian;
                                    i10 = NavigationStub.this.rotate_PI_num;
                                    d23 = NavigationStub.this.PI;
                                    navigationStub12.remain_radian = d22 + (i10 * d23);
                                }
                                String tag6 = NavigationStub.this.getTAG();
                                StringBuilder sb7 = new StringBuilder();
                                sb7.append("begin_radian is ");
                                d24 = NavigationStub.this.begin_radian;
                                sb7.append(d24);
                                Pdlog.m3273d(tag6, sb7.toString());
                                String tag7 = NavigationStub.this.getTAG();
                                StringBuilder sb8 = new StringBuilder();
                                sb8.append("rotate_PI_num is ");
                                i11 = NavigationStub.this.rotate_PI_num;
                                sb8.append(i11);
                                Pdlog.m3273d(tag7, sb8.toString());
                                String tag8 = NavigationStub.this.getTAG();
                                StringBuilder sb9 = new StringBuilder();
                                sb9.append("remain_radian is ");
                                d25 = NavigationStub.this.remain_radian;
                                sb9.append(d25);
                                Pdlog.m3273d(tag8, sb9.toString());
                                NavigationStub.this.rotate_begin = true;
                            }
                            z20 = NavigationStub.this.deceleration_complete;
                            if (z20) {
                                d = NavigationStub.this.rotate_radian;
                                double d34 = 0;
                                double d35 = d < d34 ? 0.5d * (-1) : 0.5d;
                                i3 = NavigationStub.this.rotate_PI_id;
                                i4 = NavigationStub.this.rotate_PI_num;
                                if (i3 < i4) {
                                    vector3d25 = NavigationStub.this.dirLis;
                                    double y4 = vector3d25.getY();
                                    vector3d26 = NavigationStub.this.dirLis;
                                    double atan2 = Math.atan2(y4, vector3d26.getX());
                                    d9 = NavigationStub.this.rotate_radian;
                                    if (d9 > d34) {
                                        NavigationStub navigationStub13 = NavigationStub.this;
                                        d14 = navigationStub13.begin_radian;
                                        i9 = NavigationStub.this.rotate_PI_id;
                                        d15 = NavigationStub.this.PI;
                                        navigationStub13.goal_radian = navigationStub13.normalizeRadian(d14 + ((i9 + 1) * d15));
                                    } else {
                                        NavigationStub navigationStub14 = NavigationStub.this;
                                        d10 = navigationStub14.begin_radian;
                                        i5 = NavigationStub.this.rotate_PI_id;
                                        d11 = NavigationStub.this.PI;
                                        navigationStub14.goal_radian = navigationStub14.normalizeRadian(d10 - ((i5 + 1) * d11));
                                    }
                                    NavigationStub navigationStub15 = NavigationStub.this;
                                    d12 = navigationStub15.goal_radian;
                                    if (Math.abs(navigationStub15.normalizeRadian(atan2 - d12)) < 0.1d) {
                                        NavigationStub navigationStub16 = NavigationStub.this;
                                        i8 = navigationStub16.rotate_PI_id;
                                        navigationStub16.rotate_PI_id = i8 + 1;
                                    }
                                    Pdlog.m3273d(NavigationStub.this.getTAG(), "current_radian is " + atan2);
                                    String tag9 = NavigationStub.this.getTAG();
                                    StringBuilder sb10 = new StringBuilder();
                                    sb10.append("rotate_PI_id is ");
                                    i6 = NavigationStub.this.rotate_PI_id;
                                    sb10.append(i6);
                                    Pdlog.m3273d(tag9, sb10.toString());
                                    String tag10 = NavigationStub.this.getTAG();
                                    StringBuilder sb11 = new StringBuilder();
                                    sb11.append("rotate_PI_num is ");
                                    i7 = NavigationStub.this.rotate_PI_num;
                                    sb11.append(i7);
                                    Pdlog.m3273d(tag10, sb11.toString());
                                    String tag11 = NavigationStub.this.getTAG();
                                    StringBuilder sb12 = new StringBuilder();
                                    sb12.append("goal_radian is ");
                                    d13 = NavigationStub.this.goal_radian;
                                    sb12.append(d13);
                                    Pdlog.m3273d(tag11, sb12.toString());
                                    listener.onResult(new NavigationResult(0.0d, d35, 1.0d, NavigationStatus.Rotating));
                                    return;
                                }
                                d2 = NavigationStub.this.remain_radian;
                                if (Math.abs(d2) < 0.2d) {
                                    Pdlog.m3273d(NavigationStub.this.getTAG(), "rotate_end");
                                    NavigationStub.this.rotate_end = true;
                                    LocalizationStub localization4 = MirCoreImpl.INSTANCE.getLocalization();
                                    z22 = NavigationStub.this.rotate_end;
                                    localization4.setRotateEnd(z22);
                                    listener.onResult(new NavigationResult(0.0d, 0.0d, 1.0d, NavigationStatus.Navigating));
                                    return;
                                }
                                NavigationStub navigationStub17 = NavigationStub.this;
                                d3 = navigationStub17.begin_radian;
                                d4 = NavigationStub.this.rotate_radian;
                                navigationStub17.goal_radian = navigationStub17.normalizeRadian(d3 + d4);
                                vector3d23 = NavigationStub.this.dirLis;
                                double y5 = vector3d23.getY();
                                vector3d24 = NavigationStub.this.dirLis;
                                double atan22 = Math.atan2(y5, vector3d24.getX());
                                NavigationStub navigationStub18 = NavigationStub.this;
                                d5 = navigationStub18.goal_radian;
                                if (Math.abs(navigationStub18.normalizeRadian(atan22 - d5)) < 0.1d) {
                                    Pdlog.m3273d(NavigationStub.this.getTAG(), "rotate_end");
                                    NavigationStub.this.rotate_end = true;
                                    LocalizationStub localization5 = MirCoreImpl.INSTANCE.getLocalization();
                                    z21 = NavigationStub.this.rotate_end;
                                    localization5.setRotateEnd(z21);
                                    listener.onResult(new NavigationResult(0.0d, 0.0d, 1.0d, NavigationStatus.Navigating));
                                } else {
                                    listener.onResult(new NavigationResult(0.0d, d35, 1.0d, NavigationStatus.Rotating));
                                }
                                Pdlog.m3273d(NavigationStub.this.getTAG(), "current_radian is " + atan22);
                                String tag12 = NavigationStub.this.getTAG();
                                StringBuilder sb13 = new StringBuilder();
                                sb13.append("begin_radian is ");
                                d6 = NavigationStub.this.begin_radian;
                                sb13.append(d6);
                                Pdlog.m3273d(tag12, sb13.toString());
                                String tag13 = NavigationStub.this.getTAG();
                                StringBuilder sb14 = new StringBuilder();
                                sb14.append("rotate_radian is ");
                                d7 = NavigationStub.this.rotate_radian;
                                sb14.append(d7);
                                Pdlog.m3273d(tag13, sb14.toString());
                                String tag14 = NavigationStub.this.getTAG();
                                StringBuilder sb15 = new StringBuilder();
                                sb15.append("goal_radian is ");
                                d8 = NavigationStub.this.goal_radian;
                                sb15.append(d8);
                                Pdlog.m3273d(tag14, sb15.toString());
                                return;
                            }
                            Pdlog.m3273d(NavigationStub.this.getTAG(), "To slow down");
                            vector2d10 = NavigationStub.this.speedLis;
                            double x5 = vector2d10.getX() * 0.7d;
                            vector2d11 = NavigationStub.this.speedLis;
                            double y6 = 0.7d * vector2d11.getY();
                            if (Math.abs(y6) >= 0.4d || Math.abs(y6) >= 0.5d) {
                                d16 = x5;
                                d17 = y6;
                            } else {
                                NavigationStub.this.deceleration_complete = true;
                                d16 = 0.0d;
                                d17 = 0.0d;
                            }
                            listener.onResult(new NavigationResult(d16, d17, 1.0d, NavigationStatus.Rotating));
                            return;
                        }
                        Pdlog.m3273d(NavigationStub.this.getTAG(), "rotate_end");
                        LocalizationStub localization6 = MirCoreImpl.INSTANCE.getLocalization();
                        z23 = NavigationStub.this.rotate_end;
                        localization6.setRotateEnd(z23);
                        listener.onResult(new NavigationResult(0.0d, 0.0d, 1.0d, NavigationStatus.Navigating));
                        return;
                    }
                }
                z4 = false;
                ScheduleMaster scheduleMaster3 = ScheduleMaster.INSTANCE;
                vector3d19 = NavigationStub.this.poseNav;
                double x6 = vector3d19.getX();
                vector3d20 = NavigationStub.this.poseNav;
                double y7 = vector3d20.getY();
                moveMode = NavigationStub.this.moveMode;
                int value2 = moveMode.getValue();
                vector2d8 = NavigationStub.this.speedNav;
                double x22 = vector2d8.getX();
                vector2d9 = NavigationStub.this.speedNav;
                boolean preScheduleCheckReplanVel2 = scheduleMaster3.preScheduleCheckReplanVel(x6, y7, value2, x22, vector2d9.getY());
                Perception perception2 = Perception.INSTANCE;
                vector3d21 = NavigationStub.this.poseNav;
                double x32 = vector3d21.getX();
                vector3d22 = NavigationStub.this.poseNav;
                long[] costmapAddress2 = perception2.getCostmapAddress(x32, vector3d22.getY());
                long[] dynamicAddress2 = Perception.INSTANCE.getDynamicAddress();
                long[] jArr2 = {costmapAddress2[0], costmapAddress2[1], dynamicAddress2[0], dynamicAddress2[1], Perception.INSTANCE.getMarkerAddress()};
                String tag42 = NavigationStub.this.getTAG();
                StringBuilder sb42 = new StringBuilder();
                sb42.append("current runType is ");
                executeType2 = NavigationStub.this.runType;
                sb42.append(executeType2.toString());
                Pdlog.m3273d(tag42, sb42.toString());
                executeType3 = NavigationStub.this.runType;
                i = NavigationStub.WhenMappings.$EnumSwitchMapping$13[executeType3.ordinal()];
                if (i != 1) {
                }
            }
        });
    }

    @Override // com.pudutech.mirsdk.mircore.NavigationInterface
    public void pauseNavigation() {
        Perception.INSTANCE.unregistMapUpdatedListener("nav");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2, types: [T, java.lang.String] */
    @Override // com.pudutech.mirsdk.mircore.NavigationInterface
    public void uploadCliffCenterIr() {
        Pdlog.m3273d(this.TAG, "uploadCliffCenterIr begin.");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = Perception.INSTANCE.getCliffIrImg();
        Pdlog.m3273d(this.TAG, "uploadCliffCenterIr --> cliffIrImg = " + ((String) objectRef.element) + FilenameUtils.EXTENSION_SEPARATOR);
        MirCoreImpl.INSTANCE.getCliffDisListener$mircore_packRelease().notify(new Function2<CliffInfoListener, String, Unit>() { // from class: com.pudutech.mirsdk.mircore.module.NavigationStub$uploadCliffCenterIr$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CliffInfoListener cliffInfoListener, String str) {
                invoke2(cliffInfoListener, str);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(CliffInfoListener it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                it.cliffIrImg((String) Ref.ObjectRef.this.element);
            }
        });
    }

    public final void setReflectBrakeFlag(boolean flag) {
        this.reflectorBrakeFlag = flag;
        if (this.reflectorBrakeFlag) {
            MirCoreImpl.INSTANCE.setAccParam(AccelerationType.EmergencyStopDeceleration, this.origin_emergence);
        }
    }

    public final void sendTaskPlannerParam(String speedMode) {
        if (speedMode != null && !this.steadyFlag) {
            Navigation.INSTANCE.updateConfig(PlannerParamUtils.INSTANCE.getParamBySpeedModeLessThanCurrentMode(speedMode, this.moveMode));
            return;
        }
        Pdlog.m3273d(this.TAG, "try to send the planner param");
        if (this.steadyFlag) {
            return;
        }
        int i = WhenMappings.$EnumSwitchMapping$14[this.moveMode.ordinal()];
        if (i == 1) {
            PlannerParamUtils.INSTANCE.sendP2PParams();
        } else if (i == 2) {
            PlannerParamUtils.INSTANCE.sendCruiseParams();
        } else {
            if (i != 3) {
                return;
            }
            PlannerParamUtils.INSTANCE.sendGoHomeParams();
        }
    }

    @Override // com.pudutech.mirsdk.mircore.NavigationInterface
    public void resetChargeTimes() {
        this.chargeTimes = 0;
        Navigation.INSTANCE.updateCycleParam("{\"charge_times\":" + this.chargeTimes + '}');
    }

    @Override // com.pudutech.mirsdk.mircore.NavigationInterface
    public void updateTrayDis(double tray_dis) {
        double d;
        Pdlog.m3273d(this.TAG, "1call updateTrayDis with " + tray_dis);
        if (tray_dis < 0.0d) {
            tray_dis = 0.0d;
        } else if (tray_dis > 0.1d) {
            tray_dis = 0.1d;
        }
        RunParamUtils.INSTANCE.writeRunParam("tray_dis", tray_dis);
        if (this.trayDis == tray_dis) {
            return;
        }
        Pdlog.m3273d(this.TAG, "call updateTrayDis with " + tray_dis);
        this.trayDis = tray_dis;
        Navigation.INSTANCE.updateCycleParam("{\"dynamic_tray\":" + tray_dis + '}');
        int i = WhenMappings.$EnumSwitchMapping$15[MirCoreImpl.INSTANCE.getMachineType().ordinal()];
        if (i == 1) {
            Pdlog.m3273d(this.TAG, "updateTrayDis machine mode bella");
            d = this.trayDis;
        } else if (i == 2) {
            Pdlog.m3273d(this.TAG, "updateTrayDis machine mode hola");
            d = this.trayDis;
        } else {
            Pdlog.m3274e(this.TAG, "updateTrayDis machine mode not support");
            return;
        }
        double d2 = d + 0.27d;
        if (d2 > 0.0d) {
            PlannerParamUtils.INSTANCE.updateObsDis(d2);
        }
    }

    @Override // com.pudutech.mirsdk.mircore.NavigationInterface
    public double getTrayDis() {
        return RunParamUtils.INSTANCE.getInitRunParamDouble("tray_dis");
    }

    @Override // com.pudutech.mirsdk.mircore.NavigationInterface
    public void updateGateLimitSpeed(double speed) {
        Navigation.INSTANCE.updateCycleParam("{\"gate_limit_speed\":" + speed + '}');
        Pdlog.m3273d(this.TAG, "updateGateLimitSpeed: " + speed);
    }
}
