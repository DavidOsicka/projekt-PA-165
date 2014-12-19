package com.pa165.ddtroops.web;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import org.apache.taglibs.standard.functions.Functions;

/**
 * Parent class for action beans of entities
 * 
 * @version 1.0,10/12/2014
 * @author Martin Jelínek
 */
public class BaseActionBean implements ActionBean {

    private ActionBeanContext context;
    
    /**
     * Method setContext
     * 
     * @param context 
     * 
     * @see net.sourceforge.stripes.action.ActionBean
     */
    @Override
    public void setContext(ActionBeanContext context) {
        this.context = context;
    }
    
    /**
     * Method getContext
     * 
     * @return context
     * 
     * @see net.sourceforge.stripes.action.ActionBean
     */
    @Override
    public ActionBeanContext getContext() {
        return context;
    }
    
    /**
     * Method escapeHTML
     * 
     * @param s string
     * 
     * @return string
     */
    public static String escapeHTML(String s) {
        return Functions.escapeXml(s);
    }
   
}
