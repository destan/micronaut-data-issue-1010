package com.example

import javax.persistence.*

@Entity
@Table(name = "app_user")
class User(
    @Id
    @GeneratedValue
    var id: Long?,
    var username: String,
    var something: String,

    @ManyToMany
    var groups: Set<Group>
) {

    override fun toString(): String {
        return "User(username='$username')"
    }
}