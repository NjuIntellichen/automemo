package com.intellichens.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by raychen on 2017/3/23.
 */
@Entity
@Table(name = "user", schema = "automemo_test", catalog = "")
public class UserModel {
    private int id;
    private String userName;
    private String userAvatar;
    private String password;
    private String name;
    private String phone;
    private String email;
    private Date createAt;
    private List<RecordModel> records;
    private List<UserGroupModel> user_groups;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_name", nullable = true, length = 255)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "user_avatar", nullable = true, length = 255)
    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "phone", nullable = false, length = 255)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "create_at", nullable = false)
    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserModel userModel = (UserModel) o;

        if (id != userModel.id) return false;
        if (userName != null ? !userName.equals(userModel.userName) : userModel.userName != null) return false;
        if (userAvatar != null ? !userAvatar.equals(userModel.userAvatar) : userModel.userAvatar != null) return false;
        if (password != null ? !password.equals(userModel.password) : userModel.password != null) return false;
        if (name != null ? !name.equals(userModel.name) : userModel.name != null) return false;
        if (phone != null ? !phone.equals(userModel.phone) : userModel.phone != null) return false;
        if (email != null ? !email.equals(userModel.email) : userModel.email != null) return false;
        if (createAt != null ? !createAt.equals(userModel.createAt) : userModel.createAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userAvatar != null ? userAvatar.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "userId")
    public List<RecordModel> getRecords() {
        return records;
    }

    public void setRecords(List<RecordModel> records) {
        this.records = records;
    }

    @OneToMany(mappedBy = "userId")
    public List<UserGroupModel> getUser_groups() {
        return user_groups;
    }

    public void setUser_groups(List<UserGroupModel> user_groups) {
        this.user_groups = user_groups;
    }
}
