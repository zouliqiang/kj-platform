package com.mysiteforme.admin.util.poi;


import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

/**
 * 设置 HttpServletResponse 格式
 *
 * @author sunfeilong   (sunfl@cloud-young.com)
 * @version V1.0
 * @date 2018年03月20日 下午17:09
 */
public class ResponseUtil {


    /**
     * 设置Excel的响应头格式
     *
     * @param response HttpServletResponse
     * @param fileName 文件名
     * @throws UnsupportedEncodingException 不支持的编码格式，可以忽略
     * @version V1.0
     * @author sunfeilong (sunfl@cloud-young.com)
     * @date 2018/3/21 15:29
     */
    public static void setResponseExcelFile(HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
        response.setContentType("application/x-execl");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gbk"), "ISO-8859-1") + ".xls");
    }
}
