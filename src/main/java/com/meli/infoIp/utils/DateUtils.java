package com.meli.infoIp.utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class DateUtils {

    public static LocalDateTime getLocalDateTimeByUTCZone(String UTC){
        LocalDateTime ldt = getLocalDateTimeUTCZero();
        return ldt.plusHours(extractTimeZoneValueFromUTC(UTC));
    }
    private static LocalDateTime getLocalDateTimeUTCZero(){
        LocalDateTime ldt = LocalDateTime.now();
        return ldt.plusHours(3L);
    }
    private static Integer extractTimeZoneValueFromUTC(String utc){
        String[] splitted = utc.split("UTC");
        if(splitted.length==0) return 0;
        return Integer.parseInt(splitted[1].split(":")[0]);
    }
    public static List<LocalDateTime> getLocalDateTimeListFromUTCArray(
        String[] utc
    ){
        List<LocalDateTime> ldt = new ArrayList<>();
        for(int i=0; i<utc.length; i++){
            ldt.add(getLocalDateTimeByUTCZone(utc[i]));
        }
        return ldt;
    }

}
