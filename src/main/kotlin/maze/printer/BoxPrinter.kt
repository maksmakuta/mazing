package maze.printer

import maze.core.IPrinter
import maze.core.Maze
import maze.enums.Cell

class BoxPrinter : IPrinter {

    private val mods = listOf(
        "\u001B[91m",
        "\u001B[0m"
    )

    private val chars = listOf(
        "─","│","○","●", // 0-3
        "┐","└","┘","┌", // 4-7
        "├","┤","┬","┴", // 8-11
        "┼"              // 12
    )

    override fun print(maze: Maze) {
        for(i in 0..<maze.width){
            for(j in 0..<maze.height){
                val cell = maze[i,j]
                val c = when(cell){
                    Cell.WALL  -> {
                        val arr = Array(3){
                            BooleanArray(3){
                                false
                            }
                        }
                        for(a in -1 .. 1){
                            for(b in -1 .. 1){
                                val x = i + a
                                val y = j + b
                                if(x in 0..<maze.width && y in 0..<maze.height){
                                    arr[a+1][b+1] = (maze[x,y] == Cell.WALL)
                                }
                            }
                        }
                        /**   0 1 2
                         *  0   0
                         *  1 0 0 0
                         *  2   0
                         */
                        if(arr[1][1] && arr[0][1] && arr[1][0] && arr[2][1] && arr[1][2]){
                            chars[12]
                        }else if(arr[1][0] && arr[1][1] && arr[1][2]) {
                            if (arr[0][1]){
                                chars[11]
                            }else if(arr[2][1]){
                                chars[10]
                            }else{
                                chars[0]
                            }
                        }else if(arr[0][1] && arr[1][1] && arr[2][1]) {
                            if (arr[1][0]){
                                chars[9]
                            }else if(arr[1][2]){
                                chars[8]
                            }else{
                                chars[1]
                            }
                        }else if(arr[1][1]){
                            if (arr[0][1] && arr[1][0]){
                                chars[6]
                            }else if (arr[1][0] && arr[2][1]){
                                chars[4]
                            }else if (arr[2][1] && arr[1][2]){
                                chars[7]
                            }else if (arr[1][2] && arr[0][1]){
                                chars[5]
                            }else if(arr[1][0] || arr[1][2]){
                                chars[0]
                            }else if(arr[0][1] || arr[2][1]){
                                chars[1]
                            }else{
                                " "
                            }
                        }else
                            " "
                    }
                    Cell.START -> chars[2]
                    Cell.EMPTY -> " "
                    Cell.END   -> chars[3]
                    Cell.PATH  -> {
                        val arr = Array(3){
                            BooleanArray(3){
                                false
                            }
                        }
                        for(a in -1 .. 1){
                            for(b in -1 .. 1){
                                val x = i + a
                                val y = j + b
                                if(x in 0..<maze.width && y in 0..<maze.height){
                                    arr[a+1][b+1] = (maze[x,y] == Cell.PATH)
                                }
                            }
                        }
                        /**   0 1 2
                         *  0   0
                         *  1 0 0 0
                         *  2   0
                         */
                        val tc = if(arr[1][1]){
                            if (arr[0][1] && arr[1][0]){
                                chars[6]
                            }else if (arr[1][0] && arr[2][1]){
                                chars[4]
                            }else if (arr[2][1] && arr[1][2]){
                                chars[7]
                            }else if (arr[1][2] && arr[0][1]){
                                chars[5]
                            }else if(arr[1][0] || arr[1][2]){
                                chars[0]
                            }else if(arr[0][1] || arr[2][1]){
                                chars[1]
                            }else{
                                " "
                            }
                        }else
                            " "
                        "${mods[0]}$tc${mods[1]}"
                    }
                }
                print(c)
            }
            println()
        }
    }
}