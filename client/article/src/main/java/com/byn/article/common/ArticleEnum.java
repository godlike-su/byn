package com.byn.article.common;

/**
 * @Author: `sujinwang`
 * @Date: `2022/4/17 21:57`
 * @Version: 1.0
 * @Description:
 */
public enum ArticleEnum {

    CAT1_SQUARE("0", "动态广场"),
    CAT1_HOLE("1", "树洞");

    private String key;
    private String value;

    ArticleEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }

}
