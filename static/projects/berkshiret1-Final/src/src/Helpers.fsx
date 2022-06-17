//******************************************************************************
//
//      filename:  Helpers.fsx
//
//      description:  Supporting functions
//
//       author:  Berkshire, Tyler P.
//
//       Copyright (c) 2019 Tyler P. Berkshire, University of Dayton
//******************************************************************************

#load "Shared.fsx"

open Thoth.Json
open Elmish
open Shared
open System

/// Sort tournaments by nearest starting time
let sortByUpcoming tournaments =
    tournaments
    |> Array.sortBy (fun (_, _, _, _, startTime, _, _, _, _, _) -> startTime)

/// Sort tournaments by most recent
let sortByPast tournaments =
    tournaments
    |> Array.sortBy (fun (_, _, _, _, startTime, _, _, _, _, _) -> startTime)
    |> Array.rev

/// Convert DateTime object to string
let getDate startTime =
    let date = DateTimeOffset.FromUnixTimeSeconds(startTime).DateTime
    date.ToLongDateString()

/// Decode JSON into an array of tournaments
let getTournaments (dispatch: Dispatch<Msg>) =
    let eventDecoder =
        Decode.object (fun get ->
            get.Required.Field "name" Decode.string,
            get.Required.Field "numEntrants" Decode.int)
        |> Decode.array
    let decoder =
        Decode.object (fun get ->
            get.Required.Field "id" Decode.int,
            get.Required.Field "name" Decode.string,
            get.Required.Field "city" Decode.string,
            get.Required.Field "addrState" Decode.string,
            get.Required.Field "startAt" Decode.int64,
            get.Required.Field "venueAddress" Decode.string,
            get.Required.Field "imageURL" Decode.string,
            get.Required.Field "slug" Decode.string,
            get.Required.Field "primaryContact" Decode.string,
            get.Required.Field "events" eventDecoder)
        |> Decode.array
    
    Fetch.fetch "/tournaments.json" []
    |> Promise.bind (fun response -> response.text())
    |> Promise.map (fun json ->
        let result = Decode.fromString decoder json
        match result with
        | Ok tournaments ->
            Msg.TournamentsLoaded tournaments
            |> dispatch
        | Error err ->
            Msg.FailedToLoad err
            |> dispatch
    )
    |> Promise.catchEnd (fun ex ->
        Msg.FailedToLoad (ex.ToString())
       |> dispatch
    )
