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
import javax.swing.JOptionPane;
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
import minitwitter.observerpattern.Observer;
import minitwitter.observerpattern.User;
import minitwitter.visitorpattern.GroupManager;
import minitwitter.visitorpattern.PositiveMessageVisitor;
import minitwitter.visitorpattern.TotalGroupVisitor;
import minitwitter.visitorpattern.TotalMessageVisitor;
import minitwitter.visitorpattern.TotalUserVisitor;
import minitwitter.visitorpattern.UsersManager;

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
    
    private Map<User, UserViewFrame> userViewFrameControl;
    
    private UsersManager users;
    private GroupManager groups;
    
    public AdminFrame(){
        super();
        this.setTitle("Mini Twitter");
        this.setBounds(100, 100, 800, 600);
        this.getContentPane().setLayout(null);   
        users = new UsersManager();
        groups = new GroupManager();
        userViewFrameControl = new HashMap<>();
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
                if(addUserInput.getText().equals("") || users.hasUser(addUserInput.getText())){
                    return;
                }
                User user = new User(addUserInput.getText());
                users.addUser(user);
                addUserInput.setText("");
                if(curSelectedNode.isLeaf()){
                    DefaultMutableTreeNode n = (DefaultMutableTreeNode)curSelectedNode.getParent();
                    if(n.getUserObject() instanceof Group){
                        groups.getGroup(n.getUserObject().toString()).add(user);
                    } 
                    user.updateTree((DefaultMutableTreeNode)curSelectedNode.getParent());
                }
                else{
                    if(curSelectedNode.getUserObject() instanceof Group){
                        groups.getGroup(curSelectedNode.getUserObject().toString()).add(user);
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
                if(addGroupInput.getText().equals("") || groups.hasGroup(addGroupInput.getText())){
                    return;
                }
                Group group = new Group(addGroupInput.getText());
                groups.addGroup(group);
                addGroupInput.setText("");
                if(curSelectedNode.getUserObject() instanceof Group){
                    ((Group)curSelectedNode.getUserObject()).add(group);
                }
                else{
                    ((Group)((DefaultMutableTreeNode)curSelectedNode.getParent()).getUserObject()).add(group);
                }
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
                    creatUserViewFrame((User)curSelectedNode.getUserObject());
                    
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
                TotalUserVisitor totalUserVisitor = new TotalUserVisitor();
                users.accept(totalUserVisitor);
                JOptionPane.showConfirmDialog(null, totalUserVisitor.getTotal(), "Total Users", JOptionPane.PLAIN_MESSAGE);
            }
        });
           
        showGroupTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TotalGroupVisitor totalGroupVisitor = new TotalGroupVisitor();
                groups.accept(totalGroupVisitor);
                JOptionPane.showConfirmDialog(null, totalGroupVisitor.getTotal(), "Total Groups", JOptionPane.PLAIN_MESSAGE);
            }
        });
        
        showMessagesTotal.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                TotalMessageVisitor totalMessageVisitor = new TotalMessageVisitor();
                users.accept(totalMessageVisitor);
                JOptionPane.showConfirmDialog(null, totalMessageVisitor.getTotal(), "Total Messages", JOptionPane.PLAIN_MESSAGE);
            }
        });
        
        showPositivePerc.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                PositiveMessageVisitor positiveMessageVisitor = new PositiveMessageVisitor();
                users.accept(positiveMessageVisitor);
                JOptionPane.showConfirmDialog(null, String.format("%.2f", positiveMessageVisitor.getPercent()*100)+"%", "Total Messages", JOptionPane.PLAIN_MESSAGE);
            }
        });
        //Initialize Tree
        treeView = new JTree();
        treeView.setBounds(10, 10, 380, 580);
        Group rootGroup = new Group("Root");
        root = new DefaultMutableTreeNode(rootGroup);
        groups.addGroup(rootGroup);
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
    }
    
    public void expandAllNodes(JTree tree, int startingIndex, int rowCount){
        for(int i=startingIndex;i<rowCount;++i){
            tree.expandRow(i);
        }

        if(tree.getRowCount()!=rowCount){
            expandAllNodes(tree, rowCount, tree.getRowCount());
        }
    }
    
    public UsersManager getAllUser(){
        return users;
    }
    
    public void creatUserViewFrame(User user){
        UserViewFrame userViewFrame = new UserViewFrame((User)curSelectedNode.getUserObject(), users, this);
        userViewFrame.setVisible(true);
        userViewFrameControl.put((User)curSelectedNode.getUserObject(), userViewFrame);
    }
    
    public void refreshUserFrame(User user){
        if(userViewFrameControl.containsKey(user)){
            userViewFrameControl.get(user).refreshFeeds();
        }
    }
    
    public void refreshUsersFrame(List<Observer> users){
        for(Observer user : users){
            User follower = (User)user;
            if(userViewFrameControl.containsKey(follower)){
                userViewFrameControl.get(follower).refreshFeeds();
            }
        }
    }
    
}
