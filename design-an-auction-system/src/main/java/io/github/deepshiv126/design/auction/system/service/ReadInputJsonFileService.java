package io.github.deepshiv126.design.auction.system.service;

import io.github.deepshiv126.design.auction.system.entity.AuctionEntity;

import java.util.List;

public interface ReadInputJsonFileService {

    /**
     * Reads given input json file and maps to simple Java POJO.
     *
     * @param inputFile A input file full path and name.
     * @return
     */
    List<AuctionEntity> readInputJsonFile(final String inputFile);
}
