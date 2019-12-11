function makecounter(in::Int)
   c = in
   iterat = function()
      c = c +1
      return c
   end
        println("made counter")
   return iterat
end

function makecounter()
   return makecounter(0)
end

count1 = makecounter()

count2 = makecounter(10)

function printcount(i::Function, num::Int)
    for c in 1:num
        println(i())
    end
end

printcount(count1, 4)
printcount(count2, 4)
typeof(count1)
