package org.jetbrains.scala.libextensions.test

import org.jetbrains.plugins.scala.lang.macros.evaluator.{MacroContext, MacroImpl, ScalaMacroTypeable}
import org.jetbrains.plugins.scala.lang.psi.api.statements.ScFunction
import org.jetbrains.plugins.scala.lang.psi.impl.ScalaPsiElementFactory
import org.jetbrains.plugins.scala.lang.psi.types.{ScParameterizedType, ScType}

class MacroFooableSupport extends ScalaMacroTypeable {
  override val boundMacro: Seq[MacroImpl] =
    MacroImpl("materialize", "org.jetbrains.scala.libextensions.test.Fooable") :: Nil

  override def checkMacro(macros: ScFunction, context: MacroContext): Option[ScType] = {
    val tpe = context.expectedType match {
      case Some(tp:ScParameterizedType) => tp.typeArguments.headOption.map(_.canonicalText)
      case _ => return None
    }
    val txt = s"_root_.org.jetbrains.scala.libextensions.test.Fooable[$tpe] { def foo: $tpe }"
    ScalaPsiElementFactory.createTypeFromText(txt, context.place, null)
  }
}
