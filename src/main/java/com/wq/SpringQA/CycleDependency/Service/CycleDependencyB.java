package com.wq.SpringQA.CycleDependency.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
// @Scope("prototype")
public class CycleDependencyB {
    @Autowired
    private CycleDependencyA a;
//    public CycleDependencyB(CycleDependencyC c){
//    }
}
