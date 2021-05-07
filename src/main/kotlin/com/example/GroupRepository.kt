package com.example

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface GroupRepository: JpaRepository<Group, Long> {
}