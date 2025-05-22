package org.mozilla.javascript.tools.idswitch;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.http.protocol.HTTP;
import org.mozilla.javascript.EvaluatorException;
import org.mozilla.javascript.tools.ToolErrorReporter;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class Main {
    private static final int GENERATED_TAG = 2;
    private static final String GENERATED_TAG_STR = "generated";
    private static final int NORMAL_LINE = 0;
    private static final int STRING_TAG = 3;
    private static final String STRING_TAG_STR = "string";
    private static final int SWITCH_TAG = 1;
    private static final String SWITCH_TAG_STR = "string_id_map";

    /* renamed from: P */
    private CodePrinter f10270P;

    /* renamed from: R */
    private ToolErrorReporter f10271R;
    private final List<IdValuePair> all_pairs = new ArrayList();
    private FileBody body;
    private String source_file;
    private int tag_definition_end;
    private int tag_value_end;
    private int tag_value_start;

    private static boolean is_value_type(int i) {
        return i == 3;
    }

    private static boolean is_white_space(int i) {
        return i == 32 || i == 9;
    }

    private static String tag_name(int i) {
        return i != -2 ? i != -1 ? i != 1 ? i != 2 ? "" : GENERATED_TAG_STR : SWITCH_TAG_STR : "/string_id_map" : "/generated";
    }

    void process_file(String str) throws IOException {
        InputStream fileInputStream;
        OutputStream fileOutputStream;
        this.source_file = str;
        this.body = new FileBody();
        if (str.equals("-")) {
            fileInputStream = System.in;
        } else {
            fileInputStream = new FileInputStream(str);
        }
        try {
            this.body.readData(new InputStreamReader(fileInputStream, HTTP.ASCII));
            fileInputStream.close();
            process_file();
            if (this.body.wasModified()) {
                if (str.equals("-")) {
                    fileOutputStream = System.out;
                } else {
                    fileOutputStream = new FileOutputStream(str);
                }
                try {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                    this.body.writeData(outputStreamWriter);
                    outputStreamWriter.flush();
                } finally {
                    fileOutputStream.close();
                }
            }
        } catch (Throwable th) {
            fileInputStream.close();
            throw th;
        }
    }

    private void process_file() {
        char[] buffer = this.body.getBuffer();
        this.body.startLineLoop();
        int i = 0;
        int i2 = -1;
        int i3 = -1;
        int i4 = -1;
        int i5 = -1;
        while (this.body.nextLine()) {
            int lineBegin = this.body.getLineBegin();
            int lineEnd = this.body.getLineEnd();
            int extract_line_tag_id = extract_line_tag_id(buffer, lineBegin, lineEnd);
            boolean z = true;
            if (i != 0) {
                if (i != 1) {
                    if (i == 2) {
                        if (extract_line_tag_id == 0) {
                            if (i2 < 0) {
                                z = false;
                                i2 = lineBegin;
                            }
                        } else if (extract_line_tag_id == -2) {
                            if (i2 < 0) {
                                i2 = lineBegin;
                            }
                            i3 = lineBegin;
                            i = 1;
                        }
                    }
                    z = false;
                } else {
                    if (extract_line_tag_id == 0) {
                        look_for_id_definitions(buffer, lineBegin, lineEnd, false);
                    } else if (extract_line_tag_id == 3) {
                        look_for_id_definitions(buffer, lineBegin, lineEnd, true);
                    } else if (extract_line_tag_id == 2) {
                        if (i2 < 0) {
                            i4 = this.tag_definition_end;
                            z = false;
                            i5 = lineEnd;
                            i = 2;
                        }
                    } else if (extract_line_tag_id == -1) {
                        if (i2 >= 0 && !this.all_pairs.isEmpty()) {
                            generate_java_code();
                            if (this.body.setReplacement(i2, i3, this.f10270P.toString())) {
                                this.body.setReplacement(i4, i5, get_time_stamp());
                            }
                        }
                        i = 0;
                        z = false;
                    }
                    z = false;
                }
            } else if (extract_line_tag_id == 1) {
                this.all_pairs.clear();
                i2 = -1;
                i = 1;
                z = false;
            } else {
                if (extract_line_tag_id == -1) {
                }
                z = false;
            }
            if (z) {
                throw this.f10271R.runtimeError(ToolErrorReporter.getMessage("msg.idswitch.bad_tag_order", tag_name(extract_line_tag_id)), this.source_file, this.body.getLineNumber(), null, 0);
            }
        }
        if (i == 0) {
            return;
        }
        throw this.f10271R.runtimeError(ToolErrorReporter.getMessage("msg.idswitch.file_end_in_switch", tag_name(i)), this.source_file, this.body.getLineNumber(), null, 0);
    }

    private String get_time_stamp() {
        return new SimpleDateFormat(" 'Last update:' yyyy-MM-dd HH:mm:ss z").format(new Date());
    }

    private void generate_java_code() {
        this.f10270P.clear();
        IdValuePair[] idValuePairArr = new IdValuePair[this.all_pairs.size()];
        this.all_pairs.toArray(idValuePairArr);
        SwitchGenerator switchGenerator = new SwitchGenerator();
        switchGenerator.char_tail_test_threshold = 2;
        switchGenerator.setReporter(this.f10271R);
        switchGenerator.setCodePrinter(this.f10270P);
        switchGenerator.generateSwitch(idValuePairArr, "0");
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0044, code lost:
    
        r7 = skip_white_space(r10, r5, r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0048, code lost:
    
        if (r7 == r12) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x004a, code lost:
    
        r8 = r10[r7];
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x004c, code lost:
    
        if (r8 == '=') goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x004e, code lost:
    
        if (r8 != '#') goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0050, code lost:
    
        r1 = get_tag_id(r10, r0, r5, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0054, code lost:
    
        if (r1 == 0) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0056, code lost:
    
        r11 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0057, code lost:
    
        if (r8 != '#') goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0059, code lost:
    
        if (r3 == false) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x005b, code lost:
    
        r1 = -r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0060, code lost:
    
        if (is_value_type(r1) == false) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0062, code lost:
    
        r11 = "msg.idswitch.no_end_usage";
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0064, code lost:
    
        r9.tag_definition_end = r7 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x007b, code lost:
    
        if (r11 != null) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0096, code lost:
    
        throw r9.f10271R.runtimeError(org.mozilla.javascript.tools.ToolErrorReporter.getMessage(r11, tag_name(r1)), r9.source_file, r9.body.getLineNumber(), null, 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0068, code lost:
    
        if (r3 == false) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x006a, code lost:
    
        r11 = "msg.idswitch.no_end_with_value";
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0075, code lost:
    
        r1 = extract_tag_value(r10, r7 + 1, r12, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0071, code lost:
    
        if (is_value_type(r1) != false) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0073, code lost:
    
        r11 = "msg.idswitch.no_value_allowed";
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private int extract_line_tag_id(char[] cArr, int i, int i2) {
        boolean z;
        char c;
        int skip_white_space = skip_white_space(cArr, i, i2);
        int look_for_slash_slash = look_for_slash_slash(cArr, skip_white_space, i2);
        int i3 = 0;
        if (look_for_slash_slash != i2) {
            boolean z2 = skip_white_space + 2 == look_for_slash_slash;
            int skip_white_space2 = skip_white_space(cArr, look_for_slash_slash, i2);
            if (skip_white_space2 != i2 && cArr[skip_white_space2] == '#') {
                int i4 = skip_white_space2 + 1;
                if (i4 == i2 || cArr[i4] != '/') {
                    z = false;
                } else {
                    i4++;
                    z = true;
                }
                int i5 = i4;
                while (i5 != i2 && (c = cArr[i5]) != '#' && c != '=' && !is_white_space(c)) {
                    i5++;
                }
            }
        }
        return i3;
    }

    private int look_for_slash_slash(char[] cArr, int i, int i2) {
        while (i + 2 <= i2) {
            int i3 = i + 1;
            if (cArr[i] == '/') {
                i = i3 + 1;
                if (cArr[i3] == '/') {
                    return i;
                }
            } else {
                i = i3;
            }
        }
        return i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private int extract_tag_value(char[] cArr, int i, int i2, int i3) {
        int i4;
        int skip_white_space = skip_white_space(cArr, i, i2);
        boolean z = true;
        if (skip_white_space != i2) {
            int i5 = skip_white_space;
            while (true) {
                if (i5 == i2) {
                    i4 = skip_white_space;
                    break;
                }
                char c = cArr[i5];
                if (is_white_space(c)) {
                    int skip_white_space2 = skip_white_space(cArr, i5 + 1, i2);
                    if (skip_white_space2 != i2 && cArr[skip_white_space2] == '#') {
                        i4 = i5;
                        i5 = skip_white_space2;
                        break;
                    }
                    i5 = skip_white_space2 + 1;
                } else {
                    if (c == '#') {
                        i4 = i5;
                        break;
                    }
                    i5++;
                }
                if (z) {
                    return i3;
                }
                return 0;
            }
            if (i5 != i2) {
                this.tag_value_start = skip_white_space;
                this.tag_value_end = i4;
                this.tag_definition_end = i5 + 1;
                if (z) {
                }
            }
        }
        z = false;
        if (z) {
        }
    }

    private int get_tag_id(char[] cArr, int i, int i2, boolean z) {
        if (z) {
            if (equals(SWITCH_TAG_STR, cArr, i, i2)) {
                return 1;
            }
            if (equals(GENERATED_TAG_STR, cArr, i, i2)) {
                return 2;
            }
        }
        return equals("string", cArr, i, i2) ? 3 : 0;
    }

    private void look_for_id_definitions(char[] cArr, int i, int i2, boolean z) {
        int skip_name_char;
        int skip_white_space;
        int i3;
        int i4;
        int skip_white_space2 = skip_white_space(cArr, i, i2);
        int skip_matched_prefix = skip_matched_prefix("Id_", cArr, skip_white_space2, i2);
        if (skip_matched_prefix < 0 || skip_matched_prefix == (skip_name_char = skip_name_char(cArr, skip_matched_prefix, i2)) || (skip_white_space = skip_white_space(cArr, skip_name_char, i2)) == i2 || cArr[skip_white_space] != '=') {
            return;
        }
        if (z) {
            i3 = this.tag_value_start;
            i4 = this.tag_value_end;
        } else {
            i3 = skip_matched_prefix;
            i4 = skip_name_char;
        }
        add_id(cArr, skip_white_space2, skip_name_char, i3, i4);
    }

    private void add_id(char[] cArr, int i, int i2, int i3, int i4) {
        IdValuePair idValuePair = new IdValuePair(new String(cArr, i3, i4 - i3), new String(cArr, i, i2 - i));
        idValuePair.setLineNumber(this.body.getLineNumber());
        this.all_pairs.add(idValuePair);
    }

    private static int skip_white_space(char[] cArr, int i, int i2) {
        while (i != i2 && is_white_space(cArr[i])) {
            i++;
        }
        return i;
    }

    private static int skip_matched_prefix(String str, char[] cArr, int i, int i2) {
        int length = str.length();
        if (length > i2 - i) {
            return -1;
        }
        int i3 = 0;
        while (i3 != length) {
            if (str.charAt(i3) != cArr[i]) {
                return -1;
            }
            i3++;
            i++;
        }
        return i;
    }

    private static boolean equals(String str, char[] cArr, int i, int i2) {
        if (str.length() != i2 - i) {
            return false;
        }
        int i3 = 0;
        while (i != i2) {
            if (cArr[i] != str.charAt(i3)) {
                return false;
            }
            i++;
            i3++;
        }
        return true;
    }

    private static int skip_name_char(char[] cArr, int i, int i2) {
        while (i != i2) {
            char c = cArr[i];
            if (('a' > c || c > 'z') && (('A' > c || c > 'Z') && (('0' > c || c > '9') && c != '_'))) {
                break;
            }
            i++;
        }
        return i;
    }

    public static void main(String[] strArr) {
        System.exit(new Main().exec(strArr));
    }

    private int exec(String[] strArr) {
        this.f10271R = new ToolErrorReporter(true, System.err);
        int process_options = process_options(strArr);
        if (process_options == 0) {
            option_error(ToolErrorReporter.getMessage("msg.idswitch.no_file_argument"));
            return -1;
        }
        if (process_options > 1) {
            option_error(ToolErrorReporter.getMessage("msg.idswitch.too_many_arguments"));
            return -1;
        }
        CodePrinter codePrinter = new CodePrinter();
        this.f10270P = codePrinter;
        codePrinter.setIndentStep(4);
        this.f10270P.setIndentTabSize(0);
        try {
            process_file(strArr[0]);
            return 0;
        } catch (IOException e) {
            print_error(ToolErrorReporter.getMessage("msg.idswitch.io_error", e.toString()));
            return -1;
        } catch (EvaluatorException unused) {
            return -1;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x0066, code lost:
    
        r5 = 1;
     */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0078  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private int process_options(String[] strArr) {
        int i;
        int length = strArr.length;
        int i2 = 0;
        int i3 = 0;
        boolean z = false;
        boolean z2 = false;
        loop0: while (true) {
            i = -1;
            if (i3 == length) {
                break;
            }
            String str = strArr[i3];
            int length2 = str.length();
            if (length2 >= 2 && str.charAt(0) == '-') {
                if (str.charAt(1) != '-') {
                    int i4 = 1;
                    while (i4 != length2) {
                        char charAt = str.charAt(i4);
                        if (charAt != 'h') {
                            option_error(ToolErrorReporter.getMessage("msg.idswitch.bad_option_char", String.valueOf(charAt)));
                            break loop0;
                        }
                        i4++;
                        z = true;
                    }
                    strArr[i3] = null;
                } else {
                    if (length2 == 2) {
                        strArr[i3] = null;
                        break;
                    }
                    if (!str.equals("--help")) {
                        if (!str.equals("--version")) {
                            option_error(ToolErrorReporter.getMessage("msg.idswitch.bad_option", str));
                            break;
                        }
                        z2 = true;
                    } else {
                        z = true;
                    }
                    strArr[i3] = null;
                }
            }
            i3++;
        }
        if (i == 1) {
            if (z) {
                show_usage();
                i = 0;
            }
            if (z2) {
                show_version();
                if (i2 != 1) {
                    System.exit(i2);
                }
                return remove_nulls(strArr);
            }
        }
        i2 = i;
        if (i2 != 1) {
        }
        return remove_nulls(strArr);
    }

    private void show_usage() {
        System.out.println(ToolErrorReporter.getMessage("msg.idswitch.usage"));
        System.out.println();
    }

    private void show_version() {
        System.out.println(ToolErrorReporter.getMessage("msg.idswitch.version"));
    }

    private void option_error(String str) {
        print_error(ToolErrorReporter.getMessage("msg.idswitch.bad_invocation", str));
    }

    private void print_error(String str) {
        System.err.println(str);
    }

    private int remove_nulls(String[] strArr) {
        int length = strArr.length;
        int i = 0;
        while (i != length && strArr[i] != null) {
            i++;
        }
        if (i != length) {
            for (int i2 = i + 1; i2 != length; i2++) {
                String str = strArr[i2];
                if (str != null) {
                    strArr[i] = str;
                    i++;
                }
            }
        }
        return i;
    }
}
