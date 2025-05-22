package com.pudutech.mirsdk.mircore.mirperception;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.ICenterRgbdData;
import com.pudutech.mirsdk.hardware.IDownRgbdData;
import com.pudutech.mirsdk.hardware.ILeftRgbdData;
import com.pudutech.mirsdk.hardware.ILidarData;
import com.pudutech.mirsdk.hardware.IMarkerCameraData;
import com.pudutech.mirsdk.hardware.IRightRgbdData;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.hardware.serialize.PolarCoordinates;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import java.io.FileDescriptor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: Perception.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u008e\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b#\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0006\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0016\n\u0002\b\u0003\n\u0002\u0010\u0013\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b&\n\u0002\u0018\u0002\n\u0002\b3*\b\u0004\u0007\n?BEHN\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J4\u0010P\u001a\u00020Q2\u0006\u0010R\u001a\u00020S2$\u0010T\u001a \u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020Q0UJ:\u0010V\u001a\u00020Q2\u0006\u0010R\u001a\u00020S2*\u0010T\u001a&\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020Q0WJ\"\u0010X\u001a\u00020Q2\u0006\u0010R\u001a\u00020S2\u0012\u0010T\u001a\u000e\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020Q0YJ\"\u0010Z\u001a\u00020Q2\u0006\u0010R\u001a\u00020S2\u0012\u0010T\u001a\u000e\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020Q0YJ:\u0010[\u001a\u00020Q2\u0006\u0010R\u001a\u00020S2*\u0010T\u001a&\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020Q0WJ\"\u0010\\\u001a\u00020Q2\u0006\u0010R\u001a\u00020S2\u0012\u0010T\u001a\u000e\u0012\u0004\u0012\u00020]\u0012\u0004\u0012\u00020Q0YJ:\u0010^\u001a\u00020Q2\u0006\u0010R\u001a\u00020S2*\u0010T\u001a&\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020Q0WJ\"\u0010_\u001a\u00020Q2\u0006\u0010R\u001a\u00020S2\u0012\u0010T\u001a\u000e\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020Q0YJF\u0010`\u001a\u00020Q2\u0006\u0010R\u001a\u00020S26\u0010T\u001a2\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020Q0aJ.\u0010b\u001a\u00020Q2\u0006\u0010R\u001a\u00020S2\u001e\u0010T\u001a\u001a\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020Q0cJ\"\u0010d\u001a\u00020Q2\u0006\u0010R\u001a\u00020S2\u0012\u0010T\u001a\u000e\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020Q0YJ\u0019\u0010e\u001a\u00020Q2\f\u0010f\u001a\b\u0012\u0004\u0012\u00020h0g¢\u0006\u0002\u0010iJ:\u0010j\u001a\u00020Q2\u0006\u0010R\u001a\u00020S2*\u0010T\u001a&\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020Q0WJ\"\u0010k\u001a\u00020Q2\u0006\u0010R\u001a\u00020S2\u0012\u0010T\u001a\u000e\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020Q0YJ\t\u0010l\u001a\u00020\rH\u0086 J\u0006\u0010m\u001a\u00020nJ\u0006\u0010o\u001a\u00020QJ\u000e\u0010p\u001a\u00020Q2\u0006\u0010q\u001a\u00020\rJ\u000e\u0010\u0012\u001a\u00020Q2\u0006\u0010q\u001a\u00020\rJ\u000e\u0010r\u001a\u00020Q2\u0006\u0010q\u001a\u00020\rJ\u000e\u0010\u001b\u001a\u00020Q2\u0006\u0010q\u001a\u00020\rJ\u000e\u0010s\u001a\u00020Q2\u0006\u0010q\u001a\u00020\rJ\u000e\u0010t\u001a\u00020Q2\u0006\u0010q\u001a\u00020\rJ\u0010\u0010u\u001a\u00020Q2\u0006\u0010v\u001a\u00020wH\u0002J\u000e\u0010x\u001a\u00020Q2\u0006\u0010q\u001a\u00020\rJ\u000e\u0010'\u001a\u00020Q2\u0006\u0010q\u001a\u00020\rJ&\u0010y\u001a\u00020Q2\u0006\u0010q\u001a\u00020\r2\u0006\u0010z\u001a\u00020:2\u0006\u0010{\u001a\u00020:2\u0006\u0010|\u001a\u00020:J\u000e\u0010}\u001a\u00020\r2\u0006\u0010~\u001a\u00020SJ\u000f\u0010\u007f\u001a\u00020Q2\u0007\u0010\u0080\u0001\u001a\u00020\rJ\u000f\u0010-\u001a\u00020Q2\u0007\u0010\u0080\u0001\u001a\u00020\rJ\u0010\u0010\u0081\u0001\u001a\u00020Q2\u0007\u0010\u0082\u0001\u001a\u000201J\u000f\u00106\u001a\u00020Q2\u0007\u0010\u0080\u0001\u001a\u00020\rJ\u0007\u0010\u0083\u0001\u001a\u000201J\u0007\u0010\u0084\u0001\u001a\u00020SJ\u001a\u0010\u0085\u0001\u001a\u00030\u0086\u00012\u0007\u0010\u0087\u0001\u001a\u00020:2\u0007\u0010\u0088\u0001\u001a\u00020:J\b\u0010\u0089\u0001\u001a\u00030\u008a\u0001J\b\u0010\u008b\u0001\u001a\u00030\u008a\u0001J\u0007\u0010\u008c\u0001\u001a\u000201J\b\u0010\u008d\u0001\u001a\u00030\u0086\u0001J\b\u0010\u008e\u0001\u001a\u00030\u0086\u0001J\u0011\u0010\u008f\u0001\u001a\u00020Q2\b\u0010\u0090\u0001\u001a\u00030\u0091\u0001J\u0007\u0010\u0092\u0001\u001a\u00020SJ\b\u0010\u0093\u0001\u001a\u00030\u0094\u0001J\b\u0010\u0095\u0001\u001a\u00030\u008a\u0001J\u0011\u0010\u0096\u0001\u001a\u00020Q2\b\u0010\u0090\u0001\u001a\u00030\u0091\u0001J\b\u0010\u0097\u0001\u001a\u00030\u008a\u0001J\n\u0010\u0098\u0001\u001a\u00020\rH\u0082 J\u000f\u0010\u0099\u0001\u001a\u00020\r2\u0006\u0010v\u001a\u00020wJ\u0007\u0010\u009a\u0001\u001a\u00020\rJ\u0007\u0010\u009b\u0001\u001a\u00020\rJ\u0007\u0010\u009c\u0001\u001a\u00020\rJ\u0007\u0010\u009d\u0001\u001a\u00020\rJ\u0007\u0010\u009e\u0001\u001a\u00020\rJ0\u0010\u009f\u0001\u001a\u00020Q2\b\u0010 \u0001\u001a\u00030¡\u00012\u0007\u0010¢\u0001\u001a\u0002012\u0007\u0010£\u0001\u001a\u0002012\b\u0010¤\u0001\u001a\u00030\u0094\u0001H\u0082 J\u0014\u0010¥\u0001\u001a\u00020Q2\b\u0010 \u0001\u001a\u00030¡\u0001H\u0082 J\u0014\u0010¦\u0001\u001a\u00020Q2\b\u0010 \u0001\u001a\u00030¡\u0001H\u0082 J \u0010§\u0001\u001a\u00020Q2\u000e\u0010¨\u0001\u001a\t\u0012\u0005\u0012\u00030©\u00010gH\u0082 ¢\u0006\u0003\u0010ª\u0001J \u0010«\u0001\u001a\u00020Q2\u000e\u0010¨\u0001\u001a\t\u0012\u0005\u0012\u00030©\u00010gH\u0082 ¢\u0006\u0003\u0010ª\u0001J\u0014\u0010¬\u0001\u001a\u00020Q2\b\u0010 \u0001\u001a\u00030¡\u0001H\u0082 J0\u0010\u00ad\u0001\u001a\u00020Q2\b\u0010 \u0001\u001a\u00030¡\u00012\u0007\u0010¢\u0001\u001a\u0002012\u0007\u0010£\u0001\u001a\u0002012\b\u0010¤\u0001\u001a\u00030\u0094\u0001H\u0082 J\u0014\u0010®\u0001\u001a\u00020Q2\b\u0010 \u0001\u001a\u00030¡\u0001H\u0082 J\u001e\u0010¯\u0001\u001a\u00020Q2\u0006\u0010R\u001a\u00020S2\r\u0010T\u001a\t\u0012\u0004\u0012\u00020Q0°\u0001J$\u0010±\u0001\u001a\u00020Q2\u001b\u0010²\u0001\u001a\u0016\u0012\u0004\u0012\u00020S\u0012\u0005\u0012\u00030´\u0001\u0012\u0004\u0012\u00020Q0³\u0001J$\u0010µ\u0001\u001a\u00020Q2\u001b\u0010²\u0001\u001a\u0016\u0012\u0004\u0012\u00020S\u0012\u0005\u0012\u00030¶\u0001\u0012\u0004\u0012\u00020Q0³\u0001J$\u0010·\u0001\u001a\u00020Q2\u001b\u0010²\u0001\u001a\u0016\u0012\u0004\u0012\u00020S\u0012\u0005\u0012\u00030¸\u0001\u0012\u0004\u0012\u00020Q0³\u0001J$\u0010¹\u0001\u001a\u00020Q2\u001b\u0010²\u0001\u001a\u0016\u0012\u0004\u0012\u00020S\u0012\u0005\u0012\u00030º\u0001\u0012\u0004\u0012\u00020Q0³\u0001J$\u0010»\u0001\u001a\u00020Q2\u001b\u0010²\u0001\u001a\u0016\u0012\u0004\u0012\u00020S\u0012\u0005\u0012\u00030º\u0001\u0012\u0004\u0012\u00020Q0³\u0001J$\u0010¼\u0001\u001a\u00020Q2\u001b\u0010²\u0001\u001a\u0016\u0012\u0004\u0012\u00020S\u0012\u0005\u0012\u00030½\u0001\u0012\u0004\u0012\u00020Q0³\u0001J$\u0010¾\u0001\u001a\u00020Q2\u001b\u0010²\u0001\u001a\u0016\u0012\u0004\u0012\u00020S\u0012\u0005\u0012\u00030´\u0001\u0012\u0004\u0012\u00020Q0³\u0001J$\u0010¿\u0001\u001a\u00020Q2\u001b\u0010²\u0001\u001a\u0016\u0012\u0004\u0012\u00020S\u0012\u0005\u0012\u00030À\u0001\u0012\u0004\u0012\u00020Q0³\u0001J\u000f\u0010Á\u0001\u001a\u00020Q2\u0006\u0010R\u001a\u00020SJ\u000f\u0010Â\u0001\u001a\u00020Q2\u0006\u0010R\u001a\u00020SJ\u000f\u0010Ã\u0001\u001a\u00020Q2\u0006\u0010R\u001a\u00020SJ\u000f\u0010Ä\u0001\u001a\u00020Q2\u0006\u0010R\u001a\u00020SJ\u000f\u0010Å\u0001\u001a\u00020Q2\u0006\u0010R\u001a\u00020SJ\u000f\u0010Æ\u0001\u001a\u00020Q2\u0006\u0010R\u001a\u00020SJ\u000f\u0010Ç\u0001\u001a\u00020Q2\u0006\u0010R\u001a\u00020SJ\u000f\u0010È\u0001\u001a\u00020Q2\u0006\u0010R\u001a\u00020SJ\u000f\u0010É\u0001\u001a\u00020Q2\u0006\u0010R\u001a\u00020SJ\u000f\u0010Ê\u0001\u001a\u00020Q2\u0006\u0010R\u001a\u00020SJ\u000f\u0010Ë\u0001\u001a\u00020Q2\u0006\u0010R\u001a\u00020SJ\u000f\u0010Ì\u0001\u001a\u00020Q2\u0006\u0010R\u001a\u00020SJ\u000f\u0010Í\u0001\u001a\u00020Q2\u0006\u0010R\u001a\u00020SJ\u0007\u0010Î\u0001\u001a\u00020QJ&\u0010Ï\u0001\u001a\t\u0012\u0005\u0012\u00030©\u00010g2\u000e\u0010Ð\u0001\u001a\t\u0012\u0005\u0012\u00030©\u00010gH\u0002¢\u0006\u0003\u0010Ñ\u0001J\u0010\u0010Ò\u0001\u001a\u00020Q2\u0007\u0010Ó\u0001\u001a\u00020\rJ\u0019\u0010Ô\u0001\u001a\u00020Q2\u0007\u0010Õ\u0001\u001a\u00020:2\u0007\u0010Ö\u0001\u001a\u00020:J\u0010\u0010×\u0001\u001a\u00020Q2\u0007\u0010Ø\u0001\u001a\u00020:J\u0019\u0010Ù\u0001\u001a\u00020Q2\u0007\u0010Ú\u0001\u001a\u00020:2\u0007\u0010Û\u0001\u001a\u00020:J\u0019\u0010Ü\u0001\u001a\u00020Q2\u0007\u0010Ý\u0001\u001a\u00020\r2\u0007\u0010Þ\u0001\u001a\u000201J\u0010\u0010ß\u0001\u001a\u00020Q2\u0007\u0010à\u0001\u001a\u00020\rJ\u0010\u0010á\u0001\u001a\u00020Q2\u0007\u0010â\u0001\u001a\u00020hJ\u0010\u0010ã\u0001\u001a\u00020Q2\u0007\u0010ä\u0001\u001a\u00020\rJ\u0011\u0010å\u0001\u001a\u00020Q2\b\u0010æ\u0001\u001a\u00030ç\u0001J%\u0010è\u0001\u001a\u00020Q2\u0007\u0010é\u0001\u001a\u00020\r2\r\u0010ê\u0001\u001a\b\u0012\u0004\u0012\u00020h0g¢\u0006\u0003\u0010ë\u0001J=\u0010ì\u0001\u001a\u00020Q2\u0007\u0010í\u0001\u001a\u00020S2\u0007\u0010î\u0001\u001a\u00020:2\u0007\u0010ï\u0001\u001a\u00020:2\u0007\u0010ð\u0001\u001a\u00020:2\u0007\u0010ñ\u0001\u001a\u00020:2\u0007\u0010ò\u0001\u001a\u00020:J\u0019\u0010ó\u0001\u001a\u00020Q2\u0007\u0010ô\u0001\u001a\u0002012\u0007\u0010õ\u0001\u001a\u00020:J\u0010\u0010ö\u0001\u001a\u00020Q2\u0007\u0010÷\u0001\u001a\u00020SJu\u0010ø\u0001\u001a\u00020Q2\u0006\u0010v\u001a\u00020w2\u0007\u0010ù\u0001\u001a\u00020\r2\u0007\u0010ú\u0001\u001a\u00020\r2\u0007\u0010û\u0001\u001a\u00020\r2\u0007\u0010ü\u0001\u001a\u00020\r2\u0007\u0010ý\u0001\u001a\u00020\r2\u0007\u0010þ\u0001\u001a\u00020\r2\u0007\u0010ÿ\u0001\u001a\u00020\r2\u0007\u0010\u0080\u0002\u001a\u00020\r2\u0007\u0010\u0081\u0002\u001a\u00020\r2\u0007\u0010\u0082\u0002\u001a\u00020\r2\u0007\u0010\u0083\u0002\u001a\u00020\rH\u0086 J\u0010\u0010\u0084\u0002\u001a\u00020Q2\u0007\u0010\u0085\u0002\u001a\u00020\rJ\u0010\u0010\u0086\u0002\u001a\u00020\r2\u0007\u0010\u0087\u0002\u001a\u00020\rJ\u0010\u0010\u0088\u0002\u001a\u00020\r2\u0007\u0010\u0089\u0002\u001a\u00020\rJ\u0010\u0010\u008a\u0002\u001a\u00020Q2\u0007\u0010\u008b\u0002\u001a\u00020\rJ\u000f\u0010\u008c\u0002\u001a\u00020Q2\u0006\u0010R\u001a\u00020SJ\u001c\u0010\u008d\u0002\u001a\u00020Q2\u0013\u0010²\u0001\u001a\u000e\u0012\u0004\u0012\u00020S\u0012\u0004\u0012\u00020Q0YJ\u001c\u0010\u008e\u0002\u001a\u00020Q2\u0013\u0010²\u0001\u001a\u000e\u0012\u0004\u0012\u00020S\u0012\u0004\u0012\u00020Q0YJ\u001c\u0010\u008f\u0002\u001a\u00020Q2\u0013\u0010²\u0001\u001a\u000e\u0012\u0004\u0012\u00020S\u0012\u0004\u0012\u00020Q0YJ\u001c\u0010\u0090\u0002\u001a\u00020Q2\u0013\u0010²\u0001\u001a\u000e\u0012\u0004\u0012\u00020S\u0012\u0004\u0012\u00020Q0YJ\u001c\u0010\u0091\u0002\u001a\u00020Q2\u0013\u0010²\u0001\u001a\u000e\u0012\u0004\u0012\u00020S\u0012\u0004\u0012\u00020Q0YJ\u001c\u0010\u0092\u0002\u001a\u00020Q2\u0013\u0010²\u0001\u001a\u000e\u0012\u0004\u0012\u00020S\u0012\u0004\u0012\u00020Q0YJ\u001c\u0010\u0093\u0002\u001a\u00020Q2\u0013\u0010²\u0001\u001a\u000e\u0012\u0004\u0012\u00020S\u0012\u0004\u0012\u00020Q0YJ\u001c\u0010\u0094\u0002\u001a\u00020Q2\u0013\u0010²\u0001\u001a\u000e\u0012\u0004\u0012\u00020S\u0012\u0004\u0012\u00020Q0YJ\u0014\u0010\u0095\u0002\u001a\u00020Q2\b\u0010\u0096\u0002\u001a\u00030\u0094\u0001H\u0086 J\u0013\u0010\u0097\u0002\u001a\u00020Q2\u0007\u0010\u0098\u0002\u001a\u00020hH\u0086 J\u0013\u0010\u0099\u0002\u001a\u00020Q2\u0007\u0010â\u0001\u001a\u00020hH\u0086 R\u0010\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005R\u0010\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\bR\u0010\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\u001a\u0010\u0015\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000f\"\u0004\b\u0017\u0010\u0011R\u001a\u0010\u0018\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u000f\"\u0004\b\u001a\u0010\u0011R\u001a\u0010\u001b\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u000f\"\u0004\b\u001d\u0010\u0011R\u001a\u0010\u001e\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u000f\"\u0004\b \u0010\u0011R\u001a\u0010!\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u000f\"\u0004\b#\u0010\u0011R\u001a\u0010$\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u000f\"\u0004\b&\u0010\u0011R\u001a\u0010'\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u000f\"\u0004\b)\u0010\u0011R\u001a\u0010*\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u000f\"\u0004\b,\u0010\u0011R\u001a\u0010-\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u000f\"\u0004\b/\u0010\u0011R\u001a\u00100\u001a\u000201X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u001a\u00106\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\u000f\"\u0004\b8\u0010\u0011R\u000e\u00109\u001a\u00020:X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010;\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010\u000f\"\u0004\b=\u0010\u0011R\u0010\u0010>\u001a\u00020?X\u0082\u0004¢\u0006\u0004\n\u0002\u0010@R\u0010\u0010A\u001a\u00020BX\u0082\u0004¢\u0006\u0004\n\u0002\u0010CR\u0010\u0010D\u001a\u00020EX\u0082\u0004¢\u0006\u0004\n\u0002\u0010FR\u0010\u0010G\u001a\u00020HX\u0082\u0004¢\u0006\u0004\n\u0002\u0010IR\u001a\u0010J\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bK\u0010\u000f\"\u0004\bL\u0010\u0011R\u0010\u0010M\u001a\u00020NX\u0082\u0004¢\u0006\u0004\n\u0002\u0010O¨\u0006\u009a\u0002"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/mirperception/Perception;", "", "()V", "cameraListener", "com/pudutech/mirsdk/mircore/mirperception/Perception$cameraListener$1", "Lcom/pudutech/mirsdk/mircore/mirperception/Perception$cameraListener$1;", "centerRGBDListener", "com/pudutech/mirsdk/mircore/mirperception/Perception$centerRGBDListener$1", "Lcom/pudutech/mirsdk/mircore/mirperception/Perception$centerRGBDListener$1;", "downRGBDListener", "com/pudutech/mirsdk/mircore/mirperception/Perception$downRGBDListener$1", "Lcom/pudutech/mirsdk/mircore/mirperception/Perception$downRGBDListener$1;", "enableAutoDock", "", "getEnableAutoDock", "()Z", "setEnableAutoDock", "(Z)V", "enableChildrenDetect", "getEnableChildrenDetect", "setEnableChildrenDetect", "enableCostmap", "getEnableCostmap", "setEnableCostmap", "enableDEngine", "getEnableDEngine", "setEnableDEngine", "enableDataRecord", "getEnableDataRecord", "setEnableDataRecord", "enableDocking", "getEnableDocking", "setEnableDocking", "enableDynamic", "getEnableDynamic", "setEnableDynamic", "enableLaserInteraction", "getEnableLaserInteraction", "setEnableLaserInteraction", "enablePersonDetect", "getEnablePersonDetect", "setEnablePersonDetect", "enableReflectorDetect", "getEnableReflectorDetect", "setEnableReflectorDetect", "enableVision", "getEnableVision", "setEnableVision", "enableVisionModules", "", "getEnableVisionModules", "()I", "setEnableVisionModules", "(I)V", "enableWaveDetect", "getEnableWaveDetect", "setEnableWaveDetect", "filter_threshold", "", "initialize_result", "getInitialize_result", "setInitialize_result", "leftRGBDListener", "com/pudutech/mirsdk/mircore/mirperception/Perception$leftRGBDListener$1", "Lcom/pudutech/mirsdk/mircore/mirperception/Perception$leftRGBDListener$1;", "lidarLDListener", "com/pudutech/mirsdk/mircore/mirperception/Perception$lidarLDListener$1", "Lcom/pudutech/mirsdk/mircore/mirperception/Perception$lidarLDListener$1;", "lidarListener", "com/pudutech/mirsdk/mircore/mirperception/Perception$lidarListener$1", "Lcom/pudutech/mirsdk/mircore/mirperception/Perception$lidarListener$1;", "markerCameraListener", "com/pudutech/mirsdk/mircore/mirperception/Perception$markerCameraListener$1", "Lcom/pudutech/mirsdk/mircore/mirperception/Perception$markerCameraListener$1;", "perception_initialized", "getPerception_initialized", "setPerception_initialized", "rightRGBDListener", "com/pudutech/mirsdk/mircore/mirperception/Perception$rightRGBDListener$1", "Lcom/pudutech/mirsdk/mircore/mirperception/Perception$rightRGBDListener$1;", "addAutoDockListener", "", "name", "", "callback", "Lkotlin/Function4;", "addChildrenListener", "Lkotlin/Function5;", "addCostmapErrorListener", "Lkotlin/Function1;", "addDEngineErrorListener", "addFaceServerListener", "addInteractionListener", "", "addObjPropertyServerListener", "addPersonCountListener", "addPersonListener", "Lkotlin/Function7;", "addReflectorDetectListener", "Lkotlin/Function3;", "addRoadWidthMeasureListener", "addVirtualWall", "walls", "", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "([Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;)V", "addVisionListener", "addWaveDetectListener", "checkNPU", "checkNoise", "", "cleanData", "dengineTest", "enable", "enableCostmapPerception", "enableFaceFilter", "enableFaceServer", "enableModules", "machineModel", "Lcom/pudutech/mirsdk/hardware/serialize/MachineModel;", "enableObjDetServer", "enablePersonDetectInRange", "min_angle", "max_angle", "max_dist", "enableRGBD", "rgbd_config_file", "enableReflectorDetector", "flag", "enableVisionModule", "index", "getCliffInfo", "getCliffIrImg", "getCostmapAddress", "", "posex", "posey", "getDetectRegin", "", "getDockerEstimateTransform", "getDropDetectType", "getDropInfoAddress", "getDynamicAddress", "getFaceDetView", "bitmap", "Landroid/graphics/Bitmap;", "getGitHash", "getMarkerAddress", "", "getMarkerPose", "getObjectDetView", "identifyDocker", "initPublicData", "initialize", "isCostmapInited", "isModuleInited", "isPreciseDockingInited", "isVisionInited", "isWaveDetectInited", "processCamera", "file", "Ljava/io/FileDescriptor;", "rows", "cols", "time_stamp", "processCenterRGBD", "processDownRGBD", "processLDLaser", "laser_points", "Lcom/pudutech/mirsdk/hardware/serialize/PolarCoordinates;", "([Lcom/pudutech/mirsdk/hardware/serialize/PolarCoordinates;)V", "processLaser", "processLeftRGBD", "processMarkerCamera", "processRightRGBD", "registMapUpdatedListener", "Lkotlin/Function0;", "registerCameraListener", "handler", "Lkotlin/Function2;", "Lcom/pudutech/mirsdk/hardware/IMarkerCameraData;", "registerCenterRGBDListener", "Lcom/pudutech/mirsdk/hardware/ICenterRgbdData;", "registerDownRGBDListener", "Lcom/pudutech/mirsdk/hardware/IDownRgbdData;", "registerLDLaserListener", "Lcom/pudutech/mirsdk/hardware/ILidarData;", "registerLaserListener", "registerLeftRGBDListener", "Lcom/pudutech/mirsdk/hardware/ILeftRgbdData;", "registerMarkerCameraListener", "registerRightRGBDListenner", "Lcom/pudutech/mirsdk/hardware/IRightRgbdData;", "removeAutoDockListener", "removeChildrenListener", "removeCostmapErrorListener", "removeDEngineErrorListener", "removeFaceServerListener", "removeInteractionListener", "removeObjPropertyServerListener", "removePersonCountListener", "removePersonListener", "removeReflectorDetectListener", "removeRoadWidthMeasureListener", "removeVisionListener", "removeWaveDetectListener", "resetAutoDockPerception", "scan_filtered", "scan_origin", "([Lcom/pudutech/mirsdk/hardware/serialize/PolarCoordinates;)[Lcom/pudutech/mirsdk/hardware/serialize/PolarCoordinates;", "setAutoDockSwitch", "need_autodock", "setDetectRegin", "dist", "angle", "setDockDist", "dock_dist", "setDockerPose", "docker_pose_x", "docker_pose_y", "setDockingSwitch", "need_docking", "target_id", "setDropDetSwitch", "need_dropdet", "setInitRobotPose", "pose", "setLaserInteractionSwitch", "need_interaction", "setLidarType", "lidar_type", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$LidarType;", "setNoiseDetectSwitch", "need_noise_detect", "rect_vector", "(Z[Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;)V", "setOtherRobotInfo", "id", "x", "y", "yaw", "vx", "vy", "setReflectorLevel", "level", "distance", "setTopoTrackPath", "topo_path", "summaryModules", "costmap_enable", "docking_enable", "dynamic_enable", "vision_enable", "autodock_enable", "laser_interaction_enable", "person_detect_enable", "children_detect_enable", "dengine_enable", "wave_detect_enable", "reflector_detect_enable", "switchDataRecord", "need_record", "switchDynamic", "enable_dynamic", "switchFeasibalRegionSeg", "use_cape", "switchUseDropInfo", "need_use_drop_info", "unregistMapUpdatedListener", "unregisterCameraListener", "unregisterCenterRGBDListener", "unregisterDownRGBDListener", "unregisterLDLaserListener", "unregisterLaserListener", "unregisterLeftRGBDListener", "unregisterMarkerCameraListener", "unregisterRightRGBDListener", "updateRobotMapArea", "address", "updateRobotOdom", "odom", "updateRobotPose", "MirPerception_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class Perception {
    public static final Perception INSTANCE = new Perception();
    private static final Perception$cameraListener$1 cameraListener;
    private static final Perception$centerRGBDListener$1 centerRGBDListener;
    private static final Perception$downRGBDListener$1 downRGBDListener;
    private static boolean enableAutoDock = false;
    private static boolean enableChildrenDetect = false;
    private static boolean enableCostmap = false;
    private static boolean enableDEngine = false;
    private static boolean enableDataRecord = false;
    private static boolean enableDocking = false;
    private static boolean enableDynamic = false;
    private static boolean enableLaserInteraction = false;
    private static boolean enablePersonDetect = false;
    private static boolean enableReflectorDetect = false;
    private static boolean enableVision = false;
    private static int enableVisionModules = 0;
    private static boolean enableWaveDetect = false;
    private static final double filter_threshold;
    private static boolean initialize_result;
    private static final Perception$leftRGBDListener$1 leftRGBDListener;
    private static final Perception$lidarLDListener$1 lidarLDListener;
    private static final Perception$lidarListener$1 lidarListener;
    private static final Perception$markerCameraListener$1 markerCameraListener;
    private static boolean perception_initialized;
    private static final Perception$rightRGBDListener$1 rightRGBDListener;

    private final native boolean initPublicData();

    /* JADX INFO: Access modifiers changed from: private */
    public final native void processCamera(FileDescriptor file, int rows, int cols, long time_stamp);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void processCenterRGBD(FileDescriptor file);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void processDownRGBD(FileDescriptor file);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void processLDLaser(PolarCoordinates[] laser_points);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void processLaser(PolarCoordinates[] laser_points);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void processLeftRGBD(FileDescriptor file);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void processMarkerCamera(FileDescriptor file, int rows, int cols, long time_stamp);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void processRightRGBD(FileDescriptor file);

    public final native boolean checkNPU();

    public final String getGitHash() {
        return "{\"perception\":\"commit: 5f1e179, auth: “zhouxiaofan”<“zhouxiaofan@pudutech.com”>, time: “Fri May 19 17:13:26 2023 +0800”\"}";
    }

    public final native void summaryModules(MachineModel machineModel, boolean costmap_enable, boolean docking_enable, boolean dynamic_enable, boolean vision_enable, boolean autodock_enable, boolean laser_interaction_enable, boolean person_detect_enable, boolean children_detect_enable, boolean dengine_enable, boolean wave_detect_enable, boolean reflector_detect_enable);

    public final native void updateRobotMapArea(long address);

    public final native void updateRobotOdom(Vector3d odom);

    public final native void updateRobotPose(Vector3d pose);

    /* JADX WARN: Type inference failed for: r0v10, types: [com.pudutech.mirsdk.mircore.mirperception.Perception$markerCameraListener$1] */
    /* JADX WARN: Type inference failed for: r0v3, types: [com.pudutech.mirsdk.mircore.mirperception.Perception$lidarListener$1] */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.pudutech.mirsdk.mircore.mirperception.Perception$lidarLDListener$1] */
    /* JADX WARN: Type inference failed for: r0v5, types: [com.pudutech.mirsdk.mircore.mirperception.Perception$leftRGBDListener$1] */
    /* JADX WARN: Type inference failed for: r0v6, types: [com.pudutech.mirsdk.mircore.mirperception.Perception$rightRGBDListener$1] */
    /* JADX WARN: Type inference failed for: r0v7, types: [com.pudutech.mirsdk.mircore.mirperception.Perception$centerRGBDListener$1] */
    /* JADX WARN: Type inference failed for: r0v8, types: [com.pudutech.mirsdk.mircore.mirperception.Perception$downRGBDListener$1] */
    /* JADX WARN: Type inference failed for: r0v9, types: [com.pudutech.mirsdk.mircore.mirperception.Perception$cameraListener$1] */
    static {
        Pdlog.m3273d(PerceptionKt.getTAG(), "load library: mirperception");
        System.loadLibrary("mirperception");
        lidarListener = new ILidarData.Stub() { // from class: com.pudutech.mirsdk.mircore.mirperception.Perception$lidarListener$1
            @Override // com.pudutech.mirsdk.hardware.ILidarData
            public void onFrame(PolarCoordinates[] laser_points, long lidar_time_stamp) {
                if (laser_points != null) {
                    Perception.INSTANCE.processLaser(laser_points);
                }
            }
        };
        filter_threshold = filter_threshold;
        lidarLDListener = new ILidarData.Stub() { // from class: com.pudutech.mirsdk.mircore.mirperception.Perception$lidarLDListener$1
            @Override // com.pudutech.mirsdk.hardware.ILidarData
            public void onFrame(PolarCoordinates[] laser_points, long lidar_time_stamp) {
                if (laser_points != null) {
                    Perception.INSTANCE.processLDLaser(laser_points);
                }
            }
        };
        leftRGBDListener = new ILeftRgbdData.Stub() { // from class: com.pudutech.mirsdk.mircore.mirperception.Perception$leftRGBDListener$1
            @Override // com.pudutech.mirsdk.hardware.ILeftRgbdData
            public void onLeftFrameDescriptor(ParcelFileDescriptor p0, int rows, int cols, int memorySize) {
                if (p0 == null) {
                    return;
                }
                Perception perception = Perception.INSTANCE;
                FileDescriptor fileDescriptor = p0.getFileDescriptor();
                Intrinsics.checkExpressionValueIsNotNull(fileDescriptor, "p0.fileDescriptor");
                perception.processLeftRGBD(fileDescriptor);
            }
        };
        rightRGBDListener = new IRightRgbdData.Stub() { // from class: com.pudutech.mirsdk.mircore.mirperception.Perception$rightRGBDListener$1
            @Override // com.pudutech.mirsdk.hardware.IRightRgbdData
            public void onRightFrameDescriptor(ParcelFileDescriptor p0, int rows, int cols, int memorySize) {
                if (p0 == null) {
                    return;
                }
                Perception perception = Perception.INSTANCE;
                FileDescriptor fileDescriptor = p0.getFileDescriptor();
                Intrinsics.checkExpressionValueIsNotNull(fileDescriptor, "p0.fileDescriptor");
                perception.processRightRGBD(fileDescriptor);
            }
        };
        centerRGBDListener = new ICenterRgbdData.Stub() { // from class: com.pudutech.mirsdk.mircore.mirperception.Perception$centerRGBDListener$1
            @Override // com.pudutech.mirsdk.hardware.ICenterRgbdData
            public void onCenterFrameDescriptor(ParcelFileDescriptor p0, int rows, int cols, int memorySize) {
                if (p0 == null) {
                    return;
                }
                Perception perception = Perception.INSTANCE;
                FileDescriptor fileDescriptor = p0.getFileDescriptor();
                Intrinsics.checkExpressionValueIsNotNull(fileDescriptor, "p0.fileDescriptor");
                perception.processCenterRGBD(fileDescriptor);
            }
        };
        downRGBDListener = new IDownRgbdData.Stub() { // from class: com.pudutech.mirsdk.mircore.mirperception.Perception$downRGBDListener$1
            @Override // com.pudutech.mirsdk.hardware.IDownRgbdData
            public void onDownFrameDescriptor(ParcelFileDescriptor p0, int rows, int cols, int memorySize) {
                if (p0 == null) {
                    return;
                }
                Perception perception = Perception.INSTANCE;
                FileDescriptor fileDescriptor = p0.getFileDescriptor();
                Intrinsics.checkExpressionValueIsNotNull(fileDescriptor, "p0.fileDescriptor");
                perception.processDownRGBD(fileDescriptor);
            }
        };
        cameraListener = new IMarkerCameraData.Stub() { // from class: com.pudutech.mirsdk.mircore.mirperception.Perception$cameraListener$1
            @Override // com.pudutech.mirsdk.hardware.IMarkerCameraData
            public void onFrame(ParcelFileDescriptor p0, int p1, int p2, int p3, int p4, long p5) {
                if (p0 == null) {
                    return;
                }
                Perception perception = Perception.INSTANCE;
                FileDescriptor fileDescriptor = p0.getFileDescriptor();
                Intrinsics.checkExpressionValueIsNotNull(fileDescriptor, "p0.fileDescriptor");
                perception.processCamera(fileDescriptor, p1, p2, p5);
            }
        };
        markerCameraListener = new IMarkerCameraData.Stub() { // from class: com.pudutech.mirsdk.mircore.mirperception.Perception$markerCameraListener$1
            @Override // com.pudutech.mirsdk.hardware.IMarkerCameraData
            public void onFrame(ParcelFileDescriptor p0, int p1, int p2, int p3, int p4, long p5) {
                if (p0 == null) {
                    return;
                }
                Perception perception = Perception.INSTANCE;
                FileDescriptor fileDescriptor = p0.getFileDescriptor();
                Intrinsics.checkExpressionValueIsNotNull(fileDescriptor, "p0.fileDescriptor");
                perception.processMarkerCamera(fileDescriptor, p1, p2, p5);
            }
        };
        enableDataRecord = true;
        enableVisionModules = 1;
    }

    private Perception() {
    }

    public final boolean initialize(MachineModel machineModel) {
        Intrinsics.checkParameterIsNotNull(machineModel, "machineModel");
        Pdlog.m3277w(PerceptionKt.getTAG(), "Perception Initialize...");
        if (perception_initialized) {
            Pdlog.m3277w(PerceptionKt.getTAG(), "Perception have Initialized, cannot be called two time!");
            return initialize_result;
        }
        perception_initialized = true;
        if (!initPublicData()) {
            Pdlog.m3277w(PerceptionKt.getTAG(), "PublicData Initialize Failed!");
        }
        enableModules(machineModel);
        if (enableCostmap) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "Try to Initialize Costmap Module!");
            if (!Costmap.INSTANCE.initialize(machineModel)) {
                Pdlog.m3273d(PerceptionKt.getTAG(), "Costmap Module Initialize Failed!");
                initialize_result = false;
                return false;
            }
        }
        if (enableDynamic) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "Try to Initialize Dynamic Module!");
            if (!DynamicObject.INSTANCE.initialize(machineModel)) {
                Pdlog.m3273d(PerceptionKt.getTAG(), "DynamicObject Module Initialize Failed!");
            }
        }
        if (enableDocking) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "Try to Initialize Docking Module!");
            if (!PreciseDocking.INSTANCE.initialize()) {
                Pdlog.m3273d(PerceptionKt.getTAG(), "PreciseDocking Module Initialize Failed!");
            }
        }
        if (enableVision) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "Try to Initialize Vision Module!");
            if (!Vision.INSTANCE.initialize(machineModel)) {
                Pdlog.m3273d(PerceptionKt.getTAG(), "Vision Module Initialize Failed!");
            }
        }
        if (enableAutoDock) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "Try to Initialize AutoDock Perception Module!");
            if (!AutoDockPerception.INSTANCE.initialize(machineModel)) {
                Pdlog.m3273d(PerceptionKt.getTAG(), "AutoDock Perception Module Initialize Failed!");
            }
        }
        if (enableLaserInteraction) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "Try to Initialize LaserInteraction Module!");
            if (!LaserInteraction.INSTANCE.initialize(machineModel)) {
                Pdlog.m3273d(PerceptionKt.getTAG(), "LaserInteraction Module Initialize Failed!");
            }
        }
        if (enablePersonDetect) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "Try to Initialize PersonDetect Module!");
            if (!PersonDetect.INSTANCE.initialize(machineModel)) {
                Pdlog.m3273d(PerceptionKt.getTAG(), "PersonDetect Module Initialize Failed!");
            }
        }
        if (enableDataRecord) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "Try to Initialize DataRecord Module!");
            if (!DataRecord.INSTANCE.initialize()) {
                Pdlog.m3273d(PerceptionKt.getTAG(), "DataRecord Module Initialize Failed!");
            }
        }
        if (enableChildrenDetect) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "Try to Initialize ChildrenDetect Module!");
            if (!ChildrenDetect.INSTANCE.initialize(machineModel)) {
                Pdlog.m3273d(PerceptionKt.getTAG(), "ChildrenDetect Module Initialize Failed!");
            }
        }
        if (enableDEngine) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "Try to Initialize Dengine Module!");
            if (!DEngineServer.INSTANCE.initialize(machineModel)) {
                Pdlog.m3273d(PerceptionKt.getTAG(), "Dengine Module Initialize Failed!");
            }
        }
        if (enableWaveDetect) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "Try to Initialize WaveDetect Module!");
            if (!WaveDetect.INSTANCE.initialize(machineModel)) {
                Pdlog.m3273d(PerceptionKt.getTAG(), "WaveDetect Module Initialize Failed!");
            }
        }
        if (enableReflectorDetect) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "Try to Initialize ReflectorDetect Module!");
            if (!ReflectorDetect.INSTANCE.initialize(machineModel)) {
                Pdlog.m3273d(PerceptionKt.getTAG(), "ReflectorDetect Module Initialize Failed!");
            }
        }
        summaryModules(machineModel, enableCostmap, enableDocking, enableDynamic, enableVision, enableAutoDock, enableLaserInteraction, enablePersonDetect, enableChildrenDetect, enableDEngine, enableWaveDetect, enableReflectorDetect);
        Pdlog.m3273d(PerceptionKt.getTAG(), "Perception Initialize Finish!");
        initialize_result = true;
        return initialize_result;
    }

    public final void enableVisionModule(int index) {
        Pdlog.m3273d(PerceptionKt.getTAG(), "Eable Vision Module: ", Integer.valueOf(index));
        enableVisionModules = index;
    }

    public final boolean isModuleInited() {
        boolean z;
        if (enableCostmap) {
            z = Costmap.INSTANCE.isModuleInited();
            Pdlog.m3273d(PerceptionKt.getTAG(), "Costmap Module State: ", Boolean.valueOf(z));
        } else {
            z = true;
        }
        if (enableDynamic) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "DynamicObject Module State: ", Boolean.valueOf(DynamicObject.INSTANCE.isModuleInited()));
        }
        if (enableDocking) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "PreciseDocking Module State: ", Boolean.valueOf(PreciseDocking.INSTANCE.isModuleInited()));
        }
        if (enableVision) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "Vision Module State: ", Boolean.valueOf(Vision.INSTANCE.isModuleInited()));
        }
        if (enableAutoDock) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "AutoDock Module State: ", Boolean.valueOf(AutoDockPerception.INSTANCE.isModuleInited()));
        }
        if (enableLaserInteraction) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "LaserInteraction Module State: ", Boolean.valueOf(LaserInteraction.INSTANCE.isModuleInited()));
        }
        if (enablePersonDetect) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "PersonDetect Module State: ", Boolean.valueOf(PersonDetect.INSTANCE.isModuleInited()));
        }
        if (enableChildrenDetect) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "ChildrenDetect Module State: ", Boolean.valueOf(ChildrenDetect.INSTANCE.isModuleInited()));
        }
        if (enableDEngine) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "DEngine Module State: ", Boolean.valueOf(DEngineServer.INSTANCE.isModuleInited()));
        }
        if (enableWaveDetect) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "WaveDetect Module State: ", Boolean.valueOf(WaveDetect.INSTANCE.isModuleInited()));
        }
        if (enableReflectorDetect) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "ReflectorDetect Module State: ", Boolean.valueOf(ReflectorDetect.INSTANCE.isModuleInited()));
        }
        return z;
    }

    public final void registMapUpdatedListener(String name, Function0<Unit> callback) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Costmap.INSTANCE.registMapUpdatedListener(name, callback);
    }

    public final void unregistMapUpdatedListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Costmap.INSTANCE.unregistMapUpdatedListener(name);
    }

    public final void addCostmapErrorListener(String name, Function1<? super Integer, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Costmap.INSTANCE.addCostmapErrorListener(name, callback);
    }

    public final void removeCostmapErrorListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Costmap.INSTANCE.removeCostmapErrorListener(name);
    }

    public final long[] getCostmapAddress(double posex, double posey) {
        return Costmap.INSTANCE.getCostmapAddress(posex, posey);
    }

    public final boolean isCostmapInited() {
        return Costmap.INSTANCE.isModuleInited();
    }

    public final void addVirtualWall(Vector3d[] walls) {
        Intrinsics.checkParameterIsNotNull(walls, "walls");
        Costmap.INSTANCE.addVirtualWall(walls);
    }

    public final boolean enableRGBD(String rgbd_config_file) {
        Intrinsics.checkParameterIsNotNull(rgbd_config_file, "rgbd_config_file");
        boolean enableRGBD = enableCostmap ? Costmap.INSTANCE.enableRGBD(rgbd_config_file) : true;
        Pdlog.m3273d(PerceptionKt.getTAG(), "enableRGBD return ", Boolean.valueOf(enableRGBD));
        return enableRGBD;
    }

    public final void setNoiseDetectSwitch(boolean need_noise_detect, Vector3d[] rect_vector) {
        Intrinsics.checkParameterIsNotNull(rect_vector, "rect_vector");
        Costmap.INSTANCE.setNoiseDetectSwitch(need_noise_detect, rect_vector);
    }

    public final int[] checkNoise() {
        return Costmap.INSTANCE.checkNoise();
    }

    public final void enableCostmapPerception(boolean enable) {
        Costmap.INSTANCE.enableCostmap(enable);
    }

    public final long[] getDynamicAddress() {
        return DynamicObject.INSTANCE.getDynamicAddress();
    }

    public final void setOtherRobotInfo(String id, double x, double y, double yaw, double vx, double vy) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        DynamicObject.INSTANCE.setOtherRobotInfo(id, x, y, yaw, vx, vy);
    }

    public final void setTopoTrackPath(String topo_path) {
        Intrinsics.checkParameterIsNotNull(topo_path, "topo_path");
        DynamicObject.INSTANCE.setTopoTrackPath(topo_path);
    }

    public final boolean isVisionInited() {
        if (enableVisionModules == 0) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "isVisionInited, Vision Module is Not Enable!");
            return false;
        }
        if (enableDEngine) {
            return DEngineServer.INSTANCE.isModuleInited();
        }
        return Vision.INSTANCE.isModuleInited();
    }

    public final void addVisionListener(String name, Function5<? super Integer, ? super Double, ? super Double, ? super Double, ? super Double, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        if (enableVisionModules == 0) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "addVisionListener, Vision Module is Not Enable!");
        } else if (enableDEngine) {
            DEngineServer.INSTANCE.addFacePropertyServerListener(name, callback);
        } else {
            Vision.INSTANCE.addVisionListener(name, callback);
        }
    }

    public final void removeVisionListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        if (enableVisionModules == 0) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "removeVisionListener, Vision Module is Not Enable!");
        } else if (enableDEngine) {
            DEngineServer.INSTANCE.removeFacePropertyServerListener(name);
        } else {
            Vision.INSTANCE.removeVisionListener(name);
        }
    }

    public final void enableVision(boolean flag) {
        if (enableVisionModules == 0) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "enableVision, Vision Module is Not Enable!");
        } else if (enableDEngine) {
            DEngineServer.INSTANCE.enableFaceServer(flag);
        } else {
            Vision.INSTANCE.enableVision(flag);
        }
    }

    public final boolean isPreciseDockingInited() {
        return PreciseDocking.INSTANCE.isModuleInited();
    }

    public final long getMarkerAddress() {
        return PreciseDocking.INSTANCE.getMarkerPoseAddress();
    }

    public final double[] getMarkerPose() {
        return PreciseDocking.INSTANCE.getMarkerPose();
    }

    public final void setDockDist(double dock_dist) {
        PreciseDocking.INSTANCE.setDockDist(dock_dist);
    }

    public final void setDockingSwitch(boolean need_docking, int target_id) {
        PreciseDocking.INSTANCE.setDockingSwitch(need_docking, target_id);
    }

    public final void setAutoDockSwitch(boolean need_autodock) {
        AutoDockPerception.INSTANCE.setAutoDockSwitch(need_autodock);
    }

    public final void setDockerPose(double docker_pose_x, double docker_pose_y) {
        AutoDockPerception.INSTANCE.setDockerPose(docker_pose_x, docker_pose_y);
    }

    public final void resetAutoDockPerception() {
        AutoDockPerception.INSTANCE.resetAutoDockPerception();
    }

    public final double[] getDockerEstimateTransform() {
        return AutoDockPerception.INSTANCE.getDockerEstimateTransform();
    }

    public final double[] identifyDocker() {
        return AutoDockPerception.INSTANCE.identifyDocker();
    }

    public final void addAutoDockListener(String name, Function4<? super Boolean, ? super Double, ? super Double, ? super Double, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        AutoDockPerception.INSTANCE.addAutoDockListener(name, callback);
    }

    public final void removeAutoDockListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        AutoDockPerception.INSTANCE.removeAutoDockListener(name);
    }

    public final void setLaserInteractionSwitch(boolean need_interaction) {
        LaserInteraction.INSTANCE.setInteractionSwitch(need_interaction);
    }

    public final void addInteractionListener(String name, Function1<? super byte[], Unit> callback) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        LaserInteraction.INSTANCE.addInteractionListener(name, callback);
    }

    public final void removeInteractionListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        LaserInteraction.INSTANCE.removeInteractionListener(name);
    }

    public final void enablePersonDetect(boolean enable) {
        PersonDetect.INSTANCE.enablePersonDetect(enable);
    }

    public final void setInitRobotPose(Vector3d pose) {
        Intrinsics.checkParameterIsNotNull(pose, "pose");
        PersonDetect.INSTANCE.setInitRobotPose(pose);
    }

    public final void addPersonListener(String name, Function7<? super Integer, ? super Integer, ? super Double, ? super Double, ? super Double, ? super Double, ? super Double, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        PersonDetect.INSTANCE.addPersonListener(name, callback);
    }

    public final void removePersonListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        PersonDetect.INSTANCE.removePersonListener(name);
    }

    public final void addPersonCountListener(String name, Function1<? super Integer, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        PersonDetect.INSTANCE.addPersonCountListener(name, callback);
    }

    public final void removePersonCountListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        PersonDetect.INSTANCE.removePersonCountListener(name);
    }

    public final void enablePersonDetectInRange(boolean enable, double min_angle, double max_angle, double max_dist) {
        PersonDetect.INSTANCE.enablePersonDetectInRange(enable, min_angle, max_angle, max_dist);
    }

    public final void setDetectRegin(double dist, double angle) {
        PersonDetect.INSTANCE.setDetectRegin(dist, angle);
    }

    public final double[] getDetectRegin() {
        return PersonDetect.INSTANCE.getDetectRegin();
    }

    public final void enableChildrenDetect(boolean enable) {
        ChildrenDetect.INSTANCE.enableChildrenDetect(enable);
    }

    public final void addChildrenListener(String name, Function5<? super Integer, ? super Double, ? super Double, ? super Double, ? super Double, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        ChildrenDetect.INSTANCE.addChildrenListener(name, callback);
    }

    public final void removeChildrenListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        ChildrenDetect.INSTANCE.removeChildrenListener(name);
    }

    public final void enableDataRecord(boolean enable) {
        DataRecord.INSTANCE.enableDataRecord(enable);
    }

    public final void cleanData() {
        DataRecord.INSTANCE.cleanData();
    }

    public final void enableFaceServer(boolean enable) {
        int i = enableVisionModules;
        if (i == 0 || i == 1) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "enableFaceServer, DEngine Module is Not Enable!");
        } else {
            DEngineServer.INSTANCE.enableFaceServer(enable);
        }
    }

    public final void enableObjDetServer(boolean enable) {
        int i = enableVisionModules;
        if (i == 0 || i == 1) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "enableObjDetServer, DEngine Module is Not Enable!");
        } else {
            DEngineServer.INSTANCE.enableObjDetServer(enable);
        }
    }

    public final void enableFaceFilter(boolean enable) {
        int i = enableVisionModules;
        if (i == 0 || i == 1) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "enableFaceFilter, DEngine Module is Not Enable!");
        } else {
            DEngineServer.INSTANCE.enableFaceFilter(enable);
        }
    }

    public final void addFaceServerListener(String name, Function5<? super Integer, ? super Double, ? super Double, ? super Double, ? super Double, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        int i = enableVisionModules;
        if (i == 0 || i == 1) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "addFaceServerListener, DEngine Module is Not Enable!");
        } else {
            DEngineServer.INSTANCE.addFacePropertyServerListener(name, callback);
        }
    }

    public final void removeFaceServerListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        int i = enableVisionModules;
        if (i == 0 || i == 1) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "removeFaceServerListener, DEngine Module is Not Enable!");
        } else {
            DEngineServer.INSTANCE.removeFacePropertyServerListener(name);
        }
    }

    public final void addObjPropertyServerListener(String name, Function5<? super Integer, ? super Integer, ? super Double, ? super Double, ? super Double, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        int i = enableVisionModules;
        if (i == 0 || i == 1) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "addObjPropertyServerListener, DEngine Module is Not Enable!");
        } else {
            DEngineServer.INSTANCE.addObjPropertyServerListener(name, callback);
        }
    }

    public final void removeObjPropertyServerListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        int i = enableVisionModules;
        if (i == 0 || i == 1) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "removeObjPropertyServerListener, DEngine Module is Not Enable!");
        } else {
            DEngineServer.INSTANCE.removeObjPropertyServerListener(name);
        }
    }

    public final void addDEngineErrorListener(String name, Function1<? super Integer, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        int i = enableVisionModules;
        if (i == 0 || i == 1) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "addDEngineErrorListener, DEngine Module is Not Enable!");
        } else {
            DEngineServer.INSTANCE.addDEngineErrorListener(name, callback);
        }
    }

    public final void removeDEngineErrorListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        int i = enableVisionModules;
        if (i == 0 || i == 1) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "removeDEngineErrorListener, DEngine Module is Not Enable!");
        } else {
            DEngineServer.INSTANCE.removeDEngineErrorListener(name);
        }
    }

    public final void getFaceDetView(Bitmap bitmap) {
        Intrinsics.checkParameterIsNotNull(bitmap, "bitmap");
        DEngineServer.INSTANCE.getFaceDetView(bitmap);
    }

    public final void getObjectDetView(Bitmap bitmap) {
        Intrinsics.checkParameterIsNotNull(bitmap, "bitmap");
        DEngineServer.INSTANCE.getObjectDetView(bitmap);
    }

    public final void dengineTest(boolean enable) {
        DEngineServer.INSTANCE.dengineTest(enable);
    }

    public final boolean isWaveDetectInited() {
        return WaveDetect.INSTANCE.isModuleInited();
    }

    public final void addWaveDetectListener(String name, Function1<? super Integer, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        WaveDetect.INSTANCE.addWaveDetectListener(name, callback);
    }

    public final void removeWaveDetectListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        WaveDetect.INSTANCE.removeWaveDetectListener(name);
    }

    public final void enableWaveDetect(boolean flag) {
        WaveDetect.INSTANCE.enableWaveDetect(flag);
    }

    public final boolean switchFeasibalRegionSeg(boolean use_cape) {
        return Costmap.INSTANCE.switchFeasibalRegionSeg(use_cape);
    }

    public final boolean switchDynamic(boolean enable_dynamic) {
        return DynamicObject.INSTANCE.switchDynamic(enable_dynamic);
    }

    public final void setDropDetSwitch(boolean need_dropdet) {
        Costmap.INSTANCE.switchDropDet(need_dropdet);
    }

    public final void switchUseDropInfo(boolean need_use_drop_info) {
        Costmap.INSTANCE.switchUseDropInfo(need_use_drop_info);
    }

    public final void switchDataRecord(boolean need_record) {
        Costmap.INSTANCE.switchDataRecord(need_record);
    }

    public final int getCliffInfo() {
        return Costmap.INSTANCE.getCliffInfo();
    }

    public final String getCliffIrImg() {
        return Costmap.INSTANCE.getCliffIrImg();
    }

    public final long[] getDropInfoAddress() {
        return Costmap.INSTANCE.getDropInfoAddress();
    }

    public final int getDropDetectType() {
        return Costmap.INSTANCE.getDropDetectType();
    }

    public final void setLidarType(MachineInfo.LidarType lidar_type) {
        Intrinsics.checkParameterIsNotNull(lidar_type, "lidar_type");
        if (lidar_type == MachineInfo.LidarType.ECHOX) {
            ReflectorDetect.INSTANCE.setLidarType(1);
        } else if (lidar_type == MachineInfo.LidarType.LDS_50C) {
            ReflectorDetect.INSTANCE.setLidarType(2);
        } else {
            ReflectorDetect.INSTANCE.setLidarType(3);
        }
    }

    public final void setReflectorLevel(int level, double distance) {
        ReflectorDetect.INSTANCE.setReflectorLevel(level, distance);
    }

    public final void addReflectorDetectListener(String name, Function3<? super Double, ? super Integer, ? super Integer, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        ReflectorDetect.INSTANCE.addReflectorDetectListener(name, callback);
    }

    public final void removeReflectorDetectListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        ReflectorDetect.INSTANCE.removeReflectorDetectListener(name);
    }

    public final void addRoadWidthMeasureListener(String name, Function1<? super Double, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        ReflectorDetect.INSTANCE.addRoadWidthMeasureListener(name, callback);
    }

    public final void removeRoadWidthMeasureListener(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        ReflectorDetect.INSTANCE.removeRoadWidthMeasureListener(name);
    }

    public final void enableReflectorDetector(boolean flag) {
        ReflectorDetect.INSTANCE.enableReflectorDetect(flag);
    }

    public final void registerLaserListener(Function2<? super String, ? super ILidarData, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        handler.invoke(PerceptionKt.getTAG(), lidarListener);
    }

    public final void unregisterLaserListener(Function1<? super String, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        handler.invoke(PerceptionKt.getTAG());
    }

    public final void registerLDLaserListener(Function2<? super String, ? super ILidarData, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        handler.invoke(PerceptionKt.getTAG(), lidarLDListener);
    }

    public final void unregisterLDLaserListener(Function1<? super String, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        handler.invoke(PerceptionKt.getTAG());
    }

    public final void registerLeftRGBDListener(Function2<? super String, ? super ILeftRgbdData, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        Pdlog.m3273d(PerceptionKt.getTAG(), "register left rgbd listener");
        handler.invoke(PerceptionKt.getTAG(), leftRGBDListener);
    }

    public final void unregisterLeftRGBDListener(Function1<? super String, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        Pdlog.m3273d(PerceptionKt.getTAG(), "unregister left rgbd listener");
        handler.invoke(PerceptionKt.getTAG());
    }

    public final void registerRightRGBDListenner(Function2<? super String, ? super IRightRgbdData, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        Pdlog.m3273d(PerceptionKt.getTAG(), "register right rgbd listener");
        handler.invoke(PerceptionKt.getTAG(), rightRGBDListener);
    }

    public final void unregisterRightRGBDListener(Function1<? super String, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        Pdlog.m3273d(PerceptionKt.getTAG(), "unregister right rgbd listener");
        handler.invoke(PerceptionKt.getTAG());
    }

    public final void registerCenterRGBDListener(Function2<? super String, ? super ICenterRgbdData, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        Pdlog.m3273d(PerceptionKt.getTAG(), "register center rgbd listener");
        handler.invoke(PerceptionKt.getTAG(), centerRGBDListener);
    }

    public final void unregisterCenterRGBDListener(Function1<? super String, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        Pdlog.m3273d(PerceptionKt.getTAG(), "unregister center rgbd listener");
        handler.invoke(PerceptionKt.getTAG());
    }

    public final void registerDownRGBDListener(Function2<? super String, ? super IDownRgbdData, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        Pdlog.m3273d(PerceptionKt.getTAG(), "register down rgbd listener");
        handler.invoke(PerceptionKt.getTAG(), downRGBDListener);
    }

    public final void unregisterDownRGBDListener(Function1<? super String, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        Pdlog.m3273d(PerceptionKt.getTAG(), "unregister down rgbd listener");
        handler.invoke(PerceptionKt.getTAG());
    }

    public final void registerCameraListener(Function2<? super String, ? super IMarkerCameraData, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        Pdlog.m3273d(PerceptionKt.getTAG(), "register camera listener");
        handler.invoke(PerceptionKt.getTAG(), cameraListener);
    }

    public final void unregisterCameraListener(Function1<? super String, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        Pdlog.m3273d(PerceptionKt.getTAG(), "unregister camera listener");
        handler.invoke(PerceptionKt.getTAG());
    }

    public final void registerMarkerCameraListener(Function2<? super String, ? super IMarkerCameraData, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        Pdlog.m3273d(PerceptionKt.getTAG(), "register marker camera listener");
        handler.invoke(PerceptionKt.getTAG(), markerCameraListener);
    }

    public final void unregisterMarkerCameraListener(Function1<? super String, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        Pdlog.m3273d(PerceptionKt.getTAG(), "unregister marker camera listener");
        handler.invoke(PerceptionKt.getTAG());
    }

    private final PolarCoordinates[] scan_filtered(PolarCoordinates[] scan_origin) {
        int length = scan_origin.length - 3;
        for (int i = 2; i < length; i++) {
            double distance_m = scan_origin[i].getDistance_m();
            if (distance_m < 3.0d && Math.abs(distance_m - scan_origin[i - 2].getDistance_m()) > filter_threshold && Math.abs(distance_m - scan_origin[i - 1].getDistance_m()) > filter_threshold && Math.abs(distance_m - scan_origin[i + 1].getDistance_m()) > filter_threshold && Math.abs(distance_m - scan_origin[i + 2].getDistance_m()) > filter_threshold) {
                scan_origin[i].setDistance_m(0.0d);
            }
        }
        return scan_origin;
    }

    private final void enableModules(MachineModel machineModel) {
        if (machineModel == MachineModel.Hls) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "MachineType: HLS");
            enableCostmap = true;
            enableDocking = true;
            enableDynamic = false;
            enableVision = false;
            enableAutoDock = true;
            enableLaserInteraction = true;
            enablePersonDetect = false;
            enableChildrenDetect = false;
            enableDEngine = false;
            enableWaveDetect = false;
            enableReflectorDetect = true;
            return;
        }
        if (machineModel == MachineModel.BellaBot) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "MachineType: Bella");
            enableCostmap = true;
            enableDocking = true;
            enableDynamic = true;
            if (enableVisionModules == 1) {
                enableVision = true;
            }
            enableAutoDock = true;
            enableLaserInteraction = true;
            enablePersonDetect = true;
            enableChildrenDetect = false;
            enableDEngine = false;
            enableWaveDetect = false;
            enableReflectorDetect = true;
            return;
        }
        if (machineModel == MachineModel.RecycleDog) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "MachineType: Hola");
            enableCostmap = true;
            enableDocking = true;
            enableDynamic = true;
            enableVision = false;
            enableAutoDock = true;
            enableLaserInteraction = true;
            enablePersonDetect = false;
            enableChildrenDetect = false;
            enableDEngine = false;
            enableWaveDetect = false;
            enableReflectorDetect = true;
            return;
        }
        if (machineModel == MachineModel.Firefox) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "MachineType: FireFox");
            enableCostmap = true;
            enableDocking = true;
            enableDynamic = false;
            if (enableVisionModules == 1) {
                enableVision = true;
            }
            enableAutoDock = true;
            enableLaserInteraction = true;
            enablePersonDetect = false;
            enableChildrenDetect = false;
            enableDEngine = false;
            enableWaveDetect = false;
            enableReflectorDetect = true;
            return;
        }
        if (machineModel == MachineModel.Peanut) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "MachineType: Peanut");
            enableCostmap = true;
            enableDocking = true;
            enableDynamic = false;
            enableAutoDock = true;
            enableLaserInteraction = true;
            enablePersonDetect = true;
            enableChildrenDetect = true;
            if (checkNPU() && enableVisionModules == 2) {
                Pdlog.m3273d(PerceptionKt.getTAG(), "NPU exit, enable denigne module");
                enableVision = false;
                enableDEngine = true;
            } else if (enableVisionModules != 0) {
                Pdlog.m3273d(PerceptionKt.getTAG(), "NPU not exit, enable vision module");
                enableVision = true;
                enableDEngine = false;
            }
            enableWaveDetect = true;
            enableReflectorDetect = true;
            return;
        }
        if (machineModel == MachineModel.Phoenix) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "MachineType: Phoenix");
            enableCostmap = true;
            enableDocking = true;
            enableDynamic = false;
            enableVision = false;
            enableAutoDock = true;
            enableLaserInteraction = true;
            enablePersonDetect = false;
            enableChildrenDetect = false;
            enableDEngine = false;
            enableWaveDetect = false;
            enableReflectorDetect = true;
            return;
        }
        if (machineModel == MachineModel.CleanBot) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "MachineType: Phoenix");
            enableCostmap = true;
            enableDocking = true;
            enableDynamic = false;
            enableVision = false;
            enableAutoDock = true;
            enableLaserInteraction = true;
            enablePersonDetect = false;
            enableChildrenDetect = false;
            enableDEngine = false;
            enableWaveDetect = false;
            enableReflectorDetect = true;
            return;
        }
        if (machineModel == MachineModel.Ninetales) {
            Pdlog.m3273d(PerceptionKt.getTAG(), "MachineType: Phoenix");
            enableCostmap = true;
            enableDocking = true;
            enableDynamic = false;
            enableVision = false;
            enableAutoDock = true;
            enableLaserInteraction = true;
            enablePersonDetect = false;
            enableChildrenDetect = false;
            enableDEngine = false;
            enableWaveDetect = false;
            enableReflectorDetect = true;
            return;
        }
        Pdlog.m3273d(PerceptionKt.getTAG(), "MachineType: Other");
        enableCostmap = true;
        enableDocking = false;
        enableDynamic = false;
        enableVision = false;
        enableAutoDock = true;
        enableLaserInteraction = true;
        enablePersonDetect = false;
        enableChildrenDetect = false;
        enableDEngine = false;
        enableWaveDetect = false;
        enableReflectorDetect = true;
    }

    public final boolean getEnableCostmap() {
        return enableCostmap;
    }

    public final void setEnableCostmap(boolean z) {
        enableCostmap = z;
    }

    public final boolean getEnableDynamic() {
        return enableDynamic;
    }

    public final void setEnableDynamic(boolean z) {
        enableDynamic = z;
    }

    public final boolean getEnableDocking() {
        return enableDocking;
    }

    public final void setEnableDocking(boolean z) {
        enableDocking = z;
    }

    public final boolean getEnableVision() {
        return enableVision;
    }

    public final void setEnableVision(boolean z) {
        enableVision = z;
    }

    public final boolean getEnableDEngine() {
        return enableDEngine;
    }

    public final void setEnableDEngine(boolean z) {
        enableDEngine = z;
    }

    public final boolean getEnableWaveDetect() {
        return enableWaveDetect;
    }

    public final void setEnableWaveDetect(boolean z) {
        enableWaveDetect = z;
    }

    public final boolean getEnableReflectorDetect() {
        return enableReflectorDetect;
    }

    public final void setEnableReflectorDetect(boolean z) {
        enableReflectorDetect = z;
    }

    public final boolean getEnableAutoDock() {
        return enableAutoDock;
    }

    public final void setEnableAutoDock(boolean z) {
        enableAutoDock = z;
    }

    public final boolean getEnableLaserInteraction() {
        return enableLaserInteraction;
    }

    public final void setEnableLaserInteraction(boolean z) {
        enableLaserInteraction = z;
    }

    public final boolean getEnablePersonDetect() {
        return enablePersonDetect;
    }

    public final void setEnablePersonDetect(boolean z) {
        enablePersonDetect = z;
    }

    public final boolean getEnableChildrenDetect() {
        return enableChildrenDetect;
    }

    public final void setEnableChildrenDetect(boolean z) {
        enableChildrenDetect = z;
    }

    public final boolean getEnableDataRecord() {
        return enableDataRecord;
    }

    public final void setEnableDataRecord(boolean z) {
        enableDataRecord = z;
    }

    public final boolean getPerception_initialized() {
        return perception_initialized;
    }

    public final void setPerception_initialized(boolean z) {
        perception_initialized = z;
    }

    public final boolean getInitialize_result() {
        return initialize_result;
    }

    public final void setInitialize_result(boolean z) {
        initialize_result = z;
    }

    public final int getEnableVisionModules() {
        return enableVisionModules;
    }

    public final void setEnableVisionModules(int i) {
        enableVisionModules = i;
    }
}
