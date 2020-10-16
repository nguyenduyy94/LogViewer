package com.logviewer.LogViewer.controllers;

import com.logviewer.LogViewer.model.LogRepository;
import com.logviewer.LogViewer.repository.LogRepoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@RestController
@RequestMapping("/api/repos")
public class LogRepoController {

    @Autowired
    LogRepoRepository logRepoRepository;

    @GetMapping("")
    public ResponseEntity<List<LogRepository>> getLogReposoritories() {
        List<LogRepository> logRepositories = new ArrayList<>();
        logRepoRepository.findAll().forEach(new Consumer<LogRepository>() {
            @Override
            public void accept(LogRepository logRepository) {
                logRepositories.add(logRepository);
            }
        });
        return ResponseEntity.ok(logRepositories);
    }

    @PostMapping("/add")
    public ResponseEntity<LogRepository> addLogRepo(@RequestBody LogRepository logRepository) {
        LogRepository repo = logRepoRepository.save(logRepository);
        return ResponseEntity.ok(repo);
    }

    @GetMapping("/delete")
    public ResponseEntity<LogRepository> removeLogRepo(@RequestParam Long id) {
        LogRepository logRepository = logRepoRepository.findById(id).orElseThrow(() -> new RuntimeException("Repo " + id + " is not exixts"));
        logRepoRepository.delete(logRepository);
        return ResponseEntity.ok(logRepository);
    }

}
