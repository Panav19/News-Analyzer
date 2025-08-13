package org.godigit.NewsAnalyzer.Repository;

import org.godigit.NewsAnalyzer.Models.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
}
