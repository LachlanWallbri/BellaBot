package com.pudutech.pd_network.report.dao;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.pudutech.pd_network.report.bean.ReportBean;
import java.util.List;
import kotlin.Metadata;

/* compiled from: ReportDao.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\b`\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0005H&J\u0016\u0010\u0007\u001a\u00020\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\nH&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH&J\u0010\u0010\u000e\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rH&J\u001e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0005H&J2\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\n2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0005H&J\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00100\n2\u0006\u0010\f\u001a\u00020\rH&J\b\u0010\u0016\u001a\u00020\u0005H&J\b\u0010\u0017\u001a\u00020\u0005H&J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00102\u0006\u0010\b\u001a\u00020\u0005H&J\u0017\u0010\u0019\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001a\u001a\u00020\u0010H&¢\u0006\u0002\u0010\u001bJ\u0016\u0010\u001c\u001a\u00020\u00032\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00100\nH&J\u0010\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u0010H&J\u001e\u0010\u001d\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\nH&¨\u0006\u001e"}, m3961d2 = {"Lcom/pudutech/pd_network/report/dao/IReportDao;", "", RequestParameters.SUBRESOURCE_DELETE, "", "count", "", "deleteAll", "deleteById", "id", "ids", "", "deleteByUpload", "upload", "", "getCount", "getList", "Lcom/pudutech/pd_network/report/bean/ReportBean;", "timeStamp", "env", "", "cluster", "getListByUpload", "getMaxId", "getMinId", "getReportById", "insert", "bean", "(Lcom/pudutech/pd_network/report/bean/ReportBean;)Ljava/lang/Long;", "insertList", "updateUploadState", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface IReportDao {
    void delete(long count);

    void deleteAll();

    void deleteById(long id);

    void deleteById(List<Long> ids);

    void deleteByUpload(int upload);

    long getCount(int upload);

    List<ReportBean> getList(int upload, long timeStamp);

    List<ReportBean> getList(String env, String cluster, int upload, long timeStamp);

    List<ReportBean> getListByUpload(int upload);

    long getMaxId();

    long getMinId();

    ReportBean getReportById(long id);

    Long insert(ReportBean bean);

    void insertList(List<ReportBean> bean);

    void updateUploadState(int upload, List<Long> ids);

    void updateUploadState(ReportBean bean);
}
