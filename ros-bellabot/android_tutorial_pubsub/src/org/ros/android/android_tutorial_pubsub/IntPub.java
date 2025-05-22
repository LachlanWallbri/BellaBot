package org.ros.android.android_tutorial_pubsub;

import org.ros.namespace.GraphName;
import org.ros.node.AbstractNodeMain;
import org.ros.node.ConnectedNode;
import org.ros.node.topic.Publisher;
import std_msgs.Int32;
import android.util.Log;

public class IntPub extends AbstractNodeMain {
    private String topic_name;
    private Publisher<std_msgs.Int32> publisher;
    private static final String TAG = "IntPublisher";

    public IntPub() {
        this.topic_name = "int";
    }

    public IntPub(String topic) {
        this.topic_name = topic;
    }

    @Override
    public GraphName getDefaultNodeName() {
        return GraphName.of("bellabot/" + this.topic_name);
    }

    public void onStart(ConnectedNode connectedNode) {
        publisher = connectedNode.newPublisher(this.topic_name, Int32._TYPE);
        Log.d(TAG, "Publisher started for topic: " + this.topic_name);
    }

    public synchronized void publishInt(int intToPub) {
        if (publisher != null) {
            std_msgs.Int32 msg = publisher.newMessage();
            msg.setData(intToPub);
            publisher.publish(msg);
            Log.d(TAG, "Published: " + intToPub + " to topic: " + topic_name);
        } else {
            Log.e(TAG, "Publisher not initialized for topic: " + topic_name);
        }
    }
}
