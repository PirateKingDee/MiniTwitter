/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minitwitter.observerpattern;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andyliang
 */

public abstract class Subject {
    List<Observer> followers = new ArrayList<Observer>();

    
    public void attach(Observer observer) {
        followers.add(observer);
    }

    public void detach(Observer observer) {
        followers.remove(observer);
    }
    
    public void notifyObserver() {
        for(Observer follower : followers){
            follower.update(this);
        }
    }
}
