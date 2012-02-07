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

import javax.swing.*;
import java.io.*;

/**
 * This class provides an interface to perform graphical operations with R.
 * @author Sankha Narayan Guria
 * @version 1.0
 */
public class RGraphics {

    private RBase rb;
    private RFactory rf;
    private int device;

    /**
     * This indicates that the current R Graphics Device is Postscript(PS).
     */
    public static final int POSTSCRIPT=0;
    /**
     * This indicates that the current R Graphics Device is Portable Document Format(PDF).
     */
    public static final int PDF=1;
    /**
     * This indicates that the current R Graphics Device is embeddable picture LaTeX form (PicTeX).
     */
    public static final int PICTEX=2;
    /**
     * This indicates that the current R Graphics Device is Portable Network Graphics(PNG).
     */
    public static final int PNG=3;
    /**
     * This indicates that the current R Graphics Device is JPEG.
     */
    public static final int JPEG=4;
    /**
     * This indicates that the current R Graphics Device is Bitmap(BMP).
     */
    public static final int BMP=5;
    /**
     * This indicates that the current R Graphics Device is XFig.
     */
    public static final int XFIG=6;
    /**
     * This indicates that the current R Graphics Device is TIFF.
     */
    public static final int TIFF=7;
    /**
     * This is not a real R Device. It just shows the graphics on-screen in a window.
     */
    public static final int JAVA2R=8;

    /**
     * This creates an instance of the <code>RGraphics</code> class.<br>
     * The <code>System.out</code> is used as the standard output stream (stdout) and <code>System.err</code> is used as the error stream(stderr).
     * @param rf An instance of the <code>RFactory</code> class that has been initialized.
     * @param device An integer representing the R Device to be used <ul><li>RGraphics.BMP</li><li>RGraphics.JAVA2R</li><li>RGraphics.JPEG</li><li>RGraphics.PDF</li><li>RGraphics.PICTEX</li><li>RGraphics.PNG</li><li>RGraphics.POSTSCRIPT</li><li>RGraphics.TIFF</li><li>RGraphics.XFIG</li></ul>
     * @param file The filename to which the graphics operations will be saved after closing the device. Should be left as <code>null</code> or "" if the device used is <code>RGraphics.JAVA2R</code>.
     * @throws java.io.IOException Thrown if there is an error in redirecting the IO Streams.
     */
    public RGraphics(RFactory rf,int device,String file) throws IOException{
        this.rf=rf;
        rb=new RBase(rf,System.out,System.err,RBase.VERY_QUIET);
        this.device=device;
        if(device==0)
            rb.execute("postscript(\""+file.replace(File.separator,"/")+"\")");
        else if(device==1)
            rb.execute("pdf(\""+file.replace(File.separator,"/")+"\")");
        else if(device==2)
            rb.execute("pictex(\""+file.replace(File.separator,"/")+"\")");
        else if(device==3)
            rb.execute("png(\""+file.replace(File.separator,"/")+"\")");
        else if(device==4)
            rb.execute("jpeg(\""+file.replace(File.separator,"/")+"\")");
        else if(device==5)
            rb.execute("bmp(\""+file.replace(File.separator,"/")+"\")");
        else if(device==6)
            rb.execute("xfig(\""+file.replace(File.separator,"/")+"\")");
        else if(device==7)
            rb.execute("tiff(\""+file.replace(File.separator,"/")+"\")");
        else if(device==8){
            rb.execute("jpeg(\""+rf.getSesnDir()+"/tmp.jpeg".replace(File.separator,"/")+"\")");
        }
    }

    /**
     * This command executes the command in R. This should preferably be a command that performs a graphicsl operation.
     * @param cmd The command exactly in the same form as R
     */
    public void execute(String cmd){
        rb.execute(cmd);
    }

    /**
     * This closes the current R Device.<br>
     * The graphics operations are saved in the target file. If the current device is <code>RGraphics.JAVA2R</code> then a window containing the graphics is displayed.
     * @param title The title of the window that will be displayed if the Graphics Device is <code>RGraphics.JAVA2R</code>. It has no effect on other Graphics Devices in which case it may be left as <code>null</code> or "".
     */
    public void close(String title){
        rb.execute("dev.off()");
        rb.finalize();
        while(new File(rf.getSesnDir()+"/tmp.jpeg").exists()==false);
        if(device==8){
            JFrame jf=new JFrame(title);
            jf.setSize(480,480);
            ImageIcon i=new ImageIcon(rf.getSesnDir()+"/tmp.jpeg");
            JLabel jl=new JLabel(i);
            jf.add(jl);
            jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jf.setResizable(false);
            jf.setVisible(true);
        }
    }
}
