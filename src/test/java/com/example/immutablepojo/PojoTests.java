package com.example.immutablepojo;

import org.junit.jupiter.api.Test;

import pl.pojo.tester.api.DefaultPackageFilter;
import pl.pojo.tester.api.assertion.Assertions;
import pl.pojo.tester.api.assertion.Method;

class PojoTests {

    @Test
    public void validate() {
        Assertions.assertPojoMethodsForAll(
                DefaultPackageFilter.forPackage(this.getClass().getPackageName()))
                .testing(Method.GETTER, Method.TO_STRING)
                .areWellImplemented();
    }
}
