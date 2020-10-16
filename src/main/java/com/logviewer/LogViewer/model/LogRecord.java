package com.logviewer.LogViewer.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class LogRecord {
    @Id @GeneratedValue
    Long id;

    String text;

    String absolutePath;

    Long lineNumber;
}
