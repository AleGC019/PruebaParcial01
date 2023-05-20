package com.example.pruebaparcial01.repository

import com.example.pruebaparcial01.data.model.CarModel

class CarRepository (private val Car: MutableList<CarModel>){

    fun getCars() = Car

    fun addCar(newCar: CarModel){
        Car.add(newCar)
    }
}