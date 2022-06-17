%Traveling salesman problem where diagraph is assumed
%modified solution from http://stackoverflow.com/questions/8318293/simplified-travelling-salesman-in-prolog

road(paris,rouen,1).
road(paris,lyon,1).
road(lyon,marseille,1).
road(marseille,nice,1).
road(paris,bordeaux,1).
road(paris,caen,1).
road(bordeaux,madrid,1).
road(madrid,cuenca,1).


path(Start, End, Visited, Result) :-
    path(Start, End, [Start], 0, Visited, Result).

path(Start, End, Waypoints, DistanceAcc, Visited, TotalDistance) :-
    road(Start, End, Distance),
    reverse([End|Waypoints], Visited),
    TotalDistance is DistanceAcc + Distance.

%forwards
path(Start, End, Waypoints, DistanceAcc, Visited, TotalDistance) :-
    road(Start, Waypoint, Distance),
    \+ member(Waypoint, Waypoints),
    NewDistanceAcc is DistanceAcc + Distance,
    path(Waypoint, End, [Waypoint|Waypoints], NewDistanceAcc, Visited, TotalDistance).

%backwards
path(Start, End, Waypoints, DistanceAcc, Visited, TotalDistance) :-
    road(Waypoint, Start, Distance),
    \+ member(Waypoint, Waypoints),
    NewDistanceAcc is DistanceAcc + Distance,
    path(Waypoint, End, [Waypoint|Waypoints], NewDistanceAcc, Visited, TotalDistance).
