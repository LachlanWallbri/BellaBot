package com.google.auth.oauth2;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.webtoken.JsonWebSignature;
import com.google.common.base.MoreObjects;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class IdToken extends AccessToken implements Serializable {
    private static final long serialVersionUID = -8514239465808977353L;
    private transient JsonWebSignature jsonWebSignature;

    private IdToken(String str, JsonWebSignature jsonWebSignature) {
        super(str, new Date(jsonWebSignature.getPayload().getExpirationTimeSeconds().longValue() * 1000));
        this.jsonWebSignature = jsonWebSignature;
    }

    public static IdToken create(String str) throws IOException {
        return create(str, OAuth2Utils.JSON_FACTORY);
    }

    public static IdToken create(String str, JsonFactory jsonFactory) throws IOException {
        return new IdToken(str, JsonWebSignature.parse(jsonFactory, str));
    }

    JsonWebSignature getJsonWebSignature() {
        return this.jsonWebSignature;
    }

    @Override // com.google.auth.oauth2.AccessToken
    public int hashCode() {
        return Objects.hash(super.getTokenValue(), this.jsonWebSignature.getHeader(), this.jsonWebSignature.getPayload());
    }

    @Override // com.google.auth.oauth2.AccessToken
    public String toString() {
        return MoreObjects.toStringHelper(this).add("tokenValue", super.getTokenValue()).add("JsonWebSignature", this.jsonWebSignature).toString();
    }

    @Override // com.google.auth.oauth2.AccessToken
    public boolean equals(Object obj) {
        if (!(obj instanceof IdToken)) {
            return false;
        }
        IdToken idToken = (IdToken) obj;
        return Objects.equals(super.getTokenValue(), idToken.getTokenValue()) && Objects.equals(this.jsonWebSignature.getHeader(), idToken.jsonWebSignature.getHeader()) && Objects.equals(this.jsonWebSignature.getPayload(), idToken.jsonWebSignature.getPayload());
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(getTokenValue());
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        this.jsonWebSignature = JsonWebSignature.parse(OAuth2Utils.JSON_FACTORY, (String) objectInputStream.readObject());
    }
}
