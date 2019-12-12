//******************************************************************************
//
//      filename:  TournamentPage.fsx
//
//      description:  Page for tournament list display
//
//       author:  Berkshire, Tyler P.
//
//       Copyright (c) 2019 Tyler P. Berkshire, University of Dayton
//******************************************************************************

#load "Shared.fsx"
#load "Helpers.fsx"

open Fable.Core.JsInterop
open Fable.React
open Fable.React.Props
open System
open Shared
open Helpers

let TournamentsPage =
    FunctionComponent.Of (
        (fun () ->
            let model = useModel()
            let dispatch = useDispatch()
            
            let upcomingTournaments =
                model.Tournaments
                |> sortByUpcoming
                |> Array.toList
                |> List.map (fun (id, name, _, _, startTime, address, imageURL, _, _, _) ->
                    let current = DateTime.Now
                    let start = DateTimeOffset.FromUnixTimeSeconds(startTime).DateTime
                    if DateTime.Compare(start, current) >= 0 then
                        a [Href "#";
                           OnClick (fun ev -> ev.preventDefault(); Msg.Navigate (Route.Detail id) |> dispatch);
                           Key !!id] [
                            li [ClassName "tournaments"] [
                                img [ClassName "tournaments"; Src imageURL]
                                h3 [ClassName "tournaments"] [str name]
                                p [ClassName "tournaments"] [ str address ]
                                p [] [str (getDate(startTime))]
                                hr []
                            ]
                        ]
                    else
                        span [Key !!id] [str "I'm hidden"]
                )
                
            let pastTournaments =
                model.Tournaments
                |> sortByPast
                |> Array.toList
                |> List.map (fun (id, name, _, _, startTime, address, imageURL, _, _, _) ->
                    let current = DateTime.Now
                    let start = DateTimeOffset.FromUnixTimeSeconds(startTime).DateTime
                    if DateTime.Compare(start, current) < 0 then
                        a [Href "#";
                           OnClick (fun ev -> ev.preventDefault(); Msg.Navigate (Route.Detail id) |> dispatch);
                           Key !!id] [
                            li [ClassName "tournaments"] [
                                img [ClassName "tournaments"; Src imageURL]
                                h3 [ClassName "tournaments"] [str name]
                                p [ClassName "tournaments"] [ str address ]
                                p [] [str (getDate(startTime))]
                                hr []
                            ]
                        ]
                    else
                        span [Key !!id] [str "I'm hidden"]
                )
                
            div [ClassName "tournaments"] [
                h1 [] [str "Upcoming"]
                ul [ClassName "tournaments"] [ ofList upcomingTournaments ]
                h1 [ClassName "past"] [str "Past"]
                ul [ClassName "tournaments"] [ ofList pastTournaments ]
                br []
                button [] [ A Route.Root [str "Return To Home"] ]
                br []
                br []
                br []
                br []
            ]
        )
        , "TournamentsPage")
    
exportDefault TournamentsPage