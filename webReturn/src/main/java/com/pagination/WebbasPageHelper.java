package com.pagination;

import com.github.pagehelper.PageHelper;

public class WebbasPageHelper extends PageHelper {
    public static final int DEFAULT_PAGE_NUM = 1;
    public static final int DEFAULT_PAGE_SIZE = 10;

    public WebbasPageHelper() {
    }

    public static void startPage(PageParam pageParam) {
        int pageNum = pageParam.getPage() == 0 ? 1 : pageParam.getPage();
        int pageSize = pageParam.getRows() == 0 ? 10 : pageParam.getRows();
        startPage(pageNum, pageSize);
    }

    public static void startPageUnCount(PageParam pageParam) {
        int pageNum = pageParam.getPage() == 0 ? 1 : pageParam.getPage();
        int pageSize = pageParam.getRows() == 0 ? 10 : pageParam.getRows();
        startPage(pageNum, pageSize, false);
    }
}