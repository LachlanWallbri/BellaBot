#!/usr/bin/env python

import rospy
from std_msgs.msg import Float64, Int32
from bellabot.msg import Bellabot

class BellabotNode:
    def __init__(self):
        # Initialize the ROS node
        rospy.init_node('bellabot_pub', anonymous=True)

        # Subscribers
        rospy.Subscriber('/bellabot/linear_speed', Float64, self.linear_speed_callback)
        rospy.Subscriber('/bellabot/angular_speed', Float64, self.angular_speed_callback)
        rospy.Subscriber('/bellabot/encoder_linear', Float64, self.encoderL_callback)
        rospy.Subscriber('/bellabot/encoder_angular', Float64, self.encoderA_callback)
        rospy.Subscriber('/bellabot/max_speed', Float64, self.max_speed_callback)
        rospy.Subscriber('/bellabot/battery', Int32, self.battery_callback)

        # Publisher
        self.pub = rospy.Publisher('/bellabot', Bellabot, queue_size=10)

        # Variables to store the latest speeds
        self.name = "Bellabot"
        self.battery = 100
        self.linear_speed = 0.0
        self.angular_speed = 0.0
        self.encoder_linear = 0.0
        self.encoder_angular = 0.0
        self.max_speed = 0.0

        # Publish at a rate of 10 Hz
        self.rate = rospy.Rate(10)

    def linear_speed_callback(self, msg):
        self.linear_speed = msg.data

    def angular_speed_callback(self, msg):
        self.angular_speed = msg.data

    def battery_callback(self, msg):
        self.battery = msg.data

    def encoderL_callback(self, msg):
        self.encoder_linear = msg.data

    def encoderA_callback(self, msg):
        self.encoder_angular = msg.data

    def max_speed_callback(self, msg):
        self.max_speed = msg.data

    def run(self):
        while not rospy.is_shutdown():
            # Create the custom message
            msg = Bellabot()
            msg.name.data = self.name
            msg.battery = self.battery
            msg.inputLinearSpeed = self.linear_speed
            msg.inputAngularSpeed = self.angular_speed
            msg.actualLinearSpeed = self.encoder_linear
            msg.actualAngularSpeed = self.encoder_angular
            msg.maxSpeed = self.max_speed

            # Publish the message
            self.pub.publish(msg)

            # Sleep to maintain the 10 Hz rate
            self.rate.sleep()

if __name__ == '__main__':
    try:
        node = BellabotNode()
        node.run()
    except rospy.ROSInterruptException:
        pass
