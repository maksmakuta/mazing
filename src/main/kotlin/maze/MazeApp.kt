package maze

import maze.core.AGenerator
import maze.core.IPrinter
import maze.core.ISolvable
import maze.enums.Generator
import maze.enums.Printer
import maze.enums.Solver
import maze.generator.DFSGenerator
import maze.generator.PrimGenerator
import maze.generator.RandomGenerator
import maze.printer.BoxPrinter
import maze.printer.WidePrinter
import maze.solver.BFSSolver
import maze.solver.DFSSolver
import maze.solver.StarSolver
import maze.solver.WalkerSolver
import kotlin.random.Random
import kotlin.time.Duration

class MazeApp{

    private var size = Pair(5,5)
    private var gen : AGenerator = RandomGenerator(size)
    private var sol : ISolvable? = null
    private var prt : IPrinter = WidePrinter()

    fun setSize(w : Int,h : Int){
        size = Pair(w+1,h+1)
        gen.size = size
    }

    fun withGenerator(generator: Generator){
        gen = when (generator){
            Generator.RND -> RandomGenerator(size)
            Generator.DFS -> DFSGenerator(size)
            Generator.IRP -> PrimGenerator(size)
        }
    }

    fun withSolver(solver: Solver) {
        sol = when (solver){
            Solver.RND      -> WalkerSolver()
            Solver.Astar    -> StarSolver()
            Solver.BFS      -> BFSSolver()
            Solver.DFS      -> DFSSolver()
        }
    }

    fun withPrinter(prnt : Printer){
        prt = when(prnt){
            Printer.BOX -> BoxPrinter()
            Printer.WIDE -> WidePrinter()
        }
    }

    private fun randSeed() : Long{
        return Random.nextLong()
    }

    fun getGenTime() = gen.time()
    fun getSolTime() = sol?.time() ?: Duration.ZERO

    fun exec(seed : Long = randSeed()){
        println("seed : $seed")
        val m = gen.generate(seed)
        m.solve(sol)
        prt.print(m)
    }
}