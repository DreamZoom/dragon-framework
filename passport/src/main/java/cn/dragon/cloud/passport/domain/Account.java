package cn.dragon.cloud.passport.domain;

import cn.dragon.framework.domain.BaseEntity;
import cn.dragon.framework.query.Where;
import cn.dragon.framework.query.WhereOperator;

/***
 * 账户模型
 */
public class Account extends BaseEntity {

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Where(WhereOperator.LIKE)
    private String username;

    private String password;

    @Where(WhereOperator.LIKE)
    private String email;

    @Where(WhereOperator.LIKE)
    private String tell;

    @Where(WhereOperator.LIKE)
    private String nickname;

    private String avatar;
    private String source;
    @Where
    private Integer status;

    public Integer getRoot() {
        return root;
    }

    public void setRoot(Integer root) {
        this.root = root;
    }

    private Integer root;
}
