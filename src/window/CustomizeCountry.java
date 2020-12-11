package window;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import paraType.Country;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class CustomizeCountry {

	protected Shell shell;
	private Text textCity;
	private Text textAverageContect;
	public Country c;
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

	public CustomizeCountry() {
		super();

			
	}
	public Country creatCity(){
		Country city = new Country();
		city.setCountryName(this.textCity.getText());
		city.setDensity(Double.valueOf(this.textAverageContect.getText()));
		city.setPolicyChangeDay(6);//default
		return city;	
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(230, 272);
		shell.setText("SWT Application");
		
		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setText("Country Name:");
		lblNewLabel_3.setBounds(26, 31, 89, 17);
		
		Label lblNewLabel_4 = new Label(shell, SWT.NONE);
		lblNewLabel_4.setText("Average Contact(1-5):");
		lblNewLabel_4.setBounds(26, 83, 126, 17);
		
		textCity = new Text(shell, SWT.BORDER);
		textCity.setBounds(26, 54, 73, 23);
		
		textAverageContect = new Text(shell, SWT.BORDER);
		textAverageContect.setBounds(26, 106, 73, 23);
		
		Button ButtonFinish = new Button(shell, SWT.NONE);
		ButtonFinish.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean can1=false;
				boolean can2=false;
				try {
					double a = Double.valueOf(textAverageContect.getText());
					
					can1=true;
					if(a<1 ||a>5 ) {
						MessageBox mBox2= new MessageBox(shell, SWT.ICON_ERROR);
						mBox2.setText("Unaccepted Range");
						mBox2.setMessage("Average Contect must in Range(1-5)");
						mBox2.open();
					}else {
						can2=true;
					}
				} catch (Exception e1) {
					MessageBox mBox3= new MessageBox(shell, SWT.ICON_ERROR);
					mBox3.setText("Wrong Type");
					mBox3.setMessage("Average Contect must be the number");
					mBox3.open();
					// TODO: handle exception
				}
				
				
				if(can1 && can2) {
					c=creatCity();
					MainWindow.ac.add(c);
					shell.close();
				}
				
			}
		});
		ButtonFinish.setText("OK");
		ButtonFinish.setBounds(116, 189, 80, 27);

	}

}
