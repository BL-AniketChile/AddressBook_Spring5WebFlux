package com.bridgelabz.service;

import com.bridgelabz.model.Person;
import reactor.core.publisher.Mono;

public interface IPersonService {

    Mono<Person> savePerson(Person person, String addressBookId);
}
