package org.jetbrains.anko.p093db;

import android.database.sqlite.SQLiteException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function10;
import kotlin.jvm.functions.Function11;
import kotlin.jvm.functions.Function12;
import kotlin.jvm.functions.Function13;
import kotlin.jvm.functions.Function14;
import kotlin.jvm.functions.Function15;
import kotlin.jvm.functions.Function16;
import kotlin.jvm.functions.Function17;
import kotlin.jvm.functions.Function18;
import kotlin.jvm.functions.Function19;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function20;
import kotlin.jvm.functions.Function21;
import kotlin.jvm.functions.Function22;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.functions.Function9;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* compiled from: SqlParserHelpers.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u008e\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a,\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u00022\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00020\u0005\u001a8\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0006\"\u0004\b\u0002\u0010\u00022\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u00020\u0007\u001aD\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0006\"\u0004\b\u0002\u0010\b\"\u0004\b\u0003\u0010\u00022\u001e\u0010\u0004\u001a\u001a\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\u00020\t\u001aP\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0006\"\u0004\b\u0002\u0010\b\"\u0004\b\u0003\u0010\n\"\u0004\b\u0004\u0010\u00022$\u0010\u0004\u001a \u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u00020\u000b\u001a\\\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0006\"\u0004\b\u0002\u0010\b\"\u0004\b\u0003\u0010\n\"\u0004\b\u0004\u0010\f\"\u0004\b\u0005\u0010\u00022*\u0010\u0004\u001a&\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u00020\r\u001ah\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0006\"\u0004\b\u0002\u0010\b\"\u0004\b\u0003\u0010\n\"\u0004\b\u0004\u0010\f\"\u0004\b\u0005\u0010\u000e\"\u0004\b\u0006\u0010\u000220\u0010\u0004\u001a,\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u0002H\u00020\u000f\u001at\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0006\"\u0004\b\u0002\u0010\b\"\u0004\b\u0003\u0010\n\"\u0004\b\u0004\u0010\f\"\u0004\b\u0005\u0010\u000e\"\u0004\b\u0006\u0010\u0010\"\u0004\b\u0007\u0010\u000226\u0010\u0004\u001a2\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u00020\u0011\u001a\u0080\u0001\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0006\"\u0004\b\u0002\u0010\b\"\u0004\b\u0003\u0010\n\"\u0004\b\u0004\u0010\f\"\u0004\b\u0005\u0010\u000e\"\u0004\b\u0006\u0010\u0010\"\u0004\b\u0007\u0010\u0012\"\u0004\b\b\u0010\u00022<\u0010\u0004\u001a8\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00020\u0013\u001a\u008c\u0001\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0006\"\u0004\b\u0002\u0010\b\"\u0004\b\u0003\u0010\n\"\u0004\b\u0004\u0010\f\"\u0004\b\u0005\u0010\u000e\"\u0004\b\u0006\u0010\u0010\"\u0004\b\u0007\u0010\u0012\"\u0004\b\b\u0010\u0014\"\u0004\b\t\u0010\u00022B\u0010\u0004\u001a>\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\u00020\u0015\u001a\u0098\u0001\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0006\"\u0004\b\u0002\u0010\b\"\u0004\b\u0003\u0010\n\"\u0004\b\u0004\u0010\f\"\u0004\b\u0005\u0010\u000e\"\u0004\b\u0006\u0010\u0010\"\u0004\b\u0007\u0010\u0012\"\u0004\b\b\u0010\u0014\"\u0004\b\t\u0010\u0016\"\u0004\b\n\u0010\u00022H\u0010\u0004\u001aD\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\u0016\u0012\u0004\u0012\u0002H\u00020\u0017\u001a¤\u0001\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0006\"\u0004\b\u0002\u0010\b\"\u0004\b\u0003\u0010\n\"\u0004\b\u0004\u0010\f\"\u0004\b\u0005\u0010\u000e\"\u0004\b\u0006\u0010\u0010\"\u0004\b\u0007\u0010\u0012\"\u0004\b\b\u0010\u0014\"\u0004\b\t\u0010\u0016\"\u0004\b\n\u0010\u0018\"\u0004\b\u000b\u0010\u00022N\u0010\u0004\u001aJ\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\u0016\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u00020\u0019\u001a°\u0001\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0006\"\u0004\b\u0002\u0010\b\"\u0004\b\u0003\u0010\n\"\u0004\b\u0004\u0010\f\"\u0004\b\u0005\u0010\u000e\"\u0004\b\u0006\u0010\u0010\"\u0004\b\u0007\u0010\u0012\"\u0004\b\b\u0010\u0014\"\u0004\b\t\u0010\u0016\"\u0004\b\n\u0010\u0018\"\u0004\b\u000b\u0010\u001a\"\u0004\b\f\u0010\u00022T\u0010\u0004\u001aP\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\u0016\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001a\u0012\u0004\u0012\u0002H\u00020\u001b\u001a¼\u0001\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0006\"\u0004\b\u0002\u0010\b\"\u0004\b\u0003\u0010\n\"\u0004\b\u0004\u0010\f\"\u0004\b\u0005\u0010\u000e\"\u0004\b\u0006\u0010\u0010\"\u0004\b\u0007\u0010\u0012\"\u0004\b\b\u0010\u0014\"\u0004\b\t\u0010\u0016\"\u0004\b\n\u0010\u0018\"\u0004\b\u000b\u0010\u001a\"\u0004\b\f\u0010\u001c\"\u0004\b\r\u0010\u00022Z\u0010\u0004\u001aV\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\u0016\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001a\u0012\u0004\u0012\u0002H\u001c\u0012\u0004\u0012\u0002H\u00020\u001d\u001aÈ\u0001\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0006\"\u0004\b\u0002\u0010\b\"\u0004\b\u0003\u0010\n\"\u0004\b\u0004\u0010\f\"\u0004\b\u0005\u0010\u000e\"\u0004\b\u0006\u0010\u0010\"\u0004\b\u0007\u0010\u0012\"\u0004\b\b\u0010\u0014\"\u0004\b\t\u0010\u0016\"\u0004\b\n\u0010\u0018\"\u0004\b\u000b\u0010\u001a\"\u0004\b\f\u0010\u001c\"\u0004\b\r\u0010\u001e\"\u0004\b\u000e\u0010\u00022`\u0010\u0004\u001a\\\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\u0016\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001a\u0012\u0004\u0012\u0002H\u001c\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H\u00020\u001f\u001aÔ\u0001\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0006\"\u0004\b\u0002\u0010\b\"\u0004\b\u0003\u0010\n\"\u0004\b\u0004\u0010\f\"\u0004\b\u0005\u0010\u000e\"\u0004\b\u0006\u0010\u0010\"\u0004\b\u0007\u0010\u0012\"\u0004\b\b\u0010\u0014\"\u0004\b\t\u0010\u0016\"\u0004\b\n\u0010\u0018\"\u0004\b\u000b\u0010\u001a\"\u0004\b\f\u0010\u001c\"\u0004\b\r\u0010\u001e\"\u0004\b\u000e\u0010 \"\u0004\b\u000f\u0010\u00022f\u0010\u0004\u001ab\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\u0016\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001a\u0012\u0004\u0012\u0002H\u001c\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H \u0012\u0004\u0012\u0002H\u00020!\u001aà\u0001\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0006\"\u0004\b\u0002\u0010\b\"\u0004\b\u0003\u0010\n\"\u0004\b\u0004\u0010\f\"\u0004\b\u0005\u0010\u000e\"\u0004\b\u0006\u0010\u0010\"\u0004\b\u0007\u0010\u0012\"\u0004\b\b\u0010\u0014\"\u0004\b\t\u0010\u0016\"\u0004\b\n\u0010\u0018\"\u0004\b\u000b\u0010\u001a\"\u0004\b\f\u0010\u001c\"\u0004\b\r\u0010\u001e\"\u0004\b\u000e\u0010 \"\u0004\b\u000f\u0010\"\"\u0004\b\u0010\u0010\u00022l\u0010\u0004\u001ah\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\u0016\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001a\u0012\u0004\u0012\u0002H\u001c\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H \u0012\u0004\u0012\u0002H\"\u0012\u0004\u0012\u0002H\u00020#\u001aì\u0001\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0006\"\u0004\b\u0002\u0010\b\"\u0004\b\u0003\u0010\n\"\u0004\b\u0004\u0010\f\"\u0004\b\u0005\u0010\u000e\"\u0004\b\u0006\u0010\u0010\"\u0004\b\u0007\u0010\u0012\"\u0004\b\b\u0010\u0014\"\u0004\b\t\u0010\u0016\"\u0004\b\n\u0010\u0018\"\u0004\b\u000b\u0010\u001a\"\u0004\b\f\u0010\u001c\"\u0004\b\r\u0010\u001e\"\u0004\b\u000e\u0010 \"\u0004\b\u000f\u0010\"\"\u0004\b\u0010\u0010$\"\u0004\b\u0011\u0010\u00022r\u0010\u0004\u001an\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\u0016\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001a\u0012\u0004\u0012\u0002H\u001c\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H \u0012\u0004\u0012\u0002H\"\u0012\u0004\u0012\u0002H$\u0012\u0004\u0012\u0002H\u00020%\u001aø\u0001\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0006\"\u0004\b\u0002\u0010\b\"\u0004\b\u0003\u0010\n\"\u0004\b\u0004\u0010\f\"\u0004\b\u0005\u0010\u000e\"\u0004\b\u0006\u0010\u0010\"\u0004\b\u0007\u0010\u0012\"\u0004\b\b\u0010\u0014\"\u0004\b\t\u0010\u0016\"\u0004\b\n\u0010\u0018\"\u0004\b\u000b\u0010\u001a\"\u0004\b\f\u0010\u001c\"\u0004\b\r\u0010\u001e\"\u0004\b\u000e\u0010 \"\u0004\b\u000f\u0010\"\"\u0004\b\u0010\u0010$\"\u0004\b\u0011\u0010&\"\u0004\b\u0012\u0010\u00022x\u0010\u0004\u001at\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\u0016\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001a\u0012\u0004\u0012\u0002H\u001c\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H \u0012\u0004\u0012\u0002H\"\u0012\u0004\u0012\u0002H$\u0012\u0004\u0012\u0002H&\u0012\u0004\u0012\u0002H\u00020'\u001a\u0084\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0006\"\u0004\b\u0002\u0010\b\"\u0004\b\u0003\u0010\n\"\u0004\b\u0004\u0010\f\"\u0004\b\u0005\u0010\u000e\"\u0004\b\u0006\u0010\u0010\"\u0004\b\u0007\u0010\u0012\"\u0004\b\b\u0010\u0014\"\u0004\b\t\u0010\u0016\"\u0004\b\n\u0010\u0018\"\u0004\b\u000b\u0010\u001a\"\u0004\b\f\u0010\u001c\"\u0004\b\r\u0010\u001e\"\u0004\b\u000e\u0010 \"\u0004\b\u000f\u0010\"\"\u0004\b\u0010\u0010$\"\u0004\b\u0011\u0010&\"\u0004\b\u0012\u0010(\"\u0004\b\u0013\u0010\u00022~\u0010\u0004\u001az\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\u0016\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001a\u0012\u0004\u0012\u0002H\u001c\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H \u0012\u0004\u0012\u0002H\"\u0012\u0004\u0012\u0002H$\u0012\u0004\u0012\u0002H&\u0012\u0004\u0012\u0002H(\u0012\u0004\u0012\u0002H\u00020)\u001a\u0092\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0006\"\u0004\b\u0002\u0010\b\"\u0004\b\u0003\u0010\n\"\u0004\b\u0004\u0010\f\"\u0004\b\u0005\u0010\u000e\"\u0004\b\u0006\u0010\u0010\"\u0004\b\u0007\u0010\u0012\"\u0004\b\b\u0010\u0014\"\u0004\b\t\u0010\u0016\"\u0004\b\n\u0010\u0018\"\u0004\b\u000b\u0010\u001a\"\u0004\b\f\u0010\u001c\"\u0004\b\r\u0010\u001e\"\u0004\b\u000e\u0010 \"\u0004\b\u000f\u0010\"\"\u0004\b\u0010\u0010$\"\u0004\b\u0011\u0010&\"\u0004\b\u0012\u0010(\"\u0004\b\u0013\u0010*\"\u0004\b\u0014\u0010\u00022\u0085\u0001\u0010\u0004\u001a\u0080\u0001\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\u0016\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001a\u0012\u0004\u0012\u0002H\u001c\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H \u0012\u0004\u0012\u0002H\"\u0012\u0004\u0012\u0002H$\u0012\u0004\u0012\u0002H&\u0012\u0004\u0012\u0002H(\u0012\u0004\u0012\u0002H*\u0012\u0004\u0012\u0002H\u00020+\u001a\u009e\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0006\"\u0004\b\u0002\u0010\b\"\u0004\b\u0003\u0010\n\"\u0004\b\u0004\u0010\f\"\u0004\b\u0005\u0010\u000e\"\u0004\b\u0006\u0010\u0010\"\u0004\b\u0007\u0010\u0012\"\u0004\b\b\u0010\u0014\"\u0004\b\t\u0010\u0016\"\u0004\b\n\u0010\u0018\"\u0004\b\u000b\u0010\u001a\"\u0004\b\f\u0010\u001c\"\u0004\b\r\u0010\u001e\"\u0004\b\u000e\u0010 \"\u0004\b\u000f\u0010\"\"\u0004\b\u0010\u0010$\"\u0004\b\u0011\u0010&\"\u0004\b\u0012\u0010(\"\u0004\b\u0013\u0010*\"\u0004\b\u0014\u0010,\"\u0004\b\u0015\u0010\u00022\u008b\u0001\u0010\u0004\u001a\u0086\u0001\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\u0016\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001a\u0012\u0004\u0012\u0002H\u001c\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H \u0012\u0004\u0012\u0002H\"\u0012\u0004\u0012\u0002H$\u0012\u0004\u0012\u0002H&\u0012\u0004\u0012\u0002H(\u0012\u0004\u0012\u0002H*\u0012\u0004\u0012\u0002H,\u0012\u0004\u0012\u0002H\u00020-\u001aª\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\u0006\"\u0004\b\u0002\u0010\b\"\u0004\b\u0003\u0010\n\"\u0004\b\u0004\u0010\f\"\u0004\b\u0005\u0010\u000e\"\u0004\b\u0006\u0010\u0010\"\u0004\b\u0007\u0010\u0012\"\u0004\b\b\u0010\u0014\"\u0004\b\t\u0010\u0016\"\u0004\b\n\u0010\u0018\"\u0004\b\u000b\u0010\u001a\"\u0004\b\f\u0010\u001c\"\u0004\b\r\u0010\u001e\"\u0004\b\u000e\u0010 \"\u0004\b\u000f\u0010\"\"\u0004\b\u0010\u0010$\"\u0004\b\u0011\u0010&\"\u0004\b\u0012\u0010(\"\u0004\b\u0013\u0010*\"\u0004\b\u0014\u0010,\"\u0004\b\u0015\u0010.\"\u0004\b\u0016\u0010\u00022\u0091\u0001\u0010\u0004\u001a\u008c\u0001\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u0002H\u0016\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001a\u0012\u0004\u0012\u0002H\u001c\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H \u0012\u0004\u0012\u0002H\"\u0012\u0004\u0012\u0002H$\u0012\u0004\u0012\u0002H&\u0012\u0004\u0012\u0002H(\u0012\u0004\u0012\u0002H*\u0012\u0004\u0012\u0002H,\u0012\u0004\u0012\u0002H.\u0012\u0004\u0012\u0002H\u00020/¨\u00060"}, m3961d2 = {"rowParser", "Lorg/jetbrains/anko/db/RowParser;", "R", "T1", "parser", "Lkotlin/Function1;", "T2", "Lkotlin/Function2;", "T3", "Lkotlin/Function3;", "T4", "Lkotlin/Function4;", "T5", "Lkotlin/Function5;", "T6", "Lkotlin/Function6;", "T7", "Lkotlin/Function7;", "T8", "Lkotlin/Function8;", "T9", "Lkotlin/Function9;", "T10", "Lkotlin/Function10;", "T11", "Lkotlin/Function11;", "T12", "Lkotlin/Function12;", "T13", "Lkotlin/Function13;", "T14", "Lkotlin/Function14;", "T15", "Lkotlin/Function15;", "T16", "Lkotlin/Function16;", "T17", "Lkotlin/Function17;", "T18", "Lkotlin/Function18;", "T19", "Lkotlin/Function19;", "T20", "Lkotlin/Function20;", "T21", "Lkotlin/Function21;", "T22", "Lkotlin/Function22;", "anko-sqlite_release"}, m3962k = 5, m3963mv = {1, 1, 13}, m3966xs = "org/jetbrains/anko/db/SQLiteParserHelpersKt")
/* loaded from: classes9.dex */
final /* synthetic */ class SQLiteParserHelpersKt__SqlParserHelpersKt {
    public static final <T1, R> RowParser<R> rowParser(final Function1<? super T1, ? extends R> parser) {
        Intrinsics.checkParameterIsNotNull(parser, "parser");
        return new RowParser<R>() { // from class: org.jetbrains.anko.db.SQLiteParserHelpersKt__SqlParserHelpersKt$rowParser$1
            @Override // org.jetbrains.anko.p093db.RowParser
            public R parseRow(Object[] columns) {
                Intrinsics.checkParameterIsNotNull(columns, "columns");
                if (columns.length != 1) {
                    throw new SQLiteException("Invalid row: 1 column required");
                }
                return (R) Function1.this.invoke(columns[0]);
            }
        };
    }

    public static final <T1, T2, R> RowParser<R> rowParser(final Function2<? super T1, ? super T2, ? extends R> parser) {
        Intrinsics.checkParameterIsNotNull(parser, "parser");
        return new RowParser<R>() { // from class: org.jetbrains.anko.db.SQLiteParserHelpersKt__SqlParserHelpersKt$rowParser$2
            @Override // org.jetbrains.anko.p093db.RowParser
            public R parseRow(Object[] columns) {
                Intrinsics.checkParameterIsNotNull(columns, "columns");
                if (columns.length != 2) {
                    throw new SQLiteException("Invalid row: 2 columns required");
                }
                return (R) Function2.this.invoke(columns[0], columns[1]);
            }
        };
    }

    public static final <T1, T2, T3, R> RowParser<R> rowParser(final Function3<? super T1, ? super T2, ? super T3, ? extends R> parser) {
        Intrinsics.checkParameterIsNotNull(parser, "parser");
        return new RowParser<R>() { // from class: org.jetbrains.anko.db.SQLiteParserHelpersKt__SqlParserHelpersKt$rowParser$3
            @Override // org.jetbrains.anko.p093db.RowParser
            public R parseRow(Object[] columns) {
                Intrinsics.checkParameterIsNotNull(columns, "columns");
                if (columns.length != 3) {
                    throw new SQLiteException("Invalid row: 3 columns required");
                }
                return (R) Function3.this.invoke(columns[0], columns[1], columns[2]);
            }
        };
    }

    public static final <T1, T2, T3, T4, R> RowParser<R> rowParser(final Function4<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> parser) {
        Intrinsics.checkParameterIsNotNull(parser, "parser");
        return new RowParser<R>() { // from class: org.jetbrains.anko.db.SQLiteParserHelpersKt__SqlParserHelpersKt$rowParser$4
            @Override // org.jetbrains.anko.p093db.RowParser
            public R parseRow(Object[] columns) {
                Intrinsics.checkParameterIsNotNull(columns, "columns");
                if (columns.length != 4) {
                    throw new SQLiteException("Invalid row: 4 columns required");
                }
                return (R) Function4.this.invoke(columns[0], columns[1], columns[2], columns[3]);
            }
        };
    }

    public static final <T1, T2, T3, T4, T5, R> RowParser<R> rowParser(final Function5<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> parser) {
        Intrinsics.checkParameterIsNotNull(parser, "parser");
        return new RowParser<R>() { // from class: org.jetbrains.anko.db.SQLiteParserHelpersKt__SqlParserHelpersKt$rowParser$5
            @Override // org.jetbrains.anko.p093db.RowParser
            public R parseRow(Object[] columns) {
                Intrinsics.checkParameterIsNotNull(columns, "columns");
                if (columns.length != 5) {
                    throw new SQLiteException("Invalid row: 5 columns required");
                }
                return (R) Function5.this.invoke(columns[0], columns[1], columns[2], columns[3], columns[4]);
            }
        };
    }

    public static final <T1, T2, T3, T4, T5, T6, R> RowParser<R> rowParser(final Function6<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> parser) {
        Intrinsics.checkParameterIsNotNull(parser, "parser");
        return new RowParser<R>() { // from class: org.jetbrains.anko.db.SQLiteParserHelpersKt__SqlParserHelpersKt$rowParser$6
            @Override // org.jetbrains.anko.p093db.RowParser
            public R parseRow(Object[] columns) {
                Intrinsics.checkParameterIsNotNull(columns, "columns");
                if (columns.length != 6) {
                    throw new SQLiteException("Invalid row: 6 columns required");
                }
                return (R) Function6.this.invoke(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5]);
            }
        };
    }

    public static final <T1, T2, T3, T4, T5, T6, T7, R> RowParser<R> rowParser(final Function7<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> parser) {
        Intrinsics.checkParameterIsNotNull(parser, "parser");
        return new RowParser<R>() { // from class: org.jetbrains.anko.db.SQLiteParserHelpersKt__SqlParserHelpersKt$rowParser$7
            @Override // org.jetbrains.anko.p093db.RowParser
            public R parseRow(Object[] columns) {
                Intrinsics.checkParameterIsNotNull(columns, "columns");
                if (columns.length != 7) {
                    throw new SQLiteException("Invalid row: 7 columns required");
                }
                return (R) Function7.this.invoke(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5], columns[6]);
            }
        };
    }

    public static final <T1, T2, T3, T4, T5, T6, T7, T8, R> RowParser<R> rowParser(final Function8<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> parser) {
        Intrinsics.checkParameterIsNotNull(parser, "parser");
        return new RowParser<R>() { // from class: org.jetbrains.anko.db.SQLiteParserHelpersKt__SqlParserHelpersKt$rowParser$8
            @Override // org.jetbrains.anko.p093db.RowParser
            public R parseRow(Object[] columns) {
                Intrinsics.checkParameterIsNotNull(columns, "columns");
                if (columns.length != 8) {
                    throw new SQLiteException("Invalid row: 8 columns required");
                }
                return (R) Function8.this.invoke(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5], columns[6], columns[7]);
            }
        };
    }

    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> RowParser<R> rowParser(final Function9<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? extends R> parser) {
        Intrinsics.checkParameterIsNotNull(parser, "parser");
        return new RowParser<R>() { // from class: org.jetbrains.anko.db.SQLiteParserHelpersKt__SqlParserHelpersKt$rowParser$9
            @Override // org.jetbrains.anko.p093db.RowParser
            public R parseRow(Object[] columns) {
                Intrinsics.checkParameterIsNotNull(columns, "columns");
                if (columns.length != 9) {
                    throw new SQLiteException("Invalid row: 9 columns required");
                }
                return (R) Function9.this.invoke(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5], columns[6], columns[7], columns[8]);
            }
        };
    }

    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> RowParser<R> rowParser(final Function10<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? extends R> parser) {
        Intrinsics.checkParameterIsNotNull(parser, "parser");
        return new RowParser<R>() { // from class: org.jetbrains.anko.db.SQLiteParserHelpersKt__SqlParserHelpersKt$rowParser$10
            @Override // org.jetbrains.anko.p093db.RowParser
            public R parseRow(Object[] columns) {
                Intrinsics.checkParameterIsNotNull(columns, "columns");
                if (columns.length != 10) {
                    throw new SQLiteException("Invalid row: 10 columns required");
                }
                return (R) Function10.this.invoke(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5], columns[6], columns[7], columns[8], columns[9]);
            }
        };
    }

    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R> RowParser<R> rowParser(final Function11<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? super T11, ? extends R> parser) {
        Intrinsics.checkParameterIsNotNull(parser, "parser");
        return new RowParser<R>() { // from class: org.jetbrains.anko.db.SQLiteParserHelpersKt__SqlParserHelpersKt$rowParser$11
            @Override // org.jetbrains.anko.p093db.RowParser
            public R parseRow(Object[] columns) {
                Intrinsics.checkParameterIsNotNull(columns, "columns");
                if (columns.length != 11) {
                    throw new SQLiteException("Invalid row: 11 columns required");
                }
                return (R) Function11.this.invoke(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5], columns[6], columns[7], columns[8], columns[9], columns[10]);
            }
        };
    }

    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, R> RowParser<R> rowParser(final Function12<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? super T11, ? super T12, ? extends R> parser) {
        Intrinsics.checkParameterIsNotNull(parser, "parser");
        return new RowParser<R>() { // from class: org.jetbrains.anko.db.SQLiteParserHelpersKt__SqlParserHelpersKt$rowParser$12
            @Override // org.jetbrains.anko.p093db.RowParser
            public R parseRow(Object[] columns) {
                Intrinsics.checkParameterIsNotNull(columns, "columns");
                if (columns.length != 12) {
                    throw new SQLiteException("Invalid row: 12 columns required");
                }
                return (R) Function12.this.invoke(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5], columns[6], columns[7], columns[8], columns[9], columns[10], columns[11]);
            }
        };
    }

    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, R> RowParser<R> rowParser(final Function13<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? super T11, ? super T12, ? super T13, ? extends R> parser) {
        Intrinsics.checkParameterIsNotNull(parser, "parser");
        return new RowParser<R>() { // from class: org.jetbrains.anko.db.SQLiteParserHelpersKt__SqlParserHelpersKt$rowParser$13
            @Override // org.jetbrains.anko.p093db.RowParser
            public R parseRow(Object[] columns) {
                Intrinsics.checkParameterIsNotNull(columns, "columns");
                if (columns.length != 13) {
                    throw new SQLiteException("Invalid row: 13 columns required");
                }
                return (R) Function13.this.invoke(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5], columns[6], columns[7], columns[8], columns[9], columns[10], columns[11], columns[12]);
            }
        };
    }

    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, R> RowParser<R> rowParser(final Function14<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? super T11, ? super T12, ? super T13, ? super T14, ? extends R> parser) {
        Intrinsics.checkParameterIsNotNull(parser, "parser");
        return new RowParser<R>() { // from class: org.jetbrains.anko.db.SQLiteParserHelpersKt__SqlParserHelpersKt$rowParser$14
            @Override // org.jetbrains.anko.p093db.RowParser
            public R parseRow(Object[] columns) {
                Intrinsics.checkParameterIsNotNull(columns, "columns");
                if (columns.length != 14) {
                    throw new SQLiteException("Invalid row: 14 columns required");
                }
                return (R) Function14.this.invoke(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5], columns[6], columns[7], columns[8], columns[9], columns[10], columns[11], columns[12], columns[13]);
            }
        };
    }

    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R> RowParser<R> rowParser(final Function15<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? super T11, ? super T12, ? super T13, ? super T14, ? super T15, ? extends R> parser) {
        Intrinsics.checkParameterIsNotNull(parser, "parser");
        return new RowParser<R>() { // from class: org.jetbrains.anko.db.SQLiteParserHelpersKt__SqlParserHelpersKt$rowParser$15
            @Override // org.jetbrains.anko.p093db.RowParser
            public R parseRow(Object[] columns) {
                Intrinsics.checkParameterIsNotNull(columns, "columns");
                if (columns.length != 15) {
                    throw new SQLiteException("Invalid row: 15 columns required");
                }
                return (R) Function15.this.invoke(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5], columns[6], columns[7], columns[8], columns[9], columns[10], columns[11], columns[12], columns[13], columns[14]);
            }
        };
    }

    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, R> RowParser<R> rowParser(final Function16<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? super T11, ? super T12, ? super T13, ? super T14, ? super T15, ? super T16, ? extends R> parser) {
        Intrinsics.checkParameterIsNotNull(parser, "parser");
        return new RowParser<R>() { // from class: org.jetbrains.anko.db.SQLiteParserHelpersKt__SqlParserHelpersKt$rowParser$16
            @Override // org.jetbrains.anko.p093db.RowParser
            public R parseRow(Object[] columns) {
                Intrinsics.checkParameterIsNotNull(columns, "columns");
                if (columns.length != 16) {
                    throw new SQLiteException("Invalid row: 16 columns required");
                }
                return (R) Function16.this.invoke(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5], columns[6], columns[7], columns[8], columns[9], columns[10], columns[11], columns[12], columns[13], columns[14], columns[15]);
            }
        };
    }

    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, R> RowParser<R> rowParser(final Function17<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? super T11, ? super T12, ? super T13, ? super T14, ? super T15, ? super T16, ? super T17, ? extends R> parser) {
        Intrinsics.checkParameterIsNotNull(parser, "parser");
        return new RowParser<R>() { // from class: org.jetbrains.anko.db.SQLiteParserHelpersKt__SqlParserHelpersKt$rowParser$17
            @Override // org.jetbrains.anko.p093db.RowParser
            public R parseRow(Object[] columns) {
                Intrinsics.checkParameterIsNotNull(columns, "columns");
                if (columns.length != 17) {
                    throw new SQLiteException("Invalid row: 17 columns required");
                }
                return (R) Function17.this.invoke(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5], columns[6], columns[7], columns[8], columns[9], columns[10], columns[11], columns[12], columns[13], columns[14], columns[15], columns[16]);
            }
        };
    }

    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, R> RowParser<R> rowParser(final Function18<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? super T11, ? super T12, ? super T13, ? super T14, ? super T15, ? super T16, ? super T17, ? super T18, ? extends R> parser) {
        Intrinsics.checkParameterIsNotNull(parser, "parser");
        return new RowParser<R>() { // from class: org.jetbrains.anko.db.SQLiteParserHelpersKt__SqlParserHelpersKt$rowParser$18
            @Override // org.jetbrains.anko.p093db.RowParser
            public R parseRow(Object[] columns) {
                Intrinsics.checkParameterIsNotNull(columns, "columns");
                if (columns.length != 18) {
                    throw new SQLiteException("Invalid row: 18 columns required");
                }
                return (R) Function18.this.invoke(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5], columns[6], columns[7], columns[8], columns[9], columns[10], columns[11], columns[12], columns[13], columns[14], columns[15], columns[16], columns[17]);
            }
        };
    }

    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, R> RowParser<R> rowParser(final Function19<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? super T11, ? super T12, ? super T13, ? super T14, ? super T15, ? super T16, ? super T17, ? super T18, ? super T19, ? extends R> parser) {
        Intrinsics.checkParameterIsNotNull(parser, "parser");
        return new RowParser<R>() { // from class: org.jetbrains.anko.db.SQLiteParserHelpersKt__SqlParserHelpersKt$rowParser$19
            @Override // org.jetbrains.anko.p093db.RowParser
            public R parseRow(Object[] columns) {
                Intrinsics.checkParameterIsNotNull(columns, "columns");
                if (columns.length != 19) {
                    throw new SQLiteException("Invalid row: 19 columns required");
                }
                return (R) Function19.this.invoke(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5], columns[6], columns[7], columns[8], columns[9], columns[10], columns[11], columns[12], columns[13], columns[14], columns[15], columns[16], columns[17], columns[18]);
            }
        };
    }

    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, R> RowParser<R> rowParser(final Function20<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? super T11, ? super T12, ? super T13, ? super T14, ? super T15, ? super T16, ? super T17, ? super T18, ? super T19, ? super T20, ? extends R> parser) {
        Intrinsics.checkParameterIsNotNull(parser, "parser");
        return new RowParser<R>() { // from class: org.jetbrains.anko.db.SQLiteParserHelpersKt__SqlParserHelpersKt$rowParser$20
            @Override // org.jetbrains.anko.p093db.RowParser
            public R parseRow(Object[] columns) {
                Intrinsics.checkParameterIsNotNull(columns, "columns");
                if (columns.length != 20) {
                    throw new SQLiteException("Invalid row: 20 columns required");
                }
                return (R) Function20.this.invoke(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5], columns[6], columns[7], columns[8], columns[9], columns[10], columns[11], columns[12], columns[13], columns[14], columns[15], columns[16], columns[17], columns[18], columns[19]);
            }
        };
    }

    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, R> RowParser<R> rowParser(final Function21<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? super T11, ? super T12, ? super T13, ? super T14, ? super T15, ? super T16, ? super T17, ? super T18, ? super T19, ? super T20, ? super T21, ? extends R> parser) {
        Intrinsics.checkParameterIsNotNull(parser, "parser");
        return new RowParser<R>() { // from class: org.jetbrains.anko.db.SQLiteParserHelpersKt__SqlParserHelpersKt$rowParser$21
            @Override // org.jetbrains.anko.p093db.RowParser
            public R parseRow(Object[] columns) {
                Intrinsics.checkParameterIsNotNull(columns, "columns");
                if (columns.length != 21) {
                    throw new SQLiteException("Invalid row: 21 columns required");
                }
                return (R) Function21.this.invoke(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5], columns[6], columns[7], columns[8], columns[9], columns[10], columns[11], columns[12], columns[13], columns[14], columns[15], columns[16], columns[17], columns[18], columns[19], columns[20]);
            }
        };
    }

    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, R> RowParser<R> rowParser(final Function22<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? super T10, ? super T11, ? super T12, ? super T13, ? super T14, ? super T15, ? super T16, ? super T17, ? super T18, ? super T19, ? super T20, ? super T21, ? super T22, ? extends R> parser) {
        Intrinsics.checkParameterIsNotNull(parser, "parser");
        return new RowParser<R>() { // from class: org.jetbrains.anko.db.SQLiteParserHelpersKt__SqlParserHelpersKt$rowParser$22
            @Override // org.jetbrains.anko.p093db.RowParser
            public R parseRow(Object[] columns) {
                Intrinsics.checkParameterIsNotNull(columns, "columns");
                if (columns.length != 22) {
                    throw new SQLiteException("Invalid row: 22 columns required");
                }
                return (R) Function22.this.invoke(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5], columns[6], columns[7], columns[8], columns[9], columns[10], columns[11], columns[12], columns[13], columns[14], columns[15], columns[16], columns[17], columns[18], columns[19], columns[20], columns[21]);
            }
        };
    }
}
