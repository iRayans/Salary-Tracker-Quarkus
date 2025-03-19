package com.rayan.salarytracker.resource;

import com.rayan.salarytracker.core.exception.EntityNotFoundException;
import com.rayan.salarytracker.dto.salary.SalaryReadOnlyDTO;
import com.rayan.salarytracker.model.Salary;
import com.rayan.salarytracker.service.SalaryService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path(("/salaries"))
public class SalaryResource {

    @Inject
    SalaryService salaryService;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getSalary(@PathParam("id") Long id) throws EntityNotFoundException {
    List<SalaryReadOnlyDTO> salary = salaryService.findById(id);
        return Response.status(Response.Status.OK)
                .entity(salary)
                .build();
    }
}

