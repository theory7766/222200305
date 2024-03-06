package com.example.controller;

import com.example.controller.result.Base;
import com.example.controller.result.Code;
import com.example.controller.result.QueryRequest;
import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.exception.BusinessException;
import com.example.service.ArticleService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/juejin")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/api")
    public String detail() {
        return "部署成功";
    }

    // 首页
    @GetMapping("/homepage")
    public QueryRequest<Article> getArticleList(HttpServletRequest request,
                                                @RequestParam Integer getHottest, @RequestParam Integer count) {
        int user_id = articleService.checkToken(request);
        List<Article> articles = articleService.getArticleList(getHottest, count);
        return new QueryRequest<Article>(Base.success(), articles, articles.size());
    }

    // 文章页
    @GetMapping("/article")
    public QueryRequest<Comment> getArticleCommentList(HttpServletRequest request, @RequestParam Integer article_id) {
        articleService.checkToken(request);
        Article article = articleService.getArticleById(article_id);
        List<Comment> comments = articleService.getCommentListByArticleId(article_id);
        return new QueryRequest<Comment>(Base.success(), comments, comments.size(), article);
    }

    // 我的主页
    @GetMapping("/myhomepage")
    public QueryRequest<Article> getMyArticleList(HttpServletRequest request, @RequestParam int getWrittenArticle) {
        int user_id = articleService.checkToken(request);
        if (getWrittenArticle != 0 && getWrittenArticle != 1) {
            throw new BusinessException(Code.ERROR, "getWrittenArticle must be 1 or 0");
        }
        List<Article> articles = new ArrayList<Article>();
        if (getWrittenArticle == 1) {
            articles = articleService.getArticleListByUserId(user_id);
        } else if (getWrittenArticle == 0) {
            articles = articleService.getUserLikeArticleIdByUserId(user_id);
        }
        return new QueryRequest<>(Base.success(), articles, articles.size());
    }

    // 写文章
    @PostMapping("/write")
    public QueryRequest writeArticle(HttpServletRequest request, @RequestBody Article article) {
        int user_id = articleService.checkToken(request);
        articleService.insertArticle(user_id, article);
        return new QueryRequest(Base.success());
    }
}
