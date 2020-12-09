package window;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import paraType.City;
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

public class Simulation {

	protected Shell shell;
	private Virus selectedV;
	private City selectedC;
	private Policy selectedP;
	private Person initPerson;
	Label data;
	Label day;
	//to do
	double noBarriersRate ;
	double impactOfMasks ;//prevent 75% virus
	double impactOfQuarantine ;//prevent 90% virus
	double socialDistance ;//prevent 50% virus
	int averageContact ;//average contact person
	int testPeriod ;// days
	
	int dailyInfected[];// daily infected count
	int infectedCount = 1;// infected count
	double rValue = 0; //R value
	double deathRate = 0;
	double selfHealingRate = 0;
	

	/**
	 * Launch the application.
	 * @param args
	 */
	
	

	public Simulation(Virus selectedV, City selectedC, Policy selectedP) {
		super();
		this.selectedV = selectedV;
		this.selectedC = selectedC;
		this.selectedP = selectedP;
		this.noBarriersRate=0.5;
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
		this.testPeriod=(int) this.selectedC.getSimulationDay();
		this.rValue=this.selectedV.getrFac();
		this.dailyInfected = new int [this.testPeriod];
		this.deathRate=this.selectedV.getkFac();
		this.selfHealingRate = 0;
		//
		initPerson = new Person(1, null);
		initPerson.setDay(0);
	}
	
	public int[] getDailyInfected() {
		return dailyInfected;
	}

	public void setDailyInfected(int[] dailyInfected) {
		this.dailyInfected = dailyInfected;
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
	
	public void simulateProcess(Person p, int averageContact, int testPeriod, double rate, double deathRate, double selfHealingRate) {
		//to do
		if (p.getDay() < testPeriod) {

			p.setChildPerson(new Person[averageContact+1]);
			if (p.getIsinfected() == 1) {
				//judge if person dead
				int isDead = getNumber(deathRate);
				int isSelfHealing = getNumber(selfHealingRate);
				if (isDead == 1) {// person dead, stop spread virus
					p.setIsinfected(2);//set person dead status
				}
				else if (isSelfHealing == 1) {// person self-healing, stop spread virus
					p.setIsinfected(0);//set person dead status
				    this.infectedCount -= 1;
				}
				else {
					for (int i = 0; i < averageContact + 1; i++) {
						if (i == 0) {
							p.getChildPerson()[i] = new Person(p.getIsinfected(), null);
						}
						else{
							int isInfected = getNumber(rate);
							p.getChildPerson()[i] = new Person(isInfected, null);
							if (isInfected == 1) {
								this.infectedCount += 1;
								this.dailyInfected[p.getDay()] += 1;
							}
						}
					}
					
					for (Person person : p.getChildPerson()) {
						person.setDay(p.getDay() + 1);
						System.out.println(person.getDay() + "+" + person.getIsinfected());
						simulateProcess(person, averageContact, testPeriod, rate, deathRate, selfHealingRate);
					}
				}
			}
		}
	}
	
	public int calculateInfectedCount(Person p, int averageContact, int testPeriod, double rate, double deathRate, double selfHealingRate) {
		this.infectedCount = 1;//reset
		simulateProcess(p, averageContact, testPeriod, rate, deathRate, selfHealingRate);
		return this.infectedCount;
	}
	
	public double calculateRValue(int averageContact, int testPeriod) {
		int totalPerson = 1;
		int dailyIncrease = averageContact + 1;//每个人每天都传播 所以加上自己
		for (int i = 0; i < testPeriod; i++) {
			totalPerson += dailyIncrease;
			totalPerson -= dailyIncrease/(averageContact+1);//减去重复的自己
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
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String dailyString="";
				for(int i=0;i<dailyInfected.length;i++)
				{
					dailyString=dailyString+dailyInfected[i]+" "+"\n";
				}
				data.setText(dailyString);
				String dayString="";
				for (int i = 0; i < selectedC.getSimulationDay(); i++) {
					dayString=dayString+(i+1)+" "+"\n";
				}
				day.setText(dayString);
			}
		});
		btnNewButton.setBounds(331, 225, 80, 27);
		btnNewButton.setText("New Button");
		
		Label lblV = new Label(shell, SWT.NONE);
		lblV.setBounds(29, 10, 80, 17);
		lblV.setText(this.selectedV.getVirusName());
		
		Label lblC = new Label(shell, SWT.NONE);
		lblC.setBounds(145, 10, 80, 17);
		lblC.setText(this.selectedC.getCityName());
		
		Label lblP = new Label(shell, SWT.NONE);
		lblP.setBounds(269, 10, 80, 17);
		lblP.setText(this.selectedP.getPName());
		
		data = new Label(shell, SWT.NONE);
		data.setBounds(155, 36, 113, 177);
		data.setText("sdf");
		
		day = new Label(shell, SWT.NONE);
		day.setBounds(25, 33, 113, 177);
		day.setText("New Label");
		
		//dailyInfected
		btnNewButton.addPaintListener( new PaintListener() {
			  @Override
			  public void paintControl( PaintEvent event ) {
			    event.gc.setBackground( event.display.getSystemColor( SWT.COLOR_GREEN ) );
			    event.gc.fillRectangle( event.x, event.y, event.width, event.height );
			    Image image = event.display.getSystemImage( SWT.ICON_QUESTION );
			    event.gc.drawImage( image, 0, 0 );
			    //
			    double rate = calculateRate(noBarriersRate, impactOfMasks, impactOfQuarantine, socialDistance);
			    calculateInfectedCount(initPerson, averageContact, testPeriod, rate, deathRate, selfHealingRate);
			    rValue = calculateRValue(averageContact, testPeriod);
			    
			    for (int i = 0; i < dailyInfected.length; i++) {
					System.out.println(i + " days: " + dailyInfected[i]);
				}
			  }
			} );
		
	}
}
