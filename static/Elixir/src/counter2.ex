defmodule Counter do
   def go(val) do
      send(:first, {:go, :second, val})
   end

   def firstCounter() do
      pid = spawn_link(__MODULE__, :listenFirst, [])
      Process.register(pid, :first)
      pid
   end
   def secondCounter() do
      pid = spawn_link(__MODULE__, :listenSecond, [])
      Process.register(pid, :second)
      pid
   end

   def listenFirst() do
      receive do
      {:go, sender, val} ->
         IO.puts val
         :timer.sleep(1000)
         send(sender, {:go, self(), val + 1})
      end
      listenFirst()
   end
   def listenSecond() do
      receive do
      {:go, sender, val} ->
         IO.puts val
         :timer.sleep(1000)
         send(sender, {:go, self(), val + 1})
      end
      listenSecond()
   end
end

first = Counter.firstCounter()
second = Counter.secondCounter()
Counter.go(1)
