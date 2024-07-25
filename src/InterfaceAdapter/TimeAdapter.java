package InterfaceAdapter;

import Enums.Month.Month;
import Enums.Month.Season;
import UseCase.World.TimeUseCase;

public class TimeAdapter {
    private TimeUseCase timeUseCase;

    public TimeAdapter(UseCaseManager useCaseManager) {
        this.timeUseCase = useCaseManager.getTimeUseCase();
    }

    public TimeUseCase getTimeUseCase() {
        return timeUseCase;
    }

    public void setTimeUseCase(TimeUseCase timeUseCase) {
        this.timeUseCase = timeUseCase;
    }

    public void setTime(int year, int month, int day, int hour, int minute){
        timeUseCase.setTimeValue(year, month, day, hour, minute);
    }

    public void timePass(int minute){
        timeUseCase.timePass(minute);
    }

    public int getYear(){
        return timeUseCase.getTime().getYear();
    }

    public Month getMonth(){
        return timeUseCase.getMonth();
    }

    public int getDay(){
        return timeUseCase.getTime().getDay();
    }

    public int getHour(){
        return timeUseCase.getTime().getHour();
    }

    public int getMinute(){
        return timeUseCase.getTime().getMinute();
    }

    public Season getSeason(){
        return timeUseCase.getSeason();
    }

    public String getSeasonName(){
        String lowerCaseSeason = getSeason().toString().toLowerCase();
        return lowerCaseSeason.substring(0, 1).toUpperCase() + lowerCaseSeason.substring(1);
    }

    public String getMonthName(){
        String lowerCaseMonth = getMonth().toString().toLowerCase();
        return lowerCaseMonth.substring(0, 1).toUpperCase() + lowerCaseMonth.substring(1);
    }

    public int getMonthValue(){
        return timeUseCase.getTime().getMonth();
    }

    public boolean isNight(){
        int hour = timeUseCase.getTime().getHour();
        if (hour >= 8 && hour <= 22 ){
            return false;
        }else {
            return true;
        }
    }
}
