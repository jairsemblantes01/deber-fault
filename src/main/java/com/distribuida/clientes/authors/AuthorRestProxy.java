package com.distribuida.clientes.authors;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//@RegisterRestClient(baseUri="http://localhost:8080")
@RegisterRestClient(configKey = "author")
public interface AuthorRestProxy {

    @GET
    @Path("/{id}")
    @Timeout(value = 500)
    @Retry(maxRetries = 2, delay = 400L)
    AuthorsCliente findById(@PathParam("id") Long id);

    @GET
    List<AuthorsCliente> findAll();

    @POST
    @Timeout(value = 500)
    void insert(AuthorsCliente obj);

    @PUT
    @Path("/{id}")
    @Timeout(value = 500)
    void update(AuthorsCliente obj, @PathParam("id") Long id);

    @DELETE
    @Timeout(value = 500)
    @Path("/{id}")
    void delete( @PathParam("id") Long id );
}
