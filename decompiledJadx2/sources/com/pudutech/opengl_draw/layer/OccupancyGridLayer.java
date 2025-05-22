package com.pudutech.opengl_draw.layer;

import android.graphics.Bitmap;
import android.util.Log;
import androidx.core.util.Preconditions;
import com.pudutech.opengl_draw.base.TextureBitmap;
import com.pudutech.opengl_draw.base.TfLayer;
import com.pudutech.opengl_draw.base.VisualizationView;
import com.pudutech.opengl_draw.bean.OccupancyGrid;
import com.pudutech.opengl_draw.geometry.Quaternion;
import com.pudutech.opengl_draw.geometry.Transform;
import com.pudutech.opengl_draw.geometry.Vector3;
import com.pudutech.opengl_draw.namespace.GraphName;
import com.pudutech.opengl_draw.node.NodeMainExecutor;
import com.pudutech.opengl_draw.util.MessageBuffers;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.opengles.GL10;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.HeapChannelBufferFactory;

/* loaded from: classes5.dex */
public class OccupancyGridLayer extends DefaultLayer implements TfLayer {
    private static final int COLOR_FREE = -16759655;
    private static final int COLOR_OCCUPIED = -15195085;
    private static final int COLOR_TRANSPARENT = 0;
    private static final int COLOR_UNKNOWN = 0;
    private GraphName frame;
    private GL10 previousGl;
    private String TAG = "OccupancyGridLayer";
    private final List<Tile> tiles = new CopyOnWriteArrayList();
    private final Object mutex = new Object();
    private boolean ready = false;

    /* loaded from: classes5.dex */
    public interface OccupancyOneListener {
        void onSuccess();
    }

    @Override // com.pudutech.opengl_draw.layer.DefaultLayer, com.pudutech.opengl_draw.base.Layer
    public void init(NodeMainExecutor nodeMainExecutor) {
    }

    /* loaded from: classes5.dex */
    private class Tile {
        private Transform origin;
        private final float resolution;
        private int stride;
        private final ChannelBuffer pixelBuffer = MessageBuffers.dynamicBuffer();
        private final TextureBitmap textureBitmap = new TextureBitmap();
        private boolean ready = false;

        public Tile(float f) {
            this.resolution = f;
        }

        public void draw(VisualizationView visualizationView, GL10 gl10) {
            if (this.ready) {
                this.textureBitmap.draw(visualizationView, gl10);
            }
        }

        public void clearHandle() {
            this.textureBitmap.clearHandle();
        }

        public void writeInt(int i) {
            this.pixelBuffer.writeInt(i);
        }

        public void update() {
            Preconditions.checkNotNull(this.origin);
            Preconditions.checkNotNull(Integer.valueOf(this.stride));
            this.textureBitmap.updateFromPixelBuffer(this.pixelBuffer, this.stride, this.resolution, this.origin, 0);
            this.pixelBuffer.clear();
            this.ready = true;
        }

        public void setOrigin(Transform transform) {
            this.origin = transform;
        }

        public void setStride(int i) {
            this.stride = i;
        }
    }

    @Override // com.pudutech.opengl_draw.layer.DefaultLayer, com.pudutech.opengl_draw.base.OpenGlDrawable
    public void draw(VisualizationView visualizationView, GL10 gl10) {
        if (this.previousGl != gl10) {
            Iterator<Tile> it = this.tiles.iterator();
            while (it.hasNext()) {
                it.next().clearHandle();
            }
            this.previousGl = gl10;
        }
        if (this.ready) {
            Iterator<Tile> it2 = this.tiles.iterator();
            while (it2.hasNext()) {
                it2.next().draw(visualizationView, gl10);
            }
        }
    }

    @Override // com.pudutech.opengl_draw.base.TfLayer
    public GraphName getFrame() {
        return this.frame;
    }

    @Override // com.pudutech.opengl_draw.layer.DefaultLayer, com.pudutech.opengl_draw.base.Layer
    public void onStart(VisualizationView visualizationView) {
        super.onStart(visualizationView);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0133 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00ef A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void update(byte[] bArr, int i, int i2, OccupancyGrid occupancyGrid, OccupancyOneListener occupancyOneListener) {
        float f;
        int i3;
        synchronized (this.mutex) {
            this.frame = GraphName.m3302of("OccupancyGridLayer");
            String str = this.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("bytes.length :");
            sb.append(bArr.length);
            sb.append(" width*height");
            int i4 = i * i2;
            sb.append(i4);
            Log.d(str, sb.toString());
            if (bArr.length < i4) {
                return;
            }
            float f2 = 1024.0f;
            int ceil = (int) Math.ceil(i / 1024.0f);
            int ceil2 = (int) Math.ceil(i2 / 1024.0f);
            int i5 = ceil * ceil2;
            Transform fromPoseMessage = Transform.fromPoseMessage(occupancyGrid);
            while (true) {
                f = 0.05f;
                if (this.tiles.size() >= i5) {
                    break;
                } else {
                    this.tiles.add(new Tile(0.05f));
                }
            }
            int i6 = 0;
            while (i6 < ceil2) {
                int i7 = 0;
                while (i7 < ceil) {
                    int i8 = (i6 * ceil) + i7;
                    int i9 = ceil2;
                    int i10 = i7;
                    this.tiles.get(i8).setOrigin(fromPoseMessage.multiply(new Transform(new Vector3(i7 * f * f2, i6 * f * 1024.0f, 0.0d), new Quaternion(0.0d, 0.0d, 0.0d, 1.0d))));
                    if (i10 < ceil - 1) {
                        this.tiles.get(i8).setStride(1024);
                    } else {
                        this.tiles.get(i8).setStride(i % 1024);
                    }
                    i7 = i10 + 1;
                    f2 = 1024.0f;
                    ceil2 = i9;
                    f = 0.05f;
                }
                i6++;
                f = 0.05f;
            }
            ChannelBuffer buffer = HeapChannelBufferFactory.getInstance().getBuffer(bArr, 0, bArr.length);
            int i11 = 0;
            int i12 = 0;
            while (buffer.readable()) {
                int i13 = ((i11 / 1024) * ceil) + (i12 / 1024);
                byte readByte = buffer.readByte();
                if (readByte == -1) {
                    this.tiles.get(i13).writeInt(COLOR_OCCUPIED);
                } else if (readByte == 0) {
                    this.tiles.get(i13).writeInt(COLOR_FREE);
                } else {
                    i3 = 0;
                    this.tiles.get(i13).writeInt(0);
                    i12++;
                    if (i12 != i) {
                        i11++;
                        i12 = i3;
                    }
                }
                i3 = 0;
                i12++;
                if (i12 != i) {
                }
            }
            Iterator<Tile> it = this.tiles.iterator();
            while (it.hasNext()) {
                it.next().update();
            }
            if (occupancyOneListener != null) {
                occupancyOneListener.onSuccess();
            }
            this.ready = true;
        }
    }

    public void update(Bitmap bitmap, OccupancyGrid occupancyGrid, OccupancyOneListener occupancyOneListener) {
        float f;
        int i;
        synchronized (this.mutex) {
            this.frame = GraphName.m3302of("OccupancyGridLayer");
            Log.d(this.TAG, "update");
            if (bitmap == null) {
                return;
            }
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            float f2 = 1024.0f;
            int ceil = (int) Math.ceil(width / 1024.0f);
            int ceil2 = (int) Math.ceil(height / 1024.0f);
            int i2 = ceil * ceil2;
            int[] iArr = new int[width * height];
            Transform fromPoseMessage = Transform.fromPoseMessage(occupancyGrid);
            bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
            bitmap.recycle();
            while (true) {
                f = 0.05f;
                if (this.tiles.size() >= i2) {
                    break;
                } else {
                    this.tiles.add(new Tile(0.05f));
                }
            }
            int i3 = 0;
            while (i3 < ceil2) {
                int i4 = 0;
                while (i4 < ceil) {
                    int i5 = (i3 * ceil) + i4;
                    int i6 = ceil;
                    Transform transform = fromPoseMessage;
                    this.tiles.get(i5).setOrigin(transform.multiply(new Transform(new Vector3(i4 * f * f2, i3 * f * 1024.0f, 0.0d), Quaternion.identity())));
                    if (i4 < i6 - 1) {
                        this.tiles.get(i5).setStride(1024);
                    } else {
                        this.tiles.get(i5).setStride(width % 1024);
                    }
                    i4++;
                    fromPoseMessage = transform;
                    f2 = 1024.0f;
                    ceil = i6;
                    f = 0.05f;
                }
                i3++;
                f = 0.05f;
            }
            int i7 = ceil;
            int length = iArr.length;
            int i8 = 0;
            int i9 = 0;
            int i10 = 0;
            while (true) {
                boolean z = true;
                if (i8 >= length) {
                    break;
                }
                int i11 = iArr[i8];
                if (i9 >= height) {
                    z = false;
                }
                Preconditions.checkState(z);
                int i12 = ((i9 / 1024) * i7) + (i10 / 1024);
                if (i11 == -1) {
                    this.tiles.get(i12).writeInt(COLOR_OCCUPIED);
                    i = 0;
                } else if (i11 == -8093052) {
                    i = 0;
                    this.tiles.get(i12).writeInt(0);
                } else {
                    i = 0;
                    this.tiles.get(i12).writeInt(COLOR_FREE);
                }
                i10++;
                if (i10 == width) {
                    i9++;
                    i10 = i;
                }
                i8++;
            }
            Iterator<Tile> it = this.tiles.iterator();
            while (it.hasNext()) {
                it.next().update();
            }
            if (occupancyOneListener != null) {
                occupancyOneListener.onSuccess();
            }
            this.ready = true;
        }
    }
}
