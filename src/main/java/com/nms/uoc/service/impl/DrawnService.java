package com.nms.uoc.service.impl;

import com.nms.uoc.config.exceptions.AppException;
import com.nms.uoc.config.exceptions.ErrorResponseBase;
import com.nms.uoc.model.entity.Club;
import com.nms.uoc.model.entity.Quarterfinals;
import com.nms.uoc.model.entity.Semifinal;
import com.nms.uoc.service.IClubService;
import com.nms.uoc.service.IDrawnService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Random;

@Service
@Transactional
public class DrawnService implements IDrawnService {
    @Autowired
    IClubService clubService;

    @Override
    @SneakyThrows
    public Quarterfinals quarterfinals(int... clubId) {
        if (clubId.length != 8) {
            throw new AppException(ErrorResponseBase.NUMBER_OF_TEAM_IS_NOT_VALID);
        }
        if (!checkLoop(clubId)) {
            throw new AppException(ErrorResponseBase.TEAM_IS_DUPLICATED);
        }
        int[] teams = {0, 1, 2, 3, 4, 5, 6, 7};
        teams = shuffle(teams, teams.length);
        Club[] round_1 = match(clubId[teams[0]], clubId[teams[1]]);
        Club[] round_2 = match(clubId[teams[2]], clubId[teams[3]]);
        Club[] round_3 = match(clubId[teams[4]], clubId[teams[5]]);
        Club[] round_4 = match(clubId[teams[6]], clubId[teams[7]]);
        Quarterfinals quarterfinals = new Quarterfinals(round_1, round_2, round_3, round_4);

        return quarterfinals;
    }

    @Override
    @SneakyThrows
    public Semifinal semifinal(int... clubId) {
        if (clubId.length != 4) {
            throw new AppException(ErrorResponseBase.NUMBER_OF_TEAM_IS_NOT_VALID);
        }
        if (!checkLoop(clubId)) {
            throw new AppException(ErrorResponseBase.TEAM_IS_DUPLICATED);
        }
        int[] teams = {0, 1, 2, 3};
        teams = shuffle(teams, teams.length);
        Club[] round_1 = match(clubId[teams[0]], clubId[teams[1]]);
        Club[] round_2 = match(clubId[teams[2]], clubId[teams[3]]);
        Semifinal semifinal = new Semifinal(round_1, round_2);
        return semifinal;
    }

    static int[] shuffle(int[] numbers, int length) {
        Random r = new Random();
        for (int i = length - 1; i >= 0; i--) {
            int j = r.nextInt(i + 1);
            int temp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = temp;
        }
        return numbers;
    }

    public Club[] match(int team_1, int team_2) {
        Club[] match = new Club[2];
        match[0] = clubService.getByID(team_1);
        match[1] = clubService.getByID(team_2);
        return match;
    }

    public boolean checkLoop(int[] idClub) {
        for (int i = 0; i < idClub.length; i++) {
            int count = 0;
            for (int j = 0; j < idClub.length; j++) {
                if (idClub[i] == idClub[j]) {
                    count++;
                }
            }
            if (count > 1) {
                return false;
            }
        }
        return true;
    }
}
