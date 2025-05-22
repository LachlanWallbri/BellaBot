package com.pudutech.factory_test.test_pack;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;
import androidx.exifinterface.media.ExifInterface;
import com.pudutech.base.Pdlog;
import com.pudutech.factory_test.ServiceConnectionKt;
import com.pudutech.factory_test.single_test.AtomizationModuleTestActivity;
import com.pudutech.factory_test.single_test.AudioTestActivity;
import com.pudutech.factory_test.single_test.AutoRechargeTestActivity;
import com.pudutech.factory_test.single_test.BusinessTestActivity;
import com.pudutech.factory_test.single_test.ChargeTestActivity;
import com.pudutech.factory_test.single_test.DisinfectionTestActivity;
import com.pudutech.factory_test.single_test.ESP32TestActivity;
import com.pudutech.factory_test.single_test.EncoderTestActivity;
import com.pudutech.factory_test.single_test.FrontFacingCameraTestActivity;
import com.pudutech.factory_test.single_test.HardwareVersionActivity;
import com.pudutech.factory_test.single_test.HumanSensorTestActivity;
import com.pudutech.factory_test.single_test.IMUTestActivity;
import com.pudutech.factory_test.single_test.LEDFaceScreenTestActivity;
import com.pudutech.factory_test.single_test.LEDScreenTestActivity;
import com.pudutech.factory_test.single_test.LidarTestActivity;
import com.pudutech.factory_test.single_test.LocateCameraTestActivity;
import com.pudutech.factory_test.single_test.LoraTestActivity;
import com.pudutech.factory_test.single_test.MicrophoneTestActivity;
import com.pudutech.factory_test.single_test.MotorTestActivity;
import com.pudutech.factory_test.single_test.RgbdTestActivity;
import com.pudutech.factory_test.single_test.ScreenTestActivity;
import com.pudutech.factory_test.single_test.SurfaceLEDTestActivity;
import com.pudutech.factory_test.single_test.SwitchTestActivity;
import com.pudutech.factory_test.single_test.TouchPlaneTestActivity;
import com.pudutech.factory_test.single_test.TouchSensorTestActivity;
import com.pudutech.factory_test.single_test.TrayTestActivity;
import com.pudutech.factory_test.single_test.WifiTestActivity2;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.MachineModel;
import com.pudutech.mirsdk.hardware.serialize.ProductMachineType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AllTestItem.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\n\bÆ\u0002\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u001a\u001a\u00020\u0002J\u0006\u0010\u001b\u001a\u00020\u0011J\u0006\u0010\u001c\u001a\u00020\u001dJ\u0006\u0010\u001e\u001a\u00020\u001dJ\u0006\u0010\u001f\u001a\u00020\u001dJ\u0006\u0010 \u001a\u00020\u001dJ\u0006\u0010!\u001a\u00020\u001dJ\u0006\u0010\"\u001a\u00020\u001dJ\b\u0010#\u001a\u00020\u0011H\u0002J\u0006\u0010$\u001a\u00020\u0011J\u001d\u0010%\u001a\u00020\u0011\"\n\b\u0000\u0010&\u0018\u0001*\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0082\bR\u0014\u0010\u0005\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006'"}, m3961d2 = {"Lcom/pudutech/factory_test/test_pack/AllTestItem;", "Ljava/util/ArrayList;", "Lcom/pudutech/factory_test/test_pack/TestItem;", "Lkotlin/collections/ArrayList;", "()V", "EXTER_TEST_STAGE_KEY", "", "getEXTER_TEST_STAGE_KEY", "()Ljava/lang/String;", "TAG", "activity", "Landroid/app/Activity;", "getActivity", "()Landroid/app/Activity;", "setActivity", "(Landroid/app/Activity;)V", "addBellaItem", "", "stage", "Lcom/pudutech/factory_test/test_pack/TestStage;", "addHLSItem", "addHolaItem", "addNineTalesItem", "addPeanutItem", "addTestItems", "getNextUntested", "current", "initItems", "isBella", "", "isHLS", "isHLX", "isHola", "isNineTales", "isPeanut", "loadRecoder", "resetAll", "startActivity", ExifInterface.GPS_DIRECTION_TRUE, "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class AllTestItem extends ArrayList<TestItem> {
    private static Activity activity;
    public static final AllTestItem INSTANCE = new AllTestItem();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final String EXTER_TEST_STAGE_KEY = EXTER_TEST_STAGE_KEY;
    private static final String EXTER_TEST_STAGE_KEY = EXTER_TEST_STAGE_KEY;

    private AllTestItem() {
    }

    public /* bridge */ boolean contains(TestItem testItem) {
        return super.contains((Object) testItem);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof TestItem) {
            return contains((TestItem) obj);
        }
        return false;
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ int indexOf(TestItem testItem) {
        return super.indexOf((Object) testItem);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof TestItem) {
            return indexOf((TestItem) obj);
        }
        return -1;
    }

    public /* bridge */ int lastIndexOf(TestItem testItem) {
        return super.lastIndexOf((Object) testItem);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof TestItem) {
            return lastIndexOf((TestItem) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ TestItem remove(int i) {
        return removeAt(i);
    }

    public /* bridge */ boolean remove(TestItem testItem) {
        return super.remove((Object) testItem);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean remove(Object obj) {
        if (obj instanceof TestItem) {
            return remove((TestItem) obj);
        }
        return false;
    }

    public /* bridge */ TestItem removeAt(int i) {
        return (TestItem) super.remove(i);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ int size() {
        return getSize();
    }

    public final Activity getActivity() {
        return activity;
    }

    public final void setActivity(Activity activity2) {
        activity = activity2;
    }

    public final String getEXTER_TEST_STAGE_KEY() {
        return EXTER_TEST_STAGE_KEY;
    }

    public final void initItems() {
        MachineInfo machineInfo;
        MachineInfo machineInfo2;
        ProductMachineType productType;
        MachineModel model;
        if (ServiceConnectionKt.getHdInterface() == null) {
            return;
        }
        clear();
        String str = TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("init ");
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        HashMap<MachineInfo.Byte23Info, UByte> hashMap = null;
        sb.append((hdInterface == null || (machineInfo2 = hdInterface.getMachineInfo()) == null || (productType = machineInfo2.getProductType()) == null || (model = productType.getModel()) == null) ? null : model.name());
        objArr[0] = sb.toString();
        Pdlog.m3275i(str, objArr);
        String str2 = TAG;
        Object[] objArr2 = new Object[1];
        StringBuilder sb2 = new StringBuilder();
        sb2.append("init ");
        HardwareInterface hdInterface2 = ServiceConnectionKt.getHdInterface();
        if (hdInterface2 != null && (machineInfo = hdInterface2.getMachineInfo()) != null) {
            hashMap = machineInfo.getByte23Data();
        }
        sb2.append(String.valueOf(hashMap));
        objArr2[0] = sb2.toString();
        Pdlog.m3275i(str2, objArr2);
        addTestItems(TestStage.INIT_TEST);
        addTestItems(TestStage.FINAL_TEST);
        loadRecoder();
    }

    public final void addTestItems(TestStage stage) {
        Intrinsics.checkParameterIsNotNull(stage, "stage");
        if (isHLS() || isHLX()) {
            addHLSItem(stage);
        }
        if (isPeanut()) {
            addPeanutItem(stage);
        }
        if (isBella()) {
            addBellaItem(stage);
        }
        if (isHola()) {
            addHolaItem(stage);
        }
        if (isNineTales()) {
            addNineTalesItem(stage);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final /* synthetic */ <T extends Activity> void startActivity(TestStage stage) {
        Activity activity2 = getActivity();
        if (activity2 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Intent intent = new Intent(activity2, (Class<?>) Activity.class);
        intent.putExtra(getEXTER_TEST_STAGE_KEY(), stage.getStr());
        Activity activity3 = getActivity();
        if (activity3 == null) {
            Intrinsics.throwNpe();
        }
        activity3.startActivity(intent);
    }

    public final TestItem getNextUntested(TestItem current) {
        Intrinsics.checkParameterIsNotNull(current, "current");
        int indexOf = indexOf((Object) current);
        ArrayList arrayList = new ArrayList();
        for (TestItem testItem : this) {
            TestItem testItem2 = testItem;
            if (testItem2.getStatus() == TestStatus.UNTESTED && testItem2.getStage() == current.getStage()) {
                arrayList.add(testItem);
            }
        }
        ArrayList<TestItem> arrayList2 = arrayList;
        for (TestItem testItem3 : arrayList2) {
            if (INSTANCE.indexOf((Object) testItem3) > indexOf) {
                return testItem3;
            }
        }
        return (TestItem) CollectionsKt.firstOrNull((List) arrayList2);
    }

    public final void resetAll() {
        Pdlog.m3273d(TAG, "resetAll ");
        Iterator<TestItem> it = INSTANCE.iterator();
        while (it.hasNext()) {
            it.next().setStatus(TestStatus.UNTESTED);
        }
    }

    private final void loadRecoder() {
        Pdlog.m3273d(TAG, "loadRecoder ");
        for (TestItem testItem : this) {
            TestStatus testStatus = Recorder.INSTANCE.get(testItem.getStage() + testItem.getName());
            Pdlog.m3273d(TAG, "load " + testItem.getName() + ' ' + testStatus);
            testItem.setStatus(testStatus);
        }
    }

    public final void addHLSItem(final TestStage stage) {
        MachineInfo machineInfo;
        Intrinsics.checkParameterIsNotNull(stage, "stage");
        add(new TestItem(stage, TestConstantKt.HARDWARE_VERSION, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addHLSItem$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) HardwareVersionActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.TOUCH_PALNE, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addHLSItem$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) TouchPlaneTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.SCREEN, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addHLSItem$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) ScreenTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.AUDIO, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addHLSItem$4
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) AudioTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.SURFACE_LED, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addHLSItem$5
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) SurfaceLEDTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.WIFI, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addHLSItem$6
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                if (WifiUtil.INSTANCE.getMac() != null) {
                    WifiUtil wifiUtil = WifiUtil.INSTANCE;
                    Activity activity2 = AllTestItem.INSTANCE.getActivity();
                    if (activity2 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (wifiUtil.isNetworkAvailable(activity2)) {
                        Activity activity3 = AllTestItem.INSTANCE.getActivity();
                        if (activity3 == null) {
                            Intrinsics.throwNpe();
                        }
                        Intent intent = new Intent(activity3, (Class<?>) WifiTestActivity2.class);
                        intent.putExtra(AllTestItem.INSTANCE.getEXTER_TEST_STAGE_KEY(), TestStage.this.getStr());
                        Activity activity4 = AllTestItem.INSTANCE.getActivity();
                        if (activity4 != null) {
                            activity4.startActivityForResult(intent, 100);
                            return;
                        }
                        return;
                    }
                }
                Toast.makeText(AllTestItem.INSTANCE.getActivity(), "请先连接WIFI", 1).show();
            }
        }));
        add(new TestItem(stage, TestConstantKt.IMU, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addHLSItem$7
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) IMUTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.ENCODER, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addHLSItem$8
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                TestItem testItem;
                Iterator<TestItem> it = AllTestItem.INSTANCE.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        testItem = null;
                        break;
                    }
                    testItem = it.next();
                    TestItem testItem2 = testItem;
                    if (Intrinsics.areEqual(testItem2.getName(), TestConstantKt.IMU) && testItem2.getStage() == TestStage.this) {
                        break;
                    }
                }
                if (testItem == null) {
                    Intrinsics.throwNpe();
                }
                if (testItem.getStatus() != TestStatus.SUCCESS) {
                    Toast.makeText(AllTestItem.INSTANCE.getActivity(), "请先保证陀螺仪测试成功", 1).show();
                    return;
                }
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) EncoderTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.MOTOR, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addHLSItem$9
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                TestItem testItem;
                Iterator<TestItem> it = AllTestItem.INSTANCE.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        testItem = null;
                        break;
                    }
                    testItem = it.next();
                    TestItem testItem2 = testItem;
                    if (Intrinsics.areEqual(testItem2.getName(), TestConstantKt.ENCODER) && testItem2.getStage() == TestStage.this) {
                        break;
                    }
                }
                if (testItem == null) {
                    Intrinsics.throwNpe();
                }
                if (testItem.getStatus() != TestStatus.SUCCESS) {
                    Toast.makeText(AllTestItem.INSTANCE.getActivity(), "请先保证编码器测试成功", 1).show();
                    return;
                }
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) MotorTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.LOCATE_CAMERA, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addHLSItem$10
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) LocateCameraTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.LIDAR, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addHLSItem$11
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) LidarTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        if (((hdInterface == null || (machineInfo = hdInterface.getMachineInfo()) == null) ? null : machineInfo.getRGBDMode()) != MachineInfo.RGBDType.NODevice) {
            add(new TestItem(stage, TestConstantKt.RGBD, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addHLSItem$12
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    AllTestItem allTestItem = AllTestItem.INSTANCE;
                    TestStage testStage = TestStage.this;
                    Activity activity2 = allTestItem.getActivity();
                    if (activity2 == null) {
                        Intrinsics.throwNpe();
                    }
                    Intent intent = new Intent(activity2, (Class<?>) RgbdTestActivity.class);
                    intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                    Activity activity3 = allTestItem.getActivity();
                    if (activity3 == null) {
                        Intrinsics.throwNpe();
                    }
                    activity3.startActivity(intent);
                }
            }));
        }
        add(new TestItem(stage, TestConstantKt.ESP32, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addHLSItem$13
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                if (WifiUtil.INSTANCE.getMac() != null) {
                    AllTestItem allTestItem = AllTestItem.INSTANCE;
                    TestStage testStage = TestStage.this;
                    Activity activity2 = allTestItem.getActivity();
                    if (activity2 == null) {
                        Intrinsics.throwNpe();
                    }
                    Intent intent = new Intent(activity2, (Class<?>) ESP32TestActivity.class);
                    intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                    Activity activity3 = allTestItem.getActivity();
                    if (activity3 == null) {
                        Intrinsics.throwNpe();
                    }
                    activity3.startActivity(intent);
                    return;
                }
                Toast.makeText(AllTestItem.INSTANCE.getActivity(), "请先连网以获取mac地址", 1).show();
            }
        }));
        add(new TestItem(stage, TestConstantKt.CHARGE, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addHLSItem$14
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) ChargeTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.BUSINESS, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addHLSItem$15
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                ArrayList arrayList = new ArrayList();
                Iterator<TestItem> it = allTestItem.iterator();
                while (true) {
                    boolean z = true;
                    if (!it.hasNext()) {
                        break;
                    }
                    TestItem next = it.next();
                    TestItem testItem = next;
                    if (testItem.getStage() == TestStage.this && testItem.getStatus() != TestStatus.SUCCESS && !Intrinsics.areEqual(testItem.getName(), TestConstantKt.BUSINESS)) {
                        z = false;
                    }
                    if (!z) {
                        arrayList.add(next);
                    }
                }
                if (!arrayList.isEmpty()) {
                    Toast.makeText(AllTestItem.INSTANCE.getActivity(), "请先完成其他未测试项", 1).show();
                    return;
                }
                if (WifiUtil.INSTANCE.getMac() != null) {
                    WifiUtil wifiUtil = WifiUtil.INSTANCE;
                    Activity activity2 = AllTestItem.INSTANCE.getActivity();
                    if (activity2 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (wifiUtil.isNetworkAvailable(activity2)) {
                        Activity activity3 = AllTestItem.INSTANCE.getActivity();
                        if (activity3 == null) {
                            Intrinsics.throwNpe();
                        }
                        Intent intent = new Intent(activity3, (Class<?>) BusinessTestActivity.class);
                        intent.putExtra(AllTestItem.INSTANCE.getEXTER_TEST_STAGE_KEY(), TestStage.this.getStr());
                        Activity activity4 = AllTestItem.INSTANCE.getActivity();
                        if (activity4 != null) {
                            activity4.startActivityForResult(intent, 100);
                            return;
                        }
                        return;
                    }
                }
                Toast.makeText(AllTestItem.INSTANCE.getActivity(), "请先连接WIFI", 1).show();
            }
        }));
    }

    public final void addPeanutItem(final TestStage stage) {
        MachineInfo machineInfo;
        Intrinsics.checkParameterIsNotNull(stage, "stage");
        add(new TestItem(stage, TestConstantKt.HARDWARE_VERSION, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addPeanutItem$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) HardwareVersionActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.TOUCH_PALNE, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addPeanutItem$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) TouchPlaneTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.SCREEN, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addPeanutItem$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) ScreenTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.AUDIO, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addPeanutItem$4
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) AudioTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.MICROPHONE, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addPeanutItem$5
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                TestItem testItem;
                Iterator<TestItem> it = AllTestItem.INSTANCE.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        testItem = null;
                        break;
                    }
                    testItem = it.next();
                    TestItem testItem2 = testItem;
                    if (Intrinsics.areEqual(testItem2.getName(), TestConstantKt.AUDIO) && testItem2.getStage() == TestStage.this) {
                        break;
                    }
                }
                if (testItem == null) {
                    Intrinsics.throwNpe();
                }
                if (testItem.getStatus() != TestStatus.SUCCESS) {
                    Toast.makeText(AllTestItem.INSTANCE.getActivity(), "请先保证音响测试成功", 1).show();
                    return;
                }
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) MicrophoneTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.SURFACE_LED, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addPeanutItem$6
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) SurfaceLEDTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.WIFI, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addPeanutItem$7
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                if (WifiUtil.INSTANCE.getMac() != null) {
                    WifiUtil wifiUtil = WifiUtil.INSTANCE;
                    Activity activity2 = AllTestItem.INSTANCE.getActivity();
                    if (activity2 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (wifiUtil.isNetworkAvailable(activity2)) {
                        Activity activity3 = AllTestItem.INSTANCE.getActivity();
                        if (activity3 == null) {
                            Intrinsics.throwNpe();
                        }
                        Intent intent = new Intent(activity3, (Class<?>) WifiTestActivity2.class);
                        intent.putExtra(AllTestItem.INSTANCE.getEXTER_TEST_STAGE_KEY(), TestStage.this.getStr());
                        Activity activity4 = AllTestItem.INSTANCE.getActivity();
                        if (activity4 != null) {
                            activity4.startActivityForResult(intent, 100);
                            return;
                        }
                        return;
                    }
                }
                Toast.makeText(AllTestItem.INSTANCE.getActivity(), "请先连接WIFI", 1).show();
            }
        }));
        add(new TestItem(stage, TestConstantKt.IMU, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addPeanutItem$8
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) IMUTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.ENCODER, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addPeanutItem$9
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                TestItem testItem;
                Iterator<TestItem> it = AllTestItem.INSTANCE.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        testItem = null;
                        break;
                    }
                    testItem = it.next();
                    TestItem testItem2 = testItem;
                    if (Intrinsics.areEqual(testItem2.getName(), TestConstantKt.IMU) && testItem2.getStage() == TestStage.this) {
                        break;
                    }
                }
                if (testItem == null) {
                    Intrinsics.throwNpe();
                }
                if (testItem.getStatus() != TestStatus.SUCCESS) {
                    Toast.makeText(AllTestItem.INSTANCE.getActivity(), "请先保证陀螺仪测试成功", 1).show();
                    return;
                }
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) EncoderTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.FRONT_FACING_CAMERA, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addPeanutItem$10
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) FrontFacingCameraTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.MOTOR, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addPeanutItem$11
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                TestItem testItem;
                Iterator<TestItem> it = AllTestItem.INSTANCE.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        testItem = null;
                        break;
                    }
                    testItem = it.next();
                    TestItem testItem2 = testItem;
                    if (Intrinsics.areEqual(testItem2.getName(), TestConstantKt.ENCODER) && testItem2.getStage() == TestStage.this) {
                        break;
                    }
                }
                if (testItem == null) {
                    Intrinsics.throwNpe();
                }
                if (testItem.getStatus() != TestStatus.SUCCESS) {
                    Toast.makeText(AllTestItem.INSTANCE.getActivity(), "请先保证编码器测试成功", 1).show();
                    return;
                }
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) MotorTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.LOCATE_CAMERA, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addPeanutItem$12
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) LocateCameraTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.LIDAR, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addPeanutItem$13
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) LidarTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        if (((hdInterface == null || (machineInfo = hdInterface.getMachineInfo()) == null) ? null : machineInfo.getRGBDMode()) != MachineInfo.RGBDType.NODevice) {
            add(new TestItem(stage, TestConstantKt.RGBD, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addPeanutItem$14
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    AllTestItem allTestItem = AllTestItem.INSTANCE;
                    TestStage testStage = TestStage.this;
                    Activity activity2 = allTestItem.getActivity();
                    if (activity2 == null) {
                        Intrinsics.throwNpe();
                    }
                    Intent intent = new Intent(activity2, (Class<?>) RgbdTestActivity.class);
                    intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                    Activity activity3 = allTestItem.getActivity();
                    if (activity3 == null) {
                        Intrinsics.throwNpe();
                    }
                    activity3.startActivity(intent);
                }
            }));
        }
        add(new TestItem(stage, TestConstantKt.ESP32, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addPeanutItem$15
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                if (WifiUtil.INSTANCE.getMac() != null) {
                    AllTestItem allTestItem = AllTestItem.INSTANCE;
                    TestStage testStage = TestStage.this;
                    Activity activity2 = allTestItem.getActivity();
                    if (activity2 == null) {
                        Intrinsics.throwNpe();
                    }
                    Intent intent = new Intent(activity2, (Class<?>) ESP32TestActivity.class);
                    intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                    Activity activity3 = allTestItem.getActivity();
                    if (activity3 == null) {
                        Intrinsics.throwNpe();
                    }
                    activity3.startActivity(intent);
                    return;
                }
                Toast.makeText(AllTestItem.INSTANCE.getActivity(), "请先连网以获取mac地址", 1).show();
            }
        }));
        add(new TestItem(stage, TestConstantKt.CHARGE, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addPeanutItem$16
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) ChargeTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.BUSINESS, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addPeanutItem$17
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                ArrayList arrayList = new ArrayList();
                Iterator<TestItem> it = allTestItem.iterator();
                while (true) {
                    boolean z = true;
                    if (!it.hasNext()) {
                        break;
                    }
                    TestItem next = it.next();
                    TestItem testItem = next;
                    if (testItem.getStage() == TestStage.this && testItem.getStatus() != TestStatus.SUCCESS && !Intrinsics.areEqual(testItem.getName(), TestConstantKt.BUSINESS)) {
                        z = false;
                    }
                    if (!z) {
                        arrayList.add(next);
                    }
                }
                if (!arrayList.isEmpty()) {
                    Toast.makeText(AllTestItem.INSTANCE.getActivity(), "请先完成其他未测试项", 1).show();
                    return;
                }
                if (WifiUtil.INSTANCE.getMac() != null) {
                    WifiUtil wifiUtil = WifiUtil.INSTANCE;
                    Activity activity2 = AllTestItem.INSTANCE.getActivity();
                    if (activity2 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (wifiUtil.isNetworkAvailable(activity2)) {
                        Activity activity3 = AllTestItem.INSTANCE.getActivity();
                        if (activity3 == null) {
                            Intrinsics.throwNpe();
                        }
                        Intent intent = new Intent(activity3, (Class<?>) BusinessTestActivity.class);
                        intent.putExtra(AllTestItem.INSTANCE.getEXTER_TEST_STAGE_KEY(), TestStage.this.getStr());
                        Activity activity4 = AllTestItem.INSTANCE.getActivity();
                        if (activity4 != null) {
                            activity4.startActivityForResult(intent, 100);
                            return;
                        }
                        return;
                    }
                }
                Toast.makeText(AllTestItem.INSTANCE.getActivity(), "请先连接WIFI", 1).show();
            }
        }));
    }

    public final void addBellaItem(final TestStage stage) {
        MachineInfo machineInfo;
        MachineInfo machineInfo2;
        ProductMachineType productType;
        Intrinsics.checkParameterIsNotNull(stage, "stage");
        add(new TestItem(stage, TestConstantKt.HARDWARE_VERSION, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addBellaItem$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) HardwareVersionActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.TOUCH_PALNE, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addBellaItem$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) TouchPlaneTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.SCREEN, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addBellaItem$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) ScreenTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.AUDIO, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addBellaItem$4
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) AudioTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.MICROPHONE, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addBellaItem$5
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                TestItem testItem;
                Iterator<TestItem> it = AllTestItem.INSTANCE.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        testItem = null;
                        break;
                    }
                    testItem = it.next();
                    TestItem testItem2 = testItem;
                    if (Intrinsics.areEqual(testItem2.getName(), TestConstantKt.AUDIO) && testItem2.getStage() == TestStage.this) {
                        break;
                    }
                }
                if (testItem == null) {
                    Intrinsics.throwNpe();
                }
                if (testItem.getStatus() != TestStatus.SUCCESS) {
                    Toast.makeText(AllTestItem.INSTANCE.getActivity(), "请先保证音响测试成功", 1).show();
                    return;
                }
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) MicrophoneTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.SURFACE_LED, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addBellaItem$6
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) SurfaceLEDTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.LED_SCREEN, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addBellaItem$7
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) LEDScreenTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.TRAY_SENSOR, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addBellaItem$8
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) TrayTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.TOUCH_SENSOR, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addBellaItem$9
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) TouchSensorTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.SWITCH, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addBellaItem$10
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) SwitchTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.WIFI, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addBellaItem$11
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                if (WifiUtil.INSTANCE.getMac() != null) {
                    WifiUtil wifiUtil = WifiUtil.INSTANCE;
                    Activity activity2 = AllTestItem.INSTANCE.getActivity();
                    if (activity2 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (wifiUtil.isNetworkAvailable(activity2)) {
                        Activity activity3 = AllTestItem.INSTANCE.getActivity();
                        if (activity3 == null) {
                            Intrinsics.throwNpe();
                        }
                        Intent intent = new Intent(activity3, (Class<?>) WifiTestActivity2.class);
                        intent.putExtra(AllTestItem.INSTANCE.getEXTER_TEST_STAGE_KEY(), TestStage.this.getStr());
                        Activity activity4 = AllTestItem.INSTANCE.getActivity();
                        if (activity4 != null) {
                            activity4.startActivityForResult(intent, 100);
                            return;
                        }
                        return;
                    }
                }
                Toast.makeText(AllTestItem.INSTANCE.getActivity(), "请先连接WIFI", 1).show();
            }
        }));
        add(new TestItem(stage, TestConstantKt.IMU, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addBellaItem$12
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) IMUTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.ENCODER, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addBellaItem$13
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                TestItem testItem;
                Iterator<TestItem> it = AllTestItem.INSTANCE.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        testItem = null;
                        break;
                    }
                    testItem = it.next();
                    TestItem testItem2 = testItem;
                    if (Intrinsics.areEqual(testItem2.getName(), TestConstantKt.IMU) && testItem2.getStage() == TestStage.this) {
                        break;
                    }
                }
                if (testItem == null) {
                    Intrinsics.throwNpe();
                }
                if (testItem.getStatus() != TestStatus.SUCCESS) {
                    Toast.makeText(AllTestItem.INSTANCE.getActivity(), "请先保证陀螺仪测试成功", 1).show();
                    return;
                }
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) EncoderTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.MOTOR, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addBellaItem$14
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                TestItem testItem;
                Iterator<TestItem> it = AllTestItem.INSTANCE.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        testItem = null;
                        break;
                    }
                    testItem = it.next();
                    TestItem testItem2 = testItem;
                    if (Intrinsics.areEqual(testItem2.getName(), TestConstantKt.ENCODER) && testItem2.getStage() == TestStage.this) {
                        break;
                    }
                }
                if (testItem == null) {
                    Intrinsics.throwNpe();
                }
                if (testItem.getStatus() != TestStatus.SUCCESS) {
                    Toast.makeText(AllTestItem.INSTANCE.getActivity(), "请先保证编码器测试成功", 1).show();
                    return;
                }
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) MotorTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.LOCATE_CAMERA, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addBellaItem$15
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) LocateCameraTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        MachineInfo.RGBDType rGBDType = null;
        if (((hdInterface == null || (machineInfo2 = hdInterface.getMachineInfo()) == null || (productType = machineInfo2.getProductType()) == null) ? null : productType.getModel()) == MachineModel.BellaBot) {
            add(new TestItem(stage, TestConstantKt.FRONT_FACING_CAMERA, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addBellaItem$16
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    AllTestItem allTestItem = AllTestItem.INSTANCE;
                    TestStage testStage = TestStage.this;
                    Activity activity2 = allTestItem.getActivity();
                    if (activity2 == null) {
                        Intrinsics.throwNpe();
                    }
                    Intent intent = new Intent(activity2, (Class<?>) FrontFacingCameraTestActivity.class);
                    intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                    Activity activity3 = allTestItem.getActivity();
                    if (activity3 == null) {
                        Intrinsics.throwNpe();
                    }
                    activity3.startActivity(intent);
                }
            }));
        }
        add(new TestItem(stage, TestConstantKt.LIDAR, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addBellaItem$17
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) LidarTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        HardwareInterface hdInterface2 = ServiceConnectionKt.getHdInterface();
        if (hdInterface2 != null && (machineInfo = hdInterface2.getMachineInfo()) != null) {
            rGBDType = machineInfo.getRGBDMode();
        }
        if (rGBDType != MachineInfo.RGBDType.NODevice) {
            add(new TestItem(stage, TestConstantKt.RGBD, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addBellaItem$18
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    AllTestItem allTestItem = AllTestItem.INSTANCE;
                    TestStage testStage = TestStage.this;
                    Activity activity2 = allTestItem.getActivity();
                    if (activity2 == null) {
                        Intrinsics.throwNpe();
                    }
                    Intent intent = new Intent(activity2, (Class<?>) RgbdTestActivity.class);
                    intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                    Activity activity3 = allTestItem.getActivity();
                    if (activity3 == null) {
                        Intrinsics.throwNpe();
                    }
                    activity3.startActivity(intent);
                }
            }));
        }
        add(new TestItem(stage, TestConstantKt.ESP32, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addBellaItem$19
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                if (WifiUtil.INSTANCE.getMac() != null) {
                    AllTestItem allTestItem = AllTestItem.INSTANCE;
                    TestStage testStage = TestStage.this;
                    Activity activity2 = allTestItem.getActivity();
                    if (activity2 == null) {
                        Intrinsics.throwNpe();
                    }
                    Intent intent = new Intent(activity2, (Class<?>) ESP32TestActivity.class);
                    intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                    Activity activity3 = allTestItem.getActivity();
                    if (activity3 == null) {
                        Intrinsics.throwNpe();
                    }
                    activity3.startActivity(intent);
                    return;
                }
                Toast.makeText(AllTestItem.INSTANCE.getActivity(), "请先连网以获取mac地址", 1).show();
            }
        }));
        add(new TestItem(stage, TestConstantKt.CHARGE, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addBellaItem$20
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) ChargeTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.BUSINESS, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addBellaItem$21
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                ArrayList arrayList = new ArrayList();
                Iterator<TestItem> it = allTestItem.iterator();
                while (true) {
                    boolean z = true;
                    if (!it.hasNext()) {
                        break;
                    }
                    TestItem next = it.next();
                    TestItem testItem = next;
                    if (testItem.getStage() == TestStage.this && testItem.getStatus() != TestStatus.SUCCESS && !Intrinsics.areEqual(testItem.getName(), TestConstantKt.BUSINESS)) {
                        z = false;
                    }
                    if (!z) {
                        arrayList.add(next);
                    }
                }
                if (!arrayList.isEmpty()) {
                    Toast.makeText(AllTestItem.INSTANCE.getActivity(), "请先完成其他未测试项", 1).show();
                    return;
                }
                if (WifiUtil.INSTANCE.getMac() != null) {
                    Activity activity2 = AllTestItem.INSTANCE.getActivity();
                    if (activity2 == null) {
                        Intrinsics.throwNpe();
                    }
                    Intent intent = new Intent(activity2, (Class<?>) BusinessTestActivity.class);
                    intent.putExtra(AllTestItem.INSTANCE.getEXTER_TEST_STAGE_KEY(), TestStage.this.getStr());
                    Activity activity3 = AllTestItem.INSTANCE.getActivity();
                    if (activity3 != null) {
                        activity3.startActivityForResult(intent, 100);
                        return;
                    }
                    return;
                }
                Toast.makeText(AllTestItem.INSTANCE.getActivity(), "请先连网以获取mac地址", 1).show();
            }
        }));
    }

    public final void addHolaItem(final TestStage stage) {
        MachineInfo machineInfo;
        Intrinsics.checkParameterIsNotNull(stage, "stage");
        add(new TestItem(stage, TestConstantKt.HARDWARE_VERSION, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addHolaItem$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) HardwareVersionActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.TOUCH_PALNE, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addHolaItem$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) TouchPlaneTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.SCREEN, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addHolaItem$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) ScreenTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.AUDIO, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addHolaItem$4
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) AudioTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.MICROPHONE, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addHolaItem$5
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                TestItem testItem;
                Iterator<TestItem> it = AllTestItem.INSTANCE.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        testItem = null;
                        break;
                    }
                    testItem = it.next();
                    TestItem testItem2 = testItem;
                    if (Intrinsics.areEqual(testItem2.getName(), TestConstantKt.AUDIO) && testItem2.getStage() == TestStage.this) {
                        break;
                    }
                }
                if (testItem == null) {
                    Intrinsics.throwNpe();
                }
                if (testItem.getStatus() != TestStatus.SUCCESS) {
                    Toast.makeText(AllTestItem.INSTANCE.getActivity(), "请先保证音响测试成功", 1).show();
                    return;
                }
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) MicrophoneTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.SURFACE_LED, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addHolaItem$6
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) SurfaceLEDTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.LED_FACE_SCREEN, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addHolaItem$7
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) LEDFaceScreenTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.TOUCH_SENSOR, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addHolaItem$8
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) TouchSensorTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.SWITCH, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addHolaItem$9
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) SwitchTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.WIFI, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addHolaItem$10
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                if (WifiUtil.INSTANCE.getMac() != null) {
                    WifiUtil wifiUtil = WifiUtil.INSTANCE;
                    Activity activity2 = AllTestItem.INSTANCE.getActivity();
                    if (activity2 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (wifiUtil.isNetworkAvailable(activity2)) {
                        Activity activity3 = AllTestItem.INSTANCE.getActivity();
                        if (activity3 == null) {
                            Intrinsics.throwNpe();
                        }
                        Intent intent = new Intent(activity3, (Class<?>) WifiTestActivity2.class);
                        intent.putExtra(AllTestItem.INSTANCE.getEXTER_TEST_STAGE_KEY(), TestStage.this.getStr());
                        Activity activity4 = AllTestItem.INSTANCE.getActivity();
                        if (activity4 != null) {
                            activity4.startActivityForResult(intent, 100);
                            return;
                        }
                        return;
                    }
                }
                Toast.makeText(AllTestItem.INSTANCE.getActivity(), "请先连接WIFI", 1).show();
            }
        }));
        add(new TestItem(stage, TestConstantKt.IMU, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addHolaItem$11
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) IMUTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.ENCODER, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addHolaItem$12
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                TestItem testItem;
                Iterator<TestItem> it = AllTestItem.INSTANCE.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        testItem = null;
                        break;
                    }
                    testItem = it.next();
                    TestItem testItem2 = testItem;
                    if (Intrinsics.areEqual(testItem2.getName(), TestConstantKt.IMU) && testItem2.getStage() == TestStage.this) {
                        break;
                    }
                }
                if (testItem == null) {
                    Intrinsics.throwNpe();
                }
                if (testItem.getStatus() != TestStatus.SUCCESS) {
                    Toast.makeText(AllTestItem.INSTANCE.getActivity(), "请先保证陀螺仪测试成功", 1).show();
                    return;
                }
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) EncoderTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.MOTOR, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addHolaItem$13
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                TestItem testItem;
                Iterator<TestItem> it = AllTestItem.INSTANCE.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        testItem = null;
                        break;
                    }
                    testItem = it.next();
                    TestItem testItem2 = testItem;
                    if (Intrinsics.areEqual(testItem2.getName(), TestConstantKt.ENCODER) && testItem2.getStage() == TestStage.this) {
                        break;
                    }
                }
                if (testItem == null) {
                    Intrinsics.throwNpe();
                }
                if (testItem.getStatus() != TestStatus.SUCCESS) {
                    Toast.makeText(AllTestItem.INSTANCE.getActivity(), "请先保证编码器测试成功", 1).show();
                    return;
                }
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) MotorTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.LOCATE_CAMERA, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addHolaItem$14
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) LocateCameraTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.LIDAR, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addHolaItem$15
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) LidarTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        if (((hdInterface == null || (machineInfo = hdInterface.getMachineInfo()) == null) ? null : machineInfo.getRGBDMode()) != MachineInfo.RGBDType.NODevice) {
            add(new TestItem(stage, TestConstantKt.RGBD, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addHolaItem$16
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    AllTestItem allTestItem = AllTestItem.INSTANCE;
                    TestStage testStage = TestStage.this;
                    Activity activity2 = allTestItem.getActivity();
                    if (activity2 == null) {
                        Intrinsics.throwNpe();
                    }
                    Intent intent = new Intent(activity2, (Class<?>) RgbdTestActivity.class);
                    intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                    Activity activity3 = allTestItem.getActivity();
                    if (activity3 == null) {
                        Intrinsics.throwNpe();
                    }
                    activity3.startActivity(intent);
                }
            }));
        }
        add(new TestItem(stage, TestConstantKt.ESP32, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addHolaItem$17
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                if (WifiUtil.INSTANCE.getMac() != null) {
                    AllTestItem allTestItem = AllTestItem.INSTANCE;
                    TestStage testStage = TestStage.this;
                    Activity activity2 = allTestItem.getActivity();
                    if (activity2 == null) {
                        Intrinsics.throwNpe();
                    }
                    Intent intent = new Intent(activity2, (Class<?>) ESP32TestActivity.class);
                    intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                    Activity activity3 = allTestItem.getActivity();
                    if (activity3 == null) {
                        Intrinsics.throwNpe();
                    }
                    activity3.startActivity(intent);
                    return;
                }
                Toast.makeText(AllTestItem.INSTANCE.getActivity(), "请先连网以获取mac地址", 1).show();
            }
        }));
        add(new TestItem(stage, TestConstantKt.Lora, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addHolaItem$18
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) LoraTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.CHARGE, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addHolaItem$19
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) ChargeTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.BUSINESS, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addHolaItem$20
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                ArrayList arrayList = new ArrayList();
                Iterator<TestItem> it = allTestItem.iterator();
                while (true) {
                    boolean z = true;
                    if (!it.hasNext()) {
                        break;
                    }
                    TestItem next = it.next();
                    TestItem testItem = next;
                    if (testItem.getStage() == TestStage.this && testItem.getStatus() != TestStatus.SUCCESS && !Intrinsics.areEqual(testItem.getName(), TestConstantKt.BUSINESS)) {
                        z = false;
                    }
                    if (!z) {
                        arrayList.add(next);
                    }
                }
                if (!arrayList.isEmpty()) {
                    Toast.makeText(AllTestItem.INSTANCE.getActivity(), "请先完成其他未测试项", 1).show();
                    return;
                }
                if (WifiUtil.INSTANCE.getMac() != null) {
                    Activity activity2 = AllTestItem.INSTANCE.getActivity();
                    if (activity2 == null) {
                        Intrinsics.throwNpe();
                    }
                    Intent intent = new Intent(activity2, (Class<?>) BusinessTestActivity.class);
                    intent.putExtra(AllTestItem.INSTANCE.getEXTER_TEST_STAGE_KEY(), TestStage.this.getStr());
                    Activity activity3 = AllTestItem.INSTANCE.getActivity();
                    if (activity3 != null) {
                        activity3.startActivityForResult(intent, 100);
                        return;
                    }
                    return;
                }
                Toast.makeText(AllTestItem.INSTANCE.getActivity(), "请先连网以获取mac地址", 1).show();
            }
        }));
    }

    public final void addNineTalesItem(final TestStage stage) {
        MachineInfo machineInfo;
        Intrinsics.checkParameterIsNotNull(stage, "stage");
        add(new TestItem(stage, TestConstantKt.HARDWARE_VERSION, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addNineTalesItem$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) HardwareVersionActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.TOUCH_PALNE, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addNineTalesItem$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) TouchPlaneTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.SCREEN, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addNineTalesItem$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) ScreenTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.AUDIO, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addNineTalesItem$4
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) AudioTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.SURFACE_LED, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addNineTalesItem$5
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) SurfaceLEDTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.SWITCH, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addNineTalesItem$6
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) SwitchTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.HUMAN_SENSOR, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addNineTalesItem$7
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) HumanSensorTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.AUTO_RECHARGE, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addNineTalesItem$8
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) AutoRechargeTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.WIFI, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addNineTalesItem$9
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                if (WifiUtil.INSTANCE.getMac() != null) {
                    WifiUtil wifiUtil = WifiUtil.INSTANCE;
                    Activity activity2 = AllTestItem.INSTANCE.getActivity();
                    if (activity2 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (wifiUtil.isNetworkAvailable(activity2)) {
                        Activity activity3 = AllTestItem.INSTANCE.getActivity();
                        if (activity3 == null) {
                            Intrinsics.throwNpe();
                        }
                        Intent intent = new Intent(activity3, (Class<?>) WifiTestActivity2.class);
                        intent.putExtra(AllTestItem.INSTANCE.getEXTER_TEST_STAGE_KEY(), TestStage.this.getStr());
                        Activity activity4 = AllTestItem.INSTANCE.getActivity();
                        if (activity4 != null) {
                            activity4.startActivityForResult(intent, 100);
                            return;
                        }
                        return;
                    }
                }
                Toast.makeText(AllTestItem.INSTANCE.getActivity(), "请先连接WIFI", 1).show();
            }
        }));
        add(new TestItem(stage, TestConstantKt.IMU, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addNineTalesItem$10
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) IMUTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.ENCODER, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addNineTalesItem$11
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                TestItem testItem;
                Iterator<TestItem> it = AllTestItem.INSTANCE.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        testItem = null;
                        break;
                    }
                    testItem = it.next();
                    TestItem testItem2 = testItem;
                    if (Intrinsics.areEqual(testItem2.getName(), TestConstantKt.IMU) && testItem2.getStage() == TestStage.this) {
                        break;
                    }
                }
                if (testItem == null) {
                    Intrinsics.throwNpe();
                }
                if (testItem.getStatus() != TestStatus.SUCCESS) {
                    Toast.makeText(AllTestItem.INSTANCE.getActivity(), "请先保证陀螺仪测试成功", 1).show();
                    return;
                }
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) EncoderTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.MOTOR, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addNineTalesItem$12
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                TestItem testItem;
                Iterator<TestItem> it = AllTestItem.INSTANCE.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        testItem = null;
                        break;
                    }
                    testItem = it.next();
                    TestItem testItem2 = testItem;
                    if (Intrinsics.areEqual(testItem2.getName(), TestConstantKt.ENCODER) && testItem2.getStage() == TestStage.this) {
                        break;
                    }
                }
                if (testItem == null) {
                    Intrinsics.throwNpe();
                }
                if (testItem.getStatus() != TestStatus.SUCCESS) {
                    Toast.makeText(AllTestItem.INSTANCE.getActivity(), "请先保证编码器测试成功", 1).show();
                    return;
                }
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) MotorTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.LIDAR, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addNineTalesItem$13
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) LidarTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        if (((hdInterface == null || (machineInfo = hdInterface.getMachineInfo()) == null) ? null : machineInfo.getRGBDMode()) != MachineInfo.RGBDType.NODevice) {
            add(new TestItem(stage, TestConstantKt.RGBD, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addNineTalesItem$14
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    AllTestItem allTestItem = AllTestItem.INSTANCE;
                    TestStage testStage = TestStage.this;
                    Activity activity2 = allTestItem.getActivity();
                    if (activity2 == null) {
                        Intrinsics.throwNpe();
                    }
                    Intent intent = new Intent(activity2, (Class<?>) RgbdTestActivity.class);
                    intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                    Activity activity3 = allTestItem.getActivity();
                    if (activity3 == null) {
                        Intrinsics.throwNpe();
                    }
                    activity3.startActivity(intent);
                }
            }));
        }
        add(new TestItem(stage, TestConstantKt.ESP32, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addNineTalesItem$15
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                if (WifiUtil.INSTANCE.getMac() != null) {
                    AllTestItem allTestItem = AllTestItem.INSTANCE;
                    TestStage testStage = TestStage.this;
                    Activity activity2 = allTestItem.getActivity();
                    if (activity2 == null) {
                        Intrinsics.throwNpe();
                    }
                    Intent intent = new Intent(activity2, (Class<?>) ESP32TestActivity.class);
                    intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                    Activity activity3 = allTestItem.getActivity();
                    if (activity3 == null) {
                        Intrinsics.throwNpe();
                    }
                    activity3.startActivity(intent);
                    return;
                }
                Toast.makeText(AllTestItem.INSTANCE.getActivity(), "请先连网以获取mac地址", 1).show();
            }
        }));
        add(new TestItem(stage, TestConstantKt.CHARGE, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addNineTalesItem$16
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) ChargeTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        Pdlog.m3273d(TAG, "雾化测试模块添加");
        add(new TestItem(stage, TestConstantKt.ATOMIZATIONMODULE, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addNineTalesItem$17
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) AtomizationModuleTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.DISINFECTION, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addNineTalesItem$18
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                TestItem testItem;
                Iterator<TestItem> it = AllTestItem.INSTANCE.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        testItem = null;
                        break;
                    }
                    testItem = it.next();
                    TestItem testItem2 = testItem;
                    if (Intrinsics.areEqual(testItem2.getName(), TestConstantKt.ATOMIZATIONMODULE) && testItem2.getStage() == TestStage.this) {
                        break;
                    }
                }
                if (testItem == null) {
                    Intrinsics.throwNpe();
                }
                if (testItem.getStatus() != TestStatus.SUCCESS) {
                    Toast.makeText(AllTestItem.INSTANCE.getActivity(), "请先保证雾化模块测试成功", 1).show();
                    return;
                }
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                TestStage testStage = TestStage.this;
                Activity activity2 = allTestItem.getActivity();
                if (activity2 == null) {
                    Intrinsics.throwNpe();
                }
                Intent intent = new Intent(activity2, (Class<?>) DisinfectionTestActivity.class);
                intent.putExtra(allTestItem.getEXTER_TEST_STAGE_KEY(), testStage.getStr());
                Activity activity3 = allTestItem.getActivity();
                if (activity3 == null) {
                    Intrinsics.throwNpe();
                }
                activity3.startActivity(intent);
            }
        }));
        add(new TestItem(stage, TestConstantKt.BUSINESS, TestStatus.UNTESTED, new Function0<Unit>() { // from class: com.pudutech.factory_test.test_pack.AllTestItem$addNineTalesItem$19
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                AllTestItem allTestItem = AllTestItem.INSTANCE;
                ArrayList arrayList = new ArrayList();
                Iterator<TestItem> it = allTestItem.iterator();
                while (true) {
                    boolean z = true;
                    if (!it.hasNext()) {
                        break;
                    }
                    TestItem next = it.next();
                    TestItem testItem = next;
                    if (testItem.getStage() == TestStage.this && testItem.getStatus() != TestStatus.SUCCESS && !Intrinsics.areEqual(testItem.getName(), TestConstantKt.BUSINESS)) {
                        z = false;
                    }
                    if (!z) {
                        arrayList.add(next);
                    }
                }
                if (!arrayList.isEmpty()) {
                    Toast.makeText(AllTestItem.INSTANCE.getActivity(), "请先完成其他未测试项", 1).show();
                    return;
                }
                if (WifiUtil.INSTANCE.getMac() != null) {
                    Activity activity2 = AllTestItem.INSTANCE.getActivity();
                    if (activity2 == null) {
                        Intrinsics.throwNpe();
                    }
                    Intent intent = new Intent(activity2, (Class<?>) BusinessTestActivity.class);
                    intent.putExtra(AllTestItem.INSTANCE.getEXTER_TEST_STAGE_KEY(), TestStage.this.getStr());
                    Activity activity3 = AllTestItem.INSTANCE.getActivity();
                    if (activity3 != null) {
                        activity3.startActivityForResult(intent, 100);
                        return;
                    }
                    return;
                }
                Toast.makeText(AllTestItem.INSTANCE.getActivity(), "请先连网以获取mac地址", 1).show();
            }
        }));
    }

    public final boolean isHLS() {
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        if (hdInterface == null) {
            Intrinsics.throwNpe();
        }
        ProductMachineType productType = hdInterface.getMachineInfo().getProductType();
        return (productType != null ? productType.getModel() : null) == MachineModel.Hls;
    }

    public final boolean isHLX() {
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        if (hdInterface == null) {
            Intrinsics.throwNpe();
        }
        ProductMachineType productType = hdInterface.getMachineInfo().getProductType();
        return (productType != null ? productType.getModel() : null) == MachineModel.Puductor;
    }

    public final boolean isBella() {
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        if (hdInterface == null) {
            Intrinsics.throwNpe();
        }
        ProductMachineType productType = hdInterface.getMachineInfo().getProductType();
        return (productType != null ? productType.getModel() : null) == MachineModel.BellaBot;
    }

    public final boolean isHola() {
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        if (hdInterface == null) {
            Intrinsics.throwNpe();
        }
        ProductMachineType productType = hdInterface.getMachineInfo().getProductType();
        return (productType != null ? productType.getModel() : null) == MachineModel.RecycleDog;
    }

    public final boolean isNineTales() {
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        if (hdInterface == null) {
            Intrinsics.throwNpe();
        }
        ProductMachineType productType = hdInterface.getMachineInfo().getProductType();
        return (productType != null ? productType.getModel() : null) == MachineModel.Ninetales;
    }

    public final boolean isPeanut() {
        HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
        if (hdInterface == null) {
            Intrinsics.throwNpe();
        }
        ProductMachineType productType = hdInterface.getMachineInfo().getProductType();
        return (productType != null ? productType.getModel() : null) == MachineModel.Peanut;
    }
}
