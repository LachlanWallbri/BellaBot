package com.sunny.ylhk.lib_base_mvvm.util;

import com.aliyun.alink.linksdk.tools.p045ut.AUserTrack;
import com.pudutech.shared.lib_syntime.SystemTimeSetting;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: DateUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006J\u0010\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u0006J\u0016\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u0006J\u0010\u0010\r\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u0006J\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006¨\u0006\u0012"}, m3961d2 = {"Lcom/sunny/ylhk/lib_base_mvvm/util/DateUtils;", "", "()V", "convertSecToTime", "", "lSeconds", "", "dateDiff", AUserTrack.UTKEY_START_TIME, AUserTrack.UTKEY_END_TIME, "formatTime", "", "time", "getDateFormatStr", "type", "", "getOneBuyHeadTip", "currentTime", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class DateUtils {
    public static final DateUtils INSTANCE = new DateUtils();

    private DateUtils() {
    }

    public final String getDateFormatStr(long time) {
        return new SimpleDateFormat("MM.dd HH:mm:ss").format(new Date(time));
    }

    public final String getDateFormatStr(int type, long time) {
        SimpleDateFormat simpleDateFormat;
        if (type == 0) {
            simpleDateFormat = new SimpleDateFormat("HH:mm");
        } else if (type == 2) {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        } else if (type == 3) {
            simpleDateFormat = new SimpleDateFormat(SystemTimeSetting.TIME_FORMAT);
        } else {
            simpleDateFormat = new SimpleDateFormat("MM.dd");
        }
        String format = simpleDateFormat.format(new Date(time));
        Intrinsics.checkExpressionValueIsNotNull(format, "format.format(d1)");
        return format;
    }

    public final long[] convertSecToTime(long lSeconds) {
        long j = 86400;
        long j2 = lSeconds / j;
        long j3 = 3600;
        long j4 = (lSeconds / j3) - (24 * j2);
        long j5 = 60;
        long j6 = ((lSeconds / j5) - (1440 * j2)) - (j4 * j5);
        return new long[]{j2, j4, j6, ((lSeconds - (j * j2)) - (j3 * j4)) - (j5 * j6)};
    }

    public final String formatTime(long time) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = {Long.valueOf(time)};
        String format = String.format("%02d", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        return format;
    }

    public final String getOneBuyHeadTip(long currentTime, long time) {
        long dateDiff = dateDiff(currentTime, time);
        if (dateDiff == 0) {
            return "今天" + getDateFormatStr(0, time) + "开始";
        }
        if (dateDiff == 1) {
            return "明天" + getDateFormatStr(0, time) + "开始";
        }
        return getDateFormatStr(1, time) + "开始";
    }

    public final long dateDiff(long startTime, long endTime) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            Date date = new Date(startTime);
            Date date2 = new Date(endTime);
            Date parse = simpleDateFormat.parse(simpleDateFormat.format(date));
            Date parse2 = simpleDateFormat.parse(simpleDateFormat.format(date2));
            if (parse2 == null) {
                Intrinsics.throwNpe();
            }
            long time = parse2.getTime();
            if (parse == null) {
                Intrinsics.throwNpe();
            }
            return (time - parse.getTime()) / 86400000;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0L;
        }
    }
}
