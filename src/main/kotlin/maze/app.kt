package maze

import maze.enums.Generator
import maze.enums.Printer
import maze.enums.Solver
import kotlin.random.Random

fun main() {
    val seed = Random.nextLong()
    val app = MazeApp()
    app.setSize(200,200)
    app.withGenerator(Generator.IRP)
    app.withSolver(Solver.BFS)
    app.withPrinter(Printer.IMG)
    app.exec(seed)
    //println("GenTime : ${app.getGenTime()}")
    //println("SolTime : ${app.getSolTime()}")
}


