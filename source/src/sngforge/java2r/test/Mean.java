/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sngforge.java2r.test;

import sngforge.java2r.*;
import java.io.*;

public class Mean {
    public static void main(String args[])throws Exception{
        RFactory rf=new RFactory("C:\\Program Files\\R\\R-2.9.2\\bin\\R.exe");
        
        /*RBase rb=new RBase(rf,fout,System.err,RBase.VERY_QUIET);
        rb.execute("x=c(8,4,5,9,5)");
        rb.execute("mean(x)");*/
        RScript rs=new RScript(rf);
        String result=rs.eval("C:\\Documents and Settings\\s.n.guria\\Desktop\\mmn.R");
        System.out.println(result);
        /*RGraphics rg=new RGraphics(rf,RGraphics.PDF,"C:\\Documents and Settings\\s.n.guria\\Desktop\\graph.pdf");
        rg.execute("plot(sin,-pi,pi)");
        rg.close(null);*/
        rf.close();
    }
}
