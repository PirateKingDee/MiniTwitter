/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minitwitter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;

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
    
    private final int BTN1_WIDTH = 180;
    private final int BTN1_HEIGHT = 50;
    
    
    public AdminFrame(){
        super();
        this.setTitle("Mini Twitter");
        this.setBounds(100, 100, 800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);
        init();
    }
    
    public void init(){
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
        
        //Initialize open user view button
        openUserView = new JButton();
        openUserView.setText("Open User View");
        openUserView.setBounds(400, 170, 370, 50);
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
        
        //Initialize Tree
        treeView = new JTree();
        treeView.setBounds(10, 10, 300, 500);
        this.getContentPane().add(treeView);
    }
    
    
    public static void main(String[] args){
        new AdminFrame().setVisible(true);
    }
}
