/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jc.se.util.event;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ruffy
 */
public class EventManager implements IEventManager{

    Map<String, List<IEventListener>> _eventListeners;

    
    public EventManager(){
        _eventListeners = new HashMap<>();
    }
    
    
    public void registerListener(String topic, IEventListener listener) {
        
        List<IEventListener> observerList;
        if(!_eventListeners.containsKey(topic)) {
            observerList = new LinkedList<>();
            _eventListeners.put(topic, observerList);
        } else {
            observerList = (List<IEventListener>) _eventListeners.get(topic);
        }
        
        observerList.add(listener);
    }
    
    public void removeListener(String topic, IEventListener listenerToRemove) {
        List<IEventListener> observerList;
        if(_eventListeners.containsKey(topic)) {
            observerList = (List<IEventListener>) _eventListeners.get(topic);
            observerList.remove(listenerToRemove);
        }
        
        
    }
    
    public void publishEvent(String topic, IEvent event){
        
        if(_eventListeners.containsKey(topic)) {
            List<IEventListener> allListeners = _eventListeners.get(topic);
            for(IEventListener listener : allListeners) {
                listener.NotifyEvent(event);
            }
        }
    }
}
