package io.grpc;

import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class PersistentHashArrayMappedTrie {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface Node<K, V> {
        V get(K k, int i, int i2);

        Node<K, V> put(K k, V v, int i, int i2);

        int size();
    }

    private PersistentHashArrayMappedTrie() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <K, V> V get(Node<K, V> node, K k) {
        if (node == null) {
            return null;
        }
        return node.get(k, k.hashCode(), 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <K, V> Node<K, V> put(Node<K, V> node, K k, V v) {
        if (node == null) {
            return new Leaf(k, v);
        }
        return node.put(k, v, k.hashCode(), 0);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    static final class Leaf<K, V> implements Node<K, V> {
        private final K key;
        private final V value;

        @Override // io.grpc.PersistentHashArrayMappedTrie.Node
        public int size() {
            return 1;
        }

        public Leaf(K k, V v) {
            this.key = k;
            this.value = v;
        }

        @Override // io.grpc.PersistentHashArrayMappedTrie.Node
        public V get(K k, int i, int i2) {
            if (this.key == k) {
                return this.value;
            }
            return null;
        }

        @Override // io.grpc.PersistentHashArrayMappedTrie.Node
        public Node<K, V> put(K k, V v, int i, int i2) {
            int hashCode = this.key.hashCode();
            if (hashCode != i) {
                return CompressedIndex.combine(new Leaf(k, v), i, this, hashCode, i2);
            }
            K k2 = this.key;
            if (k2 == k) {
                return new Leaf(k, v);
            }
            return new CollisionLeaf(k2, this.value, k, v);
        }

        public String toString() {
            return String.format("Leaf(key=%s value=%s)", this.key, this.value);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    static final class CollisionLeaf<K, V> implements Node<K, V> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final K[] keys;
        private final V[] values;

        CollisionLeaf(K k, V v, K k2, V v2) {
            this(new Object[]{k, k2}, new Object[]{v, v2});
        }

        private CollisionLeaf(K[] kArr, V[] vArr) {
            this.keys = kArr;
            this.values = vArr;
        }

        @Override // io.grpc.PersistentHashArrayMappedTrie.Node
        public int size() {
            return this.values.length;
        }

        @Override // io.grpc.PersistentHashArrayMappedTrie.Node
        public V get(K k, int i, int i2) {
            int i3 = 0;
            while (true) {
                K[] kArr = this.keys;
                if (i3 >= kArr.length) {
                    return null;
                }
                if (kArr[i3] == k) {
                    return this.values[i3];
                }
                i3++;
            }
        }

        @Override // io.grpc.PersistentHashArrayMappedTrie.Node
        public Node<K, V> put(K k, V v, int i, int i2) {
            int hashCode = this.keys[0].hashCode();
            if (hashCode != i) {
                return CompressedIndex.combine(new Leaf(k, v), i, this, hashCode, i2);
            }
            int indexOfKey = indexOfKey(k);
            if (indexOfKey != -1) {
                K[] kArr = this.keys;
                Object[] copyOf = Arrays.copyOf(kArr, kArr.length);
                Object[] copyOf2 = Arrays.copyOf(this.values, this.keys.length);
                copyOf[indexOfKey] = k;
                copyOf2[indexOfKey] = v;
                return new CollisionLeaf(copyOf, copyOf2);
            }
            K[] kArr2 = this.keys;
            Object[] copyOf3 = Arrays.copyOf(kArr2, kArr2.length + 1);
            Object[] copyOf4 = Arrays.copyOf(this.values, this.keys.length + 1);
            K[] kArr3 = this.keys;
            copyOf3[kArr3.length] = k;
            copyOf4[kArr3.length] = v;
            return new CollisionLeaf(copyOf3, copyOf4);
        }

        private int indexOfKey(K k) {
            int i = 0;
            while (true) {
                K[] kArr = this.keys;
                if (i >= kArr.length) {
                    return -1;
                }
                if (kArr[i] == k) {
                    return i;
                }
                i++;
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("CollisionLeaf(");
            for (int i = 0; i < this.values.length; i++) {
                sb.append("(key=");
                sb.append(this.keys[i]);
                sb.append(" value=");
                sb.append(this.values[i]);
                sb.append(") ");
            }
            sb.append(")");
            return sb.toString();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    static final class CompressedIndex<K, V> implements Node<K, V> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final int BITS = 5;
        private static final int BITS_MASK = 31;
        final int bitmap;
        private final int size;
        final Node<K, V>[] values;

        private static int uncompressedIndex(int i, int i2) {
            return (i >>> i2) & 31;
        }

        private CompressedIndex(int i, Node<K, V>[] nodeArr, int i2) {
            this.bitmap = i;
            this.values = nodeArr;
            this.size = i2;
        }

        @Override // io.grpc.PersistentHashArrayMappedTrie.Node
        public int size() {
            return this.size;
        }

        @Override // io.grpc.PersistentHashArrayMappedTrie.Node
        public V get(K k, int i, int i2) {
            int indexBit = indexBit(i, i2);
            if ((this.bitmap & indexBit) == 0) {
                return null;
            }
            return this.values[compressedIndex(indexBit)].get(k, i, i2 + 5);
        }

        @Override // io.grpc.PersistentHashArrayMappedTrie.Node
        public Node<K, V> put(K k, V v, int i, int i2) {
            int indexBit = indexBit(i, i2);
            int compressedIndex = compressedIndex(indexBit);
            int i3 = this.bitmap;
            if ((i3 & indexBit) == 0) {
                int i4 = i3 | indexBit;
                Node<K, V>[] nodeArr = this.values;
                Node[] nodeArr2 = new Node[nodeArr.length + 1];
                System.arraycopy(nodeArr, 0, nodeArr2, 0, compressedIndex);
                nodeArr2[compressedIndex] = new Leaf(k, v);
                Node<K, V>[] nodeArr3 = this.values;
                System.arraycopy(nodeArr3, compressedIndex, nodeArr2, compressedIndex + 1, nodeArr3.length - compressedIndex);
                return new CompressedIndex(i4, nodeArr2, size() + 1);
            }
            Node<K, V>[] nodeArr4 = this.values;
            Node[] nodeArr5 = (Node[]) Arrays.copyOf(nodeArr4, nodeArr4.length);
            nodeArr5[compressedIndex] = this.values[compressedIndex].put(k, v, i, i2 + 5);
            return new CompressedIndex(this.bitmap, nodeArr5, (size() + nodeArr5[compressedIndex].size()) - this.values[compressedIndex].size());
        }

        static <K, V> Node<K, V> combine(Node<K, V> node, int i, Node<K, V> node2, int i2, int i3) {
            int indexBit = indexBit(i, i3);
            int indexBit2 = indexBit(i2, i3);
            if (indexBit == indexBit2) {
                Node combine = combine(node, i, node2, i2, i3 + 5);
                return new CompressedIndex(indexBit, new Node[]{combine}, combine.size());
            }
            if (uncompressedIndex(i, i3) > uncompressedIndex(i2, i3)) {
                node2 = node;
                node = node2;
            }
            return new CompressedIndex(indexBit | indexBit2, new Node[]{node, node2}, node.size() + node2.size());
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("CompressedIndex(");
            sb.append(String.format("bitmap=%s ", Integer.toBinaryString(this.bitmap)));
            for (Node<K, V> node : this.values) {
                sb.append(node);
                sb.append(" ");
            }
            sb.append(")");
            return sb.toString();
        }

        private int compressedIndex(int i) {
            return Integer.bitCount((i - 1) & this.bitmap);
        }

        private static int indexBit(int i, int i2) {
            return 1 << uncompressedIndex(i, i2);
        }
    }
}
