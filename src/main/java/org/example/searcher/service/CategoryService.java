package org.example.searcher.service;
import org.example.searcher.entity.Category;
import org.example.searcher.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Page<Category> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return categoryRepository.findAll(pageable);
    }
}
