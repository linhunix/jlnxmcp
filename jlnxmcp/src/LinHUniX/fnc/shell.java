/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinHUniX.fnc;

import java.net.URL;

/**
 *
 * @author linhunix
 */
public class shell {

    public static void main(String args[]) {
        lnxmcp.as().setCfg("app.type", "shell");
        String headerdata = "";
        String ver = "" + lnxmcp.as().getCfg("app.version");
        if (ver.equals("null")) {
            ver = "0.0.1";
        }
        headerdata = lnxmcp.as().LoadCommonFile("resources/Shell_Header.txt");
        if (headerdata != null) {
            headerdata = headerdata.replace("{{version}}", ver);
        }
        System.out.println(headerdata);

    }
}
