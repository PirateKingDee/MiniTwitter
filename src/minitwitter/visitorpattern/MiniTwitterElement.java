/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minitwitter.visitorpattern;

/**
 *
 * @author andyliang
 */
public interface MiniTwitterElement {
    public void accept(MiniTwitterElementVisitor visitor);
}
