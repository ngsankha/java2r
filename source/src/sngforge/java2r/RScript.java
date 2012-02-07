/*
 *  Java2R: A library to connect Java and R
 *  Copyright (C) 2009  Sankha Narayan Guria
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package sngforge.java2r;

import java.io.*;

/**
 * This class parses R scipts stored in a file.<br>
 * After the execution the <code>proc.time()</code> command is executed automatically. The command <code>q(save = \"no\", status = 0, runLast = FALSE)</code> needs to be the last line of your script to prevent this.
 * @author Sankha Narayan Guria
 * @version 1.0
 */
public class RScript {
    private RFactory rf;

    /**
     * This creates an instance of this class.
     * @param rf A <code>RFactory</code> object that has already ben instantiated.
     */
    public RScript(RFactory rf){
        this.rf=rf;
    }

    /**
     * This executes the script.
     * @param file A <code>File</code> refering to the script file
     * @return The results in a String
     * @throws java.io.IOException Thrown if there is an error while performing IO Operations.
     * @throws java.lang.InterruptedException Thrown if R is interrpted during execution.
     * @throws nr.co.sngforge.java2r.RException Thrown if there is any other error.
     */
    public String eval(File file)throws IOException, InterruptedException, RException{
        Process r=Runtime.getRuntime().exec(rf.getRexec()+" CMD BATCH --slave \""+file.getAbsolutePath()+"\" \""+rf.getSesnDir()+"/tmp.Rout\"");
        int i=r.waitFor();
        if(i!=0)
            throw new RException("Unspecified Error Occured!");
        FileReader fr=new FileReader(rf.getSesnDir()+"/tmp.Rout");
        BufferedReader br=new BufferedReader(fr);
        String out="", line="";
        while((line=br.readLine())!=null)
            out=out+line+"\n";
        fr.close();
        return out;
    }

    /**
     * This executes the script.
     * @param file Path to the script file
     * @return The results in a String
     * @throws java.io.IOException Thrown if there is an error while performing IO Operations.
     * @throws java.lang.InterruptedException Thrown if R is interrpted during execution.
     * @throws nr.co.sngforge.java2r.RException Thrown if there is any other error.
     */
    public String eval(String file)throws IOException, InterruptedException, RException{
        return eval(new File(file));
    }

    /**
     * This executes the script.
     * @param file A <code>File</code> refering to the script file
     * @param out The <code>OutputStream</code> in which the results will be dumped.
     * @param close A boolean value specifying whether the <code>OutputStream</code> should be closed or not. It is useful when the <code>OutputStream</code> is writing to a file.
     * @throws java.io.IOException Thrown if there is an error while performing IO Operations.
     * @throws java.lang.InterruptedException Thrown if R is interrpted during execution.
     * @throws nr.co.sngforge.java2r.RException Thrown if there is any other error.
     */
    public void eval(File file, OutputStream out, boolean close)throws IOException, InterruptedException, RException{
        Process r=Runtime.getRuntime().exec(rf.getRexec()+" CMD BATCH --slave \""+file.getAbsolutePath()+"\" \""+rf.getSesnDir()+"/tmp.Rout\"");
        int i=r.waitFor();
        if(i!=0)
            throw new RException("Unspecified Error Occured!");
        FileReader fr=new FileReader(rf.getSesnDir()+"/tmp.Rout");
        BufferedReader br=new BufferedReader(fr);
        PrintStream printStream = new PrintStream(out);
        String line="";
        while((line=br.readLine())!=null)
            printStream.println(line);
        fr.close();
        if(close)
            out.close();
    }

    /**
     * This executes the script.
     * @param file Path to the script file
     * @param out The <code>OutputStream</code> in which the results will be dumped.
     * @param close A boolean value specifying whether the <code>OutputStream</code> should be closed or not. It is useful when the <code>OutputStream</code> is writing to a file.
     * @throws java.io.IOException Thrown if there is an error while performing IO Operations.
     * @throws java.lang.InterruptedException Thrown if R is interrpted during execution.
     * @throws nr.co.sngforge.java2r.RException Thrown if there is any other error.
     */
    public void eval(String file, OutputStream out, boolean close)throws IOException, InterruptedException, RException{
        eval(new File(file),out,close);
    }
}
