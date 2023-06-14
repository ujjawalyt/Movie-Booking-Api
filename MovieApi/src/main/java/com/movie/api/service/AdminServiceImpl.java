package com.movie.api.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.api.constant.AppConstant;
import com.movie.api.dto.AdminDto;
import com.movie.api.dto.AdminWalletsDto;
import com.movie.api.dto.ManagerDto;
import com.movie.api.dto.RolesDto;
import com.movie.api.dto.UserWalletsDto;
import com.movie.api.dto.UsersDto;
import com.movie.api.exception.AdminNotFoundException;
import com.movie.api.exception.RoleNotFoundException;
import com.movie.api.exception.UserNotFoundException;
import com.movie.api.exception.WalletAlreadyExistsException;
import com.movie.api.model.Address;
import com.movie.api.model.Admin;
import com.movie.api.model.Manager;
import com.movie.api.model.MovieCompanyWallet;
import com.movie.api.model.Roles;
import com.movie.api.model.User;
import com.movie.api.model.UserWallet;
import com.movie.api.repository.AdminRepo;
import com.movie.api.repository.CompanyWalletRepo;
import com.movie.api.repository.ManagerRepo;
import com.movie.api.repository.RolesRepo;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminRepo adminRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RolesRepo rolesRepo;
	
	@Autowired
	private  ManagerRepo managerRepo;
	@Autowired
	private CompanyWalletRepo companyWalletRepo;

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

	@Override
	public AdminWalletsDto createWalletByAdmin(AdminWalletsDto walletsDto, Integer managerId)
			throws AdminNotFoundException, WalletAlreadyExistsException {
		
		
		Manager manger = managerRepo.findById( managerId)
				.orElseThrow(() -> new  AdminNotFoundException("User not found with ID: " + managerId));

		boolean walletExists = companyWalletRepo.existsByManager(manger);
		if (walletExists) {
			throw new WalletAlreadyExistsException("Wallet already exists for user with ID: " + managerId);
		}

		MovieCompanyWallet wallets = modelMapper.map(walletsDto, MovieCompanyWallet.class);
		wallets.setManager(manger);
		MovieCompanyWallet saveWallet =	companyWalletRepo.save(wallets);
		AdminWalletsDto walletsDto2 = modelMapper.map(saveWallet, AdminWalletsDto.class);
	
		ManagerDto managerDto = modelMapper.map(manger, ManagerDto.class);
		 walletsDto2.setManagerDto(managerDto);
		return walletsDto2;
		
		
	}
	
	
}
