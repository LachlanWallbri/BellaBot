package com.pudutech.mirsdk.map;

import com.pudutech.mirsdk.compat.topo.ConfigJson;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MapConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0018\u0018\u00002\u00020\u0001B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003¢\u0006\u0002\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\r\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\f\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0013\"\u0004\b\u0017\u0010\u0015R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0019R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0019\"\u0004\b\u001e\u0010\u001fR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0013\"\u0004\b!\u0010\u0015R\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0019¨\u0006#"}, m3961d2 = {"Lcom/pudutech/mirsdk/map/MapConfig;", "", ConfigJson.MAP_VERSION, "", ConfigJson.ALIAS, "", ConfigJson.IMAGE_WIDTH, ConfigJson.IMAGE_HEIGHT, ConfigJson.IMAGE_FPS, "exposure_time", "new_map", "", "default", ConfigJson.AUTOEXP, ConfigJson.SENSOR, "(ILjava/lang/String;IIIIZZZI)V", "getAlias", "()Ljava/lang/String;", "getAutoexp", "()Z", "setAutoexp", "(Z)V", "getDefault", "setDefault", "getExposure_time", "()I", "getImage_fps", "getImage_height", "getImage_width", "getMap_version", "setMap_version", "(I)V", "getNew_map", "setNew_map", "getSensor", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MapConfig {
    private final String alias;
    private boolean autoexp;
    private boolean default;
    private final int exposure_time;
    private final int image_fps;
    private final int image_height;
    private final int image_width;
    private int map_version;
    private boolean new_map;
    private final int sensor;

    public MapConfig(int i, String alias, int i2, int i3, int i4, int i5, boolean z, boolean z2, boolean z3, int i6) {
        Intrinsics.checkParameterIsNotNull(alias, "alias");
        this.map_version = i;
        this.alias = alias;
        this.image_width = i2;
        this.image_height = i3;
        this.image_fps = i4;
        this.exposure_time = i5;
        this.new_map = z;
        this.default = z2;
        this.autoexp = z3;
        this.sensor = i6;
    }

    public final int getMap_version() {
        return this.map_version;
    }

    public final void setMap_version(int i) {
        this.map_version = i;
    }

    public final String getAlias() {
        return this.alias;
    }

    public final int getImage_width() {
        return this.image_width;
    }

    public final int getImage_height() {
        return this.image_height;
    }

    public final int getImage_fps() {
        return this.image_fps;
    }

    public final int getExposure_time() {
        return this.exposure_time;
    }

    public final boolean getNew_map() {
        return this.new_map;
    }

    public final void setNew_map(boolean z) {
        this.new_map = z;
    }

    public final boolean getDefault() {
        return this.default;
    }

    public final void setDefault(boolean z) {
        this.default = z;
    }

    public final boolean getAutoexp() {
        return this.autoexp;
    }

    public final void setAutoexp(boolean z) {
        this.autoexp = z;
    }

    public /* synthetic */ MapConfig(int i, String str, int i2, int i3, int i4, int i5, boolean z, boolean z2, boolean z3, int i6, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, i2, i3, i4, i5, (i7 & 64) != 0 ? false : z, (i7 & 128) != 0 ? false : z2, (i7 & 256) != 0 ? true : z3, (i7 & 512) != 0 ? 1 : i6);
    }

    public final int getSensor() {
        return this.sensor;
    }
}
