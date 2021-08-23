package com.eachut.cylinder.Object

import com.eachut.cylinder.entity.Member

object MemberList {
    var shortedMemberList = mutableListOf<Member>()

    fun setMemberList(memberList: MutableList<Member>){
        shortedMemberList = memberList
    }
    fun getMemberList():MutableList<Member>{
        return shortedMemberList
    }
}