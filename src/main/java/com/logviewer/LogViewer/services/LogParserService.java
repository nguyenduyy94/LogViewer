package com.logviewer.LogViewer.services;

import com.logviewer.LogViewer.model.LogFile;
import com.logviewer.LogViewer.model.LogRecord;
import com.logviewer.LogViewer.model.LogRepository;
import com.logviewer.LogViewer.utils.SearchQuery.SearchQuery;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class LogParserService {

    public List<LogFile> listLogFiles(LogRepository logRepository, String suffix) {
        File folder = new File(logRepository.getAbsolutePath());
        File[] files = folder.listFiles();
        List<LogFile> logFiles = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(suffix)) {
                    LogFile logFile = new LogFile();
                    logFile.setAbsolutePath(file.getAbsolutePath());
                    logFiles.add(logFile);
                }
            }
        }
        return logFiles;

    }

    public List<LogRecord> searchLog(LogFile logFile, SearchQuery searchQuery) throws IOException {
        List<LogRecord> logRecords = new ArrayList<>();
        BufferedReader fileReader = new BufferedReader(new FileReader(logFile.getAbsolutePath()));
        String line = null;
        Long lineNumber = 0L;
        while ((line = fileReader.readLine()) != null) {
            lineNumber++;
            if (searchQuery.matchLine(line)) {
                LogRecord logRecord = new LogRecord();
                logRecord.setText(line);
                logRecord.setAbsolutePath(logFile.getAbsolutePath());
                logRecord.setLineNumber(lineNumber);
                logRecords.add(logRecord);
            }
        }
        return logRecords;
    }
}
