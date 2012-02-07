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
 * This class can be used for displaying the help from R.
 * @author Sankha Narayan Guria
 * @version 1.0
 */

public class RHelp {

    private RBase rb;

    /**
     * This creates an instance of this class.<br>
     * <code>System.out</code> is used as the standard output stream (stdout) nad <code>System.err</code> is used as the error stream(stderr).
     * @param rf An instance of <code>RFactory</code> that has already been initialized.
     * @throws java.io.IOException Thrown if there is an error in redirecting the IO Streams.
     */
    public RHelp(RFactory rf) throws IOException
    {
        rb=new RBase(rf, System.out, System.err, RBase.VERY_QUIET);
    }

    /**
     * Constructor to instantiate an object of this class.
     * @param rf The <code>RFactory</code> object that has already been initialized.
     * @param out The <code>OutputStream</code> to print the normal standard output(stdout) messages of R. Can be <code>System.out</code> or <code>System.err</code> also.
     * @param err The <code>OutputStream</code> to print the error output(stderr) messages of R. Can be <code>System.out</code> or <code>System.err</code> also.
     * @param q The quietness level in which R will be run.<br> The follwing values may be used <ul><li>RBase.LESS_QUIET</li><li>RBase.NOT_QUIET</li><li>RBase.VERY_QUIET</li></ul>
     * @throws java.io.IOException Thrown if there is an error in redirecting the IO Streams.
     */
    public RHelp(RFactory rf, OutputStream out, OutputStream err, int q) throws IOException
    {
        rb=new RBase(rf, out, err, q);
    }

    /**
     * This tells R to show help on a topic. This is similiar to the command <code>help(<i>topic</i>)</code>.
     * @param topic The topic on which help is to shown.
     */
    public void getHelp(String topic)
    {
        rb.execute("help("+topic+")");
    }

    /**
     * This tells R to show some demos. This is similiar to the command <code>demo()</code>.
     */
    public void demo(){
        rb.execute("demo()");
    }

    /**
     * This starts the help in an HTML browser interface. This is similiar to the command <code>help.start()</code>.
     */
    public void helpStart(){
        rb.execute("help.start()");
    }

    /**
     * This tells R to search its help for a query. This is similiar to the command <code>help.search(<i>query</i>)</code>.
     * @param query The search string
     */
    public void helpSearch(String query){
        rb.execute("help.search("+query+")");
    }

    /**
     * This shows Apropos. This is similiar to the command <code>apropos(<i>query</i>)</code>.
     * @param query The string to be used as the parameter.
     */
    public void apropos(String query){
        rb.execute("apropos("+query+")");
    }

    /**
     * This method ensures that R has exited successfully before the <code>RHelp</code> object is destroyed.
     */
    protected void finalize(){
        rb.execute("q()");
    }
}
