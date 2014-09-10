package com.cognizant.cdc.management.model

interface Entity<T> {
    public Map toDBMap()
    public T fromDBMap(Map map)
}