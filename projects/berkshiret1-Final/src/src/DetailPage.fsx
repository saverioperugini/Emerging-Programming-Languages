//******************************************************************************
//
//      filename:  DetailPage.fsx
//
//      description:  Detail page for a given tournament
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
open Shared
open Helpers

let DetailPage =
    FunctionComponent.Of (
        (fun () ->
            let model = useModel()
            let dispatch = useDispatch()
            
            let tournament =
                model.Tournaments
                |> Array.find (fun (id, name, _, _, startTime, address, _, _, _, _) -> id = model.SelectedTournament)
                |> (fun (id, name, _, _, startTime, address, imageURL, slug, primaryContact, events) ->
                    // Building event table
                    let events =
                        events
                        |> Array.toList
                        |> List.map (fun (name, entrants) ->
                            tr [Key !!name] [
                                td [] [str name]
                                td [] [str (entrants.ToString() + " entrants")]
                            ]
                        )
                    
                    // Info card
                    li [ClassName "detail"] [
                        img [ClassName "detail"; Src imageURL]
                        br []
                        h3 [] [str name]
                        table [ClassName "detail"] [
                            tbody [] [
                                tr [ClassName "detail"] [
                                    td [] [str address]
                                ]
                                tr [ClassName "detail"] [
                                    td [] [str (getDate(startTime))]
                                ]
                                tr [ClassName "detail"] [
                                    td [] [str primaryContact]
                                ]
                            ]
                        ]
                        br []
                        h3 [] [str "Events"]
                        table [ClassName "detail"] [
                            tbody [] [ ofList events ]
                        ]
                        br []
                        button [] [
                            a [Href ("https://smash.gg/" + slug); Target "blank"] [str "View on Smash.gg"]
                        ]
                    ]
                )
            
            // Main layout
            div [] [
                ul [ClassName "detail"] [ tournament ]
                button [ClassName "back"] [ A Route.Tournaments [str "Go Back"] ]
                button [] [ A Route.Root [str "Return To Home"] ]
                br []
                br []
                br []
                br []
            ]
        )
        , "DetailPage")
    
exportDefault DetailPage