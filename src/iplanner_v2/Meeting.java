package iplanner_v2;

/**
 * @author Rucira Ruwan Gunarathna
 *
 */
/**
 * @author Rucira Ruwan Gunarathna
 *
 */
public class Meeting implements java.io.Serializable {
    //input holder

    private String time, location, task;
    private MeetingWith mWith;
    private int day,timeSlot;
    private int priority;

    public void setAll(int day, int timeSlot, MeetingWith meetingWith, String location, String task, int priority) {
        this.day = day;
        this.timeSlot = timeSlot;
        this.mWith = meetingWith;
        this.location = location;
        this.task = task;
        this.setPriority(priority);
    }

    /**
     * @return the date
     */
    public int getDay() {
        return day;
    }

    /**
     * @param day
     */
    public void setDate(int day) {
        this.day = day;
    }

    /**
     * @return the time
     */
    public int getTimeSlot() {
        return timeSlot;
    }

    /**
     * @param timeSlot the time to set
     */
    public void setTimeSlot(int timeSlot) {
        this.timeSlot = timeSlot;
    }

    /**
     * @return the meetingWith
     */
    public MeetingWith getMeetingWith() {
        return mWith;
    }

    public int getMeetingWithNum() {
        return mWith.ordinal();
    }

    /**
     * @param meetingWith the meetingWith to set
     */
    public void setMeetingWith(MeetingWith meetingWith) {
        this.mWith = meetingWith;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the task
     */
    public String getTask() {
        return task;
    }

    /**
     * @param task the task to set
     */
    public void setTask(String task) {
        this.task = task;
    }

    /**
     * @return the priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }
}
