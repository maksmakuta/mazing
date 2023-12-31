package maze.app

import maze.core.*
import maze.enums.Generator
import maze.enums.Printer
import maze.enums.Solver
import maze.generator.DFSGenerator
import maze.generator.KruskalGenerator
import maze.generator.PrimGenerator
import maze.generator.RandomGenerator
import maze.printer.BoxPrinter
import maze.printer.PNGPrinter
import maze.printer.SVGPrinter
import maze.printer.WidePrinter
import maze.solver.ASolver
import maze.solver.BFSSolver
import maze.solver.DFSSolver
import kotlin.random.Random
import kotlin.time.Duration

class MazeApp{

    private var size = Size(5,5)
    private var startPoint = Point(1,1)
    private var endPoint = Point(size.w-2,size.h-2)
    private var gen : IGenerator = RandomGenerator(size)
    private var sol : ISolver? = null
    private var prt : IPrinter = WidePrinter()

    fun setSize(w : Int,h : Int){
        size = Size(w+1,h+1)
        gen.size = size
        startPoint = Point(1,1)
        endPoint = Point(size.w-2,size.h-2)
    }

    fun setSize(s : Size){
        setSize(s.w,s.h)
    }

    fun withGenerator(generator: Generator){
        gen = when (generator){
            Generator.RND -> RandomGenerator(size)
            Generator.DFS -> DFSGenerator(size)
            Generator.IRP -> PrimGenerator(size)
            Generator.IRK -> KruskalGenerator(size)
        }
    }

    fun withSolver(solver: Solver) {
        sol = when (solver){
            Solver.BFS      -> BFSSolver()
            Solver.DFS      -> DFSSolver()
            Solver.Astar    -> ASolver()
        }
    }

    fun withPrinter(prnt : Printer){
        prt = when(prnt){
            Printer.BOX -> BoxPrinter()
            Printer.WIDE -> WidePrinter()
            Printer.PNG -> PNGPrinter()
            Printer.SVG -> SVGPrinter()
        }
    }

    private fun randSeed() : Long{
        return Random.nextLong()
    }

    fun setStart(point: Point){
        startPoint = point
    }

    fun setEnd(point: Point){
        endPoint = point
    }

    fun getGenTime() = gen.time()
    fun getSolTime() = sol?.time() ?: Duration.ZERO

    fun exec(seed : Long = randSeed()){
        println("seed : $seed")
        gen.setPoints(startPoint,endPoint)
        val m = gen.generate(seed)
        m.solve(sol)
        prt.print(m)
    }
}