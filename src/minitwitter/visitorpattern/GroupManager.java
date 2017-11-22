/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minitwitter.visitorpattern;

import java.util.HashMap;
import java.util.Map;
import minitwitter.compositepattern.Group;

/**
 *
 * @author andyliang
 */

//This class is to store all the groups that are created.
//Applied Visitor Pattern: accept visitor and visit the groups
public class GroupManager implements MiniTwitterElement {
    private Map<String, Group> groups;
    private int counter;
    
    public GroupManager(){
        groups = new HashMap<String, Group>();
        counter = 0;
    }
    
    public void addGroup(Group group){
        if(!groups.containsKey(group.getId())){
            groups.put(group.getId(), group);
            counter++;
        }   
    }
    
    public Group getGroup(String groupId){
        if(groups.containsKey(groupId)){
            return groups.get(groupId);
        }
        return null;
    }
    
    public boolean hasGroup(String groupId){
        return groups.containsKey(groupId);
    }
    
    public int getSize(){
        return counter;
    }
    
    public Map<String, Group> getAllGroups(){
        return groups;
    }

    @Override
    public void accept(MiniTwitterElementVisitor visitor) {
        visitor.visitGroup(this);
    }
}
