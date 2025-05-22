package com.fasterxml.jackson.core.filter;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.util.JsonParserDelegate;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
public class FilteringParserDelegate extends JsonParserDelegate {
    protected boolean _allowMultipleMatches;
    protected JsonToken _currToken;
    protected TokenFilterContext _exposedContext;
    protected TokenFilterContext _headContext;

    @Deprecated
    protected boolean _includeImmediateParent;
    protected boolean _includePath;
    protected TokenFilter _itemFilter;
    protected JsonToken _lastClearedToken;
    protected int _matchCount;
    protected TokenFilter rootFilter;

    public FilteringParserDelegate(JsonParser jsonParser, TokenFilter tokenFilter, boolean z, boolean z2) {
        super(jsonParser);
        this.rootFilter = tokenFilter;
        this._itemFilter = tokenFilter;
        this._headContext = TokenFilterContext.createRootContext(tokenFilter);
        this._includePath = z;
        this._allowMultipleMatches = z2;
    }

    public TokenFilter getFilter() {
        return this.rootFilter;
    }

    public int getMatchCount() {
        return this._matchCount;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonToken getCurrentToken() {
        return this._currToken;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonToken currentToken() {
        return this._currToken;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public final int getCurrentTokenId() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == null) {
            return 0;
        }
        return jsonToken.m562id();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public final int currentTokenId() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == null) {
            return 0;
        }
        return jsonToken.m562id();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public boolean hasCurrentToken() {
        return this._currToken != null;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public boolean hasTokenId(int i) {
        JsonToken jsonToken = this._currToken;
        return jsonToken == null ? i == 0 : jsonToken.m562id() == i;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public final boolean hasToken(JsonToken jsonToken) {
        return this._currToken == jsonToken;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public boolean isExpectedStartArrayToken() {
        return this._currToken == JsonToken.START_ARRAY;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public boolean isExpectedStartObjectToken() {
        return this._currToken == JsonToken.START_OBJECT;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonLocation getCurrentLocation() {
        return this.delegate.getCurrentLocation();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonStreamContext getParsingContext() {
        return _filterContext();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public String getCurrentName() throws IOException {
        JsonStreamContext _filterContext = _filterContext();
        if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
            JsonStreamContext parent = _filterContext.getParent();
            if (parent == null) {
                return null;
            }
            return parent.getCurrentName();
        }
        return _filterContext.getCurrentName();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public void clearCurrentToken() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != null) {
            this._lastClearedToken = jsonToken;
            this._currToken = null;
        }
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonToken getLastClearedToken() {
        return this._lastClearedToken;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public void overrideCurrentName(String str) {
        throw new UnsupportedOperationException("Can not currently override name during filtering read");
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x0120, code lost:
    
        if (r1 != com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL) goto L98;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x0122, code lost:
    
        r5._headContext = r5._headContext.createChildArrayContext(r1, true);
        r5._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x012c, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x012d, code lost:
    
        if (r1 != null) goto L100;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x012f, code lost:
    
        r5.delegate.skipChildren();
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x0136, code lost:
    
        r1 = r5._headContext.checkValue(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x013c, code lost:
    
        if (r1 != null) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x013e, code lost:
    
        r5.delegate.skipChildren();
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x0147, code lost:
    
        if (r1 == com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL) goto L106;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x0149, code lost:
    
        r1 = r1.filterStartArray();
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x014d, code lost:
    
        r5._itemFilter = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x0151, code lost:
    
        if (r1 != com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL) goto L110;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x0153, code lost:
    
        r5._headContext = r5._headContext.createChildArrayContext(r1, true);
        r5._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x015d, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x015e, code lost:
    
        r5._headContext = r5._headContext.createChildArrayContext(r1, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x0168, code lost:
    
        if (r5._includePath == false) goto L147;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x016a, code lost:
    
        r0 = _nextTokenWithBuffering(r5._headContext);
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x0170, code lost:
    
        if (r0 == null) goto L147;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x0172, code lost:
    
        r5._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x0174, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x0175, code lost:
    
        r1 = r5._headContext.isStartHandled();
        r2 = r5._headContext.getFilter();
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x0181, code lost:
    
        if (r2 == null) goto L121;
     */
    /* JADX WARN: Code restructure failed: missing block: B:124:0x0185, code lost:
    
        if (r2 == com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL) goto L121;
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x0187, code lost:
    
        r2.filterFinishArray();
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x018a, code lost:
    
        r5._headContext = r5._headContext.getParent();
        r5._itemFilter = r5._headContext.getFilter();
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x019a, code lost:
    
        if (r1 == false) goto L147;
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x019c, code lost:
    
        r5._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x019e, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x019f, code lost:
    
        r1 = r5._itemFilter;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x01a3, code lost:
    
        if (r1 != com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL) goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x01a5, code lost:
    
        r5._headContext = r5._headContext.createChildObjectContext(r1, true);
        r5._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x01af, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x01b0, code lost:
    
        if (r1 != null) goto L131;
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x01b2, code lost:
    
        r5.delegate.skipChildren();
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x01b8, code lost:
    
        r1 = r5._headContext.checkValue(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x01be, code lost:
    
        if (r1 != null) goto L134;
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x01c0, code lost:
    
        r5.delegate.skipChildren();
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x01c8, code lost:
    
        if (r1 == com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL) goto L137;
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x01ca, code lost:
    
        r1 = r1.filterStartObject();
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x01ce, code lost:
    
        r5._itemFilter = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x01d2, code lost:
    
        if (r1 != com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL) goto L141;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x01d4, code lost:
    
        r5._headContext = r5._headContext.createChildObjectContext(r1, true);
        r5._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x01de, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x01df, code lost:
    
        r5._headContext = r5._headContext.createChildObjectContext(r1, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x01e9, code lost:
    
        if (r5._includePath == false) goto L147;
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x01eb, code lost:
    
        r0 = _nextTokenWithBuffering(r5._headContext);
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x01f1, code lost:
    
        if (r0 == null) goto L147;
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x01f3, code lost:
    
        r5._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x01f5, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x002a, code lost:
    
        if (r0 != null) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x002c, code lost:
    
        r2 = r0.nextTokenToRead();
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0030, code lost:
    
        if (r2 == null) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0035, code lost:
    
        r2 = r5._headContext;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0037, code lost:
    
        if (r0 != r2) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x004a, code lost:
    
        r0 = r2.findChildOf(r0);
        r5._exposedContext = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0050, code lost:
    
        if (r0 == null) goto L149;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0059, code lost:
    
        throw _constructError("Unexpected problem: chain of filtered context broken");
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0039, code lost:
    
        r5._exposedContext = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x003f, code lost:
    
        if (r0.inArray() == false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0041, code lost:
    
        r0 = r5.delegate.getCurrentToken();
        r5._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0049, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0032, code lost:
    
        r5._currToken = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0034, code lost:
    
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x005a, code lost:
    
        r0 = r5.delegate.nextToken();
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0060, code lost:
    
        if (r0 != null) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0062, code lost:
    
        r5._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0064, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0065, code lost:
    
        r1 = r0.m562id();
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x006b, code lost:
    
        if (r1 == 1) goto L125;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x006e, code lost:
    
        if (r1 == 2) goto L116;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0071, code lost:
    
        if (r1 == 3) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0074, code lost:
    
        if (r1 == 4) goto L116;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0077, code lost:
    
        if (r1 == 5) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0079, code lost:
    
        r1 = r5._itemFilter;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x007d, code lost:
    
        if (r1 != com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x007f, code lost:
    
        r5._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0081, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0082, code lost:
    
        if (r1 == null) goto L147;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0084, code lost:
    
        r1 = r5._headContext.checkValue(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x008c, code lost:
    
        if (r1 == com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x008e, code lost:
    
        if (r1 == null) goto L147;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0096, code lost:
    
        if (r1.includeValue(r5.delegate) == false) goto L147;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x009c, code lost:
    
        if (_verifyAllowedMatches() == false) goto L147;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x009e, code lost:
    
        r5._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00a0, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x01fa, code lost:
    
        return _nextToken2();
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00a1, code lost:
    
        r1 = r5.delegate.getCurrentName();
        r2 = r5._headContext.setFieldName(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00af, code lost:
    
        if (r2 != com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00b1, code lost:
    
        r5._itemFilter = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00b5, code lost:
    
        if (r5._includePath != false) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x00b9, code lost:
    
        if (r5._includeImmediateParent == false) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x00c1, code lost:
    
        if (r5._headContext.isStartHandled() != false) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x00c3, code lost:
    
        r0 = r5._headContext.nextTokenToRead();
        r5._exposedContext = r5._headContext;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x00cd, code lost:
    
        r5._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x00cf, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x00d0, code lost:
    
        if (r2 != null) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x00d2, code lost:
    
        r5.delegate.nextToken();
        r5.delegate.skipChildren();
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x00de, code lost:
    
        r1 = r2.includeProperty(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x00e2, code lost:
    
        if (r1 != null) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x00e4, code lost:
    
        r5.delegate.nextToken();
        r5.delegate.skipChildren();
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x00f0, code lost:
    
        r5._itemFilter = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x00f4, code lost:
    
        if (r1 != com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x00fa, code lost:
    
        if (_verifyAllowedMatches() == false) goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x00fe, code lost:
    
        if (r5._includePath == false) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0100, code lost:
    
        r5._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0102, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0103, code lost:
    
        r5.delegate.nextToken();
        r5.delegate.skipChildren();
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x010f, code lost:
    
        if (r5._includePath == false) goto L147;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0111, code lost:
    
        r0 = _nextTokenWithBuffering(r5._headContext);
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0117, code lost:
    
        if (r0 == null) goto L147;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x0119, code lost:
    
        r5._currToken = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x011b, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x011c, code lost:
    
        r1 = r5._itemFilter;
     */
    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public JsonToken nextToken() throws IOException {
        JsonToken jsonToken;
        if (!this._allowMultipleMatches && (jsonToken = this._currToken) != null && this._exposedContext == null && jsonToken.isScalarValue() && !this._headContext.isStartHandled() && !this._includePath && this._itemFilter == TokenFilter.INCLUDE_ALL) {
            this._currToken = null;
            return null;
        }
        TokenFilterContext tokenFilterContext = this._exposedContext;
    }

    protected final JsonToken _nextToken2() throws IOException {
        TokenFilter checkValue;
        JsonToken _nextTokenWithBuffering;
        JsonToken _nextTokenWithBuffering2;
        JsonToken _nextTokenWithBuffering3;
        while (true) {
            JsonToken nextToken = this.delegate.nextToken();
            if (nextToken == null) {
                this._currToken = nextToken;
                return nextToken;
            }
            int m562id = nextToken.m562id();
            if (m562id == 1) {
                TokenFilter tokenFilter = this._itemFilter;
                if (tokenFilter == TokenFilter.INCLUDE_ALL) {
                    this._headContext = this._headContext.createChildObjectContext(tokenFilter, true);
                    this._currToken = nextToken;
                    return nextToken;
                }
                if (tokenFilter == null) {
                    this.delegate.skipChildren();
                } else {
                    TokenFilter checkValue2 = this._headContext.checkValue(tokenFilter);
                    if (checkValue2 == null) {
                        this.delegate.skipChildren();
                    } else {
                        if (checkValue2 != TokenFilter.INCLUDE_ALL) {
                            checkValue2 = checkValue2.filterStartObject();
                        }
                        this._itemFilter = checkValue2;
                        if (checkValue2 == TokenFilter.INCLUDE_ALL) {
                            this._headContext = this._headContext.createChildObjectContext(checkValue2, true);
                            this._currToken = nextToken;
                            return nextToken;
                        }
                        this._headContext = this._headContext.createChildObjectContext(checkValue2, false);
                        if (this._includePath && (_nextTokenWithBuffering3 = _nextTokenWithBuffering(this._headContext)) != null) {
                            this._currToken = _nextTokenWithBuffering3;
                            return _nextTokenWithBuffering3;
                        }
                    }
                }
            } else {
                if (m562id != 2) {
                    if (m562id == 3) {
                        TokenFilter tokenFilter2 = this._itemFilter;
                        if (tokenFilter2 == TokenFilter.INCLUDE_ALL) {
                            this._headContext = this._headContext.createChildArrayContext(tokenFilter2, true);
                            this._currToken = nextToken;
                            return nextToken;
                        }
                        if (tokenFilter2 == null) {
                            this.delegate.skipChildren();
                        } else {
                            TokenFilter checkValue3 = this._headContext.checkValue(tokenFilter2);
                            if (checkValue3 == null) {
                                this.delegate.skipChildren();
                            } else {
                                if (checkValue3 != TokenFilter.INCLUDE_ALL) {
                                    checkValue3 = checkValue3.filterStartArray();
                                }
                                this._itemFilter = checkValue3;
                                if (checkValue3 == TokenFilter.INCLUDE_ALL) {
                                    this._headContext = this._headContext.createChildArrayContext(checkValue3, true);
                                    this._currToken = nextToken;
                                    return nextToken;
                                }
                                this._headContext = this._headContext.createChildArrayContext(checkValue3, false);
                                if (this._includePath && (_nextTokenWithBuffering2 = _nextTokenWithBuffering(this._headContext)) != null) {
                                    this._currToken = _nextTokenWithBuffering2;
                                    return _nextTokenWithBuffering2;
                                }
                            }
                        }
                    } else if (m562id != 4) {
                        if (m562id == 5) {
                            String currentName = this.delegate.getCurrentName();
                            TokenFilter fieldName = this._headContext.setFieldName(currentName);
                            if (fieldName == TokenFilter.INCLUDE_ALL) {
                                this._itemFilter = fieldName;
                                this._currToken = nextToken;
                                return nextToken;
                            }
                            if (fieldName == null) {
                                this.delegate.nextToken();
                                this.delegate.skipChildren();
                            } else {
                                TokenFilter includeProperty = fieldName.includeProperty(currentName);
                                if (includeProperty == null) {
                                    this.delegate.nextToken();
                                    this.delegate.skipChildren();
                                } else {
                                    this._itemFilter = includeProperty;
                                    if (includeProperty == TokenFilter.INCLUDE_ALL) {
                                        if (_verifyAllowedMatches() && this._includePath) {
                                            this._currToken = nextToken;
                                            return nextToken;
                                        }
                                    } else if (this._includePath && (_nextTokenWithBuffering = _nextTokenWithBuffering(this._headContext)) != null) {
                                        this._currToken = _nextTokenWithBuffering;
                                        return _nextTokenWithBuffering;
                                    }
                                }
                            }
                        } else {
                            TokenFilter tokenFilter3 = this._itemFilter;
                            if (tokenFilter3 == TokenFilter.INCLUDE_ALL) {
                                this._currToken = nextToken;
                                return nextToken;
                            }
                            if (tokenFilter3 != null && ((checkValue = this._headContext.checkValue(tokenFilter3)) == TokenFilter.INCLUDE_ALL || (checkValue != null && checkValue.includeValue(this.delegate)))) {
                                if (_verifyAllowedMatches()) {
                                    this._currToken = nextToken;
                                    return nextToken;
                                }
                            }
                        }
                    }
                }
                boolean isStartHandled = this._headContext.isStartHandled();
                TokenFilter filter = this._headContext.getFilter();
                if (filter != null && filter != TokenFilter.INCLUDE_ALL) {
                    filter.filterFinishArray();
                }
                this._headContext = this._headContext.getParent();
                this._itemFilter = this._headContext.getFilter();
                if (isStartHandled) {
                    this._currToken = nextToken;
                    return nextToken;
                }
            }
        }
    }

    protected final JsonToken _nextTokenWithBuffering(TokenFilterContext tokenFilterContext) throws IOException {
        TokenFilter checkValue;
        while (true) {
            JsonToken nextToken = this.delegate.nextToken();
            if (nextToken == null) {
                return nextToken;
            }
            int m562id = nextToken.m562id();
            boolean z = false;
            if (m562id == 1) {
                TokenFilter tokenFilter = this._itemFilter;
                if (tokenFilter == TokenFilter.INCLUDE_ALL) {
                    this._headContext = this._headContext.createChildObjectContext(tokenFilter, true);
                    return nextToken;
                }
                if (tokenFilter == null) {
                    this.delegate.skipChildren();
                } else {
                    TokenFilter checkValue2 = this._headContext.checkValue(tokenFilter);
                    if (checkValue2 == null) {
                        this.delegate.skipChildren();
                    } else {
                        if (checkValue2 != TokenFilter.INCLUDE_ALL) {
                            checkValue2 = checkValue2.filterStartObject();
                        }
                        this._itemFilter = checkValue2;
                        if (checkValue2 == TokenFilter.INCLUDE_ALL) {
                            this._headContext = this._headContext.createChildObjectContext(checkValue2, true);
                            return _nextBuffered(tokenFilterContext);
                        }
                        this._headContext = this._headContext.createChildObjectContext(checkValue2, false);
                    }
                }
            } else {
                if (m562id != 2) {
                    if (m562id == 3) {
                        TokenFilter checkValue3 = this._headContext.checkValue(this._itemFilter);
                        if (checkValue3 == null) {
                            this.delegate.skipChildren();
                        } else {
                            if (checkValue3 != TokenFilter.INCLUDE_ALL) {
                                checkValue3 = checkValue3.filterStartArray();
                            }
                            this._itemFilter = checkValue3;
                            if (checkValue3 == TokenFilter.INCLUDE_ALL) {
                                this._headContext = this._headContext.createChildArrayContext(checkValue3, true);
                                return _nextBuffered(tokenFilterContext);
                            }
                            this._headContext = this._headContext.createChildArrayContext(checkValue3, false);
                        }
                    } else if (m562id != 4) {
                        if (m562id == 5) {
                            String currentName = this.delegate.getCurrentName();
                            TokenFilter fieldName = this._headContext.setFieldName(currentName);
                            if (fieldName == TokenFilter.INCLUDE_ALL) {
                                this._itemFilter = fieldName;
                                return _nextBuffered(tokenFilterContext);
                            }
                            if (fieldName == null) {
                                this.delegate.nextToken();
                                this.delegate.skipChildren();
                            } else {
                                TokenFilter includeProperty = fieldName.includeProperty(currentName);
                                if (includeProperty == null) {
                                    this.delegate.nextToken();
                                    this.delegate.skipChildren();
                                } else {
                                    this._itemFilter = includeProperty;
                                    if (includeProperty != TokenFilter.INCLUDE_ALL) {
                                        continue;
                                    } else {
                                        if (_verifyAllowedMatches()) {
                                            return _nextBuffered(tokenFilterContext);
                                        }
                                        this._itemFilter = this._headContext.setFieldName(currentName);
                                    }
                                }
                            }
                        } else {
                            TokenFilter tokenFilter2 = this._itemFilter;
                            if (tokenFilter2 == TokenFilter.INCLUDE_ALL) {
                                return _nextBuffered(tokenFilterContext);
                            }
                            if (tokenFilter2 != null && ((checkValue = this._headContext.checkValue(tokenFilter2)) == TokenFilter.INCLUDE_ALL || (checkValue != null && checkValue.includeValue(this.delegate)))) {
                                if (_verifyAllowedMatches()) {
                                    return _nextBuffered(tokenFilterContext);
                                }
                            }
                        }
                    }
                }
                TokenFilter filter = this._headContext.getFilter();
                if (filter != null && filter != TokenFilter.INCLUDE_ALL) {
                    filter.filterFinishArray();
                }
                if ((this._headContext == tokenFilterContext) && this._headContext.isStartHandled()) {
                    z = true;
                }
                this._headContext = this._headContext.getParent();
                this._itemFilter = this._headContext.getFilter();
                if (z) {
                    return nextToken;
                }
            }
        }
    }

    private JsonToken _nextBuffered(TokenFilterContext tokenFilterContext) throws IOException {
        this._exposedContext = tokenFilterContext;
        JsonToken nextTokenToRead = tokenFilterContext.nextTokenToRead();
        if (nextTokenToRead != null) {
            return nextTokenToRead;
        }
        while (tokenFilterContext != this._headContext) {
            tokenFilterContext = this._exposedContext.findChildOf(tokenFilterContext);
            this._exposedContext = tokenFilterContext;
            if (tokenFilterContext == null) {
                throw _constructError("Unexpected problem: chain of filtered context broken");
            }
            JsonToken nextTokenToRead2 = this._exposedContext.nextTokenToRead();
            if (nextTokenToRead2 != null) {
                return nextTokenToRead2;
            }
        }
        throw _constructError("Internal error: failed to locate expected buffered tokens");
    }

    private final boolean _verifyAllowedMatches() throws IOException {
        if (this._matchCount != 0 && !this._allowMultipleMatches) {
            return false;
        }
        this._matchCount++;
        return true;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonToken nextValue() throws IOException {
        JsonToken nextToken = nextToken();
        return nextToken == JsonToken.FIELD_NAME ? nextToken() : nextToken;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonParser skipChildren() throws IOException {
        if (this._currToken != JsonToken.START_OBJECT && this._currToken != JsonToken.START_ARRAY) {
            return this;
        }
        int i = 1;
        while (true) {
            JsonToken nextToken = nextToken();
            if (nextToken == null) {
                return this;
            }
            if (nextToken.isStructStart()) {
                i++;
            } else if (nextToken.isStructEnd() && i - 1 == 0) {
                return this;
            }
        }
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public String getText() throws IOException {
        return this.delegate.getText();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public boolean hasTextCharacters() {
        return this.delegate.hasTextCharacters();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public char[] getTextCharacters() throws IOException {
        return this.delegate.getTextCharacters();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public int getTextLength() throws IOException {
        return this.delegate.getTextLength();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public int getTextOffset() throws IOException {
        return this.delegate.getTextOffset();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public BigInteger getBigIntegerValue() throws IOException {
        return this.delegate.getBigIntegerValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public boolean getBooleanValue() throws IOException {
        return this.delegate.getBooleanValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public byte getByteValue() throws IOException {
        return this.delegate.getByteValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public short getShortValue() throws IOException {
        return this.delegate.getShortValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public BigDecimal getDecimalValue() throws IOException {
        return this.delegate.getDecimalValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public double getDoubleValue() throws IOException {
        return this.delegate.getDoubleValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public float getFloatValue() throws IOException {
        return this.delegate.getFloatValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public int getIntValue() throws IOException {
        return this.delegate.getIntValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public long getLongValue() throws IOException {
        return this.delegate.getLongValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonParser.NumberType getNumberType() throws IOException {
        return this.delegate.getNumberType();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public Number getNumberValue() throws IOException {
        return this.delegate.getNumberValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public int getValueAsInt() throws IOException {
        return this.delegate.getValueAsInt();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public int getValueAsInt(int i) throws IOException {
        return this.delegate.getValueAsInt(i);
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public long getValueAsLong() throws IOException {
        return this.delegate.getValueAsLong();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public long getValueAsLong(long j) throws IOException {
        return this.delegate.getValueAsLong(j);
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public double getValueAsDouble() throws IOException {
        return this.delegate.getValueAsDouble();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public double getValueAsDouble(double d) throws IOException {
        return this.delegate.getValueAsDouble(d);
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public boolean getValueAsBoolean() throws IOException {
        return this.delegate.getValueAsBoolean();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public boolean getValueAsBoolean(boolean z) throws IOException {
        return this.delegate.getValueAsBoolean(z);
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public String getValueAsString() throws IOException {
        return this.delegate.getValueAsString();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public String getValueAsString(String str) throws IOException {
        return this.delegate.getValueAsString(str);
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public Object getEmbeddedObject() throws IOException {
        return this.delegate.getEmbeddedObject();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public byte[] getBinaryValue(Base64Variant base64Variant) throws IOException {
        return this.delegate.getBinaryValue(base64Variant);
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public int readBinaryValue(Base64Variant base64Variant, OutputStream outputStream) throws IOException {
        return this.delegate.readBinaryValue(base64Variant, outputStream);
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonLocation getTokenLocation() {
        return this.delegate.getTokenLocation();
    }

    protected JsonStreamContext _filterContext() {
        TokenFilterContext tokenFilterContext = this._exposedContext;
        return tokenFilterContext != null ? tokenFilterContext : this._headContext;
    }
}
