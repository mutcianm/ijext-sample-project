package org.jetbrains.scala.libextensions.test

import org.jetbrains.plugins.scala.lang.macros.evaluator.{MacroImpl, MacroInvocationContext, ScalaMacroExpandable}
import org.jetbrains.plugins.scala.lang.psi.api.expr.{ScExpression, ScReferenceExpression}
import org.jetbrains.plugins.scala.lang.psi.api.statements.ScFunction
import org.jetbrains.plugins.scala.lang.psi.impl.ScalaPsiElementFactory

class MacroDynamicSupport extends ScalaMacroExpandable {
  override val boundMacro: Seq[MacroImpl] =
    MacroImpl("applyDynamic", "org.jetbrains.scala.libextensions.test.MyApplyDynamicMacro") :: Nil

  override def expandMacro(macros: ScFunction, context: MacroInvocationContext): Option[ScExpression] = {
    val MacroInvocationContext(mc, resolveResult) = context

    val nameArg = resolveResult.nameArgForDynamic match {
      case Some(name) => name
      case _ => return None
    }

    val invokedExprText = mc.getEffectiveInvokedExpr match {
      case ref: ScReferenceExpression if ref.refName == nameArg =>
        ref.qualifier.map(_.getText).getOrElse("")
      case expr => expr.getText // foo() may be interpreted as foo.applyDynamic("apply")()
    }

    val invokedWithDot = if (invokedExprText.isEmpty) "" else s"$invokedExprText."

    Some(ScalaPsiElementFactory.createExpressionWithContextFromText(s"""$invokedWithDot${nameArg}Dynamic(12, \"$nameArg\")""", mc, null))
  }
}
