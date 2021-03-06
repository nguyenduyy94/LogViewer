package com.logviewer.LogViewer.controllers;

import com.logviewer.LogViewer.model.LogFile;
import com.logviewer.LogViewer.model.LogRecord;
import com.logviewer.LogViewer.model.LogRepository;
import com.logviewer.LogViewer.model.SearchDTO;
import com.logviewer.LogViewer.repository.LogRepoRepository;
import com.logviewer.LogViewer.services.LogParserService;
import com.logviewer.LogViewer.utils.SearchQuery.SearchQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    LogParserService logParserService;

    @Autowired
    LogRepoRepository logRepoRepository;

    private final String DEFAULT_SUFFIX = ".log";

    @PostMapping("/search")
    public ResponseEntity<List<LogRecord>> search(@RequestBody SearchDTO searchDTO) throws IOException {
        Iterator<LogRepository> repositoryIterator = logRepoRepository.findAll().iterator();
        List<LogRecord> logRecords = new ArrayList<>();
        SearchQuery searchQuery = SearchQuery.parse(searchDTO.getQ());

        while (repositoryIterator.hasNext()) {
            LogRepository repository = repositoryIterator.next();
            List<LogFile> logFiles = logParserService.listLogFiles(repository, DEFAULT_SUFFIX);
            for (LogFile logFile : logFiles) {
                logRecords.addAll(logParserService.searchLog(logFile,searchQuery));
            }
        }

        return ResponseEntity.ok(logRecords);
    }
}
