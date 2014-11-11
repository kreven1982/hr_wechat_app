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

    USER("user", [
            [[ userName: 1 ], [ unique: true ]],
            [[ token: 1 ]]
    ]),
    SEQUENCE("sequence"),
    PROFILE("profile", [
            [[ name: 1, mobile: 1 ], [ unique:  true ]]
    ]),
    ATTACHMENT("attachment")

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
