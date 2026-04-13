package com.gms.backend.customer.profile.profileController;

import com.gms.backend.customer.profile.dto.ChangePasswordRequest;
import com.gms.backend.customer.profile.dto.CustomerDto;
import com.gms.backend.customer.profile.dto.GetProfile;
import com.gms.backend.customer.profile.profileService.ProfileService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileService service;

    // ✅ Constructor Injection (Best Practice)
    public ProfileController(ProfileService service) {
        this.service = service;
    }

    // ==============================
    // GET PROFILE BY ID
    // ==============================
    @GetMapping("/{id}")
    public ResponseEntity<GetProfile> getProfileById(@PathVariable("id") Long id) {
        GetProfile profile = service.getProfileById(id);
        return ResponseEntity.ok(profile);
    }

    // ==============================
    // UPDATE PROFILE BY ID
    // ==============================
    @PutMapping("/{id}")
    public ResponseEntity<GetProfile> updateProfileById(
            @PathVariable("id") Long id,
            @RequestBody GetProfile dto) {

        GetProfile updatedProfile = service.updateProfileById(id, dto);
        return ResponseEntity.ok(updatedProfile);
    }





    // ==============================
    // CHANGE PASSWORD
    // ==============================
    @PutMapping("/change-password/{id}")
    public ResponseEntity<String> changePassword(
            @PathVariable Long id,
            @RequestBody ChangePasswordRequest request) {

        boolean success = service.changePassword(
                id,
                request.getOldPassword(),
                request.getNewPassword()
        );

        if (success) {
            return ResponseEntity.ok("Password changed successfully");
        } else {
            return ResponseEntity.badRequest().body("Old password is incorrect");
        }
    }
    // ==============================
    // TOGGLE TWO FACTOR
    // ==============================
    @PutMapping("/two-factor")
    public ResponseEntity<String> toggleTwoFactor(
            @RequestParam("enabled") boolean enabled) {

        service.toggleTwoFactor(enabled);

        if (enabled) {
            return ResponseEntity.ok("Two-Factor Enabled");
        } else {
            return ResponseEntity.ok("Two-Factor Disabled");
        }
    }

    // ==============================
    // UPLOAD PROFILE IMAGE
    // ==============================
    @PostMapping("/upload-image")
    public ResponseEntity<String> uploadProfileImage(
            @RequestParam("file") MultipartFile file) {

        boolean success = service.uploadProfileImage(file);

        if (success) {
            return ResponseEntity.ok("Image uploaded successfully");
        } else {
            return ResponseEntity.badRequest().body("Image upload failed");
        }
    }
}