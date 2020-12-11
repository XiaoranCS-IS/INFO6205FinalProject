package window;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import paraType.Virus;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class CustomizeVirus {

	protected Shell shlCreateVirus;
	private Text text;
	private Text text_1;
	private Text text_2;
	public Virus v; 
	private Label lblCure;
	private Text text_3;
	private Label lblD;
	private Text text_4;

	/**
	 * Launch the application.
	 * @param args
	 */
	

	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlCreateVirus.open();
		shlCreateVirus.layout();
		while (!shlCreateVirus.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	public CustomizeVirus() {
		super();
	}
	public Virus creatVirus() {
		Virus virus =new Virus();
		virus.setVirusName(this.text.getText());
		virus.setDeathRate(Double.valueOf(this.text_4.getText()));
		virus.setkFac(Double.valueOf(this.text_1.getText()));
		virus.setrFac(Double.valueOf(this.text_2.getText()));
		virus.setCureRate(Double.valueOf(this.text_3.getText()));
		return virus;
		
	}
	

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlCreateVirus = new Shell();
		shlCreateVirus.setSize(230, 366);
		shlCreateVirus.setText("Create Virus");
		
		Label lblVirusName = new Label(shlCreateVirus, SWT.NONE);
		lblVirusName.setText("Virus Name:");
		lblVirusName.setBounds(21, 20, 73, 17);
		
		Label lblk = new Label(shlCreateVirus, SWT.NONE);
		lblk.setText("K Factor:");
		lblk.setBounds(21, 220, 91, 17);
		
		Label lblR = new Label(shlCreateVirus, SWT.NONE);
		lblR.setText("R Factor:");
		lblR.setBounds(21, 170, 126, 17);
		
		text = new Text(shlCreateVirus, SWT.BORDER);
		text.setBounds(21, 40, 73, 23);
		
		text_1 = new Text(shlCreateVirus, SWT.BORDER);
		text_1.setBounds(21, 240, 73, 23);
		
		text_2 = new Text(shlCreateVirus, SWT.BORDER);
		text_2.setBounds(21, 190, 73, 23);
		
		Button ButtonFinish = new Button(shlCreateVirus, SWT.NONE);
		ButtonFinish.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean can=false;
				boolean can2=false;
				try {
					Double.valueOf(text_1.getText());
					Double.valueOf(text_2.getText());
					Double.valueOf(text_3.getText());	
					Double.valueOf(text_4.getText());
					
					can=true;
				} catch (Exception e1) {
					MessageBox mBox3= new MessageBox(shlCreateVirus, SWT.ICON_ERROR);
					mBox3.setText("Wrong Type");
					mBox3.setMessage("R Factor, K Factor, Death Rate and Cure Rate must be the number");
					mBox3.open();
					// TODO: handle exception
				}
				try
				{
					if(Double.valueOf(text_1.getText())>1 ||Double.valueOf(text_1.getText())<0||Double.valueOf(text_3.getText())>1||Double.valueOf(text_3.getText())<0||Double.valueOf(text_4.getText())<0||Double.valueOf(text_4.getText())>1)
					{				
						throw new Exception();
					}
					can2=true;
				}catch (Exception e2) {
					MessageBox mBox1= new MessageBox(shlCreateVirus, SWT.ICON_ERROR);
						mBox1.setText("Wrong Value");
						mBox1.setMessage("K Factor, Death Rate and Cure Rate must be in the range (0-1)");
						mBox1.open();
				}
				
				if(can&&can2) {
					v=creatVirus();
					MainWindow.av.add(v);
					shlCreateVirus.close();
				}		
			}
		});
		ButtonFinish.setText("OK");
		ButtonFinish.setBounds(111, 285, 80, 27);
		
		lblCure = new Label(shlCreateVirus, SWT.NONE);
		lblCure.setBounds(21, 120, 61, 17);
		lblCure.setText("Cure Rate:");
		
		text_3 = new Text(shlCreateVirus, SWT.BORDER);
		text_3.setBounds(21, 140, 73, 23);
		
		lblD = new Label(shlCreateVirus, SWT.NONE);
		lblD.setBounds(21, 70, 59, 14);
		lblD.setText("Death Rate:");
		
		text_4 = new Text(shlCreateVirus, SWT.BORDER);
		text_4.setBounds(21, 90, 73, 23);

	}
}
