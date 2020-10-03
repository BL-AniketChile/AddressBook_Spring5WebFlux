package com.bridgelabz.exception;

public class AddressBookNotFoundException extends RuntimeException {

    public AddressBookNotFoundException(String s) {
        super(s);
    }
}
