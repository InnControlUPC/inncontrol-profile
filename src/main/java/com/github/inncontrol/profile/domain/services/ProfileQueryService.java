package com.github.inncontrol.profile.domain.services;


import com.github.inncontrol.profile.domain.model.aggregates.Profile;
import com.github.inncontrol.profile.domain.model.queries.GetAllProfilesQuery;
import com.github.inncontrol.profile.domain.model.queries.GetProfileByEmailQuery;
import com.github.inncontrol.profile.domain.model.queries.GetProfileByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ProfileQueryService {
    Optional<Profile> handle(GetProfileByIdQuery query);
    Optional<Profile> handle(GetProfileByEmailQuery query);
    List<Profile> handle(GetAllProfilesQuery query);
}
