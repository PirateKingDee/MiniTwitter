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

//Applied Visitor Pattern to visit GroupManager object and findout total number of groups
public class TotalGroupVisitor implements MiniTwitterElementVisitor{

    int totalGroup;
    
    public int getTotal(){
        return totalGroup;
    }
    @Override
    public void visitUser(UsersManager userManager) {
        
    }

    @Override
    public void visitGroup(GroupManager group) {
        totalGroup = group.getSize();
    }
    
}
