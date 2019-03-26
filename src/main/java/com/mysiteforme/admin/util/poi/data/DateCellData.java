package com.mysiteforme.admin.util.poi.data;

/**
 * 日期类型
 *
 * @author sunfeilong   (sunfl@cloud-young.com)
 * @version V1.0
 * @date 2018年03月20日 下午15:48
 */
public class DateCellData extends CellData {

    private String dateFormat = "yyyy-mm-dd";

    public DateCellData(String value) {
        super(value);
    }

    public DateCellData(String value, String dateFormat) {
        super(value);
        this.dateFormat = dateFormat;
    }

    public String getDateFormat() {
        return this.dateFormat;
    }
}
