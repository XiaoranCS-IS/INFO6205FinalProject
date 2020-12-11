package window;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import paraType.Policy;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class CustomizePolicy {

	protected Shell shell;
	Button btnCheckMask;
	Button btnCheckBarrier;
	Button btnCheckTrace;
	public Policy p;

	/**
	 * Launch the application.
	 * @param args
	 */
	/*
	 * public static void main(String[] args) { try { CustomizePolicy window = new
	 * CustomizePolicy(); window.open(); } catch (Exception e) {
	 * e.printStackTrace(); } }
	 */

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
	

	public CustomizePolicy() {
		super();
		
	}
	public Policy createPolicy() {
		Policy policy = new Policy();
		policy.setIfMaskRequired(this.btnCheckMask.getSelection());
		policy.setIfsocialDistance(this.btnCheckBarrier.getSelection());
		policy.setIfTracingInfectedIndividual(this.btnCheckTrace.getSelection());
		return policy;
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(279, 299);
		shell.setText("SWT Application");
		
		btnCheckMask = new Button(shell, SWT.CHECK);
		btnCheckMask.setBounds(64, 33, 126, 17);
		btnCheckMask.setText("Mask Required");
		
		btnCheckBarrier = new Button(shell, SWT.CHECK);
		btnCheckBarrier.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnCheckBarrier.setBounds(64, 93, 175, 17);
		btnCheckBarrier.setText("Social Distance Required");
		
		btnCheckTrace = new Button(shell, SWT.CHECK);
		btnCheckTrace.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnCheckTrace.setBounds(64, 153, 147, 17);
		btnCheckTrace.setText("Quarantine Required:");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				p=createPolicy();
				MainWindow.ap.add(p);
				shell.close();
			}
		});
		btnNewButton.setBounds(159, 223, 80, 27);
		btnNewButton.setText("OK");

	}
}
