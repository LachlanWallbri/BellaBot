package org.yaml.snakeyaml;

import java.util.Map;
import java.util.TimeZone;
import kotlin.text.Typography;
import org.apache.commons.io.IOUtils;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.serializer.AnchorGenerator;
import org.yaml.snakeyaml.serializer.NumberAnchorGenerator;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class DumperOptions {
    private ScalarStyle defaultStyle = ScalarStyle.PLAIN;
    private FlowStyle defaultFlowStyle = FlowStyle.AUTO;
    private boolean canonical = false;
    private boolean allowUnicode = true;
    private boolean allowReadOnlyProperties = false;
    private int indent = 2;
    private int indicatorIndent = 0;
    private int bestWidth = 80;
    private boolean splitLines = true;
    private LineBreak lineBreak = LineBreak.UNIX;
    private boolean explicitStart = false;
    private boolean explicitEnd = false;
    private TimeZone timeZone = null;
    private Version version = null;
    private Map<String, String> tags = null;
    private Boolean prettyFlow = false;
    private AnchorGenerator anchorGenerator = new NumberAnchorGenerator(0);

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public enum ScalarStyle {
        DOUBLE_QUOTED('\"'),
        SINGLE_QUOTED('\''),
        LITERAL('|'),
        FOLDED(Character.valueOf(Typography.greater)),
        PLAIN(null);

        private Character styleChar;

        ScalarStyle(Character ch) {
            this.styleChar = ch;
        }

        public Character getChar() {
            return this.styleChar;
        }

        @Override // java.lang.Enum
        public String toString() {
            return "Scalar style: '" + this.styleChar + "'";
        }

        public static ScalarStyle createStyle(Character ch) {
            if (ch == null) {
                return PLAIN;
            }
            char charValue = ch.charValue();
            if (charValue == '\"') {
                return DOUBLE_QUOTED;
            }
            if (charValue == '\'') {
                return SINGLE_QUOTED;
            }
            if (charValue == '>') {
                return FOLDED;
            }
            if (charValue == '|') {
                return LITERAL;
            }
            throw new YAMLException("Unknown scalar style character: " + ch);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public enum FlowStyle {
        FLOW(Boolean.TRUE),
        BLOCK(Boolean.FALSE),
        AUTO(null);

        private Boolean styleBoolean;

        FlowStyle(Boolean bool) {
            this.styleBoolean = bool;
        }

        public Boolean getStyleBoolean() {
            return this.styleBoolean;
        }

        @Override // java.lang.Enum
        public String toString() {
            return "Flow style: '" + this.styleBoolean + "'";
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public enum LineBreak {
        WIN(IOUtils.LINE_SEPARATOR_WINDOWS),
        MAC("\r"),
        UNIX("\n");

        private String lineBreak;

        LineBreak(String str) {
            this.lineBreak = str;
        }

        public String getString() {
            return this.lineBreak;
        }

        @Override // java.lang.Enum
        public String toString() {
            return "Line break: " + name();
        }

        public static LineBreak getPlatformLineBreak() {
            String property = System.getProperty("line.separator");
            for (LineBreak lineBreak : values()) {
                if (lineBreak.lineBreak.equals(property)) {
                    return lineBreak;
                }
            }
            return UNIX;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public enum Version {
        V1_0(new Integer[]{1, 0}),
        V1_1(new Integer[]{1, 1});

        private Integer[] version;

        Version(Integer[] numArr) {
            this.version = numArr;
        }

        public int major() {
            return this.version[0].intValue();
        }

        public int minor() {
            return this.version[1].intValue();
        }

        public String getRepresentation() {
            return this.version[0] + "." + this.version[1];
        }

        @Override // java.lang.Enum
        public String toString() {
            return "Version: " + getRepresentation();
        }
    }

    public boolean isAllowUnicode() {
        return this.allowUnicode;
    }

    public void setAllowUnicode(boolean z) {
        this.allowUnicode = z;
    }

    public ScalarStyle getDefaultScalarStyle() {
        return this.defaultStyle;
    }

    public void setDefaultScalarStyle(ScalarStyle scalarStyle) {
        if (scalarStyle == null) {
            throw new NullPointerException("Use ScalarStyle enum.");
        }
        this.defaultStyle = scalarStyle;
    }

    public void setIndent(int i) {
        if (i < 1) {
            throw new YAMLException("Indent must be at least 1");
        }
        if (i > 10) {
            throw new YAMLException("Indent must be at most 10");
        }
        this.indent = i;
    }

    public int getIndent() {
        return this.indent;
    }

    public void setIndicatorIndent(int i) {
        if (i < 0) {
            throw new YAMLException("Indicator indent must be non-negative.");
        }
        if (i > 9) {
            throw new YAMLException("Indicator indent must be at most Emitter.MAX_INDENT-1: 9");
        }
        this.indicatorIndent = i;
    }

    public int getIndicatorIndent() {
        return this.indicatorIndent;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public Version getVersion() {
        return this.version;
    }

    public void setCanonical(boolean z) {
        this.canonical = z;
    }

    public boolean isCanonical() {
        return this.canonical;
    }

    public void setPrettyFlow(boolean z) {
        this.prettyFlow = Boolean.valueOf(z);
    }

    public boolean isPrettyFlow() {
        return this.prettyFlow.booleanValue();
    }

    public void setWidth(int i) {
        this.bestWidth = i;
    }

    public int getWidth() {
        return this.bestWidth;
    }

    public void setSplitLines(boolean z) {
        this.splitLines = z;
    }

    public boolean getSplitLines() {
        return this.splitLines;
    }

    public LineBreak getLineBreak() {
        return this.lineBreak;
    }

    public void setDefaultFlowStyle(FlowStyle flowStyle) {
        if (flowStyle == null) {
            throw new NullPointerException("Use FlowStyle enum.");
        }
        this.defaultFlowStyle = flowStyle;
    }

    public FlowStyle getDefaultFlowStyle() {
        return this.defaultFlowStyle;
    }

    public void setLineBreak(LineBreak lineBreak) {
        if (lineBreak == null) {
            throw new NullPointerException("Specify line break.");
        }
        this.lineBreak = lineBreak;
    }

    public boolean isExplicitStart() {
        return this.explicitStart;
    }

    public void setExplicitStart(boolean z) {
        this.explicitStart = z;
    }

    public boolean isExplicitEnd() {
        return this.explicitEnd;
    }

    public void setExplicitEnd(boolean z) {
        this.explicitEnd = z;
    }

    public Map<String, String> getTags() {
        return this.tags;
    }

    public void setTags(Map<String, String> map) {
        this.tags = map;
    }

    public boolean isAllowReadOnlyProperties() {
        return this.allowReadOnlyProperties;
    }

    public void setAllowReadOnlyProperties(boolean z) {
        this.allowReadOnlyProperties = z;
    }

    public TimeZone getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public AnchorGenerator getAnchorGenerator() {
        return this.anchorGenerator;
    }

    public void setAnchorGenerator(AnchorGenerator anchorGenerator) {
        this.anchorGenerator = anchorGenerator;
    }
}
