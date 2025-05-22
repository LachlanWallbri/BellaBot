package com.pudutech.bumblebee.presenter.utils.ext;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ListExt.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\u001a \u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0004\u0012\u0002H\u00020\u0003j\b\u0012\u0004\u0012\u0002H\u0002`\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0005¨\u0006\u0006"}, m3961d2 = {"toCompactString", "", ExifInterface.LONGITUDE_EAST, "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "", "module_bumblebee_presenter_robotRelease"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ListExtKt {
    public static final <E> String toCompactString(ArrayList<E> toCompactString) {
        Intrinsics.checkParameterIsNotNull(toCompactString, "$this$toCompactString");
        Iterator<E> it = toCompactString.iterator();
        Intrinsics.checkExpressionValueIsNotNull(it, "iterator()");
        if (!it.hasNext()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        while (true) {
            Object next = it.next();
            if (next == toCompactString) {
                next = "(this Collection)";
            }
            sb.append(next);
            if (!it.hasNext()) {
                String sb2 = sb.toString();
                Intrinsics.checkExpressionValueIsNotNull(sb2, "sb.toString()");
                return sb2;
            }
            sb.append(',');
        }
    }

    public static final <E> String toCompactString(List<? extends E> toCompactString) {
        Intrinsics.checkParameterIsNotNull(toCompactString, "$this$toCompactString");
        Iterator<? extends E> it = toCompactString.iterator();
        if (!it.hasNext()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        while (true) {
            Object next = it.next();
            if (next == toCompactString) {
                next = "(this Collection)";
            }
            sb.append(next);
            if (!it.hasNext()) {
                String sb2 = sb.toString();
                Intrinsics.checkExpressionValueIsNotNull(sb2, "sb.toString()");
                return sb2;
            }
            sb.append(',');
        }
    }
}
