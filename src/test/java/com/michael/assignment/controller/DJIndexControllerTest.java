package com.michael.assignment.controller;

import com.michael.assignment.entity.DJIndex;
import com.michael.assignment.service.DJIndexService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author michaelwang on 2021-08-20
 */
public class DJIndexControllerTest {
    private MockMvc mockMvc;
    private Validator validator;
    @Mock
    private DJIndexService djIndexService;
    @InjectMocks
    private DJIndexController djIndexController;

    @Before
    public void setup() {
        djIndexController = new DJIndexController();
        validator = new Validator() {
            @Override
            public boolean supports(Class<?> aClass) {
                return false;
            }

            @Override
            public void validate(Object o, Errors errors) {

            }
        };
        mockMvc = MockMvcBuilders.standaloneSetup(djIndexController).setValidator(validator).build();
    }

    @Test
    public void testUploadPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/djindex/upload"))
                .andExpect(status().isOk())
                .andExpect(view().name("uploadpage"));
    }


    @Test
    public void testList() throws Exception {
        List<DJIndex> djIndexList = new ArrayList<>();
        djIndexList.add(new DJIndex());
        djIndexList.add(new DJIndex());
        when(djIndexService.getAllStocks()).thenReturn((List)djIndexList);
        mockMvc.perform(MockMvcRequestBuilders.get("/djindex/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("indices"))
                .andExpect(model().attribute("Indices", hasSize(2)));
    }


}
