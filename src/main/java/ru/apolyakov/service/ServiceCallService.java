package ru.apolyakov.service;

import ru.apolyakov.model.ServiceCall;

import java.util.List;

public interface ServiceCallService {
    Iterable<ServiceCall> findAll();

    List<ServiceCall> search(String q);

    ServiceCall findOne(int id);

    void save(ServiceCall serviceCall);

    void delete(int id);
}
