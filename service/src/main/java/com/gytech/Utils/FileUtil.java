package com.gytech.Utils;

import com.alibaba.fastjson.JSON;
import com.gytech.Const;
import org.springframework.util.FileCopyUtils;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by Eric on 2017/2/9.
 */
public class FileUtil {

    /**
     * 传入文件以及字符串, 将字符串信息保存到文件中
     *
     * @param fileText
     * @param strBuffer
     */

    public static void TextToFile(File fileText, final String strBuffer)
    {
        try
        {
            // 向文件写入对象写入信息
            FileWriter fileWriter = new FileWriter(fileText);

            // 写文件
            fileWriter.write(strBuffer);
            // 关闭
            fileWriter.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 文件转换为字符串
     *
     * @param f             文件
     * @param charset 文件的字符集
     * @return 文件内容
     */
    public static String file2String(File f, String charset) {
        String result = null;
        try {
            result = stream2String(new FileInputStream(f), charset);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 文件转换为字符串
     *
     * @param in            字节流
     * @param charset 文件的字符集
     * @return 文件内容
     */
    public static String stream2String(InputStream in, String charset) {
        StringBuffer sb = new StringBuffer();
        try {
            Reader r = new InputStreamReader(in, charset);
            int length = 0;
            for (char[] c = new char[1024]; (length = r.read(c)) != -1;) {
                sb.append(c, 0, length);
            }
            r.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    //输出的list进行格式化
    public static String cellFormat(Object obj){
        return (obj == null) ? "" : obj.toString();
    }

    //response 调用浏览器下载，下载的文件名转utf-8
    public static String toUtf8String(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes("utf-8");
                } catch (Exception ex) {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

    //秒 转换成 几时几分几秒
    public static String formatSecond(Object second){
        String html="0秒";
        if(second!=null){
            Double s= Double.valueOf(String.valueOf(second));
            String format;
            Object[] array;
            Integer hours =(int) (s/(60*60));
            Integer minutes = (int) (s/60-hours*60);
            Integer seconds = (int) (s-minutes*60-hours*60*60);
            if(hours>0){
                format="%1$,d时%2$,d分%3$,d秒";
                array=new Object[]{hours,minutes,seconds};
            }else if(minutes>0){
                format="%1$,d分%2$,d秒";
                array=new Object[]{minutes,seconds};
            }else{
                format="%1$,d秒";
                array=new Object[]{seconds};
            }
            html= String.format(format, array);
        }
        return html;
    }

    /**
     * 创建ZIP文件
     * @param sourcePath 文件或文件夹路径
     * @param zipPath 生成的zip文件存在路径（包括文件名）
     */
    public static void createZip(String sourcePath, String zipPath) {
        FileOutputStream fos;
        ZipOutputStream zos = null;
        try {
            fos = new FileOutputStream(zipPath);
            zos = new ZipOutputStream(fos);
            zos.setEncoding(System.getProperty("sun.jnu.encoding"));
            writeZip(new File(sourcePath), "", zos);
        } catch (FileNotFoundException e) {
            //log.error("创建ZIP文件失败",e);
        } finally {
            try {
                if (zos != null) {
                    zos.close();
                }
            } catch (IOException e) {
                //log.error("创建ZIP文件失败",e);
            }

        }
    }

    private static void writeZip(File file, String parentPath, ZipOutputStream zos) {
        if(file.exists()){
            if(file.isDirectory()){//处理文件夹
                parentPath+=file.getName()+ File.separator;
                File[] files=file.listFiles();
                for(File f:files){
                    writeZip(f, parentPath, zos);
                }
            }else{
                FileInputStream fis=null;
                DataInputStream dis=null;
                try {
                    fis=new FileInputStream(file);
                    dis=new DataInputStream(new BufferedInputStream(fis));
                    ZipEntry ze = new ZipEntry(parentPath + file.getName());
                    zos.putNextEntry(ze);
                    byte [] content=new byte[1024];
                    int len;
                    while((len=fis.read(content))!=-1){
                        zos.write(content,0,len);
                        zos.flush();
                    }
                    zos.setEncoding(System.getProperty("sun.jnu.encoding"));

                } catch (FileNotFoundException e) {
                    //log.error("创建ZIP文件失败",e);
                } catch (IOException e) {
                    //log.error("创建ZIP文件失败",e);
                }finally{
                    try {
                        if(dis!=null){
                            dis.close();
                        }
                    }catch(IOException e){
                        //logger.error("创建ZIP文件失败",e);
                    }
                }
            }
        }
    }

    /**
     * send file
     * @param file
     * @param response
     */
    public static void sendFile(File file, HttpServletResponse response) throws IOException {
        if (file == null || response == null) return;
        if (file.exists()) {
            response.setDateHeader("Last-Modified", file.lastModified());
            response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(file.getName(), Const.DEFAULT_ENCODING));
            response.setContentLength((int) file.length());
            FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "request file not found");
        }
    }

    public static Map copeMapDeep(Map origin){
        String originstr = JSON.toJSONString(origin);
        Map map = JSON.parseObject(originstr,Map.class);
        return map;
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     *
     * @param file
     * @param path 目录路径即可
     * @return
     */
    public static boolean copyFile2Path(File file,String path){
        if (!file.exists()){
            return false;
        }
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            String oneFileName = file.getName();
            fis = new FileInputStream(file);
            fos = new FileOutputStream(path + File.separator + oneFileName);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = fis.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            close(fos, fis);
        }
        return true;
    }

    private static void close(FileOutputStream fos, FileInputStream fis) {
        if (fis != null) {
            try {
                fis.close();
                fis = null;
            } catch (IOException e) {
                System.out.println("FileInputStream关闭失败");
                e.printStackTrace();
            }
        }
        if (fos != null) {
            try {
                fos.close();
                fis = null;
            } catch (IOException e) {
                System.out.println("FileOutputStream关闭失败");
                e.printStackTrace();
            }
        }
    }

    public static boolean writeFile(FileInputStream inputStream,String outPath) throws IOException {
        //读取文件(缓存字节流)
        BufferedInputStream in = new BufferedInputStream(inputStream);
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outPath));
        //一次性取多少字节
        byte[] bytes = new byte[2048];
        int n = -1;
        while ((n = in.read(bytes,0,bytes.length)) != -1) {
            String str = new String(bytes,0,n,"UTF-8");
            out.write(bytes, 0, n);
        }
        out.flush();
        in.close();
        out.close();
        return true;
    }
}
