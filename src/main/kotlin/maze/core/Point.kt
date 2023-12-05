package maze.core

class Point(val x : Int, val y : Int) {

    operator fun plus(p : Point) : Point {
        return Point(this.x + p.x,this.y + p.y)
    }

    operator fun minus(p : Point) : Point {
        return Point(this.x - p.x,this.y - p.y)
    }

    operator fun times(i : Int) : Point{
        return Point(x*i,y*i)
    }

    operator fun div(i: Int) : Point{
        return Point(x/i,y/i)
    }

    operator fun component1() : Int = x
    operator fun component2() : Int = y

    override fun toString(): String {
        return "($x,$y)"
    }

    override fun equals(other: Any?): Boolean {
        return if(other is Point){
            return x == other.x && y == other.y
        }else{
            false
        }
    }

    companion object{
        fun Pair<Int,Int>.toPoint() : Point {
            return Point(this.first,this.second)
        }

        val DIRECTIONS = listOf(
            Point(-1,0),
            Point(1,0),
            Point(0,-1),
            Point(0,1))
    }
}