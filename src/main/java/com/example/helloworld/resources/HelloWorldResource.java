package com.example.helloworld.resources;

import com.example.helloworld.core.Task;
import com.google.common.base.Optional;
import com.codahale.metrics.annotation.Timed;
import java.net.*;
import javax.ws.rs.core.UriInfo;

import javax.ws.rs.*;
import java.io.File;
import javax.ws.rs.core.MediaType;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Path("/Tasks")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    private static List<Task> tasks;
    private static Integer counter_Tasks;


    
    public HelloWorldResource() {
        counter_Tasks=0;
        tasks=new ArrayList<Task>();
    }
    @POST
    @Timed
    public Response createTask(Task task, @Context UriInfo uriInfo)
    {
        int id = counter_Tasks++;
        task.setId(id);
        tasks.add(task);
	URI createdURI = URI.create(uriInfo.getBaseUri().toString() + "/Tasks/" + id);
        return Response
                .status(Status.CREATED)
                .contentLocation(createdURI)
                .entity(task)
                .build();
    }
    @GET @Path("/{id}")
    @Timed
    public Task getSingleTask(@PathParam("id") String id)
    {
        int i = Integer.parseInt(id);
        if (i>tasks.size()) {
            return null;
        }
        else
        {
            return tasks.get(i);
        }
    }
    @GET
    @Timed
    public Collection<Task> getTasks() {
        return tasks;
    }
    @DELETE @Path("/{id}")
    @Timed
    public Task deleteTask(@PathParam("id") String id)
    {
        int i = Integer.parseInt(id);
        if (i>tasks.size()) {
            return null;
        }
        else
        {
            Task delTask= tasks.get(i);
            tasks.remove(i);
            return delTask ;
        }
    }
}