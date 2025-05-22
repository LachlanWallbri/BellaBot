package com.pudutech.mic_array.util;

import android.content.Context;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes5.dex */
public class FileUtil {
    private static final String PCM_SURFFIX = ".pcm";
    private String WRITE_PCM_DIR;
    private FileInputStream mFis;
    private FileOutputStream mFos;

    public FileUtil(String str) {
        this.WRITE_PCM_DIR = "";
        this.WRITE_PCM_DIR = str;
    }

    public boolean openPcmFile(String str) {
        try {
            this.mFis = new FileInputStream(new File(str));
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            this.mFis = null;
            return false;
        }
    }

    public int read(byte[] bArr) {
        FileInputStream fileInputStream = this.mFis;
        if (fileInputStream == null) {
            return -1;
        }
        try {
            return fileInputStream.read(bArr);
        } catch (IOException e) {
            e.printStackTrace();
            closeReadFile();
            return 0;
        }
    }

    public void closeReadFile() {
        FileInputStream fileInputStream = this.mFis;
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
                this.mFis = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void createPcmFile() {
        File file = new File(this.WRITE_PCM_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (this.mFos != null) {
            return;
        }
        File file2 = new File(this.WRITE_PCM_DIR + new SimpleDateFormat("MM-dd-hh-mm-ss", Locale.CHINA).format(new Date()) + ".pcm");
        try {
            if (file2.createNewFile()) {
                this.mFos = new FileOutputStream(file2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(byte[] bArr) {
        synchronized (this) {
            if (this.mFos != null) {
                try {
                    this.mFos.write(bArr);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void write(byte[] bArr, int i, int i2) {
        synchronized (this) {
            if (this.mFos != null) {
                try {
                    this.mFos.write(bArr, i, i2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void closeWriteFile() {
        synchronized (this) {
            if (this.mFos != null) {
                try {
                    this.mFos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.mFos = null;
            }
        }
    }

    public static void CopyAssets(Context context, String str, String str2) {
        try {
            String[] list = context.getAssets().list(str);
            if (list.length > 0) {
                new File(str2).mkdirs();
                for (String str3 : list) {
                    CopyAssets(context, str + "/" + str3, str2 + "/" + str3);
                }
                return;
            }
            InputStream open = context.getAssets().open(str);
            FileOutputStream fileOutputStream = new FileOutputStream(new File(str2));
            byte[] bArr = new byte[1024];
            while (true) {
                int read = open.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.flush();
                    open.close();
                    fileOutputStream.close();
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
