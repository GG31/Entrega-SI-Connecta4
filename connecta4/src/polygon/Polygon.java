package polygon;

import java.util.ArrayList;

public class Polygon {
	private Point[] points;

	public Polygon(ArrayList<Point> points) {
		this.points = new Point[points.size() + 1];
		for (int i = 0; i < points.size(); i++) {
			this.points[i] = points.get(i);
		}
		this.points[points.size()] = this.points[0];
	}

	public Point centroid() {
		float cx = 0, cy = 0;
		for (int i = 0; i < this.points.length-1; i++) {
			cx = cx
					+ (points[i].getX() + points[i + 1].getX())
					* (points[i].getY() * points[i + 1].getX() - points[i]
							.getX() * points[i + 1].getY());
			cy = cy
					+ (points[i].getY() + points[i + 1].getY())
					* (points[i].getY() * points[i + 1].getX() - points[i]
							.getX() * points[i + 1].getY());
		}
		cx /= (float) (6 * area());
		cy /= (float) (6 * area());
		return new Point(cx, cy);
	}

	// return area of polygon
	public double area() {
		return Math.abs(signedArea());
	}

	// return signed area of polygon
	public double signedArea() {
		double sum = 0.0;
		for (int i = 0; i < this.points.length-1; i++) {
			sum = sum + (points[i].getX() * points[i + 1].getY())
					- (points[i].getY() * points[i + 1].getX());
		}
		return 0.5 * sum;
	}

	public Point[] getPoints() {
		return points;
	}

	public String toString() {
		String s = "";
		for (int i = 0; i < this.points.length; i++) {
			s += "(" + points[i].getX() + ", " + points[i].getY() + ") ";
		}
		return s;
	}

}
