package com.gms.backend.technician.progress.service;

import com.gms.backend.technician.progress.dto.ProgressUpdateResponse;
import com.gms.backend.technician.progress.entity.ProgressPhoto;
import com.gms.backend.technician.progress.entity.ProgressUpdate;
import com.gms.backend.technician.progress.repository.ProgressUpdateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProgressUpdateService {

    @Autowired
    private ProgressUpdateRepository repository;

    private final String uploadDir = "uploads/";

    //  ADD UPDATE
    public ProgressUpdateResponse addUpdate(String jobId, String note, List<MultipartFile> files) {

        ProgressUpdate update = new ProgressUpdate();
        update.setJobId(jobId);
        update.setNote(note);
        update.setCreatedAt(LocalDateTime.now());

        List<ProgressPhoto> photos = new ArrayList<>();

        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                try {
                    Files.createDirectories(Paths.get(uploadDir));

                    String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                    Path filePath = Paths.get(uploadDir + fileName);

                    Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                    ProgressPhoto photo = new ProgressPhoto();
                    photo.setFileUrl(uploadDir + fileName);
                    photo.setProgressUpdate(update);

                    photos.add(photo);

                } catch (Exception e) {
                    throw new RuntimeException("File upload failed: " + e.getMessage());
                }
            }
        }

        update.setPhotos(photos);

        ProgressUpdate saved = repository.save(update);

        return mapToResponse(saved);
    }

    //  GET UPDATES
    public List<ProgressUpdateResponse> getUpdates(String jobId) {
        return repository.findByJobIdOrderByCreatedAtDesc(jobId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    //  NEW: ACTIVE JOBS
    public List<String> getActiveJobs() {
        return repository.findDistinctJobIds();
    }

    //  RESPONSE MAPPER
    private ProgressUpdateResponse mapToResponse(ProgressUpdate update) {

        ProgressUpdateResponse res = new ProgressUpdateResponse();

        res.setId(update.getId());
        res.setJobId(update.getJobId());
        res.setNote(update.getNote());
        res.setCreatedAt(update.getCreatedAt());

        List<String> urls = new ArrayList<>();

        if (update.getPhotos() != null) {
            for (ProgressPhoto p : update.getPhotos()) {
                urls.add(p.getFileUrl());
            }
        }

        res.setPhotoUrls(urls);

        return res;
    }
}