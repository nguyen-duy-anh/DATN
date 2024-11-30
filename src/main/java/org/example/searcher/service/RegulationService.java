package org.example.searcher.service;

import org.example.searcher.entity.Category;
import org.example.searcher.entity.Keyword;
import org.example.searcher.entity.Regulation;
import org.example.searcher.exception.NotFoundException;
import org.example.searcher.model.request.UpsertRegulationRequest;
import org.example.searcher.repository.CategoryRepository;
import org.example.searcher.repository.KeywordRepository;
import org.example.searcher.repository.RegulationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegulationService {
    private final RegulationRepository regulationRepository;
    private final CategoryRepository categoryRepository;
    private final KeywordRepository keywordRepository;

    public RegulationService(RegulationRepository regulationRepository, CategoryRepository categoryRepository, KeywordRepository keywordRepository) {
        this.regulationRepository = regulationRepository;
        this.categoryRepository = categoryRepository;
        this.keywordRepository = keywordRepository;
    }

    public Page<Regulation> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("publishedAt").descending());
        return regulationRepository.findAll(pageable);
    }

    public List<Regulation> getAll() {
        return regulationRepository.findAll();
    }
    public Regulation createRegulation(UpsertRegulationRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new NotFoundException("Not found category"));
        List<Keyword> keywords = keywordRepository.findByIdIn(request.getKeywordIds());
        Regulation regulation = Regulation.builder()
                .title(request.getTitle())
                .category(category)
                .description(request.getDescription())
                .keyword(keywords)
                .build();
        return regulationRepository.save(regulation);
    }

    public Regulation updateRegulation(UpsertRegulationRequest request, Integer id) {
        Regulation regulation = regulationRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found regulation"));

        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new NotFoundException("Not found category"));
        List<Keyword> keywords = keywordRepository.findByIdIn(request.getKeywordIds());
        regulation.setTitle(request.getTitle());
        regulation.setCategory(category);
        regulation.setDescription(request.getDescription());
        regulation.setKeyword(keywords);
        return regulationRepository.save(regulation);
    }

    public void deleteRegulation(Integer id) {
        Regulation regulation = regulationRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found regulation"));
        regulationRepository.delete(regulation);
    }
}
