package com.example

import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.runtime.server.event.ServerStartupEvent
import javax.inject.Singleton
import javax.transaction.Transactional

@Singleton
@Transactional
open class StartupListener(
    private val userRepository: UserRepository,
    private val groupRepository: GroupRepository
): ApplicationEventListener<ServerStartupEvent?> {

    override fun onApplicationEvent(event: ServerStartupEvent?) {
        val group1 = groupRepository.save(Group(null, "Group 1"))
        val group2 = groupRepository.save(Group(null, "Group 2"))

        val user1 = userRepository.save(User(null, "john", "blabla", setOf(group1, group2)))
        val user2 = userRepository.save(User(null, "jane", "blabla", setOf()))
        println("all saved")

        println(userRepository.findAll())
        println(userRepository.list())
    }
}