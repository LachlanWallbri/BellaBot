package com.pudutech.rgbdlib;

import android.content.Context;
import android.content.Intent;
import android.os.ParcelFileDescriptor;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.google.gson.Gson;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.compat.topo.ConfigJson;
import com.pudutech.mirsdk.config.MapFilePathConfig;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.hardware.serialize.SensorImageContainer;
import com.pudutech.rgbdlib.activity.CheckToolActivity;
import com.pudutech.rgbdlib.activity.DisplayRGBDActivity;
import com.pudutech.rgbdlib.config.DataJson;
import com.pudutech.rgbdlib.config.ExtrinsicsJson;
import com.pudutech.rgbdlib.config.RGBDConfig;
import com.pudutech.rgbdlib.config.RGBDJson;
import com.pudutech.rgbdlib.util.ConfigUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.ThreadPoolDispatcherKt;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes5.dex
  classes6.dex
 */
/* compiled from: RGBDSensor.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0011\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\u0018\u00002\u00020\u0001:\u0002 \u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010C\u001a\u00020DJ\b\u0010E\u001a\u00020\fH\u0002J\u0006\u0010F\u001a\u00020\fJ\u0006\u0010G\u001a\u00020\fJ\u0011\u0010H\u001a\u00020\f2\u0006\u0010I\u001a\u00020\tH\u0082 J\t\u0010J\u001a\u00020DH\u0082 J\t\u0010K\u001a\u00020DH\u0082 J\b\u0010L\u001a\u00020DH\u0002J\t\u0010M\u001a\u00020DH\u0082 J(\u0010N\u001a\u00020D2\u0006\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020\u000e2\u0006\u0010R\u001a\u00020\u000e2\u0006\u0010S\u001a\u00020\u000eH\u0002J(\u0010T\u001a\u00020D2\u0006\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020\u000e2\u0006\u0010R\u001a\u00020\u000e2\u0006\u0010S\u001a\u00020\u000eH\u0002J(\u0010U\u001a\u00020D2\u0006\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020\u000e2\u0006\u0010R\u001a\u00020\u000e2\u0006\u0010S\u001a\u00020\u000eH\u0002J(\u0010V\u001a\u00020D2\u0006\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020\u000e2\u0006\u0010R\u001a\u00020\u000e2\u0006\u0010S\u001a\u00020\u000eH\u0002J(\u0010W\u001a\u00020D2\u0006\u0010O\u001a\u00020P2\u0006\u0010Q\u001a\u00020\u000e2\u0006\u0010R\u001a\u00020\u000e2\u0006\u0010S\u001a\u00020\u000eH\u0002J\u0006\u0010X\u001a\u00020DJ\t\u0010Y\u001a\u00020\tH\u0082 J\u0014\u0010Z\u001a\b\u0012\u0004\u0012\u00020\t0[H\u0082 ¢\u0006\u0002\u0010\\J\u0006\u0010]\u001a\u00020\tJ\u0006\u0010^\u001a\u00020\tJ\t\u0010_\u001a\u00020\tH\u0082 J\u0014\u0010`\u001a\b\u0012\u0004\u0012\u00020\t0[H\u0082 ¢\u0006\u0002\u0010\\J\u0010\u0010a\u001a\u00020\t2\u0006\u0010b\u001a\u00020\tH\u0002J\t\u0010c\u001a\u00020\tH\u0082 J\u0014\u0010d\u001a\b\u0012\u0004\u0012\u00020\t0[H\u0082 ¢\u0006\u0002\u0010\\J\b\u0010e\u001a\u00020\fH\u0002J\t\u0010f\u001a\u00020\fH\u0082 J\t\u0010g\u001a\u00020\fH\u0082 J\b\u0010h\u001a\u00020\fH\u0002J\t\u0010i\u001a\u00020\fH\u0082 J\t\u0010j\u001a\u00020\fH\u0082 J\t\u0010k\u001a\u00020lH\u0082 J\t\u0010m\u001a\u00020lH\u0082 J\n\u0010n\u001a\u0004\u0018\u00010oH\u0002J\u0018\u0010p\u001a\u00020D2\u0006\u0010q\u001a\u00020\u000e2\b\u0010r\u001a\u0004\u0018\u00010\u001dJ\u0006\u0010s\u001a\u00020DJ)\u0010t\u001a\u00020\f2\u0006\u0010u\u001a\u00020\u000e2\u0006\u0010Q\u001a\u00020\u000e2\u0006\u0010R\u001a\u00020\u000e2\u0006\u0010v\u001a\u00020\u000eH\u0086 J)\u0010w\u001a\u00020\f2\u0006\u0010u\u001a\u00020\u000e2\u0006\u0010Q\u001a\u00020\u000e2\u0006\u0010R\u001a\u00020\u000e2\u0006\u0010v\u001a\u00020\u000eH\u0086 J!\u0010x\u001a\u00020\f2\u0006\u0010y\u001a\u00020\u000e2\u0006\u0010z\u001a\u00020\t2\u0006\u0010?\u001a\u00020\u000eH\u0082 J\u0018\u0010{\u001a\u00020D2\u0006\u0010q\u001a\u00020\u000e2\b\u0010|\u001a\u0004\u0018\u00010\u001dJ!\u0010}\u001a\u00020\f2\u0006\u0010y\u001a\u00020\u000e2\u0006\u0010z\u001a\u00020\t2\u0006\u0010?\u001a\u00020\u000eH\u0082 J\u0011\u0010~\u001a\u00020D2\u0006\u0010\u007f\u001a\u00020\fH\u0086 J\u001a\u0010\u0080\u0001\u001a\u00020D2\u0006\u0010b\u001a\u00020\t2\u0007\u0010\u0081\u0001\u001a\u00020\tH\u0002J\u001c\u0010\u0082\u0001\u001a\u00020\f2\u0007\u0010\u0083\u0001\u001a\u00020\u000e2\u0007\u0010\u0084\u0001\u001a\u00020\u000eH\u0082 J\"\u0010\u0085\u0001\u001a\u00020\f2\u0006\u0010y\u001a\u00020\u000e2\u0006\u0010z\u001a\u00020\t2\u0006\u0010?\u001a\u00020\u000eH\u0082 JÑ\u0003\u0010\u0086\u0001\u001a\u00030\u0087\u00012\u0007\u0010\u0088\u0001\u001a\u00020@2\u0006\u0010)\u001a\u00020\u000e2l\u0010\u0089\u0001\u001ag\u0012\u0017\u0012\u00150\u008b\u0001¢\u0006\u000f\b\u008c\u0001\u0012\n\b\u008d\u0001\u0012\u0005\b\b(\u008e\u0001\u0012\u0015\u0012\u00130\u000e¢\u0006\u000e\b\u008c\u0001\u0012\t\b\u008d\u0001\u0012\u0004\b\b(Q\u0012\u0015\u0012\u00130\u000e¢\u0006\u000e\b\u008c\u0001\u0012\t\b\u008d\u0001\u0012\u0004\b\b(R\u0012\u0015\u0012\u00130\u000e¢\u0006\u000e\b\u008c\u0001\u0012\t\b\u008d\u0001\u0012\u0004\b\b(S\u0012\u0004\u0012\u00020D0\u008a\u00012l\u0010\u008f\u0001\u001ag\u0012\u0017\u0012\u00150\u008b\u0001¢\u0006\u000f\b\u008c\u0001\u0012\n\b\u008d\u0001\u0012\u0005\b\b(\u008e\u0001\u0012\u0015\u0012\u00130\u000e¢\u0006\u000e\b\u008c\u0001\u0012\t\b\u008d\u0001\u0012\u0004\b\b(Q\u0012\u0015\u0012\u00130\u000e¢\u0006\u000e\b\u008c\u0001\u0012\t\b\u008d\u0001\u0012\u0004\b\b(R\u0012\u0015\u0012\u00130\u000e¢\u0006\u000e\b\u008c\u0001\u0012\t\b\u008d\u0001\u0012\u0004\b\b(S\u0012\u0004\u0012\u00020D0\u008a\u00012l\u0010\u0090\u0001\u001ag\u0012\u0017\u0012\u00150\u008b\u0001¢\u0006\u000f\b\u008c\u0001\u0012\n\b\u008d\u0001\u0012\u0005\b\b(\u008e\u0001\u0012\u0015\u0012\u00130\u000e¢\u0006\u000e\b\u008c\u0001\u0012\t\b\u008d\u0001\u0012\u0004\b\b(Q\u0012\u0015\u0012\u00130\u000e¢\u0006\u000e\b\u008c\u0001\u0012\t\b\u008d\u0001\u0012\u0004\b\b(R\u0012\u0015\u0012\u00130\u000e¢\u0006\u000e\b\u008c\u0001\u0012\t\b\u008d\u0001\u0012\u0004\b\b(S\u0012\u0004\u0012\u00020D0\u008a\u00012l\u0010\u0091\u0001\u001ag\u0012\u0017\u0012\u00150\u008b\u0001¢\u0006\u000f\b\u008c\u0001\u0012\n\b\u008d\u0001\u0012\u0005\b\b(\u008e\u0001\u0012\u0015\u0012\u00130\u000e¢\u0006\u000e\b\u008c\u0001\u0012\t\b\u008d\u0001\u0012\u0004\b\b(Q\u0012\u0015\u0012\u00130\u000e¢\u0006\u000e\b\u008c\u0001\u0012\t\b\u008d\u0001\u0012\u0004\b\b(R\u0012\u0015\u0012\u00130\u000e¢\u0006\u000e\b\u008c\u0001\u0012\t\b\u008d\u0001\u0012\u0004\b\b(S\u0012\u0004\u0012\u00020D0\u008a\u0001J\n\u0010\u0092\u0001\u001a\u00020\fH\u0082 J\u0019\u0010\u0093\u0001\u001a\u00020\f2\u0007\u0010\u0094\u0001\u001a\u00020\u00032\u0007\u0010\u0095\u0001\u001a\u00020\u0000J\n\u0010\u0096\u0001\u001a\u00020\fH\u0082 J\u0019\u0010\u0097\u0001\u001a\u00020D2\u0007\u0010\u0094\u0001\u001a\u00020\u00032\u0007\u0010\u0095\u0001\u001a\u00020\u0000J\u0011\u0010\u0098\u0001\u001a\u00020\f2\u0006\u0010I\u001a\u00020\u000eH\u0002J\n\u0010\u0099\u0001\u001a\u00020\fH\u0082 J\n\u0010\u009a\u0001\u001a\u00020\fH\u0082 J\u001a\u0010\u009b\u0001\u001a\u00020\f2\u0006\u0010y\u001a\u00020\u000e2\u0006\u0010z\u001a\u00020\tH\u0082 J\u001a\u0010\u009c\u0001\u001a\u00020\f2\u0006\u0010y\u001a\u00020\u000e2\u0006\u0010z\u001a\u00020\tH\u0082 J#\u0010\u009d\u0001\u001a\u00020\f2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\t2\u000b\b\u0002\u0010\u009e\u0001\u001a\u0004\u0018\u00010oH\u0082 J\u001a\u0010\u009f\u0001\u001a\u00020\f2\u0006\u0010y\u001a\u00020\u000e2\u0006\u0010z\u001a\u00020\tH\u0082 R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u000e\u0010\u001a\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R.\u0010\u001b\u001a\"\u0012\u0004\u0012\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u001d0\u001cj\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u001d`\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u001f\u001a\u001e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\u001cj\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t`\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00150\u0014¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0017R\u000e\u0010\"\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u0017\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0017R\u001a\u0010)\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u0010\"\u0004\b+\u0010\u0012R\u000e\u0010,\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00100\u001a\u0004\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00102\u001a\u0004\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00103\u001a\u0004\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00104\u001a\u0004\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u00106\u001a\u0012\u0012\u0004\u0012\u00020\u000e07j\b\u0012\u0004\u0012\u00020\u000e`8X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010:\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R\u000e\u0010?\u001a\u00020@X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010A\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014¢\u0006\b\n\u0000\u001a\u0004\bB\u0010\u0017¨\u0006¡\u0001"}, m3961d2 = {"Lcom/pudutech/rgbdlib/RGBDSensor;", "", "appContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "CenterRGBDContext", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "DownRGBDContext", "TAG", "", "angstrongRgbdConfigFileName", "angstrongWork", "", "centerRgbdType", "", "getCenterRgbdType", "()I", "setCenterRgbdType", "(I)V", "centernRGBDListeners", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/rgbdlib/RGBDDataCatcher;", "getCenternRGBDListeners", "()Lcom/pudutech/base/architecture/ThreadSafeListener;", "checkRGBDListener", "getCheckRGBDListener", "configFileName", "configSerialMap", "Ljava/util/HashMap;", "Lcom/pudutech/rgbdlib/config/RGBDJson;", "Lkotlin/collections/HashMap;", "curSerialMap", "downRGBDListener", "getDownRGBDListener", "downRgbdConfigFileName", "finishInit", "firstRunRGBD", "fwFileName", "fwVersion", "leftRGBDListeners", "getLeftRGBDListeners", "machineModelId", "getMachineModelId", "setMachineModelId", "numberOfAngStrong", "numberOfOrbbec", "numberOfRealSense", "orbbecWork", "readCenterJob", "Lkotlinx/coroutines/Job;", "readDownJob", "readLeftJob", "readRightJob", "realSenseWork", "replaceList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "ret", "rgbdError", "getRgbdError", "()Ljava/lang/String;", "setRgbdError", "(Ljava/lang/String;)V", "rgbdVersion", "Lcom/pudutech/mirsdk/hardware/serialize/MachineInfo$RGBDType;", "rightRGBDListeners", "getRightRGBDListeners", "RefreshConfigure", "", "checkConfigFile", "checkDownRgbdConfigFile", "checkUpdateFirmware", "checkUpdateFw", "version", "closeAngStrongRGBD", "closeOrbbecRGBD", "closeRGBD", "closeRealSenseRGBD", "destributeCenterRGBD", "fileDescriptor", "Ljava/io/FileDescriptor;", "rows", "cols", "memorySize", "destributeCheckRGBD", "destributeDownRGBD", "destributeLeftRGBD", "destributeRightRGBD", "exportAngstrongConfig", "getAngStrongLastError", "getAngstrongRGBDSerial", "", "()[Ljava/lang/String;", "getGitHash", "getLastError", "getOrbbecLastError", "getOrbbecRGBDSerial", "getProperty", TransferTable.COLUMN_KEY, "getRealSenseLastError", "getRealSenseRGBDSerial", "isNewSystemVersion", "openAngStrongRGBD", "openOrbbecRGBD", "openRGBD", "openRealSenseRGBD", "openUpDownOrbRGBD", "readAngStrongCenterFrame", "Lcom/pudutech/mirsdk/hardware/serialize/SensorImageContainer;", "readCenterFrame", "readFwBin", "", "refreshRGBDJson", "local", "rgbdJson", "releaseRGBD", "runRGBDCheckTool", "fd", "memSize", "runRGBDCheckToolSaveImg", "setAngstrongRGBD", "loacation", "sn", "setConfig", "config", "setOrbbecRGBD", "setParkingMode", "enable", "setProperty", ES6Iterator.VALUE_PROPERTY, "setRGBDCheckTool", "machineType", "rgbdType", "setRealSenseRGBD", "start", "Lcom/pudutech/rgbdlib/RGBDSensor$Result;", "RgbdVersion", "leftCallback", "Lkotlin/Function4;", "Landroid/os/ParcelFileDescriptor;", "Lkotlin/ParameterName;", "name", "parcelFileDescriptor", "rightCallback", "centerCallback", "downCallback", "startAngStrongRGBD", "startCheckToolActivity", "context", ConfigJson.SENSOR, "startOrbbecRGBD", "startPreviewActivity", "startRGBD", "startRealSenseRGBD", "startUpDownOrbRGBD", "updateAngstrongRGBD", "updateOrbbecRGBD", "updateRealSenseFw", "bin", "updateRealSenseRGBD", "Result", "RGBDLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class RGBDSensor {
    private final ExecutorCoroutineDispatcher CenterRGBDContext;
    private final ExecutorCoroutineDispatcher DownRGBDContext;
    private final String TAG;
    private final String angstrongRgbdConfigFileName;
    private boolean angstrongWork;
    private final Context appContext;
    private int centerRgbdType;
    private final ThreadSafeListener<RGBDDataCatcher> centernRGBDListeners;
    private final ThreadSafeListener<RGBDDataCatcher> checkRGBDListener;
    private final String configFileName;
    private HashMap<Integer, RGBDJson> configSerialMap;
    private HashMap<String, String> curSerialMap;
    private final ThreadSafeListener<RGBDDataCatcher> downRGBDListener;
    private final String downRgbdConfigFileName;
    private boolean finishInit;
    private boolean firstRunRGBD;
    private final String fwFileName;
    private final String fwVersion;
    private final ThreadSafeListener<RGBDDataCatcher> leftRGBDListeners;
    private int machineModelId;
    private int numberOfAngStrong;
    private int numberOfOrbbec;
    private int numberOfRealSense;
    private boolean orbbecWork;
    private Job readCenterJob;
    private Job readDownJob;
    private Job readLeftJob;
    private Job readRightJob;
    private boolean realSenseWork;
    private ArrayList<Integer> replaceList;
    private boolean ret;
    private String rgbdError;
    private MachineInfo.RGBDType rgbdVersion;
    private final ThreadSafeListener<RGBDDataCatcher> rightRGBDListeners;

    /* compiled from: RGBDSensor.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/rgbdlib/RGBDSensor$RgbdState;", "Ljava/io/Serializable;", "onState", "", "state", "", "RGBDLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public interface RgbdState extends Serializable {
        void onState(String state);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes6.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MachineInfo.RGBDType.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[MachineInfo.RGBDType.NODevice.ordinal()] = 1;
        }
    }

    private final native boolean checkUpdateFw(String version);

    private final native void closeAngStrongRGBD();

    private final native void closeOrbbecRGBD();

    private final native void closeRealSenseRGBD();

    private final native String getAngStrongLastError();

    private final native String[] getAngstrongRGBDSerial();

    private final native String getOrbbecLastError();

    private final native String[] getOrbbecRGBDSerial();

    private final native String getRealSenseLastError();

    private final native String[] getRealSenseRGBDSerial();

    private final native boolean openAngStrongRGBD();

    private final native boolean openOrbbecRGBD();

    private final native boolean openRealSenseRGBD();

    private final native boolean openUpDownOrbRGBD();

    private final native SensorImageContainer readAngStrongCenterFrame();

    private final native SensorImageContainer readCenterFrame();

    private final native boolean setAngstrongRGBD(int loacation, String sn, int rgbdVersion);

    private final native boolean setOrbbecRGBD(int loacation, String sn, int rgbdVersion);

    private final native boolean setRGBDCheckTool(int machineType, int rgbdType);

    private final native boolean setRealSenseRGBD(int loacation, String sn, int rgbdVersion);

    private final native boolean startAngStrongRGBD();

    private final native boolean startOrbbecRGBD();

    private final native boolean startRealSenseRGBD();

    private final native boolean startUpDownOrbRGBD();

    private final native boolean updateAngstrongRGBD(int loacation, String sn);

    private final native boolean updateOrbbecRGBD(int loacation, String sn);

    private final native boolean updateRealSenseFw(String fwVersion, byte[] bin);

    private final native boolean updateRealSenseRGBD(int loacation, String sn);

    public final String getGitHash() {
        return "{\"RGBDLib\":\"commit: 7b1f8d5, auth: “wuminghao”<“wuminghao@pudutech.com”>, time: “Fri May 28 16:21:38 2021 +0800”\"}";
    }

    public final native boolean runRGBDCheckTool(int fd, int rows, int cols, int memSize);

    public final native boolean runRGBDCheckToolSaveImg(int fd, int rows, int cols, int memSize);

    public final native void setParkingMode(boolean enable);

    public RGBDSensor(Context appContext) {
        Intrinsics.checkParameterIsNotNull(appContext, "appContext");
        this.appContext = appContext;
        this.TAG = "rgbd";
        this.fwVersion = "05.12.07.191";
        this.fwFileName = "Signed_Image_UVC_5_12_7_191.bin";
        this.configFileName = "/sdcard/pudu/config/rgbd.json";
        this.downRgbdConfigFileName = MapFilePathConfig.PRE_PARAM_PATH;
        this.angstrongRgbdConfigFileName = "asg_calib.xml";
        this.firstRunRGBD = true;
        this.rgbdVersion = MachineInfo.RGBDType.NODevice;
        this.curSerialMap = new HashMap<>();
        this.replaceList = new ArrayList<>();
        this.configSerialMap = new HashMap<>();
        this.CenterRGBDContext = ThreadPoolDispatcherKt.newSingleThreadContext("ReadCenterRGBD");
        this.DownRGBDContext = ThreadPoolDispatcherKt.newSingleThreadContext("DownReadCenterRGBD");
        this.ret = true;
        this.machineModelId = -1;
        this.centerRgbdType = -1;
        this.rgbdError = "";
        this.leftRGBDListeners = new ThreadSafeListener<>();
        this.rightRGBDListeners = new ThreadSafeListener<>();
        this.centernRGBDListeners = new ThreadSafeListener<>();
        this.downRGBDListener = new ThreadSafeListener<>();
        this.checkRGBDListener = new ThreadSafeListener<>();
        System.loadLibrary("realsense_rgbd");
        System.loadLibrary("orbbec_rgbd");
        System.loadLibrary("angstrong_rgbd");
        System.loadLibrary("check_tool");
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes6.dex
     */
    /* compiled from: RGBDSensor.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\r\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\t¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/rgbdlib/RGBDSensor$Result;", "", "isSuccess", "", "description", "", "(ZLjava/lang/String;)V", "getDescription", "()Ljava/lang/String;", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "RGBDLib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static final /* data */ class Result {
        private final String description;
        private final boolean isSuccess;

        public static /* synthetic */ Result copy$default(Result result, boolean z, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                z = result.isSuccess;
            }
            if ((i & 2) != 0) {
                str = result.description;
            }
            return result.copy(z, str);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getIsSuccess() {
            return this.isSuccess;
        }

        /* renamed from: component2, reason: from getter */
        public final String getDescription() {
            return this.description;
        }

        public final Result copy(boolean isSuccess, String description) {
            Intrinsics.checkParameterIsNotNull(description, "description");
            return new Result(isSuccess, description);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Result)) {
                return false;
            }
            Result result = (Result) other;
            return this.isSuccess == result.isSuccess && Intrinsics.areEqual(this.description, result.description);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v4 */
        /* JADX WARN: Type inference failed for: r0v5 */
        public int hashCode() {
            boolean z = this.isSuccess;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            int i = r0 * 31;
            String str = this.description;
            return i + (str != null ? str.hashCode() : 0);
        }

        public String toString() {
            return "Result(isSuccess=" + this.isSuccess + ", description=" + this.description + ")";
        }

        public Result(boolean z, String description) {
            Intrinsics.checkParameterIsNotNull(description, "description");
            this.isSuccess = z;
            this.description = description;
        }

        public final String getDescription() {
            return this.description;
        }

        public final boolean isSuccess() {
            return this.isSuccess;
        }
    }

    public final int getMachineModelId() {
        return this.machineModelId;
    }

    public final void setMachineModelId(int i) {
        this.machineModelId = i;
    }

    public final int getCenterRgbdType() {
        return this.centerRgbdType;
    }

    public final void setCenterRgbdType(int i) {
        this.centerRgbdType = i;
    }

    public final String getRgbdError() {
        return this.rgbdError;
    }

    public final void setRgbdError(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.rgbdError = str;
    }

    public final ThreadSafeListener<RGBDDataCatcher> getLeftRGBDListeners() {
        return this.leftRGBDListeners;
    }

    public final ThreadSafeListener<RGBDDataCatcher> getRightRGBDListeners() {
        return this.rightRGBDListeners;
    }

    public final ThreadSafeListener<RGBDDataCatcher> getCenternRGBDListeners() {
        return this.centernRGBDListeners;
    }

    public final ThreadSafeListener<RGBDDataCatcher> getDownRGBDListener() {
        return this.downRGBDListener;
    }

    public final ThreadSafeListener<RGBDDataCatcher> getCheckRGBDListener() {
        return this.checkRGBDListener;
    }

    static /* synthetic */ boolean updateRealSenseFw$default(RGBDSensor rGBDSensor, String str, byte[] bArr, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            bArr = (byte[]) null;
        }
        return rGBDSensor.updateRealSenseFw(str, bArr);
    }

    public final void startPreviewActivity(Context context, RGBDSensor sensor) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(sensor, "sensor");
        Intent intent = new Intent(context, (Class<?>) DisplayRGBDActivity.class);
        intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
        DisplayRGBDActivity.INSTANCE.setRgbdSensor(sensor);
        context.startActivity(intent);
    }

    public final boolean startCheckToolActivity(Context context, RGBDSensor sensor) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(sensor, "sensor");
        int i = this.centerRgbdType;
        if (i == -1 || !setRGBDCheckTool(this.machineModelId, i)) {
            return false;
        }
        Intent intent = new Intent(context, (Class<?>) CheckToolActivity.class);
        CheckToolActivity.INSTANCE.setRgbdSensor(sensor);
        context.startActivity(intent);
        return true;
    }

    public final void setConfig(int local, RGBDJson config) {
        if (config != null) {
            this.configSerialMap.put(Integer.valueOf(local), config);
            if (this.curSerialMap.get(config.getSerial()) != null && Intrinsics.areEqual(this.curSerialMap.get(config.getSerial()), config.getType())) {
                this.curSerialMap.remove(config.getSerial());
                if (!Intrinsics.areEqual((Object) config.getShield(), (Object) true)) {
                    String type = config.getType();
                    if (Intrinsics.areEqual(type, RGBDType.RealSense.getStr())) {
                        if (setRealSenseRGBD(local, config.getSerial(), 0)) {
                            this.realSenseWork = true;
                            return;
                        }
                        this.ret = false;
                        this.rgbdError = this.rgbdError + "set real sense rgbd fail";
                        return;
                    }
                    if (Intrinsics.areEqual(type, RGBDType.Orbbec.getStr())) {
                        if (setOrbbecRGBD(local, config.getSerial(), 0)) {
                            this.orbbecWork = true;
                            return;
                        }
                        this.ret = false;
                        this.rgbdError = this.rgbdError + "set orbbec rgbd fail";
                        return;
                    }
                    if (Intrinsics.areEqual(type, RGBDType.AngStrong.getStr())) {
                        exportAngstrongConfig();
                        if (setAngstrongRGBD(local, config.getSerial(), 0)) {
                            this.angstrongWork = true;
                            return;
                        }
                        this.ret = false;
                        this.rgbdError = this.rgbdError + "set angstrong rgbd fail";
                        return;
                    }
                    String str = "unkonwn rgbd type " + config.getType();
                    this.rgbdError = str;
                    Pdlog.m3274e(this.TAG, str);
                    this.ret = false;
                    return;
                }
                String str2 = "rgbd shield " + config.getSerial();
                this.rgbdError = str2;
                Pdlog.m3277w(this.TAG, str2);
                return;
            }
            Pdlog.m3277w(this.TAG, "vail serial number " + config.getSerial());
            if (Intrinsics.areEqual((Object) config.getShield(), (Object) false)) {
                this.replaceList.add(Integer.valueOf(local));
            }
        }
    }

    public final void refreshRGBDJson(int local, RGBDJson rgbdJson) {
        if (rgbdJson != null) {
            String type = rgbdJson.getType();
            if (Intrinsics.areEqual(type, RGBDType.RealSense.getStr())) {
                updateRealSenseRGBD(local, rgbdJson.getSerial());
                this.realSenseWork = true;
                return;
            }
            if (Intrinsics.areEqual(type, RGBDType.Orbbec.getStr())) {
                updateOrbbecRGBD(local, rgbdJson.getSerial());
                this.orbbecWork = true;
            } else {
                if (Intrinsics.areEqual(type, RGBDType.AngStrong.getStr())) {
                    updateAngstrongRGBD(local, rgbdJson.getSerial());
                    this.angstrongWork = true;
                    return;
                }
                String str = "unkonwn rgbd type " + rgbdJson.getType();
                this.rgbdError = str;
                Pdlog.m3274e(this.TAG, str);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:303:0x0aa8, code lost:
    
        if (r3 == com.pudutech.mirsdk.hardware.serialize.MachineModel.Ninetales.getId()) goto L309;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0cb8  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0bc2 A[Catch: Exception -> 0x06ce, TryCatch #0 {Exception -> 0x06ce, blocks: (B:96:0x06c7, B:112:0x0bbc, B:114:0x0bc2, B:116:0x0bc8, B:117:0x0bcc, B:119:0x0bda, B:120:0x0bdd, B:122:0x0be9, B:123:0x0bec, B:125:0x0bf8, B:126:0x0bfb, B:128:0x0bff, B:131:0x0c14, B:135:0x0c26, B:138:0x0c28, B:140:0x0c2c, B:143:0x0c41, B:147:0x0c53, B:150:0x0c55, B:152:0x0c59, B:155:0x0c6e, B:159:0x0c80, B:165:0x0728, B:248:0x0b6c, B:250:0x0b83, B:252:0x0b9c, B:255:0x0baf, B:257:0x0c83, B:258:0x0c8a, B:286:0x0af4, B:288:0x0af8, B:291:0x0afd, B:292:0x0b09, B:294:0x0b5e, B:295:0x0b61, B:297:0x0b67), top: B:95:0x06c7 }] */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0bff A[Catch: Exception -> 0x06ce, TryCatch #0 {Exception -> 0x06ce, blocks: (B:96:0x06c7, B:112:0x0bbc, B:114:0x0bc2, B:116:0x0bc8, B:117:0x0bcc, B:119:0x0bda, B:120:0x0bdd, B:122:0x0be9, B:123:0x0bec, B:125:0x0bf8, B:126:0x0bfb, B:128:0x0bff, B:131:0x0c14, B:135:0x0c26, B:138:0x0c28, B:140:0x0c2c, B:143:0x0c41, B:147:0x0c53, B:150:0x0c55, B:152:0x0c59, B:155:0x0c6e, B:159:0x0c80, B:165:0x0728, B:248:0x0b6c, B:250:0x0b83, B:252:0x0b9c, B:255:0x0baf, B:257:0x0c83, B:258:0x0c8a, B:286:0x0af4, B:288:0x0af8, B:291:0x0afd, B:292:0x0b09, B:294:0x0b5e, B:295:0x0b61, B:297:0x0b67), top: B:95:0x06c7 }] */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0c2c A[Catch: Exception -> 0x06ce, TryCatch #0 {Exception -> 0x06ce, blocks: (B:96:0x06c7, B:112:0x0bbc, B:114:0x0bc2, B:116:0x0bc8, B:117:0x0bcc, B:119:0x0bda, B:120:0x0bdd, B:122:0x0be9, B:123:0x0bec, B:125:0x0bf8, B:126:0x0bfb, B:128:0x0bff, B:131:0x0c14, B:135:0x0c26, B:138:0x0c28, B:140:0x0c2c, B:143:0x0c41, B:147:0x0c53, B:150:0x0c55, B:152:0x0c59, B:155:0x0c6e, B:159:0x0c80, B:165:0x0728, B:248:0x0b6c, B:250:0x0b83, B:252:0x0b9c, B:255:0x0baf, B:257:0x0c83, B:258:0x0c8a, B:286:0x0af4, B:288:0x0af8, B:291:0x0afd, B:292:0x0b09, B:294:0x0b5e, B:295:0x0b61, B:297:0x0b67), top: B:95:0x06c7 }] */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0c59 A[Catch: Exception -> 0x06ce, TryCatch #0 {Exception -> 0x06ce, blocks: (B:96:0x06c7, B:112:0x0bbc, B:114:0x0bc2, B:116:0x0bc8, B:117:0x0bcc, B:119:0x0bda, B:120:0x0bdd, B:122:0x0be9, B:123:0x0bec, B:125:0x0bf8, B:126:0x0bfb, B:128:0x0bff, B:131:0x0c14, B:135:0x0c26, B:138:0x0c28, B:140:0x0c2c, B:143:0x0c41, B:147:0x0c53, B:150:0x0c55, B:152:0x0c59, B:155:0x0c6e, B:159:0x0c80, B:165:0x0728, B:248:0x0b6c, B:250:0x0b83, B:252:0x0b9c, B:255:0x0baf, B:257:0x0c83, B:258:0x0c8a, B:286:0x0af4, B:288:0x0af8, B:291:0x0afd, B:292:0x0b09, B:294:0x0b5e, B:295:0x0b61, B:297:0x0b67), top: B:95:0x06c7 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x02d0 A[Catch: Exception -> 0x02e9, TryCatch #5 {Exception -> 0x02e9, blocks: (B:19:0x02b7, B:21:0x02d0, B:388:0x02e1, B:389:0x02e8), top: B:18:0x02b7 }] */
    /* JADX WARN: Removed duplicated region for block: B:250:0x0b83 A[Catch: Exception -> 0x06ce, TryCatch #0 {Exception -> 0x06ce, blocks: (B:96:0x06c7, B:112:0x0bbc, B:114:0x0bc2, B:116:0x0bc8, B:117:0x0bcc, B:119:0x0bda, B:120:0x0bdd, B:122:0x0be9, B:123:0x0bec, B:125:0x0bf8, B:126:0x0bfb, B:128:0x0bff, B:131:0x0c14, B:135:0x0c26, B:138:0x0c28, B:140:0x0c2c, B:143:0x0c41, B:147:0x0c53, B:150:0x0c55, B:152:0x0c59, B:155:0x0c6e, B:159:0x0c80, B:165:0x0728, B:248:0x0b6c, B:250:0x0b83, B:252:0x0b9c, B:255:0x0baf, B:257:0x0c83, B:258:0x0c8a, B:286:0x0af4, B:288:0x0af8, B:291:0x0afd, B:292:0x0b09, B:294:0x0b5e, B:295:0x0b61, B:297:0x0b67), top: B:95:0x06c7 }] */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0c83 A[Catch: Exception -> 0x06ce, TryCatch #0 {Exception -> 0x06ce, blocks: (B:96:0x06c7, B:112:0x0bbc, B:114:0x0bc2, B:116:0x0bc8, B:117:0x0bcc, B:119:0x0bda, B:120:0x0bdd, B:122:0x0be9, B:123:0x0bec, B:125:0x0bf8, B:126:0x0bfb, B:128:0x0bff, B:131:0x0c14, B:135:0x0c26, B:138:0x0c28, B:140:0x0c2c, B:143:0x0c41, B:147:0x0c53, B:150:0x0c55, B:152:0x0c59, B:155:0x0c6e, B:159:0x0c80, B:165:0x0728, B:248:0x0b6c, B:250:0x0b83, B:252:0x0b9c, B:255:0x0baf, B:257:0x0c83, B:258:0x0c8a, B:286:0x0af4, B:288:0x0af8, B:291:0x0afd, B:292:0x0b09, B:294:0x0b5e, B:295:0x0b61, B:297:0x0b67), top: B:95:0x06c7 }] */
    /* JADX WARN: Removed duplicated region for block: B:306:0x06b0 A[Catch: Exception -> 0x03ec, TryCatch #3 {Exception -> 0x03ec, blocks: (B:370:0x03af, B:372:0x03da, B:373:0x03dd, B:375:0x03e3, B:376:0x03e6, B:42:0x0419, B:48:0x044a, B:54:0x047b, B:60:0x04a4, B:62:0x04b9, B:63:0x04c2, B:65:0x04c8, B:66:0x04cb, B:68:0x04d1, B:69:0x04da, B:71:0x04e0, B:72:0x04e3, B:74:0x04e9, B:75:0x04f2, B:77:0x04f8, B:78:0x04fb, B:80:0x0501, B:81:0x050a, B:83:0x0510, B:84:0x0513, B:89:0x0687, B:91:0x06a0, B:97:0x06c9, B:109:0x070d, B:166:0x072a, B:170:0x077a, B:172:0x0792, B:174:0x07a2, B:175:0x07ab, B:177:0x07bb, B:178:0x07c4, B:180:0x07d4, B:181:0x07db, B:183:0x07e2, B:185:0x0800, B:186:0x0804, B:187:0x096f, B:190:0x081d, B:192:0x0829, B:194:0x0839, B:195:0x0842, B:197:0x0852, B:198:0x085b, B:200:0x086b, B:201:0x0872, B:203:0x0879, B:205:0x0897, B:206:0x089b, B:208:0x08b4, B:210:0x08c0, B:212:0x08d0, B:213:0x08d9, B:215:0x08e9, B:216:0x08f2, B:218:0x0902, B:219:0x0909, B:221:0x0910, B:223:0x092e, B:224:0x0931, B:226:0x0949, B:230:0x0985, B:239:0x09bd, B:241:0x09c1, B:243:0x0a05, B:244:0x0a08, B:246:0x0a0e, B:259:0x0a13, B:261:0x0a19, B:264:0x0a1e, B:265:0x0a2b, B:267:0x0a80, B:268:0x0a83, B:270:0x0a89, B:279:0x0aae, B:281:0x0ae5, B:282:0x0ae8, B:284:0x0aee, B:306:0x06b0, B:307:0x06b7, B:308:0x0523, B:310:0x0529, B:312:0x052f, B:315:0x0536, B:317:0x055e, B:320:0x0567, B:322:0x056e, B:324:0x0574, B:325:0x057b, B:327:0x0583, B:330:0x0593, B:331:0x059c, B:333:0x05a4, B:335:0x05aa, B:337:0x05b0, B:338:0x05b9, B:340:0x05bf, B:341:0x05c2, B:343:0x05c8, B:344:0x05e1, B:346:0x05e7, B:347:0x05f0, B:349:0x05f6, B:350:0x05fa, B:352:0x0600, B:354:0x0606, B:356:0x0610, B:358:0x0678, B:359:0x067b, B:361:0x0681, B:362:0x0684), top: B:369:0x03af }] */
    /* JADX WARN: Removed duplicated region for block: B:388:0x02e1 A[Catch: Exception -> 0x02e9, TryCatch #5 {Exception -> 0x02e9, blocks: (B:19:0x02b7, B:21:0x02d0, B:388:0x02e1, B:389:0x02e8), top: B:18:0x02b7 }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x06a0 A[Catch: Exception -> 0x03ec, TryCatch #3 {Exception -> 0x03ec, blocks: (B:370:0x03af, B:372:0x03da, B:373:0x03dd, B:375:0x03e3, B:376:0x03e6, B:42:0x0419, B:48:0x044a, B:54:0x047b, B:60:0x04a4, B:62:0x04b9, B:63:0x04c2, B:65:0x04c8, B:66:0x04cb, B:68:0x04d1, B:69:0x04da, B:71:0x04e0, B:72:0x04e3, B:74:0x04e9, B:75:0x04f2, B:77:0x04f8, B:78:0x04fb, B:80:0x0501, B:81:0x050a, B:83:0x0510, B:84:0x0513, B:89:0x0687, B:91:0x06a0, B:97:0x06c9, B:109:0x070d, B:166:0x072a, B:170:0x077a, B:172:0x0792, B:174:0x07a2, B:175:0x07ab, B:177:0x07bb, B:178:0x07c4, B:180:0x07d4, B:181:0x07db, B:183:0x07e2, B:185:0x0800, B:186:0x0804, B:187:0x096f, B:190:0x081d, B:192:0x0829, B:194:0x0839, B:195:0x0842, B:197:0x0852, B:198:0x085b, B:200:0x086b, B:201:0x0872, B:203:0x0879, B:205:0x0897, B:206:0x089b, B:208:0x08b4, B:210:0x08c0, B:212:0x08d0, B:213:0x08d9, B:215:0x08e9, B:216:0x08f2, B:218:0x0902, B:219:0x0909, B:221:0x0910, B:223:0x092e, B:224:0x0931, B:226:0x0949, B:230:0x0985, B:239:0x09bd, B:241:0x09c1, B:243:0x0a05, B:244:0x0a08, B:246:0x0a0e, B:259:0x0a13, B:261:0x0a19, B:264:0x0a1e, B:265:0x0a2b, B:267:0x0a80, B:268:0x0a83, B:270:0x0a89, B:279:0x0aae, B:281:0x0ae5, B:282:0x0ae8, B:284:0x0aee, B:306:0x06b0, B:307:0x06b7, B:308:0x0523, B:310:0x0529, B:312:0x052f, B:315:0x0536, B:317:0x055e, B:320:0x0567, B:322:0x056e, B:324:0x0574, B:325:0x057b, B:327:0x0583, B:330:0x0593, B:331:0x059c, B:333:0x05a4, B:335:0x05aa, B:337:0x05b0, B:338:0x05b9, B:340:0x05bf, B:341:0x05c2, B:343:0x05c8, B:344:0x05e1, B:346:0x05e7, B:347:0x05f0, B:349:0x05f6, B:350:0x05fa, B:352:0x0600, B:354:0x0606, B:356:0x0610, B:358:0x0678, B:359:0x067b, B:361:0x0681, B:362:0x0684), top: B:369:0x03af }] */
    /* JADX WARN: Type inference failed for: r15v1 */
    /* JADX WARN: Type inference failed for: r15v2, types: [boolean] */
    /* JADX WARN: Type inference failed for: r15v28 */
    /* JADX WARN: Type inference failed for: r3v103 */
    /* JADX WARN: Type inference failed for: r3v104 */
    /* JADX WARN: Type inference failed for: r3v61, types: [int] */
    /* JADX WARN: Type inference failed for: r3v62 */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v23 */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v9, types: [int, boolean] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean startRGBD(int version) {
        String str;
        String str2;
        Boolean bool;
        Integer num;
        Boolean bool2;
        String str3;
        int i;
        ?? r15;
        char c;
        int i2;
        int i3;
        Exception exc;
        Gson gson;
        int i4;
        String str4;
        String str5;
        RGBDConfig rGBDConfig;
        Boolean bool3;
        String str6;
        RGBDConfig rGBDConfig2;
        boolean z;
        String json;
        ?? r3;
        Boolean bool4;
        String json2;
        RGBDConfig rGBDConfig3;
        Boolean bool5;
        String str7;
        boolean z2;
        String json3;
        this.machineModelId = version;
        boolean exists = new File(this.configFileName).exists();
        Float valueOf = Float.valueOf(0.12f);
        Float valueOf2 = Float.valueOf(-0.0797f);
        Float valueOf3 = Float.valueOf(0.0797f);
        Float valueOf4 = Float.valueOf(0.23012f);
        Float valueOf5 = Float.valueOf(0.18105f);
        Float valueOf6 = Float.valueOf(0.61f);
        Float valueOf7 = Float.valueOf(0.12835f);
        Float valueOf8 = Float.valueOf(0.20661f);
        Float valueOf9 = Float.valueOf(3.14159f);
        Float valueOf10 = Float.valueOf(0.23f);
        Float valueOf11 = Float.valueOf(-0.61f);
        Float valueOf12 = Float.valueOf(0.0f);
        if (!exists) {
            File file = new File(ConfigUtil.CONFIG_DIR);
            if (!file.exists()) {
                file.mkdirs();
            }
            Gson gson2 = new Gson();
            RGBDConfig rGBDConfig4 = new RGBDConfig(null, null, null, null, null, true);
            Pdlog.m3274e("rgbd", "rgbdType" + this.machineModelId);
            int i5 = this.machineModelId;
            try {
                if (i5 != MachineModel.Hls.getId() && i5 != MachineModel.Puductor.getId()) {
                    str7 = "gson.toJson(configure)";
                    rGBDConfig3 = rGBDConfig4;
                    bool5 = false;
                    num = 0;
                    bool2 = true;
                    str3 = "/sdcard/pudu/costmap_data";
                    z2 = false;
                    z2 = false;
                    if (i5 == MachineModel.BellaBot.getId() || i5 == MachineModel.RecycleDog.getId()) {
                        RGBDJson rGBDJson = new RGBDJson(RGBDType.RealSense.getStr(), "origin", null, null, new ExtrinsicsJson(valueOf8, valueOf3, valueOf7, valueOf6, valueOf11, valueOf12));
                        RGBDJson rGBDJson2 = new RGBDJson(RGBDType.RealSense.getStr(), "origin", null, null, new ExtrinsicsJson(valueOf8, valueOf2, valueOf7, valueOf11, valueOf11, valueOf9));
                        RGBDJson rGBDJson3 = new RGBDJson(RGBDType.RealSense.getStr(), "origin", null, null, null);
                        DataJson dataJson = new DataJson(str3, null);
                        rGBDConfig3.setLeft_rgbd(rGBDJson);
                        rGBDConfig3.setRight_rgbd(rGBDJson2);
                        rGBDConfig3.setCenter_rgbd(rGBDJson3);
                        rGBDConfig3.setData(dataJson);
                    } else if (i5 == MachineModel.Ninetales.getId()) {
                        RGBDJson rGBDJson4 = new RGBDJson(RGBDType.RealSense.getStr(), "origin", null, null, new ExtrinsicsJson(valueOf8, valueOf3, valueOf7, valueOf6, valueOf11, valueOf12));
                        RGBDJson rGBDJson5 = new RGBDJson(RGBDType.RealSense.getStr(), "origin", null, null, new ExtrinsicsJson(valueOf8, valueOf2, valueOf7, valueOf11, valueOf11, valueOf9));
                        DataJson dataJson2 = new DataJson(str3, null);
                        rGBDConfig3.setLeft_rgbd(rGBDJson4);
                        rGBDConfig3.setRight_rgbd(rGBDJson5);
                        rGBDConfig3.setData(dataJson2);
                    } else {
                        if (i5 == MachineModel.Peanut.getId()) {
                            RGBDJson rGBDJson6 = new RGBDJson(RGBDType.RealSense.getStr(), "origin", null, null, new ExtrinsicsJson(valueOf10, valueOf12, valueOf, valueOf12, Float.valueOf(5.48f), valueOf12));
                            RGBDJson rGBDJson7 = new RGBDJson(RGBDType.RealSense.getStr(), "origin", null, null, new ExtrinsicsJson(valueOf10, valueOf12, Float.valueOf(0.904f), valueOf12, Float.valueOf(0.925f), valueOf12));
                            DataJson dataJson3 = new DataJson(str3, null);
                            rGBDConfig3.setCenter_rgbd(rGBDJson6);
                            rGBDConfig3.setDown_rgbd(rGBDJson7);
                            rGBDConfig3.setData(dataJson3);
                            str2 = "rgbd";
                            bool = null;
                        } else {
                            str2 = "rgbd";
                            if (i5 == MachineModel.Firefox.getId()) {
                                rGBDConfig3.setCenter_rgbd(new RGBDJson(RGBDType.RealSense.getStr(), "origin", null, null, null));
                                bool = null;
                                rGBDConfig3.setReset(null);
                            } else {
                                this.rgbdError = "unkonw machine type";
                                return false;
                            }
                        }
                        FileOutputStream fileOutputStream = new FileOutputStream(new File(this.configFileName));
                        json3 = gson2.toJson(rGBDConfig3);
                        str = str7;
                        Intrinsics.checkExpressionValueIsNotNull(json3, str);
                        Charset charset = Charsets.UTF_8;
                        if (json3 != null) {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }
                        byte[] bytes = json3.getBytes(charset);
                        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
                        fileOutputStream.write(bytes);
                        fileOutputStream.close();
                        i = 1;
                        r15 = z2;
                    }
                } else {
                    rGBDConfig3 = rGBDConfig4;
                    num = 0;
                    bool2 = true;
                    bool5 = false;
                    str7 = "gson.toJson(configure)";
                    str3 = "/sdcard/pudu/costmap_data";
                    RGBDJson rGBDJson8 = new RGBDJson(RGBDType.RealSense.getStr(), "origin", null, null, new ExtrinsicsJson(valueOf5, Float.valueOf(0.08143f), valueOf4, valueOf6, valueOf11, valueOf12));
                    RGBDJson rGBDJson9 = new RGBDJson(RGBDType.RealSense.getStr(), "origin", null, null, new ExtrinsicsJson(valueOf5, Float.valueOf(-0.08143f), valueOf4, valueOf11, valueOf11, valueOf9));
                    DataJson dataJson4 = new DataJson(str3, null);
                    rGBDConfig3.setLeft_rgbd(rGBDJson8);
                    rGBDConfig3.setRight_rgbd(rGBDJson9);
                    rGBDConfig3.setData(dataJson4);
                }
                FileOutputStream fileOutputStream2 = new FileOutputStream(new File(this.configFileName));
                json3 = gson2.toJson(rGBDConfig3);
                str = str7;
                Intrinsics.checkExpressionValueIsNotNull(json3, str);
                Charset charset2 = Charsets.UTF_8;
                if (json3 != null) {
                }
            } catch (Exception e) {
                String str8 = "rgbd config file create err: " + e.getMessage();
                this.rgbdError = str8;
                Object[] objArr = new Object[1];
                objArr[z2 ? 1 : 0] = str8;
                Pdlog.m3274e(str2, objArr);
                return z2;
            }
            str2 = "rgbd";
            bool = bool5;
            z2 = false;
        } else {
            str = "gson.toJson(configure)";
            str2 = "rgbd";
            bool = false;
            num = 0;
            bool2 = true;
            str3 = "/sdcard/pudu/costmap_data";
            i = 1;
            r15 = 0;
        }
        int i6 = this.machineModelId;
        if ((i6 == MachineModel.BellaBot.getId() || i6 == MachineModel.RecycleDog.getId() || i6 == MachineModel.Peanut.getId() || i6 == MachineModel.Phoenix.getId()) && !checkDownRgbdConfigFile()) {
            this.rgbdError = "check down rgbd config fail";
            String str9 = this.TAG;
            Object[] objArr2 = new Object[i];
            objArr2[r15] = "check down rgbd config fail";
            Pdlog.m3274e(str9, objArr2);
            this.ret = r15;
        }
        if (!updateRealSenseFw(this.fwVersion, readFwBin())) {
            this.ret = r15;
            this.rgbdError = "real sense update fw fail";
            String str10 = this.TAG;
            Object[] objArr3 = new Object[i];
            objArr3[r15] = "real sense update fw fail";
            Pdlog.m3274e(str10, objArr3);
        }
        exportAngstrongConfig();
        try {
            gson = new Gson();
            RGBDConfig rGBDConfig5 = (RGBDConfig) gson.fromJson((Reader) new FileReader(this.configFileName), RGBDConfig.class);
            if (this.machineModelId == MachineModel.Peanut.getId()) {
                try {
                    str4 = str;
                    i4 = 1;
                    i2 = 1;
                    str5 = str2;
                    try {
                        ExtrinsicsJson extrinsicsJson = new ExtrinsicsJson(valueOf10, valueOf12, valueOf, valueOf12, Float.valueOf(5.48f), valueOf12);
                        ExtrinsicsJson extrinsicsJson2 = new ExtrinsicsJson(valueOf10, valueOf12, Float.valueOf(0.904f), valueOf12, Float.valueOf(0.925f), valueOf12);
                        DataJson dataJson5 = new DataJson(str3, num);
                        RGBDJson center_rgbd = rGBDConfig5.getCenter_rgbd();
                        if (center_rgbd != null) {
                            center_rgbd.setExtrinsics(extrinsicsJson);
                        }
                        RGBDJson down_rgbd = rGBDConfig5.getDown_rgbd();
                        if (down_rgbd != null) {
                            down_rgbd.setExtrinsics(extrinsicsJson2);
                        }
                        rGBDConfig = rGBDConfig5;
                        rGBDConfig.setData(dataJson5);
                    } catch (Exception e2) {
                        e = e2;
                        exc = e;
                        c = 0;
                        i3 = i4;
                        String str11 = this.TAG;
                        Object[] objArr4 = new Object[i3];
                        objArr4[c] = "read rgbd config file exception " + exc.getLocalizedMessage();
                        Pdlog.m3274e(str11, objArr4);
                        this.rgbdError = "read config file fail";
                        if (!this.ret) {
                        }
                        return this.ret;
                    }
                } catch (Exception e3) {
                    e = e3;
                    i4 = 1;
                }
            } else {
                str4 = str;
                rGBDConfig = rGBDConfig5;
                str5 = str2;
                i2 = 1;
            }
            try {
                this.curSerialMap.clear();
                this.configSerialMap.clear();
                this.replaceList.clear();
                String[] realSenseRGBDSerial = getRealSenseRGBDSerial();
                ArrayList arrayList = new ArrayList(realSenseRGBDSerial.length);
                for (String str12 : realSenseRGBDSerial) {
                    this.curSerialMap.put(str12, RGBDType.RealSense.getStr());
                    int i7 = this.numberOfRealSense;
                    this.numberOfRealSense = i7 + 1;
                    arrayList.add(Integer.valueOf(i7));
                }
                ArrayList arrayList2 = arrayList;
                String[] orbbecRGBDSerial = getOrbbecRGBDSerial();
                ArrayList arrayList3 = new ArrayList(orbbecRGBDSerial.length);
                for (String str13 : orbbecRGBDSerial) {
                    this.curSerialMap.put(str13, RGBDType.Orbbec.getStr());
                    int i8 = this.numberOfOrbbec;
                    this.numberOfOrbbec = i8 + 1;
                    arrayList3.add(Integer.valueOf(i8));
                }
                ArrayList arrayList4 = arrayList3;
                String[] angstrongRGBDSerial = getAngstrongRGBDSerial();
                ArrayList arrayList5 = new ArrayList(angstrongRGBDSerial.length);
                for (String str14 : angstrongRGBDSerial) {
                    this.curSerialMap.put(str14, RGBDType.AngStrong.getStr());
                    int i9 = this.numberOfAngStrong;
                    this.numberOfAngStrong = i9 + 1;
                    arrayList5.add(Integer.valueOf(i9));
                }
                ArrayList arrayList6 = arrayList5;
                if (rGBDConfig.getReset() == null) {
                    Object[] objArr5 = new Object[i2];
                    objArr5[0] = "update config";
                    String str15 = str5;
                    Pdlog.m3273d(str15, objArr5);
                    rGBDConfig.setReset(bool);
                    RGBDJson right_rgbd = rGBDConfig.getRight_rgbd();
                    if (right_rgbd != null) {
                        right_rgbd.setType(RGBDType.RealSense.getStr());
                    }
                    RGBDJson right_rgbd2 = rGBDConfig.getRight_rgbd();
                    if (right_rgbd2 != null) {
                        right_rgbd2.setShield(bool);
                    }
                    RGBDJson left_rgbd = rGBDConfig.getLeft_rgbd();
                    if (left_rgbd != null) {
                        left_rgbd.setType(RGBDType.RealSense.getStr());
                    }
                    RGBDJson left_rgbd2 = rGBDConfig.getLeft_rgbd();
                    if (left_rgbd2 != null) {
                        left_rgbd2.setShield(bool);
                    }
                    RGBDJson center_rgbd2 = rGBDConfig.getCenter_rgbd();
                    if (center_rgbd2 != null) {
                        center_rgbd2.setType(RGBDType.RealSense.getStr());
                    }
                    RGBDJson center_rgbd3 = rGBDConfig.getCenter_rgbd();
                    if (center_rgbd3 != null) {
                        center_rgbd3.setShield(bool);
                    }
                    RGBDJson down_rgbd2 = rGBDConfig.getDown_rgbd();
                    if (down_rgbd2 != null) {
                        down_rgbd2.setType(RGBDType.RealSense.getStr());
                    }
                    RGBDJson down_rgbd3 = rGBDConfig.getDown_rgbd();
                    if (down_rgbd3 != null) {
                        down_rgbd3.setShield(bool);
                    }
                    int size = this.curSerialMap.size();
                    if (size == 2) {
                        bool4 = bool2;
                        if (rGBDConfig.getLeft_rgbd() == null && rGBDConfig.getRight_rgbd() == null) {
                            RGBDJson center_rgbd4 = rGBDConfig.getCenter_rgbd();
                            if (center_rgbd4 != null) {
                                center_rgbd4.setType(RGBDType.RealSense.getStr());
                            }
                            RGBDJson center_rgbd5 = rGBDConfig.getCenter_rgbd();
                            if (center_rgbd5 != null) {
                                center_rgbd5.setShield(bool);
                            }
                            if (rGBDConfig.getDown_rgbd() == null) {
                                rGBDConfig.setDown_rgbd(new RGBDJson(RGBDType.RealSense.getStr(), "waitforupdate", bool, null, null));
                            } else {
                                RGBDJson down_rgbd4 = rGBDConfig.getDown_rgbd();
                                if (down_rgbd4 != null) {
                                    down_rgbd4.setType(RGBDType.RealSense.getStr());
                                }
                                RGBDJson down_rgbd5 = rGBDConfig.getDown_rgbd();
                                if (down_rgbd5 != null) {
                                    down_rgbd5.setShield(bool);
                                }
                            }
                        } else if (rGBDConfig.getLeft_rgbd() != null && rGBDConfig.getRight_rgbd() != null && this.machineModelId == MachineModel.Peanut.getId()) {
                            Object[] objArr6 = new Object[i2];
                            objArr6[0] = "peanut";
                            Pdlog.m3273d(str15, objArr6);
                            bool3 = bool4;
                            ExtrinsicsJson extrinsicsJson3 = new ExtrinsicsJson(valueOf10, valueOf12, valueOf, valueOf12, Float.valueOf(5.48f), valueOf12);
                            ExtrinsicsJson extrinsicsJson4 = new ExtrinsicsJson(valueOf10, valueOf12, Float.valueOf(0.904f), valueOf12, Float.valueOf(0.925f), valueOf12);
                            rGBDConfig.setCenter_rgbd(rGBDConfig.getRight_rgbd());
                            rGBDConfig.setDown_rgbd(rGBDConfig.getLeft_rgbd());
                            rGBDConfig.setRight_rgbd((RGBDJson) null);
                            rGBDConfig.setLeft_rgbd((RGBDJson) null);
                            rGBDConfig.setReset(bool3);
                            DataJson dataJson6 = new DataJson(str3, num);
                            RGBDJson center_rgbd6 = rGBDConfig.getCenter_rgbd();
                            if (center_rgbd6 != null) {
                                center_rgbd6.setExtrinsics(extrinsicsJson3);
                            }
                            RGBDJson down_rgbd6 = rGBDConfig.getDown_rgbd();
                            if (down_rgbd6 != null) {
                                down_rgbd6.setExtrinsics(extrinsicsJson4);
                            }
                            rGBDConfig.setData(dataJson6);
                            FileOutputStream fileOutputStream3 = new FileOutputStream(new File(this.configFileName));
                            json2 = gson.toJson(rGBDConfig);
                            str6 = str4;
                            Intrinsics.checkExpressionValueIsNotNull(json2, str6);
                            Charset charset3 = Charsets.UTF_8;
                            if (json2 == null) {
                            }
                        }
                        bool3 = bool4;
                        FileOutputStream fileOutputStream32 = new FileOutputStream(new File(this.configFileName));
                        json2 = gson.toJson(rGBDConfig);
                        str6 = str4;
                        Intrinsics.checkExpressionValueIsNotNull(json2, str6);
                        Charset charset32 = Charsets.UTF_8;
                        if (json2 == null) {
                        }
                    } else if (size == 3) {
                        if (rGBDConfig.getDown_rgbd() == null && rGBDConfig.getRight_rgbd() != null && rGBDConfig.getLeft_rgbd() != null) {
                            Object[] objArr7 = new Object[i2];
                            objArr7[0] = "3 rgbd update config";
                            Pdlog.m3273d(str15, objArr7);
                            rGBDConfig.setCenter_rgbd(new RGBDJson(RGBDType.Orbbec.getStr(), "updateconfig", bool, null, null));
                            RGBDJson center_rgbd7 = rGBDConfig.getCenter_rgbd();
                            if (center_rgbd7 != null) {
                                center_rgbd7.setShield(Boolean.valueOf(this.rgbdVersion == MachineInfo.RGBDType.TwoDevice));
                            }
                            RGBDJson center_rgbd8 = rGBDConfig.getCenter_rgbd();
                            bool4 = bool2;
                            if (Intrinsics.areEqual(center_rgbd8 != null ? center_rgbd8.getShield() : null, bool4)) {
                                this.replaceList.add(Integer.valueOf(RGBDLocation.cenerRGBD.getId()));
                            }
                            bool3 = bool4;
                            FileOutputStream fileOutputStream322 = new FileOutputStream(new File(this.configFileName));
                            json2 = gson.toJson(rGBDConfig);
                            str6 = str4;
                            Intrinsics.checkExpressionValueIsNotNull(json2, str6);
                            Charset charset322 = Charsets.UTF_8;
                            if (json2 == null) {
                            }
                        }
                        bool4 = bool2;
                        this.rgbdError = "illegal rgbd config, please delete the old config.";
                        bool3 = bool4;
                        FileOutputStream fileOutputStream3222 = new FileOutputStream(new File(this.configFileName));
                        json2 = gson.toJson(rGBDConfig);
                        str6 = str4;
                        Intrinsics.checkExpressionValueIsNotNull(json2, str6);
                        Charset charset3222 = Charsets.UTF_8;
                        if (json2 == null) {
                        }
                    } else {
                        bool3 = bool2;
                        FileOutputStream fileOutputStream32222 = new FileOutputStream(new File(this.configFileName));
                        json2 = gson.toJson(rGBDConfig);
                        str6 = str4;
                        Intrinsics.checkExpressionValueIsNotNull(json2, str6);
                        Charset charset32222 = Charsets.UTF_8;
                        if (json2 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }
                        byte[] bytes2 = json2.getBytes(charset32222);
                        Intrinsics.checkExpressionValueIsNotNull(bytes2, "(this as java.lang.String).getBytes(charset)");
                        fileOutputStream32222.write(bytes2);
                        fileOutputStream32222.close();
                    }
                } else {
                    bool3 = bool2;
                    str6 = str4;
                }
                if (Intrinsics.areEqual(rGBDConfig.getReset(), bool3)) {
                    c = 0;
                    try {
                        this.ret = false;
                        this.rgbdError = "reset status, please reset the location.";
                    } catch (Exception e4) {
                        e = e4;
                        exc = e;
                        i3 = i2;
                        String str112 = this.TAG;
                        Object[] objArr42 = new Object[i3];
                        objArr42[c] = "read rgbd config file exception " + exc.getLocalizedMessage();
                        Pdlog.m3274e(str112, objArr42);
                        this.rgbdError = "read config file fail";
                        if (!this.ret) {
                        }
                        return this.ret;
                    }
                }
                setConfig(RGBDLocation.leftRGBD.getId(), rGBDConfig.getLeft_rgbd());
                setConfig(RGBDLocation.rightRGBD.getId(), rGBDConfig.getRight_rgbd());
                setConfig(RGBDLocation.cenerRGBD.getId(), rGBDConfig.getCenter_rgbd());
                setConfig(RGBDLocation.downRGBD.getId(), rGBDConfig.getDown_rgbd());
            } catch (Exception e5) {
                e = e5;
                c = 0;
            }
        } catch (Exception e6) {
            e = e6;
            c = 0;
            i2 = 1;
        }
        if (this.curSerialMap.size() != 0 || this.replaceList.size() != 0) {
            if (this.curSerialMap.size() > this.replaceList.size()) {
                this.ret = false;
                String str16 = "illegal rgbd config, devices " + this.curSerialMap.size() + ", replace number " + this.replaceList.size();
                this.rgbdError = str16;
                String str17 = this.TAG;
                Object[] objArr8 = new Object[i2];
                objArr8[0] = str16;
                Pdlog.m3274e(str17, objArr8);
            } else {
                HashMap<String, String> hashMap = this.curSerialMap;
                ArrayList arrayList7 = new ArrayList(hashMap.size());
                int i10 = 0;
                for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                    String value = entry.getValue();
                    if (Intrinsics.areEqual(value, RGBDType.RealSense.getStr())) {
                        RGBDJson rGBDJson10 = this.configSerialMap.get(this.replaceList.get(i10));
                        if (rGBDJson10 != null) {
                            rGBDJson10.setSerial(entry.getKey());
                        }
                        RGBDJson rGBDJson11 = this.configSerialMap.get(this.replaceList.get(i10));
                        if (rGBDJson11 != null) {
                            rGBDJson11.setType(entry.getValue());
                        }
                        RGBDJson rGBDJson12 = this.configSerialMap.get(this.replaceList.get(i10));
                        if (((Intrinsics.areEqual(rGBDJson12 != null ? rGBDJson12.getShield() : null, bool3) ? 1 : 0) ^ i2) != 0) {
                            Integer num2 = this.replaceList.get(i10);
                            Intrinsics.checkExpressionValueIsNotNull(num2, "replaceList[index]");
                            if (setRealSenseRGBD(num2.intValue(), entry.getKey(), 0)) {
                                this.realSenseWork = i2;
                            } else {
                                this.ret = false;
                                this.rgbdError = this.rgbdError + "set real sense rgbd fail";
                            }
                        }
                    } else if (Intrinsics.areEqual(value, RGBDType.Orbbec.getStr())) {
                        RGBDJson rGBDJson13 = this.configSerialMap.get(this.replaceList.get(i10));
                        if (rGBDJson13 != null) {
                            rGBDJson13.setSerial(entry.getKey());
                        }
                        RGBDJson rGBDJson14 = this.configSerialMap.get(this.replaceList.get(i10));
                        if (rGBDJson14 != null) {
                            rGBDJson14.setType(entry.getValue());
                        }
                        RGBDJson rGBDJson15 = this.configSerialMap.get(this.replaceList.get(i10));
                        if (((Intrinsics.areEqual(rGBDJson15 != null ? rGBDJson15.getShield() : null, bool3) ? 1 : 0) ^ i2) != 0) {
                            Integer num3 = this.replaceList.get(i10);
                            Intrinsics.checkExpressionValueIsNotNull(num3, "replaceList[index]");
                            if (setOrbbecRGBD(num3.intValue(), entry.getKey(), 0)) {
                                this.orbbecWork = i2;
                            } else {
                                this.ret = false;
                                this.rgbdError = this.rgbdError + "set orbbec rgbd fail";
                            }
                        }
                    } else if (Intrinsics.areEqual(value, RGBDType.AngStrong.getStr())) {
                        RGBDJson rGBDJson16 = this.configSerialMap.get(this.replaceList.get(i10));
                        if (rGBDJson16 != null) {
                            rGBDJson16.setSerial(entry.getKey());
                        }
                        RGBDJson rGBDJson17 = this.configSerialMap.get(this.replaceList.get(i10));
                        if (rGBDJson17 != null) {
                            rGBDJson17.setType(entry.getValue());
                        }
                        RGBDJson rGBDJson18 = this.configSerialMap.get(this.replaceList.get(i10));
                        if (((Intrinsics.areEqual(rGBDJson18 != null ? rGBDJson18.getShield() : null, bool3) ? 1 : 0) ^ i2) != 0) {
                            Integer num4 = this.replaceList.get(i10);
                            Intrinsics.checkExpressionValueIsNotNull(num4, "replaceList[index]");
                            if (setAngstrongRGBD(num4.intValue(), entry.getKey(), 0)) {
                                this.angstrongWork = i2;
                            } else {
                                this.ret = false;
                                this.rgbdError = this.rgbdError + "set angstrong rgbd fail";
                            }
                        }
                    } else {
                        this.ret = false;
                        String str18 = "unkonw rgbd type " + entry.getValue();
                        this.rgbdError = str18;
                        String str19 = this.TAG;
                        Object[] objArr9 = new Object[i2];
                        objArr9[0] = str18;
                        Pdlog.m3274e(str19, objArr9);
                    }
                    int i11 = i10 + 1;
                    arrayList7.add(Integer.valueOf(i10));
                    i10 = i11;
                }
                ArrayList arrayList8 = arrayList7;
                rGBDConfig.setReset(Boolean.valueOf(this.curSerialMap.size() != i2 || Intrinsics.areEqual(rGBDConfig.getReset(), bool3)));
                this.ret = this.curSerialMap.size() == i2;
                int i12 = this.machineModelId;
                if (i12 != MachineModel.Hls.getId() && i12 != MachineModel.Puductor.getId()) {
                    rGBDConfig2 = rGBDConfig;
                    if (i12 != MachineModel.BellaBot.getId()) {
                        if (i12 != MachineModel.RecycleDog.getId()) {
                        }
                    }
                    if (this.numberOfRealSense > i2) {
                        String str20 = this.TAG;
                        Object[] objArr10 = new Object[i2];
                        objArr10[0] = "set bella realsense extrinsics";
                        Pdlog.m3273d(str20, objArr10);
                        ExtrinsicsJson extrinsicsJson5 = new ExtrinsicsJson(valueOf8, valueOf3, valueOf7, valueOf6, valueOf11, valueOf12);
                        ExtrinsicsJson extrinsicsJson6 = new ExtrinsicsJson(valueOf8, valueOf2, valueOf7, valueOf11, valueOf11, valueOf9);
                        RGBDJson left_rgbd3 = rGBDConfig2.getLeft_rgbd();
                        if (left_rgbd3 != null) {
                            left_rgbd3.setExtrinsics(extrinsicsJson5);
                        }
                        RGBDJson right_rgbd3 = rGBDConfig2.getRight_rgbd();
                        if (right_rgbd3 != null) {
                            right_rgbd3.setExtrinsics(extrinsicsJson6);
                        }
                        z = false;
                        FileOutputStream fileOutputStream4 = new FileOutputStream(new File(this.configFileName));
                        json = gson.toJson(rGBDConfig2);
                        Intrinsics.checkExpressionValueIsNotNull(json, str6);
                        Charset charset4 = Charsets.UTF_8;
                        if (json != null) {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }
                        byte[] bytes3 = json.getBytes(charset4);
                        Intrinsics.checkExpressionValueIsNotNull(bytes3, "(this as java.lang.String).getBytes(charset)");
                        fileOutputStream4.write(bytes3);
                        fileOutputStream4.close();
                        r3 = z;
                        if (Intrinsics.areEqual(rGBDConfig2.getReset(), bool3)) {
                            String str21 = this.curSerialMap.size() == this.replaceList.size() ? "reset status, please reset the location." : "lack of rgbd";
                            this.rgbdError = str21;
                            String str22 = this.TAG;
                            Object[] objArr11 = new Object[i2];
                            objArr11[z ? 1 : 0] = str21;
                            Pdlog.m3274e(str22, objArr11);
                            this.ret = z;
                            r3 = z;
                        }
                        if (rGBDConfig2.getCenter_rgbd() != null) {
                            RGBDJson center_rgbd9 = rGBDConfig2.getCenter_rgbd();
                            String type = center_rgbd9 != null ? center_rgbd9.getType() : null;
                            if (Intrinsics.areEqual(type, RGBDType.RealSense.getStr())) {
                                this.centerRgbdType = i2;
                            } else if (Intrinsics.areEqual(type, RGBDType.Orbbec.getStr())) {
                                this.centerRgbdType = r3;
                            } else if (Intrinsics.areEqual(type, RGBDType.AngStrong.getStr())) {
                                this.centerRgbdType = 3;
                            }
                        }
                        if (this.angstrongWork) {
                            boolean startAngStrongRGBD = startAngStrongRGBD();
                            String str23 = this.rgbdError;
                            StringBuilder sb = new StringBuilder();
                            sb.append(str23);
                            sb.append(startAngStrongRGBD ? "" : ", can't start the angstrong rgbd");
                            this.rgbdError = sb.toString();
                            this.ret = this.ret && startAngStrongRGBD;
                        }
                        if (this.realSenseWork) {
                            boolean startRealSenseRGBD = startRealSenseRGBD();
                            String str24 = this.rgbdError;
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(str24);
                            sb2.append(startRealSenseRGBD ? "" : ", can't start the realSense rgbd");
                            this.rgbdError = sb2.toString();
                            this.ret = this.ret && startRealSenseRGBD;
                        }
                        if (this.orbbecWork) {
                            boolean startOrbbecRGBD = startOrbbecRGBD();
                            String str25 = this.rgbdError;
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append(str25);
                            sb3.append(startOrbbecRGBD ? "" : ", can't start the orbbec rgbd");
                            this.rgbdError = sb3.toString();
                            this.ret = this.ret && startOrbbecRGBD;
                        }
                        if (!this.ret) {
                            this.rgbdError = this.rgbdError + getLastError();
                        }
                        return this.ret;
                    }
                    z = false;
                    z = false;
                    z = false;
                    if (this.numberOfOrbbec <= i2 && this.numberOfAngStrong <= i2) {
                        String str26 = this.TAG;
                        Object[] objArr12 = new Object[i2];
                        objArr12[0] = "set bella nothing";
                        Pdlog.m3273d(str26, objArr12);
                        FileOutputStream fileOutputStream42 = new FileOutputStream(new File(this.configFileName));
                        json = gson.toJson(rGBDConfig2);
                        Intrinsics.checkExpressionValueIsNotNull(json, str6);
                        Charset charset42 = Charsets.UTF_8;
                        if (json != null) {
                        }
                    }
                    String str27 = this.TAG;
                    Object[] objArr13 = new Object[i2];
                    objArr13[0] = "set bella orbbec or angstrong extrinsics";
                    Pdlog.m3273d(str27, objArr13);
                    ExtrinsicsJson extrinsicsJson7 = new ExtrinsicsJson(Float.valueOf(0.181f), Float.valueOf(0.08f), Float.valueOf(0.13f), Float.valueOf(0.52f), valueOf11, valueOf12);
                    ExtrinsicsJson extrinsicsJson8 = new ExtrinsicsJson(Float.valueOf(0.181f), Float.valueOf(-0.08f), Float.valueOf(0.13f), Float.valueOf(-0.52f), valueOf11, valueOf9);
                    RGBDJson left_rgbd4 = rGBDConfig2.getLeft_rgbd();
                    if (left_rgbd4 != null) {
                        left_rgbd4.setExtrinsics(extrinsicsJson7);
                    }
                    RGBDJson right_rgbd4 = rGBDConfig2.getRight_rgbd();
                    if (right_rgbd4 != null) {
                        right_rgbd4.setExtrinsics(extrinsicsJson8);
                    }
                    FileOutputStream fileOutputStream422 = new FileOutputStream(new File(this.configFileName));
                    json = gson.toJson(rGBDConfig2);
                    Intrinsics.checkExpressionValueIsNotNull(json, str6);
                    Charset charset422 = Charsets.UTF_8;
                    if (json != null) {
                    }
                }
                if (this.numberOfRealSense > i2) {
                    String str28 = this.TAG;
                    Object[] objArr14 = new Object[i2];
                    objArr14[0] = "set hls realsense extrinsics";
                    Pdlog.m3273d(str28, objArr14);
                    rGBDConfig2 = rGBDConfig;
                    ExtrinsicsJson extrinsicsJson9 = new ExtrinsicsJson(valueOf5, Float.valueOf(0.08143f), valueOf4, valueOf6, valueOf11, valueOf12);
                    ExtrinsicsJson extrinsicsJson10 = new ExtrinsicsJson(valueOf5, Float.valueOf(-0.08143f), valueOf4, valueOf11, valueOf11, valueOf9);
                    RGBDJson left_rgbd5 = rGBDConfig2.getLeft_rgbd();
                    if (left_rgbd5 != null) {
                        left_rgbd5.setExtrinsics(extrinsicsJson9);
                    }
                    RGBDJson right_rgbd5 = rGBDConfig2.getRight_rgbd();
                    if (right_rgbd5 != null) {
                        right_rgbd5.setExtrinsics(extrinsicsJson10);
                    }
                } else {
                    rGBDConfig2 = rGBDConfig;
                    if (this.numberOfOrbbec <= i2 && this.numberOfAngStrong <= i2) {
                        String str29 = this.TAG;
                        Object[] objArr15 = new Object[i2];
                        objArr15[0] = "set hls nothing";
                        Pdlog.m3273d(str29, objArr15);
                    }
                    String str30 = this.TAG;
                    Object[] objArr16 = new Object[i2];
                    objArr16[0] = "set hls orbbec or angstrong extrinsics";
                    Pdlog.m3273d(str30, objArr16);
                    ExtrinsicsJson extrinsicsJson11 = new ExtrinsicsJson(Float.valueOf(0.181f), Float.valueOf(0.08f), valueOf10, Float.valueOf(0.52f), Float.valueOf(-0.698f), valueOf12);
                    ExtrinsicsJson extrinsicsJson12 = new ExtrinsicsJson(Float.valueOf(0.181f), Float.valueOf(-0.08f), valueOf10, Float.valueOf(-0.52f), Float.valueOf(-0.698f), valueOf9);
                    RGBDJson left_rgbd6 = rGBDConfig2.getLeft_rgbd();
                    if (left_rgbd6 != null) {
                        left_rgbd6.setExtrinsics(extrinsicsJson11);
                    }
                    RGBDJson right_rgbd6 = rGBDConfig2.getRight_rgbd();
                    if (right_rgbd6 != null) {
                        right_rgbd6.setExtrinsics(extrinsicsJson12);
                    }
                }
                z = false;
                FileOutputStream fileOutputStream4222 = new FileOutputStream(new File(this.configFileName));
                json = gson.toJson(rGBDConfig2);
                Intrinsics.checkExpressionValueIsNotNull(json, str6);
                Charset charset4222 = Charsets.UTF_8;
                if (json != null) {
                }
            }
        }
        rGBDConfig2 = rGBDConfig;
        r3 = 0;
        if (rGBDConfig2.getCenter_rgbd() != null) {
        }
        if (this.angstrongWork) {
        }
        if (this.realSenseWork) {
        }
        if (this.orbbecWork) {
        }
        if (!this.ret) {
        }
        return this.ret;
    }

    public final void RefreshConfigure() {
        try {
            RGBDConfig rGBDConfig = (RGBDConfig) new Gson().fromJson((Reader) new FileReader(this.configFileName), RGBDConfig.class);
            refreshRGBDJson(RGBDLocation.leftRGBD.getId(), rGBDConfig.getLeft_rgbd());
            refreshRGBDJson(RGBDLocation.rightRGBD.getId(), rGBDConfig.getRight_rgbd());
            refreshRGBDJson(RGBDLocation.cenerRGBD.getId(), rGBDConfig.getCenter_rgbd());
            refreshRGBDJson(RGBDLocation.downRGBD.getId(), rGBDConfig.getDown_rgbd());
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, "read rgbd config file exception " + e.getLocalizedMessage());
            this.rgbdError = "read config file fail";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean openRGBD() {
        boolean openRealSenseRGBD = this.realSenseWork ? openRealSenseRGBD() : true;
        if (this.orbbecWork) {
            openRealSenseRGBD = openOrbbecRGBD() && openRealSenseRGBD;
        }
        if (this.angstrongWork) {
            return openAngStrongRGBD() && openRealSenseRGBD;
        }
        return openRealSenseRGBD;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void closeRGBD() {
        if (this.realSenseWork) {
            closeRealSenseRGBD();
        }
        if (this.orbbecWork) {
            closeOrbbecRGBD();
        }
        if (this.angstrongWork) {
            closeAngStrongRGBD();
        }
    }

    public final String getLastError() {
        String str;
        if (WhenMappings.$EnumSwitchMapping$0[this.rgbdVersion.ordinal()] == 1) {
            return "no rgbd device";
        }
        if (this.realSenseWork) {
            str = "" + getRealSenseLastError() + "";
        } else {
            str = "";
        }
        if (this.orbbecWork) {
            str = str + getOrbbecLastError() + "";
        }
        if (!this.angstrongWork) {
            return str;
        }
        return str + getAngStrongLastError() + "";
    }

    public final boolean checkDownRgbdConfigFile() {
        if (new File(this.downRgbdConfigFileName).exists()) {
            return true;
        }
        this.rgbdError = "down rgbd config not exist";
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x009e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean checkConfigFile() {
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream;
        Throwable th;
        Exception e;
        if (!new File(this.configFileName).exists()) {
            File file = new File(ConfigUtil.CONFIG_DIR);
            if (!file.exists()) {
                file.mkdirs();
            }
            if (!new File("/sdcard/PuduRobotMap/rgbd.json").exists()) {
                this.rgbdError = "rgbd config not exist";
                return false;
            }
            OutputStream outputStream = (OutputStream) null;
            InputStream inputStream = (InputStream) null;
            try {
                fileOutputStream = new FileOutputStream(this.configFileName);
            } catch (Exception e2) {
                e = e2;
                fileOutputStream = outputStream;
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = outputStream;
            }
            try {
                fileInputStream = new FileInputStream("/sdcard/PuduRobotMap/rgbd.json");
            } catch (Exception e3) {
                e = e3;
                fileInputStream = inputStream;
                e = e;
                String str = "copy rgbd config file err: " + e.getMessage();
                this.rgbdError = str;
                Pdlog.m3274e(this.TAG, str);
                if (fileOutputStream != null) {
                }
                if (fileInputStream != null) {
                }
                return false;
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = inputStream;
                th = th;
                if (fileOutputStream != null) {
                }
                if (fileInputStream != null) {
                }
                throw th;
            }
            try {
                try {
                    byte[] bArr = new byte[256];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    fileOutputStream.close();
                    fileInputStream.close();
                } catch (Throwable th4) {
                    th = th4;
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    throw th;
                }
            } catch (Exception e4) {
                e = e4;
                String str2 = "copy rgbd config file err: " + e.getMessage();
                this.rgbdError = str2;
                Pdlog.m3274e(this.TAG, str2);
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return false;
            }
        }
        return true;
    }

    private final byte[] readFwBin() {
        InputStream inputStream = (InputStream) null;
        try {
            try {
                inputStream = this.appContext.getAssets().open(this.fwFileName);
                if (inputStream == null) {
                    return null;
                }
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read != -1) {
                        byteArrayOutputStream.write(bArr, 0, read);
                    } else {
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        byteArrayOutputStream.close();
                        inputStream.close();
                        return byteArray;
                    }
                }
            } catch (Exception e) {
                Pdlog.m3277w(this.TAG, "read rgbd fw failed: " + e.getMessage());
                if (inputStream != null) {
                    inputStream.close();
                }
                return null;
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    private final boolean isNewSystemVersion() {
        String property;
        try {
            String property2 = getProperty("ro.build.id");
            String property3 = getProperty("ro.pudutech.version_name");
            property = getProperty("ro.pudutech.version_code");
            Pdlog.m3273d(this.TAG, "system build id : ", property2, " version name ", property3, " version code ", property);
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, e, " stack:", e.getMessage());
        }
        if (property.length() == 0) {
            return false;
        }
        return Integer.parseInt(property) >= 6;
    }

    private final String getProperty(String key) {
        try {
            Class<?> loadClass = this.appContext.getClassLoader().loadClass("android.os.SystemProperties");
            Object invoke = loadClass.getMethod(TmpConstant.PROPERTY_IDENTIFIER_GET, String.class).invoke(loadClass, key);
            if (invoke != null) {
                return (String) invoke;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, e, " stack:", e.getMessage());
            return "";
        }
    }

    private final void setProperty(String key, String value) {
        try {
            Class<?> loadClass = this.appContext.getClassLoader().loadClass("android.os.SystemProperties");
            loadClass.getMethod(TmpConstant.PROPERTY_IDENTIFIER_SET, String.class, String.class).invoke(loadClass, key, value);
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, e, " stack:", e.getMessage());
        }
    }

    private final void destributeLeftRGBD(FileDescriptor fileDescriptor, final int rows, final int cols, final int memorySize) {
        final ParcelFileDescriptor dup = ParcelFileDescriptor.dup(fileDescriptor);
        this.leftRGBDListeners.notify(new Function2<RGBDDataCatcher, String, Unit>() { // from class: com.pudutech.rgbdlib.RGBDSensor$destributeLeftRGBD$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(RGBDDataCatcher rGBDDataCatcher, String str) {
                invoke2(rGBDDataCatcher, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(RGBDDataCatcher it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                ParcelFileDescriptor parcelFileDescriptor = dup;
                Intrinsics.checkExpressionValueIsNotNull(parcelFileDescriptor, "parcelFileDescriptor");
                it.onFrameDescriptor(parcelFileDescriptor, rows, cols, memorySize);
            }
        });
        if (dup != null) {
            dup.close();
        }
    }

    private final void destributeRightRGBD(FileDescriptor fileDescriptor, final int rows, final int cols, final int memorySize) {
        final ParcelFileDescriptor dup = ParcelFileDescriptor.dup(fileDescriptor);
        this.rightRGBDListeners.notify(new Function2<RGBDDataCatcher, String, Unit>() { // from class: com.pudutech.rgbdlib.RGBDSensor$destributeRightRGBD$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(RGBDDataCatcher rGBDDataCatcher, String str) {
                invoke2(rGBDDataCatcher, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(RGBDDataCatcher it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                ParcelFileDescriptor parcelFileDescriptor = dup;
                Intrinsics.checkExpressionValueIsNotNull(parcelFileDescriptor, "parcelFileDescriptor");
                it.onFrameDescriptor(parcelFileDescriptor, rows, cols, memorySize);
            }
        });
        if (dup != null) {
            dup.close();
        }
    }

    private final void destributeCenterRGBD(FileDescriptor fileDescriptor, final int rows, final int cols, final int memorySize) {
        final ParcelFileDescriptor dup = ParcelFileDescriptor.dup(fileDescriptor);
        this.centernRGBDListeners.notify(new Function2<RGBDDataCatcher, String, Unit>() { // from class: com.pudutech.rgbdlib.RGBDSensor$destributeCenterRGBD$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(RGBDDataCatcher rGBDDataCatcher, String str) {
                invoke2(rGBDDataCatcher, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(RGBDDataCatcher it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                ParcelFileDescriptor parcelFileDescriptor = dup;
                Intrinsics.checkExpressionValueIsNotNull(parcelFileDescriptor, "parcelFileDescriptor");
                it.onFrameDescriptor(parcelFileDescriptor, rows, cols, memorySize);
            }
        });
        if (dup != null) {
            dup.close();
        }
    }

    private final void destributeDownRGBD(FileDescriptor fileDescriptor, final int rows, final int cols, final int memorySize) {
        final ParcelFileDescriptor dup = ParcelFileDescriptor.dup(fileDescriptor);
        this.downRGBDListener.notify(new Function2<RGBDDataCatcher, String, Unit>() { // from class: com.pudutech.rgbdlib.RGBDSensor$destributeDownRGBD$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(RGBDDataCatcher rGBDDataCatcher, String str) {
                invoke2(rGBDDataCatcher, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(RGBDDataCatcher it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                ParcelFileDescriptor parcelFileDescriptor = dup;
                Intrinsics.checkExpressionValueIsNotNull(parcelFileDescriptor, "parcelFileDescriptor");
                it.onFrameDescriptor(parcelFileDescriptor, rows, cols, memorySize);
            }
        });
        if (dup != null) {
            dup.close();
        }
    }

    private final void destributeCheckRGBD(FileDescriptor fileDescriptor, final int rows, final int cols, final int memorySize) {
        final ParcelFileDescriptor dup = ParcelFileDescriptor.dup(fileDescriptor);
        Pdlog.m3273d(this.TAG, "get check tool data");
        this.checkRGBDListener.notify(new Function2<RGBDDataCatcher, String, Unit>() { // from class: com.pudutech.rgbdlib.RGBDSensor$destributeCheckRGBD$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(RGBDDataCatcher rGBDDataCatcher, String str) {
                invoke2(rGBDDataCatcher, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(RGBDDataCatcher it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                ParcelFileDescriptor parcelFileDescriptor = dup;
                Intrinsics.checkExpressionValueIsNotNull(parcelFileDescriptor, "parcelFileDescriptor");
                it.onFrameDescriptor(parcelFileDescriptor, rows, cols, memorySize);
            }
        });
        if (dup != null) {
            dup.close();
        }
    }

    public final Result start(MachineInfo.RGBDType RgbdVersion, int machineModelId, final Function4<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, Unit> leftCallback, final Function4<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, Unit> rightCallback, final Function4<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, Unit> centerCallback, final Function4<? super ParcelFileDescriptor, ? super Integer, ? super Integer, ? super Integer, Unit> downCallback) {
        boolean openRGBD;
        Intrinsics.checkParameterIsNotNull(RgbdVersion, "RgbdVersion");
        Intrinsics.checkParameterIsNotNull(leftCallback, "leftCallback");
        Intrinsics.checkParameterIsNotNull(rightCallback, "rightCallback");
        Intrinsics.checkParameterIsNotNull(centerCallback, "centerCallback");
        Intrinsics.checkParameterIsNotNull(downCallback, "downCallback");
        this.rgbdVersion = RgbdVersion;
        Pdlog.m3273d("rgbd", "start rgbd");
        if (this.finishInit) {
            return new Result(false, "rgbd is running or releasing");
        }
        if (this.firstRunRGBD) {
            this.leftRGBDListeners.add("leftCallback", new RGBDDataCatcher() { // from class: com.pudutech.rgbdlib.RGBDSensor$start$startResult$1
                @Override // com.pudutech.rgbdlib.RGBDDataCatcher
                public void onFrameDescriptor(ParcelFileDescriptor parcelFileDescriptor, int rows, int cols, int memorySize) {
                    Intrinsics.checkParameterIsNotNull(parcelFileDescriptor, "parcelFileDescriptor");
                    Function4.this.invoke(parcelFileDescriptor, Integer.valueOf(rows), Integer.valueOf(cols), Integer.valueOf(memorySize));
                }
            });
            this.rightRGBDListeners.add("rightCallback", new RGBDDataCatcher() { // from class: com.pudutech.rgbdlib.RGBDSensor$start$startResult$2
                @Override // com.pudutech.rgbdlib.RGBDDataCatcher
                public void onFrameDescriptor(ParcelFileDescriptor parcelFileDescriptor, int rows, int cols, int memorySize) {
                    Intrinsics.checkParameterIsNotNull(parcelFileDescriptor, "parcelFileDescriptor");
                    Function4.this.invoke(parcelFileDescriptor, Integer.valueOf(rows), Integer.valueOf(cols), Integer.valueOf(memorySize));
                }
            });
            this.centernRGBDListeners.add("centerCallback", new RGBDDataCatcher() { // from class: com.pudutech.rgbdlib.RGBDSensor$start$startResult$3
                @Override // com.pudutech.rgbdlib.RGBDDataCatcher
                public void onFrameDescriptor(ParcelFileDescriptor parcelFileDescriptor, int rows, int cols, int memorySize) {
                    Intrinsics.checkParameterIsNotNull(parcelFileDescriptor, "parcelFileDescriptor");
                    Function4.this.invoke(parcelFileDescriptor, Integer.valueOf(rows), Integer.valueOf(cols), Integer.valueOf(memorySize));
                }
            });
            this.downRGBDListener.add("downCallback", new RGBDDataCatcher() { // from class: com.pudutech.rgbdlib.RGBDSensor$start$startResult$4
                @Override // com.pudutech.rgbdlib.RGBDDataCatcher
                public void onFrameDescriptor(ParcelFileDescriptor parcelFileDescriptor, int rows, int cols, int memorySize) {
                    Intrinsics.checkParameterIsNotNull(parcelFileDescriptor, "parcelFileDescriptor");
                    Function4.this.invoke(parcelFileDescriptor, Integer.valueOf(rows), Integer.valueOf(cols), Integer.valueOf(memorySize));
                }
            });
            this.firstRunRGBD = false;
            openRGBD = startRGBD(machineModelId);
        } else {
            openRGBD = openRGBD();
        }
        if (!openRGBD) {
            Pdlog.m3273d("rgbd", this.rgbdError);
            return new Result(false, this.rgbdError);
        }
        this.finishInit = true;
        return new Result(true, "");
    }

    public final void releaseRGBD() {
        Pdlog.m3273d(this.TAG, "releaseRGBD");
        BuildersKt__BuildersKt.runBlocking$default(null, new RGBDSensor$releaseRGBD$1(this, null), 1, null);
        Pdlog.m3273d(this.TAG, "finish release RGBD device");
    }

    public final boolean checkUpdateFirmware() {
        if (isNewSystemVersion()) {
            return checkUpdateFw(this.fwVersion);
        }
        return false;
    }

    public final void exportAngstrongConfig() {
        try {
            InputStream open = this.appContext.getAssets().open(this.angstrongRgbdConfigFileName);
            File file = new File(MapFilePathConfig.CONFIG_PATH + '/' + this.angstrongRgbdConfigFileName);
            if (file.exists()) {
                return;
            }
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = open.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.flush();
                    open.close();
                    fileOutputStream.close();
                    Pdlog.m3273d(this.TAG, "create " + this.angstrongRgbdConfigFileName + " successfully in ", MapFilePathConfig.CONFIG_PATH);
                    return;
                }
            }
        } catch (FileNotFoundException unused) {
            Pdlog.m3275i(this.TAG, this.angstrongRgbdConfigFileName + " can not be overwrite, maybe busy");
        } catch (IOException e) {
            Pdlog.m3274e(this.TAG, "", e);
            e.printStackTrace();
        }
    }
}
