// "Safe delete 'Marker'" "false"
// COMPILER_ARGUMENTS: -Xopt-in=kotlin.RequiresOptIn -Xuse-experimental=test.Marker
// WITH_RUNTIME
// ACTION: Rename file to Marker.kt
// TOOL: org.jetbrains.kotlin.idea.inspections.UnusedSymbolInspection

package test

@RequiresOptIn
annotation class <caret>Marker
