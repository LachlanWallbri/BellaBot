package kotlin.reflect.jvm.internal.impl.utils;

import com.iflytek.cloud.SpeechUtility;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import kotlin.jvm.functions.Function1;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* loaded from: classes2.dex */
public class DFS {

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* loaded from: classes2.dex */
    public static abstract class AbstractNodeHandler<N, R> implements NodeHandler<N, R> {
        @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
        public void afterChildren(N n) {
        }

        @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
        public boolean beforeChildren(N n) {
            return true;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* loaded from: classes2.dex */
    public interface Neighbors<N> {
        Iterable<? extends N> getNeighbors(N n);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* loaded from: classes2.dex */
    public interface NodeHandler<N, R> {
        void afterChildren(N n);

        boolean beforeChildren(N n);

        R result();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* loaded from: classes2.dex */
    public interface Visited<N> {
        boolean checkAndMarkVisited(N n);
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        switch (i) {
            case 1:
            case 5:
            case 8:
            case 11:
            case 15:
            case 18:
            case 21:
            case 23:
                objArr[0] = "neighbors";
                break;
            case 2:
            case 12:
            case 16:
            case 19:
            case 24:
                objArr[0] = "visited";
                break;
            case 3:
            case 6:
            case 13:
            case 25:
                objArr[0] = "handler";
                break;
            case 4:
            case 7:
            case 17:
            case 20:
            default:
                objArr[0] = "nodes";
                break;
            case 9:
                objArr[0] = "predicate";
                break;
            case 10:
            case 14:
                objArr[0] = "node";
                break;
            case 22:
                objArr[0] = "current";
                break;
        }
        objArr[1] = "kotlin/reflect/jvm/internal/impl/utils/DFS";
        switch (i) {
            case 7:
            case 8:
            case 9:
                objArr[2] = "ifAny";
                break;
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
                objArr[2] = "dfsFromNode";
                break;
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                objArr[2] = "topologicalOrder";
                break;
            case 22:
            case 23:
            case 24:
            case 25:
                objArr[2] = "doDfs";
                break;
            default:
                objArr[2] = "dfs";
                break;
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public static <N, R> R dfs(Collection<N> collection, Neighbors<N> neighbors, Visited<N> visited, NodeHandler<N, R> nodeHandler) {
        if (collection == null) {
            $$$reportNull$$$0(0);
        }
        if (neighbors == null) {
            $$$reportNull$$$0(1);
        }
        if (visited == null) {
            $$$reportNull$$$0(2);
        }
        if (nodeHandler == null) {
            $$$reportNull$$$0(3);
        }
        Iterator<N> it = collection.iterator();
        while (it.hasNext()) {
            doDfs(it.next(), neighbors, visited, nodeHandler);
        }
        return nodeHandler.result();
    }

    public static <N, R> R dfs(Collection<N> collection, Neighbors<N> neighbors, NodeHandler<N, R> nodeHandler) {
        if (collection == null) {
            $$$reportNull$$$0(4);
        }
        if (neighbors == null) {
            $$$reportNull$$$0(5);
        }
        if (nodeHandler == null) {
            $$$reportNull$$$0(6);
        }
        return (R) dfs(collection, neighbors, new VisitedWithSet(), nodeHandler);
    }

    public static <N> Boolean ifAny(Collection<N> collection, Neighbors<N> neighbors, final Function1<N, Boolean> function1) {
        if (collection == null) {
            $$$reportNull$$$0(7);
        }
        if (neighbors == null) {
            $$$reportNull$$$0(8);
        }
        if (function1 == null) {
            $$$reportNull$$$0(9);
        }
        final boolean[] zArr = new boolean[1];
        return (Boolean) dfs(collection, neighbors, new AbstractNodeHandler<N, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.utils.DFS.1
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.AbstractNodeHandler, kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
            public boolean beforeChildren(N n) {
                if (((Boolean) Function1.this.invoke(n)).booleanValue()) {
                    zArr[0] = true;
                }
                return !zArr[0];
            }

            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
            public Boolean result() {
                return Boolean.valueOf(zArr[0]);
            }
        });
    }

    public static <N> void doDfs(N n, Neighbors<N> neighbors, Visited<N> visited, NodeHandler<N, ?> nodeHandler) {
        if (n == null) {
            $$$reportNull$$$0(22);
        }
        if (neighbors == null) {
            $$$reportNull$$$0(23);
        }
        if (visited == null) {
            $$$reportNull$$$0(24);
        }
        if (nodeHandler == null) {
            $$$reportNull$$$0(25);
        }
        if (visited.checkAndMarkVisited(n) && nodeHandler.beforeChildren(n)) {
            Iterator<? extends N> it = neighbors.getNeighbors(n).iterator();
            while (it.hasNext()) {
                doDfs(it.next(), neighbors, visited, nodeHandler);
            }
            nodeHandler.afterChildren(n);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* loaded from: classes2.dex */
    public static class VisitedWithSet<N> implements Visited<N> {
        private final Set<N> visited;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visited", "kotlin/reflect/jvm/internal/impl/utils/DFS$VisitedWithSet", "<init>"));
        }

        public VisitedWithSet() {
            this(new HashSet());
        }

        public VisitedWithSet(Set<N> set) {
            if (set == null) {
                $$$reportNull$$$0(0);
            }
            this.visited = set;
        }

        @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.Visited
        public boolean checkAndMarkVisited(N n) {
            return this.visited.add(n);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* loaded from: classes2.dex */
    public static abstract class CollectingNodeHandler<N, R, C extends Iterable<R>> extends AbstractNodeHandler<N, C> {
        protected final C result;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
            Object[] objArr = new Object[i != 1 ? 3 : 2];
            if (i != 1) {
                objArr[0] = SpeechUtility.TAG_RESOURCE_RESULT;
            } else {
                objArr[0] = "kotlin/reflect/jvm/internal/impl/utils/DFS$CollectingNodeHandler";
            }
            if (i != 1) {
                objArr[1] = "kotlin/reflect/jvm/internal/impl/utils/DFS$CollectingNodeHandler";
            } else {
                objArr[1] = SpeechUtility.TAG_RESOURCE_RESULT;
            }
            if (i != 1) {
                objArr[2] = "<init>";
            }
            String format = String.format(str, objArr);
            if (i == 1) {
                throw new IllegalStateException(format);
            }
        }

        protected CollectingNodeHandler(C c) {
            if (c == null) {
                $$$reportNull$$$0(0);
            }
            this.result = c;
        }

        @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
        public C result() {
            C c = this.result;
            if (c == null) {
                $$$reportNull$$$0(1);
            }
            return c;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* loaded from: classes2.dex */
    public static abstract class NodeHandlerWithListResult<N, R> extends CollectingNodeHandler<N, R, LinkedList<R>> {
        /* JADX INFO: Access modifiers changed from: protected */
        public NodeHandlerWithListResult() {
            super(new LinkedList());
        }
    }
}
