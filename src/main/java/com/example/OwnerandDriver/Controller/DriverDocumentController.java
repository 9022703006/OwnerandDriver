package com.example.OwnerandDriver.Controller;

import com.example.OwnerandDriver.Model.Driver_Documents;
import com.example.OwnerandDriver.Services.DriverDocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/driver/document")
public class DriverDocumentController {

    private final DriverDocumentService driverDocumentService;

    public DriverDocumentController(
            DriverDocumentService driverDocumentService) {
        this.driverDocumentService = driverDocumentService;
    }
    @PostMapping("/upload")
    public ResponseEntity<?> uploadDocument(
            @RequestParam("driverId") Long driverId,
            @RequestParam("documentType") String documentType,
            @RequestParam("file") MultipartFile file) {

        return ResponseEntity.ok(
                driverDocumentService.uploadDocument(
                        driverId,
                        documentType,
                        file
                )
        );
    }

    @PostMapping
    public ResponseEntity<?> addDocument(
            @RequestBody Driver_Documents document) {

        return ResponseEntity.ok(
                driverDocumentService.addDocument(document)
        );
    }

    @GetMapping
    public ResponseEntity<?> getAllDocuments() {

        return ResponseEntity.ok(
                driverDocumentService.getAllDocuments()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDocumentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                driverDocumentService.getDocumentById(id)
        );
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<?> getDocumentsByDriver(
            @PathVariable Long driverId) {

        return ResponseEntity.ok(
                driverDocumentService.getDocumentsByDriver(driverId)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDocument(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                driverDocumentService.deleteDocument(id)
        );
    }
}