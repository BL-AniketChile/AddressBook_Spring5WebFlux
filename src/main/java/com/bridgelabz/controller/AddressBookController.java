package com.bridgelabz.controller;

import com.bridgelabz.dto.Response;
import com.bridgelabz.model.AddressBook;
import com.bridgelabz.service.IAddressBookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class AddressBookController {

    @Autowired
    private IAddressBookService addressBookService;

    @PostMapping("addressbook")
    @Operation(description = "This API is used to create Address Book.", summary = "Create Address Book")
//    @ApiResponses(value = [])
    public Mono<AddressBook> saveAddressBook(@RequestBody AddressBook addressBook){
        return addressBookService.saveAddressBook(addressBook);
    }

    @GetMapping("addressbook")
    public Flux<AddressBook> getAddressBook() {
        return addressBookService.getAddressBook();
    }
}
