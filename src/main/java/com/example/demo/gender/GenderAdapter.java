package com.example.demo.gender;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GenderAdapter {
    private final GenderRepository genderRepository;

    public GenderEntity findByLabel(String label) {
        return genderRepository.findByLabel(label);
    }
}
