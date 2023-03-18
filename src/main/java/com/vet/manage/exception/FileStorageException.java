package com.vet.manage.exception;

/**
 * Custom exception class for file storage
 * @author Jane Aarthy Joseph
 */
public class FileStorageException extends RuntimeException {
    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}