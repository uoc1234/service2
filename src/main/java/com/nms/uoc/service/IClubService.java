package com.nms.uoc.service;

import com.nms.uoc.contain.ServiceContext;
import com.nms.uoc.model.RequestDTO.ClubRequestDTO;
import com.nms.uoc.model.entity.Club;
import org.springframework.data.domain.Page;

public interface IClubService {
    Page<Club> search(ServiceContext serviceContext);

    Club getByID(long id);

    Club create(ClubRequestDTO requestDTO);

    boolean delete(long id);

    Club update(Club country);
}
