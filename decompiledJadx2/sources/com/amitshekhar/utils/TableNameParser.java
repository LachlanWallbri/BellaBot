package com.amitshekhar.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public final class TableNameParser {
    private static final String KEYWORD_FROM = "from";
    private static final String KEYWORD_TABLE = "table";
    private static final int NO_INDEX = -1;
    private static final String REGEX_SPACE = "\\s+";
    private static final String SPACE = " ";
    private static final String TOKEN_ASTERICK = "*";
    private static final String TOKEN_COMMA = ",";
    private static final String TOKEN_CREATE = "create";
    private static final String TOKEN_DELETE = "delete";
    private static final String TOKEN_INDEX = "index";
    private static final String TOKEN_ORACLE_HINT_END = "*/";
    private static final String TOKEN_ORACLE_HINT_START = "/*+";
    private static final String TOKEN_SEMI_COLON = ";";
    private static final String TOKEN_SET = "set";
    private static final String TOKEN_SINGLE_LINE_COMMENT = "--";
    private Map<String, String> tables = new HashMap();
    private static final String KEYWORD_INTO = "into";
    private static final String KEYWORD_JOIN = "join";
    private static final String KEYWORD_USING = "using";
    private static final String KEYWORD_UPDATE = "update";
    private static final List<String> concerned = Arrays.asList("table", KEYWORD_INTO, KEYWORD_JOIN, KEYWORD_USING, KEYWORD_UPDATE);
    private static final String TOKEN_PARAN_START = "(";
    private static final String TOKEN_OF = "of";
    private static final String TOKEN_DUAL = "dual";
    private static final List<String> ignored = Arrays.asList(TOKEN_PARAN_START, "set", TOKEN_OF, TOKEN_DUAL);
    private static String TOKEN_NEWLINE = "\\r\\n|\\r|\\n|\\n\\r";

    public TableNameParser(String str) {
        String[] split = clean(normalized(removeComments(str))).split(REGEX_SPACE);
        int i = 0;
        String str2 = split[0];
        if (isOracleSpecialDelete(str2, split, 0)) {
            handleSpecialOracleSpecialDelete(str2, split, 0);
            return;
        }
        if (isCreateIndex(str2, split, 0)) {
            handleCreateIndex(str2, split, 0);
            return;
        }
        while (moreTokens(split, i)) {
            int i2 = i + 1;
            String str3 = split[i];
            if (isFromToken(str3)) {
                processFromToken(split, i2);
            } else if (shouldProcess(str3)) {
                i = i2 + 1;
                considerInclusion(split[i2]);
                if (moreTokens(split, i)) {
                    i2 = i + 1;
                    String str4 = split[i];
                }
            }
            i = i2;
        }
    }

    private String removeComments(String str) {
        StringBuilder sb = new StringBuilder(str);
        for (int indexOf = sb.indexOf(TOKEN_SINGLE_LINE_COMMENT); indexOf > -1; indexOf = sb.indexOf(TOKEN_SINGLE_LINE_COMMENT)) {
            int indexOfRegex = indexOfRegex(TOKEN_NEWLINE, sb.substring(indexOf));
            if (indexOfRegex == -1) {
                return sb.substring(0, indexOf);
            }
            sb.replace(indexOf, indexOfRegex + indexOf, "");
        }
        return sb.toString();
    }

    private int indexOfRegex(String str, String str2) {
        Matcher matcher = Pattern.compile(str).matcher(str2);
        if (matcher.find()) {
            return matcher.start();
        }
        return -1;
    }

    private String normalized(String str) {
        String replaceAll = str.trim().replaceAll(TOKEN_NEWLINE, SPACE).replaceAll(TOKEN_COMMA, " , ").replaceAll("\\(", " ( ").replaceAll("\\)", " ) ");
        return replaceAll.endsWith(";") ? replaceAll.substring(0, replaceAll.length() - 1) : replaceAll;
    }

    private String clean(String str) {
        int indexOf;
        int indexOf2 = str.indexOf(TOKEN_ORACLE_HINT_START);
        if (indexOf2 == -1 || (indexOf = str.indexOf(TOKEN_ORACLE_HINT_END)) == -1) {
            return str;
        }
        return str.substring(0, indexOf2).trim() + SPACE + str.substring(indexOf + 2, str.length()).trim();
    }

    private boolean isOracleSpecialDelete(String str, String[] strArr, int i) {
        int i2 = i + 1;
        if (!"delete".equals(str) || !moreTokens(strArr, i2)) {
            return false;
        }
        String str2 = strArr[i2];
        return ("from".equals(str2) || "*".equals(str2)) ? false : true;
    }

    private void handleSpecialOracleSpecialDelete(String str, String[] strArr, int i) {
        considerInclusion(strArr[i + 1]);
    }

    private boolean isCreateIndex(String str, String[] strArr, int i) {
        int i2 = i + 1;
        return TOKEN_CREATE.equals(str.toLowerCase()) && hasIthToken(strArr, i2, 3) && TOKEN_INDEX.equals(strArr[i2].toLowerCase());
    }

    private void handleCreateIndex(String str, String[] strArr, int i) {
        considerInclusion(strArr[i + 4]);
    }

    private boolean hasIthToken(String[] strArr, int i, int i2) {
        return moreTokens(strArr, i) && strArr.length > i + i2;
    }

    private boolean shouldProcess(String str) {
        return concerned.contains(str.toLowerCase());
    }

    private boolean isFromToken(String str) {
        return "from".equals(str.toLowerCase());
    }

    private void processFromToken(String[] strArr, int i) {
        int i2;
        String str;
        int i3 = i + 1;
        String str2 = strArr[i];
        considerInclusion(str2);
        if (moreTokens(strArr, i3)) {
            i2 = i3 + 1;
            str = strArr[i3];
        } else {
            i2 = i3;
            str = null;
        }
        if (shouldProcessMultipleTables(str)) {
            processNonAliasedMultiTables(strArr, i2, str);
        } else {
            processAliasedMultiTables(strArr, i2, str2);
        }
    }

    private void processNonAliasedMultiTables(String[] strArr, int i, String str) {
        while (str.equals(TOKEN_COMMA)) {
            int i2 = i + 1;
            considerInclusion(strArr[i]);
            if (!moreTokens(strArr, i2)) {
                return;
            }
            i = i2 + 1;
            str = strArr[i2];
        }
    }

    private void processAliasedMultiTables(String[] strArr, int i, String str) {
        int i2;
        String str2;
        String str3;
        int i3;
        if (moreTokens(strArr, i)) {
            i2 = i + 1;
            str2 = strArr[i];
        } else {
            i2 = i;
            str2 = null;
        }
        if (shouldProcessMultipleTables(str2)) {
            while (moreTokens(strArr, i2) && str2.equals(TOKEN_COMMA)) {
                if (moreTokens(strArr, i2)) {
                    i3 = i2 + 1;
                    str3 = strArr[i2];
                } else {
                    int i4 = i2;
                    str3 = str;
                    i3 = i4;
                }
                if (moreTokens(strArr, i3)) {
                    i3++;
                }
                if (moreTokens(strArr, i3)) {
                    int i5 = i3 + 1;
                    String str4 = strArr[i3];
                    i3 = i5;
                    str2 = str4;
                }
                considerInclusion(str3);
                String str5 = str3;
                i2 = i3;
                str = str5;
            }
        }
    }

    private boolean shouldProcessMultipleTables(String str) {
        return str != null && str.equals(TOKEN_COMMA);
    }

    private boolean moreTokens(String[] strArr, int i) {
        return i < strArr.length;
    }

    private void considerInclusion(String str) {
        if (ignored.contains(str.toLowerCase()) || this.tables.containsKey(str.toLowerCase())) {
            return;
        }
        this.tables.put(str.toLowerCase(), str);
    }

    public Collection<String> tables() {
        return new HashSet(this.tables.values());
    }
}
