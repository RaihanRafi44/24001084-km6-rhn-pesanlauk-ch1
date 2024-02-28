//Raihan Rafi Rizqullah

open class SideDishOrder {
    private val menuItems = listOf(
        Food(1,"Ayam Bakar", 50000),
        Food(2,"Ayam Goreng", 40000),
        Food(3,"Ayam Geprek", 40000),
        Food(4,"Kulit Ayam Crispy", 15000),
        Food(5,"Sate Usus Ayam", 5000)
    )

    fun run() {
        val selectedFood = getChooseFood() ?: return
        val paymentProcess = Payment()
        selectedFood.let { paymentProcess.inputPayment(it) } // Accessing the inputPayment method from the Payment class
        printDeliveryMethod()
        val selectedDeliveryMethod = getDeliveryMethod()
        selectedDeliveryMethod?.delivery(selectedFood)
    }

    private fun foodList() { // Function to display the list of food
        println("List Menu Makanan")
        println("-------------------------------------")
        menuItems.forEach { food ->
            println("${food.foodNumber}. ${food.foodName} = Rp ${food.foodPrice}")  // Displaying the food name and price
        }
        println("-------------------------------------")
    }

    private fun getChooseFood(): Food? { // Function to select the food
        foodList() // Displaying the list of food
        println("Pilih Menu Makanan : ")
        return try {
            val selectedListNumber = readLine()?.toInt()
            if (selectedListNumber != null && selectedListNumber in 1..menuItems.size) { // Checking if the selected number is within the range of the menu items
                val selectedFoodChoice = menuItems[selectedListNumber - 1] // Getting the selected food
                println("Kamu memilih menu $selectedListNumber")
                println("Nama menu : ${selectedFoodChoice.foodName}")
                println("Harga : Rp ${selectedFoodChoice.foodPrice}")
                selectedFoodChoice
            } else { // If the selected number is not within the range of the menu items
                println("Pilihan yang anda masukkan salah")
                getChooseFood()
            }
        } catch (e: NumberFormatException) { // Catching the exception if the input is not a number
            println("Pilihan yang anda masukkan salah")
            getChooseFood()
        }
    }

class Payment {
    fun inputPayment(selectedFood: Food) { // Function to input the payment of the selected food
        println("Masukkan pembayaran Anda: ")
        return try {
            val payment = readLine()?.toInt() ?: return
            if (payment < selectedFood.foodPrice) { // If the payment is less than the price of the selected food
                println("Maaf, pembayaran Anda gagal!")
                inputPayment(selectedFood)
            } else { // If the payment is equal to or greater than the price of the selected food
                println("Terima kasih, Anda berhasil memesan makanan")
            }
        } catch (e: NumberFormatException) { // Catching the exception if the input is not a number
            println("Maaf, pembayaran Anda gagal!")
            inputPayment(selectedFood)
        }
    }
}

private fun printDeliveryMethod() { // Function to print the delivery method options
    println("Pilih metode pengiriman : ")
    println("1. Take Away")
    println("2. Delivery")
}

interface DeliveryMethod {
    fun delivery(selectedFood: Food)
}

private fun getDeliveryMethod(): DeliveryMethod? {
    return try {
        when (readLine()?.toInt()) { // Checking the input for the delivery method
            1 -> TakeAway()
            2 -> Delivery()
            else -> { // If the input is not 1 or 2
                println("Pilihan yang anda masukkan salah")
                getDeliveryMethod()
            }
        }
    } catch (e: NumberFormatException) { // Catching the exception if the input is not a number
        println("Pilihan yang anda masukkan salah")
        getDeliveryMethod()
    }
}

class TakeAway : DeliveryMethod {
    override fun delivery(selectedFood: Food) {
        println("Anda memilih Take Away")
        println("Makananmu sedang dimasak (5 detik) .....")
        Thread.sleep(5000)
        println("Makananmu sudah siap. Silakan ambil di resto ya! (5 detik) .....")
        Thread.sleep(5000)
        println("Pesanan selesai (3 detik) ...")
        Thread.sleep(3000)
    }
}

class Delivery : DeliveryMethod {
    override fun delivery(selectedFood: Food) {
        println("Anda memilih Delivery")
        println("Makananmu sedang dimasak (5 detik) .....")
        Thread.sleep(5000)
        println("Makananmu sudah siap. Driver segera menuju tempatmu! (5 detik) .....")
        Thread.sleep(5000)
        println("Driver sampai! Pesanan selesai! (3 detik) ...")
        Thread.sleep(3000)
    }
}
}

fun main() {
    SideDishOrder().run()
}
