package com.huolala.core.model.request.mapTextSearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TextsearchV1AddrAlyRequest {
    private String key;
    private String pid;
    private String args;
    private String revision;
    private String searchId;
    private String address_analysis;
    private String debug;
}