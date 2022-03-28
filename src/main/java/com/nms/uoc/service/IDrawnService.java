package com.nms.uoc.service;

import com.nms.uoc.model.entity.Quarterfinals;
import com.nms.uoc.model.entity.Semifinal;

public interface IDrawnService {
    Quarterfinals quarterfinals(int... clubId);

    Semifinal semifinal(int... clubId);
}
