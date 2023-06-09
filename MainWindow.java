import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * 
 *  MainWindow class that creates a GUI for user to interact with.
 *  Implements homeScreen method that creates the landing page and 
 *  entry portal to invoke the coffeeListPage method that creates the
 *  coffee list page.  
 * 
 * CSE 201 A
 * Group 11
 * @author Joseph Brocato, Sriram Ranganathan, Abbey Noonen,
 * 		   Brody Criz, and Jack Stapleton. 
 * 
 */


public class MainWindow implements ActionListener {
    public static void main(String[] args) {
    	MainWindow window = new MainWindow();
        window.homeScreen();
        
    }
    
    /**
     * homeScreen method that creates the landing page for the GUI.
     */
    public void homeScreen() {
    	JFrame frame = new JFrame();
        frame.setTitle("Coffee Catalog Home");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,800);
        
        frame.setResizable(true);  
        frame.setLocationRelativeTo(null);

        frame.getContentPane().setBackground(new Color(188, 209, 201));
        
        // logo image 
        ImageIcon image = new ImageIcon(MainWindow.class.getResource("/res/logo.png"));
        Image image2 = image.getImage(); 
        Image newimg = image2.getScaledInstance(490, 350,  java.awt.Image.SCALE_SMOOTH); 
        image = new ImageIcon(newimg);
        var icon = new ImageIcon("logo.png");
        var label = new JLabel(icon);
        label.setIcon(image);
        frame.setIconImage(image.getImage());
        
        
        JButton enter = new JButton("Enter Catalog");
        enter.setSize(200, 75);
        enter.setLocation(500, 570);
        enter.setFont(new Font("Frutiger", Font.BOLD, 20));
        
        enter.addActionListener(this);
        
        JLabel companyName = new JLabel();
        companyName.setText("ProRegistry");
        companyName.setFont(new Font("Optima-Regular", Font.ITALIC | Font.BOLD, 20));
        companyName.setBounds(550, 700, 300, 100);
        frame.add(companyName);
        
        frame.getContentPane().add(enter, BorderLayout.CENTER);
        
        frame.add(label, BorderLayout.CENTER);
        
        frame.setVisible(true);
        
        System.out.println("Landing page created successfully.");
    } // end homeScreen method 
    
    /**
     * coffeeListPage method that creates a list of the Starbucks drinks
     * @throws IOException
     */
    public static void coffeeListPage() throws IOException {
    	/**
    	 * Create new frame for coffee objects
    	 */
    	JFrame coffeeFrame = new JFrame();
    	coffeeFrame.setTitle("List of Coffees");
    	coffeeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	coffeeFrame.setSize(1200,800);
    	coffeeFrame.setResizable(true);  
    	coffeeFrame.setLocationRelativeTo(null);
    	coffeeFrame.getContentPane().setBackground(new Color(188, 209, 201));
    	
    	
    	/***
    	 * Menu Items
    	 */
    	JMenu filter = new JMenu("Filter");
	    JMenu sort = new JMenu("Sort");
	    JMenuBar menuBar = new JMenuBar();
	    menuBar.add(filter);
	    menuBar.add(sort);
	    
	    
	    CoffeeReader readFile = new CoffeeReader();
	   	ArrayList<Coffee> defaultList = readFile.reader();

    	/**
    	 * JPanel-->JScrollPane for each coffee object to reside
    	 */
    	JPanel coffeePanel = new JPanel();
    	coffeePanel.setLayout(new BoxLayout(coffeePanel, BoxLayout.Y_AXIS));
    	coffeePanel.setBackground(new Color(188, 209, 201));
    	
    	JScrollPane scroller = new JScrollPane(coffeePanel);
    	scroller.getVerticalScrollBar().setUnitIncrement(10);
    	
    	
    	
    	/***
	     * Implement event handling (making the menu options do stuff)
	     */
	    // filter menu options 
	    // Either hot or cold 
	    JMenuItem filterTypeHot = new JMenuItem("By type: hot");
        filter.add(filterTypeHot);
        filterTypeHot.addActionListener(
        		new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					   	ArrayList<Coffee> defaultList = readFile.reader();
					   	ArrayList<Coffee> tempList = new ArrayList<Coffee>();
						
						try {
							tempList = filterHot(defaultList);
							coffeePanel.removeAll();
							createList(coffeePanel, tempList);
						} catch (IOException e1) {
							e1.printStackTrace();
						}	
					}
        		}
        );
        JMenuItem filterTypeCold = new JMenuItem("By type: cold");
        filter.add(filterTypeCold);
        filterTypeCold.addActionListener(
        		new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						CoffeeReader readFile = new CoffeeReader();
					   	ArrayList<Coffee> defaultList = readFile.reader();
					   	ArrayList<Coffee> tempList = new ArrayList<Coffee>();
						
						try {
							tempList = filterCold(defaultList);
							coffeePanel.removeAll();
							createList(coffeePanel, tempList);
						} catch (IOException e1) {
							e1.printStackTrace();
						}	
						
					}
        		}
        );
	    JMenuItem filterSeasonal = new JMenuItem("By seasonal");
        filter.add(filterSeasonal);
        filterSeasonal.addActionListener(
        		new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// add filter by seasonal boolean comparator logic here 
						CoffeeReader readFile = new CoffeeReader();
					   	ArrayList<Coffee> defaultList = readFile.reader();
					   	ArrayList<Coffee> tempList = new ArrayList<Coffee>();
						
						try {
							tempList = filterSeasonal(defaultList);
							coffeePanel.removeAll();
							createList(coffeePanel, tempList);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
        		}
        );
        
        // sort menu options
        JMenuItem sortName = new JMenuItem("By name (alphabetical)");
        sort.add(sortName);
        sortName.addActionListener(
        		new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						CoffeeReader readFile = new CoffeeReader();
					   	ArrayList<Coffee> defaultList = readFile.reader();
					   	ArrayList<Coffee> tempList = new ArrayList<Coffee>();
						
						try {
							tempList = sortAlphabetical(defaultList);
							coffeePanel.removeAll();
							createList(coffeePanel, tempList);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
        		}
        );
        JMenuItem sortCalsLow = new JMenuItem("By calories (low to high)");
        sort.add(sortCalsLow);
        sortCalsLow.addActionListener(
        		new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						CoffeeReader readFile = new CoffeeReader();
					   	ArrayList<Coffee> defaultList = readFile.reader();
					   	ArrayList<Coffee> tempList = new ArrayList<Coffee>();
						
						try {
							tempList = sortCaloriesLowToHigh(defaultList);
							coffeePanel.removeAll();
							createList(coffeePanel, tempList);
						} catch (IOException e1) {
							e1.printStackTrace();
						}					}
        		}
        );
        JMenuItem sortCalsHigh = new JMenuItem("By calories (high to low)");
        sort.add(sortCalsHigh);
        sortCalsHigh.addActionListener(
        		new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						CoffeeReader readFile = new CoffeeReader();
					   	ArrayList<Coffee> defaultList = readFile.reader();
					   	ArrayList<Coffee> tempList = new ArrayList<Coffee>();
						
						try {
							tempList = sortCaloriesHighToLow(defaultList);
							coffeePanel.removeAll();
							createList(coffeePanel, tempList);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
        		}
        );
		
		
    	
    	// whole list of coffee objects from CoffeeList.txt
    	createList(coffeePanel, defaultList);
    	    	
    	
    	// Add to coffeeFrame
    	coffeeFrame.setJMenuBar(menuBar);
    	coffeeFrame.add(scroller);
    	
    	
    	
    	coffeeFrame.setVisible(true);
    	
    	
    } // end coffeeListPage method 

    
    /**
     * createList method that creates a list of coffees for the GUI 
     * when given a JPanel and ArrayList as input.
     * @param panel
     * @param list
     * @throws IOException
     */
   public static void createList(JPanel panel, ArrayList<Coffee> list) throws IOException {
	   	/**
	   	 * Display coffee items from CoffeeList.txt
	   	 */
	   // FileInputStream fis = new FileInputStream("res/sheet.xlsx");
	    BufferedImage logo = ImageIO.read(MainWindow.class.getResourceAsStream("/res/logo.png"));
	   	Image image = logo.getScaledInstance(210, 150, Image.SCALE_SMOOTH);
	   	JLabel logoLabel = new JLabel(new ImageIcon(image));
	   	logoLabel.setText("                         Coffee List Page");
	   	logoLabel.setFont(new Font("Frutiger", Font.BOLD, 36));
	   	panel.add(logoLabel);
	   	
	   	for (int i = 0; i < list.size(); i++) {
	   		int x = i;
	   		
	   		JLabel label = new JLabel();
	    	label.setText("<html><h1> " + list.get(i).getName() + "</h1>"
	    			+ "<body>Calories: " + list.get(i).getCalories()
	    			+ "<br>Type: " + list.get(i).getType()
	    			+ "<br>Seasonal: " + list.get(i).getSeasonal()
	    			+ "<br>Link to Starbucks website: " + "<a href='" + list.get(i).getLink() + "'>" + list.get(i).getLink() + "</a></body></html>");
	    	label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	    	label.setFont(new Font("Frutiger", Font.PLAIN, 18));
		
		   	label.addMouseListener(new MouseAdapter() {
		   		public void mouseEntered(MouseEvent me) {
		           label.setCursor(new Cursor(Cursor.HAND_CURSOR));
		        }
		        public void mouseExited(MouseEvent me) {
		           label.setCursor(Cursor.getDefaultCursor());
		        }
		        public void mouseClicked(MouseEvent e) {
		            try {
		                Desktop.getDesktop().browse(new URI(list.get(x).getLink()));
		            } catch (IOException | URISyntaxException e1) {
		                e1.printStackTrace();
		            }
		        }
		    });
		   	// add images to each item
		   	URL url = new URL(list.get(i).getImgLink());
	        BufferedImage nitroBI = ImageIO.read(url);
			Image nitroI = nitroBI.getScaledInstance(150, 150, Image.SCALE_FAST);
			ImageIcon nitroII = new ImageIcon(nitroI);
			label.setIcon(nitroII);
		   	
	    	// last step
	    	panel.add(label);
	   		
	   	}
		
		
		
   } // end createList method
   
   
   
    
    /**
     * This method will load up the list of coffees 
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			coffeeListPage();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	
	} // end actionPerfromed method
    
    /**
     * Below we have the static sort and filter methods for button in the JMenuBar.
     * @throws IOException 
     * 
     */
	public static ArrayList<Coffee> filterSeasonal(ArrayList<Coffee> list) {
	    ArrayList<Coffee> updateList = new ArrayList<Coffee>();
	    for (int i = 0; i < list.size(); i++) {
	        if (list.get(i).getSeasonal() == true) {
	            updateList.add(list.get(i));
	        }
	    }
	    return updateList; 
	}
	
	public static ArrayList<Coffee> filterHot(ArrayList<Coffee> list) {
        ArrayList<Coffee> updateList = new ArrayList<Coffee>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getType().equals("hot")) {
                updateList.add(list.get(i));
            }
        }
        return updateList;
    }
    
    public static ArrayList<Coffee> filterCold(ArrayList<Coffee> list) {
        ArrayList<Coffee> updateList = new ArrayList<Coffee>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getType().equals("cold")) {
                updateList.add(list.get(i));
            }
        }
        return updateList;
    }
	
    	
	public static ArrayList<Coffee> sortAlphabetical(ArrayList<Coffee> list) {
		ArrayList<Coffee> tmpList = list;
		ArrayList<Coffee> newList = new ArrayList<Coffee>();
		Coffee cof = list.get(0);	
		while (tmpList.size() != 0) {
			for (int i = 0; i < tmpList.size(); i++) {
				if (cof.getName().compareTo(tmpList.get(i).getName()) > 0) {
					cof = tmpList.get(i);
				}
			}
			newList.add(cof);
			tmpList.remove(cof);
			if (tmpList.size() > 0) {
				cof = list.get(0);
			}
		}
		return newList;
	}
	
	public static ArrayList<Coffee> sortCaloriesLowToHigh(ArrayList<Coffee> list) {
		ArrayList<Coffee> tmpList = list;
		ArrayList<Coffee> newList = new ArrayList<Coffee>();
		Coffee cof = list.get(0);
		int index = 0;
		while (tmpList.size() != 0) {
			for (int i = 0; i < tmpList.size(); i++) {
				if (cof.getCalories() > tmpList.get(i).getCalories()) {
					cof = tmpList.get(i);
					index = i;
				}
			}
			newList.add(cof);
			tmpList.remove(index);
			if (tmpList.size() > 0) {
				cof = tmpList.get(0);
				index = 0;
			}
		}
		return newList;
	}
	
	public static ArrayList<Coffee> sortCaloriesHighToLow(ArrayList<Coffee> list) {
		ArrayList<Coffee> tmpList = list;
		ArrayList<Coffee> newList = new ArrayList<Coffee>();
		Coffee cof = list.get(0);
		int index = 0;
		while (tmpList.size() != 0) {
			for (int i = 0; i < tmpList.size(); i++) {
				if (cof.getCalories() < tmpList.get(i).getCalories()) {
					cof = tmpList.get(i);
					index = i;
				}
			}
			newList.add(cof);
			tmpList.remove(index);
			if (tmpList.size() > 0) {
				cof = tmpList.get(0);
				index = 0;
			}
		}
		return newList;
	}
	
    
	
} // end MainWindow class









