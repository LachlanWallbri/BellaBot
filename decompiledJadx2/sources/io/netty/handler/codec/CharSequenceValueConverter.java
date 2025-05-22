package io.netty.handler.codec;

import io.netty.util.AsciiString;
import io.netty.util.internal.PlatformDependent;
import java.text.ParseException;
import java.util.Date;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public class CharSequenceValueConverter implements ValueConverter<CharSequence> {
    public static final CharSequenceValueConverter INSTANCE = new CharSequenceValueConverter();
    private static final AsciiString TRUE_ASCII = new AsciiString("true");

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.netty.handler.codec.ValueConverter
    public CharSequence convertObject(Object obj) {
        if (obj instanceof CharSequence) {
            return (CharSequence) obj;
        }
        return obj.toString();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.netty.handler.codec.ValueConverter
    public CharSequence convertInt(int i) {
        return String.valueOf(i);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.netty.handler.codec.ValueConverter
    public CharSequence convertLong(long j) {
        return String.valueOf(j);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.netty.handler.codec.ValueConverter
    public CharSequence convertDouble(double d) {
        return String.valueOf(d);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.netty.handler.codec.ValueConverter
    public CharSequence convertChar(char c) {
        return String.valueOf(c);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.netty.handler.codec.ValueConverter
    public CharSequence convertBoolean(boolean z) {
        return String.valueOf(z);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.netty.handler.codec.ValueConverter
    public CharSequence convertFloat(float f) {
        return String.valueOf(f);
    }

    @Override // io.netty.handler.codec.ValueConverter
    public boolean convertToBoolean(CharSequence charSequence) {
        return AsciiString.contentEqualsIgnoreCase(charSequence, TRUE_ASCII);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.netty.handler.codec.ValueConverter
    public CharSequence convertByte(byte b) {
        return String.valueOf((int) b);
    }

    @Override // io.netty.handler.codec.ValueConverter
    public byte convertToByte(CharSequence charSequence) {
        if (charSequence instanceof AsciiString) {
            return ((AsciiString) charSequence).byteAt(0);
        }
        return Byte.parseByte(charSequence.toString());
    }

    @Override // io.netty.handler.codec.ValueConverter
    public char convertToChar(CharSequence charSequence) {
        return charSequence.charAt(0);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.netty.handler.codec.ValueConverter
    public CharSequence convertShort(short s) {
        return String.valueOf((int) s);
    }

    @Override // io.netty.handler.codec.ValueConverter
    public short convertToShort(CharSequence charSequence) {
        if (charSequence instanceof AsciiString) {
            return ((AsciiString) charSequence).parseShort();
        }
        return Short.parseShort(charSequence.toString());
    }

    @Override // io.netty.handler.codec.ValueConverter
    public int convertToInt(CharSequence charSequence) {
        if (charSequence instanceof AsciiString) {
            return ((AsciiString) charSequence).parseInt();
        }
        return Integer.parseInt(charSequence.toString());
    }

    @Override // io.netty.handler.codec.ValueConverter
    public long convertToLong(CharSequence charSequence) {
        if (charSequence instanceof AsciiString) {
            return ((AsciiString) charSequence).parseLong();
        }
        return Long.parseLong(charSequence.toString());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.netty.handler.codec.ValueConverter
    public CharSequence convertTimeMillis(long j) {
        return DateFormatter.format(new Date(j));
    }

    @Override // io.netty.handler.codec.ValueConverter
    public long convertToTimeMillis(CharSequence charSequence) {
        Date parseHttpDate = DateFormatter.parseHttpDate(charSequence);
        if (parseHttpDate == null) {
            PlatformDependent.throwException(new ParseException("header can't be parsed into a Date: " + ((Object) charSequence), 0));
            return 0L;
        }
        return parseHttpDate.getTime();
    }

    @Override // io.netty.handler.codec.ValueConverter
    public float convertToFloat(CharSequence charSequence) {
        if (charSequence instanceof AsciiString) {
            return ((AsciiString) charSequence).parseFloat();
        }
        return Float.parseFloat(charSequence.toString());
    }

    @Override // io.netty.handler.codec.ValueConverter
    public double convertToDouble(CharSequence charSequence) {
        if (charSequence instanceof AsciiString) {
            return ((AsciiString) charSequence).parseDouble();
        }
        return Double.parseDouble(charSequence.toString());
    }
}
