package maze

import maze.app.MazeApp
import maze.core.Point
import maze.core.Size
import maze.enums.Generator
import maze.enums.Printer
import maze.enums.Solver

fun cli(args : Map<String,ArrayList<String>>){
    if(args.containsKey("help"))
        help()
    else {
        val app = MazeApp()
        if (args.containsKey("size")) {
            val size = Size.parse(args["size"]!!.first())
            app.setSize(size)
        } else {
            println("Using default size: 50x50")
            app.setSize(50, 50)
        }
        if (args.containsKey("gen")) {
            val g = args["gen"]!!.first()
            val generator = Generator.valueOf(g)
            app.withGenerator(generator)
        } else {
            println("Using default generator: DFS")
            app.withGenerator(Generator.DFS)
        }
        if (args.containsKey("sol")) {
            val g = args["sol"]!!.first()
            app.withSolver(Solver.valueOf(g))
        }
        if (args.containsKey("start")) {
            app.setStart(Point.parse(args["start"]!!.first()))
        }
        if (args.containsKey("end")) {
            app.setEnd(Point.parse(args["end"]!!.first()))
        }
        if (args.containsKey("prt")) {
            val g = args["prt"]!!.first()
            app.withPrinter(Printer.valueOf(g))
        } else {
            println("Using default printer: WIDE")
            app.withPrinter(Printer.WIDE)
        }
        println("Running...")
        app.exec()
        if (args.containsKey("time")) {
            println("Generation Time : ${app.getGenTime()}")
            println("Solving Time    : ${app.getSolTime()}")
        }
    }
}

fun help(){
    println("mazing - console app for generating and solving mazes")
    println()
    println("\tusage: maze [--args] {params}")
    println()
    println("\t --size \t\t set size: WxH ex: 20x30 or 26..75")
    println("\t --gen  \t\t set generator: RND, DFS, IRP")
    println("\t --sol  \t\t set solver: DFS, BFS")
    println("\t --prt  \t\t set printer: WIDE, BOX, PNG, SVG")
    println("\t --seed \t\t set seed")
    println("\t --start \t\t set start point: 5,9")
    println("\t --end \t\t set end point: 15,5")
    println()
    println("\t --time \t\t print time for generation and solving")
    println("\t --help \t\t show this message")
    println()
    println("all algorithms details on project's site : https://github.com/maksmakuta/mazing")
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
    if (args.isNotEmpty())
        cli(parse(args))
    else {
        val app = MazeApp()
        app.setSize(50, 50)
        app.withGenerator(Generator.IRK)
        app.withSolver(Solver.BFS)
        app.withPrinter(Printer.PNG)
        app.exec()
        println("gen   : ${app.getGenTime()}")
        println("solve : ${app.getSolTime()}")
    }
}
