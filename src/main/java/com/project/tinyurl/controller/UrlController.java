package com.project.tinyurl.controller;

import com.project.tinyurl.dto.UrlRequest;
import com.project.tinyurl.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("url/shortener")
public class UrlController {

    @Autowired
    private UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> getOriginalUrl(@PathVariable String shortUrl) {
        var url = urlService.getOriginalUrl(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url))
                .build();
    }

    @PostMapping("/{urlRequest}")
    public String generateShortUrl(@PathVariable UrlRequest urlRequest) {
        return urlService.generateShortUrl(urlRequest);
    }
}
