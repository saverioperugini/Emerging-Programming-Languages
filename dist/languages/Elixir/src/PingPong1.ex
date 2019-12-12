defmodule PingPong do
   def play(count) do
      send(:ping, {:ping, :pong, count})
   end

   def new_ping() do
      pid = spawn_link(__MODULE__, :listenPing, [])
      Process.register(pid, :ping)
      pid
   end

   def new_pong() do
      pid = spawn_link(__MODULE__, :listenPong, [])
      Process.register(pid, :pong)
      pid
   end

   def listenPing do
      receive do
         {:ping, sender, count} ->
            IO.puts("ping")
            :timer.sleep(1000)
            send(sender, {:pong, :ping, count-1})
         {:shutdown} -> exit(:normal)
      end
      listenPing
   end

   def listenPong do
      receive do
         {:pong, sender, count} ->
            IO.puts("pong")
            :timer.sleep(1000)
            cond do
               count > 1 ->
                  send(sender, {:ping, :pong, count-1})
               count == 1 ->
                  send(sender, {:shutdown})
                  exit(:normal)
            end
      end
      listenPong
   end
end
                                      
i = String.to_integer(hd(System.argv()))

Process.flag(:trap_exit, true)
ping = PingPong.new_ping()
pong = PingPong.new_pong()
PingPong.play(i)

receive do
   {:EXIT, ^ping, reason} ->
      IO.puts("ping has exited (#{reason})")
end

receive do
   {:EXIT, ^pong, reason} ->
      IO.puts("pong has exited (#{reason})")
end
