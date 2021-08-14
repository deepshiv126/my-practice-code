package io.github.deepshiv126.design.auction.system.service.impl;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.deepshiv126.design.auction.system.entity.AuctionEntity;
import io.github.deepshiv126.design.auction.system.service.ReadInputJsonFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * This class reads JSON file and maps to a pojo.
 *
 * @author deepakshivanandappa
 */
public class ReadInputJsonFileServiceImpl implements ReadInputJsonFileService {

    private static final Logger LOG = LoggerFactory.getLogger(ReadInputJsonFileServiceImpl.class);

    private final ObjectMapper objectMapper;

    public ReadInputJsonFileServiceImpl() {
        // object mapper is thread safe, so going with singleton.
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Reads given input json file and maps to simple Java POJO.
     *
     * @param inputFile A input file full path and name.
     * @return List of Auction Entity ( json ).
     */
    @Override
    public List<AuctionEntity> readInputJsonFile(String inputFile) {
        StringBuilder inputJsonFileAsString = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(inputFile), StandardCharsets.UTF_8)) {
            stream.forEach(lineData -> inputJsonFileAsString.append(lineData).append("\n"));
            return Arrays.asList(objectMapper.readValue(inputJsonFileAsString.toString(), AuctionEntity[].class));
        } catch (JsonMappingException e) {
            LOG.error("Invalid JSON file  : {}", e.getMessage());
        } catch (IOException e) {
            LOG.error("Invalid Input file  : {}", e.getMessage());
        }
        return null;
    }
}
