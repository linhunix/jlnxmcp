/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinHUniX.mcp;

import static LinHUniX.mcp.component.loadConfigComponent.*;
import LinHUniX.mcp.model.logModelClass;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author linhunix
 */
public class mastercontrolprogram {

    protected Map<String, Object> config;
    protected logModelClass mcpLogging;

    public mastercontrolprogram() {
        this.config=new HashMap<String, Object>();
        this.config=LoadConfig(this.config);
    }

    /**
     * Get Conf return the config info
     *
     * @param name
     * @return
     */
    public Object getCfg(String name) {
        if (this.config.containsKey(name)) {
            return this.config.get(name);
        }
        return null;
    }

    public void setCfg(String name, Object value) {
        if (value.equals('.')) {
            if (this.config.containsKey(name)) {
                this.config.remove(name);
            }
        } else {
            this.config.put(name, value);
        }
       this.info("setCfg:"+name+"="+value);
    }

    public void debug(String message) {
        if (mcpLogging != null) {
            mcpLogging.debug(message);
        }
    }

    public void debugVar(String message,Object var) {
        if (mcpLogging != null) {
            mcpLogging.debugVar(message,var);
        }
    }

    public void info(String message) {
        if (mcpLogging != null) {
            mcpLogging.info(message);
        }
    }

    public void warning(String message) {
        if (mcpLogging != null) {
            mcpLogging.warning(message);
        }
    }

    public void error(String message) {
        if (mcpLogging != null) {
            mcpLogging.error(message);
        }
    }

    public void critical(String message) {
        if (mcpLogging != null) {
            mcpLogging.critical(message);
        }
    }
    ////////////////////////////////////////////////////////////////////////////
    ///loadConfigComponent 
    ////////////////////////////////////////////////////////////////////////////
    public String LoadFile(String Name){
        return mcpLoadFile(Name);
    }
    public String LoadCommonFile(String Name){
        return mcpLoadCommonFile(Name);
    }
    public void WriteFile(String Name, String Content){
        mcpWriteFile(Content, Name);
    }
    public String Json2String(Map<String,Object>In){
        return mcpJson2String(In);
    }
    public Map<String,Object> String2Json (String In){
        return mcpString2Json(In);
    }
    public Map<String,Object> GetJsonFile(String filename){
        return mcpReadJsonFile(filename);
    }
   public void PutJsonFile(String filename, Map<String,Object> content){
       mcpSaveJsonFile(filename, content);
   }
}
