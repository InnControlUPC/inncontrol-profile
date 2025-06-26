package com.github.inncontrol.profile.domain.model.commands;

public record CreateProfileCommand(String firstName, String lastName,String phoneNumber, String email,Long userId) { }
