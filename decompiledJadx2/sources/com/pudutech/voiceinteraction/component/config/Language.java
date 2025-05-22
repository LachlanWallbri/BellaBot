package com.pudutech.voiceinteraction.component.config;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: Language.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001e\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b ¨\u0006!"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/config/Language;", "", "code", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getCode", "()Ljava/lang/String;", "Chinese", "Japanese", "Korean", "Spanish", "English", "French", "Italian", "Russian", "German", "ChineseHk", "Dutch", "Portuguese", "PortugueseBr", "Polish", "Thai", "Turkish", "Indonesian", "Swedish", "Czech", "Indonesia", "Sweden", "Brazilian", "Arabic", "EuropeanPortuguese", "Poland", "ChineseTW", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public enum Language {
    Chinese("zh-CN"),
    Japanese("ja-JP"),
    Korean("ko-KR"),
    Spanish("es-CO"),
    English("en-US"),
    French("fr-FR"),
    Italian("it-IT"),
    Russian("ru-RU"),
    German("de-DE"),
    ChineseHk("yue-HK"),
    Dutch("nl-NL"),
    Portuguese("pt-PT"),
    PortugueseBr("pt-BR"),
    Polish("pl-PL"),
    Thai("th-TH"),
    Turkish("tr-TR"),
    Indonesian("id-ID"),
    Swedish("sv-SE"),
    Czech("cs-CZ"),
    Indonesia("id-ID"),
    Sweden("sv-SE"),
    Brazilian("pt-BR"),
    Arabic("ar-SA"),
    EuropeanPortuguese("pt-PT"),
    Poland("pl-PL"),
    ChineseTW("ch-TW");

    private final String code;

    Language(String str) {
        this.code = str;
    }

    public final String getCode() {
        return this.code;
    }
}
