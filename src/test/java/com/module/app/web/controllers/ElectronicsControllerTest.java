package com.module.app.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.module.app.web.entity.Electronics;
import com.module.app.web.repository.ElectronicsRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ElectronicsControllerTest {

    @InjectMocks
    private ElectronicsController electronicsController;

    @Mock
    private ElectronicsRepository electronicsRepository;

    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(electronicsController).build();
    }

    @Test
    public void getElectronicsTest() throws Exception {
        List<Electronics> electronicsList = mockElectronicsList();
        when(electronicsRepository.findAll()).thenReturn(electronicsList);

        mockMvc.perform(get("/electronics/list")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].tag", is("Sony")))
                .andExpect(jsonPath("$[0].category", is("TV")))
                .andExpect(jsonPath("$[0].details", is("[Detail1: XYZ, Detail2:JPG]")))
                .andExpect(jsonPath("$[0].broken", is(false)))
                .andExpect(jsonPath("$[0]comment", is("")))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void getElectronicByIdTest() throws Exception {
        Electronics electronics = mockElectronic();

        when(electronicsRepository.findById(1L)).thenReturn(java.util.Optional.of(electronics));

        mockMvc.perform(get("/electronics/get/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.tag", is("Sony")))
                .andExpect(jsonPath("$.category", is("TV")))
                .andExpect(jsonPath("$.details", is("[Detail1: XYZ, Detail2:JPG]")))
                .andExpect(jsonPath("$.broken", is(false)))
                .andExpect(jsonPath("$.comment", is("")))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }

    @Ignore
    @Test
    public void addElectronicsTest() {
    }

    @Test
    public void reportSomeDamageTest() throws Exception {
        Electronics electronics = mockElectronic();

        mockMvc.perform(put("/electronics/report/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(setDetailsAsString(electronics)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    private List<Electronics> mockElectronicsList() {
        return Collections.singletonList(mockElectronic());
    }

    private Electronics mockElectronic() {
        return Electronics.builder()
                .id(1)
                .tag("Sony")
                .category("TV")
                .details("[Detail1: XYZ, Detail2:JPG]")
                .broken(false)
                .comment("")
                .build();
    }

    private String setDetailsAsString(Electronics electronics) {
        try {
            return new ObjectMapper().writeValueAsString(electronics);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
