package com.cognizant.cdc.model

interface Entity<T> {
    public Map toDBMap()
    public void fromDBMap(Map map)
    public Map toRepresentationMap()
}