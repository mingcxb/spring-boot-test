package com.cxb.swagger.test.repository;

import com.cxb.swagger.test.entity.Girl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.IntStream;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GirlRepositoryTest {

    @Autowired
    private GirlRepository girlRepository;

    @Test
    public void testAddGirl() {
        IntStream.range(1, 30).forEach(i-> {
            Girl girl = new Girl();
            girl.setName("girl" + i);
            girl.setAge(i + 20);
            girl.setCupSize("B");
            girlRepository.save(girl);
        });
    }

}