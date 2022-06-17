local counter = {
   val = 10,
   inc = function(self)
      self.val = self.val + 1
   end
}

print(counter.val)
counter.inc(counter)
counter:inc() -- syntactic sugar
print(counter.val)
