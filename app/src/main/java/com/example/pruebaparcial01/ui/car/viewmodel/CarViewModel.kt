package com.example.pruebaparcial01.ui.car.viewmodel

import android.text.Spannable.Factory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pruebaparcial01.CarReviewerApplication
import com.example.pruebaparcial01.data.model.CarModel
import com.example.pruebaparcial01.repository.CarRepository

class CarViewModel(private val carRepository: CarRepository): ViewModel(){


    var brand = MutableLiveData("")
    var model = MutableLiveData("")
    var year = MutableLiveData("")
    var color = MutableLiveData("")
    var price = MutableLiveData("")
    var image = MutableLiveData("")
    var status = MutableLiveData("")

    fun getCars() = carRepository.getCars()

    fun addCar(car: CarModel) = carRepository.addCar(car)

    private fun validateData(): Boolean{
        when{
            brand.value.isNullOrEmpty() -> return false
            model.value.isNullOrEmpty() -> return false
            year.value.isNullOrEmpty() -> return false
            color.value.isNullOrEmpty() -> return false
            price.value.isNullOrEmpty() -> return false
            image.value.isNullOrEmpty() -> return false
        }
        return true
    }

    fun createCar(){

        if(!validateData()){
            status.value = CAR_WRONG
            return
        }
        val newCar = CarModel(
            brand.value.toString(),
            model.value.toString(),
            year.value.toString().toInt(),
            color.value.toString(),
            price.value.toString().toInt(),
            image.value.toString()
        )
        addCar(newCar)
        status.value = CAR_CREATED
    }


    fun setSelectedCar(car: CarModel){
        brand.value = car.brand
        model.value = car.model
        year.value = car.year.toString()
        color.value = car.color
        price.value = car.price.toString()
        image.value = car.image
    }

    fun clearData(){
        brand.value = ""
        model.value = ""
        year.value = ""
        color.value = ""
        price.value = ""
        image.value = ""
    }

    fun clearStatus(){
        status.value = CAR_BASE
    }

    companion object{
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as CarReviewerApplication
                CarViewModel(app.carRepository)
            }
        }

        const val CAR_BASE = ""
        const val CAR_CREATED = "Car created"
        const val CAR_WRONG = "Wrong data"
    }




}