package com.movie.api.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Manager extends AbstractUser  implements UserDetails{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer managerId;
	
	@OneToMany(mappedBy = "manager",cascade =CascadeType.ALL )
	List<Theater> theaters=new ArrayList<>();
	
	
	@OneToMany(mappedBy = "manager",cascade =CascadeType.ALL )
	List<ScreenTime>  screenTimes=new ArrayList<>();
	
	@OneToMany(mappedBy = "manager",cascade =CascadeType.ALL )
	List<ScreenSeats>  screenSeats=new ArrayList<>();
	
	@OneToMany(mappedBy = "manager",cascade =CascadeType.ALL )
	List<Movie>  movie=new ArrayList<>();
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "adminId")
	private Admin admin;
	
	@ManyToMany
	@JoinTable(name="ManagerRoles",
	joinColumns = @JoinColumn(name="manager_Id", referencedColumnName = "managerId"),
	inverseJoinColumns = @JoinColumn(name="role_Id", referencedColumnName = "roleId"))
	private Set<Roles> roles=new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream().map((role)->new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
		 
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
}

