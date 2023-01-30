package com.example.newsData.controller;

import com.example.newsData.dto.NewsDataDto;
import com.example.newsData.service.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @PostMapping()
    public NewsDataDto addNewsData(@Valid @RequestBody NewsDataDto newsDataDto,
                                   HttpServletRequest request) {
        log.info("Получить новый запрос: {}", RequestBuilder.getStringFromRequest(request));
        return newsService.addNewsData(newsDataDto);
    }

    @PostMapping("/file")
    public NewsDataDto addNewsDataFromFile(HttpServletRequest request) {
        log.info("Получить новый запрос: {}", RequestBuilder.getStringFromRequest(request));
        return newsService.addNewsDataFromFile();
    }

    @GetMapping("/statistics")
    @Scheduled(cron = "12 12 12 * * *")
    public void getNewDataStatistics() {
        log.info("Получить новый запрос статистики");
        newsService.getNewDataStatistics();
    }

    @GetMapping()
    public List<NewsDataDto> getNewData(@PositiveOrZero @RequestParam(defaultValue = "0") int from,
                                        @Positive @RequestParam(defaultValue = "10") int size,
                                        HttpServletRequest request) {
        log.info("Получить новый запрос: {}", RequestBuilder.getStringFromRequest(request));
        return newsService.getListNewsDataDto(from, size);
    }

    @GetMapping("/source")
    public List<String> getSource(HttpServletRequest request) {
        log.info("Получить новый запрос: {}", RequestBuilder.getStringFromRequest(request));
        return newsService.getSource();
    }

    @GetMapping("/subjectMatter")
    public List<String> getSubjectMatter(HttpServletRequest request) {
        log.info("Получить новый запрос: {}", RequestBuilder.getStringFromRequest(request));
        return newsService.getSubjectMatter();
    }

    @GetMapping("/subjectMatter/{source_name}")
    public List<String> getSubjectMatterInSource(@PathVariable final String source_name, HttpServletRequest request) {
        log.info("Получить новый запрос: {} ", RequestBuilder.getStringFromRequest(request));
        return newsService.getSubjectMatterInSource(source_name);
    }

    @GetMapping("/all")
    public List<String> getNewsAll(@PositiveOrZero @RequestParam(defaultValue = "0") int from,
                                   @Positive @RequestParam(defaultValue = "10") int size,
                                   HttpServletRequest request) {
        log.info("Получить новый запрос: {} ", RequestBuilder.getStringFromRequest(request));
        return newsService.getNewsAll(from, size);
    }

    @GetMapping("/{source_name}")
    public List<String> getNewsInSource(@PositiveOrZero @RequestParam(defaultValue = "0") int from,
                                        @Positive @RequestParam(defaultValue = "10") int size,
                                        @PathVariable final String source_name, HttpServletRequest request) {
        log.info("Получить новый запрос: {} ", RequestBuilder.getStringFromRequest(request));
        return newsService.getNewsInSource(from, size, source_name);
    }

    @GetMapping("/{subjectMatter}")
    public List<String> getNewsInSubjectMatter(@PositiveOrZero @RequestParam(defaultValue = "0") int from,
                                               @Positive @RequestParam(defaultValue = "10") int size,
                                               @PathVariable final String subjectMatter, HttpServletRequest request) {
        log.info("Получить новый запрос: {} ", RequestBuilder.getStringFromRequest(request));
        return newsService.getNewsInSubjectMatter(from, size, subjectMatter);
    }
}
