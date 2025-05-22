package org.nanohttpd.protocols.http.content;

import com.iflytek.speech.VoiceWakeuperAidl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.nanohttpd.protocols.http.response.Response;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class CookieHandler implements Iterable<String> {
    private final HashMap<String, String> cookies = new HashMap<>();
    private final ArrayList<Cookie> queue = new ArrayList<>();

    public CookieHandler(Map<String, String> map) {
        String str = map.get("cookie");
        if (str != null) {
            for (String str2 : str.split(VoiceWakeuperAidl.PARAMS_SEPARATE)) {
                String[] split = str2.trim().split("=");
                if (split.length == 2) {
                    this.cookies.put(split[0], split[1]);
                }
            }
        }
    }

    public void delete(String str) {
        set(str, "-delete-", -30);
    }

    @Override // java.lang.Iterable
    public Iterator<String> iterator() {
        return this.cookies.keySet().iterator();
    }

    public String read(String str) {
        return this.cookies.get(str);
    }

    public void set(Cookie cookie) {
        this.queue.add(cookie);
    }

    public void set(String str, String str2, int i) {
        this.queue.add(new Cookie(str, str2, Cookie.getHTTPTime(i)));
    }

    public void unloadQueue(Response response) {
        Iterator<Cookie> it = this.queue.iterator();
        while (it.hasNext()) {
            response.addCookieHeader(it.next().getHTTPHeader());
        }
    }
}
