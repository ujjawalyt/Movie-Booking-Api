package com.movie.api.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.api.constant.AppConstant;
import com.movie.api.dto.AdminDto;
import com.movie.api.dto.RolesDto;
import com.movie.api.exception.AdminNotFoundException;
import com.movie.api.exception.RoleNotFoundException;
import com.movie.api.model.Address;
import com.movie.api.model.Admin;
import com.movie.api.model.Roles;
import com.movie.api.repository.AdminRepo;
import com.movie.api.repository.RolesRepo;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminRepo adminRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RolesRepo rolesRepo;

	@Override
	public AdminDto registerNewAdmin(AdminDto adminDto) throws AdminNotFoundException,RoleNotFoundException {
	
		Optional<Admin> existingAdminOptional = adminRepo.findByEmail(adminDto.getEmail());
	    if (existingAdminOptional.isPresent()) {
	        throw new AdminNotFoundException("Admin is already present with this email address: " + adminDto.getEmail());
	    }
	    
	    
	    Admin admin = modelMapper.map(adminDto, Admin.class);
	    Address address = new Address();
	    address.setCity(adminDto.getAddress().getCity()); 
	    address.setCountry(adminDto.getAddress().getCountry()); 
	    address.setPincode(adminDto.getAddress().getPincode()); 
	    address.setStreet(adminDto.getAddress().getStreet()); 

	    admin.setAddress(address);
	    
	   
	    Roles adminRole = rolesRepo.findById(AppConstant.ADMIN_USER)
	            .orElseThrow(() -> new RoleNotFoundException("Admin role not found"));
	    
	    admin.getRoles().add(adminRole);
	    
	  
	    Admin savedAdmin = adminRepo.save(admin);
	    
	 
	    AdminDto savedAdminDto = modelMapper.map(savedAdmin, AdminDto.class);
	    RolesDto adminRoleDto = modelMapper.map(adminRole, RolesDto.class);
	    savedAdminDto.getRoles().add(adminRoleDto);

	   
	    return savedAdminDto;
	}
	
	
}
