package ru.apolyakov.repository;

import ru.apolyakov.model.ServiceCall;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServiceCallRepository extends CrudRepository<ServiceCall, Integer> {
    List<ServiceCall> findByNameContaining(String q);

    List<ServiceCall> findByEmployee_Id(int id);
}
