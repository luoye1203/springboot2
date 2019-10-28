package com.lht.bService;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;


@Service
public class ExportService {


    public ResponseEntity<FileSystemResource> export(HttpServletRequest request, File file) {
        if (file == null) {
            return null;
        }
        try {

            String fileName = file.getName();
            String userAgent = request.getHeader("User-Agent");
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");

            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Content-Disposition", "attachment; filename=" + fileName + ".docx");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            headers.add("Last-Modified", new Date().toString());
            headers.add("ETag", String.valueOf(System.currentTimeMillis()));

            return ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("application/octet-stream")).body(new FileSystemResource(file));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
