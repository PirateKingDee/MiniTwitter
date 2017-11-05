/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minitwitter.observerpattern;

import java.util.ArrayList;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import minitwitter.compositepattern.Group;
import minitwitter.compositepattern.TreeComponents;

/**
 *
 * @author andyliang
 */
public class User extends Subject implements Observer, TreeComponents{
    private String id;
    
    public User(){
        id = this.toString();
    }
    
    public User(String name){
        id = name;
    }
    
    public String getId(){
        return id;
    }
    
    public List<Observer> getFollower(){
        return followers;
    }
    
    @Override
    public String toString(){
        return id;
    }
    
    @Override
    public void updateTree(DefaultMutableTreeNode node){
        if(node.getUserObject() instanceof Group){
            DefaultMutableTreeNode child = (DefaultMutableTreeNode)node.getFirstChild();
            System.out.println(child.getUserObject() == null);
            if(child.getUserObject() == null){
                node.remove(0);
            }
        }
        
        node.insert(new DefaultMutableTreeNode(this, false), 0);
        
    }

    @Override
    public void update(Subject subject) {
        if(subject instanceof User){
            System.out.println(id+"got updates from : "+((User) subject).getId() );
        }
    }

//    @Override
//    public void update() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    
}
