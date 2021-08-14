package io.github.deepshiv126.design.auction.system;

import io.github.deepshiv126.design.auction.system.entity.AuctionEntity;
import io.github.deepshiv126.design.auction.system.service.impl.AuctionProcessingServiceImpl;
import io.github.deepshiv126.design.auction.system.service.impl.ReadInputJsonFileServiceImpl;
import org.testng.annotations.Test;

import java.util.List;

public class AppTest {

    @Test
    public void appTest() {
        final Long rateOfDataInjection = 0L;
        final String inputFileName = this.getClass().getResource("/auctionitems.json").getFile();

        List<AuctionEntity> auctionEntityList = new ReadInputJsonFileServiceImpl().readInputJsonFile(inputFileName);
        new AuctionProcessingServiceImpl().processAuctionEntities(auctionEntityList, rateOfDataInjection);
    }
}
