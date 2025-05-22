package io.minio.messages;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "Filter", strict = false)
/* loaded from: classes7.dex */
public class Filter {

    @ElementList(name = "S3Key")
    private List<FilterRule> filterRuleList;

    private void setRule(String str, String str2) throws IllegalArgumentException {
        if (str2.length() > 1024) {
            throw new IllegalArgumentException("value '" + str2 + "' is more than 1024 long");
        }
        if (this.filterRuleList == null) {
            this.filterRuleList = new LinkedList();
        }
        for (FilterRule filterRule : this.filterRuleList) {
            if (filterRule.name().equals(str)) {
                this.filterRuleList.remove(filterRule);
            }
        }
        this.filterRuleList.add(new FilterRule(str, str2));
    }

    public void setPrefixRule(String str) throws IllegalArgumentException {
        setRule(RequestParameters.PREFIX, str);
    }

    public void setSuffixRule(String str) throws IllegalArgumentException {
        setRule("suffix", str);
    }

    public List<FilterRule> filterRuleList() {
        List list = this.filterRuleList;
        if (list == null) {
            list = new LinkedList();
        }
        return Collections.unmodifiableList(list);
    }
}
