/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinHUniX.mcp.component;

import LinHUniX.fnc.lnxmcp;
import LinHUniX.mcp.model.ConfigModelClass;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author linhunix
 */
public class loadConfigComponent {

    public static String mcpLoadFile(String filename) {
        InputStream inputStream = null;
        StringBuilder textBuilder = new StringBuilder();
        try {
            inputStream = new FileInputStream( filename);
            InputStreamReader InputStreamReader = new InputStreamReader(inputStream, Charset.forName(StandardCharsets.UTF_8.name()));
            Reader reader = new BufferedReader(InputStreamReader);
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        } catch (FileNotFoundException ex) {
            lnxmcp.as().warning(ex.getMessage());
        } catch (IOException ex) {
            lnxmcp.as().warning(ex.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    lnxmcp.as().warning(e.getMessage());
                }
            }
        }
        return textBuilder.toString();
    }
    public static String mcpLoadCommonFile(String filename) {
        InputStream inputStream = null;
        StringBuilder textBuilder = new StringBuilder();
        try {
            ClassLoader classLoader = LinHUniX.mcp.mastercontrolprogram.class.getClassLoader();
            inputStream = classLoader.getResourceAsStream(filename);
            InputStreamReader InputStreamReader = new InputStreamReader(inputStream, Charset.forName(StandardCharsets.UTF_8.name()));
            Reader reader = new BufferedReader(InputStreamReader);
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        } catch (Exception ex) {
            lnxmcp.as().warning(ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    lnxmcp.as().warning(e.getMessage());
                }
            }
        }
        return textBuilder.toString();
    }
    public static void mcpWriteFile(String Content, String filename) {
        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(Content);
            myWriter.close();
        } catch (IOException e) {
            lnxmcp.as().warning(e.getMessage());

        }
    }
    public static Map<String,Object> mcpString2Json (String content){
        Map<String, Object> obj = null;
        JSONParser parser = new JSONParser();
        try {
            obj = (Map<String, Object>) parser.parse(content);
        }catch(Exception e){
            lnxmcp.as().warning(e.getMessage());
        }
        return obj;
    }
    public static String  mcpJson2String (Map<String,Object> content){
        String out = null;
        try{
            out=JSONObject.toJSONString(content);
        }catch(Exception e){
            lnxmcp.as().warning(e.getMessage());
        }        
        return out ;
    }
    public static  Map<String,Object> mcpReadJsonFile(String filename) {
        Map<String, Object> out = null;
        String content = mcpLoadFile(filename);
        if (content !=null){
            out = mcpString2Json(content);
        }
        return out;
    }
    public static void mcpSaveJsonFile(String filename,Map<String,Object> Content){
        String out = mcpJson2String(Content);
        if (out!= null){
            mcpWriteFile(out, filename);
        }
    }
    protected static ConfigModelClass mcpLoadConfigClass(String Name) {
        Class<?> cfgCls;
        Object cfgInst=null; 
        try {
            cfgCls = Class.forName(Name);
            if (cfgCls!=null){
                cfgInst= cfgCls.newInstance();
            }
        } catch (Exception e) {
            return null;
        }
        if (cfgInst instanceof ConfigModelClass){
            return (ConfigModelClass) cfgInst;
        }
        return null;
    }
    public static  Map<String,Object> LoadConfig(Map<String, Object> In){
        String app_path =loadConfigComponent.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        Map <String,Object> res = null;
        In.put("app.def","LinHUniX");
        In.put("app.lang","en");
        In.put("app.type","lib");
        In.put("app.path",app_path);
        In.put("app.level","70");
        In.put("app.debug","false");
        In.put("app.env","dev");
        In.put("app.support.name","LinHuniX Support Team");
        In.put("app.support.mail","support@linhunix.com");
        In.put("app.support.onerrorsend","false");
        String app_cfg = app_path+"/config/";
        In.put("app.path.config",app_cfg);
        ConfigModelClass cfgcls=null;
        if (In.containsKey("app.class.config")){
            cfgcls=mcpLoadConfigClass(In.get("app.class.config")+"");
        }else{
            cfgcls=mcpLoadConfigClass("App.config");        
        }
        if (cfgcls!=null){
            res = cfgcls.GetConfig();
            res.forEach((k, v) -> {
                In.put(k, v);
            });
            res=null;
        }
        return In;
    }
    
}
