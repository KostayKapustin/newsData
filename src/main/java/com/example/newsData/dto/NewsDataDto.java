package com.example.newsData.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Validated
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewsDataDto {

    @NotNull(message = "source_name не может быть Null!")
    String source_name;

    @NotNull(message = "subjectMatter не может быть Null!")
    String subjectMatter;

    @NotNull(message = "news не может быть Null!")
    String news;
}
