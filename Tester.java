import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.Timer;

public class Tester extends JPanel implements ActionListener {
	
	Timer tmr = null;
	Random rnd = new Random();
	private String v1, v2;
	
	public Tester() {
		JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
		JPanel northPanel = new JPanel(new BorderLayout());
		JPanel centerPanel = new JPanel(new BorderLayout());
		JPanel southPanel = new JPanel(new BorderLayout());
		
		mainPanel.add(northPanel, BorderLayout.NORTH);
		
		// List for ScrollBar List
		
		List<String> addresses = new ArrayList<>();
		
		// Read in information for addresses
		
		try {
			Scanner file = new Scanner(new File("MapInformation.txt"));
			
			String line = file.nextLine();
			while (!line.equals("<Nodes>")) { line = file.nextLine(); }
			file.nextLine();
			line = file.nextLine();
			while (!line.equals("</Nodes>")) {
				String[] s = line.split("\t");
				addresses.add(s[0] + "    " + s[1]);
				line = file.nextLine();
			}
		} catch (FileNotFoundException e) {
			e.getMessage();
		}
		
		// Creating JList of addresses
		
		final JList<String> list1 = new JList<String>(addresses.toArray(new String[addresses.size()]));
		list1.setCellRenderer(new Render<String>());
		final JList<String> list2 = new JList<String>(addresses.toArray(new String[addresses.size()]));
		
		// Creating JScrollPanels
		
		JPanel scrollPanel = new JPanel();
		
		JScrollPane pane1 = new JScrollPane(list1);
		JScrollPane pane2 = new JScrollPane(list2);
		
		pane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        pane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
        scrollPanel.add(pane1);
        scrollPanel.add(pane2);
        
        northPanel.add(scrollPanel, BorderLayout.WEST);
        
        // Creating JCheckBoxes
        
        
        JPanel boxPanel = new JPanel(new GridLayout(2,1, 0, -40));
        
        boxPanel.setSize(1,1);
        JToggleButton box1 = new JCheckBox("Directions based on lowest price");
        JToggleButton box2 = new JCheckBox("Directions based on lowest distance");
        box1.setSelected(true);
        
        //box1.setBorder(BorderFactory.createEmptyBorder());
        //box2.setBorder(BorderFactory.createEmptyBorder());
        
        ButtonGroup buttons = new ButtonGroup();
        buttons.add(box1);
        buttons.add(box2);

        
        boxPanel.add(box1);
        boxPanel.add(box2);
        
        northPanel.add(boxPanel);
        
        // Creating JTextBoxes to show and submit
        
        JPanel submitPanel = new JPanel();
        
        JTextField field1 = new JTextField(15);
        JTextField field2 = new JTextField(15);
        JLabel label = new JLabel("to");
        JButton submit = new JButton("Get shortest path");
        JButton all = new JButton("Show all directions");
        field1.setEditable(false);
        field2.setEditable(false);
        
        submitPanel.add(field1);
        submitPanel.add(label);
        submitPanel.add(field2);
        submitPanel.add(submit);
        submitPanel.add(all);

        centerPanel.add(submitPanel, BorderLayout.WEST);

        // Creating JTextField that will output the results
        
        JPanel outputPanel = new JPanel();
        JTextArea output = new JTextArea();
        output.setText("Please make Selections");
        output.setEditable(false);
        output.setColumns(30);
        output.setRows(8);
        output.setBounds(0, 0, 50, 50);
        JLabel outputLabel = new JLabel("Output");
        JScrollPane pane3 = new JScrollPane(output);
        outputPanel.add(pane3);
        
        
        outputPanel.add(outputLabel, BorderLayout.NORTH);
        outputPanel.add(output, BorderLayout.SOUTH);
        southPanel.add(outputPanel);
        
        // Adding all secondary panels to the primary panel
        
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);
		
        // Adding Events to Lists
        
        list1.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				String selected = list1.getSelectedValue();
				v1 = list1.getSelectedValue().substring(0, 1);
				field1.setText(selected);
			}
		});
        
        list2.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String selected = list2.getSelectedValue();
				v2 = list2.getSelectedValue().substring(0, 1);
				field2.setText(selected);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub	
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        // Changing the useDistCost boolean based on which box is checked
        
        box1.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent event) {
        		Graph.useDistCost = false;
        	}
        });
        
        box2.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent event) {
        		Graph.useDistCost = true;
        	}
        });
        
        // Adding Event to Submit Button
        
        submit.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent event) {
        		Graph graph = new Graph("MapInformation.txt");
        		//output.setText(graph.toString());
        		output.setText(Dijkstra.shortestPath(graph, v1, v2).toString());
        		
        	}

        });
        
        all.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent event) {
        		Graph graph = new Graph("MapInformation.txt");
        		//output.setText(graph.toString());
        		output.setText(graph.toString());
        		
        	}

        });
        
        
        
        // Completely the GUI
        
		JFrame window = new JFrame("Navigation");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(mainPanel);		
	    window.setSize(800, 500);
	    window.setLocationRelativeTo(null);
	    window.setVisible(true);
	    window.setResizable(false);
		//============================================================ Events
		
		tmr = new Timer(0, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				if (!(field1.getText().equals("")) && !(field2.getText().equals(""))) {
//					output.setText("Selections Made. Click Submit.");
//				}
			}
		});
		
		//============================================================ Mouse Pressed
		
		addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		//============================================================ Mouse Moved, Dragged
		
		addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
			}
			@Override
			public void mouseDragged(MouseEvent e) {
				mouseMoved(e);
			}
		});
		
		//============================================================ Button pressed

		
		//============================================================ Key pressed
		
		window.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		tmr.start();
	}
	
	//============================================================ Drawing
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	//======================================================
	public static void main(String[] args) { new Tester(); }
	//======================================================

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
