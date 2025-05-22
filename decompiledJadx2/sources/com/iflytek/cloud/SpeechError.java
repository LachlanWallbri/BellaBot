package com.iflytek.cloud;

import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.resource.Resource;

/* loaded from: classes3.dex */
public class SpeechError extends Exception {
    public static final int TIP_ERROR_ALREADY_EXIST = 66;
    public static final int TIP_ERROR_AUTHID_NOT_AVAILABLE = 71;
    public static final int TIP_ERROR_GROUP_EMPTY = 68;
    public static final int TIP_ERROR_IVP_EXTRA_RGN_SOPPORT = 56;
    public static final int TIP_ERROR_IVP_GENERAL = 55;
    public static final int TIP_ERROR_IVP_MUCH_NOISE = 58;
    public static final int TIP_ERROR_IVP_NO_ENOUGH_AUDIO = 63;
    public static final int TIP_ERROR_IVP_TEXT_NOT_MATCH = 62;
    public static final int TIP_ERROR_IVP_TOO_LOW = 59;
    public static final int TIP_ERROR_IVP_TRUNCATED = 57;
    public static final int TIP_ERROR_IVP_UTTER_TOO_SHORT = 61;
    public static final int TIP_ERROR_IVP_ZERO_AUDIO = 60;
    public static final int TIP_ERROR_MODEL_IS_CREATING = 65;
    public static final int TIP_ERROR_MODEL_NOT_FOUND = 64;
    public static final int TIP_ERROR_NO_GROUP = 67;
    public static final int TIP_ERROR_NO_USER = 69;
    public static final int TIP_ERROR_OVERFLOW_IN_GROUP = 70;
    private static final long serialVersionUID = 4434424251478985596L;

    /* renamed from: a */
    private int f2783a;

    /* renamed from: b */
    private String f2784b;

    public SpeechError(Exception exc) {
        this.f2783a = 0;
        this.f2784b = "";
        this.f2783a = 20999;
        this.f2784b = exc.toString();
    }

    public SpeechError(Throwable th, int i) {
        this(i);
        initCause(th);
    }

    public SpeechError(int i, String str) {
        this(i);
        if (SpeechConstant.ENG_WFR.equals(str)) {
            if (10118 == i) {
                this.f2784b = Resource.getErrorDescription(33);
            } else if (10119 == i) {
                this.f2784b = Resource.getErrorDescription(34);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:145:0x0134, code lost:
    
        if (r0 == 11503) goto L56;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public SpeechError(int i) {
        int i2;
        this.f2783a = 0;
        this.f2784b = "";
        this.f2783a = i;
        int i3 = 31;
        int i4 = 65;
        if (i < 20001) {
            int i5 = this.f2783a;
            if (i5 != 10118) {
                if (10106 == i5 || 10107 == i5 || 10124 == i5) {
                    DebugLog.LogD("sdk errorcode", this.f2783a + "");
                    i3 = 7;
                } else if (i5 == 10110) {
                    i3 = 32;
                } else {
                    if (i5 != 10111) {
                        if (i5 < 10200 || i5 >= 10300) {
                            int i6 = this.f2783a;
                            if (i6 == 10117 || i6 == 10101) {
                                i3 = 16;
                            } else if (i6 == 10113) {
                                i3 = 17;
                            } else {
                                if (i6 != 10116) {
                                    if (i6 == 10121) {
                                        i3 = 66;
                                    } else {
                                        if (i6 < 10400 || i6 > 10407) {
                                            int i7 = this.f2783a;
                                            if (i7 < 11000 || i7 >= 11099) {
                                                int i8 = this.f2783a;
                                                if (i8 != 10129) {
                                                    if (i8 == 10109) {
                                                        i3 = 20;
                                                    } else if (i8 == 10702) {
                                                        i3 = 21;
                                                    } else if (i8 < 10500 || i8 >= 10600) {
                                                        int i9 = this.f2783a;
                                                        if (i9 < 11200 || i9 > 11250) {
                                                            int i10 = this.f2783a;
                                                            if ((i10 < 14000 || i10 > 14006) && ((i2 = this.f2783a) < 16000 || i2 > 16006)) {
                                                                int i11 = this.f2783a;
                                                                if (11401 == i11) {
                                                                    i3 = 35;
                                                                } else if (11402 == i11) {
                                                                    i3 = 36;
                                                                } else if (11403 == i11) {
                                                                    i3 = 37;
                                                                } else if (11404 == i11) {
                                                                    i3 = 38;
                                                                } else if (11405 == i11) {
                                                                    i3 = 39;
                                                                } else if (11406 == i11) {
                                                                    i3 = 40;
                                                                } else if (11407 == i11) {
                                                                    i3 = 41;
                                                                } else if (11408 == i11) {
                                                                    i3 = 42;
                                                                } else if (i11 == 11501) {
                                                                    i3 = 65;
                                                                } else if (i11 != 11502) {
                                                                }
                                                            }
                                                        } else {
                                                            i3 = 25;
                                                        }
                                                    } else {
                                                        i3 = 22;
                                                    }
                                                }
                                                i3 = 19;
                                            } else if (i7 == 11005) {
                                                i3 = 23;
                                            } else if (i7 == 11006) {
                                                i3 = 24;
                                            }
                                        }
                                        i3 = 18;
                                    }
                                }
                                i3 = 64;
                            }
                        }
                        i3 = 3;
                    }
                    i3 = 28;
                }
            }
            i3 = 11;
        } else {
            int i12 = this.f2783a;
            if (i12 < 30000) {
                if (i12 == 20001) {
                    i3 = 1;
                } else if (i12 == 20002) {
                    i3 = 2;
                } else if (i12 != 20003) {
                    if (i12 == 20004) {
                        i3 = 5;
                    } else if (i12 == 20005) {
                        i3 = 10;
                    } else if (i12 == 20006) {
                        i3 = 9;
                    } else if (i12 == 20007) {
                        i3 = 12;
                    } else {
                        if (i12 != 20008) {
                            if (i12 == 20009) {
                                i3 = 13;
                            } else if (i12 == 20010) {
                                i3 = 14;
                            } else {
                                if (i12 != 20012) {
                                    if (i12 != 21003) {
                                        i3 = (i12 == 21002 || i12 == 21001) ? 29 : i12 == 26001 ? 71 : 30;
                                    }
                                    i3 = 28;
                                }
                                i3 = 7;
                            }
                        }
                        i3 = 11;
                    }
                }
            }
            i3 = 3;
        }
        int i13 = this.f2783a;
        if (i13 != 10031) {
            if (i13 != 11610) {
                switch (i13) {
                    case 10141:
                        i4 = 68;
                        break;
                    case 10142:
                        i4 = 69;
                        break;
                    case 10143:
                        i4 = 67;
                        break;
                    case 10144:
                        i4 = 70;
                        break;
                    default:
                        switch (i13) {
                            case 11600:
                                i4 = 55;
                                break;
                            case 11601:
                                i4 = 56;
                                break;
                            case 11602:
                                i4 = 57;
                                break;
                            case 11603:
                                i4 = 58;
                                break;
                            case 11604:
                                i4 = 59;
                                break;
                            case 11605:
                                i4 = 60;
                                break;
                            case 11606:
                                i4 = 61;
                                break;
                            case 11607:
                                i4 = 62;
                                break;
                            case 11608:
                                i4 = 63;
                                break;
                            default:
                                switch (i13) {
                                    case 11700:
                                        i4 = 43;
                                        break;
                                    case 11701:
                                        i4 = 44;
                                        break;
                                    case 11702:
                                        i4 = 45;
                                        break;
                                    case 11703:
                                        i4 = 46;
                                        break;
                                    case 11704:
                                        i4 = 47;
                                        break;
                                    case 11705:
                                        i4 = 48;
                                        break;
                                    case 11706:
                                        i4 = 49;
                                        break;
                                    case 11707:
                                        i4 = 50;
                                        break;
                                    case 11708:
                                        i4 = 51;
                                        break;
                                    case 11709:
                                        i4 = 52;
                                        break;
                                    case 11710:
                                        i4 = 53;
                                        break;
                                    case 11711:
                                        i4 = 54;
                                        break;
                                    case 11712:
                                        break;
                                    default:
                                        i4 = i3;
                                        break;
                                }
                        }
                }
            }
            i4 = 64;
        }
        this.f2784b = Resource.getErrorDescription(i4);
    }

    @Override // java.lang.Throwable
    public String toString() {
        return getPlainDescription(true);
    }

    public int getErrorCode() {
        return this.f2783a;
    }

    public String getErrorDescription() {
        return this.f2784b;
    }

    public String getHtmlDescription(boolean z) {
        String str = this.f2784b + "...";
        if (!z) {
            return str;
        }
        return ((str + "<br>(") + Resource.getErrorTag(0) + ":") + this.f2783a + ")";
    }

    public String getPlainDescription(boolean z) {
        String str = this.f2784b;
        if (!z) {
            return str;
        }
        return ((str + ".") + "(" + Resource.getErrorTag(0) + ":") + this.f2783a + ")";
    }
}
