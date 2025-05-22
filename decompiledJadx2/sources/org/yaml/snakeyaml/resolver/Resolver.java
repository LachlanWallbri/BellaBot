package org.yaml.snakeyaml.resolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.Tag;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class Resolver {
    protected Map<Character, List<ResolverTuple>> yamlImplicitResolvers = new HashMap();
    public static final Pattern BOOL = Pattern.compile("^(?:yes|Yes|YES|no|No|NO|true|True|TRUE|false|False|FALSE|on|On|ON|off|Off|OFF)$");
    public static final Pattern FLOAT = Pattern.compile("^([-+]?(\\.[0-9]+|[0-9_]+(\\.[0-9_]*)?)([eE][-+]?[0-9]+)?|[-+]?[0-9][0-9_]*(?::[0-5]?[0-9])+\\.[0-9_]*|[-+]?\\.(?:inf|Inf|INF)|\\.(?:nan|NaN|NAN))$");
    public static final Pattern INT = Pattern.compile("^(?:[-+]?0b[0-1_]+|[-+]?0[0-7_]+|[-+]?(?:0|[1-9][0-9_]*)|[-+]?0x[0-9a-fA-F_]+|[-+]?[1-9][0-9_]*(?::[0-5]?[0-9])+)$");
    public static final Pattern MERGE = Pattern.compile("^(?:<<)$");
    public static final Pattern NULL = Pattern.compile("^(?:~|null|Null|NULL| )$");
    public static final Pattern EMPTY = Pattern.compile("^$");
    public static final Pattern TIMESTAMP = Pattern.compile("^(?:[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]|[0-9][0-9][0-9][0-9]-[0-9][0-9]?-[0-9][0-9]?(?:[Tt]|[ \t]+)[0-9][0-9]?:[0-9][0-9]:[0-9][0-9](?:\\.[0-9]*)?(?:[ \t]*(?:Z|[-+][0-9][0-9]?(?::[0-9][0-9])?))?)$");
    public static final Pattern VALUE = Pattern.compile("^(?:=)$");
    public static final Pattern YAML = Pattern.compile("^(?:!|&|\\*)$");

    protected void addImplicitResolvers() {
        addImplicitResolver(Tag.BOOL, BOOL, "yYnNtTfFoO");
        addImplicitResolver(Tag.INT, INT, "-+0123456789");
        addImplicitResolver(Tag.FLOAT, FLOAT, "-+0123456789.");
        addImplicitResolver(Tag.MERGE, MERGE, "<");
        addImplicitResolver(Tag.NULL, NULL, "~nN\u0000");
        addImplicitResolver(Tag.NULL, EMPTY, null);
        addImplicitResolver(Tag.TIMESTAMP, TIMESTAMP, "0123456789");
        addImplicitResolver(Tag.YAML, YAML, "!&*");
    }

    public Resolver() {
        addImplicitResolvers();
    }

    public void addImplicitResolver(Tag tag, Pattern pattern, String str) {
        if (str == null) {
            List<ResolverTuple> list = this.yamlImplicitResolvers.get(null);
            if (list == null) {
                list = new ArrayList<>();
                this.yamlImplicitResolvers.put(null, list);
            }
            list.add(new ResolverTuple(tag, pattern));
            return;
        }
        for (char c : str.toCharArray()) {
            Character valueOf = Character.valueOf(c);
            if (valueOf.charValue() == 0) {
                valueOf = null;
            }
            List<ResolverTuple> list2 = this.yamlImplicitResolvers.get(valueOf);
            if (list2 == null) {
                list2 = new ArrayList<>();
                this.yamlImplicitResolvers.put(valueOf, list2);
            }
            list2.add(new ResolverTuple(tag, pattern));
        }
    }

    public Tag resolve(NodeId nodeId, String str, boolean z) {
        List<ResolverTuple> list;
        if (nodeId == NodeId.scalar && z) {
            if (str.length() == 0) {
                list = this.yamlImplicitResolvers.get((char) 0);
            } else {
                list = this.yamlImplicitResolvers.get(Character.valueOf(str.charAt(0)));
            }
            if (list != null) {
                for (ResolverTuple resolverTuple : list) {
                    Tag tag = resolverTuple.getTag();
                    if (resolverTuple.getRegexp().matcher(str).matches()) {
                        return tag;
                    }
                }
            }
            if (this.yamlImplicitResolvers.containsKey(null)) {
                for (ResolverTuple resolverTuple2 : this.yamlImplicitResolvers.get(null)) {
                    Tag tag2 = resolverTuple2.getTag();
                    if (resolverTuple2.getRegexp().matcher(str).matches()) {
                        return tag2;
                    }
                }
            }
        }
        int i = C89921.$SwitchMap$org$yaml$snakeyaml$nodes$NodeId[nodeId.ordinal()];
        if (i == 1) {
            return Tag.STR;
        }
        if (i == 2) {
            return Tag.SEQ;
        }
        return Tag.MAP;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* renamed from: org.yaml.snakeyaml.resolver.Resolver$1 */
    /* loaded from: classes9.dex */
    static /* synthetic */ class C89921 {
        static final /* synthetic */ int[] $SwitchMap$org$yaml$snakeyaml$nodes$NodeId = new int[NodeId.values().length];

        static {
            try {
                $SwitchMap$org$yaml$snakeyaml$nodes$NodeId[NodeId.scalar.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$yaml$snakeyaml$nodes$NodeId[NodeId.sequence.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
