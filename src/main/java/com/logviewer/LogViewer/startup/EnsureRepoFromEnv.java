package com.logviewer.LogViewer.startup;

import com.logviewer.LogViewer.model.LogRepository;
import com.logviewer.LogViewer.repository.LogRepoRepository;
import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class EnsureRepoFromEnv {

    @Autowired
    LogRepoRepository logRepoRepository;

    @PostConstruct
    public void init() {
        String defaultReposConfig = System.getProperty("repos", null);
        if (defaultReposConfig != null) {
            String[] paths = defaultReposConfig.split(",");
            for (String path : paths) {
                LogRepository repo = logRepoRepository.findById(path).orElse(null);
                if (repo == null) {
                    repo = new LogRepository();
                    repo.setAbsolutePath(path);
                    logRepoRepository.save(repo);
                }
            }
        }

    }
}
