package com.pudutech.mpmodule.bean;

import com.pudutech.mpmodule.C5441R;
import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public enum ModeEnum implements Serializable {
    DELIVERY_AND_RETURNING("1001", "配送-返航模式", C5441R.string.pdStr10_29),
    BIRTHDAY("1002", "生日模式", C5441R.string.pdStr10_30),
    BIRTHDAY_BACK("1003", "生日返航模式", C5441R.string.pdStr10_86),
    CRUISE("1004", "巡航模式", C5441R.string.pdStr10_31),
    ARRIVED("1005", "到达模式", C5441R.string.pdStr10_32),
    NEWYEAR("1006", "春节模式", C5441R.string.pdStr10_33),
    SPECIAL("1007", "特殊模式", C5441R.string.pdStr10_68),
    GREETER("1008", "门迎模式", C5441R.string.pdStr10_76),
    GUIDE("1009", "领位模式", C5441R.string.pdStr10_77),
    GUIDE_ARRIVED("1011", "领位-到达模式", C5441R.string.pdStr10_88),
    GUIDE_BACK("1010", "领位-返航模式", C5441R.string.pdStr10_84),
    RECYCLE("1012", "回盘模式", C5441R.string.pdStr10_82);


    /* renamed from: id */
    private final String f6785id;
    private final int listNameResources;
    private final String name;

    ModeEnum(String str, String str2, int i) {
        this.f6785id = str;
        this.name = str2;
        this.listNameResources = i;
    }

    public String getId() {
        return this.f6785id;
    }

    public String getName() {
        return this.name;
    }

    public int getListNameResources() {
        return this.listNameResources;
    }
}
