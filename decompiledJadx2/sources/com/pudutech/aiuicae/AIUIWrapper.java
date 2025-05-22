package com.pudutech.aiuicae;

import android.content.Context;
import android.text.TextUtils;
import com.iflytek.aiui.AIUIAgent;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.aiui.AIUIEvent;
import com.iflytek.aiui.AIUIListener;
import com.iflytek.aiui.AIUIMessage;
import com.iflytek.aiui.constant.InternalConstant;
import com.pudutech.base.Pdlog;
import java.io.PipedReader;
import java.io.PipedWriter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes.dex */
public class AIUIWrapper implements AIUIListener {
    static final String ASSETS_CONFIG_PATH = "cfg/aiui_phone.cfg";
    public static final int BOS_TIMEOUT = 4;
    public static final int EOS = 5;
    public static final int IDLE = 2;
    public static final int LOW_AUDIO_LEVEL = 5;
    public static final int NETWORK_ERROR = 20001;
    public static final int PLAY_COMPLETE = 7;
    public static final int RECORDING = 1;
    public static final int SPEAKING = 3;
    public static final int TIMEOUT = 10120;
    public static final int UNCONNETT = 6;
    public static final int VAD_TIMEOUT = 10;
    static final String sTag = "AIUIevent";
    private AIUIAgent mAgent;
    private OnStateChangedListener onStateChangedListener;
    private AIUIConfig mConfig = new AIUIConfig();
    private int mCurrentState = 6;
    private boolean WakeUp = false;
    private int mVadCount = 0;
    PipedReader reader = new PipedReader();
    PipedWriter writer = new PipedWriter();

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* loaded from: classes.dex */
    public interface OnStateChangedListener {
        void onResultChange(String str);

        void onStateChanged(int i);

        void onTextChanged(String str);

        void onVadChange(int i);
    }

    public void initAIUIAgent(Context context) {
        Pdlog.m3275i(sTag, "init AIUIAgent");
        this.WakeUp = false;
        this.mCurrentState = 6;
        this.mVadCount = 0;
        this.mAgent = AIUIAgent.createAgent(context, this.mConfig.readAssetFile(context, "cfg/aiui_phone.cfg"), this);
    }

    private void sendMessage(AIUIMessage aIUIMessage) {
        if (this.mAgent != null) {
            Pdlog.m3275i(sTag, "wakeup_state:" + this.WakeUp);
            if (!this.WakeUp) {
                Pdlog.m3275i("wakeup", "sending wakeup_cmd");
                this.mAgent.sendMessage(new AIUIMessage(7, 0, 0, "", null));
            }
            Pdlog.m3275i(sTag, "send msg");
            this.mAgent.sendMessage(aIUIMessage);
        }
    }

    public void startRecordAudio(byte[] bArr) {
        Pdlog.m3275i(sTag, "START RECORD");
        sendMessage(new AIUIMessage(2, 0, 0, "data_type=audio,sample_rate=16000,dwa=wpgs", bArr));
        updateState(1);
    }

    public void stopRecordAudio() {
        Pdlog.m3275i(sTag, "STOP RECORD");
        sendMessage(new AIUIMessage(3, 0, 0, "data_type=audio,sample_rate=16000", null));
    }

    public void stopPlay() {
        Pdlog.m3275i(sTag, "STOP PLAY");
    }

    public void releaseAIUIAgent() {
        Pdlog.m3275i(sTag, "DESTROY AGENT");
        AIUIAgent aIUIAgent = this.mAgent;
        if (aIUIAgent != null) {
            aIUIAgent.destroy();
        }
    }

    private void updateState(int i) {
        OnStateChangedListener onStateChangedListener = this.onStateChangedListener;
        if (onStateChangedListener != null) {
            onStateChangedListener.onStateChanged(i);
        }
        this.mCurrentState = i;
        Pdlog.m3275i(sTag, "UPDATE STATE");
    }

    private void updateText(String str) {
        OnStateChangedListener onStateChangedListener = this.onStateChangedListener;
        if (onStateChangedListener != null) {
            onStateChangedListener.onTextChanged(str);
        }
        Pdlog.m3275i(sTag, "UPDATE TEXT");
    }

    private void updateResult(String str) {
        OnStateChangedListener onStateChangedListener = this.onStateChangedListener;
        if (onStateChangedListener != null) {
            onStateChangedListener.onResultChange(str);
        }
        Pdlog.m3275i(sTag, "UPDATE RESULT");
    }

    private void updateVad(int i) {
        OnStateChangedListener onStateChangedListener = this.onStateChangedListener;
        if (onStateChangedListener != null) {
            onStateChangedListener.onVadChange(i);
        }
        Pdlog.m3275i(sTag, "UPDATE VAD");
    }

    public void setOnStateChangedListener(OnStateChangedListener onStateChangedListener) {
        this.onStateChangedListener = onStateChangedListener;
    }

    @Override // com.iflytek.aiui.AIUIListener
    public void onEvent(AIUIEvent aIUIEvent) {
        int i = aIUIEvent.eventType;
        if (i == 13) {
            Pdlog.m3275i(sTag, "CONNECTED");
            updateState(2);
            return;
        }
        if (i != 15) {
            switch (i) {
                case 1:
                    Pdlog.m3275i(sTag, "RESULT");
                    processResult(aIUIEvent);
                    return;
                case 2:
                    int i2 = aIUIEvent.arg1;
                    processError(aIUIEvent);
                    Pdlog.m3275i(sTag, "ERROR:" + i2);
                    return;
                case 3:
                    Pdlog.m3275i(sTag, "STATE: " + aIUIEvent.arg1);
                    return;
                case 4:
                    Pdlog.m3275i(sTag, "WAKE_UP");
                    this.WakeUp = true;
                    return;
                case 5:
                    Pdlog.m3275i(sTag, "SLEEP");
                    this.WakeUp = false;
                    return;
                case 6:
                    Pdlog.m3275i(sTag, "VAD");
                    processVADEvent(aIUIEvent);
                    return;
                default:
                    return;
            }
        }
        Pdlog.m3275i(sTag, "TTS:" + aIUIEvent.arg1 + aIUIEvent.arg2);
        int i3 = aIUIEvent.arg1;
        if (i3 == 4) {
            updateState(3);
        } else {
            if (i3 != 5) {
                return;
            }
            updateState(7);
            updateState(2);
        }
    }

    private void processResult(AIUIEvent aIUIEvent) {
        JSONObject optJSONObject;
        try {
            JSONObject jSONObject = new JSONObject(aIUIEvent.info).getJSONArray("data").getJSONObject(0);
            JSONObject jSONObject2 = jSONObject.getJSONObject("params");
            JSONObject jSONObject3 = jSONObject.getJSONArray(AIUIConstant.KEY_CONTENT).getJSONObject(0);
            Pdlog.m3275i(sTag, "processResult");
            String optString = jSONObject2.optString("sub");
            if ("tts".equals(optString) && jSONObject3.has(InternalConstant.KEY_CONTENT_ID)) {
                aIUIEvent.data.getString("sid");
                aIUIEvent.data.getByteArray(jSONObject3.getString(InternalConstant.KEY_CONTENT_ID));
            }
            if (!jSONObject3.has(InternalConstant.KEY_CONTENT_ID) || optString.equals("tts")) {
                return;
            }
            JSONObject jSONObject4 = new JSONObject(new String(aIUIEvent.data.getByteArray(jSONObject3.getString(InternalConstant.KEY_CONTENT_ID)), "utf-8"));
            if (InternalConstant.SUB_IAT.equals(optString)) {
                processIATResult(jSONObject4);
                return;
            }
            if (!InternalConstant.SUB_NLP.equals(optString) || (optJSONObject = jSONObject4.optJSONObject("intent")) == null || optJSONObject.length() == 0) {
                return;
            }
            JSONObject optJSONObject2 = optJSONObject.optJSONObject("answer");
            updateResult(optJSONObject2.optString("text"));
            Pdlog.m3275i(sTag, "result: " + optJSONObject2.optString("text"));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void processIATResult(JSONObject jSONObject) throws JSONException {
        JSONObject optJSONObject = jSONObject.optJSONObject("text");
        StringBuilder sb = new StringBuilder();
        JSONArray optJSONArray = optJSONObject.optJSONArray("ws");
        optJSONObject.optBoolean("ls");
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONArray optJSONArray2 = optJSONArray.optJSONObject(i).optJSONArray("cw");
            for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                sb.append(optJSONArray2.optJSONObject(i2).opt("w"));
            }
        }
        if (TextUtils.isEmpty(sb)) {
            return;
        }
        Pdlog.m3275i(sTag, "iatText:" + ((Object) sb));
        updateText(sb.toString());
    }

    private void processVADEvent(AIUIEvent aIUIEvent) {
        Pdlog.m3275i(sTag, "PROCESS VADEVENT");
        if (aIUIEvent.eventType == 6) {
            if (aIUIEvent.arg1 == 1) {
                updateVad(aIUIEvent.arg2);
                if (aIUIEvent.arg2 <= 5) {
                    Pdlog.m3275i(sTag, "stopcnt：" + this.mVadCount);
                    this.mVadCount = this.mVadCount + 1;
                } else {
                    Pdlog.m3275i(sTag, "vadcnt:" + this.mVadCount + "vol:" + aIUIEvent.arg2);
                    this.mVadCount = 0;
                }
            }
            if (aIUIEvent.arg1 == 3) {
                Pdlog.m3275i(sTag, "VAD BOS TIMEOUT");
                stopRecordAudio();
                updateState(4);
                updateState(2);
            }
        }
        if (this.mVadCount > 10) {
            this.mVadCount = 0;
            stopRecordAudio();
            updateState(5);
            updateState(2);
        }
    }

    private void processError(AIUIEvent aIUIEvent) {
        int i = aIUIEvent.arg1;
        stopRecordAudio();
        updateState(i);
        updateState(2);
        if (i == 10120) {
            Pdlog.m3274e(sTag, "Network Error");
            return;
        }
        if (i == 20006) {
            Pdlog.m3274e(sTag, "Start Record fail");
            return;
        }
        Pdlog.m3274e(sTag, "Error code: " + i);
    }
}
