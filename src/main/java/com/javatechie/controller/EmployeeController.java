package com.javatechie.controller;

import com.javatechie.dto.EmployeeDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "Employee API")
public interface EmployeeController {
    @ApiOperation("Add new data")
    public EmployeeDTO save(@RequestBody EmployeeDTO employee);

    @ApiOperation("Find by Id")
    public EmployeeDTO findById(@PathVariable("id") Integer id);

    @ApiOperation("Delete based on primary key")
    public void delete(@PathVariable("id") Integer id);

    @ApiOperation("Find all data")
    public List<EmployeeDTO> list();

    @ApiOperation("Pagination request")
    public Page<EmployeeDTO> pageQuery(Pageable pageable);

    @ApiOperation("Update one data")
    public EmployeeDTO update(@RequestBody EmployeeDTO dto, @PathVariable("id") Integer id);
}