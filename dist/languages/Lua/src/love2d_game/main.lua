local _SW = 800
local _SH = 600

local player = {
   x    = _SW/2,
   y    = _SH/2,
   size = 32
}

function love.draw()
    love.graphics.rectangle('fill',
                     player.x - player.size/2,
                     player.y - player.size/2,
                     player.size,
                     player.size)
end

function love.update(dt)
   if     love.keyboard.isDown('w') then player.y = player.y - 2
   elseif love.keyboard.isDown('s') then player.y = player.y + 2
   end
   if     love.keyboard.isDown('a') then player.x = player.x - 2
   elseif love.keyboard.isDown('d') then player.x = player.x + 2
   end
end
