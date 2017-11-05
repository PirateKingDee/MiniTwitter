/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minitwitter.observerpattern;

import minitwitter.compositepattern.Group;
import minitwitter.compositepattern.TreeComponents;

/**
 *
 * @author andyliang
 */
public class Driver {
    public static void main(String[] args){
        User user1 = new User("user1");
        User user2 = new User("user2");
        User user3 = new User("user3");
        User user4 = new User("user4");
        User user5 = new User("user5");
        
        
        System.out.println(user1.getId());
        System.out.println(user2.getId());
        System.out.println(user3.getId());
        System.out.println(user4.getId());
        System.out.println(user5.getId());
        
        user1.attach(user2);
        user1.attach(user3);
        user1.notifyObserver();
        
        user2.attach(user1);
        user2.attach(user3);
        user2.notifyObserver();
        
        Group group1 = new Group();
        group1.add((TreeComponents) user1);
        
        
        
    }
}
