package maze.solver

import maze.core.IMaze
import maze.core.ISolvable
import maze.enums.Cell
import java.util.*
import kotlin.time.Duration
import kotlin.time.measureTime

class BFSSolver : ISolvable {

    private var t = Duration.ZERO

    override fun solve(maze: IMaze): List<Pair<Int, Int>> {
        val steps = mutableListOf<Pair<Int,Int>>()
        t = measureTime {
            steps.addAll(bfs(maze))
        }
        return steps
    }

    private data class Point(val p : Pair<Int,Int>,var parent : Point? = null)

    private fun bfs(maze: IMaze) : List<Pair<Int,Int>>{
        val visited = Array(maze.size().first){
            BooleanArray(maze.size().second){
                false
            }
        }
        val queue: Queue<Point> = LinkedList()
        queue.add(Point(maze.start()))
        while (queue.isNotEmpty()) {
            val current = queue.poll()
            if(current.p == maze.end()){
                return toSteps(current)
            }
            for (neighbor in neighbors(current.p,maze)) {
                val (x,y) = neighbor
                if (!visited[x][y]) {
                    visited[x][y] = true
                    queue.add(Point(neighbor,current))
                }
            }
        }
        return listOf()
    }

    private fun toSteps(p : Point) : List<Pair<Int,Int>>{
        val list = mutableListOf<Pair<Int,Int>>()
        list.add(p.p)
        var c = p.parent
        while (c?.parent != null){
            list.add(c.p)
            c = c.parent
        }
        return list
    }

    private fun neighbors(point : Pair<Int,Int>,maze: IMaze) : List<Pair<Int,Int>>{
        val list = mutableListOf<Pair<Int,Int>>()
        for(i in listOf(Pair(-1,0),Pair(1,0),Pair(0,-1),Pair(0,1))){
            val x = point.first + i.first
            val y = point.second + i.second
            if(x in 0..maze.size().first && y in 0..maze.size().second && maze[x,y] != Cell.WALL){
                list.add(Pair(x,y))
            }
        }
        return list
    }

    override fun time(): Duration {
        return t
    }
}