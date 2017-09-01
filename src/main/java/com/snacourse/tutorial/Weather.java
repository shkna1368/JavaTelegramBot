package com.snacourse.tutorial;

public class Weather {



    private double windSpeed;
    private double windDegree;
    private double seaLevel;
    private double pressure;
    private int humidity;
    private double tempMax;
    private double tempMin;
    private double grandLevel;
    private double temp;
    String descryption;
    String date;


    @Override
    public String toString() {

        String styledText="متوسط دما:"+temp+'\n'+
                           "حداقل دما:"+tempMin+'\n'+
                           "حداکثر دما:"+tempMax+'\n'+
                           " رطوبت:"+humidity+'\n'+
                           " فشار:"+pressure+'\n'+
                           " سرعت باد:"+windSpeed+'\n'+
                           " درجه باد:"+windDegree+'\n'+
                           "  ارتفاع از دریا:"+seaLevel+'\n'+
                           "  ارتفاع از سطح زمین:"+grandLevel+'\n'+
                           "آخرین زمان بروزرسانی:"+date+'\n'+
                           " وضعیت هوا:"+descryption+'\n';

        return styledText;
    }

    public double getGrandLevel() {
        return grandLevel;
    }

    public void setGrandLevel(double grandLevel) {
        this.grandLevel = grandLevel;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getWindDegree() {
        return windDegree;
    }

    public void setWindDegree(double windDegree) {
        this.windDegree = windDegree;
    }

    public double getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(double seaLevel) {
        this.seaLevel = seaLevel;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public String getDescryption() {
        return descryption;
    }

    public void setDescryption(String descryption) {
        this.descryption = descryption;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
