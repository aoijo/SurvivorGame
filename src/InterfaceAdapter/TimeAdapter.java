package InterfaceAdapter;

import Enums.Season;
import UseCase.TimeUseCase;

public class TimeAdapter {
    private TimeUseCase timeUseCase;

    public TimeAdapter(TimeUseCase timeUseCase) {
        this.timeUseCase = timeUseCase;
    }

    public TimeAdapter() {
        this.timeUseCase = new TimeUseCase();
    }

    public TimeUseCase getTimeUseCase() {
        return timeUseCase;
    }

    public void setTimeUseCase(TimeUseCase timeUseCase) {
        this.timeUseCase = timeUseCase;
    }

    public int[] getTime(){
        int[] time = new int[5];
        time[0] = timeUseCase.getTime().getYear();
        time[1] = timeUseCase.getTime().getMonth();
        time[2] = timeUseCase.getTime().getDay();
        time[3] = timeUseCase.getTime().getHour();
        time[4] = timeUseCase.getTime().getMinute();
        return time;
    }

    public void setTime(int year, int month, int day, int hour, int minute){
        timeUseCase.setTimeValue(year, month, day, hour, minute);
    }

    public void timePass(int minute){
        timeUseCase.timePass(minute);
    }

    public void getMonth(){
        timeUseCase.getMonth();
    }

    public Season getSeason(){
        return timeUseCase.getSeason();
    }

    public String getSeasonName(){
        String lowerCaseSeason = getSeason().toString().toLowerCase();
        return lowerCaseSeason.substring(0, 1).toUpperCase() + lowerCaseSeason.substring(1);
    }
}
