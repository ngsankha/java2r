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
 * This class parses R commands by converting them to a script.<br>
 * The command to be executed can be passed as a String and multiple lines can be entered by using the newline character. The workspace image is saved and is automatically retrieved during the next operation. The results can obtained as a String or it may be dumped into an <code>OutputStream</code>.
 * @author Sankha Narayan Guria
 * @version 1.0
 */
public class RParser {

    private RFactory rf;

    /**
     * This creates an object of this class.
     * @param rf A <code>RFactory</code> object that has already been initialized.
     * @throws java.io.IOException Thrown if there is an error while performing IO Operations.
     * @throws java.lang.InterruptedException Thrown if R is interrpted during execution.
     * @throws nr.co.sngforge.java2r.RException Thrown if there is any other error.
     */
    public RParser(RFactory rf) throws IOException, InterruptedException, RException{
        this.rf=rf;
        createBlankImage();
    }

    /**
     * This evaluates the R command(s) by executing it as a script.<br>
     * The last saved or the default workspace image is retrieved and after execution it is again temporarily stored. Multple commnad can be separated by the newline character.
     * @param cmd The command(s) to be executed.
     * @return The results are returned as a String.
     * @throws java.io.IOException Thrown if there is an error while performing IO Operations.
     * @throws java.lang.InterruptedException Thrown if R is interrpted during execution.
     * @throws nr.co.sngforge.java2r.RException Thrown if there is any other error.
     */
    public String eval(String cmd)throws IOException, InterruptedException, RException{
        FileWriter code=new FileWriter(rf.getSesnDir()+"/tmp.R");
        code.write("attach(\""+rf.getSesnDir().replace(File.separator, "/")+"/tmp.RData\")\n");
        code.write(cmd+"\n");
        code.write("save.image(file=\""+rf.getSesnDir().replace(File.separator,"/")+"/tmp.RData\")\n");
        code.write("q(save = \"no\", status = 0, runLast = FALSE)");
        code.close();
        Process r=Runtime.getRuntime().exec(rf.getRexec()+" CMD BATCH --slave \""+rf.getSesnDir()+"/tmp.R\"");
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
    private void createBlankImage()throws IOException, InterruptedException, RException{
        FileWriter code=new FileWriter(rf.getSesnDir()+"/tmp.R");
        code.write("save.image(file=\""+rf.getSesnDir().replace(File.separator, "/")+"/tmp.RData\")");
        code.close();
        Process r=Runtime.getRuntime().exec(rf.getRexec()+" CMD BATCH \""+rf.getSesnDir()+"/tmp.R\"");
        int i=r.waitFor();
        if(i!=0)
            throw new RException("Unspecified Error Occured!");
    }

    /**
     * This evaluates the R command(s) by executing it as a script.<br>
     * The last saved or the default workspace image is retrieved and after execution it is again temporarily stored. Multple commnad can be separated by the newline character.
     * @param cmd The command(s) to be executed.
     * @param out The <code>OutputStream</code> in which the results will be dumped.
     * @param close A boolean value specifying whether the <code>OutputStream</code> should be closed or not. It is useful when the <code>OutputStream</code> is writing to a file.
     * @throws java.io.IOException Thrown if there is an error while performing IO Operations.
     * @throws java.lang.InterruptedException Thrown if R is interrpted during execution.
     * @throws nr.co.sngforge.java2r.RException Thrown if there is any other error.
     */
    public void eval(String cmd, OutputStream out, boolean close)throws IOException, InterruptedException, RException{
        FileWriter code=new FileWriter(rf.getSesnDir()+"/tmp.R");
        code.write("attach(\""+rf.getSesnDir().replace(File.separator, "/")+"/tmp.RData\")\n");
        code.write(cmd+"\n");
        code.write("save.image(file=\""+rf.getSesnDir().replace(File.separator,"/")+"/tmp.RData\")\n");
        code.write("q(save = \"no\",, runLast = FALSE)");
        code.close();
        Process r=Runtime.getRuntime().exec(rf.getRexec()+" CMD BATCH --slave \""+rf.getSesnDir()+"/tmp.R\"");
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
}
