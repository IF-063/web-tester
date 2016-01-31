package com.softserve.webtester.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.webtester.mapper.ArticleMapper;
import com.softserve.webtester.model.Article;

@Service
@Transactional
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    public Article load(int id) {
	return articleMapper.load(id);
    }

}