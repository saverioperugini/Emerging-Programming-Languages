//*******************************************************************************
//
//      filename:  async.fs
//
//   description:  Solution to homework problem 1 - Asynchronous Workflows
//
//        author:  Berkshire, Tyler P.
//       Copyright (c) 2019 Tyler Berkshire, University of Dayton
//******************************************************************************

module asyncWorkflow
open System
open System.Net
open Microsoft.FSharp.Control.WebExtensions

let urls = [ "http://www.google.com"
             "http://www.udayton.edu"
             "http://www.youtube.com" ]

let fetchAsync(url:string) =
    async {
        try
            let uri = new System.Uri(url)
            let webClient = new WebClient()
            let! html = webClient.AsyncDownloadString(uri)
            printfn "Read %d characters for %s" html.Length url
        with
            | ex -> printfn "%s" (ex.Message);
    }

let run() =
    urls
    |> Seq.map fetchAsync
    |> Async.Parallel
    |> Async.RunSynchronously
    |> ignore
    printfn "All urls fetched"
    Console.ReadLine() |> ignore
