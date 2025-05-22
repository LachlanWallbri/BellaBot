package com.pudutech.pdmqtt.config;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: base.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0004\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, m3961d2 = {"ALI_CERTIFICATE", "", "EMQX_CERTIFICATE", "toStr", "Lcom/pudutech/pdmqtt/config/BaseMqttConfig;", "pdmqtt_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class BaseKt {
    public static final String ALI_CERTIFICATE = "-----BEGIN CERTIFICATE-----\nMIIDdTCCAl2gAwIBAgILBAAAAAABFUtaw5QwDQYJKoZIhvcNAQEFBQAwVzELMAkG\nA1UEBhMCQkUxGTAXBgNVBAoTEEdsb2JhbFNpZ24gbnYtc2ExEDAOBgNVBAsTB1Jv\nb3QgQ0ExGzAZBgNVBAMTEkdsb2JhbFNpZ24gUm9vdCBDQTAeFw05ODA5MDExMjAw\nMDBaFw0yODAxMjgxMjAwMDBaMFcxCzAJBgNVBAYTAkJFMRkwFwYDVQQKExBHbG9i\nYWxTaWduIG52LXNhMRAwDgYDVQQLEwdSb290IENBMRswGQYDVQQDExJHbG9iYWxT\naWduIFJvb3QgQ0EwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDaDuaZ\njc6j40+Kfvvxi4Mla+pIH/EqsLmVEQS98GPR4mdmzxzdzxtIK+6NiY6arymAZavp\nxy0Sy6scTHAHoT0KMM0VjU/43dSMUBUc71DuxC73/OlS8pF94G3VNTCOXkNz8kHp\n1Wrjsok6Vjk4bwY8iGlbKk3Fp1S4bInMm/k8yuX9ifUSPJJ4ltbcdG6TRGHRjcdG\nsnUOhugZitVtbNV4FpWi6cgKOOvyJBNPc1STE4U6G7weNLWLBYy5d4ux2x8gkasJ\nU26Qzns3dLlwR5EiUWMWea6xrkEmCMgZK9FGqkjWZCrXgzT/LCrBbBlDSgeF59N8\n9iFo7+ryUp9/k5DPAgMBAAGjQjBAMA4GA1UdDwEB/wQEAwIBBjAPBgNVHRMBAf8E\nBTADAQH/MB0GA1UdDgQWBBRge2YaRQ2XyolQL30EzTSo//z9SzANBgkqhkiG9w0B\nAQUFAAOCAQEA1nPnfE920I2/7LqivjTFKDK1fPxsnCwrvQmeU79rXqoRSLblCKOz\nyj1hTdNGCbM+w6DjY1Ub8rrvrTnhQ7k4o+YviiY776BQVvnGCv04zcQLcFGUl5gE\n38NflNUVyRRBnMRddWQVDf9VMOyGj/8N7yy5Y0b2qvzfvGn9LhJIZJrglfCm7ymP\nAbEVtQwdpf5pLGkkeB6zpxxxYu7KyJesF12KwvhHhm4qxFYxldBniYUr+WymXUad\nDKqC5JlR3XC321Y9YeRq4VzW9v493kHMB65jUr9TU/Qr6cf9tveCX4XSQRjbgbME\nHMUfpIBvFSDJ3gyICh3WZlXi/EjJKSZp4A==\n-----END CERTIFICATE-----";
    public static final String EMQX_CERTIFICATE = "-----BEGIN CERTIFICATE-----\nMIIB2zCCAUQCCQCZLnlNhmN8BjANBgkqhkiG9w0BAQUFADAyMTAwLgYDVQQKDCdU\nTFMgUHJvamVjdCBEb2RneSBDZXJ0aWZpY2F0ZSBBdXRob3JpdHkwHhcNMjEwMzE3\nMTAwMjI3WhcNMzQxMTI0MTAwMjI3WjAyMTAwLgYDVQQKDCdUTFMgUHJvamVjdCBE\nb2RneSBDZXJ0aWZpY2F0ZSBBdXRob3JpdHkwgZ8wDQYJKoZIhvcNAQEBBQADgY0A\nMIGJAoGBAMxt02KMuOSCSuIlFmaqDkmTkh1nV4TEMqn1wOQ+KvhfUtEp/cGw1jlW\nUmvMRiwW/xyBpJypAWuWPu4I+w/l+WG5vbsh/03blLpLiPt1SeMi9UGExd8oWw9N\n/8zGyPzbXKWZnUty8Vpe8ta6em3xIPdZWLVV5XR8EUYBWstYTKDHAgMBAAEwDQYJ\nKoZIhvcNAQEFBQADgYEAOPBpsUbuoUKkc+2Pv+2LtaInoIyTBcIPW0PF3Ih0nJYq\nUdmWADBA/zzcVbe79aNqtOhmh1/xKMXOGJt9qYx5yMPecK7biK/6vEXdahzS5tB6\n6TeOfte48IKgqf2oPLd5KbLUUbCbCd6CmqAegeHzrYi9J69x7WMoSymmz4YEQAI=\n-----END CERTIFICATE-----\n";

    public static final String toStr(BaseMqttConfig toStr) {
        Intrinsics.checkParameterIsNotNull(toStr, "$this$toStr");
        return "BaseMqttConfig: \nserverURI = " + toStr.serverURI() + "\nclientId = " + toStr.clientId() + "\nusername = " + toStr.username() + "\npassword = " + toStr.password() + "\ncertificateJsonInfo = " + toStr.certificateJsonInfo() + '\n';
    }
}
