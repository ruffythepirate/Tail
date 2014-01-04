/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jc.se.tail.service.impl;

import jc.se.tail.model.document.Document;
import jc.se.tail.service.IViewFactory;
import jc.se.tail.view.DocumentViewPane;
import jc.se.tail.view.TailWindow;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.support.*;

/**
 *
 * @author ruffy
 */
public class ViewFactory implements IViewFactory, BeanFactoryAware {

    private BeanFactory _beanFactory;

    /**
     * Initializes the view factory by telling it where to find the spring
     * configuration file.
     *
     * @param springConfigClassPath The path to the spring config file. Should
     * be the namespace sperated by slashes and then the name of the xml-file
     */
    public ViewFactory() {
    }

    @Override
    public DocumentViewPane createDocumentViewPane(Document documentToTrack) {
        DocumentViewPane viewPane = (DocumentViewPane) _beanFactory.getBean("documentViewPane");
        viewPane.setDocumentToTrack(documentToTrack);
        return viewPane;
    }

    @Override
    public TailWindow createTailWindow() {
        return (TailWindow) _beanFactory.getBean("tailWindow");
    }

    @Override
    public void setBeanFactory(BeanFactory bf) throws BeansException {
        _beanFactory = bf;
    }

}
