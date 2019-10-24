#include <lua.h>
#include <lauxlib.h>
#include <lualib.h>
#include <stdio.h>

void load (char *filename, int *width, int *height) {
  lua_State *L = lua_open();
  luaopen_base(L);
  luaopen_io(L);
  luaopen_string(L);
  luaopen_math(L);

  if (luaL_loadfile(L, filename) || lua_pcall(L, 0, 0, 0))
    error(L, "cannot run configuration file: %s",
             lua_tostring(L, -1));

  lua_getglobal(L, "width");
  lua_getglobal(L, "height");
  if (!lua_isnumber(L, -2))
    error(L, "`width' should be a number\n");
  if (!lua_isnumber(L, -1))
    error(L, "`height' should be a number\n");
  *width = (int)lua_tonumber(L, -2);
  *height = (int)lua_tonumber(L, -1);

  lua_close(L);
}

int main(int argc, char** argv)
{
  int width, height;

  if (argc < 2)
  {
    printf("%s: no supplied init script\n", argv[0]);
    return 1;
  }

  load(argv[1], &width, &height);
  printf("loaded width=%d, height=%d from lua script", width, height);
}
