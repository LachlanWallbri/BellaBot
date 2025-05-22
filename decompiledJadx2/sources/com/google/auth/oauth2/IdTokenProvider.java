package com.google.auth.oauth2;

import java.io.IOException;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface IdTokenProvider {
    IdToken idTokenWithAudience(String str, List<Option> list) throws IOException;

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public enum Option {
        FORMAT_FULL("formatFull"),
        LICENSES_TRUE("licensesTrue"),
        INCLUDE_EMAIL("includeEmail");

        private String option;

        Option(String str) {
            this.option = str;
        }

        public String getOption() {
            return this.option;
        }
    }
}
