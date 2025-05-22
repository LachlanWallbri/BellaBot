package com.iflytek.aiui.vad.sdk;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class VadConstant {
    public static final int ERROR_APPID_NOT_FOUND = 70000;
    public static final int ERROR_APPID_NOT_MATCHED = 70001;
    public static final int ERROR_AUTHOR_AUTHOR_DISABLE = 80004;
    public static final int ERROR_AUTHOR_FAILED = 80000;
    public static final int ERROR_AUTHOR_NO_AUTHOR = 80001;
    public static final int ERROR_AUTHOR_OUT_FRESH = 80003;
    public static final int ERROR_AUTHOR_OUT_LIMIT = 80002;
    public static final int ERROR_AUTHOR_PARAM_ERR = 80005;
    public static final int ERROR_AUTHOR_PARSE_ERR = 80006;
    public static final int ERROR_AUTHOR_STREAM_LIMIT = 80007;
    public static final int ERROR_NULL_POINTER = 50000;
    public static final int ERROR_READ_STATUS_FAILED = 50004;
    public static final int ERROR_RES_FORMAT = 50001;
    public static final int ERROR_RES_SIZE = 50002;
    public static final int ERROR_SN_FORMAT = 70003;
    public static final int ERROR_SN_NOT_FOUND = 70002;
    public static final int ERROR_WRITE_DATA_FAILED = 50003;
    public static final int FAILED = -1;
    public static final String KEY_BOS_THRESHOLD = "bos_threshold";
    public static final String KEY_BOS_TIMEOUT = "bos_timeout";
    public static final String KEY_EOS_THRESHOLD = "eos_threshold";
    public static final String KEY_EOS_TIMEOUT = "eos_timeout";
    public static final String KEY_MAX_SENTENCE_LENGTH = "max_sentence_length";
    public static final int STATUS_BOS_TIMEOUT = 3;
    public static final int STATUS_CONTINUE = 1;
    public static final int STATUS_FIND_BOS = 0;
    public static final int STATUS_FIND_EOS = 2;
    public static final int STATUS_FINISH = 4;
    public static final int SUCCESS = 0;
    private static Map<Integer, String> sErrDesMap = new HashMap();

    static {
        sErrDesMap.put(Integer.valueOf(ERROR_NULL_POINTER), "null pointer");
        sErrDesMap.put(Integer.valueOf(ERROR_RES_FORMAT), "wrong resource format");
        sErrDesMap.put(Integer.valueOf(ERROR_RES_SIZE), "wrong resource size");
        sErrDesMap.put(Integer.valueOf(ERROR_WRITE_DATA_FAILED), "write data failed");
        sErrDesMap.put(Integer.valueOf(ERROR_READ_STATUS_FAILED), "read data failed");
        sErrDesMap.put(Integer.valueOf(ERROR_APPID_NOT_FOUND), "appid not found");
        sErrDesMap.put(Integer.valueOf(ERROR_APPID_NOT_MATCHED), "appid not matched");
        sErrDesMap.put(Integer.valueOf(ERROR_SN_NOT_FOUND), "sn not found");
        sErrDesMap.put(Integer.valueOf(ERROR_SN_FORMAT), "wrong sn format");
        sErrDesMap.put(Integer.valueOf(ERROR_AUTHOR_FAILED), "authorize failed");
        sErrDesMap.put(Integer.valueOf(ERROR_AUTHOR_NO_AUTHOR), "no authorization");
        sErrDesMap.put(Integer.valueOf(ERROR_AUTHOR_OUT_LIMIT), "authorization out of limit");
        sErrDesMap.put(Integer.valueOf(ERROR_AUTHOR_OUT_FRESH), "flash erased out of limit");
        sErrDesMap.put(Integer.valueOf(ERROR_AUTHOR_AUTHOR_DISABLE), "authorization disabled");
        sErrDesMap.put(Integer.valueOf(ERROR_AUTHOR_PARAM_ERR), "device parameters error");
        sErrDesMap.put(Integer.valueOf(ERROR_AUTHOR_PARSE_ERR), "data parse error");
        sErrDesMap.put(Integer.valueOf(ERROR_AUTHOR_STREAM_LIMIT), "server stream out of limit");
    }

    public static String getDes(int i) {
        String str = sErrDesMap.get(Integer.valueOf(i));
        return str == null ? "" : str;
    }
}
