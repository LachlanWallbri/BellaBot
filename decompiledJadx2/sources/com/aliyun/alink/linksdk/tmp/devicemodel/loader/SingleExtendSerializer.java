package com.aliyun.alink.linksdk.tmp.devicemodel.loader;

import android.os.AsyncTask;
import android.text.TextUtils;
import com.aliyun.alink.linksdk.tmp.devicemodel.DeviceModel;
import com.aliyun.alink.linksdk.tmp.utils.LogCat;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class SingleExtendSerializer extends DeviceModelSerializer {
    public SingleExtendSerializer(String str) {
        super(str);
    }

    public SingleExtendSerializer() {
        this(RootDeviceModelSerializer.SINGLEEXTEND_DEVICEMODELSERIALIZER_ID);
    }

    @Override // com.aliyun.alink.linksdk.tmp.devicemodel.loader.DeviceModelSerializer
    public String serialize(String str, DeviceModel deviceModel) {
        return serializeInner(deviceModel);
    }

    @Override // com.aliyun.alink.linksdk.tmp.devicemodel.loader.DeviceModelSerializer
    public boolean deserialize(String str, String str2, ILoaderHandler iLoaderHandler) {
        DeviceModel deserializeInner = deserializeInner(str2);
        LoaderWrapperHandler loaderWrapperHandler = new LoaderWrapperHandler(iLoaderHandler);
        if (deserializeInner != null && deserializeInner.getExtend() != null && !deserializeInner.getExtend().isEmpty()) {
            for (int i = 0; i < deserializeInner.getExtend().size(); i++) {
                String str3 = deserializeInner.getExtend().get(i);
                if (!TextUtils.isEmpty(str3)) {
                    DeserializeTask.Builder.create().setSerializer(this).setDeviceModel(deserializeInner).setCurTaskThingId(str3).setChildThingId(expand(deserializeInner.getId(), str3)).setListener(loaderWrapperHandler).start();
                }
            }
        } else {
            DeserializeTask.Builder.create().setSerializer(this).setDeviceModel(deserializeInner).setCurTaskThingId(null).setChildThingId(null).setListener(loaderWrapperHandler).start();
        }
        return deserializeInner != null;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class DeserializeTask extends AsyncTask<String, String, String> {
        protected String mChildThingId;
        protected DeviceModel mDevcieModel;
        protected LoaderWrapperHandler mListener;
        protected DeviceModelSerializer mSerializer;
        protected String mTaskThingId;

        public DeserializeTask() {
        }

        public DeserializeTask(DeviceModelSerializer deviceModelSerializer, DeviceModel deviceModel, String str, String str2, LoaderWrapperHandler loaderWrapperHandler) {
            this.mSerializer = deviceModelSerializer;
            this.mDevcieModel = deviceModel;
            this.mListener = loaderWrapperHandler;
            this.mChildThingId = str2;
            this.mTaskThingId = str;
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            super.onPreExecute();
            LogCat.m469d("[Tmp]DeviceModelSerializer", "onPreExecute mTaskThingId:" + this.mTaskThingId + " mChildThingId:" + this.mChildThingId);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public String doInBackground(String... strArr) {
            LogCat.m469d("[Tmp]DeviceModelSerializer", "doInBackground mTaskThingId:" + this.mTaskThingId + " mChildThingId:" + this.mChildThingId);
            if (TextUtils.isEmpty(this.mTaskThingId)) {
                return null;
            }
            String deviceModel = RootDeviceModelSerializer.getInstance().getDeviceModel(this.mTaskThingId);
            return TextUtils.isEmpty(deviceModel) ? DeviceModelSerializer.requestDeviceModel(this.mDevcieModel.getSchema(), this.mTaskThingId) : deviceModel;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(String str) {
            LogCat.m469d("[Tmp]DeviceModelSerializer", "onPostExecute mTaskThingId:" + this.mTaskThingId + " mChildThingId:" + this.mChildThingId);
            if (!TextUtils.isEmpty(str)) {
                DeviceModel deserializeInner = this.mSerializer.deserializeInner(str);
                if (deserializeInner == null) {
                    LogCat.m471e("[Tmp]DeviceModelSerializer", "onPostExecute deserializeInner return null error s:" + str);
                    onTaskError("deserialize null");
                    return;
                }
                RootDeviceModelSerializer.getInstance().insertDeviceModel(this.mTaskThingId, str);
                DeviceModelSerializer.addChildModel(this.mDevcieModel, deserializeInner);
                if (deserializeInner.getExtend() != null && !deserializeInner.getExtend().isEmpty()) {
                    for (int i = 0; i < deserializeInner.getExtend().size(); i++) {
                        String str2 = deserializeInner.getExtend().get(i);
                        if (!TextUtils.isEmpty(str2)) {
                            Builder.create().setSerializer(this.mSerializer).setDeviceModel(this.mDevcieModel).setCurTaskThingId(str2).setChildThingId(DeviceModelSerializer.expand(this.mChildThingId, str2)).setListener(this.mListener).start();
                        }
                    }
                }
                onTaskEnd();
                return;
            }
            if (TextUtils.isEmpty(this.mTaskThingId)) {
                onTaskEnd();
            } else {
                onTaskError("request thingid null");
            }
        }

        public void onTaskError(String str) {
            this.mListener.decreaseTaskCount();
            this.mListener.onDeserializeError(str);
        }

        public void onTaskEnd() {
            this.mListener.decreaseTaskCount();
            this.mListener.onDeserialize(this.mDevcieModel);
        }

        /* JADX WARN: Classes with same name are omitted:
          
         */
        /* loaded from: classes.dex */
        public static class Builder {
            DeserializeTask mTask = new DeserializeTask();

            Builder() {
            }

            public static Builder create() {
                return new Builder();
            }

            public Builder setSerializer(DeviceModelSerializer deviceModelSerializer) {
                this.mTask.mSerializer = deviceModelSerializer;
                return this;
            }

            public Builder setDeviceModel(DeviceModel deviceModel) {
                this.mTask.mDevcieModel = deviceModel;
                return this;
            }

            public Builder setCurTaskThingId(String str) {
                this.mTask.mTaskThingId = str;
                return this;
            }

            public Builder setChildThingId(String str) {
                this.mTask.mChildThingId = str;
                return this;
            }

            public Builder setListener(LoaderWrapperHandler loaderWrapperHandler) {
                this.mTask.mListener = loaderWrapperHandler;
                return this;
            }

            public void start() {
                if (this.mTask.mListener != null) {
                    this.mTask.mListener.increaseTaskCount();
                }
                this.mTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]);
            }
        }
    }
}
