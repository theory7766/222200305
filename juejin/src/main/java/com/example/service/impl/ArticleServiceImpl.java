package com.example.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.crypto.digest.DigestUtil;
import com.example.controller.result.Code;
import com.example.dao.ArticleDao;
import com.example.dao.UserDao;
import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.domain.User;
import com.example.exception.BusinessException;
import com.example.service.ArticleService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private UserDao userDao;

    // 校验token,返回user_id
    @Override
    public int checkToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        System.out.println("token:" + token);
        if (token == null) {
            throw new BusinessException(Code.ERROR, "token message is empty");
        } else {
            int user_id = articleDao.getUserIdByToken(token);
            if (user_id <= 0) {
                throw new BusinessException(Code.ERROR, "token message error");
            } else {
                return user_id;
            }
        }
    }

    @Override
    public List<Article> getArticleList(int getHottest, int count) {
        List<Article> articles = new ArrayList<Article>();
        if (count <= 0) {
            throw new BusinessException(Code.ERROR, "count must > 0");
        }
        if (getHottest == 1) {
            articles = articleDao.getArticleByVisitCount(count);
        } else {
            articles = articleDao.getArticleByCreatedAt(count);
        }
        if (articles == null) {
            throw new BusinessException(Code.ERROR, "文章表为空");
        } else {
            for (int i = 0; i < articles.size(); i++) {
                User user = userDao.getUserInformationByUserId(articles.get(i).getUser_id());
                articles.get(i).setUsername(user.getUsername());
            }
            return articles;
        }
    }

    @Override
    public Article getArticleById(int article_id) {
        Article article = articleDao.getArticleById(article_id);
        if (article == null) {
            throw new BusinessException(Code.ERROR, "article_id error");
        } else {
            User user = userDao.getUserInformationByUserId(article.getUser_id());
            article.setUsername(user.getUsername());
            return article;
        }
    }

    // 评论可能为空
    @Override
    public List<Comment> getCommentListByArticleId(int article_id) {
        List<Comment> comments = articleDao.getCommentListByArticleId(article_id);
        if (comments != null) {
            for (int i = 0; i < comments.size(); i++) {
                User user = userDao.getUserInformationByUserId(comments.get(i).getUser_id());
                comments.get(i).setUsername(user.getUsername());
                comments.get(i).setAvatar_url(user.getAvatar_url());
            }
        }
        return comments;
    }

    @Override
    public List<Article> getArticleListByUserId(int user_id) {
        List<Article> articles = articleDao.getArticleListByUserId(user_id);
        if (articles != null) {
            User user = userDao.getUserInformationByUserId(user_id);
            for (int i = 0; i < articles.size(); i++) {
                articles.get(i).setUsername(user.getUsername());
            }
        }
        return articles;
    }

    @Override
    public List<Article> getUserLikeArticleIdByUserId(int user_id) {
        List<Integer> articles_id_list = articleDao.getUserLikeArticleIdByUserId(user_id);
        if (articles_id_list == null) {
            throw new BusinessException(Code.ERROR, "该用户没点赞文章");
        } else {
            List<Article> articles = new ArrayList<Article>();
            for (int i = 0; i < articles_id_list.size(); i++) {
                Article article = articleDao.getArticleById(articles_id_list.get(i));
                if (article == null) {
                    throw new BusinessException(Code.ERROR, "用户点赞的文章在articles表中找不到");
                } else {
                    articles.add(article);
                }
            }
            return articles;
        }
    }


    @Override
    public boolean insertArticle(int user_id, Article article) {
        article.setUser_id(user_id);
        String mysqlFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(mysqlFormat);
        String formattedDateTime = sdf.format(DateTime.now());
        article.setCreated_at(formattedDateTime);
        int set = articleDao.insertArticle(article);
        if (set == 0) {
            throw new BusinessException(Code.ERROR, "insert article failed");
        } else {
            return true;
        }
    }
}
