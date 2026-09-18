package com.example.OwnerandDriver.Services;

import com.example.OwnerandDriver.Model.Driver_Documents;
import com.example.OwnerandDriver.Model.Driver_Profile;
import com.example.OwnerandDriver.Repository.DriverDocumentRepository;
import com.example.OwnerandDriver.Repository.DriverResository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class DriverDocumentService {

    private final DriverDocumentRepository driverDocumentRepository;
    private final DriverResository driverResository;


    public DriverDocumentService(DriverDocumentRepository driverDocumentRepository, DriverResository driverResository) {
        this.driverDocumentRepository = driverDocumentRepository;
        this.driverResository = driverResository;
    }

    public Driver_Documents addDocument(Driver_Documents document) {
        return driverDocumentRepository.save(document);
    }

    public Driver_Documents getDocumentById(Long id) {
        return driverDocumentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Document not found"));
    }

    public List<Driver_Documents> getAllDocuments() {
        return driverDocumentRepository.findAll();
    }

    public List<Driver_Documents> getDocumentsByDriver(Long driverId) {
        return driverDocumentRepository.findByDriver_Id(driverId);
    }

    public String deleteDocument(Long id) {

        if (!driverDocumentRepository.existsById(id)) {
            throw new RuntimeException("Document not found");
        }

        driverDocumentRepository.deleteById(id);

        return "Document deleted successfully";
    }

    public Driver_Documents uploadDocument(
            Long driverId,
            String documentType,
            MultipartFile file) {

        try {

            // 1. Check driver
            Driver_Profile driver = driverResository.findById(driverId)
                    .orElseThrow(() ->
                            new RuntimeException("Driver not found"));

            // 2. Check file
            if (file == null || file.isEmpty()) {
                throw new RuntimeException("File is empty");
            }

            // 3. Check document type
            if (documentType == null || documentType.isBlank()) {
                throw new RuntimeException("Document type is required");
            }

            // 4. Create upload directory
            String uploadDir = System.getProperty("user.dir")
                    + File.separator + "uploads"
                    + File.separator + "drivers"
                    + File.separator + driverId;

            Path uploadPath = Paths.get(uploadDir);

            Files.createDirectories(uploadPath);

            System.out.println("UPLOAD FOLDER = "
                    + uploadPath.toAbsolutePath());

            // 5. Get file name
            String fileName = file.getOriginalFilename();

            if (fileName == null || fileName.isBlank()) {
                throw new RuntimeException("Invalid file name");
            }

            // 6. Create file path
            Path filePath = uploadPath.resolve(fileName);

            System.out.println("FILE PATH = "
                    + filePath.toAbsolutePath());

            // 7. Save file
            Files.copy(
                    file.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING
            );

            System.out.println("FILE EXISTS = "
                    + Files.exists(filePath));

            // 8. Save document information in database
            Driver_Documents document = new Driver_Documents();

            document.setDriver(driver);
            document.setDocumentType(documentType);
            document.setDocumentUrl(filePath.toString());

            return driverDocumentRepository.save(document);

        } catch (IOException e) {

            throw new RuntimeException(
                    "Document upload failed: " + e.getMessage(), e
            );
        }
    }
}