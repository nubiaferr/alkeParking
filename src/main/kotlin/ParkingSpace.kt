import java.util.*

data class ParkingSpace(
    var vehicle: Vehicle
) {
    //calcula quanto tempo geral ficou estacionado
    fun getParkedTime() =
        ((Calendar.getInstance().timeInMillis - vehicle.checkInTime.timeInMillis)/ 60000.00).toLong()


    //retorna o valor a ser pago em caso de sucesso
    fun checkOutVehicle() : Int {
        val finalValue = calculateValue()
        onSuccess(finalValue)
        return finalValue
    }

    private fun calculateValue(): Int {
        val parkedTime = getParkedTime()
        var fee = vehicle.type.startingRate
        val discount = vehicle.discountCard?.let { true } ?: false

        //verifica se passou de 2h
        if (parkedTime > 120.00) {
            val extraTime = parkedTime - 120.00
            fee += ((extraTime / 15.00) * 5).toInt()
        }

        return calculateDiscount(discount, fee)
    }

    private fun calculateDiscount(hasDiscount: Boolean, fee: Int) : Int {
        return if (hasDiscount) {
            (fee - (fee * 0.15).toInt())
        } else {
            fee
        }
    }

    fun onSuccess(fee: Int) {
        println("Thanks for checking out, your fee is R$$fee")
    }

    fun onError() {
        println("Checkout error")
    }

}