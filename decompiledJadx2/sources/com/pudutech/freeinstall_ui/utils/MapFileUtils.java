package com.pudutech.freeinstall_ui.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.yaml.snakeyaml.Yaml;

/* compiled from: MapFileUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/utils/MapFileUtils;", "", "()V", "Companion", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class MapFileUtils {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String YAML_FILE_NAME = "optemap.yaml";
    private static final String MAP_FILE_NAME = "optemap.png";

    /* compiled from: MapFileUtils.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\u0004J.\u0010\f\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001\u0018\u00010\rj\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001\u0018\u0001`\u000e2\u0006\u0010\u000b\u001a\u00020\u0004R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/utils/MapFileUtils$Companion;", "", "()V", "MAP_FILE_NAME", "", "getMAP_FILE_NAME", "()Ljava/lang/String;", "YAML_FILE_NAME", "getYAML_FILE_NAME", "getMapPic", "Landroid/graphics/Bitmap;", "path", "readYaml", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getYAML_FILE_NAME() {
            return MapFileUtils.YAML_FILE_NAME;
        }

        public final String getMAP_FILE_NAME() {
            return MapFileUtils.MAP_FILE_NAME;
        }

        public final HashMap<String, Object> readYaml(String path) {
            Intrinsics.checkParameterIsNotNull(path, "path");
            FileInputStream fileInputStream = (FileInputStream) null;
            try {
                try {
                    FileInputStream fileInputStream2 = new FileInputStream(new File(path + '/' + getYAML_FILE_NAME()));
                    try {
                        Object loadAs = new Yaml().loadAs(fileInputStream2, (Class<Object>) HashMap.class);
                        if (loadAs == null) {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.HashMap<kotlin.String, kotlin.Any> /* = java.util.HashMap<kotlin.String, kotlin.Any> */");
                        }
                        HashMap<String, Object> hashMap = (HashMap) loadAs;
                        fileInputStream2.close();
                        fileInputStream2.close();
                        return hashMap;
                    } catch (Exception e) {
                        fileInputStream = fileInputStream2;
                        e = e;
                        e.printStackTrace();
                        if (fileInputStream == null) {
                            return null;
                        }
                        fileInputStream.close();
                        return null;
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream = fileInputStream2;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e2) {
                e = e2;
            }
        }

        public final Bitmap getMapPic(String path) {
            Intrinsics.checkParameterIsNotNull(path, "path");
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 1;
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            return BitmapFactory.decodeFile(path + '/' + getMAP_FILE_NAME(), options);
        }
    }
}
