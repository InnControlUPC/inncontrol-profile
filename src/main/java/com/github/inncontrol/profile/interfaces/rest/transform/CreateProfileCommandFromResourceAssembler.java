package com.github.inncontrol.profile.interfaces.rest.transform;


import com.github.inncontrol.profile.domain.model.commands.CreateProfileCommand;
import com.github.inncontrol.profile.interfaces.rest.resources.CreateProfileResource;

public class CreateProfileCommandFromResourceAssembler {
    public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource) {
        return new CreateProfileCommand(resource.firstName() , resource.lastName(), resource.phoneNumber(),resource.email(), 0L);
    }
}
