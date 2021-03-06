package com.byn.article.controller;


import com.byn.article.fo.ArticleAddFO;
import com.byn.article.fo.ArticleFO;
import com.byn.article.fo.ArticleSaveFO;
import com.byn.article.service.ArticleService;
import com.byn.article.vo.ArticleVO;
import com.byn.common.session.entity.SessionUserDetail;
import com.byn.common.session.service.SessionUser;
import com.github.pagehelper.Page;
import com.result.ListResult;
import com.result.MessageResult;
import com.result.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统数据字典配置控制层代码
 *
 * @author
 */
@RestController
@Api(value = "文章操作", tags = "文章操作")
@RequestMapping("/${byn.mapping.name}/${byn.mapping.prefix}/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private SessionUser sessionUser;

    /**
     * 查询所有文章
     *
     * @return ArticleFO
     */
    @PostMapping(value = "/allArticle")
    @ApiOperation(value = "查询所有文章")
    public ListResult<ArticleVO> allArticle() {
        List<ArticleVO> all = articleService.all();
        return new ListResult<>(all);
    }

    /**
     * 根据查询参数查询文章
     */
    @PostMapping(value = "/queryArticleList")
    @ApiOperation(value = "根据查询参数查询文章")
    public PageResult<ArticleVO> queryArticleList(@RequestBody ArticleFO articleFO) {
        Page<ArticleVO> result = articleService.queryArticleList(articleFO);
        return new PageResult<>(result.getTotal(), result);
    }

    /**
     * 修改文章
     *
     * @return 更新成功结果
     */
    @PostMapping(value = "/updateArticle")
    @ApiOperation(value = "修改文章")
    public MessageResult updateArticle(@RequestBody @Validated ArticleSaveFO articleSaveFO) {
        SessionUserDetail sessionUser = this.sessionUser.getSessionUser();
        articleService.updateArticle(articleSaveFO, sessionUser);
        return new MessageResult("修改文章成功！");
    }

    /**
     * 新增文章
     *
     */
    @PostMapping(value = "/addArticleByCat0")
    @ApiOperation(value = "新增文章,动态广场")
    public MessageResult addArticleByCat0(@RequestBody ArticleAddFO articleAddFO) {
        SessionUserDetail sessionUser = this.sessionUser.getSessionUser();
        articleService.addArticleByCat0(articleAddFO, sessionUser);
        return new MessageResult("新增文章成功！");
    }


    /**
     * 新增文章
     *
     */
    @PostMapping(value = "/addArticleByCat1")
    @ApiOperation(value = "新增文章,树洞")
    public MessageResult addArticleByCat1(@RequestBody ArticleAddFO articleAddFO) {
        SessionUserDetail sessionUser = this.sessionUser.getSessionUser();
        articleService.addArticleByCat1(articleAddFO, sessionUser);
        return new MessageResult("新增文章成功！");
    }

    /**
     * 删除文章
     *
     * @return 删除成功结果
     * @param articleSaveFO
     */
    @PostMapping(value = "/deleteArticle")
    @ApiOperation(value = "删除文章")
    public MessageResult deleteArticle(@RequestBody @Validated ArticleSaveFO articleSaveFO) {
        SessionUserDetail sessionUser = this.sessionUser.getSessionUser();
        articleService.deleteArticle(articleSaveFO, sessionUser);
        return new MessageResult("删除文章成功!");
    }

}
