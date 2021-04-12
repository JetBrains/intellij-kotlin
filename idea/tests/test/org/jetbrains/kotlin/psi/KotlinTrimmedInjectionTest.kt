package org.jetbrains.kotlin.psi

import com.intellij.lang.Language
import com.intellij.lang.html.HTMLLanguage
import com.intellij.openapi.actionSystem.IdeActions
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.util.TextRange
import com.intellij.psi.injection.Injectable
import junit.framework.TestCase
import org.intellij.plugins.intelliLang.inject.InjectLanguageAction
import org.junit.internal.runners.JUnit38ClassRunner
import org.junit.runner.RunWith
import java.lang.IllegalArgumentException

@RunWith(JUnit38ClassRunner::class)
class KotlinTrimmedInjectionTest : AbstractInjectionTest() {

    override fun setUp() {
        super.setUp()
        myFixture.addFileToProject("kotlin/text/trims.kt", """
            package kotlin.text
            
            public fun String.trimIndent(): String = this
            public fun String.trimMargin(marginPrefix: String = "|"): String = this
        """.trimIndent())
    }

    fun testTrimIndentInJavaAnnotation() {
        doInjectionPresentTest(
            """
                import kotlin.text.trimIndent
    
                fun bar(a: Int) { 
                    Test.foo(""${'"'}
                    some trim indented code
                        is written here
                    no spaces before each line should appear<caret>
                    ""${'"'}.trimIndent())
                }
                """,
            javaText =
            """
                import org.intellij.lang.annotations.Language;
    
                public class Test {
                    public static void foo(@Language("HTML") String str) {}
                }
                """,
            languageId = HTMLLanguage.INSTANCE.id,
            unInjectShouldBePresent = false,
            shreds = null,
            injectedText = "some trim indented code\n" +
                    "    is written here\n" +
                    "no spaces before each line should appear"
        )
    }

    fun testTrimMarginInJavaAnnotation() {
        doInjectionPresentTest(
            """
                import kotlin.text.trimIndent
    
                fun bar(a: Int) { 
                    Test.foo(""${'"'}
                    > <html><caret>
                    > <body>
                    >   <div>
                    >       <h1>
                    >       ${"\$"}a
                    >       </h1>
                    >  </div>
                    > </body>
                    ></head>
                ""${'"'}.trimMargin(">"))
                }
                """,
            javaText =
            """
                import org.intellij.lang.annotations.Language;
    
                public class Test {
                    public static void foo(@Language("HTML") String str) {}
                }
                """,
            languageId = HTMLLanguage.INSTANCE.id,
            unInjectShouldBePresent = false,
            shreds = null,
            injectedText = " <html>\n" +
                    " <body>\n" +
                    "   <div>\n" +
                    "       <h1>\n" +
                    "       a\n" +
                    "       </h1>\n" +
                    "  </div>\n" +
                    " </body>\n" +
                    "</head>"
        )
    }

    fun testTrimIndentInKotlinAnnotation() {
        doInjectionPresentTest(
            mkFoo(
                """
                        <html>
                            <body>
                                        
                            </body>
                        </html><caret>
                    """,
                "HTML"
            ),
            languageId = HTMLLanguage.INSTANCE.id,
            unInjectShouldBePresent = false,
            shreds = null,
            injectedText = "<html>\n" +
                    "    <body>\n" +
                    "                \n" +
                    "    </body>\n" +
                    "</html>"
        )
    }

    fun testTempInjection() {
        myFixture.configureByText(
            "Foo.kt", """
                import kotlin.text.trimIndent
                
                fun foo() {
                    val toInject = ""${'"'}
                        <html>
                            <body><caret>
                                        
                            </body>
                        </html>
                    ""${'"'}.trimIndent()
                }
            """.trimIndent()
        )

        InjectLanguageAction.invokeImpl(project, editor, file, Injectable.fromLanguage(Language.findLanguageByID("HTML")))
        myFixture.checkResult(
            """
                import kotlin.text.trimIndent
                
                fun foo() {
                    val toInject = ""${'"'}
                        <html>
                            <body><caret>
                                        
                            </body>
                        </html>
                    ""${'"'}.trimIndent()
                }
            """.trimIndent()
        )

        TestCase.assertEquals(
            """
            <html>
                <body>
                            
                </body>
            </html>
        """.trimIndent(), myInjectionFixture.injectedElement?.containingFile?.text
        )

        val editorTestFixture = myInjectionFixture.openInFragmentEditor();
        TestCase.assertEquals(
            "<html>\n" +
                    "    <body>\n" +
                    "                \n" +
                    "    </body>\n" +
                    "</html>", editorTestFixture.file.text
        )

        editorTestFixture.type('\n')
        editorTestFixture.type("asss")
        editorTestFixture.type("s")

        myFixture.checkResult(
            "Foo.kt", """
                import kotlin.text.trimIndent

                fun foo() {
                    val toInject = ""${'"'}
                        <html>
                            <body>
                            assss

                            </body>
                        </html>
                    ""${'"'}.trimIndent()
                }
            """.trimIndent(), true
        )

        TestCase.assertEquals(
            "<html>\n" +
                    "    <body>\n" +
                    "    assss\n" +
                    "                \n" +
                    "    </body>\n" +
                    "</html>", editorTestFixture.file.text
        )
    }


    fun testNewLineInFragmentEditor() {

        myFixture.configureByText(
            "Foo.kt", mkFoo(
                """
                        <html>
                            <body><caret>
                                        
                            </body>
                        </html>
                    """,
                "HTML"
            )
        )

        val editorTestFixture = myInjectionFixture.openInFragmentEditor()
        val injectedFile = editorTestFixture.file

        TestCase.assertEquals(
            "<html>\n" +
                    "    <body>\n" +
                    "                \n" +
                    "    </body>\n" +
                    "</html>", injectedFile.text
        )

        editorTestFixture.type('\n')
        editorTestFixture.type("asss")
        editorTestFixture.type("s")

        myFixture.checkResult(
            "Foo.kt",
            mkFoo(
                """
                        <html>
                            <body>
                            assss
                
                            </body>
                        </html>
                    """,
                "HTML"
            ), true
        )

        TestCase.assertEquals(
            "<html>\n" +
                    "    <body>\n" +
                    "    assss\n" +
                    "                \n" +
                    "    </body>\n" +
                    "</html>", injectedFile.text
        )
    }

    fun testPutTagOnNewLineInFragmentEditor() {
        myFixture.configureByText(
            "Foo.kt", mkFoo(
                """
                        <html>
                            <body><caret></body>
                        </html>
                    """,
                "HTML"
            )
        )

        val editorTestFixture = myInjectionFixture.openInFragmentEditor()
        editorTestFixture.type('\n')

        myFixture.checkResult(
            "Foo.kt",
            mkFoo(
                """
                        <html>
                            <body>
                
                            </body>
                        </html>
                    """,
                "HTML"
            ), true
        )
    }


    fun testRemoveLineBreak() {
        myFixture.configureByText(
            "Foo.kt", mkFoo(
                """
                            <html>
                                <body>
                                <caret></body>
                            </html>
                        """, "HTML"
            )
        )

        val editorTestFixture = myInjectionFixture.openInFragmentEditor()
        editorTestFixture.performEditorAction(IdeActions.ACTION_EDITOR_BACKSPACE)

        myFixture.checkResult(
            "Foo.kt",
            mkFoo(
                """
                            <html>
                                <body></body>
                            </html>
                        """,
                "HTML"
            ), true
        )
    }

    fun mkFoo(literalText: String, lang: String): String {
        return """
                import kotlin.text.trimIndent
                import org.intellij.lang.annotations.Language;

                fun foo(@Language("$lang") html: String) {}

                fun bar(a: Int) {
                    foo(
                        ""${'"'}$literalText""${'"'}.trimIndent())
                }
                """.trimIndent()
    }


    fun testNewLineBetweenBodies() {
        myFixture.configureByText(
            "Foo.kt", mkFoo(
                """
                        <html>
                            <body><caret></body>
                        </html>
                    """,
                "HTML"
            )
        )

        myFixture.type("\n")
        myFixture.checkResult(mkFoo(
            """
                        <html>
                            <body>
                            
                            </body>
                        </html>
                    """, "HTML"
        )
       )

    }

    fun testOneLineTrimIndent() {
        myFixture.configureByText(
            "Foo.kt", mkFoo(""" <caret>""", "HTML")
        )

        val editorTestFixture = myInjectionFixture.openInFragmentEditor()
        editorTestFixture.performEditorAction(IdeActions.ACTION_EDITOR_ENTER)

        myFixture.checkResult(
            "Foo.kt", mkFoo(
                """
                """, "HTML"
            ), true
        )
    }

    fun testNewLineJSONAuthoComma() {
        myFixture.configureByText(
            "Foo.kt", mkFoo(
                """
                    {
                      "abc": 1<caret>
                    }
                """,
                "JSON"
            )
        )
        val editorTestFixture = myInjectionFixture.openInFragmentEditor()
        editorTestFixture.performEditorAction(IdeActions.ACTION_EDITOR_ENTER)
        myFixture.checkResult(
            mkFoo(
                """
                    {
                      "abc": 1,
                      
                    }
                """,
                "JSON"
            )
        )
    }

    fun testJsonWrapBrackets() {
        myFixture.configureByText(
            "Foo.kt", mkFoo(
                """
                    {
                        "abc": 1,
                        "fffdd": <caret>{
                            "select abc where ": [1, 2, 3]
                        }
                    }
                """,
                "JSON"
            )
        )
        val editorTestFixture = myInjectionFixture.openInFragmentEditor()

        val selectionRange = "{\n        \"select abc where \": [1, 2, 3]\n    }".let { toSelect ->
            TextRange.from(editorTestFixture.file.text.let { text ->
                text.indexOf(toSelect).also { if (it == -1) throw IllegalArgumentException("can't find text: '$toSelect' in '$text'") }
            }, toSelect.length)
        }

        editorTestFixture.editor.selectionModel.setSelection(selectionRange.startOffset, selectionRange.endOffset)
        editorTestFixture.type("boo")
        editorTestFixture.performEditorAction(IdeActions.ACTION_EDITOR_ENTER)
        myFixture.checkResult(
            mkFoo(
                """
                    {
                        "abc": 1,
                        "fffdd": boo
                      
                    }
                """,
                "JSON"
            )
        )
    }

    fun testJsonDeleteAll() {
        myFixture.configureByText(
            "Foo.kt", mkFoo(
                """
                    {
                        "abc": 1,
                        "fffdd": <caret>{
                            "select abc where ": [1, 2, 3]
                        }
                    }
                """,
                "JSON"
            )
        )
        val editorTestFixture = myInjectionFixture.openInFragmentEditor()
        editorTestFixture.performEditorAction(IdeActions.ACTION_SELECT_ALL)
        editorTestFixture.performEditorAction(IdeActions.ACTION_EDITOR_DELETE)

        myFixture.checkResult(
            mkFoo(
                """""",
                "JSON"
            )
        )
    }

    fun testJsonDeleteAllAndEmptyLines() {
        myFixture.configureByText(
            "Foo.kt", mkFoo(
                """
                    {
                        "abc": 1,
                        "fffdd": <caret>{
                            "select abc where ": [1, 2, 3]
                        }
                    }
                    
                """,
                "JSON"
            )
        )
        val editorTestFixture = myInjectionFixture.openInFragmentEditor()
        editorTestFixture.performEditorAction(IdeActions.ACTION_SELECT_ALL)
        editorTestFixture.performEditorAction(IdeActions.ACTION_EDITOR_DELETE)

        myFixture.checkResult(
            mkFoo(
                """""",
                "JSON"
            )
        )
    }

    fun testJsonDuplicateAll() {
        myFixture.configureByText(
            "Foo.kt", mkFoo(
                """
                    {
                        "abc": 1,
                        "fffdd": <caret>{
                            "select abc where ": [1, 2, 3]
                        }
                    }
                    

                """,
                "JSON"
            )
        )
        val editorTestFixture = myInjectionFixture.openInFragmentEditor()
        editorTestFixture.performEditorAction(IdeActions.ACTION_SELECT_ALL)
        editorTestFixture.performEditorAction(IdeActions.ACTION_EDITOR_DUPLICATE)

        myFixture.checkResult(
            mkFoo(
                """
                    {
                        "abc": 1,
                        "fffdd": {
                            "select abc where ": [1, 2, 3]
                        }
                    }
                    
                    {
                        "abc": 1,
                        "fffdd": {
                            "select abc where ": [1, 2, 3]
                        }
                    }
                

                """,
                "JSON"
            )
        )
    }


    fun testJsonReplaceAll() {
        myFixture.configureByText(
            "Foo.kt", mkFoo(
                """
                    {<caret>
                        "select abc where ": [1, 2, 3]
                    }
                """,
                "JSON"
            )
        )
        val editorTestFixture = myInjectionFixture.openInFragmentEditor()
        editorTestFixture.performEditorAction(IdeActions.ACTION_SELECT_ALL)
        WriteCommandAction.runWriteCommandAction(project) {
            editorTestFixture.editor.document.setText("{\"abc\": { \n \"def\": 1 \n}")
        }

        myFixture.checkResult(
            mkFoo(
                """
                    {"abc": { 
                     "def": 1 
                    }
                """,
                "JSON"
            )
        )
    }

}