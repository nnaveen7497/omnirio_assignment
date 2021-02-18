package com.omnirio.userservice.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omnirio.userservice.model.Role;
import com.omnirio.userservice.model.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class UserResourceTest {

    @Autowired
    private UserResource userResource;

    private MockMvc mockMvc;
    private static Role customerRole;
    private static Role branchManagerRole;

    @BeforeAll
    public static void setData() {
        customerRole = new Role("1", "Customer", "ROLE_CUSTOMER");
        branchManagerRole = new Role("2", "Branch Manager", "ROLE_BRANCH_MANAGER");
    }

    @BeforeEach
    public void setup() {
        this.mockMvc = standaloneSetup(this.userResource).build();
    }

    @Test
    @DisplayName("Test to get all users")
    public void getAllUsersTest() throws Exception {
        User branchManager = new User();
        branchManager.setRole(branchManagerRole);
        branchManager.setUserId("abcd");
        branchManager.setUserName("Nishant Naveen");
        branchManager.setPhoneNumber("7895458975");
        branchManager.setGender("M");
        branchManager.setDateOfBirth(LocalDate.now());

        User customer = new User();
        customer.setRole(customerRole);
        customer.setUserId("efgh");
        customer.setUserName("Bibhakar Prakash");
        customer.setPhoneNumber("7878197225");
        customer.setGender("M");
        customer.setDateOfBirth(LocalDate.now());

        String uri = "/users";
        this.mockMvc.perform(get(uri)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].userId", is(branchManager.getUserId())))
                .andExpect(jsonPath("$[0].role.roleCode", is(branchManager.getRole().getRoleCode())))
                .andExpect(jsonPath("$[1].userId", is(customer.getUserId())))
                .andExpect(jsonPath("$[1].role.roleCode", is(customer.getRole().getRoleCode())))
                .andReturn();
    }

    @Test
    @DisplayName("Test to get user details by id")
    public void getUserDetailsByIdTest() throws Exception {
        User customer = new User();
        customer.setRole(customerRole);
        customer.setUserId("efgh");
        customer.setUserName("Bibhakar Prakash");
        customer.setPhoneNumber("7878197225");
        customer.setGender("M");
        customer.setDateOfBirth(LocalDate.now());

        String uri = "/users/" + customer.getUserId();
        this.mockMvc.perform(get(uri)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(customer.getUserId())))
                .andExpect(jsonPath("$.userName", is(customer.getUserName())))
                .andExpect(jsonPath("$.role.roleCode", is(customer.getRole().getRoleCode())))
                .andReturn();
    }

    @Test
    @DisplayName("Test to save user details")
    public void saveUserDetailsTest() throws Exception {
        User userDto = new User();
        userDto.setRoleCode(customerRole.getRoleCode());
        userDto.setUserId("xyz");
        userDto.setUserName("Bibhakar Prakash");
        userDto.setPhoneNumber("7878197225");
        userDto.setGender("M");
        userDto.setDateOfBirth(LocalDate.now());

        ObjectMapper mapper = new ObjectMapper();
        String requestBodyParam = mapper.writeValueAsString(userDto);

        String uri = "/users";
        RequestBuilder requestBuilder = post(uri)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBodyParam)
                .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId", is(userDto.getUserId())))
                .andExpect(jsonPath("$.userName", is(userDto.getUserName())))
                .andExpect(jsonPath("$.role.roleCode", is(userDto.getRoleCode())))
                .andReturn();
    }

}
