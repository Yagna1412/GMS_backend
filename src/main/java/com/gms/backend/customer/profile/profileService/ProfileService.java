package com.gms.backend.customer.profile.profileService;

import com.gms.backend.customer.profile.dto.CustomerDto;
import com.gms.backend.customer.profile.dto.GetProfile;
import com.gms.backend.customer.profile.entity.ProfileDetails;
import com.gms.backend.customer.profile.repositary.ProfileRepo;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Optional;

@Service
public class ProfileService {

    private final ProfileRepo repository;

    public ProfileService(ProfileRepo repository) {
        this.repository = repository;
    }

    // ==============================
    // GET PROFILE BY ID
    // ==============================
    public GetProfile getProfileById(Long id) {
        ProfileDetails user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapToDTO(user);
    }

    // ==============================
    // UPDATE PROFILE BY ID
    // ==============================
    public GetProfile updateProfileById(Long id, GetProfile dto) {
        ProfileDetails user = repository.findById(id)
                .orElse(new ProfileDetails());

        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setMobile(dto.getMobile());
        user.setAddress(dto.getAddress());

        ProfileDetails saved = repository.save(user);

        return mapToDTO(saved);
    }

    // ==============================
    // GET FIRST USER (COMMON METHOD)
    // ==============================
    private ProfileDetails getCustomer() {
        return repository.findAll()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No customer found in DB"));
    }

    // ==============================
    // GET PROFILE (NO ID)
    // ==============================
    public CustomerDto getProfile() {
        ProfileDetails customer = getCustomer();

        CustomerDto dto = new CustomerDto();
        dto.setUsername(customer.getUsername());
        dto.setEmail(customer.getEmail());
        dto.setMobile(customer.getMobile());
        dto.setAddress(customer.getAddress());

        return dto;
    }

    // ==============================
    // UPDATE PROFILE
    // ==============================
    public CustomerDto updateProfile(CustomerDto dto) {
        ProfileDetails customer = getCustomer();

        customer.setUsername(dto.getUsername());
        customer.setEmail(dto.getEmail());
        customer.setMobile(dto.getMobile());
        customer.setAddress(dto.getAddress());

        repository.save(customer);

        return dto;
    }

    // ==============================
    // CHANGE PASSWORD
    // (Ensure ProfileDetails has password field)
    // ==============================
    public boolean changePassword(Long id, String oldPassword, String newPassword) {

        Optional<ProfileDetails> optionalUser = repository.findById(id);

        if (optionalUser.isPresent()) {
            ProfileDetails user = optionalUser.get();

            if (user.getPassword().equals(oldPassword)) {
                user.setPassword(newPassword);
                repository.save(user);
                return true;
            }
        }
        return false;
    }

    // ==============================
    // TOGGLE TWO FACTOR
    // ==============================
    public void toggleTwoFactor(boolean enabled) {
        ProfileDetails customer = getCustomer();
        customer.setTwoFactorEnabled(enabled);
        repository.save(customer);
    }

    // ==============================
    // UPLOAD PROFILE IMAGE
    // ==============================
    public boolean uploadProfileImage(MultipartFile file) {
        try {
            if (file == null || file.isEmpty()) {
                System.out.println("File is empty!");
                return false;
            }

            String uploadDir = System.getProperty("user.dir")
                    + File.separator + "Uploads" + File.separator;

            File folder = new File(uploadDir);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File dest = new File(uploadDir + fileName);

            file.transferTo(dest);

            ProfileDetails customer = getCustomer();
            customer.setProfileImagePath(dest.getAbsolutePath());

            repository.save(customer);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ==============================
    // DTO MAPPING
    // ==============================
    private GetProfile mapToDTO(ProfileDetails user) {
        return new GetProfile(
                user.getUsername(),
                user.getEmail(),
                user.getMobile(),
                user.getAddress()
        );
    }
}