package com.pudutech.robotselfclean.filter;

import com.pudutech.mirsdk.config.MapFilePathConfig;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;

/* compiled from: HlsLegacyFilter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016R!\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/robotselfclean/filter/HlsLegacyFilter;", "Lcom/pudutech/robotselfclean/filter/IFilter;", "()V", "legacyList", "", "", "getLegacyList", "()Ljava/util/List;", "legacyList$delegate", "Lkotlin/Lazy;", "onCreateDeleteList", "onCreateRetainList", "RobotSelfClean_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class HlsLegacyFilter implements IFilter {

    /* renamed from: legacyList$delegate, reason: from kotlin metadata */
    private final Lazy legacyList = LazyKt.lazy(new Function0<List<? extends String>>() { // from class: com.pudutech.robotselfclean.filter.HlsLegacyFilter$legacyList$2
        @Override // kotlin.jvm.functions.Function0
        public final List<? extends String> invoke() {
            return CollectionsKt.listOf((Object[]) new String[]{MapFilePathConfig.DEFAULT_MAP_LOCATE_PATH, "/sdcard/pudu/custom/dialogflowconfig", "/sdcard/voice_download", "/sdcard/voice_package", "/sdcard/music", "/sdcard/PuduVoice", "/sdcard/PuduMusic", "/sdcard/pudu/music", "/sdcard/led_animation", "/sdcard/PuduRobotMap/pdrobot.pdmap", "/sdcard/PuduRobotMedia/logo/logo.png", "/sdcard/PuduLogo/logo.png", "/sdcard/PuduLogo/login_logo.png", "/sdcard/pudu/config/runparam.cfg", "/sdcard/pudu/config/speedlevel.cfg", "/sdcard/pudu/config/history_report.json"});
        }
    });

    private final List<String> getLegacyList() {
        return (List) this.legacyList.getValue();
    }

    @Override // com.pudutech.robotselfclean.filter.IFilter
    public List<String> onCreateDeleteList() {
        return getLegacyList();
    }

    @Override // com.pudutech.robotselfclean.filter.IFilter
    public List<String> onCreateRetainList() {
        return CollectionsKt.emptyList();
    }
}
