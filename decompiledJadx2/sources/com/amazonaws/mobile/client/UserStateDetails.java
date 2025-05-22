package com.amazonaws.mobile.client;

import java.util.Map;

/* loaded from: classes.dex */
public class UserStateDetails {
    private final Map<String, String> details;
    private Exception exception;
    private final UserState userState;

    public UserStateDetails(UserState userState, Map<String, String> map) {
        this.userState = userState;
        this.details = map;
    }

    public UserState getUserState() {
        return this.userState;
    }

    public Map<String, String> getDetails() {
        return this.details;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setException(Exception exc) {
        this.exception = exc;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Exception getException() {
        return this.exception;
    }

    public boolean equals(Object obj) {
        if (obj instanceof UserStateDetails) {
            UserStateDetails userStateDetails = (UserStateDetails) obj;
            if (!this.userState.equals(userStateDetails.userState)) {
                return false;
            }
            Map<String, String> map = userStateDetails.details;
            Map<String, String> map2 = this.details;
            if (map == map2) {
                return true;
            }
            if (map2 == null) {
                return false;
            }
            return map2.equals(map);
        }
        return super.equals(obj);
    }
}
