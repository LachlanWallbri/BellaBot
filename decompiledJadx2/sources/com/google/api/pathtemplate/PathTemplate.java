package com.google.api.pathtemplate;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.collect.UnmodifiableListIterator;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class PathTemplate {
    public static final String HOSTNAME_VAR = "$hostname";
    private final ImmutableMap<String, Segment> bindings;
    private final ImmutableList<Segment> segments;
    private final boolean urlEncoding;
    private static final Pattern CUSTOM_VERB_PATTERN = Pattern.compile(":([^/*}{=]+)$");
    private static final Pattern HOSTNAME_PATTERN = Pattern.compile("^(\\w+:)?//");
    private static final Splitter SLASH_SPLITTER = Splitter.m615on('/').trimResults();
    private static final Pattern COMPLEX_DELIMITER_PATTERN = Pattern.compile("[_\\-\\.~]");
    private static final Pattern MULTIPLE_COMPLEX_DELIMITER_PATTERN = Pattern.compile("\\}[_\\-\\.~]{2,}\\{");
    private static final Pattern MISSING_COMPLEX_DELIMITER_PATTERN = Pattern.compile("\\}\\{");
    private static final Pattern INVALID_COMPLEX_DELIMITER_PATTERN = Pattern.compile("\\}[^_\\-\\.~]\\{");
    private static final Pattern END_SEGMENT_COMPLEX_DELIMITER_PATTERN = Pattern.compile("\\}[_\\-\\.~]{1}");

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public enum SegmentKind {
        LITERAL,
        CUSTOM_VERB,
        WILDCARD,
        PATH_WILDCARD,
        BINDING,
        END_BINDING
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static abstract class Segment {
        private static final Segment WILDCARD = create(SegmentKind.WILDCARD, "*");
        private static final Segment PATH_WILDCARD = create(SegmentKind.PATH_WILDCARD, "**");
        private static final Segment END_BINDING = create(SegmentKind.END_BINDING, "");

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract String complexSeparator();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract SegmentKind kind();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract String value();

        /* JADX INFO: Access modifiers changed from: private */
        public static Segment create(SegmentKind segmentKind, String str) {
            return new AutoValue_PathTemplate_Segment(segmentKind, str, "");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Segment create(SegmentKind segmentKind, String str, String str2) {
            return new AutoValue_PathTemplate_Segment(segmentKind, str, str2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Segment wildcardCreate(String str) {
            SegmentKind segmentKind = SegmentKind.WILDCARD;
            if (str.isEmpty() || !PathTemplate.COMPLEX_DELIMITER_PATTERN.matcher(str).find()) {
                str = "";
            }
            return new AutoValue_PathTemplate_Segment(segmentKind, "*", str);
        }

        boolean isAnyWildcard() {
            return kind() == SegmentKind.WILDCARD || kind() == SegmentKind.PATH_WILDCARD;
        }

        String separator() {
            int i = C21251.$SwitchMap$com$google$api$pathtemplate$PathTemplate$SegmentKind[kind().ordinal()];
            return i != 1 ? i != 2 ? "/" : "" : ":";
        }
    }

    public static PathTemplate create(String str) {
        return create(str, true);
    }

    public static PathTemplate createWithoutUrlEncoding(String str) {
        return create(str, false);
    }

    private static PathTemplate create(String str, boolean z) {
        return new PathTemplate(parseTemplate(str), z);
    }

    private PathTemplate(Iterable<Segment> iterable, boolean z) {
        this.segments = ImmutableList.copyOf(iterable);
        if (this.segments.isEmpty()) {
            throw new ValidationException("template cannot be empty.", new Object[0]);
        }
        LinkedHashMap newLinkedHashMap = Maps.newLinkedHashMap();
        UnmodifiableIterator<Segment> it = this.segments.iterator();
        while (it.hasNext()) {
            Segment next = it.next();
            if (next.kind() == SegmentKind.BINDING) {
                if (newLinkedHashMap.containsKey(next.value())) {
                    throw new ValidationException("Duplicate binding '%s'", next.value());
                }
                newLinkedHashMap.put(next.value(), next);
            }
        }
        this.bindings = ImmutableMap.copyOf((Map) newLinkedHashMap);
        this.urlEncoding = z;
    }

    public Set<String> vars() {
        return this.bindings.keySet();
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x002e, code lost:
    
        if (r0 == 0) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x003d, code lost:
    
        return new com.google.api.pathtemplate.PathTemplate(r4.segments.subList(0, r0), r4.urlEncoding);
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0047, code lost:
    
        throw new com.google.api.pathtemplate.ValidationException("template does not have a parent", new java.lang.Object[0]);
     */
    /* JADX WARN: Code restructure failed: missing block: B:2:0x0016, code lost:
    
        if (r4.segments.get(r0).kind() == com.google.api.pathtemplate.PathTemplate.SegmentKind.END_BINDING) goto L4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x0018, code lost:
    
        if (r0 <= 0) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x001a, code lost:
    
        r0 = r0 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x002a, code lost:
    
        if (r4.segments.get(r0).kind() == com.google.api.pathtemplate.PathTemplate.SegmentKind.BINDING) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public PathTemplate parentTemplate() {
        int size = this.segments.size() - 1;
    }

    public PathTemplate withoutVars() {
        StringBuilder sb = new StringBuilder();
        UnmodifiableListIterator<Segment> listIterator = this.segments.listIterator();
        boolean z = true;
        while (listIterator.hasNext()) {
            Segment next = listIterator.next();
            int i = C21251.$SwitchMap$com$google$api$pathtemplate$PathTemplate$SegmentKind[next.kind().ordinal()];
            if (i != 2 && i != 3) {
                if (z) {
                    z = false;
                } else {
                    sb.append(next.separator());
                }
                sb.append(next.value());
            }
        }
        return create(sb.toString(), this.urlEncoding);
    }

    public PathTemplate subTemplate(String str) {
        ArrayList newArrayList = Lists.newArrayList();
        UnmodifiableIterator<Segment> it = this.segments.iterator();
        boolean z = false;
        while (it.hasNext()) {
            Segment next = it.next();
            if (next.kind() == SegmentKind.BINDING && next.value().equals(str)) {
                z = true;
            } else if (!z) {
                continue;
            } else {
                if (next.kind() == SegmentKind.END_BINDING) {
                    return create(toSyntax(newArrayList, true), this.urlEncoding);
                }
                newArrayList.add(next);
            }
        }
        throw new ValidationException(String.format("Variable '%s' is undefined in template '%s'", str, toRawString()), new Object[0]);
    }

    public boolean endsWithLiteral() {
        ImmutableList<Segment> immutableList = this.segments;
        return immutableList.get(immutableList.size() - 1).kind() == SegmentKind.LITERAL;
    }

    public boolean endsWithCustomVerb() {
        ImmutableList<Segment> immutableList = this.segments;
        return immutableList.get(immutableList.size() - 1).kind() == SegmentKind.CUSTOM_VERB;
    }

    public TemplatedResourceName parse(String str) {
        return TemplatedResourceName.create(this, str);
    }

    @Nullable
    public String singleVar() {
        if (this.bindings.size() == 1) {
            return this.bindings.entrySet().iterator().next().getKey();
        }
        return null;
    }

    public void validate(String str, String str2) {
        if (!matches(str)) {
            throw new ValidationException(String.format("%s: Parameter \"%s\" must be in the form \"%s\"", str2, str, toString()), new Object[0]);
        }
    }

    public Map<String, String> validatedMatch(String str, String str2) {
        Map<String, String> match = match(str);
        if (match != null) {
            return match;
        }
        throw new ValidationException(String.format("%s: Parameter \"%s\" must be in the form \"%s\"", str2, str, toString()), new Object[0]);
    }

    public boolean matches(String str) {
        return match(str) != null;
    }

    @Nullable
    public Map<String, String> match(String str) {
        return match(str, false);
    }

    @Nullable
    public Map<String, String> matchFromFullName(String str) {
        return match(str, true);
    }

    private Map<String, String> match(String str, boolean z) {
        ImmutableList<Segment> immutableList = this.segments;
        int i = 1;
        Segment segment = immutableList.get(immutableList.size() - 1);
        if (segment.kind() == SegmentKind.CUSTOM_VERB) {
            Matcher matcher = CUSTOM_VERB_PATTERN.matcher(str);
            if (!matcher.find() || !decodeUrl(matcher.group(1)).equals(segment.value())) {
                return null;
            }
            str = str.substring(0, matcher.start(0));
        }
        Matcher matcher2 = HOSTNAME_PATTERN.matcher(str);
        boolean find = matcher2.find();
        if (find) {
            str = matcher2.replaceFirst("");
        }
        List<String> splitToList = SLASH_SPLITTER.splitToList(str);
        LinkedHashMap newLinkedHashMap = Maps.newLinkedHashMap();
        if (!find && !z) {
            i = 0;
        } else {
            if (splitToList.isEmpty()) {
                return null;
            }
            String str2 = splitToList.get(0);
            if (find) {
                str2 = matcher2.group(0) + str2;
            }
            newLinkedHashMap.put(HOSTNAME_VAR, str2);
        }
        if (match(splitToList, find ? alignInputToAlignableSegment(splitToList, i, this.segments.get(0)) : i, this.segments, 0, newLinkedHashMap)) {
            return ImmutableMap.copyOf((Map) newLinkedHashMap);
        }
        return null;
    }

    private int alignInputToAlignableSegment(List<String> list, int i, Segment segment) {
        int i2 = C21251.$SwitchMap$com$google$api$pathtemplate$PathTemplate$SegmentKind[segment.kind().ordinal()];
        if (i2 != 3) {
            return i2 != 4 ? i : alignInputPositionToLiteral(list, i, segment.value());
        }
        return alignInputPositionToLiteral(list, i, segment.value() + "s") + 1;
    }

    private int alignInputPositionToLiteral(List<String> list, int i, String str) {
        while (i < list.size() && !str.equals(list.get(i))) {
            i++;
        }
        return i;
    }

    private boolean match(List<String> list, int i, List<Segment> list2, int i2, Map<String, String> map) {
        ArrayList arrayList = new ArrayList(list);
        String str = null;
        while (true) {
            int i3 = 0;
            if (i2 >= list2.size()) {
                return i == arrayList.size();
            }
            int i4 = i2 + 1;
            Segment segment = list2.get(i2);
            switch (segment.kind()) {
                case END_BINDING:
                    str = null;
                    break;
                case BINDING:
                    str = segment.value();
                    break;
                case LITERAL:
                case WILDCARD:
                    if (i >= arrayList.size()) {
                        return false;
                    }
                    int i5 = i + 1;
                    String decodeUrl = decodeUrl((String) arrayList.get(i));
                    if (segment.kind() == SegmentKind.LITERAL && !segment.value().equals(decodeUrl)) {
                        return false;
                    }
                    if (segment.kind() == SegmentKind.WILDCARD && !segment.complexSeparator().isEmpty()) {
                        int indexOf = decodeUrl.indexOf(segment.complexSeparator());
                        if (indexOf < 0) {
                            return false;
                        }
                        arrayList.add(i5, decodeUrl.substring(indexOf + 1));
                        decodeUrl = decodeUrl.substring(0, indexOf);
                        arrayList.set(i5 - 1, decodeUrl);
                    }
                    if (str != null) {
                        map.put(str, concatCaptures(map.get(str), decodeUrl));
                    }
                    i = i5;
                    break;
                    break;
                case PATH_WILDCARD:
                    for (int i6 = i4; i6 < list2.size(); i6++) {
                        int i7 = C21251.$SwitchMap$com$google$api$pathtemplate$PathTemplate$SegmentKind[list2.get(i6).kind().ordinal()];
                        if (i7 != 2 && i7 != 3) {
                            i3++;
                        }
                    }
                    int size = (arrayList.size() - i) - i3;
                    if (size == 0 && !map.containsKey(str)) {
                        map.put(str, "");
                    }
                    while (true) {
                        int i8 = size - 1;
                        if (size > 0) {
                            map.put(str, concatCaptures(map.get(str), decodeUrl((String) arrayList.get(i))));
                            size = i8;
                            i++;
                        }
                    }
                    break;
            }
            i2 = i4;
        }
    }

    private static String concatCaptures(@Nullable String str, String str2) {
        if (str == null) {
            return str2;
        }
        return str + "/" + str2;
    }

    public String instantiate(Map<String, String> map) {
        return instantiate(map, false);
    }

    public String instantiate(String... strArr) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        for (int i = 0; i < strArr.length; i += 2) {
            builder.put(strArr[i], strArr[i + 1]);
        }
        return instantiate(builder.build());
    }

    public String instantiatePartial(Map<String, String> map) {
        return instantiate(map, true);
    }

    private String instantiate(Map<String, String> map, boolean z) {
        StringBuilder sb = new StringBuilder();
        if (map.containsKey(HOSTNAME_VAR)) {
            sb.append(map.get(HOSTNAME_VAR));
            sb.append('/');
        }
        UnmodifiableListIterator<Segment> listIterator = this.segments.listIterator();
        boolean z2 = true;
        String str = "";
        boolean z3 = false;
        while (listIterator.hasNext()) {
            Segment next = listIterator.next();
            if (!z3 && !z2) {
                if (str.isEmpty()) {
                    str = next.separator();
                }
                sb.append(str);
                str = next.complexSeparator().isEmpty() ? next.separator() : next.complexSeparator();
            }
            int i = C21251.$SwitchMap$com$google$api$pathtemplate$PathTemplate$SegmentKind[next.kind().ordinal()];
            if (i != 2) {
                if (i == 3) {
                    String value = next.value();
                    String str2 = map.get(next.value());
                    if (str2 == null) {
                        if (!z) {
                            throw new ValidationException(String.format("Unbound variable '%s'. Bindings: %s", value, map), new Object[0]);
                        }
                        if (value.startsWith("$")) {
                            sb.append(listIterator.next().value());
                            listIterator.next();
                        } else {
                            sb.append('{');
                            sb.append(next.value());
                            sb.append('=');
                            z2 = true;
                        }
                    } else {
                        boolean z4 = listIterator.next().kind() == SegmentKind.PATH_WILDCARD || listIterator.next().kind() != SegmentKind.END_BINDING;
                        restore(listIterator, listIterator.nextIndex() - 2);
                        if (!z4) {
                            sb.append(encodeUrl(str2));
                        } else {
                            boolean z5 = true;
                            for (String str3 : SLASH_SPLITTER.split(str2)) {
                                if (!z5) {
                                    sb.append('/');
                                }
                                sb.append(encodeUrl(str3));
                                z5 = false;
                            }
                        }
                        z3 = true;
                    }
                } else if (!z3) {
                    sb.append(next.value());
                }
                z2 = false;
            } else {
                if (!z3) {
                    sb.append('}');
                }
                z3 = false;
                z2 = false;
            }
        }
        return sb.toString();
    }

    public String encode(String... strArr) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        int length = strArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            builder.put("$" + i2, strArr[i]);
            i++;
            i2++;
        }
        return instantiate(builder.build());
    }

    public List<String> decode(String str) {
        Map<String, String> match = match(str);
        if (match == null) {
            throw new IllegalArgumentException(String.format("template '%s' does not match '%s'", this, str));
        }
        ArrayList newArrayList = Lists.newArrayList();
        for (Map.Entry<String, String> entry : match.entrySet()) {
            String key = entry.getKey();
            if (!key.startsWith("$")) {
                throw new IllegalArgumentException("template must not contain named bindings");
            }
            int parseInt = Integer.parseInt(key.substring(1));
            while (newArrayList.size() <= parseInt) {
                newArrayList.add("");
            }
            newArrayList.set(parseInt, entry.getValue());
        }
        return ImmutableList.copyOf((Collection) newArrayList);
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x024b  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0250 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x026c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0210  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0219  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static ImmutableList<Segment> parseTemplate(String str) {
        String str2;
        int i;
        String str3;
        int i2;
        char c;
        int i3;
        String str4 = str;
        int i4 = 1;
        if (str4.startsWith("/")) {
            str4 = str4.substring(1);
        }
        Matcher matcher = CUSTOM_VERB_PATTERN.matcher(str4);
        int i5 = 0;
        if (matcher.find()) {
            str2 = matcher.group(1);
            str4 = str4.substring(0, matcher.start(0));
        } else {
            str2 = null;
        }
        ImmutableList.Builder builder = ImmutableList.builder();
        int i6 = 0;
        int i7 = 0;
        String str5 = null;
        for (String str6 : Splitter.m615on('/').trimResults().split(str4)) {
            if (str6.equals("_deleted-topic_")) {
                builder.add((ImmutableList.Builder) Segment.create(SegmentKind.LITERAL, str6));
            } else {
                int i8 = ((str4.indexOf(str6) + str6.length() == str4.length() ? i4 : i5) == 0 && (str6.equals("-") || str6.equals("-}"))) ? i4 : i5;
                if (i8 == 0 && (COMPLEX_DELIMITER_PATTERN.matcher(str6.substring(i5, i4)).find() || COMPLEX_DELIMITER_PATTERN.matcher(str6.substring(str6.length() - i4)).find())) {
                    Object[] objArr = new Object[i4];
                    objArr[i5] = str6;
                    throw new ValidationException("parse error: invalid begin or end character in '%s'", objArr);
                }
                if (MULTIPLE_COMPLEX_DELIMITER_PATTERN.matcher(str6).find() || MISSING_COMPLEX_DELIMITER_PATTERN.matcher(str6).find()) {
                    Object[] objArr2 = new Object[i4];
                    objArr2[i5] = str6;
                    throw new ValidationException("parse error: missing or 2+ consecutive delimiter characters in '%s'", objArr2);
                }
                if (!str6.startsWith("{")) {
                    i = i5;
                    str3 = str5;
                    i2 = i;
                } else {
                    if (str5 != null) {
                        Object[] objArr3 = new Object[i4];
                        objArr3[i5] = str4;
                        throw new ValidationException("parse error: nested binding in '%s'", objArr3);
                    }
                    str6 = str6.substring(i4);
                    if (INVALID_COMPLEX_DELIMITER_PATTERN.matcher(str6).find()) {
                        Object[] objArr4 = new Object[i4];
                        objArr4[i5] = str6;
                        throw new ValidationException("parse error: invalid complex resource ID delimiter character in '%s'", objArr4);
                    }
                    i = (i8 == 0 && END_SEGMENT_COMPLEX_DELIMITER_PATTERN.matcher(str6).find()) ? i4 : i5;
                    if (i != 0) {
                        builder.addAll((Iterable) parseComplexResourceId(str6));
                        str3 = str5;
                        i2 = i5;
                    } else {
                        int indexOf = str6.indexOf(61);
                        if (indexOf <= 0) {
                            if (str6.endsWith("}")) {
                                str5 = str6.substring(i5, str6.length() - i4).trim();
                                str6 = str6.substring(str6.length() - i4).trim();
                            } else {
                                Object[] objArr5 = new Object[i4];
                                objArr5[i5] = str4;
                                throw new ValidationException("parse error: invalid binding syntax in '%s'", objArr5);
                            }
                        } else if (str6.indexOf(45) > 0 || i8 == 0) {
                            str5 = str6.substring(i5, indexOf).trim();
                            str6 = str6.substring(indexOf + 1).trim();
                            i3 = i5;
                            builder.add((ImmutableList.Builder) Segment.create(SegmentKind.BINDING, str5));
                            String str7 = str5;
                            i2 = i3;
                            str3 = str7;
                        }
                        i3 = i4;
                        builder.add((ImmutableList.Builder) Segment.create(SegmentKind.BINDING, str5));
                        String str72 = str5;
                        i2 = i3;
                        str3 = str72;
                    }
                }
                if (i == 0) {
                    boolean endsWith = str6.endsWith("}");
                    if (endsWith) {
                        str6 = str6.substring(i5, str6.length() - i4).trim();
                    }
                    int hashCode = str6.hashCode();
                    if (hashCode == 0) {
                        if (str6.equals("")) {
                            c = 2;
                            if (c != 0) {
                            }
                            if ("**".equals(str6)) {
                            }
                            if (str6.length() != 2) {
                            }
                            if (str3 != null) {
                            }
                            if (endsWith) {
                            }
                            i4 = 1;
                            if (i7 <= i4) {
                            }
                        }
                        c = 65535;
                        if (c != 0) {
                        }
                        if ("**".equals(str6)) {
                        }
                        if (str6.length() != 2) {
                        }
                        if (str3 != null) {
                        }
                        if (endsWith) {
                        }
                        i4 = 1;
                        if (i7 <= i4) {
                        }
                    } else if (hashCode == 42) {
                        if (str6.equals("*")) {
                            c = 1;
                            if (c != 0) {
                            }
                            if ("**".equals(str6)) {
                            }
                            if (str6.length() != 2) {
                            }
                            if (str3 != null) {
                            }
                            if (endsWith) {
                            }
                            i4 = 1;
                            if (i7 <= i4) {
                            }
                        }
                        c = 65535;
                        if (c != 0) {
                        }
                        if ("**".equals(str6)) {
                        }
                        if (str6.length() != 2) {
                        }
                        if (str3 != null) {
                        }
                        if (endsWith) {
                        }
                        i4 = 1;
                        if (i7 <= i4) {
                        }
                    } else if (hashCode != 45) {
                        if (hashCode == 1344 && str6.equals("**")) {
                            c = 0;
                            if (c != 0 || c == 1) {
                                if ("**".equals(str6)) {
                                    i7++;
                                }
                                Segment segment = str6.length() != 2 ? Segment.PATH_WILDCARD : Segment.WILDCARD;
                                if (str3 != null) {
                                    builder.add((ImmutableList.Builder) Segment.create(SegmentKind.BINDING, "$" + i6));
                                    i6++;
                                    builder.add((ImmutableList.Builder) segment);
                                    builder.add((ImmutableList.Builder) Segment.END_BINDING);
                                } else {
                                    builder.add((ImmutableList.Builder) segment);
                                }
                            } else if (c != 2) {
                                if (c != 3) {
                                    builder.add((ImmutableList.Builder) Segment.create(SegmentKind.LITERAL, str6));
                                } else {
                                    builder.add((ImmutableList.Builder) Segment.WILDCARD);
                                    i2 = 0;
                                }
                            } else if (!endsWith) {
                                throw new ValidationException("parse error: empty segment not allowed in '%s'", str4);
                            }
                            if (endsWith || i != 0) {
                                i4 = 1;
                            } else {
                                if (i2 != 0) {
                                    builder.add((ImmutableList.Builder) Segment.WILDCARD);
                                }
                                builder.add((ImmutableList.Builder) Segment.END_BINDING);
                                i4 = 1;
                                str3 = null;
                            }
                            if (i7 <= i4) {
                                Object[] objArr6 = new Object[i4];
                                objArr6[0] = str4;
                                throw new ValidationException("parse error: pattern must not contain more than one path wildcard ('**') in '%s'", objArr6);
                            }
                            str5 = str3;
                            i5 = 0;
                        }
                        c = 65535;
                        if (c != 0) {
                        }
                        if ("**".equals(str6)) {
                        }
                        if (str6.length() != 2) {
                        }
                        if (str3 != null) {
                        }
                        if (endsWith) {
                        }
                        i4 = 1;
                        if (i7 <= i4) {
                        }
                    } else {
                        if (str6.equals("-")) {
                            c = 3;
                            if (c != 0) {
                            }
                            if ("**".equals(str6)) {
                            }
                            if (str6.length() != 2) {
                            }
                            if (str3 != null) {
                            }
                            if (endsWith) {
                            }
                            i4 = 1;
                            if (i7 <= i4) {
                            }
                        }
                        c = 65535;
                        if (c != 0) {
                        }
                        if ("**".equals(str6)) {
                        }
                        if (str6.length() != 2) {
                        }
                        if (str3 != null) {
                        }
                        if (endsWith) {
                        }
                        i4 = 1;
                        if (i7 <= i4) {
                        }
                    }
                } else {
                    str5 = str3;
                }
            }
        }
        if (str2 != null) {
            builder.add((ImmutableList.Builder) Segment.create(SegmentKind.CUSTOM_VERB, str2));
        }
        return builder.build();
    }

    private static List<Segment> parseComplexResourceId(String str) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Matcher matcher = END_SEGMENT_COMPLEX_DELIMITER_PATTERN.matcher(str);
        boolean find = matcher.find();
        while (find) {
            int start = matcher.start();
            if (str.substring(start).startsWith("}")) {
                start++;
            }
            int i = start + 1;
            String substring = str.substring(start, i);
            if (!COMPLEX_DELIMITER_PATTERN.matcher(substring).find()) {
                throw new ValidationException("parse error: invalid complex ID delimiter '%s' in '%s'", substring, str);
            }
            arrayList2.add(substring);
            find = matcher.find(i);
        }
        arrayList2.add("");
        int i2 = 0;
        for (String str2 : Splitter.onPattern("\\}[_\\-\\.~]").trimResults().split(str)) {
            if (str2.startsWith("{")) {
                str2 = str2.substring(1);
            }
            String trim = str2.trim();
            boolean endsWith = str2.endsWith("}");
            int indexOf = str2.indexOf(61);
            if (indexOf > 0) {
                trim = str2.substring(0, indexOf).trim();
                if (str2.substring(indexOf + 1).trim().equals("**")) {
                    throw new ValidationException("parse error: wildcard path not allowed in complex ID resource '%s'", trim);
                }
            } else if (endsWith) {
                trim = str2.substring(0, str2.length() - 1).trim();
                str2.substring(str2.length() - 1).trim();
            }
            String str3 = i2 < arrayList2.size() ? (String) arrayList2.get(i2) : "";
            arrayList.add(Segment.create(SegmentKind.BINDING, trim, str3));
            arrayList.add(Segment.wildcardCreate(str3));
            arrayList.add(Segment.END_BINDING);
            i2++;
        }
        return arrayList;
    }

    private String encodeUrl(String str) {
        if (this.urlEncoding) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException unused) {
                throw new ValidationException("UTF-8 encoding is not supported on this platform", new Object[0]);
            }
        }
        if (!str.contains("/")) {
            return str;
        }
        throw new ValidationException("Invalid character \"/\" in path section \"" + str + "\".", new Object[0]);
    }

    private String decodeUrl(String str) {
        if (!this.urlEncoding) {
            return str;
        }
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            throw new ValidationException("UTF-8 encoding is not supported on this platform", new Object[0]);
        }
    }

    private static boolean peek(ListIterator<Segment> listIterator, SegmentKind... segmentKindArr) {
        int nextIndex = listIterator.nextIndex();
        for (SegmentKind segmentKind : segmentKindArr) {
            if (!listIterator.hasNext() || listIterator.next().kind() != segmentKind) {
                break;
            }
        }
        restore(listIterator, nextIndex);
        return false;
    }

    private static void restore(ListIterator<?> listIterator, int i) {
        while (listIterator.nextIndex() > i) {
            listIterator.previous();
        }
    }

    public String toString() {
        return toSyntax(this.segments, true);
    }

    public String toRawString() {
        return toSyntax(this.segments, false);
    }

    private static String toSyntax(List<Segment> list, boolean z) {
        StringBuilder sb = new StringBuilder();
        ListIterator<Segment> listIterator = list.listIterator();
        while (true) {
            boolean z2 = true;
            while (listIterator.hasNext()) {
                Segment next = listIterator.next();
                if (!z2) {
                    sb.append(next.separator());
                }
                int i = C21251.$SwitchMap$com$google$api$pathtemplate$PathTemplate$SegmentKind[next.kind().ordinal()];
                if (i == 2) {
                    sb.append('}');
                } else if (i == 3) {
                    if (z && next.value().startsWith("$")) {
                        sb.append(listIterator.next().value());
                        listIterator.next();
                    } else {
                        sb.append('{');
                        sb.append(next.value());
                        if (z && peek(listIterator, SegmentKind.WILDCARD, SegmentKind.END_BINDING)) {
                            sb.append('}');
                        } else {
                            sb.append('=');
                        }
                    }
                } else {
                    sb.append(next.value());
                }
                z2 = false;
            }
            return sb.toString();
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof PathTemplate) {
            return Objects.equals(this.segments, ((PathTemplate) obj).segments);
        }
        return false;
    }

    public int hashCode() {
        return this.segments.hashCode();
    }
}
