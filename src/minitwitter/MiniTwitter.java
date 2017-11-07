/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package minitwitter;
import javax.swing.JFrame;


/**
 *
 * @author andyliang
 */

//Apply Singleton Pattern: MiniTwitter can only have one instance
public class MiniTwitter {

   private static MiniTwitter instance = null;
   private AdminFrame adminFrame;
   private MiniTwitter(){
       adminFrame = new AdminFrame();
       adminFrame.init();
       adminFrame.setVisible(true);
   };
   public static MiniTwitter getInstance(){
       if(instance == null){
           synchronized(MiniTwitter.class){
               if(instance == null){
                   instance = new MiniTwitter();
               }
           }   
       }
       return instance;
   }
    
}
