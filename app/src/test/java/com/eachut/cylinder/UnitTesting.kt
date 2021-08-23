package com.eachut.cylinder

import com.eachut.cylinder.repository.MemberRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class UnitTesting {

    private lateinit var memberRepository: MemberRepository

    // Login Test
    @Test
    fun checkLogin() = runBlocking{
        memberRepository = MemberRepository()
        val response = memberRepository.checkMember("adminadmin","4321")
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }

    // Change Password Test
    @Test
    fun checkChangePassword() = runBlocking{
        memberRepository = MemberRepository()
        val response = memberRepository.changePassword("adminadmin","4321","1234")
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }
}