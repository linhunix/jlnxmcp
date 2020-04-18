/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinHUniX.mcp.component;

import LinHUniX.fnc.lnxmcp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author linhunix
 */
public class loadConfigComponent {

    public static String loadFile(String filename) {
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
    public static String loadCommonFile(String filename) {
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
    public static void writeFile(String Content, String filename) {
        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(Content);
            myWriter.close();
        } catch (IOException e) {
            lnxmcp.as().warning(e.getMessage());

        }
    }
}
