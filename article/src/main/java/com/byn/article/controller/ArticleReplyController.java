package com.byn.article.controller;


import com.byn.article.fo.ArticleReplyAddFO;
import com.byn.article.fo.ArticleReplyFO;
import com.byn.article.service.ArticleReplyService;
import com.byn.article.vo.ArticleReplyVO;
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
 * 文章评论回复操作
 *
 * @author
 */
@RestController
@Api(value = "文章评论回复操作", tags = "文章评论回复操作")
@RequestMapping("${byn.mapping.name}/article/articleReply")
public class ArticleReplyController {

    @Autowired
    private ArticleReplyService articleReplyService;

    @Autowired
    private SessionUser sessionUser;

    /**
     * 查询所有文章
     *
     * @return ArticleReplyFO
     */
    @PostMapping(value = "/allArticleReply")
    @ApiOperation(value = "查询文章所有评论")
    public ListResult<ArticleReplyVO> allArticleReply() {
        List<ArticleReplyVO> all = articleReplyService.all();
        return new ListResult<>(all);
    }

    /**
     * 根据查询参数查询文章
     */
    @PostMapping(value = "/queryArticleReplyList")
    @ApiOperation(value = "根据查询参数查询文章评论")
    public PageResult<ArticleReplyVO> queryArticleList(@RequestBody ArticleReplyFO articleReplyFO) {
        Page<ArticleReplyVO> result = articleReplyService.queryArticleReplyList(articleReplyFO);
        return new PageResult<>(result.getTotal(), result);
    }

    /**
     * 新增文章
     *
     */
    @PostMapping(value = "/addArticleReply")
    @ApiOperation(value = "新增文章评论")
    public MessageResult addArticleReply(@RequestBody @Validated ArticleReplyAddFO articleReplyAddFO) {
        SessionUserDetail sessionUser = this.sessionUser.getSessionUser();
        articleReplyService.addArticleReply(articleReplyAddFO, sessionUser);
        return new MessageResult("新增文章评论成功！");
    }

    /**
     * 删除文章评论
     *
     * @return 删除成功结果
     */
    @PostMapping(value = "/deleteArticleReply")
    @ApiOperation(value = "删除文章评论")
    public MessageResult deleteArticleReply(@RequestBody ArticleReplyFO articleReplyFO) {
        SessionUserDetail sessionUser = this.sessionUser.getSessionUser();
        articleReplyService.deleteArticleReply(articleReplyFO, sessionUser);
        return new MessageResult("删除文章成功!");
    }

}
