package com.example.newsData.service;

import com.example.newsData.dto.NewsDataDto;
import com.example.newsData.mapper.NewsDataMapper;
import com.example.newsData.model.NewsData;
import com.example.newsData.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;

    @Override
    public NewsDataDto addNewsData(NewsDataDto newsDataDto) {
        NewsData newsData = NewsDataMapper.toNewsData(newsDataDto);
        return NewsDataMapper.toNewsDataDto(newsRepository.save(newsData));
    }

    @Override
    public List<NewsDataDto> getListNewsDataDto(int from, int size) {
        int page = from / size;
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<NewsData> pageNewsDataDto = newsRepository.findAll(pageable);
        List<NewsDataDto> listNewsDataDto =
                pageNewsDataDto.stream().map(NewsDataMapper::toNewsDataDto).toList();
        if (listNewsDataDto.isEmpty()) throw new ValidationException("Список новостных данных пуст!");
        return listNewsDataDto;
    }

    @Override
    public List<String> getSource() {
        List<String> setSource = newsRepository.findDistinctSource_name();
        if (setSource.isEmpty()) throw new ValidationException("Список источников пуст!");
        return setSource;
    }

    @Override
    public List<String> getSubjectMatter() {
        List<String> setSubjectMatter = newsRepository.findDistinctSubjectMatter();
        if (setSubjectMatter.isEmpty()) throw new ValidationException("Список тематики пуст!");
        return setSubjectMatter;
    }

    @Override
    public List<String> getSubjectMatterInSource(String source_name) {
        if (!getSource().contains(source_name)) {
            throw new ValidationException("Такого источника не существует!");
        }
        return newsRepository.findDistinctSubjectMatterInSource(source_name);
    }

    @Override
    public List<String> getNewsAll(int from, int size) {
        int page = from / size;
        Pageable pageable = PageRequest.of(page, size, Sort.by("news").descending());
        List<String> listNewsAll = newsRepository.findNewsAll(pageable).stream().toList();
        return listNewsAll;
    }

    @Override
    public List<String> getNewsInSource(int from, int size, String source_name) {
        if (!getSource().contains(source_name)) {
            throw new ValidationException("Такого источника не существует!");
        }
        int page = from / size;
        Pageable pageable = PageRequest.of(page, size, Sort.by("news").descending());
        return newsRepository.findNewsInSource(pageable, source_name);
    }

    @Override
    public List<String> getNewsInSubjectMatter(int from, int size, String subjectMatter) {
        if (!getSubjectMatter().contains(subjectMatter)) {
            throw new ValidationException("Такой тематики не существует!");
        }
        int page = from / size;
        Pageable pageable = PageRequest.of(page, size, Sort.by("news").descending());
        return newsRepository.findNewsInSubjectMatter(pageable, subjectMatter);
    }
}
