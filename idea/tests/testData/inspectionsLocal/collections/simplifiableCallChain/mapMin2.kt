// API_VERSION: 1.4
// WITH_RUNTIME
// PROBLEM: none

data class OrderItem(val name: String, val price: Double, val count: Int)

fun main() {
    val order = listOf<OrderItem>(
        OrderItem("Cake", price = 10.0, count = 1),
        OrderItem("Coffee", price = 2.5, count = 3),
        OrderItem("Tea", price = 1.5, count = 2)
    )

    val min = order.map<caret> { it.price }.min()
}