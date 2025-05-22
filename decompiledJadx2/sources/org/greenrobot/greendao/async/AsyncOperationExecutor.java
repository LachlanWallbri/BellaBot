package org.greenrobot.greendao.async;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.DaoLog;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.Query;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class AsyncOperationExecutor implements Runnable, Handler.Callback {
    private static ExecutorService executorService = Executors.newCachedThreadPool();
    private int countOperationsCompleted;
    private int countOperationsEnqueued;
    private volatile boolean executorRunning;
    private Handler handlerMainThread;
    private int lastSequenceNumber;
    private volatile AsyncOperationListener listener;
    private volatile AsyncOperationListener listenerMainThread;
    private final BlockingQueue<AsyncOperation> queue = new LinkedBlockingQueue();
    private volatile int maxOperationCountToMerge = 50;
    private volatile int waitForMergeMillis = 50;

    public void enqueue(AsyncOperation asyncOperation) {
        synchronized (this) {
            int i = this.lastSequenceNumber + 1;
            this.lastSequenceNumber = i;
            asyncOperation.sequenceNumber = i;
            this.queue.add(asyncOperation);
            this.countOperationsEnqueued++;
            if (!this.executorRunning) {
                this.executorRunning = true;
                executorService.execute(this);
            }
        }
    }

    public int getMaxOperationCountToMerge() {
        return this.maxOperationCountToMerge;
    }

    public void setMaxOperationCountToMerge(int i) {
        this.maxOperationCountToMerge = i;
    }

    public int getWaitForMergeMillis() {
        return this.waitForMergeMillis;
    }

    public void setWaitForMergeMillis(int i) {
        this.waitForMergeMillis = i;
    }

    public AsyncOperationListener getListener() {
        return this.listener;
    }

    public void setListener(AsyncOperationListener asyncOperationListener) {
        this.listener = asyncOperationListener;
    }

    public AsyncOperationListener getListenerMainThread() {
        return this.listenerMainThread;
    }

    public void setListenerMainThread(AsyncOperationListener asyncOperationListener) {
        this.listenerMainThread = asyncOperationListener;
    }

    public synchronized boolean isCompleted() {
        return this.countOperationsEnqueued == this.countOperationsCompleted;
    }

    public synchronized void waitForCompletion() {
        while (!isCompleted()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new DaoException("Interrupted while waiting for all operations to complete", e);
            }
        }
    }

    public synchronized boolean waitForCompletion(int i) {
        if (!isCompleted()) {
            try {
                wait(i);
            } catch (InterruptedException e) {
                throw new DaoException("Interrupted while waiting for all operations to complete", e);
            }
        }
        return isCompleted();
    }

    @Override // java.lang.Runnable
    public void run() {
        AsyncOperation poll;
        while (true) {
            try {
                AsyncOperation poll2 = this.queue.poll(1L, TimeUnit.SECONDS);
                if (poll2 == null) {
                    synchronized (this) {
                        poll2 = this.queue.poll();
                        if (poll2 == null) {
                            return;
                        }
                    }
                }
                if (poll2.isMergeTx() && (poll = this.queue.poll(this.waitForMergeMillis, TimeUnit.MILLISECONDS)) != null) {
                    if (poll2.isMergeableWith(poll)) {
                        mergeTxAndExecute(poll2, poll);
                    } else {
                        executeOperationAndPostCompleted(poll2);
                        executeOperationAndPostCompleted(poll);
                    }
                } else {
                    executeOperationAndPostCompleted(poll2);
                }
            } catch (InterruptedException e) {
                DaoLog.m4145w(Thread.currentThread().getName() + " was interruppted", e);
                return;
            } finally {
                this.executorRunning = false;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x0063, code lost:
    
        r4 = false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void mergeTxAndExecute(AsyncOperation asyncOperation, AsyncOperation asyncOperation2) {
        boolean z;
        ArrayList arrayList = new ArrayList();
        arrayList.add(asyncOperation);
        arrayList.add(asyncOperation2);
        Database database = asyncOperation.getDatabase();
        database.beginTransaction();
        boolean z2 = false;
        int i = 0;
        while (true) {
            try {
                z = true;
                if (i >= arrayList.size()) {
                    break;
                }
                AsyncOperation asyncOperation3 = (AsyncOperation) arrayList.get(i);
                executeOperation(asyncOperation3);
                if (asyncOperation3.isFailed()) {
                    break;
                }
                if (i == arrayList.size() - 1) {
                    AsyncOperation peek = this.queue.peek();
                    if (i >= this.maxOperationCountToMerge || !asyncOperation3.isMergeableWith(peek)) {
                        break;
                    }
                    AsyncOperation remove = this.queue.remove();
                    if (remove != peek) {
                        throw new DaoException("Internal error: peeked op did not match removed op");
                    }
                    arrayList.add(remove);
                }
                i++;
            } catch (Throwable th) {
                try {
                    database.endTransaction();
                } catch (RuntimeException e) {
                    DaoLog.m4141i("Async transaction could not be ended, success so far was: false", e);
                }
                throw th;
            }
        }
        database.setTransactionSuccessful();
        try {
            database.endTransaction();
            z2 = z;
        } catch (RuntimeException e2) {
            DaoLog.m4141i("Async transaction could not be ended, success so far was: " + z, e2);
        }
        if (z2) {
            int size = arrayList.size();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                AsyncOperation asyncOperation4 = (AsyncOperation) it.next();
                asyncOperation4.mergedOperationsCount = size;
                handleOperationCompleted(asyncOperation4);
            }
            return;
        }
        DaoLog.m4140i("Reverted merged transaction because one of the operations failed. Executing operations one by one instead...");
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            AsyncOperation asyncOperation5 = (AsyncOperation) it2.next();
            asyncOperation5.reset();
            executeOperationAndPostCompleted(asyncOperation5);
        }
    }

    private void handleOperationCompleted(AsyncOperation asyncOperation) {
        asyncOperation.setCompleted();
        AsyncOperationListener asyncOperationListener = this.listener;
        if (asyncOperationListener != null) {
            asyncOperationListener.onAsyncOperationCompleted(asyncOperation);
        }
        if (this.listenerMainThread != null) {
            if (this.handlerMainThread == null) {
                this.handlerMainThread = new Handler(Looper.getMainLooper(), this);
            }
            this.handlerMainThread.sendMessage(this.handlerMainThread.obtainMessage(1, asyncOperation));
        }
        synchronized (this) {
            this.countOperationsCompleted++;
            if (this.countOperationsCompleted == this.countOperationsEnqueued) {
                notifyAll();
            }
        }
    }

    private void executeOperationAndPostCompleted(AsyncOperation asyncOperation) {
        executeOperation(asyncOperation);
        handleOperationCompleted(asyncOperation);
    }

    private void executeOperation(AsyncOperation asyncOperation) {
        asyncOperation.timeStarted = System.currentTimeMillis();
        try {
            switch (asyncOperation.type) {
                case Delete:
                    asyncOperation.dao.delete(asyncOperation.parameter);
                    break;
                case DeleteInTxIterable:
                    asyncOperation.dao.deleteInTx((Iterable<Object>) asyncOperation.parameter);
                    break;
                case DeleteInTxArray:
                    asyncOperation.dao.deleteInTx((Object[]) asyncOperation.parameter);
                    break;
                case Insert:
                    asyncOperation.dao.insert(asyncOperation.parameter);
                    break;
                case InsertInTxIterable:
                    asyncOperation.dao.insertInTx((Iterable<Object>) asyncOperation.parameter);
                    break;
                case InsertInTxArray:
                    asyncOperation.dao.insertInTx((Object[]) asyncOperation.parameter);
                    break;
                case InsertOrReplace:
                    asyncOperation.dao.insertOrReplace(asyncOperation.parameter);
                    break;
                case InsertOrReplaceInTxIterable:
                    asyncOperation.dao.insertOrReplaceInTx((Iterable<Object>) asyncOperation.parameter);
                    break;
                case InsertOrReplaceInTxArray:
                    asyncOperation.dao.insertOrReplaceInTx((Object[]) asyncOperation.parameter);
                    break;
                case Update:
                    asyncOperation.dao.update(asyncOperation.parameter);
                    break;
                case UpdateInTxIterable:
                    asyncOperation.dao.updateInTx((Iterable<Object>) asyncOperation.parameter);
                    break;
                case UpdateInTxArray:
                    asyncOperation.dao.updateInTx((Object[]) asyncOperation.parameter);
                    break;
                case TransactionRunnable:
                    executeTransactionRunnable(asyncOperation);
                    break;
                case TransactionCallable:
                    executeTransactionCallable(asyncOperation);
                    break;
                case QueryList:
                    asyncOperation.result = ((Query) asyncOperation.parameter).forCurrentThread().list();
                    break;
                case QueryUnique:
                    asyncOperation.result = ((Query) asyncOperation.parameter).forCurrentThread().unique();
                    break;
                case DeleteByKey:
                    asyncOperation.dao.deleteByKey(asyncOperation.parameter);
                    break;
                case DeleteAll:
                    asyncOperation.dao.deleteAll();
                    break;
                case Load:
                    asyncOperation.result = asyncOperation.dao.load(asyncOperation.parameter);
                    break;
                case LoadAll:
                    asyncOperation.result = asyncOperation.dao.loadAll();
                    break;
                case Count:
                    asyncOperation.result = Long.valueOf(asyncOperation.dao.count());
                    break;
                case Refresh:
                    asyncOperation.dao.refresh(asyncOperation.parameter);
                    break;
                default:
                    throw new DaoException("Unsupported operation: " + asyncOperation.type);
            }
        } catch (Throwable th) {
            asyncOperation.throwable = th;
        }
        asyncOperation.timeCompleted = System.currentTimeMillis();
    }

    private void executeTransactionRunnable(AsyncOperation asyncOperation) {
        Database database = asyncOperation.getDatabase();
        database.beginTransaction();
        try {
            ((Runnable) asyncOperation.parameter).run();
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
    }

    private void executeTransactionCallable(AsyncOperation asyncOperation) throws Exception {
        Database database = asyncOperation.getDatabase();
        database.beginTransaction();
        try {
            asyncOperation.result = ((Callable) asyncOperation.parameter).call();
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        AsyncOperationListener asyncOperationListener = this.listenerMainThread;
        if (asyncOperationListener == null) {
            return false;
        }
        asyncOperationListener.onAsyncOperationCompleted((AsyncOperation) message.obj);
        return false;
    }
}
