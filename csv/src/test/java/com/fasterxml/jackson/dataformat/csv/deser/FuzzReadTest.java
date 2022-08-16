package com.fasterxml.jackson.dataformat.csv.deser;

import java.io.IOException;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;

/**
 * Collection of OSS-Fuzz found issues.
 */
public class FuzzReadTest extends StreamingCSVReadTest
{
    private final CsvMapper MAPPER = mapperForCsv();

    // https://bugs.chromium.org/p/oss-fuzz/issues/detail?id=50036
    public void testUTF8Decoding50036() throws Exception
    {
        byte[] INPUT = new byte[] { 0x20, (byte) 0xCD };
        try {
            MAPPER.readTree(INPUT);
            fail("Should not pass");
        } catch (IOException e) {
            verifyException(e, "End-of-input after first 1 byte");
            verifyException(e, "of a UTF-8 character");
        }
    }
}
