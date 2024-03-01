package com.project.tinyurl.service;

import com.project.tinyurl.dto.UrlRequest;
import com.project.tinyurl.modal.Url;
import com.project.tinyurl.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.Date;


@Service
public class UrlService {

    @Autowired

    private UrlRepository urlRepository;
    private BaseConversion baseConversion;

    public UrlService(UrlRepository urlRepository, BaseConversion baseConversion){

        this.urlRepository = urlRepository;
        this.baseConversion = baseConversion;

    }
    public String getUrl(String url) {
        System.out.println("url: " + url);
        long id = baseConversion.decode(url);
        System.out.println("id: " + id);

        var entity = urlRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No url find for this short url: " + url));

        if(entity.getExpirationDate()!=null && entity.getExpirationDate().before(new Date())){
            urlRepository.delete(entity);
            throw new EntityNotFoundException("Tiny Url Expired");
        }
        return entity.getUrl();
    }

    public String generateShortUrl(UrlRequest urlRequest) {
        Url url = new Url();
        url.setUrl(urlRequest.getUrl());
        url.setCreationDate(new Date());
        url.setExpirationDate(urlRequest.getExpirationDate());
        var entry = urlRepository.save(url);

        return baseConversion.encode(entry.getId());
    }
}
