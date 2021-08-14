package io.github.deepshiv126.design.auction.system.service;


import io.github.deepshiv126.design.auction.system.entity.AuctionEntity;

import java.util.List;

public interface AuctionProcessingService {

    /**
     * This method takes a list of json objects and rate of data ingestion,
     * process based on defined auction system rules.
     *
     * @param auctionEntityList   List of Json objects.
     * @param rateOfDataIngestion Rate of Data ingestion.
     */
    void processAuctionEntities(final List<AuctionEntity> auctionEntityList, final Long rateOfDataIngestion);
}
