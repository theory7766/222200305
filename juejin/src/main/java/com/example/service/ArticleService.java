package com.example.service;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticleService {

    /**
     * 校验token的正确性
     * @parameter token
     * @return
     */
    public int checkToken(HttpServletRequest request);

    /**
     * 首页：根据getHottest,count参数返回最热或最新文章列表
     * @param getHottest
     * @param count
     * @return
     */
    public List<Article> getArticleList(int getHottest,int count);

    /**
     * 根据article_id获取文章信息
     * @param article_id
     * @return
     */
    public Article getArticleById(int article_id);

    /**
     * 根据文章id获取评论列表
     * @param article_id
     * @return
     */
    public List<Comment> getCommentListByArticleId(int article_id);

    /**
     * 按user_id获取相应文章列表
     * @param user_id
     * @return
     */
    public List<Article> getArticleListByUserId(int user_id);

    /**
     * 按user_id查询赞过的文章id列表
     * @param user_id
     * @return
     */
    public List<Article> getUserLikeArticleIdByUserId(int user_id);


    /**
     * 写文章
     * @param user_id
     * @param article
     * @return
     */
    public boolean insertArticle(int user_id,Article article);

}
