package com.pudutech.mirsdk.map.elements;

import androidx.core.os.EnvironmentCompat;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.compat.topo.MapElement;
import java.util.ArrayList;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: Source.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0013\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0011\u00105\u001a\u0002002\u0006\u00106\u001a\u00020\u0000H\u0096\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\f\"\u0004\b\u0017\u0010\u000eR\u001a\u0010\u0018\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0012\"\u0004\b\u001a\u0010\u0014R\u001a\u0010\u001b\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0012\"\u0004\b\u001d\u0010\u0014R\u0019\u0010\u001e\u001a\n  *\u0004\u0018\u00010\u001f0\u001f¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\"\u0010)\u001a\n\u0012\u0004\u0012\u00020$\u0018\u00010*X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001a\u0010/\u001a\u000200X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104¨\u00067"}, m3961d2 = {"Lcom/pudutech/mirsdk/map/elements/Source;", "", "()V", MapElement.Key.DIR, "", "getDir", "()D", "setDir", "(D)V", "doubleDir", "", "getDoubleDir", "()Z", "setDoubleDir", "(Z)V", MapElement.Key.GROUP, "", "getGroup", "()Ljava/lang/String;", "setGroup", "(Ljava/lang/String;)V", "high_precision", "getHigh_precision", "setHigh_precision", "id", "getId", "setId", "mode", "getMode", "setMode", "pattern", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "getPattern", "()Ljava/util/regex/Pattern;", RequestParameters.POSITION, "", "getPosition", "()[D", "setPosition", "([D)V", "rect", "Ljava/util/ArrayList;", "getRect", "()Ljava/util/ArrayList;", "setRect", "(Ljava/util/ArrayList;)V", "sort_weight", "", "getSort_weight", "()I", "setSort_weight", "(I)V", "compareTo", "other", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class Source implements Comparable<Source> {
    private double dir;
    private boolean doubleDir;
    private boolean high_precision;
    private ArrayList<double[]> rect;
    private int sort_weight;
    private String id = " ";
    private double[] position = {0.0d, 0.0d};
    private String mode = EnvironmentCompat.MEDIA_UNKNOWN;
    private String group = "";
    private final Pattern pattern = Pattern.compile("(^(^[A-Za-z]?)[0-9]+)([A-Za-z]?$)");

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.id = str;
    }

    public final double[] getPosition() {
        return this.position;
    }

    public final void setPosition(double[] dArr) {
        Intrinsics.checkParameterIsNotNull(dArr, "<set-?>");
        this.position = dArr;
    }

    public final double getDir() {
        return this.dir;
    }

    public final void setDir(double d) {
        this.dir = d;
    }

    public final boolean getDoubleDir() {
        return this.doubleDir;
    }

    public final void setDoubleDir(boolean z) {
        this.doubleDir = z;
    }

    public final ArrayList<double[]> getRect() {
        return this.rect;
    }

    public final void setRect(ArrayList<double[]> arrayList) {
        this.rect = arrayList;
    }

    public final String getMode() {
        return this.mode;
    }

    public final void setMode(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.mode = str;
    }

    public final String getGroup() {
        return this.group;
    }

    public final void setGroup(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.group = str;
    }

    public final int getSort_weight() {
        return this.sort_weight;
    }

    public final void setSort_weight(int i) {
        this.sort_weight = i;
    }

    public final boolean getHigh_precision() {
        return this.high_precision;
    }

    public final void setHigh_precision(boolean z) {
        this.high_precision = z;
    }

    public final Pattern getPattern() {
        return this.pattern;
    }

    @Override // java.lang.Comparable
    public int compareTo(Source other) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        int i = this.sort_weight;
        int i2 = other.sort_weight;
        if (i != i2) {
            return i < i2 ? -1 : 1;
        }
        boolean find = this.pattern.matcher(this.id).find();
        boolean find2 = this.pattern.matcher(other.id).find();
        Pdlog.m3273d("Source", "id " + this.id + " other " + other.id + ' ' + find2);
        if (find && !find2) {
            return -1;
        }
        if (find || !find2) {
            if (find || find2) {
                if (find && find2) {
                    Pattern compile = Pattern.compile("[A-Za-z]");
                    String str = this.id;
                    if (str == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                    String substring = str.substring(0, 1);
                    Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    boolean find3 = compile.matcher(substring).find();
                    String str2 = other.id;
                    if (str2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                    String substring2 = str2.substring(0, 1);
                    Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    boolean find4 = compile.matcher(substring2).find();
                    if (!find3 || find4) {
                        if (!find3 && find4) {
                            return -1;
                        }
                        if (find3 && find4) {
                            String str3 = this.id;
                            int length = str3.length() - 1;
                            if (str3 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                            }
                            String substring3 = str3.substring(length);
                            Intrinsics.checkExpressionValueIsNotNull(substring3, "(this as java.lang.String).substring(startIndex)");
                            boolean find5 = compile.matcher(substring3).find();
                            String str4 = other.id;
                            int length2 = str4.length() - 1;
                            if (str4 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                            }
                            String substring4 = str4.substring(length2);
                            Intrinsics.checkExpressionValueIsNotNull(substring4, "(this as java.lang.String).substring(startIndex)");
                            boolean find6 = compile.matcher(substring4).find();
                            String str5 = this.id;
                            int length3 = str5.length();
                            if (find5) {
                                length3--;
                            }
                            if (str5 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                            }
                            String substring5 = str5.substring(1, length3);
                            Intrinsics.checkExpressionValueIsNotNull(substring5, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                            int parseInt = Integer.parseInt(substring5);
                            String str6 = other.id;
                            int length4 = str6.length();
                            if (find6) {
                                length4--;
                            }
                            if (str6 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                            }
                            String substring6 = str6.substring(1, length4);
                            Intrinsics.checkExpressionValueIsNotNull(substring6, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                            int parseInt2 = Integer.parseInt(substring6);
                            String str7 = this.id;
                            if (str7 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                            }
                            String substring7 = str7.substring(0, 1);
                            Intrinsics.checkExpressionValueIsNotNull(substring7, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                            String str8 = other.id;
                            if (str8 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                            }
                            String substring8 = str8.substring(0, 1);
                            Intrinsics.checkExpressionValueIsNotNull(substring8, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                            int compareTo = substring7.compareTo(substring8);
                            if (compareTo == 0) {
                                if (parseInt <= parseInt2) {
                                    if (parseInt < parseInt2) {
                                        return -1;
                                    }
                                    if (!find5 || find6) {
                                        if (!find5 && find6) {
                                            return -1;
                                        }
                                        if ((find5 || find6) && find5 && find6) {
                                            String str9 = this.id;
                                            int length5 = str9.length() - 1;
                                            if (str9 == null) {
                                                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                                            }
                                            String substring9 = str9.substring(length5);
                                            Intrinsics.checkExpressionValueIsNotNull(substring9, "(this as java.lang.String).substring(startIndex)");
                                            String str10 = other.id;
                                            int length6 = str10.length() - 1;
                                            if (str10 == null) {
                                                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                                            }
                                            String substring10 = str10.substring(length6);
                                            Intrinsics.checkExpressionValueIsNotNull(substring10, "(this as java.lang.String).substring(startIndex)");
                                            int compareTo2 = substring9.compareTo(substring10);
                                            if (compareTo2 != 0 && compareTo2 <= 0) {
                                                return -1;
                                            }
                                        }
                                    }
                                }
                            } else if (compareTo < 0) {
                                return -1;
                            }
                        } else if (!find3 && !find4) {
                            String str11 = this.id;
                            int length7 = str11.length() - 1;
                            if (str11 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                            }
                            String substring11 = str11.substring(length7);
                            Intrinsics.checkExpressionValueIsNotNull(substring11, "(this as java.lang.String).substring(startIndex)");
                            boolean find7 = compile.matcher(substring11).find();
                            String str12 = other.id;
                            int length8 = str12.length() - 1;
                            if (str12 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                            }
                            String substring12 = str12.substring(length8);
                            Intrinsics.checkExpressionValueIsNotNull(substring12, "(this as java.lang.String).substring(startIndex)");
                            boolean find8 = compile.matcher(substring12).find();
                            String str13 = this.id;
                            int length9 = str13.length();
                            if (find7) {
                                length9--;
                            }
                            if (str13 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                            }
                            String substring13 = str13.substring(0, length9);
                            Intrinsics.checkExpressionValueIsNotNull(substring13, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                            int parseInt3 = Integer.parseInt(substring13);
                            String str14 = other.id;
                            int length10 = str14.length();
                            if (find8) {
                                length10--;
                            }
                            if (str14 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                            }
                            String substring14 = str14.substring(0, length10);
                            Intrinsics.checkExpressionValueIsNotNull(substring14, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                            int parseInt4 = Integer.parseInt(substring14);
                            if (parseInt3 <= parseInt4) {
                                if (parseInt3 < parseInt4) {
                                    return -1;
                                }
                                if (!find7 || find8) {
                                    if (!find7 && find8) {
                                        return -1;
                                    }
                                    if ((find7 || find8) && find7 && find8) {
                                        String str15 = this.id;
                                        int length11 = str15.length() - 1;
                                        if (str15 == null) {
                                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                                        }
                                        String substring15 = str15.substring(length11);
                                        Intrinsics.checkExpressionValueIsNotNull(substring15, "(this as java.lang.String).substring(startIndex)");
                                        String str16 = other.id;
                                        int length12 = str16.length() - 1;
                                        if (str16 == null) {
                                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                                        }
                                        String substring16 = str16.substring(length12);
                                        Intrinsics.checkExpressionValueIsNotNull(substring16, "(this as java.lang.String).substring(startIndex)");
                                        int compareTo3 = substring15.compareTo(substring16);
                                        if (compareTo3 != 0 && compareTo3 <= 0) {
                                            return -1;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (this.id.compareTo(other.id) <= 0) {
                return -1;
            }
        }
        return 1;
    }
}
