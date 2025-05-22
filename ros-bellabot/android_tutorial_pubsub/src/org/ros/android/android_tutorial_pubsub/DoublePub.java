package org.ros.android.android_tutorial_pubsub;

import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.topic.Publisher;
import std_msgs.Float64;
import android.util.Log;

public class DoublePub extends AbstractNodeMain {
    private String topic_name;
    private Publisher<std_msgs.Float64> publisher;
    private static final String TAG = "DoublePublisher";

    public DoublePub() {
        this.topic_name = "double";
    }

    public DoublePub(String topic) {
        this.topic_name = topic;
    }

    @Override
    public GraphName getDefaultNodeName() {
        return GraphName.of("bellabot/" + this.topic_name);
    }

    public void onStart(ConnectedNode connectedNode) {
        publisher = connectedNode.newPublisher(this.topic_name, Float64._TYPE);
        Log.d(TAG, "Publisher started for topic: " + this.topic_name);
    }

    public synchronized void publishDouble(double doubleToPub) {
        if (publisher != null) {
            std_msgs.Float64 msg = publisher.newMessage();
            msg.setData(doubleToPub);
            publisher.publish(msg);
            Log.d(TAG, "Published double: " + doubleToPub + " to topic: " + topic_name);
        } else {
            Log.e(TAG, "Publisher not initialized for topic: " + topic_name);
        }
    }
}
