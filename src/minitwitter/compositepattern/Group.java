/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minitwitter.compositepattern;

import java.util.ArrayList;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author andyliang
 */

//Apply Composite Pattern: Group is a TreeComponents
public class Group implements TreeComponents{
    private String id;
    private List<TreeComponents> list;
    
    public Group(){
        id = this.toString();
        list = new ArrayList<TreeComponents>();
    }
    
    public Group(String id){
        this.id = id;
        list = new ArrayList<TreeComponents>();
    }
    
    public void add(TreeComponents component){
        list.add(component);
    }
    
    public List<TreeComponents> getAll(){
        return list;
    }
    
    public String getId(){
        return id;
    }
    
    public String toString(){
        return id;
    }

    @Override
    public void updateTree(DefaultMutableTreeNode node) {
        DefaultMutableTreeNode groupNode = new DefaultMutableTreeNode(this);
        groupNode.setAllowsChildren(true);
        groupNode.add(new DefaultMutableTreeNode());
        node.add(groupNode);
    }
    
    
    
}
