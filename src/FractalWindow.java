import org.apache.commons.math3.complex.Complex;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;



public class FractalWindow {
	private String type;
	private double cReal;
	private double cImaginary;
	
	private FractalMaker fractal;
	
	private Shell shell;
	private Display display;
	
	public FractalWindow(String type){
		this.type = type;
		fractal = new FractalMaker();

		display = Display.getDefault();
		runWindow();
	}
	
	public FractalWindow(String type, double cReal, double cImaginary){
		this.type = type;
		this.cReal = cReal;
		this.cImaginary = cImaginary;
		fractal = new FractalMaker();
		
		display = Display.getDefault();
		runWindow();
	}
	
	public void runWindow()
	{
		shell = new Shell(display, SWT.CLOSE | SWT.TITLE);
		shell.setData("AboutWindow");
		shell.setSize(fractal.getXPixels(),fractal.getYPixels());
		
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		
		shell.setText(type);
		shell.setLocation (x, y);
		
		final Canvas canvas = new Canvas(shell, SWT.NONE);
		canvas.setBounds(0, 0, fractal.getXPixels(), fractal.getYPixels());

		canvas.addPaintListener(new PaintListener() {
		      public void paintControl(PaintEvent e) {
		  		switch(type){
					case "Mandelbrot": fractal.iterateMandelbrot(); break;
					case "Julia1": fractal.iterateJulia(1, new Complex(cReal, cImaginary)); break;
					case "Julia2": fractal.iterateJulia(2, new Complex(cReal, cImaginary)); break;
					case "Julia3": fractal.iterateJulia(3, new Complex(cReal, cImaginary)); break;
					case "Julia4": fractal.iterateJulia(4, new Complex(cReal, cImaginary)); break;
					case "Julia5": fractal.iterateJulia(5, new Complex(cReal, cImaginary)); break;
				}
		    	  
		    	int[][] iterationArr = fractal.getIterationArray();
		  		
		    	for(int i = 0; i < iterationArr.length; i++){
					for(int j = 0; j < iterationArr[i].length; j++){
						if(iterationArr[j][i] < fractal.getMaxIterations()){
							e.gc.setForeground(e.display.getSystemColor(SWT.COLOR_WHITE));
							e.gc.drawLine(j, i, j, i);
						}
						
						else{
							e.gc.setForeground(e.display.getSystemColor(SWT.COLOR_BLACK));
							e.gc.drawLine(j, i, j, i);
						}
					}
		    	}
		      }
		    });
		
		canvas.addMouseListener(new MouseListener(){
			public void mouseDown(MouseEvent event){
				if(event.button == 3){
					fractal.zoom(3.0/2.0, event.x, event.y);
					System.out.println("ZOOM OUT " + event.x + " " + event.y);
				}
				else{
					fractal.zoom(2.0/3.0, event.x, event.y);
					System.out.println("ZOOM IN " + event.x + " " + event.y);
				}
				canvas.redraw();
			}

			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseUp(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		shell.open();
		
		
		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch())
			{
				display.sleep();
			}
		}
	}
}


