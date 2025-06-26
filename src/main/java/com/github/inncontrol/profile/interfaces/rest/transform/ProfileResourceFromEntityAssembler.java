package com.github.inncontrol.profile.interfaces.rest.transform;

import com.github.inncontrol.profile.domain.model.aggregates.Profile;
import com.github.inncontrol.profile.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource toResourceFromEntity(Profile entity) {
        return new ProfileResource(entity.getId(), entity.getName().firstName(), entity.getName().lastName(), entity.getEmailAddress(),
                entity.getPhoneNumber(),
                entity.getUserId());

    }
}
