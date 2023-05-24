package com.movie.api.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.api.constant.AppConstant;
import com.movie.api.dto.AdminDto;
import com.movie.api.dto.ManagerDto;
import com.movie.api.dto.RolesDto;
import com.movie.api.exception.AdminNotFoundException;
import com.movie.api.exception.ManagerNotFoundException;
import com.movie.api.exception.RoleNotFoundException;
import com.movie.api.model.Address;
import com.movie.api.model.Admin;
import com.movie.api.model.Manager;
import com.movie.api.model.Roles;
import com.movie.api.repository.AdminRepo;
import com.movie.api.repository.ManagerRepo;
import com.movie.api.repository.RolesRepo;

@Service
public class ManagerServiceImpl implements ManagerService {

	
	@Autowired
	private ManagerRepo managerRepo;
	@Autowired
	private RolesRepo rolesRepo;
	@Autowired
	private AdminRepo adminRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public ManagerDto AdminRegisterManager(Integer adminId, ManagerDto managerDto)
			throws  RoleNotFoundException, AdminNotFoundException {
	
		Optional<Admin> admin = adminRepo.findById(adminId);
		
		if(admin.isEmpty()) {
			throw new AdminNotFoundException("AdminNot Found with this admin id");
		}
		
		
		Manager manager = modelMapper.map(managerDto, Manager.class);
		    Address address = new Address();
		    address.setCity(managerDto.getAddress().getCity()); 
		    address.setCountry(managerDto.getAddress().getCountry()); 
		    address.setPincode(managerDto.getAddress().getPincode()); 
		    address.setStreet(managerDto.getAddress().getStreet()); 

		    manager.setAddress(address);
		    
		   
		    Roles managerRole = rolesRepo.findById(AppConstant.MANAGER_USER)
		            .orElseThrow(() -> new RoleNotFoundException("Manager role not found"));
		    
		    manager.getRoles().add(managerRole);
		    
		    manager.setAdmin(admin.get());
		  
		 Manager savedManager = managerRepo.save(manager);
		    
		 
		    ManagerDto savedManagerDto = modelMapper.map(savedManager, ManagerDto.class);
		    RolesDto managerRoleDto = modelMapper.map(managerRole, RolesDto.class);
//		    savedManagerDto.getRoles().add(managerRoleDto);

		   
		    return savedManagerDto;
		
		
		
	}
	
	
	
	
}
