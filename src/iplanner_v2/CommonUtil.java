/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iplanner_v2;

import java.util.ArrayList;

/**
 *
 * @author Thushan
 */
public class CommonUtil {

    public static int NUM_CLASSES = 9;   //Number of slots per day
    public static ArrayList<Integer> tr_keywords = new ArrayList<Integer>();
    public static ArrayList<Integer> tr_priorities = new ArrayList<Integer>();

    public static ArrayList<String> getAvailableTimeSlots() {
        ArrayList<String> timeSlots = new ArrayList<String>();
        timeSlots.add(" 8.00 AM -  9.00 AM");
        timeSlots.add(" 9.00 AM - 10.00 AM");
        timeSlots.add("10.00 AM - 11.00 AM");
        timeSlots.add("11.00 AM - 12.00 PM");
        timeSlots.add(" 1.00 PM -  2.00 PM");
        timeSlots.add(" 2.00 PM -  3.00 PM");
        timeSlots.add(" 3.00 PM -  4.00 PM");
        timeSlots.add(" 4.00 PM -  5.00 PM");
        timeSlots.add(" 5.00 PM -  6.00 PM");

        return timeSlots;
    }

    public static int timeSlotToInt(String str) {
        int var = -1;
        if ((" 8.00 AM -  9.00 AM").equals(str)) {
            var = 0;
        }
        if ((" 9.00 AM - 10.00 AM").equals(str)) {
            var = 1;
        }
        if (("10.00 AM - 11.00 AM").equals(str)) {
            var = 2;
        }
        if (("11.00 AM - 12.00 PM").equals(str)) {
            var = 3;
        }
        if ((" 1.00 PM -  2.00 PM").equals(str)) {
            var = 4;
        }
        if ((" 2.00 PM -  3.00 PM").equals(str)) {
            var = 5;
        }
        if ((" 3.00 PM -  4.00 PM").equals(str)) {
            var = 6;
        }
        if ((" 4.00 PM -  5.00 PM").equals(str)) {
            var = 7;
        }
        if ((" 5.00 PM -  6.00 PM").equals(str)) {
            var = 8;
        }

        return var;

    }

    public static  String intTotimeSlot(int val) {
        String timeSlot = null;
        if (val == 0) {
            timeSlot = (" 8.00 AM -  9.00 AM");
        }

        if (val == 1) {
            timeSlot = (" 9.00 AM - 10.00 AM");
        }
        if (val == 2) {
            timeSlot = (" 10.00 AM - 11.00 AM");
        }

        if (val == 3) {
            timeSlot = ("11.00 AM - 12.00 PM");
        }
        if (val == 4) {
            timeSlot = (" 1.00 PM -  2.00 PM");
        }
        if (val == 5) {
            timeSlot = (" 2.00 PM -  3.00 PM");
        }
        if (val == 6) {
            timeSlot = (" 3.00 PM -  4.00 PM");
        }
        if (val == 7) {
            timeSlot = (" 4.00 PM -  5.00 PM");
        }
        if (val == 8) {
            timeSlot = (" 5.00 PM -  6.00 PM");
        }

        return timeSlot;

    }

    public String intToString(int index) {
        String day = null;
        switch (index) {
            case 0:
                day = "Monday";
                break;
            case 1:
                day = "Tuesday";
                break;
            case 2:
                day = "Wednesday";
                break;
            case 3:
                day = "Thursdau";
                break;
            case 4:
                day = "Friday";
                break;
        }
        return day;
    }

    public MeetingWith intToMeetingWith(int index) {
        MeetingWith meetingWith = null;
        switch (index) {
            case 0:
                meetingWith = MeetingWith.BOSS;
                break;
            case 1:
                meetingWith = MeetingWith.CLIENT;
                break;
            case 2:
                meetingWith = MeetingWith.MANAGER;
                break;
            case 3:
                meetingWith = MeetingWith.FRIEND;
                break;
        }
        return meetingWith;
    }
}
