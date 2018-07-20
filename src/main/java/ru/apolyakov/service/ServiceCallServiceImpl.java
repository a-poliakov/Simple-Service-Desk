package ru.apolyakov.service;

import ru.apolyakov.model.ServiceCall;
import ru.apolyakov.repository.ServiceCallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServiceCallServiceImpl implements ServiceCallService{

    @Autowired
    private ServiceCallRepository serviceCallRepository;

    @Override
    public Iterable<ServiceCall> findAll() {
        return serviceCallRepository.findAll();
    }

    @Override
    public List<ServiceCall> search(String q) {
        return serviceCallRepository.findByNameContaining(q);
    }

    @Override
    public ServiceCall findOne(int id) {
        return serviceCallRepository.findOne(id);
    }

    @Override
    public void save(ServiceCall contact) {
        serviceCallRepository.save(contact);
    }

    @Override
    public void delete(int id) {
        serviceCallRepository.delete(id);
    }
}
