package org.hash.mock.debug.view.sheet.entity;

import org.hash.mock.debug.view.radio.GroupRadioView;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class RadioEntity extends SimpleEntity {
    private int checkPosition;
    private String groupBtnCode;
    private String groupBtnName;
    private GroupRadioView.OnGroupBtnClickListener onCheckedChangeListener;

    public RadioEntity setOnCheckedChangeListener(GroupRadioView.OnGroupBtnClickListener onGroupBtnClickListener) {
        this.onCheckedChangeListener = onGroupBtnClickListener;
        return this;
    }

    public GroupRadioView.OnGroupBtnClickListener getOnCheckedChangeListener() {
        return this.onCheckedChangeListener;
    }

    public String getGroupBtnName() {
        return this.groupBtnName;
    }

    public RadioEntity setGroupBtnName(String str) {
        this.groupBtnName = str;
        return this;
    }

    public String getGroupBtnCode() {
        return this.groupBtnCode;
    }

    public RadioEntity setGroupBtnCode(String str) {
        this.groupBtnCode = str;
        return this;
    }

    public int getCheckPosition() {
        return this.checkPosition;
    }

    public RadioEntity setCheckPosition(int i) {
        this.checkPosition = i;
        return this;
    }
}
