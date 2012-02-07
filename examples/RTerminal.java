import nr.co.sngforge.java2r.*;
import java.io.*;

/*
	This example creates a R Terminal based on Java.
*/

class RTerminal
{
	public static void main(String args[])throws Exception
	{
		//initializing RFactory class
		//the next line is for Windows. If on other systems comment out this line
		RFactory rf=new RFactory("c:/Program Files/R/R-2.7.1/bin/R.exe");//replace this with the location of R executable
		//the next line is for Linux. If on ther systems comment out this line
		//RFactory rf=new RFactory("R");
		//for(int i=0;i<=10000;i++);
		System.out.println("Enter q() to exit");
		//to take input
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String cmd="";
		//initializing RBase class
		RBase rb=new RBase(rf,System.out,System.err,RBase.VERY_QUIET);
		while(cmd.equals("q()")==false)
		{
			cmd=br.readLine();
			rb.execute(cmd);
		}
		rf.close();
	}
}
