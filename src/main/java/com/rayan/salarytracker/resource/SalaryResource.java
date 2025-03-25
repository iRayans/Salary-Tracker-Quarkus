package com.rayan.salarytracker.resource;

import com.rayan.salarytracker.core.exception.EntityAlreadyExistsException;
import com.rayan.salarytracker.core.exception.EntityInvalidArgumentsException;
import com.rayan.salarytracker.core.exception.EntityNotFoundException;
import com.rayan.salarytracker.dto.salary.SalaryInsertDTO;
import com.rayan.salarytracker.dto.salary.SalaryReadOnlyDTO;
import com.rayan.salarytracker.dto.salary.SalaryUpdateRequestDTO;
import com.rayan.salarytracker.service.impl.SalaryService;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.Year;
import java.util.List;

@Path(("/salaries"))
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class SalaryResource {

    @Inject
    SalaryService salaryService;

    @GET
    public Response getSalaries(@QueryParam("year") int year) throws EntityNotFoundException {
        if (year <= 0) {
            year = Year.now().getValue();
        }
        List<SalaryReadOnlyDTO> salary = salaryService.findAllSalaries(year);
        return Response.status(Response.Status.OK)
                .entity(salary)
                .build();
    }

    @POST
    public Response createSalary(SalaryInsertDTO salary) throws EntityInvalidArgumentsException, EntityNotFoundException, EntityAlreadyExistsException {
        SalaryReadOnlyDTO salaryReadOnlyDTO = salaryService.createSalary(salary);
        return Response.status((Response.Status.CREATED))
                .entity(salaryReadOnlyDTO)
                .build();
    }

    @GET
    @Path("/{salaryId}")
    public Response getSalaryById(@PathParam("salaryId") Long salaryId) throws EntityNotFoundException {
        SalaryReadOnlyDTO salaryReadOnlyDTO = salaryService.findSalaryById(salaryId);
        return Response.status((Response.Status.OK))
                .entity(salaryReadOnlyDTO)
                .build();
    }

    @PUT
    @Path("/{salaryId}")
    public Response updateSalary(@PathParam("salaryId") Long salaryId, SalaryUpdateRequestDTO salaryUpdateRequest) throws EntityNotFoundException {
        SalaryReadOnlyDTO salaryReadOnlyDTO = salaryService.updateSalary(salaryId, salaryUpdateRequest);
        return Response.status((Response.Status.OK))
                .entity(salaryReadOnlyDTO)
                .build();
    }

    @DELETE
    @Path("/{salaryId}")
    public Response deleteSalary(@PathParam("salaryId") Long salaryId) throws EntityNotFoundException {
        salaryService.deleteSalaryById(salaryId);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}

