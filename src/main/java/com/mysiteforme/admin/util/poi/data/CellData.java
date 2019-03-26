package com.mysiteforme.admin.util.poi.data;

/**
 * Excel表格数据类型抽象类
 *
 * @author sunfeilong   (sunfl@cloud-young.com)
 * @version V1.0
 * @date 2018年03月19日 下午20:32
 */
public abstract class CellData {

    private String value;

    public CellData(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
