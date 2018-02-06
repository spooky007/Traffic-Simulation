import java.awt.Color;
import java.awt.Graphics;

public class Vehicle {
	// co-ordinates
	int x;
	int y;
	
	// length(width) and height of lanes
	int width = 0;
	int height = 0;
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public int getSpeed() {
		return speed;
	}

	int speed = 0;
	
	public Vehicle(int newx, int newy) {
		x = newx;
		y = newy;
	}
	
	public void paintMe(Graphics g) {

	}
}
