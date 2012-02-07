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

/**
 * Exception due to miscellaneous error.
 * @author Sankha Narayan Guria
 * @version 1.0
 */
public class RException extends Exception {
    /**
     * Constructs an instance of <code>RException</code> with the specified detail message.
     * @param msg The detail message.
     */
    public RException(String msg) {
        super(msg);
    }
}
