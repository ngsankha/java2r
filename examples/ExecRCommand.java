import nr.co.sngforge.java2r.*;

/*
	This example shows how to execute a R command from Java.
	This executes the command using RParser class.
*/

class ExecRCommand
{
	public static void main(String args[])throws Exception
	{
		//initializing RFactory class
		//the next line is for Windows. If on other systems comment out this line
		RFactory rf=new RFactory("c:/Program Files/R/R-2.7.1/bin/R.exe");//replace this with the location of R executable
		//the next line is for Linux. If on ther systems comment out this line
		//RFactory rf=new RFactory("R");
		RParser rp=new RParser(rf);
		//Perforimg a simple addition
		rp.eval("4+4",System.out,false);
		//Show summary about the trees dataset
		rp.eval("summary(trees)",System.out,false);
		rf.close();
	}
}
