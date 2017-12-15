package com.cxb.swagger.test.service;

import com.cxb.swagger.test.entity.Girl;
import com.cxb.swagger.test.repository.GirlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GirlService {

    @Autowired
    private GirlRepository girlRepository;

    public Page<Girl> findUser(Pageable pageable) {
        return girlRepository.findAll(pageable);
    }

    public Girl addGril(Girl girl) {
        return girlRepository.save(girl);
    }

    public void update(Integer id, Girl girl) {
        Girl girl1 = girlRepository.findOne(id);
        girl1.setCupSize(girl.getCupSize());
        girl1.setName(girl.getName());
        girl1.setAge(girl.getAge());
    }

    public void deleteGirl(Integer id) {
        girlRepository.delete(id);
    }
}
