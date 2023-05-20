package com.example.pruebaparcial01

import com.example.pruebaparcial01.data.cars
import com.example.pruebaparcial01.repository.CarRepository

class CarReviewerApplication {

    val carRepository: CarRepository by lazy {
        CarRepository(cars)
    }

}