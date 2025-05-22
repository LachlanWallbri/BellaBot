package com.iflytek.cloud.util;

import android.text.TextUtils;
import com.iflytek.cloud.msc.util.log.DebugLog;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* loaded from: classes3.dex */
public class UserWords {

    /* renamed from: a */
    private Hashtable<String, ArrayList<String>> f3489a;

    public UserWords() {
        this.f3489a = null;
        this.f3489a = new Hashtable<>();
    }

    public UserWords(String str) {
        this.f3489a = null;
        this.f3489a = new Hashtable<>();
        m2304a(str);
    }

    public boolean hasKey(String str) {
        return this.f3489a.containsKey(str);
    }

    public boolean putWord(String str) {
        return putWord("default", str);
    }

    public boolean putWord(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        ArrayList<String> words = getWords(str);
        if (words != null) {
            m2305a(words, str2);
            return true;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        m2305a(arrayList, str2);
        this.f3489a.put(str, arrayList);
        return true;
    }

    public boolean putWords(ArrayList<String> arrayList) {
        return putWords("default", arrayList);
    }

    public boolean putWords(String str, ArrayList<String> arrayList) {
        if (TextUtils.isEmpty(str) || arrayList == null) {
            return false;
        }
        ArrayList<String> words = getWords(str);
        if (words != null) {
            m2306a(words, arrayList);
            return true;
        }
        ArrayList<String> arrayList2 = new ArrayList<>();
        m2306a(arrayList2, arrayList);
        this.f3489a.put(str, arrayList2);
        return true;
    }

    /* renamed from: a */
    private boolean m2305a(ArrayList<String> arrayList, String str) {
        if (arrayList == null || arrayList.contains(str)) {
            return false;
        }
        arrayList.add(str);
        return true;
    }

    /* renamed from: a */
    private boolean m2306a(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        if (arrayList == null || arrayList2 == null) {
            return false;
        }
        Iterator<String> it = arrayList2.iterator();
        while (it.hasNext()) {
            m2305a(arrayList, it.next());
        }
        return true;
    }

    public ArrayList<String> getWords() {
        return getWords("default");
    }

    public ArrayList<String> getKeys() {
        Hashtable<String, ArrayList<String>> hashtable = this.f3489a;
        if (hashtable == null || hashtable.isEmpty()) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator<String> it = this.f3489a.keySet().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    public ArrayList<String> getWords(String str) {
        return this.f3489a.get(str);
    }

    /* renamed from: a */
    private void m2304a(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                DebugLog.LogD("userword is null...");
                return;
            }
            JSONArray jSONArray = new JSONObject(new JSONTokener(str)).getJSONArray("userword");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                JSONArray jSONArray2 = jSONObject.getJSONArray("words");
                ArrayList<String> arrayList = new ArrayList<>();
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    String obj = jSONArray2.get(i2).toString();
                    if (!arrayList.contains(obj)) {
                        arrayList.add(obj);
                    }
                }
                this.f3489a.put(jSONObject.get("name").toString(), arrayList);
            }
        } catch (JSONException e) {
            DebugLog.LogE(e);
        }
    }

    /* renamed from: a */
    private String m2303a() {
        try {
            if (this.f3489a == null) {
                DebugLog.LogD("mwords is null...");
                return null;
            }
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            for (Map.Entry<String, ArrayList<String>> entry : this.f3489a.entrySet()) {
                JSONObject jSONObject2 = new JSONObject();
                JSONArray jSONArray2 = new JSONArray();
                Iterator<String> it = entry.getValue().iterator();
                while (it.hasNext()) {
                    jSONArray2.put(it.next());
                }
                jSONObject2.put("words", jSONArray2);
                jSONObject2.put("name", entry.getKey());
                jSONArray.put(jSONObject2);
            }
            jSONObject.put("userword", jSONArray);
            return jSONObject.toString();
        } catch (JSONException e) {
            DebugLog.LogE(e);
            return null;
        }
    }

    public String toString() {
        return m2303a();
    }
}
