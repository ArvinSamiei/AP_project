package controller;

public class TimerOfGame {
    private int second = 0;
    private int minute = 0;
    private int hour = 0;

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    @Override
    public String toString() {
        String secondString = second + "";
        String minuteString = minute + "";
        String hourString = hour + "";
        if (second < 10) {
            secondString = "0" + second;
        }
        if (minute < 10) {
            minuteString = "0" + minuteString;
        }
        if (hour < 10) {
            hourString = "0" + hourString;
        }
        return hourString + ":" + minuteString + ":" + secondString;
    }

    public void increaseTimer() {
        if (getSecond() == 59) {
            setSecond(0);
            if (getMinute() == 59) {
                setMinute(0);
                setHour(getHour() + 1);
            } else {
                setMinute(getMinute() + 1);
            }
        } else {
            setSecond(getSecond() + 1);
        }
    }
}
