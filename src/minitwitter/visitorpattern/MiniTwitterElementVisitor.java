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
public interface MiniTwitterElementVisitor {
    public void visitUser(UsersManager userManager);
    public void visitGroup(GroupManager group);
}
