/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minitwitter.compositepattern;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author andyliang
 */
public interface TreeComponents {
    public String getId();
    public void updateTree(DefaultMutableTreeNode node);
}
