package com.pudutech.resources.language;

import com.aliyun.alink.linksdk.alcs.coap.resources.LinkFormat;
import java.util.List;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: SupportedLocale.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\bG\n\u0002\u0010 \n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0011\u0010\u000f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0011\u0010\u0011\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006R\u0011\u0010\u0013\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006R\u0011\u0010\u0015\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0006R\u0011\u0010\u0017\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0006R\u0011\u0010\u0019\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0006R\u0011\u0010\u001b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0006R\u0011\u0010\u001d\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0006R\u0011\u0010\u001f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0006R\u0011\u0010!\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0006R\u0011\u0010#\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0006R\u0011\u0010%\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0006R\u0011\u0010'\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0006R\u0011\u0010)\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0006R\u0011\u0010+\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u0006R\u0011\u0010-\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u0006R\u0011\u0010/\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u0006R\u0011\u00101\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u0006R\u0011\u00103\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u0006R\u0011\u00105\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\u0006R\u0011\u00107\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\u0006R\u0011\u00109\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\u0006R\u0011\u0010;\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u0010\u0006R\u0011\u0010=\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\u0006R\u0011\u0010?\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u0010\u0006R\u0011\u0010A\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bB\u0010\u0006R\u0011\u0010C\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bD\u0010\u0006R\u0011\u0010E\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bF\u0010\u0006R\u0011\u0010G\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bH\u0010\u0006R\u0011\u0010I\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010\u0006R!\u0010K\u001a\b\u0012\u0004\u0012\u00020\u00040L8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bO\u0010P\u001a\u0004\bM\u0010N¨\u0006Q"}, m3961d2 = {"Lcom/pudutech/resources/language/SupportedLocale;", "", "()V", "ARAB", "Lcom/pudutech/resources/language/Option;", "getARAB", "()Lcom/pudutech/resources/language/Option;", "BULGARIAN", "getBULGARIAN", "CHINESE", "getCHINESE", "CHINESE_HK", "getCHINESE_HK", "CHINESE_TW", "getCHINESE_TW", "CROATIAN", "getCROATIAN", "CZECH", "getCZECH", "DUTCH", "getDUTCH", "ENGLISH", "getENGLISH", "ESTONIA", "getESTONIA", "FRENCH", "getFRENCH", "GEORGIA", "getGEORGIA", "GERMAN", "getGERMAN", "HUNGARIAN", "getHUNGARIAN", "INDONESIAN", "getINDONESIAN", "ISRAELI", "getISRAELI", "ITALIAN", "getITALIAN", "JAPANESE", "getJAPANESE", "KOREAN", "getKOREAN", "MALAY", "getMALAY", "POLAND", "getPOLAND", "PORTUGUESE", "getPORTUGUESE", "PORTUGUESE_Brazil", "getPORTUGUESE_Brazil", "ROMANIAN", "getROMANIAN", "RUSSIAN", "getRUSSIAN", "SERBIAN", "getSERBIAN", "SLOVAK", "getSLOVAK", "SLOVENIAN", "getSLOVENIAN", "SPANISH", "getSPANISH", "SPANISH_LATIN_AMERICA", "getSPANISH_LATIN_AMERICA", "SWEDISH", "getSWEDISH", "THAI_LAND", "getTHAI_LAND", "TURKEY", "getTURKEY", "VIETNAMESE", "getVIETNAMESE", "default", "getDefault", "list", "", "getList", "()Ljava/util/List;", "list$delegate", "Lkotlin/Lazy;", "resources_bellabot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class SupportedLocale {
    public static final SupportedLocale INSTANCE = new SupportedLocale();
    private static final Option ENGLISH = new Option(new Locale("en", "US"), "English");
    private static final Option CHINESE = new Option(new Locale("zh", "CN"), "中文");
    private static final Option KOREAN = new Option(new Locale("ko", "KR"), "한국어");
    private static final Option CHINESE_HK = new Option(new Locale("zh", "HK"), "繁體中文（香港）");
    private static final Option CHINESE_TW = new Option(new Locale("zh", "TW"), "繁體中文（台灣）");
    private static final Option JAPANESE = new Option(new Locale("ja", "JP"), "日本語");
    private static final Option GERMAN = new Option(new Locale("de", "DE"), "Deutsch");
    private static final Option DUTCH = new Option(new Locale("nl", "NL"), "Nederlands");
    private static final Option SPANISH = new Option(new Locale("es", "ES"), "Español");
    private static final Option PORTUGUESE = new Option(new Locale("pt", "PT"), "Português (Portugal)");
    private static final Option PORTUGUESE_Brazil = new Option(new Locale("pt", "BR"), "Português (Brasil)");
    private static final Option RUSSIAN = new Option(new Locale("ru", "RU"), "русский");
    private static final Option ITALIAN = new Option(new Locale("it", "IT"), "italiano");
    private static final Option FRENCH = new Option(new Locale("fr", "FR"), "français");
    private static final Option SPANISH_LATIN_AMERICA = new Option(new Locale("es", "US"), "Español (Latinoamérica)");
    private static final Option POLAND = new Option(new Locale("pl", "PL"), "Polski");
    private static final Option ARAB = new Option(new Locale(ArchiveStreamFactory.f8911AR, "AR"), "اللغة العربية");
    private static final Option TURKEY = new Option(new Locale("tr", "TR"), "Türkçe");
    private static final Option CZECH = new Option(new Locale("cs", "CZ"), "Čeština");
    private static final Option THAI_LAND = new Option(new Locale("th", "TH"), "ไทย");
    private static final Option INDONESIAN = new Option(new Locale("in", "ID"), "Bahasa Indonesia");
    private static final Option ISRAELI = new Option(new Locale("iw", "IL"), "Israeli");
    private static final Option SWEDISH = new Option(new Locale("sv", "SE"), "Swedish");
    private static final Option HUNGARIAN = new Option(new Locale("hu", "HU"), "Magyar");
    private static final Option BULGARIAN = new Option(new Locale("bg", "BG"), "Български език");
    private static final Option VIETNAMESE = new Option(new Locale("vi", "VN"), "Tiếng Việt");
    private static final Option SLOVENIAN = new Option(new Locale("sl", "SI"), "Slovenian");
    private static final Option CROATIAN = new Option(new Locale("hr", "HR"), "Hrvatski");
    private static final Option SLOVAK = new Option(new Locale("sk", "SK"), "Slovenčina");
    private static final Option ESTONIA = new Option(new Locale(LinkFormat.END_POINT_TYPE, "EE"), "väärtused");
    private static final Option SERBIAN = new Option(new Locale("sr", "RS"), "Cрпски");
    private static final Option ROMANIAN = new Option(new Locale("ro", "RO"), "Română");
    private static final Option MALAY = new Option(new Locale("ms", "MY"), "Bahasa Melayu");
    private static final Option GEORGIA = new Option(new Locale("ka", "GE"), "ქართული");

    /* renamed from: list$delegate, reason: from kotlin metadata */
    private static final Lazy list = LazyKt.lazy(new Function0<List<? extends Option>>() { // from class: com.pudutech.resources.language.SupportedLocale$list$2
        @Override // kotlin.jvm.functions.Function0
        public final List<? extends Option> invoke() {
            return CollectionsKt.listOf((Object[]) new Option[]{SupportedLocale.INSTANCE.getENGLISH(), SupportedLocale.INSTANCE.getCHINESE(), SupportedLocale.INSTANCE.getKOREAN(), SupportedLocale.INSTANCE.getCHINESE_HK(), SupportedLocale.INSTANCE.getCHINESE_TW(), SupportedLocale.INSTANCE.getJAPANESE(), SupportedLocale.INSTANCE.getGERMAN(), SupportedLocale.INSTANCE.getDUTCH(), SupportedLocale.INSTANCE.getSPANISH(), SupportedLocale.INSTANCE.getPORTUGUESE(), SupportedLocale.INSTANCE.getPORTUGUESE_Brazil(), SupportedLocale.INSTANCE.getRUSSIAN(), SupportedLocale.INSTANCE.getITALIAN(), SupportedLocale.INSTANCE.getFRENCH(), SupportedLocale.INSTANCE.getPOLAND(), SupportedLocale.INSTANCE.getARAB(), SupportedLocale.INSTANCE.getTURKEY(), SupportedLocale.INSTANCE.getCZECH(), SupportedLocale.INSTANCE.getTHAI_LAND(), SupportedLocale.INSTANCE.getINDONESIAN(), SupportedLocale.INSTANCE.getISRAELI(), SupportedLocale.INSTANCE.getSWEDISH(), SupportedLocale.INSTANCE.getSPANISH_LATIN_AMERICA(), SupportedLocale.INSTANCE.getHUNGARIAN(), SupportedLocale.INSTANCE.getBULGARIAN(), SupportedLocale.INSTANCE.getVIETNAMESE(), SupportedLocale.INSTANCE.getSLOVENIAN(), SupportedLocale.INSTANCE.getCROATIAN(), SupportedLocale.INSTANCE.getSLOVAK(), SupportedLocale.INSTANCE.getESTONIA(), SupportedLocale.INSTANCE.getSERBIAN(), SupportedLocale.INSTANCE.getROMANIAN(), SupportedLocale.INSTANCE.getMALAY(), SupportedLocale.INSTANCE.getGEORGIA()});
        }
    });
    private static final Option default = CHINESE;

    public final List<Option> getList() {
        return (List) list.getValue();
    }

    private SupportedLocale() {
    }

    public final Option getENGLISH() {
        return ENGLISH;
    }

    public final Option getCHINESE() {
        return CHINESE;
    }

    public final Option getKOREAN() {
        return KOREAN;
    }

    public final Option getCHINESE_HK() {
        return CHINESE_HK;
    }

    public final Option getCHINESE_TW() {
        return CHINESE_TW;
    }

    public final Option getJAPANESE() {
        return JAPANESE;
    }

    public final Option getGERMAN() {
        return GERMAN;
    }

    public final Option getDUTCH() {
        return DUTCH;
    }

    public final Option getSPANISH() {
        return SPANISH;
    }

    public final Option getPORTUGUESE() {
        return PORTUGUESE;
    }

    public final Option getPORTUGUESE_Brazil() {
        return PORTUGUESE_Brazil;
    }

    public final Option getRUSSIAN() {
        return RUSSIAN;
    }

    public final Option getITALIAN() {
        return ITALIAN;
    }

    public final Option getFRENCH() {
        return FRENCH;
    }

    public final Option getSPANISH_LATIN_AMERICA() {
        return SPANISH_LATIN_AMERICA;
    }

    public final Option getPOLAND() {
        return POLAND;
    }

    public final Option getARAB() {
        return ARAB;
    }

    public final Option getTURKEY() {
        return TURKEY;
    }

    public final Option getCZECH() {
        return CZECH;
    }

    public final Option getTHAI_LAND() {
        return THAI_LAND;
    }

    public final Option getINDONESIAN() {
        return INDONESIAN;
    }

    public final Option getISRAELI() {
        return ISRAELI;
    }

    public final Option getSWEDISH() {
        return SWEDISH;
    }

    public final Option getHUNGARIAN() {
        return HUNGARIAN;
    }

    public final Option getBULGARIAN() {
        return BULGARIAN;
    }

    public final Option getVIETNAMESE() {
        return VIETNAMESE;
    }

    public final Option getSLOVENIAN() {
        return SLOVENIAN;
    }

    public final Option getCROATIAN() {
        return CROATIAN;
    }

    public final Option getSLOVAK() {
        return SLOVAK;
    }

    public final Option getESTONIA() {
        return ESTONIA;
    }

    public final Option getSERBIAN() {
        return SERBIAN;
    }

    public final Option getROMANIAN() {
        return ROMANIAN;
    }

    public final Option getMALAY() {
        return MALAY;
    }

    public final Option getGEORGIA() {
        return GEORGIA;
    }

    public final Option getDefault() {
        return default;
    }
}
