/*
   CISC 260, winter 2018
   Queen's University, Kingston, Ontario
   author: TONG CHEN 
*/

% Move family from North to South
% FName - The name of the family (as given in a family fact) -- must be bound 
% MaxTime - A maximum time for the total crossing -- must be bound 
% A list of moves that would take the family across the river -- must be unbound 
% The time this list of moves would take -- must be unbound
moveFamily(FName, MaxTime, Moves, Time):-
  % Initially, all family members are on North side
  family(FName, North),
  % Move family and after move, the list moves and time stored in Moves1
  moveFamily(North, [], Moves1),
  % Find all times in the Moves1
  findall(TTime, member((TTime/_), Moves1), LTime),
  % Sum up all the times in entire move
  sumlist(LTime, Time),
  % Only those moves with time less than or equal to MaxTime will be selected
  Time < (MaxTime+1),
  % find all moves
  findall(TMoves, member((_/TMoves), Moves1), Moves).

%Move one pair of people and name in alphabetical order
moveFamily([P1/T1, P2/T2], _, [T/(P1+P2)]):-
  P1 @< P2,
  T is max(T1, T2).  
moveFamily([P1/T1, P2/T2], _, [T/(P2+P1)]):-
  P2 @< P1,
  T is max(T1, T2).
  
moveFamily(North, South, [N2S_T/(P1+P2),S2N_T/P3|Moves]):-
  % Move from North to South, two person each time
  select(P1/T1, North, MNorth1),
  select(P2/T2, MNorth1, MNorth2),
  P1 @< P2,
  N2S_T is max(T1, T2),
  % Move South to North, one person each time
  select(P3/S2N_T, [P1/T1,P2/T2|South], MSouth),
  % Recursively to move the rest on North side to South
  moveFamily([P3/S2N_T|MNorth2], MSouth, Moves).
