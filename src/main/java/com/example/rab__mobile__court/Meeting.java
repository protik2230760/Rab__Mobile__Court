package com.example.rab__mobile__court;

public class Meeting {

    private final String meetingId;
    private final String time;
    private final String participants;
    private final String date;

    public Meeting(String meetingId, String time, String date, String participants) {
        this.meetingId = meetingId;
        this.time = time;
        this.date = date;
        this.participants = participants;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public String getTime() {
        return time;
    }

    public String getParticipants() {
        return participants;
    }

    public String getDate() {
        return date;
    }

    public String toFileString() {
        return meetingId + "," + time + "," + date + "," + participants;
    }

    public static Meeting fromFileString(String fileString) {
        String[] tokens = fileString.split(",");
        if (tokens.length < 4) {
            throw new IllegalArgumentException("Invalid file format.");
        }
        return new Meeting(tokens[0], tokens[1], tokens[2], tokens[3]);
    }
}
