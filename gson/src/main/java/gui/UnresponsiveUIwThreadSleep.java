package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Resolve the unresponsive UI problem by running the compute-intensive task in
 * this own thread, which yields control to the EDT regularly
 */
public class UnresponsiveUIwThreadSleep extends JFrame {
	private boolean stop = false;
	private JTextField tfCount;
	private int count = 1;

	/** Constructor to setup the GUI components */
	public UnresponsiveUIwThreadSleep() {
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		cp.add(new JLabel("Counter"));
		tfCount = new JTextField(count + "", 10);
		tfCount.setEditable(false);
		cp.add(tfCount);

		JButton btnStart = new JButton("Start Counting");
		cp.add(btnStart);
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				setStop(false);
				// Create a new Thread to do the counting
				new Thread() {
					@Override
					public void run() { // override the run() for the running behaviors
						for (int i = 0; i < 10000; ++i) {
							if (isStop()) break;
							getTfCount().setText(getCount() + "");
							setCount();
							// Suspend this thread via sleep() and yield control to other threads.
							// Also provide the necessary delay.
							try {
								sleep(1); // milliseconds
							} catch (InterruptedException ex) {/**/}
						}
					}
				}.start(); // call back run()
			}
		});

		JButton btnStop = new JButton("Stop Counting");
		cp.add(btnStop);
		btnStop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				setStop(true);
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Counter");
		setSize(500, 200);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/** The entry main method */
	public static void main(String[] args) {
		// Run GUI codes in Event-Dispatching thread for thread safety
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new UnresponsiveUIwThreadSleep(); // Let the constructor do the job
			}
		});
	}

	boolean isStop() {
		return stop;
	}

	void setStop(boolean stop) {
		this.stop = stop;
	}

	JTextField getTfCount() {
		return tfCount;
	}

	void setTfCount(JTextField tfCount) {
		this.tfCount = tfCount;
	}

	int getCount() {
		return count;
	}

	void setCount() {
		++this.count;
	}
}