package com.nexteducation.dependencyTool.module.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExampleRepository extends JpaRepository<Object, Serializable> {

}
