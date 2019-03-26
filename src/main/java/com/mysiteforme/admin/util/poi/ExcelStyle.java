package com.mysiteforme.admin.util.poi;


import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

/**
 * excel单元格样式定义
 *
 * @author sunfeilong
 * @version V1.0
 * @date 2018年03月19日 下午20:16
 */

public interface ExcelStyle {

    /**
     * 单元格标题样式
     *
     * @param workbook 单元格对象
     * @return
     * @version V1.0
     * @author sunfeilong (sunfl@cloud-young.com)
     * @date 2018/3/20 16:24
     */
    static HSSFCellStyle titleStyle(HSSFWorkbook workbook) {
        HSSFFont titleFont = workbook.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short) 13);
        titleFont.setFontName("宋体");
        HSSFCellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        titleStyle.setFont(titleFont);
        return titleStyle;
    }

    /**
     * 单元格表头样式
     *
     * @param workbook 单元格对象
     * @return
     * @version V1.0
     * @author sunfeilong (sunfl@cloud-young.com)
     * @date 2018/3/20 16:24
     */
    static HSSFCellStyle linkStyle(HSSFWorkbook workbook) {
        HSSFCellStyle linkStyle = workbook.createCellStyle();
        HSSFFont linkFont = workbook.createFont();
        linkFont.setUnderline(HSSFFont.U_SINGLE);
        linkFont.setColor(HSSFColor.BLUE.index);
        linkStyle.setFont(linkFont);
        return linkStyle;
    }

    /**
     * 单元格默认样式
     *
     * @param workbook 单元格对象
     * @return
     * @version V1.0
     * @author sunfeilong (sunfl@cloud-young.com)
     * @date 2018/3/20 16:24
     */
    static HSSFCellStyle defaultStyle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 10);

        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(true);
        style.setFont(font);
        return style;
    }
}
