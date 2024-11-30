package org.example.searcher.rest;

import org.example.searcher.model.request.UpsertRegulationRequest;
import org.example.searcher.service.RegulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/regulation")
public class RegulationResource {
    @Autowired
    private RegulationService regulationService;
    @GetMapping
    public ResponseEntity<?> getAllRegulations( @RequestParam(required = false, defaultValue = "1") Integer page,
                                                @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return ResponseEntity.ok(regulationService.findAll(page, pageSize));
    }

    @PostMapping("create")
    public ResponseEntity<?> createRegulation(@RequestBody UpsertRegulationRequest request){
        return new ResponseEntity<>(regulationService.createRegulation(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRegulation(@RequestBody UpsertRegulationRequest request, @PathVariable Integer id){
        return ResponseEntity.ok(regulationService.updateRegulation(request, id));
    }
}
