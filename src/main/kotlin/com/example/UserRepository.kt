package com.example

import io.micronaut.data.annotation.Join
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface UserRepository: JpaRepository<User, Long> {

    @Join("groups", type = Join.Type.LEFT_FETCH)
    override fun findAll(): MutableList<User>

    //Works
    //fun list(): MutableList<SomeDto>

    //Works
    //@Join("groups", type = Join.Type.LEFT)
    //fun list(): MutableList<SomeDto>

    // Doesn't work with or without @Join annotation
    //@Join("groups", type = Join.Type.LEFT)
    //fun list(): MutableList<SomeDtoWithGroup>

    //@Query("SELECT u FROM User u LEFT JOIN FETCH u.groups")
    @Query("SELECT new com.example.SomeDtoWithGroup(u.username, u.something, u.groups) FROM User u LEFT JOIN u.groups g")
    //@Query("SELECT new com.example.SomeDtoWithGroup(u.username, u.something, u.groups) FROM User u LEFT JOIN FETCH u.groups g")
    fun list(): MutableList<SomeDtoWithGroup>

}