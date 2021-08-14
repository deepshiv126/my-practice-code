package io.github.deepshiv126.design.auction.system.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import io.github.deepshiv126.design.auction.system.entity.AuctionEntity;
import io.github.deepshiv126.design.auction.system.service.AuctionProcessingService;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class AuctionProcessingServiceTest {

    private ObjectMapper objectMapper;

    @BeforeTest
    public void beforTest() {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void readInputJsonFileTest() throws IOException {
        String inputJson = String.format("[{\"id\":\"a8cfcb76-7f24-4420-a5ba-d46dd77bdffd\",\"type\":\"newItem\"," +
                "\"name\":\"Bicycle\",\"description\":\"Hot Wheels Child's Bicycle\",\"timeOfAuction\":300},{\"id\"" +
                ":\"58e9b5fe-3fde-4a27-8e98-682e58a4a65d\",\"type\":\"bid\",\"name\":\"Alice\",\"itemName\":\"Bicycle\"" +
                ",\"item\":\"a8cfcb76-7f24-4420-a5ba-d46dd77bdffd\",\"startingBid\":50,\"maxBid\":80,\"bidIncrement\":3}" +
                ",{\"id\":\"60252799-e5c1-4b99-9f43-4db4728e7854\",\"type\":\"bid\",\"name\":\"Aaron\",\"itemName\":" +
                "\"Bicycle\",\"item\":\"a8cfcb76-7f24-4420-a5ba-d46dd77bdffd\",\"startingBid\":60,\"maxBid\":82," +
                "\"bidIncrement\":2},{\"id\":\"71889c03-ad40-4251-be90-61591a2f3894\",\"type\":\"bid\",\"name\":" +
                "\"Amanda\",\"itemName\":\"Bicycle\",\"item\":\"a8cfcb76-7f24-4420-a5ba-d46dd77bdffd\",\"startingBid\"" +
                ":55,\"maxBid\":85,\"bidIncrement\":5}]");
        TypeFactory typeFactory = this.objectMapper.getTypeFactory();
        List<AuctionEntity> auctionEntityList = this.objectMapper.readValue(inputJson, typeFactory.constructCollectionType(List.class, AuctionEntity.class));

        AuctionProcessingService auctionProcessingService = new AuctionProcessingServiceImpl();
        auctionProcessingService.processAuctionEntities(auctionEntityList, 1L);
    }
}
