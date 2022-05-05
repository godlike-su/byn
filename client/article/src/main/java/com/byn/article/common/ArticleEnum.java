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

    /**
     * 通过key获取描述value
     */
    public static String getValue(String key) {
        for (ArticleEnum itemEnum : ArticleEnum.values()) {
            if (itemEnum.getKey().equals(key)) {
                return itemEnum.getValue();
            }
        }
        return "";
    }

    /**
     * 通过value获取key
     */
    public static String getKey(String value) {
        for (ArticleEnum itemEnum : ArticleEnum.values()) {
            if (itemEnum.getKey().equals(value)) {
                return itemEnum.getKey();
            }
        }
        return "";
    }

    /**
     * 根据value获取枚举
     */
    public static ArticleEnum getEnum(String value) {
        for (ArticleEnum enums : ArticleEnum.values()) {
            if (enums.value.equals(value)) {
                return enums;
            }
        }
        return null;
    }

}
