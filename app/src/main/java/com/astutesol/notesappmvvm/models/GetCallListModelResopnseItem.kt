package com.astutesol.notesappmvvm.models

data class GetCallListModelResopnseItem(
    val id: Int,
    val name: String,
    val number: String,
    val price: String,
    val quantity: String,
    val type: Int,


    ) {
    val Nname: String
        get() = "Name : $name"

    val Nnumber: String
        get() = "Number : $number"

    val Pprice: String
        get() = "Price : $price"

    val Qquantity: String
        get() = "Quantity : $quantity"

    val typeString: String
        get() = type.toString()
}