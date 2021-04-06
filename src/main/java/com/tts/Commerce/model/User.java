package com.tts.Commerce.model;

import java.util.Collection;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;
   
    @NotNull(message = "Username may not be null.")
    private String username;
   
    @Size (min = 8, message = "Password should have at least 8 characters")
    @NotNull(message = "Password may not be null.")
    private String password;
    
    @ElementCollection
    @CollectionTable(name = "Product_quantity", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "quantity")
    @MapKeyColumn(name = "product")
    private Map<Product, Integer> cart;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {     
        return null;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    } 
}
