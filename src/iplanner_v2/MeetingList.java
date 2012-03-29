/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iplanner_v2;

/**
 * @author Rucira Ruwan Gunarathna
 *
 */

import java.util.ArrayList;

/**
 * @author Rucira Ruwan Gunarathna
 *
 */
public class MeetingList
implements java.io.Serializable {

    // Create a Vector to hold the train data
    private ArrayList<Meeting> al = new
ArrayList<Meeting>();

    /**
     * @return the al
     */
    public ArrayList<Meeting> getAl() {
        return al;
    }


 /**
     * @param al the al to set
     */
    public void setAl(ArrayList<Meeting> al) {
        this.al = al;
    }


public void addElement(Meeting ic) {
        al.add(ic);
    }

}
