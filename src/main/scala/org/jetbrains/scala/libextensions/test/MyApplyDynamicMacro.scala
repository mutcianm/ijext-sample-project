package org.jetbrains.scala.libextensions.test

import scala.language.dynamics
import scala.language.experimental.macros
import scala.reflect.macros.whitebox

object MyApplyDynamicMacro extends Dynamic {
  def applyDynamic(name: String)(args: Any*): TestClassA[String] = macro impl

  def transformedMethod(i: Int, s: String): TestClassA[String] = new TestClassA[String](s)

  def impl(c: whitebox.Context)(name: c.Expr[String])(args: c.Expr[Any]*): c.Tree = {
    import c.universe._
    q"""_root_.org.jetbrains.scala.libextensions.test.MyApplyDynamicMacro.transformedMethod(1234, $name)"""
  }

}
