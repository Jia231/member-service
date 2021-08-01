package com.example.demo.members;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<GenderEntity, Long> {
    GenderEntity findByLabel(String label);
}
