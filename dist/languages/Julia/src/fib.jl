@everywhere function fib_parallel(n::Int)
   if n<35
       return fib(n)
   else 
      x = @spawn fib_parallel(n-1)
      y = fib_parallel(n-2)
      return fetch(x) + y
   end
end

@everywhere function fib(n::Int)
   if n<2
       return n
   else 
      return fib(n-1) + fib(n-2)
   end
end
