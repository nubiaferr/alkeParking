class Parking(val vehicles: MutableSet<Vehicle>){
    private var parkingInformation: Pair<Int, Int> = Pair(0, 0)
    private var totalEarning = 0
    private var totalVehiclesCheckedOut = 0

    //adiciona caso ainda tenha vaga
    fun addVehicle(vehicle: Vehicle): Boolean {
        return if (vehicles.size < 20) vehicles.add(vehicle) else false
    }

    //caso tenha adicionado, mensagem de boas vindas
    fun greetingVehicle(vehicles: List<Vehicle>) {
        vehicles.forEach {
            if (addVehicle(it)) println("Welcome to AlkeParking")
            else println("Error, no more spaces or duplicated plate")
        }
    }

    //remove e faz a conta final com parkingspace
    fun remove(vehicle: Vehicle) {
        val parkingSpace = ParkingSpace(vehicle)

        //acha atraves da placa
        //se a placa estiver la, faz checkout, caso nao, leva ao erro
        val vehicleToRemove = vehicles.find { it.plate == vehicle.plate }
        vehicleToRemove?.let {
            setBalanceInformation(parkingSpace.checkOutVehicle())
            vehicles.remove(vehicleToRemove)
        } ?: parkingSpace.onError()
    }

    //soma valor e total de carros que fizeram checkout
    private fun setBalanceInformation(checkOutVehicle: Int) {
        totalEarning += checkOutVehicle
        totalVehiclesCheckedOut++
        parkingInformation = Pair(totalEarning, totalVehiclesCheckedOut)
    }

    //imprime o balanço
    fun printBalanceInformation() {
        println("${parkingInformation.second} " +
                "vehicles have checked out and have earnings of $${parkingInformation.first}")
    }

    //imprime as placas
    fun printPlates() {
        vehicles.map { println(it.plate) }
    }

}