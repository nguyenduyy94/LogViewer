package com.logviewer.LogViewer.model;

import lombok.Data;

@Data
public class SearchDTO {
    String q;

    Long limit = 20L;

    
}
