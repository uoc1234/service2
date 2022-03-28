package com.nms.uoc.service.impl;

import com.nms.uoc.config.exceptions.AppException;
import com.nms.uoc.config.exceptions.ErrorResponseBase;
import com.nms.uoc.contain.CheckManager;
import com.nms.uoc.contain.ServiceContext;
import com.nms.uoc.model.RequestDTO.ClubRequestDTO;
import com.nms.uoc.model.entity.Club;
import com.nms.uoc.model.entity.Country;
import com.nms.uoc.repository.ClubRepository;
import com.nms.uoc.service.IClubService;
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
public class ClubService implements IClubService {
    @Autowired
    ClubRepository repository;

    @Autowired
    ICountryService countryService;

    @Override
    @SneakyThrows
    public Page<Club> search(ServiceContext serviceContext) {
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
    public Club getByID(long id) {
        if (!repository.existsById(id)) {
            throw new AppException(ErrorResponseBase.NOT_FOUND, id);
        }
        try {
            return repository.findById(id).get();
        } catch (Exception exception) {
            throw new AppException(exception);
        }
    }

    @Override
    @SneakyThrows
    public Club create(ClubRequestDTO requestDTO) {
        Club club = convertToEntity(requestDTO);
        try {
            return repository.save(club);
        } catch (Exception exception) {
            throw new AppException(exception);
        }
    }

    @Override
    @SneakyThrows
    public boolean delete(long id) {
        if (!repository.existsById(id)) {
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
    public Club update(Club country) {
        if (!repository.existsById(country.getId())) {
            throw new AppException(ErrorResponseBase.NOT_EXISTED);
        }
        try {
            return repository.save(country);
        } catch (Exception exception) {
            throw new AppException(exception);
        }
    }

    @SneakyThrows
    private Club convertToEntity(ClubRequestDTO clubRequestDTO) {
        Club club = new Club();
        BeanUtils.copyProperties(clubRequestDTO, club, "id");
        Country country = countryService.getByID(clubRequestDTO.getCountryId());
        club.setCountry(country);
        return club;
    }
}
