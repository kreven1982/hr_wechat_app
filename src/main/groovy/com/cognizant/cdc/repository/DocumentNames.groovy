package com.cognizant.cdc.repository

import groovy.transform.CompileStatic
import groovy.transform.TypeChecked

@CompileStatic
@TypeChecked
enum DocumentNames {

    JOB("job", [
                [[locations: 1]],
                [[experienceFrom: 1]],
                [[experienceTo: 1]],
                [[type: 1]],
                [[diploma: 1]],
                [[keywords: 1]]
    ]),

    USER("user"),
    SEQUENCE("sequence"),
    PROFILE("profile")

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
