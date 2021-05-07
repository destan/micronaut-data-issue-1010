package com.example

import io.micronaut.core.annotation.Introspected

@Introspected
class SomeDto(var username: String, var something: String) {
    override fun toString(): String {
        return "SomeDto(username='$username', something='$something')"
    }
}