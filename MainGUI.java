import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

/**
 *<h1>Zadanie ulohy</h1>
 *<p>Napiste program na najdenie prvocisel v nahodne generovanom poli. 
 *		Najdene prvocisla sa usporiadaju a zapisu do suboru.(GUI)</p> 
 *
 * @author Samuel Domin
 * MTF STU
 * @version 0.0.1
 * @see javax.swing
 * @see java.awt
 * 
 */

class MainGUI {
	
	//Variables
	
	protected static final Frame f = null;
	private JFrame frame = null;
	private JTextArea inData=null;
	private JTextArea outData=null;
	private JTabbedPane tabpanel = null;
	private int[] cisla = new int[500] ;
	String otext="";
	

    public MainGUI() {
		super();
		setup();
		
	}

    
    public void setup()
    {	
    	
        //Creating the Frame
        frame = new JFrame("Zadanie Domin");
        frame.setBounds(50, 50, 500, 650);
        

        //Menu bar
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("File");
        JMenu m2 = new JMenu("Help");
        
        //Components
        
        JMenuItem m11 = new JMenuItem("Open");
        JMenuItem m12 = new JMenuItem("Save");
        JMenuItem m13 = new JMenuItem("Close");
        JMenuItem m21 = new JMenuItem("About");
        
        
        //calling ActionListeners
        
        m13.addActionListener(closeListener());
        m11.addActionListener(openListener());
        m12.addActionListener(saveListener());
        m21.addActionListener(aboutListener());
        
        //Adding components to menu bar 
        
        mb.add(m1);
        mb.add(m2);
        
        m1.add(m11);
        m1.add(m12);
        m1.addSeparator();
        m1.add(m13);
        
        m2.add(m21);
        
        frame.setJMenuBar(mb);
        
        // Tab panel
       
        tabpanel = new JTabbedPane();
        
        
        inData = new JTextArea("Click on generate numbers");
        JScrollPane scrollin = new JScrollPane(inData,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); //Input data is scrollable
        tabpanel.addTab("Generated numbers",scrollin);
        
        outData = new JTextArea("The output will be displayed here, after clicking the button");
        JScrollPane scrollout = new JScrollPane(outData,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);  //Output data is srollable
        tabpanel.addTab("Prime numbers",scrollout);
        tabpanel.setBounds(15, 40, 470, 500);
        
        JPanel panel = new JPanel(); // the panel is not visible in output
        panel.setLayout(null);
        panel.add(tabpanel);
        frame.add(panel);
        
    	frame.setResizable(false); //disable scaling
  	    
    	
    	for(int i=0; i>5;i++)
        {
        	inData = new JTextArea("Cislo");	
        }
    	
    	
    	JButton button = new JButton("Generate numbers");
    	JButton saveButton = new JButton("Save output");
    	
    	
    	panel.add(button);
    	panel.add(saveButton);
    	button.setBounds(15, 540, 150, 50);
    	saveButton.setBounds(165,540,150,50);
    	button.addActionListener(generateNum());
    	saveButton.addActionListener(exportListener());
    	
    	
	
    }
        /**
         * funkcia bez navratovej hodnoty sluzi ako spustac dialogoveho okna pre 
         */
    private void savePrimeNum()
    {
    	JFileChooser chooser = new JFileChooser();
		chooser.showSaveDialog(null);
    	File f = chooser.getSelectedFile();
    	if(f == null)
    	{
    		return;
    	}
    	
		
    	try 
		{
    		String str1 = f.getName();
    		if (!str1.endsWith(".txt")) 
    		{
    			str1 = f.getAbsolutePath();
    			str1 += ".txt";
    			f = new File(str1);
    		}
    		
			FileWriter fw = new FileWriter(f);
			fw.write(otext);
			fw.close();
			
		}
		
		catch(IOException e1)
		{
			System.out.println(e1);
		}
		
		
    }
    
    /**
    *
    * @param num
    * su to cisla ktore su z funkcie numberGenerator
    * @return 1
    * je cislo
    * @return 2
    * ak cislo je ine
    */
    
	static int isPrimeNum(int num)
    {
    	boolean flag = false;
        for(int i = 2; i <= num/2; ++i)
        {
            // condition for non-prime number
            if(num % i == 0)
            {
                flag = true;
                break;
            }
        }

        if (!flag)
            return 1;
        else
            return 2;
    
    }
    
	/**
	 * funkcia ktora generuje nahodne cisla do 500
	 * 
	 * @return x 
	 */
    static int numberGenerator() 
    {
    	int x;
    	Random rand = new Random(); 
    	x=rand.nextInt(500);
    	
    	return x;
	}


	/**
     * Generate num
     * 
     * Nahodne generuje cisla a nasledne zistuje ci je cislo prvocislo
     * 
     * @return generateNum
     */
 
    private ActionListener generateNum()
    {
    	ActionListener generateNum = new ActionListener() 
    	{
    		public void actionPerformed(ActionEvent e)
    		{
    			inData.setText("");
    			outData.setText("");
    			
	    	    String itext="";
	    	    //String otext="";
    	    	for (int i=0;i<cisla.length;i++)
    	    	{	
    	    		cisla[i]=numberGenerator();
    	    		itext += cisla[i] + "\n";
    	    		    	    		
    	    		if(isPrimeNum(cisla[i]) == 1 )
    	    		{
    	    			otext += cisla[i]+" "+"pn=yes" + "\n";
    	    			
    	    		}
    	    		else
    	    		{
    	    			otext += cisla[i]+" "+"pn=no" + "\n";
    	    			
    	    			
   	    		}
    	    	}
    	    	inData.setText(itext);
    	    	outData.setText(otext);
    	    	tabpanel.setSelectedIndex(1);
    		}
    	};
    	return generateNum;
    }
    
    /**
     * 
     * Action listener ktory sluzi na to ked sa stlaci tlacitko save output tak sa zobrazi dialogove okno
     * @apiNote ak pri ukladani zabudnes dat priponu .txt program ju automaticky doplni
     * @return export 
     */
    
    private ActionListener exportListener() {

    	ActionListener export = new ActionListener()
    			{
    			
    	@Override
    	public void actionPerformed(ActionEvent e)
    	{
    		savePrimeNum();
			 
    	}
    			};
    	
		return export;
		}
		
    /**
     * 
     * ActionListener, ktory po kliknuti zatvori okno
     * 
     * @return close
     */
   
    private ActionListener closeListener()
    {
    	ActionListener close = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
				
			}
		};
		
		return close;
 
    }
    
    /**
     * ActionListener ktory v menu otvori OpenDialog 
     * 
     * @return open
     */
    
    private ActionListener openListener()
    {
    	ActionListener open = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				
				int result = fileChooser.showOpenDialog(null);
				
				if (result == JFileChooser.APPROVE_OPTION) 
				{
				    File selectedFile = fileChooser.getSelectedFile();
				    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
				}
				
			
			}
		}; 
		
		return open;
    			
    	
    }
    
    /**
     * 
     * ActionListener ktory po kliknuti spusti dialogove okno so strucnimi informaciami 
     * 
     * @return about 
     */
    
    private ActionListener aboutListener()
    {
    	ActionListener about = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				JDialog aboutDialog = new JDialog(f, "About"); 
				JPanel panel = new JPanel();
				JButton closeButton = new JButton("Close");
	            // create a label 
	            JLabel l = new JLabel("Tento program sluzi na zistenie ci je cislo prvocislo"); 
	            JLabel l1 = new JLabel("© Copyright MTF STU Samuel Domin \n"); 
	            JLabel l2 = new JLabel("Cisla sa generuju len do 500. Je to pre lahsiu kontrolu :)");
	            
	           
	            l.setBounds(15, 10, 400, 20);
	            l1.setBounds(15, 35,400 ,20 );
	            l2.setBounds(15, 60, 400, 20);
	            closeButton.setBounds(15, 140, 80, 25);
	            
	            panel.setLayout(null);
	            panel.add(l); 
	            panel.add(l1);
	            panel.add(l2);
	            panel.add(closeButton);
	            aboutDialog.add(panel);
	            aboutDialog.setResizable(false);
	  
	            closeButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						aboutDialog.setVisible(false);
					}
				});
	            
	            // set size of dialog 
	            aboutDialog.setBounds(100,150,400, 200); 
	            // set visibility of dialog 
	            aboutDialog.setVisible(true); 
				
			}
    		
    	};
    	
    	return about;
    	
 	
    	
    }
    /**
     * funkcia, ktora po kliknuti zavola funkciu savePrimeNum() a spusti save dialog
     * 
     * @return save
     */
    
    private ActionListener saveListener()
    {
    	ActionListener save = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				savePrimeNum();
			}
		};
		
		return save; 	
    }
    
    /**
     * spusti hlavnu proceduru okna
     */
    
    public void run()
    {
    	frame.setVisible(true);
    }
        
	public static void main(String args[]){

			MainGUI mg = new MainGUI();
			mg.run();
      	}

	
}