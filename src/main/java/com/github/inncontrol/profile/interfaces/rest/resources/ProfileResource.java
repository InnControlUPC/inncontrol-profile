package com.github.inncontrol.profile.interfaces.rest.resources;

public record ProfileResource(
        Long id,
        String names,
        String lastName,
        String email,
        String phoneNumber,
        Long userId
) { }
