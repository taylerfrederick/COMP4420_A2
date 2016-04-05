import org.apache.commons.math3.complex.Complex;

public class FractalMaker {
	private int xPixels;
	private int yPixels;
	
	private double minReal;
	private double maxReal;
	private double minImaginary;
	private double maxImaginary;
	
	private double maxModulus;
	private int maxIterations;
	
	private int[][] iterationArr;
	
	public FractalMaker(){
		xPixels = 400;
		yPixels = 400;
		
		minReal = -2.0;
		maxReal = 2.0;
		minImaginary = -2.0;
		maxImaginary = 2.0;
		
		maxModulus = 2.0;
		maxIterations = 100;
		
		iterationArr = new int[xPixels][yPixels];
	}
	
	public void iterateMandelbrot(){
		for(int i = 0; i < this.yPixels; i++) {
			for(int j = 0; j < xPixels; j++){
				Complex c = new Complex(minReal + j*xResolution(), minImaginary + i*yResolution());
				Complex z = c;
				
				int iters = 0;
				while(iters < maxIterations && getModulus(z) < maxModulus){
					z = function1(z, c);
					iters++;
				}
				
				iterationArr[j][i] = iters;
			}
		}
	}
	
	public int getXPixels(){
		return xPixels;
	}
	
	public int getYPixels(){
		return yPixels;
	}
	
	public int[][] getIterationArray(){
		return iterationArr;
	}
	
	public int getMaxIterations(){
		return maxIterations;
	}

	public void iterateJulia(int functionNum, Complex c){
		for(int i = 0; i < yPixels; i++){
			for(int j = 0; j < xPixels; j++){
				Complex z = new Complex(minReal + j*xResolution(), minImaginary + i*yResolution());
				
				int iters = 0;
				while(iters < maxIterations && getModulus(z) < maxModulus){
					z = decideFunction(functionNum, z, c);
					iters++;
				}
				
				iterationArr[j][i] = iters;
			}
		}
	}
	
	public Complex decideFunction(int functionNum, Complex z, Complex c){
		switch(functionNum) {
			case 1: return function1(z, c);
			case 2: return function2(z, c);
			case 3: return function3(z, c);
			case 4: return function4(z, c);
			case 5: return function5(z, c);
		}
		
		return null;
	}
	
	public void printIterations(){
		for(int[] row : iterationArr){
			for(int cell : row){
				System.out.print(cell + ", ");
			}
			System.out.println();
		}
	}
	
	public Complex function1(Complex z, Complex c){
		return (z.pow(2)).add(c); // g(z) = z^2 + c
	}
	
	public Complex function2(Complex z, Complex c){
		return (z.pow(3)).add(c); // g(z) = z^3 + c
	}
	
	public Complex function3(Complex z, Complex c){
		Complex base;
		Complex addend;
		
		base = c.multiply(z);
		addend = c.multiply(-1);
		addend = c.add(1);
		addend = c.divide(z);
		
		return base.add(addend); // g(z) = cz + (1-c)/z
	}
	
	public Complex function4(Complex z, Complex c){
		Complex base;
		Complex addend;
		
		base = z.pow(2);
		addend = c.divide(z);
		
		return base.add(addend); // g(z) = z^2 + c/z
	}
	
	public Complex function5(Complex z, Complex c){
		Complex base;
		Complex addend;
		
		base = c.multiply(z);
		addend = c.multiply(-1);
		addend = c.add(1);
		addend = c.divide(z.pow(2));
		
		return base.add(addend); // g(z) = cz + (1 - c)/z^2
	}
	
	public double getModulus(Complex z){		
		return z.abs();
	}
	
	public void zoom(double zoomFactor, int x, int y){
		double xCenter = minReal + x*xResolution();
		double yCenter = minImaginary + y*yResolution();
		double newXRes = zoomFactor*xResolution();
		double newYRes = zoomFactor*yResolution();
		
		minReal = xCenter - newXRes*xPixels/2;
		maxReal = xCenter + newXRes*xPixels/2;
		minImaginary = yCenter - newYRes*yPixels/2;
		maxImaginary = yCenter + newYRes*yPixels/2.0;
		
		maxIterations -= 10*Math.log(zoomFactor);
		maxModulus += Math.log(zoomFactor);
		
		System.out.println("Iterations: " + maxIterations);
		System.out.println("Max Modulus: " + maxModulus);
	}
	
	public double xResolution(){
		return (maxReal - minReal)/xPixels;
	}
	
	public double yResolution(){
		return (maxImaginary - minImaginary)/yPixels;
	}

}
