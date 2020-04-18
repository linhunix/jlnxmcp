/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinHUniX.fnc;

import LinHUniX.mcp.mastercontrolprogram;

/**
 *
 * @author linhunix
 */
public class lnxmcp {
    private static mastercontrolprogram mcp=null;
    public static mastercontrolprogram as(){
        if (mcp == null){
            mcp = new mastercontrolprogram();
        }
        return mcp;
    }
}
