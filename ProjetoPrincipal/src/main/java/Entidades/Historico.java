package Entidades;

public class Historico {

    long id;
    double[] lastHours;
    double[] monthAverage;
    double[] weekAverage;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public double[] getLastHours() {
        return lastHours;
    }
    public void setLastHours(double[] lastHours) {
        this.lastHours = lastHours;
    }

    public double[] getMonthAverage() {
        return monthAverage;
    }
    public void setMonthAverage(double[] monthAverage) {
        this.monthAverage = monthAverage;
    }

    public double[] getWeekAverage() {
        return weekAverage;
    }
    public void setWeekAverage(double[] weekAverage) {
        this.weekAverage = weekAverage;
    }
}
