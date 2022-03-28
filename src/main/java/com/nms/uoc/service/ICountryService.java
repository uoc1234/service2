package com.nms.uoc.service;

import com.nms.uoc.contain.ServiceContext;
import com.nms.uoc.model.RequestDTO.CountryRequestDTO;
import com.nms.uoc.model.entity.Country;
import org.springframework.data.domain.Page;

public interface ICountryService {
    Page<Country> search(ServiceContext serviceContext);

    Country getByID(long id);

    Country create(CountryRequestDTO country);

    boolean delete(long id);

    Country update(Country country);
}
