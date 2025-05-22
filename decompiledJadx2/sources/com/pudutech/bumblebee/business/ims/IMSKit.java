package com.pudutech.bumblebee.business.ims;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.ims.IMSKit;
import com.pudutech.bumblebee.business.ims.config.CallPoint;
import com.pudutech.bumblebee.business.ims.config.DestinationType;
import com.pudutech.bumblebee.business.ims.config.MsgType;
import com.pudutech.bumblebee.business.ims.listener.IMsgSentStatusListener;
import com.pudutech.bumblebee.business.ims.listener.OnMsgReceivedListener;
import com.pudutech.bumblebee.business.ims.lora.Channel;
import com.pudutech.bumblebee.business.ims.lora.LoRaChannelManager2;
import com.pudutech.bumblebee.business.ims.utils.ShortUUID;
import com.pudutech.bumblebee.business.ims.utils.SystemTool;
import com.pudutech.bumblebee.business.protobuf.MessageProtobuf;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Charsets;
import kotlinx.coroutines.Job;

/* compiled from: IMSKit.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 o2\u00020\u0001:\u0002opB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u008b\u0001\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00142\u0006\u0010!\u001a\u00020\u00042\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00042'\b\u0002\u0010#\u001a!\u0012\u0013\u0012\u00110%¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u00010$2>\b\u0002\u0010)\u001a8\u0012\u0015\u0012\u0013\u0018\u00010%¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(+\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u00010*J\u0083\u0001\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00142\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00042'\b\u0002\u0010#\u001a!\u0012\u0013\u0012\u00110%¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u00010$2>\b\u0002\u0010)\u001a8\u0012\u0015\u0012\u0013\u0018\u00010%¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(+\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u00010*Jo\u0010,\u001a\u00020\u001f2'\b\u0002\u0010#\u001a!\u0012\u0013\u0012\u00110%¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u00010$2>\b\u0002\u0010)\u001a8\u0012\u0015\u0012\u0013\u0018\u00010%¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(+\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u00010*J\u0006\u0010-\u001a\u00020\u001fJ\b\u0010.\u001a\u00020\u001fH\u0002J\u000e\u0010/\u001a\u00020\u001f2\u0006\u00100\u001a\u000201Jo\u00102\u001a\u00020\u001f2'\b\u0002\u0010#\u001a!\u0012\u0013\u0012\u00110%¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u00010$2>\b\u0002\u0010)\u001a8\u0012\u0015\u0012\u0013\u0018\u00010%¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(+\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u00010*Jo\u00103\u001a\u00020\u001f2'\b\u0002\u0010#\u001a!\u0012\u0013\u0012\u00110%¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u00010$2>\b\u0002\u0010)\u001a8\u0012\u0015\u0012\u0013\u0018\u00010%¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(+\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u00010*Jw\u00104\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00142'\b\u0002\u0010#\u001a!\u0012\u0013\u0012\u00110%¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u00010$2>\b\u0002\u0010)\u001a8\u0012\u0015\u0012\u0013\u0018\u00010%¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(+\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u00010*Jo\u00105\u001a\u00020\u001f2'\b\u0002\u0010#\u001a!\u0012\u0013\u0012\u00110%¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u00010$2>\b\u0002\u0010)\u001a8\u0012\u0015\u0012\u0013\u0018\u00010%¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(+\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u00010*J0\u00106\u001a\u00020\u001f2\u0012\b\u0002\u00107\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u0001082\u0012\b\u0002\u00109\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u000108H\u0002J6\u0010:\u001a\u00020\u001f2\u0006\u0010;\u001a\u00020\u00142\u0012\b\u0002\u0010<\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u0001082\u0012\b\u0002\u0010=\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u000108J\u0010\u0010>\u001a\u0004\u0018\u00010?2\u0006\u0010@\u001a\u00020AJ\u0018\u0010B\u001a\u0004\u0018\u00010?2\u0006\u0010@\u001a\u00020A2\u0006\u0010!\u001a\u00020\u0004J\u000e\u0010C\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010D\u001a\u00020\u001f2\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010E\u001a\u00020\u0014Jo\u0010F\u001a\u00020\u001f2'\b\u0002\u0010#\u001a!\u0012\u0013\u0012\u00110%¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u00010$2>\b\u0002\u0010)\u001a8\u0012\u0015\u0012\u0013\u0018\u00010%¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(+\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u00010*Jo\u0010G\u001a\u00020\u001f2'\b\u0002\u0010#\u001a!\u0012\u0013\u0012\u00110%¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u00010$2>\b\u0002\u0010)\u001a8\u0012\u0015\u0012\u0013\u0018\u00010%¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(+\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u00010*J\u0006\u0010H\u001a\u00020\u001fJ\u000e\u0010I\u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u0004Jo\u0010J\u001a\u00020\u001f2'\b\u0002\u0010#\u001a!\u0012\u0013\u0012\u00110%¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u00010$2>\b\u0002\u0010)\u001a8\u0012\u0015\u0012\u0013\u0018\u00010%¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(+\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u00010*J\u000e\u0010K\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0014J\u007f\u0010L\u001a\u00020\u001f2\u0006\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020P2'\b\u0002\u0010#\u001a!\u0012\u0013\u0012\u00110%¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u00010$2>\b\u0002\u0010)\u001a8\u0012\u0015\u0012\u0013\u0018\u00010%¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(+\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u00010*Jo\u0010Q\u001a\u00020\u001f2'\b\u0002\u0010#\u001a!\u0012\u0013\u0012\u00110%¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u00010$2>\b\u0002\u0010)\u001a8\u0012\u0015\u0012\u0013\u0018\u00010%¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(+\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u00010*Jo\u0010R\u001a\u00020\u001f2'\b\u0002\u0010#\u001a!\u0012\u0013\u0012\u00110%¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u00010$2>\b\u0002\u0010)\u001a8\u0012\u0015\u0012\u0013\u0018\u00010%¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(+\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u00010*J\u008d\u0001\u0010S\u001a\u00020\u001f2\b\u0010(\u001a\u0004\u0018\u00010%2'\b\u0002\u0010#\u001a!\u0012\u0013\u0012\u00110%¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u00010$2>\b\u0002\u0010)\u001a8\u0012\u0015\u0012\u0013\u0018\u00010%¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(+\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u00010*2\b\b\u0002\u0010T\u001a\u00020\u00142\b\b\u0002\u0010U\u001a\u00020\u0014J\u001a\u0010V\u001a\u00020\u001f2\b\u0010W\u001a\u0004\u0018\u00010X2\b\u0010Y\u001a\u0004\u0018\u00010ZJ\u001a\u0010[\u001a\u00020\u001f2\b\u0010W\u001a\u0004\u0018\u00010X2\b\u0010Y\u001a\u0004\u0018\u00010ZJR\u0010\\\u001a\u00020\u001f2\u0006\u0010@\u001a\u00020A2\u0006\u0010]\u001a\u00020\u00042\u0016\u0010^\u001a\u0012\u0012\u0004\u0012\u00020`0_j\b\u0012\u0004\u0012\u00020``a2\u0006\u0010b\u001a\u00020\u00042\b\u0010c\u001a\u0004\u0018\u00010\u00042\u0006\u0010d\u001a\u00020\u00142\u0006\u0010Y\u001a\u00020eH\u0002Jw\u0010f\u001a\u00020\u001f2\u0006\u0010]\u001a\u00020\u00042\u0016\u0010^\u001a\u0012\u0012\u0004\u0012\u00020`0_j\b\u0012\u0004\u0012\u00020``a2\u0012\b\u0002\u0010g\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u0001082\u0012\b\u0002\u0010h\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u0001082'\b\u0002\u0010i\u001a!\u0012\u0013\u0012\u001101¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(j\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u00010$Jw\u0010k\u001a\u00020\u001f2\u0006\u0010]\u001a\u00020\u00042\u0016\u0010^\u001a\u0012\u0012\u0004\u0012\u00020`0_j\b\u0012\u0004\u0012\u00020``a2\u0012\b\u0002\u0010l\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u0001082\u0012\b\u0002\u0010m\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u0001082'\b\u0002\u0010n\u001a!\u0012\u0013\u0012\u001101¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(j\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u00010$R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\u000eX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006q"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/IMSKit;", "", "()V", "centralControlMac", "", "getCentralControlMac", "()Ljava/lang/String;", "setCentralControlMac", "(Ljava/lang/String;)V", "closeBeeperBroadcastTask", "Lcom/pudutech/bumblebee/business/ims/CloseBeeperBroadcastTask;", "closeBroadcastTask", "Lcom/pudutech/bumblebee/business/ims/CloseBroadcastTask;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "isOpenedBroadcast", "", "()Z", "setOpenedBroadcast", "(Z)V", "receiver", "robotStatusReportTask", "Lcom/pudutech/bumblebee/business/ims/RobotStatusReportTask;", "sender", "syncMapTask", "Lkotlinx/coroutines/Job;", "addBeeperBroadcastControl", "", "isOpen", "msgId", "centralControlCode", "onSentSucceed", "Lkotlin/Function1;", "Lcom/pudutech/bumblebee/business/protobuf/MessageProtobuf$Msg;", "Lkotlin/ParameterName;", "name", NotificationCompat.CATEGORY_MESSAGE, "onSentFailed", "Lkotlin/Function2;", "reason", "addDevice", "cancelSyncMap", "cancelSyncMapTask", "changeReportInterval", "delayTime", "", "checkMapSyncStatus", "checkTaskPartitionTableSyncStatus", "controlCentralControlBroadcast", "deleteDevice", "deleteTaskPartitionTable", "onDeleteTaskPartitionTableSucceed", "Lkotlin/Function0;", "onDeleteTaskPartitionTableFailed", "enableTaskPartition", "isEnable", "onSucceed", "onFailed", "generateCommonMsgBuilder", "Lcom/pudutech/bumblebee/business/protobuf/MessageProtobuf$Msg$Builder;", "msgType", "Lcom/pudutech/bumblebee/business/ims/config/MsgType;", "generateCommonMsgBuilderById", "getMac", "init", "isReportingRobotStatus", "queryHasUnallocatedTask", "queryMapVersion", "release", "removeMsg", "reportCurrentTime", "reportRobotStatus", "reportTaskStatus", "task", "Lcom/pudutech/bumblebee/business/protobuf/MessageProtobuf$Task;", "callPoint", "Lcom/pudutech/bumblebee/business/protobuf/MessageProtobuf$CallPoint;", "requestTask", "resetCentralControl", "sendMsg", "checkMac", "joinResendManager", "setupChannel", "chl", "Lcom/pudutech/bumblebee/business/ims/lora/Channel;", "listener", "Lcom/pudutech/bumblebee/business/ims/lora/LoRaChannelManager2$OnSetupChannelListener;", "setupLocalChannel", "syncCallPointData", "version", "callPointList", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/business/ims/config/CallPoint;", "Lkotlin/collections/ArrayList;", "frameHead", "frameNext", "isFrameEnd", "Lcom/pudutech/bumblebee/business/ims/IMSKit$OnSyncCallPointListener;", "syncMap", "onSyncMapSucceed", "onSyncMapFailed", "onSyncMapPercent", "percent", "syncTaskPartitionTable", "onSyncTaskPartitionTableSucceed", "onSyncTaskPartitionTableFailed", "onSyncTaskPartitionTablePercent", "Companion", "OnSyncCallPointListener", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class IMSKit {
    public static final String BROADCAST_MAC = "FFFF";
    public static final String TAG = "FreddyChen";
    private String centralControlMac;
    private CloseBeeperBroadcastTask closeBeeperBroadcastTask;
    private CloseBroadcastTask closeBroadcastTask;
    public Context context;
    private boolean isOpenedBroadcast;
    private String receiver;
    private RobotStatusReportTask robotStatusReportTask;
    private String sender;
    private Job syncMapTask;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy instance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<IMSKit>() { // from class: com.pudutech.bumblebee.business.ims.IMSKit$Companion$instance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final IMSKit invoke() {
            return new IMSKit(null);
        }
    });

    /* compiled from: IMSKit.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bb\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J \u0010\u0004\u001a\u00020\u00032\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bH&¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/IMSKit$OnSyncCallPointListener;", "", "onFailed", "", "onSucceed", "list", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/business/ims/config/CallPoint;", "Lkotlin/collections/ArrayList;", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public interface OnSyncCallPointListener {
        void onFailed();

        void onSucceed(ArrayList<CallPoint> list);
    }

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[MsgType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[MsgType.BroadcastControl.ordinal()] = 1;
            $EnumSwitchMapping$1 = new int[MsgType.values().length];
            $EnumSwitchMapping$1[MsgType.BroadcastControl.ordinal()] = 1;
        }
    }

    private IMSKit() {
    }

    public /* synthetic */ IMSKit(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final Context getContext() {
        Context context = this.context;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        return context;
    }

    public final void setContext(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "<set-?>");
        this.context = context;
    }

    public final String getCentralControlMac() {
        return this.centralControlMac;
    }

    public final void setCentralControlMac(String str) {
        this.centralControlMac = str;
    }

    /* renamed from: isOpenedBroadcast, reason: from getter */
    public final boolean getIsOpenedBroadcast() {
        return this.isOpenedBroadcast;
    }

    public final void setOpenedBroadcast(boolean z) {
        this.isOpenedBroadcast = z;
    }

    /* compiled from: IMSKit.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/IMSKit$Companion;", "", "()V", "BROADCAST_MAC", "", "TAG", "instance", "Lcom/pudutech/bumblebee/business/ims/IMSKit;", "getInstance", "()Lcom/pudutech/bumblebee/business/ims/IMSKit;", "instance$delegate", "Lkotlin/Lazy;", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        public final IMSKit getInstance() {
            Lazy lazy = IMSKit.instance$delegate;
            Companion companion = IMSKit.INSTANCE;
            return (IMSKit) lazy.getValue();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final String getMac(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return SystemTool.INSTANCE.getMac(context);
    }

    public final void init(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Pdlog.m3273d(TAG, "IMSKit init start");
        this.context = context;
        IMSClientFactory.INSTANCE.getIMSClient().init(context, null, new OnMsgReceivedListener());
        this.sender = getMac(context);
        this.robotStatusReportTask = new RobotStatusReportTask();
        RobotStatusReportTask robotStatusReportTask = this.robotStatusReportTask;
        if (robotStatusReportTask != null) {
            robotStatusReportTask.start();
        }
        Pdlog.m3273d(TAG, "IMSKit init end");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void controlCentralControlBroadcast$default(IMSKit iMSKit, boolean z, Function1 function1, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = (Function1) null;
        }
        if ((i & 4) != 0) {
            function2 = (Function2) null;
        }
        iMSKit.controlCentralControlBroadcast(z, function1, function2);
    }

    public final void controlCentralControlBroadcast(boolean isOpen, Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed) {
        MessageProtobuf.Msg.Builder broadcastControl;
        boolean z = true;
        Pdlog.m3273d(TAG, "controlCentralControlBroadcast() isOpen = " + isOpen);
        if (isOpen) {
            CloseBroadcastTask closeBroadcastTask = this.closeBroadcastTask;
            if (closeBroadcastTask != null) {
                closeBroadcastTask.release();
            }
            MessageProtobuf.Msg msg = null;
            this.closeBroadcastTask = (CloseBroadcastTask) null;
            MessageProtobuf.Msg.Builder generateCommonMsgBuilder = generateCommonMsgBuilder(MsgType.BroadcastControl);
            if (generateCommonMsgBuilder != null && (broadcastControl = generateCommonMsgBuilder.setBroadcastControl(MessageProtobuf.BroadcastControl.newBuilder().setEnable(true).build())) != null) {
                msg = broadcastControl.build();
            }
            sendMsg$default(this, msg, onSentSucceed, onSentFailed, false, false, 16, null);
            return;
        }
        String str = this.receiver;
        if (str != null && str.length() != 0) {
            z = false;
        }
        if (z) {
            return;
        }
        if (this.closeBroadcastTask == null) {
            this.closeBroadcastTask = new CloseBroadcastTask();
            CloseBroadcastTask closeBroadcastTask2 = this.closeBroadcastTask;
            if (closeBroadcastTask2 != null) {
                closeBroadcastTask2.start();
            }
        }
        CloseBroadcastTask closeBroadcastTask3 = this.closeBroadcastTask;
        if (closeBroadcastTask3 != null) {
            closeBroadcastTask3.active();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void addDevice$default(IMSKit iMSKit, Function1 function1, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = (Function1) null;
        }
        if ((i & 2) != 0) {
            function2 = (Function2) null;
        }
        iMSKit.addDevice(function1, function2);
    }

    public final void addDevice(Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed) {
        Pdlog.m3273d(TAG, "addDevice()");
        MessageProtobuf.Msg.Builder generateCommonMsgBuilder = generateCommonMsgBuilder(MsgType.AddDevice);
        sendMsg$default(this, generateCommonMsgBuilder != null ? generateCommonMsgBuilder.build() : null, onSentSucceed, onSentFailed, false, false, 24, null);
    }

    public final void reportRobotStatus(boolean isOpen) {
        Pdlog.m3273d(TAG, "reportRobotStatus() isOpen = " + isOpen + ", robotStatusReportTask = " + this.robotStatusReportTask);
        String str = this.centralControlMac;
        if (str == null || str.length() == 0) {
            Pdlog.m3273d(TAG, "centralControlMac is null or empty.");
            return;
        }
        if (isOpen) {
            RobotStatusReportTask robotStatusReportTask = this.robotStatusReportTask;
            if (robotStatusReportTask != null) {
                robotStatusReportTask.active();
                return;
            }
            return;
        }
        RobotStatusReportTask robotStatusReportTask2 = this.robotStatusReportTask;
        if (robotStatusReportTask2 != null) {
            robotStatusReportTask2.inactive();
        }
    }

    public final void changeReportInterval(int delayTime) {
        RobotStatusReportTask robotStatusReportTask = this.robotStatusReportTask;
        if (robotStatusReportTask != null) {
            robotStatusReportTask.updateDelayTime(delayTime);
        }
    }

    public final boolean isReportingRobotStatus() {
        RobotStatusReportTask robotStatusReportTask = this.robotStatusReportTask;
        if (robotStatusReportTask != null) {
            return robotStatusReportTask.isActive();
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void reportTaskStatus$default(IMSKit iMSKit, MessageProtobuf.Task task, MessageProtobuf.CallPoint callPoint, Function1 function1, Function2 function2, int i, Object obj) {
        if ((i & 4) != 0) {
            function1 = (Function1) null;
        }
        if ((i & 8) != 0) {
            function2 = (Function2) null;
        }
        iMSKit.reportTaskStatus(task, callPoint, function1, function2);
    }

    public final void reportTaskStatus(MessageProtobuf.Task task, MessageProtobuf.CallPoint callPoint, Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed) {
        MessageProtobuf.Msg.Builder task2;
        MessageProtobuf.Msg.Builder callPoint2;
        Intrinsics.checkParameterIsNotNull(task, "task");
        Intrinsics.checkParameterIsNotNull(callPoint, "callPoint");
        Pdlog.m3273d(TAG, "reportTaskStatus() task = " + task);
        MessageProtobuf.Msg.Builder generateCommonMsgBuilder = generateCommonMsgBuilder(MsgType.SyncTaskState);
        sendMsg$default(this, (generateCommonMsgBuilder == null || (task2 = generateCommonMsgBuilder.setTask(task)) == null || (callPoint2 = task2.setCallPoint(callPoint)) == null) ? null : callPoint2.build(), onSentSucceed, onSentFailed, false, false, 24, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void requestTask$default(IMSKit iMSKit, Function1 function1, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = (Function1) null;
        }
        if ((i & 2) != 0) {
            function2 = (Function2) null;
        }
        iMSKit.requestTask(function1, function2);
    }

    public final void requestTask(Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed) {
        Pdlog.m3273d(TAG, "requestTask()");
        MessageProtobuf.Msg.Builder generateCommonMsgBuilder = generateCommonMsgBuilder(MsgType.RequestTask);
        sendMsg$default(this, generateCommonMsgBuilder != null ? generateCommonMsgBuilder.build() : null, onSentSucceed, onSentFailed, false, false, 24, null);
    }

    public static /* synthetic */ void syncMap$default(IMSKit iMSKit, String str, ArrayList arrayList, Function0 function0, Function0 function02, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            function0 = (Function0) null;
        }
        Function0 function03 = function0;
        if ((i & 8) != 0) {
            function02 = (Function0) null;
        }
        Function0 function04 = function02;
        if ((i & 16) != 0) {
            function1 = (Function1) null;
        }
        iMSKit.syncMap(str, arrayList, function03, function04, function1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v14, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v3, types: [T, java.lang.String] */
    public final void syncMap(final String version, final ArrayList<CallPoint> callPointList, final Function0<Unit> onSyncMapSucceed, final Function0<Unit> onSyncMapFailed, final Function1<? super Integer, Unit> onSyncMapPercent) {
        Intrinsics.checkParameterIsNotNull(version, "version");
        Intrinsics.checkParameterIsNotNull(callPointList, "callPointList");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        ?? r0 = (String) 0;
        objectRef2.element = r0;
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        objectRef3.element = r0;
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = 0;
        final ArrayList<CallPoint> arrayList = new ArrayList<>();
        final String str = "pdmap";
        objectRef.element = "pdmap";
        if (callPointList.size() >= 5) {
            arrayList.addAll(callPointList.subList(0, 5));
            if (callPointList.size() > 5) {
                objectRef2.element = "pdmap" + ShortUUID.INSTANCE.randomUUID();
                objectRef3.element = (String) objectRef2.element;
                booleanRef.element = false;
            } else {
                objectRef2.element = r0;
                booleanRef.element = true;
            }
        } else {
            arrayList.addAll(callPointList);
            booleanRef.element = true;
        }
        final int i = 5;
        syncCallPointData(MsgType.SyncMap, version, arrayList, (String) objectRef.element, (String) objectRef2.element, booleanRef.element, new OnSyncCallPointListener() { // from class: com.pudutech.bumblebee.business.ims.IMSKit$syncMap$onSyncCallPointListener$1
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r0v13, types: [T, java.lang.String] */
            /* JADX WARN: Type inference failed for: r0v29, types: [T, java.lang.String] */
            /* JADX WARN: Type inference failed for: r0v32, types: [T, java.lang.String] */
            /* JADX WARN: Type inference failed for: r2v1, types: [T, java.lang.String] */
            /* JADX WARN: Type inference failed for: r2v3, types: [T, java.lang.String] */
            @Override // com.pudutech.bumblebee.business.ims.IMSKit.OnSyncCallPointListener
            public void onSucceed(ArrayList<CallPoint> list) {
                Intrinsics.checkParameterIsNotNull(list, "list");
                intRef.element += list.size();
                arrayList.clear();
                if (intRef.element == callPointList.size()) {
                    Function0 function0 = onSyncMapSucceed;
                    if (function0 != null) {
                        return;
                    }
                    return;
                }
                int size = (int) (((intRef.element * 1.0f) / callPointList.size()) * 100);
                Function1 function1 = onSyncMapPercent;
                if (function1 != null) {
                }
                Ref.ObjectRef objectRef4 = objectRef;
                ?? r02 = (String) objectRef3.element;
                if (r02 == 0) {
                    Intrinsics.throwNpe();
                }
                objectRef4.element = r02;
                if (callPointList.size() - intRef.element >= i) {
                    arrayList.addAll(callPointList.subList(intRef.element, intRef.element + i));
                    if (callPointList.size() - intRef.element > i) {
                        objectRef2.element = str + ShortUUID.INSTANCE.randomUUID();
                        objectRef3.element = (String) objectRef2.element;
                        booleanRef.element = false;
                    } else {
                        objectRef2.element = (String) 0;
                        booleanRef.element = true;
                    }
                } else {
                    arrayList.addAll(callPointList.subList(intRef.element, callPointList.size()));
                    objectRef2.element = (String) 0;
                    booleanRef.element = true;
                }
                IMSKit.this.syncCallPointData(MsgType.SyncMap, version, arrayList, (String) objectRef.element, (String) objectRef2.element, booleanRef.element, this);
            }

            @Override // com.pudutech.bumblebee.business.ims.IMSKit.OnSyncCallPointListener
            public void onFailed() {
                Function0 function0 = onSyncMapFailed;
                if (function0 != null) {
                }
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void deleteTaskPartitionTable$default(IMSKit iMSKit, Function0 function0, Function0 function02, int i, Object obj) {
        if ((i & 1) != 0) {
            function0 = (Function0) null;
        }
        if ((i & 2) != 0) {
            function02 = (Function0) null;
        }
        iMSKit.deleteTaskPartitionTable(function0, function02);
    }

    private final void deleteTaskPartitionTable(final Function0<Unit> onDeleteTaskPartitionTableSucceed, final Function0<Unit> onDeleteTaskPartitionTableFailed) {
        MessageProtobuf.Msg.Builder generateCommonMsgBuilder = generateCommonMsgBuilder(MsgType.DeleteTaskPartitionTable);
        sendMsg$default(this, generateCommonMsgBuilder != null ? generateCommonMsgBuilder.build() : null, new Function1<MessageProtobuf.Msg, Unit>() { // from class: com.pudutech.bumblebee.business.ims.IMSKit$deleteTaskPartitionTable$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(MessageProtobuf.Msg it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Function0 function0 = Function0.this;
                if (function0 != null) {
                    return (Unit) function0.invoke();
                }
                return null;
            }
        }, new Function2<MessageProtobuf.Msg, String, Unit>() { // from class: com.pudutech.bumblebee.business.ims.IMSKit$deleteTaskPartitionTable$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Unit invoke(MessageProtobuf.Msg msg, String reason) {
                Intrinsics.checkParameterIsNotNull(reason, "reason");
                Function0 function0 = Function0.this;
                if (function0 != null) {
                    return (Unit) function0.invoke();
                }
                return null;
            }
        }, false, false, 24, null);
    }

    public static /* synthetic */ void syncTaskPartitionTable$default(IMSKit iMSKit, String str, ArrayList arrayList, Function0 function0, Function0 function02, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            function0 = (Function0) null;
        }
        Function0 function03 = function0;
        if ((i & 8) != 0) {
            function02 = (Function0) null;
        }
        Function0 function04 = function02;
        if ((i & 16) != 0) {
            function1 = (Function1) null;
        }
        iMSKit.syncTaskPartitionTable(str, arrayList, function03, function04, function1);
    }

    public final void syncTaskPartitionTable(String version, ArrayList<CallPoint> callPointList, Function0<Unit> onSyncTaskPartitionTableSucceed, Function0<Unit> onSyncTaskPartitionTableFailed, Function1<? super Integer, Unit> onSyncTaskPartitionTablePercent) {
        Intrinsics.checkParameterIsNotNull(version, "version");
        Intrinsics.checkParameterIsNotNull(callPointList, "callPointList");
        deleteTaskPartitionTable$default(this, new IMSKit$syncTaskPartitionTable$1(this, callPointList, onSyncTaskPartitionTableSucceed, onSyncTaskPartitionTablePercent, version, onSyncTaskPartitionTableFailed), null, 2, null);
    }

    public final void syncCallPointData(MsgType msgType, String version, final ArrayList<CallPoint> callPointList, String frameHead, String frameNext, boolean isFrameEnd, final OnSyncCallPointListener listener) {
        MessageProtobuf.Msg.Builder mapVersion;
        MessageProtobuf.CallPointList.Builder newBuilder = MessageProtobuf.CallPointList.newBuilder();
        for (CallPoint callPoint : callPointList) {
            String name = callPoint.getName();
            Charset charset = Charsets.UTF_8;
            if (name == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            byte[] bytes = name.getBytes(charset);
            Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
            if (bytes.length >= 18) {
                name = new String(ArraysKt.copyOfRange(bytes, 0, 18), Charsets.UTF_8);
            }
            DestinationType destinationType = DestinationType.Unknown;
            String type = callPoint.getType();
            if (Intrinsics.areEqual(type, com.pudutech.bumblebee.business.map_task.DestinationType.INSTANCE.getTable())) {
                destinationType = DestinationType.Table;
            } else if (Intrinsics.areEqual(type, com.pudutech.bumblebee.business.map_task.DestinationType.INSTANCE.getTransit())) {
                destinationType = DestinationType.Transit;
            } else if (Intrinsics.areEqual(type, com.pudutech.bumblebee.business.map_task.DestinationType.INSTANCE.getDishwashing())) {
                destinationType = DestinationType.DishWashing;
            } else if (Intrinsics.areEqual(type, com.pudutech.bumblebee.business.map_task.DestinationType.INSTANCE.getDining_outlet())) {
                destinationType = DestinationType.DiningOutlet;
            }
            newBuilder.addCallPoint(MessageProtobuf.CallPoint.newBuilder().setCallPointId(name).setType(destinationType.getType()).build());
        }
        MessageProtobuf.Msg.Builder generateCommonMsgBuilder = generateCommonMsgBuilder(msgType);
        MessageProtobuf.Msg.Builder callPointList2 = (generateCommonMsgBuilder == null || (mapVersion = generateCommonMsgBuilder.setMapVersion(version)) == null) ? null : mapVersion.setCallPointList(newBuilder.build());
        if (callPointList2 != null) {
            callPointList2.setMapFrameHead(frameHead);
        }
        if (frameNext != null && callPointList2 != null) {
            callPointList2.setMapFrameNext(frameNext);
        }
        if (callPointList2 != null) {
            callPointList2.setIsFrameEnd(isFrameEnd);
        }
        sendMsg$default(this, callPointList2 != null ? callPointList2.build() : null, new Function1<MessageProtobuf.Msg, Unit>() { // from class: com.pudutech.bumblebee.business.ims.IMSKit$syncCallPointData$3
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MessageProtobuf.Msg msg) {
                invoke2(msg);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MessageProtobuf.Msg it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                IMSKit.OnSyncCallPointListener.this.onSucceed(callPointList);
            }
        }, new Function2<MessageProtobuf.Msg, String, Unit>() { // from class: com.pudutech.bumblebee.business.ims.IMSKit$syncCallPointData$4
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(MessageProtobuf.Msg msg, String str) {
                invoke2(msg, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MessageProtobuf.Msg msg, String reason) {
                Intrinsics.checkParameterIsNotNull(reason, "reason");
                IMSKit.OnSyncCallPointListener.this.onFailed();
            }
        }, false, false, 24, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void enableTaskPartition$default(IMSKit iMSKit, boolean z, Function0 function0, Function0 function02, int i, Object obj) {
        if ((i & 2) != 0) {
            function0 = (Function0) null;
        }
        if ((i & 4) != 0) {
            function02 = (Function0) null;
        }
        iMSKit.enableTaskPartition(z, function0, function02);
    }

    public final void enableTaskPartition(boolean isEnable, final Function0<Unit> onSucceed, final Function0<Unit> onFailed) {
        MessageProtobuf.Msg.Builder generateCommonMsgBuilder = generateCommonMsgBuilder(isEnable ? MsgType.EnableTaskPartition : MsgType.DisableTaskPartition);
        sendMsg$default(this, generateCommonMsgBuilder != null ? generateCommonMsgBuilder.build() : null, new Function1<MessageProtobuf.Msg, Unit>() { // from class: com.pudutech.bumblebee.business.ims.IMSKit$enableTaskPartition$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(MessageProtobuf.Msg it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Function0 function0 = Function0.this;
                if (function0 != null) {
                    return (Unit) function0.invoke();
                }
                return null;
            }
        }, new Function2<MessageProtobuf.Msg, String, Unit>() { // from class: com.pudutech.bumblebee.business.ims.IMSKit$enableTaskPartition$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Unit invoke(MessageProtobuf.Msg msg, String reason) {
                Intrinsics.checkParameterIsNotNull(reason, "reason");
                Function0 function0 = Function0.this;
                if (function0 != null) {
                    return (Unit) function0.invoke();
                }
                return null;
            }
        }, false, false, 24, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void checkTaskPartitionTableSyncStatus$default(IMSKit iMSKit, Function1 function1, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = (Function1) null;
        }
        if ((i & 2) != 0) {
            function2 = (Function2) null;
        }
        iMSKit.checkTaskPartitionTableSyncStatus(function1, function2);
    }

    public final void checkTaskPartitionTableSyncStatus(Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed) {
        Pdlog.m3273d(TAG, "checkTaskPartitionTableSyncStatus()");
        MessageProtobuf.Msg.Builder generateCommonMsgBuilder = generateCommonMsgBuilder(MsgType.CheckTaskPartitionTableSyncStatus);
        sendMsg$default(this, generateCommonMsgBuilder != null ? generateCommonMsgBuilder.build() : null, onSentSucceed, onSentFailed, false, false, 24, null);
    }

    public final void cancelSyncMap() {
        cancelSyncMapTask();
    }

    private final void cancelSyncMapTask() {
        Job job = this.syncMapTask;
        if (job != null && job.isActive()) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.syncMapTask = (Job) null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void addBeeperBroadcastControl$default(IMSKit iMSKit, boolean z, String str, Function1 function1, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            function1 = (Function1) null;
        }
        if ((i & 8) != 0) {
            function2 = (Function2) null;
        }
        iMSKit.addBeeperBroadcastControl(z, str, function1, function2);
    }

    public final void addBeeperBroadcastControl(boolean isOpen, String centralControlCode, Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed) {
        MessageProtobuf.Msg.Builder broadcastControl;
        boolean z = true;
        Pdlog.m3273d(TAG, "addBeeperBroadcastControl() isOpen = " + isOpen + ", centralControlCode = " + centralControlCode);
        MessageProtobuf.BroadcastControl.Builder broadcastControlBuilder = MessageProtobuf.BroadcastControl.newBuilder().setEnable(isOpen);
        String str = centralControlCode;
        if (!(str == null || str.length() == 0)) {
            Intrinsics.checkExpressionValueIsNotNull(broadcastControlBuilder, "broadcastControlBuilder");
            broadcastControlBuilder.setCentralControlCode(centralControlCode);
        }
        if (isOpen) {
            CloseBeeperBroadcastTask closeBeeperBroadcastTask = this.closeBeeperBroadcastTask;
            if (closeBeeperBroadcastTask != null) {
                closeBeeperBroadcastTask.release();
            }
            MessageProtobuf.Msg msg = null;
            this.closeBeeperBroadcastTask = (CloseBeeperBroadcastTask) null;
            MessageProtobuf.Msg.Builder generateCommonMsgBuilder = generateCommonMsgBuilder(MsgType.AddBeeperBroadcastControl);
            if (generateCommonMsgBuilder != null && (broadcastControl = generateCommonMsgBuilder.setBroadcastControl(broadcastControlBuilder.build())) != null) {
                msg = broadcastControl.build();
            }
            sendMsg$default(this, msg, onSentSucceed, onSentFailed, false, false, 24, null);
            return;
        }
        String str2 = this.receiver;
        if (str2 != null && str2.length() != 0) {
            z = false;
        }
        if (z) {
            return;
        }
        if (this.closeBeeperBroadcastTask == null) {
            this.closeBeeperBroadcastTask = new CloseBeeperBroadcastTask();
            CloseBeeperBroadcastTask closeBeeperBroadcastTask2 = this.closeBeeperBroadcastTask;
            if (closeBeeperBroadcastTask2 != null) {
                closeBeeperBroadcastTask2.start();
            }
        }
        CloseBeeperBroadcastTask closeBeeperBroadcastTask3 = this.closeBeeperBroadcastTask;
        if (closeBeeperBroadcastTask3 != null) {
            closeBeeperBroadcastTask3.active();
        }
    }

    public static /* synthetic */ void addBeeperBroadcastControl$default(IMSKit iMSKit, boolean z, String str, String str2, Function1 function1, Function2 function2, int i, Object obj) {
        if ((i & 4) != 0) {
            str2 = (String) null;
        }
        String str3 = str2;
        if ((i & 8) != 0) {
            function1 = (Function1) null;
        }
        Function1 function12 = function1;
        if ((i & 16) != 0) {
            function2 = (Function2) null;
        }
        iMSKit.addBeeperBroadcastControl(z, str, str3, function12, function2);
    }

    public final void addBeeperBroadcastControl(boolean isOpen, String msgId, String centralControlCode, Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed) {
        MessageProtobuf.Msg.Builder broadcastControl;
        Intrinsics.checkParameterIsNotNull(msgId, "msgId");
        boolean z = true;
        Pdlog.m3273d(TAG, "addBeeperBroadcastControl() isOpen = " + isOpen + ", centralControlCode = " + centralControlCode);
        MessageProtobuf.BroadcastControl.Builder broadcastControlBuilder = MessageProtobuf.BroadcastControl.newBuilder().setEnable(isOpen);
        String str = centralControlCode;
        if (!(str == null || str.length() == 0)) {
            Intrinsics.checkExpressionValueIsNotNull(broadcastControlBuilder, "broadcastControlBuilder");
            broadcastControlBuilder.setCentralControlCode(centralControlCode);
        }
        if (isOpen) {
            CloseBeeperBroadcastTask closeBeeperBroadcastTask = this.closeBeeperBroadcastTask;
            if (closeBeeperBroadcastTask != null) {
                closeBeeperBroadcastTask.release();
            }
            MessageProtobuf.Msg msg = null;
            this.closeBeeperBroadcastTask = (CloseBeeperBroadcastTask) null;
            MessageProtobuf.Msg.Builder generateCommonMsgBuilderById = generateCommonMsgBuilderById(MsgType.AddBeeperBroadcastControl, msgId);
            if (generateCommonMsgBuilderById != null && (broadcastControl = generateCommonMsgBuilderById.setBroadcastControl(broadcastControlBuilder.build())) != null) {
                msg = broadcastControl.build();
            }
            sendMsg$default(this, msg, onSentSucceed, onSentFailed, false, false, 24, null);
            return;
        }
        String str2 = this.receiver;
        if (str2 != null && str2.length() != 0) {
            z = false;
        }
        if (z) {
            return;
        }
        if (this.closeBeeperBroadcastTask == null) {
            this.closeBeeperBroadcastTask = new CloseBeeperBroadcastTask();
            CloseBeeperBroadcastTask closeBeeperBroadcastTask2 = this.closeBeeperBroadcastTask;
            if (closeBeeperBroadcastTask2 != null) {
                closeBeeperBroadcastTask2.start();
            }
        }
        CloseBeeperBroadcastTask closeBeeperBroadcastTask3 = this.closeBeeperBroadcastTask;
        if (closeBeeperBroadcastTask3 != null) {
            closeBeeperBroadcastTask3.active();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void checkMapSyncStatus$default(IMSKit iMSKit, Function1 function1, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = (Function1) null;
        }
        if ((i & 2) != 0) {
            function2 = (Function2) null;
        }
        iMSKit.checkMapSyncStatus(function1, function2);
    }

    public final void checkMapSyncStatus(Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed) {
        Pdlog.m3273d(TAG, "checkMapSyncStatus()");
        MessageProtobuf.Msg.Builder generateCommonMsgBuilder = generateCommonMsgBuilder(MsgType.CheckMapSyncStatus);
        sendMsg$default(this, generateCommonMsgBuilder != null ? generateCommonMsgBuilder.build() : null, onSentSucceed, onSentFailed, false, false, 24, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void queryMapVersion$default(IMSKit iMSKit, Function1 function1, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = (Function1) null;
        }
        if ((i & 2) != 0) {
            function2 = (Function2) null;
        }
        iMSKit.queryMapVersion(function1, function2);
    }

    public final void queryMapVersion(Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed) {
        Pdlog.m3273d(TAG, "queryMapVersion()");
        MessageProtobuf.Msg.Builder generateCommonMsgBuilder = generateCommonMsgBuilder(MsgType.QueryMapVersion);
        sendMsg$default(this, generateCommonMsgBuilder != null ? generateCommonMsgBuilder.build() : null, onSentSucceed, onSentFailed, false, false, 24, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void deleteDevice$default(IMSKit iMSKit, Function1 function1, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = (Function1) null;
        }
        if ((i & 2) != 0) {
            function2 = (Function2) null;
        }
        iMSKit.deleteDevice(function1, function2);
    }

    public final void deleteDevice(Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed) {
        Pdlog.m3273d(TAG, "deleteDevice()");
        MessageProtobuf.Msg.Builder generateCommonMsgBuilder = generateCommonMsgBuilder(MsgType.DeleteDevice);
        sendMsg$default(this, generateCommonMsgBuilder != null ? generateCommonMsgBuilder.build() : null, onSentSucceed, onSentFailed, false, false, 24, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void resetCentralControl$default(IMSKit iMSKit, Function1 function1, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = (Function1) null;
        }
        if ((i & 2) != 0) {
            function2 = (Function2) null;
        }
        iMSKit.resetCentralControl(function1, function2);
    }

    public final void resetCentralControl(Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed) {
        Pdlog.m3273d(TAG, "resetCentralControl()");
        MessageProtobuf.Msg.Builder generateCommonMsgBuilder = generateCommonMsgBuilder(MsgType.ResetCentralControl);
        sendMsg$default(this, generateCommonMsgBuilder != null ? generateCommonMsgBuilder.build() : null, onSentSucceed, onSentFailed, false, false, 24, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void queryHasUnallocatedTask$default(IMSKit iMSKit, Function1 function1, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = (Function1) null;
        }
        if ((i & 2) != 0) {
            function2 = (Function2) null;
        }
        iMSKit.queryHasUnallocatedTask(function1, function2);
    }

    public final void queryHasUnallocatedTask(Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed) {
        Pdlog.m3273d(TAG, "queryHasUnallocatedTask()");
        MessageProtobuf.Msg.Builder generateCommonMsgBuilder = generateCommonMsgBuilder(MsgType.HasUnallocatedTask);
        sendMsg$default(this, generateCommonMsgBuilder != null ? generateCommonMsgBuilder.build() : null, onSentSucceed, onSentFailed, false, false, 24, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void reportCurrentTime$default(IMSKit iMSKit, Function1 function1, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = (Function1) null;
        }
        if ((i & 2) != 0) {
            function2 = (Function2) null;
        }
        iMSKit.reportCurrentTime(function1, function2);
    }

    public final void reportCurrentTime(Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed) {
        MessageProtobuf.Msg.Builder timestamp;
        Pdlog.m3273d(TAG, "reportCurrentTime()");
        MessageProtobuf.Msg.Builder generateCommonMsgBuilder = generateCommonMsgBuilder(MsgType.ReportCurrentTime);
        sendMsg$default(this, (generateCommonMsgBuilder == null || (timestamp = generateCommonMsgBuilder.setTimestamp(System.currentTimeMillis() / ((long) 1000))) == null) ? null : timestamp.build(), onSentSucceed, onSentFailed, false, false, 24, null);
    }

    public final void removeMsg(String msgId) {
        Intrinsics.checkParameterIsNotNull(msgId, "msgId");
        IMSClientFactory.INSTANCE.getIMSClient().removeMsg(msgId);
    }

    public static /* synthetic */ void sendMsg$default(IMSKit iMSKit, MessageProtobuf.Msg msg, Function1 function1, Function2 function2, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = (Function1) null;
        }
        Function1 function12 = function1;
        if ((i & 4) != 0) {
            function2 = (Function2) null;
        }
        Function2 function22 = function2;
        if ((i & 8) != 0) {
            z = false;
        }
        boolean z3 = z;
        if ((i & 16) != 0) {
            z2 = true;
        }
        iMSKit.sendMsg(msg, function12, function22, z3, z2);
    }

    public final void sendMsg(MessageProtobuf.Msg r2, final Function1<? super MessageProtobuf.Msg, Unit> onSentSucceed, final Function2<? super MessageProtobuf.Msg, ? super String, Unit> onSentFailed, boolean checkMac, boolean joinResendManager) {
        if (r2 == null) {
            if (onSentFailed != null) {
                onSentFailed.invoke(r2, "msg is null.");
                return;
            }
            return;
        }
        if (checkMac) {
            String str = this.centralControlMac;
            if (str == null || str.length() == 0) {
                if (onSentFailed != null) {
                    onSentFailed.invoke(r2, "centralControlMac is null or empty.");
                    return;
                }
                return;
            }
        }
        IMSClientFactory.INSTANCE.getIMSClient().sendMsg(r2, joinResendManager, new IMsgSentStatusListener() { // from class: com.pudutech.bumblebee.business.ims.IMSKit$sendMsg$1
            @Override // com.pudutech.bumblebee.business.ims.listener.IMsgSentStatusListener
            public void onSentSucceed(MessageProtobuf.Msg msg) {
                Intrinsics.checkParameterIsNotNull(msg, "msg");
                Function1 function1 = Function1.this;
                if (function1 != null) {
                }
            }

            @Override // com.pudutech.bumblebee.business.ims.listener.IMsgSentStatusListener
            public void onSentFailed(MessageProtobuf.Msg msg, String reason) {
                Intrinsics.checkParameterIsNotNull(reason, "reason");
                Function2 function2 = onSentFailed;
                if (function2 != null) {
                }
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0044 A[Catch: Exception -> 0x0071, TryCatch #0 {Exception -> 0x0071, blocks: (B:3:0x0008, B:5:0x0010, B:10:0x001e, B:13:0x0028, B:15:0x0038, B:20:0x0044, B:22:0x004e), top: B:2:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x004e A[Catch: Exception -> 0x0071, TRY_LEAVE, TryCatch #0 {Exception -> 0x0071, blocks: (B:3:0x0008, B:5:0x0010, B:10:0x001e, B:13:0x0028, B:15:0x0038, B:20:0x0044, B:22:0x004e), top: B:2:0x0008 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final MessageProtobuf.Msg.Builder generateCommonMsgBuilder(MsgType msgType) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(msgType, "msgType");
        try {
            String str = this.sender;
            if (str == null || str.length() == 0) {
                Pdlog.m3277w(TAG, "generateCommonMsgBuilder error, sender is null or empty");
                return null;
            }
            int i = WhenMappings.$EnumSwitchMapping$0[msgType.ordinal()];
            this.receiver = BROADCAST_MAC;
            String str2 = this.receiver;
            if (str2 != null && str2.length() != 0) {
                z = false;
                if (!z) {
                    Pdlog.m3277w(TAG, "generateCommonMsgBuilder error, receiver is null or empty");
                    return null;
                }
                return MessageProtobuf.Msg.newBuilder().setMsgId(ShortUUID.INSTANCE.randomUUID()).setMsgType(msgType.getType()).setSender(this.sender).setReceiver(this.receiver);
            }
            z = true;
            if (!z) {
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0049 A[Catch: Exception -> 0x0070, TryCatch #0 {Exception -> 0x0070, blocks: (B:3:0x000d, B:5:0x0015, B:10:0x0023, B:13:0x002d, B:15:0x003d, B:20:0x0049, B:22:0x0053), top: B:2:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0053 A[Catch: Exception -> 0x0070, TRY_LEAVE, TryCatch #0 {Exception -> 0x0070, blocks: (B:3:0x000d, B:5:0x0015, B:10:0x0023, B:13:0x002d, B:15:0x003d, B:20:0x0049, B:22:0x0053), top: B:2:0x000d }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final MessageProtobuf.Msg.Builder generateCommonMsgBuilderById(MsgType msgType, String msgId) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(msgType, "msgType");
        Intrinsics.checkParameterIsNotNull(msgId, "msgId");
        try {
            String str = this.sender;
            if (str == null || str.length() == 0) {
                Pdlog.m3277w(TAG, "generateCommonMsgBuilder error, sender is null or empty");
                return null;
            }
            int i = WhenMappings.$EnumSwitchMapping$1[msgType.ordinal()];
            this.receiver = BROADCAST_MAC;
            String str2 = this.receiver;
            if (str2 != null && str2.length() != 0) {
                z = false;
                if (!z) {
                    Pdlog.m3277w(TAG, "generateCommonMsgBuilder error, receiver is null or empty");
                    return null;
                }
                return MessageProtobuf.Msg.newBuilder().setMsgId(msgId).setMsgType(msgType.getType()).setSender(this.sender).setReceiver(this.receiver);
            }
            z = true;
            if (!z) {
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public final void setupChannel(Channel chl, LoRaChannelManager2.OnSetupChannelListener listener) {
        LoRaChannelManager2.INSTANCE.getINSTANCE().setupChannel(chl, listener);
    }

    public final void setupLocalChannel(Channel chl, LoRaChannelManager2.OnSetupChannelListener listener) {
        LoRaChannelManager2.INSTANCE.getINSTANCE().setupLocalChannel(chl, listener);
    }

    public final void release() {
        RobotStatusReportTask robotStatusReportTask = this.robotStatusReportTask;
        if (robotStatusReportTask != null) {
            robotStatusReportTask.release();
        }
        this.robotStatusReportTask = (RobotStatusReportTask) null;
    }
}
