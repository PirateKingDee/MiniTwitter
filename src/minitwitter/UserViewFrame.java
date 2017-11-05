/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minitwitter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;
import minitwitter.observerpattern.Observer;
import minitwitter.observerpattern.User;

/**
 *
 * @author andyliang
 */
public class UserViewFrame extends JFrame{
    private JTextField userIdInput;
    private JButton followBtn;
    private JList<String> followingLv;
    private JTextField tweetInput;
    private JButton postBtn;
    private JList<String> feedsLv;
   
    private User user;
    private Map<String, User> allUser;
    private DefaultListModel followListModel;
    private DefaultListModel tweetListModel;
    
    
    private final int BTN1_WIDTH = 180;
    private final int BTN1_HEIGHT = 50;
    
            
    public UserViewFrame(User user, Map users){
        super();
        this.user = user;
        allUser = users;
        this.setTitle(user.toString()+"'s view");
        this.setBounds(200, 200, 400, 600);
        this.getContentPane().setLayout(null);      
        
        initUserIdInput(); 
        initFollowBtn();
        initFollowingLv();
        initTweetInput();
        initPostBtn();
        initFeedsLv();
    }
    
    public void initUserIdInput(){
        userIdInput = new JTextField();
        userIdInput.setBounds(10, 10, BTN1_WIDTH, BTN1_HEIGHT);
        this.getContentPane().add(userIdInput);
    }
    
    public void initFollowBtn(){
        followBtn = new JButton();
        followBtn.setBounds(200, 10, BTN1_WIDTH, BTN1_HEIGHT);
        followBtn.setText("Follow User");
        this.getContentPane().add(followBtn);
        
        followBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(userIdInput.getText().equals("")){
                    return;
                }
                System.out.println(allUser.containsKey(userIdInput.getText()));
                if(allUser.containsKey(userIdInput.getText())){
                    user.attach(allUser.get(userIdInput.getText()));
                    addToFollowListView(allUser.get(userIdInput.getText()));
                }
                userIdInput.setText("");
            }
        });
    }
    
    public void initFollowingLv(){
        followListModel = new DefaultListModel();
        followingLv = new JList<String>();
        followingLv.setBounds(10, 70, 370, 240);        
        followingLv.setModel(followListModel);
        updateFollowListView();
        this.getContentPane().add(followingLv);
    }
    
    public void initTweetInput(){
        tweetInput = new JTextField();
        tweetInput.setBounds(10, 320, BTN1_WIDTH, BTN1_HEIGHT);
        this.getContentPane().add(tweetInput);
    }
    
    public void initPostBtn(){
        postBtn = new JButton();
        postBtn.setBounds(200, 320, BTN1_WIDTH, BTN1_HEIGHT);
        postBtn.setText("Post Tweet");
        this.getContentPane().add(postBtn);
    }
    
    public void initFeedsLv(){
        tweetListModel = new DefaultListModel();
        feedsLv = new JList<String>();
        feedsLv.setBounds(10, 380, 370, 180);
        feedsLv.setModel(tweetListModel);
        this.getContentPane().add(feedsLv);
    }
    
    public void updateFollowListView(){
        for(Observer u : user.getFollower()){
            User toUser = (User)u;
            followListModel.addElement(toUser);
        }
    }
    
    public void addToFollowListView(User user){
        followListModel.addElement(user);
    }
        
}
