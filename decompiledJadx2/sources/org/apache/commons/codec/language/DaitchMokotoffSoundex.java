package org.apache.commons.codec.language;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class DaitchMokotoffSoundex implements StringEncoder {
    private static final String COMMENT = "//";
    private static final String DOUBLE_QUOTE = "\"";
    private static final int MAX_LENGTH = 6;
    private static final String MULTILINE_COMMENT_END = "*/";
    private static final String MULTILINE_COMMENT_START = "/*";
    private static final String RESOURCE_FILE = "org/apache/commons/codec/language/dmrules.txt";
    private final boolean folding;
    private static final Map<Character, List<Rule>> RULES = new HashMap();
    private static final Map<Character, Character> FOLDINGS = new HashMap();

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes7.dex
     */
    /* loaded from: classes2.dex */
    public static final class Branch {
        private final StringBuilder builder;
        private String cachedString;
        private String lastReplacement;

        private Branch() {
            this.builder = new StringBuilder();
            this.lastReplacement = null;
            this.cachedString = null;
        }

        public Branch createBranch() {
            Branch branch = new Branch();
            branch.builder.append(toString());
            branch.lastReplacement = this.lastReplacement;
            return branch;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Branch) {
                return toString().equals(((Branch) obj).toString());
            }
            return false;
        }

        public void finish() {
            while (this.builder.length() < 6) {
                this.builder.append('0');
                this.cachedString = null;
            }
        }

        public int hashCode() {
            return toString().hashCode();
        }

        public void processNextReplacement(String str, boolean z) {
            String str2 = this.lastReplacement;
            if ((str2 == null || !str2.endsWith(str) || z) && this.builder.length() < 6) {
                this.builder.append(str);
                if (this.builder.length() > 6) {
                    StringBuilder sb = this.builder;
                    sb.delete(6, sb.length());
                }
                this.cachedString = null;
            }
            this.lastReplacement = str;
        }

        public String toString() {
            if (this.cachedString == null) {
                this.cachedString = this.builder.toString();
            }
            return this.cachedString;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes7.dex
     */
    /* loaded from: classes2.dex */
    public static final class Rule {
        private final String pattern;
        private final String[] replacementAtStart;
        private final String[] replacementBeforeVowel;
        private final String[] replacementDefault;

        private boolean isVowel(char c) {
            return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
        }

        protected Rule(String str, String str2, String str3, String str4) {
            this.pattern = str;
            this.replacementAtStart = str2.split("\\|");
            this.replacementBeforeVowel = str3.split("\\|");
            this.replacementDefault = str4.split("\\|");
        }

        public int getPatternLength() {
            return this.pattern.length();
        }

        public String[] getReplacements(String str, boolean z) {
            if (z) {
                return this.replacementAtStart;
            }
            int patternLength = getPatternLength();
            if (patternLength < str.length() ? isVowel(str.charAt(patternLength)) : false) {
                return this.replacementBeforeVowel;
            }
            return this.replacementDefault;
        }

        public boolean matches(String str) {
            return str.startsWith(this.pattern);
        }

        public String toString() {
            return String.format("%s=(%s,%s,%s)", this.pattern, Arrays.asList(this.replacementAtStart), Arrays.asList(this.replacementBeforeVowel), Arrays.asList(this.replacementDefault));
        }
    }

    static {
        InputStream resourceAsStream = DaitchMokotoffSoundex.class.getClassLoader().getResourceAsStream(RESOURCE_FILE);
        if (resourceAsStream == null) {
            throw new IllegalArgumentException("Unable to load resource: org/apache/commons/codec/language/dmrules.txt");
        }
        Scanner scanner = new Scanner(resourceAsStream, "UTF-8");
        try {
            parseRules(scanner, RESOURCE_FILE, RULES, FOLDINGS);
            scanner.close();
            Iterator<Map.Entry<Character, List<Rule>>> it = RULES.entrySet().iterator();
            while (it.hasNext()) {
                Collections.sort(it.next().getValue(), new Comparator<Rule>() { // from class: org.apache.commons.codec.language.DaitchMokotoffSoundex.1
                    @Override // java.util.Comparator
                    public int compare(Rule rule, Rule rule2) {
                        return rule2.getPatternLength() - rule.getPatternLength();
                    }
                });
            }
        } catch (Throwable th) {
            scanner.close();
            throw th;
        }
    }

    private static void parseRules(Scanner scanner, String str, Map<Character, List<Rule>> map, Map<Character, Character> map2) {
        int i = 0;
        boolean z = false;
        while (scanner.hasNextLine()) {
            i++;
            String nextLine = scanner.nextLine();
            if (z) {
                if (nextLine.endsWith(MULTILINE_COMMENT_END)) {
                    z = false;
                }
            } else if (nextLine.startsWith(MULTILINE_COMMENT_START)) {
                z = true;
            } else {
                int indexOf = nextLine.indexOf(COMMENT);
                String trim = (indexOf >= 0 ? nextLine.substring(0, indexOf) : nextLine).trim();
                if (trim.length() == 0) {
                    continue;
                } else if (trim.contains("=")) {
                    String[] split = trim.split("=");
                    if (split.length != 2) {
                        throw new IllegalArgumentException("Malformed folding statement split into " + split.length + " parts: " + nextLine + " in " + str);
                    }
                    String str2 = split[0];
                    String str3 = split[1];
                    if (str2.length() != 1 || str3.length() != 1) {
                        throw new IllegalArgumentException("Malformed folding statement - patterns are not single characters: " + nextLine + " in " + str);
                    }
                    map2.put(Character.valueOf(str2.charAt(0)), Character.valueOf(str3.charAt(0)));
                } else {
                    String[] split2 = trim.split("\\s+");
                    if (split2.length != 4) {
                        throw new IllegalArgumentException("Malformed rule statement split into " + split2.length + " parts: " + nextLine + " in " + str);
                    }
                    try {
                        Rule rule = new Rule(stripQuotes(split2[0]), stripQuotes(split2[1]), stripQuotes(split2[2]), stripQuotes(split2[3]));
                        char charAt = rule.pattern.charAt(0);
                        List<Rule> list = map.get(Character.valueOf(charAt));
                        if (list == null) {
                            list = new ArrayList<>();
                            map.put(Character.valueOf(charAt), list);
                        }
                        list.add(rule);
                    } catch (IllegalArgumentException e) {
                        throw new IllegalStateException("Problem parsing line '" + i + "' in " + str, e);
                    }
                }
            }
        }
    }

    private static String stripQuotes(String str) {
        if (str.startsWith(DOUBLE_QUOTE)) {
            str = str.substring(1);
        }
        return str.endsWith(DOUBLE_QUOTE) ? str.substring(0, str.length() - 1) : str;
    }

    public DaitchMokotoffSoundex() {
        this(true);
    }

    public DaitchMokotoffSoundex(boolean z) {
        this.folding = z;
    }

    private String cleanup(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (!Character.isWhitespace(c)) {
                char lowerCase = Character.toLowerCase(c);
                if (this.folding && FOLDINGS.containsKey(Character.valueOf(lowerCase))) {
                    lowerCase = FOLDINGS.get(Character.valueOf(lowerCase)).charValue();
                }
                sb.append(lowerCase);
            }
        }
        return sb.toString();
    }

    @Override // org.apache.commons.codec.Encoder
    public Object encode(Object obj) throws EncoderException {
        if (!(obj instanceof String)) {
            throw new EncoderException("Parameter supplied to DaitchMokotoffSoundex encode is not of type java.lang.String");
        }
        return encode((String) obj);
    }

    @Override // org.apache.commons.codec.StringEncoder
    public String encode(String str) {
        if (str == null) {
            return null;
        }
        return soundex(str, false)[0];
    }

    public String soundex(String str) {
        String[] soundex = soundex(str, true);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (String str2 : soundex) {
            sb.append(str2);
            i++;
            if (i < soundex.length) {
                sb.append('|');
            }
        }
        return sb.toString();
    }

    private String[] soundex(String str, boolean z) {
        String str2;
        int i;
        String str3;
        if (str == null) {
            return null;
        }
        String cleanup = cleanup(str);
        LinkedHashSet<Branch> linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(new Branch());
        int i2 = 0;
        char c = 0;
        while (i2 < cleanup.length()) {
            char charAt = cleanup.charAt(i2);
            if (!Character.isWhitespace(charAt)) {
                String substring = cleanup.substring(i2);
                List<Rule> list = RULES.get(Character.valueOf(charAt));
                if (list != null) {
                    List arrayList = z ? new ArrayList() : Collections.EMPTY_LIST;
                    Iterator<Rule> it = list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            str2 = cleanup;
                            i = 1;
                            break;
                        }
                        Rule next = it.next();
                        if (next.matches(substring)) {
                            if (z) {
                                arrayList.clear();
                            }
                            String[] replacements = next.getReplacements(substring, c == 0);
                            boolean z2 = replacements.length > 1 && z;
                            for (Branch branch : linkedHashSet) {
                                int length = replacements.length;
                                int i3 = 0;
                                while (true) {
                                    if (i3 >= length) {
                                        str3 = cleanup;
                                        break;
                                    }
                                    String str4 = replacements[i3];
                                    Branch createBranch = z2 ? branch.createBranch() : branch;
                                    str3 = cleanup;
                                    createBranch.processNextReplacement(str4, (c == 'm' && charAt == 'n') || (c == 'n' && charAt == 'm'));
                                    if (z) {
                                        arrayList.add(createBranch);
                                        i3++;
                                        cleanup = str3;
                                    }
                                }
                                cleanup = str3;
                            }
                            str2 = cleanup;
                            if (z) {
                                linkedHashSet.clear();
                                linkedHashSet.addAll(arrayList);
                            }
                            i = 1;
                            i2 += next.getPatternLength() - 1;
                        }
                    }
                    c = charAt;
                    i2 += i;
                    cleanup = str2;
                }
            }
            str2 = cleanup;
            i = 1;
            i2 += i;
            cleanup = str2;
        }
        String[] strArr = new String[linkedHashSet.size()];
        int i4 = 0;
        for (Branch branch2 : linkedHashSet) {
            branch2.finish();
            strArr[i4] = branch2.toString();
            i4++;
        }
        return strArr;
    }
}
