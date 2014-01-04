/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jc.se.util.event;

/**
 *
 * @author ruffy
 */
public interface IEventManager {

    public void registerListener(String topic, IEventListener listener);

    public void removeListener(String topic, IEventListener listenerToRemove);

    public void publishEvent(String topic, IEvent event);

}
