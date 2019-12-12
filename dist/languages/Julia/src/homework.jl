function array_map(f::Function, arr::Array{Float64, 2})
    newarr = similar(arr)
    for i in eachindex(arr)
        newarr[i] = f(arr[i])
    end
    newarr
end

function array_map_parallel(f::Function, arr::Array{Float64,2})
   if workers() == Int64[1]
      throw(error("not enough workers"))
   else
      newarr = arr
      for i in eachindex(arr)
          @spawn newarr[i] = f(arr[i])
       end
   end
   newarr
end

function slowsqrt(num)
   sleep(1.0)
   sqrt(num)
end
