package com.springboot.blog.controller;

import com.springboot.blog.entity.Category;
import com.springboot.blog.payload.CategoryDto;
import com.springboot.blog.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@Tag(
        name = "CRUD REST API for Category Resource"
)
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(
            summary = "Add Category REST API",
            description = "Add Category REST API is used to add the category into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http status 201 Created"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    //building add category rest api
    @PostMapping
    //this rest api is secured by default, so we need to give the permission to admin only to create categories
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto savedCategory = categoryService.addCategory(categoryDto);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get Category REST API",
            description = "Get Category REST API is used to get the category from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 Success"
    )
    //building get category rest api
    @GetMapping("{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") Long categoryId) {
        CategoryDto categoryDto = categoryService.getCategory(categoryId);
        return ResponseEntity.ok(categoryDto);
    }

    @Operation(
            summary = "Get All Category REST API",
            description = "Get All Category REST API is used to get all the categories from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 Success"
    )
    //building get all category rest api
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @Operation(
            summary = "Update Category REST API",
            description = "Update Category REST API is used to update the category from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 Success"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    //building update category rest api
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,
                                                      @PathVariable("id") Long categoryId){
        return ResponseEntity.ok(categoryService.updateCategory(categoryDto, categoryId));
    }

    @Operation(
            summary = "Delete Category REST API",
            description = "Delete Category REST API is used to Delete the category from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 Success"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    //build delete category rest api
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long categoryId){
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("Category deleted successfully!.");
    }
}