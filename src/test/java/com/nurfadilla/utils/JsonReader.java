package com.nurfadilla.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonReader {

    public static File getJsonFile(String fileName) {
        return new File(
                "src/test/resources/testdata/" + fileName
        );
    }

    public static String readJson(String fileName) throws Exception {
        return new String(
                Files.readAllBytes(
                        Paths.get(
                                "src/test/resources/testdata/" + fileName
                        )
                )
        );
    }
}