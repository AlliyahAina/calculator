import kotlin.math.log
import kotlin.math.log10
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.system.exitProcess

class Operators{
    fun divide(a: Double, b: Double): Double {
        var ans = 0.0
        if (b == 0.00){
            println("Cannot divide by 0")
        } else{
            ans = a / b
        }
        return ans
    }
    fun add(a: Double, b: Double): Double {
        return a + b
    }
    fun subtract(a: Double, b: Double): Double {
        return a - b
    }
    fun multiply(a: Double, b: Double): Double {
        return a * b
    }
    fun modulo(a: Double, b: Double): Double {
        return a % b
    }
    fun power(a: Double, b: Double): Double {
        return a.pow(b)
    }
    fun logarithmic(a: Double, b: Double):Double {
        return log(a,b)
    }
}
class OperatorsForOneValue{
    fun percentage(a: Double): Double {
        return a/100
    }
    fun squareRoot(a: Double): Double {
        return sqrt(a)
    }
    fun factorial(a: Double): Double{
        return if (a== 0.0||a==1.0){
            1.0
        } else{
            a*factorial(a-1)
        }
    }
    fun logarithmic(a:Double): Double{
        return log10(a)
    }
}

fun main(){
    val twoInput = Operators()
    val oneInput = OperatorsForOneValue()
    var values: List<String>
    val history: MutableList<String> = mutableListOf()
    var operator: String
    var num1 = - 1.0
    var num2: Double
    var answer = 0.0
    while(true){
        val input = readln()

        if (input.isEmpty())continue
        else if (input.startsWith("/")){
            when (input){
                "/hi", "/hello" -> println("HELL0")
                "/x", "/exit" -> break
                "/history" -> printHistory(history)
                else -> println("Unknown command")
            }
        } else{
            values = input.split(" ")

            if (values.size < 2){
                println("Invalid input./n Expected: value operator/n Expected: value operator value")
            }
            else if (values.size == 2 ){
                operator = values[1]
                num1 = if (num1 < 0.0){
                    values[0].toDoubleOrNull() ?:throw IllegalArgumentException("Invalid Input: ${values[0]}")
                } else{
                    answer
                }
                answer = when (operator){
                    "%" -> oneInput.percentage(num1)
                    "|" -> oneInput.squareRoot(num1)
                    "!" -> oneInput.factorial(num1)
                    "log" -> oneInput.logarithmic(num1)
                    else -> isOperatorOrIsCommand(history, operator)
                }
                print(answer)
                history.add("$num1 $operator = $answer")
            }
            else if (values.size == 3){
                operator = values[1]
                num1 = if (num1 < 0.0){
                    values[0].toDoubleOrNull() ?:throw IllegalArgumentException("Invalid Input: ${values[0]}")
                } else{
                    answer
                }
                num2 = values[2].toDoubleOrNull() ?:throw IllegalArgumentException("Invalid Input: ${values[2]}")
                answer = when (operator){
                    "+" -> twoInput.add(num1,num2)
                    "-" -> twoInput.subtract(num1,num2)
                    "*" -> twoInput.multiply(num1,num2)
                    "/" -> twoInput.divide(num1,num2)
                    "mod" -> twoInput.modulo(num1,num2)
                    "^" -> twoInput.power(num1,num2)
                    "log" -> twoInput.logarithmic(num1, num2)
                    else -> isOperatorOrIsCommand(history, operator)
                }
                print(answer)
                history.add("$num1 $operator $num2 = $answer")
            }
            num1 = answer
        }
    }
    println("Adios")
}

fun printHistory(a:MutableList<String>){
    println("\nHistory: ")
    for (words in a){
        println(words)
    }
}

fun isOperatorOrIsCommand(a:MutableList<String>, b:String): Double {
    try{
        if (b.startsWith("/")){
            when (b){
                "/hi", "/hello" -> println("HELL0")
                "/history" -> printHistory(a)
                "/x", "/exit" -> exitProcess(0)
                else -> println("Unknown command")
            }
        }
    }
    catch(e: IllegalArgumentException){
        throw IllegalArgumentException("INVALID OPERATOR: $b")
    }
    return 0.0
}