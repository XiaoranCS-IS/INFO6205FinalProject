package window;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

import paraType.Country;
import paraType.Policy;
import paraType.Virus;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import javax.swing.JOptionPane;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;

public class MainWindow {

	protected  Shell shell;
	private Label VName2;
	private Combo comboC;
	private Combo comboV;
	private Combo comboP;
	public Virus selectedV;
	public Country selectedC;
	public Policy selectedP;
	//Test data
	public 	Country c;
	public 	Country c2;
	public  Policy p;
	public  Policy p2;
	public  Virus v;
	public  Virus v2;
	private Label lblCityName;
	private Label lblDensity;
	private Label lblNumberOfHospital;
	public static ArrayList<Country> ac;
	public static ArrayList<Virus> av;
	public static ArrayList<Policy> ap;
	private Button ButtonCustomizeCity;
	private Label lblVirusName;
	private Label lblFR;
	private Label lblDrl;
	private Button ButtonCustomizeVirus;
	private Label lblMask;
	private Label lblBarrier;
	private Label lblTest;
	private Label lblTrace;
	private Button ButtonCustomizePolicy;
	private Label lblNewLabel_2;
	private int Simulationd;
	private Label  Simulationday;
	private Combo comboday;
	private Label lblFactorK;
	private Label lblFKL;

	/**
	 * Launch the application.
	 * @param args
	 */
	
	/*
	 * public static void main(String[] args) { try { MainWindow window = new
	 * MainWindow(); window.open(); } catch (Exception e) { e.printStackTrace(); } }
	 */

	public MainWindow() {
		super();
		ac=new ArrayList<Country>();
		av=new ArrayList<Virus>();
		ap=new ArrayList<Policy>();
		//Test data
		c= new Country("US",3,6);
		c2 = new Country("China",5,6);
		p= new Policy(true,true,true,true);
		p.setPName("StrictPolicy");
		p2= new Policy(false,false,false,false);
		p2.setPName("OpenPolicy");
		v =new Virus("Covid-19",0.1,0.03,3,1);// death cure r k
		v2 = new Virus("Sars",0.1,0.1,1,1);
		// TODO Auto-generated constructor stub
		ac.add(c);
		ac.add(c2);
		av.add(v);
		av.add(v2);
		ap.add(p);
		ap.add(p2);
	}

	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
	    
	    lblNewLabel_2 = new Label(shell, SWT.NONE);
	    lblNewLabel_2.setImage(SWTResourceManager.getImage("src/window/CHLA-What-You-Should-Know-Covid-19-1200x628-02.png"));
	    lblNewLabel_2.setBounds(0, 0, 753, 94);
	    
	    Button btnNewButton = new Button(shell, SWT.NONE);
	    btnNewButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
	    btnNewButton.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent e) {
	    		shell.close();
	    	}
	    });
	    btnNewButton.setBounds(590, 331, 107, 42);
	    btnNewButton.setText("Exit");
	    
	    comboday = new Combo(shell, SWT.NONE);
	    comboday.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	    comboday.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
	    comboday.setItems(new String[] {"2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"});
	    comboday.setBounds(222, 401, 107, 25);
	    
	    Simulationday = new Label(shell, SWT.NONE);
	    Simulationday.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
	    Simulationday.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	    Simulationday.setBounds(222, 381, 123, 17);
	    Simulationday.setText("Policy Change Day：");
	    
	    lblFactorK = new Label(shell, SWT.NONE);
	    lblFactorK.setBounds(35, 381, 59, 14);
	    lblFactorK.setText("Factor K:");
	    
	    lblFKL = new Label(shell, SWT.NONE);
	    lblFKL.setFont(SWTResourceManager.getFont("Times New Roman", 11, SWT.NORMAL));
	    lblFKL.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
	    lblFKL.setBounds(35, 401, 103, 14);
	    lblFKL.setText("Please Select a Virus");
		shell.open();
		
		shell.layout();
		while (!shell.isDisposed()) {
			this.refreshText();
			shell.layout();
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	public void refreshText() {
		
		
		if(comboC.getSelectionIndex()!=-1) {
			//refresh ac and combo

			if(ac.size()>comboC.getItemCount())
			{
				comboC.add(ac.get(ac.size()-1).getCountryName());
			}
			
			for(int i=0;i<ac.size();i++)
			{
				if(comboC.getItem(comboC.getSelectionIndex()).equals(ac.get(i).getCountryName())) {
					this.lblCityName.setText(ac.get(i).getCountryName());
					this.lblDensity.setText(Double.toString(ac.get(i).getDensity()));
					this.selectedC=ac.get(i);
				}
			}		
		}
		
		if(comboV.getSelectionIndex()!=-1) {
			//refresh av and combo

			if(av.size()>comboV.getItemCount())
			{
				comboV.add(av.get(av.size()-1).getVirusName());
			}
			
			for(int i=0;i<av.size();i++)
			{
				if(comboV.getItem(comboV.getSelectionIndex()).equals(av.get(i).getVirusName())) {
					this.lblVirusName.setText(av.get(i).getVirusName());
					this.lblFR.setText(Double.toString(av.get(i).getrFac()));
					this.lblDrl.setText(Double.toString(av.get(i).getDeathRate()));
					this.lblFKL.setText(Double.toString(av.get(i).getkFac()));
					this.lblNumberOfHospital.setText(Double.toString(av.get(i).getCureRate()));
					this.selectedV=av.get(i);
				}
			}		
		}
		
		if(comboP.getSelectionIndex()!=-1) {
			//refresh ap and combo

			if(ap.size()>comboP.getItemCount())
			{
				comboP.add(ap.get(ap.size()-1).getPName());
			}
			
			for(int i=0;i<ap.size();i++)
			{
				if(comboP.getItem(comboP.getSelectionIndex()).equals(ap.get(i).getPName())) {
					this.lblMask.setText(checkPolicy(ap.get(i).isIfMaskRequired()));
					this.lblBarrier.setText(checkPolicy(ap.get(i).isIfsocialDistance()));
					this.lblTest.setText(checkPolicy(ap.get(i).isIfTesting()));
					this.lblTrace.setText(checkPolicy(ap.get(i).isIfTracingInfectedIndividual()));
					this.selectedP=ap.get(i);
				}
			}		
		}
		
		
	}
	public String checkPolicy(boolean b) {
		if(b==true)
		{
			return "Yes";
		}else
		{
			return "No";
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell(SWT.CLOSE);
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		shell.setSize(752, 510);
		shell.setText("SWT Application");
		shell.setLayout(null);
		
		comboV = new Combo(shell, SWT.NONE);
		comboV.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		comboV.setBounds(35, 121, 103, 22);
		comboV.setItems(new String[] {"Covid-19","Sars"});
		comboV.setText("Select Virus");
		
		ButtonCustomizeVirus = new Button(shell, SWT.NONE);
		ButtonCustomizeVirus.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		ButtonCustomizeVirus.setBounds(590, 121, 107, 42);
		ButtonCustomizeVirus.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					CustomizeVirus window = new CustomizeVirus();
					window.open();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		ButtonCustomizeVirus.setText("New Virus");
		
		Label label = new Label(shell, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(180, 108, 2, 290);
		
		comboC = new Combo(shell, SWT.NONE);
		comboC.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		comboC.setBounds(222, 121, 115, 22);
		comboC.setItems(new String[] {"US", "China"});
		comboC.setText("Select Country");
		
		ButtonCustomizeCity = new Button(shell, SWT.NONE);
		ButtonCustomizeCity.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		ButtonCustomizeCity.setBounds(590, 191, 107, 42);
		ButtonCustomizeCity.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					CustomizeCountry window = new CustomizeCountry();
					window.open();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		ButtonCustomizeCity.setText("New Country");
		
		Label label_1 = new Label(shell, SWT.SEPARATOR | SWT.VERTICAL);
		label_1.setBounds(372, 108, 2, 290);
		
		comboP = new Combo(shell, SWT.NONE);
		comboP.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		comboP.setBounds(417, 121, 103, 22);
		comboP.setItems(new String[] {"OpenPolicy", "StrictPolicy"});
		comboP.setText("Select Policy");
		
		ButtonCustomizePolicy = new Button(shell, SWT.NONE);
		ButtonCustomizePolicy.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		ButtonCustomizePolicy.setBounds(590, 261, 107, 42);
		ButtonCustomizePolicy.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					CustomizePolicy window = new CustomizePolicy();
					window.open();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		ButtonCustomizePolicy.setText("New Policy");
		
		VName2 = new Label(shell, SWT.NONE);
		VName2.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		VName2.setBounds(35, 180, 68, 17);
		VName2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		VName2.setText("Virus Name:");
		
		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblNewLabel_3.setBounds(222, 180, 91, 17);
		lblNewLabel_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_3.setText("Country Name:");
		
		Label lblNewLabel_5 = new Label(shell, SWT.NONE);
		lblNewLabel_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblNewLabel_5.setBounds(417, 180, 89, 17);
		lblNewLabel_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_5.setText("Mask Required:");
		
		lblVirusName = new Label(shell, SWT.NONE);
		lblVirusName.setFont(SWTResourceManager.getFont("Times New Roman", 11, SWT.NORMAL));
		lblVirusName.setBounds(35, 202, 119, 17);
		lblVirusName.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblVirusName.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblVirusName.setText("Please Select a Virus");
		
		lblCityName = new Label(shell, SWT.NONE);
		lblCityName.setFont(SWTResourceManager.getFont("Times New Roman", 11, SWT.NORMAL));
		lblCityName.setBounds(222, 200, 115, 17);
		lblCityName.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblCityName.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblCityName.setText("Please Select a Country");
		
		lblMask = new Label(shell, SWT.NONE);
		lblMask.setFont(SWTResourceManager.getFont("Times New Roman", 11, SWT.NORMAL));
		lblMask.setBounds(417, 200, 123, 17);
		lblMask.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblMask.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblMask.setText("Please Select a Policy");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblNewLabel_1.setBounds(35, 336, 68, 17);
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_1.setText("Factor R:");
		
		Label lblNewLabel_4 = new Label(shell, SWT.NONE);
		lblNewLabel_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblNewLabel_4.setBounds(222, 283, 103, 17);
		lblNewLabel_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_4.setText("Average Contact:");
		
		Label lblNewLabel_6 = new Label(shell, SWT.NONE);
		lblNewLabel_6.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblNewLabel_6.setBounds(417, 254, 118, 17);
		lblNewLabel_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_6.setText("Social Distance:");
		
		lblFR = new Label(shell, SWT.NONE);
		lblFR.setFont(SWTResourceManager.getFont("Times New Roman", 11, SWT.NORMAL));
		lblFR.setBounds(35, 358, 119, 17);
		lblFR.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblFR.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblFR.setText("Please Select a Virus");
		
		
		lblDensity = new Label(shell, SWT.NONE);
		lblDensity.setFont(SWTResourceManager.getFont("Times New Roman", 11, SWT.NORMAL));
		lblDensity.setBounds(222, 305, 115, 17);
		lblDensity.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblDensity.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblDensity.setText("Please Select a Country");
		
		lblBarrier = new Label(shell, SWT.NONE);
		lblBarrier.setFont(SWTResourceManager.getFont("Times New Roman", 11, SWT.NORMAL));
		lblBarrier.setBounds(417, 274, 123, 17);
		lblBarrier.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblBarrier.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblBarrier.setText("Please Select a Policy");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblNewLabel.setBounds(35, 231, 103, 17);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setText("Death Rate(0-1):");
		
		Label Cure = new Label(shell, SWT.NONE);
		Cure.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		Cure.setBounds(35, 283, 121, 17);
		Cure.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		Cure.setText("Cure Rate(0-1):");
		
		Label lblNewLabel_7 = new Label(shell, SWT.NONE);
		lblNewLabel_7.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblNewLabel_7.setBounds(417, 316, 68, 17);
		lblNewLabel_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_7.setText("Test Virus:");
		
		lblDrl = new Label(shell, SWT.NONE);
		lblDrl.setFont(SWTResourceManager.getFont("Times New Roman", 11, SWT.NORMAL));
		lblDrl.setBounds(35, 253, 119, 17);
		lblDrl.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblDrl.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblDrl.setText("Please Select a Virus");
		
		lblNumberOfHospital = new Label(shell, SWT.NONE);
		lblNumberOfHospital.setFont(SWTResourceManager.getFont("Times New Roman", 11, SWT.NORMAL));
		lblNumberOfHospital.setBounds(35, 305, 119, 17);
		lblNumberOfHospital.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNumberOfHospital.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNumberOfHospital.setText("Please Select a Virus");
		
		lblTest = new Label(shell, SWT.NONE);
		lblTest.setFont(SWTResourceManager.getFont("Times New Roman", 11, SWT.NORMAL));
		lblTest.setBounds(417, 336, 123, 17);
		lblTest.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblTest.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblTest.setText("Please Select a Policy");
		
		Label lblNewLabel_8 = new Label(shell, SWT.NONE);
		lblNewLabel_8.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblNewLabel_8.setText("Quarantine:");
		lblNewLabel_8.setBounds(417, 381, 154, 17);
		lblNewLabel_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		lblTrace = new Label(shell, SWT.NONE);
		lblTrace.setFont(SWTResourceManager.getFont("Times New Roman", 11, SWT.NORMAL));
		lblTrace.setBounds(417, 401, 123, 17);
		lblTrace.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblTrace.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblTrace.setText("Please Select a Policy");
		
		Button simulationButton = new Button(shell, SWT.NONE);
		simulationButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		simulationButton.setBounds(590, 401, 107, 42);
		simulationButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(comboV.getSelectionIndex()==-1) {
					MessageBox mBox= new MessageBox(shell, SWT.ICON_ERROR);
					mBox.setText("Please Select");
					mBox.setMessage("Please Select a Virus");
					mBox.open();
				}else if(comboC.getSelectionIndex()==-1) {
					MessageBox mBox2= new MessageBox(shell, SWT.ICON_ERROR);
					mBox2.setText("Please Select");
					mBox2.setMessage("Please Select a Country");
					mBox2.open();
				}else if(comboP.getSelectionIndex()==-1) {
					MessageBox mBox3= new MessageBox(shell, SWT.ICON_ERROR);
					mBox3.setText("Please Select");
					mBox3.setMessage("Please Select a Policy");
					mBox3.open();
				}else if(comboday.getSelectionIndex()==-1){
					MessageBox mBox4= new MessageBox(shell, SWT.ICON_ERROR);
					mBox4.setText("Please Select");
					mBox4.setMessage("Please Select Policy Change Days");
					mBox4.open();
				}else {
					try {
						Simulationd=Integer.valueOf(comboday.getItem(comboday.getSelectionIndex()));
						selectedC.setPolicyChangeDay(Simulationd);
						Simulation window = new Simulation(selectedV,selectedC,selectedP);
						window.open();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}					
			}
		});
		simulationButton.setText("Simulation");
		
	
		

	}
}
