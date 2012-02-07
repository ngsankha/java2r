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
 * This class should be initialized at the start of the program.<p>
 * It creates a temporary session directory in the user's home directory's <code>.java2r</code> folder. It is deleted when the <code>close()</code> method is called.
 * @author Sankha Narayan Guria
 * @version 1.0 
 */
public class RFactory {

    private String rexec;
    private String sesnDir;

    /**
     * Creates an instance of the RFactory class.
     * @param rexec The path to the R executable
     * @exception nr.co.sngforge.java2r.RFactoryException Thrown if there is error while creating a <code>RFactory</code> object or temporary session directory.
     */
    public RFactory(String rexec) throws RFactoryException{
        this.rexec=rexec;
        setWorkDir();
    }

    private void setWorkDir() throws RFactoryException{
        boolean result=true;
        String wd=System.getProperty("user.home").replace("\\","/")+"/.java2r";
        File wdf=new File(wd);
        if(wdf.exists()==false)
            result=wdf.mkdir();
        String sesn=wd+"/s";
        int n=0;
        while(new File(sesn+n).exists()==true)
            n++;
        File sesnf=new File(sesn+n);
        result=sesnf.mkdir();
        if(result==false)
            throw new RFactoryException("Error in creating temporary session diectory");
        sesnDir=sesn+n;
    }

    /**
     * Returns the path of the R executable.
     */
    public String getRexec(){
        return rexec;
    }

    /**
     * Returns the path of the temporay session directory
     */
    public String getSesnDir(){
        return sesnDir;
    }

    /**
     * Closes the RFactory instance.<p>
     * This method should be called at the end of your program. This deletes the temporary session directory and sets the location of the session directory and the R exeutable to <code>null</code>.
     * @exception nr.co.sngforge.java2r.RFactoryException Thrown if there is error while deleting the temporary session directory.
     */
    public void close() throws RFactoryException{
        boolean result=true;
        File sesnf=new File(sesnDir);
        File inside[]=sesnf.listFiles();
        for(int i=0;i<inside.length;i++){
            result=inside[i].delete();
            if(result==false)
                throw new RFactoryException("Error in closing RFactory");
        }
        result=sesnf.delete();
        if(result==false)
                throw new RFactoryException("Error in closing RFactory");
        rexec="";
        sesnDir="";
    }
}
