package com.guojin.baffle;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lubing
 * @describe 一句话描述
 * @Date 2019/9/25 16:20
 * @since
 */
public class Exe {

    public static void main(String[] args) throws IOException {
//        useProcessBuilder();
//        useAWTDesktop();
        useRuntimeExec();
    }

    /**
     * 借助java.lang.ProcessBuilder打开
     * @throws IOException
     */
    private static void useProcessBuilder() throws IOException{
        //new ProcessBuilder("notepad.exe", "C:/Users/Jadyer/Desktop/test file/readme.txt").start();
        List<String> commands = new ArrayList<String>();
        commands.add("D:/softWare/WeChat/WeChat.exe");
        //commands.add("F:/C.Project/便签.txt");
        new ProcessBuilder(commands).start();
    }

    /**
     * 借助java.awt.Desktop打开
     * @see 打开的目录或文件名中允许包含空格
     */
    private static void useAWTDesktop() throws IOException{
        Desktop.getDesktop().open(new File("F:/C.Project/便签.txt"));
    }

    /**
     * 借助java.lang.Runtime打开
     * @see WPS文字--------Runtime.getRuntime().exec("cmd /c start wps")
     * @see WPS表格--------Runtime.getRuntime().exec("cmd /c start et")
     * @see WPS演示--------Runtime.getRuntime().exec("cmd /c start wpp")
     * @see Office Word---Runtime.getRuntime().exec("cmd /c start winword")
     * @see Office Excel--Runtime.getRuntime().exec("cmd /c start excel")
     */
    private static void useRuntimeExec() throws IOException{
        /*
         * 若打开的目录或文件名中不包含空格,就用下面的方式
         */
        Runtime.getRuntime().exec("cmd /c start C:/Users/Administrator.SC-201905051325/Desktop/PO模板.pdf");

       // Runtime.getRuntime().exec("cmd /c start F:/C.Project/便签.txt");
        /*
         * (可以'运行'或'Win+R',然后输入'cmd /?'查看帮助信息)
         */
        //Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", "D:/my local/测试用例.xls"});
        /*
         * 借助本地安装程序打开
         * 若打开的目录或文件名中包含空格,它就无能为力了..不过本地程序的安装目录允许含空格
         */
//        String etCommand = "D:/Program Files/WPS/8.1.0.3526/office6/et.exe";
//        String filePath = "D:/mylocal/测试用例.xls";
//        Runtime.getRuntime().exec(etCommand + " " + filePath);
    }
}
