package maze.core

class Size(val w : Int, val h : Int) {

    fun iterate(l : (Int,Int) -> Unit){

        for(y in 0 ..< h){
            for(x in 0 ..< w){
                l(x,y)
            }
        }
    }

    companion object{
        val SPLITERS = listOf(
            "x","..","..<","_"
        )

        fun parse(str : String) : Size{
            SPLITERS.forEach{
                if(str.contains(it)){
                    val t = str.split(it)
                    return Size(t.first().toInt(),t.last().toInt())
                }
            }
            println("no delimiter found")
            return Size(0,0)
        }
    }
}