package com.loc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.text.TextUtils;
import com.loc.C3809ak;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: DBOperation.java */
/* renamed from: com.loc.af */
/* loaded from: classes4.dex */
public final class C3804af {

    /* renamed from: d */
    private static Map<Class<? extends InterfaceC3803ae>, InterfaceC3803ae> f3561d = new HashMap();

    /* renamed from: a */
    private C3809ak f3562a;

    /* renamed from: b */
    private SQLiteDatabase f3563b;

    /* renamed from: c */
    private InterfaceC3803ae f3564c;

    public C3804af(Context context, InterfaceC3803ae interfaceC3803ae) {
        try {
            this.f3562a = new C3809ak(context.getApplicationContext(), interfaceC3803ae.mo2416a(), interfaceC3803ae);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.f3564c = interfaceC3803ae;
    }

    public C3804af(Context context, InterfaceC3803ae interfaceC3803ae, String str) {
        try {
            boolean equals = "mounted".equals(Environment.getExternalStorageState());
            if (!TextUtils.isEmpty(str) && equals) {
                context = new C3809ak.a(context.getApplicationContext(), str);
            }
            this.f3562a = new C3809ak(context, interfaceC3803ae.mo2416a(), interfaceC3803ae);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.f3564c = interfaceC3803ae;
    }

    /* renamed from: a */
    private static ContentValues m2418a(Object obj, InterfaceC3805ag interfaceC3805ag) {
        ContentValues contentValues = new ContentValues();
        for (Field field : m2425a(obj.getClass(), interfaceC3805ag.m2436b())) {
            field.setAccessible(true);
            Annotation annotation = field.getAnnotation(InterfaceC3806ah.class);
            if (annotation != null) {
                InterfaceC3806ah interfaceC3806ah = (InterfaceC3806ah) annotation;
                switch (interfaceC3806ah.m2438b()) {
                    case 1:
                        contentValues.put(interfaceC3806ah.m2437a(), Short.valueOf(field.getShort(obj)));
                        break;
                    case 2:
                        contentValues.put(interfaceC3806ah.m2437a(), Integer.valueOf(field.getInt(obj)));
                        break;
                    case 3:
                        contentValues.put(interfaceC3806ah.m2437a(), Float.valueOf(field.getFloat(obj)));
                        break;
                    case 4:
                        contentValues.put(interfaceC3806ah.m2437a(), Double.valueOf(field.getDouble(obj)));
                        break;
                    case 5:
                        contentValues.put(interfaceC3806ah.m2437a(), Long.valueOf(field.getLong(obj)));
                        break;
                    case 6:
                        contentValues.put(interfaceC3806ah.m2437a(), (String) field.get(obj));
                        break;
                    case 7:
                        try {
                            contentValues.put(interfaceC3806ah.m2437a(), (byte[]) field.get(obj));
                            break;
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                            break;
                        }
                }
            }
        }
        return contentValues;
    }

    /* renamed from: a */
    private SQLiteDatabase m2419a() {
        try {
            if (this.f3563b == null || this.f3563b.isReadOnly()) {
                if (this.f3563b != null) {
                    this.f3563b.close();
                }
                this.f3563b = this.f3562a.getWritableDatabase();
            }
        } catch (Throwable th) {
            C3897w.m3249a(th, "DBOperation", "getWriteDatabase");
        }
        return this.f3563b;
    }

    /* renamed from: a */
    private SQLiteDatabase m2420a(boolean z) {
        try {
            if (this.f3563b == null) {
                this.f3563b = this.f3562a.getReadableDatabase();
            }
        } catch (Throwable th) {
            if (z) {
                th.printStackTrace();
            } else {
                C3897w.m3249a(th, "DBOperation", "getReadAbleDataBase");
            }
        }
        return this.f3563b;
    }

    /* renamed from: a */
    public static synchronized InterfaceC3803ae m2421a(Class<? extends InterfaceC3803ae> cls) throws IllegalAccessException, InstantiationException {
        InterfaceC3803ae interfaceC3803ae;
        synchronized (C3804af.class) {
            if (f3561d.get(cls) == null) {
                f3561d.put(cls, cls.newInstance());
            }
            interfaceC3803ae = f3561d.get(cls);
        }
        return interfaceC3803ae;
    }

    /* renamed from: a */
    private static <T> T m2422a(Cursor cursor, Class<T> cls, InterfaceC3805ag interfaceC3805ag) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Object valueOf;
        Field[] m2425a = m2425a((Class<?>) cls, interfaceC3805ag.m2436b());
        Constructor<T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
        declaredConstructor.setAccessible(true);
        T newInstance = declaredConstructor.newInstance(new Object[0]);
        for (Field field : m2425a) {
            field.setAccessible(true);
            Annotation annotation = field.getAnnotation(InterfaceC3806ah.class);
            if (annotation != null) {
                InterfaceC3806ah interfaceC3806ah = (InterfaceC3806ah) annotation;
                int m2438b = interfaceC3806ah.m2438b();
                int columnIndex = cursor.getColumnIndex(interfaceC3806ah.m2437a());
                switch (m2438b) {
                    case 1:
                        valueOf = Short.valueOf(cursor.getShort(columnIndex));
                        break;
                    case 2:
                        valueOf = Integer.valueOf(cursor.getInt(columnIndex));
                        break;
                    case 3:
                        valueOf = Float.valueOf(cursor.getFloat(columnIndex));
                        break;
                    case 4:
                        valueOf = Double.valueOf(cursor.getDouble(columnIndex));
                        break;
                    case 5:
                        valueOf = Long.valueOf(cursor.getLong(columnIndex));
                        break;
                    case 6:
                        valueOf = cursor.getString(columnIndex);
                        break;
                    case 7:
                        field.set(newInstance, cursor.getBlob(columnIndex));
                        continue;
                }
                field.set(newInstance, valueOf);
            }
        }
        return newInstance;
    }

    /* renamed from: a */
    private static <T> String m2423a(InterfaceC3805ag interfaceC3805ag) {
        if (interfaceC3805ag == null) {
            return null;
        }
        return interfaceC3805ag.m2435a();
    }

    /* renamed from: a */
    public static String m2424a(Map<String, String> map) {
        if (map == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String str : map.keySet()) {
            if (z) {
                sb.append(str);
                sb.append(" = '");
                sb.append(map.get(str));
                sb.append("'");
                z = false;
            } else {
                sb.append(" and ");
                sb.append(str);
                sb.append(" = '");
                sb.append(map.get(str));
                sb.append("'");
            }
        }
        return sb.toString();
    }

    /* renamed from: a */
    private static Field[] m2425a(Class<?> cls, boolean z) {
        if (cls == null) {
            return null;
        }
        return z ? cls.getSuperclass().getDeclaredFields() : cls.getDeclaredFields();
    }

    /* renamed from: b */
    private static <T> InterfaceC3805ag m2426b(Class<T> cls) {
        Annotation annotation = cls.getAnnotation(InterfaceC3805ag.class);
        if (annotation != null) {
            return (InterfaceC3805ag) annotation;
        }
        return null;
    }

    /* renamed from: a */
    public final <T> List<T> m2427a(String str, Class<T> cls, boolean z) {
        Cursor cursor;
        String str2;
        String str3;
        synchronized (this.f3564c) {
            ArrayList arrayList = new ArrayList();
            InterfaceC3805ag m2426b = m2426b((Class) cls);
            String m2423a = m2423a(m2426b);
            if (this.f3563b == null) {
                this.f3563b = m2420a(z);
            }
            if (this.f3563b == null || TextUtils.isEmpty(m2423a) || str == null) {
                return arrayList;
            }
            try {
                cursor = this.f3563b.query(m2423a, null, str, null, null, null, null);
            } catch (Throwable th) {
                th = th;
                cursor = null;
            }
            try {
            } catch (Throwable th2) {
                th = th2;
                if (!z) {
                    try {
                        C3897w.m3249a(th, "DataBase", "searchListData");
                    } finally {
                        if (cursor != null) {
                            try {
                                cursor.close();
                            } catch (Throwable th3) {
                                if (!z) {
                                    C3897w.m3249a(th3, "DataBase", "searchListData");
                                }
                            }
                        }
                        try {
                            if (this.f3563b != null) {
                                this.f3563b.close();
                                this.f3563b = null;
                            }
                        } catch (Throwable th4) {
                            if (!z) {
                                C3897w.m3249a(th4, "DataBase", "searchListData");
                            }
                        }
                    }
                }
                if (cursor != null) {
                    try {
                        cursor.close();
                    } catch (Throwable th5) {
                        if (!z) {
                            C3897w.m3249a(th5, "DataBase", "searchListData");
                        }
                    }
                }
                try {
                    if (this.f3563b != null) {
                        this.f3563b.close();
                        this.f3563b = null;
                    }
                } catch (Throwable th6) {
                    th = th6;
                    if (!z) {
                        str2 = "DataBase";
                        str3 = "searchListData";
                        C3897w.m3249a(th, str2, str3);
                    }
                }
                return arrayList;
            }
            if (cursor == null) {
                this.f3563b.close();
                this.f3563b = null;
                return arrayList;
            }
            while (cursor.moveToNext()) {
                arrayList.add(m2422a(cursor, cls, m2426b));
            }
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Throwable th7) {
                    if (!z) {
                        C3897w.m3249a(th7, "DataBase", "searchListData");
                    }
                }
            }
            try {
                if (this.f3563b != null) {
                    this.f3563b.close();
                    this.f3563b = null;
                }
            } catch (Throwable th8) {
                th = th8;
                if (!z) {
                    str2 = "DataBase";
                    str3 = "searchListData";
                    C3897w.m3249a(th, str2, str3);
                }
            }
            return arrayList;
        }
    }

    /* renamed from: a */
    public final <T> void m2428a(T t) {
        m2434b((C3804af) t);
    }

    /* renamed from: a */
    public final void m2429a(Object obj, String str) {
        synchronized (this.f3564c) {
            List m2427a = m2427a(str, (Class) obj.getClass(), false);
            if (m2427a != null && m2427a.size() != 0) {
                m2432a(str, obj, false);
            }
            m2434b((C3804af) obj);
        }
    }

    /* renamed from: a */
    public final <T> void m2430a(String str, Class<T> cls) {
        synchronized (this.f3564c) {
            String m2423a = m2423a(m2426b((Class) cls));
            if (TextUtils.isEmpty(m2423a)) {
                return;
            }
            this.f3563b = m2419a();
            if (this.f3563b == null) {
                return;
            }
            try {
                this.f3563b.delete(m2423a, str, null);
            } catch (Throwable th) {
                try {
                    C3897w.m3249a(th, "DataBase", "deleteData");
                    if (this.f3563b != null) {
                        this.f3563b.close();
                    }
                } catch (Throwable th2) {
                    if (this.f3563b != null) {
                        this.f3563b.close();
                        this.f3563b = null;
                    }
                    throw th2;
                }
            }
            if (this.f3563b != null) {
                this.f3563b.close();
                this.f3563b = null;
            }
        }
    }

    /* renamed from: a */
    public final <T> void m2431a(String str, Object obj) {
        m2432a(str, obj, false);
    }

    /* renamed from: a */
    public final <T> void m2432a(String str, Object obj, boolean z) {
        synchronized (this.f3564c) {
            if (obj == null) {
                return;
            }
            InterfaceC3805ag m2426b = m2426b((Class) obj.getClass());
            String m2423a = m2423a(m2426b);
            if (TextUtils.isEmpty(m2423a)) {
                return;
            }
            ContentValues m2418a = m2418a(obj, m2426b);
            if (m2418a == null) {
                return;
            }
            this.f3563b = m2419a();
            if (this.f3563b == null) {
                return;
            }
            try {
                this.f3563b.update(m2423a, m2418a, str, null);
            } catch (Throwable th) {
                try {
                    if (z) {
                        th.printStackTrace();
                    } else {
                        C3897w.m3249a(th, "DataBase", "updateData");
                    }
                    if (this.f3563b != null) {
                        this.f3563b.close();
                    }
                } finally {
                    if (this.f3563b != null) {
                        this.f3563b.close();
                        this.f3563b = null;
                    }
                }
            }
            if (this.f3563b != null) {
                this.f3563b.close();
                this.f3563b = null;
            }
        }
    }

    /* renamed from: b */
    public final <T> List<T> m2433b(String str, Class<T> cls) {
        return m2427a(str, (Class) cls, false);
    }

    /* renamed from: b */
    public final <T> void m2434b(T t) {
        ContentValues m2418a;
        synchronized (this.f3564c) {
            this.f3563b = m2419a();
            if (this.f3563b == null) {
                return;
            }
            try {
                SQLiteDatabase sQLiteDatabase = this.f3563b;
                InterfaceC3805ag m2426b = m2426b((Class) t.getClass());
                String m2423a = m2423a(m2426b);
                if (!TextUtils.isEmpty(m2423a) && t != null && sQLiteDatabase != null && (m2418a = m2418a(t, m2426b)) != null) {
                    sQLiteDatabase.insert(m2423a, null, m2418a);
                }
            } catch (Throwable th) {
                try {
                    C3897w.m3249a(th, "DataBase", "insertData");
                    if (this.f3563b != null) {
                        this.f3563b.close();
                    }
                } catch (Throwable th2) {
                    if (this.f3563b != null) {
                        this.f3563b.close();
                        this.f3563b = null;
                    }
                    throw th2;
                }
            }
            if (this.f3563b != null) {
                this.f3563b.close();
                this.f3563b = null;
            }
        }
    }
}
