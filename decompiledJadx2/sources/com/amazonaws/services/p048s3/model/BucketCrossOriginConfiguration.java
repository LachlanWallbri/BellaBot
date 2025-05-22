package com.amazonaws.services.p048s3.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class BucketCrossOriginConfiguration implements Serializable {
    private List<CORSRule> rules;

    public List<CORSRule> getRules() {
        return this.rules;
    }

    public void setRules(List<CORSRule> list) {
        this.rules = list;
    }

    public BucketCrossOriginConfiguration withRules(List<CORSRule> list) {
        setRules(list);
        return this;
    }

    public BucketCrossOriginConfiguration withRules(CORSRule... cORSRuleArr) {
        setRules(Arrays.asList(cORSRuleArr));
        return this;
    }

    public BucketCrossOriginConfiguration(List<CORSRule> list) {
        this.rules = list;
    }

    public BucketCrossOriginConfiguration() {
    }
}
