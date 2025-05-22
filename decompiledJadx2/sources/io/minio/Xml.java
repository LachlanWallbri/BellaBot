package io.minio;

import io.minio.errors.XmlParserException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;

/* loaded from: classes7.dex */
public class Xml {
    public static String marshal(Object obj) throws XmlParserException {
        try {
            Persister persister = new Persister(new AnnotationStrategy(), new Format(0));
            StringWriter stringWriter = new StringWriter();
            persister.write(obj, stringWriter);
            return stringWriter.toString();
        } catch (Exception e) {
            throw new XmlParserException(e);
        }
    }

    public static <T> T unmarshal(Class<? extends T> cls, Reader reader) throws XmlParserException {
        try {
            return (T) new Persister(new AnnotationStrategy()).read((Class) cls, reader);
        } catch (Exception e) {
            throw new XmlParserException(e);
        }
    }

    public static <T> T unmarshal(Class<? extends T> cls, String str) throws XmlParserException {
        try {
            return (T) new Persister(new AnnotationStrategy()).read((Class) cls, (Reader) new StringReader(str));
        } catch (Exception e) {
            throw new XmlParserException(e);
        }
    }

    public static boolean validate(Class cls, String str) throws XmlParserException {
        try {
            return new Persister(new AnnotationStrategy()).validate(cls, str);
        } catch (Exception e) {
            throw new XmlParserException(e);
        }
    }
}
