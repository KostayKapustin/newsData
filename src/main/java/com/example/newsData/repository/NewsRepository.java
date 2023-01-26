package com.example.newsData.repository;

import com.example.newsData.model.NewsData;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<NewsData, Long> {

    @Query(value = "SELECT DISTINCT source_name FROM new_data", nativeQuery = true)
    List<String> findDistinctSource_name();

    @Query(value = "SELECT DISTINCT subjectMatter FROM new_data", nativeQuery = true)
    List<String> findDistinctSubjectMatter();

    @Query(value = "select  n.subject_matter from new_data n where n.source_name = :source_name",
            nativeQuery = true)
    List<String> findDistinctSubjectMatterInSource(@Param("source_name") String source_name);

    @Query(value = "SELECT news FROM new_data", nativeQuery = true)
    List<String> findNewsAll(Pageable pageable);

    @Query(value = "select n.news from new_data n where n.source_name = :source_name", nativeQuery = true)
    List<String> findNewsInSource(Pageable pageable, @Param("source_name") String source_name);

    @Query(value = "select n.news from new_data n where n.subjectMatter = :subjectMatter", nativeQuery = true)
    List<String> findNewsInSubjectMatter(Pageable pageable, @Param("subjectMatter") String subjectMatter);
}
