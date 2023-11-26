package maze

import maze.enums.Generator
import maze.enums.Printer
import maze.enums.Solver

fun main() {
    val app = MazeApp()
    app.setSize(20,20)
    app.withGenerator(Generator.IRP)
    app.withSolver(Solver.DFS)
    app.withPrinter(Printer.WIDE)
    val t = app.exec()
    println("Time : $t")
}


