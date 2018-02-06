import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Traffic implements ActionListener, Runnable {
	JFrame frame = new JFrame("Traffic");
	Road road = new Road();
	
	//south container
	JButton start = new JButton("start");
	JButton stop = new JButton("stop");
	JButton clear = new JButton("clear");
	JLabel throughput = new JLabel("throughtput:0");
	Container south = new Container();
	
	//west container
	JButton semi = new JButton("Add Semi");
	JButton suv = new JButton("Add SUV");
	JButton sportsCar = new JButton("Add Sports Car");
	Container west = new Container();
	
	boolean running = false;
	int carCount = 0;
	long startTime = 0;

	
	public Traffic() {
		frame.setSize(1000, 550);
		frame.setLayout(new BorderLayout());
		frame.add(road, BorderLayout.CENTER);
		
		south.setLayout(new GridLayout(1,4));
		south.add(start);
		start.addActionListener(this);
		south.add(stop);
		stop.addActionListener(this);
		south.add(clear);
		stop.addActionListener(this);
		south.add(throughput);
//		stop.addActionListener(this);
		frame.add(south, BorderLayout.SOUTH);
		
		west.setLayout(new GridLayout(3,1));
		west.add(semi);
		semi.addActionListener(this);
		west.add(suv);
		suv.addActionListener(this);
		west.add(sportsCar);
		sportsCar.addActionListener(this);
		frame.add(west, BorderLayout.EAST);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(start)) {
			if (running == false)
			running = true;
			road.resetCarCount();
			startTime = System.currentTimeMillis();
			Thread t = new Thread(this);
			t.start();
		}
		if (event.getSource().equals(stop)){
			running = false;
		}
		if (event.getSource().equals(clear)){
			road.clear();
			frame.repaint();
		}
		
		if (event.getSource().equals(semi)) {
			Semi semi = new Semi(0, 30);
			road.addCar(semi);
			for (int x = 0; x < road.ROAD_WIDTH; x += 20) {
				for (int y = 40; y < 480; y += 120) {
					semi.setX(x);
					semi.setY(y);
					if(road.collision(x, y, semi) == false) {
						frame.repaint();
						return;
					}
				}
			}
		}
		if (event.getSource().equals(suv)) {
			SUV suv = new SUV(0, 30);
			road.addCar(suv);
			for (int x = 0; x < road.ROAD_WIDTH; x += 20) {
				for (int y = 40; y < 480; y += 120) {
					suv.setX(x);
					suv.setY(y);
					if(road.collision(x, y, suv) == false) {
						frame.repaint();
						return;
					}
				}
			}
		}
		if (event.getSource().equals(sportsCar)) {
			SportsCar sportsCar = new SportsCar(0, 30);
			road.addCar(sportsCar);
			for (int x = 0; x < road.ROAD_WIDTH; x += 20) {
				for (int y = 40; y < 480; y += 120) {
					sportsCar.setX(x);
					sportsCar.setY(y);
					if(road.collision(x, y, sportsCar) == false) {
						frame.repaint();
						return;
					}
				}
			}
		}
		
	}
	
	@Override
	public void run() {
		while(running == true) {
			road.step();
			carCount = road.getCarCount();
			double throughputCalc = carCount / (double)(System.currentTimeMillis() - startTime);
			throughput.setText("Throughput:" + throughputCalc);
			frame.repaint();
			try {
				Thread.sleep(100);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new Thread(new Traffic()).start();
	}
	
}
