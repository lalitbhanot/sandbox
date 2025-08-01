package com.lalit.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FileLoader {
    private String fileName;
    private List<String> teams;
    private String folder;

    public FileLoader(String folder) {
        this.folder = folder;
    }

    public String getFileName() {
        return fileName;
    }

    public void loadFile() throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        try {
            Thread.sleep(10000);
            // Look for all files in the classpath folder
            Resource[] resources = resolver.getResources("classpath:" + folder + "/*");

            if (resources.length == 0) {
                throw new IOException("No files found in classpath:" + folder);
            }

            // Take the first file found
            Resource resource = resources[0];
            loadFileFromResource(resource);

        } catch (Exception e) {
            throw new IOException("Error loading files from folder: " + folder, e);
        }
    }

    private void loadFileFromResource(Resource resource) throws Exception {
        this.fileName = resource.getFilename();
        ObjectMapper mapper = new ObjectMapper();

        try (InputStream inputStream = resource.getInputStream()) {
            teams = mapper.readValue(inputStream, new TypeReference<List<String>>() {});
        }
    }

    public List<String> getTeams() {
        return teams;
    }
}