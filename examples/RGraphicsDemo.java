import nr.co.sngforge.java2r.*;

/*
	This example shows a Graph in a Swing Window
*/

class RGraphicsDemo
{
	public static void main(String args[])throws Exception
	{
		//initializing RFactory class
		//the next line is for Windows. If on other systems comment out this line
		RFactory rf=new RFactory("c:/Program Files/R/R-2.7.1/bin/R.exe");//replace this with the location of R executable
		//the next line is for Linux. If on ther systems comment out this line
		//RFactory rf=new RFactory("R");
		RGraphics rg=new RGraphics(rf,RGraphics.JAVA2R,"");
		rg.execute("plot(sin, -pi, 2*pi)");
		rf.close();
	}
}
