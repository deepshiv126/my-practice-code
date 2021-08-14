package io.github.deepshiv126.design.auction.system.service.impl;

import io.github.deepshiv126.design.auction.system.entity.AuctionEntity;
import io.github.deepshiv126.design.auction.system.service.ReadInputJsonFileService;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ReadInputJsonFileServiceTest {

    @Test
    public void readInputJsonFileTest() {
        String inputJsonFile = this.getClass().getResource("/auctionitems.json").getFile();
        ReadInputJsonFileService readInputJsonFileService = new ReadInputJsonFileServiceImpl();
        List<AuctionEntity> auctionEntityList = readInputJsonFileService.readInputJsonFile(inputJsonFile);
        Assert.assertEquals(4, auctionEntityList.size());
        Assert.assertEquals(auctionEntityList.get(0).getType(), "newItem");
        Assert.assertEquals(auctionEntityList.get(1).getType(), "bid");
        Assert.assertEquals(auctionEntityList.get(2).getName(), "Aaron");
        Assert.assertEquals(auctionEntityList.get(2).getItem(), "a8cfcb76-7f24-4420-a5ba-d46dd77bdffd");
    }
}
