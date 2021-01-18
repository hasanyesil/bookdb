package com.hashy.bookdb.services;

import com.hashy.bookdb.domain.Book;
import com.hashy.bookdb.domain.Publisher;
import com.hashy.bookdb.repositories.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PublisherServiceImpl implements PublisherService{

    private final PublisherRepository publisherRepository;

    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public Set<Publisher> findAll() {
        Set<Publisher> publishers = new HashSet<>();
        publisherRepository.findAll().forEach(publishers::add);
        return publishers;
    }

    @Override
    public Publisher findByBook(Book book) {
        return publisherRepository.findByBooks(book);
    }

    @Override
    public Publisher save(Publisher publisher) {
        return publisherRepository.save(publisher);
    }
}
