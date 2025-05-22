package org.mozilla.javascript.tools.shell;

import androidx.core.app.NotificationCompat;
import com.aliyun.alink.linksdk.tmp.device.panel.data.InvokeServiceData;
import com.pudutech.mirsdk.compat.topo.MapElement;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.IOUtils;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextAction;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.ImporterTopLevel;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.RhinoException;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Synchronizer;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.Wrapper;
import org.mozilla.javascript.commonjs.module.Require;
import org.mozilla.javascript.commonjs.module.RequireBuilder;
import org.mozilla.javascript.commonjs.module.provider.SoftCachingModuleScriptProvider;
import org.mozilla.javascript.commonjs.module.provider.UrlModuleSourceProvider;
import org.mozilla.javascript.serialize.ScriptableInputStream;
import org.mozilla.javascript.serialize.ScriptableOutputStream;
import org.mozilla.javascript.tools.ToolErrorReporter;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class Global extends ImporterTopLevel {
    static final long serialVersionUID = 4029130780977538005L;
    boolean attemptedJLineLoad;
    private ShellConsole console;
    private HashMap<String, String> doctestCanonicalizations;
    private PrintStream errStream;
    NativeArray history;
    private InputStream inStream;
    boolean initialized;
    private PrintStream outStream;
    private QuitAction quitAction;
    private boolean sealedStdLib = false;
    private String[] prompts = {"js> ", "  > "};

    public Global() {
    }

    public Global(Context context) {
        init(context);
    }

    public boolean isInitialized() {
        return this.initialized;
    }

    public void initQuitAction(QuitAction quitAction) {
        if (quitAction == null) {
            throw new IllegalArgumentException("quitAction is null");
        }
        if (this.quitAction != null) {
            throw new IllegalArgumentException("The method is once-call.");
        }
        this.quitAction = quitAction;
    }

    public void init(ContextFactory contextFactory) {
        contextFactory.call(new ContextAction() { // from class: org.mozilla.javascript.tools.shell.Global.1
            @Override // org.mozilla.javascript.ContextAction
            public Object run(Context context) {
                Global.this.init(context);
                return null;
            }
        });
    }

    public void init(Context context) {
        initStandardObjects(context, this.sealedStdLib);
        defineFunctionProperties(new String[]{"defineClass", "deserialize", "doctest", "gc", "help", "load", "loadClass", "print", "quit", "readline", "readFile", "readUrl", "runCommand", "seal", "serialize", "spawn", InvokeServiceData.CALL_TYPE_SYNC, "toint32", "version", "write"}, Global.class, 2);
        Environment.defineClass(this);
        defineProperty("environment", new Environment(this), 2);
        NativeArray nativeArray = (NativeArray) context.newArray(this, 0);
        this.history = nativeArray;
        defineProperty("history", nativeArray, 2);
        this.initialized = true;
    }

    public Require installRequire(Context context, List<String> list, boolean z) {
        RequireBuilder requireBuilder = new RequireBuilder();
        requireBuilder.setSandboxed(z);
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (String str : list) {
                try {
                    URI uri = new URI(str);
                    if (!uri.isAbsolute()) {
                        uri = new File(str).toURI().resolve("");
                    }
                    if (!uri.toString().endsWith("/")) {
                        uri = new URI(uri + "/");
                    }
                    arrayList.add(uri);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        requireBuilder.setModuleScriptProvider(new SoftCachingModuleScriptProvider(new UrlModuleSourceProvider(arrayList, null)));
        Require createRequire = requireBuilder.createRequire(context, this);
        createRequire.install(this);
        return createRequire;
    }

    public static void help(Context context, Scriptable scriptable, Object[] objArr, Function function) {
        getInstance(function).getOut().println(ToolErrorReporter.getMessage("msg.help"));
    }

    /* renamed from: gc */
    public static void m4192gc(Context context, Scriptable scriptable, Object[] objArr, Function function) {
        System.gc();
    }

    public static Object print(Context context, Scriptable scriptable, Object[] objArr, Function function) {
        return doPrint(objArr, function, true);
    }

    public static Object write(Context context, Scriptable scriptable, Object[] objArr, Function function) {
        return doPrint(objArr, function, false);
    }

    private static Object doPrint(Object[] objArr, Function function, boolean z) {
        PrintStream out = getInstance(function).getOut();
        for (int i = 0; i < objArr.length; i++) {
            if (i > 0) {
                out.print(" ");
            }
            out.print(Context.toString(objArr[i]));
        }
        if (z) {
            out.println();
        }
        return Context.getUndefinedValue();
    }

    public static void quit(Context context, Scriptable scriptable, Object[] objArr, Function function) {
        Global global = getInstance(function);
        if (global.quitAction != null) {
            global.quitAction.quit(context, objArr.length != 0 ? ScriptRuntime.toInt32(objArr[0]) : 0);
        }
    }

    public static double version(Context context, Scriptable scriptable, Object[] objArr, Function function) {
        double languageVersion = context.getLanguageVersion();
        if (objArr.length > 0) {
            context.setLanguageVersion((int) Context.toNumber(objArr[0]));
        }
        return languageVersion;
    }

    public static void load(Context context, Scriptable scriptable, Object[] objArr, Function function) {
        for (Object obj : objArr) {
            String context2 = Context.toString(obj);
            try {
                Main.processFile(context, scriptable, context2);
            } catch (IOException e) {
                throw Context.reportRuntimeError(ToolErrorReporter.getMessage("msg.couldnt.read.source", context2, e.getMessage()));
            } catch (VirtualMachineError e2) {
                e2.printStackTrace();
                throw Context.reportRuntimeError(ToolErrorReporter.getMessage("msg.uncaughtJSException", e2.toString()));
            }
        }
    }

    public static void defineClass(Context context, Scriptable scriptable, Object[] objArr, Function function) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<?> cls = getClass(objArr);
        if (!Scriptable.class.isAssignableFrom(cls)) {
            throw reportRuntimeError("msg.must.implement.Scriptable");
        }
        ScriptableObject.defineClass(scriptable, cls);
    }

    public static void loadClass(Context context, Scriptable scriptable, Object[] objArr, Function function) throws IllegalAccessException, InstantiationException {
        Class<?> cls = getClass(objArr);
        if (!Script.class.isAssignableFrom(cls)) {
            throw reportRuntimeError("msg.must.implement.Script");
        }
        ((Script) cls.newInstance()).exec(context, scriptable);
    }

    private static Class<?> getClass(Object[] objArr) {
        if (objArr.length == 0) {
            throw reportRuntimeError("msg.expected.string.arg");
        }
        Object obj = objArr[0];
        if (obj instanceof Wrapper) {
            Object unwrap = ((Wrapper) obj).unwrap();
            if (unwrap instanceof Class) {
                return (Class) unwrap;
            }
        }
        String context = Context.toString(objArr[0]);
        try {
            return Class.forName(context);
        } catch (ClassNotFoundException unused) {
            throw reportRuntimeError("msg.class.not.found", context);
        }
    }

    public static void serialize(Context context, Scriptable scriptable, Object[] objArr, Function function) throws IOException {
        if (objArr.length < 2) {
            throw Context.reportRuntimeError("Expected an object to serialize and a filename to write the serialization to");
        }
        Object obj = objArr[0];
        ScriptableOutputStream scriptableOutputStream = new ScriptableOutputStream(new FileOutputStream(Context.toString(objArr[1])), ScriptableObject.getTopLevelScope(scriptable));
        scriptableOutputStream.writeObject(obj);
        scriptableOutputStream.close();
    }

    public static Object deserialize(Context context, Scriptable scriptable, Object[] objArr, Function function) throws IOException, ClassNotFoundException {
        if (objArr.length < 1) {
            throw Context.reportRuntimeError("Expected a filename to read the serialization from");
        }
        FileInputStream fileInputStream = new FileInputStream(Context.toString(objArr[0]));
        Scriptable topLevelScope = ScriptableObject.getTopLevelScope(scriptable);
        ScriptableInputStream scriptableInputStream = new ScriptableInputStream(fileInputStream, topLevelScope);
        Object readObject = scriptableInputStream.readObject();
        scriptableInputStream.close();
        return Context.toObject(readObject, topLevelScope);
    }

    public String[] getPrompts(Context context) {
        if (ScriptableObject.hasProperty(this, "prompts")) {
            Object property = ScriptableObject.getProperty(this, "prompts");
            if (property instanceof Scriptable) {
                Scriptable scriptable = (Scriptable) property;
                if (ScriptableObject.hasProperty(scriptable, 0) && ScriptableObject.hasProperty(scriptable, 1)) {
                    Object property2 = ScriptableObject.getProperty(scriptable, 0);
                    if (property2 instanceof Function) {
                        property2 = ((Function) property2).call(context, this, scriptable, new Object[0]);
                    }
                    this.prompts[0] = Context.toString(property2);
                    Object property3 = ScriptableObject.getProperty(scriptable, 1);
                    if (property3 instanceof Function) {
                        property3 = ((Function) property3).call(context, this, scriptable, new Object[0]);
                    }
                    this.prompts[1] = Context.toString(property3);
                }
            }
        }
        return this.prompts;
    }

    public static Object doctest(Context context, Scriptable scriptable, Object[] objArr, Function function) {
        if (objArr.length == 0) {
            return Boolean.FALSE;
        }
        String context2 = Context.toString(objArr[0]);
        Global global = getInstance(function);
        return new Integer(global.runDoctest(context, global, context2, null, 0));
    }

    /* JADX WARN: Unreachable blocks removed: 2, instructions: 10 */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 4 */
    public int runDoctest(Context context, Scriptable scriptable, String str, String str2, int i) {
        PrintStream printStream;
        StringBuilder sb;
        this.doctestCanonicalizations = new HashMap<>();
        String[] split = str.split("\r\n?|\n");
        String trim = this.prompts[0].trim();
        String trim2 = this.prompts[1].trim();
        int i2 = 0;
        while (i2 < split.length && !split[i2].trim().startsWith(trim)) {
            i2++;
        }
        int i3 = 0;
        while (i2 < split.length) {
            String substring = split[i2].trim().substring(trim.length());
            int i4 = i2 + 1;
            String str3 = substring + "\n";
            while (i4 < split.length && split[i4].trim().startsWith(trim2)) {
                str3 = (str3 + split[i4].trim().substring(trim2.length())) + "\n";
                i4++;
            }
            int i5 = i4;
            String str4 = "";
            while (i5 < split.length && !split[i5].trim().startsWith(trim)) {
                str4 = str4 + split[i5] + "\n";
                i5++;
            }
            PrintStream out = getOut();
            PrintStream err = getErr();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            setOut(new PrintStream(byteArrayOutputStream));
            setErr(new PrintStream(byteArrayOutputStream2));
            ErrorReporter errorReporter = context.getErrorReporter();
            String str5 = str4;
            context.setErrorReporter(new ToolErrorReporter(false, getErr()));
            int i6 = i3 + 1;
            int i7 = i5;
            String[] strArr = split;
            String str6 = trim;
            String str7 = str3;
            try {
                Object evaluateString = context.evaluateString(scriptable, str3, "doctest input", 1, null);
                String context2 = (evaluateString == Context.getUndefinedValue() || ((evaluateString instanceof Function) && str7.trim().startsWith("function"))) ? "" : Context.toString(evaluateString);
                setOut(out);
                setErr(err);
                context.setErrorReporter(errorReporter);
                sb = new StringBuilder();
                sb.append(context2);
            } catch (RhinoException e) {
                printStream = out;
                try {
                    ToolErrorReporter.reportException(context.getErrorReporter(), e);
                    setOut(printStream);
                    setErr(err);
                    context.setErrorReporter(errorReporter);
                    sb = new StringBuilder();
                    sb.append("");
                } catch (Throwable th) {
                    th = th;
                    setOut(printStream);
                    setErr(err);
                    context.setErrorReporter(errorReporter);
                    byteArrayOutputStream2.toString();
                    byteArrayOutputStream.toString();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                printStream = out;
                setOut(printStream);
                setErr(err);
                context.setErrorReporter(errorReporter);
                byteArrayOutputStream2.toString();
                byteArrayOutputStream.toString();
                throw th;
            }
            sb.append(byteArrayOutputStream2.toString());
            sb.append(byteArrayOutputStream.toString());
            String sb2 = sb.toString();
            if (!doctestOutputMatches(str5, sb2)) {
                String str8 = "doctest failure running:\n" + str7 + "expected: " + str5 + "actual: " + sb2 + "\n";
                if (str2 != null) {
                    throw Context.reportRuntimeError(str8, str2, (i + i7) - 1, null, 0);
                }
                throw Context.reportRuntimeError(str8);
            }
            trim = str6;
            i2 = i7;
            split = strArr;
            i3 = i6;
        }
        return i3;
    }

    private boolean doctestOutputMatches(String str, String str2) {
        String trim = str.trim();
        String replace = str2.trim().replace(IOUtils.LINE_SEPARATOR_WINDOWS, "\n");
        if (trim.equals(replace)) {
            return true;
        }
        for (Map.Entry<String, String> entry : this.doctestCanonicalizations.entrySet()) {
            trim = trim.replace(entry.getKey(), entry.getValue());
        }
        if (trim.equals(replace)) {
            return true;
        }
        Pattern compile = Pattern.compile("@[0-9a-fA-F]+");
        Matcher matcher = compile.matcher(trim);
        Matcher matcher2 = compile.matcher(replace);
        while (matcher.find() && matcher2.find() && matcher2.start() == matcher.start()) {
            int start = matcher.start();
            if (!trim.substring(0, start).equals(replace.substring(0, start))) {
                return false;
            }
            String group = matcher.group();
            String group2 = matcher2.group();
            String str3 = this.doctestCanonicalizations.get(group);
            if (str3 == null) {
                this.doctestCanonicalizations.put(group, group2);
                trim = trim.replace(group, group2);
            } else if (!group2.equals(str3)) {
                return false;
            }
            if (trim.equals(replace)) {
                return true;
            }
        }
        return false;
    }

    public static Object spawn(Context context, Scriptable scriptable, Object[] objArr, Function function) {
        Runner runner;
        Scriptable parentScope = function.getParentScope();
        if (objArr.length != 0 && (objArr[0] instanceof Function)) {
            Object[] objArr2 = null;
            if (objArr.length > 1 && (objArr[1] instanceof Scriptable)) {
                objArr2 = context.getElements((Scriptable) objArr[1]);
            }
            if (objArr2 == null) {
                objArr2 = ScriptRuntime.emptyArgs;
            }
            runner = new Runner(parentScope, (Function) objArr[0], objArr2);
        } else if (objArr.length != 0 && (objArr[0] instanceof Script)) {
            runner = new Runner(parentScope, (Script) objArr[0]);
        } else {
            throw reportRuntimeError("msg.spawn.args");
        }
        runner.factory = context.getFactory();
        Thread thread = new Thread(runner);
        thread.start();
        return thread;
    }

    public static Object sync(Context context, Scriptable scriptable, Object[] objArr, Function function) {
        if (objArr.length >= 1 && objArr.length <= 2 && (objArr[0] instanceof Function)) {
            Object obj = null;
            if (objArr.length == 2 && objArr[1] != Undefined.instance) {
                obj = objArr[1];
            }
            return new Synchronizer((Function) objArr[0], obj);
        }
        throw reportRuntimeError("msg.sync.args");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00df  */
    /* JADX WARN: Type inference failed for: r11v7, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r11v8, types: [java.io.ByteArrayOutputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Object runCommand(Context context, Scriptable scriptable, Object[] objArr, Function function) throws IOException {
        Object[] objArr2;
        Object obj;
        Object obj2;
        PrintStream printStream;
        String[] strArr;
        File file;
        InputStream inputStream;
        Scriptable scriptable2;
        OutputStream outputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2;
        String[] strArr2;
        PrintStream printStream2;
        ByteArrayOutputStream byteArrayOutputStream3;
        PrintStream printStream3;
        Object property;
        OutputStream outputStream2;
        OutputStream outputStream3;
        Object property2;
        Object property3;
        String str;
        int length = objArr.length;
        if (length == 0 || (length == 1 && (objArr[0] instanceof Scriptable))) {
            throw reportRuntimeError("msg.runCommand.bad.args");
        }
        int i = length - 1;
        if (objArr[i] instanceof Scriptable) {
            Scriptable scriptable3 = (Scriptable) objArr[i];
            length--;
            Object property4 = ScriptableObject.getProperty(scriptable3, "env");
            if (property4 == Scriptable.NOT_FOUND) {
                strArr2 = null;
            } else if (property4 == null) {
                strArr2 = new String[0];
            } else {
                if (!(property4 instanceof Scriptable)) {
                    throw reportRuntimeError("msg.runCommand.bad.env");
                }
                Scriptable scriptable4 = (Scriptable) property4;
                Object[] propertyIds = ScriptableObject.getPropertyIds(scriptable4);
                String[] strArr3 = new String[propertyIds.length];
                for (int i2 = 0; i2 != propertyIds.length; i2++) {
                    Object obj3 = propertyIds[i2];
                    if (obj3 instanceof String) {
                        str = (String) obj3;
                        property3 = ScriptableObject.getProperty(scriptable4, str);
                    } else {
                        int intValue = ((Number) obj3).intValue();
                        String num = Integer.toString(intValue);
                        property3 = ScriptableObject.getProperty(scriptable4, intValue);
                        str = num;
                    }
                    if (property3 == ScriptableObject.NOT_FOUND) {
                        property3 = Undefined.instance;
                    }
                    strArr3[i2] = str + '=' + ScriptRuntime.toString(property3);
                }
                strArr2 = strArr3;
            }
            Object property5 = ScriptableObject.getProperty(scriptable3, MapElement.Key.DIR);
            File file2 = property5 != Scriptable.NOT_FOUND ? new File(ScriptRuntime.toString(property5)) : null;
            Object property6 = ScriptableObject.getProperty(scriptable3, "input");
            InputStream inputStream2 = property6 != Scriptable.NOT_FOUND ? toInputStream(property6) : null;
            Object property7 = ScriptableObject.getProperty(scriptable3, "output");
            if (property7 != Scriptable.NOT_FOUND) {
                ?? outputStream4 = toOutputStream(property7);
                printStream2 = outputStream4;
                if (outputStream4 == 0) {
                    ?? byteArrayOutputStream4 = new ByteArrayOutputStream();
                    byteArrayOutputStream3 = byteArrayOutputStream4;
                    printStream3 = byteArrayOutputStream4;
                    property = ScriptableObject.getProperty(scriptable3, NotificationCompat.CATEGORY_ERROR);
                    if (property == Scriptable.NOT_FOUND) {
                        OutputStream outputStream5 = toOutputStream(property);
                        outputStream2 = outputStream5;
                        if (outputStream5 == null) {
                            ByteArrayOutputStream byteArrayOutputStream5 = new ByteArrayOutputStream();
                            byteArrayOutputStream = byteArrayOutputStream5;
                            outputStream3 = byteArrayOutputStream5;
                            property2 = ScriptableObject.getProperty(scriptable3, "args");
                            if (property2 != Scriptable.NOT_FOUND) {
                                objArr2 = context.getElements(Context.toObject(property2, getTopLevelScope(scriptable)));
                                printStream = printStream3;
                                byteArrayOutputStream2 = byteArrayOutputStream3;
                            } else {
                                printStream = printStream3;
                                byteArrayOutputStream2 = byteArrayOutputStream3;
                                objArr2 = null;
                            }
                            inputStream = inputStream2;
                            scriptable2 = scriptable3;
                            obj = property7;
                            file = file2;
                            strArr = strArr2;
                            obj2 = property;
                            outputStream = outputStream3;
                        }
                    } else {
                        outputStream2 = null;
                    }
                    byteArrayOutputStream = null;
                    outputStream3 = outputStream2;
                    property2 = ScriptableObject.getProperty(scriptable3, "args");
                    if (property2 != Scriptable.NOT_FOUND) {
                    }
                    inputStream = inputStream2;
                    scriptable2 = scriptable3;
                    obj = property7;
                    file = file2;
                    strArr = strArr2;
                    obj2 = property;
                    outputStream = outputStream3;
                }
            } else {
                printStream2 = null;
            }
            byteArrayOutputStream3 = null;
            printStream3 = printStream2;
            property = ScriptableObject.getProperty(scriptable3, NotificationCompat.CATEGORY_ERROR);
            if (property == Scriptable.NOT_FOUND) {
            }
            byteArrayOutputStream = null;
            outputStream3 = outputStream2;
            property2 = ScriptableObject.getProperty(scriptable3, "args");
            if (property2 != Scriptable.NOT_FOUND) {
            }
            inputStream = inputStream2;
            scriptable2 = scriptable3;
            obj = property7;
            file = file2;
            strArr = strArr2;
            obj2 = property;
            outputStream = outputStream3;
        } else {
            objArr2 = null;
            obj = null;
            obj2 = null;
            printStream = null;
            strArr = null;
            file = null;
            inputStream = null;
            scriptable2 = null;
            outputStream = null;
            byteArrayOutputStream = null;
            byteArrayOutputStream2 = null;
        }
        Global global = getInstance(function);
        if (printStream == null) {
            printStream = global != null ? global.getOut() : System.out;
        }
        PrintStream printStream4 = printStream;
        OutputStream outputStream6 = outputStream;
        if (outputStream == null) {
            outputStream6 = global != null ? global.getErr() : System.err;
        }
        String[] strArr4 = new String[objArr2 == null ? length : objArr2.length + length];
        for (int i3 = 0; i3 != length; i3++) {
            strArr4[i3] = ScriptRuntime.toString(objArr[i3]);
        }
        if (objArr2 != null) {
            for (int i4 = 0; i4 != objArr2.length; i4++) {
                strArr4[length + i4] = ScriptRuntime.toString(objArr2[i4]);
            }
        }
        Scriptable scriptable5 = scriptable2;
        int runProcess = runProcess(strArr4, strArr, file, inputStream, printStream4, outputStream6);
        if (byteArrayOutputStream2 != null) {
            ScriptableObject.putProperty(scriptable5, "output", ScriptRuntime.toString(obj) + byteArrayOutputStream2.toString());
        }
        if (byteArrayOutputStream != null) {
            ScriptableObject.putProperty(scriptable5, NotificationCompat.CATEGORY_ERROR, ScriptRuntime.toString(obj2) + byteArrayOutputStream.toString());
        }
        return new Integer(runProcess);
    }

    public static void seal(Context context, Scriptable scriptable, Object[] objArr, Function function) {
        for (int i = 0; i != objArr.length; i++) {
            Object obj = objArr[i];
            if (!(obj instanceof ScriptableObject) || obj == Undefined.instance) {
                if (!(obj instanceof Scriptable) || obj == Undefined.instance) {
                    throw reportRuntimeError("msg.shell.seal.not.object");
                }
                throw reportRuntimeError("msg.shell.seal.not.scriptable");
            }
        }
        for (int i2 = 0; i2 != objArr.length; i2++) {
            ((ScriptableObject) objArr[i2]).sealObject();
        }
    }

    public static Object readFile(Context context, Scriptable scriptable, Object[] objArr, Function function) throws IOException {
        if (objArr.length == 0) {
            throw reportRuntimeError("msg.shell.readFile.bad.args");
        }
        return readUrl(ScriptRuntime.toString(objArr[0]), objArr.length >= 2 ? ScriptRuntime.toString(objArr[1]) : null, true);
    }

    public static Object readUrl(Context context, Scriptable scriptable, Object[] objArr, Function function) throws IOException {
        if (objArr.length == 0) {
            throw reportRuntimeError("msg.shell.readUrl.bad.args");
        }
        return readUrl(ScriptRuntime.toString(objArr[0]), objArr.length >= 2 ? ScriptRuntime.toString(objArr[1]) : null, false);
    }

    public static Object toint32(Context context, Scriptable scriptable, Object[] objArr, Function function) {
        Object obj = objArr.length != 0 ? objArr[0] : Undefined.instance;
        return obj instanceof Integer ? obj : ScriptRuntime.wrapInt(ScriptRuntime.toInt32(obj));
    }

    private boolean loadJLine(Charset charset) {
        if (!this.attemptedJLineLoad) {
            this.attemptedJLineLoad = true;
            this.console = ShellConsole.getConsole(this, charset);
        }
        return this.console != null;
    }

    public ShellConsole getConsole(Charset charset) {
        if (!loadJLine(charset)) {
            this.console = ShellConsole.getConsole(getIn(), getErr(), charset);
        }
        return this.console;
    }

    public InputStream getIn() {
        if (this.inStream == null && !this.attemptedJLineLoad && loadJLine(Charset.defaultCharset())) {
            this.inStream = this.console.getIn();
        }
        InputStream inputStream = this.inStream;
        return inputStream == null ? System.in : inputStream;
    }

    public void setIn(InputStream inputStream) {
        this.inStream = inputStream;
    }

    public PrintStream getOut() {
        PrintStream printStream = this.outStream;
        return printStream == null ? System.out : printStream;
    }

    public void setOut(PrintStream printStream) {
        this.outStream = printStream;
    }

    public PrintStream getErr() {
        PrintStream printStream = this.errStream;
        return printStream == null ? System.err : printStream;
    }

    public void setErr(PrintStream printStream) {
        this.errStream = printStream;
    }

    public void setSealedStdLib(boolean z) {
        this.sealedStdLib = z;
    }

    private static Global getInstance(Function function) {
        Scriptable parentScope = function.getParentScope();
        if (!(parentScope instanceof Global)) {
            throw reportRuntimeError("msg.bad.shell.function.scope", String.valueOf(parentScope));
        }
        return (Global) parentScope;
    }

    private static int runProcess(String[] strArr, String[] strArr2, File file, InputStream inputStream, OutputStream outputStream, OutputStream outputStream2) throws IOException {
        Process exec;
        PipeThread pipeThread;
        PipeThread pipeThread2;
        PipeThread pipeThread3 = null;
        if (strArr2 == null) {
            exec = Runtime.getRuntime().exec(strArr, (String[]) null, file);
        } else {
            exec = Runtime.getRuntime().exec(strArr, strArr2, file);
        }
        try {
            if (inputStream != null) {
                pipeThread = new PipeThread(false, inputStream, exec.getOutputStream());
                pipeThread.start();
            } else {
                exec.getOutputStream().close();
                pipeThread = null;
            }
            if (outputStream != null) {
                pipeThread2 = new PipeThread(true, exec.getInputStream(), outputStream);
                pipeThread2.start();
            } else {
                exec.getInputStream().close();
                pipeThread2 = null;
            }
            if (outputStream2 != null) {
                pipeThread3 = new PipeThread(true, exec.getErrorStream(), outputStream2);
                pipeThread3.start();
            } else {
                exec.getErrorStream().close();
            }
            while (true) {
                try {
                    exec.waitFor();
                    if (pipeThread2 != null) {
                        pipeThread2.join();
                    }
                    if (pipeThread != null) {
                        pipeThread.join();
                    }
                    if (pipeThread3 == null) {
                        break;
                    }
                    pipeThread3.join();
                    break;
                } catch (InterruptedException unused) {
                }
            }
            return exec.exitValue();
        } finally {
            exec.destroy();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void pipe(boolean z, InputStream inputStream, OutputStream outputStream) throws IOException {
        int read;
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                if (!z) {
                    read = inputStream.read(bArr, 0, 4096);
                } else {
                    read = inputStream.read(bArr, 0, 4096);
                }
                if (read >= 0) {
                    if (z) {
                        outputStream.write(bArr, 0, read);
                        outputStream.flush();
                    } else {
                        try {
                            outputStream.write(bArr, 0, read);
                            outputStream.flush();
                        } catch (IOException unused) {
                        }
                    }
                }
                try {
                    break;
                } catch (IOException unused2) {
                    return;
                }
            }
        } finally {
            try {
                if (z) {
                    inputStream.close();
                } else {
                    outputStream.close();
                }
            } catch (IOException unused3) {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static InputStream toInputStream(Object obj) throws IOException {
        String str;
        InputStream inputStream = null;
        if (obj instanceof Wrapper) {
            Object unwrap = ((Wrapper) obj).unwrap();
            if (unwrap instanceof InputStream) {
                inputStream = (InputStream) unwrap;
                str = null;
            } else if (unwrap instanceof byte[]) {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream((byte[]) unwrap);
                str = null;
                inputStream = byteArrayInputStream;
            } else if (unwrap instanceof Reader) {
                str = readReader((Reader) unwrap);
            } else if (unwrap instanceof char[]) {
                str = new String((char[]) unwrap);
            }
            if (inputStream == null) {
                return inputStream;
            }
            if (str == null) {
                str = ScriptRuntime.toString(obj);
            }
            return new ByteArrayInputStream(str.getBytes());
        }
        str = null;
        if (inputStream == null) {
        }
    }

    private static OutputStream toOutputStream(Object obj) {
        if (obj instanceof Wrapper) {
            Object unwrap = ((Wrapper) obj).unwrap();
            if (unwrap instanceof OutputStream) {
                return (OutputStream) unwrap;
            }
        }
        return null;
    }

    private static String readUrl(String str, String str2, boolean z) throws IOException {
        int i;
        InputStream inputStream;
        InputStreamReader inputStreamReader;
        String contentType;
        InputStream inputStream2 = null;
        try {
            if (!z) {
                URLConnection openConnection = new URL(str).openConnection();
                inputStream = openConnection.getInputStream();
                i = openConnection.getContentLength();
                if (i <= 0) {
                    i = 1024;
                }
                if (str2 == null && (contentType = openConnection.getContentType()) != null) {
                    str2 = getCharCodingFromType(contentType);
                }
            } else {
                File file = new File(str);
                if (!file.exists()) {
                    throw new FileNotFoundException("File not found: " + str);
                }
                if (!file.canRead()) {
                    throw new IOException("Cannot read file: " + str);
                }
                long length = file.length();
                int i2 = (int) length;
                if (i2 != length) {
                    throw new IOException("Too big file size: " + length);
                }
                if (i2 == 0) {
                    return "";
                }
                FileInputStream fileInputStream = new FileInputStream(file);
                i = i2;
                inputStream = fileInputStream;
            }
            if (str2 == null) {
                inputStreamReader = new InputStreamReader(inputStream);
            } else {
                inputStreamReader = new InputStreamReader(inputStream, str2);
            }
            String readReader = readReader(inputStreamReader, i);
            if (inputStream != null) {
                inputStream.close();
            }
            return readReader;
        } catch (Throwable th) {
            if (0 != 0) {
                inputStream2.close();
            }
            throw th;
        }
    }

    public static Object readline(Context context, Scriptable scriptable, Object[] objArr, Function function) throws IOException {
        Global global = getInstance(function);
        if (objArr.length > 0) {
            return global.console.readLine(Context.toString(objArr[0]));
        }
        return global.console.readLine();
    }

    private static String getCharCodingFromType(String str) {
        int indexOf = str.indexOf(59);
        if (indexOf < 0) {
            return null;
        }
        int length = str.length();
        do {
            indexOf++;
            if (indexOf == length) {
                break;
            }
        } while (str.charAt(indexOf) <= ' ');
        if (!"charset".regionMatches(true, 0, str, indexOf, 7)) {
            return null;
        }
        int i = indexOf + 7;
        while (i != length && str.charAt(i) <= ' ') {
            i++;
        }
        if (i == length || str.charAt(i) != '=') {
            return null;
        }
        do {
            i++;
            if (i == length) {
                break;
            }
        } while (str.charAt(i) <= ' ');
        if (i == length) {
            return null;
        }
        while (str.charAt(length - 1) <= ' ') {
            length--;
        }
        return str.substring(i, length);
    }

    private static String readReader(Reader reader) throws IOException {
        return readReader(reader, 4096);
    }

    private static String readReader(Reader reader, int i) throws IOException {
        char[] cArr = new char[i];
        int i2 = 0;
        while (true) {
            int read = reader.read(cArr, i2, cArr.length - i2);
            if (read >= 0) {
                i2 += read;
                if (i2 == cArr.length) {
                    char[] cArr2 = new char[cArr.length * 2];
                    System.arraycopy(cArr, 0, cArr2, 0, i2);
                    cArr = cArr2;
                }
            } else {
                return new String(cArr, 0, i2);
            }
        }
    }

    static RuntimeException reportRuntimeError(String str) {
        return Context.reportRuntimeError(ToolErrorReporter.getMessage(str));
    }

    static RuntimeException reportRuntimeError(String str, String str2) {
        return Context.reportRuntimeError(ToolErrorReporter.getMessage(str, str2));
    }
}
