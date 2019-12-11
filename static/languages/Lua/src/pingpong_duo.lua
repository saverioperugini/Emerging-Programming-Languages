ping = coroutine.create(function()
   while true do
      print('ping')
      coroutine.resume(pong)
   end
end)

pong = coroutine.create(function()
   while true do
      print('pong')
      coroutine.yield()
   end
end)

coroutine.resume(ping)
