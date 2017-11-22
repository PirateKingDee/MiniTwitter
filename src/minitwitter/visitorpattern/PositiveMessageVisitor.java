/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minitwitter.visitorpattern;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 *
 * @author andyliang
 */

//Applied Visitor Pattern to visit UserManager object and findout percentage of good tweet
public class PositiveMessageVisitor implements MiniTwitterElementVisitor{

    int totalMessage = 0;
    int totalGoodMessage = 0;
    Set<String> goodWords = new HashSet<>(Arrays.asList("good", "great", "excellent", "perfect", "happy", "fun", "nice"));
    
    public float getPercent(){
        if(totalGoodMessage == 0){
            return 0;
        }
        return (float)totalGoodMessage / totalMessage;     
    }
    @Override
    public void visitUser(UsersManager userManager) {
        for(String key: userManager.getAllUsers().keySet()){
            LinkedList<String> messages = (LinkedList<String>)userManager.getAllUsers().get(key).getOwnTwits();
            totalMessage += messages.size();
            for(String message : messages){
                String[] words = message.split(" ");
                for(String word : words){
                    if(goodWords.contains(word.toLowerCase())){
                        totalGoodMessage++;
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void visitGroup(GroupManager group) {
       
    }
    
}
