function ping()
   local pong = coroutine.create(function()
      while true do
         print('pong')
         coroutine.yield()
      end
   end)

   for i=1,10 do
      print('ping')
      coroutine.resume(pong)
   end
end

ping()
