defmodule MakeCounter do
   def makecounter(i,s) do
      c = i
      fn() ->
         c = c + s
      end
   end
end

counter = MakeCounter.makecounter(1,1)
anotherCounter = MakeCounter.makecounter(42,1)

anotherCounter.()
anotherCounter.()
anotherCounter.()
counter.()
anotherCounter.()
counter.()
