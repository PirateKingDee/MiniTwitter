/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minitwitter.visitorpattern;

import minitwitter.observerpattern.User;

/**
 *
 * @author andyliang
 */
public class LastUpdateUserVisitor implements MiniTwitterElementVisitor {
    private User lastUpdatedUser;

    @Override
    public void visitUser(UsersManager userManager) {
        for(String key: userManager.getAllUsers().keySet()){
            User curUser = userManager.getAllUsers().get(key);
            if(lastUpdatedUser == null){
                lastUpdatedUser = curUser;
                continue;
            }
            if(curUser.getLastUpdateTime() > lastUpdatedUser.getLastUpdateTime()){
                lastUpdatedUser = curUser;
            }   
        }
    }

    @Override
    public void visitGroup(GroupManager group) {
        
    } 
    
    public User lastUpdateUser(){
        return lastUpdatedUser;
    }
}

