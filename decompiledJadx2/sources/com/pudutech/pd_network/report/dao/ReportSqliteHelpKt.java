package com.pudutech.pd_network.report.dao;

import kotlin.Metadata;

/* compiled from: ReportSqliteHelp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, m3961d2 = {"DB_CREATE", "", "DB_NAME", "DB_TABLE", "DB_VERSION", "", "pd_network_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ReportSqliteHelpKt {
    public static final String DB_CREATE = "create table t_report (reportId integer primary key ,timeStamp integer ,data TEXT ,host TEXT ,env TEXT ,api TEXT ,cluster TEXT ,upload integer)";
    public static final String DB_NAME = "report.db";
    public static final String DB_TABLE = "t_report";
    public static final int DB_VERSION = 4;
}
