package maze

import maze.app.MazeApp
import maze.enums.Generator
import maze.enums.Printer
import maze.enums.Solver

fun main(){
    val app = MazeApp()
    app.setSize(20,20)
    app.withGenerator(Generator.RND)
    app.withSolver(Solver.DFS)
    app.withPrinter(Printer.WIDE)
    app.exec()
    println("GenTime : ${app.getGenTime()}")
    println("SolTime : ${app.getSolTime()}")
}

fun cli(args : Map<String,ArrayList<String>>){
    println("CLI is not working")
}

private fun parse(args : Array<String>) : Map<String,ArrayList<String>>{
    val m = mutableMapOf<String,ArrayList<String>>()
    return m
}

fun main(args : Array<String>) {
    if(args.isEmpty()){
        main()
    }else{
        cli(parse(args))
    }
}


