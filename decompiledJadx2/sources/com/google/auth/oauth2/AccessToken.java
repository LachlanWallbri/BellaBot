package com.google.auth.oauth2;

import com.google.common.base.MoreObjects;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class AccessToken implements Serializable {
    private static final long serialVersionUID = -8514239465808977353L;
    private final Long expirationTimeMillis;
    private final String tokenValue;

    public AccessToken(String str, Date date) {
        this.tokenValue = str;
        this.expirationTimeMillis = date == null ? null : Long.valueOf(date.getTime());
    }

    public String getTokenValue() {
        return this.tokenValue;
    }

    public Date getExpirationTime() {
        Long l = this.expirationTimeMillis;
        if (l == null) {
            return null;
        }
        return new Date(l.longValue());
    }

    Long getExpirationTimeMillis() {
        return this.expirationTimeMillis;
    }

    public int hashCode() {
        return Objects.hash(this.tokenValue, this.expirationTimeMillis);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("tokenValue", this.tokenValue).add("expirationTimeMillis", this.expirationTimeMillis).toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AccessToken)) {
            return false;
        }
        AccessToken accessToken = (AccessToken) obj;
        return Objects.equals(this.tokenValue, accessToken.tokenValue) && Objects.equals(this.expirationTimeMillis, accessToken.expirationTimeMillis);
    }
}
