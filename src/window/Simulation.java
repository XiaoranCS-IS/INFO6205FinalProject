package window;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import paraType.Country;
import paraType.Person;
import paraType.Policy;
import paraType.Virus;

import org.eclipse.swt.widgets.Button;

import java.awt.print.Printable;

import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.xml.crypto.Data;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class Simulation {

	protected Shell shell;
	private Virus selectedV;
	private Country selectedC;
	private Policy selectedP;
	private Person initPerson;
	Label data;
	Label day;
	Label totaldata;
	//to do
	double noBarriersRate ;
	double impactOfMasks ;//prevent 75% virus
	double impactOfQuarantine ;//prevent 90% virus
	double socialDistance ;//prevent 50% virus
	int averageContact ;//average contact person
	int testPeriod ;// days
	
	int dailyInfected[];// daily infected count
	int dailytotal[];//daily all infected person;
	int infectedCount = 1;// infected count
	double rValue = 0; //R value
	double kValue = 1; //K value
	double deathRate = 0;
	double selfHealingRate = 0;
	int policyChangeDay = 0;
	
	

	/**
	 * Launch the application.
	 * @param args
	 */
	
	

	public Simulation(Virus selectedV, Country selectedC, Policy selectedP) {
		super();
		this.selectedV = selectedV;
		this.selectedC = selectedC;
		this.selectedP = selectedP;
		
		if(this.selectedP.isIfMaskRequired())
		{
			this.impactOfMasks=0.75;
		}else {
			this.impactOfMasks=0;
		}
		
		if(this.selectedP.isIfTracingInfectedIndividual())
		{
			this.impactOfQuarantine=0.9;
		}else {
			this.impactOfQuarantine=0;
		}
		if(this.selectedP.isIfsocialDistance())
		{
			this.socialDistance=0.5;
		}else {
			this.socialDistance=0;
		}
		this.averageContact=(int) this.selectedC.getDensity();
		this.testPeriod=12;
		this.policyChangeDay=(int) this.selectedC.getPolicyChangeDay();
		this.rValue=this.selectedV.getrFac();
		this.kValue = this.selectedV.getkFac();
		this.dailyInfected = new int [this.testPeriod];
		this.dailytotal = new int [this.testPeriod+1];
		this.deathRate=this.selectedV.getDeathRate();
		this.selfHealingRate= this.selectedV.getCureRate();
		this.noBarriersRate=this.rValue/this.averageContact;
		//
		initPerson = new Person(1, null);
		initPerson.setDay(0);
		//Test
		double rate = calculateRate(noBarriersRate, impactOfMasks, impactOfQuarantine, socialDistance);
	    calculateInfectedCount(initPerson, averageContact, testPeriod, rate, deathRate, selfHealingRate, kValue, policyChangeDay);
	    rValue = calculateRValue(averageContact, testPeriod);
		
	    for (int i = 0; i < dailyInfected.length; i++) {
			System.out.println(i + " days: " + dailyInfected[i]);
		}
	    
	    
	}
	
	public int[] getDailyInfected() {
		return dailyInfected;
	}

	public void setDailyInfected(int[] dailyInfected) {
		this.dailyInfected = dailyInfected;
	}

	public double getNoBarriersRate() {
		return noBarriersRate;
	}

	public void setNoBarriersRate(double noBarriersRate) {
		this.noBarriersRate = noBarriersRate;
	}

	public static int getNumber(double rate) {
	    double pr = Math.random();
	    if(pr < rate){
	        return 1;
	    }else{
	        return 0;
	    }
	}
	
	public static double calculateRate(double initRate, double impactOfMasks, double impactOfQuarantine, double socialDistance) {
		double rate = initRate * (1 - impactOfMasks) * (1 - impactOfQuarantine) * (1 - socialDistance);
		return rate;
	}
	
	public void simulateProcess(Person p, int averageContact, int testPeriod, double rate, double deathRate, double selfHealingRate, double kValue, int policyChangeDay) {
		//to do
		if (p.getDay() < testPeriod) {

			p.setChildPerson(new Person[averageContact+1]);
			if (p.getIsinfected() == 1 || p.getIsinfected() == 3) {
				//judge if person status
				int isDead = getNumber(deathRate);
				int isSelfHealing = getNumber(selfHealingRate);
				if (isDead == 1) {// person dead, stop spread virus
					p.setIsinfected(2);//set person dead status
					System.out.println("###########");
					this.infectedCount -= 1;
					this.dailyInfected[p.getDay()] -= 1;
					System.out.println(this.infectedCount);
				}
				else if (isSelfHealing == 1) {// person self-healing, stop spread virus
					p.setIsinfected(0);//set person dead status
				    this.infectedCount -= 1;
				    this.dailyInfected[p.getDay()] -= 1;
				}
				else if (p.getIsinfected() == 1) {
					for (int i = 0; i < averageContact + 1; i++) {
						if (i == 0) {
							p.getChildPerson()[i] = new Person(p.getIsinfected(), null);
						}
						else{
							int isInfected = getNumber(rate);
							if (p.getDay() < policyChangeDay) {// the day before using policy
								isInfected = getNumber(this.noBarriersRate);
							}

							int canSpread = getNumber(kValue);
							if (canSpread == 1) {
								p.getChildPerson()[i] = new Person(isInfected, null);
							}
							else {
								isInfected = 3;
								p.getChildPerson()[i] = new Person(isInfected, null);//person infected but can not spread
							}
							
							if (isInfected == 1 || isInfected == 3) {
								this.infectedCount += 1;
								this.dailyInfected[p.getDay()] += 1;
							}
						}
					}
					
					for (Person person : p.getChildPerson()) {
						person.setDay(p.getDay() + 1);
						System.out.println(person.getDay() + "+" + person.getIsinfected());
						simulateProcess(person, averageContact, testPeriod, rate, deathRate, selfHealingRate, kValue, policyChangeDay);
					}
				}
			}
		}
	}
	
	public int calculateInfectedCount(Person p, int averageContact, int testPeriod, double rate, double deathRate, double selfHealingRate, double kValue, int policyChangeDay) {
		this.infectedCount = 1;//reset
		simulateProcess(p, averageContact, testPeriod, rate, deathRate, selfHealingRate, kValue, policyChangeDay);
		for(int i=0;i<dailyInfected.length;i++) {//count total infected
			for(int j=0;j<dailyInfected.length;j++)
			{
				if(i>=j) {
					this.dailytotal[i]+=this.dailyInfected[j];
				}
			}	
		}
		for(int i=0;i<dailytotal.length;i++)
		{
			this.dailytotal[i]+=1;
		}
		return this.infectedCount;
	}
	
	public double calculateRValue(int averageContact, int testPeriod) {
		int totalPerson = 1;
		int dailyIncrease = averageContact + 1;
		for (int i = 0; i < testPeriod; i++) {
			totalPerson += dailyIncrease;
			totalPerson -= dailyIncrease/(averageContact+1);
			dailyIncrease =	dailyIncrease * (averageContact + 1);
		}
		return this.infectedCount / totalPerson;
	}


	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
		    refreashP();
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	public void  refreashP() {
		String dailyaddString="0"+"\n";
		for(int i=0;i<dailyInfected.length;i++)
		{
			dailyaddString=dailyaddString+dailyInfected[i]+" "+"\n";
		}
		data.setText(dailyaddString);
		String dayString="";
		for (int i = 0; i < this.testPeriod+1; i++) {
			dayString=dayString+i+" "+"\n";
		}
		day.setText(dayString);
		
		String dailyString="1"+"\n";

		for(int i=0;i<dailyInfected.length;i++)
		{
			dailyString=dailyString+this.dailytotal[i]+" "+"\n";
		}
		totaldata.setText(dailyString);
	}
	/**
	 * Create contents of the window.
	 */
	
	protected void createContents() {
		shell = new Shell();
		shell.setSize(526, 379);
		shell.setText("SWT Application");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		btnNewButton.setBounds(407, 293, 80, 38);
		btnNewButton.setText("back");
		
		Label lblV = new Label(shell, SWT.NONE);
		lblV.setBounds(29, 30, 110, 97);
		lblV.setText("Virus : "+this.selectedV.getVirusName()+"\n"
				+ "     K Factor  :"+this.selectedV.getkFac()
				+"\n     R Factor  :"+this.selectedV.getrFac()
				+"\n     Death Rate:"+this.selectedV.getDeathRate()
				+"\n     Cure Rate :"+this.selectedV.getCureRate());
		
		Label lblC = new Label(shell, SWT.NONE);
		lblC.setBounds(29, 224, 199, 51);
		lblC.setText("Country : "+this.selectedC.getCountryName()+"\n     Average Contact  :"+this.selectedC.getDensity());
		
		Label lblP = new Label(shell, SWT.NONE);
		lblP.setBounds(29, 133, 150, 97);
		lblP.setText("Policy : "+this.selectedP.getPName()
				+"\n     Mask Required  :"+this.selectedP.isIfMaskRequired()
				+"\n     Social Distance  :"+this.selectedP.isIfsocialDistance()
				+"\n     Virus Test  :"+this.selectedP.isIfTesting()
				+"\n     Quarantine  :"+this.selectedP.isIfTracingInfectedIndividual());
		
		data = new Label(shell, SWT.NONE);
		data.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		data.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		data.setBounds(407, 56, 65, 219);
		data.setText("sdf");
		
		day = new Label(shell, SWT.NONE);
		day.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		day.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		day.setBounds(234, 56, 26, 219);
		day.setText("New Label");
		
		Label lblPolicyChangeDay = new Label(shell, SWT.NONE);
		lblPolicyChangeDay.setBounds(29, 293, 150, 17);
		lblPolicyChangeDay.setText("Policy Change Day : "+this.selectedC.getPolicyChangeDay());
		
		Label lblDay = new Label(shell, SWT.NONE);
		lblDay.setBounds(234, 33, 59, 14);
		lblDay.setText("Day:");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(288, 33, 104, 14);
		lblNewLabel.setText("Total infected:");
		
		totaldata = new Label(shell, SWT.NONE);
		totaldata.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		totaldata.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		totaldata.setBounds(303, 56, 59, 219);
		totaldata.setText("New Label");
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(398, 33, 92, 14);
		lblNewLabel_2.setText("Daily infected:");
		
		//dailyInfected
		btnNewButton.addPaintListener( new PaintListener() {
			  @Override
			  public void paintControl( PaintEvent event ) {
			   
			 
			  }
			} );
		
	}
}
