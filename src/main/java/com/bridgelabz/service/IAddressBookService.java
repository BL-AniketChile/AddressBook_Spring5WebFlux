package com.bridgelabz.service;

import com.bridgelabz.model.AddressBook;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IAddressBookService {

    Mono<AddressBook>  saveAddressBook(AddressBook addressBook);

    Flux<AddressBook> getAddressBook();
}
