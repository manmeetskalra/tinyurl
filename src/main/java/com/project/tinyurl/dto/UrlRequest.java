package com.project.tinyurl.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(description = "Parameters for Post Request for URL Shortener")
public class UrlRequest {

    @ApiModelProperty(required = true, notes = "String url that needed to be shortened")
    private String url;

    @ApiModelProperty(notes = "Expiration DateTime of URL")
    private Date expirationDate;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
