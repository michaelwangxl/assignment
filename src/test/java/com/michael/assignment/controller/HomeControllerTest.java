package com.michael.assignment.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.OptionalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @author michaelwang on 2021-08-20
 */
public class HomeControllerTest {
    private MockMvc mockMvc;

    private Validator validator;

    private HomeController homeController;

    @Before
    public void setup() {
        homeController = new HomeController();
        validator = new Validator() {
            @Override
            public boolean supports(Class<?> aClass) {
                return false;
            }

            @Override
            public void validate(Object o, Errors errors) {

            }
        };
        mockMvc = MockMvcBuilders.standaloneSetup(homeController).setValidator(validator).build();
    }

    @Test
    public void testHomePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }
}
