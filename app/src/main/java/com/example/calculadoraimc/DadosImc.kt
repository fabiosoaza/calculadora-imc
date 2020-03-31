package com.example.calculadoraimc

import android.os.Parcel
import android.os.Parcelable

data class DadosImc (var sexo:Int, var idade:Int, var altura:Double, var peso:Double) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(sexo)
        parcel.writeInt(idade)
        parcel.writeDouble(altura)
        parcel.writeDouble(peso)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DadosImc> {
        override fun createFromParcel(parcel: Parcel): DadosImc {
            return DadosImc(parcel)
        }

        override fun newArray(size: Int): Array<DadosImc?> {
            return arrayOfNulls(size)
        }
    }
}