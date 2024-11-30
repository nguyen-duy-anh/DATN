package org.example.searcher.repository;

import org.example.searcher.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Integer> {
    List<Keyword> findByIdIn(List<Integer> ids);
}

