package com.logviewer.LogViewer.repository;

import com.logviewer.LogViewer.model.LogRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepoRepository extends PagingAndSortingRepository<LogRepository, String> {
}
