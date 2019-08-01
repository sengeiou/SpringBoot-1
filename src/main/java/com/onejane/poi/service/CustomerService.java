package com.onejane.poi.service;

import org.springframework.web.multipart.MultipartFile;

public interface CustomerService {
    boolean batchImport(String fileName, MultipartFile file) throws Exception;
}
