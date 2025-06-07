package com.recruitment.controller;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.recruitment.model.ApplyJobRequest;
import com.recruitment.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {
	
	@Autowired
	private EmailService emailService;
	
	// Allowed MIME types
    private static final List<String> ALLOWED_CONTENT_TYPES = Arrays.asList(
            "application/pdf",
            "application/msword",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
    );

    // Max file size: 5 MB
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024;

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestPart("data") @Valid ApplyJobRequest data,
            @RequestPart("file") MultipartFile file) throws MessagingException {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        String contentType = file.getContentType();

        // Validation: empty file
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        // Validation: content type
        if (!ALLOWED_CONTENT_TYPES.contains(contentType)) {
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                    .body("Only PDF, DOC, and DOCX files are allowed");
        }

        // Validation: file size
        if (file.getSize() > MAX_FILE_SIZE) {
            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                    .body("File exceeds maximum allowed size of 5 MB");
        }

        // Logic: Save file (optional)
        // e.g., Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        
        emailService.sendMailWithAttachment("venkateswara.guduru88@gmail.com", "Test Mail", "Hello , PFA",file);

        return ResponseEntity.ok("File uploaded successfully: " + filename);
    }

}
