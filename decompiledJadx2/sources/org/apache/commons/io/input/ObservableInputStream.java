package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
public class ObservableInputStream extends ProxyInputStream {
    private final List<Observer> observers;

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes9.dex
     */
    /* loaded from: classes2.dex */
    public static abstract class Observer {
        void closed() throws IOException {
        }

        void data(int i) throws IOException {
        }

        void data(byte[] bArr, int i, int i2) throws IOException {
        }

        void finished() throws IOException {
        }

        void error(IOException iOException) throws IOException {
            throw iOException;
        }
    }

    public ObservableInputStream(InputStream inputStream) {
        super(inputStream);
        this.observers = new ArrayList();
    }

    public void add(Observer observer) {
        this.observers.add(observer);
    }

    public void remove(Observer observer) {
        this.observers.remove(observer);
    }

    public void removeAllObservers() {
        this.observers.clear();
    }

    @Override // org.apache.commons.io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int i;
        try {
            i = super.read();
            e = null;
        } catch (IOException e) {
            e = e;
            i = 0;
        }
        if (e != null) {
            noteError(e);
        } else if (i == -1) {
            noteFinished();
        } else {
            noteDataByte(i);
        }
        return i;
    }

    @Override // org.apache.commons.io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        int i;
        try {
            i = super.read(bArr);
            e = null;
        } catch (IOException e) {
            e = e;
            i = 0;
        }
        if (e != null) {
            noteError(e);
        } else if (i == -1) {
            noteFinished();
        } else if (i > 0) {
            noteDataBytes(bArr, 0, i);
        }
        return i;
    }

    @Override // org.apache.commons.io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        try {
            i3 = super.read(bArr, i, i2);
            e = null;
        } catch (IOException e) {
            e = e;
            i3 = 0;
        }
        if (e != null) {
            noteError(e);
        } else if (i3 == -1) {
            noteFinished();
        } else if (i3 > 0) {
            noteDataBytes(bArr, i, i3);
        }
        return i3;
    }

    protected void noteDataBytes(byte[] bArr, int i, int i2) throws IOException {
        Iterator<Observer> it = getObservers().iterator();
        while (it.hasNext()) {
            it.next().data(bArr, i, i2);
        }
    }

    protected void noteFinished() throws IOException {
        Iterator<Observer> it = getObservers().iterator();
        while (it.hasNext()) {
            it.next().finished();
        }
    }

    protected void noteDataByte(int i) throws IOException {
        Iterator<Observer> it = getObservers().iterator();
        while (it.hasNext()) {
            it.next().data(i);
        }
    }

    protected void noteError(IOException iOException) throws IOException {
        Iterator<Observer> it = getObservers().iterator();
        while (it.hasNext()) {
            it.next().error(iOException);
        }
    }

    protected void noteClosed() throws IOException {
        Iterator<Observer> it = getObservers().iterator();
        while (it.hasNext()) {
            it.next().closed();
        }
    }

    protected List<Observer> getObservers() {
        return this.observers;
    }

    @Override // org.apache.commons.io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            super.close();
            e = null;
        } catch (IOException e) {
            e = e;
        }
        if (e == null) {
            noteClosed();
        } else {
            noteError(e);
        }
    }

    public void consume() throws IOException {
        do {
        } while (read(new byte[8192]) != -1);
    }
}
