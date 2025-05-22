package org.checkerframework.checker.i18nformatter;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amitshekhar.utils.DataType;
import com.slamtec.slamware.robot.SystemParameters;
import java.text.ChoiceFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Locale;
import org.checkerframework.checker.i18nformatter.qual.I18nChecksFormat;
import org.checkerframework.checker.i18nformatter.qual.I18nConversionCategory;
import org.checkerframework.checker.i18nformatter.qual.I18nValidFormat;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* loaded from: classes9.dex */
public class I18nFormatUtil {
    public static void tryFormatSatisfiability(String str) throws IllegalFormatException {
        MessageFormat.format(str, (Object[]) null);
    }

    public static I18nConversionCategory[] formatParameterCategories(String str) throws IllegalFormatException {
        I18nConversionCategory i18nConversionCategory;
        tryFormatSatisfiability(str);
        I18nConversion[] parse = MessageFormatParser.parse(str);
        HashMap hashMap = new HashMap();
        int i = -1;
        for (I18nConversion i18nConversion : parse) {
            int i2 = i18nConversion.index;
            Integer valueOf = Integer.valueOf(i2);
            I18nConversionCategory i18nConversionCategory2 = i18nConversion.category;
            if (hashMap.containsKey(valueOf)) {
                i18nConversionCategory = (I18nConversionCategory) hashMap.get(valueOf);
            } else {
                i18nConversionCategory = I18nConversionCategory.UNUSED;
            }
            hashMap.put(valueOf, I18nConversionCategory.intersect(i18nConversionCategory2, i18nConversionCategory));
            i = Math.max(i, i2);
        }
        I18nConversionCategory[] i18nConversionCategoryArr = new I18nConversionCategory[i + 1];
        for (int i3 = 0; i3 <= i; i3++) {
            Integer valueOf2 = Integer.valueOf(i3);
            i18nConversionCategoryArr[i3] = hashMap.containsKey(valueOf2) ? (I18nConversionCategory) hashMap.get(valueOf2) : I18nConversionCategory.UNUSED;
        }
        return i18nConversionCategoryArr;
    }

    @I18nChecksFormat
    public static boolean hasFormat(String str, I18nConversionCategory... i18nConversionCategoryArr) {
        I18nConversionCategory[] formatParameterCategories = formatParameterCategories(str);
        if (formatParameterCategories.length != i18nConversionCategoryArr.length) {
            return false;
        }
        for (int i = 0; i < i18nConversionCategoryArr.length; i++) {
            if (!I18nConversionCategory.isSubsetOf(i18nConversionCategoryArr[i], formatParameterCategories[i])) {
                return false;
            }
        }
        return true;
    }

    @I18nValidFormat
    public static boolean isFormat(String str) {
        try {
            formatParameterCategories(str);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class I18nConversion {
        public I18nConversionCategory category;
        public int index;

        public I18nConversion(int i, I18nConversionCategory i18nConversionCategory) {
            this.index = i;
            this.category = i18nConversionCategory;
        }

        public String toString() {
            return this.category.toString() + "(index: " + this.index + ")";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class MessageFormatParser {
        private static final int MODIFIER_CURRENCY = 1;
        private static final int MODIFIER_DEFAULT = 0;
        private static final int MODIFIER_INTEGER = 3;
        private static final int MODIFIER_PERCENT = 2;
        private static final int SEG_INDEX = 1;
        private static final int SEG_MODIFIER = 3;
        private static final int SEG_RAW = 0;
        private static final int SEG_TYPE = 2;
        private static final int TYPE_CHOICE = 4;
        private static final int TYPE_DATE = 2;
        private static final int TYPE_NULL = 0;
        private static final int TYPE_NUMBER = 1;
        private static final int TYPE_TIME = 3;
        private static List<Integer> argumentIndices;
        private static List<I18nConversionCategory> categories;
        private static Locale locale;
        public static int maxOffset;
        private static int numFormat;
        private static final String[] TYPE_KEYWORDS = {"", "number", TmpConstant.TYPE_VALUE_DATE, "time", "choice"};
        private static final String[] NUMBER_MODIFIER_KEYWORDS = {"", "currency", "percent", "integer"};
        private static final String[] DATE_TIME_MODIFIER_KEYWORDS = {"", "short", SystemParameters.SYSVAL_ROBOT_SPEED_MEDIUM, DataType.LONG, "full"};

        private MessageFormatParser() {
        }

        @EnsuresNonNull({"categories", "argumentIndices", "locale"})
        public static I18nConversion[] parse(String str) {
            categories = new ArrayList();
            argumentIndices = new ArrayList();
            locale = Locale.getDefault(Locale.Category.FORMAT);
            applyPattern(str);
            I18nConversion[] i18nConversionArr = new I18nConversion[numFormat];
            for (int i = 0; i < numFormat; i++) {
                i18nConversionArr[i] = new I18nConversion(argumentIndices.get(i).intValue(), categories.get(i));
            }
            return i18nConversionArr;
        }

        @RequiresNonNull({"argumentIndices", "categories", "locale"})
        private static void applyPattern(String str) {
            StringBuilder[] sbArr = new StringBuilder[4];
            sbArr[0] = new StringBuilder();
            numFormat = 0;
            maxOffset = -1;
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            boolean z = false;
            while (i < str.length()) {
                char charAt = str.charAt(i);
                if (i3 == 0) {
                    if (charAt == '\'') {
                        int i4 = i + 1;
                        if (i4 >= str.length() || str.charAt(i4) != '\'') {
                            z = !z;
                        } else {
                            sbArr[i3].append(charAt);
                            i = i4;
                        }
                    } else if (charAt == '{' && !z) {
                        if (sbArr[1] == null) {
                            sbArr[1] = new StringBuilder();
                        }
                        i3 = 1;
                    } else {
                        sbArr[i3].append(charAt);
                    }
                } else if (z) {
                    sbArr[i3].append(charAt);
                    if (charAt == '\'') {
                        z = false;
                    }
                } else if (charAt != ' ') {
                    if (charAt == '\'') {
                        sbArr[i3].append(charAt);
                        z = true;
                    } else if (charAt != ',') {
                        if (charAt == '{') {
                            i2++;
                            sbArr[i3].append(charAt);
                        } else if (charAt != '}') {
                            sbArr[i3].append(charAt);
                        } else if (i2 == 0) {
                            makeFormat(numFormat, sbArr);
                            numFormat++;
                            sbArr[1] = null;
                            sbArr[2] = null;
                            sbArr[3] = null;
                            i3 = 0;
                        } else {
                            i2--;
                            sbArr[i3].append(charAt);
                        }
                    } else if (i3 < 3) {
                        i3++;
                        if (sbArr[i3] == null) {
                            sbArr[i3] = new StringBuilder();
                        }
                    } else {
                        sbArr[i3].append(charAt);
                    }
                } else if (i3 != 2 || sbArr[2].length() > 0) {
                    sbArr[i3].append(charAt);
                }
                i++;
            }
            if (i2 != 0 || i3 == 0) {
                return;
            }
            maxOffset = -1;
            throw new IllegalArgumentException("Unmatched braces in the pattern");
        }

        @RequiresNonNull({"argumentIndices", "categories", "locale"})
        private static void makeFormat(int i, StringBuilder[] sbArr) {
            I18nConversionCategory i18nConversionCategory;
            String[] strArr = new String[sbArr.length];
            for (int i2 = 0; i2 < sbArr.length; i2++) {
                StringBuilder sb = sbArr[i2];
                strArr[i2] = sb != null ? sb.toString() : "";
            }
            try {
                int parseInt = Integer.parseInt(strArr[1]);
                if (parseInt < 0) {
                    throw new IllegalArgumentException("negative argument number: " + parseInt);
                }
                int i3 = maxOffset;
                maxOffset = i;
                argumentIndices.add(Integer.valueOf(parseInt));
                if (strArr[2].length() != 0) {
                    int findKeyword = findKeyword(strArr[2], TYPE_KEYWORDS);
                    if (findKeyword == 0) {
                        i18nConversionCategory = I18nConversionCategory.GENERAL;
                    } else if (findKeyword == 1) {
                        int findKeyword2 = findKeyword(strArr[3], NUMBER_MODIFIER_KEYWORDS);
                        if (findKeyword2 != 0 && findKeyword2 != 1 && findKeyword2 != 2 && findKeyword2 != 3) {
                            try {
                                new DecimalFormat(strArr[3], DecimalFormatSymbols.getInstance(locale));
                            } catch (IllegalArgumentException e) {
                                maxOffset = i3;
                                throw e;
                            }
                        }
                        i18nConversionCategory = I18nConversionCategory.NUMBER;
                    } else if (findKeyword == 2 || findKeyword == 3) {
                        int findKeyword3 = findKeyword(strArr[3], DATE_TIME_MODIFIER_KEYWORDS);
                        if (findKeyword3 < 0 || findKeyword3 >= DATE_TIME_MODIFIER_KEYWORDS.length) {
                            try {
                                new SimpleDateFormat(strArr[3], locale);
                            } catch (IllegalArgumentException e2) {
                                maxOffset = i3;
                                throw e2;
                            }
                        }
                        i18nConversionCategory = I18nConversionCategory.DATE;
                    } else if (findKeyword == 4) {
                        if (strArr[3].length() == 0) {
                            throw new IllegalArgumentException("Choice Pattern requires Subformat Pattern: " + strArr[3]);
                        }
                        try {
                            new ChoiceFormat(strArr[3]);
                            i18nConversionCategory = I18nConversionCategory.NUMBER;
                        } catch (Exception e3) {
                            maxOffset = i3;
                            throw new IllegalArgumentException("Choice Pattern incorrect: " + strArr[3], e3);
                        }
                    } else {
                        maxOffset = i3;
                        throw new IllegalArgumentException("unknown format type: " + strArr[2]);
                    }
                } else {
                    i18nConversionCategory = I18nConversionCategory.GENERAL;
                }
                categories.add(i18nConversionCategory);
            } catch (NumberFormatException e4) {
                throw new IllegalArgumentException("can't parse argument number: " + strArr[1], e4);
            }
        }

        private static final int findKeyword(String str, String[] strArr) {
            for (int i = 0; i < strArr.length; i++) {
                if (str.equals(strArr[i])) {
                    return i;
                }
            }
            String lowerCase = str.trim().toLowerCase(Locale.ROOT);
            if (lowerCase == str) {
                return -1;
            }
            for (int i2 = 0; i2 < strArr.length; i2++) {
                if (lowerCase.equals(strArr[i2])) {
                    return i2;
                }
            }
            return -1;
        }
    }
}
