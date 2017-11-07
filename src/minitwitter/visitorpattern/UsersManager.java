/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minitwitter.visitorpattern;


import java.util.HashMap;
import java.util.Map;
import minitwitter.observerpattern.User;

/**
 *
 * @author andyliang
 */

//This class is to store all the users that are created.
//Applied Visitor Pattern: accept visitor and visit the users
public class UsersManager implements MiniTwitterElement{
    
    private Map<String, User> allUsers;
    private int counter;
    public UsersManager(){
        allUsers = new HashMap<String, User>();
        counter = 0;
    }
    
    public void addUser(String userId){
        if(!allUsers.containsKey(userId)){
           allUsers.put(userId, new User(userId));
           counter++;
        }
    }
    
    public void addUser(User user){
        if(!allUsers.containsKey(user.getId())){
           allUsers.put(user.getId(), user);
           counter++;
        }
    }
    
    public boolean hasUser(User user){
        return allUsers.containsKey(user.getId());
    }
    
    public boolean hasUser(String userId){
        return allUsers.containsKey(userId);
    }
    
    public User getUser(String userId){
        return allUsers.get(userId);
    }
    
    public Map<String, User> getAllUsers(){
        return allUsers;
    }

    public int getSize(){
        return counter;
    }
    @Override
    public void accept(MiniTwitterElementVisitor visitor) {
        visitor.visitUser(this);
    }
    
}
