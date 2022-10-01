package com.javatechie.controller.impl;

import com.javatechie.entity.Employee;
import com.javatechie.mapper.EmployeeMapper;
import com.javatechie.service.EmployeeService;
import com.javatechie.util.EmployeeBuilder;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class EmployeeControllerImplTest {
    //TODO: create the data Test generator class EmployeeBuilder
    private static final String ENDPOINT_URL = "/api/employee";

    @InjectMocks
    private EmployeeControllerImpl employeeController;
    @Mock
    private EmployeeService employeeService;
    @Mock
    private EmployeeMapper employeeMapper;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.employeeController).build();
    }

    @Test
    public void getAll() throws Exception {
        Mockito.when(employeeMapper.asDTOList(ArgumentMatchers.any())).thenReturn(EmployeeBuilder.getListDTO());

        Mockito.when(employeeService.findAll()).thenReturn(EmployeeBuilder.getListEntities());
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(employeeMapper.asDTO(ArgumentMatchers.any())).thenReturn(EmployeeBuilder.getDTO());

        Mockito.when(employeeService.findById(ArgumentMatchers.anyInt())).thenReturn(java.util.Optional.of(EmployeeBuilder.getEntity()));

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(employeeService, Mockito.times(1)).findById(1);
        Mockito.verifyNoMoreInteractions(employeeService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(employeeMapper.asEntity(ArgumentMatchers.any())).thenReturn(EmployeeBuilder.getEntity());
        Mockito.when(employeeService.save(ArgumentMatchers.any(Employee.class))).thenReturn(EmployeeBuilder.getEntity());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(EmployeeBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(employeeService, Mockito.times(1)).save(ArgumentMatchers.any(Employee.class));
        Mockito.verifyNoMoreInteractions(employeeService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(employeeMapper.asEntity(ArgumentMatchers.any())).thenReturn(EmployeeBuilder.getEntity());
        Mockito.when(employeeService.update(ArgumentMatchers.any(), ArgumentMatchers.anyInt())).thenReturn(EmployeeBuilder.getEntity());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(EmployeeBuilder.getDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(employeeService, Mockito.times(1)).update(ArgumentMatchers.any(Employee.class), ArgumentMatchers.anyInt());
        Mockito.verifyNoMoreInteractions(employeeService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(employeeService).deleteById(ArgumentMatchers.anyInt());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(employeeService, Mockito.times(1)).deleteById(Mockito.anyInt());
        Mockito.verifyNoMoreInteractions(employeeService);
    }

}