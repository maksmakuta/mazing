package maze.app

class Point(val x : Int, val y : Int) {

    operator fun plus(p : Point) : Point {
        return Point(this.x + p.x,this.y + p.y)
    }

    operator fun minus(p : Point) : Point {
        return Point(this.x - p.x,this.y - p.y)
    }

    companion object{
        fun pointOf(x : Int,y : Int) : Point{
            return Point(x,y)
        }

        fun Pair<Int,Int>.toPoint() : Point{
            return Point(this.first,this.second)
        }
    }
}