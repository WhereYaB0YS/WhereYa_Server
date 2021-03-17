package com.where.WhereYouAt.domain.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface Uploader {
    String upload(MultipartFile multipartFile, String dirName) throws IOException;
}
