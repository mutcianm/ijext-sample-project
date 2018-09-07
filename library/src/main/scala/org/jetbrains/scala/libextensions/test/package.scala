package org.jetbrains.scala.libextensions

package object test {
  def genFoo[T](x: T)(implicit s: Fooable[T]): s.type = s
}
