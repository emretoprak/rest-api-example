package com.restapi;

import com.restapi.authentication.SecurityRoles;
import com.restapi.domain.TestDTO;
import com.restapi.exceptions.BusinessException;
import com.restapi.responser.HttpResponse;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class Test {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(SecurityRoles.USER)
    public TestDTO getTest() {
        TestDTO testVo = new TestDTO();
        testVo.setnumber(1);
        testVo.setorder("Welcome");
        return testVo;
    }

    @GET
    @Path("/businessexception")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(SecurityRoles.USER)
    public TestDTO businessException() {
        throw new BusinessException("Business Error");
    }

    @GET
    @Path("/admin")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(SecurityRoles.ADMIN)
    public TestDTO adminRoleTest() {
        TestDTO testVo = new TestDTO();
        testVo.setnumber(1);
        testVo.setorder("Admin Test");
        return testVo;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(SecurityRoles.USER)
    public Response putTest() {
        return HttpResponse.getOK();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(SecurityRoles.USER)
    public Response postTest() {
        return HttpResponse.getCreatedOK();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(SecurityRoles.USER)
    public Response deleteTest() {
        return HttpResponse.getOK();
    }
}