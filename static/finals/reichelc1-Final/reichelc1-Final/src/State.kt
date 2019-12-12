import java.util.ArrayDeque
import kotlin.math.max
import java.util.ArrayList

class State constructor(n: Int){
    var size = n
    var board = arrayOf<Array<Int>>()
    var numqueens = 0 //convenience
    var locations = arrayListOf<Pair<Int, Int>>() // (x, y)
    var validmoves = arrayListOf(arrayListOf<Int>())


    init {
        //empty board
        for (row in 0..(size - 1)) {
            var array = arrayOf<Int>()
            for (col in 0..(size - 1)) {
                array  += 0
            }
            board += array
        }

        //Initialize for valid moves per column
        validmoves.removeAt(0)
        for(col in 0..(size-1)){
            validmoves.add(arrayListOf<Int>())
            for(row in 0..(size-1)){
                validmoves[col].add(row)
            }
        }
    }

    fun printBoard() {
        for (row in board) {
            for (col in row) {
                print("$col ")
            }
            println()
        }
        println()
    }

    fun valid(): Boolean{
        checkDiag()
        if(checkRow() && checkCol() && checkDiag())
            return true
        return false
    }

    fun checkRow(): Boolean {
        for (row in 0..(size-1)){
            var inRow = false
            for(col in 0..(size-1)){
                if (board[row][col] == 1) {
                    if (inRow)
                        return false
                    else
                        inRow = true
                }
            }
        }
        return true
    }

    fun checkCol(): Boolean {
        for (col in 0..(size-1)){
            var inCol = false
            for(row in 0..(size-1)){
                if (board[row][col] == 1) {
                    if (inCol)
                        return false
                    else
                        inCol = true
                }
            }
        }
        return true
    }

    fun checkDiag(): Boolean{
        for(queen in locations){
            var x: Int
            var y: Int
            var diag = false

            //down right diagonal
            if(queen.first <= queen.second){
                x = 0
                y = queen.second - queen.first
            }
            else{
                x = queen.first - queen.second
                y = 0
            }
            while((x < size ) && (y < size)){
                if(board[x++][y++] == 1){
                    if(diag)
                        return false
                    else
                        diag = true
                }
            }

            diag = false

            //down left diag
            if((size - 1) - queen.first >= queen.second ){
                x = (size -1) - (((size - 1)  - queen.first) - queen.second)
                y = 0
            }
            else{
                x = size - 1
                y = queen.second - ((size - 1) - queen.first)
            }
            while((y < size) && (x >= 0)){
                if(board[x--][y++] == 1){
                    if(diag)
                        return false
                    else
                        diag = true
                }
            }
        }
        return true
    }

    fun clone(template: State){
        numqueens = template.numqueens
        size = template.size //board size

        for(pair in template.locations){ //copy locations
            locations.add(Pair(pair.first, pair.second))
        }

        for(row in 0..(size-1)){    //copy board state
            for(col in 0..(size-1)){
                board[row][col] = template.board[row][col]
            }
        }

        //Copy validmoves
        var i =0;
        for(col in template.validmoves) {
            validmoves[i].clear()
            for (move in col) {
                validmoves[i].add(move)
            }
            i++

        }
    }

}

