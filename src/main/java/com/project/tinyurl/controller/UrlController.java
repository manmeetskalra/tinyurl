package com.project.tinyurl.controller;

import com.project.tinyurl.dto.UrlRequest;
import com.project.tinyurl.service.UrlService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/url/shortener")
public class UrlController {

    @Autowired
    private UrlService urlService;

//    public UrlController(UrlService urlService) {
//        this.urlService = urlService;
//    }
//
    @ApiOperation(value = "Redirect", notes = "Finds original url from short url and redirects")
    @GetMapping("{shortUrl}")
    @Cacheable(value = "urls", key = "#shortUrl", sync = true)
    public String getOriginalUrl(@PathVariable String shortUrl) {
        var url = urlService.getUrl(shortUrl);
//        return ResponseEntity.status(HttpStatus.FOUND)
//                .location(URI.create(url))
//                .build();
        return url;
    }


    @ApiOperation(value = "Convert new url", notes = "Converts long url to short url")
    @PostMapping("convert")
    public String generateShortUrl(@RequestBody UrlRequest urlRequest) {
        return urlService.generateShortUrl(urlRequest);
    }
}
