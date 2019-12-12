//*******************************************************************************
//
//      filename:  binary-tree.fs
//
//   description:  Solution to homework problem 2 - Sequences/Types
//
//        author:  Berkshire, Tyler P.
//       Copyright (c) 2019 Tyler Berkshire, University of Dayton
//******************************************************************************

module binaryTree
open System

type Tree<'a> =
   | Tree of 'a * Tree<'a> * Tree<'a>
   | Leaf of 'a

// inorder : Tree<'a> -> seq<'a>
let rec inorder tree =
    seq {
        match tree with
            | Tree(x, left, right) ->
                yield! inorder left
                yield x
                yield! inorder right
            | Leaf x -> yield x
    }

let run() =
    // Test Case 1
    let tree1 = Tree(6, Tree(2, Leaf(1), Leaf(3)), Leaf(9))
    let seq1 = inorder tree1
    let first = Seq.head seq1
    let last = Seq.last seq1
    printfn "First element: %A" first                         // 1
    printfn "Last element: %A" last                           // 9

    // Test Case 2
    let tree2 = Tree(10, Tree(4, Leaf(2), Leaf(5)), Tree(15, Tree(13, Leaf(12), Leaf(14)), Leaf(16)))
    let trav2 = inorder tree2
    let first2 = Seq.head trav2
    let last2 = Seq.last trav2
    printfn "First element: %A" first2                         // 2
    printfn "Last element: %A" last2                           // 16

    Console.ReadLine() |> ignore