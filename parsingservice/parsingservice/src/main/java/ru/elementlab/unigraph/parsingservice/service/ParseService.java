package ru.elementlab.unigraph.parsingservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface ParseService {
    void parse(MultipartFile data);
}
