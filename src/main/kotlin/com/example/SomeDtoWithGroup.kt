package com.example

import io.micronaut.core.annotation.Introspected

@Introspected
class SomeDtoWithGroup(var username: String, var something: String, var groups: Set<Group>) {
    override fun toString(): String {
        return "SomeDtoWithGroup(username='$username', something='$something', groups=$groups)"
    }
}