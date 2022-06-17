//*******************************************************************************
//
//      filename:  code.fs
//
//   description:  Code examples from Introduction to F# language html page
//
//        author:  Berkshire, Tyler P.
//       Copyright (c) 2019 Tyler Berkshire, University of Dayton
//******************************************************************************

// Core F# //

match functionReturn with
   | Some returnValue -> printfn "%A" returnValue
   | None -> printfn "We found an error case"

   
match list with
   | [] -> printfn "Empty list" // empty list
   | [x] -> printfn "Only %A" x // only one item in the list
   | x::y::tail -> printfn "First %A, Second %A, Tail %A" x y tail // at least two items in the list
   

   
// Algebraic Types //

open System
type Person<'a> = { FirstName: string; LastName: string; Weight: 'a } // quote denotes generic type
let main() =
   let Tyler = { FirstName = "Tyler"; LastName = "Berkshire"; Weight = 145 } // construct
   let TylerB = { FirstName = "Tyler"; LastName = "Berkshire"; Weight = "145" }
   printfn "%s\'s last name is %s and weighs %A" Tyler.FirstName Tyler.LastName Tyler.Weight // deconstruct
   printfn "%s\'s last name is %s and weighs %A" TylerB.FirstName TylerB.LastName TylerB.Weight
   let { Weight = 145.50 } = Tyler // error: cannot change generic once assigned

   
type Tree<'a> = Leaf | T of Tree<'a> * 'a * Tree<'a>
let main() =
   let leaf1 = T(Leaf)
   let leaf2 = T(Leaf)
   let tree = T(leaf1, "Tyler", leaf2)

   
let list1 = [ 1; 2; 3; 4; 5 ] // list construction
let list2 = [ 1 .. 5 ] // using ranges
let list3 = 20 :: list2 // cons
let list4 = list3 @ [ 6; 7; 8 ] // append
let list5 = [ for x in 1 .. 10 do yield (x * x) ] // generator comprehension (with lazy eval)


let tuple1 = ("CPS", 452) // type string * int
let tuple2 = (452, "CPS") // int * string /= string * int
fst tuple1 // "CPS"
snd tuple1 // 452
let tuple3 = ("CPS", 452, true) // cannot use fst or snd on non-pair tuples
tuple3.GetHashCode() // tuples can be used as dictionary keys w/ automatically defined hash values



// Metaprogramming //

let expr : Expr<'T> = <@ 1 + 1 @> // 'T will be int in this case


let expr2 : Expr = <@@ 1 + 1 @@> // The resulting Expr is the raw non-generic type


<@ let f x = x + 10 in f 20 @>
<@
   let f x = x + 10
   f 20
@>


<@ let f x = x + 10 @>


<@ 1 + %expr @>


<@@ 1 + %%expr @@>


open System // (Microsoft, 2018)
open Microsoft.FSharp.Quotations.Patterns
open Microsoft.FSharp.Quotations.DerivedPatterns
let printQ expr =
   let rec print expr =
      match expr with
      | Application(expr1, expr2) -> // Function application
         print expr1
         printf " "
         print expr2
      | SpecificCall <@@ (+) @@> (_, _, exprList) -> // Matches a call to +
         print exprList.Head
         printf " + "
         print exprList.Tail.Head
      | Call(_, methodInfo, exprList) ->
         printf "%s.%s(" methodInfo.DeclaringType.Name methodInfo.Name
         if (exprList.IsEmpty) then printf ")" else
         print exprList.Head
         for expr in exprList.Tail do
            printf ","
            print expr
         printf ")"
      | Int32(n) ->
         printf "%d" n
      | Lambda(param, body) -> // Lambda expression
         printf "fun (%s:%s) -> " param.Name (param.Type.ToString())
         print body
      | Let(var, expr1, expr2) -> // Let binding
         if (var.IsMutable) then
            printf "let mutable %s = " var.Name
         else
            printf "let %s = " var.Name
         print expr1
         printf " in "
         print expr2
      | PropertyGet(_, propOrValInfo, _) ->
         printf "%s" propOrValInfo.Name
      | String(str) ->
         printf "%s" str
      | Value(value, typ) ->
         printf "%s" (value.ToString())
      | Var(var) ->
         printf "%s" var.Name
      | _ -> printf "%s" (expr.ToString())
      print expr
      printfn ""

let a = 2
let exprLambda = <@ fun x -> x + 1 @>> // type (int -> int)
let exprCall = <@ a + 1 @> // type unit
let exprUnimplemented = <@ a - 1 @>
printQ exprLambda // fun (x:System.Int32) -> x + 1
printQ exprCall // a + 1
printQ exprUnimplemented // Operators.op_Subtraction(a,1)
printQ <@@ let f x = x + 10 in f 10 @@> // let f = fun (x:System.Int32) -> x + 10 in f 10



// Piping, Composiiton, and Currying //

let list = [1 .. 10 ] |> Seq.filter (fun x -> x % 2 <> 0)
            |> Seq.map (fun x -> x * x)
            |> Seq.sum // 165

			
let composed = Seq.filter (fun x -> x % 2 <> 0)
               >> Seq.map (fun x -> x * x)
               >> Seq.sum
composed [1 .. 10] // 165


foo (fun x -> x |> bar |> baz) // Explicit piping
foo (bar >> baz) // Function composition


let printTwo(a,b) = printfn "%A and %A" a b // Uncurried (int * int -> unit)

let printTwoCurried a = // Curried (int -> int -> unit)
   let printTwoSecond b =
      printfn "%A and %A" a b
   printTwoSecond
   
   
let filterEvens = List.filter (fun x -> x % 2 = 0)
filterEvens [ 1 .. 5 ] // [2;4]


let replace oldStr newStr (s:string) = s.Replace(oldValue = oldStr, newValue = newStr) // Input string is now the last parameter
let result = // "452"
   "CPS"
   |> replace "CPS" "452"
   
   
   
// Asynchronous, Concurrent, and Parallel Programming //

open System
let timerWithAsync =
   let timer = new System.Timers.Timer(2000.0)
   let timerEvent = Async.AwaitEvent (timer.Elapsed) |> Async.Ignore // Create async workflow and ignore its output

   timer.Start() // Start async work

   // Do some other computations

   Async.RunSynchronously timerEvent // Block until async event completed
   
   
let otherWorkflow = async { // Expressions in async{} can be executed in the background
   do! Async.Sleep 2000
   }
let nestedWorkflow = async {
   let! childWorkflow = Async.StartChild otherWorkflow // Start

   do! Async.Sleep 100 // Do some work

   let! result = childWorkflow // Wait for the child
   }
Async.RunSynchronously nestedWorkflow


open System
open System.Threading
let cancellationSource = new CancellationTokenSource() // Creates cancellation soruce
Async.Start (testWorkflow, cancellationSource.Token) // Attach the token to the workflow
cancellationSource.Cancel() // Cancel the workflow


let agent = MailboxProcessor.Start(fun inbox->
   let rec messageLoop() = async {
      let! msg = inbox.Receive() // Read a message
      printfn "Message is: %s" msg
      return! messageLoop() // Infinite loop
      }
   messageLoop()
   )
agent.Post "CPS"
agent.Post "452"



// Miscellaneous //

let x = 100
let result = lazy (x + 10) // lazy freezes the expression and Force evaluates it
printfn "%i" (result.Force())// 110


let seq1 = seq { for x in 1 .. 10 do yield x * x } // seq [1; 4; 9; 16; ...]
let seq2 = seq { for x in 1 .. 10 -> x * x } // equivalent


let simpleClosure =
   let scope = "old scope"
   let enclose() =
      sprintf "%s" scope
   let scope = "new scope"
   sprintf "%s --- %s" scope (enclose())

printfn "%A" simpleClosure // "new scope --- old scope"


let factTail x =
   let rec factTailHelp = x acc =
      if x <= 1 then acc
      else factTailHelp (x - 1) (acc * x)
   factTailHelp x 1