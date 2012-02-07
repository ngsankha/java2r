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
 * This class forms the basic port to the R executable. It can be used for interacting with R.
 * @author Sankha Narayan Guria
 * @version 1.0
 */

public class RBase {

    private RFactory rf;
    private OutputStream out;
    private Process r;
    private PrintStream rIn;

    /**
     * Parameter to specify that R runs in the slave and silent mode. In this case it gives the least messages.
     */
    public static final int VERY_QUIET=0;
    /**
     * Parameter to specify that R runs in the silent mode. In this case it gives some messages.
     */
    public static final int LESS_QUIET=1;
    /**
     * Parameter to specify that R runs in normal mode. In this case it gives all messages.
     */
    public static final int NOT_QUIET=2;

    private static class LineRedirecter extends Thread {
        private InputStream in;
        private OutputStream out;

        LineRedirecter(InputStream in, OutputStream out) {
            this.in = in;
            this.out = out;
        }

        public void run()
        {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                PrintStream printStream = new PrintStream(out);
                String line;

                while ( (line = reader.readLine()) != null)
                    printStream.println(line);
            } catch (IOException exc) {
                System.err.println(exc);
            }
        }

    }

    /**
     * Constructor to instantiate an object of this class.
     * @param rf The <code>RFactory</code> object that has already been initialized.
     * @param out The <code>OutputStream</code> to print the normal standard output(stdout) messages of R. Can be <code>System.out</code> or <code>System.err</code> also.
     * @param err The <code>OutputStream</code> to print the error output(stderr) messages of R. Can be <code>System.out</code> or <code>System.err</code> also.
     * @param q The quietness level in which R will be run.<br> The follwing values may be used <ul><li>RBase.LESS_QUIET</li><li>RBase.NOT_QUIET</li><li>RBase.VERY_QUIET</li></ul>
     * @throws java.io.IOException Thrown if there is an error in redirecting the IO Streams.
     */
    public RBase(RFactory rf, OutputStream out, OutputStream err, int q) throws IOException{
        this.rf=rf;
        this.out=out;
        String process=rf.getRexec()+" --no-save";
        if(q==0)
            process=process+" --slave";
        else if(q==1)
            process=process+" -q";
        r=Runtime.getRuntime().exec(process);
        new LineRedirecter(r.getInputStream(), out).start();
        new LineRedirecter(r.getErrorStream(), err).start();
        rIn = new PrintStream(r.getOutputStream());
    }

    /**
     * This command executes the command in R.
     * @param cmd The command exactly in the same form as R
     */
    public void execute(String cmd){
        rIn.println(cmd);
        rIn.flush();
    }

    /**
     * This method ensures that R has exited successfully before the <code>RBase</code> object is destroyed.
     */
    protected void finalize(){
        execute("q()");
    }
}
