package com.byn.article.controller;

import com.byn.web.fo.LoginFo;
import com.result.SingleResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version v1.0.0.0
 * @Date `2022/4/5 16:04`
 */
@RestController
@Api(value = "文章操作", tags = "文章操作")
@RequestMapping("${byn.mapping.name}/article/article")
public class ArticleController {

    @PostMapping("/articleListPage")
    @ApiOperation(value = "获取文章列表分页")
    public SingleResult articleListPage() {
        return new SingleResult();
    }
}
