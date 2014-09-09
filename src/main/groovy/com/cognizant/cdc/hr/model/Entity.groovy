package com.cognizant.cdc.hr.model

interface Entity<T> {
    public Map toDBMap()
    public T fromDBMap(Map map)
}