function ping(num::Int)
    println("ping ", num)
    println(consume(pingreply))
end

function pong(num::String)
    while true
        produce(num)
    end
end

pingreply = Task(() -> pong("pong"))

for i in 1:10
    ping(i)
end
