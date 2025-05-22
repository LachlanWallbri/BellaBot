package com.pudutech.freeinstall_ui.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.ParcelFileDescriptor;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.ext.util.CommonExtKt;
import com.pudutech.freeinstall_ui.AppContext;
import com.pudutech.freeinstall_ui.adapter.TableListItem;
import com.pudutech.freeinstall_ui.dialog.CommonDialog;
import com.pudutech.freeinstall_ui.view.NodeProgressBar;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.mircore.coreparcel.Destination;
import com.pudutech.mirsdk.mircore.coreparcel.DockerDetectResult;
import com.pudutech.mirsdk.mircore.coreparcel.MapData;
import com.pudutech.module_freeinstall_ui.C5362R;
import com.pudutech.opengl_draw.bean.Point;
import com.pudutech.opengl_draw.geometry.Quaternion;
import com.pudutech.opengl_draw.geometry.Transform;
import com.pudutech.opengl_draw.geometry.Vector3;
import com.pudutech.opengl_draw.layer.OccupancyGridLayer;
import com.pudutech.opengl_draw.layer.PointsLayer;
import com.pudutech.opengl_draw.layer.RobotLayer;
import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: Utils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/utils/Utils;", "", "()V", "Companion", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class Utils {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static double origin_x;
    private static double origin_y;
    private static double resolution;
    private static int size_x;
    private static int size_y;

    /* compiled from: Utils.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000Ä\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J*\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u000e\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001d2\b\u0010\u001f\u001a\u0004\u0018\u00010 J$\u0010!\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\"\u001a\u0004\u0018\u00010\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 J\u001e\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020)J&\u0010*\u001a\u00020$2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020,2\u000e\u0010.\u001a\n\u0012\u0004\u0012\u00020/\u0018\u00010\u001dJ\u000e\u00100\u001a\u0002012\u0006\u0010\"\u001a\u00020\u001eJ\u000e\u00102\u001a\u0002012\u0006\u00103\u001a\u000204J:\u00105\u001a\u0004\u0018\u00010 2\b\u00106\u001a\u0004\u0018\u0001072\u0006\u00108\u001a\u00020\u00102\u0006\u00109\u001a\u00020\u00102\u0006\u0010:\u001a\u00020\u00102\u0006\u0010;\u001a\u00020\u00102\u0006\u0010<\u001a\u00020)J:\u0010=\u001a\u0004\u0018\u00010 2\b\u00106\u001a\u0004\u0018\u0001072\u0006\u00108\u001a\u00020\u00102\u0006\u00109\u001a\u00020\u00102\u0006\u0010:\u001a\u00020\u00102\u0006\u0010;\u001a\u00020\u00102\u0006\u0010<\u001a\u00020)J\u001e\u0010>\u001a\u0012\u0012\u0004\u0012\u00020@0?j\b\u0012\u0004\u0012\u00020@`A2\u0006\u0010B\u001a\u00020\u0010J\u0016\u0010C\u001a\u00020\u00042\u0006\u0010D\u001a\u00020\u00102\u0006\u0010E\u001a\u00020\u0010J\"\u0010F\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010+\u001a\u00020,2\b\u0010\u001f\u001a\u0004\u0018\u00010 J \u0010G\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010+\u001a\u00020,2\u0006\u0010H\u001a\u00020IJ\u0012\u0010J\u001a\u0004\u0018\u00010K2\b\u0010L\u001a\u0004\u0018\u000107J \u0010M\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010+\u001a\u00020,2\u0006\u0010N\u001a\u00020,J\u000e\u0010O\u001a\u00020\u00192\u0006\u0010P\u001a\u00020QJ\"\u0010R\u001a\u00020\u00192\b\u0010S\u001a\u0004\u0018\u00010T2\b\u00106\u001a\u0004\u0018\u00010K2\u0006\u0010U\u001a\u00020VJ\\\u0010R\u001a\u00020\u00192\b\u0010S\u001a\u0004\u0018\u00010T2\b\u00106\u001a\u0004\u0018\u00010,2\u0006\u0010U\u001a\u00020V28\b\u0002\u0010W\u001a2\u0012\u0013\u0012\u00110\u0010¢\u0006\f\bY\u0012\b\b+\u0012\u0004\b\b(Z\u0012\u0013\u0012\u00110\u0010¢\u0006\f\bY\u0012\b\b+\u0012\u0004\b\b([\u0012\u0004\u0012\u00020\u00190XJ\u001c\u0010\\\u001a\u00020\u00192\b\u0010]\u001a\u0004\u0018\u00010^2\b\u00106\u001a\u0004\u0018\u00010&H\u0007J\u0012\u0010_\u001a\u0004\u0018\u00010&2\b\u0010`\u001a\u0004\u0018\u00010aJ\u0012\u0010b\u001a\u0004\u0018\u00010a2\b\u0010%\u001a\u0004\u0018\u00010&R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0012\"\u0004\b\u0017\u0010\u0014¨\u0006c"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/utils/Utils$Companion;", "", "()V", "origin_x", "", "getOrigin_x", "()D", "setOrigin_x", "(D)V", "origin_y", "getOrigin_y", "setOrigin_y", "resolution", "getResolution", "setResolution", "size_x", "", "getSize_x", "()I", "setSize_x", "(I)V", "size_y", "getSize_y", "setSize_y", "addAllPoint", "", "pointLayer", "Lcom/pudutech/opengl_draw/layer/PointsLayer;", "list", "", "Lcom/pudutech/mirsdk/mircore/coreparcel/Destination;", "bitmap", "Landroid/graphics/Bitmap;", "addPoint", "destination", "calculateDistance", "", "vector3d", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "lastVector3d", "l", "", "checkPointNameRepeat", "name", "", "mode", "listItems", "Lcom/pudutech/freeinstall_ui/adapter/TableListItem;", "destinationToTransform", "Lcom/pudutech/opengl_draw/geometry/Transform;", "dockerDetectResultToTransform", "dockerDetectResult", "Lcom/pudutech/mirsdk/mircore/coreparcel/DockerDetectResult;", "getBitMapFromFace", "p0", "Landroid/os/ParcelFileDescriptor;", "p1", "p2", "p3", "p4", "p5", "getBitMapFromMarker", "getNodeData", "Ljava/util/ArrayList;", "Lcom/pudutech/freeinstall_ui/view/NodeProgressBar$Node;", "Lkotlin/collections/ArrayList;", "step", "getScreenScale", "mapWith", "mapHeight", "reBitmapPoint", "reColor", TypedValues.Custom.S_COLOR, "Lcom/pudutech/opengl_draw/base/Color;", "readParcelFileDescriptor", "Lcom/pudutech/mirsdk/mircore/coreparcel/MapData;", "parcelFileDescriptor", "renamePoint", "newName", "showBackDialog", "context", "Landroid/app/Activity;", "updateMap", "mapLayer", "Lcom/pudutech/opengl_draw/layer/OccupancyGridLayer;", "occupancyOneListener", "Lcom/pudutech/opengl_draw/layer/OccupancyGridLayer$OccupancyOneListener;", "mapSuccessListener", "Lkotlin/Function2;", "Lkotlin/ParameterName;", JsonPOJOBuilder.DEFAULT_WITH_PREFIX, "height", "updateRobotPosition", "robotLayer", "Lcom/pudutech/opengl_draw/layer/RobotLayer;", "vector3ToVector3d", "vector3", "Lcom/pudutech/opengl_draw/geometry/Vector3;", "vector3dToVector3", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Vector3d vector3ToVector3d(Vector3 vector3) {
            if (vector3 == null) {
                return null;
            }
            Companion companion = this;
            double d = 2;
            return new Vector3d(((companion.getSize_x() * companion.getResolution()) / d) + vector3.getX() + companion.getOrigin_x(), (((companion.getSize_y() * companion.getResolution()) - vector3.getY()) + companion.getOrigin_y()) - ((companion.getSize_y() * companion.getResolution()) / d), 0.0d);
        }

        public final Vector3 vector3dToVector3(Vector3d vector3d) {
            if (vector3d == null) {
                return null;
            }
            Companion companion = this;
            double d = 2;
            return new Vector3((vector3d.getX() - companion.getOrigin_x()) - ((companion.getSize_x() * companion.getResolution()) / d), (((companion.getSize_y() * companion.getResolution()) - vector3d.getY()) + companion.getOrigin_y()) - ((companion.getSize_y() * companion.getResolution()) / d), 0.0d);
        }

        public final Transform destinationToTransform(Destination destination) {
            Intrinsics.checkParameterIsNotNull(destination, "destination");
            return new Transform(vector3dToVector3(destination.getVector()), Quaternion.identity().scale(destination.getVector().getZ()));
        }

        public final Transform dockerDetectResultToTransform(DockerDetectResult dockerDetectResult) {
            Intrinsics.checkParameterIsNotNull(dockerDetectResult, "dockerDetectResult");
            return new Transform(vector3dToVector3(new Vector3d(dockerDetectResult.getX(), dockerDetectResult.getY(), dockerDetectResult.getTheta())), Quaternion.identity().scale(dockerDetectResult.getTheta()));
        }

        public final Bitmap getBitMapFromFace(ParcelFileDescriptor p0, int p1, int p2, int p3, int p4, long p5) {
            if (p0 == null) {
                return null;
            }
            FileInputStream fileInputStream = new FileInputStream(p0.getFileDescriptor());
            byte[] bArr = new byte[p3];
            try {
                int read = fileInputStream.read(bArr);
                if (read <= 12) {
                    Pdlog.m3276v("getBitMapFromFace--error", " length:" + read);
                    return null;
                }
                Pdlog.m3276v("getBitMapFromFace", "on rgb camera frame width:" + p2 + " height:" + p1);
                int i = p1 * p2;
                int[] iArr = new int[i];
                for (int i2 = 0; i2 < i; i2++) {
                    int i3 = i2 * 3;
                    iArr[i2] = Color.rgb(UByte.m4528constructorimpl(bArr[i3 + 2 + 12]) & 255, UByte.m4528constructorimpl(bArr[i3 + 1 + 12]) & 255, UByte.m4528constructorimpl(bArr[i3 + 12]) & 255);
                }
                Bitmap createBitmap = Bitmap.createBitmap(iArr, p2, p1, Bitmap.Config.RGB_565);
                Matrix matrix = new Matrix();
                Intrinsics.checkExpressionValueIsNotNull(createBitmap, "createBitmap");
                matrix.setRotate(90.0f, (createBitmap.getWidth() / 2) * 1.0f, (createBitmap.getHeight() / 2) * 1.0f);
                Bitmap createBitmap2 = Bitmap.createBitmap(createBitmap, 0, 0, createBitmap.getWidth(), createBitmap.getHeight(), matrix, true);
                createBitmap.recycle();
                return createBitmap2;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                fileInputStream.close();
                p0.close();
            }
        }

        public final Bitmap getBitMapFromMarker(ParcelFileDescriptor p0, int p1, int p2, int p3, int p4, long p5) {
            if (p0 == null) {
                return null;
            }
            FileInputStream fileInputStream = new FileInputStream(p0.getFileDescriptor());
            try {
                int read = fileInputStream.read(new byte[p3]);
                Pdlog.m3273d("getBitMapFromMarker", "length is --" + read);
                if (read <= 12) {
                    Pdlog.m3276v("getBitMapFromMarker--error", " length:" + read);
                    return null;
                }
                Pdlog.m3276v("getBitMapFromMarker", "on marker camera frame width:" + p2 + " height:" + p1);
                int i = p1 * p2;
                int[] iArr = new int[i];
                for (int i2 = 0; i2 < i; i2++) {
                    long m4528constructorimpl = UByte.m4528constructorimpl(r12[i2 + 12]) & 255;
                    iArr[i2] = UInt.m4595constructorimpl(UInt.m4595constructorimpl((int) m4528constructorimpl) | UInt.m4595constructorimpl(UInt.m4595constructorimpl((-16777216) | UInt.m4595constructorimpl((int) (m4528constructorimpl << 16))) | UInt.m4595constructorimpl((int) (m4528constructorimpl << 8))));
                }
                Bitmap createBitmap = Bitmap.createBitmap(iArr, p2, p1, Bitmap.Config.RGB_565);
                Matrix matrix = new Matrix();
                Intrinsics.checkExpressionValueIsNotNull(createBitmap, "createBitmap");
                matrix.setRotate(90.0f, (createBitmap.getWidth() / 2) * 1.0f, (createBitmap.getHeight() / 2) * 1.0f);
                Bitmap createBitmap2 = Bitmap.createBitmap(createBitmap, 0, 0, createBitmap.getWidth(), createBitmap.getHeight(), matrix, true);
                createBitmap.recycle();
                return createBitmap2;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                fileInputStream.close();
                p0.close();
            }
        }

        public final MapData readParcelFileDescriptor(ParcelFileDescriptor parcelFileDescriptor) {
            if (parcelFileDescriptor == null) {
                return null;
            }
            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
            if (!fileDescriptor.valid()) {
                return null;
            }
            MapData mapData = new MapData();
            FileInputStream fileInputStream = new FileInputStream(fileDescriptor);
            byte[] bArr = new byte[8];
            byte[] bArr2 = new byte[8];
            byte[] bArr3 = new byte[8];
            byte[] bArr4 = new byte[4];
            byte[] bArr5 = new byte[4];
            fileInputStream.read(bArr);
            fileInputStream.read(bArr2);
            fileInputStream.read(bArr3);
            fileInputStream.read(bArr4);
            fileInputStream.read(bArr5);
            mapData.setOrigin_x(ByteUtil.getDouble(bArr));
            mapData.setOrigin_y(ByteUtil.getDouble(bArr2));
            mapData.setScale(ByteUtil.getDouble(bArr3));
            mapData.setSize_x(ByteUtil.getInt(bArr4));
            mapData.setSize_y(ByteUtil.getInt(bArr5));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr6 = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr6);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr6, 0, read);
                    byteArrayOutputStream.flush();
                } else {
                    byteArrayOutputStream.close();
                    fileInputStream.close();
                    parcelFileDescriptor.close();
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    Intrinsics.checkExpressionValueIsNotNull(byteArray, "out.toByteArray()");
                    mapData.setData(byteArray);
                    return mapData;
                }
            }
        }

        public final void updateMap(OccupancyGridLayer mapLayer, MapData p0, OccupancyGridLayer.OccupancyOneListener occupancyOneListener) {
            Intrinsics.checkParameterIsNotNull(occupancyOneListener, "occupancyOneListener");
            if (p0 == null) {
                return;
            }
            Companion companion = this;
            companion.setOrigin_x(p0.getOrigin_x());
            companion.setOrigin_y(p0.getOrigin_y());
            companion.setSize_y(p0.getSize_y());
            companion.setSize_x(p0.getSize_x());
            companion.setResolution(p0.getScale());
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new Utils$Companion$updateMap$1(p0, mapLayer, occupancyOneListener, null), 3, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void updateMap$default(Companion companion, OccupancyGridLayer occupancyGridLayer, String str, OccupancyGridLayer.OccupancyOneListener occupancyOneListener, Function2 function2, int i, Object obj) {
            if ((i & 8) != 0) {
                function2 = new Function2<Integer, Integer, Unit>() { // from class: com.pudutech.freeinstall_ui.utils.Utils$Companion$updateMap$2
                    public final void invoke(int i2, int i3) {
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Integer num, Integer num2) {
                        invoke(num.intValue(), num2.intValue());
                        return Unit.INSTANCE;
                    }
                };
            }
            companion.updateMap(occupancyGridLayer, str, occupancyOneListener, function2);
        }

        public final void updateMap(OccupancyGridLayer mapLayer, String p0, OccupancyGridLayer.OccupancyOneListener occupancyOneListener, Function2<? super Integer, ? super Integer, Unit> mapSuccessListener) {
            Object obj;
            Intrinsics.checkParameterIsNotNull(occupancyOneListener, "occupancyOneListener");
            Intrinsics.checkParameterIsNotNull(mapSuccessListener, "mapSuccessListener");
            if (p0 == null) {
                return;
            }
            Bitmap mapPic = MapFileUtils.INSTANCE.getMapPic(p0);
            HashMap<String, Object> readYaml = MapFileUtils.INSTANCE.readYaml(p0);
            if (readYaml == null || (obj = readYaml.get("origin")) == null) {
                return;
            }
            Intrinsics.checkExpressionValueIsNotNull(obj, "readYaml[\"origin\"] ?: return");
            if (obj != null) {
                List list = (List) obj;
                if (list.size() < 3) {
                    return;
                }
                Companion companion = this;
                companion.setOrigin_x(((Number) list.get(0)).doubleValue());
                companion.setOrigin_y(((Number) list.get(1)).doubleValue());
                companion.setSize_y(mapPic != null ? mapPic.getHeight() : 0);
                companion.setSize_x(mapPic != null ? mapPic.getWidth() : 0);
                companion.setResolution(0.05d);
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new Utils$Companion$updateMap$3(list, mapPic, mapLayer, occupancyOneListener, null), 3, null);
                mapSuccessListener.invoke(Integer.valueOf(mapPic != null ? mapPic.getWidth() : 0), Integer.valueOf(mapPic != null ? mapPic.getHeight() : 0));
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<kotlin.Double>");
        }

        public final double getOrigin_x() {
            return Utils.origin_x;
        }

        public final void setOrigin_x(double d) {
            Utils.origin_x = d;
        }

        public final int getSize_y() {
            return Utils.size_y;
        }

        public final void setSize_y(int i) {
            Utils.size_y = i;
        }

        public final double getOrigin_y() {
            return Utils.origin_y;
        }

        public final void setOrigin_y(double d) {
            Utils.origin_y = d;
        }

        public final int getSize_x() {
            return Utils.size_x;
        }

        public final void setSize_x(int i) {
            Utils.size_x = i;
        }

        public final double getResolution() {
            return Utils.resolution;
        }

        public final void setResolution(double d) {
            Utils.resolution = d;
        }

        public final void updateRobotPosition(RobotLayer robotLayer, Vector3d p0) {
            if (p0 == null) {
                return;
            }
            Pdlog.m3273d("RobotPosition", "x " + p0.getX() + " y " + p0.getY() + " z " + p0.getZ());
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new Utils$Companion$updateRobotPosition$1(p0, robotLayer, null), 3, null);
        }

        public final boolean calculateDistance(Vector3d vector3d, Vector3d lastVector3d, long l) {
            Intrinsics.checkParameterIsNotNull(vector3d, "vector3d");
            Intrinsics.checkParameterIsNotNull(lastVector3d, "lastVector3d");
            double d = 2;
            return Math.sqrt(Math.pow(vector3d.getX() - lastVector3d.getX(), d) + Math.pow(vector3d.getY() - lastVector3d.getY(), d)) * ((double) 100) >= ((double) l);
        }

        public final void reColor(PointsLayer pointLayer, String name, com.pudutech.opengl_draw.base.Color color) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(color, "color");
            if (pointLayer != null) {
                pointLayer.reColor(name, color);
            }
        }

        public final void renamePoint(PointsLayer pointLayer, String name, String newName) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(newName, "newName");
            if (pointLayer != null) {
                pointLayer.reName(name, newName);
            }
        }

        public final void reBitmapPoint(PointsLayer pointLayer, String name, Bitmap bitmap) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            if (bitmap == null || pointLayer == null) {
                return;
            }
            pointLayer.reBitmap(name, bitmap);
        }

        public final void addPoint(PointsLayer pointLayer, Destination destination, Bitmap bitmap) {
            if ((destination != null ? destination.getVector() : null) == null || pointLayer == null) {
                return;
            }
            pointLayer.add(new Point(destination.getName(), destinationToTransform(destination), bitmap));
        }

        public final void addAllPoint(PointsLayer pointLayer, List<Destination> list, Bitmap bitmap) {
            if (list == null || list.isEmpty()) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (Destination destination : list) {
                arrayList.add(new Point(destination.getName(), Utils.INSTANCE.destinationToTransform(destination), bitmap));
            }
            if (pointLayer != null) {
                pointLayer.addAll(arrayList);
            }
        }

        public final double getScreenScale(int mapWith, int mapHeight) {
            int screenWidth = CommonExtKt.getScreenWidth(AppContext.INSTANCE.getContext()) / 5;
            int screenHeight = CommonExtKt.getScreenHeight(AppContext.INSTANCE.getContext()) / 5;
            Pdlog.m3273d("getScreenScale", "screenWidth :" + screenWidth + "screenHeight :" + screenHeight + "mapWith :" + mapWith + "mapHeight :" + mapHeight);
            if (screenWidth < mapHeight || screenHeight < mapWith) {
                return Math.min((screenWidth * 1.0d) / mapHeight, (screenHeight * 1.0d) / mapWith);
            }
            return 1.0d;
        }

        public final boolean checkPointNameRepeat(String name, String mode, List<TableListItem> listItems) {
            Object obj;
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(mode, "mode");
            List<TableListItem> addTable = SpDataUtils.INSTANCE.getAddTable();
            List<TableListItem> addDoorPoint = SpDataUtils.INSTANCE.getAddDoorPoint();
            List<TableListItem> addMeal = SpDataUtils.INSTANCE.getAddMeal();
            List<TableListItem> addStation = SpDataUtils.INSTANCE.getAddStation();
            ArrayList arrayList = new ArrayList();
            switch (mode.hashCode()) {
                case -793201736:
                    if (mode.equals("parking")) {
                        if (addTable != null) {
                            Iterator<T> it = addTable.iterator();
                            while (it.hasNext()) {
                                arrayList.add(((TableListItem) it.next()).getDestination());
                            }
                        }
                        if (addDoorPoint != null) {
                            Iterator<T> it2 = addDoorPoint.iterator();
                            while (it2.hasNext()) {
                                arrayList.add(((TableListItem) it2.next()).getDestination());
                            }
                        }
                        if (addMeal != null) {
                            Iterator<T> it3 = addMeal.iterator();
                            while (it3.hasNext()) {
                                arrayList.add(((TableListItem) it3.next()).getDestination());
                            }
                        }
                        if (listItems != null) {
                            Iterator<T> it4 = listItems.iterator();
                            while (it4.hasNext()) {
                                arrayList.add(((TableListItem) it4.next()).getDestination());
                            }
                            break;
                        }
                    }
                    break;
                case 110115790:
                    if (mode.equals(Constants.POINT_TYPE_TABLE)) {
                        if (listItems != null) {
                            Iterator<T> it5 = listItems.iterator();
                            while (it5.hasNext()) {
                                arrayList.add(((TableListItem) it5.next()).getDestination());
                            }
                        }
                        if (addDoorPoint != null) {
                            Iterator<T> it6 = addDoorPoint.iterator();
                            while (it6.hasNext()) {
                                arrayList.add(((TableListItem) it6.next()).getDestination());
                            }
                        }
                        if (addMeal != null) {
                            Iterator<T> it7 = addMeal.iterator();
                            while (it7.hasNext()) {
                                arrayList.add(((TableListItem) it7.next()).getDestination());
                            }
                        }
                        if (addStation != null) {
                            Iterator<T> it8 = addStation.iterator();
                            while (it8.hasNext()) {
                                arrayList.add(((TableListItem) it8.next()).getDestination());
                            }
                            break;
                        }
                    }
                    break;
                case 111581111:
                    if (mode.equals(Constants.POINT_TYPE_DOOR)) {
                        if (addTable != null) {
                            Iterator<T> it9 = addTable.iterator();
                            while (it9.hasNext()) {
                                arrayList.add(((TableListItem) it9.next()).getDestination());
                            }
                        }
                        if (listItems != null) {
                            Iterator<T> it10 = listItems.iterator();
                            while (it10.hasNext()) {
                                arrayList.add(((TableListItem) it10.next()).getDestination());
                            }
                        }
                        if (addMeal != null) {
                            Iterator<T> it11 = addMeal.iterator();
                            while (it11.hasNext()) {
                                arrayList.add(((TableListItem) it11.next()).getDestination());
                            }
                        }
                        if (addStation != null) {
                            Iterator<T> it12 = addStation.iterator();
                            while (it12.hasNext()) {
                                arrayList.add(((TableListItem) it12.next()).getDestination());
                            }
                            break;
                        }
                    }
                    break;
                case 1710315603:
                    if (mode.equals("dining_outlet")) {
                        if (addTable != null) {
                            Iterator<T> it13 = addTable.iterator();
                            while (it13.hasNext()) {
                                arrayList.add(((TableListItem) it13.next()).getDestination());
                            }
                        }
                        if (addDoorPoint != null) {
                            Iterator<T> it14 = addDoorPoint.iterator();
                            while (it14.hasNext()) {
                                arrayList.add(((TableListItem) it14.next()).getDestination());
                            }
                        }
                        if (listItems != null) {
                            Iterator<T> it15 = listItems.iterator();
                            while (it15.hasNext()) {
                                arrayList.add(((TableListItem) it15.next()).getDestination());
                            }
                        }
                        if (addStation != null) {
                            Iterator<T> it16 = addStation.iterator();
                            while (it16.hasNext()) {
                                arrayList.add(((TableListItem) it16.next()).getDestination());
                            }
                            break;
                        }
                    }
                    break;
            }
            Iterator it17 = arrayList.iterator();
            while (true) {
                if (it17.hasNext()) {
                    obj = it17.next();
                    if (Intrinsics.areEqual(name, ((Destination) obj).getName())) {
                    }
                } else {
                    obj = null;
                }
            }
            return ((Destination) obj) != null;
        }

        public final void showBackDialog(final Activity context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            CommonDialog.Builder builder = new CommonDialog.Builder(context);
            String string = context.getString(C5362R.string.tips);
            Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.string.tips)");
            CommonDialog.Builder title = builder.setTitle(string);
            String string2 = context.getString(C5362R.string.give_up_modify);
            Intrinsics.checkExpressionValueIsNotNull(string2, "context.getString(R.string.give_up_modify)");
            CommonDialog.Builder minContent = title.setMinContent(string2);
            String string3 = context.getString(C5362R.string.yes);
            Intrinsics.checkExpressionValueIsNotNull(string3, "context.getString(R.string.yes)");
            CommonDialog.Builder btRight = minContent.setBtRight(string3);
            String string4 = context.getString(C5362R.string.f6652no);
            Intrinsics.checkExpressionValueIsNotNull(string4, "context.getString(R.string.no)");
            final CommonDialog create = btRight.setBtLeft(string4).setClose(false).create();
            String string5 = context.getString(C5362R.string.f6652no);
            Intrinsics.checkExpressionValueIsNotNull(string5, "context.getString(R.string.no)");
            create.setBtLeft(string5, CommonDialog.BtBg.RED, context.getColor(C5362R.color.white));
            create.setBtLeftClick(new Function0<Unit>() { // from class: com.pudutech.freeinstall_ui.utils.Utils$Companion$showBackDialog$1$1
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
                    CommonDialog.this.dismiss();
                }
            });
            create.setBtRightClick(new Function0<Unit>() { // from class: com.pudutech.freeinstall_ui.utils.Utils$Companion$showBackDialog$$inlined$let$lambda$1
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

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    CommonDialog.this.dismiss();
                    context.finish();
                }
            });
            create.show();
        }

        public final ArrayList<NodeProgressBar.Node> getNodeData(int step) {
            ArrayList<NodeProgressBar.Node> arrayList = new ArrayList<>();
            int i = 1;
            while (i <= 5) {
                NodeProgressBar.Node node = new NodeProgressBar.Node();
                node.nodeState = i == step ? 2 : 1;
                node.nodeAfterLineState = i < step ? 0 : 1;
                if (i == 1) {
                    node.bottomTxt = AppContext.INSTANCE.getContext().getString(C5362R.string.start_create_map);
                } else if (i == 2) {
                    node.bottomTxt = AppContext.INSTANCE.getContext().getString(C5362R.string.set_function);
                } else if (i == 3) {
                    node.bottomTxt = AppContext.INSTANCE.getContext().getString(C5362R.string.set_virtual_wall);
                } else if (i == 4) {
                    node.bottomTxt = AppContext.INSTANCE.getContext().getString(C5362R.string.set_double_path);
                } else if (i == 5) {
                    node.bottomTxt = AppContext.INSTANCE.getContext().getString(C5362R.string.complete);
                }
                arrayList.add(node);
                i++;
            }
            return arrayList;
        }
    }
}
