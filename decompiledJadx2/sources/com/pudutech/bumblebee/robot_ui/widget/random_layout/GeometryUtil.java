package com.pudutech.bumblebee.robot_ui.widget.random_layout;

import android.graphics.Point;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes4.dex */
public class GeometryUtil {
    static List points = new LinkedList();

    public static float getDistanceBetween2Points(ChildViewBound childViewBound, Point point) {
        Point point2 = new Point(childViewBound.getChildLeft(), childViewBound.getChildTop());
        return (float) Math.sqrt(Math.pow(point2.y - point.y, 2.0d) + Math.pow(point2.x - point.x, 2.0d));
    }

    public static float getDistanceBetween2Points(int i, int i2, Point point) {
        Point point2 = new Point(i, i2);
        return (float) Math.sqrt(Math.pow(point2.y - point.y, 2.0d) + Math.pow(point2.x - point.x, 2.0d));
    }

    public static float evaluateValue(float f, Number number, Number number2) {
        return number.floatValue() + ((number2.floatValue() - number.floatValue()) * f);
    }

    public static float caculateDx(ChildViewBound childViewBound, Point point) {
        return point.x - childViewBound.getChildLeft();
    }

    public static float caculateDy(ChildViewBound childViewBound, Point point) {
        return point.y - childViewBound.getChildTop();
    }

    public static void initPointsCircular(Point point) {
        for (int i = 0; i < 360; i++) {
            double d = ((i - 90) * 3.141592653589793d) / 180.0d;
            points.add(new Point((int) (point.x - (Math.sin(d) * 250.0d)), (int) (((point.y - 250) - 10) + (Math.cos(d) * 250.0d))));
        }
    }

    public static Point cul(ChildViewBound childViewBound, Point point) {
        int childLeft = (childViewBound.getChildLeft() - point.x) / (childViewBound.getChildTop() - point.y);
        for (int i = 0; i < points.size(); i++) {
            Point point2 = (Point) points.get(i);
            if ((point2.x - 250) / (point2.y - 250) == childLeft) {
                return point2;
            }
        }
        return point;
    }

    public static Point getPoint(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        double d = (i7 - i5) / (i6 - i4);
        double d2 = i7 - (i6 * d);
        double d3 = i2;
        double d4 = d2 - d3;
        int i8 = i3 * i3;
        double d5 = (d * d) + 1.0d;
        double d6 = (i * 2) - ((d * 2.0d) * d4);
        double sqrt = Math.sqrt((d6 * d6) - ((4.0d * d5) * (((i * i) + (d4 * d4)) - i8)));
        double d7 = d5 * 2.0d;
        double d8 = (d6 + sqrt) / d7;
        double d9 = (d * d8) + d2;
        double d10 = (d6 - sqrt) / d7;
        double d11 = (d * d10) + d2;
        double d12 = d8 - i;
        double d13 = d9 - d3;
        Point point = new Point();
        if (((int) ((d12 * d12) + (d13 * d13))) == i8) {
            point.x = (int) d8;
            point.y = (int) d9;
        } else {
            point.x = (int) d10;
            point.y = (int) d11;
        }
        if (i4 > i6) {
            point.x = (int) d8;
            point.y = (int) d9;
        } else {
            point.x = (int) d10;
            point.y = (int) d11;
        }
        return point;
    }
}
