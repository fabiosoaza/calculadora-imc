package com.example.calculadoraimc

class CalculadoraImc() {

    fun calcular(peso: Double, altura: Double): Double{
        return peso / (altura * altura)
    }


}