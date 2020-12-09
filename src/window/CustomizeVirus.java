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
		virus.setkFac(Double.valueOf(this.text_1.getText()));
		virus.setrFac(Double.valueOf(this.text_2.getText()));
		return virus;
		
	}
	

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlCreateVirus = new Shell();
		shlCreateVirus.setSize(230, 269);
		shlCreateVirus.setText("Create Virus");
		
		Label lblVirusName = new Label(shlCreateVirus, SWT.NONE);
		lblVirusName.setText("Virus Name:");
		lblVirusName.setBounds(21, 17, 73, 17);
		
		Label lblk = new Label(shlCreateVirus, SWT.NONE);
		lblk.setText("Death Rate:");
		lblk.setBounds(21, 69, 91, 17);
		
		Label lblR = new Label(shlCreateVirus, SWT.NONE);
		lblR.setText("R Factor:");
		lblR.setBounds(21, 121, 126, 17);
		
		text = new Text(shlCreateVirus, SWT.BORDER);
		text.setBounds(21, 40, 73, 23);
		
		text_1 = new Text(shlCreateVirus, SWT.BORDER);
		text_1.setBounds(21, 92, 73, 23);
		
		text_2 = new Text(shlCreateVirus, SWT.BORDER);
		text_2.setBounds(21, 144, 73, 23);
		
		Button ButtonFinish = new Button(shlCreateVirus, SWT.NONE);
		ButtonFinish.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean can=false;
				try {
					Double.valueOf(text_1.getText());
					Double.valueOf(text_2.getText());
					can=true;
				} catch (Exception e1) {
					MessageBox mBox3= new MessageBox(shlCreateVirus, SWT.ICON_ERROR);
					mBox3.setText("Wrong Type");
					mBox3.setMessage("R Factor and Death Rate must be the number");
					mBox3.open();
					// TODO: handle exception
				}
				
				if(can) {
					v=creatVirus();
					MainWindow.av.add(v);
					shlCreateVirus.close();
				}		
			}
		});
		ButtonFinish.setText("OK");
		ButtonFinish.setBounds(104, 184, 80, 27);

	}

}
