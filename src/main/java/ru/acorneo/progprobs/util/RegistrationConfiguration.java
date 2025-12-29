package ru.acorneo.progprobs.util;

import lombok.Getter;

import java.util.List;

@Getter
public class RegistrationConfiguration {
    List<String> rolesGivenUponRegistration = List.of("user");
}
