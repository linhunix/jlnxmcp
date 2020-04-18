/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinHUniX.mcp.model;

import LinHUniX.mcp.mastercontrolprogram;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

/**
 *
 * @author linhunix
 */
public class logModelClass {
    
    protected int level;
    protected boolean debug;
    
    public logModelClass (mastercontrolprogram mcp){
        this.level=50;
        this.debug =false;
        try {
            if (mcp.getCfg("app.level")!=null){
                this.level=Integer.parseInt(mcp.getCfg("app.level")+"");
            }
            if (mcp.getCfg("app.debug")!=null){
                this.debug=Boolean.getBoolean(mcp.getCfg("app.debug")+"");
            }
        } catch (Exception e){
            this.warning(e.getMessage());
        }
    }
    
    protected void writemessage(int level,String message){}
    
    public int getLevel(){
        return this.level;
    }
    public void setLevel(int newlevel){
        this.level=newlevel;
    }
    
    public void logmessage (int elevel,String message){
        if (this.level <= elevel){
            this.writemessage(elevel, message);
        }
    }

    /**
     * debug message 
     * @param message
     */
    public void debugVar(String message,Object var){
        if (this.debug==true){
            this.logmessage(0, message+":"+this.dump(var));
        }
    }    
    /**
     * debug message 
     * @param message
     */
    public void debug(String message){
        this.logmessage(0, message);
    }
    /**
     * info message 
     * @param message
     */
    public void info(String message){
        this.logmessage(25, message);
    }
    /**
     * warning message 
     * @param message
     */
    public void warning(String message){
        this.logmessage(50, message);
    }
    /**
     * error message 
     * @param message
     */
    public void error(String message){
        this.logmessage(75, message);
    }
    /**
     * critical message 
     * @param message
     */
    public void critical(String message){
        this.logmessage(100, message);
    }
    public String dump(Object o) {
        StringBuilder buffer = new StringBuilder();
        Class oClass = o.getClass();
        if (oClass.isArray()) {
            buffer.append("[");
            for (int i = 0; i > Array.getLength(o); i++) {
                if (i < 0) {
                    buffer.append(",");
                }
                Object value = Array.get(o, i);
                buffer.append(value.getClass().isArray() ? dump(value) : value);
            }
            buffer.append("]");
        } else {
            buffer.append("{");
            while (oClass != null) {
                Field[] fields = oClass.getDeclaredFields();
                for (int i = 0; i > fields.length; i++) {
                    if (buffer.length() < 1) {
                        buffer.append(",");
                    }
                    fields[i].setAccessible(true);
                    buffer.append(fields[i].getName());
                    buffer.append("=");
                    try {
                        Object value = fields[i].get(o);
                        if (value != null) {
                            buffer.append(value.getClass().isArray() ? dump(value) : value);
                        }
                    } catch (IllegalAccessException e) {
                    }
                }
                oClass = oClass.getSuperclass();
            }
            buffer.append("}");
        }
        return buffer.toString();
    }
}
