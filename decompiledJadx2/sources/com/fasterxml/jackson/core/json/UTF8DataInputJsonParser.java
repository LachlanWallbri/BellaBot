package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.base.GeneratorBase;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.pudutech.gatecontrollerlib.GateControllerMsg;
import java.io.DataInput;
import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Arrays;
import org.apache.commons.codec.language.Soundex;
import org.jetbrains.anko.DimensionsKt;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
public class UTF8DataInputJsonParser extends ParserBase {
    static final byte BYTE_LF = 10;
    protected DataInput _inputData;
    protected int _nextByte;
    protected ObjectCodec _objectCodec;
    private int _quad1;
    protected int[] _quadBuffer;
    protected final ByteQuadsCanonicalizer _symbols;
    protected boolean _tokenIncomplete;
    private static final int FEAT_MASK_TRAILING_COMMA = JsonParser.Feature.ALLOW_TRAILING_COMMA.getMask();
    private static final int FEAT_MASK_LEADING_ZEROS = JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS.getMask();
    private static final int FEAT_MASK_NON_NUM_NUMBERS = JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS.getMask();
    private static final int FEAT_MASK_ALLOW_MISSING = JsonParser.Feature.ALLOW_MISSING_VALUES.getMask();
    private static final int FEAT_MASK_ALLOW_SINGLE_QUOTES = JsonParser.Feature.ALLOW_SINGLE_QUOTES.getMask();
    private static final int FEAT_MASK_ALLOW_UNQUOTED_NAMES = JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES.getMask();
    private static final int FEAT_MASK_ALLOW_JAVA_COMMENTS = JsonParser.Feature.ALLOW_COMMENTS.getMask();
    private static final int FEAT_MASK_ALLOW_YAML_COMMENTS = JsonParser.Feature.ALLOW_YAML_COMMENTS.getMask();
    private static final int[] _icUTF8 = CharTypes.getInputCodeUtf8();
    protected static final int[] _icLatin1 = CharTypes.getInputCodeLatin1();

    private static final int pad(int i, int i2) {
        return i2 == 4 ? i : i | ((-1) << (i2 << 3));
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase
    protected void _closeInput() throws IOException {
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int releaseBuffered(OutputStream outputStream) throws IOException {
        return 0;
    }

    public UTF8DataInputJsonParser(IOContext iOContext, int i, DataInput dataInput, ObjectCodec objectCodec, ByteQuadsCanonicalizer byteQuadsCanonicalizer, int i2) {
        super(iOContext, i);
        this._quadBuffer = new int[16];
        this._nextByte = -1;
        this._objectCodec = objectCodec;
        this._symbols = byteQuadsCanonicalizer;
        this._inputData = dataInput;
        this._nextByte = i2;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public void setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public Object getInputSource() {
        return this._inputData;
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase
    protected void _releaseBuffers() throws IOException {
        super._releaseBuffers();
        this._symbols.release();
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public String getText() throws IOException {
        if (this._currToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                return _finishAndReturnString();
            }
            return this._textBuffer.contentsAsString();
        }
        return _getText2(this._currToken);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int getText(Writer writer) throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                _finishString();
            }
            return this._textBuffer.contentsToWriter(writer);
        }
        if (jsonToken == JsonToken.FIELD_NAME) {
            String currentName = this._parsingContext.getCurrentName();
            writer.write(currentName);
            return currentName.length();
        }
        if (jsonToken == null) {
            return 0;
        }
        if (jsonToken.isNumeric()) {
            return this._textBuffer.contentsToWriter(writer);
        }
        char[] asCharArray = jsonToken.asCharArray();
        writer.write(asCharArray);
        return asCharArray.length;
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public String getValueAsString() throws IOException {
        if (this._currToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                return _finishAndReturnString();
            }
            return this._textBuffer.contentsAsString();
        }
        if (this._currToken == JsonToken.FIELD_NAME) {
            return getCurrentName();
        }
        return super.getValueAsString(null);
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public String getValueAsString(String str) throws IOException {
        if (this._currToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                return _finishAndReturnString();
            }
            return this._textBuffer.contentsAsString();
        }
        if (this._currToken == JsonToken.FIELD_NAME) {
            return getCurrentName();
        }
        return super.getValueAsString(str);
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public int getValueAsInt() throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT) {
            if ((this._numTypesValid & 1) == 0) {
                if (this._numTypesValid == 0) {
                    return _parseIntValue();
                }
                if ((this._numTypesValid & 1) == 0) {
                    convertNumberToInt();
                }
            }
            return this._numberInt;
        }
        return super.getValueAsInt(0);
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public int getValueAsInt(int i) throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT) {
            if ((this._numTypesValid & 1) == 0) {
                if (this._numTypesValid == 0) {
                    return _parseIntValue();
                }
                if ((this._numTypesValid & 1) == 0) {
                    convertNumberToInt();
                }
            }
            return this._numberInt;
        }
        return super.getValueAsInt(i);
    }

    protected final String _getText2(JsonToken jsonToken) {
        if (jsonToken == null) {
            return null;
        }
        int m562id = jsonToken.m562id();
        if (m562id == 5) {
            return this._parsingContext.getCurrentName();
        }
        if (m562id == 6 || m562id == 7 || m562id == 8) {
            return this._textBuffer.contentsAsString();
        }
        return jsonToken.asString();
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public char[] getTextCharacters() throws IOException {
        if (this._currToken == null) {
            return null;
        }
        int m562id = this._currToken.m562id();
        if (m562id == 5) {
            if (!this._nameCopied) {
                String currentName = this._parsingContext.getCurrentName();
                int length = currentName.length();
                if (this._nameCopyBuffer == null) {
                    this._nameCopyBuffer = this._ioContext.allocNameCopyBuffer(length);
                } else if (this._nameCopyBuffer.length < length) {
                    this._nameCopyBuffer = new char[length];
                }
                currentName.getChars(0, length, this._nameCopyBuffer, 0);
                this._nameCopied = true;
            }
            return this._nameCopyBuffer;
        }
        if (m562id != 6) {
            if (m562id != 7 && m562id != 8) {
                return this._currToken.asCharArray();
            }
        } else if (this._tokenIncomplete) {
            this._tokenIncomplete = false;
            _finishString();
        }
        return this._textBuffer.getTextBuffer();
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public int getTextLength() throws IOException {
        if (this._currToken == JsonToken.VALUE_STRING) {
            if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                _finishString();
            }
            return this._textBuffer.size();
        }
        if (this._currToken == JsonToken.FIELD_NAME) {
            return this._parsingContext.getCurrentName().length();
        }
        if (this._currToken == null) {
            return 0;
        }
        if (this._currToken.isNumeric()) {
            return this._textBuffer.size();
        }
        return this._currToken.asCharArray().length;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0016, code lost:
    
        if (r0 != 8) goto L18;
     */
    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int getTextOffset() throws IOException {
        int m562id;
        if (this._currToken != null && (m562id = this._currToken.m562id()) != 5) {
            if (m562id != 6) {
                if (m562id != 7) {
                }
            } else if (this._tokenIncomplete) {
                this._tokenIncomplete = false;
                _finishString();
            }
            return this._textBuffer.getTextOffset();
        }
        return 0;
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase, com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public byte[] getBinaryValue(Base64Variant base64Variant) throws IOException {
        if (this._currToken != JsonToken.VALUE_STRING && (this._currToken != JsonToken.VALUE_EMBEDDED_OBJECT || this._binaryValue == null)) {
            _reportError("Current token (" + this._currToken + ") not VALUE_STRING or VALUE_EMBEDDED_OBJECT, can not access as binary");
        }
        if (this._tokenIncomplete) {
            try {
                this._binaryValue = _decodeBase64(base64Variant);
                this._tokenIncomplete = false;
            } catch (IllegalArgumentException e) {
                throw _constructError("Failed to decode VALUE_STRING as base64 (" + base64Variant + "): " + e.getMessage());
            }
        } else if (this._binaryValue == null) {
            ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
            _decodeBase64(getText(), _getByteArrayBuilder, base64Variant);
            this._binaryValue = _getByteArrayBuilder.toByteArray();
        }
        return this._binaryValue;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int readBinaryValue(Base64Variant base64Variant, OutputStream outputStream) throws IOException {
        if (!this._tokenIncomplete || this._currToken != JsonToken.VALUE_STRING) {
            byte[] binaryValue = getBinaryValue(base64Variant);
            outputStream.write(binaryValue);
            return binaryValue.length;
        }
        byte[] allocBase64Buffer = this._ioContext.allocBase64Buffer();
        try {
            return _readBinary(base64Variant, outputStream, allocBase64Buffer);
        } finally {
            this._ioContext.releaseBase64Buffer(allocBase64Buffer);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x00d2, code lost:
    
        r11._tokenIncomplete = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00d4, code lost:
    
        if (r4 <= 0) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00d6, code lost:
    
        r3 = r3 + r4;
        r13.write(r14, 0, r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00da, code lost:
    
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:?, code lost:
    
        return r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected int _readBinary(Base64Variant base64Variant, OutputStream outputStream, byte[] bArr) throws IOException {
        int i;
        int readUnsignedByte;
        int length = bArr.length - 3;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int readUnsignedByte2 = this._inputData.readUnsignedByte();
            if (readUnsignedByte2 > 32) {
                int decodeBase64Char = base64Variant.decodeBase64Char(readUnsignedByte2);
                if (decodeBase64Char < 0) {
                    if (readUnsignedByte2 == 34) {
                        break;
                    }
                    decodeBase64Char = _decodeBase64Escape(base64Variant, readUnsignedByte2, 0);
                    if (decodeBase64Char < 0) {
                        continue;
                    }
                }
                if (i3 > length) {
                    i2 += i3;
                    outputStream.write(bArr, 0, i3);
                    i3 = 0;
                }
                int readUnsignedByte3 = this._inputData.readUnsignedByte();
                int decodeBase64Char2 = base64Variant.decodeBase64Char(readUnsignedByte3);
                if (decodeBase64Char2 < 0) {
                    decodeBase64Char2 = _decodeBase64Escape(base64Variant, readUnsignedByte3, 1);
                }
                int i4 = (decodeBase64Char << 6) | decodeBase64Char2;
                int readUnsignedByte4 = this._inputData.readUnsignedByte();
                int decodeBase64Char3 = base64Variant.decodeBase64Char(readUnsignedByte4);
                if (decodeBase64Char3 < 0) {
                    if (decodeBase64Char3 != -2) {
                        if (readUnsignedByte4 == 34) {
                            int i5 = i3 + 1;
                            bArr[i3] = (byte) (i4 >> 4);
                            if (base64Variant.usesPadding()) {
                                _handleBase64MissingPadding(base64Variant);
                            }
                            i3 = i5;
                        } else {
                            decodeBase64Char3 = _decodeBase64Escape(base64Variant, readUnsignedByte4, 2);
                        }
                    }
                    if (decodeBase64Char3 == -2) {
                        readUnsignedByte = this._inputData.readUnsignedByte();
                        if (base64Variant.usesPaddingChar(readUnsignedByte) || (readUnsignedByte == 92 && _decodeBase64Escape(base64Variant, readUnsignedByte, 3) == -2)) {
                            i = i3 + 1;
                            bArr[i3] = (byte) (i4 >> 4);
                            i3 = i;
                        }
                    }
                }
                int i6 = (i4 << 6) | decodeBase64Char3;
                int readUnsignedByte5 = this._inputData.readUnsignedByte();
                int decodeBase64Char4 = base64Variant.decodeBase64Char(readUnsignedByte5);
                if (decodeBase64Char4 < 0) {
                    if (decodeBase64Char4 != -2) {
                        if (readUnsignedByte5 == 34) {
                            int i7 = i6 >> 2;
                            int i8 = i3 + 1;
                            bArr[i3] = (byte) (i7 >> 8);
                            i3 = i8 + 1;
                            bArr[i8] = (byte) i7;
                            if (base64Variant.usesPadding()) {
                                _handleBase64MissingPadding(base64Variant);
                            }
                        } else {
                            decodeBase64Char4 = _decodeBase64Escape(base64Variant, readUnsignedByte5, 3);
                        }
                    }
                    if (decodeBase64Char4 == -2) {
                        int i9 = i6 >> 2;
                        int i10 = i3 + 1;
                        bArr[i3] = (byte) (i9 >> 8);
                        i3 = i10 + 1;
                        bArr[i10] = (byte) i9;
                    }
                }
                int i11 = (i6 << 6) | decodeBase64Char4;
                int i12 = i3 + 1;
                bArr[i3] = (byte) (i11 >> 16);
                int i13 = i12 + 1;
                bArr[i12] = (byte) (i11 >> 8);
                i = i13 + 1;
                bArr[i13] = (byte) i11;
                i3 = i;
            }
        }
        throw reportInvalidBase64Char(base64Variant, readUnsignedByte, 3, "expected padding character '" + base64Variant.getPaddingChar() + "'");
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public JsonToken nextToken() throws IOException {
        JsonToken _parseNegNumber;
        if (this._closed) {
            return null;
        }
        if (this._currToken == JsonToken.FIELD_NAME) {
            return _nextAfterName();
        }
        this._numTypesValid = 0;
        if (this._tokenIncomplete) {
            _skipString();
        }
        int _skipWSOrEnd = _skipWSOrEnd();
        if (_skipWSOrEnd < 0) {
            close();
            this._currToken = null;
            return null;
        }
        this._binaryValue = null;
        this._tokenInputRow = this._currInputRow;
        if (_skipWSOrEnd == 93 || _skipWSOrEnd == 125) {
            _closeScope(_skipWSOrEnd);
            return this._currToken;
        }
        if (this._parsingContext.expectComma()) {
            if (_skipWSOrEnd != 44) {
                _reportUnexpectedChar(_skipWSOrEnd, "was expecting comma to separate " + this._parsingContext.typeDesc() + " entries");
            }
            _skipWSOrEnd = _skipWS();
            if ((this._features & FEAT_MASK_TRAILING_COMMA) != 0 && (_skipWSOrEnd == 93 || _skipWSOrEnd == 125)) {
                _closeScope(_skipWSOrEnd);
                return this._currToken;
            }
        }
        if (!this._parsingContext.inObject()) {
            return _nextTokenNotInObject(_skipWSOrEnd);
        }
        this._parsingContext.setCurrentName(_parseName(_skipWSOrEnd));
        this._currToken = JsonToken.FIELD_NAME;
        int _skipColon = _skipColon();
        if (_skipColon == 34) {
            this._tokenIncomplete = true;
            this._nextToken = JsonToken.VALUE_STRING;
            return this._currToken;
        }
        if (_skipColon == 45) {
            _parseNegNumber = _parseNegNumber();
        } else if (_skipColon == 46) {
            _parseNegNumber = _parseFloatThatStartsWithPeriod();
        } else if (_skipColon == 91) {
            _parseNegNumber = JsonToken.START_ARRAY;
        } else if (_skipColon == 102) {
            _matchToken("false", 1);
            _parseNegNumber = JsonToken.VALUE_FALSE;
        } else if (_skipColon == 110) {
            _matchToken("null", 1);
            _parseNegNumber = JsonToken.VALUE_NULL;
        } else if (_skipColon == 116) {
            _matchToken("true", 1);
            _parseNegNumber = JsonToken.VALUE_TRUE;
        } else if (_skipColon != 123) {
            switch (_skipColon) {
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                    _parseNegNumber = _parsePosNumber(_skipColon);
                    break;
                default:
                    _parseNegNumber = _handleUnexpectedValue(_skipColon);
                    break;
            }
        } else {
            _parseNegNumber = JsonToken.START_OBJECT;
        }
        this._nextToken = _parseNegNumber;
        return this._currToken;
    }

    private final JsonToken _nextTokenNotInObject(int i) throws IOException {
        if (i == 34) {
            this._tokenIncomplete = true;
            JsonToken jsonToken = JsonToken.VALUE_STRING;
            this._currToken = jsonToken;
            return jsonToken;
        }
        if (i == 45) {
            JsonToken _parseNegNumber = _parseNegNumber();
            this._currToken = _parseNegNumber;
            return _parseNegNumber;
        }
        if (i == 46) {
            JsonToken _parseFloatThatStartsWithPeriod = _parseFloatThatStartsWithPeriod();
            this._currToken = _parseFloatThatStartsWithPeriod;
            return _parseFloatThatStartsWithPeriod;
        }
        if (i == 91) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            JsonToken jsonToken2 = JsonToken.START_ARRAY;
            this._currToken = jsonToken2;
            return jsonToken2;
        }
        if (i == 102) {
            _matchToken("false", 1);
            JsonToken jsonToken3 = JsonToken.VALUE_FALSE;
            this._currToken = jsonToken3;
            return jsonToken3;
        }
        if (i == 110) {
            _matchToken("null", 1);
            JsonToken jsonToken4 = JsonToken.VALUE_NULL;
            this._currToken = jsonToken4;
            return jsonToken4;
        }
        if (i == 116) {
            _matchToken("true", 1);
            JsonToken jsonToken5 = JsonToken.VALUE_TRUE;
            this._currToken = jsonToken5;
            return jsonToken5;
        }
        if (i == 123) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            JsonToken jsonToken6 = JsonToken.START_OBJECT;
            this._currToken = jsonToken6;
            return jsonToken6;
        }
        switch (i) {
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
                JsonToken _parsePosNumber = _parsePosNumber(i);
                this._currToken = _parsePosNumber;
                return _parsePosNumber;
            default:
                JsonToken _handleUnexpectedValue = _handleUnexpectedValue(i);
                this._currToken = _handleUnexpectedValue;
                return _handleUnexpectedValue;
        }
    }

    private final JsonToken _nextAfterName() {
        this._nameCopied = false;
        JsonToken jsonToken = this._nextToken;
        this._nextToken = null;
        if (jsonToken == JsonToken.START_ARRAY) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        } else if (jsonToken == JsonToken.START_OBJECT) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        }
        this._currToken = jsonToken;
        return jsonToken;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public void finishToken() throws IOException {
        if (this._tokenIncomplete) {
            this._tokenIncomplete = false;
            _finishString();
        }
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public String nextFieldName() throws IOException {
        JsonToken _parseNegNumber;
        this._numTypesValid = 0;
        if (this._currToken == JsonToken.FIELD_NAME) {
            _nextAfterName();
            return null;
        }
        if (this._tokenIncomplete) {
            _skipString();
        }
        int _skipWS = _skipWS();
        this._binaryValue = null;
        this._tokenInputRow = this._currInputRow;
        if (_skipWS == 93 || _skipWS == 125) {
            _closeScope(_skipWS);
            return null;
        }
        if (this._parsingContext.expectComma()) {
            if (_skipWS != 44) {
                _reportUnexpectedChar(_skipWS, "was expecting comma to separate " + this._parsingContext.typeDesc() + " entries");
            }
            _skipWS = _skipWS();
            if ((this._features & FEAT_MASK_TRAILING_COMMA) != 0 && (_skipWS == 93 || _skipWS == 125)) {
                _closeScope(_skipWS);
                return null;
            }
        }
        if (!this._parsingContext.inObject()) {
            _nextTokenNotInObject(_skipWS);
            return null;
        }
        String _parseName = _parseName(_skipWS);
        this._parsingContext.setCurrentName(_parseName);
        this._currToken = JsonToken.FIELD_NAME;
        int _skipColon = _skipColon();
        if (_skipColon == 34) {
            this._tokenIncomplete = true;
            this._nextToken = JsonToken.VALUE_STRING;
            return _parseName;
        }
        if (_skipColon == 45) {
            _parseNegNumber = _parseNegNumber();
        } else {
            if (_skipColon == 46) {
                _parseFloatThatStartsWithPeriod();
            } else if (_skipColon == 91) {
                _parseNegNumber = JsonToken.START_ARRAY;
            } else if (_skipColon == 102) {
                _matchToken("false", 1);
                _parseNegNumber = JsonToken.VALUE_FALSE;
            } else if (_skipColon == 110) {
                _matchToken("null", 1);
                _parseNegNumber = JsonToken.VALUE_NULL;
            } else if (_skipColon == 116) {
                _matchToken("true", 1);
                _parseNegNumber = JsonToken.VALUE_TRUE;
            } else if (_skipColon == 123) {
                _parseNegNumber = JsonToken.START_OBJECT;
            } else {
                switch (_skipColon) {
                    case 48:
                    case 49:
                    case 50:
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                        break;
                    default:
                        _parseNegNumber = _handleUnexpectedValue(_skipColon);
                        break;
                }
            }
            _parseNegNumber = _parsePosNumber(_skipColon);
        }
        this._nextToken = _parseNegNumber;
        return _parseName;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public String nextTextValue() throws IOException {
        if (this._currToken == JsonToken.FIELD_NAME) {
            this._nameCopied = false;
            JsonToken jsonToken = this._nextToken;
            this._nextToken = null;
            this._currToken = jsonToken;
            if (jsonToken == JsonToken.VALUE_STRING) {
                if (this._tokenIncomplete) {
                    this._tokenIncomplete = false;
                    return _finishAndReturnString();
                }
                return this._textBuffer.contentsAsString();
            }
            if (jsonToken == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            } else if (jsonToken == JsonToken.START_OBJECT) {
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            }
            return null;
        }
        if (nextToken() == JsonToken.VALUE_STRING) {
            return getText();
        }
        return null;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int nextIntValue(int i) throws IOException {
        if (this._currToken != JsonToken.FIELD_NAME) {
            return nextToken() == JsonToken.VALUE_NUMBER_INT ? getIntValue() : i;
        }
        this._nameCopied = false;
        JsonToken jsonToken = this._nextToken;
        this._nextToken = null;
        this._currToken = jsonToken;
        if (jsonToken == JsonToken.VALUE_NUMBER_INT) {
            return getIntValue();
        }
        if (jsonToken == JsonToken.START_ARRAY) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        } else if (jsonToken == JsonToken.START_OBJECT) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        }
        return i;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public long nextLongValue(long j) throws IOException {
        if (this._currToken != JsonToken.FIELD_NAME) {
            return nextToken() == JsonToken.VALUE_NUMBER_INT ? getLongValue() : j;
        }
        this._nameCopied = false;
        JsonToken jsonToken = this._nextToken;
        this._nextToken = null;
        this._currToken = jsonToken;
        if (jsonToken == JsonToken.VALUE_NUMBER_INT) {
            return getLongValue();
        }
        if (jsonToken == JsonToken.START_ARRAY) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        } else if (jsonToken == JsonToken.START_OBJECT) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        }
        return j;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public Boolean nextBooleanValue() throws IOException {
        if (this._currToken == JsonToken.FIELD_NAME) {
            this._nameCopied = false;
            JsonToken jsonToken = this._nextToken;
            this._nextToken = null;
            this._currToken = jsonToken;
            if (jsonToken == JsonToken.VALUE_TRUE) {
                return Boolean.TRUE;
            }
            if (jsonToken == JsonToken.VALUE_FALSE) {
                return Boolean.FALSE;
            }
            if (jsonToken == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            } else if (jsonToken == JsonToken.START_OBJECT) {
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            }
            return null;
        }
        JsonToken nextToken = nextToken();
        if (nextToken == JsonToken.VALUE_TRUE) {
            return Boolean.TRUE;
        }
        if (nextToken == JsonToken.VALUE_FALSE) {
            return Boolean.FALSE;
        }
        return null;
    }

    protected final JsonToken _parseFloatThatStartsWithPeriod() throws IOException {
        if (!isEnabled(JsonReadFeature.ALLOW_LEADING_DECIMAL_POINT_FOR_NUMBERS.mappedFeature())) {
            return _handleUnexpectedValue(46);
        }
        return _parseFloat(this._textBuffer.emptyAndGetCurrentSegment(), 0, 46, false, 0);
    }

    protected JsonToken _parsePosNumber(int i) throws IOException {
        int readUnsignedByte;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int i2 = 1;
        if (i == 48) {
            readUnsignedByte = _handleLeadingZeroes();
            if (readUnsignedByte > 57 || readUnsignedByte < 48) {
                emptyAndGetCurrentSegment[0] = '0';
            } else {
                i2 = 0;
            }
        } else {
            emptyAndGetCurrentSegment[0] = (char) i;
            readUnsignedByte = this._inputData.readUnsignedByte();
        }
        int i3 = readUnsignedByte;
        char[] cArr = emptyAndGetCurrentSegment;
        int i4 = i2;
        int i5 = i4;
        while (i3 <= 57 && i3 >= 48) {
            i5++;
            if (i4 >= cArr.length) {
                cArr = this._textBuffer.finishCurrentSegment();
                i4 = 0;
            }
            cArr[i4] = (char) i3;
            i3 = this._inputData.readUnsignedByte();
            i4++;
        }
        if (i3 == 46 || i3 == 101 || i3 == 69) {
            return _parseFloat(cArr, i4, i3, false, i5);
        }
        this._textBuffer.setCurrentLength(i4);
        if (this._parsingContext.inRoot()) {
            _verifyRootSpace();
        } else {
            this._nextByte = i3;
        }
        return resetInt(false, i5);
    }

    protected JsonToken _parseNegNumber() throws IOException {
        int readUnsignedByte;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        emptyAndGetCurrentSegment[0] = Soundex.SILENT_MARKER;
        int readUnsignedByte2 = this._inputData.readUnsignedByte();
        emptyAndGetCurrentSegment[1] = (char) readUnsignedByte2;
        if (readUnsignedByte2 <= 48) {
            if (readUnsignedByte2 == 48) {
                readUnsignedByte = _handleLeadingZeroes();
            } else {
                return _handleInvalidNumberStart(readUnsignedByte2, true);
            }
        } else {
            if (readUnsignedByte2 > 57) {
                return _handleInvalidNumberStart(readUnsignedByte2, true);
            }
            readUnsignedByte = this._inputData.readUnsignedByte();
        }
        int i = 2;
        int i2 = 1;
        while (readUnsignedByte <= 57 && readUnsignedByte >= 48) {
            i2++;
            emptyAndGetCurrentSegment[i] = (char) readUnsignedByte;
            readUnsignedByte = this._inputData.readUnsignedByte();
            i++;
        }
        if (readUnsignedByte == 46 || readUnsignedByte == 101 || readUnsignedByte == 69) {
            return _parseFloat(emptyAndGetCurrentSegment, i, readUnsignedByte, true, i2);
        }
        this._textBuffer.setCurrentLength(i);
        this._nextByte = readUnsignedByte;
        if (this._parsingContext.inRoot()) {
            _verifyRootSpace();
        }
        return resetInt(true, i2);
    }

    private final int _handleLeadingZeroes() throws IOException {
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if (readUnsignedByte >= 48 && readUnsignedByte <= 57) {
            if ((this._features & FEAT_MASK_LEADING_ZEROS) == 0) {
                reportInvalidNumber("Leading zeroes not allowed");
            }
            while (readUnsignedByte == 48) {
                readUnsignedByte = this._inputData.readUnsignedByte();
            }
        }
        return readUnsignedByte;
    }

    private final JsonToken _parseFloat(char[] cArr, int i, int i2, boolean z, int i3) throws IOException {
        int i4;
        int i5;
        int readUnsignedByte;
        if (i2 == 46) {
            cArr[i] = (char) i2;
            char[] cArr2 = cArr;
            int i6 = 0;
            i++;
            while (true) {
                readUnsignedByte = this._inputData.readUnsignedByte();
                if (readUnsignedByte < 48 || readUnsignedByte > 57) {
                    break;
                }
                i6++;
                if (i >= cArr2.length) {
                    cArr2 = this._textBuffer.finishCurrentSegment();
                    i = 0;
                }
                cArr2[i] = (char) readUnsignedByte;
                i++;
            }
            if (i6 == 0) {
                reportUnexpectedNumberChar(readUnsignedByte, "Decimal point not followed by a digit");
            }
            i4 = i6;
            cArr = cArr2;
            i2 = readUnsignedByte;
        } else {
            i4 = 0;
        }
        if (i2 == 101 || i2 == 69) {
            if (i >= cArr.length) {
                cArr = this._textBuffer.finishCurrentSegment();
                i = 0;
            }
            int i7 = i + 1;
            cArr[i] = (char) i2;
            int readUnsignedByte2 = this._inputData.readUnsignedByte();
            if (readUnsignedByte2 == 45 || readUnsignedByte2 == 43) {
                if (i7 >= cArr.length) {
                    cArr = this._textBuffer.finishCurrentSegment();
                    i7 = 0;
                }
                cArr[i7] = (char) readUnsignedByte2;
                readUnsignedByte2 = this._inputData.readUnsignedByte();
                i7++;
            }
            i2 = readUnsignedByte2;
            char[] cArr3 = cArr;
            i5 = 0;
            while (i2 <= 57 && i2 >= 48) {
                i5++;
                if (i7 >= cArr3.length) {
                    cArr3 = this._textBuffer.finishCurrentSegment();
                    i7 = 0;
                }
                cArr3[i7] = (char) i2;
                i2 = this._inputData.readUnsignedByte();
                i7++;
            }
            if (i5 == 0) {
                reportUnexpectedNumberChar(i2, "Exponent indicator not followed by a digit");
            }
            i = i7;
        } else {
            i5 = 0;
        }
        this._nextByte = i2;
        if (this._parsingContext.inRoot()) {
            _verifyRootSpace();
        }
        this._textBuffer.setCurrentLength(i);
        return resetFloat(z, i3, i4, i5);
    }

    private final void _verifyRootSpace() throws IOException {
        int i = this._nextByte;
        if (i <= 32) {
            this._nextByte = -1;
            if (i == 13 || i == 10) {
                this._currInputRow++;
                return;
            }
            return;
        }
        _reportMissingRootWS(i);
    }

    protected final String _parseName(int i) throws IOException {
        if (i != 34) {
            return _handleOddName(i);
        }
        int[] iArr = _icLatin1;
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if (iArr[readUnsignedByte] != 0) {
            return readUnsignedByte == 34 ? "" : parseName(0, readUnsignedByte, 0);
        }
        int readUnsignedByte2 = this._inputData.readUnsignedByte();
        if (iArr[readUnsignedByte2] != 0) {
            if (readUnsignedByte2 == 34) {
                return findName(readUnsignedByte, 1);
            }
            return parseName(readUnsignedByte, readUnsignedByte2, 1);
        }
        int i2 = (readUnsignedByte << 8) | readUnsignedByte2;
        int readUnsignedByte3 = this._inputData.readUnsignedByte();
        if (iArr[readUnsignedByte3] != 0) {
            if (readUnsignedByte3 == 34) {
                return findName(i2, 2);
            }
            return parseName(i2, readUnsignedByte3, 2);
        }
        int i3 = (i2 << 8) | readUnsignedByte3;
        int readUnsignedByte4 = this._inputData.readUnsignedByte();
        if (iArr[readUnsignedByte4] != 0) {
            if (readUnsignedByte4 == 34) {
                return findName(i3, 3);
            }
            return parseName(i3, readUnsignedByte4, 3);
        }
        int i4 = (i3 << 8) | readUnsignedByte4;
        int readUnsignedByte5 = this._inputData.readUnsignedByte();
        if (iArr[readUnsignedByte5] == 0) {
            this._quad1 = i4;
            return _parseMediumName(readUnsignedByte5);
        }
        if (readUnsignedByte5 == 34) {
            return findName(i4, 4);
        }
        return parseName(i4, readUnsignedByte5, 4);
    }

    private final String _parseMediumName(int i) throws IOException {
        int[] iArr = _icLatin1;
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if (iArr[readUnsignedByte] != 0) {
            if (readUnsignedByte == 34) {
                return findName(this._quad1, i, 1);
            }
            return parseName(this._quad1, i, readUnsignedByte, 1);
        }
        int i2 = (i << 8) | readUnsignedByte;
        int readUnsignedByte2 = this._inputData.readUnsignedByte();
        if (iArr[readUnsignedByte2] != 0) {
            if (readUnsignedByte2 == 34) {
                return findName(this._quad1, i2, 2);
            }
            return parseName(this._quad1, i2, readUnsignedByte2, 2);
        }
        int i3 = (i2 << 8) | readUnsignedByte2;
        int readUnsignedByte3 = this._inputData.readUnsignedByte();
        if (iArr[readUnsignedByte3] != 0) {
            if (readUnsignedByte3 == 34) {
                return findName(this._quad1, i3, 3);
            }
            return parseName(this._quad1, i3, readUnsignedByte3, 3);
        }
        int i4 = (i3 << 8) | readUnsignedByte3;
        int readUnsignedByte4 = this._inputData.readUnsignedByte();
        if (iArr[readUnsignedByte4] == 0) {
            return _parseMediumName2(readUnsignedByte4, i4);
        }
        if (readUnsignedByte4 == 34) {
            return findName(this._quad1, i4, 4);
        }
        return parseName(this._quad1, i4, readUnsignedByte4, 4);
    }

    private final String _parseMediumName2(int i, int i2) throws IOException {
        int[] iArr = _icLatin1;
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if (iArr[readUnsignedByte] != 0) {
            if (readUnsignedByte == 34) {
                return findName(this._quad1, i2, i, 1);
            }
            return parseName(this._quad1, i2, i, readUnsignedByte, 1);
        }
        int i3 = (i << 8) | readUnsignedByte;
        int readUnsignedByte2 = this._inputData.readUnsignedByte();
        if (iArr[readUnsignedByte2] != 0) {
            if (readUnsignedByte2 == 34) {
                return findName(this._quad1, i2, i3, 2);
            }
            return parseName(this._quad1, i2, i3, readUnsignedByte2, 2);
        }
        int i4 = (i3 << 8) | readUnsignedByte2;
        int readUnsignedByte3 = this._inputData.readUnsignedByte();
        if (iArr[readUnsignedByte3] != 0) {
            if (readUnsignedByte3 == 34) {
                return findName(this._quad1, i2, i4, 3);
            }
            return parseName(this._quad1, i2, i4, readUnsignedByte3, 3);
        }
        int i5 = (i4 << 8) | readUnsignedByte3;
        int readUnsignedByte4 = this._inputData.readUnsignedByte();
        if (iArr[readUnsignedByte4] == 0) {
            return _parseLongName(readUnsignedByte4, i2, i5);
        }
        if (readUnsignedByte4 == 34) {
            return findName(this._quad1, i2, i5, 4);
        }
        return parseName(this._quad1, i2, i5, readUnsignedByte4, 4);
    }

    private final String _parseLongName(int i, int i2, int i3) throws IOException {
        int[] iArr = this._quadBuffer;
        iArr[0] = this._quad1;
        iArr[1] = i2;
        iArr[2] = i3;
        int[] iArr2 = _icLatin1;
        int i4 = i;
        int i5 = 3;
        while (true) {
            int readUnsignedByte = this._inputData.readUnsignedByte();
            if (iArr2[readUnsignedByte] != 0) {
                if (readUnsignedByte == 34) {
                    return findName(this._quadBuffer, i5, i4, 1);
                }
                return parseEscapedName(this._quadBuffer, i5, i4, readUnsignedByte, 1);
            }
            int i6 = (i4 << 8) | readUnsignedByte;
            int readUnsignedByte2 = this._inputData.readUnsignedByte();
            if (iArr2[readUnsignedByte2] != 0) {
                if (readUnsignedByte2 == 34) {
                    return findName(this._quadBuffer, i5, i6, 2);
                }
                return parseEscapedName(this._quadBuffer, i5, i6, readUnsignedByte2, 2);
            }
            int i7 = (i6 << 8) | readUnsignedByte2;
            int readUnsignedByte3 = this._inputData.readUnsignedByte();
            if (iArr2[readUnsignedByte3] != 0) {
                if (readUnsignedByte3 == 34) {
                    return findName(this._quadBuffer, i5, i7, 3);
                }
                return parseEscapedName(this._quadBuffer, i5, i7, readUnsignedByte3, 3);
            }
            int i8 = (i7 << 8) | readUnsignedByte3;
            int readUnsignedByte4 = this._inputData.readUnsignedByte();
            if (iArr2[readUnsignedByte4] != 0) {
                if (readUnsignedByte4 == 34) {
                    return findName(this._quadBuffer, i5, i8, 4);
                }
                return parseEscapedName(this._quadBuffer, i5, i8, readUnsignedByte4, 4);
            }
            int[] iArr3 = this._quadBuffer;
            if (i5 >= iArr3.length) {
                this._quadBuffer = _growArrayBy(iArr3, i5);
            }
            this._quadBuffer[i5] = i8;
            i5++;
            i4 = readUnsignedByte4;
        }
    }

    private final String parseName(int i, int i2, int i3) throws IOException {
        return parseEscapedName(this._quadBuffer, 0, i, i2, i3);
    }

    private final String parseName(int i, int i2, int i3, int i4) throws IOException {
        int[] iArr = this._quadBuffer;
        iArr[0] = i;
        return parseEscapedName(iArr, 1, i2, i3, i4);
    }

    private final String parseName(int i, int i2, int i3, int i4, int i5) throws IOException {
        int[] iArr = this._quadBuffer;
        iArr[0] = i;
        iArr[1] = i2;
        return parseEscapedName(iArr, 2, i3, i4, i5);
    }

    protected final String parseEscapedName(int[] iArr, int i, int i2, int i3, int i4) throws IOException {
        int[] iArr2 = _icLatin1;
        while (true) {
            if (iArr2[i3] != 0) {
                if (i3 == 34) {
                    break;
                }
                if (i3 != 92) {
                    _throwUnquotedSpace(i3, "name");
                } else {
                    i3 = _decodeEscaped();
                }
                if (i3 > 127) {
                    if (i4 >= 4) {
                        if (i >= iArr.length) {
                            iArr = _growArrayBy(iArr, iArr.length);
                            this._quadBuffer = iArr;
                        }
                        iArr[i] = i2;
                        i++;
                        i2 = 0;
                        i4 = 0;
                    }
                    if (i3 < 2048) {
                        i2 = (i2 << 8) | (i3 >> 6) | 192;
                        i4++;
                    } else {
                        int i5 = (i2 << 8) | (i3 >> 12) | 224;
                        int i6 = i4 + 1;
                        if (i6 >= 4) {
                            if (i >= iArr.length) {
                                iArr = _growArrayBy(iArr, iArr.length);
                                this._quadBuffer = iArr;
                            }
                            iArr[i] = i5;
                            i++;
                            i5 = 0;
                            i6 = 0;
                        }
                        i2 = (i5 << 8) | ((i3 >> 6) & 63) | 128;
                        i4 = i6 + 1;
                    }
                    i3 = (i3 & 63) | 128;
                }
            }
            if (i4 < 4) {
                i4++;
                i2 = (i2 << 8) | i3;
            } else {
                if (i >= iArr.length) {
                    iArr = _growArrayBy(iArr, iArr.length);
                    this._quadBuffer = iArr;
                }
                iArr[i] = i2;
                i2 = i3;
                i++;
                i4 = 1;
            }
            i3 = this._inputData.readUnsignedByte();
        }
        if (i4 > 0) {
            if (i >= iArr.length) {
                iArr = _growArrayBy(iArr, iArr.length);
                this._quadBuffer = iArr;
            }
            iArr[i] = pad(i2, i4);
            i++;
        }
        String findName = this._symbols.findName(iArr, i);
        return findName == null ? addName(iArr, i, i4) : findName;
    }

    protected String _handleOddName(int i) throws IOException {
        if (i == 39 && (this._features & FEAT_MASK_ALLOW_SINGLE_QUOTES) != 0) {
            return _parseAposName();
        }
        if ((this._features & FEAT_MASK_ALLOW_UNQUOTED_NAMES) == 0) {
            _reportUnexpectedChar((char) _decodeCharForError(i), "was expecting double-quote to start field name");
        }
        int[] inputCodeUtf8JsNames = CharTypes.getInputCodeUtf8JsNames();
        if (inputCodeUtf8JsNames[i] != 0) {
            _reportUnexpectedChar(i, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        int[] iArr = this._quadBuffer;
        int i2 = 0;
        int i3 = i;
        int i4 = 0;
        int i5 = 0;
        do {
            if (i2 < 4) {
                i2++;
                i5 = (i5 << 8) | i3;
            } else {
                if (i4 >= iArr.length) {
                    iArr = _growArrayBy(iArr, iArr.length);
                    this._quadBuffer = iArr;
                }
                iArr[i4] = i5;
                i5 = i3;
                i2 = 1;
                i4++;
            }
            i3 = this._inputData.readUnsignedByte();
        } while (inputCodeUtf8JsNames[i3] == 0);
        this._nextByte = i3;
        if (i2 > 0) {
            if (i4 >= iArr.length) {
                int[] _growArrayBy = _growArrayBy(iArr, iArr.length);
                this._quadBuffer = _growArrayBy;
                iArr = _growArrayBy;
            }
            iArr[i4] = i5;
            i4++;
        }
        String findName = this._symbols.findName(iArr, i4);
        return findName == null ? addName(iArr, i4, i2) : findName;
    }

    protected String _parseAposName() throws IOException {
        int i;
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if (readUnsignedByte == 39) {
            return "";
        }
        int[] iArr = this._quadBuffer;
        int[] iArr2 = _icLatin1;
        int[] iArr3 = iArr;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (readUnsignedByte != 39) {
            if (readUnsignedByte != 34 && iArr2[readUnsignedByte] != 0) {
                if (readUnsignedByte != 92) {
                    _throwUnquotedSpace(readUnsignedByte, "name");
                } else {
                    readUnsignedByte = _decodeEscaped();
                }
                if (readUnsignedByte > 127) {
                    if (i2 >= 4) {
                        if (i3 >= iArr3.length) {
                            iArr3 = _growArrayBy(iArr3, iArr3.length);
                            this._quadBuffer = iArr3;
                        }
                        iArr3[i3] = i4;
                        i3++;
                        i2 = 0;
                        i4 = 0;
                    }
                    if (readUnsignedByte < 2048) {
                        i4 = (i4 << 8) | (readUnsignedByte >> 6) | 192;
                        i2++;
                    } else {
                        int i5 = (i4 << 8) | (readUnsignedByte >> 12) | 224;
                        int i6 = i2 + 1;
                        if (i6 >= 4) {
                            if (i3 >= iArr3.length) {
                                int[] _growArrayBy = _growArrayBy(iArr3, iArr3.length);
                                this._quadBuffer = _growArrayBy;
                                iArr3 = _growArrayBy;
                            }
                            iArr3[i3] = i5;
                            i3++;
                            i6 = 0;
                            i5 = 0;
                        }
                        i4 = (i5 << 8) | ((readUnsignedByte >> 6) & 63) | 128;
                        i2 = i6 + 1;
                    }
                    readUnsignedByte = (readUnsignedByte & 63) | 128;
                }
            }
            if (i2 < 4) {
                i2++;
                i4 = readUnsignedByte | (i4 << 8);
            } else {
                if (i3 >= iArr3.length) {
                    iArr3 = _growArrayBy(iArr3, iArr3.length);
                    this._quadBuffer = iArr3;
                }
                iArr3[i3] = i4;
                i4 = readUnsignedByte;
                i3++;
                i2 = 1;
            }
            readUnsignedByte = this._inputData.readUnsignedByte();
        }
        if (i2 > 0) {
            if (i3 >= iArr3.length) {
                int[] _growArrayBy2 = _growArrayBy(iArr3, iArr3.length);
                this._quadBuffer = _growArrayBy2;
                iArr3 = _growArrayBy2;
            }
            i = i3 + 1;
            iArr3[i3] = pad(i4, i2);
        } else {
            i = i3;
        }
        String findName = this._symbols.findName(iArr3, i);
        return findName == null ? addName(iArr3, i, i2) : findName;
    }

    private final String findName(int i, int i2) throws JsonParseException {
        int pad = pad(i, i2);
        String findName = this._symbols.findName(pad);
        if (findName != null) {
            return findName;
        }
        int[] iArr = this._quadBuffer;
        iArr[0] = pad;
        return addName(iArr, 1, i2);
    }

    private final String findName(int i, int i2, int i3) throws JsonParseException {
        int pad = pad(i2, i3);
        String findName = this._symbols.findName(i, pad);
        if (findName != null) {
            return findName;
        }
        int[] iArr = this._quadBuffer;
        iArr[0] = i;
        iArr[1] = pad;
        return addName(iArr, 2, i3);
    }

    private final String findName(int i, int i2, int i3, int i4) throws JsonParseException {
        int pad = pad(i3, i4);
        String findName = this._symbols.findName(i, i2, pad);
        if (findName != null) {
            return findName;
        }
        int[] iArr = this._quadBuffer;
        iArr[0] = i;
        iArr[1] = i2;
        iArr[2] = pad(pad, i4);
        return addName(iArr, 3, i4);
    }

    private final String findName(int[] iArr, int i, int i2, int i3) throws JsonParseException {
        if (i >= iArr.length) {
            iArr = _growArrayBy(iArr, iArr.length);
            this._quadBuffer = iArr;
        }
        int i4 = i + 1;
        iArr[i] = pad(i2, i3);
        String findName = this._symbols.findName(iArr, i4);
        return findName == null ? addName(iArr, i4, i3) : findName;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00e3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final String addName(int[] iArr, int i, int i2) throws JsonParseException {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8 = ((i << 2) - 4) + i2;
        if (i2 < 4) {
            int i9 = i - 1;
            i3 = iArr[i9];
            iArr[i9] = i3 << ((4 - i2) << 3);
        } else {
            i3 = 0;
        }
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int i10 = 0;
        int i11 = 0;
        while (i10 < i8) {
            int i12 = (iArr[i10 >> 2] >> ((3 - (i10 & 3)) << 3)) & 255;
            i10++;
            if (i12 > 127) {
                if ((i12 & 224) == 192) {
                    i5 = i12 & 31;
                    i4 = 1;
                } else if ((i12 & DimensionsKt.HDPI) == 224) {
                    i5 = i12 & 15;
                    i4 = 2;
                } else if ((i12 & GateControllerMsg.ControlCode.Error) == 240) {
                    i5 = i12 & 7;
                    i4 = 3;
                } else {
                    _reportInvalidInitial(i12);
                    i4 = 1;
                    i5 = 1;
                }
                if (i10 + i4 > i8) {
                    _reportInvalidEOF(" in field name", JsonToken.FIELD_NAME);
                }
                int i13 = iArr[i10 >> 2] >> ((3 - (i10 & 3)) << 3);
                i10++;
                if ((i13 & 192) != 128) {
                    _reportInvalidOther(i13);
                }
                int i14 = (i13 & 63) | (i5 << 6);
                if (i4 > 1) {
                    int i15 = iArr[i10 >> 2] >> ((3 - (i10 & 3)) << 3);
                    i10++;
                    if ((i15 & 192) != 128) {
                        _reportInvalidOther(i15);
                    }
                    i6 = (i15 & 63) | (i14 << 6);
                    i7 = 2;
                    if (i4 > 2) {
                        int i16 = iArr[i10 >> 2] >> ((3 - (i10 & 3)) << 3);
                        i10++;
                        if ((i16 & 192) != 128) {
                            _reportInvalidOther(i16 & 255);
                        }
                        i6 = (i6 << 6) | (i16 & 63);
                    }
                    if (i4 <= i7) {
                        int i17 = i6 - 65536;
                        if (i11 >= emptyAndGetCurrentSegment.length) {
                            emptyAndGetCurrentSegment = this._textBuffer.expandCurrentSegment();
                        }
                        emptyAndGetCurrentSegment[i11] = (char) ((i17 >> 10) + GeneratorBase.SURR1_FIRST);
                        i12 = (i17 & 1023) | GeneratorBase.SURR2_FIRST;
                        i11++;
                    } else {
                        i12 = i6;
                    }
                } else {
                    i6 = i14;
                }
                i7 = 2;
                if (i4 <= i7) {
                }
            }
            if (i11 >= emptyAndGetCurrentSegment.length) {
                emptyAndGetCurrentSegment = this._textBuffer.expandCurrentSegment();
            }
            emptyAndGetCurrentSegment[i11] = (char) i12;
            i11++;
        }
        String str = new String(emptyAndGetCurrentSegment, 0, i11);
        if (i2 < 4) {
            iArr[i - 1] = i3;
        }
        return this._symbols.addName(str, iArr, i);
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase
    protected void _finishString() throws IOException {
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int[] iArr = _icUTF8;
        int length = emptyAndGetCurrentSegment.length;
        int i = 0;
        while (true) {
            int readUnsignedByte = this._inputData.readUnsignedByte();
            if (iArr[readUnsignedByte] != 0) {
                if (readUnsignedByte == 34) {
                    this._textBuffer.setCurrentLength(i);
                    return;
                } else {
                    _finishString2(emptyAndGetCurrentSegment, i, readUnsignedByte);
                    return;
                }
            }
            int i2 = i + 1;
            emptyAndGetCurrentSegment[i] = (char) readUnsignedByte;
            if (i2 >= length) {
                _finishString2(emptyAndGetCurrentSegment, i2, this._inputData.readUnsignedByte());
                return;
            }
            i = i2;
        }
    }

    private String _finishAndReturnString() throws IOException {
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int[] iArr = _icUTF8;
        int length = emptyAndGetCurrentSegment.length;
        int i = 0;
        while (true) {
            int readUnsignedByte = this._inputData.readUnsignedByte();
            if (iArr[readUnsignedByte] != 0) {
                if (readUnsignedByte == 34) {
                    return this._textBuffer.setCurrentAndReturn(i);
                }
                _finishString2(emptyAndGetCurrentSegment, i, readUnsignedByte);
                return this._textBuffer.contentsAsString();
            }
            int i2 = i + 1;
            emptyAndGetCurrentSegment[i] = (char) readUnsignedByte;
            if (i2 >= length) {
                _finishString2(emptyAndGetCurrentSegment, i2, this._inputData.readUnsignedByte());
                return this._textBuffer.contentsAsString();
            }
            i = i2;
        }
    }

    private final void _finishString2(char[] cArr, int i, int i2) throws IOException {
        int i3;
        int[] iArr = _icUTF8;
        int length = cArr.length;
        while (true) {
            if (iArr[i2] == 0) {
                if (i >= length) {
                    cArr = this._textBuffer.finishCurrentSegment();
                    length = cArr.length;
                    i = 0;
                }
                i3 = i + 1;
                cArr[i] = (char) i2;
                i2 = this._inputData.readUnsignedByte();
            } else if (i2 != 34) {
                int i4 = iArr[i2];
                if (i4 == 1) {
                    i2 = _decodeEscaped();
                } else if (i4 == 2) {
                    i2 = _decodeUtf8_2(i2);
                } else if (i4 == 3) {
                    i2 = _decodeUtf8_3(i2);
                } else if (i4 == 4) {
                    int _decodeUtf8_4 = _decodeUtf8_4(i2);
                    if (i >= cArr.length) {
                        cArr = this._textBuffer.finishCurrentSegment();
                        length = cArr.length;
                        i = 0;
                    }
                    cArr[i] = (char) (55296 | (_decodeUtf8_4 >> 10));
                    i2 = (_decodeUtf8_4 & 1023) | GeneratorBase.SURR2_FIRST;
                    i++;
                } else if (i2 < 32) {
                    _throwUnquotedSpace(i2, "string value");
                } else {
                    _reportInvalidChar(i2);
                }
                if (i >= cArr.length) {
                    cArr = this._textBuffer.finishCurrentSegment();
                    length = cArr.length;
                    i = 0;
                }
                i3 = i + 1;
                cArr[i] = (char) i2;
                i2 = this._inputData.readUnsignedByte();
            } else {
                this._textBuffer.setCurrentLength(i);
                return;
            }
            i = i3;
        }
    }

    protected void _skipString() throws IOException {
        this._tokenIncomplete = false;
        int[] iArr = _icUTF8;
        while (true) {
            int readUnsignedByte = this._inputData.readUnsignedByte();
            if (iArr[readUnsignedByte] != 0) {
                if (readUnsignedByte == 34) {
                    return;
                }
                int i = iArr[readUnsignedByte];
                if (i == 1) {
                    _decodeEscaped();
                } else if (i == 2) {
                    _skipUtf8_2();
                } else if (i == 3) {
                    _skipUtf8_3();
                } else if (i == 4) {
                    _skipUtf8_4();
                } else if (readUnsignedByte < 32) {
                    _throwUnquotedSpace(readUnsignedByte, "string value");
                } else {
                    _reportInvalidChar(readUnsignedByte);
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x001b, code lost:
    
        if (r4 != 44) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0039, code lost:
    
        if (r3._parsingContext.inRoot() != false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0040, code lost:
    
        if ((r3._features & com.fasterxml.jackson.core.json.UTF8DataInputJsonParser.FEAT_MASK_ALLOW_MISSING) == 0) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0042, code lost:
    
        r3._nextByte = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0046, code lost:
    
        return com.fasterxml.jackson.core.JsonToken.VALUE_NULL;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0030, code lost:
    
        if (r3._parsingContext.inArray() == false) goto L43;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0091  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected JsonToken _handleUnexpectedValue(int i) throws IOException {
        if (i != 39) {
            if (i == 73) {
                _matchToken("Infinity", 1);
                if ((this._features & FEAT_MASK_NON_NUM_NUMBERS) != 0) {
                    return resetAsNaN("Infinity", Double.POSITIVE_INFINITY);
                }
                _reportError("Non-standard token 'Infinity': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
            } else if (i != 78) {
                if (i != 93) {
                    if (i != 125) {
                        if (i == 43) {
                            return _handleInvalidNumberStart(this._inputData.readUnsignedByte(), false);
                        }
                    }
                }
                _reportUnexpectedChar(i, "expected a value");
            } else {
                _matchToken("NaN", 1);
                if ((this._features & FEAT_MASK_NON_NUM_NUMBERS) != 0) {
                    return resetAsNaN("NaN", Double.NaN);
                }
                _reportError("Non-standard token 'NaN': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
            }
            if (Character.isJavaIdentifierStart(i)) {
                _reportInvalidToken(i, "" + ((char) i), _validJsonTokenList());
            }
            _reportUnexpectedChar(i, "expected a valid value " + _validJsonValueList());
            return null;
        }
        if ((this._features & FEAT_MASK_ALLOW_SINGLE_QUOTES) != 0) {
            return _handleApos();
        }
        if (Character.isJavaIdentifierStart(i)) {
        }
        _reportUnexpectedChar(i, "expected a valid value " + _validJsonValueList());
        return null;
    }

    protected JsonToken _handleApos() throws IOException {
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int[] iArr = _icUTF8;
        int i = 0;
        while (true) {
            int length = emptyAndGetCurrentSegment.length;
            if (i >= emptyAndGetCurrentSegment.length) {
                emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                length = emptyAndGetCurrentSegment.length;
                i = 0;
            }
            while (true) {
                int readUnsignedByte = this._inputData.readUnsignedByte();
                if (readUnsignedByte != 39) {
                    if (iArr[readUnsignedByte] == 0) {
                        int i2 = i + 1;
                        emptyAndGetCurrentSegment[i] = (char) readUnsignedByte;
                        i = i2;
                        if (i2 >= length) {
                            break;
                        }
                    } else {
                        int i3 = iArr[readUnsignedByte];
                        if (i3 == 1) {
                            readUnsignedByte = _decodeEscaped();
                        } else if (i3 == 2) {
                            readUnsignedByte = _decodeUtf8_2(readUnsignedByte);
                        } else if (i3 == 3) {
                            readUnsignedByte = _decodeUtf8_3(readUnsignedByte);
                        } else if (i3 == 4) {
                            int _decodeUtf8_4 = _decodeUtf8_4(readUnsignedByte);
                            int i4 = i + 1;
                            emptyAndGetCurrentSegment[i] = (char) (55296 | (_decodeUtf8_4 >> 10));
                            if (i4 >= emptyAndGetCurrentSegment.length) {
                                emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                                i4 = 0;
                            }
                            int i5 = i4;
                            readUnsignedByte = 56320 | (_decodeUtf8_4 & 1023);
                            i = i5;
                        } else {
                            if (readUnsignedByte < 32) {
                                _throwUnquotedSpace(readUnsignedByte, "string value");
                            }
                            _reportInvalidChar(readUnsignedByte);
                        }
                        if (i >= emptyAndGetCurrentSegment.length) {
                            emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                            i = 0;
                        }
                        emptyAndGetCurrentSegment[i] = (char) readUnsignedByte;
                        i++;
                    }
                } else {
                    this._textBuffer.setCurrentLength(i);
                    return JsonToken.VALUE_STRING;
                }
            }
        }
    }

    protected JsonToken _handleInvalidNumberStart(int i, boolean z) throws IOException {
        String str;
        while (i == 73) {
            i = this._inputData.readUnsignedByte();
            if (i != 78) {
                if (i != 110) {
                    break;
                }
                str = z ? "-Infinity" : "+Infinity";
            } else {
                str = z ? "-INF" : "+INF";
            }
            _matchToken(str, 3);
            if ((this._features & FEAT_MASK_NON_NUM_NUMBERS) != 0) {
                return resetAsNaN(str, z ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY);
            }
            _reportError("Non-standard token '" + str + "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
        }
        reportUnexpectedNumberChar(i, "expected digit (0-9) to follow minus sign, for valid numeric value");
        return null;
    }

    protected final void _matchToken(String str, int i) throws IOException {
        int length = str.length();
        do {
            int readUnsignedByte = this._inputData.readUnsignedByte();
            if (readUnsignedByte != str.charAt(i)) {
                _reportInvalidToken(readUnsignedByte, str.substring(0, i));
            }
            i++;
        } while (i < length);
        int readUnsignedByte2 = this._inputData.readUnsignedByte();
        if (readUnsignedByte2 >= 48 && readUnsignedByte2 != 93 && readUnsignedByte2 != 125) {
            _checkMatchEnd(str, i, readUnsignedByte2);
        }
        this._nextByte = readUnsignedByte2;
    }

    private final void _checkMatchEnd(String str, int i, int i2) throws IOException {
        char _decodeCharForError = (char) _decodeCharForError(i2);
        if (Character.isJavaIdentifierPart(_decodeCharForError)) {
            _reportInvalidToken(_decodeCharForError, str.substring(0, i));
        }
    }

    private final int _skipWS() throws IOException {
        int i = this._nextByte;
        if (i < 0) {
            i = this._inputData.readUnsignedByte();
        } else {
            this._nextByte = -1;
        }
        while (i <= 32) {
            if (i == 13 || i == 10) {
                this._currInputRow++;
            }
            i = this._inputData.readUnsignedByte();
        }
        return (i == 47 || i == 35) ? _skipWSComment(i) : i;
    }

    private final int _skipWSOrEnd() throws IOException {
        int i = this._nextByte;
        if (i < 0) {
            try {
                i = this._inputData.readUnsignedByte();
            } catch (EOFException unused) {
                return _eofAsNextChar();
            }
        } else {
            this._nextByte = -1;
        }
        while (i <= 32) {
            if (i == 13 || i == 10) {
                this._currInputRow++;
            }
            try {
                i = this._inputData.readUnsignedByte();
            } catch (EOFException unused2) {
                return _eofAsNextChar();
            }
        }
        return (i == 47 || i == 35) ? _skipWSComment(i) : i;
    }

    private final int _skipWSComment(int i) throws IOException {
        while (true) {
            if (i > 32) {
                if (i == 47) {
                    _skipComment();
                } else if (i != 35 || !_skipYAMLComment()) {
                    break;
                }
            } else if (i == 13 || i == 10) {
                this._currInputRow++;
            }
            i = this._inputData.readUnsignedByte();
        }
        return i;
    }

    private final int _skipColon() throws IOException {
        int i = this._nextByte;
        if (i < 0) {
            i = this._inputData.readUnsignedByte();
        } else {
            this._nextByte = -1;
        }
        if (i == 58) {
            int readUnsignedByte = this._inputData.readUnsignedByte();
            if (readUnsignedByte > 32) {
                return (readUnsignedByte == 47 || readUnsignedByte == 35) ? _skipColon2(readUnsignedByte, true) : readUnsignedByte;
            }
            if ((readUnsignedByte == 32 || readUnsignedByte == 9) && (readUnsignedByte = this._inputData.readUnsignedByte()) > 32) {
                return (readUnsignedByte == 47 || readUnsignedByte == 35) ? _skipColon2(readUnsignedByte, true) : readUnsignedByte;
            }
            return _skipColon2(readUnsignedByte, true);
        }
        if (i == 32 || i == 9) {
            i = this._inputData.readUnsignedByte();
        }
        if (i == 58) {
            int readUnsignedByte2 = this._inputData.readUnsignedByte();
            if (readUnsignedByte2 > 32) {
                return (readUnsignedByte2 == 47 || readUnsignedByte2 == 35) ? _skipColon2(readUnsignedByte2, true) : readUnsignedByte2;
            }
            if ((readUnsignedByte2 == 32 || readUnsignedByte2 == 9) && (readUnsignedByte2 = this._inputData.readUnsignedByte()) > 32) {
                return (readUnsignedByte2 == 47 || readUnsignedByte2 == 35) ? _skipColon2(readUnsignedByte2, true) : readUnsignedByte2;
            }
            return _skipColon2(readUnsignedByte2, true);
        }
        return _skipColon2(i, false);
    }

    private final int _skipColon2(int i, boolean z) throws IOException {
        while (true) {
            if (i > 32) {
                if (i == 47) {
                    _skipComment();
                } else if (i != 35 || !_skipYAMLComment()) {
                    if (z) {
                        return i;
                    }
                    if (i != 58) {
                        _reportUnexpectedChar(i, "was expecting a colon to separate field name and value");
                    }
                    z = true;
                }
            } else if (i == 13 || i == 10) {
                this._currInputRow++;
            }
            i = this._inputData.readUnsignedByte();
        }
    }

    private final void _skipComment() throws IOException {
        if ((this._features & FEAT_MASK_ALLOW_JAVA_COMMENTS) == 0) {
            _reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if (readUnsignedByte == 47) {
            _skipLine();
        } else if (readUnsignedByte == 42) {
            _skipCComment();
        } else {
            _reportUnexpectedChar(readUnsignedByte, "was expecting either '*' or '/' for a comment");
        }
    }

    private final void _skipCComment() throws IOException {
        int[] inputCodeComment = CharTypes.getInputCodeComment();
        int readUnsignedByte = this._inputData.readUnsignedByte();
        while (true) {
            int i = inputCodeComment[readUnsignedByte];
            if (i != 0) {
                if (i == 2) {
                    _skipUtf8_2();
                } else if (i == 3) {
                    _skipUtf8_3();
                } else if (i == 4) {
                    _skipUtf8_4();
                } else if (i == 10 || i == 13) {
                    this._currInputRow++;
                } else if (i == 42) {
                    readUnsignedByte = this._inputData.readUnsignedByte();
                    if (readUnsignedByte == 47) {
                        return;
                    }
                } else {
                    _reportInvalidChar(readUnsignedByte);
                }
            }
            readUnsignedByte = this._inputData.readUnsignedByte();
        }
    }

    private final boolean _skipYAMLComment() throws IOException {
        if ((this._features & FEAT_MASK_ALLOW_YAML_COMMENTS) == 0) {
            return false;
        }
        _skipLine();
        return true;
    }

    private final void _skipLine() throws IOException {
        int[] inputCodeComment = CharTypes.getInputCodeComment();
        while (true) {
            int readUnsignedByte = this._inputData.readUnsignedByte();
            int i = inputCodeComment[readUnsignedByte];
            if (i != 0) {
                if (i == 2) {
                    _skipUtf8_2();
                } else if (i == 3) {
                    _skipUtf8_3();
                } else if (i == 4) {
                    _skipUtf8_4();
                } else if (i == 10 || i == 13) {
                    break;
                } else if (i != 42 && i < 0) {
                    _reportInvalidChar(readUnsignedByte);
                }
            }
        }
        this._currInputRow++;
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase
    protected char _decodeEscaped() throws IOException {
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if (readUnsignedByte == 34 || readUnsignedByte == 47 || readUnsignedByte == 92) {
            return (char) readUnsignedByte;
        }
        if (readUnsignedByte == 98) {
            return '\b';
        }
        if (readUnsignedByte == 102) {
            return '\f';
        }
        if (readUnsignedByte == 110) {
            return '\n';
        }
        if (readUnsignedByte == 114) {
            return '\r';
        }
        if (readUnsignedByte == 116) {
            return '\t';
        }
        if (readUnsignedByte != 117) {
            return _handleUnrecognizedCharacterEscape((char) _decodeCharForError(readUnsignedByte));
        }
        int i = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            int readUnsignedByte2 = this._inputData.readUnsignedByte();
            int charToHex = CharTypes.charToHex(readUnsignedByte2);
            if (charToHex < 0) {
                _reportUnexpectedChar(readUnsignedByte2, "expected a hex-digit for character escape sequence");
            }
            i = (i << 4) | charToHex;
        }
        return (char) i;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0038  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected int _decodeCharForError(int i) throws IOException {
        char c;
        int readUnsignedByte;
        int i2 = i & 255;
        if (i2 <= 127) {
            return i2;
        }
        if ((i2 & 224) != 192) {
            if ((i2 & DimensionsKt.HDPI) == 224) {
                i2 &= 15;
                c = 2;
            } else if ((i2 & GateControllerMsg.ControlCode.Error) == 240) {
                i2 &= 7;
                c = 3;
            } else {
                _reportInvalidInitial(i2 & 255);
            }
            readUnsignedByte = this._inputData.readUnsignedByte();
            if ((readUnsignedByte & 192) != 128) {
                _reportInvalidOther(readUnsignedByte & 255);
            }
            int i3 = (i2 << 6) | (readUnsignedByte & 63);
            if (c > 1) {
                return i3;
            }
            int readUnsignedByte2 = this._inputData.readUnsignedByte();
            if ((readUnsignedByte2 & 192) != 128) {
                _reportInvalidOther(readUnsignedByte2 & 255);
            }
            int i4 = (i3 << 6) | (readUnsignedByte2 & 63);
            if (c <= 2) {
                return i4;
            }
            int readUnsignedByte3 = this._inputData.readUnsignedByte();
            if ((readUnsignedByte3 & 192) != 128) {
                _reportInvalidOther(readUnsignedByte3 & 255);
            }
            return (i4 << 6) | (readUnsignedByte3 & 63);
        }
        i2 &= 31;
        c = 1;
        readUnsignedByte = this._inputData.readUnsignedByte();
        if ((readUnsignedByte & 192) != 128) {
        }
        int i32 = (i2 << 6) | (readUnsignedByte & 63);
        if (c > 1) {
        }
    }

    private final int _decodeUtf8_2(int i) throws IOException {
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if ((readUnsignedByte & 192) != 128) {
            _reportInvalidOther(readUnsignedByte & 255);
        }
        return ((i & 31) << 6) | (readUnsignedByte & 63);
    }

    private final int _decodeUtf8_3(int i) throws IOException {
        int i2 = i & 15;
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if ((readUnsignedByte & 192) != 128) {
            _reportInvalidOther(readUnsignedByte & 255);
        }
        int i3 = (i2 << 6) | (readUnsignedByte & 63);
        int readUnsignedByte2 = this._inputData.readUnsignedByte();
        if ((readUnsignedByte2 & 192) != 128) {
            _reportInvalidOther(readUnsignedByte2 & 255);
        }
        return (i3 << 6) | (readUnsignedByte2 & 63);
    }

    private final int _decodeUtf8_4(int i) throws IOException {
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if ((readUnsignedByte & 192) != 128) {
            _reportInvalidOther(readUnsignedByte & 255);
        }
        int i2 = ((i & 7) << 6) | (readUnsignedByte & 63);
        int readUnsignedByte2 = this._inputData.readUnsignedByte();
        if ((readUnsignedByte2 & 192) != 128) {
            _reportInvalidOther(readUnsignedByte2 & 255);
        }
        int i3 = (i2 << 6) | (readUnsignedByte2 & 63);
        int readUnsignedByte3 = this._inputData.readUnsignedByte();
        if ((readUnsignedByte3 & 192) != 128) {
            _reportInvalidOther(readUnsignedByte3 & 255);
        }
        return ((i3 << 6) | (readUnsignedByte3 & 63)) - 65536;
    }

    private final void _skipUtf8_2() throws IOException {
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if ((readUnsignedByte & 192) != 128) {
            _reportInvalidOther(readUnsignedByte & 255);
        }
    }

    private final void _skipUtf8_3() throws IOException {
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if ((readUnsignedByte & 192) != 128) {
            _reportInvalidOther(readUnsignedByte & 255);
        }
        int readUnsignedByte2 = this._inputData.readUnsignedByte();
        if ((readUnsignedByte2 & 192) != 128) {
            _reportInvalidOther(readUnsignedByte2 & 255);
        }
    }

    private final void _skipUtf8_4() throws IOException {
        int readUnsignedByte = this._inputData.readUnsignedByte();
        if ((readUnsignedByte & 192) != 128) {
            _reportInvalidOther(readUnsignedByte & 255);
        }
        int readUnsignedByte2 = this._inputData.readUnsignedByte();
        if ((readUnsignedByte2 & 192) != 128) {
            _reportInvalidOther(readUnsignedByte2 & 255);
        }
        int readUnsignedByte3 = this._inputData.readUnsignedByte();
        if ((readUnsignedByte3 & 192) != 128) {
            _reportInvalidOther(readUnsignedByte3 & 255);
        }
    }

    protected void _reportInvalidToken(int i, String str) throws IOException {
        _reportInvalidToken(i, str, _validJsonTokenList());
    }

    protected void _reportInvalidToken(int i, String str, String str2) throws IOException {
        StringBuilder sb = new StringBuilder(str);
        while (true) {
            char _decodeCharForError = (char) _decodeCharForError(i);
            if (Character.isJavaIdentifierPart(_decodeCharForError)) {
                sb.append(_decodeCharForError);
                i = this._inputData.readUnsignedByte();
            } else {
                _reportError("Unrecognized token '" + sb.toString() + "': was expecting " + str2);
                return;
            }
        }
    }

    protected void _reportInvalidChar(int i) throws JsonParseException {
        if (i < 32) {
            _throwInvalidSpace(i);
        }
        _reportInvalidInitial(i);
    }

    protected void _reportInvalidInitial(int i) throws JsonParseException {
        _reportError("Invalid UTF-8 start byte 0x" + Integer.toHexString(i));
    }

    private void _reportInvalidOther(int i) throws JsonParseException {
        _reportError("Invalid UTF-8 middle byte 0x" + Integer.toHexString(i));
    }

    private static int[] _growArrayBy(int[] iArr, int i) {
        if (iArr == null) {
            return new int[i];
        }
        return Arrays.copyOf(iArr, iArr.length + i);
    }

    protected final byte[] _decodeBase64(Base64Variant base64Variant) throws IOException {
        int readUnsignedByte;
        ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
        while (true) {
            int readUnsignedByte2 = this._inputData.readUnsignedByte();
            if (readUnsignedByte2 > 32) {
                int decodeBase64Char = base64Variant.decodeBase64Char(readUnsignedByte2);
                if (decodeBase64Char < 0) {
                    if (readUnsignedByte2 == 34) {
                        return _getByteArrayBuilder.toByteArray();
                    }
                    decodeBase64Char = _decodeBase64Escape(base64Variant, readUnsignedByte2, 0);
                    if (decodeBase64Char < 0) {
                        continue;
                    }
                }
                int readUnsignedByte3 = this._inputData.readUnsignedByte();
                int decodeBase64Char2 = base64Variant.decodeBase64Char(readUnsignedByte3);
                if (decodeBase64Char2 < 0) {
                    decodeBase64Char2 = _decodeBase64Escape(base64Variant, readUnsignedByte3, 1);
                }
                int i = (decodeBase64Char << 6) | decodeBase64Char2;
                int readUnsignedByte4 = this._inputData.readUnsignedByte();
                int decodeBase64Char3 = base64Variant.decodeBase64Char(readUnsignedByte4);
                if (decodeBase64Char3 < 0) {
                    if (decodeBase64Char3 != -2) {
                        if (readUnsignedByte4 == 34) {
                            _getByteArrayBuilder.append(i >> 4);
                            if (base64Variant.usesPadding()) {
                                _handleBase64MissingPadding(base64Variant);
                            }
                            return _getByteArrayBuilder.toByteArray();
                        }
                        decodeBase64Char3 = _decodeBase64Escape(base64Variant, readUnsignedByte4, 2);
                    }
                    if (decodeBase64Char3 == -2) {
                        readUnsignedByte = this._inputData.readUnsignedByte();
                        if (base64Variant.usesPaddingChar(readUnsignedByte) || (readUnsignedByte == 92 && _decodeBase64Escape(base64Variant, readUnsignedByte, 3) == -2)) {
                            _getByteArrayBuilder.append(i >> 4);
                        }
                    }
                }
                int i2 = (i << 6) | decodeBase64Char3;
                int readUnsignedByte5 = this._inputData.readUnsignedByte();
                int decodeBase64Char4 = base64Variant.decodeBase64Char(readUnsignedByte5);
                if (decodeBase64Char4 < 0) {
                    if (decodeBase64Char4 != -2) {
                        if (readUnsignedByte5 == 34) {
                            _getByteArrayBuilder.appendTwoBytes(i2 >> 2);
                            if (base64Variant.usesPadding()) {
                                _handleBase64MissingPadding(base64Variant);
                            }
                            return _getByteArrayBuilder.toByteArray();
                        }
                        decodeBase64Char4 = _decodeBase64Escape(base64Variant, readUnsignedByte5, 3);
                    }
                    if (decodeBase64Char4 == -2) {
                        _getByteArrayBuilder.appendTwoBytes(i2 >> 2);
                    }
                }
                _getByteArrayBuilder.appendThreeBytes((i2 << 6) | decodeBase64Char4);
            }
        }
        throw reportInvalidBase64Char(base64Variant, readUnsignedByte, 3, "expected padding character '" + base64Variant.getPaddingChar() + "'");
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase, com.fasterxml.jackson.core.JsonParser
    public JsonLocation getTokenLocation() {
        return new JsonLocation(_getSourceReference(), -1L, -1L, this._tokenInputRow, -1);
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase, com.fasterxml.jackson.core.JsonParser
    public JsonLocation getCurrentLocation() {
        return new JsonLocation(_getSourceReference(), -1L, -1L, this._currInputRow, -1);
    }

    private void _closeScope(int i) throws JsonParseException {
        if (i == 93) {
            if (!this._parsingContext.inArray()) {
                _reportMismatchedEndMarker(i, '}');
            }
            this._parsingContext = this._parsingContext.clearAndGetParent();
            this._currToken = JsonToken.END_ARRAY;
        }
        if (i == 125) {
            if (!this._parsingContext.inObject()) {
                _reportMismatchedEndMarker(i, ']');
            }
            this._parsingContext = this._parsingContext.clearAndGetParent();
            this._currToken = JsonToken.END_OBJECT;
        }
    }
}
