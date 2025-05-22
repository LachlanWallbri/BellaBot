package com.amazonaws.mobile.client;

import android.app.Activity;

/* loaded from: classes.dex */
public class SignInUIOptions {
    private final Builder builder;

    SignInUIOptions(Builder builder) {
        this.builder = builder;
    }

    public String getBrowserPackage() {
        return this.builder.browserPackage;
    }

    public Integer getLogo() {
        return this.builder.logo;
    }

    public Integer getBackgroundColor() {
        return this.builder.backgroundColor;
    }

    public boolean canCancel() {
        return this.builder.canCancel;
    }

    public Class<? extends Activity> nextActivity() {
        return this.builder.nextActivityClass;
    }

    public HostedUIOptions getHostedUIOptions() {
        return this.builder.hostedUIOptions;
    }

    public static Builder builder() {
        return new Builder();
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private Integer backgroundColor;
        private String browserPackage;
        private boolean canCancel;
        private HostedUIOptions hostedUIOptions;
        private Integer logo;
        private Class<? extends Activity> nextActivityClass;

        public Builder logo(Integer num) {
            this.logo = num;
            return this;
        }

        public Builder browserPackage(String str) {
            this.browserPackage = str;
            return this;
        }

        public Builder backgroundColor(Integer num) {
            this.backgroundColor = num;
            return this;
        }

        public Builder canCancel(boolean z) {
            this.canCancel = z;
            return this;
        }

        public Builder nextActivity(Class<? extends Activity> cls) {
            this.nextActivityClass = cls;
            return this;
        }

        public Builder hostedUIOptions(HostedUIOptions hostedUIOptions) {
            this.hostedUIOptions = hostedUIOptions;
            return this;
        }

        public SignInUIOptions build() {
            return new SignInUIOptions(this);
        }
    }
}
