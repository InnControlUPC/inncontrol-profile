package com.github.inncontrol.profile.infrastructure.persistence.jpa.repositories;


import com.github.inncontrol.profile.domain.model.aggregates.Profile;
import com.github.inncontrol.profile.domain.model.valueobjects.EmailAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByEmail(EmailAddress emailAddress);
    boolean existsByEmail(EmailAddress emailAddress);
}

