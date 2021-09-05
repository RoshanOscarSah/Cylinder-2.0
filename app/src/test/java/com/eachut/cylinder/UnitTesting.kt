package com.eachut.cylinder

import com.eachut.cylinder.entity.*
import com.eachut.cylinder.repository.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class UnitTesting {

    private lateinit var memberRepository: MemberRepository
    private lateinit var companyRepository: CompanyRepository
    private lateinit var resellerRepository: ResellerRepository
    private lateinit var companyStockRepository: CompanyStockRepository
    private lateinit var notificationRepository: NotificationRepository
    private lateinit var resellerStockRepository: ResellerStockRepository
    private lateinit var scheduleExtraWorkRepository: ScheduleExtraWorkRepository
    private lateinit var stockRepository: StockRepository

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

    // Add Company Test
    @Test
    fun checkAddnewCompany() = runBlocking {
        companyRepository = CompanyRepository()
        val company = Company(company_fullname = "Loop", cylinder_name = "Mechi", address = "Kathmandu", phone_number = "9814028312")
        val response = companyRepository.newCompany(company)
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }

    // Add Reseller Test
    @Test
    fun checkAddnewReseller() = runBlocking {
        resellerRepository = ResellerRepository()
        val reseller = Reseller(reseller_fullname = "Sabin Chapagain", pasal_name = "Loop", address = "Damak", phone_number = "9814028312", rateforReseller = "1500")
        val response = resellerRepository.addNewReseller(reseller)
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }

    // Add Member Test
    @Test
    fun checkAddMember() = runBlocking {
        memberRepository = MemberRepository()
        val member = Member(Firstname = "Sabin", Lastname = "Chapagain", Phonenumber = "548211545", Username = "csabin1", Address = "Damak", Status = "Employee", Comission= "50")
        val response = memberRepository.addnewmemberadmin(member)
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }

    // Add Company Stock
    @Test
    fun checkAddCompanyStock() = runBlocking {
        companyStockRepository = CompanyStockRepository()
        val companyStock = CompanyStock(CompanyReceiptNo = "5845", Gas_state = "Full", Regular_Prima = 5, Leak_Kamakhya = 8, Sold_Suvidha = 9, SendOrReceive = "Send", Amount = "58450", Remarks = "Testing" )
        val response = companyStockRepository.addCompanyStock(companyStock)
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }

    // Add Notification
    @Test
    fun checkAddNotification() = runBlocking {
        notificationRepository = NotificationRepository()
        val notificationHistory = NotificationHistory(Title = "Testing", L1 = "Test1", L2 = "Test2", L3 = "Test3", R1 = "Test4", R2 = "Test5", Action = "Notify")
        val response = notificationRepository.addNotification(notificationHistory)
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }

    // Add Reseller Stock
    @Test
    fun checkAddResellerStock() = runBlocking {
        resellerStockRepository = ResellerStockRepository()
        val resellerStock = ResellerStock(ResellerReceiptNo = "5845", Gas_state = "Full", Regular_Prima = 5, Leak_Kamakhya = 8, Sold_Suvidha = 9, SendOrReceive = "Send", Amount = "58450", Remarks = "Testing")
        val response = resellerStockRepository.addResellerStock(resellerStock)
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }

    // Add Schedule Extra Work
    @Test
    fun checkAddExtraWorkSchedule() = runBlocking {
        scheduleExtraWorkRepository = ScheduleExtraWorkRepository()
        val scheduleExtraWork = ScheduleExtraWork(scheduledDate = "25 October", scheduledTime = "5:15", subject = "New Stock added", message = "New Stock Added in the list", acceptedBy = "Sabin", createdAt = "June 15", scheduledBy = "Unish" )
        val response = scheduleExtraWorkRepository.addExtraWorkSchedule(scheduleExtraWork)
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }

    // Get All Member
    @Test
    fun checkGetAllMember() = runBlocking {
        memberRepository = MemberRepository()
        val response = memberRepository.allmemberList()
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }

    // Get All Company
    @Test
    fun checkGetAllCompany() = runBlocking {
        companyRepository = CompanyRepository()
        val response = companyRepository.allCompanyList()
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }

    // Get All Reseller
    @Test
    fun checkGetAllReseller() = runBlocking {
        resellerRepository = ResellerRepository()
        val response = resellerRepository.allresellerList()
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }

    // Get Single Reseller
    @Test
    fun checkGetSingleReseller() = runBlocking {
        resellerRepository = ResellerRepository()
        val response = resellerRepository.resellerList("60ed6ce16cf3d811b03bdf95")
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }

    // Get Total Reseller
    @Test
    fun checkTotalReseller() = runBlocking {
        resellerRepository = ResellerRepository()
        val response = resellerRepository.totalreseller()
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }

    // Get Single Reseller Stock
    @Test
    fun checkGetSingleResellerStock() = runBlocking {
        resellerStockRepository = ResellerStockRepository()
        val response = resellerStockRepository.singleresellerStockList("60ed6ce16cf3d811b03bdf95")
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }

    // Get Notification History
    @Test
    fun checkGetNotificationHistory() = runBlocking {
        notificationRepository = NotificationRepository()
        val response = notificationRepository.notificationHistoryList()
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }

    // Get Extra Work Schedule
    @Test
    fun checkGetExtraWorkSchedule() = runBlocking {
        scheduleExtraWorkRepository = ScheduleExtraWorkRepository()
        val response = scheduleExtraWorkRepository.getExtraWorkSchedule()
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }

    // Get Stock
    @Test
    fun checkGetStock() = runBlocking {
        stockRepository = StockRepository()
        val response = stockRepository.viewStock()
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }

    // Get Gas Cylinder
    @Test
    fun checkGetGasCylinder() = runBlocking {
        stockRepository = StockRepository()
        val response = stockRepository.gascylindersold()
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }

    // Get Best Selling
    @Test
    fun checkGetBestSelling() = runBlocking {
        stockRepository = StockRepository()
        val response = stockRepository.bestSelling()
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }

}