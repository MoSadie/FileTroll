package io.github.mosadie.FileTroll;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
 
public class Launch implements Runnable {

    @Override
    public void run() {
        // Create the window
        JFrame f = new JFrame("Hello, !");
        // Sets the behavior for when the window is closed
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Add a layout manager so that the button is not placed on top of the label
        f.setLayout(new FlowLayout());
        // Add a label and a button
        JButton start = new JButton("Click to start the loop.");
        start.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
            	System.out.print("Go!");
            	start.setEnabled(false);
            	while(true) {
            		try {
            			System.out.print("Checking");
            			File triggerFile = new File("go.txt");
            			if (triggerFile.exists()) {
            				System.out.print("Exists!");
            				BufferedReader command = new BufferedReader(new FileReader(triggerFile));
            				Process commandRan = Runtime.getRuntime().exec(command.readLine());
            				commandRan.destroy();
            				command.close();
            				System.exit(0);
            			} else {
            				System.out.print("Doesn't exist...");
            			}
						Thread.sleep(5000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            	}
            }
        });    
        f.add(start);
        // Arrange the components inside the window
        f.pack();
        // By default, the window is not visible. Make it visible.
        f.setVisible(true);
    }
 
    public static void main(String[] args) {
        Launch se = new Launch();
        // Schedules the application to be run at the correct time in the event queue.
        SwingUtilities.invokeLater(se);
    }

}