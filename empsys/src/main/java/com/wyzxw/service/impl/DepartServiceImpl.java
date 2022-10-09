package com.wyzxw.service.impl;

import com.wyzxw.entity.Depart;
import com.wyzxw.mapper.DepartMapper;
import com.wyzxw.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartServiceImpl implements DepartService {
    @Autowired
    private DepartMapper departMapper;
    @Override
    public List<Depart> getDepartLsit() {
        return departMapper.selectByExample(null);
    }
}
