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
    
    public String loadfile(String Name){
        return loadFile(Name);
    }
    public String loadcommonfile(String Name){
        return loadCommonFile(Name);
    }
}
