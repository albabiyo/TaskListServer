package com.example.helloworld.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Task {

    private long id;
    private String des;


    public Task() {
        // Jackson deserialization
    }

    public Task(long id, String des) {
        this.id = id;
        this.des=des;
    }


    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getDes() {
        return des;
    }

    public void setId(long id)
    {
        this.id = id;
    }


}