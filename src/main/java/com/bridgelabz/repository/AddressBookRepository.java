package com.bridgelabz.repository;

import com.bridgelabz.model.AddressBook;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressBookRepository extends ReactiveMongoRepository<AddressBook, String> {

}
