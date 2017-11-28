package ru.stqa.pft.sandbox;

public class Point {
  double x, y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double distance(Point p2) {
    double difX = this.x - p2.x;
    double difY = this.y - p2.y;
    return Math.sqrt(Math.pow(difX, 2) + Math.pow(difY, 2));
  }
}
