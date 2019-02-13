package com.yseop.springboot.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

public interface ParentRepository extends JpaRepository<Parent, Long> {

    @RestResource
    @Query(value = "select i from Parent i " +
            "where (?#{principal.userId} = 1 or ?#{principal.userId} = 2) " +
            "and i.name like %:text% " +
            "and i.name not like '%foobarfoobar%' " +
            "and i.application.title like %:text%")
    Page<Parent> findByText(@Param("text") String text, Pageable pageable);
}
