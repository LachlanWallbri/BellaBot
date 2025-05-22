package org.yaml.snakeyaml.representer;

import androidx.exifinterface.media.ExifInterface;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Pattern;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.reader.StreamReader;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class SafeRepresenter extends BaseRepresenter {
    public static Pattern MULTILINE_PATTERN = Pattern.compile("\n|\u0085|\u2028|\u2029");
    protected Map<Class<? extends Object>, Tag> classTags;
    protected TimeZone timeZone = null;

    public SafeRepresenter() {
        this.nullRepresenter = new RepresentNull();
        this.representers.put(String.class, new RepresentString());
        this.representers.put(Boolean.class, new RepresentBoolean());
        this.representers.put(Character.class, new RepresentString());
        this.representers.put(UUID.class, new RepresentUuid());
        this.representers.put(byte[].class, new RepresentByteArray());
        RepresentPrimitiveArray representPrimitiveArray = new RepresentPrimitiveArray();
        this.representers.put(short[].class, representPrimitiveArray);
        this.representers.put(int[].class, representPrimitiveArray);
        this.representers.put(long[].class, representPrimitiveArray);
        this.representers.put(float[].class, representPrimitiveArray);
        this.representers.put(double[].class, representPrimitiveArray);
        this.representers.put(char[].class, representPrimitiveArray);
        this.representers.put(boolean[].class, representPrimitiveArray);
        this.multiRepresenters.put(Number.class, new RepresentNumber());
        this.multiRepresenters.put(List.class, new RepresentList());
        this.multiRepresenters.put(Map.class, new RepresentMap());
        this.multiRepresenters.put(Set.class, new RepresentSet());
        this.multiRepresenters.put(Iterator.class, new RepresentIterator());
        this.multiRepresenters.put(new Object[0].getClass(), new RepresentArray());
        this.multiRepresenters.put(Date.class, new RepresentDate());
        this.multiRepresenters.put(Enum.class, new RepresentEnum());
        this.multiRepresenters.put(Calendar.class, new RepresentDate());
        this.classTags = new HashMap();
    }

    protected Tag getTag(Class<?> cls, Tag tag) {
        return this.classTags.containsKey(cls) ? this.classTags.get(cls) : tag;
    }

    public Tag addClassTag(Class<? extends Object> cls, Tag tag) {
        if (tag == null) {
            throw new NullPointerException("Tag must be provided.");
        }
        return this.classTags.put(cls, tag);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    protected class RepresentNull implements Represent {
        protected RepresentNull() {
        }

        @Override // org.yaml.snakeyaml.representer.Represent
        public Node representData(Object obj) {
            return SafeRepresenter.this.representScalar(Tag.NULL, "null");
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    protected class RepresentString implements Represent {
        protected RepresentString() {
        }

        @Override // org.yaml.snakeyaml.representer.Represent
        public Node representData(Object obj) {
            Character ch;
            Tag tag = Tag.STR;
            String obj2 = obj.toString();
            if (StreamReader.NON_PRINTABLE.matcher(obj2).find()) {
                tag = Tag.BINARY;
                try {
                    obj2 = String.valueOf(Base64Coder.encode(obj2.getBytes("UTF-8")));
                    ch = '|';
                } catch (UnsupportedEncodingException e) {
                    throw new YAMLException(e);
                }
            } else {
                ch = null;
            }
            if (SafeRepresenter.this.defaultScalarStyle == null && SafeRepresenter.MULTILINE_PATTERN.matcher(obj2).find()) {
                ch = '|';
            }
            return SafeRepresenter.this.representScalar(tag, obj2, ch);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    protected class RepresentBoolean implements Represent {
        protected RepresentBoolean() {
        }

        @Override // org.yaml.snakeyaml.representer.Represent
        public Node representData(Object obj) {
            return SafeRepresenter.this.representScalar(Tag.BOOL, Boolean.TRUE.equals(obj) ? "true" : "false");
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    protected class RepresentNumber implements Represent {
        protected RepresentNumber() {
        }

        @Override // org.yaml.snakeyaml.representer.Represent
        public Node representData(Object obj) {
            Tag tag;
            String obj2;
            if ((obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof BigInteger)) {
                tag = Tag.INT;
                obj2 = obj.toString();
            } else {
                Number number = (Number) obj;
                tag = Tag.FLOAT;
                if (number.equals(Double.valueOf(Double.NaN))) {
                    obj2 = ".NaN";
                } else if (number.equals(Double.valueOf(Double.POSITIVE_INFINITY))) {
                    obj2 = ".inf";
                } else {
                    obj2 = number.equals(Double.valueOf(Double.NEGATIVE_INFINITY)) ? "-.inf" : number.toString();
                }
            }
            SafeRepresenter safeRepresenter = SafeRepresenter.this;
            return safeRepresenter.representScalar(safeRepresenter.getTag(obj.getClass(), tag), obj2);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    protected class RepresentList implements Represent {
        protected RepresentList() {
        }

        @Override // org.yaml.snakeyaml.representer.Represent
        public Node representData(Object obj) {
            SafeRepresenter safeRepresenter = SafeRepresenter.this;
            return safeRepresenter.representSequence(safeRepresenter.getTag(obj.getClass(), Tag.SEQ), (List) obj, null);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    protected class RepresentIterator implements Represent {
        protected RepresentIterator() {
        }

        @Override // org.yaml.snakeyaml.representer.Represent
        public Node representData(Object obj) {
            Iterator it = (Iterator) obj;
            SafeRepresenter safeRepresenter = SafeRepresenter.this;
            return safeRepresenter.representSequence(safeRepresenter.getTag(obj.getClass(), Tag.SEQ), new IteratorWrapper(it), null);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    private static class IteratorWrapper implements Iterable<Object> {
        private Iterator<Object> iter;

        public IteratorWrapper(Iterator<Object> it) {
            this.iter = it;
        }

        @Override // java.lang.Iterable
        public Iterator<Object> iterator() {
            return this.iter;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    protected class RepresentArray implements Represent {
        protected RepresentArray() {
        }

        @Override // org.yaml.snakeyaml.representer.Represent
        public Node representData(Object obj) {
            return SafeRepresenter.this.representSequence(Tag.SEQ, Arrays.asList((Object[]) obj), null);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    protected class RepresentPrimitiveArray implements Represent {
        protected RepresentPrimitiveArray() {
        }

        @Override // org.yaml.snakeyaml.representer.Represent
        public Node representData(Object obj) {
            Class<?> componentType = obj.getClass().getComponentType();
            if (Byte.TYPE == componentType) {
                return SafeRepresenter.this.representSequence(Tag.SEQ, asByteList(obj), null);
            }
            if (Short.TYPE == componentType) {
                return SafeRepresenter.this.representSequence(Tag.SEQ, asShortList(obj), null);
            }
            if (Integer.TYPE == componentType) {
                return SafeRepresenter.this.representSequence(Tag.SEQ, asIntList(obj), null);
            }
            if (Long.TYPE == componentType) {
                return SafeRepresenter.this.representSequence(Tag.SEQ, asLongList(obj), null);
            }
            if (Float.TYPE == componentType) {
                return SafeRepresenter.this.representSequence(Tag.SEQ, asFloatList(obj), null);
            }
            if (Double.TYPE == componentType) {
                return SafeRepresenter.this.representSequence(Tag.SEQ, asDoubleList(obj), null);
            }
            if (Character.TYPE == componentType) {
                return SafeRepresenter.this.representSequence(Tag.SEQ, asCharList(obj), null);
            }
            if (Boolean.TYPE == componentType) {
                return SafeRepresenter.this.representSequence(Tag.SEQ, asBooleanList(obj), null);
            }
            throw new YAMLException("Unexpected primitive '" + componentType.getCanonicalName() + "'");
        }

        private List<Byte> asByteList(Object obj) {
            byte[] bArr = (byte[]) obj;
            ArrayList arrayList = new ArrayList(bArr.length);
            for (byte b : bArr) {
                arrayList.add(Byte.valueOf(b));
            }
            return arrayList;
        }

        private List<Short> asShortList(Object obj) {
            short[] sArr = (short[]) obj;
            ArrayList arrayList = new ArrayList(sArr.length);
            for (short s : sArr) {
                arrayList.add(Short.valueOf(s));
            }
            return arrayList;
        }

        private List<Integer> asIntList(Object obj) {
            int[] iArr = (int[]) obj;
            ArrayList arrayList = new ArrayList(iArr.length);
            for (int i : iArr) {
                arrayList.add(Integer.valueOf(i));
            }
            return arrayList;
        }

        private List<Long> asLongList(Object obj) {
            long[] jArr = (long[]) obj;
            ArrayList arrayList = new ArrayList(jArr.length);
            for (long j : jArr) {
                arrayList.add(Long.valueOf(j));
            }
            return arrayList;
        }

        private List<Float> asFloatList(Object obj) {
            float[] fArr = (float[]) obj;
            ArrayList arrayList = new ArrayList(fArr.length);
            for (float f : fArr) {
                arrayList.add(Float.valueOf(f));
            }
            return arrayList;
        }

        private List<Double> asDoubleList(Object obj) {
            double[] dArr = (double[]) obj;
            ArrayList arrayList = new ArrayList(dArr.length);
            for (double d : dArr) {
                arrayList.add(Double.valueOf(d));
            }
            return arrayList;
        }

        private List<Character> asCharList(Object obj) {
            char[] cArr = (char[]) obj;
            ArrayList arrayList = new ArrayList(cArr.length);
            for (char c : cArr) {
                arrayList.add(Character.valueOf(c));
            }
            return arrayList;
        }

        private List<Boolean> asBooleanList(Object obj) {
            boolean[] zArr = (boolean[]) obj;
            ArrayList arrayList = new ArrayList(zArr.length);
            for (boolean z : zArr) {
                arrayList.add(Boolean.valueOf(z));
            }
            return arrayList;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    protected class RepresentMap implements Represent {
        protected RepresentMap() {
        }

        @Override // org.yaml.snakeyaml.representer.Represent
        public Node representData(Object obj) {
            SafeRepresenter safeRepresenter = SafeRepresenter.this;
            return safeRepresenter.representMapping(safeRepresenter.getTag(obj.getClass(), Tag.MAP), (Map) obj, null);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    protected class RepresentSet implements Represent {
        protected RepresentSet() {
        }

        @Override // org.yaml.snakeyaml.representer.Represent
        public Node representData(Object obj) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            Iterator it = ((Set) obj).iterator();
            while (it.hasNext()) {
                linkedHashMap.put(it.next(), null);
            }
            SafeRepresenter safeRepresenter = SafeRepresenter.this;
            return safeRepresenter.representMapping(safeRepresenter.getTag(obj.getClass(), Tag.SET), linkedHashMap, null);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    protected class RepresentDate implements Represent {
        protected RepresentDate() {
        }

        @Override // org.yaml.snakeyaml.representer.Represent
        public Node representData(Object obj) {
            Calendar calendar;
            Object valueOf;
            if (obj instanceof Calendar) {
                calendar = (Calendar) obj;
            } else {
                calendar = Calendar.getInstance(SafeRepresenter.this.getTimeZone() == null ? TimeZone.getTimeZone("UTC") : SafeRepresenter.this.timeZone);
                calendar.setTime((Date) obj);
            }
            int i = calendar.get(1);
            int i2 = calendar.get(2) + 1;
            int i3 = calendar.get(5);
            int i4 = calendar.get(11);
            int i5 = calendar.get(12);
            int i6 = calendar.get(13);
            int i7 = calendar.get(14);
            StringBuilder sb = new StringBuilder(String.valueOf(i));
            while (sb.length() < 4) {
                sb.insert(0, "0");
            }
            sb.append("-");
            if (i2 < 10) {
                sb.append("0");
            }
            sb.append(String.valueOf(i2));
            sb.append("-");
            if (i3 < 10) {
                sb.append("0");
            }
            sb.append(String.valueOf(i3));
            sb.append(ExifInterface.GPS_DIRECTION_TRUE);
            if (i4 < 10) {
                sb.append("0");
            }
            sb.append(String.valueOf(i4));
            sb.append(":");
            if (i5 < 10) {
                sb.append("0");
            }
            sb.append(String.valueOf(i5));
            sb.append(":");
            if (i6 < 10) {
                sb.append("0");
            }
            sb.append(String.valueOf(i6));
            if (i7 > 0) {
                if (i7 < 10) {
                    sb.append(".00");
                } else if (i7 < 100) {
                    sb.append(".0");
                } else {
                    sb.append(".");
                }
                sb.append(String.valueOf(i7));
            }
            if (TimeZone.getTimeZone("UTC").equals(calendar.getTimeZone())) {
                sb.append("Z");
            } else {
                int offset = calendar.getTimeZone().getOffset(calendar.get(0), calendar.get(1), calendar.get(2), calendar.get(5), calendar.get(7), calendar.get(14)) / 60000;
                int i8 = offset / 60;
                int i9 = offset % 60;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(i8 > 0 ? "+" : "");
                sb2.append(i8);
                sb2.append(":");
                if (i9 < 10) {
                    valueOf = "0" + i9;
                } else {
                    valueOf = Integer.valueOf(i9);
                }
                sb2.append(valueOf);
                sb.append(sb2.toString());
            }
            SafeRepresenter safeRepresenter = SafeRepresenter.this;
            return safeRepresenter.representScalar(safeRepresenter.getTag(obj.getClass(), Tag.TIMESTAMP), sb.toString(), null);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    protected class RepresentEnum implements Represent {
        protected RepresentEnum() {
        }

        @Override // org.yaml.snakeyaml.representer.Represent
        public Node representData(Object obj) {
            Tag tag = new Tag((Class<? extends Object>) obj.getClass());
            SafeRepresenter safeRepresenter = SafeRepresenter.this;
            return safeRepresenter.representScalar(safeRepresenter.getTag(obj.getClass(), tag), ((Enum) obj).name());
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    protected class RepresentByteArray implements Represent {
        protected RepresentByteArray() {
        }

        @Override // org.yaml.snakeyaml.representer.Represent
        public Node representData(Object obj) {
            return SafeRepresenter.this.representScalar(Tag.BINARY, String.valueOf(Base64Coder.encode((byte[]) obj)), '|');
        }
    }

    public TimeZone getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    protected class RepresentUuid implements Represent {
        protected RepresentUuid() {
        }

        @Override // org.yaml.snakeyaml.representer.Represent
        public Node representData(Object obj) {
            SafeRepresenter safeRepresenter = SafeRepresenter.this;
            return safeRepresenter.representScalar(safeRepresenter.getTag(obj.getClass(), new Tag((Class<? extends Object>) UUID.class)), obj.toString());
        }
    }
}
