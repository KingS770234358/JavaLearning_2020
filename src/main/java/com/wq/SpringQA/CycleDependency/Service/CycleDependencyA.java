package com.wq.SpringQA.CycleDependency.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
// @Scope("prototype")
public class CycleDependencyA {
    @Autowired
    private CycleDependencyB b;
//    public CycleDependencyA(CycleDependencyB b) {
//    }
    public void Cry(){
        System.out.println("A cry");
    }
}
