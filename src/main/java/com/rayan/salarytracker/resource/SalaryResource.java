package com.rayan.salarytracker.resource;

import com.rayan.salarytracker.core.exception.EntityInvalidArgumentsException;
import com.rayan.salarytracker.core.exception.EntityNotFoundException;
import com.rayan.salarytracker.dto.salary.SalaryInsertDTO;
import com.rayan.salarytracker.dto.salary.SalaryReadOnlyDTO;
import com.rayan.salarytracker.repository.UserRepository;
import com.rayan.salarytracker.service.SalaryService;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.List;

@Path(("/salaries"))
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class SalaryResource {

    @Inject
    SalaryService salaryService;

    @GET
    @Path("/{id}")
    public Response getSalary(@PathParam("id") Long id) throws EntityNotFoundException {
    List<SalaryReadOnlyDTO> salary = salaryService.findById(id);
        return Response.status(Response.Status.OK)
                .entity(salary)
                .build();
    }

    @POST
    public Response createSalary(SalaryInsertDTO salary) throws EntityInvalidArgumentsException, EntityNotFoundException {
        SalaryReadOnlyDTO salaryReadOnlyDTO = salaryService.createSalary(salary);
        return Response.status((Response.Status.CREATED))
                .entity(salaryReadOnlyDTO)
                .build();
    }
}

