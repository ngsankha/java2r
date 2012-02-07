import nr.co.sngforge.java2r.*;
import java.io.*;

/*
	This example shows miscellaneous capabilities
*/

class RMiscDemo
{
	public static void main(String args[])throws Exception
	{
		//initializing RFactory class
		//the next line is for Windows. If on other systems comment out this line
		RFactory rf=new RFactory("c:/Program Files/R/R-2.7.1/bin/R.exe");//replace this with the location of R executable
		//the next line is for Linux. If on ther systems comment out this line
		//RFactory rf=new RFactory("R");
		System.out.println("Please enter the path to a R script file");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String path=br.readLine();
		RScript rs=new RScript(rf);
		rs.eval(path);
		System.out.println("Now I will show you a R help window.");
		RHelp rh=new RHelp(rf);
		rh.getHelp("graphics");
		rf.close();
	}
}
