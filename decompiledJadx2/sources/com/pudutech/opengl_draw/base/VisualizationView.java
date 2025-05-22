package com.pudutech.opengl_draw.base;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.pudutech.opengl_draw.geometry.FrameTransformTree;
import com.pudutech.opengl_draw.namespace.GraphName;
import com.pudutech.opengl_draw.node.NodeMain;
import com.pudutech.opengl_draw.node.NodeMainExecutor;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes5.dex */
public class VisualizationView extends GLSurfaceView implements NodeMain {
    private static final boolean DEBUG = false;
    private final XYOrthographicCamera camera;
    private final FrameTransformTree frameTransformTree;
    private List<Layer> layers;
    private final Object mutex;
    private XYOrthographicRenderer renderer;

    private void startTransformListener() {
    }

    @Override // com.pudutech.opengl_draw.node.NodeListener
    public void onError() {
    }

    @Override // com.pudutech.opengl_draw.node.NodeListener
    public void onShutdownComplete() {
    }

    public VisualizationView(Context context) {
        super(context);
        this.mutex = new Object();
        this.frameTransformTree = new FrameTransformTree();
        this.camera = new XYOrthographicCamera(this.frameTransformTree);
    }

    public VisualizationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mutex = new Object();
        this.frameTransformTree = new FrameTransformTree();
        this.camera = new XYOrthographicCamera(this.frameTransformTree);
    }

    public void onCreate(CopyOnWriteArrayList<Layer> copyOnWriteArrayList, int i, int i2) {
        this.layers = copyOnWriteArrayList;
        setDebugFlags(1);
        setEGLConfigChooser(8, 8, 8, 8, 0, 0);
        getHolder().setFormat(-3);
        this.camera.setMid(i, i2);
        this.renderer = new XYOrthographicRenderer(this);
        setRenderer(this.renderer);
    }

    public void init(NodeMainExecutor nodeMainExecutor) {
        Iterator<Layer> it = this.layers.iterator();
        while (it.hasNext()) {
            it.next().init(nodeMainExecutor);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        Iterator<Layer> it = this.layers.iterator();
        while (it.hasNext()) {
            if (it.next().onTouchEvent(this, motionEvent)) {
                return true;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // com.pudutech.opengl_draw.node.NodeMain
    public GraphName getDefaultNodeName() {
        return GraphName.m3302of("android_15/visualization_view");
    }

    public XYOrthographicRenderer getRenderer() {
        return this.renderer;
    }

    public XYOrthographicCamera getCamera() {
        return this.camera;
    }

    public FrameTransformTree getFrameTransformTree() {
        return this.frameTransformTree;
    }

    public List<Layer> getLayers() {
        return Collections.unmodifiableList(this.layers);
    }

    @Override // com.pudutech.opengl_draw.node.NodeListener
    public void onStart() {
        startTransformListener();
        startLayers();
    }

    private void startLayers() {
        Iterator<Layer> it = this.layers.iterator();
        while (it.hasNext()) {
            it.next().onStart(this);
        }
    }

    public void startLayer(Layer layer) {
        layer.onStart(this);
    }

    public void addLayer(Layer layer) {
        this.layers.add(layer);
    }

    public void removeLayer(String str) {
        for (int i = 0; i < this.layers.size(); i++) {
            if (this.layers.get(i).getName() != null && this.layers.get(i).getName().equals(str)) {
                this.layers.get(i).setEvent(false);
                this.layers.remove(i);
            }
        }
    }

    public void removeLayer(Layer layer) {
        this.layers.remove(layer);
    }

    @Override // com.pudutech.opengl_draw.node.NodeListener
    public void onShutdown() {
        Iterator<Layer> it = this.layers.iterator();
        while (it.hasNext()) {
            it.next().onShutdown(this);
        }
    }
}
