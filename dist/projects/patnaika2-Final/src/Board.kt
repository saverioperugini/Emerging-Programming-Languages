/* --------------------------------------------------------------------
/
/      filename:  board.kt
/
/   description:  Implements Board Class
/
/        author:  Patnaik, Ajay J.
/       Copyright (c) 2019 Ajay Patnaik, University of Dayton
-------------------------------------------------------------------------- */

class Board {
    var rows: Int = 6;
    var cols: Int = 7;

    var score = 100000

    var state = arrayOf<Array<Int>>()

    init {
        val r = rows - 1
        val c = cols - 1

        for (i in 0..r) {
            var array = arrayOf<Int>()
            for (j in 0..c) {
                array += 0
            }
            state += array
        }
    }

    fun playPiece(player: Int, col: Int) {
        for(row in rows downTo 1) {
            if(state[row-1][col-1] == 0) {
                state[row-1][col-1] = player
                break
            }
        }
    }

    fun checkPiece(col: Int): Boolean {
        if(state[0][col-1] == 0) {
            return true
        }
        return false
    }


    fun printBoard() {
        println()

        for (col in 1..cols) {
            print(col)
            print(" ")
        }
        println()

        for (col in 1..cols) {
            print("--")
        }

        println()

        for (array in state) {
            for (value in array) {
                print("$value ")
            }
            println()
        }

        println()
    }

    fun checkWin(): Int {
        // Vertical Win
        for (r in state.size-1 downTo 3) {
            for (c in state[0].size-1 downTo 0) {
                if(state[r][c] != 0 && state[r][c] == state[r-1][c] && state[r][c] == state[r-2][c] && state[r][c] == state[r-3][c]) {
                    return state[r][c]
                }
            }
        }

        // Horizontal Win
        for (r in state.size-1 downTo 0) {
            for (c in state[0].size-1 downTo 3) {
                if(state[r][c] != 0 && state[r][c] == state[r][c-1] && state[r][c] == state[r][c-2] && state[r][c] == state[r][c-3]) {
                    return state[r][c]
                }
            }
        }

        // Diagonal Win (positive)
        for (r in 3 until state.size) {
            for (c in 0 until 4) {
                if(state[r][c] != 0 && state[r][c] == state[r-1][c+1] && state[r][c] == state[r-2][c+2] && state[r][c] == state[r-3][c+3]) {
                    return state[r][c]
                }
            }
        }

        // Diagonal Win (negative)
        for (r in 0 until 3) {
            for (c in 0 until 4) {
                if(state[r][c] != 0 && state[r][c] == state[r+1][c+1] && state[r][c] == state[r+2][c+2] && state[r][c] == state[r+3][c+3]) {
                    return state[r][c]
                }
            }
        }

        return 0
    }

    fun scorePosition(row: Int, col: Int, deltaY: Int, deltaX: Int): Int {
        var humanPoints = 0
        var computerPoints = 0

        var nr = row
        var nc = col

        for (r in 0 until 4) {
            if(state[nr][nc] == 1) {
                humanPoints++
            } else if(state[nr][nc] == 2) {
                computerPoints++
            }

            nr += deltaY
            nc += deltaX
        }

        if(humanPoints == 4) {
            return -score
        } else if (computerPoints == 4) {
            return score
        } else {
            return computerPoints
        }
    }

    fun score(): Int {
        var points = 0
        var verticalPoints = 0
        var horizontalPoints = 0
        var diagPosPoints = 0
        var diagNegPoints = 0

        for (r in 0 until rows-3) {
            for (c in 0 until cols) {
                var s = scorePosition(r, c, 1, 0)
                if(s == score) return score
                if(s == -score) return -score
                verticalPoints += s
            }
        }

        for (r in 0 until rows) {
            for (c in 0 until cols-3) {
                var s = scorePosition(r, c, 0, 1)
                if(s == score) return score
                if(s == -score) return -score
                horizontalPoints += s
            }
        }

        for (r in 0 until rows-3) {
            for (c in 0 until cols-3) {
                var s = scorePosition(r, c, 1, 1)
                if(s == score) return score
                if(s == -score) return -score
                diagNegPoints += s
            }
        }

        for (r in 3 until rows) {
            for (c in 0 until cols-3) {
                var s = scorePosition(r, c, -1, 1)
                if(s == score) return score
                if(s == -score) return -score
                diagPosPoints += s
            }
        }

        points = horizontalPoints + verticalPoints + diagNegPoints + diagPosPoints
        return points
    }

    fun isFinished(d: Int, s: Int): Boolean {
        if(d == 0 || s == score || s == -score || isFull()) {
            return true
        }
        return false
    }

    fun isFull(): Boolean {
        for (c in 0 until cols) {
            if (state[0][c] == 0) {
                return false
            }
        }
        return true
    }

    fun copy(): Array<Array<Int>> {
        var newState = arrayOf<Array<Int>>()

        val r = rows - 1
        val c = cols - 1

        for (i in 0..r) {
            var array = arrayOf<Int>()
            for (j in 0..c) {
                array += 0
            }
            newState += array
        }

        for (rs in 0..r) {
            for (cs in 0..c) {
                newState[rs][cs] = state[rs][cs]
            }
        }

        return newState
    }

}