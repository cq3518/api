package com.winning.finance.apitool.base;

/**
 * <p>api-tool</p>
 *
 * @author cq
 * @Description
 * @date 2020/8/3 15:19
 */


import cn.hutool.core.util.StrUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.Map;


/**
 *
 * @Descript TODO (利用freemark生成word及zip)
 * @author yeting
 * @date 2019年3月19日
 *
 */
@Slf4j
public class WordUtil {

    /**
     * 生成word文件(全局可用)
     * @param dataMap word中需要展示的动态数据，用map集合来保存
     * @param templateName word模板名称，例如：test.ftl
     * @param fileFullPath 要生成的文件全路径
     */
    @SuppressWarnings("unchecked")
    public static void createWord(Map<String,Object> dataMap, String templateName, String fileFullPath) {
        log.info("【createWord】：==>方法进入");
        log.info("【fileFullPath】：==>" + fileFullPath);
        log.info("【templateName】：==>" + templateName);

        try {
            // 创建配置实例
            Configuration configuration = new Configuration();
            log.info("【创建配置实例】：==>");

            // 设置编码
            configuration.setDefaultEncoding("UTF-8");
            log.info("【设置编码】：==>");

            // 设置处理空值
            configuration.setClassicCompatible(true);

            // 设置错误控制器
//            configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

//            String pathName = Text.class.getResource("/template").getFile();
//            File templateFile = new File(pathName);
//            log.info("【pathName】：==>" + pathName);
//            log.info("【templateFile】：==>" + templateFile.getName());
//            configuration.setDirectoryForTemplateLoading(templateFile);

            // 设置ftl模板文件加载方式
            configuration.setClassForTemplateLoading(WordUtil.class,"/template");

            //创建文件
            File file = new File(fileFullPath);
            // 如果输出目标文件夹不存在，则创建
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            // 将模板和数据模型合并生成文件
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            // 获取模板
            Template template = configuration.getTemplate(templateName);
            // 生成文件
            template.process(dataMap, out);

            // 清空缓存
            out.flush();
            // 关闭流
            out.close();

        } catch (Exception e) {
            log.info("【生成word文件出错】：==>" + e.getMessage());
            e.printStackTrace();
        }
    }




    /**
     * 下载生成的文件(全局可用)
     * @param fullPath 全路径
     * @param response
     */
    public static void downLoadFile(HttpServletRequest request, String fullPath, HttpServletResponse response) {
        log.info("【downLoadFile:fullPath】：==>" + fullPath);

        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            //创建文件
            File file = new File(fullPath);
            String fileName = file.getName();

            //读文件流
            inputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);

            String userAgent = request.getHeader("User-agent");
            byte[] bytes = userAgent.contains("CHROME") ? fileName.getBytes() : fileName.getBytes("UTF-8");
            String  fileNameDisplay = new String(bytes, "ISO-8859-1");

            //清空响应
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/octet-stream; charset=utf-8");
            response.setHeader("Content-Disposition",String.format("attachment; filename=\"%s\"", fileNameDisplay));
            response.setHeader("Content-Length", "" + file.length());

            //写文件流
            outputStream = new BufferedOutputStream(response.getOutputStream());
            outputStream.write(buffer);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 下载网络文件到本地(主要用于下载简历附件)
     * @param urlAddress 网络url地址,为空时直接返回
     * @param fileFullPath 文件全路径
     */
    public static void createFromUrl(String urlAddress,String fileFullPath) {
        log.info("【service:开始下载网络文件】:==> 网上文件地址：" + urlAddress + "文件保存路径:" + fileFullPath);

        if(StrUtil.isBlank(urlAddress)) {
            return ;
        }

        DataInputStream dataInputStream = null;
        FileOutputStream fileOutputStream =null;
        try {

            URL url = new URL(urlAddress);

            dataInputStream = new DataInputStream(url.openStream());//打开网络输入流

            //创建文件
            File file = new File(fileFullPath);
            // 如果输出目标文件夹不存在，则创建
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            fileOutputStream = new FileOutputStream(file);//建立一个新的文件

            byte[] buffer = new byte[1024];
            int length;

            while((length = dataInputStream.read(buffer))>0){//开始填充数据
                fileOutputStream.write(buffer,0,length);
            }

            fileOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(dataInputStream!=null) {
                    dataInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if(fileOutputStream!=null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     *  根据路径删除指定的目录或文件，无论存在与否
     *@param sPath  要删除的目录或文件
     *@return 删除成功返回 true，否则返回 false。
     */
    public static boolean DeleteFolder(String sPath) {
       boolean flag = false;
       File file = new File(sPath);
        // 判断目录或文件是否存在
        if (!file.exists()) {
            return flag;
        } else {
            // 判断是否为文件
            if (file.isFile()) {
                return deleteFile(sPath);
            } else {  // 为目录时调用删除目录方法
                return deleteDirectory(sPath);
            }
        }
    }

    /**
     * 删除单个文件
     * @param   sPath    被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }
    /**
     * 删除目录（文件夹）以及目录下的文件
     * @param   sPath 被删除目录的文件路径
     * @return  目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String sPath) {
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
       boolean flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            } //删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }
}
