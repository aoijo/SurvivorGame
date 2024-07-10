package UseCase;

import Entity.Time;
import Enums.Month.Season;
import Enums.Month.Month;

public class TimeUseCase {
    private Time time;

    public TimeUseCase(Time time) {
        this.time = time;
    }

    public TimeUseCase() {
        this.time = new Time();
        initialize(time);
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    private void initialize(Time time) {
        time.setYear(1000);
        time.setMonth(3); // Assuming March is month 3
        time.setDay(1);
        time.setHour(9);
        time.setMinute(0);
    }

    public void timePass(int minutes) {
        int newMinute = time.getMinute();
        int newHour = time.getHour();
        int newDay = time.getDay();
        int newMonth = time.getMonth();
        int newYear = time.getYear();

        newMinute += minutes;
        while (newMinute >= 60) {
            newMinute -= 60;
            newHour += 1;
        }
        while (newHour >= 24) {
            newHour -= 24;
            newDay += 1;
        }
        while (newDay > getDaysInMonth(newMonth, newYear)) {
            newDay -= getDaysInMonth(newMonth, newYear);
            newMonth += 1;
            if (newMonth > 12) {
                newMonth -= 12;
                newYear += 1;
            }
        }

        time.setMinute(newMinute);
        time.setHour(newHour);
        time.setDay(newDay);
        time.setMonth(newMonth);
        time.setYear(newYear);
    }

    private int getDaysInMonth(int month, int year) {
        switch (month) {
            case 1, 3, 5, 7, 8, 10, 12:
                return 31;
            case 4, 6, 9, 11:
                return 30;
            case 2:
                return isLeapYear(year) ? 29 : 28;
            default:
                throw new IllegalArgumentException("Invalid month value");
        }
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public void setTimeValue(int year, int month, int day, int hour, int minute) {
        if (year >= 0) {
            time.setYear(year);
        } else {
            throw new IllegalArgumentException("Invalid year value");
        }
        if (month >= 1 && month <= 12) {
            time.setMonth(month);
        } else {
            throw new IllegalArgumentException("Invalid month value");
        }
        if (day >= 1 && day <= getDaysInMonth(month, year)) {
            time.setDay(day);
        } else {
            throw new IllegalArgumentException("Invalid day value");
        }

        if (hour >= 0 && hour < 24) {
            time.setHour(hour);
        } else {
            throw new IllegalArgumentException("Invalid hour value");
        }
        if (minute >= 0 && minute < 60) {
            time.setMinute(minute);
        } else {
            throw new IllegalArgumentException("Invalid minute value");
        }
    }

    public Month getMonth() {
        return Month.values()[time.getMonth() - 1];
    }

    public Season getSeason() {
        switch (time.getMonth()) {
            case 3, 4, 5:
                return Season.SPRING;
            case 6, 7, 8:
                return Season.SUMMER;
            case 9, 10, 11:
                return Season.AUTUMN;
            case 12, 1, 2:
                return Season.WINTER;
            default:
                throw new IllegalArgumentException("Invalid month");
        }
    }
}
