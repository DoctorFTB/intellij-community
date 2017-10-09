/*
 * Copyright 2000-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jetbrains.uast.java

import com.intellij.psi.PsiImportStatementBase
import org.jetbrains.uast.JvmDeclarationUElement
import org.jetbrains.uast.UElement
import org.jetbrains.uast.UImportStatement

class JavaUImportStatement(
        override val psi: PsiImportStatementBase, 
        override val uastParent: UElement?
) : UImportStatement, JvmDeclarationUElement {
    override val isOnDemand: Boolean
        get() = psi.isOnDemand
    override val importReference by lz { psi.importReference?.let { JavaDumbUElement(it, this, it.qualifiedName) } }
    override fun resolve() = psi.resolve()
}