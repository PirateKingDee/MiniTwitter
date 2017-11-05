/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minitwitter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import minitwitter.compositepattern.Group;
import minitwitter.compositepattern.TreeComponents;
import minitwitter.observerpattern.User;

/**
 *
 * @author andyliang
 */


public class AdminFrame extends JFrame{
    
    private JButton addUserBtn;
    private JButton addGroupBtn;
    private JTextField addUserInput;
    private JTextField addGroupInput;
    private JButton openUserView;
    private JButton showUserTotal;
    private JButton showGroupTotal;
    private JButton showMessagesTotal;
    private JButton showPositivePerc;
    private JTree treeView;
    private DefaultMutableTreeNode root;
    private DefaultMutableTreeNode curSelectedNode;
    
    private final int BTN1_WIDTH = 180;
    private final int BTN1_HEIGHT = 50;
    
    private Map<String, User> users;
    private Map<String, Group> groups;
    
    
    public AdminFrame(){
        super();
        this.setTitle("Mini Twitter");
        this.setBounds(100, 100, 800, 600);
        this.getContentPane().setLayout(null);   
        users = new HashMap<String, User>();
        groups = new HashMap<String, Group>();
    }
    
    public void init(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Initialize user and group inputs
        addUserInput = new JTextField();
        addGroupInput = new JTextField();
        addUserInput.setBounds(400, 50, BTN1_WIDTH, BTN1_HEIGHT);
        addGroupInput.setBounds(400, 120, BTN1_WIDTH, BTN1_HEIGHT);
        this.getContentPane().add(addUserInput);
        this.getContentPane().add(addGroupInput); 
        
        //Initialize user and group add buttons
        addUserBtn = new JButton();
        addGroupBtn = new JButton();
        addUserBtn.setBounds(600, 50, BTN1_WIDTH, BTN1_HEIGHT);
        addGroupBtn.setBounds(600, 120, BTN1_WIDTH, BTN1_HEIGHT);
        addUserBtn.setText("Add User");
        addGroupBtn.setText("Add Group");
        this.getContentPane().add(addUserBtn);
        this.getContentPane().add(addGroupBtn); 
        
        addUserBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(addUserInput.getText().equals("")){
                    return;
                }
                User user = new User(addUserInput.getText());
                users.put(user.getId(), user);
                addUserInput.setText("");
//                System.out.println("added "+user.getId()+" to map");
//                System.out.println("CurNode class "+curSelectedNode.getClass()+" is leaf: "+curSelectedNode.isLeaf());
//                System.out.println("CurNode count"+curSelectedNode.getChildCount());
                if(curSelectedNode.isLeaf()){
                    DefaultMutableTreeNode n = (DefaultMutableTreeNode)curSelectedNode.getParent();
                    if(n.getUserObject() instanceof Group){
                        groups.get(n.getUserObject().toString()).add(user);
                    } 
                    user.updateTree((DefaultMutableTreeNode)curSelectedNode.getParent());
                }
                else{
//                    DefaultMutableTreeNode n = (DefaultMutableTreeNode)curSelectedNode.getParent();
                    if(curSelectedNode.getUserObject() instanceof Group){
                        groups.get(curSelectedNode.getUserObject().toString()).add(user);
                    }
                   user.updateTree((DefaultMutableTreeNode)curSelectedNode); 
                }
                System.out.println("added"+ user);
                DefaultTreeModel model = (DefaultTreeModel)treeView.getModel();
                model.reload();
                expandAllNodes(treeView, 0, treeView.getRowCount());
            }
        });
        
        addGroupBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Group group = new Group(addGroupInput.getText());
                groups.put(group.getId(), group);
                addGroupInput.setText("");
//                System.out.println("added "+group.getId()+" to map");
                if(curSelectedNode.isLeaf()){
                    if(curSelectedNode.getParent() == null){
                        group.updateTree((DefaultMutableTreeNode)curSelectedNode);
                    }
                    else{
                        group.updateTree((DefaultMutableTreeNode)curSelectedNode.getParent());
                    } 
                }
                else{
                   group.updateTree((DefaultMutableTreeNode)curSelectedNode); 
                }
                DefaultTreeModel model = (DefaultTreeModel)treeView.getModel();
                model.reload();
                expandAllNodes(treeView, 0, treeView.getRowCount());
            }
        });
        
        //Initialize open user view button
        openUserView = new JButton();
        openUserView.setText("Open User View");
        openUserView.setBounds(400, 170, 370, 50);
        openUserView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(curSelectedNode.getUserObject() instanceof User){
                    new UserViewFrame((User)curSelectedNode.getUserObject(), users).setVisible(true);
                }  
            }
        });
        this.getContentPane().add(openUserView);
        
        //Initialize bottom four buttons
        showUserTotal = new JButton();
        showGroupTotal = new JButton();
        showMessagesTotal = new JButton();
        showPositivePerc = new JButton();
        showUserTotal.setBounds(400, 400, BTN1_WIDTH, BTN1_HEIGHT);
        showGroupTotal.setBounds(600, 400, BTN1_WIDTH, BTN1_HEIGHT);
        showMessagesTotal.setBounds(400, 470, BTN1_WIDTH, BTN1_HEIGHT);
        showPositivePerc.setBounds(600, 470, BTN1_WIDTH, BTN1_HEIGHT);
        showUserTotal.setText("Show User Total");
        showGroupTotal.setText("Show Group Total");
        showMessagesTotal.setText("Show Messages Total");
        showPositivePerc.setText("Show Positive Percentage");
        this.getContentPane().add(showUserTotal);
        this.getContentPane().add(showGroupTotal); 
        this.getContentPane().add(showMessagesTotal); 
        this.getContentPane().add(showPositivePerc); 
        
        showUserTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Map.Entry entry : users.entrySet()){
                    System.out.println(entry.getKey() + ", " + entry.getValue());
                }
            }
        });
        
        
        
        showGroupTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Map.Entry entry : groups.entrySet()){
                    System.out.print(entry.getKey()+" : ");
                    for(TreeComponents user : groups.get(entry.getKey()).getAll()){
                        System.out.print(user+", ");
                    }
                    System.out.print("\n");
                }
            }
        });
        //Initialize Tree
        treeView = new JTree();
        treeView.setBounds(10, 10, 380, 580);
        root = new DefaultMutableTreeNode("Root");
        root.add(new DefaultMutableTreeNode());
        treeView.setModel(new DefaultTreeModel(root));
        treeView.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) treeView.getLastSelectedPathComponent();
                if(node == null){
                    return;
                }
                curSelectedNode = node;
            }
        });
        curSelectedNode = root;
        this.getContentPane().add(treeView);
//        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) treeView.getCellRenderer();
////        Icon closedIcon = new ImageIcon();
//        Icon openIcon = new ImageIcon("/src/img/folder.png");
//        Icon leafIcon = new ImageIcon("/src/img/leaf1.jpg");
////        renderer.setClosedIcon(closedIcon);
//        renderer.setOpenIcon(openIcon);
//        renderer.setLeafIcon(leafIcon);    
    }
    
    public void expandAllNodes(JTree tree, int startingIndex, int rowCount){
        for(int i=startingIndex;i<rowCount;++i){
            tree.expandRow(i);
        }

        if(tree.getRowCount()!=rowCount){
            expandAllNodes(tree, rowCount, tree.getRowCount());
        }
    }
    
    public Map<String, User> getAllUser(){
        return users;
    }
    
}
