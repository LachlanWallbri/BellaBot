package com.pudutech.mirsdk.map;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.gson.Gson;
import com.pudutech.base.CommonKt;
import com.pudutech.base.Pdlog;
import com.pudutech.freeinstall_ui.utils.Constants;
import com.pudutech.mirsdk.compat.topo.MapElement;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import com.pudutech.mirsdk.map.elements.AccessControlPoint;
import com.pudutech.mirsdk.map.elements.ChargingPile;
import com.pudutech.mirsdk.map.elements.CircleMode;
import com.pudutech.mirsdk.map.elements.ElevatorSource;
import com.pudutech.mirsdk.map.elements.ElevatorWaiter;
import com.pudutech.mirsdk.map.elements.FireFoxGateSource;
import com.pudutech.mirsdk.map.elements.Node;
import com.pudutech.mirsdk.map.elements.Segment;
import com.pudutech.mirsdk.map.elements.Source;
import com.pudutech.mirsdk.map.elements.Track;
import com.pudutech.mirsdk.map.elements.ZonesData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MapDecode.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000¬\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0013\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0007\u0018\u0000 v2\u00020\u0001:\u0001vB\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010Z\u001a\u00020[2\u0006\u0010\\\u001a\u00020\u0016J\u0016\u0010]\u001a\u00020[2\u0006\u0010^\u001a\u00020\u00042\u0006\u0010_\u001a\u00020\tJ&\u0010`\u001a\u00020[2\u0006\u0010_\u001a\u00020\t2\u0006\u0010a\u001a\u00020\u00042\u0006\u0010^\u001a\u00020\u00042\u0006\u0010b\u001a\u00020\u0004J\u000e\u0010c\u001a\u00020[2\u0006\u0010d\u001a\u00020\u0004J\u000e\u0010c\u001a\u00020[2\u0006\u0010e\u001a\u00020fJ\u0016\u0010g\u001a\u0012\u0012\u0004\u0012\u00020h0\u0006j\b\u0012\u0004\u0012\u00020h`\u0007J\u0016\u0010i\u001a\u0012\u0012\u0004\u0012\u00020j0\u0006j\b\u0012\u0004\u0012\u00020j`\u0007J\u0016\u0010k\u001a\u0012\u0012\u0004\u0012\u00020l0\u0006j\b\u0012\u0004\u0012\u00020l`\u0007J?\u0010m\u001a\u00020[2\u0006\u0010n\u001a\u00020\u00042\u000e\u0010o\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010p2\u0006\u0010^\u001a\u00020\u00042\b\u0010q\u001a\u0004\u0018\u00010\u000b2\b\u0010r\u001a\u0004\u0018\u00010\u0019¢\u0006\u0002\u0010sJ\u0006\u0010t\u001a\u00020\u0004J\u0006\u0010u\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0006j\b\u0012\u0004\u0012\u00020\u0004`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0006j\b\u0012\u0004\u0012\u00020\t`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\u0006j\b\u0012\u0004\u0012\u00020\u000b`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\r0\u0006j\b\u0012\u0004\u0012\u00020\r`\u00078F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0006j\b\u0012\u0004\u0012\u00020\u0004`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\u0006j\b\u0012\u0004\u0012\u00020\u000b`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0006j\b\u0012\u0004\u0012\u00020\u0004`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0006j\b\u0012\u0004\u0012\u00020\u0004`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0006j\b\u0012\u0004\u0012\u00020\t`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0006j\b\u0012\u0004\u0012\u00020\u0016`\u00078F¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u001e\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u00190\u0006j\b\u0012\u0004\u0012\u00020\u0019`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010\u001a\u001a\u0012\u0012\u0004\u0012\u00020\u001b0\u0006j\b\u0012\u0004\u0012\u00020\u001b`\u00078F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u000fR\u001e\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0006j\b\u0012\u0004\u0012\u00020\t`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u001e\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0006j\b\u0012\u0004\u0012\u00020\u0004`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0006j\b\u0012\u0004\u0012\u00020\t`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010 \u001a\u0012\u0012\u0004\u0012\u00020!0\u0006j\b\u0012\u0004\u0012\u00020!`\u00078F¢\u0006\u0006\u001a\u0004\b\"\u0010\u000fR\u001e\u0010#\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0006j\b\u0012\u0004\u0012\u00020\u0004`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010$\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0006j\b\u0012\u0004\u0012\u00020\t`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010%\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0006j\b\u0012\u0004\u0012\u00020\u0004`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010&\u001a\u0012\u0012\u0004\u0012\u00020'0\u0006j\b\u0012\u0004\u0012\u00020'`\u00078F¢\u0006\u0006\u001a\u0004\b(\u0010\u000fR\u001d\u0010)\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020+0*¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u001a\u0010.\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R!\u00103\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0006j\b\u0012\u0004\u0012\u00020\u0016`\u0007¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u000fR\u001e\u00105\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0006j\b\u0012\u0004\u0012\u00020\u0004`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u00106\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0006j\b\u0012\u0004\u0012\u00020\t`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u00107\u001a\u0012\u0012\u0004\u0012\u00020\u00190\u0006j\b\u0012\u0004\u0012\u00020\u0019`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u00108\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0006j\b\u0012\u0004\u0012\u00020\t`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u00109\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0006j\b\u0012\u0004\u0012\u00020\u0004`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010:\u001a\u0012\u0012\u0004\u0012\u00020;0\u0006j\b\u0012\u0004\u0012\u00020;`\u00078F¢\u0006\u0006\u001a\u0004\b<\u0010\u000fR\u001e\u0010=\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0006j\b\u0012\u0004\u0012\u00020\t`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010>\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\u0006j\b\u0012\u0004\u0012\u00020\u000b`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010?\u001a\u0012\u0012\u0004\u0012\u00020@0\u0006j\b\u0012\u0004\u0012\u00020@`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010A\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0006j\n\u0012\u0006\u0012\u0004\u0018\u00010\u0004`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010B\u001a\u0012\u0012\u0004\u0012\u00020@0\u0006j\b\u0012\u0004\u0012\u00020@`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010C\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0006j\b\u0012\u0004\u0012\u00020\u0004`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010D\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0006j\n\u0012\u0006\u0012\u0004\u0018\u00010\u0004`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010E\u001a\u0012\u0012\u0004\u0012\u00020\u00190\u0006j\b\u0012\u0004\u0012\u00020\u0019`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010F\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0006j\b\u0012\u0004\u0012\u00020\t`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010G\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0006j\b\u0012\u0004\u0012\u00020\t`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010H\u001a\b\u0012\u0004\u0012\u00020\t0IX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010J\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\bK\u0010LR\u001e\u0010M\u001a\u0012\u0012\u0004\u0012\u00020\u00190\u0006j\b\u0012\u0004\u0012\u00020\u0019`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010N\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\u0006j\b\u0012\u0004\u0012\u00020\u000b`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010O\u001a\b\u0012\u0004\u0012\u00020\u000b0IX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010P\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\bQ\u0010LR\u001e\u0010R\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\u0006j\b\u0012\u0004\u0012\u00020\u000b`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010S\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0006j\b\u0012\u0004\u0012\u00020\t`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010T\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0006j\b\u0012\u0004\u0012\u00020\t`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010U\u001a\u0012\u0012\u0004\u0012\u00020V0\u0006j\b\u0012\u0004\u0012\u00020V`\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bW\u0010\u000f\"\u0004\bX\u0010Y¨\u0006w"}, m3961d2 = {"Lcom/pudutech/mirsdk/map/MapDecode;", "", "()V", "TAG", "", "accessControlIDs", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "accessControlPose", "", "accessControlSpendTime", "", "accessPoints", "Lcom/pudutech/mirsdk/map/elements/AccessControlPoint;", "getAccessPoints", "()Ljava/util/ArrayList;", "chargingMacs", "chargingPileAlignDist", "chargingPileGroups", "chargingPileIDs", "chargingPilePose", "chargingPiles", "Lcom/pudutech/mirsdk/map/elements/ChargingPile;", "getChargingPiles", "circleID", "", "circleModes", "Lcom/pudutech/mirsdk/map/elements/CircleMode;", "getCircleModes", "circleNodes", "eleWaiterIDs", "eleWaiterPose", "elevWaiters", "Lcom/pudutech/mirsdk/map/elements/ElevatorWaiter;", "getElevWaiters", "elevatorIDs", "elevatorPose", "elevatorWaiter", "elevators", "Lcom/pudutech/mirsdk/map/elements/ElevatorSource;", "getElevators", "fireFoxGates", "Ljava/util/HashMap;", "Lcom/pudutech/mirsdk/map/elements/FireFoxGateSource;", "getFireFoxGates", "()Ljava/util/HashMap;", "jsonMap", "getJsonMap", "()Ljava/lang/String;", "setJsonMap", "(Ljava/lang/String;)V", "newChargingPiles", "getNewChargingPiles", "newRelocateID", "newRelocatePose", "nodeID", "nodes", "relocateID", "relocatePoints", "Lcom/pudutech/mirsdk/map/RelocatePoint;", "getRelocatePoints", "relocatePose", "sourceDir", "sourceDoubleDir", "", "sourceGroup", "sourceHighPrecision", "sourceId", "sourceMode", "sourceSortWeight", "sources", TypedValues.Attributes.S_TARGET, "track", "Ljava/util/Vector;", "trackArray", "getTrackArray", "()[D", "trackId", "trackMaxSpeed", "trackWeight", "trackWeightArray", "getTrackWeightArray", "trackWidth", "virtualWall", "wall", "zonesDataList", "Lcom/pudutech/mirsdk/map/elements/ZonesData;", "getZonesDataList", "setZonesDataList", "(Ljava/util/ArrayList;)V", "addCharge", "", "pile", "addRelocatePose", "id", "pose", "addSource", "mode", MapElement.Key.GROUP, "decode", "string", "jsonObject", "Lorg/json/JSONObject;", "getNode", "Lcom/pudutech/mirsdk/map/elements/Node;", "getSource", "Lcom/pudutech/mirsdk/map/elements/Source;", "getTrack", "Lcom/pudutech/mirsdk/map/elements/Track;", "receiveAreaData", "type", "vertices", "", "maxSpeed", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/String;[[DLjava/lang/String;Ljava/lang/Double;Ljava/lang/Integer;)V", "toChargePileJsonMap", "toJsonMap", "Companion", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MapDecode {
    private static final Gson gson = new Gson();
    private final String TAG = "MapDecode";
    private final Vector<Double> trackWeight = new Vector<>();
    private final Vector<double[]> track = new Vector<>();
    private final ArrayList<Integer> trackId = new ArrayList<>();
    private final ArrayList<double[]> target = new ArrayList<>();
    private final ArrayList<double[]> sources = new ArrayList<>();
    private final ArrayList<String> sourceId = new ArrayList<>();
    private final ArrayList<Double> sourceDir = new ArrayList<>();
    private final ArrayList<Boolean> sourceDoubleDir = new ArrayList<>();
    private final ArrayList<String> sourceMode = new ArrayList<>();
    private final ArrayList<String> sourceGroup = new ArrayList<>();
    private final ArrayList<Integer> sourceSortWeight = new ArrayList<>();
    private final ArrayList<Double> trackMaxSpeed = new ArrayList<>();
    private final ArrayList<Double> trackWidth = new ArrayList<>();
    private final ArrayList<double[]> wall = new ArrayList<>();
    private final ArrayList<double[]> virtualWall = new ArrayList<>();
    private final ArrayList<Integer> nodeID = new ArrayList<>();
    private final ArrayList<double[]> nodes = new ArrayList<>();
    private final ArrayList<Integer> circleID = new ArrayList<>();
    private final ArrayList<double[]> circleNodes = new ArrayList<>();
    private final ArrayList<String> elevatorIDs = new ArrayList<>();
    private final ArrayList<double[]> elevatorPose = new ArrayList<>();
    private final ArrayList<String> elevatorWaiter = new ArrayList<>();
    private final ArrayList<String> eleWaiterIDs = new ArrayList<>();
    private final ArrayList<double[]> eleWaiterPose = new ArrayList<>();
    private final ArrayList<Boolean> sourceHighPrecision = new ArrayList<>();
    private ArrayList<ZonesData> zonesDataList = new ArrayList<>();
    private final ArrayList<String> accessControlIDs = new ArrayList<>();
    private final ArrayList<double[]> accessControlPose = new ArrayList<>();
    private final ArrayList<Double> accessControlSpendTime = new ArrayList<>();
    private final ArrayList<String> chargingPileIDs = new ArrayList<>();
    private final ArrayList<double[]> chargingPilePose = new ArrayList<>();
    private final ArrayList<Double> chargingPileAlignDist = new ArrayList<>();
    private final ArrayList<String> chargingPileGroups = new ArrayList<>();
    private final ArrayList<String> chargingMacs = new ArrayList<>();
    private final ArrayList<String> relocateID = new ArrayList<>();
    private final ArrayList<double[]> relocatePose = new ArrayList<>();
    private final HashMap<String, FireFoxGateSource> fireFoxGates = new HashMap<>();
    private final ArrayList<ChargingPile> chargingPiles = new ArrayList<>();
    private final ArrayList<ChargingPile> newChargingPiles = new ArrayList<>();
    private String jsonMap = "";
    private final ArrayList<String> newRelocateID = new ArrayList<>();
    private final ArrayList<double[]> newRelocatePose = new ArrayList<>();

    public final void receiveAreaData(String type, double[][] vertices, String id, Double maxSpeed, Integer value) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(id, "id");
    }

    public final ArrayList<ZonesData> getZonesDataList() {
        return this.zonesDataList;
    }

    public final void setZonesDataList(ArrayList<ZonesData> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
        this.zonesDataList = arrayList;
    }

    public final double[] getTrackArray() {
        double[] dArr = new double[this.track.size() * 3];
        int size = this.track.size();
        for (int i = 0; i < size; i++) {
            int i2 = i * 3;
            dArr[i2 + 0] = this.track.get(i)[0];
            dArr[i2 + 1] = this.track.get(i)[1];
            dArr[i2 + 2] = 0.0d;
        }
        Pdlog.m3275i(this.TAG, "track:", Arrays.toString(dArr));
        return dArr;
    }

    public final double[] getTrackWeightArray() {
        double[] dArr = new double[this.trackWeight.size()];
        int size = this.trackWeight.size();
        for (int i = 0; i < size; i++) {
            Double d = this.trackWeight.get(i);
            Intrinsics.checkExpressionValueIsNotNull(d, "trackWeight[i]");
            dArr[i] = d.doubleValue();
        }
        Pdlog.m3275i(this.TAG, "trackWeight: ", Arrays.toString(dArr));
        return dArr;
    }

    public final ArrayList<CircleMode> getCircleModes() {
        ArrayList<CircleMode> arrayList = new ArrayList<>();
        int size = this.circleID.size();
        for (int i = 0; i < size; i++) {
            CircleMode circleMode = new CircleMode();
            Integer num = this.circleID.get(i);
            Intrinsics.checkExpressionValueIsNotNull(num, "circleID[i]");
            circleMode.f6137id = num.intValue();
            circleMode.nodeIDs = new int[this.circleNodes.get(i).length];
            int length = this.circleNodes.get(i).length;
            for (int i2 = 0; i2 < length; i2++) {
                circleMode.nodeIDs[i2] = (int) this.circleNodes.get(i)[i2];
            }
            arrayList.add(circleMode);
            Pdlog.m3273d(this.TAG, "circle ", Integer.valueOf(circleMode.f6137id), " ", Arrays.toString(circleMode.nodeIDs));
        }
        return arrayList;
    }

    public final ArrayList<ElevatorSource> getElevators() {
        ArrayList<ElevatorSource> arrayList = new ArrayList<>();
        int size = this.elevatorIDs.size();
        for (int i = 0; i < size; i++) {
            String str = this.elevatorIDs.get(i);
            Intrinsics.checkExpressionValueIsNotNull(str, "elevatorIDs[i]");
            Vector3d vector3d = new Vector3d(this.elevatorPose.get(i)[0], this.elevatorPose.get(i)[1], this.elevatorPose.get(i)[2]);
            String str2 = this.elevatorWaiter.get(i);
            Intrinsics.checkExpressionValueIsNotNull(str2, "elevatorWaiter[i]");
            arrayList.add(new ElevatorSource("0", str, vector3d, str2));
        }
        return arrayList;
    }

    public final HashMap<String, FireFoxGateSource> getFireFoxGates() {
        return this.fireFoxGates;
    }

    public final ArrayList<ElevatorWaiter> getElevWaiters() {
        ArrayList<ElevatorWaiter> arrayList = new ArrayList<>();
        int size = this.eleWaiterIDs.size();
        for (int i = 0; i < size; i++) {
            String str = this.eleWaiterIDs.get(i);
            Intrinsics.checkExpressionValueIsNotNull(str, "eleWaiterIDs[i]");
            arrayList.add(new ElevatorWaiter("0", str, new Vector3d(this.eleWaiterPose.get(i)[0], this.eleWaiterPose.get(i)[1], this.eleWaiterPose.get(i)[2])));
        }
        return arrayList;
    }

    public final ArrayList<AccessControlPoint> getAccessPoints() {
        ArrayList<AccessControlPoint> arrayList = new ArrayList<>();
        int size = this.accessControlIDs.size();
        for (int i = 0; i < size; i++) {
            String str = this.accessControlIDs.get(i);
            Intrinsics.checkExpressionValueIsNotNull(str, "accessControlIDs[i]");
            Vector3d vector3d = new Vector3d(this.accessControlPose.get(i)[0], this.accessControlPose.get(i)[1], this.accessControlPose.get(i)[2]);
            Double d = this.accessControlSpendTime.get(i);
            Intrinsics.checkExpressionValueIsNotNull(d, "accessControlSpendTime[i]");
            arrayList.add(new AccessControlPoint(str, vector3d, d.doubleValue()));
        }
        return arrayList;
    }

    public final ArrayList<RelocatePoint> getRelocatePoints() {
        ArrayList<RelocatePoint> arrayList = new ArrayList<>();
        int size = this.relocateID.size();
        for (int i = 0; i < size; i++) {
            RelocatePoint relocatePoint = new RelocatePoint();
            String str = this.relocateID.get(i);
            Intrinsics.checkExpressionValueIsNotNull(str, "relocateID[i]");
            relocatePoint.setId(str);
            double[] dArr = this.relocatePose.get(i);
            Intrinsics.checkExpressionValueIsNotNull(dArr, "relocatePose[i]");
            relocatePoint.setPosition(dArr);
            arrayList.add(relocatePoint);
        }
        return arrayList;
    }

    public final ArrayList<ChargingPile> getChargingPiles() {
        if (this.chargingPiles.isEmpty()) {
            int size = this.chargingPileIDs.size();
            for (int i = 0; i < size; i++) {
                ArrayList<ChargingPile> arrayList = this.chargingPiles;
                String str = this.chargingPileIDs.get(i);
                Intrinsics.checkExpressionValueIsNotNull(str, "chargingPileIDs[i]");
                String str2 = str;
                Vector3d vector3d = new Vector3d(this.chargingPilePose.get(i)[0], this.chargingPilePose.get(i)[1], this.chargingPilePose.get(i)[2]);
                Double d = this.chargingPileAlignDist.get(i);
                Intrinsics.checkExpressionValueIsNotNull(d, "chargingPileAlignDist[i]");
                double doubleValue = d.doubleValue();
                String str3 = this.chargingPileGroups.get(i);
                Intrinsics.checkExpressionValueIsNotNull(str3, "chargingPileGroups[i]");
                String str4 = str3;
                String str5 = this.chargingMacs.get(i);
                Intrinsics.checkExpressionValueIsNotNull(str5, "chargingMacs[i]");
                arrayList.add(new ChargingPile("0", str2, vector3d, doubleValue, str4, str5));
            }
        }
        return this.chargingPiles;
    }

    public final ArrayList<ChargingPile> getNewChargingPiles() {
        return this.newChargingPiles;
    }

    public final String getJsonMap() {
        return this.jsonMap;
    }

    public final void setJsonMap(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.jsonMap = str;
    }

    public final void addCharge(ChargingPile pile) {
        Intrinsics.checkParameterIsNotNull(pile, "pile");
        this.newChargingPiles.add(pile);
        this.chargingPileIDs.add(pile.getId());
        this.chargingPilePose.add(new double[]{pile.getPose().getX(), pile.getPose().getY(), pile.getPose().getZ()});
        this.chargingPileAlignDist.add(Double.valueOf(pile.getAlignRange()));
        this.chargingPileGroups.add(pile.getId());
        this.chargingMacs.add(pile.getMac());
    }

    public final void decode(String string) throws JSONException {
        Intrinsics.checkParameterIsNotNull(string, "string");
        Pdlog.m3275i(this.TAG, "string:" + string);
        this.jsonMap = string;
        JSONObject jSONObject = new JSONObject(string);
        Pdlog.m3275i(this.TAG, "jsonObject:" + jSONObject);
        decode(jSONObject);
    }

    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r7v7 */
    public final void decode(JSONObject jsonObject) throws JSONException {
        Intrinsics.checkParameterIsNotNull(jsonObject, "jsonObject");
        JSONArray jSONArray = jsonObject.getJSONObject(MapElement.Key.MAP).getJSONArray(MapElement.Key.ELEMENTS);
        boolean z = true;
        ?? r7 = 0;
        if (jsonObject.getJSONObject(MapElement.Key.MAP).has("zones")) {
            JSONArray jSONArray2 = jsonObject.getJSONObject(MapElement.Key.MAP).getJSONArray("zones");
            int length = jSONArray2.length();
            for (int i = 0; i < length; i++) {
                String string = jSONArray2.getString(i);
                Intrinsics.checkExpressionValueIsNotNull(string, "zonesArray.getString(i)");
                Object fromJson = gson.fromJson(string, (Class<Object>) ZonesData.class);
                Intrinsics.checkExpressionValueIsNotNull(fromJson, "gson.fromJson(jsonValue, ZonesData::class.java)");
                Pdlog.m3275i(this.TAG, "parser zone data " + string);
                this.zonesDataList.add((ZonesData) fromJson);
            }
        } else {
            Pdlog.m3273d(this.TAG, "zones data null");
        }
        int length2 = jSONArray.length();
        int i2 = 0;
        while (i2 < length2) {
            String string2 = jSONArray.getString(i2);
            Intrinsics.checkExpressionValueIsNotNull(string2, "jsonArray.getString(i)");
            Object fromJson2 = gson.fromJson(string2, (Class<Object>) PuduJsonData.class);
            Intrinsics.checkExpressionValueIsNotNull(fromJson2, "gson.fromJson(jsonValue, PuduJsonData::class.java)");
            PuduJsonData puduJsonData = (PuduJsonData) fromJson2;
            if (Intrinsics.areEqual(puduJsonData.getType(), "track")) {
                this.track.add(puduJsonData.getVector());
                this.trackId.add(Integer.valueOf(Integer.parseInt(puduJsonData.getId())));
                this.trackWeight.add(Double.valueOf(puduJsonData.getWeight()));
                if (puduJsonData.getMaxSpeed() != null) {
                    this.trackMaxSpeed.add(puduJsonData.getMaxSpeed());
                } else {
                    this.trackMaxSpeed.add(Double.valueOf(0.0d));
                }
                if (puduJsonData.getPathWidth() != null) {
                    this.trackWidth.add(puduJsonData.getPathWidth());
                } else {
                    this.trackWidth.add(Double.valueOf(0.0d));
                }
            }
            if (Intrinsics.areEqual(puduJsonData.getType(), TypedValues.Attributes.S_TARGET)) {
                this.target.add(puduJsonData.getVector());
            }
            if (Intrinsics.areEqual(puduJsonData.getType(), MapElement.Source.SOURCE)) {
                this.sourceId.add(puduJsonData.getId());
                this.sources.add(puduJsonData.getVector());
                if (puduJsonData.getDoubleDir() != null) {
                    this.sourceDir.add(puduJsonData.getDir());
                    this.sourceDoubleDir.add(Boolean.valueOf(Intrinsics.compare(puduJsonData.getDoubleDir().intValue(), (int) r7) > 0 ? z : r7));
                } else {
                    this.sourceDir.add(Double.valueOf(0.0d));
                    this.sourceDoubleDir.add(Boolean.valueOf((boolean) r7));
                }
                this.sourceMode.add(puduJsonData.getMode());
                this.sourceGroup.add(puduJsonData.getGroup());
                this.sourceHighPrecision.add(Boolean.valueOf(puduJsonData.isHigh_precision()));
                if (puduJsonData.getSortWeight() != null) {
                    this.sourceSortWeight.add(puduJsonData.getSortWeight());
                } else {
                    this.sourceSortWeight.add(Integer.valueOf((int) r7));
                }
            }
            if (Intrinsics.areEqual(puduJsonData.getType(), "wall")) {
                this.wall.add(puduJsonData.getVector());
            }
            if (Intrinsics.areEqual(puduJsonData.getType(), "virtual_wall")) {
                this.virtualWall.add(puduJsonData.getVector());
            }
            if (Intrinsics.areEqual(puduJsonData.getType(), "nodes")) {
                this.nodes.add(puduJsonData.getVector());
                this.nodeID.add(Integer.valueOf(Integer.parseInt(puduJsonData.getId())));
            }
            if (Intrinsics.areEqual(puduJsonData.getType(), "circle")) {
                this.circleNodes.add(puduJsonData.getVector());
                this.circleID.add(Integer.valueOf(Integer.parseInt(puduJsonData.getId())));
            }
            if (Intrinsics.areEqual(puduJsonData.getType(), "elevator")) {
                this.elevatorIDs.add(puduJsonData.getId());
                this.elevatorPose.add(puduJsonData.getVector());
                this.elevatorWaiter.add(puduJsonData.getWaiter());
            }
            if (Intrinsics.areEqual(puduJsonData.getType(), "elev_waiter")) {
                this.eleWaiterIDs.add(puduJsonData.getId());
                this.eleWaiterPose.add(puduJsonData.getVector());
            }
            if (Intrinsics.areEqual(puduJsonData.getType(), "gate")) {
                String id = puduJsonData.getId();
                Intrinsics.checkExpressionValueIsNotNull(id, "puduJsonData.id");
                Double up_stop_distance = puduJsonData.getUp_stop_distance();
                Intrinsics.checkExpressionValueIsNotNull(up_stop_distance, "puduJsonData.up_stop_distance");
                double doubleValue = up_stop_distance.doubleValue();
                Double down_stop_distance = puduJsonData.getDown_stop_distance();
                Intrinsics.checkExpressionValueIsNotNull(down_stop_distance, "puduJsonData.down_stop_distance");
                double doubleValue2 = down_stop_distance.doubleValue();
                Double up_slow_distance = puduJsonData.getUp_slow_distance();
                Intrinsics.checkExpressionValueIsNotNull(up_slow_distance, "puduJsonData.up_slow_distance");
                double doubleValue3 = up_slow_distance.doubleValue();
                Double down_slow_distance = puduJsonData.getDown_slow_distance();
                Intrinsics.checkExpressionValueIsNotNull(down_slow_distance, "puduJsonData.down_slow_distance");
                double doubleValue4 = down_slow_distance.doubleValue();
                Double up_speed = puduJsonData.getUp_speed();
                Intrinsics.checkExpressionValueIsNotNull(up_speed, "puduJsonData.up_speed");
                double doubleValue5 = up_speed.doubleValue();
                Double down_speed = puduJsonData.getDown_speed();
                Intrinsics.checkExpressionValueIsNotNull(down_speed, "puduJsonData.down_speed");
                FireFoxGateSource fireFoxGateSource = new FireFoxGateSource(id, doubleValue, doubleValue2, doubleValue3, doubleValue4, doubleValue5, down_speed.doubleValue(), new Vector3d(puduJsonData.getVector()[r7], puduJsonData.getVector()[1], puduJsonData.getVector()[2]));
                HashMap<String, FireFoxGateSource> hashMap = this.fireFoxGates;
                String id2 = puduJsonData.getId();
                Intrinsics.checkExpressionValueIsNotNull(id2, "puduJsonData.id");
                hashMap.put(id2, fireFoxGateSource);
            }
            if (Intrinsics.areEqual(puduJsonData.getType(), "access_point")) {
                this.accessControlIDs.add(puduJsonData.getId());
                this.accessControlPose.add(puduJsonData.getVector());
                this.accessControlSpendTime.add(puduJsonData.getOpened_spend_time());
            }
            if (Intrinsics.areEqual(puduJsonData.getType(), "charging_pile")) {
                this.chargingPileIDs.add(puduJsonData.getId());
                this.chargingPilePose.add(puduJsonData.getVector());
                this.chargingPileAlignDist.add(puduJsonData.getAlign_dist());
                if (puduJsonData.getGroup() == null) {
                    this.chargingPileGroups.add(puduJsonData.getId());
                } else {
                    this.chargingPileGroups.add(puduJsonData.getGroup());
                }
                this.chargingMacs.add(puduJsonData.getMac() != null ? puduJsonData.getMac() : "");
            }
            if (Intrinsics.areEqual(puduJsonData.getType(), "lslam_restarter")) {
                this.relocateID.add(puduJsonData.getId());
                this.relocatePose.add(puduJsonData.getVector());
            }
            i2++;
            z = true;
            r7 = 0;
        }
        if (jsonObject.getJSONObject(MapElement.Key.MAP).has("zones")) {
            JSONArray jSONArray3 = jsonObject.getJSONObject(MapElement.Key.MAP).getJSONArray("zones");
            int length3 = jSONArray3.length();
            for (int i3 = 0; i3 < length3; i3++) {
                String string3 = jSONArray3.getString(i3);
                Intrinsics.checkExpressionValueIsNotNull(string3, "zoneArray.getString(i)");
                Object fromJson3 = gson.fromJson(string3, (Class<Object>) PuduJsonData.class);
                Intrinsics.checkExpressionValueIsNotNull(fromJson3, "gson.fromJson(jsonValue, PuduJsonData::class.java)");
                PuduJsonData puduJsonData2 = (PuduJsonData) fromJson3;
                String type = puduJsonData2.getType();
                if (type != null && type.hashCode() == -1468852930 && type.equals("elevator_area")) {
                    Pdlog.m3273d(this.TAG, "FIND ZONES elevator_area " + puduJsonData2.getNodes().size());
                }
            }
        }
    }

    public final ArrayList<Source> getSource() {
        ArrayList<Source> arrayList = new ArrayList<>();
        int size = this.sources.size();
        for (int i = 0; i < size; i++) {
            Source source = new Source();
            source.getPosition()[0] = this.sources.get(i)[0];
            source.getPosition()[1] = this.sources.get(i)[1];
            String str = this.sourceId.get(i);
            Intrinsics.checkExpressionValueIsNotNull(str, "sourceId[i]");
            source.setId(str);
            Double d = this.sourceDir.get(i);
            Intrinsics.checkExpressionValueIsNotNull(d, "sourceDir[i]");
            source.setDir(d.doubleValue());
            Boolean bool = this.sourceDoubleDir.get(i);
            Intrinsics.checkExpressionValueIsNotNull(bool, "sourceDoubleDir[i]");
            source.setDoubleDir(bool.booleanValue());
            String str2 = this.sourceMode.get(i);
            if (str2 == null) {
                str2 = Constants.POINT_TYPE_TABLE;
            }
            source.setMode(str2);
            String str3 = this.sourceGroup.get(i);
            if (str3 == null) {
                str3 = "";
            }
            source.setGroup(str3);
            Integer num = this.sourceSortWeight.get(i);
            Intrinsics.checkExpressionValueIsNotNull(num, "sourceSortWeight[i]");
            source.setSort_weight(num.intValue());
            if (Intrinsics.areEqual(source.getMode(), "dining_outlet") && Intrinsics.areEqual(source.getGroup(), "")) {
                source.setGroup("default");
            }
            Boolean bool2 = this.sourceHighPrecision.get(i);
            Intrinsics.checkExpressionValueIsNotNull(bool2, "sourceHighPrecision[i]");
            source.setHigh_precision(bool2.booleanValue());
            arrayList.add(source);
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    public final void addSource(double[] pose, String mode, String id, String group) {
        Intrinsics.checkParameterIsNotNull(pose, "pose");
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(group, "group");
        this.sources.add(pose);
        this.sourceId.add(id);
        this.sourceDir.add(Double.valueOf(pose[2]));
        this.sourceDoubleDir.add(false);
        this.sourceHighPrecision.add(false);
        this.sourceMode.add(mode);
        this.sourceGroup.add(group);
        this.sourceSortWeight.add(0);
    }

    public final void addRelocatePose(String id, double[] pose) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(pose, "pose");
        this.relocateID.add(id);
        this.relocatePose.add(pose);
        this.newRelocateID.add(id);
        this.newRelocatePose.add(pose);
    }

    public final String toJsonMap() {
        JSONObject jSONObject = new JSONObject(this.jsonMap);
        JSONArray jSONArray = jSONObject.getJSONObject(MapElement.Key.MAP).getJSONArray(MapElement.Key.ELEMENTS);
        int size = this.newRelocateID.size();
        for (int i = 0; i < size; i++) {
            jSONArray.put(new JSONObject("{\"type\":\"lslam_restarter\",\"vector\":[" + CommonKt.format(this.newRelocatePose.get(i)[0], 6) + ',' + CommonKt.format(this.newRelocatePose.get(i)[1], 6) + ',' + CommonKt.format(this.newRelocatePose.get(i)[2], 6) + "],\"id\":\"" + this.newRelocateID.get(i) + "\"}"));
        }
        String jSONObject2 = jSONObject.toString();
        Intrinsics.checkExpressionValueIsNotNull(jSONObject2, "jsonObject.toString()");
        return jSONObject2;
    }

    public final String toChargePileJsonMap() {
        JSONObject jSONObject = new JSONObject(this.jsonMap);
        JSONArray jSONArray = jSONObject.getJSONObject(MapElement.Key.MAP).getJSONArray(MapElement.Key.ELEMENTS);
        int i = 0;
        for (Object obj : this.newChargingPiles) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            ChargingPile chargingPile = (ChargingPile) obj;
            jSONArray.put(new JSONObject("{\"type\":\"charging_pile\",\"vector\":[" + CommonKt.format(chargingPile.getPose().getX(), 6) + ',' + CommonKt.format(chargingPile.getPose().getY(), 6) + ',' + CommonKt.format(chargingPile.getPose().getZ(), 6) + "],\"id\":\"" + chargingPile.getId() + "\",\"group\":\"" + chargingPile.getId() + "\",\"align_dist\":" + chargingPile.getAlignRange() + ",\"mac\":\"" + chargingPile.getMac() + "\"}"));
            i = i2;
        }
        String jSONObject2 = jSONObject.toString();
        Intrinsics.checkExpressionValueIsNotNull(jSONObject2, "jsonObject.toString()");
        return jSONObject2;
    }

    public final ArrayList<Track> getTrack() {
        int i;
        ArrayList<Track> arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        int i2 = 0;
        if (arrayList.size() > 0) {
            arrayList2.add(this.trackId.get(0));
        }
        int size = this.trackId.size();
        for (int i3 = 0; i3 < size; i3++) {
            int size2 = arrayList2.size();
            boolean z = false;
            for (int i4 = 0; i4 < size2; i4++) {
                if (Intrinsics.areEqual(this.trackId.get(i3), (Integer) arrayList2.get(i4))) {
                    z = true;
                }
            }
            if (!z) {
                arrayList2.add(this.trackId.get(i3));
            }
        }
        int size3 = arrayList2.size();
        int i5 = 0;
        while (i5 < size3) {
            ArrayList arrayList3 = new ArrayList();
            int size4 = this.trackId.size();
            int i6 = i2;
            int i7 = i6;
            while (i6 < size4) {
                if (Intrinsics.areEqual(this.trackId.get(i6), (Integer) arrayList2.get(i5))) {
                    arrayList3.add(this.track.get(i6));
                    i7 += this.track.get(i6).length / 2;
                }
                i6++;
            }
            ArrayList<Segment> arrayList4 = new ArrayList<>();
            int size5 = this.trackId.size();
            int i8 = i2;
            int i9 = i8;
            while (i8 < size5) {
                if (Intrinsics.areEqual(this.trackId.get(i8), (Integer) arrayList2.get(i5))) {
                    Segment segment = new Segment();
                    segment.seg_id = i9;
                    Double d = this.trackWidth.get(i8);
                    Intrinsics.checkExpressionValueIsNotNull(d, "trackWidth[k]");
                    i = i5;
                    segment.pathWidth = d.doubleValue();
                    Double d2 = this.trackMaxSpeed.get(i8);
                    Intrinsics.checkExpressionValueIsNotNull(d2, "trackMaxSpeed[k]");
                    segment.maxSpeed = d2.doubleValue();
                    segment.start = new double[]{this.track.get(i8)[0], this.track.get(i8)[1]};
                    segment.end = new double[]{this.track.get(i8)[2], this.track.get(i8)[3]};
                    arrayList4.add(segment);
                    i9++;
                } else {
                    i = i5;
                }
                i8++;
                i5 = i;
            }
            int i10 = i5;
            double[] dArr = new double[i7 * 3];
            Iterator it = arrayList3.iterator();
            int i11 = 0;
            while (it.hasNext()) {
                double[] dArr2 = (double[]) it.next();
                int length = dArr2.length / 2;
                for (int i12 = 0; i12 < length; i12++) {
                    int i13 = (i12 * 3) + i11;
                    int i14 = i12 * 2;
                    dArr[i13 + 0] = dArr2[i14 + 0];
                    dArr[i13 + 1] = dArr2[i14 + 1];
                    dArr[i13 + 2] = 0.0d;
                }
                i11 += (dArr2.length / 2) * 3;
            }
            Pdlog.m3275i(this.TAG, "id ", arrayList2.get(i10), " track:", Arrays.toString(dArr));
            Track track = new Track();
            track.trackPoint = dArr;
            Object obj = arrayList2.get(i10);
            Intrinsics.checkExpressionValueIsNotNull(obj, "id[i]");
            track.f6143id = ((Number) obj).intValue();
            track.segments = arrayList4;
            arrayList.add(track);
            i5 = i10 + 1;
            i2 = 0;
        }
        return arrayList;
    }

    public final ArrayList<Node> getNode() {
        ArrayList<Node> arrayList = new ArrayList<>();
        int size = this.nodeID.size();
        for (int i = 0; i < size; i++) {
            Node node = new Node();
            Integer num = this.nodeID.get(i);
            Intrinsics.checkExpressionValueIsNotNull(num, "nodeID[i]");
            node.f6141id = num.intValue();
            node.position = this.nodes.get(i);
            arrayList.add(node);
        }
        return arrayList;
    }
}
