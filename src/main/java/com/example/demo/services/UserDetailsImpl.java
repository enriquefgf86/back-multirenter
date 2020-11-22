package com.example.demo.services;

import com.example.demo.entiities.Renter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;
//Esta clase seria la interface la implementacion del UserDetailService de Java , necesaria para los
//procesos de autentificacion y autorizacion .
public class UserDetailsImpl implements UserDetails {

    public static final long serialVersionUID=1L;

    private Long id;
    private String username;
    private String email;
    @JsonIgnore
    private String password;
    //Veaseu que se establecen variable tales como id username y email, asi como se obvia el password
    //con un decoratorJsonignore , por razones de seguridad



    private Collection<? extends GrantedAuthority> authorities;
    //inicializandose una variable llamada authorities de tipo Collecttion, que extenderia
    //del metodo propio d Spring  GrantedAuthority el cual encerraria el set de roles
    //para cada usuario

    public UserDetailsImpl(Long id, String username, String email, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }
    //se establece constructor de la cvlase con las variables previamente inicializadas!!

    public static UserDetailsImpl build(Renter renter) {
        List<GrantedAuthority> authorities = renter.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(renter.getRenterName()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                renter.getId(),
                renter.getRenterName(),
                renter.getRenbterEmail(),
                renter.getRenterPassword(),
                authorities);
    }
 //Inicializandose un segundo  constructor, el cual seria accesado a traves del metodo
    //generico build  para la construccion de la data que traeria el renter autentificado
    //de ahi que se pase como parametro dicha entidad
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }


}
