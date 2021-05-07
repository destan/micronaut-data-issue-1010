package com.example

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "app_group")
class Group(
    @Id
    @GeneratedValue
    var id: Long?,
    var name: String,

) {
    override fun toString(): String {
        return "Group(name='$name')"
    }
}