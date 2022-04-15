package com.byn.article.controller;


import com.byn.article.fo.ArticleFO;
import com.byn.article.service.ArticleService;
import com.byn.article.vo.ArticleVO;
import com.github.pagehelper.Page;
import com.result.ListResult;
import com.result.MessageResult;
import com.result.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统数据字典配置控制层代码
 * @author 
 */
@RestController
@Api(value = "文章操作", tags = "文章操作")
@RequestMapping("${byn.mapping.name}/article/article")
public class ArticleController {

  @Autowired
  private ArticleService articleService;

  /**
   * 查询所有
   * @return ArticleFO 所有数据字典项
   */
  @PostMapping(value = "/allArticle")
  public ListResult<ArticleVO> allArticle() {
    List<ArticleVO> all = articleService.all();
    return new ListResult<>(all);
  }

  /**
   * 根据查询参数参训数据字典
   * @param articleId 文章id
   * @param creator 创建者id
   * @param title 文章标题
   * @param text 文章前50个字
   * @param content 内容
   * @param cat1 板块1 0: 动态广场  1:树洞
   * @param timeCreate 创建时间
   * @param timeUpdate 修改时间
   * @param niceFlag 精华文章
   * @param topFlag 置顶文章
   * @param delFlag 删除标识
   * @param type 文章类型 0:原创 1:转载 2:官方推文
   * @param address 转载地址
   * @param form 发布形式 暂时没想到
   * @param audit 需要先通过审核 0:待审核  1:审核通过 2:审核不通过
   * @param draft 是否为草稿 0: 否  1：是
   * @param numReply 回复数
   * @param numStart 收藏数
   * @param numLike 点赞数
   * @param attachGroupId 图片id，使用逗号分隔
   * @return 数据字典项列表
   */
  @PostMapping(value = "/queryArticleList")
  @ApiOperation(value = "根据查询参数参训数据字典")
  public PageResult<ArticleVO> queryArticleList(@RequestBody ArticleFO articleFO) {
    Page<ArticleVO> result = articleService.queryArticleList(articleFO);
	return new PageResult<>(result.getTotal(), result);
  }

  /**
   * 根据id更新数据数据字典项目
   * @param article 更新实体（必须包含id）
   * @return 更新成功结果
   */
  @PostMapping(value = "/updateArticle")
  @ApiOperation(value = "更新数据字典项成功")
  public MessageResult updateArticle(@RequestBody ArticleFO articleFO) {
    articleService.updateArticle(articleFO);
    return new MessageResult("更新数据字典项成功！");
  }

  /**
   * 新增数据字典项
   * @param article 新增实体
   * @return 更新成功结果
   */
  @PostMapping(value = "/addArticle")
  @ApiOperation(value = "新增数据字典项成功")
  public MessageResult addArticle(@RequestBody ArticleFO articleFO) {
    articleService.addArticle(articleFO);
    return new MessageResult("新增数据字典项成功！");
  }

  /**
   * 根据id删除数据字典项
   * @param id 删除项id
   * @return 删除成功结果
   */
  @PostMapping(value = "/deleteArticle")
  @ApiOperation(value = "删除数据字典项成功")
  public MessageResult deleteArticle(@RequestBody ArticleFO articleFO) {
    articleService.deleteArticle(articleFO);
    return new MessageResult("删除数据字典项成功!");
  }

}
