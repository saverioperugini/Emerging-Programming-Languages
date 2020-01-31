//******************************************************************************
//
//      filename:  HomePage.fsx
//
//      description:  Page for project info and basic usage
//
//       author:  Berkshire, Tyler P.
//
//       Copyright (c) 2019 Tyler P. Berkshire, University of Dayton
//******************************************************************************

#load "Shared.fsx"

open Fable.Core.JsInterop
open Fable.React
open Fable.React.Props
open Shared

let HomePage =
    FunctionComponent.Of (
        (fun () -> div [ClassName "home"] [
                        img [ Src "./smashsearch.png";]
                        br []
                        br []
                        h1 [] [str "Welcome to Super Smash Search!"]
                        br []
                        br []
                        button [] [ A (Route.Tournaments) [str "Tournaments"] ]
                   ]
        )
        , "HomePage")
    
exportDefault HomePage
