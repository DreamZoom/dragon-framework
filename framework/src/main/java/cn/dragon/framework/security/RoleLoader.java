package cn.dragon.framework.security;

import java.util.List;

public interface RoleLoader {
    List<RoleDetails> load(Token token) throws Exception;
}
