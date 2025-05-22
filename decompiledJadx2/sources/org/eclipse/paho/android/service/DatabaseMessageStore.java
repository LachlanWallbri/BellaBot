package org.eclipse.paho.android.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.Iterator;
import java.util.UUID;
import org.eclipse.paho.android.service.MessageStore;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class DatabaseMessageStore implements MessageStore {
    private static final String ARRIVED_MESSAGE_TABLE_NAME = "MqttArrivedMessageTable";
    private static final String MTIMESTAMP = "mtimestamp";
    private static final String TAG = "DatabaseMessageStore";

    /* renamed from: db */
    private SQLiteDatabase f9993db = null;
    private MQTTDatabaseHelper mqttDb;
    private MqttTraceHandler traceHandler;

    /* loaded from: classes9.dex */
    private static class MQTTDatabaseHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "mqttAndroidService.db";
        private static final int DATABASE_VERSION = 1;
        private static final String TAG = "MQTTDatabaseHelper";
        private MqttTraceHandler traceHandler;

        public MQTTDatabaseHelper(MqttTraceHandler mqttTraceHandler, Context context) {
            super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
            this.traceHandler = null;
            this.traceHandler = mqttTraceHandler;
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            this.traceHandler.traceDebug(TAG, "onCreate {CREATE TABLE MqttArrivedMessageTable(messageId TEXT PRIMARY KEY, clientHandle TEXT, destinationName TEXT, payload BLOB, qos INTEGER, retained TEXT, duplicate TEXT, mtimestamp INTEGER);}");
            try {
                sQLiteDatabase.execSQL("CREATE TABLE MqttArrivedMessageTable(messageId TEXT PRIMARY KEY, clientHandle TEXT, destinationName TEXT, payload BLOB, qos INTEGER, retained TEXT, duplicate TEXT, mtimestamp INTEGER);");
                this.traceHandler.traceDebug(TAG, "created the table");
            } catch (SQLException e) {
                this.traceHandler.traceException(TAG, "onCreate", e);
                throw e;
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            this.traceHandler.traceDebug(TAG, "onUpgrade");
            try {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS MqttArrivedMessageTable");
                onCreate(sQLiteDatabase);
                this.traceHandler.traceDebug(TAG, "onUpgrade complete");
            } catch (SQLException e) {
                this.traceHandler.traceException(TAG, "onUpgrade", e);
                throw e;
            }
        }
    }

    public DatabaseMessageStore(MqttService mqttService, Context context) {
        this.mqttDb = null;
        this.traceHandler = null;
        this.traceHandler = mqttService;
        this.mqttDb = new MQTTDatabaseHelper(this.traceHandler, context);
        this.traceHandler.traceDebug(TAG, "DatabaseMessageStore<init> complete");
    }

    @Override // org.eclipse.paho.android.service.MessageStore
    public String storeArrived(String str, String str2, MqttMessage mqttMessage) {
        this.f9993db = this.mqttDb.getWritableDatabase();
        this.traceHandler.traceDebug(TAG, "storeArrived{" + str + "}, {" + mqttMessage.toString() + "}");
        byte[] payload = mqttMessage.getPayload();
        int qos = mqttMessage.getQos();
        boolean isRetained = mqttMessage.isRetained();
        boolean isDuplicate = mqttMessage.isDuplicate();
        ContentValues contentValues = new ContentValues();
        String uuid = UUID.randomUUID().toString();
        contentValues.put("messageId", uuid);
        contentValues.put(MqttServiceConstants.CLIENT_HANDLE, str);
        contentValues.put(MqttServiceConstants.DESTINATION_NAME, str2);
        contentValues.put(MqttServiceConstants.PAYLOAD, payload);
        contentValues.put(MqttServiceConstants.QOS, Integer.valueOf(qos));
        contentValues.put(MqttServiceConstants.RETAINED, Boolean.valueOf(isRetained));
        contentValues.put(MqttServiceConstants.DUPLICATE, Boolean.valueOf(isDuplicate));
        contentValues.put(MTIMESTAMP, Long.valueOf(System.currentTimeMillis()));
        try {
            this.f9993db.insertOrThrow(ARRIVED_MESSAGE_TABLE_NAME, null, contentValues);
            int arrivedRowCount = getArrivedRowCount(str);
            this.traceHandler.traceDebug(TAG, "storeArrived: inserted message with id of {" + uuid + "} - Number of messages in database for this clientHandle = " + arrivedRowCount);
            return uuid;
        } catch (SQLException e) {
            this.traceHandler.traceException(TAG, "onUpgrade", e);
            throw e;
        }
    }

    private int getArrivedRowCount(String str) {
        Cursor query = this.f9993db.query(ARRIVED_MESSAGE_TABLE_NAME, new String[]{"messageId"}, "clientHandle=?", new String[]{str}, null, null, null);
        int i = query.moveToFirst() ? query.getInt(0) : 0;
        query.close();
        return i;
    }

    @Override // org.eclipse.paho.android.service.MessageStore
    public boolean discardArrived(String str, String str2) {
        this.f9993db = this.mqttDb.getWritableDatabase();
        this.traceHandler.traceDebug(TAG, "discardArrived{" + str + "}, {" + str2 + "}");
        try {
            int delete = this.f9993db.delete(ARRIVED_MESSAGE_TABLE_NAME, "messageId=? AND clientHandle=?", new String[]{str2, str});
            if (delete != 1) {
                this.traceHandler.traceError(TAG, "discardArrived - Error deleting message {" + str2 + "} from database: Rows affected = " + delete);
                return false;
            }
            int arrivedRowCount = getArrivedRowCount(str);
            this.traceHandler.traceDebug(TAG, "discardArrived - Message deleted successfully. - messages in db for this clientHandle " + arrivedRowCount);
            return true;
        } catch (SQLException e) {
            this.traceHandler.traceException(TAG, "discardArrived", e);
            throw e;
        }
    }

    @Override // org.eclipse.paho.android.service.MessageStore
    public Iterator<MessageStore.StoredMessage> getAllArrivedMessages(final String str) {
        return new Iterator<MessageStore.StoredMessage>() { // from class: org.eclipse.paho.android.service.DatabaseMessageStore.1

            /* renamed from: c */
            private Cursor f9994c;
            private boolean hasNext;
            private final String[] selectionArgs;

            {
                this.selectionArgs = new String[]{str};
                DatabaseMessageStore databaseMessageStore = DatabaseMessageStore.this;
                databaseMessageStore.f9993db = databaseMessageStore.mqttDb.getWritableDatabase();
                if (str == null) {
                    this.f9994c = DatabaseMessageStore.this.f9993db.query(DatabaseMessageStore.ARRIVED_MESSAGE_TABLE_NAME, null, null, null, null, null, "mtimestamp ASC");
                } else {
                    this.f9994c = DatabaseMessageStore.this.f9993db.query(DatabaseMessageStore.ARRIVED_MESSAGE_TABLE_NAME, null, "clientHandle=?", this.selectionArgs, null, null, "mtimestamp ASC");
                }
                this.hasNext = this.f9994c.moveToFirst();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (!this.hasNext) {
                    this.f9994c.close();
                }
                return this.hasNext;
            }

            @Override // java.util.Iterator
            public MessageStore.StoredMessage next() {
                Cursor cursor = this.f9994c;
                String string = cursor.getString(cursor.getColumnIndex("messageId"));
                Cursor cursor2 = this.f9994c;
                String string2 = cursor2.getString(cursor2.getColumnIndex(MqttServiceConstants.CLIENT_HANDLE));
                Cursor cursor3 = this.f9994c;
                String string3 = cursor3.getString(cursor3.getColumnIndex(MqttServiceConstants.DESTINATION_NAME));
                Cursor cursor4 = this.f9994c;
                byte[] blob = cursor4.getBlob(cursor4.getColumnIndex(MqttServiceConstants.PAYLOAD));
                Cursor cursor5 = this.f9994c;
                int i = cursor5.getInt(cursor5.getColumnIndex(MqttServiceConstants.QOS));
                Cursor cursor6 = this.f9994c;
                boolean parseBoolean = Boolean.parseBoolean(cursor6.getString(cursor6.getColumnIndex(MqttServiceConstants.RETAINED)));
                Cursor cursor7 = this.f9994c;
                boolean parseBoolean2 = Boolean.parseBoolean(cursor7.getString(cursor7.getColumnIndex(MqttServiceConstants.DUPLICATE)));
                MqttMessageHack mqttMessageHack = new MqttMessageHack(blob);
                mqttMessageHack.setQos(i);
                mqttMessageHack.setRetained(parseBoolean);
                mqttMessageHack.setDuplicate(parseBoolean2);
                this.hasNext = this.f9994c.moveToNext();
                return new DbStoredData(string, string2, string3, mqttMessageHack);
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }

            protected void finalize() throws Throwable {
                this.f9994c.close();
                super.finalize();
            }
        };
    }

    @Override // org.eclipse.paho.android.service.MessageStore
    public void clearArrivedMessages(String str) {
        int delete;
        this.f9993db = this.mqttDb.getWritableDatabase();
        String[] strArr = {str};
        if (str == null) {
            this.traceHandler.traceDebug(TAG, "clearArrivedMessages: clearing the table");
            delete = this.f9993db.delete(ARRIVED_MESSAGE_TABLE_NAME, null, null);
        } else {
            this.traceHandler.traceDebug(TAG, "clearArrivedMessages: clearing the table of " + str + " messages");
            delete = this.f9993db.delete(ARRIVED_MESSAGE_TABLE_NAME, "clientHandle=?", strArr);
        }
        this.traceHandler.traceDebug(TAG, "clearArrivedMessages: rows affected = " + delete);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public class DbStoredData implements MessageStore.StoredMessage {
        private String clientHandle;
        private MqttMessage message;
        private String messageId;
        private String topic;

        DbStoredData(String str, String str2, String str3, MqttMessage mqttMessage) {
            this.messageId = str;
            this.topic = str3;
            this.message = mqttMessage;
        }

        @Override // org.eclipse.paho.android.service.MessageStore.StoredMessage
        public String getMessageId() {
            return this.messageId;
        }

        @Override // org.eclipse.paho.android.service.MessageStore.StoredMessage
        public String getClientHandle() {
            return this.clientHandle;
        }

        @Override // org.eclipse.paho.android.service.MessageStore.StoredMessage
        public String getTopic() {
            return this.topic;
        }

        @Override // org.eclipse.paho.android.service.MessageStore.StoredMessage
        public MqttMessage getMessage() {
            return this.message;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public class MqttMessageHack extends MqttMessage {
        public MqttMessageHack(byte[] bArr) {
            super(bArr);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.eclipse.paho.client.mqttv3.MqttMessage
        public void setDuplicate(boolean z) {
            super.setDuplicate(z);
        }
    }

    @Override // org.eclipse.paho.android.service.MessageStore
    public void close() {
        SQLiteDatabase sQLiteDatabase = this.f9993db;
        if (sQLiteDatabase != null) {
            sQLiteDatabase.close();
        }
    }
}
