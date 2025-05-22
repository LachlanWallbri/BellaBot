package com.google.common.io;

import com.google.common.base.Preconditions;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.stream.Stream;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public abstract class CharSink {
    public abstract Writer openStream() throws IOException;

    public Writer openBufferedStream() throws IOException {
        Writer openStream = openStream();
        if (openStream instanceof BufferedWriter) {
            return (BufferedWriter) openStream;
        }
        return new BufferedWriter(openStream);
    }

    public void write(CharSequence charSequence) throws IOException {
        Preconditions.checkNotNull(charSequence);
        try {
            Writer writer = (Writer) Closer.create().register(openStream());
            writer.append(charSequence);
            writer.flush();
        } finally {
        }
    }

    public void writeLines(Iterable<? extends CharSequence> iterable) throws IOException {
        writeLines(iterable, System.getProperty("line.separator"));
    }

    public void writeLines(Iterable<? extends CharSequence> iterable, String str) throws IOException {
        writeLines(iterable.iterator(), str);
    }

    public void writeLines(Stream<? extends CharSequence> stream) throws IOException {
        writeLines(stream, System.getProperty("line.separator"));
    }

    public void writeLines(Stream<? extends CharSequence> stream, String str) throws IOException {
        writeLines(stream.iterator(), str);
    }

    private void writeLines(Iterator<? extends CharSequence> it, String str) throws IOException {
        Preconditions.checkNotNull(str);
        Writer openBufferedStream = openBufferedStream();
        while (it.hasNext()) {
            try {
                openBufferedStream.append(it.next()).append((CharSequence) str);
            } catch (Throwable th) {
                if (openBufferedStream != null) {
                    try {
                        openBufferedStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
        if (openBufferedStream != null) {
            openBufferedStream.close();
        }
    }

    public long writeFrom(Readable readable) throws IOException {
        Preconditions.checkNotNull(readable);
        try {
            Writer writer = (Writer) Closer.create().register(openStream());
            long copy = CharStreams.copy(readable, writer);
            writer.flush();
            return copy;
        } finally {
        }
    }
}
