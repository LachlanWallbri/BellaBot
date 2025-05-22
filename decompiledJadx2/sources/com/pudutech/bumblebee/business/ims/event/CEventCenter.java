package com.pudutech.bumblebee.business.ims.event;

import android.text.TextUtils;
import android.util.Log;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class CEventCenter {
    private static final String TAG = CEventCenter.class.getSimpleName();
    private static final ConcurrentHashMap<String, Object> LISTENER_MAP = new ConcurrentHashMap<>();
    private static final Object LISTENER_LOCK = new Object();
    private static final CEventObjPool POOL = new CEventObjPool(5);

    public static void onBindEvent(boolean z, I_CEventListener i_CEventListener, String str) {
        onBindEvent(z, i_CEventListener, new String[]{str});
    }

    public static void onBindEvent(boolean z, I_CEventListener i_CEventListener, String[] strArr) {
        if (z) {
            registerEventListener(i_CEventListener, strArr);
        } else {
            unregisterEventListener(i_CEventListener, strArr);
        }
    }

    public static void registerEventListener(I_CEventListener i_CEventListener, String str) {
        registerEventListener(i_CEventListener, new String[]{str});
    }

    public static void registerEventListener(I_CEventListener i_CEventListener, String[] strArr) {
        if (i_CEventListener == null || strArr == null) {
            return;
        }
        synchronized (LISTENER_LOCK) {
            for (String str : strArr) {
                if (!TextUtils.isEmpty(str)) {
                    Object obj = LISTENER_MAP.get(str);
                    if (obj == null) {
                        LISTENER_MAP.put(str, i_CEventListener);
                    } else if (obj instanceof I_CEventListener) {
                        I_CEventListener i_CEventListener2 = (I_CEventListener) obj;
                        if (i_CEventListener != i_CEventListener2) {
                            LinkedList linkedList = new LinkedList();
                            linkedList.add(i_CEventListener2);
                            linkedList.add(i_CEventListener);
                            LISTENER_MAP.put(str, linkedList);
                        }
                    } else if (obj instanceof List) {
                        LinkedList linkedList2 = (LinkedList) obj;
                        if (linkedList2.indexOf(i_CEventListener) < 0) {
                            linkedList2.add(i_CEventListener);
                        }
                    }
                }
            }
        }
    }

    public static void unregisterEventListener(I_CEventListener i_CEventListener, String str) {
        unregisterEventListener(i_CEventListener, new String[]{str});
    }

    public static void unregisterEventListener(I_CEventListener i_CEventListener, String[] strArr) {
        Object obj;
        if (i_CEventListener == null || strArr == null) {
            return;
        }
        synchronized (LISTENER_LOCK) {
            for (String str : strArr) {
                if (!TextUtils.isEmpty(str) && (obj = LISTENER_MAP.get(str)) != null) {
                    if (obj instanceof I_CEventListener) {
                        if (obj == i_CEventListener) {
                            LISTENER_MAP.remove(str);
                        }
                    } else if (obj instanceof List) {
                        ((LinkedList) obj).remove(i_CEventListener);
                    }
                }
            }
        }
    }

    public static void dispatchEvent(String str, int i, int i2, Object obj) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CEvent cEvent = POOL.get();
        cEvent.setTopic(str);
        cEvent.setMsgCode(i);
        cEvent.setResultCode(i2);
        cEvent.setObj(obj);
        dispatchEvent(cEvent);
    }

    public static void dispatchEvent(CEvent cEvent) {
        LinkedList linkedList;
        if (LISTENER_MAP.size() == 0 || cEvent == null || TextUtils.isEmpty(cEvent.getTopic())) {
            return;
        }
        String topic = cEvent.getTopic();
        synchronized (LISTENER_LOCK) {
            Log.d(TAG, "dispatchEvent | topic = " + topic + "\tmsgCode = " + cEvent.getMsgCode() + "\tresultCode = " + cEvent.getResultCode() + "\tobj = " + cEvent.getObj());
            Object obj = LISTENER_MAP.get(topic);
            if (obj == null) {
                return;
            }
            I_CEventListener i_CEventListener = null;
            if (obj instanceof I_CEventListener) {
                i_CEventListener = (I_CEventListener) obj;
                linkedList = null;
            } else {
                linkedList = obj instanceof LinkedList ? (LinkedList) ((LinkedList) obj).clone() : null;
            }
            if (i_CEventListener != null) {
                i_CEventListener.onCEvent(topic, cEvent.getMsgCode(), cEvent.getResultCode(), cEvent.getObj());
            } else if (linkedList != null && linkedList.size() > 0) {
                Iterator it = linkedList.iterator();
                while (it.hasNext()) {
                    ((I_CEventListener) it.next()).onCEvent(topic, cEvent.getMsgCode(), cEvent.getResultCode(), cEvent.getObj());
                }
            }
            POOL.returnObj(cEvent);
        }
    }
}
