package com.pudutech.mpmodule.bean;

import com.alibaba.fastjson.JSON;
import com.pudutech.mpmodule.utils.StringUtil;
import java.io.Serializable;
import java.util.List;
import org.greenrobot.greendao.converter.PropertyConverter;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class PlayMode implements Serializable {
    public static final String KEY_PLAY_MODE = "key_play_mode";
    private static final long serialVersionUID = -2502676053329759960L;

    /* renamed from: id */
    private Long f6786id;
    private ModeEnum mode;
    private String modeId;
    private List<String> playListIds;

    public PlayMode(String str, ModeEnum modeEnum, List<String> list) {
        this.modeId = str;
        this.mode = modeEnum;
        this.playListIds = list;
    }

    public PlayMode() {
    }

    public PlayMode(Long l, String str, ModeEnum modeEnum, List<String> list) {
        this.f6786id = l;
        this.modeId = str;
        this.mode = modeEnum;
        this.playListIds = list;
    }

    public Long getId() {
        return this.f6786id;
    }

    public void setId(Long l) {
        this.f6786id = l;
    }

    public String getModeId() {
        return this.modeId;
    }

    public void setModeId(String str) {
        this.modeId = str;
    }

    public ModeEnum getMode() {
        return this.mode;
    }

    public void setMode(ModeEnum modeEnum) {
        this.mode = modeEnum;
    }

    public String toString() {
        return "PlayMode{id=" + this.f6786id + ", modeId='" + this.modeId + "', mode=" + this.mode + ", playListIds=" + this.playListIds + '}';
    }

    public List<String> getPlayListIds() {
        return this.playListIds;
    }

    public void setPlayListIds(List<String> list) {
        this.playListIds = list;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes6.dex */
    public static class CListStringConverter implements PropertyConverter<List<String>, String> {
        @Override // org.greenrobot.greendao.converter.PropertyConverter
        public List<String> convertToEntityProperty(String str) {
            if (StringUtil.isEmpty(str)) {
                return null;
            }
            try {
                return JSON.parseArray(str, String.class);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override // org.greenrobot.greendao.converter.PropertyConverter
        public String convertToDatabaseValue(List<String> list) {
            if (list == null) {
                return null;
            }
            try {
                return JSON.toJSONString(list);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes6.dex */
    public static class CEnumStringConverter implements PropertyConverter<ModeEnum, String> {
        @Override // org.greenrobot.greendao.converter.PropertyConverter
        public ModeEnum convertToEntityProperty(String str) {
            if (StringUtil.isEmpty(str)) {
                return null;
            }
            for (ModeEnum modeEnum : ModeEnum.values()) {
                if (StringUtil.equals(modeEnum.getName(), str)) {
                    return modeEnum;
                }
            }
            return null;
        }

        @Override // org.greenrobot.greendao.converter.PropertyConverter
        public String convertToDatabaseValue(ModeEnum modeEnum) {
            if (modeEnum == null) {
                return null;
            }
            return modeEnum.getName();
        }
    }
}
