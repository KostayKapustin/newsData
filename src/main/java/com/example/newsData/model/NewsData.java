package com.example.newsData.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Entity
@Table(name = "newData")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewsData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "newData_id")
    Long id;

    @Column(name = "source_name", nullable = false)
    String source_name;

    @Column(name = "subjectMatter", nullable = false)
    String subjectMatter;

    @Column(name = "news", nullable = false)
    String news;

}
