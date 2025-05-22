package com.iflytek.aiui.pro;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* renamed from: com.iflytek.aiui.pro.ay */
/* loaded from: classes.dex */
public class C3595ay {

    /* renamed from: com.iflytek.aiui.pro.ay$a */
    /* loaded from: classes3.dex */
    public static class a {

        /* renamed from: a */
        public final int f2413a;

        /* renamed from: b */
        public final String f2414b;

        /* renamed from: c */
        public final String f2415c;

        protected a(String str) throws NumberFormatException, IndexOutOfBoundsException {
            String[] split = str.split(":");
            this.f2413a = Integer.parseInt(split[0]);
            this.f2414b = split[1];
            this.f2415c = split[2];
        }

        public String toString() {
            return String.format(Locale.ENGLISH, "%d:%s:%s", Integer.valueOf(this.f2413a), this.f2414b, this.f2415c);
        }
    }

    /* renamed from: com.iflytek.aiui.pro.ay$b */
    /* loaded from: classes3.dex */
    public static final class b extends Exception {
        public b(int i) {
            super(String.format("The process %d does not belong to any application", Integer.valueOf(i)));
        }
    }

    /* renamed from: a */
    public static SharedPreferences m1110a(Context context) {
        return context.getSharedPreferences("iflytek_collect_state", 0);
    }

    /* renamed from: a */
    public static void m1111a(Context context, String str, Long l) {
        SharedPreferences.Editor edit = m1110a(context).edit();
        edit.putLong(str, l.longValue());
        edit.apply();
    }
}
