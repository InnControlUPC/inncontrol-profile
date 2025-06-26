package com.github.inncontrol.profile.domain.model.queries;


import com.github.inncontrol.profile.domain.model.valueobjects.EmailAddress;

public record GetProfileByEmailQuery(EmailAddress emailAddress) {
}
