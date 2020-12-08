package com.gytech.Utils;

import com.gytech.LocalEntity.Res;

import java.io.*;

/**
 * 数据库备份
 */
public class DBUtil {

    /**
     * @param home MySQL数据库bin路径
     * @param hostIP MySQL数据库所在服务器地址IP
     * @param userName 进入数据库所需要的用户名
     * @param password 进入数据库所需要的密码
     * @param savePath 数据库导出文件保存路径
     * @param fileName 数据库导出文件文件名
     * @param databaseName 要导出的数据库名
     * @return 返回true表示导出成功，否则返回false。
     */
    public static Res exportDatabaseToolMysql(String home,String hostIP, String port, String userName, String password, String savePath, String fileName, String databaseName) {
        Res res = new Res();
        File saveFile = new File(savePath);
        if (!saveFile.exists()) {// 如果目录不存在
            saveFile.mkdirs();// 创建文件夹
        }
        if(!savePath.endsWith(File.separator)){
            savePath = savePath + File.separator;
        }
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        try {
            printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(savePath + fileName), "utf8"));
            Process process = Runtime.getRuntime().exec(home + "mysqldump -h " + hostIP + " -P " + port + " -u" + userName + " -p" + password + " --default-character-set=utf8 " + databaseName);
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(), "utf8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while((line = bufferedReader.readLine())!= null){
                printWriter.println(line);
            }
            printWriter.flush();
            if(process.waitFor() == 0){//0 表示线程正常终止。
                File file = new File(savePath + fileName);
                return res.success().data(file.getAbsolutePath());
            }
        }catch (IOException e) {
            e.printStackTrace();
            return res.reason("读写失败");
        } catch (InterruptedException e) {
            e.printStackTrace();
            return res.reason("进程意外终止");
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res.reason("读写失败");
    }

    /**
     * Java代码实现Oracle数据库导出
     *
     * @author GaoHuanjie
     * @param userName 进入数据库所需要的用户名
     * @param password 进入数据库所需要的密码
     * @param host 主机ip
     * @param port oracle服务端口
     * @param instance oracle实例名
     * @param savePath 数据库导出文件保存路径
     * @param fileName 数据库导出文件文件名
     * @return 返回true表示导出成功，否则返回false。
     */
    public static Res exportDatabaseToolOracle(String userName, String password, String host, String port,String instance, String savePath, String fileName) {
        Res res = new Res();
        File saveFile = new File(savePath);
        if (!saveFile.exists()) {// 如果目录不存在
            saveFile.mkdirs();// 创建文件夹
        }
        try {
            Process process = Runtime.getRuntime().exec("exp " + userName + "/" + password + "@" + host + ":" + port + "/" + instance + " file=" + savePath + "/" + fileName + ".dmp");
            if(process.waitFor() == 0){//0 表示线程正常终止。
                File file = new File(savePath + "/" + fileName + ".dmp");
                return res.success().data(file.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return res.reason("读写错误");
        } catch (InterruptedException e) {
            e.printStackTrace();
            return res.reason("进程意外终止");
        }
        return res.reason("读写错误");
    }
}
