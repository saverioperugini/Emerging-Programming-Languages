defmodule Counter do
   def loop(count) do
      receive do
         {:next} ->
            IO.puts("Current count: #{count}")
            loop(count + 1)
      end
   end
end

counter1 = spawn(Counter, :loop, [1])
counter2 = spawn(Counter, :loop, [100])

send(counter1, {:next})
send(counter1, {:next})
send(counter2, {:next})
send(counter2, {:next})
send(counter1, {:next})
send(counter2, {:next})
send(counter1, {:next})
