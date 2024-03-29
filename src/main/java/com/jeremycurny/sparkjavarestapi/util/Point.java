package com.jeremycurny.sparkjavarestapi.util;

import org.json.simple.JSONObject;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Point {
	public Integer x;
	public Integer y;

	public Point() {
	}

	public Point(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}

	public Point add(Point point) {
		return new Point(point.x + x, point.y + y);
	}

	public Point sub(Point point) {
		return new Point(x - point.x, y - point.y);
	}

	public Double distance(Point p1, Point p2) {
		return sqrt(pow(p1.x - p2.x, 2) + pow(p1.y - p2.y, 2));
	}

	public String toJson() {
		JSONObject obj = new JSONObject();

		obj.put("X", x);
		obj.put("Y", y);

		return obj.toJSONString();
	}

    public void fromJson(JSONObject point) {
        x = Integer.valueOf(point.get("X").toString());
        y = Integer.valueOf(point.get("Y").toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;

        Point point = (Point) o;

        if (!x.equals(point.x)) return false;
        return y.equals(point.y);
    }

    @Override
    public int hashCode() {
        int result = x.hashCode();
        result = 31 * result + y.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "("+ x + ","+ y +")";
    }
}
