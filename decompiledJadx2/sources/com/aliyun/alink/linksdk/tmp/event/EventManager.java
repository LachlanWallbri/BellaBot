package com.aliyun.alink.linksdk.tmp.event;

import android.text.TextUtils;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.alink.linksdk.tmp.api.OutputParams;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonRequest;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonResponse;
import com.aliyun.alink.linksdk.tmp.device.payload.ValueWrapper;
import com.aliyun.alink.linksdk.tmp.device.payload.event.EventNotifyPayload;
import com.aliyun.alink.linksdk.tmp.listener.IEventListener;
import com.aliyun.alink.linksdk.tmp.utils.GsonUtils;
import com.aliyun.alink.linksdk.tmp.utils.LogCat;
import com.aliyun.alink.linksdk.tmp.utils.TextHelper;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.linksdk.tools.ALog;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class EventManager implements INotifyHandler {
    protected static final String TAG = "[Tmp]EventManager";
    protected static EventManager mInstance = new EventManager();
    protected Map<String, Map<String, List<EventMsg>>> mEventList = new HashMap();
    protected Map<Integer, Map<String, ConcurrentSkipListSet<String>>> mSubcribedEventList = new HashMap();
    protected Map<Integer, Map<String, Map<String, IEventListener>>> mEventListenerList = new HashMap();

    protected EventManager() {
    }

    public static EventManager getInstance() {
        return mInstance;
    }

    @Override // com.aliyun.alink.linksdk.tmp.event.INotifyHandler
    public void onMessage(TmpCommonRequest tmpCommonRequest, TmpCommonResponse tmpCommonResponse) {
        OutputParams outputParams;
        if (tmpCommonRequest == null || tmpCommonResponse == null) {
            LogCat.m471e(TAG, "onMessage empty");
            return;
        }
        if (!tmpCommonResponse.isSuccess()) {
            LogCat.m471e(TAG, "onMessage isSuccess false");
            return;
        }
        try {
            JSONObject parseObject = JSONObject.parseObject(tmpCommonResponse.getResponseText());
            if (parseObject == null) {
                LogCat.m471e(TAG, "onMessage parseObject failed");
                return;
            }
            String string = parseObject.getString("method");
            String queryEventIdentifier = TextHelper.queryEventIdentifier(string);
            String topic = tmpCommonRequest.getTopic();
            String combineStr = TextHelper.combineStr(tmpCommonRequest.getProductKey(), tmpCommonRequest.getDeviceName());
            ALog.m479d(TAG, "onMessage eventIdentifier:" + queryEventIdentifier + " method:" + string + " topic:" + topic + " deviceId:" + combineStr);
            if (!TextUtils.isEmpty(string) && string.contains(TmpConstant.METHOD_PROPERTY_POST)) {
                outputParams = new OutputParams("params", new ValueWrapper(parseObject.getJSONObject("params")));
            } else {
                EventNotifyPayload eventNotifyPayload = (EventNotifyPayload) GsonUtils.fromJson(tmpCommonResponse.getResponseText(), new TypeToken<EventNotifyPayload>() { // from class: com.aliyun.alink.linksdk.tmp.event.EventManager.1
                }.getType());
                if (eventNotifyPayload == null) {
                    LogCat.m471e(TAG, "onMessage notifypayload error");
                    return;
                }
                outputParams = new OutputParams(eventNotifyPayload.getParams());
            }
            try {
                notifyListener(combineStr, queryEventIdentifier, topic, outputParams);
            } catch (Exception e) {
                ALog.m480e(TAG, "notifyListener error:" + e.toString());
            }
        } catch (Exception e2) {
            ALog.m484w(TAG, e2.toString());
        }
    }

    public EventMsg insertEventMsg(String str, String str2, EventMsg eventMsg) {
        LogCat.m469d(TAG, "insertEventMsg uri:" + str + " notifyData:" + str2);
        Map<String, List<EventMsg>> map = this.mEventList.get(str);
        if (map == null) {
            map = new HashMap<>();
            this.mEventList.put(str, map);
        }
        if (TextUtils.isEmpty(str2)) {
            List<EventMsg> list = map.get(str2);
            if (list == null) {
                list = new ArrayList<>();
                map.put(str2, list);
            }
            list.add(eventMsg);
        }
        return eventMsg;
    }

    public List<EventMsg> getEventMsgList() {
        ArrayList arrayList = new ArrayList();
        Iterator<Map.Entry<String, Map<String, List<EventMsg>>>> it = this.mEventList.entrySet().iterator();
        while (it.hasNext()) {
            Iterator<Map.Entry<String, List<EventMsg>>> it2 = it.next().getValue().entrySet().iterator();
            while (it2.hasNext()) {
                arrayList.addAll(it2.next().getValue());
            }
        }
        return arrayList;
    }

    public List<EventMsg> getEventMsgList(String str) {
        Map<String, List<EventMsg>> map;
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str) && (map = this.mEventList.get(str)) != null && !map.isEmpty()) {
            Iterator<Map.Entry<String, List<EventMsg>>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                arrayList.addAll(it.next().getValue());
            }
        }
        return arrayList;
    }

    public boolean isEventSubscribed(int i, String str, String str2) {
        ConcurrentSkipListSet<String> concurrentSkipListSet;
        ALog.m479d(TAG, "isEventSubscribed deviceId:" + str + " eventName:" + str2);
        Map<String, ConcurrentSkipListSet<String>> map = this.mSubcribedEventList.get(Integer.valueOf(i));
        return (map == null || (concurrentSkipListSet = map.get(str)) == null || !concurrentSkipListSet.contains(str2)) ? false : true;
    }

    public boolean addSubscribedEvent(int i, String str, String str2) {
        ALog.m479d(TAG, "addSubscribedEvent deviceId:" + str + " eventName:" + str2 + " ownerId:" + i);
        Map<String, ConcurrentSkipListSet<String>> map = this.mSubcribedEventList.get(Integer.valueOf(i));
        if (map == null) {
            map = new ConcurrentHashMap<>();
            this.mSubcribedEventList.put(Integer.valueOf(i), map);
        }
        ConcurrentSkipListSet<String> concurrentSkipListSet = map.get(str);
        if (concurrentSkipListSet == null) {
            concurrentSkipListSet = new ConcurrentSkipListSet<>();
            map.put(str, concurrentSkipListSet);
        }
        return concurrentSkipListSet.add(str2);
    }

    public boolean removeSubscribedEvent(int i, String str, String str2) {
        ConcurrentSkipListSet<String> concurrentSkipListSet;
        ALog.m479d(TAG, "removeSubscribedEvent deviceId:" + str + " eventName:" + str2 + " ownerId:" + i);
        Map<String, ConcurrentSkipListSet<String>> map = this.mSubcribedEventList.get(Integer.valueOf(i));
        if (map == null || (concurrentSkipListSet = map.get(str)) == null) {
            return true;
        }
        return concurrentSkipListSet.remove(str2);
    }

    public Set<String> getDevSubedEventList(int i, String str) {
        ALog.m479d(TAG, "getDevSubedEventList deviceId:" + str + " ownerId:" + i);
        Map<String, ConcurrentSkipListSet<String>> map = this.mSubcribedEventList.get(Integer.valueOf(i));
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    public boolean addEventListener(int i, String str, String str2, IEventListener iEventListener) {
        ALog.m479d(TAG, "addEventListener deviceId:" + str + " eventName:" + str2 + " listener:" + iEventListener + " ownerId:" + i);
        Map<String, Map<String, IEventListener>> map = this.mEventListenerList.get(Integer.valueOf(i));
        if (map == null) {
            map = new ConcurrentHashMap<>();
            this.mEventListenerList.put(Integer.valueOf(i), map);
        }
        Map<String, IEventListener> map2 = map.get(str);
        if (map2 == null) {
            map2 = new HashMap<>();
            map.put(str, map2);
        }
        map2.put(str2, iEventListener);
        return true;
    }

    public boolean removeEventListener(int i, String str, String str2) {
        Map<String, IEventListener> map;
        ALog.m479d(TAG, "removeEventListener deviceId:" + str + " eventName:" + str2 + " ownerId:" + i);
        Map<String, Map<String, IEventListener>> map2 = this.mEventListenerList.get(Integer.valueOf(i));
        if (map2 == null || (map = map2.get(str)) == null) {
            return true;
        }
        map.remove(str2);
        return true;
    }

    protected void notifyListener(String str, String str2, String str3, OutputParams outputParams) {
        ALog.m479d(TAG, "notifyListener deviceId:" + str + " eventName:" + str2 + " topic:" + str3 + " outputParams:" + outputParams);
        Map<Integer, Map<String, Map<String, IEventListener>>> map = this.mEventListenerList;
        if (map == null || map.isEmpty()) {
            ALog.m480e(TAG, "mEventListenerList null or empty");
            return;
        }
        Iterator<Map.Entry<Integer, Map<String, Map<String, IEventListener>>>> it = this.mEventListenerList.entrySet().iterator();
        while (it.hasNext()) {
            Map<String, IEventListener> map2 = it.next().getValue().get(str);
            if (map2 == null) {
                ALog.m480e(TAG, "notifyListener not register");
            } else {
                IEventListener iEventListener = map2.get(str2);
                if (iEventListener != null) {
                    iEventListener.onMessage(str2, str3, outputParams);
                } else {
                    ALog.m480e(TAG, "notifyListener null listener");
                }
            }
        }
    }
}
