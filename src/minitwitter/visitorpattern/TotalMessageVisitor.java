/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minitwitter.visitorpattern;

import java.util.Map;
import minitwitter.observerpattern.User;

/**
 *
 * @author andyliang
 */

//Applied Visitor Pattern to visit UserManager object and findout total number of tweets
public class TotalMessageVisitor implements MiniTwitterElementVisitor{

    int totalMessage;
    
    public int getTotal(){
        return totalMessage;
    }
    @Override
    public void visitUser(UsersManager userManager) {
        for(String key: userManager.getAllUsers().keySet()){
            totalMessage += userManager.getAllUsers().get(key).getOwnTwits().size();
        }
    }

    @Override
    public void visitGroup(GroupManager group) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
