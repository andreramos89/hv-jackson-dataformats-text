package com.fasterxml.jackson.dataformat.csv.deser;

import com.fasterxml.jackson.core.StreamReadFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;

public class FastParserStreamingCSVReadTest extends StreamingCSVReadTest {
    private final CsvMapper FAST_MAPPER = CsvMapper.builder()
            .enable(StreamReadFeature.USE_FAST_DOUBLE_PARSER)
            .build();

    @Override
    protected CsvMapper csvMapper() {
        return FAST_MAPPER;
    }
}