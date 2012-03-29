/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iplanner_v2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thushan
 */
public class IOHandler {

    static MeetingList meetingList;
    static TrainingSet trainingSet;

    public static MeetingList readObject(String fileName) {
        ObjectInputStream input = null;
        try {
            input = new ObjectInputStream(new FileInputStream(fileName));
            meetingList = (MeetingList) (input.readObject());
            input.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IOHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IOHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                input.close();
            } catch (IOException ex) {
                Logger.getLogger(IOHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return meetingList;
    }
     public static TrainingSet readTrainingSet(String fileName) {
        ObjectInputStream input = null;
        try {
            input = new ObjectInputStream(new FileInputStream(fileName));
            trainingSet = (TrainingSet) (input.readObject());
            input.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IOHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IOHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                input.close();
            } catch (IOException ex) {
                Logger.getLogger(IOHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return trainingSet;
    }

    public static boolean writeObject(Object obj, String filename) {
        boolean status = false;
        ObjectOutputStream out;
        try {
            out = new ObjectOutputStream(new FileOutputStream(filename));
            // Write an object to the file
            out.writeObject(obj);

            // Close the output streame
            out.close();

            // status means wether file is saved or not
            status = true;

        } catch (IOException e) {
            status = false;
        }
        return status;
    }

    /**
     *
     * @param filename
     * @param index
     * @return boolean
     */
    public static boolean deleteObject(String filename, int index) {
        boolean status = false;

        // Deleting a spesific object

        // Create a ArrayList to hold the trainingdata
        ArrayList<Meeting> meetings = new ArrayList<Meeting>();
        meetings = meetingList.getAl();

        // remove from the arrayList
        meetings.remove(index);

        // again update the meetingList
        meetingList.setAl(meetings);

        // write to the file
        if (writeObject(meetingList, filename)) {
            status = true;

        } else {
            status = false;
        }

        return status;
    }

    public static boolean writeTrainingData(TrainingSet trainingSet) {
        boolean status = false;
        if (writeObject(trainingSet, "training_data.tr")) {
            status = true;
        }
        else{
            status = false;
        }
        return status;
    }

    public static TrainingSet getTrainingData() {
        TrainingSet trainingSet = readTrainingSet("training_data.tr");
        return trainingSet;
    }
}

