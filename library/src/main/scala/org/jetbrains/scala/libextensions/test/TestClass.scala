package org.jetbrains.scala.libextensions.test

class TestClassA[T](t: T) {
  override def toString: String = s"${getClass.getSimpleName}: ${t.toString}"
}
class TestClassB[T](t: T) extends TestClassA[T](t: T)

