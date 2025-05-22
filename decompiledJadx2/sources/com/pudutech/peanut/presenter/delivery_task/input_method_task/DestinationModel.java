package com.pudutech.peanut.presenter.delivery_task.input_method_task;

import com.iflytek.aiui.AIUIConstant;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DestinationModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0017\u001a\u00020\u0004H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bRB\u0010\t\u001a*\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u000b0\nj\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u000b`\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R(\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/delivery_task/input_method_task/DestinationModel;", "", "()V", AIUIConstant.KEY_CONTENT, "", "getContent", "()Ljava/lang/String;", "setContent", "(Ljava/lang/String;)V", "inputtedChars", "Ljava/util/ArrayList;", "Lkotlin/Pair;", "", "Lkotlin/collections/ArrayList;", "getInputtedChars", "()Ljava/util/ArrayList;", "setInputtedChars", "(Ljava/util/ArrayList;)V", "nextChar", "getNextChar", "()Lkotlin/Pair;", "setNextChar", "(Lkotlin/Pair;)V", "toString", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class DestinationModel {
    private String content = "";
    private ArrayList<Pair<Integer, Integer>> inputtedChars = new ArrayList<>();
    private Pair<Integer, Integer> nextChar;

    public final String getContent() {
        return this.content;
    }

    public final void setContent(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.content = str;
    }

    public final ArrayList<Pair<Integer, Integer>> getInputtedChars() {
        return this.inputtedChars;
    }

    public final void setInputtedChars(ArrayList<Pair<Integer, Integer>> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
        this.inputtedChars = arrayList;
    }

    public final Pair<Integer, Integer> getNextChar() {
        return this.nextChar;
    }

    public final void setNextChar(Pair<Integer, Integer> pair) {
        this.nextChar = pair;
    }

    public String toString() {
        return "content = " + this.content + " , inputtedChars = " + this.inputtedChars + ", nextChar = " + this.nextChar;
    }
}
