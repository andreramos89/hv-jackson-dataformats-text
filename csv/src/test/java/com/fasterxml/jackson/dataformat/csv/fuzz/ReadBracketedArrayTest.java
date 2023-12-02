package com.fasterxml.jackson.dataformat.csv.fuzz;

import java.net.URL;

import com.fasterxml.jackson.annotation.*;

import com.fasterxml.jackson.databind.MappingIterator;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.ModuleTestBase;

public class ReadBracketedArrayTest extends ModuleTestBase
{
    @JsonPropertyOrder({"id", "title", "url", "score", "time", "comments", "author",
        "embeddings"
    })
    static class Article {
        public String id, title, author;
        public URL url;
        public int comments, score;
        public long time; // Unix time

        public double[] embeddings;
    }

    /*
    /**********************************************************************
    /* Test methods
    /**********************************************************************
     */

    private final CsvMapper MAPPER = mapperForCsv();

    public void testArrayWithBracketsRead() throws Exception
    {
        byte[] input = readResource("/data/story-100.csv");
        CsvSchema schema = MAPPER.schemaFor(Article.class)
                .withHeader()
                .withArrayElementSeparator(",");
        MappingIterator<Article> it = MAPPER.readerFor(Article.class)
                .with(schema)
                .readValues(input);

        Article first = it.nextValue();
        assertNotNull(first);
    }
}
