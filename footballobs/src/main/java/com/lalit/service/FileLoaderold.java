package com.lalit.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FileLoaderold {
    private String fileName;
    private List<String> teams;
    private String folder;

    public FileLoaderold(String folder) {
        this.folder = folder;
    }

    public String getFileName() {
        return fileName;
    }

    public void loadFile() throws IOException {
        System.out.println("=== FileLoaderv2 Debug Info ===");
        System.out.println("Current working directory: " + System.getProperty("user.dir"));
        System.out.println("Looking for folder: " + folder);
        System.out.println("Absolute path: " + Paths.get(folder).toAbsolutePath());
        System.out.println("Folder exists: " + Files.exists(Paths.get(folder)));

        Files.list(Paths.get(folder))
                .filter(Files::isRegularFile)
                .findFirst()
                .ifPresent(file -> {
                    try {
                        loadFile(file.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }

    private void loadFile(String fileName) throws Exception {
        this.fileName = fileName;
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(fileName);
        teams = mapper.readValue(file, new TypeReference<List<String>>() {
        });
    }

    public List<String> getTeams() {
        return teams;
    }
}
