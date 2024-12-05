package com.example.demo.controller;

import com.example.demo.model.InternshipSeeker;
import com.example.demo.service.InternshipSeekerService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class FileUploadController {

    @Autowired
    private InternshipSeekerService internshipSeekerService;  // Inject your service

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam("userId") Long userId) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file uploaded");
        }

        try {
            // Define the directory to store uploaded resumes
            String uploadDirectory = "C:/Users/Dell/Desktop/FULL-STACK/projects/intern-project/uploads/"; // or use relative path like "uploads/"
            File directory = new File(uploadDirectory);
            if (!directory.exists()) {
                directory.mkdirs();  // Create the directory if it doesn't exist
            }

            // Save the file to the specified path
            File destFile = new File(uploadDirectory + file.getOriginalFilename());
            file.transferTo(destFile);

            // Use the service to update the resume path for the given user ID
            InternshipSeeker seeker = internshipSeekerService.updateResume(userId, destFile.getPath());

            if (seeker == null) {
                return ResponseEntity.status(404).body("User not found");
            }

            return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error uploading file");
        }

    }


    @GetMapping("/{userId}/resume")
    public ResponseEntity<byte[]> getResume(@PathVariable Long userId) {
        // Retrieve the InternshipSeeker based on the userId
        InternshipSeeker seeker = internshipSeekerService.getInternshipSeeker(userId);

        // Get the file path of the resume
        String resumePath = seeker.getResumeUrl(); // This is the stored file path
        File resumeFile = new File(resumePath);

        if (!resumeFile.exists()) {
            return ResponseEntity.notFound().build(); // Return 404 if the file doesn't exist
        }

        try {
            // Read the file as bytes
            byte[] fileContent = java.nio.file.Files.readAllBytes(resumeFile.toPath());

            // Set the headers to serve the file as a PDF
            return ResponseEntity.ok()
                    .header("Content-Type", "application/pdf")
                    .header("Content-Disposition", "inline; filename=\"" + resumeFile.getName() + "\"")
                    .body(fileContent);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null); // Return 500 in case of an error
        }
    }



}
