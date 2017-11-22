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
public class VerifyGroupIdVisitor implements MiniTwitterElementVisitor {
    boolean unique = true;

    @Override
    public void visitUser(UsersManager userManager) {
        
    }

    @Override
    public void visitGroup(GroupManager groupManager) {
        Set<String> idSet = new HashSet<>();
        for(String key: groupManager.getAllGroups().keySet()){
            String groupId = groupManager.getAllGroups().get(key).getId();
            if(idSet.contains(groupId) || hasSpace(groupId)){
                unique = false;
                break;
            }
            idSet.add(groupId);
        }
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
