package com.example.newsData.mapper;

import com.example.newsData.dto.NewsDataDto;
import com.example.newsData.model.NewsData;

public class NewsDataMapper {

    public static NewsDataDto toNewsDataDto(NewsData newsData) {
        return new NewsDataDto(newsData.getNews(), newsData.getSubjectMatter(), newsData.getSource_name());
    }

    public static NewsData toNewsData(NewsDataDto newsDataDto) {
        NewsData newsData = new NewsData();
        newsData.setNews(newsDataDto.getNews());
        newsData.setSource_name(newsDataDto.getSource_name());
        newsData.setSubjectMatter(newsDataDto.getSubjectMatter());
        return newsData;
    }
}
