package org.launchcode.liftoffproject.controllers;

import org.launchcode.liftoffproject.models.Comment;
import org.launchcode.liftoffproject.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/comments")
public class CommentsController {
    @GetMapping(path= "{commentId}")
    public String getComment(
            @PathVariable("commentId") String commentId) {
        return ("returning comment with id - " + commentId);
    }
    @PostMapping(
            consumes = {"application/json", "application/xml"},
            produces= { "application/json", "application/xml"})
    public ResponseEntity<Comment> createdDate(
            @RequestBody Comment comment ) {
        return
                new ResponseEntity<Comment>(comment, HttpStatus.CREATED);
    }
}
