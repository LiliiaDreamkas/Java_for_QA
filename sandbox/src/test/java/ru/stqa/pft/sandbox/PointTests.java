package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistanceFirstQarter() {
    testFunction(2, 2, 6, 5, 5);
  }

  @Test
  public void testDistanceSecondQarter() {
    testFunction(0, 0, -3, 4, 5);
  }

  @Test
  public void testDistanceThirdQarter() {
    testFunction(-2, -2, -6, -5, 5);
  }

  @Test
  public void testDistanceFourthQarter() {
    testFunction(0, 0, 3, -4, 5);
  }

  @Test
  public void testDistanceNull() {
    testFunction(0, 0, 0, 0, 0);
  }

  public static void testFunction(double x1, double y1, double x2, double y2, double result) {
    Point p1 = new Point(x1, y1);
    Point p2 = new Point(x2, y2);
    double distance = p1.distance(p2);
    Assert.assertEquals(distance, result);
  }
}
