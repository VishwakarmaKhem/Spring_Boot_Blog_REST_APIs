package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@Tag(
        name = "Crud REST APIs for Post Resource"
)
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Operation(
            summary = "Create Post REST API",
            description = "Create Post REST API is used to create a post in the Database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http status 201 Created"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    //create blogpost restapi
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get All Post REST API",
            description = "Get All Post REST API is used to Get all the posts from the Database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 success"
    )
    //get all posts rest api
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return postService.getAllPost(pageNo, pageSize, sortBy, sortDir);
    }

    @Operation(
            summary = "Get Post by ID REST API",
            description = "Get Post by id REST API is used to Get a particular post from the Database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 success"
    )
    //get post by id rest api
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id")long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @Operation(
            summary = "Update Post by ID REST API",
            description = "Update Post by id REST API is used to update a particular post in the Database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 success"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    //update post by id rest api
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name="id") long id){
        PostDto postResponse = postService.updatePost(postDto, id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }


    @Operation(
            summary = "Delete Post by ID REST API",
            description = "Delete Post by id REST API is used to Delete a particular post from the Database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 success"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    //Delete post by id rest api
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deletePost(@PathVariable(name="id") long id){
       postService.deletePostById(id);
       return new ResponseEntity<>("Post entity deleted successfully", HttpStatus.OK);
    }

    @Operation(
            summary = "Get Post by Category REST API",
            description = "Get Post by Category REST API is used to get the post by a category from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 Success"
    )
    //building get post by category rest api
    //http://localhost:8080/api/posts/category/3
    @GetMapping("/category/{id}")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable("id") Long categoryId){
       List<PostDto> postDtos =  postService.getPostsByCategory(categoryId);
       return ResponseEntity.ok(postDtos);
    }

}
