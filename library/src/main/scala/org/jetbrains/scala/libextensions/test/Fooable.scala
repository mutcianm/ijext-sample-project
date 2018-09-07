package org.jetbrains.scala.libextensions.test

import scala.language.experimental.macros
import scala.reflect.macros.whitebox

trait Fooable[T]
object Fooable {
  implicit def materialize[T]: Fooable[T] = macro impl[T]

  def impl[T: c.WeakTypeTag](c: whitebox.Context): c.Tree = {
    import c.universe._
    val tpe = c.weakTypeOf[T]
    q"new _root_.org.jetbrains.scala.libextensions.test.Fooable[$tpe] { def foo: $tpe = ??? }"
  }
}