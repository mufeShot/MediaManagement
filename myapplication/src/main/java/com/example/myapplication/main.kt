package com.example.myapplication

import android.util.Log
import kotlin.random.Random
import kotlin.Comparable
import java.io.IOException

interface Sizeable{
    fun size() : Int
}

class ManagementClass (val managementName: String, var numberOfClients : Int) : Sizeable{
    init {
        numberOfClients = 0;
    }

    public var ClientList : MutableList<ClientsClass> = mutableListOf()

    fun addClient(client : ClientsClass){
        ClientList.add(client)
        numberOfClients++
    }

    override fun toString(): String {
        return "Management $managementName has $numberOfClients number of clients."
    }

    fun printClients(){
        println(this.ClientList)
    }

    override fun size(): Int {
        return ClientList.size
    }
}

class AgeException(val y : String) : Exception(y){
    override fun toString(): String {
        return "Year $y is not allowed!"
    }
}

class ClientsClass (val name:String, val surname:String, var age:Int, var wage:Double, var channel:ChannelClass) : Comparable<ClientsClass>{
    //val je readonly , var lahko spremenis
    init {
        if(age < 15) throw AgeException(age.toString())
    }

    override fun toString(): String{
        return "Name: $name Surname: $surname Age: $age Wage: $wage \n"
    }

    override fun compareTo( other: ClientsClass) : Int {
        return wage.compareTo(other.wage)
    }
}

class ChannelClass(val channelName:String) {
    override fun toString() : String{
        return "$channelName"
    }
}

fun rand(start: Int, end: Int): Int {
    require(start <= end) { "Illegal Argument" }
    return (start..end).random()
}

fun getRandom(min: Int, max: Int): Double {
    require(min < max) { "Invalid range [$min, $max]" }
    val number = min + Math.random() * (max - min)
    val number3digits:Double = String.format("%.3f", number).toDouble()
    val number2digits:Double = String.format("%.2f", number3digits).toDouble()
    val solution:Double = String.format("%.1f", number2digits).toDouble()
    return solution
}

fun generate(management : ManagementClass){
    val names = arrayOf<String>("Aljaz", "Blaz", "Zan", "Tjan", "Iztok", "Domen", "Ziga", "Uros", "Soraya", "Jasa")
    val surnames = arrayOf<String>("Fruhvirt", "Balazic", "Perkic", "Kazar", "Rebrica")
    val channelName = arrayOf<String>("Yurr", "Briss", "Sissks", "Klisss", "Stress", "Relaxx")

    val start1 = 17
    val end1 = 22
    val start2 = 756
    val end2 = 1500

    for (i in 1..3){
        val randomAge = rand(start1,end1)
        val randomWage = getRandom(start2, end2)
        var client_1 = ClientsClass(names.random(), surnames.random(), randomAge, randomWage, ChannelClass(channelName.random()))
        management.addClient(client_1)
    }
}



fun main() {
    println("Generating")
    var management = ManagementClass("Aljaz's Man", 0)
    generate(management)
    management.printClients()
    println("<--------Urejeno--------> \n${management.ClientList.sorted()}");

    try {
        management.addClient(ClientsClass("Test", "Test", 13, 15.3, ChannelClass("testChan")))
        //dodaj se en object z age under 15 da probas
    }catch (e:AgeException) {
        println("Age needs to be above 14")
    }

    try {
        val data = 15 / 0 //throw exception
    } catch (e: ArithmeticException) {
        println("Integer divided by 0!")
    }

    try {
        val index = 7
        var indexlist = listOf<Int>(1, 2, 3, 4, 5)
        if (index > indexlist.size) throw IndexOutOfBoundsException()
    } catch (e: IndexOutOfBoundsException) {
        println(e)
    }
}