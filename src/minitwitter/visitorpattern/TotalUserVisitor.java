/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minitwitter.visitorpattern;

/**
 *
 * @author andyliang
 */

//Applied Visitor Pattern to visit UserManager object and findout total number of users
public class TotalUserVisitor implements MiniTwitterElementVisitor{
    
    int totalUser;
    
    public int getTotal(){
        return totalUser;
    }
    @Override
    public void visitUser(UsersManager userManager) {
        totalUser = userManager.getSize();
    }

    @Override
    public void visitGroup(GroupManager group) {
        
    }
    
}
