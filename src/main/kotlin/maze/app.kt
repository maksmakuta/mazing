package maze

import maze.enums.Generator
import maze.enums.Printer
import maze.enums.Solver

fun main() {
    val app = MazeApp()
    app.setSize(40,40)
    app.withGenerator(Generator.IRP)
    app.withSolver(Solver.BFS)
    app.withPrinter(Printer.BOX)
    app.exec()
    println("GenTime : ${app.getGenTime()}")
    println("SolTime : ${app.getSolTime()}")
}


