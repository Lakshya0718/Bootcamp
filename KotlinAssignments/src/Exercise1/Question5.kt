package Exercise1

//Find common elements between two arrays.

fun main(args: Array<String>){

    val arr1 = intArrayOf(1, 2, 3, 4, 5, 8)
    val arr2 = intArrayOf(3, 4, 5, 6, 7, 8, 10, 11)
    for (i in arr1.indices) {
        for (element in arr2) {
            if (arr1[i] == element) {
                println(arr1[i])
            }
        }
    }
}