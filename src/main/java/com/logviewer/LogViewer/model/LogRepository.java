package com.logviewer.LogViewer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LogRepository {

    @Id @GeneratedValue
    Long id;

    String absolute_path;
}
