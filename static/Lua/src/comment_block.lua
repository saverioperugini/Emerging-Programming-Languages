local lines = {'/***********************************************************',
'/',
'/      filename:  ' .. vim.buffer().name,
'/',
'/   description:  ',
'/',
'/        author:  Talatinian, Luc',
'/      login id:  SP_17_CPS499_30',
'/',
'/         class:  CPS 499',
'/    instructor:  Perugini',
'/    assignment:  Homework #',
'/',
'/      assigned:  ',
'/           due:  ' .. os.date('%m/%d/%y'),
'/',
'**************************************************************************/',
''}

for lineno,str in ipairs(lines) do
    vim.buffer():insert(str, lineno - 1)
end
