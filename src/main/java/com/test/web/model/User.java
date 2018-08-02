package com.test.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table user
 *
 * @mbg.generated do_not_delete_during_merge
 * @author Gavin·Yan code generator
 * date:2018/08/02 13:33
 */
public class User implements Serializable {
    /**
     * Database Column Remarks:
     *   用户名
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.user_name
     *
     * @mbg.generated * @author Gavin·Yan code generator
 * date:2018/08/02 13:33
     */
    private String userName;

    /**
     * Database Column Remarks:
     *   昵称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.nick_name
     *
     * @mbg.generated * @author Gavin·Yan code generator
 * date:2018/08/02 13:33
     */
    private String nickName;

    /**
     * Database Column Remarks:
     *   密码
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.password
     *
     * @mbg.generated * @author Gavin·Yan code generator
 * date:2018/08/02 13:33
     */
    private String password;

    /**
     * Database Column Remarks:
     *   是否活跃状态，1为活跃状态，0为冻结状态
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.alive
     *
     * @mbg.generated * @author Gavin·Yan code generator
 * date:2018/08/02 13:33
     */
    private Integer alive;

    /**
     * Database Column Remarks:
     *   用户身份，1为管理员，0为一般用户
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.role
     *
     * @mbg.generated * @author Gavin·Yan code generator
 * date:2018/08/02 13:33
     */
    private Integer role;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.d
     *
     * @mbg.generated * @author Gavin·Yan code generator
 * date:2018/08/02 13:33
     */
    private Double d;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.t
     *
     * @mbg.generated * @author Gavin·Yan code generator
 * date:2018/08/02 13:33
     */
    private Date t;

    private long count;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table user
     *
     * @mbg.generated * @author Gavin·Yan code generator
 * date:2018/08/02 13:33
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.user_name
     *
     * @return the value of user.user_name
     *
     * @mbg.generated * @author Gavin·Yan code generator
 * date:2018/08/02 13:33
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.user_name
     *
     * @param userName the value for user.user_name
     *
     * @mbg.generated * @author Gavin·Yan code generator
 * date:2018/08/02 13:33
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.nick_name
     *
     * @return the value of user.nick_name
     *
     * @mbg.generated * @author Gavin·Yan code generator
 * date:2018/08/02 13:33
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.nick_name
     *
     * @param nickName the value for user.nick_name
     *
     * @mbg.generated * @author Gavin·Yan code generator
 * date:2018/08/02 13:33
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.password
     *
     * @return the value of user.password
     *
     * @mbg.generated * @author Gavin·Yan code generator
 * date:2018/08/02 13:33
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.password
     *
     * @param password the value for user.password
     *
     * @mbg.generated * @author Gavin·Yan code generator
 * date:2018/08/02 13:33
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.alive
     *
     * @return the value of user.alive
     *
     * @mbg.generated * @author Gavin·Yan code generator
 * date:2018/08/02 13:33
     */
    public Integer getAlive() {
        return alive;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.alive
     *
     * @param alive the value for user.alive
     *
     * @mbg.generated * @author Gavin·Yan code generator
 * date:2018/08/02 13:33
     */
    public void setAlive(Integer alive) {
        this.alive = alive;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.role
     *
     * @return the value of user.role
     *
     * @mbg.generated * @author Gavin·Yan code generator
 * date:2018/08/02 13:33
     */
    public Integer getRole() {
        return role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.role
     *
     * @param role the value for user.role
     *
     * @mbg.generated * @author Gavin·Yan code generator
 * date:2018/08/02 13:33
     */
    public void setRole(Integer role) {
        this.role = role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.d
     *
     * @return the value of user.d
     *
     * @mbg.generated * @author Gavin·Yan code generator
 * date:2018/08/02 13:33
     */
    public Double getD() {
        return d;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.d
     *
     * @param d the value for user.d
     *
     * @mbg.generated * @author Gavin·Yan code generator
 * date:2018/08/02 13:33
     */
    public void setD(Double d) {
        this.d = d;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.t
     *
     * @return the value of user.t
     *
     * @mbg.generated * @author Gavin·Yan code generator
 * date:2018/08/02 13:33
     */
    public Date getT() {
        return t;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.t
     *
     * @param t the value for user.t
     *
     * @mbg.generated * @author Gavin·Yan code generator
 * date:2018/08/02 13:33
     */
    public void setT(Date t) {
        this.t = t;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userName=").append(userName);
        sb.append(", nickName=").append(nickName);
        sb.append(", password=").append(password);
        sb.append(", alive=").append(alive);
        sb.append(", role=").append(role);
        sb.append(", d=").append(d);
        sb.append(", t=").append(t);
        sb.append(", count=").append(count);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}