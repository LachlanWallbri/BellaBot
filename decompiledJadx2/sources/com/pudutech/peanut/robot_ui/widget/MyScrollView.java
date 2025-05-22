package com.pudutech.peanut.robot_ui.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class MyScrollView extends ScrollView {
    private long DELAY_TIME;
    private Handler handler;
    private long lastScrollTime;
    private int lastScrollY;
    private float lastX;
    private float lastY;
    private int mCurrentY;
    private List<OnScrollStateListener> mOnScrollStateListeners;
    private OnScrollToBottomListener mOnScrollToBottom;
    private List<OnScrollListener> onScrollListeners;
    private Runnable scrollTask;
    private float xDistance;
    private float yDistance;

    /* loaded from: classes5.dex */
    public interface OnScrollListener {
        void onScroll(int i);

        void onScrollPosition(int i);

        void onTouchEvent(MotionEvent motionEvent);
    }

    /* loaded from: classes5.dex */
    public interface OnScrollStateListener {
        void onScrollStart();

        void onScrollStop();
    }

    /* loaded from: classes5.dex */
    public interface OnScrollToBottomListener {
        void onScrollBottom();
    }

    private void onScrollStart() {
        List<OnScrollStateListener> list = this.mOnScrollStateListeners;
        if (list == null) {
            return;
        }
        Iterator<OnScrollStateListener> it = list.iterator();
        while (it.hasNext()) {
            it.next().onScrollStart();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onScrollStop() {
        List<OnScrollStateListener> list = this.mOnScrollStateListeners;
        if (list == null) {
            return;
        }
        Iterator<OnScrollStateListener> it = list.iterator();
        while (it.hasNext()) {
            it.next().onScrollStop();
        }
    }

    public MyScrollView(Context context) {
        super(context);
        this.DELAY_TIME = 100L;
        this.lastScrollTime = -1L;
        this.handler = new Handler() { // from class: com.pudutech.peanut.robot_ui.widget.MyScrollView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int scrollY = MyScrollView.this.getScrollY();
                if (MyScrollView.this.lastScrollY != scrollY) {
                    MyScrollView.this.lastScrollY = scrollY;
                    MyScrollView.this.handler.sendMessageDelayed(MyScrollView.this.handler.obtainMessage(), 5L);
                }
                if (MyScrollView.this.onScrollListeners != null) {
                    Iterator it = MyScrollView.this.onScrollListeners.iterator();
                    while (it.hasNext()) {
                        ((OnScrollListener) it.next()).onScroll(scrollY);
                    }
                }
            }
        };
        this.scrollTask = new Runnable() { // from class: com.pudutech.peanut.robot_ui.widget.MyScrollView.2
            @Override // java.lang.Runnable
            public void run() {
                if (System.currentTimeMillis() - MyScrollView.this.lastScrollTime > MyScrollView.this.DELAY_TIME) {
                    MyScrollView.this.lastScrollTime = -1L;
                    MyScrollView.this.onScrollStop();
                } else {
                    MyScrollView.this.handler.postDelayed(this, MyScrollView.this.DELAY_TIME);
                }
            }
        };
    }

    public MyScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.DELAY_TIME = 100L;
        this.lastScrollTime = -1L;
        this.handler = new Handler() { // from class: com.pudutech.peanut.robot_ui.widget.MyScrollView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int scrollY = MyScrollView.this.getScrollY();
                if (MyScrollView.this.lastScrollY != scrollY) {
                    MyScrollView.this.lastScrollY = scrollY;
                    MyScrollView.this.handler.sendMessageDelayed(MyScrollView.this.handler.obtainMessage(), 5L);
                }
                if (MyScrollView.this.onScrollListeners != null) {
                    Iterator it = MyScrollView.this.onScrollListeners.iterator();
                    while (it.hasNext()) {
                        ((OnScrollListener) it.next()).onScroll(scrollY);
                    }
                }
            }
        };
        this.scrollTask = new Runnable() { // from class: com.pudutech.peanut.robot_ui.widget.MyScrollView.2
            @Override // java.lang.Runnable
            public void run() {
                if (System.currentTimeMillis() - MyScrollView.this.lastScrollTime > MyScrollView.this.DELAY_TIME) {
                    MyScrollView.this.lastScrollTime = -1L;
                    MyScrollView.this.onScrollStop();
                } else {
                    MyScrollView.this.handler.postDelayed(this, MyScrollView.this.DELAY_TIME);
                }
            }
        };
    }

    public MyScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.DELAY_TIME = 100L;
        this.lastScrollTime = -1L;
        this.handler = new Handler() { // from class: com.pudutech.peanut.robot_ui.widget.MyScrollView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int scrollY = MyScrollView.this.getScrollY();
                if (MyScrollView.this.lastScrollY != scrollY) {
                    MyScrollView.this.lastScrollY = scrollY;
                    MyScrollView.this.handler.sendMessageDelayed(MyScrollView.this.handler.obtainMessage(), 5L);
                }
                if (MyScrollView.this.onScrollListeners != null) {
                    Iterator it = MyScrollView.this.onScrollListeners.iterator();
                    while (it.hasNext()) {
                        ((OnScrollListener) it.next()).onScroll(scrollY);
                    }
                }
            }
        };
        this.scrollTask = new Runnable() { // from class: com.pudutech.peanut.robot_ui.widget.MyScrollView.2
            @Override // java.lang.Runnable
            public void run() {
                if (System.currentTimeMillis() - MyScrollView.this.lastScrollTime > MyScrollView.this.DELAY_TIME) {
                    MyScrollView.this.lastScrollTime = -1L;
                    MyScrollView.this.onScrollStop();
                } else {
                    MyScrollView.this.handler.postDelayed(this, MyScrollView.this.DELAY_TIME);
                }
            }
        };
    }

    public void setOnScrollStateListener(OnScrollStateListener onScrollStateListener) {
        if (this.mOnScrollStateListeners == null) {
            this.mOnScrollStateListeners = new ArrayList();
        }
        if (onScrollStateListener != null) {
            this.mOnScrollStateListeners.add(onScrollStateListener);
        }
    }

    public void removeScrollStateListener(OnScrollStateListener onScrollStateListener) {
        List<OnScrollStateListener> list;
        if (onScrollStateListener == null || (list = this.mOnScrollStateListeners) == null) {
            return;
        }
        list.remove(onScrollStateListener);
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        if (this.onScrollListeners == null) {
            this.onScrollListeners = new ArrayList();
        }
        this.onScrollListeners.add(onScrollListener);
    }

    public void removeAllScrollListener() {
        List<OnScrollListener> list = this.onScrollListeners;
        if (list != null) {
            list.clear();
        }
    }

    public void setOnScrollToBottomListener(OnScrollToBottomListener onScrollToBottomListener) {
        this.mOnScrollToBottom = onScrollToBottomListener;
    }

    @Override // android.widget.ScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.yDistance = 0.0f;
            this.xDistance = 0.0f;
            this.lastX = motionEvent.getX();
            this.lastY = motionEvent.getY();
        } else if (action == 2) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            this.xDistance += Math.abs(x - this.lastX);
            this.yDistance += Math.abs(y - this.lastY);
            this.lastX = x;
            this.lastY = y;
            if (this.xDistance > this.yDistance) {
                return false;
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.ScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        List<OnScrollListener> list = this.onScrollListeners;
        if (list != null) {
            for (OnScrollListener onScrollListener : list) {
                int scrollY = getScrollY();
                this.lastScrollY = scrollY;
                onScrollListener.onScroll(scrollY);
                onScrollListener.onTouchEvent(motionEvent);
            }
        }
        if (motionEvent.getAction() == 1) {
            Handler handler = this.handler;
            handler.sendMessageDelayed(handler.obtainMessage(), 20L);
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View
    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        List<OnScrollListener> list = this.onScrollListeners;
        if (list != null) {
            Iterator<OnScrollListener> it = list.iterator();
            while (it.hasNext()) {
                it.next().onScrollPosition(getScrollY());
            }
        }
        if (this.lastScrollTime == -1) {
            onScrollStart();
            postDelayed(this.scrollTask, this.DELAY_TIME);
        }
        this.lastScrollTime = System.currentTimeMillis();
        super.onScrollChanged(i, i2, i3, i4);
    }

    @Override // android.widget.ScrollView, android.view.View
    protected void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        OnScrollToBottomListener onScrollToBottomListener;
        super.onOverScrolled(i, i2, z, z2);
        if (i2 > this.mCurrentY && i2 > 0 && z2 && (onScrollToBottomListener = this.mOnScrollToBottom) != null) {
            onScrollToBottomListener.onScrollBottom();
        }
        this.mCurrentY = i2;
    }
}
