package com.example.ecommerceapi.api;

import com.example.ecommerceapi.dto.CategoryDTO;
import com.example.ecommerceapi.service.CategoryService;
import com.example.ecommerceapi.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Thrimal Avishka <thrimalavishka99@gmail.com>
 * @since 23-Jul-23
 */
@RestController
@RequestMapping("/api/v1/category")
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService service;

    @PostMapping
    public ResponseEntity<StandardResponse> addCategory(@RequestBody CategoryDTO categoryDTO) {
        boolean b = service.addCategory(categoryDTO);
        return new ResponseEntity<>(new StandardResponse(201, "Success", b), HttpStatus.CREATED);
    }

    @PutMapping(params = {"description"})
    public ResponseEntity<StandardResponse> updateCategory(@RequestBody CategoryDTO categoryDTO, @RequestParam String description) {
        boolean b = service.updateCategory(categoryDTO, description);
        return new ResponseEntity<>(new StandardResponse(204, "Success", b), HttpStatus.OK);
    }

    @DeleteMapping(params = {"description"})
    public ResponseEntity<StandardResponse> deleteCategory(@RequestParam String description) {
        String s = service.deleteCategory(description);
        return new ResponseEntity<>(new StandardResponse(203, "Success", s), HttpStatus.OK);
    }

    @GetMapping(value = "/find", params = {"description"})
    public ResponseEntity<StandardResponse> findCategory(@RequestParam String description) {
        CategoryDTO category = service.findCategory(description);
        return new ResponseEntity<>(new StandardResponse(200,"Success",category),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<StandardResponse> getAllCategories(){
        List<CategoryDTO> allCategory = service.getAllCategory();
        return new ResponseEntity<>(new StandardResponse(200,"Success", allCategory),HttpStatus.OK);
    }
}
