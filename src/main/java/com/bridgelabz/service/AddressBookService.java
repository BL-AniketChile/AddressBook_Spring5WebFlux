package com.bridgelabz.service;

import com.bridgelabz.model.AddressBook;
import com.bridgelabz.repository.AddressBookRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressBookService implements IAddressBookService {

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public Mono<AddressBook> saveAddressBook(AddressBook addressBook) {
        amqpTemplate.convertAndSend("address-data-exchange", "address", addressBook.toString());
        return addressBookRepository.save(addressBook);
    }

    @Override
    public Flux<AddressBook> getAddressBook() {
        return null;
    }
}
