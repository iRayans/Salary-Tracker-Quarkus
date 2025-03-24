package com.rayan.salarytracker.resource;

import com.rayan.salarytracker.core.exception.EntityNotFoundException;
import com.rayan.salarytracker.dto.expense.ExpenseInsertDTO;
import com.rayan.salarytracker.dto.expense.ExpenseReadOnlyDTO;

import com.rayan.salarytracker.service.impl.ExpenseService;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


import java.util.List;

@Path(("/expenses"))
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class ExpenseResource {

    @Inject
    ExpenseService expenseService;

    @GET
    @Path("/{salaryId}")
    public Response getExpenses(@PathParam("salaryId") Long salaryId) {
        List<ExpenseReadOnlyDTO> expenseReadOnlyDTO = expenseService.getAllExpenses(salaryId);
        return Response.status(Response.Status.OK).entity(expenseReadOnlyDTO).build();
    }

    @POST
    @Path("/{salaryId}")
    public Response addExpense(@PathParam("salaryId")Long salaryId, ExpenseInsertDTO expenseInsertDTO) throws EntityNotFoundException {
        ExpenseReadOnlyDTO expenseReadOnlyDTO = expenseService.createExpense(expenseInsertDTO, salaryId);
        System.out.println(expenseInsertDTO + " ********");
        return Response.status(Response.Status.CREATED)
                .entity(expenseReadOnlyDTO)
                .build();
    }

    @GET
    @Path("/{salaryId}/{expenseId}")
    public Response getExpense(@PathParam("salaryId") Long salaryId , @PathParam("expenseId") Long expenseId) throws EntityNotFoundException, NotAuthorizedException {
        ExpenseReadOnlyDTO expenseReadOnlyDTO = expenseService.getExpenseById(salaryId,expenseId);
        return Response.status(Response.Status.OK).entity(expenseReadOnlyDTO).build();
    }
}

