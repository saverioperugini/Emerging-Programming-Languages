/* --------------------------------------------------------------------
/
/      filename:  game.kt
/
/   description:  Implements Game Class
/
/        author:  Patnaik, Ajay J.
/       Copyright (c) 2019 Ajay Patnaik, University of Dayton
-------------------------------------------------------------------------- */

class Game {

    var isRunning: Boolean = true
    var board = Board()

    var depth = 4

    var iterations = 0

    var turn = 1
    var round = 2

    fun run() {
        println("Enter 'exit' to exit the game early.")
        println("Please enter a difficulty 1-9. ")
        val input = readLine()!!

        depth = input.toInt()

        while (isRunning) {
            board.printBoard()
            board.checkWin()

            if (turn == 1) {
                print("Player 1's turn. What column would you like to place your piece? ")

                val input = readLine()!!

                if (input == "exit") {
                    isRunning = false
                    break
                }

                if(input.toInt() > board.cols) {
                    println("Column not valid. Please try again!")
                    continue
                }

                if(board.checkPiece(input.toInt())) {
                    board.playPiece(turn, input.toInt())
                } else {
                    println("Column not valid. Please try again!")
                    continue
                }
            } else {
                println("Player 2's turn.")
                round = 2

                computerMove()
            }

            val win = board.checkWin()

            if(win != 0) {
                if(win == 1) {
                    println("Player 1 Wins!")
                } else {
                    println("Player 2 Wins!")
                }

                board.printBoard()
                break
            }

            when (turn == 1) {
                true -> turn = 2
                false -> turn = 1
            }

        }
    }

    fun computerMove() {
        if (board.score() != board.score && board.score() != -board.score && !board.isFull()) {
            var move = maximize(board, depth)
            board.playPiece(2, move[0]+1)
        }
    }


    fun maximize(b: Board, d: Int): Array<Int> {
        var s = b.score()

        var r = arrayOf<Int>()
        r += -1
        r += s

        if(b.isFinished(d, s)) return r

        var max = arrayOf<Int>()
        max += -1
        max += -99999

        for (c in 0 until b.cols) {
            var newBoard = Board()
            newBoard.state = b.copy()

            if(newBoard.checkPiece(c+1)) {
                iterations++

                if(round == 1) {
                    newBoard.playPiece(1, c+1)
                    round = 2
                } else if (round == 2) {
                    newBoard.playPiece(2, c+1)
                    round = 1
                }

                var nextMove = minimize(newBoard, d-1)
                 if (max[0] == -1 || nextMove[1] > max[1]) {
                    max[0] = c
                    max[1] = nextMove[1]
                }
            }
        }

        return max
    }

    fun minimize(b: Board, d: Int): Array<Int> {
        var s = b.score()

        var r = arrayOf<Int>()
        r += -1
        r += s

        if(b.isFinished(d, s)) return r

        var min = arrayOf<Int>()
        min += -1
        min += 99999

        for (c in 0 until b.cols) {
            var newBoard = Board()
            newBoard.state = b.copy()

            if(newBoard.checkPiece(c+1)) {
                iterations++

                if(round == 1) {
                    newBoard.playPiece(1, c+1)
                    round = 2
                } else if (round == 2) {
                    newBoard.playPiece(2, c+1)
                    round = 1
                }

                var nextMove = maximize(newBoard, d-1)
                if (min[0] == -1 || nextMove[1] < min[1]) {
                    min[0] = c
                    min[1] = nextMove[1]
                }
            }
        }

        return min
    }
}