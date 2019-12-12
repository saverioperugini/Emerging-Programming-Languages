Counter = {} -- empty table
Counter.__eq = function(a, b)
    print('our metamethod was called')
    return a.val == b.val and a.step == b.step
end

Counter.new = function(init, step)
    local new_counter = {} -- populate this with kv-pairs and return
   setmetatable(new_counter, Counter) -- we supply our metamethod(s)

    new_counter.val = init
    new_counter.step = step
    new_counter.inc = function(self) -- note need for reference to self
        self.val = self.val + self.step 
    end
    new_counter.show = function(self)
        print(self.val)
    end

   return new_counter
end

local c1 = Counter.new(10, 2)
c1.show(c1) -- 10
c1.inc(c1)
c1:show() -- 12, syntactic sugar: auto-pass reference to self
c1:inc()
c1:show() -- 14

local c2 = Counter.new(14, 2)
print(c1 == c2) -- true

local c3 = Counter.new(10, 2)
print(c2 == c3) -- false

-- no concept of private/public data in tables, we can
-- access and modify counter values directly
c1.inc = function()
    print('we broke the object')
end
c1.inc()
