/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minitwitter.observerpattern;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import minitwitter.compositepattern.Group;
import minitwitter.compositepattern.TreeComponents;

/**
 *
 * @author andyliang
 */

//Apply Observer Pattern: User is both the subject and observer in observer pattern.
//Apply Composite Pattern: User is a TreeComponent
public class User extends Subject implements Observer, TreeComponents{
    private String id;
    //holds user's own twits
    private List<String> ownTwits;
    //holds tweets include self and following users
    private List<String> allTwits;
    //holds list of following users
    private Set<String> following;
    
    private long creationTime;
    private long lastUpdateTime;
    
    public User(){
        id = this.toString();
        allTwits = new LinkedList<>();
        ownTwits = new LinkedList<>();
        following = new HashSet<>();
        creationTime = System.currentTimeMillis();
    }
    
    public User(String name){
        id = name;
        allTwits = new LinkedList<>();
        ownTwits = new LinkedList<>();
        following = new HashSet<>();
        creationTime = System.currentTimeMillis();
    }
    
    public String getId(){
        return id;
    }
    
    public void addFollowing(String userId){
        following.add(userId);
    }
    
    public Set<String> getFollowing(){
        return following;
    }
    
    public List<Observer> getFollower(){
        return followers;
    }
    
    public void addTwit(String message){
        this.allTwits.add(0, message);
    }
    
    public List<String> getAllTwit(){
        return allTwits;
    }
    
    public void addOwnTwit(String message){
        this.ownTwits.add(0, message);
    }
    
    public List<String> getOwnTwits(){
        return ownTwits;
    }
    
    public String getLatestTwit(){
        return allTwits.get(0);
    }
    
    public long getCreationTime(){
        return creationTime;
    }
    
    @Override
    public String toString(){
        return id;
    }
    
    public void updateLastUpdateTime(){
        lastUpdateTime = System.currentTimeMillis();
    }
    
    public long getLastUpdateTime(){
        return lastUpdateTime;
    }
    
    @Override
    public void updateTree(DefaultMutableTreeNode node){
        if(node.getUserObject() instanceof Group){
            DefaultMutableTreeNode child = (DefaultMutableTreeNode)node.getFirstChild();
            if(child.getUserObject() == null){
                node.remove(0);
            }
        }
        
        node.insert(new DefaultMutableTreeNode(this, false), 0);
        
    }

    @Override
    public void update(Subject subject) {
        if(subject instanceof User){
            updateLastUpdateTime();
            addTwit(((User) subject).getLatestTwit());
        }
    }
}
