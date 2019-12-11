local fact_co = coroutine.create(function(n)
   local total = 1
   for i=1,n do
      total = total * i
   end
   coroutine.yield(total)
end)

print(coroutine.resume(fact_co, 5))
