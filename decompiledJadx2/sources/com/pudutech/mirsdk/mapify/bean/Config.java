package com.pudutech.mirsdk.mapify.bean;

import com.pudutech.mirsdk.compat.topo.ConfigJson;
import com.pudutech.mirsdk.config.MapFilePathConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Config.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b2\b\u0086\b\u0018\u00002\u00020\u0001Bs\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003¢\u0006\u0002\u0010\u0010J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\t\u00100\u001a\u00020\u0005HÆ\u0003J\t\u00101\u001a\u00020\u0003HÆ\u0003J\t\u00102\u001a\u00020\u0003HÆ\u0003J\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\u0003HÆ\u0003J\t\u00105\u001a\u00020\u000bHÆ\u0003J\t\u00106\u001a\u00020\u000bHÆ\u0003J\t\u00107\u001a\u00020\u000bHÆ\u0003Jw\u00108\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u0003HÆ\u0001J\u0013\u00109\u001a\u00020\u000b2\b\u0010:\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010;\u001a\u00020\u0003HÖ\u0001J\t\u0010<\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\r\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u000f\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\f\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0016\"\u0004\b\u001e\u0010\u0018R\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001a\"\u0004\b \u0010\u001cR\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001a\"\u0004\b\"\u0010\u001cR\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u001a\"\u0004\b$\u0010\u001cR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u001a\"\u0004\b&\u0010\u001cR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u001a\"\u0004\b(\u0010\u001cR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0016\"\u0004\b*\u0010\u0018R\u001a\u0010\u000e\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u001a\"\u0004\b,\u0010\u001c¨\u0006="}, m3961d2 = {"Lcom/pudutech/mirsdk/mapify/bean/Config;", "", ConfigJson.MAP_VERSION, "", ConfigJson.ALIAS, "", ConfigJson.IMAGE_WIDTH, ConfigJson.IMAGE_HEIGHT, ConfigJson.IMAGE_FPS, "exposure_time", "new_map", "", "default", ConfigJson.AUTOEXP, ConfigJson.SENSOR, "camera_scheme", "(ILjava/lang/String;IIIIZZZII)V", "getAlias", "()Ljava/lang/String;", "setAlias", "(Ljava/lang/String;)V", "getAutoexp", "()Z", "setAutoexp", "(Z)V", "getCamera_scheme", "()I", "setCamera_scheme", "(I)V", "getDefault", "setDefault", "getExposure_time", "setExposure_time", "getImage_fps", "setImage_fps", "getImage_height", "setImage_height", "getImage_width", "setImage_width", "getMap_version", "setMap_version", "getNew_map", "setNew_map", "getSensor", "setSensor", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class Config {
    private String alias;
    private boolean autoexp;
    private int camera_scheme;
    private boolean default;
    private int exposure_time;
    private int image_fps;
    private int image_height;
    private int image_width;
    private int map_version;
    private boolean new_map;
    private int sensor;

    public Config() {
        this(0, null, 0, 0, 0, 0, false, false, false, 0, 0, MapFilePathConfig.MAX_DELETE_CODE_VALUE, null);
    }

    /* renamed from: component1, reason: from getter */
    public final int getMap_version() {
        return this.map_version;
    }

    /* renamed from: component10, reason: from getter */
    public final int getSensor() {
        return this.sensor;
    }

    /* renamed from: component11, reason: from getter */
    public final int getCamera_scheme() {
        return this.camera_scheme;
    }

    /* renamed from: component2, reason: from getter */
    public final String getAlias() {
        return this.alias;
    }

    /* renamed from: component3, reason: from getter */
    public final int getImage_width() {
        return this.image_width;
    }

    /* renamed from: component4, reason: from getter */
    public final int getImage_height() {
        return this.image_height;
    }

    /* renamed from: component5, reason: from getter */
    public final int getImage_fps() {
        return this.image_fps;
    }

    /* renamed from: component6, reason: from getter */
    public final int getExposure_time() {
        return this.exposure_time;
    }

    /* renamed from: component7, reason: from getter */
    public final boolean getNew_map() {
        return this.new_map;
    }

    /* renamed from: component8, reason: from getter */
    public final boolean getDefault() {
        return this.default;
    }

    /* renamed from: component9, reason: from getter */
    public final boolean getAutoexp() {
        return this.autoexp;
    }

    public final Config copy(int map_version, String alias, int image_width, int image_height, int image_fps, int exposure_time, boolean new_map, boolean r21, boolean autoexp, int sensor, int camera_scheme) {
        Intrinsics.checkParameterIsNotNull(alias, "alias");
        return new Config(map_version, alias, image_width, image_height, image_fps, exposure_time, new_map, r21, autoexp, sensor, camera_scheme);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Config)) {
            return false;
        }
        Config config = (Config) other;
        return this.map_version == config.map_version && Intrinsics.areEqual(this.alias, config.alias) && this.image_width == config.image_width && this.image_height == config.image_height && this.image_fps == config.image_fps && this.exposure_time == config.exposure_time && this.new_map == config.new_map && this.default == config.default && this.autoexp == config.autoexp && this.sensor == config.sensor && this.camera_scheme == config.camera_scheme;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int i = this.map_version * 31;
        String str = this.alias;
        int hashCode = (((((((((i + (str != null ? str.hashCode() : 0)) * 31) + this.image_width) * 31) + this.image_height) * 31) + this.image_fps) * 31) + this.exposure_time) * 31;
        boolean z = this.new_map;
        int i2 = z;
        if (z != 0) {
            i2 = 1;
        }
        int i3 = (hashCode + i2) * 31;
        boolean z2 = this.default;
        int i4 = z2;
        if (z2 != 0) {
            i4 = 1;
        }
        int i5 = (i3 + i4) * 31;
        boolean z3 = this.autoexp;
        int i6 = z3;
        if (z3 != 0) {
            i6 = 1;
        }
        return ((((i5 + i6) * 31) + this.sensor) * 31) + this.camera_scheme;
    }

    public String toString() {
        return "Config(map_version=" + this.map_version + ", alias=" + this.alias + ", image_width=" + this.image_width + ", image_height=" + this.image_height + ", image_fps=" + this.image_fps + ", exposure_time=" + this.exposure_time + ", new_map=" + this.new_map + ", default=" + this.default + ", autoexp=" + this.autoexp + ", sensor=" + this.sensor + ", camera_scheme=" + this.camera_scheme + ")";
    }

    public Config(int i, String alias, int i2, int i3, int i4, int i5, boolean z, boolean z2, boolean z3, int i6, int i7) {
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
        this.camera_scheme = i7;
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

    public final void setAlias(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.alias = str;
    }

    public final int getImage_width() {
        return this.image_width;
    }

    public final void setImage_width(int i) {
        this.image_width = i;
    }

    public final int getImage_height() {
        return this.image_height;
    }

    public final void setImage_height(int i) {
        this.image_height = i;
    }

    public final int getImage_fps() {
        return this.image_fps;
    }

    public final void setImage_fps(int i) {
        this.image_fps = i;
    }

    public final int getExposure_time() {
        return this.exposure_time;
    }

    public final void setExposure_time(int i) {
        this.exposure_time = i;
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

    public final int getSensor() {
        return this.sensor;
    }

    public final void setSensor(int i) {
        this.sensor = i;
    }

    public /* synthetic */ Config(int i, String str, int i2, int i3, int i4, int i5, boolean z, boolean z2, boolean z3, int i6, int i7, int i8, DefaultConstructorMarker defaultConstructorMarker) {
        this((i8 & 1) != 0 ? 1 : i, (i8 & 2) != 0 ? "pd_robot" : str, (i8 & 4) != 0 ? 1280 : i2, (i8 & 8) != 0 ? 720 : i3, (i8 & 16) != 0 ? 10 : i4, (i8 & 32) == 0 ? i5 : 10, (i8 & 64) != 0 ? false : z, (i8 & 128) != 0 ? false : z2, (i8 & 256) != 0 ? false : z3, (i8 & 512) == 0 ? i6 : 1, (i8 & 1024) == 0 ? i7 : 0);
    }

    public final int getCamera_scheme() {
        return this.camera_scheme;
    }

    public final void setCamera_scheme(int i) {
        this.camera_scheme = i;
    }
}
