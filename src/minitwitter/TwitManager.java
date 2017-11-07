/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minitwitter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import minitwitter.observerpattern.User;

/**
 *
 * @author andyliang
 */
public class TwitManager {
    private Map<User, List<String>> allTwits;
    
    public TwitManager(){
        allTwits = new HashMap<>();
    }
    
    public void postTwit(User user, String message){
        if(allTwits.containsKey(user)){
            allTwits.get(user).add(message);
        }
        else{
            List<String> messageList = new LinkedList<>();
            messageList.add(message);
            allTwits.put(user, messageList);
        }
    }
    
    public List<String> getUserMessages(User user){
        if(allTwits.containsKey(user)){
            return allTwits.get(user);
        }
        else{
            return null;
        }
    }
}
