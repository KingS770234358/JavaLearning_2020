package com.wq.AOPtest;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

@Aspect
public class IntroductionAspect {
    @DeclareParents(value="Developer", defaultImpl = Tester.class)
    public ITester itester;
}
