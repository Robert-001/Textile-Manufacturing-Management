package com.anjirwala.project.anjirwalafabrics;

/**
 * Created by Anuj Anjirwala on 05-Aug-17.
 */

public class Stock {
    private String Date_of_TakaEntry;
    private String Meter_of_Taka;
    private String Taka_Number;
    //private String Quality_Name;
    private String Machine_Number;

    public Stock()  {

    }

    public Stock(String date_of_TakaEntry, String meter_of_Taka, String taka_Number, String machine_Number) {
        Date_of_TakaEntry = date_of_TakaEntry;
        Meter_of_Taka = meter_of_Taka;
        Taka_Number = taka_Number;
        //Quality_Name = quality_name;
        Machine_Number = machine_Number;
    }

    public String getDate_of_TakaEntry() {
        return Date_of_TakaEntry;
    }
    public void setDate_of_TakaEntry(String date_of_TakaEntry) { Date_of_TakaEntry = date_of_TakaEntry; }


    //public String getQuality_Name() { return Quality_Name; }
    //public void setQuality_Name(String quality_Name) { Quality_Name = quality_Name; }


    public String getMachine_Number() { return Machine_Number; }
    public void setMachine_Number(String machine_Number) { Machine_Number = machine_Number; }


    public String getMeter_of_Taka() {
        return Meter_of_Taka;
    }
    public void setMeter_of_Taka(String meter_of_Taka) {
        Meter_of_Taka = meter_of_Taka;
    }



    public String getTaka_Number() {
        return Taka_Number;
    }
    public void setTaka_Number(String taka_Number) { Taka_Number = taka_Number; }
}
