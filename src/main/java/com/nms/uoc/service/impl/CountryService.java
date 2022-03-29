package com.nms.uoc.service.impl;

import com.nms.uoc.config.exceptions.AppException;
import com.nms.uoc.config.exceptions.ErrorResponseBase;
import com.nms.uoc.contain.CheckManager;
import com.nms.uoc.contain.ServiceContext;
import com.nms.uoc.model.RequestDTO.CountryRequestDTO;
import com.nms.uoc.model.entity.Country;
import com.nms.uoc.repository.CountryRepository;
import com.nms.uoc.service.ICountryService;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CountryService implements ICountryService {
    @Autowired
    CountryRepository repository;

    @Override
    @SneakyThrows
    public Page<Country> search(ServiceContext serviceContext) {
        CheckManager.checkServiceContext(serviceContext);
        PageRequest pageRequest = CheckManager.checkPageable(serviceContext);
        try {
            if (serviceContext.getKey() == null || serviceContext.getKey().equals("")) {
                return repository.findAll(pageRequest);
            }
            return repository.search(serviceContext.getKey(), pageRequest);
        } catch (Exception exception) {
            throw new AppException(exception);
        }
    }

    @Override
    @SneakyThrows
    public Country getByID(long id) {
        if (repository.findById(id).isPresent()) {
            return repository.findById(id).get();
        } else {
            throw new AppException(ErrorResponseBase.NOT_FOUND);
        }
    }

    @Override
    @SneakyThrows
    public Country create(CountryRequestDTO countryDTO) {
        Country country = convertToEntity(countryDTO);
        try {
            return repository.save(country);
        } catch (Exception exception) {
            throw new AppException(exception);
        }
    }

    @Override
    @SneakyThrows
    public boolean delete(long id) {
        if (!repository.findById(id).isPresent()) {
            throw new AppException(ErrorResponseBase.NOT_EXISTED);
        }
        try {
            repository.deleteById(id);
        } catch (Exception exception) {
            throw new AppException(exception);
        }
        return true;
    }

    @Override
    @SneakyThrows
    public Country update(Country country) {
        if (!repository.findById(country.getId()).isPresent()) {
            throw new AppException(ErrorResponseBase.NOT_EXISTED);
        }
        try {
            return repository.save(country);
        } catch (Exception exception) {
            throw new AppException(exception);
        }
    }

    @SneakyThrows
    private Country convertToEntity(CountryRequestDTO countryRequestDTO) {
        Country country = new Country();
        BeanUtils.copyProperties(countryRequestDTO, country, "id");
        return country;
    }
}
