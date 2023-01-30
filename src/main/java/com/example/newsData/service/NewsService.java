package com.example.newsData.service;

import com.example.newsData.dto.NewsDataDto;

import java.util.List;

public interface NewsService {
    NewsDataDto addNewsData(NewsDataDto newsDataDto);

    NewsDataDto addNewsDataFromFile();

    List<NewsDataDto> getListNewsDataDto(int from, int size);

    void getNewDataStatistics();

    List<String> getSource();

    List<String> getSubjectMatter();

   List<String> getSubjectMatterInSource(String source_name);

    List<String> getNewsAll(int from, int size);

    List<String> getNewsInSource(int from, int size, String source_name);

    List<String> getNewsInSubjectMatter(int from, int size, String subjectMatter);
}
