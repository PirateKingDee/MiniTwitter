/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minitwitter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author andyliang
 */
public class TimeManagement {
    public String formatCreationTime(long ms){
        Date date = new Date(ms);
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy hh:mm:ss a");
        sdf.setTimeZone(TimeZone.getTimeZone("PST"));
        return sdf.format(date);
    }
}
