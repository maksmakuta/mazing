package maze

import maze.app.MazeApp
import maze.core.Point
import maze.enums.Generator
import maze.enums.Printer
import maze.enums.Solver
import kotlin.system.exitProcess

fun cli(args : Map<String,ArrayList<String>>){
    if(args.containsKey("help"))
        help()

    val app = MazeApp()
    app.setSize(100,100)
    app.withGenerator(Generator.DFS)
    app.withSolver(Solver.DFS)
    app.withPrinter(Printer.IMG)
    app.exec()
    println("GenTime : ${app.getGenTime()}")
    println("SolTime : ${app.getSolTime()}")
}

fun help(){
    println("mazing - console app for generating and solving mazes")
    println("\tusage: maze --size=20x20 --gen IRP --sol BFS --prt IMG --seed 56622fd6s1")
    println()
    println("\t --size \t\t set size: WxH ex: 20x30")
    println("\t --gen  \t\t set generator: RND, DFS, IRP")
    println("\t --sol  \t\t set solver: DFS, BFS")
    println("\t --prt  \t\t set printer: WIDE, BOX, IMG")
    println("\t --seed \t\t set seed")
    println()
    println("all algorithms details on project's site")
}

private fun parse(args : Array<String>) : Map<String,ArrayList<String>>{
    var params = arrayListOf<String>()
    val m = mutableMapOf<String,ArrayList<String>>()
    args.reversed().forEach { arg ->
        if(arg.startsWith('-')){
            val name = if(arg.contains('=')){
                val t = arg.split('=')
                params.add(t.last())
                if(t.first().startsWith("--")){
                    t.first().removePrefix("--")
                }else{
                    t.first().removePrefix("-")
                }
            }else if(arg.startsWith("--")){
                arg.removePrefix("--")
            }else{
                arg.removePrefix("-")
            }
            m[name] = params
            params = arrayListOf()
        }else{
            params.add(arg)
        }
    }
    return m
}

fun main(args : Array<String>) {
    //if(args.isNotEmpty())
        cli(parse(args))
    //else
    //   help()
}


