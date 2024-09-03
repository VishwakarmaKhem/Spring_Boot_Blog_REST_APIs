package com.springboot.blog.controller;

import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(
        name = "CRUD REST API for Comment Resource"
)
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(
            summary = "Create Comment REST API",
            description = "Create Comment REST API is used to add the comment in the existing post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http status 201 Created"
    )
    //create comment rest api
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
                                                    @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);

    }

    @Operation(
            summary = "Get Comment by Post Id REST API",
            description = "Get Comment by Post Id REST API is used to get a particular comment by a post id from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 Success"
    )
    //get comment by post id rest api
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId") long postId){
        return commentService.getCommentsByPostId(postId);
    }

    @Operation(
            summary = "Get Comment by Id REST API",
            description = "Get Comment by id REST API is used to get the particular comment of a particular post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 Created"
    )
    //get Comment by id REST API
    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") long postId,
                                                     @PathVariable(value = "id") long commentId){
        CommentDto commentDto = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    @Operation(
            summary = "Update Comment REST API",
            description = "Update Comment REST API is used to update the comment of the existing post into database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 Success"
    )
    //update comment rest api
    @PutMapping("posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") long postId,
                                                    @PathVariable(value = "id") long commentId,
                                                    @Valid @RequestBody CommentDto commentDto){  //RequestBody annotation converts the json request to java object by using http message converters
       CommentDto updatedComment = commentService.updateComment(postId, commentId, commentDto);
       return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete Comment REST API",
            description = "Delete Comment REST API is used to Delete the comment of the existing post into database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 Success"
    )
    //delete comment rest api
    @DeleteMapping("posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "postId") long postId,
                                                @PathVariable(value = "id") long commentId){

        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("comment deleted successfully", HttpStatus.OK);
    }
}
