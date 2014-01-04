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
public class EventBase implements IEvent {
 
    private Object _sender;

    public EventBase(Object sender) {
        _sender = sender;
    }

    @Override
    public Object getSender() {
        return _sender;
    }
}
