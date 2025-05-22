package com.pudutech.mirsdk.hardware;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkRequest;
import android.os.SystemClock;
import android.util.Log;
import android.util.Pair;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.lidar.LidarAdapterCallback;
import com.pudutech.lidar.LidarHelper;
import com.pudutech.lidar.LidarNode;
import com.pudutech.lidar.LidarVersion;
import com.pudutech.lidar.SecondLidarHelper;
import com.pudutech.lidar.base.BaseLidarAdapter;
import com.pudutech.lidar.base.CalibrationHelper;
import com.pudutech.lidar.base.LidarError;
import com.pudutech.mirsdk.hardware.LidarInterface;
import com.pudutech.mirsdk.hardware.network.NetworkCallbackImpl;
import com.pudutech.mirsdk.hardware.network.RootCmd;
import com.pudutech.mirsdk.hardware.serialize.LidarStopReason;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.hardware.serialize.PolarCoordinates;
import com.pudutech.mirsdk.hardware.serialize.ProductMachineType;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KFunction;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: LidarInterfaceImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001c\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u00062\b\u0010(\u001a\u0004\u0018\u00010\u000bH\u0016J\u001c\u0010)\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u00062\b\u0010(\u001a\u0004\u0018\u00010\u000bH\u0016J\u001c\u0010*\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u00062\b\u0010(\u001a\u0004\u0018\u00010#H\u0016J\u0006\u0010+\u001a\u00020\u0006J%\u0010,\u001a\u00020&2\u0006\u0010-\u001a\u00020.2\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020&00ø\u0001\u0000J\u0006\u00102\u001a\u00020&J\u001b\u00103\u001a\u00020&2\f\u00104\u001a\b\u0012\u0004\u0012\u00020605H\u0002¢\u0006\u0002\u00107J\u0018\u00108\u001a\u00020&2\u0006\u00109\u001a\u00020\b2\u0006\u0010:\u001a\u00020\u0006H\u0002J\u0010\u0010;\u001a\u00020&2\u0006\u0010<\u001a\u00020=H\u0002J\u001b\u0010>\u001a\u00020&2\f\u00104\u001a\b\u0012\u0004\u0012\u00020605H\u0002¢\u0006\u0002\u00107J\b\u0010?\u001a\u00020&H\u0016J\u0010\u0010@\u001a\u00020&2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0012\u0010A\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010B\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010C\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u0010D\u001a\u00020&H\u0016J\b\u0010E\u001a\u00020&H\u0016J\b\u0010F\u001a\u00020&H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u000e\u0010\u001f\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006G"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/LidarInterfaceImpl;", "Lcom/pudutech/mirsdk/hardware/LidarInterface$Stub;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "dataArrived", "", "dataListeners", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/mirsdk/hardware/ILidarData;", "dataTimestamps", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "lastFrameTimestamps", "lidar", "Lcom/pudutech/lidar/base/BaseLidarAdapter;", "mLastErrorMsg", "machineType", "Lcom/pudutech/mirsdk/hardware/serialize/ProductMachineType;", "getMachineType", "()Lcom/pudutech/mirsdk/hardware/serialize/ProductMachineType;", "setMachineType", "(Lcom/pudutech/mirsdk/hardware/serialize/ProductMachineType;)V", "needStartSecondLidar", "getNeedStartSecondLidar", "()Z", "setNeedStartSecondLidar", "(Z)V", "productType", "secondLidar", "secondLidarDataListeners", "stateListeners", "Lcom/pudutech/mirsdk/hardware/ILidarState;", "wasOpened", "addDataListener", "", "name", "listener", "addSecondLidarDataListener", "addStateListener", "getLastError", "init", "lidarVersionCode", "", "sendCAN", "Lkotlin/reflect/KFunction1;", "Lkotlin/UByteArray;", "initSecondLidar", "onLidarData", "data", "", "Lcom/pudutech/mirsdk/hardware/serialize/PolarCoordinates;", "([Lcom/pudutech/mirsdk/hardware/serialize/PolarCoordinates;)V", "onLidarStart", "success", "description", "onLidarStop", "reason", "Lcom/pudutech/mirsdk/hardware/serialize/LidarStopReason;", "onSecondLidarData", "open", "registerNetworkCallback", "removeDataListener", "removeSecondLidarDataListener", "removeStateListener", "startPreviewActivity", "startSecondLidarPreviewActivity", "stop", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class LidarInterfaceImpl extends LidarInterface.Stub {
    private final String TAG;
    private final Context context;
    private boolean dataArrived;
    private final ThreadSafeListener<ILidarData> dataListeners;
    private ArrayList<Long> dataTimestamps;
    private long lastFrameTimestamps;
    private BaseLidarAdapter lidar;
    private String mLastErrorMsg;
    private ProductMachineType machineType;
    private boolean needStartSecondLidar;
    private ProductMachineType productType;
    private BaseLidarAdapter secondLidar;
    private final ThreadSafeListener<ILidarData> secondLidarDataListeners;
    private final ThreadSafeListener<ILidarState> stateListeners;
    private boolean wasOpened;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LidarVersion.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[LidarVersion.LTME_02A.ordinal()] = 1;
        }
    }

    public LidarInterfaceImpl(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.TAG = "LidarInterfaceImpl";
        this.stateListeners = new ThreadSafeListener<>();
        this.dataListeners = new ThreadSafeListener<>();
        this.secondLidarDataListeners = new ThreadSafeListener<>();
        this.dataTimestamps = CollectionsKt.arrayListOf(0L, 0L, 0L, 0L, 0L);
        ProductMachineType productMachineType = new ProductMachineType(MachineModel.Hls, 0, 0);
        this.productType = productMachineType;
        this.machineType = productMachineType;
        this.mLastErrorMsg = "";
    }

    public static final /* synthetic */ BaseLidarAdapter access$getLidar$p(LidarInterfaceImpl lidarInterfaceImpl) {
        BaseLidarAdapter baseLidarAdapter = lidarInterfaceImpl.lidar;
        if (baseLidarAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lidar");
        }
        return baseLidarAdapter;
    }

    public static final /* synthetic */ BaseLidarAdapter access$getSecondLidar$p(LidarInterfaceImpl lidarInterfaceImpl) {
        BaseLidarAdapter baseLidarAdapter = lidarInterfaceImpl.secondLidar;
        if (baseLidarAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("secondLidar");
        }
        return baseLidarAdapter;
    }

    public final ProductMachineType getMachineType() {
        return this.machineType;
    }

    public final void setMachineType(ProductMachineType productMachineType) {
        Intrinsics.checkParameterIsNotNull(productMachineType, "<set-?>");
        this.machineType = productMachineType;
    }

    public final boolean getNeedStartSecondLidar() {
        return this.needStartSecondLidar;
    }

    public final void setNeedStartSecondLidar(boolean z) {
        this.needStartSecondLidar = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:113:0x0271  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0288  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x028d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void init(int lidarVersionCode, final KFunction<Unit> sendCAN) {
        BaseLidarAdapter baseLidarAdapter;
        LidarVersion version;
        BaseLidarAdapter baseLidarAdapter2;
        Intrinsics.checkParameterIsNotNull(sendCAN, "sendCAN");
        LidarVersion lidarVersion = LidarVersion.valueOf(lidarVersionCode);
        this.needStartSecondLidar = this.machineType.getModel() == MachineModel.Phoenix;
        Pdlog.m3275i(this.TAG, "lidar type:" + lidarVersion + " machineType.model=" + this.machineType.getModel().getId());
        if (this.lidar != null) {
            Pdlog.m3273d(this.TAG, "lidar has init do not need to create again");
            return;
        }
        LidarAdapterCallback lidarAdapterCallback = new LidarAdapterCallback() { // from class: com.pudutech.mirsdk.hardware.LidarInterfaceImpl$init$callback$1
            @Override // com.pudutech.lidar.LidarAdapterCallback
            public void onPowerRequest(boolean isOn) {
                String str;
                str = LidarInterfaceImpl.this.TAG;
                Pdlog.m3275i(str, "set lidar power " + isOn);
                byte[] bArr = new byte[8];
                for (int i = 0; i < 8; i++) {
                    bArr[i] = 0;
                }
                byte[] m4572constructorimpl = UByteArray.m4572constructorimpl(bArr);
                UByteArray.m4582setVurrAj0(m4572constructorimpl, 0, (byte) 119);
                UByteArray.m4582setVurrAj0(m4572constructorimpl, 1, UByte.m4528constructorimpl(isOn ? (byte) 1 : (byte) 0));
                ((Function1) sendCAN).invoke(UByteArray.m4570boximpl(m4572constructorimpl));
            }

            @Override // com.pudutech.lidar.LidarAdapterCallback
            public void onOneFrame(List<? extends LidarNode> data) {
                boolean z;
                String str;
                ArrayList arrayList;
                ArrayList arrayList2;
                ArrayList arrayList3;
                ArrayList arrayList4;
                String str2;
                ArrayList arrayList5;
                boolean z2;
                boolean z3;
                ArrayList arrayList6;
                ArrayList arrayList7;
                Intrinsics.checkParameterIsNotNull(data, "data");
                LidarInterfaceImpl.this.lastFrameTimestamps = SystemClock.elapsedRealtime();
                z = LidarInterfaceImpl.this.dataArrived;
                if (!z) {
                    arrayList = LidarInterfaceImpl.this.dataTimestamps;
                    arrayList.remove(0);
                    arrayList2 = LidarInterfaceImpl.this.dataTimestamps;
                    arrayList2.add(Long.valueOf(SystemClock.elapsedRealtime()));
                    arrayList3 = LidarInterfaceImpl.this.dataTimestamps;
                    long longValue = ((Number) arrayList3.get(4)).longValue();
                    arrayList4 = LidarInterfaceImpl.this.dataTimestamps;
                    Object obj = arrayList4.get(0);
                    Intrinsics.checkExpressionValueIsNotNull(obj, "dataTimestamps[0]");
                    if (longValue - ((Number) obj).longValue() < 1000) {
                        LidarInterfaceImpl.this.dataArrived = true;
                    }
                    str2 = LidarInterfaceImpl.this.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("lidar on frame,list: ");
                    arrayList5 = LidarInterfaceImpl.this.dataTimestamps;
                    sb.append(arrayList5.toString());
                    sb.append(" ,dataArrived = ");
                    z2 = LidarInterfaceImpl.this.dataArrived;
                    sb.append(z2);
                    Pdlog.m3276v(str2, sb.toString());
                    z3 = LidarInterfaceImpl.this.dataArrived;
                    if (!z3) {
                        LidarInterfaceImpl lidarInterfaceImpl = LidarInterfaceImpl.this;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("lidar frame time ");
                        arrayList6 = LidarInterfaceImpl.this.dataTimestamps;
                        long longValue2 = ((Number) arrayList6.get(4)).longValue();
                        arrayList7 = LidarInterfaceImpl.this.dataTimestamps;
                        Object obj2 = arrayList7.get(0);
                        Intrinsics.checkExpressionValueIsNotNull(obj2, "dataTimestamps[0]");
                        sb2.append(longValue2 - ((Number) obj2).longValue());
                        sb2.append(" ms");
                        lidarInterfaceImpl.mLastErrorMsg = sb2.toString();
                        return;
                    }
                }
                int size = data.size();
                PolarCoordinates[] polarCoordinatesArr = new PolarCoordinates[size];
                for (int i = 0; i < size; i++) {
                    PolarCoordinates polarCoordinates = new PolarCoordinates();
                    polarCoordinates.setAngle_rad(data.get(i).angleInRad);
                    polarCoordinates.setDistance_m(data.get(i).distance_m);
                    polarCoordinatesArr[i] = polarCoordinates;
                }
                str = LidarInterfaceImpl.this.TAG;
                Pdlog.m3273d(str, "lidar send frame size:" + data.size() + " to listener");
                LidarInterfaceImpl.this.onLidarData(polarCoordinatesArr);
                LidarInterfaceImpl.this.mLastErrorMsg = "";
            }

            @Override // com.pudutech.lidar.LidarAdapterCallback
            public void onError(final LidarError errorMsg) {
                String str;
                ThreadSafeListener threadSafeListener;
                Intrinsics.checkParameterIsNotNull(errorMsg, "errorMsg");
                str = LidarInterfaceImpl.this.TAG;
                Pdlog.m3274e(str, String.valueOf(errorMsg));
                LidarInterfaceImpl.this.mLastErrorMsg = errorMsg.toString();
                threadSafeListener = LidarInterfaceImpl.this.stateListeners;
                threadSafeListener.notify(new Function2<ILidarState, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.LidarInterfaceImpl$init$callback$1$onError$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(ILidarState iLidarState, String str2) {
                        invoke2(iLidarState, str2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(ILidarState l, String str2) {
                        Intrinsics.checkParameterIsNotNull(l, "l");
                        Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                        l.onError(LidarError.this.toString());
                    }
                });
            }
        };
        LidarHelper lidarHelper = LidarHelper.INSTANCE;
        Context context = this.context;
        Intrinsics.checkExpressionValueIsNotNull(lidarVersion, "lidarVersion");
        BaseLidarAdapter createLidar = lidarHelper.createLidar(context, lidarVersion, lidarAdapterCallback);
        if (createLidar == null) {
            Intrinsics.throwNpe();
        }
        this.lidar = createLidar;
        if (createLidar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lidar");
        }
        if (createLidar.getVersion() == LidarVersion.EAI_TG30_SLAMWARE) {
            registerNetworkCallback(this.context);
        }
        BaseLidarAdapter baseLidarAdapter3 = this.lidar;
        if (baseLidarAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lidar");
        }
        if (baseLidarAdapter3 != null) {
            baseLidarAdapter3.startLidarService();
        }
        BaseLidarAdapter baseLidarAdapter4 = this.lidar;
        if (baseLidarAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lidar");
        }
        boolean z = WhenMappings.$EnumSwitchMapping$0[(baseLidarAdapter4 != null ? baseLidarAdapter4.getVersion() : null).ordinal()] != 1;
        if (this.machineType.getModel() == MachineModel.Hls || this.machineType.getModel() == MachineModel.Puductor || this.machineType.getModel() == MachineModel.Phoenix) {
            Double angleCalibration = CalibrationHelper.INSTANCE.getAngleCalibration();
            double doubleValue = angleCalibration != null ? angleCalibration.doubleValue() : 0.0d;
            BaseLidarAdapter baseLidarAdapter5 = this.lidar;
            if (baseLidarAdapter5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("lidar");
            }
            if (baseLidarAdapter5 != null) {
                baseLidarAdapter5.configInstall(z, false, doubleValue);
            }
            BaseLidarAdapter baseLidarAdapter6 = this.lidar;
            if (baseLidarAdapter6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("lidar");
            }
            double d = (baseLidarAdapter6 != null ? baseLidarAdapter6.getVersion() : null) == LidarVersion.LDS_50C ? 0.0d : 3.141592653589793d;
            BaseLidarAdapter baseLidarAdapter7 = this.lidar;
            if (baseLidarAdapter7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("lidar");
            }
            if (baseLidarAdapter7 != null) {
                baseLidarAdapter7.configOutput(d, Math.toRadians(-80.0d), Math.toRadians(80.0d));
            }
        }
        if (this.machineType.getModel() == MachineModel.Firefox) {
            Double angleCalibration2 = CalibrationHelper.INSTANCE.getAngleCalibration();
            double doubleValue2 = angleCalibration2 != null ? angleCalibration2.doubleValue() : 0.0d;
            BaseLidarAdapter baseLidarAdapter8 = this.lidar;
            if (baseLidarAdapter8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("lidar");
            }
            if (baseLidarAdapter8 != null) {
                baseLidarAdapter8.configInstall(z, false, doubleValue2);
            }
            BaseLidarAdapter baseLidarAdapter9 = this.lidar;
            if (baseLidarAdapter9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("lidar");
            }
            if (baseLidarAdapter9 != null) {
                baseLidarAdapter9.configOutput(3.141592653589793d, Math.toRadians(-120.0d), Math.toRadians(120.0d));
            }
        }
        if (this.machineType.getModel() == MachineModel.BellaBot || this.machineType.getModel() == MachineModel.RecycleDog || this.machineType.getModel() == MachineModel.Peanut || this.machineType.getModel() == MachineModel.Ninetales) {
            Double angleCalibration3 = CalibrationHelper.INSTANCE.getAngleCalibration();
            double doubleValue3 = angleCalibration3 != null ? angleCalibration3.doubleValue() : 0.0d;
            BaseLidarAdapter baseLidarAdapter10 = this.lidar;
            if (baseLidarAdapter10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("lidar");
            }
            if (baseLidarAdapter10 != null) {
                baseLidarAdapter10.configInstall(z, true, doubleValue3);
            }
            BaseLidarAdapter baseLidarAdapter11 = this.lidar;
            if (baseLidarAdapter11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("lidar");
            }
            if ((baseLidarAdapter11 != null ? baseLidarAdapter11.getVersion() : null) != LidarVersion.EAI_TG30) {
                BaseLidarAdapter baseLidarAdapter12 = this.lidar;
                if (baseLidarAdapter12 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("lidar");
                }
                if ((baseLidarAdapter12 != null ? baseLidarAdapter12.getVersion() : null) != LidarVersion.EAI_TG30_UART) {
                    BaseLidarAdapter baseLidarAdapter13 = this.lidar;
                    if (baseLidarAdapter13 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("lidar");
                    }
                    if ((baseLidarAdapter13 != null ? baseLidarAdapter13.getVersion() : null) == LidarVersion.EAI_TG30_SLAMWARE) {
                        BaseLidarAdapter baseLidarAdapter14 = this.lidar;
                        if (baseLidarAdapter14 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("lidar");
                        }
                        if (baseLidarAdapter14 != null) {
                            baseLidarAdapter14.configInstall(false, false, doubleValue3);
                        }
                        BaseLidarAdapter baseLidarAdapter15 = this.lidar;
                        if (baseLidarAdapter15 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("lidar");
                        }
                        if (baseLidarAdapter15 != null) {
                            baseLidarAdapter15.configOutput(0.0d, Math.toRadians(-115.0d), Math.toRadians(115.0d));
                        }
                    } else {
                        BaseLidarAdapter baseLidarAdapter16 = this.lidar;
                        if (baseLidarAdapter16 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("lidar");
                        }
                        if ((baseLidarAdapter16 != null ? baseLidarAdapter16.getVersion() : null) == LidarVersion.LDS_15D) {
                            BaseLidarAdapter baseLidarAdapter17 = this.lidar;
                            if (baseLidarAdapter17 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("lidar");
                            }
                            if (baseLidarAdapter17 != null) {
                                baseLidarAdapter17.configOutput(0.0d, Math.toRadians(-120.0d), Math.toRadians(120.0d));
                            }
                        } else {
                            BaseLidarAdapter baseLidarAdapter18 = this.lidar;
                            if (baseLidarAdapter18 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("lidar");
                            }
                            if (baseLidarAdapter18 != null) {
                                baseLidarAdapter18.configOutput(3.141592653589793d, Math.toRadians(-120.0d), Math.toRadians(120.0d));
                            }
                        }
                    }
                    baseLidarAdapter = this.lidar;
                    if (baseLidarAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("lidar");
                    }
                    if (baseLidarAdapter != null && (version = baseLidarAdapter.getVersion()) != null && !LidarHelper.INSTANCE.canScanCloseBehind(version)) {
                        baseLidarAdapter2 = this.lidar;
                        if (baseLidarAdapter2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("lidar");
                        }
                        if (baseLidarAdapter2 != null) {
                            baseLidarAdapter2.setNeedSelfCheckByInnerScan(false);
                        }
                    }
                }
            }
            BaseLidarAdapter baseLidarAdapter19 = this.lidar;
            if (baseLidarAdapter19 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("lidar");
            }
            if (baseLidarAdapter19 != null) {
                baseLidarAdapter19.configOutput(3.141592653589793d, Math.toRadians(-115.0d), Math.toRadians(115.0d));
            }
            baseLidarAdapter = this.lidar;
            if (baseLidarAdapter == null) {
            }
            if (baseLidarAdapter != null) {
                baseLidarAdapter2 = this.lidar;
                if (baseLidarAdapter2 == null) {
                }
                if (baseLidarAdapter2 != null) {
                }
            }
        }
        BaseLidarAdapter baseLidarAdapter20 = this.lidar;
        if (baseLidarAdapter20 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lidar");
        }
        if (baseLidarAdapter20 != null) {
            baseLidarAdapter20.startScan();
        }
        if (this.needStartSecondLidar) {
            initSecondLidar();
        }
    }

    public final void initSecondLidar() {
        Pdlog.m3275i(this.TAG, "lidar type:" + LidarVersion.LD_06);
        if (this.secondLidar != null) {
            return;
        }
        BaseLidarAdapter createLidar = SecondLidarHelper.INSTANCE.createLidar(this.context, LidarVersion.LD_06, new LidarAdapterCallback() { // from class: com.pudutech.mirsdk.hardware.LidarInterfaceImpl$initSecondLidar$callback$1
            @Override // com.pudutech.lidar.LidarAdapterCallback
            public void onPowerRequest(boolean isOn) {
            }

            @Override // com.pudutech.lidar.LidarAdapterCallback
            public void onOneFrame(List<? extends LidarNode> data) {
                boolean z;
                String str;
                ArrayList arrayList;
                ArrayList arrayList2;
                ArrayList arrayList3;
                ArrayList arrayList4;
                String str2;
                ArrayList arrayList5;
                boolean z2;
                boolean z3;
                Intrinsics.checkParameterIsNotNull(data, "data");
                LidarInterfaceImpl.this.lastFrameTimestamps = SystemClock.elapsedRealtime();
                z = LidarInterfaceImpl.this.dataArrived;
                if (!z) {
                    arrayList = LidarInterfaceImpl.this.dataTimestamps;
                    arrayList.remove(0);
                    arrayList2 = LidarInterfaceImpl.this.dataTimestamps;
                    arrayList2.add(Long.valueOf(SystemClock.elapsedRealtime()));
                    arrayList3 = LidarInterfaceImpl.this.dataTimestamps;
                    long longValue = ((Number) arrayList3.get(4)).longValue();
                    arrayList4 = LidarInterfaceImpl.this.dataTimestamps;
                    Object obj = arrayList4.get(0);
                    Intrinsics.checkExpressionValueIsNotNull(obj, "dataTimestamps[0]");
                    if (longValue - ((Number) obj).longValue() < 1000) {
                        LidarInterfaceImpl.this.dataArrived = true;
                    }
                    str2 = LidarInterfaceImpl.this.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("lidar on frame,list: ");
                    arrayList5 = LidarInterfaceImpl.this.dataTimestamps;
                    sb.append(arrayList5.toString());
                    sb.append(" ,dataArrived = ");
                    z2 = LidarInterfaceImpl.this.dataArrived;
                    sb.append(z2);
                    Pdlog.m3276v(str2, sb.toString());
                    z3 = LidarInterfaceImpl.this.dataArrived;
                    if (!z3) {
                        return;
                    }
                }
                int size = data.size();
                PolarCoordinates[] polarCoordinatesArr = new PolarCoordinates[size];
                for (int i = 0; i < size; i++) {
                    PolarCoordinates polarCoordinates = new PolarCoordinates();
                    polarCoordinates.setAngle_rad(data.get(i).angleInRad);
                    polarCoordinates.setDistance_m(data.get(i).distance_m);
                    polarCoordinatesArr[i] = polarCoordinates;
                }
                str = LidarInterfaceImpl.this.TAG;
                Pdlog.m3273d(str, "lidar " + LidarVersion.LD_06 + " send frame size:" + data.size() + " to listener");
                LidarInterfaceImpl.this.onSecondLidarData(polarCoordinatesArr);
            }

            @Override // com.pudutech.lidar.LidarAdapterCallback
            public void onError(final LidarError errorMsg) {
                String str;
                ThreadSafeListener threadSafeListener;
                Intrinsics.checkParameterIsNotNull(errorMsg, "errorMsg");
                str = LidarInterfaceImpl.this.TAG;
                Pdlog.m3274e(str, String.valueOf(errorMsg));
                threadSafeListener = LidarInterfaceImpl.this.stateListeners;
                threadSafeListener.notify(new Function2<ILidarState, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.LidarInterfaceImpl$initSecondLidar$callback$1$onError$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(ILidarState iLidarState, String str2) {
                        invoke2(iLidarState, str2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(ILidarState l, String str2) {
                        Intrinsics.checkParameterIsNotNull(l, "l");
                        Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                        l.onError(LidarError.this.toString());
                    }
                });
            }
        });
        if (createLidar == null) {
            Intrinsics.throwNpe();
        }
        this.secondLidar = createLidar;
        if (createLidar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("secondLidar");
        }
        if (createLidar.getVersion() == LidarVersion.EAI_TG30_SLAMWARE) {
            registerNetworkCallback(this.context);
        }
        BaseLidarAdapter baseLidarAdapter = this.secondLidar;
        if (baseLidarAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("secondLidar");
        }
        if (baseLidarAdapter != null) {
            baseLidarAdapter.configInstall(true, false, 0.0d);
        }
        BaseLidarAdapter baseLidarAdapter2 = this.secondLidar;
        if (baseLidarAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("secondLidar");
        }
        if (baseLidarAdapter2 != null) {
            baseLidarAdapter2.configOutput(1.5707963267948966d, Math.toRadians(-80.0d), Math.toRadians(80.0d));
        }
        BaseLidarAdapter baseLidarAdapter3 = this.secondLidar;
        if (baseLidarAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("secondLidar");
        }
        if (baseLidarAdapter3 != null) {
            baseLidarAdapter3.startLidarService();
        }
        BaseLidarAdapter baseLidarAdapter4 = this.secondLidar;
        if (baseLidarAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("secondLidar");
        }
        if (baseLidarAdapter4 != null) {
            baseLidarAdapter4.startScan();
        }
    }

    @Override // com.pudutech.mirsdk.hardware.LidarInterface
    public void stop() {
        Pdlog.m3275i(this.TAG, "manual stop");
        LidarInterfaceImpl lidarInterfaceImpl = this;
        if (lidarInterfaceImpl.lidar == null) {
            Pdlog.m3274e(this.TAG, "lidar not init");
        } else if (!this.needStartSecondLidar || lidarInterfaceImpl.secondLidar != null) {
            BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new LidarInterfaceImpl$stop$3(this, null), 3, null);
        } else {
            Pdlog.m3274e(this.TAG, "second lidar not init");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onLidarStop(final LidarStopReason reason) {
        this.stateListeners.notify(new Function2<ILidarState, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.LidarInterfaceImpl$onLidarStop$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ILidarState iLidarState, String str) {
                invoke2(iLidarState, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ILidarState l, String str) {
                Intrinsics.checkParameterIsNotNull(l, "l");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                l.onStopScan(LidarStopReason.this);
            }
        });
    }

    @Override // com.pudutech.mirsdk.hardware.LidarInterface
    public void addStateListener(String name, ILidarState listener) {
        Pdlog.m3273d(this.TAG, "addStateListener " + name);
        if (listener == null || name == null) {
            return;
        }
        this.stateListeners.add(name, listener);
    }

    @Override // com.pudutech.mirsdk.hardware.LidarInterface
    public void removeStateListener(String name) {
        Pdlog.m3275i(this.TAG, "removeStateListener " + name);
        if (name != null) {
            this.stateListeners.remove(name);
        }
    }

    @Override // com.pudutech.mirsdk.hardware.LidarInterface
    public void addDataListener(String name, ILidarData listener) {
        Pdlog.m3273d(this.TAG, "addDataListener " + name);
        if (listener == null || name == null) {
            return;
        }
        this.dataListeners.add(name, listener);
    }

    @Override // com.pudutech.mirsdk.hardware.LidarInterface
    public void removeDataListener(String name) {
        Pdlog.m3275i(this.TAG, "removeDataListener " + name);
        if (name != null) {
            this.dataListeners.remove(name);
        }
    }

    @Override // com.pudutech.mirsdk.hardware.LidarInterface
    public void open() {
        Pdlog.m3275i(this.TAG, "opening lidar");
        LidarInterfaceImpl lidarInterfaceImpl = this;
        if (lidarInterfaceImpl.lidar == null) {
            Pdlog.m3274e(this.TAG, "lidar not init");
            return;
        }
        if (this.needStartSecondLidar && lidarInterfaceImpl.secondLidar == null) {
            Pdlog.m3274e(this.TAG, "second lidar not init");
        } else if (!this.wasOpened || SystemClock.elapsedRealtime() - this.lastFrameTimestamps >= 1000) {
            BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new LidarInterfaceImpl$open$3(this, null), 3, null);
        } else {
            Pdlog.m3275i(this.TAG, "lidar already opened");
            onLidarStart(true, "lidar already opened");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onLidarStart(final boolean success, final String description) {
        this.stateListeners.notify(new Function2<ILidarState, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.LidarInterfaceImpl$onLidarStart$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ILidarState iLidarState, String str) {
                invoke2(iLidarState, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ILidarState l, String str) {
                Intrinsics.checkParameterIsNotNull(l, "l");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                l.onStartScan(success, description);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onLidarData(final PolarCoordinates[] data) {
        this.dataListeners.notify(new Function2<ILidarData, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.LidarInterfaceImpl$onLidarData$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ILidarData iLidarData, String str) {
                invoke2(iLidarData, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ILidarData l, String str) {
                Intrinsics.checkParameterIsNotNull(l, "l");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                l.onFrame(data, SystemClock.elapsedRealtime());
            }
        });
        this.stateListeners.notify(new Function2<ILidarState, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.LidarInterfaceImpl$onLidarData$2
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ILidarState iLidarState, String str) {
                invoke2(iLidarState, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ILidarState l, String str) {
                Intrinsics.checkParameterIsNotNull(l, "l");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                l.onFrameTick();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onSecondLidarData(final PolarCoordinates[] data) {
        this.secondLidarDataListeners.notify(new Function2<ILidarData, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.LidarInterfaceImpl$onSecondLidarData$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ILidarData iLidarData, String str) {
                invoke2(iLidarData, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ILidarData l, String str) {
                Intrinsics.checkParameterIsNotNull(l, "l");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                l.onFrame(data, SystemClock.elapsedRealtime());
            }
        });
    }

    @Override // com.pudutech.mirsdk.hardware.LidarInterface
    public void startPreviewActivity() {
        if (this.lidar == null) {
            Pdlog.m3274e(this.TAG, "lidar not init");
        } else {
            Pdlog.m3273d(this.TAG, "startPreviewActivity");
            LidarHelper.INSTANCE.startTestActivity(this.context);
        }
    }

    private final void registerNetworkCallback(Context context) {
        final NetworkCallbackImpl.OnWifiStateChangedListener onWifiStateChangedListener = new NetworkCallbackImpl.OnWifiStateChangedListener() { // from class: com.pudutech.mirsdk.hardware.LidarInterfaceImpl$registerNetworkCallback$callback$2
            @Override // com.pudutech.mirsdk.hardware.network.NetworkCallbackImpl.OnWifiStateChangedListener
            public void onUnavailable() {
            }

            @Override // com.pudutech.mirsdk.hardware.network.NetworkCallbackImpl.OnWifiStateChangedListener
            public void onAvailable() {
                String str;
                Pair<Integer, String> execCommand = RootCmd.execCommand("ip route add 192.168.11.0/24 dev eth0 proto static scope link table wlan0", true);
                Intrinsics.checkExpressionValueIsNotNull(execCommand, "RootCmd.execCommand(commend, true)");
                str = LidarInterfaceImpl.this.TAG;
                Log.d(str, "pair: " + execCommand);
            }
        };
        NetworkCallbackImpl networkCallbackImpl = new NetworkCallbackImpl(onWifiStateChangedListener) { // from class: com.pudutech.mirsdk.hardware.LidarInterfaceImpl$registerNetworkCallback$callback$1
        };
        NetworkRequest build = new NetworkRequest.Builder().build();
        Object systemService = context.getSystemService("connectivity");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
        }
        ((ConnectivityManager) systemService).registerNetworkCallback(build, networkCallbackImpl);
    }

    @Override // com.pudutech.mirsdk.hardware.LidarInterface
    public void addSecondLidarDataListener(String name, ILidarData listener) {
        Pdlog.m3273d(this.TAG, "addSecondLidarDataListener " + name);
        if (listener == null || name == null) {
            return;
        }
        this.secondLidarDataListeners.add(name, listener);
    }

    @Override // com.pudutech.mirsdk.hardware.LidarInterface
    public void removeSecondLidarDataListener(String name) {
        Pdlog.m3275i(this.TAG, "removeSecondLidarDataListener " + name);
        if (name != null) {
            this.secondLidarDataListeners.remove(name);
        }
    }

    @Override // com.pudutech.mirsdk.hardware.LidarInterface
    public void startSecondLidarPreviewActivity() {
        if (this.secondLidar == null) {
            Pdlog.m3274e(this.TAG, "second lidar not init");
        } else {
            Pdlog.m3273d(this.TAG, "startSecondLidarPreviewActivity");
            SecondLidarHelper.INSTANCE.startTestActivity(this.context);
        }
    }

    /* renamed from: getLastError, reason: from getter */
    public final String getMLastErrorMsg() {
        return this.mLastErrorMsg;
    }
}
