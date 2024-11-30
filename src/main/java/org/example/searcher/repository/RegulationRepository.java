package org.example.searcher.repository;

import org.example.searcher.entity.Regulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegulationRepository extends JpaRepository<Regulation, Integer> {
}
