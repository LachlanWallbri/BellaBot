package org.threeten.p095bp.format;

import java.text.DateFormatSymbols;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.threeten.p095bp.temporal.ChronoField;
import org.threeten.p095bp.temporal.IsoFields;
import org.threeten.p095bp.temporal.TemporalField;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class SimpleDateTimeTextProvider extends DateTimeTextProvider {
    private static final Comparator<Map.Entry<String, Long>> COMPARATOR = new Comparator<Map.Entry<String, Long>>() { // from class: org.threeten.bp.format.SimpleDateTimeTextProvider.1
        @Override // java.util.Comparator
        public int compare(Map.Entry<String, Long> entry, Map.Entry<String, Long> entry2) {
            return entry2.getKey().length() - entry.getKey().length();
        }
    };
    private final ConcurrentMap<Map.Entry<TemporalField, Locale>, Object> cache = new ConcurrentHashMap(16, 0.75f, 2);

    @Override // org.threeten.p095bp.format.DateTimeTextProvider
    public String getText(TemporalField temporalField, long j, TextStyle textStyle, Locale locale) {
        Object findStore = findStore(temporalField, locale);
        if (findStore instanceof LocaleStore) {
            return ((LocaleStore) findStore).getText(j, textStyle);
        }
        return null;
    }

    @Override // org.threeten.p095bp.format.DateTimeTextProvider
    public Iterator<Map.Entry<String, Long>> getTextIterator(TemporalField temporalField, TextStyle textStyle, Locale locale) {
        Object findStore = findStore(temporalField, locale);
        if (findStore instanceof LocaleStore) {
            return ((LocaleStore) findStore).getTextIterator(textStyle);
        }
        return null;
    }

    private Object findStore(TemporalField temporalField, Locale locale) {
        Map.Entry<TemporalField, Locale> createEntry = createEntry(temporalField, locale);
        Object obj = this.cache.get(createEntry);
        if (obj != null) {
            return obj;
        }
        this.cache.putIfAbsent(createEntry, createStore(temporalField, locale));
        return this.cache.get(createEntry);
    }

    private Object createStore(TemporalField temporalField, Locale locale) {
        if (temporalField == ChronoField.MONTH_OF_YEAR) {
            DateFormatSymbols dateFormatSymbols = DateFormatSymbols.getInstance(locale);
            HashMap hashMap = new HashMap();
            String[] months = dateFormatSymbols.getMonths();
            HashMap hashMap2 = new HashMap();
            hashMap2.put(1L, months[0]);
            hashMap2.put(2L, months[1]);
            hashMap2.put(3L, months[2]);
            hashMap2.put(4L, months[3]);
            hashMap2.put(5L, months[4]);
            hashMap2.put(6L, months[5]);
            hashMap2.put(7L, months[6]);
            hashMap2.put(8L, months[7]);
            hashMap2.put(9L, months[8]);
            hashMap2.put(10L, months[9]);
            hashMap2.put(11L, months[10]);
            hashMap2.put(12L, months[11]);
            hashMap.put(TextStyle.FULL, hashMap2);
            HashMap hashMap3 = new HashMap();
            hashMap3.put(1L, narrowMonth(1, months[0], locale));
            hashMap3.put(2L, narrowMonth(2, months[1], locale));
            hashMap3.put(3L, narrowMonth(3, months[2], locale));
            hashMap3.put(4L, narrowMonth(4, months[3], locale));
            hashMap3.put(5L, narrowMonth(5, months[4], locale));
            hashMap3.put(6L, narrowMonth(6, months[5], locale));
            hashMap3.put(7L, narrowMonth(7, months[6], locale));
            hashMap3.put(8L, narrowMonth(8, months[7], locale));
            hashMap3.put(9L, narrowMonth(9, months[8], locale));
            hashMap3.put(10L, narrowMonth(10, months[9], locale));
            hashMap3.put(11L, narrowMonth(11, months[10], locale));
            hashMap3.put(12L, narrowMonth(12, months[11], locale));
            hashMap.put(TextStyle.NARROW, hashMap3);
            String[] shortMonths = dateFormatSymbols.getShortMonths();
            HashMap hashMap4 = new HashMap();
            hashMap4.put(1L, shortMonths[0]);
            hashMap4.put(2L, shortMonths[1]);
            hashMap4.put(3L, shortMonths[2]);
            hashMap4.put(4L, shortMonths[3]);
            hashMap4.put(5L, shortMonths[4]);
            hashMap4.put(6L, shortMonths[5]);
            hashMap4.put(7L, shortMonths[6]);
            hashMap4.put(8L, shortMonths[7]);
            hashMap4.put(9L, shortMonths[8]);
            hashMap4.put(10L, shortMonths[9]);
            hashMap4.put(11L, shortMonths[10]);
            hashMap4.put(12L, shortMonths[11]);
            hashMap.put(TextStyle.SHORT, hashMap4);
            return createLocaleStore(hashMap);
        }
        if (temporalField == ChronoField.DAY_OF_WEEK) {
            DateFormatSymbols dateFormatSymbols2 = DateFormatSymbols.getInstance(locale);
            HashMap hashMap5 = new HashMap();
            String[] weekdays = dateFormatSymbols2.getWeekdays();
            HashMap hashMap6 = new HashMap();
            hashMap6.put(1L, weekdays[2]);
            hashMap6.put(2L, weekdays[3]);
            hashMap6.put(3L, weekdays[4]);
            hashMap6.put(4L, weekdays[5]);
            hashMap6.put(5L, weekdays[6]);
            hashMap6.put(6L, weekdays[7]);
            hashMap6.put(7L, weekdays[1]);
            hashMap5.put(TextStyle.FULL, hashMap6);
            HashMap hashMap7 = new HashMap();
            hashMap7.put(1L, narrowDayOfWeek(1, weekdays[2], locale));
            hashMap7.put(2L, narrowDayOfWeek(2, weekdays[3], locale));
            hashMap7.put(3L, narrowDayOfWeek(3, weekdays[4], locale));
            hashMap7.put(4L, narrowDayOfWeek(4, weekdays[5], locale));
            hashMap7.put(5L, narrowDayOfWeek(5, weekdays[6], locale));
            hashMap7.put(6L, narrowDayOfWeek(6, weekdays[7], locale));
            hashMap7.put(7L, narrowDayOfWeek(7, weekdays[1], locale));
            hashMap5.put(TextStyle.NARROW, hashMap7);
            String[] shortWeekdays = dateFormatSymbols2.getShortWeekdays();
            HashMap hashMap8 = new HashMap();
            hashMap8.put(1L, shortWeekdays[2]);
            hashMap8.put(2L, shortWeekdays[3]);
            hashMap8.put(3L, shortWeekdays[4]);
            hashMap8.put(4L, shortWeekdays[5]);
            hashMap8.put(5L, shortWeekdays[6]);
            hashMap8.put(6L, shortWeekdays[7]);
            hashMap8.put(7L, shortWeekdays[1]);
            hashMap5.put(TextStyle.SHORT, hashMap8);
            return createLocaleStore(hashMap5);
        }
        if (temporalField == ChronoField.AMPM_OF_DAY) {
            DateFormatSymbols dateFormatSymbols3 = DateFormatSymbols.getInstance(locale);
            HashMap hashMap9 = new HashMap();
            String[] amPmStrings = dateFormatSymbols3.getAmPmStrings();
            HashMap hashMap10 = new HashMap();
            hashMap10.put(0L, amPmStrings[0]);
            hashMap10.put(1L, amPmStrings[1]);
            hashMap9.put(TextStyle.FULL, hashMap10);
            hashMap9.put(TextStyle.SHORT, hashMap10);
            return createLocaleStore(hashMap9);
        }
        if (temporalField == ChronoField.ERA) {
            DateFormatSymbols dateFormatSymbols4 = DateFormatSymbols.getInstance(locale);
            HashMap hashMap11 = new HashMap();
            String[] eras = dateFormatSymbols4.getEras();
            HashMap hashMap12 = new HashMap();
            hashMap12.put(0L, eras[0]);
            hashMap12.put(1L, eras[1]);
            hashMap11.put(TextStyle.SHORT, hashMap12);
            if (locale.getLanguage().equals(Locale.ENGLISH.getLanguage())) {
                HashMap hashMap13 = new HashMap();
                hashMap13.put(0L, "Before Christ");
                hashMap13.put(1L, "Anno Domini");
                hashMap11.put(TextStyle.FULL, hashMap13);
            } else {
                hashMap11.put(TextStyle.FULL, hashMap12);
            }
            HashMap hashMap14 = new HashMap();
            hashMap14.put(0L, eras[0].substring(0, 1));
            hashMap14.put(1L, eras[1].substring(0, 1));
            hashMap11.put(TextStyle.NARROW, hashMap14);
            return createLocaleStore(hashMap11);
        }
        if (temporalField != IsoFields.QUARTER_OF_YEAR) {
            return "";
        }
        HashMap hashMap15 = new HashMap();
        HashMap hashMap16 = new HashMap();
        hashMap16.put(1L, "Q1");
        hashMap16.put(2L, "Q2");
        hashMap16.put(3L, "Q3");
        hashMap16.put(4L, "Q4");
        hashMap15.put(TextStyle.SHORT, hashMap16);
        HashMap hashMap17 = new HashMap();
        hashMap17.put(1L, "1st quarter");
        hashMap17.put(2L, "2nd quarter");
        hashMap17.put(3L, "3rd quarter");
        hashMap17.put(4L, "4th quarter");
        hashMap15.put(TextStyle.FULL, hashMap17);
        return createLocaleStore(hashMap15);
    }

    private String narrowMonth(int i, String str, Locale locale) {
        if (locale.getLanguage().equals("zh") && locale.getCountry().equals("CN")) {
            switch (i) {
                case 1:
                    return "一";
                case 2:
                    return "二";
                case 3:
                    return "三";
                case 4:
                    return "四";
                case 5:
                    return "五";
                case 6:
                    return "六";
                case 7:
                    return "七";
                case 8:
                    return "八";
                case 9:
                    return "九";
                case 10:
                    return "十";
                case 11:
                    return "十一";
                case 12:
                    return "十二";
            }
        }
        if (locale.getLanguage().equals(ArchiveStreamFactory.f8911AR)) {
            switch (i) {
                case 1:
                    return "ي";
                case 2:
                    return "ف";
                case 3:
                    return "م";
                case 4:
                    return "أ";
                case 5:
                    return "و";
                case 6:
                    return "ن";
                case 7:
                    return "ل";
                case 8:
                    return "غ";
                case 9:
                    return "س";
                case 10:
                    return "ك";
                case 11:
                    return "ب";
                case 12:
                    return "د";
            }
        }
        if (locale.getLanguage().equals("ja") && locale.getCountry().equals("JP")) {
            return Integer.toString(i);
        }
        return str.substring(0, 1);
    }

    private String narrowDayOfWeek(int i, String str, Locale locale) {
        if (locale.getLanguage().equals("zh") && locale.getCountry().equals("CN")) {
            switch (i) {
                case 1:
                    return "一";
                case 2:
                    return "二";
                case 3:
                    return "三";
                case 4:
                    return "四";
                case 5:
                    return "五";
                case 6:
                    return "六";
                case 7:
                    return "日";
            }
        }
        if (locale.getLanguage().equals(ArchiveStreamFactory.f8911AR)) {
            switch (i) {
                case 1:
                    return "ن";
                case 2:
                    return "ث";
                case 3:
                    return "ر";
                case 4:
                    return "خ";
                case 5:
                    return "ج";
                case 6:
                    return "س";
                case 7:
                    return "ح";
            }
        }
        return str.substring(0, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <A, B> Map.Entry<A, B> createEntry(A a, B b) {
        return new AbstractMap.SimpleImmutableEntry(a, b);
    }

    private static LocaleStore createLocaleStore(Map<TextStyle, Map<Long, String>> map) {
        map.put(TextStyle.FULL_STANDALONE, map.get(TextStyle.FULL));
        map.put(TextStyle.SHORT_STANDALONE, map.get(TextStyle.SHORT));
        if (map.containsKey(TextStyle.NARROW) && !map.containsKey(TextStyle.NARROW_STANDALONE)) {
            map.put(TextStyle.NARROW_STANDALONE, map.get(TextStyle.NARROW));
        }
        return new LocaleStore(map);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public static final class LocaleStore {
        private final Map<TextStyle, List<Map.Entry<String, Long>>> parsable;
        private final Map<TextStyle, Map<Long, String>> valueTextMap;

        /* JADX INFO: Access modifiers changed from: package-private */
        public LocaleStore(Map<TextStyle, Map<Long, String>> map) {
            this.valueTextMap = map;
            HashMap hashMap = new HashMap();
            ArrayList arrayList = new ArrayList();
            for (TextStyle textStyle : map.keySet()) {
                HashMap hashMap2 = new HashMap();
                for (Map.Entry<Long, String> entry : map.get(textStyle).entrySet()) {
                    hashMap2.put(entry.getValue(), SimpleDateTimeTextProvider.createEntry(entry.getValue(), entry.getKey()));
                }
                ArrayList arrayList2 = new ArrayList(hashMap2.values());
                Collections.sort(arrayList2, SimpleDateTimeTextProvider.COMPARATOR);
                hashMap.put(textStyle, arrayList2);
                arrayList.addAll(arrayList2);
                hashMap.put(null, arrayList);
            }
            Collections.sort(arrayList, SimpleDateTimeTextProvider.COMPARATOR);
            this.parsable = hashMap;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public String getText(long j, TextStyle textStyle) {
            Map<Long, String> map = this.valueTextMap.get(textStyle);
            if (map != null) {
                return map.get(Long.valueOf(j));
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Iterator<Map.Entry<String, Long>> getTextIterator(TextStyle textStyle) {
            List<Map.Entry<String, Long>> list = this.parsable.get(textStyle);
            if (list != null) {
                return list.iterator();
            }
            return null;
        }
    }
}
