package com.iflytek.cloud.thirdparty;

import android.os.Environment;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.cloud.SpeechConstant;

/* renamed from: com.iflytek.cloud.thirdparty.ae */
/* loaded from: classes3.dex */
public class C3693ae {

    /* renamed from: a */
    public static String f2976a = "wtime";

    /* renamed from: b */
    public static final String f2977b = Environment.getExternalStorageDirectory().getPath() + "/msc/ist/audio/";

    /* renamed from: c */
    public static final String[][] f2978c = {new String[]{"surl", AIUIConstant.KEY_SERVER_URL}, new String[]{"besturl_search", "search_best_url"}, new String[]{"bsts", "search_best_url"}, new String[]{SpeechConstant.ASR_SCH, "sch"}, new String[]{SpeechConstant.ASR_NOMATCH_ERROR, "asr_nme"}, new String[]{"asr_ptt", "ptt"}, new String[]{SpeechConstant.RESULT_TYPE, "rst"}, new String[]{SpeechConstant.RESULT_LEVEL, "rst_level"}, new String[]{"vad_bos", "vad_timeout"}, new String[]{"bos", "vad_timeout"}, new String[]{"vad_eos", "vad_speech_tail", "eos"}, new String[]{"eos", "vad_speech_tail", "eos"}, new String[]{SpeechConstant.ASR_AUDIO_PATH, "aap"}, new String[]{SpeechConstant.TTS_BUFFER_TIME, "tbt"}, new String[]{SpeechConstant.TTS_AUDIO_PATH, "tap"}, new String[]{SpeechConstant.SUBJECT, "sub"}, new String[]{"data_type", "dtt"}, new String[]{SpeechConstant.ASR_NBEST, "nbest"}, new String[]{SpeechConstant.ASR_WBEST, "wbest"}, new String[]{SpeechConstant.ASR_DWA, "dwa"}, new String[]{"voice_name", "vcn"}, new String[]{SpeechConstant.BACKGROUND_SOUND, "bgs"}, new String[]{SpeechConstant.TEXT_ENCODING, "tte"}, new String[]{SpeechConstant.IVW_NET_MODE, "ivwnet_mode"}};

    /* renamed from: com.iflytek.cloud.thirdparty.ae$a */
    /* loaded from: classes3.dex */
    public enum a {
        VAD_LOOKING,
        VAD_INSPEECH,
        undefined_2,
        VAD_EOS,
        VAD_TIMEOUT,
        VAD_ERROR
    }
}
