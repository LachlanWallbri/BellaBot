package com.pudutech.bumblebee.robot_ui.util;

import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import java.util.HashMap;
import java.util.Random;

/* loaded from: classes4.dex */
public class LotteryUtil {
    private static int PRIZE_0 = 0;
    private static int PRIZE_1 = 1;
    private static int PRIZE_2 = 2;
    private static int PRIZE_3 = 3;
    private static final String TAG = "LotteryUtil";
    public static float prize_probability_0;
    public static float prize_probability_1;
    public static float prize_probability_2;
    public static float prize_probability_3;
    public static HashMap<Integer, Range> rangeMap;

    private static void initDatas() {
        prize_probability_0 = SpUtils.get(RobotContext.context, "key_interaction_prize_probability_level_0", 0.0f);
        prize_probability_1 = SpUtils.get(RobotContext.context, "key_interaction_prize_probability_level_1", 0.0f);
        prize_probability_2 = SpUtils.get(RobotContext.context, "key_interaction_prize_probability_level_2", 0.0f);
        prize_probability_3 = SpUtils.get(RobotContext.context, "key_interaction_prize_probability_level_3", 0.0f);
        Pdlog.m3273d(TAG, "Out Component gets lottery probabilities: " + prize_probability_0 + "  " + prize_probability_1 + "  " + prize_probability_2 + "  " + prize_probability_3);
        if (rangeMap == null) {
            rangeMap = new HashMap<>();
        }
        float f = prize_probability_0;
        rangeMap.put(Integer.valueOf(PRIZE_0), new Range(0.0f, f));
        float f2 = prize_probability_0 + 0.0f;
        float f3 = f + prize_probability_1;
        rangeMap.put(Integer.valueOf(PRIZE_1), new Range(f2, f3));
        float f4 = f2 + prize_probability_1;
        float f5 = f3 + prize_probability_2;
        rangeMap.put(Integer.valueOf(PRIZE_2), new Range(f4, f5));
        rangeMap.put(Integer.valueOf(PRIZE_3), new Range(f4 + prize_probability_2, f5 + prize_probability_3));
    }

    public static int calculationResult() {
        initDatas();
        float nextFloat = new Random().nextFloat();
        Pdlog.m3273d(TAG, "randomNum: " + nextFloat);
        for (Integer num : rangeMap.keySet()) {
            if (rangeMap.get(num).getRangeEnd() == 1.0f) {
                return num.intValue();
            }
            if (rangeMap.get(num).inRange(nextFloat)) {
                return num.intValue();
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class Range {
        private float rangeEnd;
        private float rangeStart;

        public Range(float f, float f2) {
            this.rangeStart = f;
            this.rangeEnd = f2;
        }

        public float getRangeStart() {
            return this.rangeStart;
        }

        public void setRangeStart(float f) {
            this.rangeStart = f;
        }

        public float getRangeEnd() {
            return this.rangeEnd;
        }

        public void setRangeEnd(float f) {
            this.rangeEnd = f;
        }

        public boolean inRange(float f) {
            if (this.rangeStart < f && f <= this.rangeEnd) {
                return true;
            }
            float f2 = this.rangeStart;
            return f2 == 0.0f && f2 < this.rangeEnd && f == 0.0f;
        }
    }
}
