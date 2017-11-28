package ru.stqa.pft.sandbox;

public class MyFirstProgram{

	public static void main(String[] args){
		hello("world");
		hello("user");
		hello("Liliia");

		Square s = new Square(5);
		System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

		Rectangle r = new Rectangle(4, 6);
		System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());
		
		Point p1 = new Point(2, 3);
		Point p2 = new Point(4, 5);
		System.out.println("Расстояние между точками = " +distance(p1, p2));
	}

	public static void hello(String somebody){
		System.out.println("Hello, " + somebody + "!");
	}

	public static double distance(Point p1, Point p2) {
		double difX = p2.x - p1.x;
		double difY = p2.y - p1.y;
		return Math.sqrt(Math.pow(difX, 2) + Math.pow(difY, 2));
	}
}