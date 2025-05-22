package com.pudutech.robot.peripherals.disinfection;

import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.aliyun.alink.linksdk.alcs.coap.resources.LinkFormat;
import com.iflytek.speech.UtilityConfig;
import com.pudutech.base.Pdlog;
import com.pudutech.robot.peripherals.common.CommonRobotPeripherals;
import com.pudutech.robot.peripherals.config.LightBeltType;
import com.pudutech.robot.peripherals.manager.CANConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: DisinfectRobotPeripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000Þ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b'\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b+\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u0000 ¸\u00012\u00020\u00012\u00020\u0002:\u0002¸\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J-\u0010O\u001a\u00020\u00142#\u0010+\u001a\u001f\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0010H\u0016J7\u0010P\u001a\u00020\u00142-\u0010+\u001a)\u0012#\u0012!\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u00140\u0010j\u0002`\u001a\u0018\u00010\u0018H\u0016J3\u0010Q\u001a\u00020\u00142)\u0010+\u001a%\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u001d0\u001c¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(R\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0010H\u0016JI\u0010S\u001a\u00020\u00142?\u0010+\u001a;\u0012/\u0012-\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00050 j\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u0005`\"¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(T\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0010H\u0016J7\u0010U\u001a\u00020\u00142-\u0010+\u001a)\u0012#\u0012!\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u00140\u0010j\u0002`(\u0018\u00010\u0018H\u0016J-\u0010V\u001a\u00020\u00142#\u0010+\u001a\u001f\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0010H\u0016J-\u0010W\u001a\u00020\u00142#\u0010+\u001a\u001f\u0012\u0013\u0012\u00110!¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0010H\u0016J-\u0010X\u001a\u00020\u00142#\u0010+\u001a\u001f\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0010H\u0016J7\u0010Z\u001a\u00020\u00142-\u0010+\u001a)\u0012#\u0012!\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(3\u0012\u0004\u0012\u00020\u00140\u0010j\u0002`4\u0018\u00010\u0018H\u0016JB\u0010[\u001a\u00020\u001428\u0010+\u001a4\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\\\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(]\u0012\u0004\u0012\u00020\u0014\u0018\u00010/H\u0016J?\u0010^\u001a\u00020\u001425\u0010+\u001a1\u0012+\u0012)\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020*\u0018\u00010\u001c¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020\u00140\u0010j\u0002`,\u0018\u00010\u0018H\u0016J-\u0010_\u001a\u00020\u00142#\u0010+\u001a\u001f\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0010H\u0016J-\u0010`\u001a\u00020\u00142#\u0010+\u001a\u001f\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0010H\u0016J-\u0010a\u001a\u00020\u00142#\u0010+\u001a\u001f\u0012\u0013\u0012\u00110%¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(b\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0010H\u0016J-\u0010c\u001a\u00020\u00142#\u0010+\u001a\u001f\u0012\u0013\u0012\u00110%¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(b\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0010H\u0016J?\u0010d\u001a\u00020\u001425\u0010+\u001a1\u0012+\u0012)\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020:\u0018\u00010\u001c¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020\u00140\u0010j\u0002`;\u0018\u00010\u0018H\u0016J-\u0010e\u001a\u00020\u00142#\u0010+\u001a\u001f\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0010H\u0016J7\u0010f\u001a\u00020\u00142-\u0010+\u001a)\u0012#\u0012!\u0012\u0013\u0012\u00110>¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(?\u0012\u0004\u0012\u00020\u00140\u0010j\u0002`@\u0018\u00010\u0018H\u0016J7\u0010g\u001a\u00020\u00142-\u0010+\u001a)\u0012#\u0012!\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020\u00140\u0010j\u0002`B\u0018\u00010\u0018H\u0016J\b\u0010h\u001a\u00020\u0014H\u0016J\b\u0010i\u001a\u00020\u0014H\u0016J\u0018\u0010j\u001a\u00020\u00142\u0006\u0010k\u001a\u00020%2\u0006\u0010l\u001a\u00020%H\u0016J!\u0010m\u001a\u00020\u00142\u0012\u0010n\u001a\n\u0012\u0006\b\u0001\u0012\u00020o0\u001c\"\u00020oH\u0016¢\u0006\u0002\u0010pJ\b\u0010q\u001a\u00020\u0014H\u0016J\u001a\u0010r\u001a\u00020\u00142\u0006\u0010s\u001a\u00020tH\u0016ø\u0001\u0000¢\u0006\u0004\bu\u0010vJ\b\u0010w\u001a\u00020\u0014H\u0016J\u0010\u0010x\u001a\u00020yH\u0016ø\u0001\u0000¢\u0006\u0002\u0010zJ\u0010\u0010{\u001a\u00020\u00142\u0006\u0010|\u001a\u00020}H\u0002J\u0010\u0010~\u001a\u00020\u00142\u0006\u0010|\u001a\u00020}H\u0002J\u0010\u0010\u007f\u001a\u00020\u00142\u0006\u0010|\u001a\u00020}H\u0002J\u0011\u0010\u0080\u0001\u001a\u00020\u00142\u0006\u0010|\u001a\u00020}H\u0002J\u0012\u0010\u0081\u0001\u001a\u00020\u00142\u0007\u0010\u0082\u0001\u001a\u00020}H\u0003J\u0011\u0010\u0083\u0001\u001a\u00020\u00142\u0006\u0010|\u001a\u00020}H\u0002J\u0011\u0010\u0084\u0001\u001a\u00020\u00142\u0006\u0010|\u001a\u00020}H\u0002J\u0011\u0010\u0085\u0001\u001a\u00020\u00142\u0006\u0010|\u001a\u00020}H\u0003J\u0011\u0010\u0086\u0001\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0005H\u0016J\u001a\u0010\u0087\u0001\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u00052\u0007\u0010\u0088\u0001\u001a\u00020\u0005H\u0016J\u001a\u0010\u0089\u0001\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u00052\u0007\u0010\u008a\u0001\u001a\u00020\u0005H\u0016J%\u0010\u008b\u0001\u001a\u00020\u00142\u0007\u0010\u008c\u0001\u001a\u00020%2\u0007\u0010\u0082\u0001\u001a\u00020}H\u0097@ø\u0001\u0000¢\u0006\u0003\u0010\u008d\u0001J\t\u0010\u008e\u0001\u001a\u00020\u0014H\u0016J\t\u0010\u008f\u0001\u001a\u00020\u0014H\u0016J\t\u0010\u0090\u0001\u001a\u00020\u0014H\u0016J\t\u0010\u0091\u0001\u001a\u00020\u0014H\u0016J\t\u0010\u0092\u0001\u001a\u00020\u0014H\u0016J\t\u0010\u0093\u0001\u001a\u00020\u0014H\u0016J\t\u0010\u0094\u0001\u001a\u00020\u0014H\u0016J\t\u0010\u0095\u0001\u001a\u00020\u0014H\u0016J\t\u0010\u0096\u0001\u001a\u00020\u0014H\u0016J\t\u0010\u0097\u0001\u001a\u00020\u0014H\u0016J\t\u0010\u0098\u0001\u001a\u00020\u0014H\u0016J\t\u0010\u0099\u0001\u001a\u00020\u0014H\u0016J\t\u0010\u009a\u0001\u001a\u00020\u0014H\u0016J\t\u0010\u009b\u0001\u001a\u00020\u0014H\u0016J\t\u0010\u009c\u0001\u001a\u00020\u0014H\u0016J\t\u0010\u009d\u0001\u001a\u00020\u0014H\u0016J\t\u0010\u009e\u0001\u001a\u00020\u0014H\u0016J\t\u0010\u009f\u0001\u001a\u00020\u0014H\u0016J\t\u0010 \u0001\u001a\u00020\u0014H\u0016J\t\u0010¡\u0001\u001a\u00020\u0014H\u0016J\t\u0010¢\u0001\u001a\u00020\u0014H\u0016J\t\u0010£\u0001\u001a\u00020\u0014H\u0002J\u001c\u0010¤\u0001\u001a\u00020\u00142\u0006\u0010s\u001a\u00020tH\u0016ø\u0001\u0000¢\u0006\u0005\b¥\u0001\u0010vJ\t\u0010¦\u0001\u001a\u00020\u0014H\u0016J:\u0010§\u0001\u001a\u00020\u00142\b\u0010¨\u0001\u001a\u00030©\u00012\u0007\u0010ª\u0001\u001a\u00020t2\u0007\u0010«\u0001\u001a\u00020t2\u0007\u0010¬\u0001\u001a\u00020tH\u0016ø\u0001\u0000¢\u0006\u0006\b\u00ad\u0001\u0010®\u0001J\u0012\u0010¯\u0001\u001a\u00020\u00142\u0007\u0010°\u0001\u001a\u00020%H\u0016J\u0012\u0010±\u0001\u001a\u00020\u00142\u0007\u0010²\u0001\u001a\u00020%H\u0016J\u001c\u0010³\u0001\u001a\u00020\u00142\b\u0010¨\u0001\u001a\u00030©\u00012\u0007\u0010´\u0001\u001a\u00020%H\u0002J6\u0010µ\u0001\u001a\u00020%2\t\b\u0002\u0010ª\u0001\u001a\u00020t2\t\b\u0002\u0010«\u0001\u001a\u00020t2\t\b\u0002\u0010¬\u0001\u001a\u00020tH\u0002ø\u0001\u0000¢\u0006\u0006\b¶\u0001\u0010·\u0001R$\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR+\u0010\u000f\u001a\u001f\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\u0015\u001a\u001f\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R5\u0010\u0017\u001a)\u0012#\u0012!\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u00140\u0010j\u0002`\u001a\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R1\u0010\u001b\u001a%\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u001d0\u001c¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000RG\u0010\u001f\u001a;\u0012/\u0012-\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00050 j\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u0005`\"¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(#\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010$\u001a\u001f\u0012\u0013\u0012\u00110%¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R5\u0010'\u001a)\u0012#\u0012!\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u00140\u0010j\u0002`(\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R=\u0010)\u001a1\u0012+\u0012)\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020*\u0018\u00010\u001c¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020\u00140\u0010j\u0002`,\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010-\u001a\u001f\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R@\u0010.\u001a4\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(0\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(1\u0012\u0004\u0012\u00020\u0014\u0018\u00010/X\u0082\u000e¢\u0006\u0002\n\u0000R5\u00102\u001a)\u0012#\u0012!\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(3\u0012\u0004\u0012\u00020\u00140\u0010j\u0002`4\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R+\u00105\u001a\u001f\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R+\u00107\u001a\u001f\u0012\u0013\u0012\u00110!¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R+\u00108\u001a\u001f\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(3\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R=\u00109\u001a1\u0012+\u0012)\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020:\u0018\u00010\u001c¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020\u00140\u0010j\u0002`;\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010<\u001a\u001f\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R5\u0010=\u001a)\u0012#\u0012!\u0012\u0013\u0012\u00110>¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(?\u0012\u0004\u0012\u00020\u00140\u0010j\u0002`@\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R5\u0010A\u001a)\u0012#\u0012!\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(+\u0012\u0004\u0012\u00020\u00140\u0010j\u0002`B\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010C\u001a\u001f\u0012\u0013\u0012\u00110%¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010D\u001a\u001a\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020%\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0E0/X\u0082\u0004¢\u0006\u0002\n\u0000R,\u0010F\u001a \u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020%\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0E0GX\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010H\u001a\u001a\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020%\u0012\n\u0012\b\u0012\u0004\u0012\u00020:0E0/X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010I\u001a\b\u0012\u0004\u0012\u00020*0EX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010J\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bK\u0010\u000eR\u001e\u0010L\u001a\u00020>2\u0006\u0010\u0004\u001a\u00020>@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bM\u0010N\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006¹\u0001"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/disinfection/DisinfectRobotPeripherals;", "Lcom/pudutech/robot/peripherals/common/CommonRobotPeripherals;", "Lcom/pudutech/robot/peripherals/disinfection/IDisinfectRobotPeripherals;", "()V", "<set-?>", "", "batteryOpenState", "getBatteryOpenState", "()Z", "setBatteryOpenState", "(Z)V", "", "liquidLevel", "getLiquidLevel", "()D", "moduleOpenStatusListener", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "open", "", "onBatteryBoxOpenStatus", "p0", "onBatteryBoxOpenStatusListeners", "Ljava/util/concurrent/CopyOnWriteArrayList;", "arg", "Lcom/pudutech/robot/peripherals/disinfection/device/OnBatteryBoxOpenStatusListener;", "onBatteryChargeErrorListener", "", "Lcom/pudutech/robot/peripherals/disinfection/BatteryChargeError;", NotificationCompat.CATEGORY_ERROR, "onBatteryCommunicateErrorListener", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "batteryCommunicateError", "onFirstBatteryPowerListener", "", "p1", "onHumanSensorDetectListeners", "Lcom/pudutech/robot/peripherals/disinfection/device/OnHumanSensorDetectSignalListener;", "onSprayDeviceErrorListeners", "Lcom/pudutech/robot/peripherals/disinfection/SprayDeviceError;", "l", "Lcom/pudutech/robot/peripherals/disinfection/device/OnSprayDeviceErrorListener;", "onSprayDeviceOpenListener", "onSprayLiquidLevelListener", "Lkotlin/Function2;", "waterBoxLevel", "sprayBoxLevel", "onSprayLiquidLevelStatusListeners", "b", "Lcom/pudutech/robot/peripherals/disinfection/device/OnSprayLiquidLevelStatusChangeListener;", "onSprayMagneticConfigStatusListener", "success", "onSprayMagneticTypeListener", "onSpraySpringOpenStatusListener", "onUvcDeviceErrorListener", "Lcom/pudutech/robot/peripherals/disinfection/UvcLampDeviceError;", "Lcom/pudutech/robot/peripherals/disinfection/device/OnUvLampDeviceErrorChangeListener;", "onUvcDeviceOpenListener", "onUvcDevicePlateOpenStateListener", "Lcom/pudutech/robot/peripherals/disinfection/OpenState;", "state", "Lcom/pudutech/robot/peripherals/disinfection/device/OnUvLampPlateOpenStatusListener;", "onWaterBoxLiquidLevelListeners", "Lcom/pudutech/robot/peripherals/disinfection/device/OnLiquidLevelChangeListener;", "onZeroBatteryPowerListener", "parseBatteryChargeError", "", "parseSprayDeviceError", "Lkotlin/Function3;", "parseUvcLampDeviceError", "resetError", "sprayChamberLevel", "getSprayChamberLevel", "uvcLampPlateOpenState", "getUvcLampPlateOpenState", "()Lcom/pudutech/robot/peripherals/disinfection/OpenState;", "addBatteryBoxOpenStatus", "addBatteryBoxOpenStatusListener", "addBatteryChargeErrorListener", "list", "addBatteryCommunicateErrorListener", "batteryError", "addHumanSensorDetectSignalListener", "addMagneticConfigStatusListener", "addMagneticTypeListener", "addModuleOpenStatusListener", "openStatus", "addOnSprayLiquidLevelStatusListener", "addSprayDeviceLiquidLevelListener", "liquid", "chamberLevel", "addSprayDeviceOccurErrorListener", "addSprayDeviceOpenListener", "addSpringOpenStatusListener", "addTheFirstPowerListener", "battery", "addTheZeroPowerListener", "addUvcLampDeviceOccurErrorListener", "addUvcLampDeviceOpenListener", "addUvcLampDevicePlateOpenStateListener", "addWaterBoxLiquidLevelListener", "backFlowSprayLiquid", "bootModule", "calibrationSpray", "water", "fog", "closeDevice", UtilityConfig.KEY_DEVICE_INFO, "Lcom/pudutech/robot/peripherals/disinfection/DeviceName;", "([Lcom/pudutech/robot/peripherals/disinfection/DeviceName;)V", "closeModule", "configMagnetic", "byte", "Lkotlin/UByte;", "configMagnetic-7apg3OU", "(B)V", "getMagneticType", "getRecvCmds", "Lkotlin/UByteArray;", "()[B", "handleBatterCommunicationError", "bytes", "", "handleBatteryBoxOpenStatus", "handleBatteryChargeError", "handleBatteryHead82Protocol", "handleCanDataHead84Protocol", "data", "handleMagneticAndSpringData", "handleSprayDeviceData", "handleUvcLampDeviceData", "openHumanSensorDevice", "openSprayDevice", "spray", "openUvcLampDevice", "openUvc", "parseData", "id", "(I[BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeAndClearSprayDeviceErrorListener", "removeAndClearSprayLiquidLevelStatusListener", "removeAndClearUvcLampDeviceErrorListener", "removeAndClearUvcLampDevicePlateOpenStateListener", "removeBatteryBoxOpenStatusListener", "removeBatteryBoxOpenStatusListeners", "removeBatteryChargeErrorListener", "removeBatteryCommunicateErrorListener", "removeHumanSensorDetectSignalListener", "removeMagneticConfigStatusListener", "removeMagneticTypeListener", "removeModuleOpenStatusListener", "removeSprayDeviceLiquidLevelListener", "removeSprayDeviceOpenListener", "removeSpringOpenStatusListener", "removeTheFirstPowerListener", "removeTheZeroPowerListener", "removeUvcLampDeviceOpenListener", "removeWaterBoxLiquidLevelListener", "resetCustomCore", "resetSlamCore", "resetSprayDeviceError", "selectSpringType", "selectSpringType-7apg3OU", "sendQueryHumanSensorSignalCmd", "setLight", "led", "Lcom/pudutech/robot/peripherals/config/LightBeltType;", "red", "green", "blue", "setLight-Fh2MPcY", "(Lcom/pudutech/robot/peripherals/config/LightBeltType;BBB)V", "setMotorAngle", LinkFormat.DOMAIN, "setSpraySpeedRate", "rate", "showLight", "headRGB", "toRGBInt", "toRGBInt-b33U2AM", "(BBB)I", "Companion", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class DisinfectRobotPeripherals extends CommonRobotPeripherals implements IDisinfectRobotPeripherals {
    public static final String BATTERY_NUMBER_00 = "电池号0";
    public static final String BATTERY_NUMBER_01 = "电池号1";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy INSTANCE$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<DisinfectRobotPeripherals>() { // from class: com.pudutech.robot.peripherals.disinfection.DisinfectRobotPeripherals$Companion$INSTANCE$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final DisinfectRobotPeripherals invoke() {
            return new DisinfectRobotPeripherals(null);
        }
    });
    private static final String MAGNETIC_TYPE_DOWN = "摩乔25mm下通磁浮子";
    private static final String MAGNETIC_TYPE_NOTDEFINE = "未知类型";
    private static final String MAGNETIC_TYPE_UP = "摩乔25mm上通磁浮子";
    private static final String TAG = "DisinfectRobotPeripherals";
    private volatile boolean batteryOpenState;
    private double liquidLevel;
    private Function1<? super Boolean, Unit> moduleOpenStatusListener;
    private Function1<? super Boolean, Unit> onBatteryBoxOpenStatus;
    private CopyOnWriteArrayList<Function1<Boolean, Unit>> onBatteryBoxOpenStatusListeners;
    private Function1<? super BatteryChargeError[], Unit> onBatteryChargeErrorListener;
    private Function1<? super HashMap<String, Boolean>, Unit> onBatteryCommunicateErrorListener;
    private Function1<? super Integer, Unit> onFirstBatteryPowerListener;
    private CopyOnWriteArrayList<Function1<Boolean, Unit>> onHumanSensorDetectListeners;
    private CopyOnWriteArrayList<Function1<SprayDeviceError[], Unit>> onSprayDeviceErrorListeners;
    private Function1<? super Boolean, Unit> onSprayDeviceOpenListener;
    private Function2<? super Double, ? super Double, Unit> onSprayLiquidLevelListener;
    private CopyOnWriteArrayList<Function1<Boolean, Unit>> onSprayLiquidLevelStatusListeners;
    private Function1<? super Boolean, Unit> onSprayMagneticConfigStatusListener;
    private Function1<? super String, Unit> onSprayMagneticTypeListener;
    private Function1<? super Boolean, Unit> onSpraySpringOpenStatusListener;
    private CopyOnWriteArrayList<Function1<UvcLampDeviceError[], Unit>> onUvcDeviceErrorListener;
    private Function1<? super Boolean, Unit> onUvcDeviceOpenListener;
    private CopyOnWriteArrayList<Function1<OpenState, Unit>> onUvcDevicePlateOpenStateListener;
    private CopyOnWriteArrayList<Function1<Double, Unit>> onWaterBoxLiquidLevelListeners;
    private Function1<? super Integer, Unit> onZeroBatteryPowerListener;
    private final Function2<Integer, Integer, List<BatteryChargeError>> parseBatteryChargeError;
    private final Function3<Integer, Integer, Integer, List<SprayDeviceError>> parseSprayDeviceError;
    private final Function2<Integer, Integer, List<UvcLampDeviceError>> parseUvcLampDeviceError;
    private final List<SprayDeviceError> resetError;
    private double sprayChamberLevel;
    private OpenState uvcLampPlateOpenState;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[DeviceName.values().length];

        static {
            $EnumSwitchMapping$0[DeviceName.SprayDevice.ordinal()] = 1;
            $EnumSwitchMapping$0[DeviceName.UvCLampDevice.ordinal()] = 2;
            $EnumSwitchMapping$0[DeviceName.HumanSensor.ordinal()] = 3;
        }
    }

    /* renamed from: toRGBInt-b33U2AM, reason: not valid java name */
    private final int m4481toRGBIntb33U2AM(byte red, byte green, byte blue) {
        return ((red & 255) << 16) | ((green & 255) << 8) | (blue & 255);
    }

    private DisinfectRobotPeripherals() {
        this.uvcLampPlateOpenState = OpenState.CLOSED;
        this.resetError = new ArrayList();
        this.parseSprayDeviceError = new Function3<Integer, Integer, Integer, List<SprayDeviceError>>() { // from class: com.pudutech.robot.peripherals.disinfection.DisinfectRobotPeripherals$parseSprayDeviceError$1
            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ List<SprayDeviceError> invoke(Integer num, Integer num2, Integer num3) {
                return invoke(num.intValue(), num2.intValue(), num3.intValue());
            }

            public final List<SprayDeviceError> invoke(int i, int i2, int i3) {
                ArrayList arrayList = new ArrayList();
                if ((i3 & 1) != 0) {
                    arrayList.add(SprayDeviceError.WATER_TANK_SENSOR_ERROR);
                }
                if ((i3 & 2) != 0) {
                    arrayList.add(SprayDeviceError.SPRAY_CHAMBER_SENSOR_ERROR);
                }
                if ((i3 & 4) != 0) {
                    arrayList.add(SprayDeviceError.PUMP_ERROR);
                }
                if ((i3 & 8) != 0) {
                    arrayList.add(SprayDeviceError.SOLENOID_VALVE_ERROR);
                }
                if ((i3 & 16) != 0) {
                    arrayList.add(SprayDeviceError.BLOWER_CURRENT_ERROR);
                }
                if ((i3 & 32) != 0) {
                    arrayList.add(SprayDeviceError.SPRAY_DRIVE_CURRENT_ERROR_1);
                }
                if ((i3 & 64) != 0) {
                    arrayList.add(SprayDeviceError.SPRAY_DRIVE_CURRENT_ERROR_2);
                }
                if ((i3 & 128) != 0) {
                    arrayList.add(SprayDeviceError.SPRAY_DRIVE_CURRENT_ERROR_3);
                }
                if ((i2 & 1) != 0) {
                    arrayList.add(SprayDeviceError.SPRAY_DRIVE_CURRENT_ERROR_4);
                }
                if ((i2 & 2) != 0) {
                    arrayList.add(SprayDeviceError.SPRAY_DRIVE_VOLTAGE_ERROR_1);
                }
                if ((i2 & 4) != 0) {
                    arrayList.add(SprayDeviceError.SPRAY_DRIVE_VOLTAGE_ERROR_2);
                }
                if ((i2 & 8) != 0) {
                    arrayList.add(SprayDeviceError.SPRAY_DRIVE_VOLTAGE_ERROR_3);
                }
                if ((i2 & 16) != 0) {
                    arrayList.add(SprayDeviceError.SPRAY_DRIVE_VOLTAGE_ERROR_4);
                }
                if ((i2 & 32) != 0) {
                    arrayList.add(SprayDeviceError.LOWER_VOLTAGE);
                }
                if ((i2 & 64) != 0) {
                    arrayList.add(SprayDeviceError.LOWER_LIQUID);
                }
                if ((i2 & 128) != 0) {
                    arrayList.add(SprayDeviceError.LOWER_WATER_BOX);
                }
                if ((i & 1) != 0) {
                    arrayList.add(SprayDeviceError.SPRAY_SLOW_START_UP_ERROR);
                }
                if ((i & 2) != 0) {
                    arrayList.add(SprayDeviceError.SPRAY_DEVICE_POWER_OVERLOAD);
                }
                return arrayList;
            }
        };
        this.parseUvcLampDeviceError = new Function2<Integer, Integer, List<UvcLampDeviceError>>() { // from class: com.pudutech.robot.peripherals.disinfection.DisinfectRobotPeripherals$parseUvcLampDeviceError$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ List<UvcLampDeviceError> invoke(Integer num, Integer num2) {
                return invoke(num.intValue(), num2.intValue());
            }

            public final List<UvcLampDeviceError> invoke(int i, int i2) {
                ArrayList arrayList = new ArrayList();
                if ((i2 & 1) != 0) {
                    arrayList.add(UvcLampDeviceError.DRIVER_BOARD_NO_RESPONSE);
                }
                if ((i2 & 2) != 0) {
                    arrayList.add(UvcLampDeviceError.DRIVER_BOARD_COMM_ERROR);
                }
                if ((i2 & 4) != 0) {
                    arrayList.add(UvcLampDeviceError.DRIVER_CURRENT_ERROR_1);
                }
                if ((i2 & 8) != 0) {
                    arrayList.add(UvcLampDeviceError.DRIVER_CURRENT_ERROR_2);
                }
                if ((i2 & 16) != 0) {
                    arrayList.add(UvcLampDeviceError.DRIVER_CURRENT_ERROR_3);
                }
                if ((i2 & 32) != 0) {
                    arrayList.add(UvcLampDeviceError.DRIVER_CURRENT_ERROR_4);
                }
                if ((i2 & 64) != 0) {
                    arrayList.add(UvcLampDeviceError.DRIVER_VOLTAGE_ERROR_1);
                }
                if ((i2 & 128) != 0) {
                    arrayList.add(UvcLampDeviceError.DRIVER_VOLTAGE_ERROR_2);
                }
                if ((i & 1) != 0) {
                    arrayList.add(UvcLampDeviceError.DRIVER_VOLTAGE_ERROR_3);
                }
                if ((i & 2) != 0) {
                    arrayList.add(UvcLampDeviceError.DRIVER_VOLTAGE_ERROR_4);
                }
                if ((i & 4) != 0) {
                    arrayList.add(UvcLampDeviceError.LOWER_VOLTAGE);
                }
                if ((i & 8) != 0) {
                    arrayList.add(UvcLampDeviceError.RAYS_SLOW_START_UP_ERROR);
                }
                return arrayList;
            }
        };
        this.parseBatteryChargeError = new Function2<Integer, Integer, List<BatteryChargeError>>() { // from class: com.pudutech.robot.peripherals.disinfection.DisinfectRobotPeripherals$parseBatteryChargeError$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ List<BatteryChargeError> invoke(Integer num, Integer num2) {
                return invoke(num.intValue(), num2.intValue());
            }

            public final List<BatteryChargeError> invoke(int i, int i2) {
                ArrayList arrayList = new ArrayList();
                if ((i2 & 1) != 0) {
                    arrayList.add(BatteryChargeError.BATTERY_COMMUNICATION_FAIL_BIT0);
                }
                if ((i2 & 2) != 0) {
                    arrayList.add(BatteryChargeError.BATTERY_OVERFLOW_VOLTAGE_BIT1);
                }
                if ((i2 & 4) != 0) {
                    arrayList.add(BatteryChargeError.BATTERY_CHARGE_OVERFLOW_VOLTAGE_BIT2);
                }
                if ((i2 & 8) != 0) {
                    arrayList.add(BatteryChargeError.BATTERY_CHARGE_OVERFLOW_ELECTRIC_BIT3);
                }
                if ((i2 & 16) != 0) {
                    arrayList.add(BatteryChargeError.BATTERY_CHARGE_OVERTIME_BIT4);
                }
                if ((i2 & 32) != 0) {
                    arrayList.add(BatteryChargeError.BATTERIES_TEMPERATURE_TOO_HIGH_BIT5);
                }
                if ((i2 & 64) != 0) {
                    arrayList.add(BatteryChargeError.BATTERY_VOLTAGE_MISMATCH_BIT6);
                }
                if ((i2 & 128) != 0) {
                    arrayList.add(BatteryChargeError.BATTERY_CHARGING_UNDERVOLTAGE_BIT7);
                }
                if ((i & 1) != 0) {
                    arrayList.add(BatteryChargeError.BATTERY_LOW_POWER_BIT8);
                }
                if ((i & 2) != 0) {
                    arrayList.add(BatteryChargeError.BATTERY_CHARGING_LOWTEMPERATURE_FAILURE_BIT9);
                }
                if ((i & 4) != 0) {
                    arrayList.add(BatteryChargeError.BATTERY_DISCHARGE_HIGH_TEMPERATURE_WARNING_BIT10);
                }
                if ((i & 8) != 0) {
                    arrayList.add(BatteryChargeError.BATTERY_DISCHARGE_LOWER_TEMPERATURE_WARNING_BIT11);
                }
                if ((i & 16) != 0) {
                    arrayList.add(BatteryChargeError.BATTERY_POOR_CONTACT_BIT12);
                }
                if ((i & 32) != 0) {
                    arrayList.add(BatteryChargeError.BATTERY_CHARGING_PORT_FAILURE_BIT13);
                }
                if ((i & 64) != 0) {
                    arrayList.add(BatteryChargeError.BATTERY_DISCHARGING_PORT_FAILURE_BIT14);
                }
                return arrayList;
            }
        };
    }

    public /* synthetic */ DisinfectRobotPeripherals(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final double getLiquidLevel() {
        return this.liquidLevel;
    }

    public final double getSprayChamberLevel() {
        return this.sprayChamberLevel;
    }

    public final OpenState getUvcLampPlateOpenState() {
        return this.uvcLampPlateOpenState;
    }

    public final boolean getBatteryOpenState() {
        return this.batteryOpenState;
    }

    public final void setBatteryOpenState(boolean z) {
        this.batteryOpenState = z;
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void openHumanSensorDevice(boolean open) {
        Pdlog.m3273d(TAG, "openHumanSensorDevice： " + open);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new DisinfectRobotPeripherals$openHumanSensorDevice$1(this, open, null), 3, null);
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void openSprayDevice(boolean open, boolean spray) {
        Pdlog.m3273d(TAG, "openSprayDevice " + spray);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new DisinfectRobotPeripherals$openSprayDevice$1(this, open, spray, null), 3, null);
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void openUvcLampDevice(boolean open, boolean openUvc) {
        Pdlog.m3273d(TAG, "openUvCLampDevice " + openUvc);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new DisinfectRobotPeripherals$openUvcLampDevice$1(this, open, openUvc, null), 3, null);
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void backFlowSprayLiquid() {
        Pdlog.m3273d(TAG, "backFlowSprayLiquid");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new DisinfectRobotPeripherals$backFlowSprayLiquid$1(this, null), 3, null);
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void setSpraySpeedRate(int rate) {
        Pdlog.m3273d(TAG, "setSpraySpeedRate " + rate);
        Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = rate;
        if (rate < 1) {
            intRef.element = 1;
        } else if (rate > 4) {
            intRef.element = 4;
        }
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new DisinfectRobotPeripherals$setSpraySpeedRate$1(this, intRef, null), 3, null);
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void setMotorAngle(int d) {
        Pdlog.m3273d(TAG, "setMotorAngle " + d);
        if (d == 0) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new DisinfectRobotPeripherals$setMotorAngle$1(this, null), 3, null);
            return;
        }
        Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = d;
        if (intRef.element > 360 || intRef.element < -360) {
            intRef.element %= 360;
        }
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new DisinfectRobotPeripherals$setMotorAngle$2(this, intRef, null), 3, null);
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    /* renamed from: setLight-Fh2MPcY, reason: not valid java name */
    public void mo4485setLightFh2MPcY(LightBeltType led, byte red, byte green, byte blue) {
        Intrinsics.checkParameterIsNotNull(led, "led");
        showLight(LightBeltType.DisinfectionSprayHead, m4481toRGBIntb33U2AM(red, green, blue));
        showLight(LightBeltType.BottomChassis, m4481toRGBIntb33U2AM(red, green, blue));
    }

    private final void showLight(LightBeltType led, int headRGB) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new DisinfectRobotPeripherals$showLight$1(this, led, headRGB, null), 3, null);
    }

    /* renamed from: toRGBInt-b33U2AM$default, reason: not valid java name */
    static /* synthetic */ int m4482toRGBIntb33U2AM$default(DisinfectRobotPeripherals disinfectRobotPeripherals, byte b, byte b2, byte b3, int i, Object obj) {
        if ((i & 1) != 0) {
            b = 0;
        }
        if ((i & 2) != 0) {
            b2 = 0;
        }
        if ((i & 4) != 0) {
            b3 = 0;
        }
        return disinfectRobotPeripherals.m4481toRGBIntb33U2AM(b, b2, b3);
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    /* renamed from: selectSpringType-7apg3OU, reason: not valid java name */
    public void mo4484selectSpringType7apg3OU(byte r8) {
        Pdlog.m3273d(TAG, " selectSpringType " + UByte.m4563toStringimpl(r8));
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new DisinfectRobotPeripherals$selectSpringType$1(this, r8, null), 3, null);
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    /* renamed from: configMagnetic-7apg3OU, reason: not valid java name */
    public void mo4483configMagnetic7apg3OU(byte r8) {
        Pdlog.m3273d(TAG, "configMagnetic");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new DisinfectRobotPeripherals$configMagnetic$1(this, r8, null), 3, null);
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void getMagneticType() {
        Pdlog.m3273d(TAG, "getMagneticType");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new DisinfectRobotPeripherals$getMagneticType$1(this, null), 3, null);
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void calibrationSpray(int water, int fog) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new DisinfectRobotPeripherals$calibrationSpray$1(this, water, fog, null), 3, null);
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void addUvcLampDevicePlateOpenStateListener(CopyOnWriteArrayList<Function1<OpenState, Unit>> l) {
        this.onUvcDevicePlateOpenStateListener = l;
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void removeAndClearUvcLampDevicePlateOpenStateListener() {
        CopyOnWriteArrayList<Function1<OpenState, Unit>> copyOnWriteArrayList = this.onUvcDevicePlateOpenStateListener;
        if (copyOnWriteArrayList != null) {
            if (copyOnWriteArrayList != null) {
                copyOnWriteArrayList.clear();
            }
            this.onUvcDevicePlateOpenStateListener = (CopyOnWriteArrayList) null;
        }
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void addUvcLampDeviceOpenListener(Function1<? super Boolean, Unit> l) {
        this.onUvcDeviceOpenListener = l;
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void removeUvcLampDeviceOpenListener() {
        if (this.onUvcDeviceOpenListener != null) {
            this.onUvcDeviceOpenListener = (Function1) null;
        }
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void addUvcLampDeviceOccurErrorListener(CopyOnWriteArrayList<Function1<UvcLampDeviceError[], Unit>> l) {
        this.onUvcDeviceErrorListener = l;
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void removeAndClearUvcLampDeviceErrorListener() {
        CopyOnWriteArrayList<Function1<UvcLampDeviceError[], Unit>> copyOnWriteArrayList = this.onUvcDeviceErrorListener;
        if (copyOnWriteArrayList != null) {
            if (copyOnWriteArrayList != null) {
                copyOnWriteArrayList.clear();
            }
            this.onUvcDeviceErrorListener = (CopyOnWriteArrayList) null;
        }
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void addSprayDeviceOpenListener(Function1<? super Boolean, Unit> l) {
        this.onSprayDeviceOpenListener = l;
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void removeSprayDeviceOpenListener() {
        if (this.onSprayDeviceOpenListener != null) {
            this.onSprayDeviceOpenListener = (Function1) null;
        }
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void addOnSprayLiquidLevelStatusListener(CopyOnWriteArrayList<Function1<Boolean, Unit>> l) {
        this.onSprayLiquidLevelStatusListeners = l;
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void removeAndClearSprayLiquidLevelStatusListener() {
        CopyOnWriteArrayList<Function1<Boolean, Unit>> copyOnWriteArrayList = this.onSprayLiquidLevelStatusListeners;
        if (copyOnWriteArrayList != null) {
            if (copyOnWriteArrayList != null) {
                copyOnWriteArrayList.clear();
            }
            this.onSprayLiquidLevelStatusListeners = (CopyOnWriteArrayList) null;
        }
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void addSprayDeviceOccurErrorListener(CopyOnWriteArrayList<Function1<SprayDeviceError[], Unit>> l) {
        this.onSprayDeviceErrorListeners = l;
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void removeAndClearSprayDeviceErrorListener() {
        CopyOnWriteArrayList<Function1<SprayDeviceError[], Unit>> copyOnWriteArrayList = this.onSprayDeviceErrorListeners;
        if (copyOnWriteArrayList != null) {
            if (copyOnWriteArrayList != null) {
                copyOnWriteArrayList.clear();
            }
            this.onSprayDeviceErrorListeners = (CopyOnWriteArrayList) null;
        }
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void addSprayDeviceLiquidLevelListener(Function2<? super Double, ? super Double, Unit> l) {
        this.onSprayLiquidLevelListener = l;
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void removeSprayDeviceLiquidLevelListener() {
        if (this.onSprayLiquidLevelListener != null) {
            this.onSprayLiquidLevelListener = (Function2) null;
        }
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void addMagneticConfigStatusListener(Function1<? super Boolean, Unit> l) {
        this.onSprayMagneticConfigStatusListener = l;
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void removeMagneticConfigStatusListener() {
        if (this.onSprayMagneticConfigStatusListener != null) {
            this.onSprayMagneticConfigStatusListener = (Function1) null;
        }
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void addMagneticTypeListener(Function1<? super String, Unit> l) {
        this.onSprayMagneticTypeListener = l;
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void removeMagneticTypeListener() {
        if (this.onSprayMagneticTypeListener != null) {
            this.onSprayMagneticTypeListener = (Function1) null;
        }
    }

    @Override // com.pudutech.robot.peripherals.common.CommonRobotPeripherals
    public byte[] getRecvCmds() {
        return new byte[]{CANConfig.INSTANCE.getCAN_CMD_HEAD_84_PROTOCOL_FOR_CONTROL_SPRAY(), CANConfig.INSTANCE.getCAN_CMD_HEAD_88_PROTOCOL_FOR_UVC_RESULT(), CANConfig.INSTANCE.getCAN_CMD_HEAD_87_PROTOCOL_FOR_SPRAY_RESULT(), CANConfig.INSTANCE.getCAN_CMD_HEAD_89_PROTOCOL_FOR_UVC_CONFIG(), CANConfig.INSTANCE.getCAN_CMD_HEAD_82_PROTOCOL(), CANConfig.INSTANCE.getCAN_CMD_HEAD_47_PROTOCOL()};
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void addModuleOpenStatusListener(Function1<? super Boolean, Unit> l) {
        this.moduleOpenStatusListener = l;
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void removeModuleOpenStatusListener() {
        if (this.moduleOpenStatusListener != null) {
            this.moduleOpenStatusListener = (Function1) null;
        }
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void addSpringOpenStatusListener(Function1<? super Boolean, Unit> l) {
        this.onSpraySpringOpenStatusListener = l;
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void removeSpringOpenStatusListener() {
        if (this.onSpraySpringOpenStatusListener != null) {
            this.onSpraySpringOpenStatusListener = (Function1) null;
        }
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void addTheFirstPowerListener(Function1<? super Integer, Unit> l) {
        this.onFirstBatteryPowerListener = l;
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void addTheZeroPowerListener(Function1<? super Integer, Unit> l) {
        this.onZeroBatteryPowerListener = l;
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void removeTheZeroPowerListener() {
        if (this.onZeroBatteryPowerListener != null) {
            this.onZeroBatteryPowerListener = (Function1) null;
        }
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void removeTheFirstPowerListener() {
        if (this.onFirstBatteryPowerListener != null) {
            this.onFirstBatteryPowerListener = (Function1) null;
        }
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void addBatteryCommunicateErrorListener(Function1<? super HashMap<String, Boolean>, Unit> l) {
        this.onBatteryCommunicateErrorListener = l;
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void removeBatteryCommunicateErrorListener() {
        if (this.onBatteryCommunicateErrorListener != null) {
            this.onBatteryCommunicateErrorListener = (Function1) null;
        }
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void addBatteryChargeErrorListener(Function1<? super BatteryChargeError[], Unit> l) {
        this.onBatteryChargeErrorListener = l;
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void removeBatteryChargeErrorListener() {
        if (this.onBatteryChargeErrorListener != null) {
            this.onBatteryChargeErrorListener = (Function1) null;
        }
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void addBatteryBoxOpenStatus(Function1<? super Boolean, Unit> l) {
        this.onBatteryBoxOpenStatus = l;
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void removeBatteryBoxOpenStatusListener() {
        if (this.onBatteryBoxOpenStatus != null) {
            this.onBatteryBoxOpenStatus = (Function1) null;
        }
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void addHumanSensorDetectSignalListener(CopyOnWriteArrayList<Function1<Boolean, Unit>> l) {
        this.onHumanSensorDetectListeners = l;
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void removeHumanSensorDetectSignalListener() {
        CopyOnWriteArrayList<Function1<Boolean, Unit>> copyOnWriteArrayList = this.onHumanSensorDetectListeners;
        if (copyOnWriteArrayList != null) {
            if (copyOnWriteArrayList != null) {
                copyOnWriteArrayList.clear();
            }
            this.onHumanSensorDetectListeners = (CopyOnWriteArrayList) null;
        }
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void sendQueryHumanSensorSignalCmd() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new DisinfectRobotPeripherals$sendQueryHumanSensorSignalCmd$1(this, null), 3, null);
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void addWaterBoxLiquidLevelListener(CopyOnWriteArrayList<Function1<Double, Unit>> l) {
        this.onWaterBoxLiquidLevelListeners = l;
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void removeWaterBoxLiquidLevelListener() {
        CopyOnWriteArrayList<Function1<Double, Unit>> copyOnWriteArrayList = this.onWaterBoxLiquidLevelListeners;
        if (copyOnWriteArrayList != null) {
            if (copyOnWriteArrayList != null) {
                copyOnWriteArrayList.clear();
            }
            this.onWaterBoxLiquidLevelListeners = (CopyOnWriteArrayList) null;
        }
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void addBatteryBoxOpenStatusListener(CopyOnWriteArrayList<Function1<Boolean, Unit>> l) {
        this.onBatteryBoxOpenStatusListeners = l;
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void removeBatteryBoxOpenStatusListeners() {
        CopyOnWriteArrayList<Function1<Boolean, Unit>> copyOnWriteArrayList = this.onBatteryBoxOpenStatusListeners;
        if (copyOnWriteArrayList != null) {
            if (copyOnWriteArrayList != null) {
                copyOnWriteArrayList.clear();
            }
            this.onBatteryBoxOpenStatusListeners = (CopyOnWriteArrayList) null;
        }
    }

    /* compiled from: DisinfectRobotPeripherals.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/disinfection/DisinfectRobotPeripherals$Companion;", "", "()V", "BATTERY_NUMBER_00", "", "BATTERY_NUMBER_01", "INSTANCE", "Lcom/pudutech/robot/peripherals/disinfection/DisinfectRobotPeripherals;", "getINSTANCE", "()Lcom/pudutech/robot/peripherals/disinfection/DisinfectRobotPeripherals;", "INSTANCE$delegate", "Lkotlin/Lazy;", "MAGNETIC_TYPE_DOWN", "MAGNETIC_TYPE_NOTDEFINE", "MAGNETIC_TYPE_UP", "TAG", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        public final DisinfectRobotPeripherals getINSTANCE() {
            Lazy lazy = DisinfectRobotPeripherals.INSTANCE$delegate;
            Companion companion = DisinfectRobotPeripherals.INSTANCE;
            return (DisinfectRobotPeripherals) lazy.getValue();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Override // com.pudutech.robot.peripherals.common.CommonRobotPeripherals
    public Object parseData(int i, byte[] bArr, Continuation<? super Unit> continuation) {
        if (bArr == null) {
            Pdlog.m3273d(TAG, "parseData data is null");
            return Unit.INSTANCE;
        }
        byte m4528constructorimpl = UByte.m4528constructorimpl((byte) i);
        if (m4528constructorimpl == CANConfig.INSTANCE.getCAN_CMD_HEAD_87_PROTOCOL_FOR_SPRAY_RESULT()) {
            if (CANConfig.INSTANCE.getCAN_CMD_HEAD_87_PROTOCOL_FOR_SPRAY_RESULT() == UByte.m4528constructorimpl(bArr[0]) && CANConfig.INSTANCE.getCAN_CMD_MSG_07() == UByte.m4528constructorimpl(bArr[1])) {
                handleSprayDeviceData(bArr);
            }
        } else if (m4528constructorimpl == CANConfig.INSTANCE.getCAN_CMD_HEAD_88_PROTOCOL_FOR_UVC_RESULT()) {
            if (CANConfig.INSTANCE.getCAN_CMD_HEAD_88_PROTOCOL_FOR_UVC_RESULT() == UByte.m4528constructorimpl(bArr[0])) {
                handleUvcLampDeviceData(bArr);
            }
        } else if (m4528constructorimpl == CANConfig.INSTANCE.getCAN_CMD_HEAD_89_PROTOCOL_FOR_UVC_CONFIG()) {
            if (CANConfig.INSTANCE.getCAN_CMD_HEAD_89_PROTOCOL() == UByte.m4528constructorimpl(bArr[0]) && CANConfig.INSTANCE.getCAN_CMD_MSG_09() == UByte.m4528constructorimpl(bArr[1])) {
                handleMagneticAndSpringData(bArr);
            }
        } else if (m4528constructorimpl == CANConfig.INSTANCE.getCAN_CMD_HEAD_84_PROTOCOL_FOR_CONTROL_SPRAY()) {
            if (CANConfig.INSTANCE.getCAN_CMD_HEAD_84_PROTOCOL_FOR_CONTROL_SPRAY() == UByte.m4528constructorimpl(bArr[0])) {
                handleCanDataHead84Protocol(bArr);
            }
        } else if (m4528constructorimpl == CANConfig.INSTANCE.getCAN_CMD_HEAD_82_PROTOCOL()) {
            if (CANConfig.INSTANCE.getCAN_CMD_HEAD_82_PROTOCOL() == UByte.m4528constructorimpl(bArr[0]) && CANConfig.INSTANCE.getCAN_CMD_MSG_81() == UByte.m4528constructorimpl(bArr[1])) {
                handleBatteryHead82Protocol(bArr);
            } else if (CANConfig.INSTANCE.getCAN_CMD_HEAD_82_PROTOCOL() == UByte.m4528constructorimpl(bArr[0]) && CANConfig.INSTANCE.getCAN_CMD_MSG_84() == UByte.m4528constructorimpl(bArr[1])) {
                handleBatterCommunicationError(bArr);
            } else if (CANConfig.INSTANCE.getCAN_CMD_HEAD_82_PROTOCOL() == UByte.m4528constructorimpl(bArr[0]) && CANConfig.INSTANCE.getCAN_CMD_MSG_C0() == UByte.m4528constructorimpl(bArr[1])) {
                handleBatteryChargeError(bArr);
            }
        } else if (m4528constructorimpl == CANConfig.INSTANCE.getCAN_CMD_HEAD_47_PROTOCOL() && CANConfig.INSTANCE.getCAN_CMD_HEAD_47_PROTOCOL() == UByte.m4528constructorimpl(bArr[0])) {
            handleBatteryBoxOpenStatus(bArr);
        }
        return Unit.INSTANCE;
    }

    private final void handleBatteryBoxOpenStatus(byte[] bytes) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new DisinfectRobotPeripherals$handleBatteryBoxOpenStatus$1(this, bytes, null), 2, null);
    }

    private final void handleBatteryChargeError(byte[] bytes) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new DisinfectRobotPeripherals$handleBatteryChargeError$1(this, this.parseBatteryChargeError.invoke(Integer.valueOf(bytes[6]), Integer.valueOf(bytes[5])), null), 2, null);
    }

    private final void handleBatterCommunicationError(byte[] bytes) {
        HashMap hashMap = new HashMap();
        byte m4528constructorimpl = UByte.m4528constructorimpl(bytes[6]);
        if (m4528constructorimpl == UByte.m4528constructorimpl((byte) 0)) {
            hashMap.put(BATTERY_NUMBER_00, Boolean.valueOf(bytes[5] != 0));
        } else if (m4528constructorimpl == UByte.m4528constructorimpl((byte) 1)) {
            hashMap.put(BATTERY_NUMBER_01, Boolean.valueOf(bytes[5] != 0));
        }
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new DisinfectRobotPeripherals$handleBatterCommunicationError$1(this, hashMap, null), 2, null);
    }

    private final void handleBatteryHead82Protocol(byte[] bytes) {
        HashMap hashMap = new HashMap();
        byte m4528constructorimpl = UByte.m4528constructorimpl(bytes[6]);
        if (m4528constructorimpl == UByte.m4528constructorimpl((byte) 0)) {
            hashMap.put(BATTERY_NUMBER_00, Integer.valueOf(bytes[5]));
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new DisinfectRobotPeripherals$handleBatteryHead82Protocol$1(this, bytes, null), 2, null);
        } else if (m4528constructorimpl == UByte.m4528constructorimpl((byte) 1)) {
            hashMap.put(BATTERY_NUMBER_01, Integer.valueOf(bytes[5]));
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new DisinfectRobotPeripherals$handleBatteryHead82Protocol$2(this, bytes, null), 2, null);
        }
    }

    private final void handleMagneticAndSpringData(byte[] bytes) {
        byte m4528constructorimpl = UByte.m4528constructorimpl(bytes[2]);
        if (m4528constructorimpl == UByte.m4528constructorimpl((byte) 2)) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new DisinfectRobotPeripherals$handleMagneticAndSpringData$1(this, bytes, null), 2, null);
            return;
        }
        if (m4528constructorimpl == UByte.m4528constructorimpl((byte) 8)) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new DisinfectRobotPeripherals$handleMagneticAndSpringData$2(this, bytes, null), 2, null);
        } else if (m4528constructorimpl == UByte.m4528constructorimpl((byte) 6)) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new DisinfectRobotPeripherals$handleMagneticAndSpringData$3(this, bytes, null), 2, null);
        } else if (m4528constructorimpl == UByte.m4528constructorimpl((byte) 12)) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new DisinfectRobotPeripherals$handleMagneticAndSpringData$4(this, bytes, null), 2, null);
        }
    }

    private final void handleCanDataHead84Protocol(byte[] data) {
        if (UByte.m4528constructorimpl(data[0]) == CANConfig.INSTANCE.getCAN_CMD_HEAD_84_PROTOCOL_FOR_CONTROL_SPRAY()) {
            boolean z = (UByte.m4528constructorimpl(data[1]) & 255) != 0;
            Pdlog.m3273d(TAG, "handleCanDataHead84Protocol switch " + z);
            Function1<? super Boolean, Unit> function1 = this.moduleOpenStatusListener;
            if (function1 != null) {
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C5714x209708c8(function1, null, z), 2, null);
            }
        }
    }

    private final void handleUvcLampDeviceData(byte[] bytes) {
        byte m4528constructorimpl = UByte.m4528constructorimpl(bytes[2]);
        if (m4528constructorimpl == UByte.m4528constructorimpl((byte) 3)) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new DisinfectRobotPeripherals$handleUvcLampDeviceData$1(this, bytes, null), 3, null);
        } else if (m4528constructorimpl == UByte.m4528constructorimpl((byte) 4)) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new DisinfectRobotPeripherals$handleUvcLampDeviceData$2(this, bytes, null), 3, null);
        }
    }

    private final void handleSprayDeviceData(byte[] bytes) {
        byte m4528constructorimpl = UByte.m4528constructorimpl(bytes[2]);
        if (m4528constructorimpl == UByte.m4528constructorimpl((byte) 3)) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new DisinfectRobotPeripherals$handleSprayDeviceData$1(this, bytes, null), 3, null);
        } else if (m4528constructorimpl == UByte.m4528constructorimpl((byte) 4)) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new DisinfectRobotPeripherals$handleSprayDeviceData$2(this, bytes, null), 3, null);
        } else if (m4528constructorimpl == UByte.m4528constructorimpl((byte) 5)) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new DisinfectRobotPeripherals$handleSprayDeviceData$3(this, bytes, null), 2, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetSprayDeviceError() {
        try {
            this.resetError.clear();
            this.resetError.add(SprayDeviceError.SPRAY_RESET_ERROR);
            CopyOnWriteArrayList<Function1<SprayDeviceError[], Unit>> copyOnWriteArrayList = this.onSprayDeviceErrorListeners;
            if (copyOnWriteArrayList != null) {
                Iterator<T> it = copyOnWriteArrayList.iterator();
                while (it.hasNext()) {
                    Function1 function1 = (Function1) it.next();
                    Object[] array = this.resetError.toArray(new SprayDeviceError[0]);
                    if (array != null) {
                        function1.invoke(array);
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Pdlog.m3274e(TAG, "reset Error " + Log.getStackTraceString(e));
        }
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void bootModule() {
        try {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new DisinfectRobotPeripherals$bootModule$1(this, null), 3, null);
        } catch (Exception e) {
            e.printStackTrace();
            Pdlog.m3274e(TAG, "bootModule Error " + Log.getStackTraceString(e));
        }
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void resetSlamCore() {
        try {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new DisinfectRobotPeripherals$resetSlamCore$1(this, null), 3, null);
        } catch (Exception e) {
            e.printStackTrace();
            Pdlog.m3274e(TAG, "resetSlamCore error " + Log.getStackTraceString(e));
        }
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void resetCustomCore() {
        try {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new DisinfectRobotPeripherals$resetCustomCore$1(this, null), 3, null);
        } catch (Exception e) {
            e.printStackTrace();
            Pdlog.m3274e(TAG, "resetCustomCore error " + Log.getStackTraceString(e));
        }
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void closeModule() {
        try {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new DisinfectRobotPeripherals$closeModule$1(this, null), 3, null);
        } catch (Exception e) {
            e.printStackTrace();
            Pdlog.m3274e(TAG, "closeModule Error " + Log.getStackTraceString(e));
        }
    }

    @Override // com.pudutech.robot.peripherals.disinfection.IDisinfectRobotPeripherals
    public void closeDevice(DeviceName... device) {
        Intrinsics.checkParameterIsNotNull(device, "device");
        for (DeviceName deviceName : device) {
            int i = WhenMappings.$EnumSwitchMapping$0[deviceName.ordinal()];
            if (i == 1) {
                Pdlog.m3273d(TAG, "close SprayDevice");
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new DisinfectRobotPeripherals$closeDevice$$inlined$forEach$lambda$1(null, this), 3, null);
            } else if (i == 2) {
                Pdlog.m3273d(TAG, "close UvcLampDevice");
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new DisinfectRobotPeripherals$closeDevice$$inlined$forEach$lambda$2(null, this), 3, null);
            } else if (i == 3) {
                Pdlog.m3273d(TAG, "closeDevice: close HumanSensor");
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new DisinfectRobotPeripherals$closeDevice$$inlined$forEach$lambda$3(null, this), 3, null);
            }
        }
    }
}
