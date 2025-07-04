package com.github.inncontrol.profile.interfaces.rest;

import com.github.inncontrol.profile.domain.model.queries.GetAllProfilesQuery;
import com.github.inncontrol.profile.domain.model.queries.GetProfileByIdQuery;
import com.github.inncontrol.profile.domain.model.queries.GetProfileByEmailQuery;
import com.github.inncontrol.profile.domain.model.valueobjects.EmailAddress;
import com.github.inncontrol.profile.domain.services.ProfileCommandService;
import com.github.inncontrol.profile.domain.services.ProfileQueryService;
import com.github.inncontrol.profile.interfaces.rest.resources.CreateProfileResource;
import com.github.inncontrol.profile.interfaces.rest.resources.ProfileResource;
import com.github.inncontrol.profile.interfaces.rest.transform.CreateProfileCommandFromResourceAssembler;
import com.github.inncontrol.profile.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Profiles", description = "Profile Management Endpoints")
public class ProfilesController {
    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;

    public ProfilesController(ProfileCommandService profileCommandService, ProfileQueryService profileQueryService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
    }

    @GetMapping("/by-email")
    public ResponseEntity<ProfileResource> getProfileByEmail(@RequestParam String email) {
        var emailAddress = new EmailAddress(email);
        var getProfileByEmailQuery = new GetProfileByEmailQuery(emailAddress);
        var profile = profileQueryService.handle(getProfileByEmailQuery);
        if (profile.isEmpty()) return ResponseEntity.notFound().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }

    @PostMapping
    public ResponseEntity<ProfileResource> createProfile(@RequestBody CreateProfileResource resource) {
        var createProfileCommand = CreateProfileCommandFromResourceAssembler.toCommandFromResource(resource);
        var profile = profileCommandService.handle(createProfileCommand);
        if (profile.isEmpty()) return ResponseEntity.badRequest().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return new ResponseEntity<>(profileResource, HttpStatus.CREATED);
    }



    @GetMapping("/{profileId:\\d+}")
    public ResponseEntity<ProfileResource> getProfileById(@PathVariable Long profileId) {
        var getProfileByIdQuery = new GetProfileByIdQuery(profileId);
        var profile = profileQueryService.handle(getProfileByIdQuery);
        if (profile.isEmpty()) return ResponseEntity.notFound().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }



    @GetMapping
    public ResponseEntity<List<ProfileResource>> getAllProfiles() {
        var getAllProfilesQuery = new GetAllProfilesQuery();
        var profiles = profileQueryService.handle(getAllProfilesQuery);
        var profileResources = profiles.stream()
                .map(ProfileResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(profileResources);
    }
}
