package com.eachut.cylinder

import com.eachut.cylinder.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class UnitTesting {

    private lateinit var userRepository: UserRepository

    // Login Test
    @Test
    fun checkLogin() = runBlocking{
        userRepository = UserRepository()
        val response = userRepository.checkUser("adminadmin","4321")
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }

    // Change Password Test
    @Test
    fun checkChangePassword() = runBlocking{
        userRepository = UserRepository()
        val response = userRepository.changePassword("adminadmin","4321","1234")
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }
}