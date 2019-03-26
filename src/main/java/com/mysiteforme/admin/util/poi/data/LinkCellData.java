package com.mysiteforme.admin.util.poi.data;

/**
 * 链接类型
 *
 * @author sunfeilong   (sunfl@cloud-young.com)
 * @version V1.0
 * @date 2018年03月19日 下午20:37
 */
public class LinkCellData extends CellData {

    private String address;

    public LinkCellData(String value, String link) {
        super(value);
        this.address = link;
    }

    public String getAddress() {
        return address;
    }
}
