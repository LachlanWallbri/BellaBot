package org.apache.commons.compress.archivers.zip;

import java.io.File;
import java.io.IOException;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.compress.parallel.FileBasedScatterGatherBackingStore;
import org.apache.commons.compress.parallel.InputStreamSupplier;
import org.apache.commons.compress.parallel.ScatterGatherBackingStore;
import org.apache.commons.compress.parallel.ScatterGatherBackingStoreSupplier;

/* loaded from: classes8.dex */
public class ParallelScatterZipCreator {
    private final ScatterGatherBackingStoreSupplier backingStoreSupplier;
    private long compressionDoneAt;
    private final int compressionLevel;

    /* renamed from: es */
    private final ExecutorService f8918es;
    private final Deque<Future<? extends ScatterZipOutputStream>> futures;
    private long scatterDoneAt;
    private final long startedAt;
    private final Deque<ScatterZipOutputStream> streams;
    private final ThreadLocal<ScatterZipOutputStream> tlScatterStreams;

    /* loaded from: classes8.dex */
    private static class DefaultBackingStoreSupplier implements ScatterGatherBackingStoreSupplier {
        final AtomicInteger storeNum;

        private DefaultBackingStoreSupplier() {
            this.storeNum = new AtomicInteger(0);
        }

        @Override // org.apache.commons.compress.parallel.ScatterGatherBackingStoreSupplier
        public ScatterGatherBackingStore get() throws IOException {
            return new FileBasedScatterGatherBackingStore(File.createTempFile("parallelscatter", "n" + this.storeNum.incrementAndGet()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ScatterZipOutputStream createDeferred(ScatterGatherBackingStoreSupplier scatterGatherBackingStoreSupplier) throws IOException {
        ScatterGatherBackingStore scatterGatherBackingStore = scatterGatherBackingStoreSupplier.get();
        return new ScatterZipOutputStream(scatterGatherBackingStore, StreamCompressor.create(this.compressionLevel, scatterGatherBackingStore));
    }

    public ParallelScatterZipCreator() {
        this(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));
    }

    public ParallelScatterZipCreator(ExecutorService executorService) {
        this(executorService, new DefaultBackingStoreSupplier());
    }

    public ParallelScatterZipCreator(ExecutorService executorService, ScatterGatherBackingStoreSupplier scatterGatherBackingStoreSupplier) {
        this(executorService, scatterGatherBackingStoreSupplier, -1);
    }

    public ParallelScatterZipCreator(ExecutorService executorService, ScatterGatherBackingStoreSupplier scatterGatherBackingStoreSupplier, int i) throws IllegalArgumentException {
        this.streams = new ConcurrentLinkedDeque();
        this.futures = new ConcurrentLinkedDeque();
        this.startedAt = System.currentTimeMillis();
        this.tlScatterStreams = new ThreadLocal<ScatterZipOutputStream>() { // from class: org.apache.commons.compress.archivers.zip.ParallelScatterZipCreator.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // java.lang.ThreadLocal
            public ScatterZipOutputStream initialValue() {
                try {
                    ScatterZipOutputStream createDeferred = ParallelScatterZipCreator.this.createDeferred(ParallelScatterZipCreator.this.backingStoreSupplier);
                    ParallelScatterZipCreator.this.streams.add(createDeferred);
                    return createDeferred;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        if ((i < 0 || i > 9) && i != -1) {
            throw new IllegalArgumentException("Compression level is expected between -1~9");
        }
        this.backingStoreSupplier = scatterGatherBackingStoreSupplier;
        this.f8918es = executorService;
        this.compressionLevel = i;
    }

    public void addArchiveEntry(ZipArchiveEntry zipArchiveEntry, InputStreamSupplier inputStreamSupplier) {
        submitStreamAwareCallable(createCallable(zipArchiveEntry, inputStreamSupplier));
    }

    public void addArchiveEntry(ZipArchiveEntryRequestSupplier zipArchiveEntryRequestSupplier) {
        submitStreamAwareCallable(createCallable(zipArchiveEntryRequestSupplier));
    }

    public final void submit(final Callable<? extends Object> callable) {
        submitStreamAwareCallable(new Callable() { // from class: org.apache.commons.compress.archivers.zip.-$$Lambda$ParallelScatterZipCreator$JS4FBv-6LWnQHIDmJptPHSlW9c4
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return ParallelScatterZipCreator.this.lambda$submit$0$ParallelScatterZipCreator(callable);
            }
        });
    }

    public /* synthetic */ ScatterZipOutputStream lambda$submit$0$ParallelScatterZipCreator(Callable callable) throws Exception {
        callable.call();
        return this.tlScatterStreams.get();
    }

    public final void submitStreamAwareCallable(Callable<? extends ScatterZipOutputStream> callable) {
        this.futures.add(this.f8918es.submit(callable));
    }

    public final Callable<ScatterZipOutputStream> createCallable(ZipArchiveEntry zipArchiveEntry, InputStreamSupplier inputStreamSupplier) {
        if (zipArchiveEntry.getMethod() == -1) {
            throw new IllegalArgumentException("Method must be set on zipArchiveEntry: " + zipArchiveEntry);
        }
        final ZipArchiveEntryRequest createZipArchiveEntryRequest = ZipArchiveEntryRequest.createZipArchiveEntryRequest(zipArchiveEntry, inputStreamSupplier);
        return new Callable() { // from class: org.apache.commons.compress.archivers.zip.-$$Lambda$ParallelScatterZipCreator$XjNYo5A7DQIKobuEspLx59zAwJY
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return ParallelScatterZipCreator.this.lambda$createCallable$1$ParallelScatterZipCreator(createZipArchiveEntryRequest);
            }
        };
    }

    public /* synthetic */ ScatterZipOutputStream lambda$createCallable$1$ParallelScatterZipCreator(ZipArchiveEntryRequest zipArchiveEntryRequest) throws Exception {
        ScatterZipOutputStream scatterZipOutputStream = this.tlScatterStreams.get();
        scatterZipOutputStream.addArchiveEntry(zipArchiveEntryRequest);
        return scatterZipOutputStream;
    }

    public final Callable<ScatterZipOutputStream> createCallable(final ZipArchiveEntryRequestSupplier zipArchiveEntryRequestSupplier) {
        return new Callable() { // from class: org.apache.commons.compress.archivers.zip.-$$Lambda$ParallelScatterZipCreator$igmyeWOEilQCjl5Fz5k9RgyVykE
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return ParallelScatterZipCreator.this.lambda$createCallable$2$ParallelScatterZipCreator(zipArchiveEntryRequestSupplier);
            }
        };
    }

    public /* synthetic */ ScatterZipOutputStream lambda$createCallable$2$ParallelScatterZipCreator(ZipArchiveEntryRequestSupplier zipArchiveEntryRequestSupplier) throws Exception {
        ScatterZipOutputStream scatterZipOutputStream = this.tlScatterStreams.get();
        scatterZipOutputStream.addArchiveEntry(zipArchiveEntryRequestSupplier.get());
        return scatterZipOutputStream;
    }

    public void writeTo(ZipArchiveOutputStream zipArchiveOutputStream) throws IOException, InterruptedException, ExecutionException {
        try {
            try {
                Iterator<Future<? extends ScatterZipOutputStream>> it = this.futures.iterator();
                while (it.hasNext()) {
                    it.next().get();
                }
                this.f8918es.shutdown();
                this.f8918es.awaitTermination(60000L, TimeUnit.SECONDS);
                this.compressionDoneAt = System.currentTimeMillis();
                Iterator<Future<? extends ScatterZipOutputStream>> it2 = this.futures.iterator();
                while (it2.hasNext()) {
                    it2.next().get().zipEntryWriter().writeNextZipEntry(zipArchiveOutputStream);
                }
                Iterator<ScatterZipOutputStream> it3 = this.streams.iterator();
                while (it3.hasNext()) {
                    it3.next().close();
                }
                this.scatterDoneAt = System.currentTimeMillis();
            } catch (Throwable th) {
                this.f8918es.shutdown();
                throw th;
            }
        } finally {
            closeAll();
        }
    }

    public ScatterStatistics getStatisticsMessage() {
        long j = this.compressionDoneAt;
        return new ScatterStatistics(j - this.startedAt, this.scatterDoneAt - j);
    }

    private void closeAll() {
        Iterator<ScatterZipOutputStream> it = this.streams.iterator();
        while (it.hasNext()) {
            try {
                it.next().close();
            } catch (IOException unused) {
            }
        }
    }
}
