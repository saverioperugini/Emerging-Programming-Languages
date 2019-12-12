//*******************************************************************************
//
//      filename:  actor.fs
//
//   description:  Solution to homework problem 3 - Actor Model of Concurrency
//
//        author:  Berkshire, Tyler P.
//       Copyright (c) 2019 Tyler Berkshire, University of Dayton
//******************************************************************************

module actor
open System
open System.Threading

type MessageBasedCounter () = 
   static let updateState (count,sum) msg =                
      let newSum = sum + msg
      let newCount = count + 1
      printfn "Number of additions: %i. Sum is: %i" newCount newSum
      let rand = new Random()
      let ms = rand.Next(1,10)
      Thread.Sleep ms                                 // Emulate a short delay
      (newCount,newSum)

   static let agent = MailboxProcessor.Start(fun inbox -> 
      let rec messageLoop oldState = async{
         let! msg = inbox.Receive()                         // Read a message
         let newState = updateState oldState msg
         return! messageLoop newState                       // Infinite loop
         }
      messageLoop (0,0)                                     // Start the loop
      )

   static member Add x = agent.Post x                       // Static keyword means an instance of this type is not required to call this function

let makeTask funct id = async {
   printfn "Task %i created" id
   funct id
   }
 
let run() =
    [1..5]
       |> List.map (fun x -> makeTask MessageBasedCounter.Add x)
       |> Async.Parallel
       |> Async.RunSynchronously
       |> ignore
    Console.ReadLine() |> ignore