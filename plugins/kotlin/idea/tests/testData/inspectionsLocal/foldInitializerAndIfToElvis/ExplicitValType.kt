// WITH_STDLIB
fun foo(): String? = null

fun bar() {
    val v: String? = foo()
    <caret>if (v == null) throw Exception()
    v.length
}