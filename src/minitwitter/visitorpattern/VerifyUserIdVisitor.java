/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minitwitter.visitorpattern;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author andyliang
 */
public class VerifyUserIdVisitor implements MiniTwitterElementVisitor {
    boolean unique = true;

    @Override
    public void visitUser(UsersManager userManager) {
        Set<String> idSet = new HashSet<>();
        for(String key: userManager.getAllUsers().keySet()){
            String userId = userManager.getAllUsers().get(key).getId();
            if(idSet.contains(userId) || hasSpace(userId)){
                unique = false;
                break;
            }
            idSet.add(userId);
        }
    }

    @Override
    public void visitGroup(GroupManager group) {
        
    }
    
    private boolean hasSpace(String id){
        for(int i = 0; i < id.length(); i++){
            if(id.charAt(i) == ' '){
                return true;
            }
        }
        return false;
    }
    
    public boolean isUnique(){
        return unique;
    }
    
}
