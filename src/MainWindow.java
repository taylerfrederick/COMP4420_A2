import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Label;

public class MainWindow {
	private Display display;
	private Shell shell;
	
	private Button mandelbrot;
	private Button julia1;
	private Button julia2;
	private Button julia3;
	private Button julia4;
	private Button julia5;
	
	private Text cReal;
	private Text cImaginary;
	
	public static MainWindow instance = null;
	
	public void runWindow() {
		
		shell = new Shell(display, SWT.CLOSE | SWT.TITLE);
		shell.setSize(500, 500);
		shell.setText("Fractal Maker");
		
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		
		shell.setLocation (x, y);
		shell.addListener(SWT.Close, new Listener()
		{
			public void handleEvent(Event event) {
				closeAll();
			}
		});
		
		
		
		mandelbrot = new Button(shell, SWT.NONE);
		mandelbrot.setBounds(0, 50, 111, 27);
		mandelbrot.setText("Mandelbrot Set");
		mandelbrot.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				new FractalWindow("Mandelbrot");
			}
		});
		
		Label cRealLabel = new Label(shell, SWT.NONE);
		cRealLabel.setBounds(0, 120, 120, 20);
		cRealLabel.setText("Real part of C: ");
		
		cReal = new Text(shell, SWT.NONE);
		cReal.setBounds(130, 115, 50, 27);
		
		Label cImaginaryLabel = new Label(shell, SWT.NONE);
		cImaginaryLabel.setBounds(0, 150, 120, 20);
		cImaginaryLabel.setText("Imaginary part of C: ");
		
		cImaginary = new Text(shell, SWT.NONE);
		cImaginary.setBounds(130, 145, 50, 27);
		
		julia1 = new Button(shell, SWT.NONE);
		julia1.setBounds(0, 180, 111, 27);
		julia1.setText("Julia Set (formula 1)");
		julia1.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				String real = cReal.getText();
				String imaginary = cImaginary.getText();
				
				if(isNumeric(real) && isNumeric(imaginary)){
					new FractalWindow("Julia1", Double.parseDouble(real), Double.parseDouble(imaginary));
				}
			}
		});
		
		
		julia2 = new Button(shell, SWT.NONE);
		julia2.setBounds(0, 210, 111, 27);
		julia2.setText("Julia Set (formula 2)");
		julia2.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				String real = cReal.getText();
				String imaginary = cImaginary.getText();
				
				if(isNumeric(real) && isNumeric(imaginary)){
					new FractalWindow("Julia2", Double.parseDouble(real), Double.parseDouble(imaginary));
				}
			}
		});
		
		julia3 = new Button(shell, SWT.NONE);
		julia3.setBounds(0, 240, 111, 27);
		julia3.setText("Julia Set (formula 3)");
		julia3.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				String real = cReal.getText();
				String imaginary = cImaginary.getText();
				
				if(isNumeric(real) && isNumeric(imaginary)){
					new FractalWindow("Julia3", Double.parseDouble(real), Double.parseDouble(imaginary));
				}
			}
		});
		
		julia4 = new Button(shell, SWT.NONE);
		julia4.setBounds(0, 270, 111, 27);
		julia4.setText("Julia Set (formula 4)");
		julia4.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				String real = cReal.getText();
				String imaginary = cImaginary.getText();
				
				if(isNumeric(real) && isNumeric(imaginary)){
					new FractalWindow("Julia4", Double.parseDouble(real), Double.parseDouble(imaginary));
				}
			}
		});
		
		julia5 = new Button(shell, SWT.NONE);
		julia5.setBounds(0, 300, 111, 27);
		julia5.setText("Julia Set (formula 5)");
		julia5.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				String real = cReal.getText();
				String imaginary = cImaginary.getText();
				
				if(isNumeric(real) && isNumeric(imaginary)){
					new FractalWindow("Julia5", Double.parseDouble(real), Double.parseDouble(imaginary));
				}
			}
		});
		
		//======= shell open, close ======
		shell.open();
		while (!shell.isDisposed()){   
			if (!display.readAndDispatch()){
				display.sleep();
			}
		}
		shell.dispose();
	}

	
	private void closeAll(){
	    Shell[] shells = Display.getCurrent().getShells();
        for(Shell shell : shells){
            String data = (String) shell.getData();
            if(data != null){
                shell.dispose();
            }
        }
	}

	public MainWindow(){
		display = Display.getDefault();
		runWindow();
	}

	public static void main(String[] args){
		instance = new MainWindow();
	}
	
	public boolean isNumeric(String str){
	  return str.matches("-?\\d+(\\.\\d+)?");
	}
}