package org.apache.commons.compress.harmony.pack200;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.compress.harmony.pack200.NewAttribute;
import org.apache.commons.compress.java.util.jar.Pack200;
import org.objectweb.asm.Attribute;

/* loaded from: classes9.dex */
public class PackingOptions {
    public static final String ERROR = "error";
    public static final String KEEP = "keep";
    public static final String PASS = "pass";
    public static final String STRIP = "strip";
    private Map classAttributeActions;
    private Map codeAttributeActions;
    private Map fieldAttributeActions;
    private String logFile;
    private Map methodAttributeActions;
    private List passFiles;
    private Attribute[] unknownAttributeTypes;
    private boolean gzip = true;
    private boolean stripDebug = false;
    private boolean keepFileOrder = true;
    private long segmentLimit = 1000000;
    private int effort = 5;
    private String deflateHint = "keep";
    private String modificationTime = "keep";
    private String unknownAttributeAction = "pass";
    private boolean verbose = false;

    public boolean isGzip() {
        return this.gzip;
    }

    public void setGzip(boolean z) {
        this.gzip = z;
    }

    public boolean isStripDebug() {
        return this.stripDebug;
    }

    public void setStripDebug(boolean z) {
        this.stripDebug = z;
    }

    public boolean isKeepFileOrder() {
        return this.keepFileOrder;
    }

    public void setKeepFileOrder(boolean z) {
        this.keepFileOrder = z;
    }

    public long getSegmentLimit() {
        return this.segmentLimit;
    }

    public void setSegmentLimit(long j) {
        this.segmentLimit = j;
    }

    public int getEffort() {
        return this.effort;
    }

    public void setEffort(int i) {
        this.effort = i;
    }

    public String getDeflateHint() {
        return this.deflateHint;
    }

    public boolean isKeepDeflateHint() {
        return "keep".equals(this.deflateHint);
    }

    public void setDeflateHint(String str) {
        if (!"keep".equals(str) && !"true".equals(str) && !"false".equals(str)) {
            throw new IllegalArgumentException("Bad argument: -H " + str + " ? deflate hint should be either true, false or keep (default)");
        }
        this.deflateHint = str;
    }

    public String getModificationTime() {
        return this.modificationTime;
    }

    public void setModificationTime(String str) {
        if (!"keep".equals(str) && !Pack200.Packer.LATEST.equals(str)) {
            throw new IllegalArgumentException("Bad argument: -m " + str + " ? transmit modtimes should be either latest or keep (default)");
        }
        this.modificationTime = str;
    }

    public boolean isPassFile(String str) {
        List<String> list = this.passFiles;
        if (list == null) {
            return false;
        }
        for (String str2 : list) {
            if (str.equals(str2)) {
                return true;
            }
            if (!str2.endsWith(".class")) {
                if (!str2.endsWith("/")) {
                    str2 = str2 + "/";
                }
                return str.startsWith(str2);
            }
        }
        return false;
    }

    public void addPassFile(String str) {
        if (this.passFiles == null) {
            this.passFiles = new ArrayList();
        }
        String property = System.getProperty("file.separator");
        if (property.equals("\\")) {
            property = property + "\\";
        }
        this.passFiles.add(str.replaceAll(property, "/"));
    }

    public void removePassFile(String str) {
        this.passFiles.remove(str);
    }

    public String getUnknownAttributeAction() {
        return this.unknownAttributeAction;
    }

    public void setUnknownAttributeAction(String str) {
        this.unknownAttributeAction = str;
        if ("pass".equals(str) || "error".equals(str) || "strip".equals(str)) {
            return;
        }
        throw new RuntimeException("Incorrect option for -U, " + str);
    }

    public void addClassAttributeAction(String str, String str2) {
        if (this.classAttributeActions == null) {
            this.classAttributeActions = new HashMap();
        }
        this.classAttributeActions.put(str, str2);
    }

    public void addFieldAttributeAction(String str, String str2) {
        if (this.fieldAttributeActions == null) {
            this.fieldAttributeActions = new HashMap();
        }
        this.fieldAttributeActions.put(str, str2);
    }

    public void addMethodAttributeAction(String str, String str2) {
        if (this.methodAttributeActions == null) {
            this.methodAttributeActions = new HashMap();
        }
        this.methodAttributeActions.put(str, str2);
    }

    public void addCodeAttributeAction(String str, String str2) {
        if (this.codeAttributeActions == null) {
            this.codeAttributeActions = new HashMap();
        }
        this.codeAttributeActions.put(str, str2);
    }

    public boolean isVerbose() {
        return this.verbose;
    }

    public void setVerbose(boolean z) {
        this.verbose = z;
    }

    public void setQuiet(boolean z) {
        this.verbose = !z;
    }

    public String getLogFile() {
        return this.logFile;
    }

    public void setLogFile(String str) {
        this.logFile = str;
    }

    private void addOrUpdateAttributeActions(List list, Map map, int i) {
        NewAttribute newAttribute;
        if (map == null || map.size() <= 0) {
            return;
        }
        for (String str : map.keySet()) {
            String str2 = (String) map.get(str);
            boolean z = false;
            Iterator it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                NewAttribute newAttribute2 = (NewAttribute) it.next();
                if (newAttribute2.type.equals(str)) {
                    newAttribute2.addContext(i);
                    z = true;
                    break;
                }
            }
            if (!z) {
                if ("error".equals(str2)) {
                    newAttribute = new NewAttribute.ErrorAttribute(str, i);
                } else if ("strip".equals(str2)) {
                    newAttribute = new NewAttribute.StripAttribute(str, i);
                } else if ("pass".equals(str2)) {
                    newAttribute = new NewAttribute.PassAttribute(str, i);
                } else {
                    newAttribute = new NewAttribute(str, str2, i);
                }
                list.add(newAttribute);
            }
        }
    }

    public Attribute[] getUnknownAttributePrototypes() {
        if (this.unknownAttributeTypes == null) {
            ArrayList arrayList = new ArrayList();
            addOrUpdateAttributeActions(arrayList, this.classAttributeActions, 0);
            addOrUpdateAttributeActions(arrayList, this.methodAttributeActions, 2);
            addOrUpdateAttributeActions(arrayList, this.fieldAttributeActions, 1);
            addOrUpdateAttributeActions(arrayList, this.codeAttributeActions, 3);
            this.unknownAttributeTypes = (Attribute[]) arrayList.toArray(new Attribute[0]);
        }
        return this.unknownAttributeTypes;
    }

    public String getUnknownClassAttributeAction(String str) {
        Map map = this.classAttributeActions;
        if (map == null) {
            return this.unknownAttributeAction;
        }
        String str2 = (String) map.get(str);
        return str2 == null ? this.unknownAttributeAction : str2;
    }

    public String getUnknownMethodAttributeAction(String str) {
        Map map = this.methodAttributeActions;
        if (map == null) {
            return this.unknownAttributeAction;
        }
        String str2 = (String) map.get(str);
        return str2 == null ? this.unknownAttributeAction : str2;
    }

    public String getUnknownFieldAttributeAction(String str) {
        Map map = this.fieldAttributeActions;
        if (map == null) {
            return this.unknownAttributeAction;
        }
        String str2 = (String) map.get(str);
        return str2 == null ? this.unknownAttributeAction : str2;
    }

    public String getUnknownCodeAttributeAction(String str) {
        Map map = this.codeAttributeActions;
        if (map == null) {
            return this.unknownAttributeAction;
        }
        String str2 = (String) map.get(str);
        return str2 == null ? this.unknownAttributeAction : str2;
    }
}
