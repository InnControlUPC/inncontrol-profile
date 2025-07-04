package com.github.inncontrol.profile.application.internal.queryservices;

import com.github.inncontrol.profile.domain.model.aggregates.Profile;
import com.github.inncontrol.profile.domain.model.queries.GetAllProfilesQuery;
import com.github.inncontrol.profile.domain.model.queries.GetProfileByEmailQuery;
import com.github.inncontrol.profile.domain.model.queries.GetProfileByIdQuery;
import com.github.inncontrol.profile.domain.services.ProfileQueryService;
import com.github.inncontrol.profile.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileQueryServiceImpl implements ProfileQueryService {
    private final ProfileRepository profileRepository;

    public ProfileQueryServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Profile> handle(GetProfileByIdQuery query) {
        return profileRepository.findById(query.id());
    }

    @Override
    public Optional<Profile> handle(GetProfileByEmailQuery query) {
        System.out.println("ProfileQueryServiceImpl.handle" + query.emailAddress());
        return profileRepository.findByEmail(query.emailAddress());
    }

    @Override
    public List<Profile> handle(GetAllProfilesQuery query) {
        return profileRepository.findAll();
    }
}
