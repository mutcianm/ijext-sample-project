package org.jetbrains.scala.libextensions.test

import org.jetbrains.plugins.scala.lang.psi.api.toplevel.typedef.ScTypeDefinition
import org.jetbrains.plugins.scala.lang.psi.impl.toplevel.typedef.SyntheticMembersInjector

class MySampleInjector extends SyntheticMembersInjector {
  override def injectFunctions(source: ScTypeDefinition): Seq[String] = {
    if (source.findAnnotationNoAliases("org.jetbrains.scala.libextensions.test.MySampleAnnotation") != null) {
      Seq("def myNewCoolMethod(a: Int): String = ???")
    } else {
      Seq.empty
    }
  }
}
