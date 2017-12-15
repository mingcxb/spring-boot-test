package com.cxb.swagger.test.controller;

import com.cxb.swagger.test.entity.Girl;
import com.cxb.swagger.test.service.GirlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "Girl操作相关接口")
@RestController
@RequestMapping("girl")
public class GirlController {

    @Autowired
    private GirlService girlService;

    @ApiOperation(value="分页查询Girl", notes="test")
    @GetMapping("girls")
    public Page<Girl> findAll(@ApiParam @RequestParam(value = "page", defaultValue = "1") Integer page,
                              @ApiParam @RequestParam(value = "size", defaultValue = "5") Integer size) {
        PageRequest pageRequest = new PageRequest(page - 1 ,size);
        Page<Girl> user = girlService.findUser(pageRequest);
        return user;
    }

    @PostMapping("add")
    public Girl  addGirl(@RequestBody Girl girl) {
        return girlService.addGril(girl);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateGirl(@PathVariable Integer id, @RequestBody Girl girl){
        girlService.update(id, girl);
        return new ResponseEntity("Product updated successfully", HttpStatus.OK);
    }


    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteGirl(@PathVariable Integer id){
        girlService.deleteGirl(id);
        return new ResponseEntity("Product deleted successfully", HttpStatus.OK);

    }
}
