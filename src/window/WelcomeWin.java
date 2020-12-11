package window;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import java.awt.DisplayMode;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class WelcomeWin {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			WelcomeWin window = new WelcomeWin();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		int alpha=0;
	    shell.setAlpha(0);
	    
	    Button btnSiM = new Button(shell, SWT.NONE);
	    btnSiM.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
	    btnSiM.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent e) {
	    		
	    		try {
					MainWindow window = new MainWindow();
					shell.close();
					window.open();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	    	}
	    });
	    btnSiM.setBounds(250, 162, 128, 41);
	    btnSiM.setText("Begin Simulation");
	    
	    Label lblDesigner = new Label(shell, SWT.NONE);
	    lblDesigner.setBounds(180, 327, 301, 35);
	    lblDesigner.setText("Designer：Xiaoran Li,  Chenghuan Li,  Ruizhe Zhang");
	    while(shell.getAlpha()<255){
		    shell.setAlpha(alpha++);
		    try {
		        Thread.sleep(1);
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		}
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
		
		shell = new Shell(SWT.CLOSE);
		shell.setSize(640, 425);
		shell.setImage(null);
		shell.setBackground(SWTResourceManager.getColor(255, 255, 255));
		shell.setText("SWT Application");
	//	shell.setLayout(new GridLayout(1, false));
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setText("New Button");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setImage(SWTResourceManager.getImage("src/window/wel.png"));
		lblNewLabel.setBounds(0, 0, 644, 133);
		//btnNewButton_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		Button btnNewButton_2 = new Button(shell, SWT.NONE);
		btnNewButton_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		btnNewButton_2.setBounds(250, 232, 128, 41);
		btnNewButton_2.setText("Exit");
		//lblNewLabel.setText("New Label");

	}
}
