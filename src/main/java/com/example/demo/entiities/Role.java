package com.example.demo.entiities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ROLE")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE_TYPE")
    private EnumRoles roleType;

    @ManyToMany
    private Set<Renter> rentersRoleSet= new HashSet<>();

    public Role() { }
    public Role(EnumRoles roleType) {
        this.roleType = roleType;
    }

    /////////////////////////getters y setters///////////////////////

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public EnumRoles getRoleType() {
        return roleType;
    }
    public void setRoleType(EnumRoles roleType) {
        this.roleType = roleType;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleType=" + roleType +
                ", rentersRoleSet=" + rentersRoleSet +
                '}';
    }
}
