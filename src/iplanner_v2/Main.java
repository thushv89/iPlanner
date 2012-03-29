/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iplanner_v2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * @author Rucira Ruwan Gunarathna
 *
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static ArrayList<Meeting> arrayList = new ArrayList<Meeting>();
    static MeetingList meetingList = new MeetingList();
    static TrainingSet trainingSet;
    private static ObjectInputStream input;
    private static Meeting meeting = new Meeting();

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        
        meeting.setAll(0, 0, MeetingWith.BOSS, "office", "future plans", 2);
        meetingList = new MeetingList();
        meetingList.addElement(meeting);
        if (IOHandler.writeObject(meetingList, "object.dat")) {
            System.out.println("saved...");
        } else {
            System.out.println("Error...");
        }


        meeting.setAll(0, 0, MeetingWith.BOSS, "office", "future plans", 2);
        trainingSet = new TrainingSet();
        trainingSet.addElement(meeting);

        if (IOHandler.writeObject(trainingSet, "training_data.tr")) {
            System.out.println("saved...");
        } else {
            System.out.println("Error...");
        }

        // meetingList = IOHandler.readObject("training_data.tr");
        meetingList = IOHandler.readObject("object.dat");
        arrayList = meetingList.getAl();
        System.out.println(">> " + arrayList.get(0).getMeetingWith());

    }
}
