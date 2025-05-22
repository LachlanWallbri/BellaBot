package com.pudutech.bumblebee.presenter.delivery_task.input_method_task;

import com.iflytek.aiui.AIUIConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WordModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\bJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00052\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\r\"\u0004\b\u0010\u0010\u000fR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\r\"\u0004\b\u0011\u0010\u000f¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/delivery_task/input_method_task/WordModel;", "", AIUIConstant.KEY_CONTENT, "", "isSelectable", "", "isNumber", "isExistInMap", "(Ljava/lang/String;ZZZ)V", "getContent", "()Ljava/lang/String;", "setContent", "(Ljava/lang/String;)V", "()Z", "setExistInMap", "(Z)V", "setNumber", "setSelectable", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class WordModel {
    private String content;
    private boolean isExistInMap;
    private boolean isNumber;
    private boolean isSelectable;

    public WordModel() {
        this(null, false, false, false, 15, null);
    }

    public static /* synthetic */ WordModel copy$default(WordModel wordModel, String str, boolean z, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = wordModel.content;
        }
        if ((i & 2) != 0) {
            z = wordModel.isSelectable;
        }
        if ((i & 4) != 0) {
            z2 = wordModel.isNumber;
        }
        if ((i & 8) != 0) {
            z3 = wordModel.isExistInMap;
        }
        return wordModel.copy(str, z, z2, z3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getContent() {
        return this.content;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getIsSelectable() {
        return this.isSelectable;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getIsNumber() {
        return this.isNumber;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getIsExistInMap() {
        return this.isExistInMap;
    }

    public final WordModel copy(String content, boolean isSelectable, boolean isNumber, boolean isExistInMap) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        return new WordModel(content, isSelectable, isNumber, isExistInMap);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WordModel)) {
            return false;
        }
        WordModel wordModel = (WordModel) other;
        return Intrinsics.areEqual(this.content, wordModel.content) && this.isSelectable == wordModel.isSelectable && this.isNumber == wordModel.isNumber && this.isExistInMap == wordModel.isExistInMap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.content;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        boolean z = this.isSelectable;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode + i) * 31;
        boolean z2 = this.isNumber;
        int i3 = z2;
        if (z2 != 0) {
            i3 = 1;
        }
        int i4 = (i2 + i3) * 31;
        boolean z3 = this.isExistInMap;
        int i5 = z3;
        if (z3 != 0) {
            i5 = 1;
        }
        return i4 + i5;
    }

    public String toString() {
        return "WordModel(content=" + this.content + ", isSelectable=" + this.isSelectable + ", isNumber=" + this.isNumber + ", isExistInMap=" + this.isExistInMap + ")";
    }

    public WordModel(String content, boolean z, boolean z2, boolean z3) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        this.content = content;
        this.isSelectable = z;
        this.isNumber = z2;
        this.isExistInMap = z3;
    }

    public final String getContent() {
        return this.content;
    }

    public final void setContent(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.content = str;
    }

    public final boolean isSelectable() {
        return this.isSelectable;
    }

    public final void setSelectable(boolean z) {
        this.isSelectable = z;
    }

    public final boolean isNumber() {
        return this.isNumber;
    }

    public final void setNumber(boolean z) {
        this.isNumber = z;
    }

    public /* synthetic */ WordModel(String str, boolean z, boolean z2, boolean z3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? true : z, (i & 4) != 0 ? false : z2, (i & 8) != 0 ? true : z3);
    }

    public final boolean isExistInMap() {
        return this.isExistInMap;
    }

    public final void setExistInMap(boolean z) {
        this.isExistInMap = z;
    }
}
