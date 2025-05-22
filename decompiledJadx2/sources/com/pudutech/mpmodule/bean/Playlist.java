package com.pudutech.mpmodule.bean;

import com.alibaba.fastjson.JSON;
import com.pudutech.mpmodule.media.Media;
import com.pudutech.mpmodule.utils.Lists;
import com.pudutech.mpmodule.utils.StringUtil;
import java.io.Serializable;
import java.util.List;
import org.greenrobot.greendao.converter.PropertyConverter;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class Playlist implements Serializable {
    public static final String KEY_LIST_ID = "key_list_id";
    public static final String KEY_LIST_NAME = "key_list_name";
    public static final String KEY_PLAYLIST = "key_playlist";
    private static final long serialVersionUID = -176705037413536494L;
    private ModeEnum defaultMode;

    /* renamed from: id */
    private Long f6787id;
    private boolean isCustom;
    private String listId;
    private String listName;
    private List<Media> mediaList;

    public Playlist(Long l, String str, String str2, boolean z, List<Media> list, ModeEnum modeEnum) {
        this.mediaList = Lists.newArrayList();
        this.f6787id = l;
        this.listId = str;
        this.listName = str2;
        this.isCustom = z;
        this.mediaList = list;
        this.defaultMode = modeEnum;
    }

    public Playlist(String str, String str2, boolean z, List<Media> list) {
        this.mediaList = Lists.newArrayList();
        this.listId = str;
        this.listName = str2;
        this.isCustom = z;
        this.mediaList = list;
    }

    public Playlist(String str, String str2, boolean z) {
        this.mediaList = Lists.newArrayList();
        this.listId = str;
        this.listName = str2;
        this.isCustom = z;
    }

    public Playlist(String str, String str2, boolean z, ModeEnum modeEnum) {
        this.mediaList = Lists.newArrayList();
        this.listId = str;
        this.listName = str2;
        this.isCustom = z;
        this.defaultMode = modeEnum;
    }

    public Playlist() {
        this.mediaList = Lists.newArrayList();
    }

    public int hashCode() {
        try {
            return this.listId.hashCode();
        } catch (NullPointerException e) {
            e.printStackTrace();
            return super.hashCode();
        }
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Playlist)) {
            return false;
        }
        try {
            return StringUtil.equals(this.listId, ((Playlist) obj).getListId());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return super.equals(obj);
        }
    }

    public Long getId() {
        return this.f6787id;
    }

    public void setId(Long l) {
        this.f6787id = l;
    }

    public String getListId() {
        return this.listId;
    }

    public void setListId(String str) {
        this.listId = str;
    }

    public String getListName() {
        return this.listName;
    }

    public void setListName(String str) {
        this.listName = str;
    }

    public boolean getIsCustom() {
        return this.isCustom;
    }

    public void setIsCustom(boolean z) {
        this.isCustom = z;
    }

    public List<Media> getMediaList() {
        return this.mediaList;
    }

    public void setMediaList(List<Media> list) {
        this.mediaList = list;
    }

    public ModeEnum getDefaultMode() {
        return this.defaultMode;
    }

    public void setDefaultMode(ModeEnum modeEnum) {
        this.defaultMode = modeEnum;
    }

    public String toString() {
        return "Playlist{id=" + this.f6787id + ", listId='" + this.listId + "', listName='" + this.listName + "', isCustom=" + this.isCustom + ", mediaList=" + this.mediaList + ", defaultMode=" + this.defaultMode + '}';
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes6.dex */
    public static class CListStringConverter implements PropertyConverter<List<Media>, String> {
        @Override // org.greenrobot.greendao.converter.PropertyConverter
        public List<Media> convertToEntityProperty(String str) {
            if (StringUtil.isEmpty(str)) {
                return null;
            }
            try {
                return JSON.parseArray(str, Media.class);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override // org.greenrobot.greendao.converter.PropertyConverter
        public String convertToDatabaseValue(List<Media> list) {
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
