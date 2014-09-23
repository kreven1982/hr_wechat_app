package com.cognizant.cdc.repository

import groovy.transform.CompileStatic
import groovy.transform.TypeChecked

@CompileStatic
@TypeChecked
enum DocumentNames {

    JOB("job"),
    SEQUENCE("sequence")

    private String collectionName
    private Map options
    private List<List<Map>> indexConfigs

    DocumentNames(String collectionName, List<List<Map>> indexConfigs = null, Map options = null) {
        this.collectionName = collectionName
        this.indexConfigs = indexConfigs
        this.options = options
    }

    public List<List<Map>> getIndexConfigs() {
        return indexConfigs
    }

    public String getName() {
        collectionName
    }

    public Map getOptions() {
        options
    }
}
