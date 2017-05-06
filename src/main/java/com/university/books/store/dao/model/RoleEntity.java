package com.university.books.store.dao.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Aleksandr on 5/6/2017.
 */
@Entity
@Table(name = "Role", schema = "public", catalog = "postgres")
public class RoleEntity {
    private int roleId;
    private String description;
    private Collection<UserEntity> usersByRoleId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleEntity that = (RoleEntity) o;

        if (roleId != that.roleId) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleId;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @ManyToMany
    @JoinTable(name = "User_role",
            joinColumns = @JoinColumn(name="role_id"),
            inverseJoinColumns = @JoinColumn(name="user_id"))
    public Collection<UserEntity> getUsersByRoleId() {
        return usersByRoleId;
    }

    public void setUsersByRoleId(Collection<UserEntity> usersByRoleId) {
        this.usersByRoleId = usersByRoleId;
    }
}
