fun Int?.optint() : Unit {}
val Int?.optval : Unit get() = Unit

fun <T: Any, E> T.foo(<warning>x</warning> : E, y : A) : T   {
  y.plus(1)
  y plus 1
  y + 1.0

  <warning>this<warning>?.</warning>minus<T>(this)</warning>

  return this
}

class A

infix operator fun A.plus(<warning>a</warning> : Any) {

  1.foo()
  true.<error>foo</error>(<error><error>)</error></error>

  <warning>1</warning>
}

infix operator fun A.plus(<warning>a</warning> : Int) {
  <warning>1</warning>
}

fun <T> T.minus(<warning>t</warning> : T) : Int = 1

fun test() {
  val <warning>y</warning> = 1.abs
}
val Int.abs : Int
  get() = if (this > 0) this else -this;

<error>val <T> T.foo : T</error>

fun Int.foo() = this

// FILE: b.kt
//package null_safety

        fun parse(<warning>cmd</warning>: String): Command? { return null  }
        class Command() {
        //  fun equals(other : Any?) : Boolean
          val foo : Int = 0
        }

        <error>operator</error> fun Any.<warning>equals</warning>(<warning>other</warning> : Any?) : Boolean = true
        fun Any?.equals1(<warning>other</warning> : Any?) : Boolean = true
        fun Any.equals2(<warning>other</warning> : Any?) : Boolean = true

        fun main(<warning>args</warning>: Array<String>) {

            System.out.print(1)

            val command = parse("")

            command.foo

            command<error>.</error>equals(null)
            command?.equals(null)
            command.equals1(null)
            command?.equals1(null)

            val c = Command()
            <warning>c<warning>?.</warning>equals2(null)</warning>

            if (command == null) <warning>1</warning>
        }
