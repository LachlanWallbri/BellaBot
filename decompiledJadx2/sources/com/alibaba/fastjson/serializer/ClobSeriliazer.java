package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONException;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.sql.Clob;
import java.sql.SQLException;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ClobSeriliazer implements ObjectSerializer {
    public static final ClobSeriliazer instance = new ClobSeriliazer();

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        try {
            if (obj == null) {
                jSONSerializer.writeNull();
                return;
            }
            Reader characterStream = ((Clob) obj).getCharacterStream();
            StringBuilder sb = new StringBuilder();
            try {
                char[] cArr = new char[2048];
                while (true) {
                    int read = characterStream.read(cArr, 0, 2048);
                    if (read >= 0) {
                        sb.append(cArr, 0, read);
                    } else {
                        String sb2 = sb.toString();
                        characterStream.close();
                        jSONSerializer.write(sb2);
                        return;
                    }
                }
            } catch (Exception e) {
                throw new JSONException("read string from reader error", e);
            }
        } catch (SQLException e2) {
            throw new IOException("write clob error", e2);
        }
    }
}
