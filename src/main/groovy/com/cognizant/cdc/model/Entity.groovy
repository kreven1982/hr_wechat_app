package com.cognizant.cdc.model

interface Entity<T> {
    public Map toDBMap()
    public T fromDBMap(Map map)
    public Map toRepresentationMap()
}